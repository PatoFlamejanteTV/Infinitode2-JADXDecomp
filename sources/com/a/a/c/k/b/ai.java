package com.a.a.c.k.b;

import java.util.HashMap;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/ai.class */
public final class ai {

    /* renamed from: a, reason: collision with root package name */
    private static HashMap<String, com.a.a.c.o<?>> f603a;

    static {
        HashMap<String, com.a.a.c.o<?>> hashMap = new HashMap<>();
        f603a = hashMap;
        hashMap.put(boolean[].class.getName(), new a());
        f603a.put(byte[].class.getName(), new com.a.a.c.k.b.g());
        f603a.put(char[].class.getName(), new b());
        f603a.put(short[].class.getName(), new g());
        f603a.put(int[].class.getName(), new e());
        f603a.put(long[].class.getName(), new f());
        f603a.put(float[].class.getName(), new d());
        f603a.put(double[].class.getName(), new c());
    }

    public static com.a.a.c.o<?> a(Class<?> cls) {
        return f603a.get(cls.getName());
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ai$h.class */
    public static abstract class h<T> extends com.a.a.c.k.b.a<T> {
        protected h(Class<T> cls) {
            super(cls);
        }

        protected h(h<T> hVar, com.a.a.c.c cVar, Boolean bool) {
            super(hVar, cVar, bool);
        }

        @Override // com.a.a.c.k.j
        public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
            return this;
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ai$a.class */
    public static class a extends com.a.a.c.k.b.a<boolean[]> {
        @Override // com.a.a.c.k.b.a
        public final /* synthetic */ void b(boolean[] zArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            a(zArr, hVar);
        }

        @Override // com.a.a.c.k.j
        public final /* synthetic */ boolean a(Object obj) {
            return b((boolean[]) obj);
        }

        @Override // com.a.a.c.o
        public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
            return a((boolean[]) obj);
        }

        static {
            com.a.a.c.l.o.a().a(Boolean.class);
        }

        public a() {
            super(boolean[].class);
        }

        private a(a aVar, com.a.a.c.c cVar, Boolean bool) {
            super(aVar, cVar, bool);
        }

        @Override // com.a.a.c.k.b.a
        public final com.a.a.c.o<?> a(com.a.a.c.c cVar, Boolean bool) {
            return new a(this, cVar, bool);
        }

        @Override // com.a.a.c.k.j
        public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
            return this;
        }

        private static boolean a(boolean[] zArr) {
            return zArr.length == 0;
        }

        private static boolean b(boolean[] zArr) {
            return zArr.length == 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k.b.a, com.a.a.c.k.b.ao, com.a.a.c.o
        public void a(boolean[] zArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            int length = zArr.length;
            if (length == 1 && a(aaVar)) {
                a(zArr, hVar);
                return;
            }
            hVar.a(zArr, length);
            a(zArr, hVar);
            hVar.h();
        }

        private static void a(boolean[] zArr, com.a.a.b.h hVar) {
            for (boolean z : zArr) {
                hVar.a(z);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ai$g.class */
    public static class g extends h<short[]> {
        @Override // com.a.a.c.k.b.a
        public final /* synthetic */ void b(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            a((short[]) obj, hVar);
        }

        @Override // com.a.a.c.k.j
        public final /* synthetic */ boolean a(Object obj) {
            return b((short[]) obj);
        }

        @Override // com.a.a.c.o
        public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
            return a((short[]) obj);
        }

        static {
            com.a.a.c.l.o.a().a(Short.TYPE);
        }

        public g() {
            super(short[].class);
        }

        private g(g gVar, com.a.a.c.c cVar, Boolean bool) {
            super(gVar, cVar, bool);
        }

        @Override // com.a.a.c.k.b.a
        public final com.a.a.c.o<?> a(com.a.a.c.c cVar, Boolean bool) {
            return new g(this, cVar, bool);
        }

        private static boolean a(short[] sArr) {
            return sArr.length == 0;
        }

        private static boolean b(short[] sArr) {
            return sArr.length == 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k.b.a, com.a.a.c.k.b.ao, com.a.a.c.o
        public void a(short[] sArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            int length = sArr.length;
            if (length == 1 && a(aaVar)) {
                a(sArr, hVar);
                return;
            }
            hVar.a(sArr, length);
            a(sArr, hVar);
            hVar.h();
        }

        private static void a(short[] sArr, com.a.a.b.h hVar) {
            for (short s : sArr) {
                hVar.c(s);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ai$b.class */
    public static class b extends ao<char[]> {
        @Override // com.a.a.c.o
        public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
            return a((char[]) obj);
        }

        public b() {
            super(char[].class);
        }

        private static boolean a(char[] cArr) {
            return cArr.length == 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public void a(char[] cArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            if (aaVar.a(com.a.a.c.z.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                hVar.a(cArr, cArr.length);
                a(hVar, cArr);
                hVar.h();
                return;
            }
            hVar.a(cArr, 0, cArr.length);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.o
        public void a(char[] cArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
            com.a.a.b.f.a a2;
            if (aaVar.a(com.a.a.c.z.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                a2 = iVar.a(hVar, iVar.a(cArr, com.a.a.b.o.START_ARRAY));
                a(hVar, cArr);
            } else {
                a2 = iVar.a(hVar, iVar.a(cArr, com.a.a.b.o.VALUE_STRING));
                hVar.a(cArr, 0, cArr.length);
            }
            iVar.b(hVar, a2);
        }

        private static void a(com.a.a.b.h hVar, char[] cArr) {
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                hVar.a(cArr, i, 1);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ai$e.class */
    public static class e extends com.a.a.c.k.b.a<int[]> {
        @Override // com.a.a.c.k.b.a
        public final /* synthetic */ void b(int[] iArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            a(iArr, hVar);
        }

        @Override // com.a.a.c.k.j
        public final /* synthetic */ boolean a(Object obj) {
            return b((int[]) obj);
        }

        @Override // com.a.a.c.o
        public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
            return a((int[]) obj);
        }

        static {
            com.a.a.c.l.o.a().a(Integer.TYPE);
        }

        public e() {
            super(int[].class);
        }

        private e(e eVar, com.a.a.c.c cVar, Boolean bool) {
            super(eVar, cVar, bool);
        }

        @Override // com.a.a.c.k.b.a
        public final com.a.a.c.o<?> a(com.a.a.c.c cVar, Boolean bool) {
            return new e(this, cVar, bool);
        }

        @Override // com.a.a.c.k.j
        public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
            return this;
        }

        private static boolean a(int[] iArr) {
            return iArr.length == 0;
        }

        private static boolean b(int[] iArr) {
            return iArr.length == 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k.b.a, com.a.a.c.k.b.ao, com.a.a.c.o
        public void a(int[] iArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            if (iArr.length == 1 && a(aaVar)) {
                a(iArr, hVar);
            } else {
                hVar.a(iArr, 0, iArr.length);
            }
        }

        private static void a(int[] iArr, com.a.a.b.h hVar) {
            for (int i : iArr) {
                hVar.c(i);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ai$f.class */
    public static class f extends h<long[]> {
        @Override // com.a.a.c.k.b.a
        public final /* synthetic */ void b(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            a((long[]) obj, hVar);
        }

        @Override // com.a.a.c.k.j
        public final /* synthetic */ boolean a(Object obj) {
            return b((long[]) obj);
        }

        @Override // com.a.a.c.o
        public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
            return a((long[]) obj);
        }

        static {
            com.a.a.c.l.o.a().a(Long.TYPE);
        }

        public f() {
            super(long[].class);
        }

        private f(f fVar, com.a.a.c.c cVar, Boolean bool) {
            super(fVar, cVar, bool);
        }

        @Override // com.a.a.c.k.b.a
        public final com.a.a.c.o<?> a(com.a.a.c.c cVar, Boolean bool) {
            return new f(this, cVar, bool);
        }

        private static boolean a(long[] jArr) {
            return jArr.length == 0;
        }

        private static boolean b(long[] jArr) {
            return jArr.length == 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k.b.a, com.a.a.c.k.b.ao, com.a.a.c.o
        public void a(long[] jArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            if (jArr.length == 1 && a(aaVar)) {
                a(jArr, hVar);
            } else {
                hVar.a(jArr, 0, jArr.length);
            }
        }

        private static void a(long[] jArr, com.a.a.b.h hVar) {
            for (long j : jArr) {
                hVar.b(j);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ai$d.class */
    public static class d extends h<float[]> {
        @Override // com.a.a.c.k.b.a
        public final /* synthetic */ void b(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            a((float[]) obj, hVar);
        }

        @Override // com.a.a.c.k.j
        public final /* synthetic */ boolean a(Object obj) {
            return b((float[]) obj);
        }

        @Override // com.a.a.c.o
        public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
            return a((float[]) obj);
        }

        static {
            com.a.a.c.l.o.a().a(Float.TYPE);
        }

        public d() {
            super(float[].class);
        }

        private d(d dVar, com.a.a.c.c cVar, Boolean bool) {
            super(dVar, cVar, bool);
        }

        @Override // com.a.a.c.k.b.a
        public final com.a.a.c.o<?> a(com.a.a.c.c cVar, Boolean bool) {
            return new d(this, cVar, bool);
        }

        private static boolean a(float[] fArr) {
            return fArr.length == 0;
        }

        private static boolean b(float[] fArr) {
            return fArr.length == 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k.b.a, com.a.a.c.k.b.ao, com.a.a.c.o
        public void a(float[] fArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            int length = fArr.length;
            if (length == 1 && a(aaVar)) {
                a(fArr, hVar);
                return;
            }
            hVar.a(fArr, length);
            a(fArr, hVar);
            hVar.h();
        }

        private static void a(float[] fArr, com.a.a.b.h hVar) {
            for (float f : fArr) {
                hVar.a(f);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/ai$c.class */
    public static class c extends com.a.a.c.k.b.a<double[]> {
        @Override // com.a.a.c.k.b.a
        public final /* synthetic */ void b(double[] dArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            a(dArr, hVar);
        }

        @Override // com.a.a.c.k.j
        public final /* synthetic */ boolean a(Object obj) {
            return b((double[]) obj);
        }

        @Override // com.a.a.c.o
        public final /* bridge */ /* synthetic */ boolean a(com.a.a.c.aa aaVar, Object obj) {
            return a((double[]) obj);
        }

        static {
            com.a.a.c.l.o.a().a(Double.TYPE);
        }

        public c() {
            super(double[].class);
        }

        private c(c cVar, com.a.a.c.c cVar2, Boolean bool) {
            super(cVar, cVar2, bool);
        }

        @Override // com.a.a.c.k.b.a
        public final com.a.a.c.o<?> a(com.a.a.c.c cVar, Boolean bool) {
            return new c(this, cVar, bool);
        }

        @Override // com.a.a.c.k.j
        public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
            return this;
        }

        private static boolean a(double[] dArr) {
            return dArr.length == 0;
        }

        private static boolean b(double[] dArr) {
            return dArr.length == 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k.b.a, com.a.a.c.k.b.ao, com.a.a.c.o
        public void a(double[] dArr, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            if (dArr.length == 1 && a(aaVar)) {
                a(dArr, hVar);
            } else {
                hVar.a(dArr, 0, dArr.length);
            }
        }

        private static void a(double[] dArr, com.a.a.b.h hVar) {
            for (double d : dArr) {
                hVar.a(d);
            }
        }
    }
}
