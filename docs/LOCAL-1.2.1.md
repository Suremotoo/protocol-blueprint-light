# 本地试用 1.2.1

这一版没有 commit、push，也没有上传任一商城。背景、字体 Fantasque Sans Mono、字号 13、行距 1.2 保持原样。

## 调整

- public/private 修饰符：蓝色；class/interface 声明词：绿色；void/int 等基本类型词：紫色。全部使用常规字重，移除只给 class/interface 加粗的做法。
- Set/List/Map 等容器类型：蓝色；Long/String/BpmnModel 等泛型叶子类型实参：绿色；泛型尖括号：灰色。嵌套容器仍保持蓝色。
- 方法参数：深蓝灰，替换原玫红色。
- IDEA 按真实 Java PSI 判断泛型位置，不会把比较运算符当泛型；类型名只标最后一个名称，不染包名。新注解携带显式颜色及字重，避免基础类型样式重新合并泛型颜色。
- VS Code 改用原生 Java TextMate 上下文，不再注入替换关键字的语法。由于 Java 语言服务会把 public/class 归为同一种 modifier，本地版提供 Java 专用默认值：`editor.semanticHighlighting.enabled: false`，只影响 Java 颜色，不关闭补全、导航或诊断。启用扩展期间该 Java 默认值也会作用于其他主题，显式的 Java 用户/项目设置可覆盖；其他语言仍保留语义着色。若手动重新启用 Java 语义颜色，类型实参和参数有对应补充规则，但 class/public 的词级分色可能被合并。
- VS Code 主题的六级括号颜色统一为灰色，避免彩虹括号覆盖泛型尖括号配色。

## 安装并载入新版颜色

1. IDEA 用 Plugins → Install Plugin from Disk 安装 ZIP，**重启 IDEA**。
2. UI 主题与 scheme 都选择 **Protocol Blueprint Light**（无版本号）。
3. 同名 scheme 的可编辑副本可能保留上版颜色。**若有个人修改，先导出备份，再对这套 scheme 使用 Restore Defaults**，以便载入本版配色；单纯更新 ZIP 可能仍沿用旧颜色。本次没有自动改动你的日常 IDE 配置。
4. VS Code 用 Extensions: Install from VSIX 安装本版，再运行 Reload Window。若 Java 用户/工作区配置显式开启语义着色，可合并 `vscode/settings.json` 中的 `[java]` 设置进行本次测试。
5. 用 `examples/java/Demo.java` 或你的实际代码检查 public class、public interface、public void、Set<Long>、List<String>、Map<String,BpmnModel> 和方法参数。

## 验证

- IDEA：真实 Java PSI + 原生 AnnotationHolder/AnnotationBuilder，通过方法/类级关键字、普通/嵌套/限定名/通配符泛型、尖括号和参数的范围、属性及常规字重检查；其他 scheme、字符串、注释和比较运算符不添加标记。
- IDEA 原生 UI/配色解析、Islands 继承、字体和行距、Blame 21 个背景回归通过。
- VS Code：原生 Java TextMate 语法加主题，按上述完整样例验证实际颜色；Java 语义覆盖开关及中性括号颜色已检查。
- 所有既有背景色逐项一致；VS Code 仅新增六个中性括号前景值，原工作台颜色不变。
- 预览图是根据本版色值制作的设计预览，不是实际 IDE 截图。尚未完成完整 GUI/daemon 集成回归，请以本地使用观感为准。

## 主要颜色对纸面背景的对比度

| 类别 | 色值 | 对比度 |
| --- | --- | --- |
| modifier | `#28558B` | 7.48:1 |
| declaration | `#08754B` | 5.65:1 |
| primitive | `#6842AD` | 6.95:1 |
| type | `#185B95` | 6.96:1 |
| genericArgument | `#08754B` | 5.65:1 |
| genericBracket | `#65718A` | 4.82:1 |
| parameter | `#334C70` | 8.58:1 |
| function | `#6842AD` | 6.95:1 |
| string | `#946400` | 5.06:1 |
| keyword | `#08754B` | 5.65:1 |
