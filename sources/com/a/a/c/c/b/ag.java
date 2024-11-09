package com.a.a.c.c.b;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/ag.class */
public class ag extends com.a.a.c.p implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    private int f312b;

    /* renamed from: a, reason: collision with root package name */
    protected final Class<?> f313a;
    private q<?> c;

    protected ag(int i, Class<?> cls) {
        this(i, cls, null);
    }

    private ag(int i, Class<?> cls, q<?> qVar) {
        this.f312b = i;
        this.f313a = cls;
        this.c = qVar;
    }

    public static ag a(Class<?> cls) {
        int i;
        if (cls == String.class || cls == Object.class || cls == CharSequence.class || cls == Serializable.class) {
            return e.b(cls);
        }
        if (cls == UUID.class) {
            i = 12;
        } else if (cls == Integer.class) {
            i = 5;
        } else if (cls == Long.class) {
            i = 6;
        } else if (cls == Date.class) {
            i = 10;
        } else if (cls == Calendar.class) {
            i = 11;
        } else if (cls == Boolean.class) {
            i = 1;
        } else if (cls == Byte.class) {
            i = 2;
        } else if (cls == Character.class) {
            i = 4;
        } else if (cls == Short.class) {
            i = 3;
        } else if (cls == Float.class) {
            i = 7;
        } else if (cls == Double.class) {
            i = 8;
        } else if (cls == URI.class) {
            i = 13;
        } else if (cls == URL.class) {
            i = 14;
        } else if (cls == Class.class) {
            i = 15;
        } else {
            if (cls == Locale.class) {
                return new ag(9, cls, q.a((Class<?>) Locale.class));
            }
            if (cls == Currency.class) {
                return new ag(16, cls, q.a((Class<?>) Currency.class));
            }
            if (cls == byte[].class) {
                i = 17;
            } else {
                return null;
            }
        }
        return new ag(i, cls);
    }

    @Override // com.a.a.c.p
    public Object a(String str, com.a.a.c.g gVar) {
        if (str == null) {
            return null;
        }
        try {
            Object b2 = b(str, gVar);
            if (b2 != null) {
                return b2;
            }
            if (com.a.a.c.m.i.k(this.f313a) && gVar.a().a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return null;
            }
            return gVar.a(this.f313a, str, "not a valid representation", new Object[0]);
        } catch (Exception e2) {
            return gVar.a(this.f313a, str, "not a valid representation, problem: (%s) %s", e2.getClass().getName(), com.a.a.c.m.i.g(e2));
        }
    }

    protected Object b(String str, com.a.a.c.g gVar) {
        switch (this.f312b) {
            case 1:
                if ("true".equals(str)) {
                    return Boolean.TRUE;
                }
                if ("false".equals(str)) {
                    return Boolean.FALSE;
                }
                return gVar.a(this.f313a, str, "value not 'true' or 'false'", new Object[0]);
            case 2:
                int a2 = a(str);
                if (a2 < -128 || a2 > 255) {
                    return gVar.a(this.f313a, str, "overflow, value cannot be represented as 8-bit value", new Object[0]);
                }
                return Byte.valueOf((byte) a2);
            case 3:
                int a3 = a(str);
                if (a3 < -32768 || a3 > 32767) {
                    return gVar.a(this.f313a, str, "overflow, value cannot be represented as 16-bit value", new Object[0]);
                }
                return Short.valueOf((short) a3);
            case 4:
                if (str.length() == 1) {
                    return Character.valueOf(str.charAt(0));
                }
                return gVar.a(this.f313a, str, "can only convert 1-character Strings", new Object[0]);
            case 5:
                return Integer.valueOf(a(str));
            case 6:
                return Long.valueOf(b(str));
            case 7:
                return Float.valueOf((float) c(str));
            case 8:
                return Double.valueOf(c(str));
            case 9:
                try {
                    return this.c.a(str, gVar);
                } catch (IllegalArgumentException e2) {
                    return a(gVar, str, e2);
                }
            case 10:
                return gVar.c(str);
            case 11:
                return gVar.a(gVar.c(str));
            case 12:
                try {
                    return UUID.fromString(str);
                } catch (Exception e3) {
                    return a(gVar, str, e3);
                }
            case 13:
                try {
                    return URI.create(str);
                } catch (Exception e4) {
                    return a(gVar, str, e4);
                }
            case 14:
                try {
                    return new URL(str);
                } catch (MalformedURLException e5) {
                    return a(gVar, str, e5);
                }
            case 15:
                try {
                    return gVar.b(str);
                } catch (Exception unused) {
                    return gVar.a(this.f313a, str, "unable to parse key as Class", new Object[0]);
                }
            case 16:
                try {
                    return this.c.a(str, gVar);
                } catch (IllegalArgumentException e6) {
                    return a(gVar, str, e6);
                }
            case 17:
                try {
                    return gVar.a().v().a(str);
                } catch (IllegalArgumentException e7) {
                    return a(gVar, str, e7);
                }
            default:
                throw new IllegalStateException("Internal error: unknown key type " + this.f313a);
        }
    }

    private static int a(String str) {
        return com.a.a.b.c.h.a(str);
    }

    private static long b(String str) {
        return com.a.a.b.c.h.b(str);
    }

    private static double c(String str) {
        return com.a.a.b.c.h.c(str);
    }

    private Object a(com.a.a.c.g gVar, String str, Exception exc) {
        return gVar.a(this.f313a, str, "problem: %s", com.a.a.c.m.i.g(exc));
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/ag$e.class */
    static final class e extends ag {

        /* renamed from: b, reason: collision with root package name */
        private static final e f319b = new e(String.class);
        private static final e c = new e(Object.class);

        private e(Class<?> cls) {
            super(-1, cls);
        }

        public static e b(Class<?> cls) {
            if (cls == String.class) {
                return f319b;
            }
            if (cls == Object.class) {
                return c;
            }
            return new e(cls);
        }

        @Override // com.a.a.c.c.b.ag, com.a.a.c.p
        public final Object a(String str, com.a.a.c.g gVar) {
            return str;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/ag$a.class */
    static final class a extends com.a.a.c.p implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private Class<?> f314a;

        /* renamed from: b, reason: collision with root package name */
        private com.a.a.c.k<?> f315b;

        /* JADX INFO: Access modifiers changed from: protected */
        public a(Class<?> cls, com.a.a.c.k<?> kVar) {
            this.f314a = cls;
            this.f315b = kVar;
        }

        @Override // com.a.a.c.p
        public final Object a(String str, com.a.a.c.g gVar) {
            if (str == null) {
                return null;
            }
            com.a.a.c.m.ac m = gVar.m();
            m.b(str);
            try {
                com.a.a.b.l o = m.o();
                o.g();
                Object a2 = this.f315b.a(o, gVar);
                if (a2 != null) {
                    return a2;
                }
                return gVar.a(this.f314a, str, "not a valid representation", new Object[0]);
            } catch (Exception e) {
                return gVar.a(this.f314a, str, "not a valid representation: %s", e.getMessage());
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/ag$b.class */
    static final class b extends ag {

        /* renamed from: b, reason: collision with root package name */
        private com.a.a.c.m.l f316b;
        private com.a.a.c.f.k c;
        private com.a.a.c.m.l d;
        private Enum<?> e;

        /* JADX INFO: Access modifiers changed from: protected */
        public b(com.a.a.c.m.l lVar, com.a.a.c.f.k kVar) {
            super(-1, lVar.e());
            this.f316b = lVar;
            this.c = kVar;
            this.e = lVar.b();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [com.a.a.c.f.k] */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object] */
        @Override // com.a.a.c.c.b.ag
        public final Object b(String str, com.a.a.c.g gVar) {
            ?? r0 = this.c;
            if (r0 != 0) {
                try {
                    r0 = this.c.a(str);
                    return r0;
                } catch (Exception e) {
                    com.a.a.c.m.i.f((Throwable) r0);
                }
            }
            com.a.a.c.m.l a2 = gVar.a(com.a.a.c.i.READ_ENUMS_USING_TO_STRING) ? a(gVar) : this.f316b;
            com.a.a.c.m.l lVar = a2;
            Enum<?> a3 = a2.a(str);
            Enum<?> r13 = a3;
            if (a3 == null) {
                if (this.e != null && gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)) {
                    r13 = this.e;
                } else if (!gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    return gVar.a(this.f313a, str, "not one of the values accepted for Enum class: %s", lVar.d());
                }
            }
            return r13;
        }

        private com.a.a.c.m.l a(com.a.a.c.g gVar) {
            com.a.a.c.m.l lVar = this.d;
            com.a.a.c.m.l lVar2 = lVar;
            if (lVar == null) {
                synchronized (this) {
                    lVar2 = com.a.a.c.m.l.b(gVar.a(), this.f316b.e());
                    this.d = lVar2;
                }
            }
            return lVar2;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/ag$c.class */
    static final class c extends ag {

        /* renamed from: b, reason: collision with root package name */
        private Constructor<?> f317b;

        public c(Constructor<?> constructor) {
            super(-1, constructor.getDeclaringClass());
            this.f317b = constructor;
        }

        @Override // com.a.a.c.c.b.ag
        public final Object b(String str, com.a.a.c.g gVar) {
            return this.f317b.newInstance(str);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/ag$d.class */
    static final class d extends ag {

        /* renamed from: b, reason: collision with root package name */
        private Method f318b;

        public d(Method method) {
            super(-1, method.getDeclaringClass());
            this.f318b = method;
        }

        @Override // com.a.a.c.c.b.ag
        public final Object b(String str, com.a.a.c.g gVar) {
            return this.f318b.invoke(null, str);
        }
    }
}
