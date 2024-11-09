package com.vladsch.flexmark.util.misc;

import java.util.BitSet;
import java.util.Objects;
import java.util.function.IntPredicate;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/CharPredicate.class */
public interface CharPredicate extends IntPredicate {
    public static final CharPredicate NONE;
    public static final CharPredicate ALL;
    public static final CharPredicate SPACE;
    public static final CharPredicate TAB;
    public static final CharPredicate EOL;
    public static final CharPredicate ANY_EOL;
    public static final CharPredicate ANY_EOL_NUL;
    public static final CharPredicate BACKSLASH;
    public static final CharPredicate SLASH;
    public static final CharPredicate LINE_SEP;
    public static final CharPredicate HASH;
    public static final CharPredicate SPACE_TAB;
    public static final CharPredicate SPACE_TAB_NUL;
    public static final CharPredicate SPACE_TAB_LINE_SEP;
    public static final CharPredicate SPACE_TAB_NBSP_LINE_SEP;
    public static final CharPredicate SPACE_EOL;
    public static final CharPredicate SPACE_ANY_EOL;
    public static final CharPredicate SPACE_TAB_NBSP;
    public static final CharPredicate SPACE_TAB_EOL;
    public static final CharPredicate SPACE_TAB_NBSP_EOL;
    public static final CharPredicate WHITESPACE;
    public static final CharPredicate WHITESPACE_OR_NUL;
    public static final CharPredicate WHITESPACE_NBSP;
    public static final CharPredicate WHITESPACE_NBSP_OR_NUL;
    public static final CharPredicate BLANKSPACE;
    public static final CharPredicate HEXADECIMAL_DIGITS;
    public static final CharPredicate DECIMAL_DIGITS;
    public static final CharPredicate OCTAL_DIGITS;
    public static final CharPredicate BINARY_DIGITS;

    @Deprecated
    public static final CharPredicate FALSE;

    @Deprecated
    public static final CharPredicate TRUE;

    @Deprecated
    public static final CharPredicate SPACE_TAB_OR_NUL;

    @Override // java.util.function.IntPredicate
    boolean test(int i);

    static {
        boolean z = AnonymousClass1.$assertionsDisabled;
        NONE = i -> {
            return false;
        };
        ALL = i2 -> {
            return true;
        };
        SPACE = i3 -> {
            return i3 == 32;
        };
        TAB = i4 -> {
            return i4 == 9;
        };
        EOL = i5 -> {
            return i5 == 10;
        };
        ANY_EOL = i6 -> {
            return i6 == 10 || i6 == 13;
        };
        ANY_EOL_NUL = i7 -> {
            return i7 == 10 || i7 == 13 || i7 == 0;
        };
        BACKSLASH = i8 -> {
            return i8 == 92;
        };
        SLASH = i9 -> {
            return i9 == 47;
        };
        LINE_SEP = i10 -> {
            return i10 == 8232;
        };
        HASH = i11 -> {
            return i11 == 35;
        };
        SPACE_TAB = i12 -> {
            return i12 == 32 || i12 == 9;
        };
        SPACE_TAB_NUL = i13 -> {
            return i13 == 32 || i13 == 9 || i13 == 0;
        };
        SPACE_TAB_LINE_SEP = i14 -> {
            return i14 == 32 || i14 == 9 || i14 == 8232;
        };
        SPACE_TAB_NBSP_LINE_SEP = i15 -> {
            return i15 == 32 || i15 == 9 || i15 == 160 || i15 == 8232;
        };
        SPACE_EOL = i16 -> {
            return i16 == 32 || i16 == 10;
        };
        SPACE_ANY_EOL = i17 -> {
            return i17 == 32 || i17 == 13 || i17 == 10;
        };
        SPACE_TAB_NBSP = i18 -> {
            return i18 == 32 || i18 == 9 || i18 == 160;
        };
        SPACE_TAB_EOL = i19 -> {
            return i19 == 32 || i19 == 9 || i19 == 10;
        };
        SPACE_TAB_NBSP_EOL = i20 -> {
            return i20 == 32 || i20 == 9 || i20 == 10 || i20 == 160;
        };
        WHITESPACE = i21 -> {
            return i21 == 32 || i21 == 9 || i21 == 10 || i21 == 13;
        };
        WHITESPACE_OR_NUL = i22 -> {
            return i22 == 32 || i22 == 9 || i22 == 10 || i22 == 13 || i22 == 0;
        };
        WHITESPACE_NBSP = i23 -> {
            return i23 == 32 || i23 == 9 || i23 == 10 || i23 == 13 || i23 == 160;
        };
        WHITESPACE_NBSP_OR_NUL = i24 -> {
            return i24 == 32 || i24 == 9 || i24 == 10 || i24 == 13 || i24 == 160 || i24 == 0;
        };
        BLANKSPACE = i25 -> {
            return i25 == 32 || i25 == 9 || i25 == 10 || i25 == 13 || i25 == 11 || i25 == 12;
        };
        HEXADECIMAL_DIGITS = i26 -> {
            if (i26 >= 48 && i26 <= 57) {
                return true;
            }
            if (i26 < 97 || i26 > 102) {
                return i26 >= 65 && i26 <= 70;
            }
            return true;
        };
        DECIMAL_DIGITS = i27 -> {
            return i27 >= 48 && i27 <= 57;
        };
        OCTAL_DIGITS = i28 -> {
            return i28 >= 48 && i28 <= 55;
        };
        BINARY_DIGITS = i29 -> {
            return i29 >= 48 && i29 <= 49;
        };
        FALSE = NONE;
        TRUE = ALL;
        SPACE_TAB_OR_NUL = SPACE_TAB_NUL;
    }

