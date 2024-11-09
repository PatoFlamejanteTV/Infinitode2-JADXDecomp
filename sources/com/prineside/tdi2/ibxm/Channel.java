package com.prineside.tdi2.ibxm;

import com.vladsch.flexmark.util.sequence.builder.tree.SegmentTree;
import org.lwjgl.opengl.ARBImaging;
import org.lwjgl.opengl.CGL;
import org.lwjgl.opengl.INTELPerformanceQuery;
import org.lwjgl.opengl.NVBlendEquationAdvanced;
import org.lwjgl.opengl.NVFence;
import org.lwjgl.opengl.NVTextureShader;
import org.lwjgl.opengl.NVViewportSwizzle;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/Channel.class */
public class Channel {
    public static final int NEAREST = 0;
    public static final int LINEAR = 1;
    public static final int SINC = 2;

    /* renamed from: a, reason: collision with root package name */
    private static int[] f2200a = {32768, ARBImaging.GL_COLOR_MATRIX_STACK_DEPTH, 33125, 33305, 33486, 33667, 33850, NVFence.GL_ALL_COMPLETED_NV, 34219, 34405, NVTextureShader.GL_TEXTURE_MAG_SIZE_NV, 34779, 34968, 35158, 35349, 35541, 35734, 35928, 36123, 36319, 36516, 36715, 36914, 37114, 37316, NVBlendEquationAdvanced.GL_SRC_ATOP_NV, NVViewportSwizzle.GL_VIEWPORT_SWIZZLE_Z_NV, 37927, INTELPerformanceQuery.GL_PERFQUERY_COUNTER_TIMESTAMP_INTEL, 38340, 38548, 38757, 38968, 39180, 39392, 39606, 39821, 40037, 40255, 40473, 40693, 40914, 41136, 41360, 41584, 41810, 42037, 42265, 42495, 42726, 42958, 43191, 43425, 43661, 43898, 44137, 44376, 44617, 44859, 45103, 45348, 45594, 45842, 46091, 46341, 46593, 46846, 47100, 47356, 47613, 47871, 48131, 48393, 48655, 48920, 49185, 49452, 49721, 49991, 50262, 50535, 50810, 51085, 51363, 51642, 51922, 52204, 52488, 52773, 53059, 53347, 53637, 53928, 54221, 54515, 54811, 55109, 55408, 55709, 56012, 56316, 56622, 56929, 57238, 57549, 57861, 58176, 58491, 58809, 59128, 59449, 59772, 60097, 60423, 60751, 61081, 61413, 61746, 62081, 62419, 62757, 63098, 63441, 63785, 64132, 64480, 64830, 65182, 65536};

    /* renamed from: b, reason: collision with root package name */
    private static final short[] f2201b = {0, 24, 49, 74, 97, 120, 141, 161, 180, 197, 212, 224, 235, 244, 250, 253, 255, 253, 250, 244, 235, 224, 212, 197, 180, 161, 141, 120, 97, 74, 49, 24};
    private final Module c;
    private final GlobalVol d;
    private Instrument e = new Instrument();
    private Sample f = this.e.samples[0];
    private boolean g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    public int plRow;

    public Channel(Module module, int i, GlobalVol globalVol) {
        this.c = module;
        this.ae = i;
        this.d = globalVol;
        this.t = module.defaultPanning[i];
        this.af = (i + 1) * 11259375;
    }

