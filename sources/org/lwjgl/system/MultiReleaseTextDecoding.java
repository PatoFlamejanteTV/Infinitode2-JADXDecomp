package org.lwjgl.system;

import java.nio.charset.StandardCharsets;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/lwjgl/system/MultiReleaseTextDecoding.class */
public final class MultiReleaseTextDecoding {
    private MultiReleaseTextDecoding() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decodeUTF8(long j, int i) {
        char c;
        if (i <= 0) {
            return "";
        }
        if (Checks.DEBUG) {
            byte[] bArr = i <= MemoryUtil.ARRAY_TLC_SIZE ? MemoryUtil.ARRAY_TLC_BYTE.get() : new byte[i];
            MemoryUtil.memByteBuffer(j, i).get(bArr, 0, i);
            return new String(bArr, 0, i, StandardCharsets.UTF_8);
        }
        char[] cArr = i <= MemoryUtil.ARRAY_TLC_SIZE ? MemoryUtil.ARRAY_TLC_CHAR.get() : new char[i];
        int i2 = 0;
        int i3 = 0;
        while (i3 < i) {
            int i4 = i3;
            i3++;
            int i5 = MemoryUtil.UNSAFE.getByte((Object) null, j + i4) & 255;
            if (i5 < 128) {
                c = (char) i5;
            } else {
                i3++;
                int i6 = MemoryUtil.UNSAFE.getByte((Object) null, j + i3) & 63;
                if ((i5 & CGL.kCGLCPDispatchTableSize) == 192) {
                    c = (char) (((i5 & 31) << 6) | i6);
                } else {
                    i3++;
                    int i7 = MemoryUtil.UNSAFE.getByte((Object) null, j + i3) & 63;
                    if ((i5 & User32.VK_OEM_ATTN) == 224) {
                        c = (char) (((i5 & 15) << 12) | (i6 << 6) | i7);
                    } else {
                        i3++;
                        int i8 = ((i5 & 7) << 18) | (i6 << 12) | (i7 << 6) | (MemoryUtil.UNSAFE.getByte((Object) null, j + i3) & 63);
                        if (i2 < i) {
                            int i9 = i2;
                            i2++;
                            cArr[i9] = (char) ((i8 >>> 10) + 55232);
                        }
                        c = (char) ((i8 & 1023) + 56320);
                    }
                }
            }
            if (i2 < i) {
                int i10 = i2;
                i2++;
                cArr[i10] = c;
            }
        }
        return new String(cArr, 0, Math.min(i2, i));
    }
}
