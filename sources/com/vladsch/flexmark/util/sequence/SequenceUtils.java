package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.misc.Utils;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/SequenceUtils.class */
public interface SequenceUtils {
    public static final String EOL = "\n";
    public static final String SPACE = " ";
    public static final String ANY_EOL = "\r\n";
    public static final char SPC = ' ';
    public static final char NUL = 0;
    public static final char ENC_NUL = 65533;
    public static final char NBSP = 160;
    public static final char LS = 8232;
    public static final char US = 31;
    public static final String SPACE_TAB = " \t";
    public static final String SPACE_EOL = " \n";
    public static final String WHITESPACE = " \t\r\n";
    public static final String WHITESPACE_NBSP = " \t\r\n ";

    @Deprecated
    public static final char LSEP = 8232;

    @Deprecated
    public static final String EOL_CHARS = "\r\n";

    @Deprecated
    public static final String WHITESPACE_NO_EOL_CHARS = " \t";

    @Deprecated
    public static final String WHITESPACE_CHARS = " \t\r\n";

    @Deprecated
    public static final String WHITESPACE_NBSP_CHARS = " \t\r\n ";
    public static final int SPLIT_INCLUDE_DELIMS = 1;
    public static final int SPLIT_TRIM_PARTS = 2;
    public static final int SPLIT_SKIP_EMPTY = 4;
    public static final int SPLIT_INCLUDE_DELIM_PARTS = 8;
    public static final int SPLIT_TRIM_SKIP_EMPTY = 6;
    public static final char EOL_CHAR = "\r\n".charAt(1);
    public static final char EOL_CHAR1 = "\r\n".charAt(0);
    public static final char EOL_CHAR2 = "\r\n".charAt(1);
    public static final String LINE_SEP = Character.toString(8232);
    public static final String US_CHARS = Character.toString(31);
    public static final String NBSP_CHARS = Character.toString(160);

    @Deprecated
    public static final CharPredicate SPACE_SET = CharPredicate.SPACE;

    @Deprecated
    public static final CharPredicate TAB_SET = CharPredicate.TAB;

    @Deprecated
    public static final CharPredicate EOL_SET = CharPredicate.EOL;

    @Deprecated
    public static final CharPredicate SPACE_TAB_SET = CharPredicate.SPACE_TAB;

    @Deprecated
    public static final CharPredicate SPACE_TAB_NBSP_SET = CharPredicate.SPACE_TAB_NBSP;

    @Deprecated
    public static final CharPredicate SPACE_TAB_EOL_SET = CharPredicate.SPACE_TAB_EOL;

    @Deprecated
    public static final CharPredicate SPACE_EOL_SET = CharPredicate.WHITESPACE;

    @Deprecated
    public static final CharPredicate ANY_EOL_SET = CharPredicate.ANY_EOL;

    @Deprecated
    public static final CharPredicate WHITESPACE_SET = CharPredicate.WHITESPACE;

    @Deprecated
    public static final CharPredicate WHITESPACE_NBSP_SET = CharPredicate.WHITESPACE_NBSP;

    @Deprecated
    public static final CharPredicate BACKSLASH_SET = CharPredicate.BACKSLASH;

    @Deprecated
    public static final CharPredicate US_SET = i -> {
        return i == 31;
    };

    @Deprecated
    public static final CharPredicate HASH_SET = CharPredicate.HASH;

    @Deprecated
    public static final CharPredicate DECIMAL_DIGITS = CharPredicate.HASH;

    @Deprecated
    public static final CharPredicate HEXADECIMAL_DIGITS = CharPredicate.HASH;

    @Deprecated
    public static final CharPredicate OCTAL_DIGITS = CharPredicate.HASH;
    public static final Map<Character, String> visibleSpacesMap = getVisibleSpacesMap();
    public static final int[] EMPTY_INDICES = new int[0];

    static Map<Character, String> getVisibleSpacesMap() {
        HashMap hashMap = new HashMap();
        hashMap.put('\n', "\\n");
        hashMap.put('\r', "\\r");
        hashMap.put('\f', "\\f");
        hashMap.put('\t', "\\u2192");
        hashMap.put((char) 8232, "➥");
        return hashMap;
    }

    static <T extends CharSequence> T subSequence(T t, int i) {
        return (T) t.subSequence(i, t.length());
    }

    static <T extends CharSequence> T subSequence(T t, Range range) {
        return range.isNull() ? t : (T) t.subSequence(range.getStart(), range.getEnd());
    }

    static <T extends CharSequence> T subSequenceBefore(T t, Range range) {
        if (range.isNull()) {
            return null;
        }
        return (T) t.subSequence(0, range.getStart());
    }

    static <T extends CharSequence> T subSequenceAfter(T t, Range range) {
        if (range.isNull()) {
            return null;
        }
        return (T) t.subSequence(range.getEnd(), t.length());
    }

    static <T extends CharSequence> Pair<T, T> subSequenceBeforeAfter(T t, Range range) {
        return Pair.of(subSequenceBefore(t, range), subSequenceAfter(t, range));
    }

    static boolean containsAny(CharSequence charSequence, CharPredicate charPredicate) {
        return indexOfAny(charSequence, charPredicate, 0, Integer.MAX_VALUE) != -1;
    }

    static boolean containsAny(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return indexOfAny(charSequence, charPredicate, i, Integer.MAX_VALUE) != -1;
    }

    static boolean containsAnyNot(CharSequence charSequence, CharPredicate charPredicate) {
        return indexOfAny(charSequence, charPredicate.negate(), 0, Integer.MAX_VALUE) != -1;
    }

    static boolean containsAnyNot(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return indexOfAny(charSequence, charPredicate.negate(), i, Integer.MAX_VALUE) != -1;
    }

    static boolean containsAnyNot(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        return indexOfAny(charSequence, charPredicate.negate(), i, i2) != -1;
    }

    static int indexOf(CharSequence charSequence, CharSequence charSequence2) {
        return indexOf(charSequence, charSequence2, 0, Integer.MAX_VALUE);
    }

    static int indexOf(CharSequence charSequence, CharSequence charSequence2, int i) {
        return indexOf(charSequence, charSequence2, i, Integer.MAX_VALUE);
    }

    static int indexOf(CharSequence charSequence, char c) {
        return indexOf(charSequence, c, 0, Integer.MAX_VALUE);
    }

    static int indexOf(CharSequence charSequence, char c, int i) {
        return indexOf(charSequence, c, i, Integer.MAX_VALUE);
    }

    static int indexOfAny(CharSequence charSequence, CharPredicate charPredicate) {
        return indexOfAny(charSequence, charPredicate, 0, Integer.MAX_VALUE);
    }

    static int indexOfAny(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return indexOfAny(charSequence, charPredicate, i, Integer.MAX_VALUE);
    }

    static int indexOfAnyNot(CharSequence charSequence, CharPredicate charPredicate) {
        return indexOfAny(charSequence, charPredicate.negate(), 0, Integer.MAX_VALUE);
    }

    static int indexOfAnyNot(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return indexOfAny(charSequence, charPredicate.negate(), i, Integer.MAX_VALUE);
    }

    static int indexOfAnyNot(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        return indexOfAny(charSequence, charPredicate.negate(), i, i2);
    }

    static int indexOfNot(CharSequence charSequence, char c) {
        return indexOfNot(charSequence, c, 0, Integer.MAX_VALUE);
    }

    static int indexOfNot(CharSequence charSequence, char c, int i) {
        return indexOfNot(charSequence, c, i, Integer.MAX_VALUE);
    }

    static int lastIndexOf(CharSequence charSequence, CharSequence charSequence2) {
        return lastIndexOf(charSequence, charSequence2, 0, Integer.MAX_VALUE);
    }

    static int lastIndexOf(CharSequence charSequence, CharSequence charSequence2, int i) {
        return lastIndexOf(charSequence, charSequence2, 0, i);
    }

    static int lastIndexOf(CharSequence charSequence, char c) {
        return lastIndexOf(charSequence, c, 0, Integer.MAX_VALUE);
    }

    static int lastIndexOf(CharSequence charSequence, char c, int i) {
        return lastIndexOf(charSequence, c, 0, i);
    }

