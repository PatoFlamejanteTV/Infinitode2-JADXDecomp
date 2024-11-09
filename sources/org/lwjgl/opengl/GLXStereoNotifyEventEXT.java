package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXStereoNotifyEventEXT.class */
public class GLXStereoNotifyEventEXT extends Struct<GLXStereoNotifyEventEXT> {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int EXTENSION;
    public static final int EVTYPE;
    public static final int WINDOW;
    public static final int STEREO_TREE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(4), __member(4), __member(POINTER_SIZE), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        EXTENSION = __struct.offsetof(4);
        EVTYPE = __struct.offsetof(5);
        WINDOW = __struct.offsetof(6);
        STEREO_TREE = __struct.offsetof(7);
    }

    protected GLXStereoNotifyEventEXT(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public GLXStereoNotifyEventEXT create(long j, ByteBuffer byteBuffer) {
        return new GLXStereoNotifyEventEXT(j, byteBuffer);
    }

    public GLXStereoNotifyEventEXT(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public int type() {
        return ntype(address());
    }

    @NativeType("unsigned long")
    public long serial() {
        return nserial(address());
    }

    @NativeType("Bool")
    public boolean send_event() {
        return nsend_event(address()) != 0;
    }

    @NativeType("Display *")
    public long display() {
        return ndisplay(address());
    }

    public int extension() {
        return nextension(address());
    }

    public int evtype() {
        return nevtype(address());
    }

    @NativeType("GLXDrawable")
    public long window() {
        return nwindow(address());
    }

    @NativeType("Bool")
    public boolean stereo_tree() {
        return nstereo_tree(address()) != 0;
    }

    public static GLXStereoNotifyEventEXT create(long j) {
        return new GLXStereoNotifyEventEXT(j, null);
    }

    public static GLXStereoNotifyEventEXT createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new GLXStereoNotifyEventEXT(j, null);
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

    public static int ntype(long j) {
        return UNSAFE.getInt((Object) null, j + TYPE);
    }

    public static long nserial(long j) {
        return MemoryUtil.memGetCLong(j + SERIAL);
    }

    public static int nsend_event(long j) {
        return UNSAFE.getInt((Object) null, j + SEND_EVENT);
    }

    public static long ndisplay(long j) {
        return MemoryUtil.memGetAddress(j + DISPLAY);
    }

    public static int nextension(long j) {
        return UNSAFE.getInt((Object) null, j + EXTENSION);
    }

    public static int nevtype(long j) {
        return UNSAFE.getInt((Object) null, j + EVTYPE);
    }

    public static long nwindow(long j) {
        return MemoryUtil.memGetAddress(j + WINDOW);
    }

    public static int nstereo_tree(long j) {
        return UNSAFE.getInt((Object) null, j + STEREO_TREE);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXStereoNotifyEventEXT$Buffer.class */
    public static class Buffer extends StructBuffer<GLXStereoNotifyEventEXT, Buffer> {
        private static final GLXStereoNotifyEventEXT ELEMENT_FACTORY = GLXStereoNotifyEventEXT.create(-1);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / GLXStereoNotifyEventEXT.SIZEOF);
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
        public GLXStereoNotifyEventEXT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return GLXStereoNotifyEventEXT.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return GLXStereoNotifyEventEXT.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return GLXStereoNotifyEventEXT.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return GLXStereoNotifyEventEXT.ndisplay(address());
        }

        public int extension() {
            return GLXStereoNotifyEventEXT.nextension(address());
        }

        public int evtype() {
            return GLXStereoNotifyEventEXT.nevtype(address());
        }

        @NativeType("GLXDrawable")
        public long window() {
            return GLXStereoNotifyEventEXT.nwindow(address());
        }

        @NativeType("Bool")
        public boolean stereo_tree() {
            return GLXStereoNotifyEventEXT.nstereo_tree(address()) != 0;
        }
    }
}
