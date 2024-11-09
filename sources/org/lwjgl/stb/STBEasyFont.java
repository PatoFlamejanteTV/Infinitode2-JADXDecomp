package org.lwjgl.stb;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBEasyFont.class */
public class STBEasyFont {
    public static native int nstb_easy_font_width(long j);

    public static native int nstb_easy_font_height(long j);

    public static native int nstb_easy_font_print(float f, float f2, long j, long j2, long j3, int i);

    public static native void stb_easy_font_spacing(float f);

    static {
        LibSTB.initialize();
    }

    protected STBEasyFont() {
        throw new UnsupportedOperationException();
    }

    public static int stb_easy_font_width(@NativeType("char *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nstb_easy_font_width(MemoryUtil.memAddress(byteBuffer));
    }

    public static int stb_easy_font_width(@NativeType("char *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nstb_easy_font_width(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int stb_easy_font_height(@NativeType("char *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nstb_easy_font_height(MemoryUtil.memAddress(byteBuffer));
    }

    public static int stb_easy_font_height(@NativeType("char *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nstb_easy_font_height(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int stb_easy_font_print(float f, float f2, @NativeType("char *") ByteBuffer byteBuffer, @NativeType("unsigned char *") ByteBuffer byteBuffer2, @NativeType("void *") ByteBuffer byteBuffer3) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkSafe((Buffer) byteBuffer2, 4);
        }
        return nstb_easy_font_print(f, f2, MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer2), MemoryUtil.memAddress(byteBuffer3), byteBuffer3.remaining());
    }

    public static int stb_easy_font_print(float f, float f2, @NativeType("char *") CharSequence charSequence, @NativeType("unsigned char *") ByteBuffer byteBuffer, @NativeType("void *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, 4);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nstb_easy_font_print(f, f2, stackGet.getPointerAddress(), MemoryUtil.memAddressSafe(byteBuffer), MemoryUtil.memAddress(byteBuffer2), byteBuffer2.remaining());
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