    public void reset() {
        this.g = false;
        this.ad = 0;
        this.ac = 0;
        this.ab = 0;
        this.aa = 0;
        this.Z = 0;
        this.Y = 0;
        this.X = 0;
        this.W = 0;
        this.V = 0;
        this.U = 0;
        this.T = 0;
        this.S = 0;
        this.R = 0;
        this.Q = 0;
        this.P = 0;
        this.O = 0;
        this.N = 0;
        this.M = 0;
        this.L = 0;
        this.K = 0;
        this.J = 0;
        this.I = 0;
        this.H = 0;
        this.G = 0;
        this.F = 0;
        this.E = 0;
        this.D = 0;
        this.C = 0;
        this.B = 0;
        this.A = 0;
        this.z = 0;
        this.y = 0;
        this.x = 0;
        this.w = 0;
        this.v = 0;
        this.u = 0;
        this.s = 0;
        this.r = 0;
        this.q = 0;
        this.p = 0;
        this.o = 0;
        this.n = 0;
        this.m = 0;
        this.l = 0;
        this.k = 0;
        this.j = 0;
        this.i = 0;
        this.h = 0;
        this.t = this.c.defaultPanning[this.ae];
        this.f = this.e.samples[0];
        this.af = (this.ae + 1) * 11259375;
    }

    public void resample(int[] iArr, int i, int i2, int i3, int i4) {
        if (this.q <= 0) {
            return;
        }
        int i5 = (this.q * (255 - this.r)) >> 8;
        int i6 = (this.q * this.r) >> 8;
        int i7 = (this.p << 12) / (i3 >> 3);
        switch (i4) {
            case 0:
                this.f.resampleNearest(this.n, this.o, i7, i5, i6, iArr, i, i2);
                return;
            case 1:
            default:
                this.f.resampleLinear(this.n, this.o, i7, i5, i6, iArr, i, i2);
                return;
            case 2:
                this.f.resampleSinc(this.n, this.o, i7, i5, i6, iArr, i, i2);
                return;
        }
    }

    public void updateSampleIdx(int i, int i2) {
        this.o += ((this.p << 12) / (i2 >> 3)) * i;
        this.n = this.f.normaliseSampleIdx(this.n + (this.o >> 15));
        this.o &= 32767;
    }

