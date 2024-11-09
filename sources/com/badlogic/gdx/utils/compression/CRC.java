package com.badlogic.gdx.utils.compression;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/CRC.class */
public class CRC {
    public static int[] Table = new int[256];
    int _value = -1;

    static {
        int i;
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2;
            for (int i4 = 0; i4 < 8; i4++) {
                if ((i3 & 1) != 0) {
                    i = (i3 >>> 1) ^ (-306674912);
                } else {
                    i = i3 >>> 1;
                }
                i3 = i;
            }
            Table[i2] = i3;
        }
    }

    public void Init() {
        this._value = -1;
    }

    public void Update(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this._value = Table[(this._value ^ bArr[i + i3]) & 255] ^ (this._value >>> 8);
        }
    }

    public void Update(byte[] bArr) {
        for (byte b2 : bArr) {
            this._value = Table[(this._value ^ b2) & 255] ^ (this._value >>> 8);
        }
    }

    public void UpdateByte(int i) {
        this._value = Table[(this._value ^ i) & 255] ^ (this._value >>> 8);
    }

    public int GetDigest() {
        return this._value ^ (-1);
    }
}
