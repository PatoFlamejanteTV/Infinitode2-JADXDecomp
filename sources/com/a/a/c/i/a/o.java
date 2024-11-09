package com.a.a.c.i.a;

import com.a.a.a.af;
import com.a.a.c.i.c;
import com.a.a.c.y;
import java.lang.reflect.Type;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/o.class */
public class o implements com.a.a.c.i.h<o> {

    /* renamed from: a, reason: collision with root package name */
    private af.b f505a;

    /* renamed from: b, reason: collision with root package name */
    private af.a f506b;
    private String c;
    private boolean d;
    private Class<?> e;
    private com.a.a.c.i.g f;

    public o() {
        this.d = false;
    }

    private o(o oVar, Class<?> cls) {
        this.d = false;
        this.f505a = oVar.f505a;
        this.f506b = oVar.f506b;
        this.c = oVar.c;
        this.d = oVar.d;
        this.f = oVar.f;
        this.e = cls;
    }

    public static o b() {
        return new o().a(af.b.NONE, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.i.h
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public o a(af.b bVar, com.a.a.c.i.g gVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("idType cannot be null");
        }
        this.f505a = bVar;
        this.f = gVar;
        this.c = bVar.a();
        return this;
    }

    @Override // com.a.a.c.i.h
    public final com.a.a.c.i.i a(y yVar, com.a.a.c.j jVar, Collection<com.a.a.c.i.b> collection) {
        if (this.f505a == af.b.NONE || jVar.l()) {
            return null;
        }
        com.a.a.c.i.g a2 = a(yVar, jVar, a(yVar), collection, true, false);
        if (this.f505a == af.b.DEDUCTION) {
            return new d(a2, null, this.c);
        }
        switch (this.f506b) {
            case WRAPPER_ARRAY:
                return new b(a2, null);
            case PROPERTY:
                return new h(a2, null, this.c);
            case WRAPPER_OBJECT:
                return new j(a2, null);
            case EXTERNAL_PROPERTY:
                return new f(a2, null, this.c);
            case EXISTING_PROPERTY:
                return new d(a2, null, this.c);
            default:
                throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this.f506b);
        }
    }

    @Override // com.a.a.c.i.h
    public final com.a.a.c.i.e a(com.a.a.c.f fVar, com.a.a.c.j jVar, Collection<com.a.a.c.i.b> collection) {
        if (this.f505a == af.b.NONE || jVar.l()) {
            return null;
        }
        com.a.a.c.i.g a2 = a(fVar, jVar, a((com.a.a.c.b.q<?>) fVar, jVar), collection, false, true);
        com.a.a.c.j a3 = a(fVar, jVar);
        if (this.f505a == af.b.DEDUCTION) {
            return new c(jVar, a2, a3, fVar, collection);
        }
        switch (this.f506b) {
            case WRAPPER_ARRAY:
                return new a(jVar, a2, this.c, this.d, a3);
            case PROPERTY:
            case EXISTING_PROPERTY:
                return new g(jVar, a2, this.c, this.d, a3, this.f506b);
            case WRAPPER_OBJECT:
                return new i(jVar, a2, this.c, this.d, a3);
            case EXTERNAL_PROPERTY:
                return new e(jVar, a2, this.c, this.d, a3);
            default:
                throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this.f506b);
        }
    }

    private com.a.a.c.j a(com.a.a.c.f fVar, com.a.a.c.j jVar) {
        if (this.e != null) {
            if (this.e == Void.class || this.e == com.a.a.c.a.j.class) {
                return fVar.p().a((Type) this.e);
            }
            if (jVar.a(this.e)) {
                return jVar;
            }
            if (jVar.c(this.e)) {
                return fVar.p().a(jVar, this.e);
            }
            if (jVar.a(this.e)) {
                return jVar;
            }
        }
        if (fVar.a(com.a.a.c.q.USE_BASE_TYPE_AS_DEFAULT_IMPL) && !jVar.d()) {
            return jVar;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.i.h
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public o a(af.a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("includeAs cannot be null");
        }
        this.f506b = aVar;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.i.h
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public o a(String str) {
        if (str == null || str.isEmpty()) {
            str = this.f505a.a();
        }
        this.c = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.i.h
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public o a(Class<?> cls) {
        this.e = cls;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.i.h
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public o a(boolean z) {
        this.d = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.i.h
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public o b(Class<?> cls) {
        if (this.e == cls) {
            return this;
        }
        com.a.a.c.m.i.a((Class<?>) o.class, this, "withDefaultImpl");
        return new o(this, cls);
    }

    @Override // com.a.a.c.i.h
    public final Class<?> a() {
        return this.e;
    }

    private com.a.a.c.i.g a(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar, com.a.a.c.i.c cVar, Collection<com.a.a.c.i.b> collection, boolean z, boolean z2) {
        if (this.f != null) {
            return this.f;
        }
        if (this.f505a == null) {
            throw new IllegalStateException("Cannot build, 'init()' not yet called");
        }
        switch (this.f505a) {
            case DEDUCTION:
            case CLASS:
                return k.a(jVar, qVar, cVar);
            case MINIMAL_CLASS:
                return m.b(jVar, qVar, cVar);
            case NAME:
                return t.a(qVar, jVar, collection, z, z2);
            case NONE:
                return null;
            default:
                throw new IllegalStateException("Do not know how to construct standard type id resolver for idType: " + this.f505a);
        }
    }

    private static com.a.a.c.i.c a(com.a.a.c.b.q<?> qVar) {
        return qVar.o();
    }

    private com.a.a.c.i.c a(com.a.a.c.b.q<?> qVar, com.a.a.c.j jVar) {
        com.a.a.c.i.c a2 = a(qVar);
        if (this.f505a == af.b.CLASS || this.f505a == af.b.MINIMAL_CLASS) {
            c.b a3 = a2.a(qVar, jVar);
            if (a3 == c.b.DENIED) {
                return a(jVar, a2);
            }
            if (a3 == c.b.ALLOWED) {
                return l.f503a;
            }
        }
        return a2;
    }

    private static com.a.a.c.i.c a(com.a.a.c.j jVar, com.a.a.c.i.c cVar) {
        throw new IllegalArgumentException(String.format("Configured `PolymorphicTypeValidator` (of type %s) denied resolution of all subtypes of base type %s", com.a.a.c.m.i.d(cVar), com.a.a.c.m.i.d((Object) jVar.b())));
    }
}
