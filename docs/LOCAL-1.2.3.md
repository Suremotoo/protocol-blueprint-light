# 本地试用 1.2.3：仅连续修饰符

本次没有 commit、push 或上传商城。其余 1.2.2 配色保持不变。

## 变动

`private static final String NUMBER_RULE = "CF";`

- private：沿用当前方案原色，标准配色为琥珀。
- static：使用已有声明绿色。
- final：使用已有方法紫色。
- String、常量名、字符串和其余标点：保留 1.2.2 样式。

仅对修饰符列表中的连续词做交替，并避开后面的声明/类型颜色。例如 `public static class` 里 static 取紫色，避开 class 的绿色。单独的 public/private、方法返回值、方法名、import/package、泛型和参数不变。

## 本地安装

IDEA：安装完整 ZIP 并重启。继续使用现有 **Protocol Blueprint Light** scheme；新增两类属性会回退到现有声明绿/方法紫。已验证不包含新属性的 1.2.2 可编辑副本，无需 Restore Defaults。本次没有自动改动日常 IDE 配置。

VS Code：安装 VSIX 后执行 Reload Window，载入仅处理修饰符链的新 Java 注入语法；既有 Java 语义着色默认设置保持不变。

## 验证

- IDEA 实际 Java PSI + 原生 AnnotationBuilder：8 组修饰符链、后接 class/type 的冲突规避、旧 1.2.2 scheme 回退通过。
- VS Code 原生 Java TextMate + 新注入语法：8 组修饰符链通过；逐字符对比，目标后续修饰符以外的颜色/字重与 1.2.2 完全一致。
- IDEA 旧属性、字体、背景及 VS Code 工作台、语义颜色、旧 TextMate 规则、palette.json 均逐项保持不变，仅新增链式修饰符属性/规则。
- 预览图为依据真实色值绘制的设计预览，并非实际 IDE 截图。未进行完整 GUI/daemon 集成回归。
