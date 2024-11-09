package nonapi.io.github.classgraph.utils;

import java.util.Iterator;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/StringUtils.class */
public final class StringUtils {
    private StringUtils() {
    }

    public static String readString(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        int i3;
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException("offset or numBytes out of range");
        }
        char[] cArr = new char[i2];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2 && (i3 = bArr[i + i4] & 255) <= 127) {
            int i6 = i5;
            i5++;
            cArr[i6] = (char) ((z && i3 == 47) ? 46 : i3);
            i4++;
        }
        while (i4 < i2) {
            int i7 = bArr[i + i4] & 255;
            switch (i7 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    i4++;
                    int i8 = i5;
                    i5++;
                    cArr[i8] = (char) ((z && i7 == 47) ? 46 : i7);
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    throw new IllegalArgumentException("Bad modified UTF8");
                case 12:
                case 13:
                    i4 += 2;
                    if (i4 > i2) {
                        throw new IllegalArgumentException("Bad modified UTF8");
                    }
                    byte b2 = bArr[(i + i4) - 1];
                    if ((b2 & 192) == 128) {
                        int i9 = ((i7 & 31) << 6) | (b2 & 63);
                        int i10 = i5;
                        i5++;
                        cArr[i10] = (char) ((z && i9 == 47) ? 46 : i9);
                        break;
                    } else {
                        throw new IllegalArgumentException("Bad modified UTF8");
                    }
                case 14:
                    i4 += 3;
                    if (i4 > i2) {
                        throw new IllegalArgumentException("Bad modified UTF8");
                    }
                    byte b3 = bArr[(i + i4) - 2];
                    byte b4 = bArr[(i + i4) - 1];
                    if ((b3 & 192) != 128 || (b4 & 192) != 128) {
                        throw new IllegalArgumentException("Bad modified UTF8");
                    }
                    int i11 = ((i7 & 15) << 12) | ((b3 & 63) << 6) | (b4 & 63);
                    int i12 = i5;
                    i5++;
                    cArr[i12] = (char) ((z && i11 == 47) ? 46 : i11);
                    break;
            }
        }
        if (i5 == i2 && !z2) {
            return new String(cArr);
        }
        if (z2) {
            if (i5 < 2 || cArr[0] != 'L' || cArr[i5 - 1] != ';') {
                throw new IllegalArgumentException("Expected string to start with 'L' and end with ';', got \"" + new String(cArr) + "\"");
            }
            return new String(cArr, 1, i5 - 2);
        }
        return new String(cArr, 0, i5);
    }

    public static void join(StringBuilder sb, String str, String str2, String str3, Iterable<?> iterable) {
        if (!str.isEmpty()) {
            sb.append(str);
        }
        boolean z = true;
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (z) {
                z = false;
            } else {
                sb.append(str2);
            }
            sb.append(next == null ? "null" : next.toString());
        }
        if (!str3.isEmpty()) {
            sb.append(str3);
        }
    }

    public static String join(String str, Iterable<?> iterable) {
        StringBuilder sb = new StringBuilder();
        join(sb, "", str, "", iterable);
        return sb.toString();
    }

    public static String join(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Object obj : objArr) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }
}
