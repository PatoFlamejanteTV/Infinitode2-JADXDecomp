package com.prineside.tdi2.managers.music;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.prineside.kryo.FixedInput;
import com.prineside.tdi2.managers.MusicManager;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/RecordedSpectrum.class */
public final class RecordedSpectrum {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2507a = TLog.forClass(RecordedSpectrum.class);

    /* renamed from: b, reason: collision with root package name */
    private final Array<MusicManager.FrequencyRange> f2508b;
    private final float c;
    private final float d;
    private final int e;
    private final float[] f;

    public RecordedSpectrum(byte[] bArr) {
        FixedInput fixedInput = new FixedInput(bArr);
        int readInt = fixedInput.readInt();
        f2507a.i("reading spectrum, frequency count: " + readInt, new Object[0]);
        this.f2508b = new Array<>(true, readInt, MusicManager.FrequencyRange.class);
        for (int i = 0; i < readInt; i++) {
            this.f2508b.add(new MusicManager.FrequencyRange(fixedInput.readFloat(), fixedInput.readFloat()));
        }
        this.c = fixedInput.readFloat();
        this.e = fixedInput.readInt();
        this.f = new float[(this.e << 1) * readInt];
        for (int i2 = 0; i2 < this.f.length; i2++) {
            this.f[i2] = fixedInput.readFloat();
        }
        this.d = (this.c * this.e) / 1000.0f;
        for (int i3 = 0; i3 < 500000; i3++) {
            getSpectrumValue(FastRandom.getFairInt(this.e), FastRandom.getFairInt(this.f2508b.size), FastRandom.getFairInt(2));
        }
    }

    public final float getSpectrumValue(int i, int i2, int i3) {
        return this.f[((i * this.f2508b.size) << 1) + (i2 << 1) + i3];
    }

    public final void generateSpectrum(float f, float[] fArr, float[] fArr2) {
        int clamp = MathUtils.clamp(MathUtils.floor(((f % this.d) / this.d) * this.e), 0, this.e - 1);
        float length = this.f2508b.size / fArr.length;
        for (int i = 0; i < fArr.length; i++) {
            int round = MathUtils.round(i * length);
            int i2 = round;
            if (round > this.f2508b.size - 1) {
                i2 = this.f2508b.size - 1;
            }
            fArr[i] = getSpectrumValue(clamp, i2, 0);
            fArr2[i] = getSpectrumValue(clamp, i2, 1);
        }
    }

    public static RecordedSpectrum fromFile(FileHandle fileHandle) {
        return new RecordedSpectrum(fileHandle.readBytes());
    }
}
