package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/DISPLAY_DEVICE.class */
public class DISPLAY_DEVICE extends Struct<DISPLAY_DEVICE> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int CB;
    public static final int DEVICENAME;
    public static final int DEVICESTRING;
    public static final int STATEFLAGS;
    public static final int DEVICEID;
    public static final int DEVICEKEY;

    static {
        Struct.Layout __struct = __struct(__member(4), __array(2, 32), __array(2, 128), __member(4), __array(2, 128), __array(2, 128));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        CB = __struct.offsetof(0);
        DEVICENAME = __struct.offsetof(1);
        DEVICESTRING = __struct.offsetof(2);
        STATEFLAGS = __struct.offsetof(3);
        DEVICEID = __struct.offsetof(4);
        DEVICEKEY = __struct.offsetof(5);
    }

    protected DISPLAY_DEVICE(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public DISPLAY_DEVICE create(long j, ByteBuffer byteBuffer) {
        return new DISPLAY_DEVICE(j, byteBuffer);
    }

    public DISPLAY_DEVICE(ByteBuffer byteBuffer) {
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

    @NativeType("TCHAR[32]")
    public ByteBuffer DeviceName() {
        return nDeviceName(address());
    }

    @NativeType("TCHAR[32]")
    public String DeviceNameString() {
        return nDeviceNameString(address());
    }

    @NativeType("TCHAR[128]")
    public ByteBuffer DeviceString() {
        return nDeviceString(address());
    }

    @NativeType("TCHAR[128]")
    public String DeviceStringString() {
        return nDeviceStringString(address());
    }

    @NativeType("DWORD")
    public int StateFlags() {
        return nStateFlags(address());
    }

    @NativeType("TCHAR[128]")
    public ByteBuffer DeviceID() {
        return nDeviceID(address());
    }

    @NativeType("TCHAR[128]")
    public String DeviceIDString() {
        return nDeviceIDString(address());
    }

    @NativeType("TCHAR[128]")
    public ByteBuffer DeviceKey() {
        return nDeviceKey(address());
    }

    @NativeType("TCHAR[128]")
    public String DeviceKeyString() {
        return nDeviceKeyString(address());
    }

    public DISPLAY_DEVICE cb(@NativeType("DWORD") int i) {
        ncb(address(), i);
        return this;
    }

    public DISPLAY_DEVICE set(DISPLAY_DEVICE display_device) {
        MemoryUtil.memCopy(display_device.address(), address(), SIZEOF);
        return this;
    }

    public static DISPLAY_DEVICE malloc() {
        return new DISPLAY_DEVICE(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static DISPLAY_DEVICE calloc() {
        return new DISPLAY_DEVICE(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static DISPLAY_DEVICE create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new DISPLAY_DEVICE(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static DISPLAY_DEVICE create(long j) {
        return new DISPLAY_DEVICE(j, null);
    }

    public static DISPLAY_DEVICE createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new DISPLAY_DEVICE(j, null);
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
    public static DISPLAY_DEVICE mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static DISPLAY_DEVICE callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static DISPLAY_DEVICE mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static DISPLAY_DEVICE callocStack(MemoryStack memoryStack) {
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

    public static DISPLAY_DEVICE malloc(MemoryStack memoryStack) {
        return new DISPLAY_DEVICE(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static DISPLAY_DEVICE calloc(MemoryStack memoryStack) {
        return new DISPLAY_DEVICE(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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
        return MemoryUtil.memByteBuffer(j + DEVICENAME, 64);
    }

    public static String nDeviceNameString(long j) {
        return MemoryUtil.memUTF16(j + DEVICENAME);
    }

    public static ByteBuffer nDeviceString(long j) {
        return MemoryUtil.memByteBuffer(j + DEVICESTRING, 256);
    }

    public static String nDeviceStringString(long j) {
        return MemoryUtil.memUTF16(j + DEVICESTRING);
    }

    public static int nStateFlags(long j) {
        return UNSAFE.getInt((Object) null, j + STATEFLAGS);
    }

    public static ByteBuffer nDeviceID(long j) {
        return MemoryUtil.memByteBuffer(j + DEVICEID, 256);
    }

    public static String nDeviceIDString(long j) {
        return MemoryUtil.memUTF16(j + DEVICEID);
    }

    public static ByteBuffer nDeviceKey(long j) {
        return MemoryUtil.memByteBuffer(j + DEVICEKEY, 256);
    }

    public static String nDeviceKeyString(long j) {
        return MemoryUtil.memUTF16(j + DEVICEKEY);
    }

    public static void ncb(long j, int i) {
        UNSAFE.putInt((Object) null, j + CB, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/DISPLAY_DEVICE$Buffer.class */
    public static class Buffer extends StructBuffer<DISPLAY_DEVICE, Buffer> implements NativeResource {
        private static final DISPLAY_DEVICE ELEMENT_FACTORY = DISPLAY_DEVICE.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / DISPLAY_DEVICE.SIZEOF);
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
        public DISPLAY_DEVICE getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("DWORD")
        public int cb() {
            return DISPLAY_DEVICE.ncb(address());
        }

        @NativeType("TCHAR[32]")
        public ByteBuffer DeviceName() {
            return DISPLAY_DEVICE.nDeviceName(address());
        }

        @NativeType("TCHAR[32]")
        public String DeviceNameString() {
            return DISPLAY_DEVICE.nDeviceNameString(address());
        }

        @NativeType("TCHAR[128]")
        public ByteBuffer DeviceString() {
            return DISPLAY_DEVICE.nDeviceString(address());
        }

        @NativeType("TCHAR[128]")
        public String DeviceStringString() {
            return DISPLAY_DEVICE.nDeviceStringString(address());
        }

        @NativeType("DWORD")
        public int StateFlags() {
            return DISPLAY_DEVICE.nStateFlags(address());
        }

        @NativeType("TCHAR[128]")
        public ByteBuffer DeviceID() {
            return DISPLAY_DEVICE.nDeviceID(address());
        }

        @NativeType("TCHAR[128]")
        public String DeviceIDString() {
            return DISPLAY_DEVICE.nDeviceIDString(address());
        }

        @NativeType("TCHAR[128]")
        public ByteBuffer DeviceKey() {
            return DISPLAY_DEVICE.nDeviceKey(address());
        }

        @NativeType("TCHAR[128]")
        public String DeviceKeyString() {
            return DISPLAY_DEVICE.nDeviceKeyString(address());
        }

        public Buffer cb(@NativeType("DWORD") int i) {
            DISPLAY_DEVICE.ncb(address(), i);
            return this;
        }
    }
}
