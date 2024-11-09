package com.badlogic.gdx.utils.compression.rangecoder;

import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/rangecoder/Decoder.class */
public class Decoder {
    static final int kTopMask = -16777216;
    static final int kNumBitModelTotalBits = 11;
    static final int kBitModelTotal = 2048;
    static final int kNumMoveBits = 5;
    int Range;
    int Code;
    InputStream Stream;

    public final void SetStream(InputStream inputStream) {
        this.Stream = inputStream;
    }

    public final void ReleaseStream() {
        this.Stream = null;
    }

    public final void Init() {
        this.Code = 0;
        this.Range = -1;
        for (int i = 0; i < 5; i++) {
            this.Code = (this.Code << 8) | this.Stream.read();
        }
    }

    public final int DecodeDirectBits(int i) {
        int i2 = 0;
        for (int i3 = i; i3 != 0; i3--) {
            this.Range >>>= 1;
            int i4 = (this.Code - this.Range) >>> 31;
            this.Code -= this.Range & (i4 - 1);
            i2 = (i2 << 1) | (1 - i4);
            if ((this.Range & kTopMask) == 0) {
                this.Code = (this.Code << 8) | this.Stream.read();
                this.Range <<= 8;
            }
        }
        return i2;
    }

    public int DecodeBit(short[] sArr, int i) {
        short s = sArr[i];
        int i2 = (this.Range >>> 11) * s;
        if ((this.Code ^ Integer.MIN_VALUE) < (i2 ^ Integer.MIN_VALUE)) {
            this.Range = i2;
            sArr[i] = (short) (s + ((2048 - s) >>> 5));
            if ((this.Range & kTopMask) == 0) {
                this.Code = (this.Code << 8) | this.Stream.read();
                this.Range <<= 8;
                return 0;
            }
            return 0;
        }
        this.Range -= i2;
        this.Code -= i2;
        sArr[i] = (short) (s - (s >>> 5));
        if ((this.Range & kTopMask) == 0) {
            this.Code = (this.Code << 8) | this.Stream.read();
            this.Range <<= 8;
            return 1;
        }
        return 1;
    }

    public static void InitBitModels(short[] sArr) {
        for (int i = 0; i < sArr.length; i++) {
            sArr[i] = 1024;
        }
    }
}
