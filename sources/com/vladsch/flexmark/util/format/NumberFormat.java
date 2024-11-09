package com.vladsch.flexmark.util.format;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/NumberFormat.class */
public enum NumberFormat {
    NONE,
    ARABIC,
    LETTERS,
    ROMAN,
    CUSTOM;

    public static String getFormat(NumberFormat numberFormat, int i) {
        switch (numberFormat) {
            case NONE:
                return "";
            case ARABIC:
                return String.valueOf(i);
            case LETTERS:
                if (i <= 0) {
                    throw new NumberFormatException("Letter format count must be > 0, actual " + i);
                }
                return getFormat(i - 1, "abcdefghijklmnopqrstuvwxyz");
            case ROMAN:
                return new RomanNumeral(i).toString();
            case CUSTOM:
                throw new IllegalStateException("CounterFormat.CUSTOM has to use custom conversion, possibly by calling getFormat(int count, CharSequence digitSet)");
            default:
                return "";
        }
    }

    public static String getFormat(int i, CharSequence charSequence) {
        int i2;
        StringBuilder sb = new StringBuilder(10);
        int length = charSequence.length();
        do {
            i2 = i / length;
            sb.append(charSequence.charAt(i - (i2 * length)));
            i = i2;
        } while (i2 > 0);
        int length2 = sb.length();
        StringBuilder sb2 = new StringBuilder(length2);
        int i3 = length2;
        while (true) {
            int i4 = i3;
            i3--;
            if (i4 > 0) {
                sb2.append(sb.charAt(i3));
            } else {
                return sb2.toString();
            }
        }
    }
}
