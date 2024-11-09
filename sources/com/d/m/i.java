package com.d.m;

import com.d.e.aa;

/* loaded from: infinitode-2.jar:com/d/m/i.class */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<a> f1425a = new j();

    public static a a() {
        return f1425a.get();
    }

    public static void b() {
        f1425a.remove();
    }

    /* loaded from: infinitode-2.jar:com/d/m/i$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private aa f1426a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ a(byte b2) {
            this();
        }

        private a() {
        }

        public final aa a() {
            if (this.f1426a == null) {
                throw new NullPointerException("SharedContext must be registered in renderer.");
            }
            return this.f1426a;
        }

        public final void a(aa aaVar) {
            this.f1426a = aaVar;
        }
    }
}