    public void row(Note note) {
        this.h = note.key;
        this.i = note.instrument;
        this.j = note.volume;
        this.k = note.effect;
        this.l = note.param;
        this.z++;
        this.A = 0;
        this.ad = 0;
        this.ab = 0;
        this.ac = 0;
        if ((this.k != 125 && this.k != 253) || this.l <= 0) {
            j();
        }
        switch (this.k) {
            case 1:
            case 134:
                if (this.l > 0) {
                    this.C = this.l;
                }
                a(this.C);
                break;
            case 2:
            case 133:
                if (this.l > 0) {
                    this.D = this.l;
                }
                b(this.D);
                break;
            case 3:
            case 135:
                if (this.l > 0) {
                    this.E = this.l;
                    break;
                }
                break;
            case 4:
            case 136:
                if ((this.l >> 4) > 0) {
                    this.V = this.l >> 4;
                }
                if ((this.l & 15) > 0) {
                    this.W = this.l & 15;
                }
                a(false);
                break;
            case 5:
            case 140:
                if (this.l > 0) {
                    this.K = this.l;
                }
                c();
                break;
            case 6:
            case 139:
                if (this.l > 0) {
                    this.K = this.l;
                }
                a(false);
                c();
                break;
            case 7:
            case 146:
                if ((this.l >> 4) > 0) {
                    this.Z = this.l >> 4;
                }
                if ((this.l & 15) > 0) {
                    this.aa = this.l & 15;
                }
                e();
                break;
            case 8:
                this.t = this.l;
                break;
            case 10:
            case 132:
                if (this.l > 0) {
                    this.K = this.l;
                }
                c();
                break;
            case 12:
                this.s = this.l >= 64 ? 64 : this.l & 63;
                break;
            case 16:
            case 150:
                this.d.volume = this.l >= 64 ? 64 : this.l & 63;
                break;
            case 17:
                if (this.l > 0) {
                    this.L = this.l;
                    break;
                }
                break;
            case 20:
                this.g = false;
                break;
            case 21:
                int i = this.l & 255;
                this.w = i;
                this.v = i;
                break;
            case 25:
                if (this.l > 0) {
                    this.M = this.l;
                    break;
                }
                break;
            case 27:
            case 145:
                if ((this.l >> 4) > 0) {
                    this.P = this.l >> 4;
                }
                if ((this.l & 15) > 0) {
                    this.Q = this.l & 15;
                }
                g();
                break;
            case 29:
            case 137:
                if ((this.l >> 4) > 0) {
                    this.R = this.l >> 4;
                }
                if ((this.l & 15) > 0) {
                    this.S = this.l & 15;
                }
                f();
                break;
            case 33:
                if (this.l > 0) {
                    this.I = this.l;
                }
                switch (this.I & User32.VK_OEM_ATTN) {
                    case 16:
                        a(224 | (this.I & 15));
                        break;
                    case 32:
                        b(224 | (this.I & 15));
                        break;
                }
            case 113:
                if (this.l > 0) {
                    this.G = this.l;
                }
                a(240 | (this.G & 15));
                break;
            case 114:
                if (this.l > 0) {
                    this.H = this.l;
                }
                b(240 | (this.H & 15));
                break;
            case 116:
            case 243:
                if (this.l < 8) {
                    this.T = this.l;
                    break;
                }
                break;
            case 119:
            case User32.VK_OEM_ENLW /* 244 */:
                if (this.l < 8) {
                    this.X = this.l;
                    break;
                }
                break;
            case 122:
                if (this.l > 0) {
                    this.N = this.l;
                }
                this.s += this.N;
                if (this.s > 64) {
                    this.s = 64;
                    break;
                }
                break;
            case 123:
                if (this.l > 0) {
                    this.O = this.l;
                }
                this.s -= this.O;
                if (this.s < 0) {
                    this.s = 0;
                    break;
                }
                break;
            case 124:
            case User32.VK_NONAME /* 252 */:
                if (this.l <= 0) {
                    this.s = 0;
                    break;
                }
                break;
            case 138:
                if (this.l > 0) {
                    this.J = this.l;
                    break;
                }
                break;
            case 149:
                if ((this.l >> 4) > 0) {
                    this.V = this.l >> 4;
                }
                if ((this.l & 15) > 0) {
                    this.W = this.l & 15;
                }
                a(true);
                break;
            case User32.VK_EXSEL /* 248 */:
                this.t = this.l * 17;
                break;
        }
        b();
        h();
        i();
        a();
    }

    public void tick() {
        this.ac = 0;
        this.A++;
        this.z++;
        if (this.k != 125 || this.A > this.l) {
            switch (this.j & User32.VK_OEM_ATTN) {
                case 96:
                    this.s -= this.j & 15;
                    if (this.s < 0) {
                        this.s = 0;
                        break;
                    }
                    break;
                case 112:
                    this.s += this.j & 15;
                    if (this.s > 64) {
                        this.s = 64;
                        break;
                    }
                    break;
                case 176:
                    this.U += this.V;
                    a(false);
                    break;
                case 208:
                    this.t -= this.j & 15;
                    if (this.t < 0) {
                        this.t = 0;
                        break;
                    }
                    break;
                case CGL.kCGLCPDispatchTableSize /* 224 */:
                    this.t += this.j & 15;
                    if (this.t > 255) {
                        this.t = 255;
                        break;
                    }
                    break;
                case User32.VK_OEM_ATTN /* 240 */:
                    d();
                    break;
            }
        }
        switch (this.k) {
            case 1:
            case 134:
                a(this.C);
                break;
            case 2:
            case 133:
                b(this.D);
                break;
            case 3:
            case 135:
                d();
                break;
            case 4:
            case 136:
                this.U += this.V;
                a(false);
                break;
            case 5:
            case 140:
                d();
                c();
                break;
            case 6:
            case 139:
                this.U += this.V;
                a(false);
                c();
                break;
            case 7:
            case 146:
                this.Y += this.Z;
                e();
                break;
            case 10:
            case 132:
                c();
                break;
            case 17:
                this.d.volume += (this.L >> 4) - (this.L & 15);
                if (this.d.volume < 0) {
                    this.d.volume = 0;
                }
                if (this.d.volume > 64) {
                    this.d.volume = 64;
                    break;
                }
                break;
            case 25:
                this.t += (this.M >> 4) - (this.M & 15);
                if (this.t < 0) {
                    this.t = 0;
                }
                if (this.t > 255) {
                    this.t = 255;
                    break;
                }
                break;
            case 27:
            case 145:
                g();
                break;
            case 29:
            case 137:
                f();
                break;
            case 121:
                if (this.A >= this.l) {
                    this.A = 0;
                    this.o = 0;
                    this.n = 0;
                    break;
                }
                break;
            case 124:
            case User32.VK_NONAME /* 252 */:
                if (this.l == this.A) {
                    this.s = 0;
                    break;
                }
                break;
            case 125:
            case User32.VK_PA1 /* 253 */:
                if (this.l == this.A) {
                    j();
                    break;
                }
                break;
            case 138:
                if (this.A > 2) {
                    this.A = 0;
                }
                if (this.A == 0) {
                    this.ad = 0;
                }
                if (this.A == 1) {
                    this.ad = this.J >> 4;
                }
                if (this.A == 2) {
                    this.ad = this.J & 15;
                    break;
                }
                break;
            case 149:
                this.U += this.V;
                a(true);
                break;
        }
        b();
        h();
        i();
        a();
    }

