package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/CRYPTPROTECT_PROMPTSTRUCT.class */
public class CRYPTPROTECT_PROMPTSTRUCT extends Struct<CRYPTPROTECT_PROMPTSTRUCT> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int CBSIZE;
    public static final int DWPROMPTFLAGS;
    public static final int HWNDAPP;
    public static final int SZPROMPT;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        CBSIZE = __struct.offsetof(0);
        DWPROMPTFLAGS = __struct.offsetof(1);
        HWNDAPP = __struct.offsetof(2);
        SZPROMPT = __struct.offsetof(3);
    }

    protected CRYPTPROTECT_PROMPTSTRUCT(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public CRYPTPROTECT_PROMPTSTRUCT create(long j, ByteBuffer byteBuffer) {
        return new CRYPTPROTECT_PROMPTSTRUCT(j, byteBuffer);
    }

    public CRYPTPROTECT_PROMPTSTRUCT(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("DWORD")
    public int cbSize() {
        return ncbSize(address());
    }

    @NativeType("DWORD")
    public int dwPromptFlags() {
        return ndwPromptFlags(address());
    }

    @NativeType("HWND")
    public long hwndApp() {
        return nhwndApp(address());
    }

    @NativeType("LPCWSTR")
    public ByteBuffer szPrompt() {
        return nszPrompt(address());
    }

    @NativeType("LPCWSTR")
    public String szPromptString() {
        return nszPromptString(address());
    }

    public CRYPTPROTECT_PROMPTSTRUCT cbSize(@NativeType("DWORD") int i) {
        ncbSize(address(), i);
        return this;
    }

    public CRYPTPROTECT_PROMPTSTRUCT cbSize$Default() {
        return cbSize(SIZEOF);
    }

    public CRYPTPROTECT_PROMPTSTRUCT dwPromptFlags(@NativeType("DWORD") int i) {
        ndwPromptFlags(address(), i);
        return this;
    }

    public CRYPTPROTECT_PROMPTSTRUCT hwndApp(@NativeType("HWND") long j) {
        nhwndApp(address(), j);
        return this;
    }

    public CRYPTPROTECT_PROMPTSTRUCT szPrompt(@NativeType("LPCWSTR") ByteBuffer byteBuffer) {
        nszPrompt(address(), byteBuffer);
        return this;
    }

    public CRYPTPROTECT_PROMPTSTRUCT set(int i, int i2, long j, ByteBuffer byteBuffer) {
        cbSize(i);
        dwPromptFlags(i2);
        hwndApp(j);
        szPrompt(byteBuffer);
        return this;
    }

    public CRYPTPROTECT_PROMPTSTRUCT set(CRYPTPROTECT_PROMPTSTRUCT cryptprotect_promptstruct) {
        MemoryUtil.memCopy(cryptprotect_promptstruct.address(), address(), SIZEOF);
        return this;
    }

    public static CRYPTPROTECT_PROMPTSTRUCT malloc() {
        return new CRYPTPROTECT_PROMPTSTRUCT(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static CRYPTPROTECT_PROMPTSTRUCT calloc() {
        return new CRYPTPROTECT_PROMPTSTRUCT(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static CRYPTPROTECT_PROMPTSTRUCT create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new CRYPTPROTECT_PROMPTSTRUCT(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static CRYPTPROTECT_PROMPTSTRUCT create(long j) {
        return new CRYPTPROTECT_PROMPTSTRUCT(j, null);
    }

    public static CRYPTPROTECT_PROMPTSTRUCT createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new CRYPTPROTECT_PROMPTSTRUCT(j, null);
    }

    public static CRYPTPROTECT_PROMPTSTRUCT malloc(MemoryStack memoryStack) {
        return new CRYPTPROTECT_PROMPTSTRUCT(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static CRYPTPROTECT_PROMPTSTRUCT calloc(MemoryStack memoryStack) {
        return new CRYPTPROTECT_PROMPTSTRUCT(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static int ncbSize(long j) {
        return UNSAFE.getInt((Object) null, j + CBSIZE);
    }

    public static int ndwPromptFlags(long j) {
        return UNSAFE.getInt((Object) null, j + DWPROMPTFLAGS);
    }

    public static long nhwndApp(long j) {
        return MemoryUtil.memGetAddress(j + HWNDAPP);
    }

    public static ByteBuffer nszPrompt(long j) {
        return MemoryUtil.memByteBufferNT2(MemoryUtil.memGetAddress(j + SZPROMPT));
    }

    public static String nszPromptString(long j) {
        return MemoryUtil.memUTF16(MemoryUtil.memGetAddress(j + SZPROMPT));
    }

    public static void ncbSize(long j, int i) {
        UNSAFE.putInt((Object) null, j + CBSIZE, i);
    }

    public static void ndwPromptFlags(long j, int i) {
        UNSAFE.putInt((Object) null, j + DWPROMPTFLAGS, i);
    }

    public static void nhwndApp(long j, long j2) {
        MemoryUtil.memPutAddress(j + HWNDAPP, Checks.check(j2));
    }

    public static void nszPrompt(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT2(byteBuffer);
        }
        MemoryUtil.memPutAddress(j + SZPROMPT, MemoryUtil.memAddress(byteBuffer));
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + HWNDAPP));
        Checks.check(MemoryUtil.memGetAddress(j + SZPROMPT));
    }
}
