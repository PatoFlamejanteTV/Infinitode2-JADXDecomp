package com.a.a.c.i;

import com.a.a.c.b.q;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/i/c.class */
public abstract class c implements Serializable {

    /* loaded from: infinitode-2.jar:com/a/a/c/i/c$b.class */
    public enum b {
        ALLOWED,
        DENIED,
        INDETERMINATE
    }

    public abstract b a(q<?> qVar, com.a.a.c.j jVar);

    public abstract b a();

    public abstract b a(q<?> qVar, com.a.a.c.j jVar, com.a.a.c.j jVar2);

    /* loaded from: infinitode-2.jar:com/a/a/c/i/c$a.class */
    public static abstract class a extends c implements Serializable {
        @Override // com.a.a.c.i.c
        public b a(q<?> qVar, com.a.a.c.j jVar) {
            return b.INDETERMINATE;
        }

        @Override // com.a.a.c.i.c
        public b a() {
            return b.INDETERMINATE;
        }

        @Override // com.a.a.c.i.c
        public b a(q<?> qVar, com.a.a.c.j jVar, com.a.a.c.j jVar2) {
            return b.INDETERMINATE;
        }
    }
}
