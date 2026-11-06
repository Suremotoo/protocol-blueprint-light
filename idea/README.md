# Protocol Blueprint Light · JetBrains IDEA

Release **1.1.1**. Full UI theme inheriting Islands Light; requires build 253+.

Install the IDEA ZIP with Plugins → Install Plugin from Disk, then restart if requested. Appearance → Theme and Editor → Color Scheme are both named **Protocol Blueprint Light** (no version suffix). If the old scheme remains selected, switch explicitly to the scheme without a version. Existing personal scheme copies are preserved.

The plugin includes a small optional Java annotator, because IDEA's native Java scheme uses one KEYWORD color for `public`, `class` and `void`. The annotator separates modifiers, declarations, primitives and control flow, and styles generic arguments, angle brackets, parameter declarations and package/import paths by their PSI context. It only applies when the active scheme defines the Protocol Java attributes, and excludes comments and strings. It performs no file/network operations. Other languages keep their native highlighter categories.

Font presets: Fantasque Sans Mono, size 13, spacing 1.2, ligatures. Install the font separately. Existing global font overrides take precedence. Terminal settings may be independent.

## Build

From the repository root, run `python3 scripts/build.py idea --idea-home /path/to/idea/Contents` (Python 3.9+, JDK 21+, local IDEA SDK including Java). Use `--javac /path/to/javac` if the bundled runtime has no compiler. The build compiles the optional annotator and packages it with the theme resources in `dist/Protocol-Blueprint-Light-IDEA-1.1.1.zip`.

The standalone `.icls` is an editor-only fallback: it cannot run the Java annotator and therefore cannot split native Java keywords. Use the full ZIP for the new Java categories.

MIT; see the root LICENSE.

Retain your existing editable 1.2.2 scheme for this modifier-chain-only update. Palette-reset instructions from earlier local versions are not required for 1.1.1. Details: `docs/LOCAL-1.1.1.md` in the repository root.

Release 1.1.1 only adds context-aware modifier-chain colors. The new attributes fall back to the existing declaration green and method violet, so an existing editable 1.2.2 scheme can be retained without Restore Defaults. Install the plugin and restart IDEA.