    static int lastIndexOfAny(CharSequence charSequence, CharPredicate charPredicate) {
        return lastIndexOfAny(charSequence, charPredicate, 0, Integer.MAX_VALUE);
    }

    static int lastIndexOfAny(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return lastIndexOfAny(charSequence, charPredicate, 0, i);
    }

    static int lastIndexOfAnyNot(CharSequence charSequence, CharPredicate charPredicate) {
        return lastIndexOfAny(charSequence, charPredicate.negate(), 0, Integer.MAX_VALUE);
    }

    static int lastIndexOfAnyNot(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return lastIndexOfAny(charSequence, charPredicate.negate(), 0, i);
    }

    static int lastIndexOfAnyNot(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        return lastIndexOfAny(charSequence, charPredicate.negate(), i, i2);
    }

    static int lastIndexOfNot(CharSequence charSequence, char c) {
        return lastIndexOfNot(charSequence, c, 0, Integer.MAX_VALUE);
    }

    static int lastIndexOfNot(CharSequence charSequence, char c, int i) {
        return lastIndexOfNot(charSequence, c, 0, i);
    }

    static int indexOf(CharSequence charSequence, char c, int i, int i2) {
        int max = Math.max(i, 0);
        int min = Math.min(charSequence.length(), i2);
        for (int i3 = max; i3 < min; i3++) {
            if (c == charSequence.charAt(i3)) {
                return i3;
            }
        }
        return -1;
    }

    static int indexOf(CharSequence charSequence, CharSequence charSequence2, int i, int i2) {
        int max = Math.max(i, 0);
        int length = charSequence2.length();
        if (length == 0) {
            return max;
        }
        int min = Math.min(charSequence.length(), i2);
        if (max < min) {
            char charAt = charSequence2.charAt(0);
            int i3 = max;
            do {
                int indexOf = indexOf(charSequence, charAt, i3);
                if (indexOf >= 0 && indexOf + length <= min) {
                    if (matchChars(charSequence, charSequence2, indexOf)) {
                        return indexOf;
                    }
                    i3 = indexOf + 1;
                } else {
                    return -1;
                }
            } while (i3 + length < min);
            return -1;
        }
        return -1;
    }

    static int lastIndexOf(CharSequence charSequence, char c, int i, int i2) {
        int min = Math.min(i2, charSequence.length() - 1) + 1;
        int max = Math.max(i, 0);
        int i3 = min;
        do {
            int i4 = i3;
            i3--;
            if (i4 <= max) {
                return -1;
            }
        } while (c != charSequence.charAt(i3));
        return i3;
    }

    static int indexOfNot(CharSequence charSequence, char c, int i, int i2) {
        int max = Math.max(i, 0);
        int min = Math.min(i2, charSequence.length());
        for (int i3 = max; i3 < min; i3++) {
            if (charSequence.charAt(i3) != c) {
                return i3;
            }
        }
        return -1;
    }

    static int indexOfAny(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        int max = Math.max(i, 0);
        int min = Math.min(i2, charSequence.length());
        for (int i3 = max; i3 < min; i3++) {
            if (charPredicate.test(charSequence.charAt(i3))) {
                return i3;
            }
        }
        return -1;
    }

    static int lastIndexOf(CharSequence charSequence, CharSequence charSequence2, int i, int i2) {
        int max = Math.max(i, 0);
        int length = charSequence2.length();
        if (length == 0) {
            return max;
        }
        int min = Math.min(i2, charSequence.length());
        if (max < min) {
            int i3 = min;
            char charAt = charSequence2.charAt(length - 1);
            do {
                int lastIndexOf = lastIndexOf(charSequence, charAt, i3);
                if (lastIndexOf + 1 >= max + length) {
                    if (matchCharsReversed(charSequence, charSequence2, lastIndexOf)) {
                        return (lastIndexOf + 1) - length;
                    }
                    i3 = lastIndexOf - 1;
                } else {
                    return -1;
                }
            } while (i3 + 1 >= max + length);
            return -1;
        }
        return -1;
    }

    static int lastIndexOfNot(CharSequence charSequence, char c, int i, int i2) {
        int min = Math.min(i2, charSequence.length() - 1) + 1;
        int max = Math.max(i, 0);
        int i3 = min;
        do {
            int i4 = i3;
            i3--;
            if (i4 <= max) {
                return -1;
            }
        } while (charSequence.charAt(i3) == c);
        return i3;
    }

    static int lastIndexOfAny(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        int min = Math.min(i2, charSequence.length() - 1) + 1;
        int max = Math.max(i, 0);
        int i3 = min;
        do {
            int i4 = i3;
            i3--;
            if (i4 <= max) {
                return -1;
            }
        } while (!charPredicate.test(charSequence.charAt(i3)));
        return i3;
    }

    static boolean equals(CharSequence charSequence, Object obj) {
        if (obj == charSequence) {
            return true;
        }
        if (!(obj instanceof CharSequence)) {
            return false;
        }
        CharSequence charSequence2 = (CharSequence) obj;
        if (charSequence2.length() != charSequence.length()) {
            return false;
        }
        if (obj instanceof String) {
            if (((String) obj).hashCode() != charSequence.hashCode()) {
                return false;
            }
        } else if ((obj instanceof IRichSequence) && ((IRichSequence) obj).hashCode() != charSequence.hashCode()) {
            return false;
        }
        return matchChars(charSequence, charSequence2, 0, false);
    }

