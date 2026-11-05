# Protocol Blueprint Light

为 IDEA 与 VS Code 制作的配套浅色主题。参考 [Protocol Explorer](https://protocol-explorer-alpha.vercel.app/) 的实际 CSS 和提供的截图，字体使用 Fantasque Sans Mono；编辑器、控制台/终端默认字号 13，行距 1.2。

## 1.1.0 更新（2026-11-04）

- 使用网站图例的绿 / 琥珀 / 紫 / 灰蓝，形成更明确的代码分类；关键字和方法声明适度加粗。
- 修复 IDEA 最新提交的 Git Blame 前景误用近白底色的问题：普通作者/日期 `#526581`，最新提交 `#0032A0`。
- 明确设置五个浅蓝 Blame 背景锚点；在 IDEA 实际生成的全部 21 个日期/作者背景上，普通文字最低对比度 4.82:1，最新提交最低 8.68:1。
- VS Code 设置内置 Git 行尾 Blame，并兼容 GitLens 的行尾、左栏与未提交提示颜色，使用不透明前景。
- IDEA 的新版编辑器方案命名为 **Protocol Blueprint Light 1.1**，避免与旧方案的用户副本混淆。完整 UI 主题名仍为 **Protocol Blueprint Light**。

### 已安装旧版本时

重新从磁盘安装新版 ZIP / VSIX。IDEA 重新选择 UI 主题后，到 **Editor → Color Scheme** 确认方案为 **Protocol Blueprint Light 1.1**；若仍固定在旧方案，手动切换即可。旧方案的个人修改可以保留，不需要恢复默认。编辑器 / 控制台仍使用 Fantasque Sans Mono、13、行距 1.2。

如果只想导入配色，可导入新版 `Protocol-Blueprint-Light.icls`，再选择 **Protocol Blueprint Light 1.1**。VS Code 用户自定义的 `editor.tokenColorCustomizations`、`editor.semanticTokenColorCustomizations` 或 `workbench.colorCustomizations` 优先于主题，可能覆盖新颜色。

## 网站风格分析

这是偏「工程蓝图 / 技术仪表盘」的视觉，而非普通蓝色后台：

1. **近白纸面 + 钴蓝墨色**。实际 CSS 的 `--bg` 为 `#FDFDFE`，`--ink` 为 `#0032A0`，`--ink-strong` 为 `#00238A`。蓝色同时承担标题、图标、边框和交互状态，形成一致的绘图感。
2. **细线组织信息**。浅蓝线、表格横线、分段标签、等距网格将内容分成模块。大面积阴影很少，层次主要由线条与轻微背景差异表达。
3. **等宽排版与技术标记**。网站实际使用 Iosevka；大写、宽字距、数字编号和紧凑标签强化仪器面板感。本主题按要求换为 Fantasque Sans Mono，用于代码与终端。
4. **有限的语义彩色**。绿色对应成功/连接，琥珀对应请求/进度，紫色对应响应，红色用于特定强调。它们是辅助色，蓝白仍占主体。
5. **截图的浏览器外框不属于网页设计**。没有把粉色浏览器边框或截图外部黑底带入 IDE。

## 转换为编辑器主题

| 用途 | 色值 | 处理 |
| --- | --- | --- |
| 编辑区 | `#FDFDFE` | 保留网站近白纸面 |
| 侧栏 | `#F3F6FD` | 极浅蓝，区分代码与导航 |
| 外框 / 状态栏 | `#E8EEFC` | 蓝灰底色承载界面 |
| UI 主强调 | `#0032A0` | 网站钴蓝 |
| 代码关键字 | `#087D52` | Handshake and ACKs 绿色，加深并加粗 |
| UI 文字 | `#0B2B86` | 深蓝标题和控件 |
| 普通代码 | `#293447` | 中性深灰，突出彩色语义 |
| 注释 | `#65718A` | Closing (FIN) 灰蓝，加深以便阅读 |
| 函数 | `#7040C8` | Data from server 紫色，方法声明加粗 |
| 类型 | `#0753A6` | 蓝色区分类名、接口和构造器 |
| 字符串 | `#9A6500` | Data from laptop 琥珀色，加深以便阅读 |
| 数字 | `#9A6500` | 加深琥珀，适应白底小字 |
| 参数 | `#A13E5C` | 少量玫红区分参数 |
| 细边框 | `#BCCAE6` | 保持蓝图分区感 |

网站的等距网格只作为设计参考，不铺在代码背后。IDEA 通过继承 Islands Light 保留原生布局，浮岛圆角收紧到 10（紧凑模式 8）；按钮与输入控件使用较小圆角。VS Code 使用标准主题 API 调整整个工作台颜色，不注入 CSS，也不改变应用布局。两端原生菜单与导航字体由 IDE 管理。

## IDEA 安装

1. Settings → Plugins → 齿轮 → **Install Plugin from Disk…**。
2. 选择 `Protocol-Blueprint-Light-IDEA-1.1.0.zip`，按提示完成安装。
3. Appearance → Theme 选择 **Protocol Blueprint Light**。
4. 如编辑器保留旧配色，在 Editor → Color Scheme 选择 **Protocol Blueprint Light 1.1**。

需要内置 Islands Light 的 IDEA 2025.3+。配色预设 Fantasque Sans Mono 13 pt、1.2 行距和连字。若全局字体覆盖主题，在 Color Scheme Font 中启用配色字体或手动设为上述值。Run/Debug 控制台已设置字体；IDEA Terminal 的独立字体设置需按其终端引擎选项调整。

`Protocol-Blueprint-Light.icls` 是可选的独立配色备份；使用完整主题 ZIP 时不必再导入。

## VS Code 安装

1. 命令面板运行 **Extensions: Install from VSIX…**。
2. 选择 `Protocol-Blueprint-Light-1.1.0.vsix`。
3. **Preferences: Color Theme** 选择 **Protocol Blueprint Light**。

扩展提供字体默认值：Fantasque Sans Mono、13、行高 1.2、编辑器连字。已有用户或工作区设置优先；若没有生效，将 `vscode/settings.json` 的键合并到你的 Settings JSON，保留其他设置。

字体默认值属于扩展级别，启用此扩展期间也可能影响其他主题；用显式用户设置可覆盖。VS Code 的 1.2 行高按字体倍数计算并取整，因此 13 px 对应约 16 px 的实际行高，和 IDEA 的排版不保证逐像素相同。

## 源码与构建

仓库按 `idea/` 和 `vscode/` 分开维护；从仓库根目录运行 `python3 scripts/build.py all`，产物写入 `dist/`。详见根目录 README 和各平台 README。

- `palette.json` 位于仓库根目录；`docs/site-colors.json` 保留网站 CSS 变量记录。
- `assets/syntax-and-blame-comparison.png` 为代码 / Git Blame 对照示意，不是实际 IDE 截图。

## 验证范围

IDEA 已通过本机平台类库的原生 JSON/配色 XML 解析、Islands 继承、主要界面色值、Java/Kotlin 方法色、字体 13 和行距 1.2 检查，并通过实际 AnnotationSource 路径和全部 21 个 Blame 背景的对比度回归。未对新版主题开展实际 GUI 全控件视觉回归。VS Code 通过 vsce 打包及独立扩展目录的安装检查；未覆盖第三方插件自定义界面。详情见 `VERIFICATION.md`。

## 来源

网站：Protocol Explorer，页面标注设计者 DilumSanJaya。本主题为独立视觉改编，不包含网站源码、插图或字体文件，也不捆绑 JetBrains 原生资源。Fantasque Sans Mono 需另行安装。
