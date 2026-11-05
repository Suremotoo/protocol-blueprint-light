# 验证记录 · 1.1.0 · 2026-11-04

- IDEA：本机平台类库原生解析检查通过；继承 Islands Light，Islands=1，Island.arc=10。主要 UI 背景、选区、按钮、Java/Kotlin 方法色、新关键字/字符串/注释色和声明加粗均通过断言。
- IDEA 字体：编辑器/控制台均为 Fantasque Sans Mono、13、行距 1.2，连字开启；新版编辑器方案名为 Protocol Blueprint Light 1.1。
- IDEA Git Blame：使用实际 AnnotationSource.LOCAL.getColor(false/true) 路径验证普通/最新提交前景；使用 AnnotationsSettings.getOrderedColors 取得全部 21 个背景，最低对比度分别为 4.82:1 / 8.68:1。
- IDEA 检查为 headless；未进行新版主题的实际 GUI 全控件视觉回归，未验证图标补丁。
- VS Code：官方 @vscode/vsce 构建 1.1.0 成功；通过 code CLI 安装至隔离的 user-data-dir / extensions-dir，返回 successfully installed。未安装到用户日常扩展目录。
- VS Code：205 项工作台颜色，包含内置 Git Blame、CodeLens 及 GitLens 颜色。内置 Git 键已对照本机扩展清单；GitLens 键对照官方 package.json，未进行 GitLens 运行时验证。
- TextMate / semanticTokenColors 已包含；未对每种语言插件逐一验证。
- IDEA 编辑器颜色与属性、VS Code JSON 无重复键；安装包资源路径与插件描述一致。
- 两个安装包均为声明式主题资源，不带运行时代码、网站素材或字体文件。
- assets/syntax-and-blame-comparison.png 为使用实际色值生成的前后对照示意，已检查图像；不是实际 IDE 截图。

主要语法前景对纸面背景 #FDFDFE 的 WCAG 对比度如下（非全 UI 无障碍认证）：

| 用途 | 前景 | 对比度 |
| --- | --- | --- |
| text | `#293447` | 12.32:1 |
| keyword | `#087D52` | 5.08:1 |
| comment | `#65718A` | 4.82:1 |
| type | `#0753A6` | 7.39:1 |
| function | `#7040C8` | 6.34:1 |
| string | `#9A6500` | 4.88:1 |
| number | `#9A6500` | 4.88:1 |
| parameter | `#A13E5C` | 6.15:1 |
| property | `#0B7185` | 5.56:1 |
| punctuation | `#65718A` | 4.82:1 |
| operator | `#45546D` | 7.53:1 |

## 发布整理检查 · 2026-11-05

- IDEA 和 VS Code 源码分目录维护，单独的 manifest、资源和 README；通用构建入口为 scripts/build.py。
- IDEA 增加 Marketplace 描述、更新说明、Vendor 链接及原创 SVG 图标。重新通过本机 IDEA 原生 headless 检查，包含 Islands 继承、字体、语法和 21 个 Blame 背景。
- Python 构建 IDEA ZIP；固定 @vscode/vsce 3.9.2 构建 VSIX。dist/ 为生成目录，不进入 Git。
- GitHub v1.1.0 Release 包含 IDEA ZIP、VSIX、可选 ICLS 和 SHA256SUMS.txt。商城插件 ID 为 35816，首次上传已提交审核；这不代表已通过审核或已公开。

## VS Code 商城发布准备 · 1.1.1 · 2026-11-05

- 三张作者提供的实际 VS Code 截图已加入 vscode/images/，与原图逐字节一致；图标和截图已检查。
- VSIX 包含图标、三张截图、CHANGELOG、README 和原有主题资源，版本 1.1.1，publisher 为 suremotoo；主题配色和字体预设不变。
- @vscode/vsce 3.9.2 打包成功；隔离扩展目录安装成功。
- 商城后台接收扩展并显示 Public / Verifying；最终可用性以商城校验结果为准。
