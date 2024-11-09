package com.b.a.a;

import com.b.a.a.f;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/b/a/a/aa.class */
public final class aa {
    private static final byte[] c = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8};

    /* renamed from: a, reason: collision with root package name */
    public static final StringBuilder f778a = new StringBuilder();
    private int[] d;
    private String e;
    private x f;

    /* renamed from: b, reason: collision with root package name */
    public static final aa f779b;

    /* loaded from: infinitode-2.jar:com/b/a/a/aa$a.class */
    public interface a {
        int a();
    }

    private aa() {
        a(f.b("ucase.icu"));
    }

    private final void a(ByteBuffer byteBuffer) {
        f.b(byteBuffer, 1665225541, new b((byte) 0));
        int i = byteBuffer.getInt();
        if (i < 16) {
            throw new IOException("indexes[0] too small in ucase.icu");
        }
        this.d = new int[i];
        this.d[0] = i;
        for (int i2 = 1; i2 < i; i2++) {
            this.d[i2] = byteBuffer.getInt();
        }
        this.f = x.b(byteBuffer);
        int i3 = this.d[2];
        int b2 = this.f.b();
        if (b2 > i3) {
            throw new IOException("ucase.icu: not enough bytes for the trie");
        }
        f.a(byteBuffer, i3 - b2);
        int i4 = this.d[3];
        if (i4 > 0) {
            this.e = f.a(byteBuffer, i4, 0);
        }
        int i5 = this.d[4];
        if (i5 > 0) {
            f.b(byteBuffer, i5, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/aa$b.class */
    public static final class b implements f.a {
        private b() {
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        @Override // com.b.a.a.f.a
        public final boolean a(byte[] bArr) {
            return bArr[0] == 3;
        }
    }

    private static final int a(int i) {
        return i >> 5;
    }

    private static final boolean b(int i) {
        return (i & 16) != 0;
    }

    static {
        try {
            f779b = new aa();
        } catch (IOException e) {
            throw new com.b.a.d.c(e);
        }
    }

    private static final boolean b(int i, int i2) {
        return (i & (1 << i2)) != 0;
    }

    private static final byte c(int i, int i2) {
        return c[i & ((1 << i2) - 1)];
    }

    private final long a(int i, int i2, int i3) {
        int c2;
        long charAt;
        if ((i & 256) == 0) {
            c2 = i3 + c(i, 7);
            charAt = this.e.charAt(c2);
        } else {
            c2 = i3 + (2 * c(i, 7)) + 1;
            charAt = (this.e.charAt(r0) << 16) | this.e.charAt(c2);
        }
        return charAt | (c2 << 32);
    }

    private final int b(int i, int i2, int i3) {
        int charAt;
        if ((i & 256) == 0) {
            charAt = this.e.charAt(i3 + c(i, i2));
        } else {
            int c2 = i3 + (2 * c(i, i2));
            charAt = (this.e.charAt(c2) << 16) | this.e.charAt(c2 + 1);
        }
        return charAt;
    }

    private int c(int i) {
        return h(this.f.a(i));
    }

    private int d(int i) {
        return i(this.f.a(i));
    }

    private int e(int i) {
        int a2 = this.f.a(i);
        if (b(a2)) {
            return (this.e.charAt(a(a2)) >> 7) & 96;
        }
        return a2 & 96;
    }

    private boolean f(int i) {
        return e(i) == 32;
    }

    private boolean g(int i) {
        return (this.f.a(i) & 8) != 0;
    }

    private final boolean a(a aVar, int i) {
        int d;
        if (aVar == null) {
            return false;
        }
        do {
            int a2 = aVar.a();
            if (a2 >= 0) {
                d = d(a2);
            } else {
                return false;
            }
        } while ((d & 4) != 0);
        if (d != 0) {
            return true;
        }
        return false;
    }

    private final boolean a(a aVar) {
        int e;
        if (aVar == null) {
            return false;
        }
        do {
            int a2 = aVar.a();
            if (a2 >= 0) {
                e = e(a2);
                if (e == 32) {
                    return true;
                }
            } else {
                return false;
            }
        } while (e == 96);
        return false;
    }

    private int a(int i, a aVar, Appendable appendable, int i2) {
        int i3 = i;
        int a2 = this.f.a(i);
        if (!b(a2)) {
            if (h(a2) >= 2) {
                i3 = i + j(a2);
            }
        } else {
            int a3 = a(a2);
            int i4 = a3 + 1;
            char charAt = this.e.charAt(a3);
            if ((charAt & 16384) != 0) {
                if (i == 304) {
                    try {
                        appendable.append("i̇");
                        return 2;
                    } catch (IOException e) {
                        throw new com.b.a.d.c(e);
                    }
                }
                if (i == 931 && !a(aVar, 1) && a(aVar, -1)) {
                    return 962;
                }
            } else if (b(charAt, 7)) {
                long a4 = a(charAt, 7, i4);
                int i5 = ((int) a4) & 15;
                if (i5 != 0) {
                    int i6 = ((int) (a4 >> 32)) + 1;
                    try {
                        appendable.append(this.e, i6, i6 + i5);
                        return i5;
                    } catch (IOException e2) {
                        throw new com.b.a.d.c(e2);
                    }
                }
            }
            if (b(charAt, 0)) {
                i3 = b(charAt, 0, i4);
            }
        }
        return i3 == i ? i3 ^ (-1) : i3;
    }

    private final int a(int i, a aVar, Appendable appendable, int i2, boolean z) {
        int i3;
        int i4;
        int i5 = i;
        int a2 = this.f.a(i);
        if (!b(a2)) {
            if (h(a2) == 1) {
                i5 = i + j(a2);
            }
        } else {
            int a3 = a(a2);
            int i6 = a3 + 1;
            char charAt = this.e.charAt(a3);
            if ((charAt & 16384) != 0) {
                if (i2 == 2 && i == 105) {
                    return 304;
                }
                if (i2 == 3 && i == 775 && a(aVar)) {
                    return 0;
                }
            } else if (b(charAt, 7)) {
                long a4 = a(charAt, 7, i6);
                int i7 = ((int) a4) & 65535;
                int i8 = ((int) (a4 >> 32)) + 1 + (i7 & 15);
                int i9 = i7 >> 4;
                int i10 = i8 + (i9 & 15);
                int i11 = i9 >> 4;
                if (z) {
                    i3 = i11 & 15;
                } else {
                    i10 += i11 & 15;
                    i3 = (i11 >> 4) & 15;
                }
                if (i3 != 0) {
                    try {
                        int i12 = i10;
                        appendable.append(this.e, i12, i12 + i3);
                        return i3;
                    } catch (IOException e) {
                        throw new com.b.a.d.c(e);
                    }
                }
            }
            if (!z && b(charAt, 3)) {
                i4 = 3;
            } else if (b(charAt, 2)) {
                i4 = 2;
            } else {
                return i ^ (-1);
            }
            i5 = b(charAt, i4, i6);
        }
        return i5 == i ? i5 ^ (-1) : i5;
    }

    private int b(int i, a aVar, Appendable appendable, int i2) {
        return a(i, aVar, appendable, 1, true);
    }

    private int c(int i, a aVar, Appendable appendable, int i2) {
        return a(i, aVar, appendable, 1, false);
    }

    public final int a(int i, Appendable appendable, int i2) {
        int i3;
        int i4 = i;
        int a2 = this.f.a(i);
        if (!b(a2)) {
            if (h(a2) >= 2) {
                i4 = i + j(a2);
            }
        } else {
            int a3 = a(a2);
            int i5 = a3 + 1;
            char charAt = this.e.charAt(a3);
            if ((charAt & 32768) != 0) {
                if ((i2 & 255) == 0) {
                    if (i == 73) {
                        return 105;
                    }
                    if (i == 304) {
                        try {
                            appendable.append("i̇");
                            return 2;
                        } catch (IOException e) {
                            throw new com.b.a.d.c(e);
                        }
                    }
                } else {
                    if (i == 73) {
                        return 305;
                    }
                    if (i == 304) {
                        return 105;
                    }
                }
            } else if (b(charAt, 7)) {
                long a4 = a(charAt, 7, i5);
                int i6 = ((int) a4) & 65535;
                int i7 = ((int) (a4 >> 32)) + 1 + (i6 & 15);
                int i8 = (i6 >> 4) & 15;
                if (i8 != 0) {
                    try {
                        appendable.append(this.e, i7, i7 + i8);
                        return i8;
                    } catch (IOException e2) {
                        throw new com.b.a.d.c(e2);
                    }
                }
            }
            if (b(charAt, 1)) {
                i3 = 1;
            } else if (b(charAt, 0)) {
                i3 = 0;
            } else {
                return i ^ (-1);
            }
            i4 = b(charAt, i3, i5);
        }
        return i4 == i ? i4 ^ (-1) : i4;
    }

    public final boolean a(int i, int i2) {
        switch (i2) {
            case 22:
                return 1 == c(i);
            case 23:
            case 24:
            case 25:
            case 26:
            case 28:
            case 29:
            case 31:
            case 32:
            case 33:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 54:
            default:
                return false;
            case 27:
                return f(i);
            case 30:
                return 2 == c(i);
            case 34:
                return g(i);
            case 49:
                return 0 != c(i);
            case 50:
                return (d(i) >> 2) != 0;
            case 51:
                f778a.setLength(0);
                return a(i, null, f778a, 1) >= 0;
            case 52:
                f778a.setLength(0);
                return b(i, null, f778a, 1) >= 0;
            case 53:
                f778a.setLength(0);
                return c(i, null, f778a, 1) >= 0;
            case 55:
                f778a.setLength(0);
                return a(i, null, f778a, 1) >= 0 || b(i, null, f778a, 1) >= 0 || c(i, null, f778a, 1) >= 0;
        }
    }

    private static final int h(int i) {
        return i & 3;
    }

    private static final int i(int i) {
        return i & 7;
    }

    private static final int j(int i) {
        return ((short) i) >> 7;
    }
}
