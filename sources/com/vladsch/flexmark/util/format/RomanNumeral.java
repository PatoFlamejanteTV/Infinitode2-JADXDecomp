package com.vladsch.flexmark.util.format;

import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/RomanNumeral.class */
public class RomanNumeral {
    private final int num;
    private static final int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] letters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public static final Pattern ROMAN_NUMERAL = Pattern.compile("M{0,3}(?:CM|DC{0,3}|CD|C{1,3})?(?:XC|LX{0,3}|XL|X{1,3})?(?:IX|VI{0,3}|IV|I{1,3})?");
    public static final Pattern LOWERCASE_ROMAN_NUMERAL = Pattern.compile("m{0,3}(?:cm|dc{0,3}|cd|c{1,3})?(?:xc|lx{0,3}|xl|x{1,3})?(?:ix|vi{0,3}|iv|i{1,3})?");
    public static final Pattern LIMITED_ROMAN_NUMERAL = Pattern.compile("(?:X{1,3})?(?:IX|VI{0,3}|IV|I{1,3})?");
    public static final Pattern LIMITED_LOWERCASE_ROMAN_NUMERAL = Pattern.compile("(?:x{1,3})?(?:ix|vi{0,3}|iv|i{1,3})?");

    public RomanNumeral(int i) {
        if (i <= 0) {
            throw new NumberFormatException("Value of RomanNumeral must be positive.");
        }
        if (i > 3999) {
            throw new NumberFormatException("Value of RomanNumeral must be 3999 or less.");
        }
        this.num = i;
    }

    public RomanNumeral(String str) {
        int letterToNumber;
        if (str.length() == 0) {
            throw new NumberFormatException("An empty string does not define a Roman numeral.");
        }
        String upperCase = str.toUpperCase();
        int i = 0;
        int i2 = 0;
        while (i < upperCase.length()) {
            char charAt = upperCase.charAt(i);
            int letterToNumber2 = letterToNumber(charAt);
            if (letterToNumber2 < 0) {
                throw new NumberFormatException("Illegal character \"" + charAt + "\" in roman numeral.");
            }
            i++;
            if (i == upperCase.length() || (letterToNumber = letterToNumber(upperCase.charAt(i))) <= letterToNumber2) {
                i2 += letterToNumber2;
            } else {
                i2 += letterToNumber - letterToNumber2;
                i++;
            }
        }
        if (i2 > 3999) {
            throw new NumberFormatException("Roman numeral must have value 3999 or less.");
        }
        this.num = i2;
    }

    private int letterToNumber(char c) {
        switch (c) {
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'J':
            case 'K':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'W':
            default:
                return -1;
            case 'I':
                return 1;
            case 'L':
                return 50;
            case 'M':
                return 1000;
            case 'V':
                return 5;
            case 'X':
                return 10;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.num;
        for (int i2 = 0; i2 < numbers.length; i2++) {
            while (i >= numbers[i2]) {
                sb.append(letters[i2]);
                i -= numbers[i2];
            }
        }
        return sb.toString();
    }

    public int toInt() {
        return this.num;
    }
}
