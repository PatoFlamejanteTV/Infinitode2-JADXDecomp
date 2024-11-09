package com.b.a.a;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/b/a/a/s.class */
public abstract class s implements Iterable<a> {
    private static d n = new t();

    /* renamed from: a, reason: collision with root package name */
    c f828a;

    /* renamed from: b, reason: collision with root package name */
    char[] f829b;
    int c;
    int[] d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/s$c.class */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        int f834a;

        /* renamed from: b, reason: collision with root package name */
        int f835b;
        int c;
        int d;
        int e;
        int f;
        int g;
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/s$d.class */
    public interface d {
        int a(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/s$e.class */
    public enum e {
        BITS_16,
        BITS_32
    }

    public abstract int a(int i);

    public abstract int a(char c2);

    static /* synthetic */ int a() {
        return -2128831035;
    }

    public static s a(ByteBuffer byteBuffer) {
        e eVar;
        s yVar;
        ByteOrder order = byteBuffer.order();
        try {
            c cVar = new c();
            cVar.f834a = byteBuffer.getInt();
            switch (cVar.f834a) {
                case 845771348:
                    byteBuffer.order(order == ByteOrder.BIG_ENDIAN ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
                    cVar.f834a = 1416784178;
                    break;
                case 1416784178:
                    break;
                default:
                    throw new IllegalArgumentException("Buffer does not contain a serialized UTrie2");
            }
            cVar.f835b = byteBuffer.getChar();
            cVar.c = byteBuffer.getChar();
            cVar.d = byteBuffer.getChar();
            cVar.e = byteBuffer.getChar();
            cVar.f = byteBuffer.getChar();
            cVar.g = byteBuffer.getChar();
            if ((cVar.f835b & 15) > 1) {
                throw new IllegalArgumentException("UTrie2 serialized format error.");
            }
            if ((cVar.f835b & 15) == 0) {
                eVar = e.BITS_16;
                yVar = new x();
            } else {
                eVar = e.BITS_32;
                yVar = new y();
            }
            yVar.f828a = cVar;
            yVar.e = cVar.c;
            yVar.f = cVar.d << 2;
            yVar.g = cVar.e;
            yVar.l = cVar.f;
            yVar.j = cVar.g << 11;
            s sVar = yVar;
            sVar.k = sVar.f - 4;
            if (eVar == e.BITS_16) {
                yVar.k += yVar.e;
            }
            int i = yVar.e;
            if (eVar == e.BITS_16) {
                i += yVar.f;
            }
            yVar.f829b = f.b(byteBuffer, i, 0);
            if (eVar == e.BITS_16) {
                s sVar2 = yVar;
                sVar2.c = sVar2.e;
            } else {
                yVar.d = f.c(byteBuffer, yVar.f, 0);
            }
            switch (eVar) {
                case BITS_16:
                    yVar.d = null;
                    s sVar3 = yVar;
                    sVar3.h = sVar3.f829b[yVar.l];
                    s sVar4 = yVar;
                    sVar4.i = sVar4.f829b[yVar.c + 128];
                    break;
                case BITS_32:
                    yVar.c = 0;
                    s sVar5 = yVar;
                    sVar5.h = sVar5.d[yVar.l];
                    s sVar6 = yVar;
                    sVar6.i = sVar6.d[128];
                    break;
                default:
                    throw new IllegalArgumentException("UTrie2 serialized format error.");
            }
            return yVar;
        } finally {
            byteBuffer.order(order);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        Iterator<a> it = sVar.iterator();
        Iterator<a> it2 = iterator();
        while (it2.hasNext()) {
            a next = it2.next();
            if (!it.hasNext() || !next.equals(it.next())) {
                return false;
            }
        }
        if (it.hasNext() || this.i != sVar.i || this.h != sVar.h) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.m == 0) {
            int i = -2128831035;
            Iterator<a> it = iterator();
            while (it.hasNext()) {
                i = f(i, it.next().hashCode());
            }
            if (i == 0) {
                i = 1;
            }
            this.m = i;
        }
        return this.m;
    }

    /* loaded from: infinitode-2.jar:com/b/a/a/s$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public int f830a;

        /* renamed from: b, reason: collision with root package name */
        public int f831b;
        public int c;
        public boolean d;

        public final boolean equals(Object obj) {
            if (obj == null || !obj.getClass().equals(getClass())) {
                return false;
            }
            a aVar = (a) obj;
            return this.f830a == aVar.f830a && this.f831b == aVar.f831b && this.c == aVar.c && this.d == aVar.d;
        }

        public final int hashCode() {
            return s.d(s.f(s.e(s.e(s.a(), this.f830a), this.f831b), this.c), this.d ? 1 : 0);
        }
    }

    @Override // java.lang.Iterable
    public Iterator<a> iterator() {
        return a(n);
    }

    private Iterator<a> a(d dVar) {
        return new b(dVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/s$b.class */
    public class b implements Iterator<a> {

        /* renamed from: a, reason: collision with root package name */
        private d f832a;
        private boolean f;

        /* renamed from: b, reason: collision with root package name */
        private a f833b = new a();
        private boolean e = true;
        private int c = 0;
        private int d = 1114112;

        b(d dVar) {
            this.f = true;
            this.f832a = dVar;
            this.f = true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a next() {
            int a2;
            int i;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.c >= this.d) {
                this.e = false;
                this.c = 55296;
            }
            if (this.e) {
                int a3 = s.this.a(this.c);
                a2 = this.f832a.a(a3);
                int a4 = s.this.a(this.c, this.d, a3);
                while (true) {
                    i = a4;
                    if (i >= this.d - 1) {
                        break;
                    }
                    int a5 = s.this.a(i + 1);
                    if (this.f832a.a(a5) != a2) {
                        break;
                    }
                    a4 = s.this.a(i + 1, this.d, a5);
                }
            } else {
                a2 = this.f832a.a(s.this.a((char) this.c));
                int a6 = a((char) this.c);
                while (true) {
                    i = a6;
                    if (i >= 56319) {
                        break;
                    }
                    if (this.f832a.a(s.this.a((char) (i + 1))) != a2) {
                        break;
                    }
                    a6 = a((char) (i + 1));
                }
            }
            this.f833b.f830a = this.c;
            this.f833b.f831b = i;
            this.f833b.c = a2;
            this.f833b.d = !this.e;
            this.c = i + 1;
            return this.f833b;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return (this.e && (this.f || this.c < this.d)) || this.c < 56320;
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }

        private int a(char c) {
            if (c >= 56319) {
                return 56319;
            }
            int a2 = s.this.a(c);
            int i = c + 1;
            while (i <= 56319 && s.this.a((char) i) == a2) {
                i++;
            }
            return i - 1;
        }
    }

    int a(int i, int i2, int i3) {
        int min = Math.min(this.j, i2);
        int i4 = i + 1;
        while (i4 < min && a(i4) == i3) {
            i4++;
        }
        if (i4 >= this.j) {
            i4 = i2;
        }
        return i4 - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int d(int i, int i2) {
        return (i * 16777619) ^ i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int e(int i, int i2) {
        return d(d(d(i, i2 & 255), (i2 >> 8) & 255), i2 >> 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int f(int i, int i2) {
        return d(d(d(d(i, i2 & 255), (i2 >> 8) & 255), (i2 >> 16) & 255), i2 >>> 24);
    }
}
