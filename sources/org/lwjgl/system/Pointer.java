package org.lwjgl.system;

import sun.misc.Unsafe;

/* loaded from: infinitode-2.jar:org/lwjgl/system/Pointer.class */
public interface Pointer {
    public static final int POINTER_SIZE;
    public static final int POINTER_SHIFT;
    public static final int CLONG_SIZE;
    public static final int CLONG_SHIFT;
    public static final boolean BITS32;
    public static final boolean BITS64;

    long address();

    static {
        int pointerSize = MemoryAccessJNI.getPointerSize();
        POINTER_SIZE = pointerSize;
        POINTER_SHIFT = pointerSize == 8 ? 3 : 2;
        int i = (POINTER_SIZE == 8 && Platform.get() == Platform.WINDOWS) ? 4 : POINTER_SIZE;
        CLONG_SIZE = i;
        CLONG_SHIFT = i == 8 ? 3 : 2;
        BITS32 = (POINTER_SIZE << 3) == 32;
        BITS64 = (POINTER_SIZE << 3) == 64;
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/Pointer$Default.class */
    public static abstract class Default implements Pointer {
        protected static final Unsafe UNSAFE = MemoryUtil.UNSAFE;
        protected static final long ADDRESS;
        protected static final long BUFFER_CONTAINER;
        protected static final long BUFFER_MARK;
        protected static final long BUFFER_POSITION;
        protected static final long BUFFER_LIMIT;
        protected static final long BUFFER_CAPACITY;
        protected long address;

        static {
            try {
                ADDRESS = UNSAFE.objectFieldOffset(Default.class.getDeclaredField("address"));
                BUFFER_CONTAINER = UNSAFE.objectFieldOffset(CustomBuffer.class.getDeclaredField("container"));
                BUFFER_MARK = UNSAFE.objectFieldOffset(CustomBuffer.class.getDeclaredField("mark"));
                BUFFER_POSITION = UNSAFE.objectFieldOffset(CustomBuffer.class.getDeclaredField("position"));
                BUFFER_LIMIT = UNSAFE.objectFieldOffset(CustomBuffer.class.getDeclaredField("limit"));
                BUFFER_CAPACITY = UNSAFE.objectFieldOffset(CustomBuffer.class.getDeclaredField("capacity"));
            } catch (Throwable th) {
                throw new UnsupportedOperationException(th);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Default(long j) {
            if (Checks.CHECKS && j == 0) {
                throw new NullPointerException();
            }
            this.address = j;
        }

        @Override // org.lwjgl.system.Pointer
        public long address() {
            return this.address;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Pointer) && this.address == ((Pointer) obj).address();
        }

        public int hashCode() {
            return (int) (this.address ^ (this.address >>> 32));
        }

        public String toString() {
            return String.format("%s pointer [0x%X]", getClass().getSimpleName(), Long.valueOf(this.address));
        }
    }
}
