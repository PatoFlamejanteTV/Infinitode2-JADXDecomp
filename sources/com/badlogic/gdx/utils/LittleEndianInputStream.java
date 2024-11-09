package com.badlogic.gdx.utils;

import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/LittleEndianInputStream.class */
public class LittleEndianInputStream extends FilterInputStream implements java.io.DataInput {
    private DataInputStream din;

    public LittleEndianInputStream(InputStream inputStream) {
        super(inputStream);
        this.din = new DataInputStream(inputStream);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr) {
        this.din.readFully(bArr);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr, int i, int i2) {
        this.din.readFully(bArr, i, i2);
    }

    @Override // java.io.DataInput
    public int skipBytes(int i) {
        return this.din.skipBytes(i);
    }

    @Override // java.io.DataInput
    public boolean readBoolean() {
        return this.din.readBoolean();
    }

    @Override // java.io.DataInput
    public byte readByte() {
        return this.din.readByte();
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() {
        return this.din.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public short readShort() {
        return (short) ((this.din.read() << 8) | (this.din.read() & 255));
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() {
        return ((this.din.read() & 255) << 8) | (this.din.read() & 255);
    }

    @Override // java.io.DataInput
    public char readChar() {
        return this.din.readChar();
    }

    @Override // java.io.DataInput
    public int readInt() {
        int[] iArr = new int[4];
        for (int i = 3; i >= 0; i--) {
            iArr[i] = this.din.read();
        }
        return ((iArr[0] & 255) << 24) | ((iArr[1] & 255) << 16) | ((iArr[2] & 255) << 8) | (iArr[3] & 255);
    }

    @Override // java.io.DataInput
    public long readLong() {
        int[] iArr = new int[8];
        for (int i = 7; i >= 0; i--) {
            iArr[i] = this.din.read();
        }
        return ((iArr[0] & 255) << 56) | ((iArr[1] & 255) << 48) | ((iArr[2] & 255) << 40) | ((iArr[3] & 255) << 32) | ((iArr[4] & 255) << 24) | ((iArr[5] & 255) << 16) | ((iArr[6] & 255) << 8) | (iArr[7] & 255);
    }

    @Override // java.io.DataInput
    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    public final String readLine() {
        return this.din.readLine();
    }

    @Override // java.io.DataInput
    public String readUTF() {
        return this.din.readUTF();
    }
}
