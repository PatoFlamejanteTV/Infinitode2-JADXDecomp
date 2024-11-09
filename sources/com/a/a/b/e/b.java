package com.a.a.b.e;

import com.a.a.b.f;
import com.a.a.b.g.g;
import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: infinitode-2.jar:com/a/a/b/e/b.class */
public final class b {
    private b e;
    private AtomicReference<C0002b> f;
    private int g;
    private int h;
    private boolean i;

    /* renamed from: a, reason: collision with root package name */
    protected String[] f136a;

    /* renamed from: b, reason: collision with root package name */
    protected a[] f137b;
    protected int c;
    private int j;
    private int k;
    protected int d;
    private boolean l;
    private BitSet m;

    private b(int i) {
        this.e = null;
        this.g = i;
        this.i = true;
        this.h = -1;
        this.l = false;
        this.d = 0;
        this.f = new AtomicReference<>(C0002b.a(64));
    }

    private b(b bVar, int i, int i2, C0002b c0002b) {
        this.e = bVar;
        this.g = i2;
        this.f = null;
        this.h = i;
        this.i = f.a.CANONICALIZE_FIELD_NAMES.a(i);
        this.f136a = c0002b.c;
        this.f137b = c0002b.d;
        this.c = c0002b.f140a;
        this.d = c0002b.f141b;
        int length = this.f136a.length;
        this.j = b(length);
        this.k = length - 1;
        this.l = true;
    }

    private static int b(int i) {
        return i - (i >> 2);
    }

