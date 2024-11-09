package com.d.e;

import java.text.BreakIterator;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/d/e/ad.class */
public class ad {

    /* renamed from: a, reason: collision with root package name */
    private final String f1109a;

    /* renamed from: b, reason: collision with root package name */
    private final int f1110b;

    /* loaded from: infinitode-2.jar:com/d/e/ad$a.class */
    public static class a implements com.d.d.g {

        /* renamed from: a, reason: collision with root package name */
        private final BreakIterator f1111a;

        public a(BreakIterator breakIterator) {
            this.f1111a = breakIterator;
        }

        @Override // com.d.d.g
        public final int a() {
            return this.f1111a.next();
        }

        @Override // com.d.d.g
        public final void a(String str) {
            this.f1111a.setText(str);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/e/ad$d.class */
    public static class d implements com.d.d.h {

        /* renamed from: a, reason: collision with root package name */
        private final Locale f1113a;

        public d(Locale locale) {
            this.f1113a = locale;
        }

        @Override // com.d.d.h
        public final String a(String str) {
            return str.toUpperCase(this.f1113a);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/e/ad$b.class */
    public static class b implements com.d.d.h {

        /* renamed from: a, reason: collision with root package name */
        private final Locale f1112a;

        public b(Locale locale) {
            this.f1112a = locale;
        }

        @Override // com.d.d.h
        public final String a(String str) {
            return str.toLowerCase(this.f1112a);
        }
    }

    /* loaded from: infinitode-2.jar:com/d/e/ad$c.class */
    public static class c implements com.d.d.h {
        @Override // com.d.d.h
        public final String a(String str) {
            StringBuilder sb = new StringBuilder(str.length());
            boolean z = true;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < str.length()) {
                    int codePointAt = str.codePointAt(i2);
                    if (Character.isLetter(codePointAt) && z) {
                        sb.appendCodePoint(Character.toTitleCase(codePointAt));
                        z = false;
                    } else if (Character.isWhitespace(codePointAt) || Character.isSpaceChar(codePointAt)) {
                        sb.appendCodePoint(codePointAt);
                        z = true;
                    } else {
                        sb.appendCodePoint(codePointAt);
                    }
                    i = i2 + Character.charCount(codePointAt);
                } else {
                    return sb.toString();
                }
            }
        }
    }

    public static String a(String str, com.d.c.f.c cVar) {
        com.d.c.a.c e = cVar.e(com.d.c.a.a.aq);
        com.d.c.a.c e2 = cVar.e(com.d.c.a.a.K);
        aa a2 = com.d.m.i.a().a();
        if (e == com.d.c.a.c.aj) {
            str = a2.C().a(str);
        }
        if (e == com.d.c.a.c.bo || e2 == com.d.c.a.c.aP) {
            str = a2.D().a(str);
        }
        if (e == com.d.c.a.c.m) {
            str = a2.E().a(str);
        }
        return str;
    }

    public static String b(String str, com.d.c.f.c cVar) {
        return a(str, cVar);
    }

    public static boolean a(int i) {
        switch (Character.getType(i)) {
            case 12:
            case 21:
            case 22:
            case 24:
            case 29:
            case 30:
                return true;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            default:
                return false;
        }
    }

    public ad(String str, int i) {
        this.f1109a = str;
        this.f1110b = i;
    }

    public String a() {
        return this.f1109a;
    }

    public int b() {
        return this.f1110b;
    }
}
