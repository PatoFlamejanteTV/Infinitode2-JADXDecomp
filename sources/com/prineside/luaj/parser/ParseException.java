package com.prineside.luaj.parser;

import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/luaj/parser/ParseException.class */
public class ParseException extends Exception {
    public Token currentToken;
    public int[][] expectedTokenSequences;
    public String[] tokenImage;

    public ParseException(Token token, int[][] iArr, String[] strArr) {
        super(a(token, iArr, strArr));
        System.getProperty("line.separator", SequenceUtils.EOL);
        this.currentToken = token;
        this.expectedTokenSequences = iArr;
        this.tokenImage = strArr;
    }

    public ParseException() {
        System.getProperty("line.separator", SequenceUtils.EOL);
    }

    public ParseException(String str) {
        super(str);
        System.getProperty("line.separator", SequenceUtils.EOL);
    }

    private static String a(Token token, int[][] iArr, String[] strArr) {
        String str;
        String property = System.getProperty("line.separator", SequenceUtils.EOL);
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i < iArr[i2].length) {
                i = iArr[i2].length;
            }
            for (int i3 = 0; i3 < iArr[i2].length; i3++) {
                stringBuffer.append(strArr[iArr[i2][i3]]).append(' ');
            }
            if (iArr[i2][iArr[i2].length - 1] != 0) {
                stringBuffer.append("...");
            }
            stringBuffer.append(property).append("    ");
        }
        String str2 = "Encountered \"";
        Token token2 = token.next;
        int i4 = 0;
        while (true) {
            if (i4 >= i) {
                break;
            }
            if (i4 != 0) {
                str2 = str2 + SequenceUtils.SPACE;
            }
            if (token2.kind == 0) {
                str2 = str2 + strArr[0];
                break;
            }
            str2 = (((str2 + SequenceUtils.SPACE + strArr[token2.kind]) + " \"") + a(token2.image)) + " \"";
            token2 = token2.next;
            i4++;
        }
        String str3 = (str2 + "\" at line " + token.next.beginLine + ", column " + token.next.beginColumn) + "." + property;
        if (iArr.length == 1) {
            str = str3 + "Was expecting:" + property + "    ";
        } else {
            str = str3 + "Was expecting one of:" + property + "    ";
        }
        return str + stringBuffer.toString();
    }

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
}
