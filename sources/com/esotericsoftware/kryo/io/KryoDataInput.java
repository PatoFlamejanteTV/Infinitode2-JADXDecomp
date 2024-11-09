package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;
import java.io.DataInput;
import java.io.EOFException;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/KryoDataInput.class */
public class KryoDataInput implements DataInput, AutoCloseable {
    protected Input input;

    public KryoDataInput(Input input) {
        this.input = input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr) {
        readFully(bArr, 0, bArr.length);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr, int i, int i2) {
        try {
            this.input.readBytes(bArr, i, i2);
        } catch (KryoException e) {
            throw new EOFException(e.getMessage());
        }
    }

    @Override // java.io.DataInput
    public int skipBytes(int i) {
        return (int) this.input.skip(i);
    }

    @Override // java.io.DataInput
    public boolean readBoolean() {
        return this.input.readBoolean();
    }

    @Override // java.io.DataInput
    public byte readByte() {
        return this.input.readByte();
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() {
        return this.input.readByteUnsigned();
    }

    @Override // java.io.DataInput
    public short readShort() {
        return this.input.readShort();
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() {
        return this.input.readShortUnsigned();
    }

    @Override // java.io.DataInput
    public char readChar() {
        return this.input.readChar();
    }

    @Override // java.io.DataInput
    public int readInt() {
        return this.input.readInt();
    }

    @Override // java.io.DataInput
    public long readLong() {
        return this.input.readLong();
    }

    @Override // java.io.DataInput
    public float readFloat() {
        return this.input.readFloat();
    }

    @Override // java.io.DataInput
    public double readDouble() {
        return this.input.readDouble();
    }

    @Override // java.io.DataInput
    public String readLine() {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.DataInput
    public String readUTF() {
        return this.input.readString();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.input.close();
    }
}
