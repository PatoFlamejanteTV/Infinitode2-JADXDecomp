package com.a.a.c.f;

import com.a.a.c.a.e;
import com.a.a.c.f.a;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/f/w.class */
public class w extends com.a.a.c.f.a {

    /* renamed from: a, reason: collision with root package name */
    private a f476a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f477b;
    private boolean c;
    private String d;
    private String e;
    private String f;

    /* loaded from: infinitode-2.jar:com/a/a/c/f/w$a.class */
    public interface a {
        boolean a();

        String b();

        com.a.a.c.k<?> c();

        com.a.a.c.k<?> d();

        com.a.a.c.k<?> e();

        com.a.a.c.k<?> f();

        com.a.a.c.k<?> g();

        com.a.a.c.k<?> h();

        com.a.a.c.k<?> i();

        com.a.a.c.k<?> j();

        com.a.a.c.k<?> k();

        com.a.a.c.c.x l();

        com.a.a.c.o<?> m();

        com.a.a.c.o<?> n();

        com.a.a.c.o<?> o();

        com.a.a.c.o<?> p();

        com.a.a.c.o<?> q();

        com.a.a.c.o<?> r();

        com.a.a.c.o<?> s();

        Rectangle t();

        int u();

        int v();

        Map<Shape, String> w();

        a x();

        boolean y();
    }

    protected w(com.a.a.c.b.q<?> qVar, d dVar, String str, String str2, String str3, a aVar) {
        this.f477b = qVar.a(com.a.a.c.q.USE_STD_BEAN_NAMING);
        this.c = qVar.a(com.a.a.c.q.ALLOW_IS_GETTERS_FOR_NON_BOOLEAN);
        this.f = str;
        this.d = str2;
        this.e = str3;
        this.f476a = aVar;
    }

    @Override // com.a.a.c.f.a
    public final String a(k kVar, String str) {
        if (this.e != null) {
            Class<?> d = kVar.d();
            if ((this.c || d == Boolean.class || d == Boolean.TYPE) && str.startsWith(this.e)) {
                if (this.f477b) {
                    return b(str, 2);
                }
                return a(str, 2);
            }
            return null;
        }
        return null;
    }

    @Override // com.a.a.c.f.a
    public String b(k kVar, String str) {
        if (this.d != null && str.startsWith(this.d)) {
            if ("getCallbacks".equals(str)) {
                if (a(kVar)) {
                    return null;
                }
            } else if ("getMetaClass".equals(str) && b(kVar)) {
                return null;
            }
            if (this.f477b) {
                return b(str, this.d.length());
            }
            return a(str, this.d.length());
        }
        return null;
    }

    @Override // com.a.a.c.f.a
    public final String a(String str) {
        if (this.f != null && str.startsWith(this.f)) {
            if (this.f477b) {
                return b(str, this.f.length());
            }
            return a(str, this.f.length());
        }
        return null;
    }

    @Override // com.a.a.c.f.a
    public final String b(String str) {
        return str;
    }

    private String a(String str, int i) {
        int length = str.length();
        if (length == i) {
            return null;
        }
        char charAt = str.charAt(i);
        if (this.f476a != null && !this.f476a.a()) {
            return null;
        }
        char lowerCase = Character.toLowerCase(charAt);
        if (charAt == lowerCase) {
            return str.substring(i);
        }
        StringBuilder sb = new StringBuilder(length - i);
        sb.append(lowerCase);
        int i2 = i + 1;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt2 = str.charAt(i2);
            char lowerCase2 = Character.toLowerCase(charAt2);
            if (charAt2 == lowerCase2) {
                sb.append((CharSequence) str, i2, length);
                break;
            }
            sb.append(lowerCase2);
            i2++;
        }
        return sb.toString();
    }

    private String b(String str, int i) {
        int length = str.length();
        if (length == i) {
            return null;
        }
        char charAt = str.charAt(i);
        if (this.f476a != null && !this.f476a.a()) {
            return null;
        }
        char lowerCase = Character.toLowerCase(charAt);
        if (charAt != lowerCase) {
            if (i + 1 < length && Character.isUpperCase(str.charAt(i + 1))) {
                return str.substring(i);
            }
            StringBuilder sb = new StringBuilder(length - i);
            sb.append(lowerCase);
            sb.append((CharSequence) str, i + 1, length);
            return sb.toString();
        }
        return str.substring(i);
    }

    private static boolean a(k kVar) {
        Class<?> d = kVar.d();
        if (d.isArray()) {
            String name = d.getComponentType().getName();
            if (name.contains(".cglib")) {
                return name.startsWith("net.sf.cglib") || name.startsWith("org.hibernate.repackage.cglib") || name.startsWith("org.springframework.cglib");
            }
            return false;
        }
        return false;
    }

    private static boolean b(k kVar) {
        return kVar.d().getName().startsWith("groovy.lang");
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/w$b.class */
    public static class b extends a.AbstractC0008a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private String f478a;

        /* renamed from: b, reason: collision with root package name */
        private String f479b;
        private String c;
        private String d;
        private a e;

        public b() {
            this("set", "with", "get", "is", null);
        }

        private b(String str, String str2, String str3, String str4, a aVar) {
            this.f478a = str;
            this.f479b = str2;
            this.c = str3;
            this.d = str4;
            this.e = aVar;
        }

        @Override // com.a.a.c.f.a.AbstractC0008a
        public final com.a.a.c.f.a a(com.a.a.c.b.q<?> qVar, d dVar) {
            return new w(qVar, dVar, this.f478a, this.c, this.d, this.e);
        }

        @Override // com.a.a.c.f.a.AbstractC0008a
        public final com.a.a.c.f.a b(com.a.a.c.b.q<?> qVar, d dVar) {
            com.a.a.c.a j = qVar.f() ? qVar.j() : null;
            e.a h = j == null ? null : j.h(dVar);
            return new w(qVar, dVar, h == null ? this.f479b : h.f200b, this.c, this.d, this.e);
        }

        @Override // com.a.a.c.f.a.AbstractC0008a
        public final com.a.a.c.f.a c(com.a.a.c.b.q<?> qVar, d dVar) {
            return new c(qVar, dVar);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/f/w$c.class */
    public static class c extends w {

        /* renamed from: a, reason: collision with root package name */
        private Set<String> f480a;

        public c(com.a.a.c.b.q<?> qVar, d dVar) {
            super(qVar, dVar, null, "get", "is", null);
            Set<String> hashSet;
            String[] a2 = com.a.a.c.g.a.a(dVar.d());
            if (a2 == null) {
                hashSet = Collections.emptySet();
            } else {
                hashSet = new HashSet<>(Arrays.asList(a2));
            }
            this.f480a = hashSet;
        }

        @Override // com.a.a.c.f.w, com.a.a.c.f.a
        public final String b(k kVar, String str) {
            if (this.f480a.contains(str)) {
                return str;
            }
            return super.b(kVar, str);
        }
    }
}