    private void a() {
        if (this.e.volumeEnvelope.enabled) {
            if (!this.g) {
                this.u -= this.e.volumeFadeOut;
                if (this.u < 0) {
                    this.u = 0;
                }
            }
            this.v = this.e.volumeEnvelope.nextTick(this.v, this.g);
        }
        if (this.e.panningEnvelope.enabled) {
            this.w = this.e.panningEnvelope.nextTick(this.w, this.g);
        }
    }

    private void b() {
        int i = this.e.vibratoDepth & 127;
        int i2 = i;
        if (i > 0) {
            int i3 = this.e.vibratoSweep & 127;
            int i4 = this.e.vibratoRate & 127;
            int i5 = this.e.vibratoType;
            if (this.B < i3) {
                i2 = (i2 * this.B) / i3;
            }
            this.ac += (a((this.B * i4) >> 2, i5 + 4) * i2) >> 8;
            this.B++;
        }
    }

    private void c() {
        int i = this.K >> 4;
        int i2 = this.K & 15;
        if (i2 == 15 && i > 0) {
            if (this.A == 0) {
                this.s += i;
            }
        } else if (i == 15 && i2 > 0) {
            if (this.A == 0) {
                this.s -= i2;
            }
        } else if (this.A > 0 || this.c.fastVolSlides) {
            this.s += i - i2;
        }
        if (this.s > 64) {
            this.s = 64;
        }
        if (this.s < 0) {
            this.s = 0;
        }
    }

    private void a(int i) {
        switch (i & User32.VK_OEM_ATTN) {
            case CGL.kCGLCPDispatchTableSize /* 224 */:
                if (this.A == 0) {
                    this.x -= i & 15;
                    break;
                }
                break;
            case User32.VK_OEM_ATTN /* 240 */:
                if (this.A == 0) {
                    this.x -= (i & 15) << 2;
                    break;
                }
                break;
            default:
                if (this.A > 0) {
                    this.x -= i << 2;
                    break;
                }
                break;
        }
        if (this.x < 0) {
            this.x = 0;
        }
    }

    private void b(int i) {
        if (this.x > 0) {
            switch (i & User32.VK_OEM_ATTN) {
                case CGL.kCGLCPDispatchTableSize /* 224 */:
                    if (this.A == 0) {
                        this.x += i & 15;
                        break;
                    }
                    break;
                case User32.VK_OEM_ATTN /* 240 */:
                    if (this.A == 0) {
                        this.x += (i & 15) << 2;
                        break;
                    }
                    break;
                default:
                    if (this.A > 0) {
                        this.x += i << 2;
                        break;
                    }
                    break;
            }
            if (this.x > 65535) {
                this.x = 65535;
            }
        }
    }

