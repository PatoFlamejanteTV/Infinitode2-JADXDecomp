package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;
import org.lwjgl.system.windows.RECT;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GPU_DEVICE.class */
public class GPU_DEVICE extends Struct<GPU_DEVICE> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int CB;
    public static final int DEVICENAME;
    public static final int DEVICESTRING;
    public static final int FLAGS;
    public static final int RCVIRTUALSCREEN;

    static {
        Struct.Layout __struct = __struct(__member(4), __array(1, 32), __array(1, 128), __member(4), __member(RECT.SIZEOF, RECT.ALIGNOF));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        CB = __struct.offsetof(0);
        DEVICENAME = __struct.offsetof(1);
        DEVICESTRING = __struct.offsetof(2);
        FLAGS = __struct.offsetof(3);
        RCVIRTUALSCREEN = __struct.offsetof(4);
    }

    protected GPU_DEVICE(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public GPU_DEVICE create(long j, ByteBuffer byteBuffer) {
        return new GPU_DEVICE(j, byteBuffer);
    }

    public GPU_DEVICE(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("DWORD")
    public int cb() {
        return ncb(address());
    }

    @NativeType("CHAR[32]")
    public ByteBuffer DeviceName() {
        return nDeviceName(address());
    }

    @NativeType("CHAR[32]")
    public String DeviceNameString() {
        return nDeviceNameString(address());
    }

    @NativeType("CHAR[128]")
    public ByteBuffer DeviceString() {
        return nDeviceString(address());
    }

    @NativeType("CHAR[128]")
    public String DeviceStringString() {
        return nDeviceStringString(address());
    }

    @NativeType("DWORD")
    public int Flags() {
        return nFlags(address());
    }

    public RECT rcVirtualScreen() {
        return nrcVirtualScreen(address());
    }

    public static GPU_DEVICE malloc() {
        return new GPU_DEVICE(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static GPU_DEVICE calloc() {
        return new GPU_DEVICE(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static GPU_DEVICE create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new GPU_DEVICE(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static GPU_DEVICE create(long j) {
        return new GPU_DEVICE(j, null);
    }

    public static GPU_DEVICE createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new GPU_DEVICE(j, null);
    }

    public static Buffer malloc(int i) {
        return new Buffer(MemoryUtil.nmemAllocChecked(__checkMalloc(i, SIZEOF)), i);
    }

    public static Buffer calloc(int i) {
        return new Buffer(MemoryUtil.nmemCallocChecked(i, SIZEOF), i);
    }

    public static Buffer create(int i) {
        ByteBuffer __create = __create(i, SIZEOF);
        return new Buffer(MemoryUtil.memAddress(__create), __create, -1, 0, i, i);
    }

    public static Buffer create(long j, int i) {
        return new Buffer(j, i);
    }

    public static Buffer createSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return new Buffer(j, i);
    }

    @Deprecated
    public static GPU_DEVICE mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static GPU_DEVICE callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static GPU_DEVICE mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static GPU_DEVICE callocStack(MemoryStack memoryStack) {
        return calloc(memoryStack);
    }

    @Deprecated
    public static Buffer mallocStack(int i) {
        return malloc(i, MemoryStack.stackGet());
    }

    @Deprecated
    public static Buffer callocStack(int i) {
        return calloc(i, MemoryStack.stackGet());
    }

    @Deprecated
    public static Buffer mallocStack(int i, MemoryStack memoryStack) {
        return malloc(i, memoryStack);
    }

    @Deprecated
    public static Buffer callocStack(int i, MemoryStack memoryStack) {
        return calloc(i, memoryStack);
    }

    public static GPU_DEVICE malloc(MemoryStack memoryStack) {
        return new GPU_DEVICE(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static GPU_DEVICE calloc(MemoryStack memoryStack) {
        return new GPU_DEVICE(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int ncb(long j) {
        return UNSAFE.getInt((Object) null, j + CB);
    }

    public static ByteBuffer nDeviceName(long j) {
        return MemoryUtil.memByteBuffer(j + DEVICENAME, 32);
    }

    public static String nDeviceNameString(long j) {
        return MemoryUtil.memASCII(j + DEVICENAME);
    }

    public static ByteBuffer nDeviceString(long j) {
        return MemoryUtil.memByteBuffer(j + DEVICESTRING, 128);
    }

    public static String nDeviceStringString(long j) {
        return MemoryUtil.memASCII(j + DEVICESTRING);
    }

    public static int nFlags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    public static RECT nrcVirtualScreen(long j) {
        return RECT.create(j + RCVIRTUALSCREEN);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/opengl/GPU_DEVICE$Buffer.class */
    public static class Buffer extends StructBuffer<GPU_DEVICE, Buffer> implements NativeResource {
        private static final GPU_DEVICE ELEMENT_FACTORY = GPU_DEVICE.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / GPU_DEVICE.SIZEOF);
        }

        public Buffer(long j, int i) {
            super(j, null, -1, 0, i, i);
        }

        Buffer(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
            super(j, byteBuffer, i, i2, i3, i4);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.lwjgl.system.CustomBuffer
        public Buffer self() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.lwjgl.system.StructBuffer
        public GPU_DEVICE getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("DWORD")
        public int cb() {
            return GPU_DEVICE.ncb(address());
        }

        @NativeType("CHAR[32]")
        public ByteBuffer DeviceName() {
            return GPU_DEVICE.nDeviceName(address());
        }

        @NativeType("CHAR[32]")
        public String DeviceNameString() {
            return GPU_DEVICE.nDeviceNameString(address());
        }

        @NativeType("CHAR[128]")
        public ByteBuffer DeviceString() {
            return GPU_DEVICE.nDeviceString(address());
        }

        @NativeType("CHAR[128]")
        public String DeviceStringString() {
            return GPU_DEVICE.nDeviceStringString(address());
        }

        @NativeType("DWORD")
        public int Flags() {
            return GPU_DEVICE.nFlags(address());
        }

        public RECT rcVirtualScreen() {
            return GPU_DEVICE.nrcVirtualScreen(address());
        }
    }
}
