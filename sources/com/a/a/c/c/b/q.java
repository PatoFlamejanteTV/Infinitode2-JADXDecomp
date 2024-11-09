package com.a.a.c.c.b;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/q.class */
public abstract class q<T> extends ai<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract T a(String str, com.a.a.c.g gVar);

    public static Class<?>[] g() {
        return new Class[]{File.class, URL.class, URI.class, Class.class, com.a.a.c.j.class, Currency.class, Pattern.class, Locale.class, Charset.class, TimeZone.class, InetAddress.class, InetSocketAddress.class, StringBuilder.class, StringBuffer.class};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public q(Class<?> cls) {
        super(cls);
    }

    public static q<?> a(Class<?> cls) {
        int i;
        if (cls == File.class) {
            i = 1;
        } else if (cls == URL.class) {
            i = 2;
        } else if (cls == URI.class) {
            i = 3;
        } else if (cls == Class.class) {
            i = 4;
        } else if (cls == com.a.a.c.j.class) {
            i = 5;
        } else if (cls == Currency.class) {
            i = 6;
        } else if (cls == Pattern.class) {
            i = 7;
        } else if (cls == Locale.class) {
            i = 8;
        } else if (cls == Charset.class) {
            i = 9;
        } else if (cls == TimeZone.class) {
            i = 10;
        } else if (cls == InetAddress.class) {
            i = 11;
        } else if (cls == InetSocketAddress.class) {
            i = 12;
        } else {
            if (cls == StringBuilder.class) {
                return new c();
            }
            if (cls == StringBuffer.class) {
                return new b();
            }
            return null;
        }
        return new a(cls, i);
    }

    @Override // com.a.a.c.c.b.ai, com.a.a.c.k
    public com.a.a.c.l.f b() {
        return com.a.a.c.l.f.OtherScalar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v20, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v38, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v42 */
    /* JADX WARN: Type inference failed for: r0v43 */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    @Override // com.a.a.c.k
    public T a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        String R = lVar.R();
        String str = R;
        if (R == null) {
            com.a.a.b.o k = lVar.k();
            if (k != com.a.a.b.o.START_OBJECT) {
                return (T) a(lVar, gVar, k);
            }
            str = gVar.a(lVar, this.s);
        }
        if (str.isEmpty()) {
            return (T) k(gVar);
        }
        boolean j = j();
        T t = j;
        if (j) {
            String str2 = str;
            String str3 = (T) str.trim();
            str = str3;
            t = str3;
            if (str3 != str2) {
                boolean z = (T) str.isEmpty();
                t = z;
                if (z) {
                    return (T) k(gVar);
                }
            }
        }
        try {
            t = a(str, gVar);
            return t;
        } catch (IllegalArgumentException | MalformedURLException e) {
            Exception exc = t;
            String str4 = "not a valid textual representation";
            String message = exc.getMessage();
            if (message != null) {
                str4 = str4 + ", problem: " + message;
            }
            throw gVar.a(str, this.s, str4).a(exc);
        }
    }

    protected boolean j() {
        return true;
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.b.o oVar) {
        if (oVar == com.a.a.b.o.START_ARRAY) {
            return d(lVar, gVar);
        }
        if (oVar == com.a.a.b.o.VALUE_EMBEDDED_OBJECT) {
            Object N = lVar.N();
            if (N != null) {
                if (this.s.isAssignableFrom(N.getClass())) {
                    return N;
                }
                return a(N, gVar);
            }
            return null;
        }
        return gVar.a(this.s, lVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T a(Object obj, com.a.a.c.g gVar) {
        gVar.a(this, "Don't know how to convert embedded Object of type %s into %s", obj.getClass().getName(), this.s.getName());
        return null;
    }

    private Object k(com.a.a.c.g gVar) {
        com.a.a.c.b.b a2 = gVar.a(b(), this.s, com.a.a.c.b.f.EmptyString);
        if (a2 == com.a.a.c.b.b.Fail) {
            gVar.a(this, "Cannot coerce empty String (\"\") to %s (but could if enabling coercion using `CoercionConfig`)", k());
        }
        if (a2 == com.a.a.c.b.b.AsNull) {
            return a(gVar);
        }
        if (a2 == com.a.a.c.b.b.AsEmpty) {
            return c(gVar);
        }
        return d(gVar);
    }

    protected Object d(com.a.a.c.g gVar) {
        return a(gVar);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/q$a.class */
    public static class a extends q<Object> {

        /* renamed from: a, reason: collision with root package name */
        private int f359a;

        protected a(Class<?> cls, int i) {
            super(cls);
            this.f359a = i;
        }

        @Override // com.a.a.c.c.b.q
        protected final Object a(String str, com.a.a.c.g gVar) {
            switch (this.f359a) {
                case 1:
                    return new File(str);
                case 2:
                    return new URL(str);
                case 3:
                    return URI.create(str);
                case 4:
                    try {
                        return gVar.b(str);
                    } catch (Exception e) {
                        return gVar.a(this.s, str, com.a.a.c.m.i.d((Throwable) e));
                    }
                case 5:
                    return gVar.b().b(str);
                case 6:
                    return Currency.getInstance(str);
                case 7:
                    return Pattern.compile(str);
                case 8:
                    return k(str);
                case 9:
                    return Charset.forName(str);
                case 10:
                    return TimeZone.getTimeZone(str);
                case 11:
                    return InetAddress.getByName(str);
                case 12:
                    if (str.startsWith("[")) {
                        int lastIndexOf = str.lastIndexOf(93);
                        if (lastIndexOf == -1) {
                            throw new com.a.a.c.d.c(gVar.j(), "Bracketed IPv6 address must contain closing bracket", str, InetSocketAddress.class);
                        }
                        int indexOf = str.indexOf(58, lastIndexOf);
                        return new InetSocketAddress(str.substring(0, lastIndexOf + 1), indexOf >= 0 ? Integer.parseInt(str.substring(indexOf + 1)) : 0);
                    }
                    int indexOf2 = str.indexOf(58);
                    if (indexOf2 >= 0 && str.indexOf(58, indexOf2 + 1) < 0) {
                        return new InetSocketAddress(str.substring(0, indexOf2), Integer.parseInt(str.substring(indexOf2 + 1)));
                    }
                    return new InetSocketAddress(str, 0);
                default:
                    com.a.a.b.g.q.a();
                    return null;
            }
        }

        @Override // com.a.a.c.k
        public final Object c(com.a.a.c.g gVar) {
            switch (this.f359a) {
                case 3:
                    return URI.create("");
                case 8:
                    return Locale.ROOT;
                default:
                    return super.c(gVar);
            }
        }

        @Override // com.a.a.c.c.b.q
        protected final Object d(com.a.a.c.g gVar) {
            return c(gVar);
        }

        @Override // com.a.a.c.c.b.q
        protected final boolean j() {
            return this.f359a != 7;
        }

        private static int j(String str) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt == '_' || charAt == '-') {
                    return i;
                }
            }
            return -1;
        }

        private Locale k(String str) {
            int j = j(str);
            if (j < 0) {
                return new Locale(str);
            }
            String substring = str.substring(0, j);
            String substring2 = str.substring(j + 1);
            int j2 = j(substring2);
            if (j2 < 0) {
                return new Locale(substring, substring2);
            }
            String substring3 = substring2.substring(0, j2);
            int indexOf = substring2.indexOf("_#");
            if (indexOf < 0) {
                return new Locale(substring, substring3, substring2.substring(j2 + 1));
            }
            return a(substring2, j2, substring, substring3, indexOf);
        }

        private static Locale a(String str, int i, String str2, String str3, int i2) {
            String str4 = "";
            if (i2 > 0 && i2 > i) {
                try {
                    str4 = str.substring(i + 1, i2);
                } catch (IllformedLocaleException unused) {
                    return new Locale(str2, str3, str4);
                }
            }
            String substring = str.substring(i2 + 2);
            int indexOf = substring.indexOf(95);
            if (indexOf < 0) {
                int indexOf2 = substring.indexOf(45);
                if (indexOf2 < 0) {
                    return new Locale.Builder().setLanguage(str2).setRegion(str3).setVariant(str4).setScript(substring).build();
                }
                return new Locale.Builder().setLanguage(str2).setRegion(str3).setVariant(str4).setExtension(substring.charAt(0), substring.substring(indexOf2 + 1)).build();
            }
            int length = substring.length();
            Locale.Builder script = new Locale.Builder().setLanguage(str2).setRegion(str3).setVariant(str4).setScript(substring.substring(0, indexOf));
            if (indexOf + 1 < length) {
                script = script.setExtension(substring.charAt(indexOf + 1), substring.substring(Math.min(length, indexOf + 3)));
            }
            return script.build();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/q$c.class */
    public static class c extends q<Object> {
        public c() {
            super(StringBuilder.class);
        }

        @Override // com.a.a.c.c.b.q, com.a.a.c.c.b.ai, com.a.a.c.k
        public final com.a.a.c.l.f b() {
            return com.a.a.c.l.f.Textual;
        }

        @Override // com.a.a.c.k
        public final Object c(com.a.a.c.g gVar) {
            return new StringBuilder();
        }

        @Override // com.a.a.c.c.b.q, com.a.a.c.k
        public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            String R = lVar.R();
            if (R != null) {
                return a(R, gVar);
            }
            return super.a(lVar, gVar);
        }

        @Override // com.a.a.c.c.b.q
        protected final Object a(String str, com.a.a.c.g gVar) {
            return new StringBuilder(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/q$b.class */
    public static class b extends q<Object> {
        public b() {
            super(StringBuffer.class);
        }

        @Override // com.a.a.c.c.b.q, com.a.a.c.c.b.ai, com.a.a.c.k
        public final com.a.a.c.l.f b() {
            return com.a.a.c.l.f.Textual;
        }

        @Override // com.a.a.c.k
        public final Object c(com.a.a.c.g gVar) {
            return new StringBuffer();
        }

        @Override // com.a.a.c.c.b.q, com.a.a.c.k
        public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            String R = lVar.R();
            if (R != null) {
                return a(R, gVar);
            }
            return super.a(lVar, gVar);
        }

        @Override // com.a.a.c.c.b.q
        protected final Object a(String str, com.a.a.c.g gVar) {
            return new StringBuffer(str);
        }
    }
}
