package b.a.a;

/* loaded from: infinitode-2.jar:b/a/a/g.class */
public final class g {

    /* renamed from: b, reason: collision with root package name */
    private int f9b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private boolean m;
    private int n;
    private int o;
    private byte[] p;
    private d r;
    private short s;
    private int t;
    private int u;

    /* renamed from: a, reason: collision with root package name */
    private static int[][] f8a = {new int[]{22050, 24000, 16000, 1}, new int[]{44100, 48000, 32000, 1}, new int[]{11025, 12000, 8000, 1}};
    private static final int[][][] v = {new int[]{new int[]{0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0}, new int[]{0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}, new int[]{0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}}, new int[]{new int[]{0, 32000, 64000, 96000, 128000, 160000, 192000, 224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000, 0}, new int[]{0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000, 0}, new int[]{0, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 0}}, new int[]{new int[]{0, 32000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0}, new int[]{0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}, new int[]{0, 8000, 16000, 24000, 32000, 40000, 48000, 56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}}};
    private static final String[][][] w = {new String[]{new String[]{"free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "176 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "forbidden"}, new String[]{"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden"}, new String[]{"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden"}}, new String[]{new String[]{"free format", "32 kbit/s", "64 kbit/s", "96 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "288 kbit/s", "320 kbit/s", "352 kbit/s", "384 kbit/s", "416 kbit/s", "448 kbit/s", "forbidden"}, new String[]{"free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "320 kbit/s", "384 kbit/s", "forbidden"}, new String[]{"free format", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "320 kbit/s", "forbidden"}}, new String[]{new String[]{"free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "176 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "forbidden"}, new String[]{"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden"}, new String[]{"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s", "forbidden"}}};
    private double[] l = {-1.0d, 384.0d, 1152.0d, 1152.0d};
    private byte q = b.f3a;

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(200);
        stringBuffer.append("Layer ");
        stringBuffer.append(p());
        stringBuffer.append(" frame ");
        stringBuffer.append(t());
        stringBuffer.append(' ');
        stringBuffer.append(u());
        if (!m()) {
            stringBuffer.append(" no");
        }
        stringBuffer.append(" checksums");
        stringBuffer.append(' ');
        stringBuffer.append(s());
        stringBuffer.append(',');
        stringBuffer.append(' ');
        stringBuffer.append(q());
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(b bVar, d[] dVarArr) {
        int a2;
        boolean z = false;
        do {
            a2 = bVar.a(this.q);
            if (this.q == b.f3a) {
                this.g = (a2 >>> 19) & 1;
                if (((a2 >>> 20) & 1) == 0) {
                    if (this.g == 0) {
                        this.g = 2;
                    } else {
                        throw b.b(256);
                    }
                }
                int i = (a2 >>> 10) & 3;
                this.i = i;
                if (i == 3) {
                    throw b.b(256);
                }
            }
            this.f9b = (4 - (a2 >>> 17)) & 3;
            this.c = (a2 >>> 16) & 1;
            this.d = (a2 >>> 12) & 15;
            this.e = (a2 >>> 9) & 1;
            this.h = (a2 >>> 6) & 3;
            this.f = (a2 >>> 4) & 3;
            if (this.h == 1) {
                this.k = (this.f << 2) + 4;
            } else {
                this.k = 0;
            }
            if (this.f9b == 1) {
                this.j = 32;
            } else {
                int i2 = this.d;
                if (this.h != 3) {
                    if (i2 == 4) {
                        i2 = 1;
                    } else {
                        i2 -= 4;
                    }
                }
                if (i2 == 1 || i2 == 2) {
                    if (this.i == 2) {
                        this.j = 12;
                    } else {
                        this.j = 8;
                    }
                } else if (this.i == 1 || (i2 >= 3 && i2 <= 5)) {
                    this.j = 27;
                } else {
                    this.j = 30;
                }
            }
            if (this.k > this.j) {
                this.k = this.j;
            }
            n();
            int c = bVar.c(this.t);
            if (this.t >= 0 && c != this.t) {
                throw b.b(261);
            }
            if (bVar.a((int) this.q)) {
                if (this.q == b.f3a) {
                    this.q = b.f4b;
                    bVar.e(a2 & (-521024));
                }
                z = true;
            } else {
                bVar.c();
            }
        } while (!z);
        bVar.e();
        if (this.c == 0) {
            this.s = (short) bVar.d(16);
            if (this.r == null) {
                this.r = new d();
            }
            this.r.a(a2, 16);
            dVarArr[0] = this.r;
            return;
        }
        dVarArr[0] = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(byte[] bArr) {
        int i;
        byte[] bArr2 = new byte[4];
        if (this.g == 1) {
            if (this.h == 3) {
                i = 17;
            } else {
                i = 32;
            }
        } else if (this.h == 3) {
            i = 9;
        } else {
            i = 17;
        }
        try {
            System.arraycopy(bArr, i, bArr2, 0, 4);
            if ("Xing".equals(new String(bArr2))) {
                this.m = true;
                this.n = -1;
                this.o = -1;
                this.p = new byte[100];
                byte[] bArr3 = new byte[4];
                System.arraycopy(bArr, i + 4, bArr3, 0, 4);
                int i2 = 8;
                if ((bArr3[3] & 1) != 0) {
                    System.arraycopy(bArr, i + 8, bArr2, 0, 4);
                    this.n = ((bArr2[0] << 24) & (-16777216)) | ((bArr2[1] << 16) & 16711680) | ((bArr2[2] << 8) & 65280) | (bArr2[3] & 255);
                    i2 = 8 + 4;
                }
                if ((bArr3[3] & 2) != 0) {
                    System.arraycopy(bArr, i + i2, bArr2, 0, 4);
                    this.o = ((bArr2[0] << 24) & (-16777216)) | ((bArr2[1] << 16) & 16711680) | ((bArr2[2] << 8) & 65280) | (bArr2[3] & 255);
                    i2 += 4;
                }
                if ((bArr3[3] & 4) != 0) {
                    System.arraycopy(bArr, i + i2, this.p, 0, this.p.length);
                    i2 += this.p.length;
                }
                if ((bArr3[3] & 8) != 0) {
                    System.arraycopy(bArr, i + i2, bArr2, 0, 4);
                }
            }
            try {
                System.arraycopy(bArr, 32, bArr2, 0, 4);
                if ("VBRI".equals(new String(bArr2))) {
                    this.m = true;
                    this.n = -1;
                    this.o = -1;
                    this.p = new byte[100];
                    System.arraycopy(bArr, 42, bArr2, 0, 4);
                    this.o = ((bArr2[0] << 24) & (-16777216)) | ((bArr2[1] << 16) & 16711680) | ((bArr2[2] << 8) & 65280) | (bArr2[3] & 255);
                    System.arraycopy(bArr, 46, bArr2, 0, 4);
                    this.n = ((bArr2[0] << 24) & (-16777216)) | ((bArr2[1] << 16) & 16711680) | ((bArr2[2] << 8) & 65280) | (bArr2[3] & 255);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new c("VBRIVBRHeader Corrupted", e);
            }
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new c("XingVBRHeader Corrupted", e2);
        }
    }

    public final int a() {
        return this.g;
    }

    public final int b() {
        return this.f9b;
    }

    public final int c() {
        return this.d;
    }

    public final int d() {
        return this.i;
    }

    public final int e() {
        return f8a[this.g][this.i];
    }

    public final int f() {
        return this.h;
    }

    private boolean m() {
        if (this.c == 0) {
            return true;
        }
        return false;
    }

    public final boolean g() {
        return this.s == this.r.a();
    }

    public final int h() {
        return this.u;
    }

    public final int i() {
        return this.f;
    }

    private int n() {
        if (this.f9b == 1) {
            this.t = (12 * v[this.g][0][this.d]) / f8a[this.g][this.i];
            if (this.e != 0) {
                this.t++;
            }
            this.t <<= 2;
        } else {
            this.t = (144 * v[this.g][this.f9b - 1][this.d]) / f8a[this.g][this.i];
            if (this.g == 0 || this.g == 2) {
                this.t >>= 1;
            }
            if (this.e != 0) {
                this.t++;
            }
            if (this.f9b == 3) {
                if (this.g == 1) {
                    this.u = ((this.t - (this.h == 3 ? 17 : 32)) - (this.c != 0 ? 0 : 2)) - 4;
                } else {
                    this.u = ((this.t - (this.h == 3 ? 9 : 17)) - (this.c != 0 ? 0 : 2)) - 4;
                }
                this.t -= 4;
                return this.t;
            }
        }
        this.u = 0;
        this.t -= 4;
        return this.t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private float o() {
        if (!this.m) {
            return new float[]{new float[]{8.707483f, 8.0f, 12.0f}, new float[]{26.12245f, 24.0f, 36.0f}, new float[]{26.12245f, 24.0f, 36.0f}}[this.f9b - 1][this.i];
        }
        double e = this.l[b()] / e();
        if (this.g == 0 || this.g == 2) {
            e /= 2.0d;
        }
        return (float) (e * 1000.0d);
    }

    private String p() {
        switch (this.f9b) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            default:
                return null;
        }
    }

    private String q() {
        if (this.m) {
            return Integer.toString(r() / 1000) + " kb/s";
        }
        return w[this.g][this.f9b - 1][this.d];
    }

    private int r() {
        if (this.m) {
            return ((int) ((this.o << 3) / (o() * this.n))) * 1000;
        }
        return v[this.g][this.f9b - 1][this.d];
    }

    private String s() {
        switch (this.i) {
            case 0:
                if (this.g == 1) {
                    return "44.1 kHz";
                }
                if (this.g == 0) {
                    return "22.05 kHz";
                }
                return "11.025 kHz";
            case 1:
                if (this.g == 1) {
                    return "48 kHz";
                }
                if (this.g == 0) {
                    return "24 kHz";
                }
                return "12 kHz";
            case 2:
                if (this.g == 1) {
                    return "32 kHz";
                }
                if (this.g == 0) {
                    return "16 kHz";
                }
                return "8 kHz";
            default:
                return null;
        }
    }

    public final int j() {
        switch (this.i) {
            case 0:
                if (this.g == 1) {
                    return 44100;
                }
                if (this.g == 0) {
                    return 22050;
                }
                return 11025;
            case 1:
                if (this.g == 1) {
                    return 48000;
                }
                if (this.g == 0) {
                    return 24000;
                }
                return 12000;
            case 2:
                if (this.g == 1) {
                    return 32000;
                }
                if (this.g == 0) {
                    return 16000;
                }
                return 8000;
            default:
                return 0;
        }
    }

    private String t() {
        switch (this.h) {
            case 0:
                return "Stereo";
            case 1:
                return "Joint stereo";
            case 2:
                return "Dual channel";
            case 3:
                return "Single channel";
            default:
                return null;
        }
    }

    private String u() {
        switch (this.g) {
            case 0:
                return "MPEG-2 LSF";
            case 1:
                return "MPEG-1";
            case 2:
                return "MPEG-2.5 LSF";
            default:
                return null;
        }
    }

    public final int k() {
        return this.j;
    }

    public final int l() {
        return this.k;
    }
}
