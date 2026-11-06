# 本地试用 1.2.2

未 commit、push，未上传商城。只微调 Java 相邻语义角色，保留 1.2.1 的泛型、参数、背景和字体。

## 三组修正

- public void method：修饰符暖琥珀 #946400 → 返回类型蓝 #185B95 → 方法名紫 #6842AD。
- public SplitApplicationRespVO response：同样是琥珀 → 蓝 → 紫。
- import / package 关键字暖琥珀，路径单独使用蓝灰 #334C70，避免继承关键字颜色。

按语义角色分色，同类修饰符仍保持统一颜色，不为每个单词随机分配色值。class/interface 保留绿色；字重仍统一为常规。void 与普通类型统一为类型蓝，方法名保留紫色。

## 本地安装

IDEA：安装完整 ZIP 后重启，UI 和 scheme 均选择 **Protocol Blueprint Light**。同名可编辑 scheme 可能保留上版值；有个人修改时先导出备份，再对该 scheme 使用 **Restore Defaults** 载入本版颜色。本次没有自动修改你的日常 IDE 设置。

VS Code：安装 VSIX 后 Reload Window。Java 沿用 1.2.1 的 TextMate 默认及中性括号颜色；显式 Java 用户/项目语义着色设置可覆盖扩展默认。其他语言不受此次 Java 专用规则调整影响。

## 验证范围

- IDEA 实际 Java PSI + 原生 AnnotationHolder / AnnotationBuilder 验证，包含包名、普通导入、静态导入，以及既有泛型、参数、注释、字符串、比较运算符和其他 scheme 的回归。
- VS Code 原生 Java TextMate 语法验证，两组方法声明和 package/import 路径均按预期分别着色。
- 与 1.2.1 比较：IDEA 旧有属性仅两个 Java 角色前景修改，新增 namespace 属性；全部背景及字体保持一致。VS Code 全部工作台颜色及旧规则不变，只追加 Java 规则。
- 预览图为依据本版色值绘制的设计预览，并非实际 IDE 截图；完整 GUI/daemon 回归仍以本地试用为准。

## 相邻角色色差检查

CIELAB ΔE76 用于检查相邻角色没有落入近似色，并不等同于主观观感评价：

- modifier / primitive: CIELAB ΔE76 92.3
- primitive / function: CIELAB ΔE76 41.4
- modifier / type: CIELAB ΔE76 92.3
- type / function: CIELAB ΔE76 41.4
- modifier / namespace: CIELAB ΔE76 79.3
