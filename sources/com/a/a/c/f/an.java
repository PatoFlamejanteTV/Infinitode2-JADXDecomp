package com.a.a.c.f;

import java.lang.reflect.Type;

/* loaded from: infinitode-2.jar:com/a/a/c/f/an.class */
public interface an {
    com.a.a.c.j a(Type type);

    /* loaded from: infinitode-2.jar:com/a/a/c/f/an$a.class */
    public static class a implements an {

        /* renamed from: a, reason: collision with root package name */
        private final com.a.a.c.l.o f443a;

        /* renamed from: b, reason: collision with root package name */
        private final com.a.a.c.l.n f444b;

        public a(com.a.a.c.l.o oVar, com.a.a.c.l.n nVar) {
            this.f443a = oVar;
            this.f444b = nVar;
        }

        @Override // com.a.a.c.f.an
        public final com.a.a.c.j a(Type type) {
            return this.f443a.a(type, this.f444b);
        }
    }
}
