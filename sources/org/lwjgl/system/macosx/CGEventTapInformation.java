package org.lwjgl.system.macosx;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/CGEventTapInformation.class */
public class CGEventTapInformation extends Struct<CGEventTapInformation> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int EVENTTAPID;
    public static final int TAPPOINT;
    public static final int OPTIONS;
    public static final int EVENTSOFINTEREST;
    public static final int TAPPINGPROCESS;
    public static final int PROCESSBEINGTAPPED;
    public static final int ENABLED;
    public static final int MINUSECLATENCY;
    public static final int AVGUSECLATENCY;
    public static final int MAXUSECLATENCY;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(8), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(1), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        EVENTTAPID = __struct.offsetof(0);
        TAPPOINT = __struct.offsetof(1);
        OPTIONS = __struct.offsetof(2);
        EVENTSOFINTEREST = __struct.offsetof(3);
        TAPPINGPROCESS = __struct.offsetof(4);
        PROCESSBEINGTAPPED = __struct.offsetof(5);
        ENABLED = __struct.offsetof(6);
        MINUSECLATENCY = __struct.offsetof(7);
        AVGUSECLATENCY = __struct.offsetof(8);
        MAXUSECLATENCY = __struct.offsetof(9);
    }

    protected CGEventTapInformation(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public CGEventTapInformation create(long j, ByteBuffer byteBuffer) {
        return new CGEventTapInformation(j, byteBuffer);
    }

    public CGEventTapInformation(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("uint32_t")
    public int eventTapID() {
        return neventTapID(address());
    }

    @NativeType("CGEventTapLocation")
    public int tapPoint() {
        return ntapPoint(address());
    }

    @NativeType("CGEventTapOptions")
    public int options() {
        return noptions(address());
    }

    @NativeType("CGEventMask")
    public long eventsOfInterest() {
        return neventsOfInterest(address());
    }

    @NativeType("pid_t")
    public long tappingProcess() {
        return ntappingProcess(address());
    }

    @NativeType("pid_t")
    public long processBeingTapped() {
        return nprocessBeingTapped(address());
    }

    @NativeType("bool")
    public boolean enabled() {
        return nenabled(address());
    }

    public float minUsecLatency() {
        return nminUsecLatency(address());
    }

    public float avgUsecLatency() {
        return navgUsecLatency(address());
    }

    public float maxUsecLatency() {
        return nmaxUsecLatency(address());
    }

    public static CGEventTapInformation malloc() {
        return new CGEventTapInformation(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static CGEventTapInformation calloc() {
        return new CGEventTapInformation(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static CGEventTapInformation create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new CGEventTapInformation(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static CGEventTapInformation create(long j) {
        return new CGEventTapInformation(j, null);
    }

    public static CGEventTapInformation createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new CGEventTapInformation(j, null);
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
    public static CGEventTapInformation mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static CGEventTapInformation callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static CGEventTapInformation mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static CGEventTapInformation callocStack(MemoryStack memoryStack) {
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

    public static CGEventTapInformation malloc(MemoryStack memoryStack) {
        return new CGEventTapInformation(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static CGEventTapInformation calloc(MemoryStack memoryStack) {
        return new CGEventTapInformation(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int neventTapID(long j) {
        return UNSAFE.getInt((Object) null, j + EVENTTAPID);
    }

    public static int ntapPoint(long j) {
        return UNSAFE.getInt((Object) null, j + TAPPOINT);
    }

    public static int noptions(long j) {
        return UNSAFE.getInt((Object) null, j + OPTIONS);
    }

    public static long neventsOfInterest(long j) {
        return UNSAFE.getLong((Object) null, j + EVENTSOFINTEREST);
    }

    public static long ntappingProcess(long j) {
        return MemoryUtil.memGetAddress(j + TAPPINGPROCESS);
    }

    public static long nprocessBeingTapped(long j) {
        return MemoryUtil.memGetAddress(j + PROCESSBEINGTAPPED);
    }

    public static boolean nenabled(long j) {
        return UNSAFE.getByte((Object) null, j + ((long) ENABLED)) != 0;
    }

    public static float nminUsecLatency(long j) {
        return UNSAFE.getFloat((Object) null, j + MINUSECLATENCY);
    }

    public static float navgUsecLatency(long j) {
        return UNSAFE.getFloat((Object) null, j + AVGUSECLATENCY);
    }

    public static float nmaxUsecLatency(long j) {
        return UNSAFE.getFloat((Object) null, j + MAXUSECLATENCY);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/CGEventTapInformation$Buffer.class */
    public static class Buffer extends StructBuffer<CGEventTapInformation, Buffer> implements NativeResource {
        private static final CGEventTapInformation ELEMENT_FACTORY = CGEventTapInformation.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / CGEventTapInformation.SIZEOF);
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
        public CGEventTapInformation getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("uint32_t")
        public int eventTapID() {
            return CGEventTapInformation.neventTapID(address());
        }

        @NativeType("CGEventTapLocation")
        public int tapPoint() {
            return CGEventTapInformation.ntapPoint(address());
        }

        @NativeType("CGEventTapOptions")
        public int options() {
            return CGEventTapInformation.noptions(address());
        }

        @NativeType("CGEventMask")
        public long eventsOfInterest() {
            return CGEventTapInformation.neventsOfInterest(address());
        }

        @NativeType("pid_t")
        public long tappingProcess() {
            return CGEventTapInformation.ntappingProcess(address());
        }

        @NativeType("pid_t")
        public long processBeingTapped() {
            return CGEventTapInformation.nprocessBeingTapped(address());
        }

        @NativeType("bool")
        public boolean enabled() {
            return CGEventTapInformation.nenabled(address());
        }

        public float minUsecLatency() {
            return CGEventTapInformation.nminUsecLatency(address());
        }

        public float avgUsecLatency() {
            return CGEventTapInformation.navgUsecLatency(address());
        }

        public float maxUsecLatency() {
            return CGEventTapInformation.nmaxUsecLatency(address());
        }
    }
}
