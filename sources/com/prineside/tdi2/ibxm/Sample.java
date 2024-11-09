package com.prineside.tdi2.ibxm;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/Sample.class */
public class Sample {
    public static final int FP_SHIFT = 15;
    public static final int FP_ONE = 32768;
    public static final int FP_MASK = 32767;
    public static final int C2_PAL = 8287;
    public static final int C2_NTSC = 8363;
    public String name;
    public int volume;
    public int panning;
    public int relNote;
    public int fineTune;

    /* renamed from: a, reason: collision with root package name */
    private int f2209a;

    /* renamed from: b, reason: collision with root package name */
    private int f2210b;
    public short[] sampleData;
    private static final short[][] c = a();

    public Sample() {
        this.name = "";
        this.volume = 0;
        this.panning = -1;
        this.relNote = 0;
        this.fineTune = 0;
        this.f2209a = 0;
        this.f2210b = 0;
    }

    public Sample(Sample sample) {
        this.name = "";
        this.volume = 0;
        this.panning = -1;
        this.relNote = 0;
        this.fineTune = 0;
        this.f2209a = 0;
        this.f2210b = 0;
        this.name = sample.name;
        this.volume = sample.volume;
        this.panning = sample.panning;
        this.relNote = sample.relNote;
        this.fineTune = sample.fineTune;
        this.f2209a = sample.f2209a;
        this.f2210b = sample.f2210b;
        if (sample.sampleData != null) {
            this.sampleData = new short[sample.sampleData.length];
            System.arraycopy(sample.sampleData, 0, this.sampleData, 0, this.sampleData.length);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [short[], short[][]] */
    private static short[][] a() {
        ?? r0 = new short[8];
        for (int i = 0; i < 8; i++) {
            r0[i] = a(1.0d / (i + 1));
        }
        return r0;
    }

    private static short[] a(double d) {
        short[] sArr = new short[272];
        int i = 0;
        for (int i2 = 0; i2 <= 16; i2++) {
            double d2 = 3.141592653589793d * ((i2 / 16.0d) + 7.0d);
            double d3 = 3.141592653589793d + ((d2 * 2.0d) / 16.0d);
            for (int i3 = 0; i3 < 16; i3++) {
                double d4 = d;
                if (d2 != 0.0d) {
                    d4 = Math.sin(d * d2) / d2;
                }
                int i4 = i;
                i++;
                sArr[i4] = (short) Math.round(d4 * (((0.35875d - (0.48829d * Math.cos(d3))) + (0.14128d * Math.cos(d3 * 2.0d))) - (0.01168d * Math.cos(d3 * 3.0d))) * 32767.0d);
                d2 -= 3.141592653589793d;
                d3 -= 0.39269908169872414d;
            }
        }
        return sArr;
    }

    public void setSampleData(short[] sArr, int i, int i2, boolean z) {
        int length = sArr.length;
        if (i < 0 || i > length) {
            i = length;
        }
        if (i2 < 0 || i + i2 > length) {
            i2 = length - i;
        }
        int i3 = i + i2;
        int i4 = i + 8;
        short[] sArr2 = new short[i3 + 8 + (z ? i2 : 0) + 16];
        System.arraycopy(sArr, 0, sArr2, 8, i3);
        if (z) {
            int i5 = i4 + i2;
            for (int i6 = 0; i6 < i2; i6++) {
                sArr2[i5 + i6] = sArr2[(i5 - i6) - 1];
            }
            i2 <<= 1;
        }
        int i7 = i4 + i2;
        int i8 = i7 + 16;
        for (int i9 = i7; i9 < i8; i9++) {
            sArr2[i9] = sArr2[i9 - i2];
        }
        this.sampleData = sArr2;
        this.f2209a = i4;
        this.f2210b = i2;
    }

    public void resampleNearest(int i, int i2, int i3, int i4, int i5, int[] iArr, int i6, int i7) {
        int i8 = this.f2210b;
        int i9 = this.f2209a + i8;
        int i10 = i + 8;
        if (i10 >= i9) {
            i10 = normaliseSampleIdx(i10);
        }
        short[] sArr = this.sampleData;
        int i11 = i6 << 1;
        int i12 = (i6 + i7) << 1;
        while (i11 < i12) {
            if (i10 >= i9) {
                if (i8 >= 2) {
                    while (i10 >= i9) {
                        i10 -= i8;
                    }
                } else {
                    return;
                }
            }
            short s = sArr[i10];
            int i13 = i11;
            int i14 = i11 + 1;
            iArr[i13] = iArr[i13] + ((s * i4) >> 15);
            i11 = i14 + 1;
            iArr[i14] = iArr[i14] + ((s * i5) >> 15);
            int i15 = i2 + i3;
            i10 += i15 >> 15;
            i2 = i15 & 32767;
        }
    }

    public void resampleLinear(int i, int i2, int i3, int i4, int i5, int[] iArr, int i6, int i7) {
        int i8 = this.f2210b;
        int i9 = this.f2209a + i8;
        int i10 = i + 8;
        if (i10 >= i9) {
            i10 = normaliseSampleIdx(i10);
        }
        short[] sArr = this.sampleData;
        int i11 = i6 << 1;
        int i12 = (i6 + i7) << 1;
        while (i11 < i12) {
            if (i10 >= i9) {
                if (i8 >= 2) {
                    while (i10 >= i9) {
                        i10 -= i8;
                    }
                } else {
                    return;
                }
            }
            short s = sArr[i10];
            int i13 = (((sArr[i10 + 1] - s) * i2) >> 15) + s;
            int i14 = i11;
            int i15 = i11 + 1;
            iArr[i14] = iArr[i14] + ((i13 * i4) >> 15);
            i11 = i15 + 1;
            iArr[i15] = iArr[i15] + ((i13 * i5) >> 15);
            int i16 = i2 + i3;
            i10 += i16 >> 15;
            i2 = i16 & 32767;
        }
    }

    public void resampleSinc(int i, int i2, int i3, int i4, int i5, int[] iArr, int i6, int i7) {
        int i8 = 0;
        if (i3 > 32768) {
            int i9 = (i3 >> 15) - 1;
            i8 = i9;
            if (i9 >= 8) {
                i8 = 7;
            }
        }
        short[] sArr = c[i8];
        int i10 = this.f2210b;
        int i11 = this.f2209a + i10;
        if (i >= i11) {
            i = normaliseSampleIdx(i);
        }
        short[] sArr2 = this.sampleData;
        int i12 = i6 << 1;
        int i13 = (i6 + i7) << 1;
        while (i12 < i13) {
            if (i >= i11) {
                if (i10 >= 2) {
                    while (i >= i11) {
                        i -= i10;
                    }
                } else {
                    return;
                }
            }
            int i14 = (i2 >> 11) << 4;
            int i15 = i14 + 16;
            int i16 = 0;
            int i17 = 0;
            for (int i18 = 0; i18 < 16; i18++) {
                i16 += sArr[i14 + i18] * sArr2[i + i18];
                i17 += sArr[i15 + i18] * sArr2[i + i18];
            }
            int i19 = i16 >> 15;
            int i20 = i19 + ((((i17 >> 15) - i19) * (i2 & 2047)) >> 11);
            int i21 = i12;
            int i22 = i12 + 1;
            iArr[i21] = iArr[i21] + ((i20 * i4) >> 15);
            i12 = i22 + 1;
            iArr[i22] = iArr[i22] + ((i20 * i5) >> 15);
            int i23 = i2 + i3;
            i += i23 >> 15;
            i2 = i23 & 32767;
        }
    }

    public int normaliseSampleIdx(int i) {
        int i2 = i - this.f2209a;
        if (i2 > 0) {
            i = this.f2209a;
            if (this.f2210b > 1) {
                i += i2 % this.f2210b;
            }
        }
        return i;
    }

    public boolean looped() {
        return this.f2210b > 1;
    }

    public void toStringBuffer(StringBuffer stringBuffer) {
        stringBuffer.append("Name: " + this.name + '\n');
        stringBuffer.append("Volume: " + this.volume + '\n');
        stringBuffer.append("Panning: " + this.panning + '\n');
        stringBuffer.append("Relative Note: " + this.relNote + '\n');
        stringBuffer.append("Fine Tune: " + this.fineTune + '\n');
        stringBuffer.append("Loop Start: " + this.f2209a + '\n');
        stringBuffer.append("Loop Length: " + this.f2210b + '\n');
    }
}
