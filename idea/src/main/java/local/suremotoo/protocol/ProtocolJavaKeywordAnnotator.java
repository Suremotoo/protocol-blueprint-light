package local.suremotoo.protocol;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.DumbAware;
import com.intellij.psi.*;

/** Context-aware Java colors, limited to schemes defining Protocol attributes. */
public final class ProtocolJavaKeywordAnnotator implements Annotator, DumbAware {
    private static final TextAttributesKey MODIFIER = key("MODIFIER");
    private static final TextAttributesKey DECLARATION = key("DECLARATION");
    private static final TextAttributesKey PRIMITIVE = key("PRIMITIVE");
    private static final TextAttributesKey FLOW = key("FLOW");
    private static final TextAttributesKey GENERIC_ARGUMENT = key("GENERIC_ARGUMENT");
    private static final TextAttributesKey GENERIC_BRACKET = key("GENERIC_BRACKET");
    private static final TextAttributesKey PARAMETER = key("PARAMETER");
    private static final TextAttributesKey NAMESPACE = key("NAMESPACE");
    // Fallbacks preserve existing editable 1.2.2 schemes without resetting user colors.
    private static final TextAttributesKey MODIFIER_GREEN = TextAttributesKey.createTextAttributesKey(
            "PROTOCOL_JAVA_MODIFIER_GREEN", DECLARATION);
    private static final TextAttributesKey MODIFIER_VIOLET = TextAttributesKey.createTextAttributesKey(
            "PROTOCOL_JAVA_MODIFIER_VIOLET", TextAttributesKey.createTextAttributesKey("METHOD_DECLARATION_ATTRIBUTES"));

    private static TextAttributesKey key(String group) {
        return TextAttributesKey.createTextAttributesKey("PROTOCOL_JAVA_" + group);
    }

    public static TextAttributesKey category(String word) {
        return switch (word) {
            case "public", "protected", "private", "static", "final", "abstract",
                 "native", "synchronized", "transient", "volatile", "strictfp",
                 "sealed", "non-sealed", "default", "package", "import" -> MODIFIER;
            case "class", "interface", "enum", "record" -> DECLARATION;
            case "void", "boolean", "byte", "short", "int", "long", "float",
                 "double", "char" -> PRIMITIVE;
            case "if", "else", "for", "while", "do", "switch", "case", "break",
                 "continue", "return", "try", "catch", "finally", "throw", "throws",
                 "new", "instanceof", "yield", "assert" -> FLOW;
            default -> null;
        };
    }

    private static boolean insideTypeArguments(PsiElement element) {
        for (PsiElement parent = element.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof PsiReferenceParameterList) return true;
            if (parent instanceof PsiStatement || parent instanceof PsiMember || parent instanceof PsiFile) return false;
        }
        return false;
    }

    private static Integer foreground(EditorColorsScheme scheme, TextAttributesKey key) {
        TextAttributes attributes = scheme.getAttributes(key);
        return attributes == null || attributes.getForegroundColor() == null
                ? null : attributes.getForegroundColor().getRGB();
    }

    private static TextAttributesKey chainedModifier(PsiKeyword keyword, EditorColorsScheme scheme) {
        if (!(keyword.getParent() instanceof PsiModifierList list)) return MODIFIER;
        java.util.List<PsiKeyword> words = new java.util.ArrayList<>();
        for (PsiElement child : list.getChildren()) {
            if (child instanceof PsiKeyword word && category(word.getText()) == MODIFIER) words.add(word);
        }
        int index = words.indexOf(keyword);
        // Single modifiers and the first word of every chain retain their existing style.
        if (index <= 0) return MODIFIER;
        TextAttributesKey nextRole = list.getParent() instanceof PsiClass ? DECLARATION : PRIMITIVE;
        Integer following = foreground(scheme, nextRole);
        TextAttributesKey previous = MODIFIER;
        for (int i = 1; i <= index; i++) {
            TextAttributesKey selected = MODIFIER;
            for (TextAttributesKey candidate : new TextAttributesKey[]{MODIFIER_GREEN, MODIFIER_VIOLET, MODIFIER}) {
                Integer color = foreground(scheme, candidate);
                if (color != null && !color.equals(foreground(scheme, previous))
                        && (i != words.size() - 1 || !color.equals(following))) {
                    selected = candidate;
                    break;
                }
            }
            previous = selected;
        }
        return previous;
    }

    @Override
    public void annotate(PsiElement element, AnnotationHolder holder) {
        TextAttributesKey category = null;
        PsiElement target = element;
        if (element instanceof PsiKeyword) {
            category = category(element.getText());
        } else if (element instanceof PsiImportStatementBase statement) {
            category = NAMESPACE;
            target = statement.getImportReference();
        } else if (element instanceof PsiPackageStatement statement) {
            category = NAMESPACE;
            target = statement.getPackageReference();
        } else if (element instanceof PsiParameter parameter) {
            category = PARAMETER;
            target = parameter.getNameIdentifier();
        } else if (element instanceof PsiJavaCodeReferenceElement reference) {
            // Color only the type name, not package qualifiers or a surrounding outer type.
            if (!(reference.getParent() instanceof PsiJavaCodeReferenceElement)
                    && (reference.getParameterList() == null || reference.getParameterList().getTypeParameterElements().length == 0)
                    && insideTypeArguments(reference)) {
                category = GENERIC_ARGUMENT;
                target = reference.getReferenceNameElement();
            }
        } else if (element instanceof PsiJavaToken token) {
            if ((token.getTokenType() == JavaTokenType.LT || token.getTokenType() == JavaTokenType.GT)
                    && (token.getParent() instanceof PsiReferenceParameterList
                        || token.getParent() instanceof PsiTypeParameterList)) {
                category = GENERIC_BRACKET;
            }
        }
        if (category == null || target == null) return;
        EditorColorsScheme scheme = EditorColorsManager.getInstance().getGlobalScheme();
        TextAttributes attributes = scheme.getAttributes(category);
        if (attributes == null || attributes.getForegroundColor() == null) return;
        if (category == MODIFIER && element instanceof PsiKeyword keyword) {
            category = chainedModifier(keyword, scheme);
            attributes = scheme.getAttributes(category);
        }
        // Explicit attributes also prevent the base Java type style from flattening generic arguments.
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(target).textAttributes(category).enforcedTextAttributes(attributes).create();
    }
}
