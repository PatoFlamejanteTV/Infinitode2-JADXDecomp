package org.a.b.b;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/b/b/y.class */
public final class y {

    /* renamed from: a, reason: collision with root package name */
    private int f4252a = 0;

    /* renamed from: b, reason: collision with root package name */
    private int f4253b = 0;
    private List<Object> c = null;

    public y(String str, String str2) {
    }

    public y(String str, int i) {
        String.format(Locale.US, "%04x", Integer.valueOf(i));
    }

    public final List<Object> a(byte[] bArr, byte[][] bArr2, byte[][] bArr3) {
        return a(bArr, bArr2, bArr3, true);
    }

    private List<Object> a(byte[] bArr, byte[][] bArr2, byte[][] bArr3, boolean z) {
        int i;
        int i2;
        if (z) {
            this.f4252a = 0;
            this.f4253b = 0;
            this.c = new ArrayList();
        }
        b bVar = new b(bArr);
        boolean z2 = bArr3 != null && bArr3.length > 0;
        boolean z3 = bArr2 != null && bArr2.length > 0;
        while (bVar.a()) {
            int d = bVar.d();
            if (d == 10 && z2) {
                Integer num = (Integer) this.c.remove(this.c.size() - 1);
                int length = bArr3.length;
                if (length < 1240) {
                    i2 = 107;
                } else if (length < 33900) {
                    i2 = 1131;
                } else {
                    i2 = 32768;
                }
                int intValue = i2 + num.intValue();
                if (intValue < bArr3.length) {
                    a(bArr3[intValue], bArr2, bArr3, false);
                    Object obj = this.c.get(this.c.size() - 1);
                    if ((obj instanceof q) && ((q) obj).a().a()[0] == 11) {
                        this.c.remove(this.c.size() - 1);
                    }
                }
            } else if (d == 29 && z3) {
                Integer num2 = (Integer) this.c.remove(this.c.size() - 1);
                int length2 = bArr2.length;
                if (length2 < 1240) {
                    i = 107;
                } else if (length2 < 33900) {
                    i = 1131;
                } else {
                    i = 32768;
                }
                int intValue2 = i + num2.intValue();
                if (intValue2 < bArr2.length) {
                    a(bArr2[intValue2], bArr2, bArr3, false);
                    Object obj2 = this.c.get(this.c.size() - 1);
                    if ((obj2 instanceof q) && ((q) obj2).a().a()[0] == 11) {
                        this.c.remove(this.c.size() - 1);
                    }
                }
            } else if (d >= 0 && d <= 27) {
                this.c.add(a(d, bVar));
            } else if (d == 28) {
                this.c.add(b(d, bVar));
            } else if (d >= 29 && d <= 31) {
                this.c.add(a(d, bVar));
            } else if (d >= 32 && d <= 255) {
                this.c.add(b(d, bVar));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return this.c;
    }

    private q a(int i, b bVar) {
        if (i == 1 || i == 18) {
            this.f4252a += b().size() / 2;
        } else if (i == 3 || i == 19 || i == 20 || i == 23) {
            this.f4253b += b().size() / 2;
        }
        if (i == 12) {
            return new q(i, bVar.d());
        }
        if (i == 19 || i == 20) {
            int[] iArr = new int[1 + a()];
            iArr[0] = i;
            for (int i2 = 1; i2 < iArr.length; i2++) {
                iArr[i2] = bVar.d();
            }
            return new q(iArr);
        }
        return new q(i);
    }

    private static Number b(int i, b bVar) {
        if (i == 28) {
            return Integer.valueOf(bVar.e());
        }
        if (i >= 32 && i <= 246) {
            return Integer.valueOf(i - 139);
        }
        if (i >= 247 && i <= 250) {
            return Integer.valueOf(((i - User32.VK_CRSEL) << 8) + bVar.d() + 108);
        }
        if (i >= 251 && i <= 254) {
            return Integer.valueOf((((-(i - User32.VK_ZOOM)) << 8) - bVar.d()) - 108);
        }
        if (i == 255) {
            return Double.valueOf(bVar.e() + (bVar.f() / 65535.0d));
        }
        throw new IllegalArgumentException();
    }

    private int a() {
        int i = this.f4252a + this.f4253b;
        int i2 = i / 8;
        if (i % 8 > 0) {
            i2++;
        }
        return i2;
    }

    private List<Number> b() {
        ArrayList arrayList = new ArrayList();
        for (int size = this.c.size() - 1; size >= 0; size--) {
            Object obj = this.c.get(size);
            if (!(obj instanceof Number)) {
                return arrayList;
            }
            arrayList.add(0, (Number) obj);
        }
        return arrayList;
    }
}
