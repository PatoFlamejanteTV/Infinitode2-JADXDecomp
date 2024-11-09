package com.badlogic.gdx.utils;

import java.io.UnsupportedEncodingException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Base64Coder.class */
public class Base64Coder {
    private static final String systemLineSeparator = "\n";
    public static final CharMap regularMap = new CharMap('+', '/');
    public static final CharMap urlsafeMap = new CharMap('-', '_');

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Base64Coder$CharMap.class */
    public static class CharMap {
        protected final char[] encodingMap = new char[64];
        protected final byte[] decodingMap = new byte[128];

        public CharMap(char c, char c2) {
            int i = 0;
            char c3 = 'A';
            while (true) {
                char c4 = c3;
                if (c4 > 'Z') {
                    break;
                }
                int i2 = i;
                i++;
                this.encodingMap[i2] = c4;
                c3 = (char) (c4 + 1);
            }
            char c5 = 'a';
            while (true) {
                char c6 = c5;
                if (c6 > 'z') {
                    break;
                }
                int i3 = i;
                i++;
                this.encodingMap[i3] = c6;
                c5 = (char) (c6 + 1);
            }
            char c7 = '0';
            while (true) {
                char c8 = c7;
                if (c8 > '9') {
                    break;
                }
                int i4 = i;
                i++;
                this.encodingMap[i4] = c8;
                c7 = (char) (c8 + 1);
            }
            this.encodingMap[i] = c;
            this.encodingMap[i + 1] = c2;
            for (int i5 = 0; i5 < this.decodingMap.length; i5++) {
                this.decodingMap[i5] = -1;
            }
            for (int i6 = 0; i6 < 64; i6++) {
                this.decodingMap[this.encodingMap[i6]] = (byte) i6;
            }
        }

        public byte[] getDecodingMap() {
            return this.decodingMap;
        }

        public char[] getEncodingMap() {
            return this.encodingMap;
        }
    }

    public static String encodeString(String str) {
        return encodeString(str, false);
    }

