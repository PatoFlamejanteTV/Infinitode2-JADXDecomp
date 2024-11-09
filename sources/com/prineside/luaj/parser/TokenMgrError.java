package com.prineside.luaj.parser;

/* loaded from: infinitode-2.jar:com/prineside/luaj/parser/TokenMgrError.class */
public class TokenMgrError extends Error {
    private static String a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case 0:
                    break;
                case '\b':
                    stringBuffer.append("\\b");
                    break;
                case '\t':
                    stringBuffer.append("\\t");
                    break;
                case '\n':
                    stringBuffer.append("\\n");
                    break;
                case '\f':
                    stringBuffer.append("\\f");
                    break;
                case '\r':
                    stringBuffer.append("\\r");
                    break;
                case '\"':
                    stringBuffer.append("\\\"");
                    break;
                case '\'':
                    stringBuffer.append("\\'");
                    break;
                case '\\':
                    stringBuffer.append("\\\\");
                    break;
                default:
                    char charAt = str.charAt(i);
                    if (charAt < ' ' || charAt > '~') {
                        String str2 = "0000" + Integer.toString(charAt, 16);
                        stringBuffer.append("\\u" + str2.substring(str2.length() - 4, str2.length()));
                        break;
                    } else {
                        stringBuffer.append(charAt);
                        break;
                    }
                    break;
            }
        }
        return stringBuffer.toString();
    }

    private static String a(boolean z, int i, int i2, String str, char c) {
        return "Lexical error at line " + i + ", column " + i2 + ".  Encountered: " + (z ? "<EOF> " : "\"" + a(String.valueOf(c)) + "\" (" + ((int) c) + "), ") + "after : \"" + a(str) + "\"";
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public TokenMgrError() {
    }

    public TokenMgrError(String str, int i) {
        super(str);
    }

    public TokenMgrError(boolean z, int i, int i2, int i3, String str, char c, int i4) {
        this(a(z, i2, i3, str, c), i4);
    }
}
