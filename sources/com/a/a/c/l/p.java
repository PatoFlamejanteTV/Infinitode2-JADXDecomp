package com.a.a.c.l;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* loaded from: infinitode-2.jar:com/a/a/c/l/p.class */
public final class p implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private o f667a;

    public p(o oVar) {
        this.f667a = oVar;
    }

    public final com.a.a.c.j a(String str) {
        a aVar = new a(str.trim());
        com.a.a.c.j a2 = a(aVar);
        if (aVar.hasMoreTokens()) {
            throw a(aVar, "Unexpected tokens after complete type");
        }
        return a2;
    }

    private com.a.a.c.j a(a aVar) {
        if (!aVar.hasMoreTokens()) {
            throw a(aVar, "Unexpected end-of-string");
        }
        Class<?> a2 = a(aVar.nextToken(), aVar);
        if (aVar.hasMoreTokens()) {
            String nextToken = aVar.nextToken();
            if ("<".equals(nextToken)) {
                return this.f667a.a((c) null, a2, n.a(a2, b(aVar)));
            }
            aVar.a(nextToken);
        }
        return this.f667a.a((c) null, a2, n.a());
    }

    private List<com.a.a.c.j> b(a aVar) {
        ArrayList arrayList = new ArrayList();
        while (aVar.hasMoreTokens()) {
            arrayList.add(a(aVar));
            if (!aVar.hasMoreTokens()) {
                break;
            }
            String nextToken = aVar.nextToken();
            if (">".equals(nextToken)) {
                return arrayList;
            }
            if (!",".equals(nextToken)) {
                throw a(aVar, "Unexpected token '" + nextToken + "', expected ',' or '>')");
            }
        }
        throw a(aVar, "Unexpected end-of-string");
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Throwable, java.lang.Class<?>, java.lang.Class] */
    private Class<?> a(String str, a aVar) {
        ?? a2;
        try {
            a2 = this.f667a.a(str);
            return a2;
        } catch (Exception e) {
            com.a.a.c.m.i.b((Throwable) a2);
            throw a(aVar, "Cannot locate class '" + str + "', problem: " + e.getMessage());
        }
    }

    private static IllegalArgumentException a(a aVar, String str) {
        return new IllegalArgumentException(String.format("Failed to parse type '%s' (remaining: '%s'): %s", aVar.a(), aVar.b(), str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/l/p$a.class */
    public static final class a extends StringTokenizer {

        /* renamed from: a, reason: collision with root package name */
        private String f668a;

        /* renamed from: b, reason: collision with root package name */
        private int f669b;
        private String c;

        public a(String str) {
            super(str, "<,>", true);
            this.f668a = str;
        }

        @Override // java.util.StringTokenizer
        public final boolean hasMoreTokens() {
            return this.c != null || super.hasMoreTokens();
        }

        @Override // java.util.StringTokenizer
        public final String nextToken() {
            String trim;
            if (this.c != null) {
                trim = this.c;
                this.c = null;
            } else {
                String nextToken = super.nextToken();
                this.f669b += nextToken.length();
                trim = nextToken.trim();
            }
            return trim;
        }

        public final void a(String str) {
            this.c = str;
        }

        public final String a() {
            return this.f668a;
        }

        public final String b() {
            return this.f668a.substring(this.f669b);
        }
    }
}
