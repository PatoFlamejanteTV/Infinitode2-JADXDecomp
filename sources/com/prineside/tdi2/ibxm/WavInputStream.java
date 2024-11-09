package com.prineside.tdi2.ibxm;

import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/WavInputStream.class */
public class WavInputStream extends InputStream {
    public static final byte[] header = {82, 73, 70, 70, 0, 0, 0, 0, 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 16, 0, 100, 97, 116, 97, 0, 0, 0, 0};

    /* renamed from: a, reason: collision with root package name */
    private IBXM f2211a;

    /* renamed from: b, reason: collision with root package name */
    private int[] f2212b;
    private byte[] c;
    private int d;
    private int e;
    private int f;
    private int g;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/WavInputStream$Mode.class */
    public enum Mode {
        FULL_SONG,
        INTRO_PART,
        LOOPING_PART;

        public static final Mode[] values = values();
    }

    public WavInputStream(IBXM ibxm, int i, Mode mode) {
        int i2 = -1;
        int i3 = 0;
        if (ibxm.getModule().restartPos != 0) {
            if (mode == Mode.INTRO_PART && ibxm.getModule().restartPos != 0) {
                i2 = 0;
                i3 = ibxm.getModule().restartPos - 1;
            } else if (mode == Mode.LOOPING_PART) {
                i2 = ibxm.getModule().restartPos;
                i3 = ibxm.getModule().sequenceLength - 1;
            }
        }
        if (i2 != -1) {
            Module module = new Module(ibxm.getModule());
            module.sequenceLength = (i3 - i2) + 1;
            int[] iArr = module.sequence;
            module.sequence = new int[module.sequenceLength];
            System.arraycopy(iArr, i2, module.sequence, 0, module.sequenceLength);
            int i4 = ibxm.interpolation;
            IBXM ibxm2 = new IBXM(module, ibxm.getSampleRate());
            ibxm = ibxm2;
            ibxm2.setInterpolation(i4);
        }
        int calculateSongDuration = ibxm.calculateSongDuration();
        this.f2211a = ibxm;
        this.f2212b = new int[ibxm.getMixBufferLength()];
        this.c = new byte[this.f2212b.length << 1];
        int i5 = calculateSongDuration << 2;
        int sampleRate = ibxm.getSampleRate();
        System.arraycopy(header, 0, this.c, 0, header.length);
        writeInt32(this.c, 4, i5 + 36);
        writeInt32(this.c, 24, sampleRate);
        writeInt32(this.c, 28, sampleRate << 2);
        writeInt32(this.c, 40, i5);
        this.d = 0;
        this.e = header.length;
        this.f = header.length + i5;
        this.g = (sampleRate << 2) * i;
    }

    public int getRemain() {
        return this.f;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.e - this.d;
    }

    @Override // java.io.InputStream
    @IgnoreMethodOverloadLuaDefWarning
    public int read() {
        byte b2 = -1;
        if (this.f > 0) {
            byte[] bArr = this.c;
            int i = this.d;
            this.d = i + 1;
            b2 = bArr[i];
            if (this.d >= this.e) {
                a();
            }
            this.f--;
        }
        return b2;
    }

    @Override // java.io.InputStream
    @IgnoreMethodOverloadLuaDefWarning
    public int read(byte[] bArr, int i, int i2) {
        int i3 = -1;
        if (this.f > 0) {
            int i4 = this.f;
            i3 = i4;
            if (i4 > i2) {
                i3 = i2;
            }
            int i5 = this.e - this.d;
            if (i3 > i5) {
                i3 = i5;
            }
            System.arraycopy(this.c, this.d, bArr, i, i3);
            this.d += i3;
            if (this.d >= this.e) {
                a();
            }
            this.f -= i3;
        }
        return i3;
    }

    private void a() {
        int audio = this.f2211a.getAudio(this.f2212b) << 1;
        int i = 1024;
        if (this.f < this.g) {
            int i2 = this.f / (this.g >> 10);
            i = ((i2 * i2) * i2) >> 20;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < audio; i4++) {
            int i5 = (this.f2212b[i4] * i) >> 10;
            int i6 = i5;
            if (i5 > 32767) {
                i6 = 32767;
            }
            if (i6 < -32768) {
                i6 = -32768;
            }
            int i7 = i3;
            int i8 = i3 + 1;
            this.c[i7] = (byte) i6;
            i3 = i8 + 1;
            this.c[i8] = (byte) (i6 >> 8);
        }
        this.d = 0;
        this.e = audio << 1;
    }

    public static void writeInt32(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) i2;
        bArr[i + 1] = (byte) (i2 >> 8);
        bArr[i + 2] = (byte) (i2 >> 16);
        bArr[i + 3] = (byte) (i2 >> 24);
    }

    public static int readInt32(byte[] bArr, int i) {
        return (bArr[i + 3] << 24) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }
}
