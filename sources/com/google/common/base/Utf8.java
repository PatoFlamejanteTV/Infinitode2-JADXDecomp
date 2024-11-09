package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/Utf8.class */
public final class Utf8 {
    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i = length;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        while (true) {
            if (i2 < length) {
                char charAt = charSequence.charAt(i2);
                if (charAt < 2048) {
                    i += (127 - charAt) >>> 31;
                    i2++;
                } else {
                    i += encodedLengthGeneral(charSequence, i2);
                    break;
                }
            } else {
                break;
            }
        }
        if (i < length) {
            throw new IllegalArgumentException(new StringBuilder(54).append("UTF-8 length does not fit in int: ").append(i + 4294967296L).toString());
        }
        return i;
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        int i3 = i;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i3) == charAt) {
                        throw new IllegalArgumentException(unpairedSurrogateMsg(i3));
                    }
                    i3++;
                }
            }
            i3++;
        }
        return i2;
    }

    public static boolean isWellFormed(byte[] bArr) {
        return isWellFormed(bArr, 0, bArr.length);
    }

    public static boolean isWellFormed(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        Preconditions.checkPositionIndexes(i, i3, bArr.length);
        for (int i4 = i; i4 < i3; i4++) {
            if (bArr[i4] < 0) {
                return isWellFormedSlowPath(bArr, i4, i3);
            }
        }
        return true;
    }

    private static boolean isWellFormedSlowPath(byte[] bArr, int i, int i2) {
        int i3 = i;
        while (i3 < i2) {
            int i4 = i3;
            i3++;
            byte b2 = bArr[i4];
            if (b2 < 0) {
                if (b2 < -32) {
                    if (i3 == i2 || b2 < -62) {
                        return false;
                    }
                    i3++;
                    if (bArr[i3] > -65) {
                        return false;
                    }
                } else if (b2 < -16) {
                    if (i3 + 1 >= i2) {
                        return false;
                    }
                    int i5 = i3 + 1;
                    byte b3 = bArr[i3];
                    if (b3 <= -65) {
                        if (b2 != -32 || b3 >= -96) {
                            if (b2 == -19 && -96 <= b3) {
                                return false;
                            }
                            i3 = i5 + 1;
                            if (bArr[i5] > -65) {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    if (i3 + 2 >= i2) {
                        return false;
                    }
                    int i6 = i3 + 1;
                    byte b4 = bArr[i3];
                    if (b4 > -65 || (((b2 << 28) + (b4 - (-112))) >> 30) != 0) {
                        return false;
                    }
                    int i7 = i6 + 1;
                    if (bArr[i6] > -65) {
                        return false;
                    }
                    i3 = i7 + 1;
                    if (bArr[i7] > -65) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static String unpairedSurrogateMsg(int i) {
        return new StringBuilder(39).append("Unpaired surrogate at index ").append(i).toString();
    }

    private Utf8() {
    }
}