    public static b a() {
        long currentTimeMillis = System.currentTimeMillis();
        return c((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    private static b c(int i) {
        return new b(i);
    }

    public final b a(int i) {
        return new b(this, i, this.g, this.f.get());
    }

    public final void b() {
        if (d() && this.e != null && this.i) {
            this.e.a(new C0002b(this));
            this.l = true;
        }
    }

    private void a(C0002b c0002b) {
        int i = c0002b.f140a;
        C0002b c0002b2 = this.f.get();
        if (i == c0002b2.f140a) {
            return;
        }
        if (i > 12000) {
            c0002b = C0002b.a(64);
        }
        this.f.compareAndSet(c0002b2, c0002b);
    }

    private boolean d() {
        return !this.l;
    }

    public final int c() {
        return this.g;
    }

    public final String a(char[] cArr, int i, int i2, int i3) {
        if (i2 <= 0) {
            return "";
        }
        if (!this.i) {
            return new String(cArr, i, i2);
        }
        int d = d(i3);
        String str = this.f136a[d];
        if (str != null) {
            if (str.length() == i2) {
                int i4 = 0;
                while (str.charAt(i4) == cArr[i + i4]) {
                    i4++;
                    if (i4 == i2) {
                        return str;
                    }
                }
            }
            a aVar = this.f137b[d >> 1];
            if (aVar != null) {
                String a2 = aVar.a(cArr, i, i2);
                if (a2 != null) {
                    return a2;
                }
                String a3 = a(cArr, i, i2, aVar.f139b);
                if (a3 != null) {
                    return a3;
                }
            }
        }
        return b(cArr, i, i2, d);
    }

    private static String a(char[] cArr, int i, int i2, a aVar) {
        while (aVar != null) {
            String a2 = aVar.a(cArr, i, i2);
            if (a2 != null) {
                return a2;
            }
            aVar = aVar.f139b;
        }
        return null;
    }

    private String b(char[] cArr, int i, int i2, int i3) {
        if (this.l) {
            e();
            this.l = false;
        } else if (this.c >= this.j) {
            f();
            i3 = d(a(cArr, i, i2));
        }
        String str = new String(cArr, i, i2);
        if (f.a.INTERN_FIELD_NAMES.a(this.h)) {
            str = g.f159a.a(str);
        }
        this.c++;
        if (this.f136a[i3] == null) {
            this.f136a[i3] = str;
        } else {
            int i4 = i3 >> 1;
            a aVar = new a(str, this.f137b[i4]);
            int i5 = aVar.c;
            if (i5 <= 150) {
                this.f137b[i4] = aVar;
                this.d = Math.max(i5, this.d);
            } else {
                a(i4, aVar, i3);
            }
        }
        return str;
    }

    private void a(int i, a aVar, int i2) {
        if (this.m == null) {
            this.m = new BitSet();
        } else if (this.m.get(i)) {
            if (f.a.FAIL_ON_SYMBOL_HASH_OVERFLOW.a(this.h)) {
                e(150);
            }
            this.i = false;
            this.f136a[i2] = aVar.f138a;
            this.f137b[i] = null;
            this.c -= aVar.c;
            this.d = -1;
        }
        this.m.set(i);
        this.f136a[i2] = aVar.f138a;
        this.f137b[i] = null;
        this.c -= aVar.c;
        this.d = -1;
    }

    private int d(int i) {
        int i2 = i + (i >>> 15);
        int i3 = i2 ^ (i2 << 7);
        return (i3 + (i3 >>> 3)) & this.k;
    }

    private int a(char[] cArr, int i, int i2) {
        int i3 = this.g;
        int i4 = i + i2;
        for (int i5 = i; i5 < i4; i5++) {
            i3 = (i3 * 33) + cArr[i5];
        }
        if (i3 == 0) {
            return 1;
        }
        return i3;
    }

    private int a(String str) {
        int length = str.length();
        int i = this.g;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i * 33) + str.charAt(i2);
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    private void e() {
        String[] strArr = this.f136a;
        this.f136a = (String[]) Arrays.copyOf(strArr, strArr.length);
        a[] aVarArr = this.f137b;
        this.f137b = (a[]) Arrays.copyOf(aVarArr, aVarArr.length);
    }

    private void f() {
        int length = this.f136a.length;
        int i = length + length;
        if (i > 65536) {
            this.c = 0;
            this.i = false;
            this.f136a = new String[64];
            this.f137b = new a[32];
            this.k = 63;
            this.l = false;
            return;
        }
        String[] strArr = this.f136a;
        a[] aVarArr = this.f137b;
        this.f136a = new String[i];
        this.f137b = new a[i >> 1];
        this.k = i - 1;
        this.j = b(i);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            String str = strArr[i4];
            if (str != null) {
                i2++;
                int d = d(a(str));
                if (this.f136a[d] == null) {
                    this.f136a[d] = str;
                } else {
                    int i5 = d >> 1;
                    a aVar = new a(str, this.f137b[i5]);
                    this.f137b[i5] = aVar;
                    i3 = Math.max(i3, aVar.c);
                }
            }
        }
        int i6 = length >> 1;
        for (int i7 = 0; i7 < i6; i7++) {
            a aVar2 = aVarArr[i7];
            while (true) {
                a aVar3 = aVar2;
                if (aVar3 != null) {
                    i2++;
                    String str2 = aVar3.f138a;
                    int d2 = d(a(str2));
                    if (this.f136a[d2] == null) {
                        this.f136a[d2] = str2;
                    } else {
                        int i8 = d2 >> 1;
                        a aVar4 = new a(str2, this.f137b[i8]);
                        this.f137b[i8] = aVar4;
                        i3 = Math.max(i3, aVar4.c);
                    }
                    aVar2 = aVar3.f139b;
                }
            }
        }
        this.d = i3;
        this.m = null;
        if (i2 != this.c) {
            throw new IllegalStateException(String.format("Internal error on SymbolTable.rehash(): had %d entries; now have %d", Integer.valueOf(this.c), Integer.valueOf(i2)));
        }
    }

    private void e(int i) {
        throw new IllegalStateException("Longest collision chain in symbol table (of size " + this.c + ") now exceeds maximum, 150 -- suspect a DoS attack based on hash collisions");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/b/e/b$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public final String f138a;

        /* renamed from: b, reason: collision with root package name */
        public final a f139b;
        public final int c;

        public a(String str, a aVar) {
            this.f138a = str;
            this.f139b = aVar;
            this.c = aVar == null ? 1 : aVar.c + 1;
        }

        public final String a(char[] cArr, int i, int i2) {
            if (this.f138a.length() != i2) {
                return null;
            }
            int i3 = 0;
            while (this.f138a.charAt(i3) == cArr[i + i3]) {
                i3++;
                if (i3 >= i2) {
                    return this.f138a;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.a.a.b.e.b$b, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/b/e/b$b.class */
    public static final class C0002b {

        /* renamed from: a, reason: collision with root package name */
        final int f140a;

        /* renamed from: b, reason: collision with root package name */
        final int f141b;
        final String[] c;
        final a[] d;

        private C0002b(int i, int i2, String[] strArr, a[] aVarArr) {
            this.f140a = 0;
            this.f141b = 0;
            this.c = strArr;
            this.d = aVarArr;
        }

        public C0002b(b bVar) {
            this.f140a = bVar.c;
            this.f141b = bVar.d;
            this.c = bVar.f136a;
            this.d = bVar.f137b;
        }

        public static C0002b a(int i) {
            return new C0002b(0, 0, new String[64], new a[32]);
        }
    }
}
