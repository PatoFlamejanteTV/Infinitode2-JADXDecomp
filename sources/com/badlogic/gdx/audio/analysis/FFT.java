package com.badlogic.gdx.audio.analysis;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/audio/analysis/FFT.class */
public class FFT extends FourierTransform {
    private int[] reverse;
    private float[] sinlookup;
    private float[] coslookup;

    public FFT(int i, float f) {
        super(i, f);
        if ((i & (i - 1)) != 0) {
            throw new IllegalArgumentException("FFT: timeSize must be a power of two.");
        }
        buildReverseTable();
        buildTrigTables();
    }

    @Override // com.badlogic.gdx.audio.analysis.FourierTransform
    protected void allocateArrays() {
        this.spectrum = new float[(this.timeSize / 2) + 1];
        this.real = new float[this.timeSize];
        this.imag = new float[this.timeSize];
    }

    @Override // com.badlogic.gdx.audio.analysis.FourierTransform
    public void scaleBand(int i, float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Can't scale a frequency band by a negative value.");
        }
        if (this.spectrum[i] != 0.0f) {
            float[] fArr = this.real;
            fArr[i] = fArr[i] / this.spectrum[i];
            float[] fArr2 = this.imag;
            fArr2[i] = fArr2[i] / this.spectrum[i];
            float[] fArr3 = this.spectrum;
            fArr3[i] = fArr3[i] * f;
            float[] fArr4 = this.real;
            fArr4[i] = fArr4[i] * this.spectrum[i];
            float[] fArr5 = this.imag;
            fArr5[i] = fArr5[i] * this.spectrum[i];
        }
        if (i != 0 && i != this.timeSize / 2) {
            this.real[this.timeSize - i] = this.real[i];
            this.imag[this.timeSize - i] = -this.imag[i];
        }
    }

    @Override // com.badlogic.gdx.audio.analysis.FourierTransform
    public void setBand(int i, float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Can't set a frequency band to a negative value.");
        }
        if (this.real[i] == 0.0f && this.imag[i] == 0.0f) {
            this.real[i] = f;
            this.spectrum[i] = f;
        } else {
            float[] fArr = this.real;
            fArr[i] = fArr[i] / this.spectrum[i];
            float[] fArr2 = this.imag;
            fArr2[i] = fArr2[i] / this.spectrum[i];
            this.spectrum[i] = f;
            float[] fArr3 = this.real;
            fArr3[i] = fArr3[i] * this.spectrum[i];
            float[] fArr4 = this.imag;
            fArr4[i] = fArr4[i] * this.spectrum[i];
        }
        if (i != 0 && i != this.timeSize / 2) {
            this.real[this.timeSize - i] = this.real[i];
            this.imag[this.timeSize - i] = -this.imag[i];
        }
    }

    private void fft() {
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 < this.real.length) {
                float cos = cos(i2);
                float sin = sin(i2);
                float f = 1.0f;
                float f2 = 0.0f;
                for (int i3 = 0; i3 < i2; i3++) {
                    int i4 = i3;
                    while (true) {
                        int i5 = i4;
                        if (i5 < this.real.length) {
                            int i6 = i5 + i2;
                            float f3 = (f * this.real[i6]) - (f2 * this.imag[i6]);
                            float f4 = (f * this.imag[i6]) + (f2 * this.real[i6]);
                            float[] fArr = this.real;
                            fArr[i6] = fArr[i5] - f3;
                            float[] fArr2 = this.imag;
                            fArr2[i6] = fArr2[i5] - f4;
                            float[] fArr3 = this.real;
                            fArr3[i5] = fArr3[i5] + f3;
                            float[] fArr4 = this.imag;
                            fArr4[i5] = fArr4[i5] + f4;
                            i4 = i5 + (2 * i2);
                        }
                    }
                    float f5 = f;
                    f = (f5 * cos) - (f2 * sin);
                    f2 = (f5 * sin) + (f2 * cos);
                }
                i = i2 << 1;
            } else {
                return;
            }
        }
    }

    @Override // com.badlogic.gdx.audio.analysis.FourierTransform
    public void forward(float[] fArr) {
        if (fArr.length != this.timeSize) {
            throw new IllegalArgumentException("FFT.forward: The length of the passed sample buffer must be equal to timeSize().");
        }
        doWindow(fArr);
        bitReverseSamples(fArr);
        fft();
        fillSpectrum();
    }

    public void forward(float[] fArr, float[] fArr2) {
        if (fArr.length != this.timeSize || fArr2.length != this.timeSize) {
            throw new IllegalArgumentException("FFT.forward: The length of the passed buffers must be equal to timeSize().");
        }
        setComplex(fArr, fArr2);
        bitReverseComplex();
        fft();
        fillSpectrum();
    }

    @Override // com.badlogic.gdx.audio.analysis.FourierTransform
    public void inverse(float[] fArr) {
        if (fArr.length > this.real.length) {
            throw new IllegalArgumentException("FFT.inverse: the passed array's length must equal FFT.timeSize().");
        }
        for (int i = 0; i < this.timeSize; i++) {
            float[] fArr2 = this.imag;
            int i2 = i;
            fArr2[i2] = -fArr2[i2];
        }
        bitReverseComplex();
        fft();
        for (int i3 = 0; i3 < fArr.length; i3++) {
            fArr[i3] = this.real[i3] / this.real.length;
        }
    }

    private void buildReverseTable() {
        int i = this.timeSize;
        this.reverse = new int[i];
        this.reverse[0] = 0;
        int i2 = 1;
        int i3 = i / 2;
        while (true) {
            int i4 = i3;
            if (i2 < i) {
                for (int i5 = 0; i5 < i2; i5++) {
                    this.reverse[i5 + i2] = this.reverse[i5] + i4;
                }
                i2 <<= 1;
                i3 = i4 >> 1;
            } else {
                return;
            }
        }
    }

    private void bitReverseSamples(float[] fArr) {
        for (int i = 0; i < fArr.length; i++) {
            this.real[i] = fArr[this.reverse[i]];
            this.imag[i] = 0.0f;
        }
    }

    private void bitReverseComplex() {
        float[] fArr = new float[this.real.length];
        float[] fArr2 = new float[this.imag.length];
        for (int i = 0; i < this.real.length; i++) {
            fArr[i] = this.real[this.reverse[i]];
            fArr2[i] = this.imag[this.reverse[i]];
        }
        this.real = fArr;
        this.imag = fArr2;
    }

    private float sin(int i) {
        return this.sinlookup[i];
    }

    private float cos(int i) {
        return this.coslookup[i];
    }

    private void buildTrigTables() {
        int i = this.timeSize;
        this.sinlookup = new float[i];
        this.coslookup = new float[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.sinlookup[i2] = (float) Math.sin((-3.1415927f) / i2);
            this.coslookup[i2] = (float) Math.cos((-3.1415927f) / i2);
        }
    }
}
