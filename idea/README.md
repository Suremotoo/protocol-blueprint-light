# Protocol Blueprint Light · JetBrains IDEA

Complete UI theme inheriting Islands Light, plus an editor/console scheme. Requires build 253+ with Islands Light.

- `src/main/resources/META-INF/plugin.xml`: plugin manifest and marketplace description.
- `src/main/resources/themes/ProtocolBlueprintLight.theme.json`: application UI colors.
- `src/main/resources/schemes/ProtocolBlueprintLight.xml`: editor/console, syntax, Git Blame and font presets.

From the repository root, run `python3 scripts/build.py idea`. Install `dist/Protocol-Blueprint-Light-IDEA-1.1.0.zip` using Plugins → Install Plugin from Disk. Select UI theme **Protocol Blueprint Light** and editor scheme **Protocol Blueprint Light 1.1**.

Install Fantasque Sans Mono separately. Defaults: size 13, line spacing 1.2, ligatures. User font overrides take precedence. Existing modified editor schemes are preserved; select the 1.1 scheme when upgrading. Terminal font settings may be managed independently by the terminal engine.

The package contains resource files only. MIT; see the root LICENSE.
