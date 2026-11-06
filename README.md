# Protocol Blueprint Light

Complete light themes for **JetBrains IDEA** and **Visual Studio Code**. Near-white paper, cobalt-blue controls and syntax colors that distinguish neighboring roles.

![Protocol Blueprint Light — light workbench and distinct Java syntax](assets/protocol-blueprint-light.png)

Theme illustration using the actual palette; not an IDE screenshot. Font preset: **Fantasque Sans Mono · 13 · line spacing 1.2**. Install the font separately.

## Install

| Platform | Marketplace | Version | Source |
| --- | --- | --- | --- |
| JetBrains IDEA | [Get the theme](https://plugins.jetbrains.com/plugin/35816-protocol-blueprint-light) | **1.1.1** | [idea/](idea/) |
| VS Code | [Get the theme](https://marketplace.visualstudio.com/items?itemName=suremotoo.protocol-blueprint-light) | **1.1.2** | [vscode/](vscode/) |

Updates become available after each marketplace's validation or approval. [Download packages from Releases](https://github.com/Suremotoo/protocol-blueprint-light/releases).

- **IDEA:** Appearance → Theme and Editor → Color Scheme → **Protocol Blueprint Light**. Requires Islands Light / build 253+. Restart after updating. An old editable scheme may retain previous colors; export personal changes before using Restore Defaults to load the new bundled palette.
- **VS Code:** Preferences: Color Theme → **Protocol Blueprint Light**. Reload Window after updating.

## What is different

- Modifiers, return types and method names use distinct amber / blue / violet roles.
- Consecutive Java modifiers separate without changing the surrounding type or value colors.
- Blue containers, green generic arguments and slate angle brackets.
- Blue-gray parameters and package/import paths; readable Git Blame author/date annotations.
- Complete UI colors alongside editor syntax, with the original light backgrounds retained.

The two platforms have separate manifests and resources. IDEA includes an optional Java annotator; VS Code uses native Java grammar contexts plus a narrow modifier-chain injection. See the platform README files for font overrides and Java color settings.

## Build

Python 3.9+; IDEA additionally needs a local IDEA SDK with Java support and JDK 21+. VS Code additionally needs Node.js 20+ and npm.

```sh
python3 scripts/build.py idea --idea-home /path/to/idea/Contents
python3 scripts/build.py vscode
```

Packages and checksums are generated in `dist/` and excluded from Git. [Java sample](examples/java/Demo.java) · [Verification](docs/RELEASE-2026-11-06.md).

## License

[MIT](LICENSE), © 2026 Suremotoo. Inspired by [Protocol Explorer](https://protocol-explorer-alpha.vercel.app/), credited to DilumSanJaya. No website artwork, JetBrains native resources or fonts are bundled.
