package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct epoll_event")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/EpollEvent.class */
public class EpollEvent extends Struct<EpollEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int EVENTS;
    public static final int DATA;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(EpollData.SIZEOF, EpollData.ALIGNOF));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        EVENTS = __struct.offsetof(0);
        DATA = __struct.offsetof(1);
    }

    protected EpollEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public EpollEvent create(long j, ByteBuffer byteBuffer) {
        return new EpollEvent(j, byteBuffer);
    }

    public EpollEvent(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("uint32_t")
    public int events() {
        return nevents(address());
    }

    @NativeType("epoll_data_t")
    public EpollData data() {
        return ndata(address());
    }

    public EpollEvent events(@NativeType("uint32_t") int i) {
        nevents(address(), i);
        return this;
    }

    public EpollEvent data(@NativeType("epoll_data_t") EpollData epollData) {
        ndata(address(), epollData);
        return this;
    }

    public EpollEvent data(Consumer<EpollData> consumer) {
        consumer.accept(data());
        return this;
    }

    public EpollEvent set(int i, EpollData epollData) {
        events(i);
        data(epollData);
        return this;
    }

    public EpollEvent set(EpollEvent epollEvent) {
        MemoryUtil.memCopy(epollEvent.address(), address(), SIZEOF);
        return this;
    }

    public static EpollEvent malloc() {
        return new EpollEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static EpollEvent calloc() {
        return new EpollEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static EpollEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new EpollEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static EpollEvent create(long j) {
        return new EpollEvent(j, null);
    }

    public static EpollEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new EpollEvent(j, null);
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

    public static EpollEvent malloc(MemoryStack memoryStack) {
        return new EpollEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static EpollEvent calloc(MemoryStack memoryStack) {
        return new EpollEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nevents(long j) {
        return UNSAFE.getInt((Object) null, j + EVENTS);
    }

    public static EpollData ndata(long j) {
        return EpollData.create(j + DATA);
    }

    public static void nevents(long j, int i) {
        UNSAFE.putInt((Object) null, j + EVENTS, i);
    }

    public static void ndata(long j, EpollData epollData) {
        MemoryUtil.memCopy(epollData.address(), j + DATA, EpollData.SIZEOF);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/EpollEvent$Buffer.class */
    public static class Buffer extends StructBuffer<EpollEvent, Buffer> implements NativeResource {
        private static final EpollEvent ELEMENT_FACTORY = EpollEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / EpollEvent.SIZEOF);
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
        public EpollEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("uint32_t")
        public int events() {
            return EpollEvent.nevents(address());
        }

        @NativeType("epoll_data_t")
        public EpollData data() {
            return EpollEvent.ndata(address());
        }

        public Buffer events(@NativeType("uint32_t") int i) {
            EpollEvent.nevents(address(), i);
            return this;
        }

        public Buffer data(@NativeType("epoll_data_t") EpollData epollData) {
            EpollEvent.ndata(address(), epollData);
            return this;
        }

        public Buffer data(Consumer<EpollData> consumer) {
            consumer.accept(data());
            return this;
        }
    }
}
