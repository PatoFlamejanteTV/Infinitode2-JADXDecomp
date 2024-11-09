package org.lwjgl.stb;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBDXT.class */
public class STBDXT {
    public static final int STB_DXT_NORMAL = 0;
    public static final int STB_DXT_DITHER = 1;
    public static final int STB_DXT_HIGHQUAL = 2;

    public static native void nstb_compress_dxt_block(long j, long j2, int i, int i2);

    public static native void nstb_compress_bc4_block(long j, long j2);

    public static native void nstb_compress_bc5_block(long j, long j2);

    static {
        LibSTB.initialize();
    }

    protected STBDXT() {
        throw new UnsupportedOperationException();
    }

    public static void stb_compress_dxt_block(@NativeType("unsigned char *") ByteBuffer byteBuffer, @NativeType("unsigned char const *") ByteBuffer byteBuffer2, @NativeType("int") boolean z, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, z ? 16 : 8);
            Checks.check((Buffer) byteBuffer2, 64);
        }
        nstb_compress_dxt_block(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), z ? 1 : 0, i);
    }

    public static void stb_compress_bc4_block(@NativeType("unsigned char *") ByteBuffer byteBuffer, @NativeType("unsigned char const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 8);
            Checks.check((Buffer) byteBuffer2, 16);
        }
        nstb_compress_bc4_block(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2));
    }

    public static void stb_compress_bc5_block(@NativeType("unsigned char *") ByteBuffer byteBuffer, @NativeType("unsigned char const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 16);
            Checks.check((Buffer) byteBuffer2, 32);
        }
        nstb_compress_bc5_block(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2));
    }
}
