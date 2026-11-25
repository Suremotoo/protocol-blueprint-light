# Protocol Blueprint Light for Zed

Protocol Blueprint Light pairs an off-white editor with pale blue panels and deep blue accents. Types, functions, strings, and keywords use separate colors. The palette also covers the terminal, Git diffs, and inline blame.

## Preview in Zed

Open the command palette and run `zed: install dev extension`, then select this `zed` directory. Open the theme selector with `cmd-k cmd-t` on macOS or `ctrl-k ctrl-t` on Linux and Windows. Choose **Protocol Blueprint Light**.

## Editor font

Add these fields to your Zed settings to match the font and spacing used by the IntelliJ IDEA edition.

```json
{
  "buffer_font_family": "Fantasque Sans Mono",
  "buffer_font_size": 13,
  "buffer_line_height": { "custom": 1.2 }
}
```

Java highlighting follows the scopes provided by the Java language extension. Modifiers share a keyword color, and generic arguments often share the surrounding type color.

This extension is available under the [MIT License](LICENSE).
