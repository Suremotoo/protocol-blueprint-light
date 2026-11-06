# 本地试用 1.2.0 · 2026-11-06

本次仅修改本地源码和生成测试包，没有 commit、push 或商城上传。远端仓库/商城仍为上一版。

- IDEA 完整 ZIP / VS Code VSIX 均为 1.2.0，字号 13，行距 1.2，Fantasque Sans Mono。
- IDEA scheme 名称恢复为 **Protocol Blueprint Light**；旧 1.1 方案及个人副本可保留。安装后手动选择无版本号方案。
- IDEA 原生 JAVA_KEYWORD 无法区分单词，完整插件加入可选 Java Annotator：public/private 修饰符、class/interface 声明、void/int 基本类型、if/return 控制流程。仅检查真实 PsiKeyword，仅在当前 scheme 定义 Protocol 属性时着色。ICLS 不包含该能力。
- Java Annotator 采用官方扩展点：https://plugins.jetbrains.com/docs/intellij/annotator.html 。无网络、文件读写或项目修改。
- VS Code Java TextMate injection 保留原生 scope 家族，排除注释、字符串；通用 keyword 语义色移除，以免覆盖细分结果。其他语言按原生类别分色，不承诺所有单词均独立着色。
- README 主图为新版单张设计预览（非真实 IDE 截图），不再使用前后对比图。
- IDEA 全部 scheme 背景、UI JSON（除两个编辑器文字前景值）、VS Code 工作台色（除 editor/terminal foreground 和 CodeLens）逐项保持一致；Blame 前景与背景保留。

## 检查

- IDEA 原生解析、Islands 继承、Java/Kotlin 配色、字体/行距通过。
- 实际 Java PSI + Annotator 调用通过，注释/字符串排除，其他 scheme 不添加标记。
- VS Code 实际 Java TextMate 语法 + injection + theme 通过，public/class/void/return 四色。
- Blame 原生全部 21 个背景回归：普通文字最低 4.82:1，最新提交最低 8.68:1。
- 新增运行时代码尚未完成 IDE GUI/daemon 回归，请重点在本地 Java 文件测试编辑、方案切换和重启后的着色。

## 代码前景对 #FDFDFE 的对比度

| 类别 | 前景 | 对比度 |
| --- | --- | --- |
| text | `#253247` | 12.71:1 |
| comment | `#53647D` | 5.92:1 |
| keyword | `#086B49` | 6.44:1 |
| modifier | `#365482` | 7.52:1 |
| declaration | `#006575` | 6.62:1 |
| primitive | `#164F8A` | 8.20:1 |
| type | `#164F8A` | 8.20:1 |
| function | `#6038AC` | 7.77:1 |
| string | `#875900` | 5.97:1 |
| number | `#91501D` | 6.13:1 |
| parameter | `#963C59` | 6.69:1 |
| property | `#086574` | 6.61:1 |
| punctuation | `#53647D` | 5.92:1 |
| operator | `#3D4F6A` | 8.18:1 |

## 本地安装

1. IDEA：Plugins → Install Plugin from Disk，选择 IDEA 1.2.0 ZIP；按提示重启。
2. Appearance → Theme 与 Editor → Color Scheme 均选 **Protocol Blueprint Light**，不要继续使用带 1.1 的旧方案。
3. 如果此前的同名个人 scheme 缓存了旧颜色，先导出备份，再对这一方案使用 Restore Defaults；本包不会自动重置你的个人设置。
4. VS Code：Extensions: Install from VSIX → 1.2.0 VSIX，再选 Protocol Blueprint Light；建议 Reload Window，让 Java grammar injection 重新加载。
5. 建议用 `public class Demo { public void run() { if (true) return; } }` 检查 public/class/void/return 四类颜色，随后看注释、字符串和 Git Blame。

字体仍为 Fantasque Sans Mono / 13 / 1.2；全局字体或自定义颜色会优先于主题。
