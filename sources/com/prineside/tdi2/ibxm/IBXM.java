package com.prineside.tdi2.ibxm;

import com.badlogic.gdx.utils.FloatArray;
import com.prineside.tdi2.utils.MovingAverageFloat;
import java.util.Arrays;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/IBXM.class */
public class IBXM {
    public static final String VERSION = "a73 (c)2017 mumart@gmail.com";

    /* renamed from: a, reason: collision with root package name */
    private final Module f2204a;
    private final boolean[] c;
    private final byte[][] d;
    public final Channel[] channels;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    public int lastSeqPos;

    /* renamed from: b, reason: collision with root package name */
    private final int[] f2205b = new int[128];
    public int interpolation = 1;
    private final GlobalVol o = new GlobalVol();
    private final Note p = new Note();

    public IBXM(Module module, int i) {
        this.f2204a = module;
        setSampleRate(i);
        this.d = new byte[module.sequenceLength][0];
        this.channels = new Channel[module.numChannels];
        this.c = new boolean[module.numChannels];
        for (int i2 = 0; i2 < module.numChannels; i2++) {
            this.channels[i2] = new Channel(module, i2, this.o);
        }
        setSequencePos(0);
    }

    public Module getModule() {
        return this.f2204a;
    }

    public int getSampleRate() {
        return this.e;
    }

    public void setSampleRate(int i) {
        if (i < 8000 || i > 128000) {
            throw new IllegalArgumentException("Unsupported sampling rate!");
        }
        this.e = i;
    }

    public void setInterpolation(int i) {
        this.interpolation = i;
    }

    public int getMixBufferLength() {
        return (a(32, 128000) + 65) << 2;
    }

    public void setMuted(int i, boolean z) {
        if (i < 0) {
            for (int i2 = 0; i2 < this.f2204a.numChannels; i2++) {
                this.c[i2] = z;
            }
            return;
        }
        if (i < this.f2204a.numChannels) {
            this.c[i] = z;
        }
    }

    public GlobalVol getGlobalVol() {
        return this.o;
    }

    public int getRow() {
        return this.h;
    }

    public int getSequencePos() {
        return this.f;
    }

    public void setSequencePosApplyEffects(int i) {
        setSequencePos(0);
        while (i != this.f) {
            a();
        }
    }

    public void setSequencePos(int i) {
        if (i >= this.f2204a.sequenceLength) {
            i = 0;
        }
        this.g = i;
        this.i = 0;
        this.j = 1;
        this.o.volume = this.f2204a.defaultGVol;
        this.k = this.f2204a.defaultSpeed > 0 ? this.f2204a.defaultSpeed : 6;
        this.l = this.f2204a.defaultTempo > 0 ? this.f2204a.defaultTempo : 125;
        this.n = -1;
        this.m = -1;
        for (int i2 = 0; i2 < this.d.length; i2++) {
            int i3 = this.f2204a.sequence[i2];
            int i4 = i3 < this.f2204a.numPatterns ? this.f2204a.patterns[i3].numRows : 0;
            if (this.d[i2].length < i4) {
                this.d[i2] = new byte[i4];
            }
            Arrays.fill(this.d[i2], (byte) 0);
        }
        for (int i5 = 0; i5 < this.f2204a.numChannels; i5++) {
            this.channels[i5].reset();
        }
        for (int i6 = 0; i6 < 128; i6++) {
            this.f2205b[i6] = 0;
        }
        a();
    }

    public int calculateSongDuration() {
        int i = 0;
        setSequencePos(0);
        boolean z = false;
        while (!z) {
            i += a(this.l, this.e);
            z = a();
        }
        setSequencePos(0);
        return i;
    }

    public int seek(int i) {
        setSequencePos(0);
        int i2 = 0;
        int a2 = a(this.l, this.e);
        while (true) {
            int i3 = a2;
            if (i - i2 >= i3) {
                for (int i4 = 0; i4 < this.f2204a.numChannels; i4++) {
                    this.channels[i4].updateSampleIdx(i3 << 1, this.e << 1);
                }
                i2 += i3;
                a();
                a2 = a(this.l, this.e);
            } else {
                return i2;
            }
        }
    }