    static int hashCode(CharSequence charSequence) {
        int i = 0;
        int length = charSequence.length();
        if (length > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                i = (i * 31) + charSequence.charAt(i2);
            }
        }
        return i;
    }

    static int compareReversed(CharSequence charSequence, CharSequence charSequence2) {
        return compare(charSequence2, charSequence);
    }

    static int compare(CharSequence charSequence, CharSequence charSequence2) {
        return compare(charSequence, charSequence2, false);
    }

    static int compare(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return compare(charSequence, charSequence2, z, null);
    }

    static int compare(CharSequence charSequence, CharSequence charSequence2, boolean z, CharPredicate charPredicate) {
        char upperCase;
        char upperCase2;
        if (charSequence == null || charSequence2 == null) {
            if (charSequence == null && charSequence2 == null) {
                return 0;
            }
            return charSequence == null ? -1 : 1;
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        int min = Math.min(length, length2);
        if (z) {
            for (int i = 0; i < min; i++) {
                char charAt = charSequence.charAt(i);
                char charAt2 = charSequence2.charAt(i);
                if (charAt != charAt2 && (upperCase = Character.toUpperCase(charAt)) != (upperCase2 = Character.toUpperCase(charAt2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2) && (charPredicate == null || !charPredicate.test(charAt) || !charPredicate.test(charAt2))) {
                    return charAt - charAt2;
                }
            }
        } else {
            for (int i2 = 0; i2 < min; i2++) {
                char charAt3 = charSequence.charAt(i2);
                char charAt4 = charSequence2.charAt(i2);
                if (charAt3 != charAt4 && (charPredicate == null || !charPredicate.test(charAt3) || !charPredicate.test(charAt4))) {
                    return charAt3 - charAt4;
                }
            }
        }
        return length - length2;
    }

    static String[] toStringArray(CharSequence... charSequenceArr) {
        String[] strArr = new String[charSequenceArr.length];
        int i = 0;
        int length = charSequenceArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            strArr[i] = charSequenceArr[i] == null ? null : charSequenceArr[i].toString();
            i++;
        }
        return strArr;
    }

    static boolean isVisibleWhitespace(char c) {
        return visibleSpacesMap.containsKey(Character.valueOf(c));
    }

    static int columnsToNextTabStop(int i) {
        return 4 - (i % 4);
    }

    static int[] expandTo(int[] iArr, int i, int i2) {
        int i3 = i + ((i & i2) != 0 ? i2 : 0);
        if (iArr.length < i3) {
            int[] iArr2 = new int[i3];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            return iArr2;
        }
        return iArr;
    }

    static int[] truncateTo(int[] iArr, int i) {
        if (iArr.length > i) {
            int[] iArr2 = new int[i];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            return iArr2;
        }
        return iArr;
    }

    static int[] indexOfAll(CharSequence charSequence, CharSequence charSequence2) {
        int length = charSequence2.length();
        if (length == 0) {
            return EMPTY_INDICES;
        }
        int indexOf = indexOf(charSequence, charSequence2);
        int i = indexOf;
        if (indexOf == -1) {
            return EMPTY_INDICES;
        }
        int[] iArr = new int[32];
        int[] iArr2 = iArr;
        int i2 = 0 + 1;
        iArr[0] = i;
        while (true) {
            int indexOf2 = indexOf(charSequence, charSequence2, i + length);
            i = indexOf2;
            if (indexOf2 != -1) {
                if (iArr2.length <= i2) {
                    iArr2 = expandTo(iArr2, i2 + 1, 32);
                }
                int i3 = i2;
                i2++;
                iArr2[i3] = i;
            } else {
                return truncateTo(iArr2, i2);
            }
        }
    }

    static boolean matches(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return charSequence2.length() == charSequence.length() && matchChars(charSequence, charSequence2, 0, z);
    }

    static boolean matches(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence2.length() == charSequence.length() && matchChars(charSequence, charSequence2, 0, false);
    }

    static boolean matchesIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence2.length() == charSequence.length() && matchChars(charSequence, charSequence2, 0, true);
    }

    static boolean matchChars(CharSequence charSequence, CharSequence charSequence2, int i, boolean z) {
        return matchedCharCount(charSequence, charSequence2, i, Integer.MAX_VALUE, true, z) == charSequence2.length();
    }

    static boolean matchChars(CharSequence charSequence, CharSequence charSequence2, int i) {
        return matchChars(charSequence, charSequence2, i, false);
    }

    static boolean matchCharsIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i) {
        return matchChars(charSequence, charSequence2, i, true);
    }

    static boolean matchChars(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return matchChars(charSequence, charSequence2, 0, z);
    }

    static boolean matchChars(CharSequence charSequence, CharSequence charSequence2) {
        return matchChars(charSequence, charSequence2, 0, false);
    }

    static boolean matchCharsIgnoreCase(CharSequence charSequence, CharSequence charSequence2) {
        return matchChars(charSequence, charSequence2, 0, true);
    }

    static boolean matchCharsReversed(CharSequence charSequence, CharSequence charSequence2, int i, boolean z) {
        return i + 1 >= charSequence2.length() && matchChars(charSequence, charSequence2, (i + 1) - charSequence2.length(), z);
    }

    static boolean matchCharsReversed(CharSequence charSequence, CharSequence charSequence2, int i) {
        return i + 1 >= charSequence2.length() && matchChars(charSequence, charSequence2, (i + 1) - charSequence2.length(), false);
    }

    static boolean matchCharsReversedIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i) {
        return i + 1 >= charSequence2.length() && matchChars(charSequence, charSequence2, (i + 1) - charSequence2.length(), true);
    }

    static int matchedCharCount(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z) {
        return matchedCharCount(charSequence, charSequence2, i, Integer.MAX_VALUE, false, z);
    }

    static int matchedCharCount(CharSequence charSequence, CharSequence charSequence2, int i, boolean z) {
        return matchedCharCount(charSequence, charSequence2, i, Integer.MAX_VALUE, false, z);
    }

    static int matchedCharCount(CharSequence charSequence, CharSequence charSequence2, int i, int i2) {
        return matchedCharCount(charSequence, charSequence2, i, Integer.MAX_VALUE, false, false);
    }

    static int matchedCharCount(CharSequence charSequence, CharSequence charSequence2, int i) {
        return matchedCharCount(charSequence, charSequence2, i, Integer.MAX_VALUE, false, false);
    }

    static int matchedCharCountIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i, int i2) {
        return matchedCharCount(charSequence, charSequence2, i, Integer.MAX_VALUE, false, true);
    }

    static int matchedCharCountIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i) {
        return matchedCharCount(charSequence, charSequence2, i, Integer.MAX_VALUE, false, true);
    }

    static int matchedCharCountReversed(CharSequence charSequence, CharSequence charSequence2, int i, int i2) {
        return matchedCharCountReversed(charSequence, charSequence2, i, i2, false);
    }

    static int matchedCharCountReversedIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i, int i2) {
        return matchedCharCountReversed(charSequence, charSequence2, i, i2, true);
    }

    static int matchedCharCountReversed(CharSequence charSequence, CharSequence charSequence2, int i, boolean z) {
        return matchedCharCountReversed(charSequence, charSequence2, 0, i, z);
    }

    static int matchedCharCountReversed(CharSequence charSequence, CharSequence charSequence2, int i) {
        return matchedCharCountReversed(charSequence, charSequence2, 0, i, false);
    }

    static int matchedCharCountReversedIgnoreCase(CharSequence charSequence, CharSequence charSequence2, int i) {
        return matchedCharCountReversed(charSequence, charSequence2, 0, i, true);
    }

    static int matchedCharCount(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        char upperCase;
        char upperCase2;
        int length = charSequence2.length();
        int min = Math.min(Math.min(charSequence.length(), i2) - i, length);
        if (z && min < length) {
            return 0;
        }
        if (z2) {
            for (int i3 = 0; i3 < min; i3++) {
                char charAt = charSequence2.charAt(i3);
                char charAt2 = charSequence.charAt(i3 + i);
                if (charAt != charAt2 && (upperCase = Character.toUpperCase(charAt)) != (upperCase2 = Character.toUpperCase(charAt2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return i3;
                }
            }
        } else {
            for (int i4 = 0; i4 < min; i4++) {
                if (charSequence2.charAt(i4) != charSequence.charAt(i4 + i)) {
                    return i4;
                }
            }
        }
        return min;
    }

    static int matchedCharCountReversed(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z) {
        char upperCase;
        char upperCase2;
        int max = Math.max(0, i);
        int max2 = Math.max(0, Math.min(charSequence.length(), i2));
        int min = Math.min(max2 - max, charSequence2.length());
        int i3 = max2 - min;
        if (z) {
            int i4 = min;
            while (true) {
                int i5 = i4;
                i4--;
                if (i5 <= 0) {
                    break;
                }
                char charAt = charSequence2.charAt(i4);
                char charAt2 = charSequence.charAt(i3 + i4);
                if (charAt != charAt2 && (upperCase = Character.toUpperCase(charAt)) != (upperCase2 = Character.toUpperCase(charAt2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return (min - i4) - 1;
                }
            }
        } else {
            int i6 = min;
            do {
                int i7 = i6;
                i6--;
                if (i7 > 0) {
                }
            } while (charSequence2.charAt(i6) == charSequence.charAt(i3 + i6));
            return (min - i6) - 1;
        }
        return min;
    }

    static int countOfSpaceTab(CharSequence charSequence) {
        return countOfAny(charSequence, CharPredicate.SPACE_TAB, 0, Integer.MAX_VALUE);
    }

    static int countOfNotSpaceTab(CharSequence charSequence) {
        return countOfAny(charSequence, CharPredicate.SPACE_TAB.negate(), 0, Integer.MAX_VALUE);
    }

    static int countOfWhitespace(CharSequence charSequence) {
        return countOfAny(charSequence, CharPredicate.WHITESPACE, Integer.MAX_VALUE);
    }

    static int countOfNotWhitespace(CharSequence charSequence) {
        return countOfAny(charSequence, CharPredicate.WHITESPACE.negate(), 0, Integer.MAX_VALUE);
    }

    static int countOfAny(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return countOfAny(charSequence, charPredicate, i, Integer.MAX_VALUE);
    }

    static int countOfAny(CharSequence charSequence, CharPredicate charPredicate) {
        return countOfAny(charSequence, charPredicate, 0, Integer.MAX_VALUE);
    }

    static int countOfAnyNot(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        return countOfAny(charSequence, charPredicate.negate(), i, i2);
    }

    static int countOfAnyNot(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return countOfAny(charSequence, charPredicate.negate(), i, Integer.MAX_VALUE);
    }

    static int countOfAnyNot(CharSequence charSequence, CharPredicate charPredicate) {
        return countOfAny(charSequence, charPredicate.negate(), 0, Integer.MAX_VALUE);
    }

    static int countOfAny(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        int max = Math.max(i, 0);
        int min = Math.min(i2, charSequence.length());
        int i3 = 0;
        for (int i4 = max; i4 < min; i4++) {
            if (charPredicate.test(charSequence.charAt(i4))) {
                i3++;
            }
        }
        return i3;
    }

    static int countLeadingSpace(CharSequence charSequence) {
        return countLeading(charSequence, CharPredicate.SPACE, 0, Integer.MAX_VALUE);
    }

    static int countLeadingSpace(CharSequence charSequence, int i) {
        return countLeading(charSequence, CharPredicate.SPACE, i, Integer.MAX_VALUE);
    }

    static int countLeadingSpace(CharSequence charSequence, int i, int i2) {
        return countLeading(charSequence, CharPredicate.SPACE, i, i2);
    }

    static int countLeadingNotSpace(CharSequence charSequence) {
        return countLeading(charSequence, CharPredicate.SPACE.negate(), 0, Integer.MAX_VALUE);
    }

    static int countLeadingNotSpace(CharSequence charSequence, int i) {
        return countLeading(charSequence, CharPredicate.SPACE.negate(), i, Integer.MAX_VALUE);
    }

    static int countLeadingNotSpace(CharSequence charSequence, int i, int i2) {
        return countLeading(charSequence, CharPredicate.SPACE.negate(), i, i2);
    }

    static int countTrailingSpace(CharSequence charSequence) {
        return countTrailing(charSequence, CharPredicate.SPACE, 0, Integer.MAX_VALUE);
    }

    static int countTrailingSpace(CharSequence charSequence, int i) {
        return countTrailing(charSequence, CharPredicate.SPACE, 0, i);
    }

    static int countTrailingSpace(CharSequence charSequence, int i, int i2) {
        return countTrailing(charSequence, CharPredicate.SPACE, i, i2);
    }

    static int countTrailingNotSpace(CharSequence charSequence) {
        return countTrailing(charSequence, CharPredicate.SPACE.negate(), 0, Integer.MAX_VALUE);
    }

    static int countTrailingNotSpace(CharSequence charSequence, int i) {
        return countTrailing(charSequence, CharPredicate.SPACE.negate(), 0, i);
    }

    static int countTrailingNotSpace(CharSequence charSequence, int i, int i2) {
        return countTrailing(charSequence, CharPredicate.SPACE.negate(), i, i2);
    }

    static int countLeadingSpaceTab(CharSequence charSequence) {
        return countLeading(charSequence, CharPredicate.SPACE_TAB, 0, Integer.MAX_VALUE);
    }

    static int countLeadingSpaceTab(CharSequence charSequence, int i) {
        return countLeading(charSequence, CharPredicate.SPACE_TAB, i, Integer.MAX_VALUE);
    }

    static int countLeadingSpaceTab(CharSequence charSequence, int i, int i2) {
        return countLeading(charSequence, CharPredicate.SPACE_TAB, i, i2);
    }

    static int countLeadingNotSpaceTab(CharSequence charSequence) {
        return countLeading(charSequence, CharPredicate.SPACE_TAB.negate(), 0, Integer.MAX_VALUE);
    }

    static int countLeadingNotSpaceTab(CharSequence charSequence, int i) {
        return countLeading(charSequence, CharPredicate.SPACE_TAB.negate(), i, Integer.MAX_VALUE);
    }

    static int countLeadingNotSpaceTab(CharSequence charSequence, int i, int i2) {
        return countLeading(charSequence, CharPredicate.SPACE_TAB.negate(), i, i2);
    }

    static int countTrailingSpaceTab(CharSequence charSequence) {
        return countTrailing(charSequence, CharPredicate.SPACE_TAB, 0, Integer.MAX_VALUE);
    }

    static int countTrailingSpaceTab(CharSequence charSequence, int i) {
        return countTrailing(charSequence, CharPredicate.SPACE_TAB, 0, i);
    }

    static int countTrailingSpaceTab(CharSequence charSequence, int i, int i2) {
        return countTrailing(charSequence, CharPredicate.SPACE_TAB, i, i2);
    }

    static int countTrailingNotSpaceTab(CharSequence charSequence) {
        return countTrailing(charSequence, CharPredicate.SPACE_TAB.negate(), 0, Integer.MAX_VALUE);
    }

    static int countTrailingNotSpaceTab(CharSequence charSequence, int i) {
        return countTrailing(charSequence, CharPredicate.SPACE_TAB.negate(), 0, i);
    }

    static int countTrailingNotSpaceTab(CharSequence charSequence, int i, int i2) {
        return countTrailing(charSequence, CharPredicate.SPACE_TAB.negate(), i, i2);
    }

    static int countLeadingWhitespace(CharSequence charSequence) {
        return countLeading(charSequence, CharPredicate.WHITESPACE, 0, Integer.MAX_VALUE);
    }

    static int countLeadingWhitespace(CharSequence charSequence, int i) {
        return countLeading(charSequence, CharPredicate.WHITESPACE, i, Integer.MAX_VALUE);
    }

    static int countLeadingWhitespace(CharSequence charSequence, int i, int i2) {
        return countLeading(charSequence, CharPredicate.WHITESPACE, i, i2);
    }

    static int countLeadingNotWhitespace(CharSequence charSequence) {
        return countLeading(charSequence, CharPredicate.WHITESPACE.negate(), 0, Integer.MAX_VALUE);
    }

    static int countLeadingNotWhitespace(CharSequence charSequence, int i) {
        return countLeading(charSequence, CharPredicate.WHITESPACE.negate(), i, Integer.MAX_VALUE);
    }

    static int countLeadingNotWhitespace(CharSequence charSequence, int i, int i2) {
        return countLeading(charSequence, CharPredicate.WHITESPACE.negate(), i, i2);
    }

    static int countTrailingWhitespace(CharSequence charSequence) {
        return countTrailing(charSequence, CharPredicate.WHITESPACE, 0, Integer.MAX_VALUE);
    }

    static int countTrailingWhitespace(CharSequence charSequence, int i) {
        return countTrailing(charSequence, CharPredicate.WHITESPACE, 0, i);
    }

    static int countTrailingWhitespace(CharSequence charSequence, int i, int i2) {
        return countTrailing(charSequence, CharPredicate.WHITESPACE, i, i2);
    }

    static int countTrailingNotWhitespace(CharSequence charSequence) {
        return countTrailing(charSequence, CharPredicate.WHITESPACE.negate(), 0, Integer.MAX_VALUE);
    }

    static int countTrailingNotWhitespace(CharSequence charSequence, int i) {
        return countTrailing(charSequence, CharPredicate.WHITESPACE.negate(), 0, i);
    }

    static int countTrailingNotWhitespace(CharSequence charSequence, int i, int i2) {
        return countTrailing(charSequence, CharPredicate.WHITESPACE.negate(), i, i2);
    }

    static int countLeading(CharSequence charSequence, CharPredicate charPredicate) {
        return countLeading(charSequence, charPredicate, 0, Integer.MAX_VALUE);
    }

    static int countLeading(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return countLeading(charSequence, charPredicate, i, Integer.MAX_VALUE);
    }

    static int countLeadingNot(CharSequence charSequence, CharPredicate charPredicate) {
        return countLeading(charSequence, charPredicate.negate(), 0, Integer.MAX_VALUE);
    }

    static int countLeadingNot(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return countLeading(charSequence, charPredicate.negate(), i, Integer.MAX_VALUE);
    }

    static int countTrailing(CharSequence charSequence, CharPredicate charPredicate) {
        return countTrailing(charSequence, charPredicate, 0, Integer.MAX_VALUE);
    }

    static int countTrailing(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return countTrailing(charSequence, charPredicate, 0, i);
    }

    static int countTrailingNot(CharSequence charSequence, CharPredicate charPredicate) {
        return countTrailing(charSequence, charPredicate.negate(), 0, Integer.MAX_VALUE);
    }

    static int countTrailingNot(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return countTrailing(charSequence, charPredicate.negate(), 0, i);
    }

    static int countLeadingNot(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        return countLeading(charSequence, charPredicate.negate(), i, i2);
    }

    static int countTrailingNot(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        return countTrailing(charSequence, charPredicate.negate(), i, i2);
    }

    static int countLeading(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        int min = Math.min(i2, charSequence.length());
        int rangeLimit = Utils.rangeLimit(i, 0, min);
        int indexOfAnyNot = indexOfAnyNot(charSequence, charPredicate, rangeLimit, min);
        return indexOfAnyNot == -1 ? min - rangeLimit : indexOfAnyNot - rangeLimit;
    }

    static int countLeadingColumns(CharSequence charSequence, int i, CharPredicate charPredicate) {
        int length = charSequence.length();
        int indexOfAnyNot = indexOfAnyNot(charSequence, charPredicate, 0, length);
        int i2 = indexOfAnyNot == -1 ? length : indexOfAnyNot;
        int i3 = indexOfAnyNot == -1 ? length : indexOfAnyNot;
        int indexOf = indexOf(charSequence, '\t', 0, i2);
        int i4 = indexOf;
        if (indexOf != -1) {
            int i5 = i;
            do {
                int i6 = i4;
                i5 += i6 + columnsToNextTabStop(i6 + i5);
                int indexOf2 = indexOf(charSequence, '\t', i4 + 1);
                i4 = indexOf2;
                if (indexOf2 < 0) {
                    break;
                }
            } while (i4 < length);
            i3 += i5;
        }
        return i3;
    }

    static int countTrailing(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        int min = Math.min(i2, charSequence.length());
        int rangeLimit = Utils.rangeLimit(i, 0, min);
        int lastIndexOfAnyNot = lastIndexOfAnyNot(charSequence, charPredicate, rangeLimit, min - 1);
        if (lastIndexOfAnyNot == -1) {
            return min - rangeLimit;
        }
        if (min <= lastIndexOfAnyNot) {
            return 0;
        }
        return (min - lastIndexOfAnyNot) - 1;
    }

    static <T extends CharSequence> T trimStart(T t, CharPredicate charPredicate) {
        return (T) subSequence(t, trimStartRange(t, 0, charPredicate));
    }

    static <T extends CharSequence> T trimmedStart(T t, CharPredicate charPredicate) {
        return (T) trimmedStart(t, 0, charPredicate);
    }

    static <T extends CharSequence> T trimEnd(T t, CharPredicate charPredicate) {
        return (T) trimEnd(t, 0, charPredicate);
    }

    static <T extends CharSequence> T trimmedEnd(T t, CharPredicate charPredicate) {
        return (T) trimmedEnd(t, 0, charPredicate);
    }

    static <T extends CharSequence> T trim(T t, CharPredicate charPredicate) {
        return (T) trim(t, 0, charPredicate);
    }

    static <T extends CharSequence> Pair<T, T> trimmed(T t, CharPredicate charPredicate) {
        return trimmed(t, 0, charPredicate);
    }

    static <T extends CharSequence> T trimStart(T t, int i) {
        return (T) trimStart(t, i, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> T trimmedStart(T t, int i) {
        return (T) trimmedStart(t, i, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> T trimEnd(T t, int i) {
        return (T) trimEnd(t, i, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> T trimmedEnd(T t, int i) {
        return (T) trimmedEnd(t, i, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> T trim(T t, int i) {
        return (T) trim(t, i, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> Pair<T, T> trimmed(T t, int i) {
        return trimmed(t, i, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> T trimStart(T t) {
        return (T) trimStart(t, 0, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> T trimmedStart(T t) {
        return (T) trimmedStart(t, 0, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> T trimEnd(T t) {
        return (T) trimEnd(t, 0, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> T trimmedEnd(T t) {
        return (T) trimmedEnd(t, 0, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> T trim(T t) {
        return (T) trim(t, 0, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> Pair<T, T> trimmed(T t) {
        return trimmed(t, 0, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> T trimStart(T t, int i, CharPredicate charPredicate) {
        return (T) subSequence(t, trimStartRange(t, i, charPredicate));
    }

    static <T extends CharSequence> T trimmedStart(T t, int i, CharPredicate charPredicate) {
        return (T) subSequenceBefore(t, trimStartRange(t, i, charPredicate));
    }

    static <T extends CharSequence> T trimEnd(T t, int i, CharPredicate charPredicate) {
        return (T) subSequence(t, trimEndRange(t, i, charPredicate));
    }

    static <T extends CharSequence> T trimmedEnd(T t, int i, CharPredicate charPredicate) {
        return (T) subSequenceAfter(t, trimEndRange(t, i, charPredicate));
    }

    static <T extends CharSequence> T trim(T t, int i, CharPredicate charPredicate) {
        return (T) subSequence(t, trimRange(t, i, charPredicate));
    }

    static <T extends CharSequence> Pair<T, T> trimmed(T t, int i, CharPredicate charPredicate) {
        return subSequenceBeforeAfter(t, trimRange(t, i, charPredicate));
    }

    static Range trimStartRange(CharSequence charSequence, CharPredicate charPredicate) {
        return trimStartRange(charSequence, 0, charPredicate);
    }

    static Range trimEndRange(CharSequence charSequence, CharPredicate charPredicate) {
        return trimEndRange(charSequence, 0, charPredicate);
    }

    static Range trimRange(CharSequence charSequence, CharPredicate charPredicate) {
        return trimRange(charSequence, 0, charPredicate);
    }

    static Range trimStartRange(CharSequence charSequence, int i) {
        return trimStartRange(charSequence, i, CharPredicate.WHITESPACE);
    }

    static Range trimEndRange(CharSequence charSequence, int i) {
        return trimEndRange(charSequence, i, CharPredicate.WHITESPACE);
    }

    static Range trimRange(CharSequence charSequence, int i) {
        return trimRange(charSequence, i, CharPredicate.WHITESPACE);
    }

    static Range trimStartRange(CharSequence charSequence) {
        return trimStartRange(charSequence, 0, CharPredicate.WHITESPACE);
    }

    static Range trimEndRange(CharSequence charSequence) {
        return trimEndRange(charSequence, 0, CharPredicate.WHITESPACE);
    }

    static Range trimRange(CharSequence charSequence) {
        return trimRange(charSequence, 0, CharPredicate.WHITESPACE);
    }

    static Range trimStartRange(CharSequence charSequence, int i, CharPredicate charPredicate) {
        int length = charSequence.length();
        int countLeading = countLeading(charSequence, charPredicate, 0, length);
        return countLeading > i ? Range.of(countLeading - i, length) : Range.NULL;
    }

    static Range trimEndRange(CharSequence charSequence, int i, CharPredicate charPredicate) {
        int length = charSequence.length();
        int countTrailing = countTrailing(charSequence, charPredicate, 0, length);
        return countTrailing > i ? Range.of(0, (length - countTrailing) + i) : Range.NULL;
    }

    static Range trimRange(CharSequence charSequence, int i, CharPredicate charPredicate) {
        int length = charSequence.length();
        if (i >= length) {
            return Range.NULL;
        }
        int countLeading = countLeading(charSequence, charPredicate, 0, length);
        if (countLeading > i) {
            int countTrailing = countTrailing(charSequence, charPredicate, countLeading - i, length);
            if (countTrailing <= i) {
                return Range.of(countLeading - i, length);
            }
            return Range.of(countLeading - i, (length - countTrailing) + i);
        }
        int countTrailing2 = countTrailing(charSequence, charPredicate, countLeading, length);
        return countTrailing2 > i ? Range.of(0, (length - countTrailing2) + i) : Range.NULL;
    }

    static String padStart(CharSequence charSequence, int i, char c) {
        return i <= charSequence.length() ? "" : RepeatedSequence.repeatOf(c, i - charSequence.length()).toString();
    }

    static String padEnd(CharSequence charSequence, int i, char c) {
        return i <= charSequence.length() ? "" : RepeatedSequence.repeatOf(c, i - charSequence.length()).toString();
    }

    static String padStart(CharSequence charSequence, int i) {
        return padStart(charSequence, i, ' ');
    }

    static String padEnd(CharSequence charSequence, int i) {
        return padEnd(charSequence, i, ' ');
    }

    static String toVisibleWhitespaceString(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder();
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            String str = visibleSpacesMap.get(Character.valueOf(charAt));
            if (str != null) {
                sb.append(str);
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    static char lastChar(CharSequence charSequence) {
        if (charSequence.length() == 0) {
            return (char) 0;
        }
        return charSequence.charAt(charSequence.length() - 1);
    }

    static char firstChar(CharSequence charSequence) {
        if (charSequence.length() == 0) {
            return (char) 0;
        }
        return charSequence.charAt(0);
    }

    static char safeCharAt(CharSequence charSequence, int i) {
        if (i < 0 || i >= charSequence.length()) {
            return (char) 0;
        }
        return charSequence.charAt(i);
    }

    static int eolEndLength(CharSequence charSequence) {
        return eolEndLength(charSequence, charSequence.length());
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x002e, code lost:            if (safeCharAt(r4, r0 + 1) != '\n') goto L16;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static int eolEndLength(java.lang.CharSequence r4, int r5) {
        /*
            r0 = r5
            r1 = 1
            int r0 = r0 - r1
            r1 = r4
            int r1 = r1.length()
            r2 = 1
            int r1 = r1 - r2
            int r0 = java.lang.Math.min(r0, r1)
            r1 = r0
            r5 = r1
            if (r0 >= 0) goto L15
            r0 = 0
            return r0
        L15:
            r0 = 0
            r6 = r0
            r0 = r4
            r1 = r5
            char r0 = r0.charAt(r1)
            r1 = r0
            r7 = r1
            r1 = 13
            if (r0 != r1) goto L34
            r0 = r4
            r1 = r5
            r2 = 1
            int r1 = r1 + r2
            char r0 = safeCharAt(r0, r1)
            r1 = 10
            if (r0 == r1) goto L4d
            goto L4b
        L34:
            r0 = r7
            r1 = 10
            if (r0 != r1) goto L4d
            r0 = r4
            r1 = r5
            r2 = 1
            int r1 = r1 - r2
            char r0 = safeCharAt(r0, r1)
            r1 = 13
            if (r0 != r1) goto L4b
            r0 = 2
            r6 = r0
            goto L4d
        L4b:
            r0 = 1
            r6 = r0
        L4d:
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.util.sequence.SequenceUtils.eolEndLength(java.lang.CharSequence, int):int");
    }

    static int eolStartLength(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int min = Math.min(i, length);
        int i2 = 0;
        if (min >= 0 && min < length) {
            char charAt = charSequence.charAt(min);
            if (charAt == '\r') {
                i2 = safeCharAt(charSequence, min + 1) == '\n' ? 2 : 1;
            } else if (charAt == '\n' && safeCharAt(charSequence, min - 1) != '\r') {
                i2 = 1;
            }
        }
        return i2;
    }

    static int endOfLine(CharSequence charSequence, int i) {
        return endOfDelimitedBy(charSequence, EOL, i);
    }

    static int endOfLineAnyEOL(CharSequence charSequence, int i) {
        return endOfDelimitedByAny(charSequence, CharPredicate.ANY_EOL, i);
    }

    static int startOfLine(CharSequence charSequence, int i) {
        return startOfDelimitedBy(charSequence, EOL, i);
    }

    static int startOfLineAnyEOL(CharSequence charSequence, int i) {
        return startOfDelimitedByAny(charSequence, CharPredicate.ANY_EOL, i);
    }

    static int startOfDelimitedByAnyNot(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return startOfDelimitedByAny(charSequence, charPredicate.negate(), i);
    }

    static int endOfDelimitedByAnyNot(CharSequence charSequence, CharPredicate charPredicate, int i) {
        return endOfDelimitedByAny(charSequence, charPredicate.negate(), i);
    }

    static int startOfDelimitedBy(CharSequence charSequence, CharSequence charSequence2, int i) {
        int lastIndexOf = lastIndexOf(charSequence, charSequence2, Utils.rangeLimit(i, 0, charSequence.length()) - 1);
        if (lastIndexOf == -1) {
            return 0;
        }
        return lastIndexOf + 1;
    }

    static int startOfDelimitedByAny(CharSequence charSequence, CharPredicate charPredicate, int i) {
        int lastIndexOfAny = lastIndexOfAny(charSequence, charPredicate, Utils.rangeLimit(i, 0, charSequence.length()) - 1);
        if (lastIndexOfAny == -1) {
            return 0;
        }
        return lastIndexOfAny + 1;
    }

    static int endOfDelimitedBy(CharSequence charSequence, CharSequence charSequence2, int i) {
        int length = charSequence.length();
        int indexOf = indexOf(charSequence, charSequence2, Utils.rangeLimit(i, 0, length));
        return indexOf == -1 ? length : indexOf;
    }

    static int endOfDelimitedByAny(CharSequence charSequence, CharPredicate charPredicate, int i) {
        int length = charSequence.length();
        int indexOfAny = indexOfAny(charSequence, charPredicate, Utils.rangeLimit(i, 0, length));
        return indexOfAny == -1 ? length : indexOfAny;
    }

    static Range lineRangeAt(CharSequence charSequence, int i) {
        return Range.of(startOfLine(charSequence, i), endOfLine(charSequence, i));
    }

    static Range lineRangeAtAnyEOL(CharSequence charSequence, int i) {
        return Range.of(startOfLineAnyEOL(charSequence, i), endOfLineAnyEOL(charSequence, i));
    }

    static Range eolEndRange(CharSequence charSequence, int i) {
        int eolEndLength = eolEndLength(charSequence, i);
        return eolEndLength == 0 ? Range.NULL : Range.of(i - eolEndLength, i);
    }

    static Range eolStartRange(CharSequence charSequence, int i) {
        int eolStartLength = eolStartLength(charSequence, i);
        return eolStartLength == 0 ? Range.NULL : Range.of(i, i + eolStartLength);
    }

    static <T extends CharSequence> T trimEOL(T t) {
        int eolEndLength = eolEndLength(t);
        return eolEndLength > 0 ? (T) t.subSequence(0, t.length() - eolEndLength) : t;
    }

    static <T extends CharSequence> T trimmedEOL(T t) {
        int eolEndLength = eolEndLength(t);
        if (eolEndLength > 0) {
            return (T) t.subSequence(t.length() - eolEndLength, t.length());
        }
        return null;
    }

    static <T extends CharSequence> T trimTailBlankLines(T t) {
        Range trailingBlankLinesRange = trailingBlankLinesRange(t);
        return trailingBlankLinesRange.isNull() ? t : (T) subSequenceBefore(t, trailingBlankLinesRange);
    }

    static <T extends CharSequence> T trimLeadBlankLines(T t) {
        Range leadingBlankLinesRange = leadingBlankLinesRange(t);
        return leadingBlankLinesRange.isNull() ? t : (T) subSequenceAfter(t, leadingBlankLinesRange);
    }

    static Range leadingBlankLinesRange(CharSequence charSequence) {
        return leadingBlankLinesRange(charSequence, CharPredicate.EOL, 0, Integer.MAX_VALUE);
    }

    static Range leadingBlankLinesRange(CharSequence charSequence, int i) {
        return leadingBlankLinesRange(charSequence, CharPredicate.EOL, i, Integer.MAX_VALUE);
    }

    static Range leadingBlankLinesRange(CharSequence charSequence, int i, int i2) {
        return leadingBlankLinesRange(charSequence, CharPredicate.EOL, i, i2);
    }

    static Range trailingBlankLinesRange(CharSequence charSequence) {
        return trailingBlankLinesRange(charSequence, CharPredicate.EOL, 0, Integer.MAX_VALUE);
    }

    static Range trailingBlankLinesRange(CharSequence charSequence, int i) {
        return trailingBlankLinesRange(charSequence, CharPredicate.EOL, i, Integer.MAX_VALUE);
    }

    static Range trailingBlankLinesRange(CharSequence charSequence, int i, int i2) {
        return trailingBlankLinesRange(charSequence, CharPredicate.EOL, i, i2);
    }

    static Range trailingBlankLinesRange(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        int min = Math.min(i2, charSequence.length());
        int rangeLimit = Utils.rangeLimit(i, 0, min);
        int i3 = min;
        int i4 = min;
        while (true) {
            int i5 = i4;
            i4--;
            if (i5 <= rangeLimit) {
                break;
            }
            char charAt = charSequence.charAt(i4);
            if (!charPredicate.test(charAt)) {
                if (charAt != ' ' && charAt != '\t') {
                    break;
                }
            } else {
                i3 = Math.min(i4 + Math.min(eolStartLength(charSequence, i4), 1), min);
            }
        }
        return i4 < rangeLimit ? Range.of(rangeLimit, min) : i3 != min ? Range.of(i3, min) : Range.NULL;
    }

    static Range leadingBlankLinesRange(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        int min = Math.min(i2, charSequence.length());
        int rangeLimit = Utils.rangeLimit(i, 0, min);
        int i3 = -1;
        int i4 = rangeLimit;
        while (i4 < min) {
            char charAt = charSequence.charAt(i4);
            if (!charPredicate.test(charAt)) {
                if (charAt != ' ' && charAt != '\t') {
                    break;
                }
            } else {
                i3 = i4;
            }
            i4++;
        }
        return i4 == min ? Range.of(rangeLimit, min) : i3 >= 0 ? Range.of(rangeLimit, Math.min(i3 + Math.min(eolStartLength(charSequence, i3), 1), min)) : Range.NULL;
    }

    static List<Range> blankLinesRemovedRanges(CharSequence charSequence) {
        return blankLinesRemovedRanges(charSequence, CharPredicate.EOL, 0, Integer.MAX_VALUE);
    }

    static List<Range> blankLinesRemovedRanges(CharSequence charSequence, int i) {
        return blankLinesRemovedRanges(charSequence, CharPredicate.EOL, i, Integer.MAX_VALUE);
    }

    static List<Range> blankLinesRemovedRanges(CharSequence charSequence, int i, int i2) {
        return blankLinesRemovedRanges(charSequence, CharPredicate.EOL, i, i2);
    }

    static List<Range> blankLinesRemovedRanges(CharSequence charSequence, CharPredicate charPredicate, int i, int i2) {
        int min = Math.min(i2, charSequence.length());
        int rangeLimit = Utils.rangeLimit(i, 0, min);
        ArrayList arrayList = new ArrayList();
        while (rangeLimit < min) {
            Range leadingBlankLinesRange = leadingBlankLinesRange(charSequence, charPredicate, rangeLimit, min);
            if (!leadingBlankLinesRange.isNull()) {
                if (rangeLimit < leadingBlankLinesRange.getStart()) {
                    arrayList.add(Range.of(rangeLimit, leadingBlankLinesRange.getStart()));
                }
                rangeLimit = leadingBlankLinesRange.getEnd();
            } else {
                int min2 = Math.min(endOfLine(charSequence, rangeLimit) + 1, min);
                if (rangeLimit < min2) {
                    arrayList.add(Range.of(rangeLimit, min2));
                }
                rangeLimit = min2;
            }
        }
        return arrayList;
    }

    static boolean isEmpty(CharSequence charSequence) {
        return charSequence.length() == 0;
    }

    static boolean isBlank(CharSequence charSequence) {
        return isEmpty(charSequence) || countLeading(charSequence, CharPredicate.WHITESPACE, 0, Integer.MAX_VALUE) == charSequence.length();
    }

    static boolean isNotEmpty(CharSequence charSequence) {
        return charSequence.length() != 0;
    }

    static boolean isNotBlank(CharSequence charSequence) {
        return !isBlank(charSequence);
    }

    static boolean endsWith(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence.length() > 0 && matchCharsReversed(charSequence, charSequence2, charSequence.length() - 1, false);
    }

    static boolean endsWith(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return charSequence.length() > 0 && matchCharsReversed(charSequence, charSequence2, charSequence.length() - 1, z);
    }

    static boolean startsWith(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence.length() > 0 && matchChars(charSequence, charSequence2, 0, false);
    }

    static boolean startsWith(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return charSequence.length() > 0 && matchChars(charSequence, charSequence2, 0, z);
    }

    static boolean endsWith(CharSequence charSequence, CharPredicate charPredicate) {
        return countTrailing(charSequence, charPredicate) > 0;
    }

    static boolean startsWith(CharSequence charSequence, CharPredicate charPredicate) {
        return countLeading(charSequence, charPredicate) > 0;
    }

    static boolean endsWithEOL(CharSequence charSequence) {
        return endsWith(charSequence, CharPredicate.EOL);
    }

    static boolean endsWithAnyEOL(CharSequence charSequence) {
        return endsWith(charSequence, CharPredicate.ANY_EOL);
    }

    static boolean endsWithSpace(CharSequence charSequence) {
        return endsWith(charSequence, CharPredicate.SPACE);
    }

    static boolean endsWithSpaceTab(CharSequence charSequence) {
        return endsWith(charSequence, CharPredicate.SPACE_TAB);
    }

    static boolean endsWithWhitespace(CharSequence charSequence) {
        return endsWith(charSequence, CharPredicate.WHITESPACE);
    }

    static boolean startsWithEOL(CharSequence charSequence) {
        return startsWith(charSequence, CharPredicate.EOL);
    }

    static boolean startsWithAnyEOL(CharSequence charSequence) {
        return startsWith(charSequence, CharPredicate.ANY_EOL);
    }

    static boolean startsWithSpace(CharSequence charSequence) {
        return startsWith(charSequence, CharPredicate.SPACE);
    }

    static boolean startsWithSpaceTab(CharSequence charSequence) {
        return startsWith(charSequence, CharPredicate.SPACE_TAB);
    }

    static boolean startsWithWhitespace(CharSequence charSequence) {
        return startsWith(charSequence, CharPredicate.WHITESPACE);
    }

    static <T extends CharSequence> List<T> splitList(T t, CharSequence charSequence) {
        return splitList(t, charSequence, 0, 0, (CharPredicate) null);
    }

    static <T extends CharSequence> List<T> splitList(T t, CharSequence charSequence, int i, boolean z, CharPredicate charPredicate) {
        return splitList(t, charSequence, i, z ? 1 : 0, charPredicate);
    }

    static <T extends CharSequence> List<T> splitList(T t, CharSequence charSequence, int i, int i2) {
        return splitList(t, charSequence, i, i2, (CharPredicate) null);
    }

    static <T extends CharSequence> List<T> splitList(T t, CharSequence charSequence, boolean z, CharPredicate charPredicate) {
        return splitList(t, charSequence, 0, z ? 1 : 0, charPredicate);
    }

    static <T extends CharSequence> List<T> splitListEOL(T t) {
        return splitList(t, EOL, 0, 1, (CharPredicate) null);
    }

    static <T extends CharSequence> List<T> splitListEOL(T t, boolean z) {
        return splitList(t, EOL, 0, z ? 1 : 0, (CharPredicate) null);
    }

    static <T extends CharSequence> List<T> splitListEOL(T t, boolean z, CharPredicate charPredicate) {
        return splitList(t, EOL, 0, z ? 1 : 0, charPredicate);
    }

    static <T extends CharSequence> T[] splitEOL(T t, T[] tArr) {
        return (T[]) split(t, tArr, EOL, 0, 1, (CharPredicate) null);
    }

    static <T extends CharSequence> T[] splitEOL(T t, T[] tArr, boolean z) {
        return (T[]) split(t, tArr, EOL, 0, z ? 1 : 0, (CharPredicate) null);
    }

    static <T extends CharSequence> T[] split(T t, T[] tArr, CharSequence charSequence, boolean z, CharPredicate charPredicate) {
        return (T[]) split(t, tArr, EOL, 0, z ? 1 : 0, charPredicate);
    }

    static <T extends CharSequence> T[] split(T t, T[] tArr, CharSequence charSequence) {
        return (T[]) split(t, tArr, charSequence, 0, 0, (CharPredicate) null);
    }

    static <T extends CharSequence> T[] split(T t, T[] tArr, CharSequence charSequence, int i, boolean z, CharPredicate charPredicate) {
        return (T[]) split(t, tArr, charSequence, i, z ? 1 : 0, charPredicate);
    }

    static <T extends CharSequence> T[] split(T t, T[] tArr, CharSequence charSequence, int i, int i2) {
        return (T[]) split(t, tArr, charSequence, i, i2, (CharPredicate) null);
    }

    static <T extends CharSequence> T[] split(T t, T[] tArr, CharSequence charSequence, int i, int i2, CharPredicate charPredicate) {
        return (T[]) ((CharSequence[]) splitList(t, charSequence, i, i2, charPredicate).toArray(tArr));
    }

    static <T extends CharSequence> List<T> splitList(T t, CharSequence charSequence, int i, int i2, CharPredicate charPredicate) {
        int indexOf;
        if (charPredicate == null) {
            charPredicate = CharPredicate.WHITESPACE;
        } else {
            i2 |= 2;
        }
        if (i <= 0) {
            i = Integer.MAX_VALUE;
        }
        boolean z = (i2 & 8) != 0;
        boolean z2 = z;
        int length = (z || (i2 & 1) == 0) ? 0 : charSequence.length();
        boolean z3 = (i2 & 2) != 0;
        boolean z4 = (i2 & 4) != 0;
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int length2 = t.length();
        if (i > 1) {
            while (true) {
                if (i3 >= length2 || (indexOf = indexOf(t, charSequence, i3)) < 0) {
                    break;
                }
                if (i3 < indexOf || !z4) {
                    CharSequence subSequence = t.subSequence(i3, indexOf + length);
                    if (z3) {
                        subSequence = trim(subSequence, charPredicate);
                    }
                    if (!isEmpty(subSequence) || !z4) {
                        arrayList.add(subSequence);
                        if (z2) {
                            arrayList.add(t.subSequence(indexOf, indexOf + charSequence.length()));
                        }
                        if (arrayList.size() >= i - 1) {
                            i3 = indexOf + 1;
                            break;
                        }
                    }
                }
                i3 = indexOf + 1;
            }
        }
        if (i3 < length2) {
            CharSequence subSequence2 = t.subSequence(i3, length2);
            if (z3) {
                subSequence2 = trim(subSequence2, charPredicate);
            }
            if (!isEmpty(subSequence2) || !z4) {
                arrayList.add(subSequence2);
            }
        }
        return arrayList;
    }

    static int columnAtIndex(CharSequence charSequence, int i) {
        int lastIndexOfAny = lastIndexOfAny(charSequence, CharPredicate.ANY_EOL, i);
        return i - (lastIndexOfAny == -1 ? 0 : lastIndexOfAny + eolStartLength(charSequence, lastIndexOfAny));
    }

    static Pair<Integer, Integer> lineColumnAtIndex(CharSequence charSequence, int i) {
        int length = charSequence.length();
        if (i < 0 || i > length) {
            throw new IllegalArgumentException("Index: " + i + " out of range [0, " + length + "]");
        }
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            char charAt = charSequence.charAt(i4);
            if (charAt == '\r') {
                i3 = 0;
                i2++;
                z = true;
            } else if (charAt == '\n') {
                if (!z) {
                    i2++;
                }
                i3 = 0;
                z = false;
            } else {
                i3++;
            }
        }
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i3));
    }

    static void validateIndex(int i, int i2) {
        if (i < 0 || i >= i2) {
            throw new StringIndexOutOfBoundsException("String index: " + i + " out of range: [0, " + i2 + ")");
        }
    }

    static void validateIndexInclusiveEnd(int i, int i2) {
        if (i < 0 || i > i2) {
            throw new StringIndexOutOfBoundsException("index: " + i + " out of range: [0, " + i2 + "]");
        }
    }

    static void validateStartEnd(int i, int i2, int i3) {
        if (i < 0 || i > i3) {
            throw new StringIndexOutOfBoundsException("startIndex: " + i + " out of range: [0, " + i3 + ")");
        }
        if (i2 < i || i2 > i3) {
            throw new StringIndexOutOfBoundsException("endIndex: " + i2 + " out of range: [" + i + ", " + i3 + "]");
        }
    }

    static Integer parseUnsignedIntOrNull(String str) {
        return parseUnsignedIntOrNull(str, 10);
    }

    static Integer parseUnsignedIntOrNull(String str, int i) {
        try {
            int parseInt = Integer.parseInt(str, i);
            if (parseInt >= 0) {
                return Integer.valueOf(parseInt);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Integer parseIntOrNull(String str) {
        return parseIntOrNull(str, 10);
    }

    static Integer parseIntOrNull(String str, int i) {
        try {
            return Integer.valueOf(Integer.parseInt(str, i));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Long parseLongOrNull(String str) {
        return parseLongOrNull(str, 10);
    }

    static Long parseLongOrNull(String str, int i) {
        try {
            return Long.valueOf(Long.parseLong(str, i));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static int parseUnsignedIntOrDefault(String str, int i) {
        return parseUnsignedIntOrDefault(str, i, 10);
    }

    static int parseUnsignedIntOrDefault(String str, int i, int i2) {
        try {
            int parseInt = Integer.parseInt(str, i2);
            return parseInt >= 0 ? parseInt : i;
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    static int parseIntOrDefault(String str, int i) {
        return parseIntOrDefault(str, i, 10);
    }

    static int parseIntOrDefault(String str, int i, int i2) {
        try {
            return Integer.parseInt(str, i2);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    static Number parseNumberOrNull(String str) {
        Long parseLongOrNull;
        if (str == null) {
            return null;
        }
        if (str.startsWith("0x")) {
            return parseLongOrNull(str.substring(2), 16);
        }
        if (str.startsWith("0b")) {
            return parseLongOrNull(str.substring(2), 2);
        }
        if (str.startsWith("0") && (parseLongOrNull = parseLongOrNull(str.substring(1), 8)) != null) {
            return parseLongOrNull;
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        ParsePosition parsePosition = new ParsePosition(0);
        Number parse = numberFormat.parse(str, parsePosition);
        if (parsePosition.getIndex() == str.length()) {
            return parse;
        }
        return null;
    }

    static Pair<Number, String> parseNumberPrefixOrNull(String str, Predicate<String> predicate) {
        int countLeading;
        if (str == null) {
            return null;
        }
        if (str.startsWith("0x")) {
            int countLeading2 = countLeading(str.substring(2), CharPredicate.HEXADECIMAL_DIGITS);
            String substring = str.substring(countLeading2 + 2);
            if (countLeading2 > 0 && (substring.isEmpty() || predicate == null || predicate.test(substring))) {
                return Pair.of(parseLongOrNull(str.substring(2, countLeading2 + 2), 16), substring);
            }
        } else if (str.startsWith("0b")) {
            int countLeading3 = countLeading(str.substring(2), CharPredicate.BINARY_DIGITS);
            String substring2 = str.substring(countLeading3 + 2);
            if (countLeading3 > 0 && (substring2.isEmpty() || predicate == null || predicate.test(substring2))) {
                return Pair.of(parseLongOrNull(str.substring(2, countLeading3 + 2), 2), substring2);
            }
        } else if (str.startsWith("0") && (countLeading = countLeading(str.substring(1), CharPredicate.OCTAL_DIGITS)) == countLeading(str.substring(1), CharPredicate.DECIMAL_DIGITS)) {
            String substring3 = str.substring(countLeading + 1);
            if (countLeading > 0 && (substring3.isEmpty() || predicate == null || predicate.test(substring3))) {
                return Pair.of(parseLongOrNull(str.substring(1, countLeading + 1), 8), substring3);
            }
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        ParsePosition parsePosition = new ParsePosition(0);
        Number parse = numberFormat.parse(str, parsePosition);
        String substring4 = str.substring(parsePosition.getIndex());
        if (parsePosition.getIndex() <= 0) {
            return null;
        }
        if (substring4.isEmpty() || predicate == null || predicate.test(substring4)) {
            return Pair.of(parse, substring4);
        }
        return null;
    }

    static <T extends CharSequence> boolean containedBy(T[] tArr, CharSequence charSequence) {
        for (T t : tArr) {
            if (equals(charSequence, t)) {
                return true;
            }
        }
        return false;
    }

    static boolean containedBy(Collection<? extends CharSequence> collection, CharSequence charSequence) {
        Iterator<? extends CharSequence> it = collection.iterator();
        while (it.hasNext()) {
            if (equals(charSequence, it.next())) {
                return true;
            }
        }
        return false;
    }
}
