# Protocol Blueprint Light · VS Code

Complete light workbench theme with TextMate and semantic syntax colors. Includes built-in Git Blame and GitLens color keys; GitLens is optional.

From the repository root, run `python3 scripts/build.py vscode` (Python 3.9+, Node.js 20+, npm). Install the generated `dist/Protocol-Blueprint-Light-1.1.0.vsix` through **Extensions: Install from VSIX**, then select **Protocol Blueprint Light**.

Install Fantasque Sans Mono separately. Defaults: size 13, line height 1.2 and ligatures. Existing user/workspace settings take precedence; merge `settings.json` if needed. Font defaults apply while the extension is enabled, including when another theme is selected. Line height 1.2 is rounded by VS Code (about 16 px at size 13).

Version 1.1.0 introduces green keywords, amber strings/numbers, purple methods, blue types and slate comments, plus readable Git Blame colors.

MIT; see LICENSE and the root LICENSE.
