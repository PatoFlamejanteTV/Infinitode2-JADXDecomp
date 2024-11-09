package com.badlogic.gdx.utils.compression.rangecoder;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/rangecoder/BitTreeDecoder.class */
public class BitTreeDecoder {
    short[] Models;
    int NumBitLevels;

    public BitTreeDecoder(int i) {
        this.NumBitLevels = i;
        this.Models = new short[1 << i];
    }

    public void Init() {
        Decoder.InitBitModels(this.Models);
    }

    public int Decode(Decoder decoder) {
        int i = 1;
        for (int i2 = this.NumBitLevels; i2 != 0; i2--) {
            i = (i << 1) + decoder.DecodeBit(this.Models, i);
        }
        return i - (1 << this.NumBitLevels);
    }

    public int ReverseDecode(Decoder decoder) {
        int i = 1;
        int i2 = 0;
        for (int i3 = 0; i3 < this.NumBitLevels; i3++) {
            int DecodeBit = decoder.DecodeBit(this.Models, i);
            i = (i << 1) + DecodeBit;
            i2 |= DecodeBit << i3;
        }
        return i2;
    }

    public static int ReverseDecode(short[] sArr, int i, Decoder decoder, int i2) {
        int i3 = 1;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            int DecodeBit = decoder.DecodeBit(sArr, i + i3);
            i3 = (i3 << 1) + DecodeBit;
            i4 |= DecodeBit << i5;
        }
        return i4;
    }
}
