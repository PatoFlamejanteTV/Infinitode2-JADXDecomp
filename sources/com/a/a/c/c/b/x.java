package com.a.a.c.c.b;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/x.class */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    private static final HashSet<String> f371a = new HashSet<>();

    static {
        Class[] clsArr = {Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, BigInteger.class};
        for (int i2 = 0; i2 < 11; i2++) {
            f371a.add(clsArr[i2].getName());
        }
    }

    public static com.a.a.c.k<?> a(Class<?> cls, String str) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return h.f384a;
            }
            if (cls == Boolean.TYPE) {
                return c.f374a;
            }
            if (cls == Long.TYPE) {
                return i.f386a;
            }
            if (cls == Double.TYPE) {
                return f.f380a;
            }
            if (cls == Character.TYPE) {
                return e.f378a;
            }
            if (cls == Byte.TYPE) {
                return d.f376a;
            }
            if (cls == Short.TYPE) {
                return l.f391a;
            }
            if (cls == Float.TYPE) {
                return g.f382a;
            }
            if (cls == Void.TYPE) {
                return w.f370a;
            }
        } else if (f371a.contains(str)) {
            if (cls == Integer.class) {
                return h.f385b;
            }
            if (cls == Boolean.class) {
                return c.f375b;
            }
            if (cls == Long.class) {
                return i.f387b;
            }
            if (cls == Double.class) {
                return f.f381b;
            }
            if (cls == Character.class) {
                return e.f379b;
            }
            if (cls == Byte.class) {
                return d.f377b;
            }
            if (cls == Short.class) {
                return l.f392b;
            }
            if (cls == Float.class) {
                return g.f383b;
            }
            if (cls == Number.class) {
                return j.f388a;
            }
            if (cls == BigDecimal.class) {
                return a.f372a;
            }
            if (cls == BigInteger.class) {
                return b.f373a;
            }
        } else {
            return null;
        }
        throw new IllegalArgumentException("Internal error: can't find deserializer for " + cls.getName());
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$k.class */
    public static abstract class k<T> extends ai<T> {

        /* renamed from: a, reason: collision with root package name */
        private com.a.a.c.l.f f389a;

        /* renamed from: b, reason: collision with root package name */
        private T f390b;
        private T d;
        protected final boolean c;

        protected k(Class<T> cls, com.a.a.c.l.f fVar, T t, T t2) {
            super((Class<?>) cls);
            this.f389a = fVar;
            this.f390b = t;
            this.d = t2;
            this.c = cls.isPrimitive();
        }

        @Override // com.a.a.c.k, com.a.a.c.c.s
        public final T a(com.a.a.c.g gVar) {
            if (this.c && gVar.a(com.a.a.c.i.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                gVar.a(this, "Cannot map `null` into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", com.a.a.c.m.i.d((Object) a()));
            }
            return this.f390b;
        }

        @Override // com.a.a.c.k
        public Object c(com.a.a.c.g gVar) {
            return this.d;
        }

        @Override // com.a.a.c.c.b.ai, com.a.a.c.k
        public final com.a.a.c.l.f b() {
            return this.f389a;
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$c.class */
    public static final class c extends k<Boolean> {

        /* renamed from: a, reason: collision with root package name */
        static final c f374a = new c(Boolean.TYPE, Boolean.FALSE);

        /* renamed from: b, reason: collision with root package name */
        static final c f375b = new c(Boolean.class, null);

        @Override // com.a.a.c.c.b.x.k, com.a.a.c.k
        public final /* bridge */ /* synthetic */ Object c(com.a.a.c.g gVar) {
            return super.c(gVar);
        }

        @Override // com.a.a.c.c.b.ai, com.a.a.c.c.b.ae, com.a.a.c.k
        public final /* synthetic */ Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
            return e(lVar, gVar);
        }

        private c(Class<Boolean> cls, Boolean bool) {
            super(cls, com.a.a.c.l.f.Boolean, bool, Boolean.FALSE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Boolean a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            com.a.a.b.o k = lVar.k();
            if (k == com.a.a.b.o.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (k == com.a.a.b.o.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (this.c) {
                return Boolean.valueOf(n(lVar, gVar));
            }
            return a(lVar, gVar, this.s);
        }

        private Boolean e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            com.a.a.b.o k = lVar.k();
            if (k == com.a.a.b.o.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (k == com.a.a.b.o.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (this.c) {
                return Boolean.valueOf(n(lVar, gVar));
            }
            return a(lVar, gVar, this.s);
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$d.class */
    public static class d extends k<Byte> {

        /* renamed from: a, reason: collision with root package name */
        static final d f376a = new d(Byte.TYPE, (byte) 0);

        /* renamed from: b, reason: collision with root package name */
        static final d f377b = new d(Byte.class, null);

        @Override // com.a.a.c.c.b.x.k, com.a.a.c.k
        public final /* bridge */ /* synthetic */ Object c(com.a.a.c.g gVar) {
            return super.c(gVar);
        }

        private d(Class<Byte> cls, Byte b2) {
            super(cls, com.a.a.c.l.f.Integer, b2, (byte) 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Byte a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (lVar.r()) {
                return Byte.valueOf(lVar.E());
            }
            if (this.c) {
                return Byte.valueOf(o(lVar, gVar));
            }
            return e(lVar, gVar);
        }

        private Byte e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            String a2;
            switch (lVar.l()) {
                case 1:
                    a2 = gVar.a(lVar, this.s);
                    break;
                case 2:
                case 4:
                case 5:
                case 9:
                case 10:
                default:
                    return (Byte) gVar.a(e(gVar), lVar);
                case 3:
                    return d(lVar, gVar);
                case 6:
                    a2 = lVar.w();
                    break;
                case 7:
                    return Byte.valueOf(lVar.E());
                case 8:
                    com.a.a.c.b.b d = d(lVar, gVar, this.s);
                    if (d == com.a.a.c.b.b.AsNull) {
                        return a(gVar);
                    }
                    if (d == com.a.a.c.b.b.AsEmpty) {
                        return (Byte) c(gVar);
                    }
                    return Byte.valueOf(lVar.E());
                case 11:
                    return a(gVar);
            }
            com.a.a.c.b.b a3 = a(gVar, a2);
            if (a3 == com.a.a.c.b.b.AsNull) {
                return a(gVar);
            }
            if (a3 == com.a.a.c.b.b.AsEmpty) {
                return (Byte) c(gVar);
            }
            String trim = a2.trim();
            if (b(gVar, trim)) {
                return a(gVar);
            }
            try {
                int a4 = com.a.a.b.c.h.a(trim);
                if (a(a4)) {
                    return (Byte) gVar.b(this.s, trim, "overflow, value cannot be represented as 8-bit value", new Object[0]);
                }
                return Byte.valueOf((byte) a4);
            } catch (IllegalArgumentException unused) {
                return (Byte) gVar.b(this.s, trim, "not a valid Byte value", new Object[0]);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$l.class */
    public static class l extends k<Short> {

        /* renamed from: a, reason: collision with root package name */
        static final l f391a = new l(Short.TYPE, 0);

        /* renamed from: b, reason: collision with root package name */
        static final l f392b = new l(Short.class, null);

        @Override // com.a.a.c.c.b.x.k, com.a.a.c.k
        public final /* bridge */ /* synthetic */ Object c(com.a.a.c.g gVar) {
            return super.c(gVar);
        }

        private l(Class<Short> cls, Short sh) {
            super(cls, com.a.a.c.l.f.Integer, sh, (short) 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Short a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (lVar.r()) {
                return Short.valueOf(lVar.F());
            }
            if (this.c) {
                return Short.valueOf(p(lVar, gVar));
            }
            return e(lVar, gVar);
        }

        private Short e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            String a2;
            switch (lVar.l()) {
                case 1:
                    a2 = gVar.a(lVar, this.s);
                    break;
                case 2:
                case 4:
                case 5:
                case 9:
                case 10:
                default:
                    return (Short) gVar.a(e(gVar), lVar);
                case 3:
                    return d(lVar, gVar);
                case 6:
                    a2 = lVar.w();
                    break;
                case 7:
                    return Short.valueOf(lVar.F());
                case 8:
                    com.a.a.c.b.b d = d(lVar, gVar, this.s);
                    if (d == com.a.a.c.b.b.AsNull) {
                        return a(gVar);
                    }
                    if (d == com.a.a.c.b.b.AsEmpty) {
                        return (Short) c(gVar);
                    }
                    return Short.valueOf(lVar.F());
                case 11:
                    return a(gVar);
            }
            com.a.a.c.b.b a3 = a(gVar, a2);
            if (a3 == com.a.a.c.b.b.AsNull) {
                return a(gVar);
            }
            if (a3 == com.a.a.c.b.b.AsEmpty) {
                return (Short) c(gVar);
            }
            String trim = a2.trim();
            if (b(gVar, trim)) {
                return a(gVar);
            }
            try {
                int a4 = com.a.a.b.c.h.a(trim);
                if (b(a4)) {
                    return (Short) gVar.b(this.s, trim, "overflow, value cannot be represented as 16-bit value", new Object[0]);
                }
                return Short.valueOf((short) a4);
            } catch (IllegalArgumentException unused) {
                return (Short) gVar.b(this.s, trim, "not a valid Short value", new Object[0]);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$e.class */
    public static class e extends k<Character> {

        /* renamed from: a, reason: collision with root package name */
        static final e f378a = new e(Character.TYPE, 0);

        /* renamed from: b, reason: collision with root package name */
        static final e f379b = new e(Character.class, null);

        @Override // com.a.a.c.c.b.x.k, com.a.a.c.k
        public final /* bridge */ /* synthetic */ Object c(com.a.a.c.g gVar) {
            return super.c(gVar);
        }

        private e(Class<Character> cls, Character ch) {
            super(cls, com.a.a.c.l.f.Integer, ch, (char) 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Failed to find 'out' block for switch in B:24:0x0060. Please report as an issue. */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Character a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            String a2;
            switch (lVar.l()) {
                case 1:
                    a2 = gVar.a(lVar, this.s);
                    break;
                case 2:
                case 4:
                case 5:
                case 8:
                case 9:
                case 10:
                default:
                    return (Character) gVar.a(e(gVar), lVar);
                case 3:
                    return d(lVar, gVar);
                case 6:
                    a2 = lVar.w();
                    break;
                case 7:
                    com.a.a.c.b.b a3 = gVar.a(b(), this.s, com.a.a.c.b.f.Integer);
                    switch (a3) {
                        case Fail:
                            a(gVar, a3, this.s, lVar.B(), "Integer value (" + lVar.w() + ")");
                        case AsNull:
                            return a(gVar);
                        case AsEmpty:
                            return (Character) c(gVar);
                        default:
                            int G = lVar.G();
                            if (G >= 0 && G <= 65535) {
                                return Character.valueOf((char) G);
                            }
                            return (Character) gVar.a(a(), (Number) Integer.valueOf(G), "value outside valid Character range (0x0000 - 0xFFFF)", new Object[0]);
                    }
                    break;
                case 11:
                    if (this.c) {
                        f(gVar);
                    }
                    return a(gVar);
            }
            if (a2.length() == 1) {
                return Character.valueOf(a2.charAt(0));
            }
            com.a.a.c.b.b a4 = a(gVar, a2);
            if (a4 == com.a.a.c.b.b.AsNull) {
                return a(gVar);
            }
            if (a4 == com.a.a.c.b.b.AsEmpty) {
                return (Character) c(gVar);
            }
            String trim = a2.trim();
            if (b(gVar, trim)) {
                return a(gVar);
            }
            return (Character) gVar.b(a(), trim, "Expected either Integer value code or 1-character String", new Object[0]);
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$h.class */
    public static final class h extends k<Integer> {

        /* renamed from: a, reason: collision with root package name */
        static final h f384a = new h(Integer.TYPE, 0);

        /* renamed from: b, reason: collision with root package name */
        static final h f385b = new h(Integer.class, null);

        @Override // com.a.a.c.c.b.x.k, com.a.a.c.k
        public final /* bridge */ /* synthetic */ Object c(com.a.a.c.g gVar) {
            return super.c(gVar);
        }

        @Override // com.a.a.c.c.b.ai, com.a.a.c.c.b.ae, com.a.a.c.k
        public final /* synthetic */ Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
            return e(lVar, gVar);
        }

        private h(Class<Integer> cls, Integer num) {
            super(cls, com.a.a.c.l.f.Integer, num, 0);
        }

        @Override // com.a.a.c.k
        public final boolean c() {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Integer a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (lVar.r()) {
                return Integer.valueOf(lVar.G());
            }
            if (this.c) {
                return Integer.valueOf(q(lVar, gVar));
            }
            return b(lVar, gVar, Integer.class);
        }

        private Integer e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (lVar.r()) {
                return Integer.valueOf(lVar.G());
            }
            if (this.c) {
                return Integer.valueOf(q(lVar, gVar));
            }
            return b(lVar, gVar, Integer.class);
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$i.class */
    public static final class i extends k<Long> {

        /* renamed from: a, reason: collision with root package name */
        static final i f386a = new i(Long.TYPE, 0L);

        /* renamed from: b, reason: collision with root package name */
        static final i f387b = new i(Long.class, null);

        @Override // com.a.a.c.c.b.x.k, com.a.a.c.k
        public final /* bridge */ /* synthetic */ Object c(com.a.a.c.g gVar) {
            return super.c(gVar);
        }

        private i(Class<Long> cls, Long l) {
            super(cls, com.a.a.c.l.f.Integer, l, 0L);
        }

        @Override // com.a.a.c.k
        public final boolean c() {
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Long a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (lVar.r()) {
                return Long.valueOf(lVar.H());
            }
            if (this.c) {
                return Long.valueOf(r(lVar, gVar));
            }
            return c(lVar, gVar, Long.class);
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$g.class */
    public static class g extends k<Float> {

        /* renamed from: a, reason: collision with root package name */
        static final g f382a = new g(Float.TYPE, Float.valueOf(0.0f));

        /* renamed from: b, reason: collision with root package name */
        static final g f383b = new g(Float.class, null);

        @Override // com.a.a.c.c.b.x.k, com.a.a.c.k
        public final /* bridge */ /* synthetic */ Object c(com.a.a.c.g gVar) {
            return super.c(gVar);
        }

        private g(Class<Float> cls, Float f) {
            super(cls, com.a.a.c.l.f.Float, f, Float.valueOf(0.0f));
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Float a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (lVar.a(com.a.a.b.o.VALUE_NUMBER_FLOAT)) {
                return Float.valueOf(lVar.J());
            }
            if (this.c) {
                return Float.valueOf(s(lVar, gVar));
            }
            return e(lVar, gVar);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:6:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x00b9  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.lang.Float e(com.a.a.b.l r7, com.a.a.c.g r8) {
            /*
                Method dump skipped, instructions count: 284
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.x.g.e(com.a.a.b.l, com.a.a.c.g):java.lang.Float");
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$f.class */
    public static class f extends k<Double> {

        /* renamed from: a, reason: collision with root package name */
        static final f f380a = new f(Double.TYPE, Double.valueOf(0.0d));

        /* renamed from: b, reason: collision with root package name */
        static final f f381b = new f(Double.class, null);

        @Override // com.a.a.c.c.b.x.k, com.a.a.c.k
        public final /* bridge */ /* synthetic */ Object c(com.a.a.c.g gVar) {
            return super.c(gVar);
        }

        @Override // com.a.a.c.c.b.ai, com.a.a.c.c.b.ae, com.a.a.c.k
        public final /* synthetic */ Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
            return e(lVar, gVar);
        }

        private f(Class<Double> cls, Double d) {
            super(cls, com.a.a.c.l.f.Float, d, Double.valueOf(0.0d));
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Double a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (lVar.a(com.a.a.b.o.VALUE_NUMBER_FLOAT)) {
                return Double.valueOf(lVar.K());
            }
            if (this.c) {
                return Double.valueOf(t(lVar, gVar));
            }
            return f(lVar, gVar);
        }

        private Double e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            if (lVar.a(com.a.a.b.o.VALUE_NUMBER_FLOAT)) {
                return Double.valueOf(lVar.K());
            }
            if (this.c) {
                return Double.valueOf(t(lVar, gVar));
            }
            return f(lVar, gVar);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:6:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x00b9  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.lang.Double f(com.a.a.b.l r7, com.a.a.c.g r8) {
            /*
                Method dump skipped, instructions count: 284
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.x.f.f(com.a.a.b.l, com.a.a.c.g):java.lang.Double");
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$j.class */
    public static class j extends ai<Object> {

        /* renamed from: a, reason: collision with root package name */
        public static final j f388a = new j();

        public j() {
            super((Class<?>) Number.class);
        }

        @Override // com.a.a.c.c.b.ai, com.a.a.c.k
        public final com.a.a.c.l.f b() {
            return com.a.a.c.l.f.Integer;
        }

        @Override // com.a.a.c.k
        public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            String a2;
            switch (lVar.l()) {
                case 1:
                    a2 = gVar.a(lVar, this.s);
                    break;
                case 2:
                case 4:
                case 5:
                default:
                    return gVar.a(e(gVar), lVar);
                case 3:
                    return d(lVar, gVar);
                case 6:
                    a2 = lVar.w();
                    break;
                case 7:
                    if (gVar.a(r)) {
                        return u(lVar, gVar);
                    }
                    return lVar.B();
                case 8:
                    if (gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS) && !lVar.s()) {
                        return lVar.L();
                    }
                    return lVar.B();
            }
            com.a.a.c.b.b a3 = a(gVar, a2);
            if (a3 == com.a.a.c.b.b.AsNull) {
                return a(gVar);
            }
            if (a3 == com.a.a.c.b.b.AsEmpty) {
                return c(gVar);
            }
            String trim = a2.trim();
            if (d(trim)) {
                return a(gVar);
            }
            if (f(trim)) {
                return Double.valueOf(Double.POSITIVE_INFINITY);
            }
            if (e(trim)) {
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            if (g(trim)) {
                return Double.valueOf(Double.NaN);
            }
            try {
                if (!i(trim)) {
                    if (gVar.a(com.a.a.c.i.USE_BIG_DECIMAL_FOR_FLOATS)) {
                        return com.a.a.b.c.h.d(trim);
                    }
                    return Double.valueOf(com.a.a.b.c.h.b(trim, lVar.a(com.a.a.b.t.USE_FAST_DOUBLE_PARSER)));
                }
                if (gVar.a(com.a.a.c.i.USE_BIG_INTEGER_FOR_INTS)) {
                    return com.a.a.b.c.h.e(trim);
                }
                long b2 = com.a.a.b.c.h.b(trim);
                if (!gVar.a(com.a.a.c.i.USE_LONG_FOR_INTS) && b2 <= 2147483647L && b2 >= -2147483648L) {
                    return Integer.valueOf((int) b2);
                }
                return Long.valueOf(b2);
            } catch (IllegalArgumentException unused) {
                return gVar.b(this.s, trim, "not a valid number", new Object[0]);
            }
        }

        @Override // com.a.a.c.c.b.ai, com.a.a.c.c.b.ae, com.a.a.c.k
        public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
            switch (lVar.l()) {
                case 6:
                case 7:
                case 8:
                    return a(lVar, gVar);
                default:
                    return eVar.c(lVar, gVar);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$b.class */
    public static class b extends ai<BigInteger> {

        /* renamed from: a, reason: collision with root package name */
        public static final b f373a = new b();

        public b() {
            super((Class<?>) BigInteger.class);
        }

        @Override // com.a.a.c.k
        public final Object c(com.a.a.c.g gVar) {
            return BigInteger.ZERO;
        }

        @Override // com.a.a.c.c.b.ai, com.a.a.c.k
        public final com.a.a.c.l.f b() {
            return com.a.a.c.l.f.Integer;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public BigInteger a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            String a2;
            if (lVar.r()) {
                return lVar.I();
            }
            switch (lVar.l()) {
                case 1:
                    a2 = gVar.a(lVar, this.s);
                    break;
                case 2:
                case 4:
                case 5:
                case 7:
                default:
                    return (BigInteger) gVar.a(e(gVar), lVar);
                case 3:
                    return d(lVar, gVar);
                case 6:
                    a2 = lVar.w();
                    break;
                case 8:
                    com.a.a.c.b.b d = d(lVar, gVar, this.s);
                    if (d == com.a.a.c.b.b.AsNull) {
                        return a(gVar);
                    }
                    if (d == com.a.a.c.b.b.AsEmpty) {
                        return (BigInteger) c(gVar);
                    }
                    return lVar.L().toBigInteger();
            }
            com.a.a.c.b.b a3 = a(gVar, a2);
            if (a3 == com.a.a.c.b.b.AsNull) {
                return a(gVar);
            }
            if (a3 == com.a.a.c.b.b.AsEmpty) {
                return (BigInteger) c(gVar);
            }
            String trim = a2.trim();
            if (d(trim)) {
                return a(gVar);
            }
            try {
                return com.a.a.b.c.h.e(trim);
            } catch (IllegalArgumentException unused) {
                return (BigInteger) gVar.b(this.s, trim, "not a valid representation", new Object[0]);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/x$a.class */
    public static class a extends ai<BigDecimal> {

        /* renamed from: a, reason: collision with root package name */
        public static final a f372a = new a();

        public a() {
            super((Class<?>) BigDecimal.class);
        }

        @Override // com.a.a.c.k
        public final Object c(com.a.a.c.g gVar) {
            return BigDecimal.ZERO;
        }

        @Override // com.a.a.c.c.b.ai, com.a.a.c.k
        public final com.a.a.c.l.f b() {
            return com.a.a.c.l.f.Float;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:17:0x009f  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x00a8  */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.math.BigDecimal a(com.a.a.b.l r7, com.a.a.c.g r8) {
            /*
                r6 = this;
                r0 = r7
                int r0 = r0.l()
                switch(r0) {
                    case 1: goto L6c;
                    case 2: goto L83;
                    case 3: goto L79;
                    case 4: goto L83;
                    case 5: goto L83;
                    case 6: goto L64;
                    case 7: goto L34;
                    case 8: goto L5f;
                    default: goto L83;
                }
            L34:
                r0 = r6
                r1 = r7
                r2 = r8
                r3 = r6
                java.lang.Class<?> r3 = r3.s
                com.a.a.c.b.b r0 = r0.e(r1, r2, r3)
                r1 = r0
                r9 = r1
                com.a.a.c.b.b r1 = com.a.a.c.b.b.AsNull
                if (r0 != r1) goto L4f
                r0 = r6
                r1 = r8
                java.lang.Object r0 = r0.a(r1)
                java.math.BigDecimal r0 = (java.math.BigDecimal) r0
                return r0
            L4f:
                r0 = r9
                com.a.a.c.b.b r1 = com.a.a.c.b.b.AsEmpty
                if (r0 != r1) goto L5f
                r0 = r6
                r1 = r8
                java.lang.Object r0 = r0.c(r1)
                java.math.BigDecimal r0 = (java.math.BigDecimal) r0
                return r0
            L5f:
                r0 = r7
                java.math.BigDecimal r0 = r0.L()
                return r0
            L64:
                r0 = r7
                java.lang.String r0 = r0.w()
                r7 = r0
                goto L91
            L6c:
                r0 = r8
                r1 = r7
                r2 = r6
                java.lang.Class<?> r2 = r2.s
                java.lang.String r0 = r0.a(r1, r2)
                r7 = r0
                goto L91
            L79:
                r0 = r6
                r1 = r7
                r2 = r8
                java.lang.Object r0 = r0.d(r1, r2)
                java.math.BigDecimal r0 = (java.math.BigDecimal) r0
                return r0
            L83:
                r0 = r8
                r1 = r6
                r2 = r8
                com.a.a.c.j r1 = r1.e(r2)
                r2 = r7
                java.lang.Object r0 = r0.a(r1, r2)
                java.math.BigDecimal r0 = (java.math.BigDecimal) r0
                return r0
            L91:
                r0 = r6
                r1 = r8
                r2 = r7
                com.a.a.c.b.b r0 = r0.a(r1, r2)
                r1 = r0
                r9 = r1
                com.a.a.c.b.b r1 = com.a.a.c.b.b.AsNull
                if (r0 != r1) goto La8
                r0 = r6
                r1 = r8
                java.lang.Object r0 = r0.a(r1)
                java.math.BigDecimal r0 = (java.math.BigDecimal) r0
                return r0
            La8:
                r0 = r9
                com.a.a.c.b.b r1 = com.a.a.c.b.b.AsEmpty
                if (r0 != r1) goto Lb8
                r0 = r6
                r1 = r8
                java.lang.Object r0 = r0.c(r1)
                java.math.BigDecimal r0 = (java.math.BigDecimal) r0
                return r0
            Lb8:
                r0 = r7
                java.lang.String r0 = r0.trim()
                r1 = r0
                r7 = r1
                boolean r0 = d(r0)
                if (r0 == 0) goto Lcd
                r0 = r6
                r1 = r8
                java.lang.Object r0 = r0.a(r1)
                java.math.BigDecimal r0 = (java.math.BigDecimal) r0
                return r0
            Lcd:
                r0 = r7
                java.math.BigDecimal r0 = com.a.a.b.c.h.d(r0)     // Catch: java.lang.IllegalArgumentException -> Ld2
                return r0
            Ld2:
                r0 = r8
                r1 = r6
                java.lang.Class<?> r1 = r1.s
                r2 = r7
                java.lang.String r3 = "not a valid representation"
                r4 = 0
                java.lang.Object[] r4 = new java.lang.Object[r4]
                java.lang.Object r0 = r0.b(r1, r2, r3, r4)
                java.math.BigDecimal r0 = (java.math.BigDecimal) r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.x.a.a(com.a.a.b.l, com.a.a.c.g):java.math.BigDecimal");
        }
    }
}
