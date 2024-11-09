package com.a.a.c.c.b;

import com.a.a.a.l;
import com.a.a.b.l;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/ae.class */
public abstract class ae<T> extends com.a.a.c.k<T> implements Serializable {
    protected static final int r = com.a.a.c.i.USE_BIG_INTEGER_FOR_INTS.b() | com.a.a.c.i.USE_LONG_FOR_INTS.b();
    protected final Class<?> s;

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.j f310a;

    static {
        com.a.a.c.i.UNWRAP_SINGLE_VALUE_ARRAYS.b();
        com.a.a.c.i.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ae(Class<?> cls) {
        this.s = cls;
        this.f310a = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ae(com.a.a.c.j jVar) {
        this.s = jVar == null ? Object.class : jVar.b();
        this.f310a = jVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ae(ae<?> aeVar) {
        this.s = aeVar.s;
        this.f310a = aeVar.f310a;
    }

    @Override // com.a.a.c.k
    public Class<?> a() {
        return this.s;
    }

    public com.a.a.c.j h() {
        return this.f310a;
    }

    public final com.a.a.c.j e(com.a.a.c.g gVar) {
        if (this.f310a != null) {
            return this.f310a;
        }
        return gVar.b(this.s);
    }

    public com.a.a.c.c.x i() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(com.a.a.c.k<?> kVar) {
        return com.a.a.c.m.i.e(kVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(com.a.a.c.p pVar) {
        return com.a.a.c.m.i.e(pVar);
    }

    @Override // com.a.a.c.k
    public Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.d(lVar, gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T d(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.c.b.b h = h(gVar);
        boolean a2 = gVar.a(com.a.a.c.i.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (a2 || h != com.a.a.c.b.b.Fail) {
            if (lVar.g() == com.a.a.b.o.END_ARRAY) {
                switch (af.f311a[h.ordinal()]) {
                    case 1:
                        return (T) c(gVar);
                    case 2:
                    case 3:
                        return a(gVar);
                }
            }
            if (a2) {
                T c = c(lVar, gVar);
                if (lVar.g() != com.a.a.b.o.END_ARRAY) {
                    j(gVar);
                }
                return c;
            }
        }
        return (T) gVar.a(e(gVar), com.a.a.b.o.START_ARRAY, lVar, (String) null, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final T m(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.c.c.x i = i();
        Class<?> a2 = a();
        String R = lVar.R();
        if (i != null && i.e()) {
            return (T) i.a(gVar, R);
        }
        if (R.isEmpty()) {
            return (T) a(gVar, gVar.a(b(), a2, com.a.a.c.b.f.EmptyString), a2);
        }
        if (h(R)) {
            return (T) a(gVar, gVar.a(b(), a2, com.a.a.c.b.b.Fail), a2);
        }
        if (i != null) {
            R = R.trim();
            if (i.f() && gVar.a(com.a.a.c.l.f.Integer, Integer.class, com.a.a.c.b.f.String) == com.a.a.c.b.b.TryConvert) {
                return (T) i.a(gVar, c(gVar, R));
            }
            if (i.g() && gVar.a(com.a.a.c.l.f.Integer, Long.class, com.a.a.c.b.f.String) == com.a.a.c.b.b.TryConvert) {
                return (T) i.a(gVar, e(gVar, R));
            }
            if (i.k() && gVar.a(com.a.a.c.l.f.Boolean, Boolean.class, com.a.a.c.b.f.String) == com.a.a.c.b.b.TryConvert) {
                String trim = R.trim();
                if ("true".equals(trim)) {
                    return (T) i.a(gVar, true);
                }
                if ("false".equals(trim)) {
                    return (T) i.a(gVar, false);
                }
            }
        }
        return (T) gVar.a(a2, i, gVar.j(), "no String-argument constructor/factory method to deserialize from String value ('%s')", R);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object a(com.a.a.c.g gVar, com.a.a.c.b.b bVar, Class<?> cls) {
        switch (af.f311a[bVar.ordinal()]) {
            case 1:
                return c(gVar);
            case 2:
            case 3:
            default:
                return null;
            case 4:
                a(gVar, bVar, cls, "", "empty String (\"\")");
                return null;
        }
    }

    private T c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.a(com.a.a.b.o.START_ARRAY)) {
            return (T) f(lVar, gVar);
        }
        return a(lVar, gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean n(com.a.a.b.l r7, com.a.a.c.g r8) {
        /*
            Method dump skipped, instructions count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.ae.n(com.a.a.b.l, com.a.a.c.g):boolean");
    }

    private static boolean j(String str) {
        char charAt = str.charAt(0);
        if (charAt == 't') {
            return "true".equals(str);
        }
        if (charAt == 'T') {
            return "TRUE".equals(str) || "True".equals(str);
        }
        return false;
    }

    private static boolean k(String str) {
        char charAt = str.charAt(0);
        if (charAt == 'f') {
            return "false".equals(str);
        }
        if (charAt == 'F') {
            return "FALSE".equals(str) || "False".equals(str);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Boolean a(com.a.a.b.l lVar, com.a.a.c.g gVar, Class<?> cls) {
        String a2;
        switch (lVar.l()) {
            case 1:
                a2 = gVar.a(lVar, cls);
                break;
            case 2:
            case 4:
            case 5:
            case 8:
            default:
                return (Boolean) gVar.a(cls, lVar);
            case 3:
                return (Boolean) d(lVar, gVar);
            case 6:
                a2 = lVar.w();
                break;
            case 7:
                return i(lVar, gVar, cls);
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
        }
        com.a.a.c.b.b a3 = a(gVar, a2, com.a.a.c.l.f.Boolean, cls);
        if (a3 == com.a.a.c.b.b.AsNull) {
            return null;
        }
        if (a3 == com.a.a.c.b.b.AsEmpty) {
            return Boolean.FALSE;
        }
        String trim = a2.trim();
        int length = trim.length();
        if (length == 4) {
            if (j(trim)) {
                return Boolean.TRUE;
            }
        } else if (length == 5 && k(trim)) {
            return Boolean.FALSE;
        }
        if (b(gVar, trim)) {
            return null;
        }
        return (Boolean) gVar.b(cls, trim, "only \"true\" or \"false\" recognized", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte o(com.a.a.b.l r7, com.a.a.c.g r8) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.ae.o(com.a.a.b.l, com.a.a.c.g):byte");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final short p(com.a.a.b.l r7, com.a.a.c.g r8) {
        /*
            Method dump skipped, instructions count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.ae.p(com.a.a.b.l, com.a.a.c.g):short");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int q(com.a.a.b.l r7, com.a.a.c.g r8) {
        /*
            Method dump skipped, instructions count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.ae.q(com.a.a.b.l, com.a.a.c.g):int");
    }

    private int c(com.a.a.c.g gVar, String str) {
        try {
            if (str.length() > 9) {
                long b2 = com.a.a.b.c.h.b(str);
                if (a(b2)) {
                    return a((Number) gVar.b(Integer.TYPE, str, "Overflow: numeric value (%s) out of range of int (%d -%d)", str, Integer.MIN_VALUE, Integer.MAX_VALUE)).intValue();
                }
                return (int) b2;
            }
            return com.a.a.b.c.h.a(str);
        } catch (IllegalArgumentException unused) {
            return a((Number) gVar.b(Integer.TYPE, str, "not a valid `int` value", new Object[0])).intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Integer b(com.a.a.b.l lVar, com.a.a.c.g gVar, Class<?> cls) {
        String a2;
        switch (lVar.l()) {
            case 1:
                a2 = gVar.a(lVar, cls);
                break;
            case 2:
            case 4:
            case 5:
            case 9:
            case 10:
            default:
                return (Integer) gVar.a(e(gVar), lVar);
            case 3:
                return (Integer) d(lVar, gVar);
            case 6:
                a2 = lVar.w();
                break;
            case 7:
                return Integer.valueOf(lVar.G());
            case 8:
                com.a.a.c.b.b d = d(lVar, gVar, cls);
                if (d == com.a.a.c.b.b.AsNull) {
                    return (Integer) a(gVar);
                }
                if (d == com.a.a.c.b.b.AsEmpty) {
                    return (Integer) c(gVar);
                }
                return Integer.valueOf(lVar.P());
            case 11:
                return (Integer) a(gVar);
        }
        com.a.a.c.b.b a3 = a(gVar, a2);
        if (a3 == com.a.a.c.b.b.AsNull) {
            return (Integer) a(gVar);
        }
        if (a3 == com.a.a.c.b.b.AsEmpty) {
            return (Integer) c(gVar);
        }
        String trim = a2.trim();
        if (b(gVar, trim)) {
            return (Integer) a(gVar);
        }
        return d(gVar, trim);
    }

    private Integer d(com.a.a.c.g gVar, String str) {
        try {
            if (str.length() > 9) {
                long b2 = com.a.a.b.c.h.b(str);
                if (a(b2)) {
                    return (Integer) gVar.b(Integer.class, str, "Overflow: numeric value (%s) out of range of `java.lang.Integer` (%d -%d)", str, Integer.MIN_VALUE, Integer.MAX_VALUE);
                }
                return Integer.valueOf((int) b2);
            }
            return Integer.valueOf(com.a.a.b.c.h.a(str));
        } catch (IllegalArgumentException unused) {
            return (Integer) gVar.b(Integer.class, str, "not a valid `java.lang.Integer` value", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long r(com.a.a.b.l r7, com.a.a.c.g r8) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.ae.r(com.a.a.b.l, com.a.a.c.g):long");
    }

    private long e(com.a.a.c.g gVar, String str) {
        try {
            return com.a.a.b.c.h.b(str);
        } catch (IllegalArgumentException unused) {
            return a((Number) gVar.b(Long.TYPE, str, "not a valid `long` value", new Object[0])).longValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Long c(com.a.a.b.l lVar, com.a.a.c.g gVar, Class<?> cls) {
        String a2;
        switch (lVar.l()) {
            case 1:
                a2 = gVar.a(lVar, cls);
                break;
            case 2:
            case 4:
            case 5:
            case 9:
            case 10:
            default:
                return (Long) gVar.a(e(gVar), lVar);
            case 3:
                return (Long) d(lVar, gVar);
            case 6:
                a2 = lVar.w();
                break;
            case 7:
                return Long.valueOf(lVar.H());
            case 8:
                com.a.a.c.b.b d = d(lVar, gVar, cls);
                if (d == com.a.a.c.b.b.AsNull) {
                    return (Long) a(gVar);
                }
                if (d == com.a.a.c.b.b.AsEmpty) {
                    return (Long) c(gVar);
                }
                return Long.valueOf(lVar.Q());
            case 11:
                return (Long) a(gVar);
        }
        com.a.a.c.b.b a3 = a(gVar, a2);
        if (a3 == com.a.a.c.b.b.AsNull) {
            return (Long) a(gVar);
        }
        if (a3 == com.a.a.c.b.b.AsEmpty) {
            return (Long) c(gVar);
        }
        String trim = a2.trim();
        if (b(gVar, trim)) {
            return (Long) a(gVar);
        }
        return f(gVar, trim);
    }

    private static Long f(com.a.a.c.g gVar, String str) {
        try {
            return Long.valueOf(com.a.a.b.c.h.b(str));
        } catch (IllegalArgumentException unused) {
            return (Long) gVar.b(Long.class, str, "not a valid `java.lang.Long` value", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float s(com.a.a.b.l r7, com.a.a.c.g r8) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.ae.s(com.a.a.b.l, com.a.a.c.g):float");
    }

    private float a(com.a.a.b.l lVar, com.a.a.c.g gVar, String str) {
        try {
            return com.a.a.b.c.h.c(str, lVar.a(com.a.a.b.t.USE_FAST_DOUBLE_PARSER));
        } catch (IllegalArgumentException unused) {
            return a((Number) gVar.b(Float.TYPE, str, "not a valid `float` value", new Object[0])).floatValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Float b(String str) {
        if (!str.isEmpty()) {
            switch (str.charAt(0)) {
                case '-':
                    if (e(str)) {
                        return Float.valueOf(Float.NEGATIVE_INFINITY);
                    }
                    return null;
                case 'I':
                    if (f(str)) {
                        return Float.valueOf(Float.POSITIVE_INFINITY);
                    }
                    return null;
                case 'N':
                    if (g(str)) {
                        return Float.valueOf(Float.NaN);
                    }
                    return null;
                default:
                    return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:6:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double t(com.a.a.b.l r7, com.a.a.c.g r8) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.ae.t(com.a.a.b.l, com.a.a.c.g):double");
    }

    private double b(com.a.a.b.l lVar, com.a.a.c.g gVar, String str) {
        try {
            return a(str, lVar.a(com.a.a.b.t.USE_FAST_DOUBLE_PARSER));
        } catch (IllegalArgumentException unused) {
            return a((Number) gVar.b(Double.TYPE, str, "not a valid `double` value (as String to convert)", new Object[0])).doubleValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final double a(String str, boolean z) {
        return com.a.a.b.c.h.b(str, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Double c(String str) {
        if (!str.isEmpty()) {
            switch (str.charAt(0)) {
                case '-':
                    if (e(str)) {
                        return Double.valueOf(Double.NEGATIVE_INFINITY);
                    }
                    return null;
                case 'I':
                    if (f(str)) {
                        return Double.valueOf(Double.POSITIVE_INFINITY);
                    }
                    return null;
                case 'N':
                    if (g(str)) {
                        return Double.valueOf(Double.NaN);
                    }
                    return null;
                default:
                    return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Date a_(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        long longValue;
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
                return (Date) gVar.a(this.s, lVar);
            case 3:
                return e(lVar, gVar);
            case 6:
                a2 = lVar.w();
                break;
            case 7:
                try {
                    longValue = lVar.H();
                } catch (com.a.a.b.b.b unused) {
                    longValue = ((Number) gVar.a(this.s, lVar.B(), "not a valid 64-bit `long` for creating `java.util.Date`", new Object[0])).longValue();
                }
                return new Date(longValue);
            case 11:
                return (Date) a(gVar);
        }
        return a(a2.trim(), gVar);
    }

    private Date e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        com.a.a.c.b.b h = h(gVar);
        boolean a2 = gVar.a(com.a.a.c.i.UNWRAP_SINGLE_VALUE_ARRAYS);
        if (a2 || h != com.a.a.c.b.b.Fail) {
            com.a.a.b.o g = lVar.g();
            if (g == com.a.a.b.o.END_ARRAY) {
                switch (af.f311a[h.ordinal()]) {
                    case 1:
                        return (Date) c(gVar);
                    case 2:
                    case 3:
                        return (Date) a(gVar);
                }
            }
            if (a2) {
                if (g == com.a.a.b.o.START_ARRAY) {
                    return (Date) f(lVar, gVar);
                }
                Date a_ = a_(lVar, gVar);
                g(lVar, gVar);
                return a_;
            }
        }
        return (Date) gVar.a(this.s, com.a.a.b.o.START_ARRAY, lVar, (String) null, new Object[0]);
    }

    private Date a(String str, com.a.a.c.g gVar) {
        try {
            if (str.isEmpty()) {
                switch (af.f311a[a(gVar, str).ordinal()]) {
                    case 1:
                        return new Date(0L);
                    default:
                        return null;
                }
            }
            if (d(str)) {
                return null;
            }
            return gVar.c(str);
        } catch (IllegalArgumentException e) {
            return (Date) gVar.b(this.s, str, "not a valid representation (error: %s)", com.a.a.c.m.i.g(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.c.s sVar) {
        String R;
        com.a.a.c.b.b bVar = com.a.a.c.b.b.TryConvert;
        switch (lVar.l()) {
            case 1:
                return gVar.a(lVar, this.s);
            case 6:
                return lVar.w();
            case 7:
                bVar = f(lVar, gVar, this.s);
                break;
            case 8:
                bVar = g(lVar, gVar, this.s);
                break;
            case 9:
            case 10:
                bVar = h(lVar, gVar, this.s);
                break;
            case 12:
                Object N = lVar.N();
                if (N instanceof byte[]) {
                    return gVar.k().a((byte[]) N, false);
                }
                if (N == null) {
                    return null;
                }
                return N.toString();
        }
        if (bVar == com.a.a.c.b.b.AsNull) {
            return (String) sVar.a(gVar);
        }
        if (bVar == com.a.a.c.b.b.AsEmpty) {
            return "";
        }
        if (lVar.k().g() && (R = lVar.R()) != null) {
            return R;
        }
        return (String) gVar.a(e(gVar), lVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean d(String str) {
        return "null".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean e(String str) {
        return "-Infinity".equals(str) || "-INF".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean f(String str) {
        return "Infinity".equals(str) || "INF".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean g(String str) {
        return "NaN".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean h(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > ' ') {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.b.b a(com.a.a.c.g gVar, String str) {
        return a(gVar, str, b(), a());
    }

    private com.a.a.c.b.b a(com.a.a.c.g gVar, String str, com.a.a.c.l.f fVar, Class<?> cls) {
        if (str.isEmpty()) {
            return a(gVar, gVar.a(fVar, cls, com.a.a.c.b.f.EmptyString), cls, str, "empty String (\"\")");
        }
        if (h(str)) {
            return a(gVar, gVar.a(fVar, cls, com.a.a.c.b.b.Fail), cls, str, "blank String (all whitespace)");
        }
        if (gVar.a(com.a.a.b.s.UNTYPED_SCALARS)) {
            return com.a.a.c.b.b.TryConvert;
        }
        com.a.a.c.b.b a2 = gVar.a(fVar, cls, com.a.a.c.b.f.String);
        if (a2 == com.a.a.c.b.b.Fail) {
            gVar.a(this, "Cannot coerce String value (\"%s\") to %s (but might if coercion using `CoercionConfig` was enabled)", str, k());
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.b.b d(com.a.a.b.l lVar, com.a.a.c.g gVar, Class<?> cls) {
        com.a.a.c.b.b a2 = gVar.a(com.a.a.c.l.f.Integer, cls, com.a.a.c.b.f.Float);
        if (a2 == com.a.a.c.b.b.Fail) {
            return a(gVar, a2, cls, lVar.B(), "Floating-point value (" + lVar.w() + ")");
        }
        return a2;
    }

    private com.a.a.c.b.b f(com.a.a.b.l lVar, com.a.a.c.g gVar, Class<?> cls) {
        return a(lVar, gVar, cls, lVar.B(), com.a.a.c.b.f.Integer);
    }

    private com.a.a.c.b.b g(com.a.a.b.l lVar, com.a.a.c.g gVar, Class<?> cls) {
        return a(lVar, gVar, cls, lVar.B(), com.a.a.c.b.f.Float);
    }

    private com.a.a.c.b.b h(com.a.a.b.l lVar, com.a.a.c.g gVar, Class<?> cls) {
        return a(lVar, gVar, cls, Boolean.valueOf(lVar.M()), com.a.a.c.b.f.Boolean);
    }

    private com.a.a.c.b.b a(com.a.a.b.l lVar, com.a.a.c.g gVar, Class<?> cls, Object obj, com.a.a.c.b.f fVar) {
        com.a.a.c.b.b a2 = gVar.a(com.a.a.c.l.f.Textual, cls, fVar);
        if (a2 == com.a.a.c.b.b.Fail) {
            return a(gVar, a2, cls, obj, fVar.name() + " value (" + lVar.w() + ")");
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.b.b e(com.a.a.b.l lVar, com.a.a.c.g gVar, Class<?> cls) {
        com.a.a.c.b.b a2 = gVar.a(com.a.a.c.l.f.Float, cls, com.a.a.c.b.f.Integer);
        if (a2 == com.a.a.c.b.b.Fail) {
            return a(gVar, a2, cls, lVar.B(), "Integer value (" + lVar.w() + ")");
        }
        return a2;
    }

    private Boolean i(com.a.a.b.l lVar, com.a.a.c.g gVar, Class<?> cls) {
        com.a.a.c.b.b a2 = gVar.a(com.a.a.c.l.f.Boolean, cls, com.a.a.c.b.f.Integer);
        switch (af.f311a[a2.ordinal()]) {
            case 1:
                return Boolean.FALSE;
            case 2:
                return null;
            case 3:
            default:
                if (lVar.D() == l.b.INT) {
                    return Boolean.valueOf(lVar.G() != 0);
                }
                return Boolean.valueOf(!"0".equals(lVar.w()));
            case 4:
                a(gVar, a2, cls, lVar.B(), "Integer value (" + lVar.w() + ")");
                return Boolean.FALSE;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.b.b a(com.a.a.c.g gVar, com.a.a.c.b.b bVar, Class<?> cls, Object obj, String str) {
        if (bVar == com.a.a.c.b.b.Fail) {
            gVar.a(cls, obj, "Cannot coerce %s to %s (but could if coercion was enabled using `CoercionConfig`)", str, k());
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean b(com.a.a.c.g gVar, String str) {
        if (d(str)) {
            if (!gVar.a(com.a.a.c.q.ALLOW_COERCION_OF_SCALARS)) {
                a(gVar, true, (Enum<?>) com.a.a.c.q.ALLOW_COERCION_OF_SCALARS, "String \"null\"");
                return true;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object u(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (gVar.a(com.a.a.c.i.USE_BIG_INTEGER_FOR_INTS)) {
            return lVar.I();
        }
        if (gVar.a(com.a.a.c.i.USE_LONG_FOR_INTS)) {
            return Long.valueOf(lVar.H());
        }
        return lVar.B();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void f(com.a.a.c.g gVar) {
        if (gVar.a(com.a.a.c.i.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            gVar.a(this, "Cannot coerce `null` to %s (disable `DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES` to allow)", k());
        }
    }

    private void g(com.a.a.c.g gVar, String str) {
        Enum<?> r9;
        boolean z;
        if (!gVar.a(com.a.a.c.q.ALLOW_COERCION_OF_SCALARS)) {
            r9 = com.a.a.c.q.ALLOW_COERCION_OF_SCALARS;
            z = true;
        } else if (gVar.a(com.a.a.c.i.FAIL_ON_NULL_FOR_PRIMITIVES)) {
            r9 = com.a.a.c.i.FAIL_ON_NULL_FOR_PRIMITIVES;
            z = false;
        } else {
            return;
        }
        a(gVar, z, r9, str.isEmpty() ? "empty String (\"\")" : String.format("String \"%s\"", str));
    }

    private void a(com.a.a.c.g gVar, boolean z, Enum<?> r11, String str) {
        gVar.a(this, "Cannot coerce %s to Null value as %s (%s `%s.%s` to allow)", str, k(), z ? "enable" : "disable", r11.getDeclaringClass().getSimpleName(), r11.name());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String k() {
        boolean z;
        String c;
        com.a.a.c.j h = h();
        if (h != null && !h.l()) {
            z = h.n() || h.F();
            c = com.a.a.c.m.i.b(h);
        } else {
            Class<?> a2 = a();
            z = a2.isArray() || Collection.class.isAssignableFrom(a2) || Map.class.isAssignableFrom(a2);
            c = com.a.a.c.m.i.c((Object) a2);
        }
        if (z) {
            return "element of " + c;
        }
        return c + " value";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static com.a.a.c.k<Object> a(com.a.a.c.g gVar, com.a.a.c.j jVar, com.a.a.c.c cVar) {
        return gVar.a(jVar, cVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean i(String str) {
        int i;
        int length = str.length();
        if (length > 0) {
            char charAt = str.charAt(0);
            if (charAt == '-' || charAt == '+') {
                if (length == 1) {
                    return false;
                }
                i = 1;
            } else {
                i = 0;
            }
            while (i < length) {
                char charAt2 = str.charAt(i);
                if (charAt2 <= '9' && charAt2 >= '0') {
                    i++;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar, com.a.a.c.k<?> kVar) {
        com.a.a.c.f.j e;
        Object j;
        com.a.a.c.a f = gVar.f();
        if (b(f, cVar) && (e = cVar.e()) != null && (j = f.j(e)) != null) {
            com.a.a.c.m.k<Object, Object> a2 = gVar.a(cVar.e(), j);
            gVar.b();
            com.a.a.c.j a3 = a2.a();
            if (kVar == null) {
                kVar = gVar.a(a3, cVar);
            }
            return new ad(a2, a3, kVar);
        }
        return kVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static l.d a(com.a.a.c.g gVar, com.a.a.c.c cVar, Class<?> cls) {
        if (cVar != null) {
            return cVar.a(gVar.a(), cls);
        }
        return gVar.a(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Boolean a(com.a.a.c.g gVar, com.a.a.c.c cVar, Class<?> cls, l.a aVar) {
        l.d a2 = a(gVar, cVar, cls);
        if (a2 != null) {
            return a2.a(aVar);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.c.s a(com.a.a.c.g gVar, com.a.a.c.c.v vVar, com.a.a.c.v vVar2) {
        if (vVar != null) {
            return a(gVar, vVar, vVar2.e(), (com.a.a.c.k<?>) vVar.p());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.c.s b(com.a.a.c.g gVar, com.a.a.c.c cVar, com.a.a.c.k<?> kVar) {
        com.a.a.a.ak b2 = b(gVar, cVar);
        if (b2 == com.a.a.a.ak.SKIP) {
            return com.a.a.c.c.a.q.a();
        }
        if (b2 == com.a.a.a.ak.FAIL) {
            if (cVar == null) {
                com.a.a.c.j b3 = gVar.b(kVar.a());
                com.a.a.c.j jVar = b3;
                if (b3.n()) {
                    jVar = jVar.u();
                }
                return com.a.a.c.c.a.r.a(jVar);
            }
            return com.a.a.c.c.a.r.a(cVar, cVar.c().u());
        }
        com.a.a.c.c.s a2 = a(gVar, cVar, b2, kVar);
        if (a2 != null) {
            return a2;
        }
        return kVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static com.a.a.a.ak b(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        if (cVar != null) {
            return cVar.d().f();
        }
        return gVar.a().q().b();
    }

    private static com.a.a.c.c.s a(com.a.a.c.g gVar, com.a.a.c.c cVar, com.a.a.a.ak akVar, com.a.a.c.k<?> kVar) {
        if (akVar == com.a.a.a.ak.FAIL) {
            if (cVar == null) {
                return com.a.a.c.c.a.r.a(gVar.b(kVar == null ? Object.class : kVar.a()));
            }
            return com.a.a.c.c.a.r.a(cVar);
        }
        if (akVar == com.a.a.a.ak.AS_EMPTY) {
            if (kVar == null) {
                return null;
            }
            if (kVar instanceof com.a.a.c.c.f) {
                com.a.a.c.c.f fVar = (com.a.a.c.c.f) kVar;
                if (!fVar.i().l()) {
                    com.a.a.c.j h = cVar == null ? fVar.h() : cVar.c();
                    return (com.a.a.c.c.s) gVar.a(h, String.format("Cannot create empty instance of %s, no default Creator", h));
                }
            }
            com.a.a.c.m.a e = kVar.e();
            if (e == com.a.a.c.m.a.ALWAYS_NULL) {
                return com.a.a.c.c.a.q.b();
            }
            if (e == com.a.a.c.m.a.CONSTANT) {
                return com.a.a.c.c.a.q.a(kVar.c(gVar));
            }
            return new com.a.a.c.c.a.p(kVar);
        }
        if (akVar == com.a.a.a.ak.SKIP) {
            return com.a.a.c.c.a.q.a();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.b.b g(com.a.a.c.g gVar) {
        return gVar.a(b(), a(), com.a.a.c.b.f.EmptyString);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.b.b h(com.a.a.c.g gVar) {
        return gVar.a(b(), a(), com.a.a.c.b.f.EmptyArray);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.b.b i(com.a.a.c.g gVar) {
        return gVar.a(b(), a(), com.a.a.c.b.b.Fail);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, String str) {
        if (obj == null) {
            obj = a();
        }
        gVar.a(lVar, this, obj, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void j(com.a.a.c.g gVar) {
        gVar.a(this, com.a.a.b.o.END_ARRAY, "Attempted to unwrap '%s' value from an array (with `DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS`) but it contains more than one value", a().getName());
    }

    private Object f(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return gVar.a(e(gVar), lVar.k(), lVar, String.format("Cannot deserialize instance of %s out of %s token: nested Arrays not allowed with %s", com.a.a.c.m.i.g(this.s), com.a.a.b.o.START_ARRAY, "DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS"), new Object[0]);
    }

    private void g(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.g() != com.a.a.b.o.END_ARRAY) {
            j(gVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean b(Object obj, Object obj2) {
        return (obj == null || obj2 == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(int i) {
        return i < -128 || i > 255;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean b(int i) {
        return i < -32768 || i > 32767;
    }

    private static boolean a(long j) {
        return j < -2147483648L || j > 2147483647L;
    }

    private static Number a(Number number) {
        if (number == null) {
            number = 0;
        }
        return number;
    }
}
