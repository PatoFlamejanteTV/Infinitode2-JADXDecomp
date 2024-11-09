package com.a.a.c.c.b;

import com.a.a.a.l;
import com.a.a.c.m.c;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/aa.class */
public abstract class aa<T> extends ae<T> implements com.a.a.c.c.k {

    /* renamed from: b, reason: collision with root package name */
    private Boolean f299b;
    private transient Object c;

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.c.s f300a;

    protected abstract T a(T t, T t2);

    protected abstract T c(com.a.a.b.l lVar, com.a.a.c.g gVar);

    protected abstract aa<?> a(com.a.a.c.c.s sVar, Boolean bool);

    protected abstract T g();

    protected aa(Class<T> cls) {
        super((Class<?>) cls);
        this.f299b = null;
        this.f300a = null;
    }

    protected aa(aa<?> aaVar, com.a.a.c.c.s sVar, Boolean bool) {
        super(aaVar.s);
        this.f299b = bool;
        this.f300a = sVar;
    }

    public static com.a.a.c.k<?> a(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return f.f301b;
        }
        if (cls == Long.TYPE) {
            return g.f302b;
        }
        if (cls == Byte.TYPE) {
            return new b();
        }
        if (cls == Short.TYPE) {
            return new h();
        }
        if (cls == Float.TYPE) {
            return new e();
        }
        if (cls == Double.TYPE) {
            return new d();
        }
        if (cls == Boolean.TYPE) {
            return new a();
        }
        if (cls == Character.TYPE) {
            return new c();
        }
        throw new IllegalStateException();
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        Boolean a2 = a(gVar, cVar, this.s, l.a.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        com.a.a.c.c.s sVar = null;
        com.a.a.a.ak b2 = b(gVar, cVar);
        if (b2 == com.a.a.a.ak.SKIP) {
            sVar = com.a.a.c.c.a.q.a();
        } else if (b2 == com.a.a.a.ak.FAIL) {
            if (cVar == null) {
                sVar = com.a.a.c.c.a.r.a(gVar.b(this.s.getComponentType()));
            } else {
                sVar = com.a.a.c.c.a.r.a(cVar, cVar.c().u());
            }
        }
        if (Objects.equals(a2, this.f299b) && sVar == this.f300a) {
            return this;
        }
        return a(sVar, a2);
    }

    @Override // com.a.a.c.k
    public com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Array;
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return Boolean.TRUE;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.m.a e() {
        return com.a.a.c.m.a.CONSTANT;
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        Object obj = this.c;
        Object obj2 = obj;
        if (obj == null) {
            Object g2 = g();
            obj2 = g2;
            this.c = g2;
        }
        return obj2;
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.b(lVar, gVar);
    }

    @Override // com.a.a.c.k
    public final T a(com.a.a.b.l lVar, com.a.a.c.g gVar, T t) {
        T a2 = a(lVar, gVar);
        if (t == null) {
            return a2;
        }
        if (Array.getLength(t) == 0) {
            return a2;
        }
        return a(t, a2);
    }