    private void d() {
        if (this.x > 0) {
            if (this.x < this.y) {
                this.x += this.E << 2;
                if (this.x > this.y) {
                    this.x = this.y;
                    return;
                }
                return;
            }
            this.x -= this.E << 2;
            if (this.x < this.y) {
                this.x = this.y;
            }
        }
    }

    private void a(boolean z) {
        this.ac = (a(this.U, this.T & 3) * this.W) >> (z ? 7 : 5);
    }

    private void e() {
        this.ab = (a(this.Y, this.X & 3) * this.aa) >> 6;
    }

    private int a(int i, int i2) {
        int i3;
        switch (i2) {
            case 1:
            case 7:
                i3 = 255 - (((i + 32) & 63) << 3);
                break;
            case 2:
            case 5:
                i3 = (i & 32) > 0 ? 255 : -255;
                break;
            case 3:
            case 8:
                i3 = (this.af >> 20) - 255;
                this.af = ((this.af * 65) + 17) & SegmentTree.MAX_VALUE;
                break;
            case 4:
            default:
                i3 = f2201b[i & 31];
                if ((i & 32) > 0) {
                    i3 = -i3;
                    break;
                }
                break;
            case 6:
                i3 = (((i + 32) & 63) << 3) - 255;
                break;
        }
        return i3;
    }

    private void f() {
        if (this.z >= this.R) {
            this.ab = -64;
        }
        if (this.z >= this.R + this.S) {
            this.z = 0;
            this.ab = 0;
        }
    }

    private void g() {
        if (this.z >= this.Q) {
            this.o = 0;
            this.n = 0;
            this.z = 0;
            switch (this.P) {
                case 1:
                    this.s--;
                    break;
                case 2:
                    this.s -= 2;
                    break;
                case 3:
                    this.s -= 4;
                    break;
                case 4:
                    this.s -= 8;
                    break;
                case 5:
                    this.s -= 16;
                    break;
                case 6:
                    this.s = (this.s << 1) / 3;
                    break;
                case 7:
                    this.s >>= 1;
                    break;
                case 9:
                    this.s++;
                    break;
                case 10:
                    this.s += 2;
                    break;
                case 11:
                    this.s += 4;
                    break;
                case 12:
                    this.s += 8;
                    break;
                case 13:
                    this.s += 16;
                    break;
                case 14:
                    this.s = (this.s * 3) / 2;
                    break;
                case 15:
                    this.s <<= 1;
                    break;
            }
            if (this.s < 0) {
                this.s = 0;
            }
            if (this.s > 64) {
                this.s = 64;
            }
        }
    }

    private void h() {
        int i = this.x + this.ac;
        if (this.c.linearPeriods) {
            int i2 = i - (this.ad << 6);
            int i3 = i2;
            if (i2 < 28 || i3 > 7680) {
                i3 = 7680;
            }
            this.p = ((this.c.c2Rate >> 4) * exp2(((4608 - i3) << 15) / 768)) >> 11;
            return;
        }
        if (i > 29021) {
            i = 29021;
        }
        int exp2 = (i << 15) / exp2((this.ad << 15) / 12);
        int i4 = exp2;
        if (exp2 < 28) {
            i4 = 29021;
        }
        this.p = (this.c.c2Rate * 1712) / i4;
    }

