package com.badlogic.gdx.utils;

import java.util.Arrays;
import org.lwjgl.opengl.CGL;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/StringBuilder.class */
public class StringBuilder implements Appendable, CharSequence {
    static final int INITIAL_CAPACITY = 16;
    public char[] chars;
    public int length;
    private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static int numChars(int i, int i2) {
        int i3 = i < 0 ? 2 : 1;
        while (true) {
            int i4 = i / i2;
            i = i4;
            if (i4 != 0) {
                i3++;
            } else {
                return i3;
            }
        }
    }

    public static int numChars(long j, int i) {
        int i2 = j < 0 ? 2 : 1;
        while (true) {
            long j2 = j / i;
            j = j2;
            if (j2 != 0) {
                i2++;
            } else {
                return i2;
            }
        }
    }

    final char[] getValue() {
        return this.chars;
    }

    public StringBuilder() {
        this.chars = new char[16];
    }

    public StringBuilder(int i) {
        if (i < 0) {
            throw new NegativeArraySizeException();
        }
        this.chars = new char[i];
    }

    public StringBuilder(CharSequence charSequence) {
        this(charSequence.toString());
    }

    public StringBuilder(StringBuilder stringBuilder) {
        this.length = stringBuilder.length;
        this.chars = new char[this.length + 16];
        System.arraycopy(stringBuilder.chars, 0, this.chars, 0, this.length);
    }

    public StringBuilder(String str) {
        this.length = str.length();
        this.chars = new char[this.length + 16];
        str.getChars(0, this.length, this.chars, 0);
    }

    private void enlargeBuffer(int i) {
        int length = (this.chars.length >> 1) + this.chars.length + 2;
        char[] cArr = new char[i > length ? i : length];
        System.arraycopy(this.chars, 0, cArr, 0, this.length);
        this.chars = cArr;
    }

    final void appendNull() {
        int i = this.length + 4;
        if (i > this.chars.length) {
            enlargeBuffer(i);
        }
        char[] cArr = this.chars;
        int i2 = this.length;
        this.length = i2 + 1;
        cArr[i2] = 'n';
        char[] cArr2 = this.chars;
        int i3 = this.length;
        this.length = i3 + 1;
        cArr2[i3] = 'u';
        char[] cArr3 = this.chars;
        int i4 = this.length;
        this.length = i4 + 1;
        cArr3[i4] = 'l';
        char[] cArr4 = this.chars;
        int i5 = this.length;
        this.length = i5 + 1;
        cArr4[i5] = 'l';
    }

    final void append0(char[] cArr) {
        int length = this.length + cArr.length;
        if (length > this.chars.length) {
            enlargeBuffer(length);
        }
        System.arraycopy(cArr, 0, this.chars, this.length, cArr.length);
        this.length = length;
    }

    final void append0(char[] cArr, int i, int i2) {
        if (i > cArr.length || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Offset out of bounds: " + i);
        }
        if (i2 < 0 || cArr.length - i < i2) {
            throw new ArrayIndexOutOfBoundsException("Length out of bounds: " + i2);
        }
        int i3 = this.length + i2;
        if (i3 > this.chars.length) {
            enlargeBuffer(i3);
        }
        System.arraycopy(cArr, i, this.chars, this.length, i2);
        this.length = i3;
    }

    final void append0(char c) {
        if (this.length == this.chars.length) {
            enlargeBuffer(this.length + 1);
        }
        char[] cArr = this.chars;
        int i = this.length;
        this.length = i + 1;
        cArr[i] = c;
    }

    final void append0(String str) {
        if (str == null) {
            appendNull();
            return;
        }
        int length = str.length();
        int i = this.length + length;
        if (i > this.chars.length) {
            enlargeBuffer(i);
        }
        str.getChars(0, length, this.chars, this.length);
        this.length = i;
    }

    final void append0(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        if (i < 0 || i2 < 0 || i > i2 || i2 > charSequence.length()) {
            throw new IndexOutOfBoundsException();
        }
        append0(charSequence.subSequence(i, i2).toString());
    }

    public int capacity() {
        return this.chars.length;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        if (i < 0 || i >= this.length) {
            throw new StringIndexOutOfBoundsException(i);
        }
        return this.chars[i];
    }