    default boolean test(char c) {
        return test((int) c);
    }

    default CharPredicate and(CharPredicate charPredicate) {
        Objects.requireNonNull(charPredicate);
        return (this == NONE || charPredicate == NONE) ? NONE : this == ALL ? charPredicate : charPredicate == ALL ? this : i -> {
            return test(i) && charPredicate.test(i);
        };
    }

    @Override // java.util.function.IntPredicate
    default CharPredicate negate() {
        return this == NONE ? ALL : this == ALL ? NONE : i -> {
            return !test(i);
        };
    }

    default CharPredicate or(CharPredicate charPredicate) {
        Objects.requireNonNull(charPredicate);
        return (this == ALL || charPredicate == ALL) ? ALL : this == NONE ? charPredicate : charPredicate == NONE ? this : i -> {
            return test(i) || charPredicate.test(i);
        };
    }

    static CharPredicate standardOrAnyOf(char c) {
        return SPACE.test(c) ? SPACE : EOL.test(c) ? EOL : TAB.test(c) ? TAB : i -> {
            return i == c;
        };
    }

    static CharPredicate standardOrAnyOf(char c, char c2) {
        return c == c2 ? standardOrAnyOf(c) : (SPACE_TAB.test(c) && SPACE_TAB.test(c2)) ? SPACE_TAB : (ANY_EOL.test(c) && ANY_EOL.test(c2)) ? ANY_EOL : i -> {
            return i == c || i == c2;
        };
    }

    static CharPredicate standardOrAnyOf(char c, char c2, char c3) {
        if (c == c2 && c2 == c3) {
            return standardOrAnyOf(c);
        }
        if (c == c2 || c == c3) {
            return standardOrAnyOf(c2, c3);
        }
        return c2 == c3 ? standardOrAnyOf(c, c3) : i -> {
            return i == c || i == c2 || i == c3;
        };
    }

    static CharPredicate standardOrAnyOf(char c, char c2, char c3, char c4) {
        if (c == c2 && c2 == c3 && c3 == c4) {
            return standardOrAnyOf(c);
        }
        if (c == c2 || c == c3 || c == c4) {
            return standardOrAnyOf(c2, c3, c4);
        }
        if (c2 == c3 || c2 == c4) {
            return standardOrAnyOf(c, c3, c4);
        }
        if (c3 == c4) {
            return standardOrAnyOf(c, c2, c3);
        }
        return (WHITESPACE.test(c) && WHITESPACE.test(c2) && WHITESPACE.test(c3) && WHITESPACE.test(c4)) ? WHITESPACE : i -> {
            return i == c || i == c2 || i == c3 || i == c4;
        };
    }

    static CharPredicate anyOf(char... cArr) {
        switch (cArr.length) {
            case 0:
                return NONE;
            case 1:
                return standardOrAnyOf(cArr[0]);
            case 2:
                return standardOrAnyOf(cArr[0], cArr[1]);
            case 3:
                return standardOrAnyOf(cArr[0], cArr[1], cArr[2]);
            case 4:
                return standardOrAnyOf(cArr[0], cArr[1], cArr[2], cArr[3]);
            default:
                return anyOf(String.valueOf(cArr));
        }
    }

    static int indexOf(CharSequence charSequence, char c) {
        return indexOf(charSequence, c, 0, charSequence.length());
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

    static CharPredicate anyOf(CharSequence charSequence) {
        CharPredicate charPredicate;
        CharPredicate charPredicate2;
        switch (charSequence.length()) {
            case 0:
                return NONE;
            case 1:
                return standardOrAnyOf(charSequence.charAt(0));
            case 2:
                return standardOrAnyOf(charSequence.charAt(0), charSequence.charAt(1));
            case 3:
                return standardOrAnyOf(charSequence.charAt(0), charSequence.charAt(1), charSequence.charAt(2));
            case 4:
                return standardOrAnyOf(charSequence.charAt(0), charSequence.charAt(1), charSequence.charAt(2), charSequence.charAt(3));
            default:
                BitSet bitSet = null;
                StringBuilder sb = null;
                int length = charSequence.length();
                for (int i = 0; i < length; i++) {
                    char charAt = charSequence.charAt(i);
                    if (charAt <= 127) {
                        if (bitSet == null) {
                            bitSet = new BitSet();
                        }
                        bitSet.set(charAt);
                    } else {
                        if (sb == null) {
                            sb = new StringBuilder();
                        }
                        if (indexOf(sb, charAt) == -1) {
                            sb.append(charAt);
                        }
                    }
                }
                String sb2 = sb == null ? null : sb.toString();
                String str = sb2;
                if (sb2 == null || str.isEmpty()) {
                    charPredicate = null;
                } else {
                    charPredicate = str.length() <= 4 ? anyOf(sb) : i2 -> {
                        return indexOf(str, (char) i2) != -1;
                    };
                }
                CharPredicate charPredicate3 = charPredicate;
                if (bitSet == null || bitSet.cardinality() == 0) {
                    charPredicate2 = null;
                } else {
                    BitSet bitSet2 = bitSet;
                    bitSet2.getClass();
                    charPredicate2 = bitSet2::get;
                }
                CharPredicate charPredicate4 = charPredicate2;
                if (!AnonymousClass1.$assertionsDisabled && charPredicate4 == null && charPredicate3 == null) {
                    throw new AssertionError();
                }
                if (charPredicate4 != null && charPredicate3 != null) {
                    return charPredicate4.or(charPredicate3);
                }
                if (charPredicate4 != null) {
                    return charPredicate4;
                }
                return charPredicate3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.vladsch.flexmark.util.misc.CharPredicate$1, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/CharPredicate$1.class */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !CharPredicate.class.desiredAssertionStatus();
        }
    }
}
