package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XTimeCoord.class */
public class XTimeCoord extends Struct<XTimeCoord> {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TIME;
    public static final int X;
    public static final int Y;

    static {
        Struct.Layout __struct = __struct(__member(CLONG_SIZE), __member(2), __member(2));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TIME = __struct.offsetof(0);
        X = __struct.offsetof(1);
        Y = __struct.offsetof(2);
    }

    protected XTimeCoord(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XTimeCoord create(long j, ByteBuffer byteBuffer) {
        return new XTimeCoord(j, byteBuffer);
    }

    public XTimeCoord(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("Time")
    public long time() {
        return ntime(address());
    }

    public short x() {
        return nx(address());
    }

    public short y() {
        return ny(address());
    }

    public static XTimeCoord create(long j) {
        return new XTimeCoord(j, null);
    }

    public static XTimeCoord createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XTimeCoord(j, null);
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

    public static long ntime(long j) {
        return MemoryUtil.memGetCLong(j + TIME);
    }

    public static short nx(long j) {
        return UNSAFE.getShort((Object) null, j + X);
    }

    public static short ny(long j) {
        return UNSAFE.getShort((Object) null, j + Y);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XTimeCoord$Buffer.class */
    public static class Buffer extends StructBuffer<XTimeCoord, Buffer> {
        private static final XTimeCoord ELEMENT_FACTORY = XTimeCoord.create(-1);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XTimeCoord.SIZEOF);
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
        public XTimeCoord getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("Time")
        public long time() {
            return XTimeCoord.ntime(address());
        }

        public short x() {
            return XTimeCoord.nx(address());
        }

        public short y() {
            return XTimeCoord.ny(address());
        }
    }
}
