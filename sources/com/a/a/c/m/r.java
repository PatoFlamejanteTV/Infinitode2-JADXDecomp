package com.a.a.c.m;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/m/r.class */
public abstract class r {

    /* renamed from: a, reason: collision with root package name */
    public static final r f742a = new b();

    public abstract String a(String str);

    /* loaded from: infinitode-2.jar:com/a/a/c/m/r$b.class */
    public static final class b extends r implements Serializable {
        protected b() {
        }

        @Override // com.a.a.c.m.r
        public final String a(String str) {
            return str;
        }
    }

    public static r a(String str, String str2) {
        boolean z = (str == null || str.isEmpty()) ? false : true;
        boolean z2 = (str2 == null || str2.isEmpty()) ? false : true;
        if (z) {
            if (z2) {
                return new s(str, str2);
            }
            return new t(str);
        }
        if (z2) {
            return new u(str2);
        }
        return f742a;
    }

    public static r a(r rVar, r rVar2) {
        return new a(rVar, rVar2);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/r$a.class */
    public static class a extends r implements Serializable {

        /* renamed from: b, reason: collision with root package name */
        private r f743b;
        private r c;

        public a(r rVar, r rVar2) {
            this.f743b = rVar;
            this.c = rVar2;
        }

        @Override // com.a.a.c.m.r
        public final String a(String str) {
            return this.f743b.a(this.c.a(str));
        }

        public final String toString() {
            return "[ChainedTransformer(" + this.f743b + ", " + this.c + ")]";
        }
    }
}
