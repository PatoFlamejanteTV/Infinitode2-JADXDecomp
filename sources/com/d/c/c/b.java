package com.d.c.c;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/d/c/c/b.class */
public abstract class b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar);

    b() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a(String str, String str2) {
        return new c(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a(String str, String str2, String str3) {
        return new f(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b b(String str, String str2, String str3) {
        return new h(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b c(String str, String str2, String str3) {
        return new g(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b d(String str, String str2, String str3) {
        return new C0019b(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b e(String str, String str2, String str3) {
        return new e(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b f(String str, String str2, String str3) {
        return new d(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a(String str) {
        return new i(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b b(String str) {
        return new l(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b c(String str) {
        return new m(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a() {
        return new k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b b() {
        return new n();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b d(String str) {
        return p.e(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b c() {
        return new j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b d() {
        return new q();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b e() {
        return new o();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b f() {
        return new r();
    }

    /* loaded from: infinitode-2.jar:com/d/c/c/b$a.class */
    static abstract class a extends b {

        /* renamed from: a, reason: collision with root package name */
        protected String f978a;

        /* renamed from: b, reason: collision with root package name */
        protected String f979b;
        private String c;

        protected abstract boolean b(String str, String str2);

        a(String str, String str2, String str3) {
            this.f978a = str;
            this.f979b = str2;
            this.c = str3;
        }

        @Override // com.d.c.c.b
        boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            String a2;
            if (aVar == null || (a2 = aVar.a(obj, this.f978a, this.f979b)) == null) {
                return false;
            }
            return b(a2, this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$c.class */
    public static class c extends a {
        c(String str, String str2) {
            super(str, str2, null);
        }

        @Override // com.d.c.c.b.a, com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            if (aVar == null || aVar.a(obj, this.f978a, this.f979b) == null) {
                return false;
            }
            return true;
        }

        @Override // com.d.c.c.b.a
        protected final boolean b(String str, String str2) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.d.c.c.b$b, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$b.class */
    public static class C0019b extends a {
        C0019b(String str, String str2, String str3) {
            super(str, str2, str3);
        }

        @Override // com.d.c.c.b.a
        protected final boolean b(String str, String str2) {
            return str.equals(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$f.class */
    public static class f extends a {
        f(String str, String str2, String str3) {
            super(str, str2, str3);
        }

        @Override // com.d.c.c.b.a
        protected final boolean b(String str, String str2) {
            return str.startsWith(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$h.class */
    public static class h extends a {
        h(String str, String str2, String str3) {
            super(str, str2, str3);
        }

        @Override // com.d.c.c.b.a
        protected final boolean b(String str, String str2) {
            return str.endsWith(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$g.class */
    public static class g extends a {
        g(String str, String str2, String str3) {
            super(str, str2, str3);
        }

        @Override // com.d.c.c.b.a
        protected final boolean b(String str, String str2) {
            return str.indexOf(str2) >= 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$e.class */
    public static class e extends a {
        e(String str, String str2, String str3) {
            super(str, str2, str3);
        }

        @Override // com.d.c.c.b.a
        protected final boolean b(String str, String str2) {
            boolean z = false;
            for (String str3 : b.b(str, ' ')) {
                if (str2.equals(str3)) {
                    z = true;
                }
            }
            return z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$d.class */
    public static class d extends a {
        d(String str, String str2, String str3) {
            super(str, str2, str3);
        }

        @Override // com.d.c.c.b.a
        protected final boolean b(String str, String str2) {
            if (str2.equals(b.b(str, '-')[0])) {
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$i.class */
    public static class i extends b {

        /* renamed from: a, reason: collision with root package name */
        private String f980a;

        i(String str) {
            this.f980a = SequenceUtils.SPACE + str + SequenceUtils.SPACE;
        }

        @Override // com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            String a2;
            return (aVar == null || (a2 = aVar.a(obj)) == null || new StringBuilder(SequenceUtils.SPACE).append(a2).append(SequenceUtils.SPACE).toString().indexOf(this.f980a) == -1) ? false : true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$l.class */
    public static class l extends b {

        /* renamed from: a, reason: collision with root package name */
        private String f981a;

        l(String str) {
            this.f981a = str;
        }

        @Override // com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            if (aVar == null || !this.f981a.equals(aVar.b(obj))) {
                return false;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$m.class */
    public static class m extends b {

        /* renamed from: a, reason: collision with root package name */
        private String f982a;

        m(String str) {
            this.f982a = str;
        }

        @Override // com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            String e;
            if (aVar != null && (e = aVar.e(obj)) != null) {
                if (this.f982a.equalsIgnoreCase(e)) {
                    return true;
                }
                if (this.f982a.equalsIgnoreCase(b.b(e, '-')[0])) {
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$k.class */
    public static class k extends b {
        k() {
        }

        @Override // com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            return dVar.c(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$n.class */
    public static class n extends b {
        n() {
        }

        @Override // com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            return dVar.d(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$p.class */
    public static class p extends b {

        /* renamed from: a, reason: collision with root package name */
        private static final Pattern f983a = Pattern.compile("([-+]?)(\\d*)n(\\s*([-+])\\s*(\\d+))?");

        /* renamed from: b, reason: collision with root package name */
        private final int f984b;
        private final int c;

        private p(int i, int i2) {
            this.f984b = i;
            this.c = i2;
        }

        @Override // com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            int e = (dVar.e(obj) + 1) - this.c;
            return this.f984b == 0 ? e == 0 : (this.f984b >= 0 || e <= 0) && e % this.f984b == 0;
        }

        static p e(String str) {
            String lowerCase = str.trim().toLowerCase();
            if ("even".equals(lowerCase)) {
                return new p(2, 0);
            }
            if ("odd".equals(lowerCase)) {
                return new p(2, 1);
            }
            try {
                return new p(0, Integer.parseInt(lowerCase));
            } catch (NumberFormatException unused) {
                Matcher matcher = f983a.matcher(lowerCase);
                if (!matcher.matches()) {
                    throw new com.d.c.d.b("Invalid nth-child selector: " + lowerCase, -1);
                }
                int parseInt = matcher.group(2).equals("") ? 1 : Integer.parseInt(matcher.group(2));
                int parseInt2 = matcher.group(5) == null ? 0 : Integer.parseInt(matcher.group(5));
                if ("-".equals(matcher.group(1))) {
                    parseInt = -parseInt;
                }
                if ("-".equals(matcher.group(4))) {
                    parseInt2 = -parseInt2;
                }
                return new p(parseInt, parseInt2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$j.class */
    public static class j extends b {
        j() {
        }

        @Override // com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            int e = dVar.e(obj);
            return e >= 0 && e % 2 == 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$q.class */
    public static class q extends b {
        q() {
        }

        @Override // com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            int e = dVar.e(obj);
            return e >= 0 && e % 2 == 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$o.class */
    public static class o extends b {
        o() {
        }

        @Override // com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            return aVar.f(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/c/c/b$r.class */
    public static class r extends b {
        r() {
        }

        @Override // com.d.c.c.b
        final boolean a(Object obj, com.d.c.b.a aVar, com.d.c.b.d dVar) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] b(String str, char c2) {
        int i2;
        if (str.indexOf(c2) == -1) {
            return new String[]{str};
        }
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (true) {
            i2 = i3;
            int indexOf = str.indexOf(c2, i2);
            if (indexOf == -1) {
                break;
            }
            if (indexOf != i2) {
                arrayList.add(str.substring(i2, indexOf));
            }
            i3 = indexOf + 1;
        }
        if (i2 != str.length()) {
            arrayList.add(str.substring(i2));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
