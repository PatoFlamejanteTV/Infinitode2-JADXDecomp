package com.prineside.luaj.ast;

import com.prineside.luaj.LuaString;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Str.class */
public class Str {
    private Str() {
    }

    public static LuaString quoteString(String str) {
        return LuaString.valueUsing(unquote(str.substring(1, str.length() - 1)));
    }

    public static LuaString charString(String str) {
        return LuaString.valueUsing(unquote(str.substring(1, str.length() - 1)));
    }

    public static LuaString longString(String str) {
        int indexOf = str.indexOf(91, str.indexOf(91) + 1) + 1;
        return LuaString.valueUsing(iso88591bytes(str.substring(indexOf, str.length() - indexOf)));
    }

    public static byte[] iso88591bytes(String str) {
        try {
            return str.getBytes("ISO8859-1");
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalStateException("ISO8859-1 not supported");
        }
    }

    public static byte[] unquote(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        while (i < length) {
            if (charArray[i] == '\\' && i < length) {
                i++;
                switch (charArray[i]) {
                    case '\"':
                        byteArrayOutputStream.write(34);
                        break;
                    case '#':
                    case '$':
                    case '%':
                    case '&':
                    case '(':
                    case ')':
                    case '*':
                    case '+':
                    case ',':
                    case '-':
                    case '.':
                    case '/':
                    case ':':
                    case ';':
                    case '<':
                    case '=':
                    case '>':
                    case '?':
                    case '@':
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                    case 'G':
                    case 'H':
                    case 'I':
                    case 'J':
                    case 'K':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'S':
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    case 'Z':
                    case '[':
                    case ']':
                    case '^':
                    case '_':
                    case '`':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'g':
                    case 'h':
                    case 'i':
                    case 'j':
                    case 'k':
                    case 'l':
                    case 'm':
                    case 'o':
                    case 'p':
                    case 'q':
                    case 's':
                    case 'u':
                    default:
                        byteArrayOutputStream.write((byte) charArray[i]);
                        break;
                    case '\'':
                        byteArrayOutputStream.write(39);
                        break;
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        int i2 = i + 1;
                        int i3 = charArray[i] - '0';
                        for (int i4 = 0; i2 < length && i4 < 2 && charArray[i2] >= '0' && charArray[i2] <= '9'; i4++) {
                            i3 = (i3 * 10) + (charArray[i2] - '0');
                            i2++;
                        }
                        byteArrayOutputStream.write((byte) i3);
                        i = i2 - 1;
                        break;
                    case '\\':
                        byteArrayOutputStream.write(92);
                        break;
                    case 'a':
                        byteArrayOutputStream.write(7);
                        break;
                    case 'b':
                        byteArrayOutputStream.write(8);
                        break;
                    case 'f':
                        byteArrayOutputStream.write(12);
                        break;
                    case 'n':
                        byteArrayOutputStream.write(10);
                        break;
                    case 'r':
                        byteArrayOutputStream.write(13);
                        break;
                    case 't':
                        byteArrayOutputStream.write(9);
                        break;
                    case 'v':
                        byteArrayOutputStream.write(11);
                        break;
                }
            } else {
                byteArrayOutputStream.write((byte) charArray[i]);
            }
            i++;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