    final void delete0(int i, int i2) {
        if (i >= 0) {
            if (i2 > this.length) {
                i2 = this.length;
            }
            if (i2 == i) {
                return;
            }
            if (i2 > i) {
                int i3 = this.length - i2;
                if (i3 >= 0) {
                    char[] cArr = this.chars;
                    System.arraycopy(cArr, i2, cArr, i, i3);
                }
                this.length -= i2 - i;
                return;
            }
        }
        throw new StringIndexOutOfBoundsException();
    }

    final void deleteCharAt0(int i) {
        if (i < 0 || i >= this.length) {
            throw new StringIndexOutOfBoundsException(i);
        }
        int i2 = (this.length - i) - 1;
        if (i2 > 0) {
            System.arraycopy(this.chars, i + 1, this.chars, i, i2);
        }
        this.length--;
    }

    public void ensureCapacity(int i) {
        if (i > this.chars.length) {
            int length = (this.chars.length << 1) + 2;
            enlargeBuffer(length > i ? length : i);
        }
    }

    public void getChars(int i, int i2, char[] cArr, int i3) {
        if (i > this.length || i2 > this.length || i > i2) {
            throw new StringIndexOutOfBoundsException();
        }
        System.arraycopy(this.chars, i, cArr, i3, i2 - i);
    }

    final void insert0(int i, char[] cArr) {
        if (i < 0 || i > this.length) {
            throw new StringIndexOutOfBoundsException(i);
        }
        if (cArr.length != 0) {
            move(cArr.length, i);
            System.arraycopy(cArr, 0, cArr, i, cArr.length);
            this.length += cArr.length;
        }
    }