    private void i() {
        int i = this.g ? 64 : 0;
        if (this.e.volumeEnvelope.enabled) {
            i = this.e.volumeEnvelope.calculateAmpl(this.v);
        }
        int i2 = this.s + this.ab;
        int i3 = i2;
        if (i2 > 64) {
            i3 = 64;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        this.q = (((((((i3 * this.c.gain) << 15) >> 13) * this.u) >> 15) * this.d.volume) * i) >> 12;
        int i4 = 32;
        if (this.e.panningEnvelope.enabled) {
            i4 = this.e.panningEnvelope.calculateAmpl(this.w);
        }
        this.r = this.t + (((this.t < 128 ? this.t : 255 - this.t) * (i4 - 32)) >> 5);
    }

    private void j() {
        if (this.i > 0 && this.i <= this.c.numInstruments) {
            this.e = this.c.instruments[this.i];
            Sample sample = this.e.samples[this.e.keyToSample[this.h < 97 ? this.h : 0]];
            this.s = sample.volume >= 64 ? 64 : sample.volume & 63;
            if (sample.panning >= 0) {
                this.t = sample.panning & 255;
            }
            if (this.x > 0 && sample.looped()) {
                this.f = sample;
            }
            this.w = 0;
            this.v = 0;
            this.m = 0;
            this.u = 32768;
            this.g = true;
        }
        if (this.k == 9 || this.k == 143) {
            if (this.l > 0) {
                this.F = this.l;
            }
            this.m = this.F << 8;
        }
        if (this.j >= 16 && this.j < 96) {
            this.s = this.j < 80 ? this.j - 16 : 64;
        }
        switch (this.j & User32.VK_OEM_ATTN) {
            case 128:
                this.s -= this.j & 15;
                if (this.s < 0) {
                    this.s = 0;
                    break;
                }
                break;
            case 144:
                this.s += this.j & 15;
                if (this.s > 64) {
                    this.s = 64;
                    break;
                }
                break;
            case 160:
                if ((this.j & 15) > 0) {
                    this.V = this.j & 15;
                    break;
                }
                break;
            case 176:
                if ((this.j & 15) > 0) {
                    this.W = this.j & 15;
                }
                a(false);
                break;
            case 192:
                this.t = (this.j & 15) * 17;
                break;
            case User32.VK_OEM_ATTN /* 240 */:
                if ((this.j & 15) > 0) {
                    this.E = this.j & 15;
                    break;
                }
                break;
        }
        if (this.h > 0) {
            if (this.h > 96) {
                this.g = false;
                return;
            }
            boolean z = (this.j & User32.VK_OEM_ATTN) == 240 || this.k == 3 || this.k == 5 || this.k == 135 || this.k == 140;
            boolean z2 = z;
            if (!z) {
                this.f = this.e.samples[this.e.keyToSample[this.h]];
            }
            int i = this.f.fineTune;
            if (this.k == 117 || this.k == 242) {
                i = ((this.l & 15) << 4) - 128;
            }
            int i2 = this.h + this.f.relNote;
            int i3 = i2;
            if (i2 <= 0) {
                i3 = 1;
            }
            if (i3 > 120) {
                i3 = 120;
            }
            int i4 = (i3 << 6) + (i >> 1);
            if (this.c.linearPeriods) {
                this.y = 7744 - i4;
            } else {
                this.y = (29021 * exp2((i4 << 15) / (-768))) >> 15;
            }
            if (!z2) {
                this.x = this.y;
                this.n = this.m;
                this.o = 0;
                if (this.T < 4) {
                    this.U = 0;
                }
                if (this.X < 4) {
                    this.Y = 0;
                }
                this.B = 0;
                this.z = 0;
            }
        }
    }

    public static int exp2(int i) {
        int i2 = (i & 32767) >> 8;
        int i3 = f2200a[i2];
        return (((((f2200a[i2 + 1] - i3) * (i & 255)) >> 8) + i3) << 15) >> (15 - (i >> 15));
    }

    public static int log2(int i) {
        int i2 = 524288;
        int i3 = 524288;
        while (true) {
            int i4 = i3;
            if (i4 > 0) {
                if (exp2(i2 - i4) >= i) {
                    i2 -= i4;
                }
                i3 = i4 >> 1;
            } else {
                return i2;
            }
        }
    }
}
