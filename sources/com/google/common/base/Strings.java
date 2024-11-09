package com.google.common.base;

import java.util.logging.Level;
import java.util.logging.Logger;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/Strings.class */
public final class Strings {
    private Strings() {
    }

    public static String nullToEmpty(String str) {
        return Platform.nullToEmpty(str);
    }

    public static String emptyToNull(String str) {
        return Platform.emptyToNull(str);
    }

    public static boolean isNullOrEmpty(String str) {
        return Platform.stringIsNullOrEmpty(str);
    }

    public static String padStart(String str, int i, char c) {
        Preconditions.checkNotNull(str);
        if (str.length() >= i) {
            return str;
        }
        StringBuilder sb = new StringBuilder(i);
        for (int length = str.length(); length < i; length++) {
            sb.append(c);
        }
        sb.append(str);
        return sb.toString();
    }

    public static String padEnd(String str, int i, char c) {
        Preconditions.checkNotNull(str);
        if (str.length() >= i) {
            return str;
        }
        StringBuilder sb = new StringBuilder(i);
        sb.append(str);
        for (int length = str.length(); length < i; length++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static String repeat(String str, int i) {
        Preconditions.checkNotNull(str);
        if (i <= 1) {
            Preconditions.checkArgument(i >= 0, "invalid count: %s", i);
            return i == 0 ? "" : str;
        }
        int length = str.length();
        long j = length * i;
        int i2 = (int) j;
        if (i2 != j) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(51).append("Required array size too large: ").append(j).toString());
        }
        char[] cArr = new char[i2];
        str.getChars(0, length, cArr, 0);
        int i3 = length;
        while (true) {
            int i4 = i3;
            if (i4 < i2 - i4) {
                System.arraycopy(cArr, 0, cArr, i4, i4);
                i3 = i4 << 1;
            } else {
                System.arraycopy(cArr, 0, cArr, i4, i2 - i4);
                return new String(cArr);
            }
        }
    }

    public static String commonPrefix(CharSequence charSequence, CharSequence charSequence2) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(charSequence2);
        int min = Math.min(charSequence.length(), charSequence2.length());
        int i = 0;
        while (i < min && charSequence.charAt(i) == charSequence2.charAt(i)) {
            i++;
        }
        if (validSurrogatePairAt(charSequence, i - 1) || validSurrogatePairAt(charSequence2, i - 1)) {
            i--;
        }
        return charSequence.subSequence(0, i).toString();
    }

    public static String commonSuffix(CharSequence charSequence, CharSequence charSequence2) {
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(charSequence2);
        int min = Math.min(charSequence.length(), charSequence2.length());
        int i = 0;
        while (i < min && charSequence.charAt((charSequence.length() - i) - 1) == charSequence2.charAt((charSequence2.length() - i) - 1)) {
            i++;
        }
        if (validSurrogatePairAt(charSequence, (charSequence.length() - i) - 1) || validSurrogatePairAt(charSequence2, (charSequence2.length() - i) - 1)) {
            i--;
        }
        return charSequence.subSequence(charSequence.length() - i, charSequence.length()).toString();
    }

    static boolean validSurrogatePairAt(CharSequence charSequence, int i) {
        return i >= 0 && i <= charSequence.length() - 2 && Character.isHighSurrogate(charSequence.charAt(i)) && Character.isLowSurrogate(charSequence.charAt(i + 1));
    }

    public static String lenientFormat(String str, Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        if (objArr == null) {
            objArr = new Object[]{"(Object[])null"};
        } else {
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = lenientToString(objArr[i]);
            }
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + (16 * objArr.length));
        int i2 = 0;
        int i3 = 0;
        while (i3 < objArr.length && (indexOf = valueOf.indexOf("%s", i2)) != -1) {
            sb.append((CharSequence) valueOf, i2, indexOf);
            int i4 = i3;
            i3++;
            sb.append(objArr[i4]);
            i2 = indexOf + 2;
        }
        sb.append((CharSequence) valueOf, i2, valueOf.length());
        if (i3 < objArr.length) {
            sb.append(" [");
            int i5 = i3;
            int i6 = i3 + 1;
            sb.append(objArr[i5]);
            while (i6 < objArr.length) {
                sb.append(", ");
                int i7 = i6;
                i6++;
                sb.append(objArr[i7]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    private static String lenientToString(Object obj) {
        String str;
        if (obj == null) {
            return "null";
        }
        try {
            return obj.toString();
        } catch (Exception e) {
            String name = obj.getClass().getName();
            String hexString = Integer.toHexString(System.identityHashCode(obj));
            String sb = new StringBuilder(1 + String.valueOf(name).length() + String.valueOf(hexString).length()).append(name).append('@').append(hexString).toString();
            Logger logger = Logger.getLogger("com.google.common.base.Strings");
            Level level = Level.WARNING;
            String valueOf = String.valueOf(sb);
            if (valueOf.length() != 0) {
                str = "Exception during lenientFormat for ".concat(valueOf);
            } else {
                str = r3;
                String str2 = new String("Exception during lenientFormat for ");
            }
            logger.log(level, str, (Throwable) e);
            String name2 = e.getClass().getName();
            return new StringBuilder(9 + String.valueOf(sb).length() + String.valueOf(name2).length()).append("<").append(sb).append(" threw ").append(name2).append(">").toString();
        }
    }
}
