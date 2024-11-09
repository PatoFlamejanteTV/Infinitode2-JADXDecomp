package com.vladsch.flexmark.util.sequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/RepeatedSequence.class */
public class RepeatedSequence implements CharSequence {
    public static RepeatedSequence NULL = new RepeatedSequence(BasedSequence.NULL, 0, 0);
    private final CharSequence chars;
    private final int startIndex;
    private final int endIndex;
    private int hashCode;

    private RepeatedSequence(CharSequence charSequence, int i, int i2) {
        this.chars = charSequence;
        this.startIndex = i;
        this.endIndex = i2;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.endIndex - this.startIndex;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        if (i < 0 || i >= this.endIndex - this.startIndex) {
            throw new IndexOutOfBoundsException();
        }
        return this.chars.charAt((this.startIndex + i) % this.chars.length());
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        if (i < 0 || i > i2 || i2 > this.endIndex - this.startIndex) {
            throw new IllegalArgumentException("subSequence($startIndex, $endIndex) in RepeatedCharSequence('', " + this.startIndex + ", " + this.endIndex + ")");
        }
        return i == i2 ? NULL : (i == this.startIndex && i2 == this.endIndex) ? this : new RepeatedSequence(this.chars, this.startIndex + i, this.startIndex + i2);
    }

    public RepeatedSequence repeat(int i) {
        int i2 = this.startIndex + ((this.endIndex - this.startIndex) * i);
        return this.startIndex >= this.endIndex ? NULL : this.endIndex == i2 ? this : new RepeatedSequence(this.chars, this.startIndex, i2);
    }

    public int hashCode() {
        int i = this.hashCode;
        int i2 = i;
        if (i == 0 && length() > 0) {
            for (int i3 = 0; i3 < length(); i3++) {
                i2 = (i2 * 31) + charAt(i3);
            }
            this.hashCode = i2;
        }
        return i2;
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof CharSequence) && toString().equals(obj.toString());
        }
        return true;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) this, 0, length());
        return sb.toString();
    }

    public static CharSequence ofSpaces(int i) {
        return new RepeatedSequence(SequenceUtils.SPACE, 0, i);
    }

    public static CharSequence repeatOf(char c, int i) {
        return new RepeatedSequence(String.valueOf(c), 0, i);
    }

    public static CharSequence repeatOf(CharSequence charSequence, int i) {
        return new RepeatedSequence(charSequence, 0, charSequence.length() * i);
    }

    public static CharSequence repeatOf(CharSequence charSequence, int i, int i2) {
        return new RepeatedSequence(charSequence, i, i2);
    }

    @Deprecated
    public static CharSequence of(char c, int i) {
        return repeatOf(c, i);
    }

    @Deprecated
    public static CharSequence of(CharSequence charSequence, int i) {
        return repeatOf(charSequence, i);
    }

    @Deprecated
    public static CharSequence of(CharSequence charSequence, int i, int i2) {
        return repeatOf(charSequence, i, i2);
    }
}
