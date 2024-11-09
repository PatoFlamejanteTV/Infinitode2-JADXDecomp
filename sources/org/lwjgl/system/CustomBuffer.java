package org.lwjgl.system;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.Pointer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/CustomBuffer.class */
public abstract class CustomBuffer<SELF extends CustomBuffer<SELF>> extends Pointer.Default {
    protected ByteBuffer container;
    protected int mark;
    protected int position;
    protected int limit;
    protected int capacity;

    public abstract int sizeof();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract SELF self();

    /* JADX INFO: Access modifiers changed from: protected */
    public CustomBuffer(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        super(j);
        this.container = byteBuffer;
        this.mark = i;
        this.position = i2;
        this.limit = i3;
        this.capacity = i4;
    }

    public long address0() {
        return this.address;
    }

    @Override // org.lwjgl.system.Pointer.Default, org.lwjgl.system.Pointer
    public long address() {
        return this.address + (Integer.toUnsignedLong(this.position) * sizeof());
    }

    public long address(int i) {
        return this.address + (Integer.toUnsignedLong(i) * sizeof());
    }

    public void free() {
        MemoryUtil.nmemFree(this.address);
    }

    public int capacity() {
        return this.capacity;
    }

    public int position() {
        return this.position;
    }

    public SELF position(int i) {
        if (i < 0 || this.limit < i) {
            throw new IllegalArgumentException();
        }
        this.position = i;
        if (i < this.mark) {
            this.mark = -1;
        }
        return self();
    }

    public int limit() {
        return this.limit;
    }

    public SELF limit(int i) {
        if (i < 0 || this.capacity < i) {
            throw new IllegalArgumentException();
        }
        this.limit = i;
        if (i < this.position) {
            this.position = i;
        }
        if (i < this.mark) {
            this.mark = -1;
        }
        return self();
    }

    public SELF mark() {
        this.mark = this.position;
        return self();
    }

    public SELF reset() {
        int i = this.mark;
        if (i < 0) {
            throw new InvalidMarkException();
        }
        this.position = i;
        return self();
    }

    public SELF clear() {
        this.position = 0;
        this.limit = this.capacity;
        this.mark = -1;
        return self();
    }

    public SELF flip() {
        this.limit = this.position;
        this.position = 0;
        this.mark = -1;
        return self();
    }

    public SELF rewind() {
        this.position = 0;
        this.mark = -1;
        return self();
    }

    public int remaining() {
        return this.limit - this.position;
    }

    public boolean hasRemaining() {
        return this.position < this.limit;
    }

    public SELF slice() {
        try {
            SELF self = (SELF) UNSAFE.allocateInstance(getClass());
            UNSAFE.putLong(self, ADDRESS, this.address + (Integer.toUnsignedLong(this.position) * sizeof()));
            UNSAFE.putInt(self, BUFFER_MARK, -1);
            UNSAFE.putInt(self, BUFFER_LIMIT, remaining());
            UNSAFE.putInt(self, BUFFER_CAPACITY, remaining());
            UNSAFE.putObject(self, BUFFER_CONTAINER, this.container);
            return self;
        } catch (InstantiationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public SELF slice(int i, int i2) {
        int i3 = this.position + i;
        if (i < 0 || this.limit < i) {
            throw new IllegalArgumentException();
        }
        if (i2 < 0 || this.capacity - i3 < i2) {
            throw new IllegalArgumentException();
        }
        try {
            SELF self = (SELF) UNSAFE.allocateInstance(getClass());
            UNSAFE.putLong(self, ADDRESS, this.address + (Integer.toUnsignedLong(i3) * sizeof()));
            UNSAFE.putInt(self, BUFFER_MARK, -1);
            UNSAFE.putInt(self, BUFFER_LIMIT, i2);
            UNSAFE.putInt(self, BUFFER_CAPACITY, i2);
            UNSAFE.putObject(self, BUFFER_CONTAINER, this.container);
            return self;
        } catch (InstantiationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public SELF duplicate() {
        try {
            SELF self = (SELF) UNSAFE.allocateInstance(getClass());
            UNSAFE.putLong(self, ADDRESS, this.address);
            UNSAFE.putInt(self, BUFFER_MARK, this.mark);
            UNSAFE.putInt(self, BUFFER_POSITION, this.position);
            UNSAFE.putInt(self, BUFFER_LIMIT, this.limit);
            UNSAFE.putInt(self, BUFFER_CAPACITY, this.capacity);
            UNSAFE.putObject(self, BUFFER_CONTAINER, this.container);
            return self;
        } catch (InstantiationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public SELF put(SELF self) {
        if (self == this) {
            throw new IllegalArgumentException();
        }
        int remaining = self.remaining();
        if (remaining() < remaining) {
            throw new BufferOverflowException();
        }
        MemoryUtil.memCopy(self.address(), address(), Integer.toUnsignedLong(remaining) * sizeof());
        this.position += remaining;
        return self();
    }

    public SELF compact() {
        MemoryUtil.memCopy(address(), this.address, Integer.toUnsignedLong(remaining()) * sizeof());
        position(remaining());
        limit(capacity());
        this.mark = -1;
        return self();
    }

    @Override // org.lwjgl.system.Pointer.Default
    public String toString() {
        return getClass().getName() + "[pos=" + position() + " lim=" + limit() + " cap=" + capacity() + "]";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int nextGetIndex() {
        if (this.position < this.limit) {
            int i = this.position;
            this.position = i + 1;
            return i;
        }
        throw new BufferUnderflowException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int nextPutIndex() {
        if (this.position < this.limit) {
            int i = this.position;
            this.position = i + 1;
            return i;
        }
        throw new BufferOverflowException();
    }
}