    final void insert0(int i, char[] cArr, int i2, int i3) {
        if (i >= 0 && i <= i3) {
            if (i2 >= 0 && i3 >= 0 && i3 <= cArr.length - i2) {
                if (i3 != 0) {
                    move(i3, i);
                    System.arraycopy(cArr, i2, this.chars, i, i3);
                    this.length += i3;
                    return;
                }
                return;
            }
            throw new StringIndexOutOfBoundsException("offset " + i2 + ", length " + i3 + ", char[].length " + cArr.length);
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    final void insert0(int i, char c) {
        if (i < 0 || i > this.length) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        move(1, i);
        this.chars[i] = c;
        this.length++;
    }

    final void insert0(int i, String str) {
        if (i >= 0 && i <= this.length) {
            if (str == null) {
                str = "null";
            }
            int length = str.length();
            if (length != 0) {
                move(length, i);
                str.getChars(0, length, this.chars, i);
                this.length += length;
                return;
            }
            return;
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    final void insert0(int i, CharSequence charSequence, int i2, int i3) {
        if (charSequence == null) {
            charSequence = "null";
        }
        if (i < 0 || i > this.length || i2 < 0 || i3 < 0 || i2 > i3 || i3 > charSequence.length()) {
            throw new IndexOutOfBoundsException();
        }
        insert0(i, charSequence.subSequence(i2, i3).toString());
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.length;
    }

    private void move(int i, int i2) {
        if (this.chars.length - this.length >= i) {
            char[] cArr = this.chars;
            System.arraycopy(cArr, i2, cArr, i2 + i, this.length - i2);
            return;
        }
        int i3 = this.length + i;
        int length = (this.chars.length << 1) + 2;
        char[] cArr2 = new char[i3 > length ? i3 : length];
        System.arraycopy(this.chars, 0, cArr2, 0, i2);
        System.arraycopy(this.chars, i2, cArr2, i2 + i, this.length - i2);
        this.chars = cArr2;
    }

    final void replace0(int i, int i2, String str) {
        if (i >= 0) {
            if (i2 > this.length) {
                i2 = this.length;
            }
            if (i2 > i) {
                int length = str.length();
                int i3 = (i2 - i) - length;
                if (i3 > 0) {
                    char[] cArr = this.chars;
                    System.arraycopy(cArr, i2, cArr, i + length, this.length - i2);
                } else if (i3 < 0) {
                    move(-i3, i2);
                }
                str.getChars(0, length, this.chars, i);
                this.length -= i3;
                return;
            }
            if (i == i2) {
                if (str == null) {
                    throw new NullPointerException();
                }
                insert0(i, str);
                return;
            }
        }
        throw new StringIndexOutOfBoundsException();
    }

    final void reverse0() {
        if (this.length < 2) {
            return;
        }
        int i = this.length - 1;
        char c = this.chars[0];
        char c2 = this.chars[i];
        boolean z = true;
        boolean z2 = true;
        int i2 = 0;
        int i3 = this.length / 2;
        while (i2 < i3) {
            char c3 = this.chars[i2 + 1];
            char c4 = this.chars[i - 1];
            boolean z3 = z && c3 >= 56320 && c3 <= 57343 && c >= 55296 && c <= 56319;
            boolean z4 = z3;
            if (z3 && this.length < 3) {
                return;
            }
            boolean z5 = z2 && c4 >= 55296 && c4 <= 56319 && c2 >= 56320 && c2 <= 57343;
            z2 = true;
            z = true;
            if (z4 == z5) {
                if (z4) {
                    this.chars[i] = c3;
                    this.chars[i - 1] = c;
                    this.chars[i2] = c4;
                    this.chars[i2 + 1] = c2;
                    c = this.chars[i2 + 2];
                    c2 = this.chars[i - 2];
                    i2++;
                    i--;
                } else {
                    this.chars[i] = c;
                    this.chars[i2] = c2;
                    c = c3;
                    c2 = c4;
                }
            } else if (z4) {
                this.chars[i] = c3;
                this.chars[i2] = c2;
                c2 = c4;
                z = false;
            } else {
                this.chars[i] = c;
                this.chars[i2] = c4;
                c = c3;
                z2 = false;
            }
            i2++;
            i--;
        }
        if ((this.length & 1) == 1) {
            if (!z || !z2) {
                this.chars[i] = z ? c2 : c;
            }
        }
    }

    public void setCharAt(int i, char c) {
        if (i < 0 || i >= this.length) {
            throw new StringIndexOutOfBoundsException(i);
        }
        this.chars[i] = c;
    }

    public void setLength(int i) {
        if (i < 0) {
            throw new StringIndexOutOfBoundsException(i);
        }
        if (i > this.chars.length) {
            enlargeBuffer(i);
        } else if (this.length < i) {
            Arrays.fill(this.chars, this.length, i, (char) 0);
        }
        this.length = i;
    }

    public String substring(int i) {
        if (i >= 0 && i <= this.length) {
            if (i == this.length) {
                return "";
            }
            return new String(this.chars, i, this.length - i);
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public String substring(int i, int i2) {
        if (i >= 0 && i <= i2 && i2 <= this.length) {
            if (i == i2) {
                return "";
            }
            return new String(this.chars, i, i2 - i);
        }
        throw new StringIndexOutOfBoundsException();
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.length == 0 ? "" : new String(this.chars, 0, this.length);
    }

    public String toStringAndClear() {
        String stringBuilder = toString();
        clear();
        return stringBuilder;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return substring(i, i2);
    }

    public int indexOf(String str) {
        return indexOf(str, 0);
    }

    public int indexOf(String str, int i) {
        if (i < 0) {
            i = 0;
        }
        int length = str.length();
        if (length == 0) {
            return (i < this.length || i == 0) ? i : this.length;
        }
        int i2 = this.length - length;
        if (i > i2) {
            return -1;
        }
        char charAt = str.charAt(0);
        while (true) {
            int i3 = i;
            boolean z = false;
            while (true) {
                if (i3 > i2) {
                    break;
                }
                if (this.chars[i3] != charAt) {
                    i3++;
                } else {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return -1;
            }
            int i4 = i3;
            int i5 = 0;
            do {
                i5++;
                if (i5 >= length) {
                    break;
                }
                i4++;
            } while (this.chars[i4] == str.charAt(i5));
            if (i5 == length) {
                return i3;
            }
            i = i3 + 1;
        }
    }

    public int indexOfIgnoreCase(String str, int i) {
        if (i < 0) {
            i = 0;
        }
        int length = str.length();
        if (length == 0) {
            return (i < this.length || i == 0) ? i : this.length;
        }
        int i2 = this.length - length;
        if (i > i2) {
            return -1;
        }
        char upperCase = Character.toUpperCase(str.charAt(0));
        char lowerCase = Character.toLowerCase(upperCase);
        while (true) {
            int i3 = i;
            boolean z = false;
            while (i3 <= i2) {
                char c = this.chars[i3];
                if (c != upperCase && c != lowerCase) {
                    i3++;
                } else {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return -1;
            }
            int i4 = i3;
            int i5 = 0;
            while (true) {
                i5++;
                if (i5 >= length) {
                    break;
                }
                i4++;
                char c2 = this.chars[i4];
                char upperCase2 = Character.toUpperCase(str.charAt(i5));
                if (c2 != upperCase2 && c2 != Character.toLowerCase(upperCase2)) {
                    break;
                }
            }
            if (i5 == length) {
                return i3;
            }
            i = i3 + 1;
        }
    }

    public boolean contains(String str) {
        return indexOf(str, 0) != -1;
    }

    public boolean containsIgnoreCase(String str) {
        return indexOfIgnoreCase(str, 0) != -1;
    }

    public int lastIndexOf(String str) {
        return lastIndexOf(str, this.length);
    }

    public int lastIndexOf(String str, int i) {
        int length = str.length();
        if (length <= this.length && i >= 0) {
            if (length <= 0) {
                return i < this.length ? i : this.length;
            }
            if (i > this.length - length) {
                i = this.length - length;
            }
            char charAt = str.charAt(0);
            while (true) {
                int i2 = i;
                boolean z = false;
                while (true) {
                    if (i2 < 0) {
                        break;
                    }
                    if (this.chars[i2] != charAt) {
                        i2--;
                    } else {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    return -1;
                }
                int i3 = i2;
                int i4 = 0;
                do {
                    i4++;
                    if (i4 >= length) {
                        break;
                    }
                    i3++;
                } while (this.chars[i3] == str.charAt(i4));
                if (i4 == length) {
                    return i2;
                }
                i = i2 - 1;
            }
        } else {
            return -1;
        }
    }

    public void trimToSize() {
        if (this.length < this.chars.length) {
            char[] cArr = new char[this.length];
            System.arraycopy(this.chars, 0, cArr, 0, this.length);
            this.chars = cArr;
        }
    }

    public int codePointAt(int i) {
        if (i < 0 || i >= this.length) {
            throw new StringIndexOutOfBoundsException(i);
        }
        return Character.codePointAt(this.chars, i, this.length);
    }

    public int codePointBefore(int i) {
        if (i <= 0 || i > this.length) {
            throw new StringIndexOutOfBoundsException(i);
        }
        return Character.codePointBefore(this.chars, i);
    }

    public int codePointCount(int i, int i2) {
        if (i < 0 || i2 > this.length || i > i2) {
            throw new StringIndexOutOfBoundsException();
        }
        return Character.codePointCount(this.chars, i, i2 - i);
    }

    public int offsetByCodePoints(int i, int i2) {
        return Character.offsetByCodePoints(this.chars, 0, this.length, i, i2);
    }

    public StringBuilder append(boolean z) {
        append0(z ? "true" : "false");
        return this;
    }

    @Override // java.lang.Appendable
    public StringBuilder append(char c) {
        append0(c);
        return this;
    }

    public StringBuilder append(int i) {
        return append(i, 0);
    }

    public StringBuilder append(int i, int i2) {
        return append(i, i2, '0');
    }

    public StringBuilder append(int i, int i2, char c) {
        if (i == Integer.MIN_VALUE) {
            append0("-2147483648");
            return this;
        }
        if (i < 0) {
            append0('-');
            i = -i;
        }
        if (i2 > 1) {
            for (int numChars = i2 - numChars(i, 10); numChars > 0; numChars--) {
                append(c);
            }
        }
        if (i >= 10000) {
            if (i >= 1000000000) {
                append0(digits[(int) ((i % 10000000000L) / 1000000000)]);
            }
            if (i >= 100000000) {
                append0(digits[(i % 1000000000) / 100000000]);
            }
            if (i >= 10000000) {
                append0(digits[(i % 100000000) / 10000000]);
            }
            if (i >= 1000000) {
                append0(digits[(i % 10000000) / 1000000]);
            }
            if (i >= 100000) {
                append0(digits[(i % 1000000) / 100000]);
            }
            append0(digits[(i % 100000) / CGL.kCGLBadAttribute]);
        }
        if (i >= 1000) {
            append0(digits[(i % CGL.kCGLBadAttribute) / 1000]);
        }
        if (i >= 100) {
            append0(digits[(i % 1000) / 100]);
        }
        if (i >= 10) {
            append0(digits[(i % 100) / 10]);
        }
        append0(digits[i % 10]);
        return this;
    }

    public StringBuilder append(long j) {
        return append(j, 0);
    }

    public StringBuilder append(long j, int i) {
        return append(j, i, '0');
    }

    public StringBuilder append(long j, int i, char c) {
        if (j == Long.MIN_VALUE) {
            append0("-9223372036854775808");
            return this;
        }
        if (j < 0) {
            append0('-');
            j = -j;
        }
        if (i > 1) {
            for (int numChars = i - numChars(j, 10); numChars > 0; numChars--) {
                append(c);
            }
        }
        if (j >= 10000) {
            if (j >= 1000000000000000000L) {
                append0(digits[(int) ((j % 1.0E19d) / 1.0E18d)]);
            }
            if (j >= 100000000000000000L) {
                append0(digits[(int) ((j % 1000000000000000000L) / 100000000000000000L)]);
            }
            if (j >= 10000000000000000L) {
                append0(digits[(int) ((j % 100000000000000000L) / 10000000000000000L)]);
            }
            if (j >= 1000000000000000L) {
                append0(digits[(int) ((j % 10000000000000000L) / 1000000000000000L)]);
            }
            if (j >= 100000000000000L) {
                append0(digits[(int) ((j % 1000000000000000L) / 100000000000000L)]);
            }
            if (j >= 10000000000000L) {
                append0(digits[(int) ((j % 100000000000000L) / 10000000000000L)]);
            }
            if (j >= 1000000000000L) {
                append0(digits[(int) ((j % 10000000000000L) / 1000000000000L)]);
            }
            if (j >= 100000000000L) {
                append0(digits[(int) ((j % 1000000000000L) / 100000000000L)]);
            }
            if (j >= 10000000000L) {
                append0(digits[(int) ((j % 100000000000L) / 10000000000L)]);
            }
            if (j >= 1000000000) {
                append0(digits[(int) ((j % 10000000000L) / 1000000000)]);
            }
            if (j >= 100000000) {
                append0(digits[(int) ((j % 1000000000) / 100000000)]);
            }
            if (j >= 10000000) {
                append0(digits[(int) ((j % 100000000) / 10000000)]);
            }
            if (j >= 1000000) {
                append0(digits[(int) ((j % 10000000) / 1000000)]);
            }
            if (j >= 100000) {
                append0(digits[(int) ((j % 1000000) / 100000)]);
            }
            append0(digits[(int) ((j % 100000) / 10000)]);
        }
        if (j >= 1000) {
            append0(digits[(int) ((j % 10000) / 1000)]);
        }
        if (j >= 100) {
            append0(digits[(int) ((j % 1000) / 100)]);
        }
        if (j >= 10) {
            append0(digits[(int) ((j % 100) / 10)]);
        }
        append0(digits[(int) (j % 10)]);
        return this;
    }

    public StringBuilder append(float f) {
        append0(Float.toString(f));
        return this;
    }

    public StringBuilder append(double d) {
        append0(Double.toString(d));
        return this;
    }

    public StringBuilder append(Object obj) {
        if (obj == null) {
            appendNull();
        } else {
            append0(obj.toString());
        }
        return this;
    }

    public StringBuilder append(String str) {
        append0(str);
        return this;
    }

    public StringBuilder append(String str, String str2) {
        if (this.length > 0) {
            append0(str2);
        }
        append0(str);
        return this;
    }

    public StringBuilder appendLine(String str) {
        append0(str);
        append0('\n');
        return this;
    }

    public StringBuilder append(char[] cArr) {
        append0(cArr);
        return this;
    }

    public StringBuilder append(char[] cArr, int i, int i2) {
        append0(cArr, i, i2);
        return this;
    }

    @Override // java.lang.Appendable
    public StringBuilder append(CharSequence charSequence) {
        if (charSequence == null) {
            appendNull();
        } else if (charSequence instanceof StringBuilder) {
            StringBuilder stringBuilder = (StringBuilder) charSequence;
            append0(stringBuilder.chars, 0, stringBuilder.length);
        } else {
            append0(charSequence.toString());
        }
        return this;
    }

    public StringBuilder append(StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            appendNull();
        } else {
            append0(stringBuilder.chars, 0, stringBuilder.length);
        }
        return this;
    }

    @Override // java.lang.Appendable
    public StringBuilder append(CharSequence charSequence, int i, int i2) {
        append0(charSequence, i, i2);
        return this;
    }

    public StringBuilder append(StringBuilder stringBuilder, int i, int i2) {
        if (stringBuilder == null) {
            appendNull();
        } else {
            append0(stringBuilder.chars, i, i2);
        }
        return this;
    }

    public StringBuilder appendCodePoint(int i) {
        append0(Character.toChars(i));
        return this;
    }

    public StringBuilder delete(int i, int i2) {
        delete0(i, i2);
        return this;
    }

    public StringBuilder deleteCharAt(int i) {
        deleteCharAt0(i);
        return this;
    }

    public void clear() {
        this.length = 0;
    }

    public StringBuilder insert(int i, boolean z) {
        insert0(i, z ? "true" : "false");
        return this;
    }

    public StringBuilder insert(int i, char c) {
        insert0(i, c);
        return this;
    }

    public StringBuilder insert(int i, int i2) {
        insert0(i, Integer.toString(i2));
        return this;
    }

    public StringBuilder insert(int i, long j) {
        insert0(i, Long.toString(j));
        return this;
    }

    public StringBuilder insert(int i, float f) {
        insert0(i, Float.toString(f));
        return this;
    }

    public StringBuilder insert(int i, double d) {
        insert0(i, Double.toString(d));
        return this;
    }

    public StringBuilder insert(int i, Object obj) {
        insert0(i, obj == null ? "null" : obj.toString());
        return this;
    }

    public StringBuilder insert(int i, String str) {
        insert0(i, str);
        return this;
    }

    public StringBuilder insert(int i, char[] cArr) {
        insert0(i, cArr);
        return this;
    }

    public StringBuilder insert(int i, char[] cArr, int i2, int i3) {
        insert0(i, cArr, i2, i3);
        return this;
    }

    public StringBuilder insert(int i, CharSequence charSequence) {
        insert0(i, charSequence == null ? "null" : charSequence.toString());
        return this;
    }

    public StringBuilder insert(int i, CharSequence charSequence, int i2, int i3) {
        insert0(i, charSequence, i2, i3);
        return this;
    }

    public StringBuilder replace(int i, int i2, String str) {
        replace0(i, i2, str);
        return this;
    }

    public StringBuilder replace(String str, String str2) {
        int length = str.length();
        int length2 = str2.length();
        int i = 0;
        while (true) {
            int indexOf = indexOf(str, i);
            if (indexOf != -1) {
                replace0(indexOf, indexOf + length, str2);
                i = indexOf + length2;
            } else {
                return this;
            }
        }
    }

    public StringBuilder replace(char c, String str) {
        int length = str.length();
        int i = 0;
        while (i != this.length) {
            if (this.chars[i] != c) {
                i++;
            } else {
                int i2 = i;
                replace0(i2, i2 + 1, str);
                i += length;
            }
        }
        return this;
    }

    public StringBuilder reverse() {
        reverse0();
        return this;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public boolean notEmpty() {
        return this.length != 0;
    }

    public int hashCode() {
        int i = 31 + this.length;
        for (int i2 = 0; i2 < this.length; i2++) {
            i = (i * 31) + this.chars[i2];
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StringBuilder stringBuilder = (StringBuilder) obj;
        int i = this.length;
        if (i != stringBuilder.length) {
            return false;
        }
        char[] cArr = this.chars;
        char[] cArr2 = stringBuilder.chars;
        for (int i2 = 0; i2 < i; i2++) {
            if (cArr[i2] != cArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public boolean equalsIgnoreCase(@Null StringBuilder stringBuilder) {
        int i;
        if (this == stringBuilder) {
            return true;
        }
        if (stringBuilder == null || (i = this.length) != stringBuilder.length) {
            return false;
        }
        char[] cArr = this.chars;
        char[] cArr2 = stringBuilder.chars;
        for (int i2 = 0; i2 < i; i2++) {
            char c = cArr[i2];
            char upperCase = Character.toUpperCase(cArr2[i2]);
            if (c != upperCase && c != Character.toLowerCase(upperCase)) {
                return false;
            }
        }
        return true;
    }

    public boolean equalsIgnoreCase(@Null String str) {
        int i;
        if (str == null || (i = this.length) != str.length()) {
            return false;
        }
        char[] cArr = this.chars;
        for (int i2 = 0; i2 < i; i2++) {
            char c = cArr[i2];
            char upperCase = Character.toUpperCase(str.charAt(i2));
            if (c != upperCase && c != Character.toLowerCase(upperCase)) {
                return false;
            }
        }
        return true;
    }
}