    public void seekSequencePos(int i, int i2) {
        setSequencePos(0);
        if (i < 0 || i >= this.f2204a.sequenceLength) {
            i = 0;
        }
        if (i2 >= this.f2204a.patterns[this.f2204a.sequence[i]].numRows) {
            i2 = 0;
        }
        do {
            if (this.f < i || this.h < i2) {
                int a2 = a(this.l, this.e);
                for (int i3 = 0; i3 < this.f2204a.numChannels; i3++) {
                    this.channels[i3].updateSampleIdx(a2 << 1, this.e << 1);
                }
            } else {
                return;
            }
        } while (!a());
        setSequencePos(i);
    }

    public FloatArray getAmplitudes(int i) {
        setSequencePos(0);
        int[] iArr = new int[getMixBufferLength()];
        int i2 = 0;
        FloatArray floatArray = new FloatArray(512);
        MovingAverageFloat movingAverageFloat = new MovingAverageFloat(i);
        MovingAverageFloat movingAverageFloat2 = new MovingAverageFloat(i);
        int i3 = 0;
        while (true) {
            int audio = getAudio(iArr) << 1;
            for (int i4 = 0; i4 < audio; i4 += 2) {
                int i5 = iArr[i4];
                int i6 = iArr[i4 + 1];
                if (i5 > 32767) {
                    i5 = 32767;
                }
                if (i5 < -32768) {
                    i5 = -32768;
                }
                if (i6 > 32767) {
                    i6 = 32767;
                }
                if (i6 < -32768) {
                    i6 = -32768;
                }
                movingAverageFloat.push(Math.abs(i5 / 32768.0f));
                movingAverageFloat2.push(Math.abs(i6 / 32768.0f));
                i3++;
                if (i3 == i) {
                    i3 = 0;
                    floatArray.add(movingAverageFloat.getAverage());
                    floatArray.add(movingAverageFloat2.getAverage());
                    movingAverageFloat.reset();
                    movingAverageFloat2.reset();
                }
            }
            int sequencePos = getSequencePos();
            if (sequencePos >= i2) {
                i2 = sequencePos;
            } else {
                floatArray.add(movingAverageFloat.getAverage());
                floatArray.add(movingAverageFloat2.getAverage());
                return floatArray;
            }
        }
    }

