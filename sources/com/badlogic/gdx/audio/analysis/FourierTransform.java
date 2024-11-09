package com.badlogic.gdx.audio.analysis;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/audio/analysis/FourierTransform.class */
public abstract class FourierTransform {
    public static final int NONE = 0;
    public static final int HAMMING = 1;
    protected static final int LINAVG = 2;
    protected static final int LOGAVG = 3;
    protected static final int NOAVG = 4;
    protected static final float TWO_PI = 6.2831855f;
    protected int timeSize;
    protected int sampleRate;
    protected float bandWidth;
    protected int whichWindow;
    protected float[] real;
    protected float[] imag;
    protected float[] spectrum;
    protected float[] averages;
    protected int whichAverage;
    protected int octaves;
    protected int avgPerOctave;

    protected abstract void allocateArrays();

    public abstract void setBand(int i, float f);

    public abstract void scaleBand(int i, float f);

    public abstract void forward(float[] fArr);

    public abstract void inverse(float[] fArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public FourierTransform(int i, float f) {
        this.timeSize = i;
        this.sampleRate = (int) f;
        this.bandWidth = (2.0f / this.timeSize) * (this.sampleRate / 2.0f);
        noAverages();
        allocateArrays();
        this.whichWindow = 0;
    }

    public int getTimeSize() {
        return this.timeSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setComplex(float[] fArr, float[] fArr2) {
        if (this.real.length != fArr.length && this.imag.length != fArr2.length) {
            throw new IllegalArgumentException("This won't work");
        }
        System.arraycopy(fArr, 0, this.real, 0, fArr.length);
        System.arraycopy(fArr2, 0, this.imag, 0, fArr2.length);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fillSpectrum() {
        float pow;
        int i;
        for (int i2 = 0; i2 < this.spectrum.length; i2++) {
            this.spectrum[i2] = (float) Math.sqrt((this.real[i2] * this.real[i2]) + (this.imag[i2] * this.imag[i2]));
        }
        if (this.whichAverage == 2) {
            int length = this.spectrum.length / this.averages.length;
            for (int i3 = 0; i3 < this.averages.length; i3++) {
                float f = 0.0f;
                int i4 = 0;
                while (i4 < length && (i = i4 + (i3 * length)) < this.spectrum.length) {
                    f += this.spectrum[i];
                    i4++;
                }
                this.averages[i3] = f / (i4 + 1);
            }
            return;
        }
        if (this.whichAverage == 3) {
            for (int i5 = 0; i5 < this.octaves; i5++) {
                if (i5 == 0) {
                    pow = 0.0f;
                } else {
                    pow = (this.sampleRate / 2) / ((float) Math.pow(2.0d, this.octaves - i5));
                }
                float pow2 = (((this.sampleRate / 2) / ((float) Math.pow(2.0d, (this.octaves - i5) - 1))) - pow) / this.avgPerOctave;
                float f2 = pow;
                for (int i6 = 0; i6 < this.avgPerOctave; i6++) {
                    float f3 = f2;
                    this.averages[i6 + (i5 * this.avgPerOctave)] = calcAvg(f3, f3 + pow2);
                    f2 += pow2;
                }
            }
        }
    }

    public void noAverages() {
        this.averages = new float[0];
        this.whichAverage = 4;
    }

    public void linAverages(int i) {
        if (i > this.spectrum.length / 2) {
            throw new IllegalArgumentException("The number of averages for this transform can be at most " + (this.spectrum.length / 2) + ".");
        }
        this.averages = new float[i];
        this.whichAverage = 2;
    }

    public void logAverages(int i, int i2) {
        float f = this.sampleRate / 2.0f;
        this.octaves = 1;
        while (true) {
            float f2 = f / 2.0f;
            f = f2;
            if (f2 > i) {
                this.octaves++;
            } else {
                this.avgPerOctave = i2;
                this.averages = new float[this.octaves * i2];
                this.whichAverage = 3;
                return;
            }
        }
    }

    public void window(int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Invalid window type.");
        }
        this.whichWindow = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doWindow(float[] fArr) {
        switch (this.whichWindow) {
            case 1:
                hamming(fArr);
                return;
            default:
                return;
        }
    }

    protected void hamming(float[] fArr) {
        for (int i = 0; i < fArr.length; i++) {
            fArr[i] = (float) (fArr[r1] * (0.5400000214576721d - (0.46000000834465027d * Math.cos((6.2831855f * i) / (fArr.length - 1)))));
        }
    }

    public int timeSize() {
        return this.timeSize;
    }

    public int specSize() {
        return this.spectrum.length;
    }

    public float getBand(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > this.spectrum.length - 1) {
            i = this.spectrum.length - 1;
        }
        return this.spectrum[i];
    }

    public float getBandWidth() {
        return this.bandWidth;
    }

    public int freqToIndex(float f) {
        if (f < getBandWidth() / 2.0f) {
            return 0;
        }
        if (f > (this.sampleRate / 2) - (getBandWidth() / 2.0f)) {
            return this.spectrum.length - 1;
        }
        return Math.round(this.timeSize * (f / this.sampleRate));
    }

    public float indexToFreq(int i) {
        float bandWidth = getBandWidth();
        if (i == 0) {
            return bandWidth * 0.25f;
        }
        if (i == this.spectrum.length - 1) {
            return ((this.sampleRate / 2) - (bandWidth / 2.0f)) + (bandWidth * 0.25f);
        }
        return i * bandWidth;
    }

    public float getAverageCenterFrequency(int i) {
        float pow;
        if (this.whichAverage == 2) {
            int length = this.spectrum.length / this.averages.length;
            return indexToFreq((i * length) + (length / 2));
        }
        if (this.whichAverage == 3) {
            int i2 = i / this.avgPerOctave;
            int i3 = i % this.avgPerOctave;
            if (i2 == 0) {
                pow = 0.0f;
            } else {
                pow = (this.sampleRate / 2) / ((float) Math.pow(2.0d, this.octaves - i2));
            }
            float pow2 = (((this.sampleRate / 2) / ((float) Math.pow(2.0d, (this.octaves - i2) - 1))) - pow) / this.avgPerOctave;
            return pow + (i3 * pow2) + (pow2 / 2.0f);
        }
        return 0.0f;
    }

    public float getFreq(float f) {
        return getBand(freqToIndex(f));
    }

    public void setFreq(float f, float f2) {
        setBand(freqToIndex(f), f2);
    }

    public void scaleFreq(float f, float f2) {
        scaleBand(freqToIndex(f), f2);
    }

    public int avgSize() {
        return this.averages.length;
    }

    public float getAvg(int i) {
        float f;
        if (this.averages.length > 0) {
            f = this.averages[i];
        } else {
            f = 0.0f;
        }
        return f;
    }

    public float calcAvg(float f, float f2) {
        int freqToIndex = freqToIndex(f);
        int freqToIndex2 = freqToIndex(f2);
        float f3 = 0.0f;
        for (int i = freqToIndex; i <= freqToIndex2; i++) {
            f3 += this.spectrum[i];
        }
        return f3 / ((freqToIndex2 - freqToIndex) + 1);
    }

    public void forward(float[] fArr, int i) {
        if (fArr.length - i < this.timeSize) {
            throw new IllegalArgumentException("FourierTransform.forward: not enough samples in the buffer between " + i + " and " + fArr.length + " to perform a transform.");
        }
        float[] fArr2 = new float[this.timeSize];
        System.arraycopy(fArr, i, fArr2, 0, fArr2.length);
        forward(fArr2);
    }

    public void inverse(float[] fArr, float[] fArr2, float[] fArr3) {
        setComplex(fArr, fArr2);
        inverse(fArr3);
    }

    public float[] getSpectrum() {
        return this.spectrum;
    }

    public float[] getRealPart() {
        return this.real;
    }

    public float[] getImaginaryPart() {
        return this.imag;
    }
}
