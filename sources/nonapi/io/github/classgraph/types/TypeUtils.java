package nonapi.io.github.classgraph.types;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/types/TypeUtils.class */
public final class TypeUtils {

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/types/TypeUtils$ModifierType.class */
    public enum ModifierType {
        CLASS,
        METHOD,
        FIELD
    }

    private TypeUtils() {
    }

    public static boolean getIdentifierToken(Parser parser, boolean z) {
        boolean z2;
        boolean z3 = false;
        while (true) {
            z2 = z3;
            if (!parser.hasMore()) {
                break;
            }
            char peek = parser.peek();
            if (peek == '/') {
                parser.appendToToken('.');
                parser.next();
                z3 = true;
            } else {
                if (peek == ';' || peek == '[' || peek == '<' || peek == '>' || peek == ':' || (z && peek == '$')) {
                    break;
                }
                parser.appendToToken(peek);
                parser.next();
                z3 = true;
            }
        }
        return z2;
    }

    private static void appendModifierKeyword(StringBuilder sb, String str) {
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ') {
            sb.append(' ');
        }
        sb.append(str);
    }

    public static void modifiersToString(int i, ModifierType modifierType, boolean z, StringBuilder sb) {
        if ((i & 1) != 0) {
            appendModifierKeyword(sb, "public");
        } else if ((i & 2) != 0) {
            appendModifierKeyword(sb, "private");
        } else if ((i & 4) != 0) {
            appendModifierKeyword(sb, "protected");
        }
        if (modifierType != ModifierType.FIELD && (i & 1024) != 0) {
            appendModifierKeyword(sb, "abstract");
        }
        if ((i & 8) != 0) {
            appendModifierKeyword(sb, "static");
        }
        if (modifierType == ModifierType.FIELD) {
            if ((i & 64) != 0) {
                appendModifierKeyword(sb, "volatile");
            }
            if ((i & 128) != 0) {
                appendModifierKeyword(sb, "transient");
            }
        }
        if ((i & 16) != 0) {
            appendModifierKeyword(sb, "final");
        }
        if (modifierType == ModifierType.METHOD) {
            if ((i & 32) != 0) {
                appendModifierKeyword(sb, "synchronized");
            }
            if (z) {
                appendModifierKeyword(sb, "default");
            }
        }
        if ((i & 4096) != 0) {
            appendModifierKeyword(sb, "synthetic");
        }
        if (modifierType != ModifierType.FIELD && (i & 64) != 0) {
            appendModifierKeyword(sb, "bridge");
        }
        if (modifierType == ModifierType.METHOD && (i & 256) != 0) {
            appendModifierKeyword(sb, "native");
        }
        if (modifierType != ModifierType.FIELD && (i & 2048) != 0) {
            appendModifierKeyword(sb, "strictfp");
        }
    }
}