    public static String encodeString(String str, boolean z) {
        try {
            return new String(encode(str.getBytes("UTF-8"), z ? urlsafeMap.encodingMap : regularMap.encodingMap));
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static String encodeLines(byte[] bArr) {
        return encodeLines(bArr, 0, bArr.length, 76, "\n", regularMap.encodingMap);
    }

    public static String encodeLines(byte[] bArr, int i, int i2, int i3, String str, CharMap charMap) {
        return encodeLines(bArr, i, i2, i3, str, charMap.encodingMap);
    }

    public static String encodeLines(byte[] bArr, int i, int i2, int i3, String str, char[] cArr) {
        int i4 = (i3 * 3) / 4;
        if (i4 > 0) {
            StringBuilder stringBuilder = new StringBuilder((((i2 + 2) / 3) << 2) + ((((i2 + i4) - 1) / i4) * str.length()));
            int i5 = 0;
            while (true) {
                int i6 = i5;
                if (i6 < i2) {
                    int min = Math.min(i2 - i6, i4);
                    stringBuilder.append(encode(bArr, i + i6, min, cArr));
                    stringBuilder.append(str);
                    i5 = i6 + min;
                } else {
                    return stringBuilder.toString();
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static char[] encode(byte[] bArr) {
        return encode(bArr, regularMap.encodingMap);
    }

    public static char[] encode(byte[] bArr, CharMap charMap) {
        return encode(bArr, 0, bArr.length, charMap);
    }

    public static char[] encode(byte[] bArr, char[] cArr) {
        return encode(bArr, 0, bArr.length, cArr);
    }

    public static char[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, i, regularMap.encodingMap);
    }

    public static char[] encode(byte[] bArr, int i, int i2, CharMap charMap) {
        return encode(bArr, i, i2, charMap.encodingMap);
    }

    public static char[] encode(byte[] bArr, int i, int i2, char[] cArr) {
        int i3;
        int i4;
        int i5 = ((i2 << 2) + 2) / 3;
        char[] cArr2 = new char[((i2 + 2) / 3) << 2];
        int i6 = i;
        int i7 = i + i2;
        int i8 = 0;
        while (i6 < i7) {
            int i9 = i6;
            i6++;
            int i10 = bArr[i9] & 255;
            if (i6 < i7) {
                i6++;
                i3 = bArr[i6] & 255;
            } else {
                i3 = 0;
            }
            int i11 = i3;
            if (i6 < i7) {
                int i12 = i6;
                i6++;
                i4 = bArr[i12] & 255;
            } else {
                i4 = 0;
            }
            int i13 = i4;
            int i14 = i10 >>> 2;
            int i15 = ((i10 & 3) << 4) | (i11 >>> 4);
            int i16 = ((i11 & 15) << 2) | (i13 >>> 6);
            int i17 = i13 & 63;
            int i18 = i8;
            int i19 = i8 + 1;
            cArr2[i18] = cArr[i14];
            int i20 = i19 + 1;
            cArr2[i19] = cArr[i15];
            cArr2[i20] = i20 < i5 ? cArr[i16] : '=';
            int i21 = i20 + 1;
            cArr2[i21] = i21 < i5 ? cArr[i17] : '=';
            i8 = i21 + 1;
        }
        return cArr2;
    }

    public static String decodeString(String str) {
        return decodeString(str, false);
    }

    public static String decodeString(String str, boolean z) {
        return new String(decode(str.toCharArray(), z ? urlsafeMap.decodingMap : regularMap.decodingMap));
    }

    public static byte[] decodeLines(String str) {
        return decodeLines(str, regularMap.decodingMap);
    }

    public static byte[] decodeLines(String str, CharMap charMap) {
        return decodeLines(str, charMap.decodingMap);
    }

    public static byte[] decodeLines(String str, byte[] bArr) {
        char[] cArr = new char[str.length()];
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != ' ' && charAt != '\r' && charAt != '\n' && charAt != '\t') {
                int i3 = i;
                i++;
                cArr[i3] = charAt;
            }
        }
        return decode(cArr, 0, i, bArr);
    }

    public static byte[] decode(String str) {
        return decode(str.toCharArray());
    }

    public static byte[] decode(String str, CharMap charMap) {
        return decode(str.toCharArray(), charMap);
    }

    public static byte[] decode(char[] cArr, byte[] bArr) {
        return decode(cArr, 0, cArr.length, bArr);
    }

    public static byte[] decode(char[] cArr, CharMap charMap) {
        return decode(cArr, 0, cArr.length, charMap);
    }

    public static byte[] decode(char[] cArr) {
        return decode(cArr, 0, cArr.length, regularMap.decodingMap);
    }

    public static byte[] decode(char[] cArr, int i, int i2, CharMap charMap) {
        return decode(cArr, i, i2, charMap.decodingMap);
    }

    public static byte[] decode(char[] cArr, int i, int i2, byte[] bArr) {
        char c;
        char c2;
        if (i2 % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (i2 > 0 && cArr[(i + i2) - 1] == '=') {
            i2--;
        }
        int i3 = (i2 * 3) / 4;
        byte[] bArr2 = new byte[i3];
        int i4 = i;
        int i5 = i + i2;
        int i6 = 0;
        while (i4 < i5) {
            int i7 = i4;
            int i8 = i4 + 1;
            char c3 = cArr[i7];
            i4 = i8 + 1;
            char c4 = cArr[i8];
            if (i4 < i5) {
                i4++;
                c = cArr[i4];
            } else {
                c = 'A';
            }
            char c5 = c;
            if (i4 < i5) {
                int i9 = i4;
                i4++;
                c2 = cArr[i9];
            } else {
                c2 = 'A';
            }
            char c6 = c2;
            if (c3 > 127 || c4 > 127 || c5 > 127 || c6 > 127) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            byte b2 = bArr[c3];
            byte b3 = bArr[c4];
            byte b4 = bArr[c5];
            byte b5 = bArr[c6];
            if (b2 < 0 || b3 < 0 || b4 < 0 || b5 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int i10 = (b2 << 2) | (b3 >>> 4);
            int i11 = ((b3 & 15) << 4) | (b4 >>> 2);
            int i12 = ((b4 & 3) << 6) | b5;
            int i13 = i6;
            i6++;
            bArr2[i13] = (byte) i10;
            if (i6 < i3) {
                i6++;
                bArr2[i6] = (byte) i11;
            }
            if (i6 < i3) {
                int i14 = i6;
                i6++;
                bArr2[i14] = (byte) i12;
            }
        }
        return bArr2;
    }

    private Base64Coder() {
    }
}