    public int getAudio(int[] iArr) {
        int a2 = a(this.l, this.e);
        int i = (a2 + 65) << 2;
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = 0;
        }
        for (int i3 = 0; i3 < this.f2204a.numChannels; i3++) {
            Channel channel = this.channels[i3];
            if (!this.c[i3]) {
                channel.resample(iArr, 0, (a2 + 65) << 1, this.e << 1, this.interpolation);
            }
            channel.updateSampleIdx(a2 << 1, this.e << 1);
        }
        b(iArr, a2 + 64);
        a(iArr, a2);
        a();
        return a2;
    }

    private static int a(int i, int i2) {
        return (i2 * 5) / (i << 1);
    }

    private void a(int[] iArr, int i) {
        int i2 = 524288 / this.e;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 < 256) {
                int i6 = 256 - i5;
                iArr[i3] = ((iArr[i3] * i5) + (this.f2205b[i3] * i6)) >> 8;
                iArr[i3 + 1] = ((iArr[i3 + 1] * i5) + (this.f2205b[i3 + 1] * i6)) >> 8;
                i3 += 2;
                i4 = i5 + i2;
            } else {
                System.arraycopy(iArr, i << 1, this.f2205b, 0, 128);
                return;
            }
        }
    }

    private static void b(int[] iArr, int i) {
        int i2 = i << 1;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4 += 2) {
            iArr[i4] = (iArr[i3] >> 2) + (iArr[i3 + 2] >> 1) + (iArr[i3 + 4] >> 2);
            iArr[i4 + 1] = (iArr[i3 + 1] >> 2) + (iArr[i3 + 3] >> 1) + (iArr[i3 + 5] >> 2);
            i3 += 4;
        }
    }

    private boolean a() {
        int i = this.j - 1;
        this.j = i;
        if (i <= 0) {
            this.j = this.k;
            b();
        } else {
            for (int i2 = 0; i2 < this.f2204a.numChannels; i2++) {
                this.channels[i2].tick();
            }
        }
        return this.d[this.f][this.h] > 1;
    }

    private void b() {
        if (this.i < 0) {
            this.g = this.f + 1;
            this.i = 0;
        }
        if (this.g >= 0) {
            if (this.g >= this.f2204a.sequenceLength) {
                this.i = 0;
                this.g = 0;
            }
            while (this.f2204a.sequence[this.g] >= this.f2204a.numPatterns) {
                this.g++;
                if (this.g >= this.f2204a.sequenceLength) {
                    this.i = 0;
                    this.g = 0;
                }
            }
            this.f = this.g;
            for (int i = 0; i < this.f2204a.numChannels; i++) {
                this.channels[i].plRow = 0;
            }
            this.g = -1;
        }
        Pattern pattern = this.f2204a.patterns[this.f2204a.sequence[this.f]];
        this.h = this.i;
        if (this.h >= pattern.numRows) {
            this.h = 0;
        }
        byte b2 = this.d[this.f][this.h];
        if (this.m < 0 && b2 < Byte.MAX_VALUE) {
            this.d[this.f][this.h] = (byte) (b2 + 1);
        }
        this.i = this.h + 1;
        if (this.i >= pattern.numRows) {
            this.i = -1;
        }
        int i2 = this.h * this.f2204a.numChannels;
        for (int i3 = 0; i3 < this.f2204a.numChannels; i3++) {
            Channel channel = this.channels[i3];
            pattern.getNote(i2 + i3, this.p);
            if (this.p.effect == 14) {
                this.p.effect = 112 | (this.p.param >> 4);
                this.p.param &= 15;
            }
            if (this.p.effect == 147) {
                this.p.effect = 240 | (this.p.param >> 4);
                this.p.param &= 15;
            }
            if (this.p.effect == 0 && this.p.param > 0) {
                this.p.effect = 138;
            }
            channel.row(this.p);
            switch (this.p.effect) {
                case 11:
                case 130:
                    if (this.m < 0) {
                        this.g = this.p.param;
                        this.i = 0;
                        break;
                    } else {
                        break;
                    }
                case 13:
                case 131:
                    if (this.m >= 0) {
                        break;
                    } else {
                        if (this.g < 0) {
                            this.g = this.f + 1;
                        }
                        this.i = ((this.p.param >> 4) * 10) + (this.p.param & 15);
                        break;
                    }
                case 15:
                    if (this.p.param <= 0) {
                        break;
                    } else if (this.p.param < 32) {
                        int i4 = this.p.param;
                        this.k = i4;
                        this.j = i4;
                        break;
                    } else {
                        this.l = this.p.param;
                        break;
                    }
                case 118:
                case User32.VK_ZOOM /* 251 */:
                    if (this.p.param == 0) {
                        channel.plRow = this.h;
                    }
                    if (channel.plRow < this.h && this.g < 0) {
                        if (this.m < 0) {
                            this.m = this.p.param;
                            this.n = i3;
                        }
                        if (this.n != i3) {
                            break;
                        } else {
                            if (this.m == 0) {
                                channel.plRow = this.h + 1;
                            } else {
                                this.i = channel.plRow;
                            }
                            this.m--;
                            break;
                        }
                    }
                    break;
                case 126:
                case 254:
                    this.j = this.k + (this.k * this.p.param);
                    break;
                case 129:
                    if (this.p.param > 0) {
                        int i5 = this.p.param;
                        this.k = i5;
                        this.j = i5;
                        break;
                    } else {
                        break;
                    }
                case 148:
                    if (this.p.param > 32) {
                        this.l = this.p.param;
                        break;
                    } else {
                        break;
                    }
            }
        }
    }
}
