#!/usr/bin/env python3
"""Build platform-specific resource packages into dist/."""
from pathlib import Path
import argparse, hashlib, io, json, os, shutil, subprocess, tempfile, zipfile
import xml.etree.ElementTree as ET
ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / 'dist'

def archive(entries):
    data = io.BytesIO()
    with zipfile.ZipFile(data, 'w', zipfile.ZIP_DEFLATED) as target:
        for name, content in sorted(entries):
            info = zipfile.ZipInfo(name, (2026, 11, 6, 0, 0, 0))
            info.compress_type = zipfile.ZIP_DEFLATED
            info.external_attr = 0o100644 << 16
            target.writestr(info, content)
    return data.getvalue()

def idea():
    resources = ROOT / 'idea/src/main/resources'
    manifest = ET.parse(resources / 'META-INF/plugin.xml').getroot()
    version = manifest.findtext('version')
    theme = json.loads((resources / 'themes/ProtocolBlueprintLight.theme.json').read_text())
    scheme = ET.parse(resources / theme['editorScheme'].lstrip('/')).getroot()
    assert theme['parentTheme'] == 'Islands Light'
    assert scheme.get('name') == 'Protocol Blueprint Light'
    for key, value in [('EDITOR_FONT_SIZE', '13'), ('CONSOLE_FONT_SIZE', '13'), ('LINE_SPACING', '1.2'), ('CONSOLE_LINE_SPACING', '1.2')]:
        assert scheme.find(f"option[@name='{key}']").get('value') == value
    entries = [(p.relative_to(resources).as_posix(), p.read_bytes()) for p in resources.rglob('*') if p.is_file() and not p.name.startswith('.')]
    entries.append(('META-INF/LICENSE.txt', (ROOT / 'LICENSE').read_bytes()))
    # Compile the optional Java-only keyword annotator against the local IDEA SDK.
    sdk = Path(args.idea_home).expanduser()
    compiler = Path(args.javac) if args.javac else sdk / 'jbr/Contents/Home/bin/javac'
    jars = sorted((sdk / 'lib').glob('*.jar')) + sorted((sdk / 'plugins/java/lib').glob('*.jar')) + sorted((sdk / 'plugins/java/lib/modules').glob('*.jar'))
    if not compiler.is_file() or not jars:
        raise SystemExit('IDEA SDK/JDK not found. Pass --idea-home and optionally --javac (JDK 21+).')
    with tempfile.TemporaryDirectory(prefix='protocol-keywords-') as temp:
        sources = sorted((ROOT / 'idea/src/main/java').rglob('*.java'))
        subprocess.run([str(compiler), '--release', '21', '-encoding', 'UTF-8', '-classpath', os.pathsep.join(map(str, jars)), '-d', temp, *map(str, sources)], check=True)
        entries += [(p.relative_to(temp).as_posix(), p.read_bytes()) for p in Path(temp).rglob('*.class')]
    jar = archive(entries)
    (OUT / f'Protocol-Blueprint-Light-IDEA-{version}.zip').write_bytes(archive([(f'protocol-blueprint-light/lib/protocol-blueprint-light-{version}.jar', jar)]))
    shutil.copy2(resources / 'schemes/ProtocolBlueprintLight.xml', OUT / 'Protocol-Blueprint-Light.icls')

def vscode():
    version = json.loads((ROOT / 'vscode/package.json').read_text())['version']
    subprocess.run(['npx', '--yes', '@vscode/vsce@3.9.2', 'package', '--baseImagesUrl', 'https://raw.githubusercontent.com/Suremotoo/protocol-blueprint-light/release-idea-1.1.1-vscode-1.1.2/vscode/', '--baseContentUrl', 'https://github.com/Suremotoo/protocol-blueprint-light/blob/release-idea-1.1.1-vscode-1.1.2/vscode/', '--out', str(OUT / f'Protocol-Blueprint-Light-{version}.vsix')], cwd=ROOT / 'vscode', check=True)

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('platform', choices=['idea', 'vscode', 'all'])
    parser.add_argument('--idea-home', default=os.environ.get('IDEA_HOME', '/Users/suremotoo/Applications/IntelliJ IDEA.app/Contents'), help='IDEA SDK home containing lib/ and plugins/java/')
    parser.add_argument('--javac', help='JDK 21+ compiler; defaults to the IDEA runtime javac')
    args = parser.parse_args()
    OUT.mkdir(exist_ok=True)
    if args.platform in ('idea', 'all'):
        idea()
    if args.platform in ('vscode', 'all'):
        vscode()
    packages = sorted(p for p in OUT.iterdir() if p.suffix in ('.zip', '.vsix', '.icls'))
    (OUT / 'SHA256SUMS.txt').write_text(''.join(f'{hashlib.sha256(p.read_bytes()).hexdigest()}  {p.name}\n' for p in packages))
    print(f'Built {args.platform}; artifacts: {OUT}')
