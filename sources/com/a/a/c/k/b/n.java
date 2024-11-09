package com.a.a.c.k.b;

import com.a.a.a.l;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.Objects;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/b/n.class */
public final class n extends an<Enum<?>> implements com.a.a.c.k.k {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.m.m f625a;

    /* renamed from: b, reason: collision with root package name */
    private Boolean f626b;

    private n(com.a.a.c.m.m mVar, Boolean bool) {
        super(mVar.a(), (byte) 0);
        this.f625a = mVar;
        this.f626b = bool;
    }

    public static n a(Class<?> cls, com.a.a.c.y yVar, l.d dVar) {
        return new n(com.a.a.c.m.m.a((com.a.a.c.b.q<?>) yVar, (Class<Enum<?>>) cls), a(cls, dVar, true, (Boolean) null));
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        l.d a2 = a(aaVar, cVar, (Class<?>) a());
        if (a2 != null) {
            Boolean a3 = a((Class<?>) a(), a2, false, this.f626b);
            if (!Objects.equals(a3, this.f626b)) {
                return new n(this.f625a, a3);
            }
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(Enum<?> r5, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        if (a(aaVar)) {
            hVar.c(r5.ordinal());
        } else if (aaVar.a(com.a.a.c.z.WRITE_ENUMS_USING_TO_STRING)) {
            hVar.b(r5.toString());
        } else {
            hVar.c(this.f625a.a(r5));
        }
    }

    private boolean a(com.a.a.c.aa aaVar) {
        if (this.f626b != null) {
            return this.f626b.booleanValue();
        }
        return aaVar.a(com.a.a.c.z.WRITE_ENUMS_USING_INDEX);
    }

    private static Boolean a(Class<?> cls, l.d dVar, boolean z, Boolean bool) {
        l.c c = dVar == null ? null : dVar.c();
        l.c cVar = c;
        if (c == null) {
            return bool;
        }
        if (cVar == l.c.ANY || cVar == l.c.SCALAR) {
            return bool;
        }
        if (cVar == l.c.STRING || cVar == l.c.NATURAL) {
            return Boolean.FALSE;
        }
        if (!cVar.a() && cVar != l.c.ARRAY) {
            Object[] objArr = new Object[3];
            objArr[0] = cVar;
            objArr[1] = cls.getName();
            objArr[2] = z ? Attribute.CLASS_ATTR : "property";
            throw new IllegalArgumentException(String.format("Unsupported serialization shape (%s) for Enum %s, not supported as %s annotation", objArr));
        }
        return Boolean.TRUE;
    }
}
