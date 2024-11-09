package com.a.a.c.f;

import com.a.a.a.f;
import com.a.a.c.f.ap;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/* loaded from: infinitode-2.jar:com/a/a/c/f/ap.class */
public interface ap<T extends ap<T>> {
    T a(com.a.a.a.f fVar);

    T a(f.a aVar);

    T a(f.b bVar);

    T b(f.b bVar);

    T c(f.b bVar);

    T d(f.b bVar);

    T e(f.b bVar);

    boolean a(k kVar);

    boolean b(k kVar);

    boolean c(k kVar);

    boolean a(j jVar);

    boolean a(h hVar);

    /* loaded from: infinitode-2.jar:com/a/a/c/f/ap$a.class */
    public static class a implements ap<a>, Serializable {

        /* renamed from: a, reason: collision with root package name */
        private static a f445a;

        /* renamed from: b, reason: collision with root package name */
        private static a f446b;
        private f.b c;
        private f.b d;
        private f.b e;
        private f.b f;
        private f.b g;

        static {
            f.b bVar = f.b.PUBLIC_ONLY;
            f.b bVar2 = f.b.ANY;
            f445a = new a(bVar, bVar, bVar2, bVar2, f.b.PUBLIC_ONLY);
            f.b bVar3 = f.b.PUBLIC_ONLY;
            f.b bVar4 = f.b.PUBLIC_ONLY;
            f446b = new a(bVar3, bVar3, bVar4, bVar4, f.b.PUBLIC_ONLY);
        }

        public static a a() {
            return f445a;
        }

        public static a b() {
            return f446b;
        }

        private a(f.b bVar, f.b bVar2, f.b bVar3, f.b bVar4, f.b bVar5) {
            this.c = bVar;
            this.d = bVar2;
            this.e = bVar3;
            this.f = bVar4;
            this.g = bVar5;
        }

        private a a(f.b bVar, f.b bVar2, f.b bVar3, f.b bVar4, f.b bVar5) {
            if (bVar == this.c && bVar2 == this.d && bVar3 == this.e && bVar4 == this.f && bVar5 == this.g) {
                return this;
            }
            return new a(bVar, bVar2, bVar3, bVar4, bVar5);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.f.ap
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public a a(com.a.a.a.f fVar) {
            if (fVar != null) {
                return a(a(this.c, fVar.a()), a(this.d, fVar.b()), a(this.e, fVar.c()), a(this.f, fVar.d()), a(this.g, fVar.e()));
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.f.ap
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public a a(f.a aVar) {
            if (aVar != null) {
                return a(a(this.c, aVar.b()), a(this.d, aVar.c()), a(this.e, aVar.d()), a(this.f, aVar.e()), a(this.g, aVar.a()));
            }
            return this;
        }

        private static f.b a(f.b bVar, f.b bVar2) {
            if (bVar2 == f.b.DEFAULT) {
                return bVar;
            }
            return bVar2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.f.ap
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public a a(f.b bVar) {
            if (bVar == f.b.DEFAULT) {
                bVar = f445a.c;
            }
            return this.c == bVar ? this : new a(bVar, this.d, this.e, this.f, this.g);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.f.ap
        /* renamed from: g, reason: merged with bridge method [inline-methods] */
        public a b(f.b bVar) {
            if (bVar == f.b.DEFAULT) {
                bVar = f445a.d;
            }
            return this.d == bVar ? this : new a(this.c, bVar, this.e, this.f, this.g);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.f.ap
        /* renamed from: h, reason: merged with bridge method [inline-methods] */
        public a c(f.b bVar) {
            if (bVar == f.b.DEFAULT) {
                bVar = f445a.e;
            }
            return this.e == bVar ? this : new a(this.c, this.d, bVar, this.f, this.g);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.f.ap
        /* renamed from: i, reason: merged with bridge method [inline-methods] */
        public a d(f.b bVar) {
            if (bVar == f.b.DEFAULT) {
                bVar = f445a.f;
            }
            return this.f == bVar ? this : new a(this.c, this.d, this.e, bVar, this.g);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.f.ap
        /* renamed from: j, reason: merged with bridge method [inline-methods] */
        public a e(f.b bVar) {
            if (bVar == f.b.DEFAULT) {
                bVar = f445a.g;
            }
            return this.g == bVar ? this : new a(this.c, this.d, this.e, this.f, bVar);
        }

        private boolean a(Member member) {
            return this.f.a(member);
        }

        @Override // com.a.a.c.f.ap
        public final boolean a(j jVar) {
            return a(jVar.i());
        }

        private boolean a(Field field) {
            return this.g.a(field);
        }

        @Override // com.a.a.c.f.ap
        public final boolean a(h hVar) {
            return a(hVar.a());
        }

        private boolean a(Method method) {
            return this.c.a(method);
        }

        @Override // com.a.a.c.f.ap
        public final boolean a(k kVar) {
            return a(kVar.a());
        }

        private boolean b(Method method) {
            return this.d.a(method);
        }

        @Override // com.a.a.c.f.ap
        public final boolean b(k kVar) {
            return b(kVar.a());
        }

        private boolean c(Method method) {
            return this.e.a(method);
        }

        @Override // com.a.a.c.f.ap
        public final boolean c(k kVar) {
            return c(kVar.a());
        }

        public final String toString() {
            return String.format("[Visibility: getter=%s,isGetter=%s,setter=%s,creator=%s,field=%s]", this.c, this.d, this.e, this.f, this.g);
        }
    }
}