    protected final T e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
            return m(lVar, gVar);
        }
        if (this.f299b == Boolean.TRUE || (this.f299b == null && gVar.a(com.a.a.c.i.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            return c(lVar, gVar);
        }
        return (T) gVar.a(this.s, lVar);
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/aa$c.class */
    static final class c extends aa<char[]> {
        @Override // com.a.a.c.c.b.aa
        protected final /* synthetic */ char[] g() {
            return j();
        }

        @Override // com.a.a.c.c.b.aa
        protected final /* bridge */ /* synthetic */ char[] a(char[] cArr, char[] cArr2) {
            return a2(cArr, cArr2);
        }

        public c() {
            super(char[].class);
        }

        @Override // com.a.a.c.c.b.aa
        protected final aa<?> a(com.a.a.c.c.s sVar, Boolean bool) {
            return this;
        }

        private static char[] j() {
            return new char[0];
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public char[] a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            String w;
            if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
                char[] x = lVar.x();
                int z = lVar.z();
                int y = lVar.y();
                char[] cArr = new char[y];
                System.arraycopy(x, z, cArr, 0, y);
                return cArr;
            }
            if (lVar.p()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    com.a.a.b.o g = lVar.g();
                    if (g != com.a.a.b.o.END_ARRAY) {
                        if (g == com.a.a.b.o.VALUE_STRING) {
                            w = lVar.w();
                        } else if (g == com.a.a.b.o.VALUE_NULL) {
                            if (this.f300a != null) {
                                this.f300a.a(gVar);
                            } else {
                                f(gVar);
                                w = "��";
                            }
                        } else {
                            w = ((CharSequence) gVar.a(Character.TYPE, lVar)).toString();
                        }
                        if (w.length() != 1) {
                            gVar.a(this, "Cannot convert a JSON String of length %d into a char element of char array", Integer.valueOf(w.length()));
                        }
                        sb.append(w.charAt(0));
                    } else {
                        return sb.toString().toCharArray();
                    }
                }
            } else {
                if (lVar.a(com.a.a.b.o.VALUE_EMBEDDED_OBJECT)) {
                    Object N = lVar.N();
                    if (N == null) {
                        return null;
                    }
                    if (N instanceof char[]) {
                        return (char[]) N;
                    }
                    if (N instanceof String) {
                        return ((String) N).toCharArray();
                    }
                    if (N instanceof byte[]) {
                        return com.a.a.b.b.a().a((byte[]) N, false).toCharArray();
                    }
                }
                return (char[]) gVar.a(this.s, lVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.c.b.aa
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public char[] c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            return (char[]) gVar.a(this.s, lVar);
        }

        /* renamed from: a, reason: avoid collision after fix types in other method */
        private static char[] a2(char[] cArr, char[] cArr2) {
            int length = cArr.length;
            int length2 = cArr2.length;
            char[] copyOf = Arrays.copyOf(cArr, length + length2);
            System.arraycopy(cArr2, 0, copyOf, length, length2);
            return copyOf;
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/aa$a.class */
    static final class a extends aa<boolean[]> {
        @Override // com.a.a.c.c.b.aa
        protected final /* synthetic */ boolean[] g() {
            return j();
        }

        @Override // com.a.a.c.c.b.aa
        protected final /* bridge */ /* synthetic */ boolean[] a(boolean[] zArr, boolean[] zArr2) {
            return a2(zArr, zArr2);
        }

        public a() {
            super(boolean[].class);
        }

        private a(a aVar, com.a.a.c.c.s sVar, Boolean bool) {
            super(aVar, sVar, bool);
        }

        @Override // com.a.a.c.c.b.aa
        protected final aa<?> a(com.a.a.c.c.s sVar, Boolean bool) {
            return new a(this, sVar, bool);
        }

        private static boolean[] j() {
            return new boolean[0];
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12, types: [com.a.a.b.o] */
        /* JADX WARN: Type inference failed for: r0v19 */
        /* JADX WARN: Type inference failed for: r0v22, types: [boolean[]] */
        /* JADX WARN: Type inference failed for: r0v24 */
        /* JADX WARN: Type inference failed for: r0v36, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v6, types: [boolean[]] */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r1v7 */
        @Override // com.a.a.c.k
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public boolean[] a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            boolean z;
            if (!lVar.p()) {
                return e(lVar, gVar);
            }
            c.a a2 = gVar.o().a();
            T b2 = a2.b();
            ?? r0 = 0;
            int i = 0;
            while (true) {
                try {
                    r0 = lVar.g();
                    if (r0 != com.a.a.b.o.END_ARRAY) {
                        if (r0 == com.a.a.b.o.VALUE_TRUE) {
                            z = true;
                        } else if (r0 == com.a.a.b.o.VALUE_FALSE) {
                            z = false;
                        } else if (r0 == com.a.a.b.o.VALUE_NULL) {
                            if (this.f300a != null) {
                                r0 = this.f300a.a(gVar);
                            } else {
                                f(gVar);
                                z = false;
                            }
                        } else {
                            z = n(lVar, gVar);
                        }
                        if (i >= b2.length) {
                            b2 = a2.a(b2, i);
                            i = 0;
                        }
                        r0 = b2;
                        int i2 = i;
                        i++;
                        r0[i2] = z;
                    } else {
                        return a2.b(b2, i);
                    }
                } catch (Exception e) {
                    throw com.a.a.c.l.a((Throwable) r0, b2, a2.a() + i);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.c.b.aa
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public boolean[] c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            return new boolean[]{n(lVar, gVar)};
        }

        /* renamed from: a, reason: avoid collision after fix types in other method */
        private static boolean[] a2(boolean[] zArr, boolean[] zArr2) {
            int length = zArr.length;
            int length2 = zArr2.length;
            boolean[] copyOf = Arrays.copyOf(zArr, length + length2);
            System.arraycopy(zArr2, 0, copyOf, length, length2);
            return copyOf;
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/aa$b.class */
    static final class b extends aa<byte[]> {
        @Override // com.a.a.c.c.b.aa
        protected final /* synthetic */ byte[] g() {
            return j();
        }

        @Override // com.a.a.c.c.b.aa
        protected final /* bridge */ /* synthetic */ byte[] a(byte[] bArr, byte[] bArr2) {
            return a2(bArr, bArr2);
        }

        public b() {
            super(byte[].class);
        }

        private b(b bVar, com.a.a.c.c.s sVar, Boolean bool) {
            super(bVar, sVar, bool);
        }

        @Override // com.a.a.c.c.b.aa
        protected final aa<?> a(com.a.a.c.c.s sVar, Boolean bool) {
            return new b(this, sVar, bool);
        }

        private static byte[] j() {
            return new byte[0];
        }

        @Override // com.a.a.c.c.b.aa, com.a.a.c.k
        public final com.a.a.c.l.f b() {
            return com.a.a.c.l.f.Binary;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [com.a.a.b.o] */
        /* JADX WARN: Type inference failed for: r0v17, types: [byte[]] */
        /* JADX WARN: Type inference failed for: r0v18 */
        /* JADX WARN: Type inference failed for: r0v19 */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.a.a.b.b.b] */
        /* JADX WARN: Type inference failed for: r0v20, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v23, types: [com.a.a.b.o] */
        /* JADX WARN: Type inference failed for: r0v31 */
        /* JADX WARN: Type inference failed for: r0v34, types: [byte[]] */
        /* JADX WARN: Type inference failed for: r0v36 */
        /* JADX WARN: Type inference failed for: r0v46, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v9, types: [byte[]] */
        /* JADX WARN: Type inference failed for: r1v16 */
        @Override // com.a.a.c.k
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public byte[] a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            byte E;
            ?? k = lVar.k();
            if (k == com.a.a.b.o.VALUE_STRING) {
                try {
                    k = lVar.a(gVar.k());
                    return k;
                } catch (com.a.a.b.b.b e) {
                    String b2 = k.b();
                    if (b2.contains("base64")) {
                        return (byte[]) gVar.b(byte[].class, lVar.w(), b2, new Object[0]);
                    }
                }
            }
            if (k == com.a.a.b.o.VALUE_EMBEDDED_OBJECT) {
                Object N = lVar.N();
                if (N == null) {
                    return null;
                }
                if (N instanceof byte[]) {
                    return (byte[]) N;
                }
            }
            if (!lVar.p()) {
                return e(lVar, gVar);
            }
            c.b b3 = gVar.o().b();
            T b4 = b3.b();
            ?? r0 = 0;
            int i = 0;
            while (true) {
                try {
                    r0 = lVar.g();
                    if (r0 != com.a.a.b.o.END_ARRAY) {
                        if (r0 == com.a.a.b.o.VALUE_NUMBER_INT) {
                            E = lVar.E();
                        } else if (r0 == com.a.a.b.o.VALUE_NULL) {
                            if (this.f300a != null) {
                                r0 = this.f300a.a(gVar);
                            } else {
                                f(gVar);
                                E = 0;
                            }
                        } else {
                            E = o(lVar, gVar);
                        }
                        if (i >= b4.length) {
                            b4 = b3.a(b4, i);
                            i = 0;
                        }
                        r0 = b4;
                        int i2 = i;
                        i++;
                        r0[i2] = E;
                    } else {
                        return b3.b(b4, i);
                    }
                } catch (Exception e2) {
                    throw com.a.a.c.l.a((Throwable) r0, b4, b3.a() + i);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.c.b.aa
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public byte[] c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            byte byteValue;
            com.a.a.b.o k = lVar.k();
            if (k == com.a.a.b.o.VALUE_NUMBER_INT) {
                byteValue = lVar.E();
            } else {
                if (k == com.a.a.b.o.VALUE_NULL) {
                    if (this.f300a != null) {
                        this.f300a.a(gVar);
                        return (byte[]) c(gVar);
                    }
                    f(gVar);
                    return null;
                }
                byteValue = ((Number) gVar.a(this.s.getComponentType(), lVar)).byteValue();
            }
            return new byte[]{byteValue};
        }

        /* renamed from: a, reason: avoid collision after fix types in other method */
        private static byte[] a2(byte[] bArr, byte[] bArr2) {
            int length = bArr.length;
            int length2 = bArr2.length;
            byte[] copyOf = Arrays.copyOf(bArr, length + length2);
            System.arraycopy(bArr2, 0, copyOf, length, length2);
            return copyOf;
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/aa$h.class */
    static final class h extends aa<short[]> {
        @Override // com.a.a.c.c.b.aa
        protected final /* synthetic */ short[] g() {
            return j();
        }

        @Override // com.a.a.c.c.b.aa
        protected final /* bridge */ /* synthetic */ short[] a(short[] sArr, short[] sArr2) {
            return a2(sArr, sArr2);
        }

        public h() {
            super(short[].class);
        }

        private h(h hVar, com.a.a.c.c.s sVar, Boolean bool) {
            super(hVar, sVar, bool);
        }

        @Override // com.a.a.c.c.b.aa
        protected final aa<?> a(com.a.a.c.c.s sVar, Boolean bool) {
            return new h(this, sVar, bool);
        }

        private static short[] j() {
            return new short[0];
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12, types: [com.a.a.b.o] */
        /* JADX WARN: Type inference failed for: r0v19 */
        /* JADX WARN: Type inference failed for: r0v24, types: [short[]] */
        /* JADX WARN: Type inference failed for: r0v30, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v6, types: [short[]] */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r1v8 */
        @Override // com.a.a.c.k
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public short[] a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            short p;
            if (!lVar.p()) {
                return e(lVar, gVar);
            }
            c.g c = gVar.o().c();
            T b2 = c.b();
            short[] sArr = 0;
            int i = 0;
            while (true) {
                try {
                    sArr = lVar.g();
                    if (sArr != com.a.a.b.o.END_ARRAY) {
                        if (sArr == com.a.a.b.o.VALUE_NULL) {
                            if (this.f300a != null) {
                                sArr = this.f300a.a(gVar);
                            } else {
                                f(gVar);
                                p = 0;
                            }
                        } else {
                            p = p(lVar, gVar);
                        }
                        if (i >= b2.length) {
                            b2 = c.a(b2, i);
                            i = 0;
                        }
                        sArr = b2;
                        int i2 = i;
                        i++;
                        sArr[i2] = p;
                    } else {
                        return c.b(b2, i);
                    }
                } catch (Exception e) {
                    throw com.a.a.c.l.a((Throwable) sArr, b2, c.a() + i);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.c.b.aa
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public short[] c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            return new short[]{p(lVar, gVar)};
        }

        /* renamed from: a, reason: avoid collision after fix types in other method */
        private static short[] a2(short[] sArr, short[] sArr2) {
            int length = sArr.length;
            int length2 = sArr2.length;
            short[] copyOf = Arrays.copyOf(sArr, length + length2);
            System.arraycopy(sArr2, 0, copyOf, length, length2);
            return copyOf;
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/aa$f.class */
    static final class f extends aa<int[]> {

        /* renamed from: b, reason: collision with root package name */
        public static final f f301b = new f();

        @Override // com.a.a.c.c.b.aa
        protected final /* synthetic */ int[] g() {
            return j();
        }

        @Override // com.a.a.c.c.b.aa
        protected final /* bridge */ /* synthetic */ int[] a(int[] iArr, int[] iArr2) {
            return a2(iArr, iArr2);
        }

        public f() {
            super(int[].class);
        }

        private f(f fVar, com.a.a.c.c.s sVar, Boolean bool) {
            super(fVar, sVar, bool);
        }

        @Override // com.a.a.c.c.b.aa
        protected final aa<?> a(com.a.a.c.c.s sVar, Boolean bool) {
            return new f(this, sVar, bool);
        }

        private static int[] j() {
            return new int[0];
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12, types: [com.a.a.b.o] */
        /* JADX WARN: Type inference failed for: r0v20 */
        /* JADX WARN: Type inference failed for: r0v23, types: [int[]] */
        /* JADX WARN: Type inference failed for: r0v25 */
        /* JADX WARN: Type inference failed for: r0v35, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v6, types: [int[]] */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r1v7 */
        @Override // com.a.a.c.k
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public int[] a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            int G;
            if (!lVar.p()) {
                return e(lVar, gVar);
            }
            c.e d = gVar.o().d();
            T b2 = d.b();
            ?? r0 = 0;
            int i = 0;
            while (true) {
                try {
                    r0 = lVar.g();
                    if (r0 != com.a.a.b.o.END_ARRAY) {
                        if (r0 == com.a.a.b.o.VALUE_NUMBER_INT) {
                            G = lVar.G();
                        } else if (r0 == com.a.a.b.o.VALUE_NULL) {
                            if (this.f300a != null) {
                                r0 = this.f300a.a(gVar);
                            } else {
                                f(gVar);
                                G = 0;
                            }
                        } else {
                            G = q(lVar, gVar);
                        }
                        if (i >= b2.length) {
                            b2 = d.a(b2, i);
                            i = 0;
                        }
                        r0 = b2;
                        int i2 = i;
                        i++;
                        r0[i2] = G;
                    } else {
                        return d.b(b2, i);
                    }
                } catch (Exception e) {
                    throw com.a.a.c.l.a((Throwable) r0, b2, d.a() + i);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.c.b.aa
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public int[] c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            return new int[]{q(lVar, gVar)};
        }

        /* renamed from: a, reason: avoid collision after fix types in other method */
        private static int[] a2(int[] iArr, int[] iArr2) {
            int length = iArr.length;
            int length2 = iArr2.length;
            int[] copyOf = Arrays.copyOf(iArr, length + length2);
            System.arraycopy(iArr2, 0, copyOf, length, length2);
            return copyOf;
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/aa$g.class */
    static final class g extends aa<long[]> {

        /* renamed from: b, reason: collision with root package name */
        public static final g f302b = new g();

        @Override // com.a.a.c.c.b.aa
        protected final /* synthetic */ long[] g() {
            return j();
        }

        @Override // com.a.a.c.c.b.aa
        protected final /* bridge */ /* synthetic */ long[] a(long[] jArr, long[] jArr2) {
            return a2(jArr, jArr2);
        }

        public g() {
            super(long[].class);
        }

        private g(g gVar, com.a.a.c.c.s sVar, Boolean bool) {
            super(gVar, sVar, bool);
        }

        @Override // com.a.a.c.c.b.aa
        protected final aa<?> a(com.a.a.c.c.s sVar, Boolean bool) {
            return new g(this, sVar, bool);
        }

        private static long[] j() {
            return new long[0];
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12, types: [com.a.a.b.o] */
        /* JADX WARN: Type inference failed for: r0v20 */
        /* JADX WARN: Type inference failed for: r0v23, types: [long[]] */
        /* JADX WARN: Type inference failed for: r0v25 */
        /* JADX WARN: Type inference failed for: r0v35, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v6, types: [long[]] */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r1v7 */
        @Override // com.a.a.c.k
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public long[] a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            long H;
            if (!lVar.p()) {
                return e(lVar, gVar);
            }
            c.f e = gVar.o().e();
            T b2 = e.b();
            ?? r0 = 0;
            int i = 0;
            while (true) {
                try {
                    r0 = lVar.g();
                    if (r0 != com.a.a.b.o.END_ARRAY) {
                        if (r0 == com.a.a.b.o.VALUE_NUMBER_INT) {
                            H = lVar.H();
                        } else if (r0 == com.a.a.b.o.VALUE_NULL) {
                            if (this.f300a != null) {
                                r0 = this.f300a.a(gVar);
                            } else {
                                f(gVar);
                                H = 0;
                            }
                        } else {
                            H = r(lVar, gVar);
                        }
                        if (i >= b2.length) {
                            b2 = e.a(b2, i);
                            i = 0;
                        }
                        r0 = b2;
                        int i2 = i;
                        i++;
                        r0[i2] = H;
                    } else {
                        return e.b(b2, i);
                    }
                } catch (Exception e2) {
                    throw com.a.a.c.l.a((Throwable) r0, b2, e.a() + i);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.c.b.aa
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public long[] c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            return new long[]{r(lVar, gVar)};
        }

        /* renamed from: a, reason: avoid collision after fix types in other method */
        private static long[] a2(long[] jArr, long[] jArr2) {
            int length = jArr.length;
            int length2 = jArr2.length;
            long[] copyOf = Arrays.copyOf(jArr, length + length2);
            System.arraycopy(jArr2, 0, copyOf, length, length2);
            return copyOf;
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/aa$e.class */
    static final class e extends aa<float[]> {
        @Override // com.a.a.c.c.b.aa
        protected final /* synthetic */ float[] g() {
            return j();
        }

        @Override // com.a.a.c.c.b.aa
        protected final /* bridge */ /* synthetic */ float[] a(float[] fArr, float[] fArr2) {
            return a2(fArr, fArr2);
        }

        public e() {
            super(float[].class);
        }

        private e(e eVar, com.a.a.c.c.s sVar, Boolean bool) {
            super(eVar, sVar, bool);
        }

        @Override // com.a.a.c.c.b.aa
        protected final aa<?> a(com.a.a.c.c.s sVar, Boolean bool) {
            return new e(this, sVar, bool);
        }

        private static float[] j() {
            return new float[0];
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12, types: [com.a.a.b.o] */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r0v21 */
        /* JADX WARN: Type inference failed for: r0v24, types: [float[]] */
        /* JADX WARN: Type inference failed for: r0v30, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v6, types: [float[]] */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r1v8 */
        @Override // com.a.a.c.k
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public float[] a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (!lVar.p()) {
                return e(lVar, gVar);
            }
            c.d f = gVar.o().f();
            T b2 = f.b();
            ?? r0 = 0;
            int i = 0;
            while (true) {
                try {
                    r0 = lVar.g();
                    if (r0 != com.a.a.b.o.END_ARRAY) {
                        if (r0 == com.a.a.b.o.VALUE_NULL && this.f300a != null) {
                            r0 = this.f300a.a(gVar);
                        } else {
                            float s = s(lVar, gVar);
                            if (i >= b2.length) {
                                b2 = f.a(b2, i);
                                i = 0;
                            }
                            r0 = b2;
                            int i2 = i;
                            i++;
                            r0[i2] = s;
                        }
                    } else {
                        return f.b(b2, i);
                    }
                } catch (Exception e) {
                    throw com.a.a.c.l.a((Throwable) r0, b2, f.a() + i);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.c.b.aa
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public float[] c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            return new float[]{s(lVar, gVar)};
        }

        /* renamed from: a, reason: avoid collision after fix types in other method */
        private static float[] a2(float[] fArr, float[] fArr2) {
            int length = fArr.length;
            int length2 = fArr2.length;
            float[] copyOf = Arrays.copyOf(fArr, length + length2);
            System.arraycopy(fArr2, 0, copyOf, length, length2);
            return copyOf;
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/aa$d.class */
    static final class d extends aa<double[]> {
        @Override // com.a.a.c.c.b.aa
        protected final /* synthetic */ double[] g() {
            return j();
        }

        @Override // com.a.a.c.c.b.aa
        protected final /* bridge */ /* synthetic */ double[] a(double[] dArr, double[] dArr2) {
            return a2(dArr, dArr2);
        }

        public d() {
            super(double[].class);
        }

        private d(d dVar, com.a.a.c.c.s sVar, Boolean bool) {
            super(dVar, sVar, bool);
        }

        @Override // com.a.a.c.c.b.aa
        protected final aa<?> a(com.a.a.c.c.s sVar, Boolean bool) {
            return new d(this, sVar, bool);
        }

        private static double[] j() {
            return new double[0];
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12, types: [com.a.a.b.o] */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r0v21 */
        /* JADX WARN: Type inference failed for: r0v24, types: [double[]] */
        /* JADX WARN: Type inference failed for: r0v30, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v6, types: [double[]] */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r1v8 */
        @Override // com.a.a.c.k
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public double[] a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (!lVar.p()) {
                return e(lVar, gVar);
            }
            c.C0013c g = gVar.o().g();
            T b2 = g.b();
            ?? r0 = 0;
            int i = 0;
            while (true) {
                try {
                    r0 = lVar.g();
                    if (r0 != com.a.a.b.o.END_ARRAY) {
                        if (r0 == com.a.a.b.o.VALUE_NULL && this.f300a != null) {
                            r0 = this.f300a.a(gVar);
                        } else {
                            double t = t(lVar, gVar);
                            if (i >= b2.length) {
                                b2 = g.a(b2, i);
                                i = 0;
                            }
                            r0 = b2;
                            int i2 = i;
                            i++;
                            r0[i2] = t;
                        }
                    } else {
                        return g.b(b2, i);
                    }
                } catch (Exception e) {
                    throw com.a.a.c.l.a((Throwable) r0, b2, g.a() + i);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.c.b.aa
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public double[] c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            return new double[]{t(lVar, gVar)};
        }

        /* renamed from: a, reason: avoid collision after fix types in other method */
        private static double[] a2(double[] dArr, double[] dArr2) {
            int length = dArr.length;
            int length2 = dArr2.length;
            double[] copyOf = Arrays.copyOf(dArr, length + length2);
            System.arraycopy(dArr2, 0, copyOf, length, length2);
            return copyOf;
        }
    }
}
