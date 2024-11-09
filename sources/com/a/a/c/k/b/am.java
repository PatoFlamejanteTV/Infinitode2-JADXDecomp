package com.a.a.c.k.b;

import com.a.a.c.k.a.k;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/am.class */
public abstract class am {

    /* renamed from: a, reason: collision with root package name */
    private static com.a.a.c.o<Object> f606a;

    static {
        new al();
        f606a = new d();
    }

    public static com.a.a.c.o<Object> a(Class<?> cls, boolean z) {
        if (cls == null || cls == Object.class) {
            return new b();
        }
        if (cls == String.class) {
            return f606a;
        }
        if (cls.isPrimitive()) {
            cls = com.a.a.c.m.i.i(cls);
        }
        if (cls == Integer.class) {
            return new a(5, cls);
        }
        if (cls == Long.class) {
            return new a(6, cls);
        }
        if (cls.isPrimitive() || Number.class.isAssignableFrom(cls)) {
            return new a(8, cls);
        }
        if (cls == Class.class) {
            return new a(3, cls);
        }
        if (Date.class.isAssignableFrom(cls)) {
            return new a(1, cls);
        }
        if (Calendar.class.isAssignableFrom(cls)) {
            return new a(2, cls);
        }
        if (cls == UUID.class) {
            return new a(8, cls);
        }
        if (cls == byte[].class) {
            return new a(7, cls);
        }
        return null;
    }

    public static com.a.a.c.o<Object> a(com.a.a.c.y yVar, Class<?> cls) {
        if (cls != null) {
            if (cls == Enum.class) {
                return new b();
            }
            if (com.a.a.c.m.i.k(cls)) {
                return c.a(cls, com.a.a.c.m.m.a((com.a.a.c.b.q<?>) yVar, (Class<Enum<?>>) cls));
            }
        }
        return new a(8, cls);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/am$a.class */
    public static class a extends ao<Object> {

        /* renamed from: a, reason: collision with root package name */
        private int f607a;

        public a(int i, Class<?> cls) {
            super(cls, (byte) 0);
            this.f607a = i;
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            String name;
            switch (this.f607a) {
                case 1:
                    aaVar.b((Date) obj, hVar);
                    return;
                case 2:
                    aaVar.a(((Calendar) obj).getTimeInMillis(), hVar);
                    return;
                case 3:
                    hVar.a(((Class) obj).getName());
                    return;
                case 4:
                    if (aaVar.a(com.a.a.c.z.WRITE_ENUMS_USING_TO_STRING)) {
                        name = obj.toString();
                    } else {
                        Enum r0 = (Enum) obj;
                        if (aaVar.a(com.a.a.c.z.WRITE_ENUM_KEYS_USING_INDEX)) {
                            name = String.valueOf(r0.ordinal());
                        } else {
                            name = r0.name();
                        }
                    }
                    hVar.a(name);
                    return;
                case 5:
                case 6:
                    hVar.a(((Number) obj).longValue());
                    return;
                case 7:
                    hVar.a(aaVar.a().v().a((byte[]) obj));
                    return;
                default:
                    hVar.a(obj.toString());
                    return;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/am$b.class */
    public static class b extends ao<Object> {

        /* renamed from: a, reason: collision with root package name */
        private transient com.a.a.c.k.a.k f608a;

        public b() {
            super(String.class, (byte) 0);
            this.f608a = com.a.a.c.k.a.k.a();
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            Class<?> cls = obj.getClass();
            com.a.a.c.k.a.k kVar = this.f608a;
            com.a.a.c.o<Object> a2 = kVar.a(cls);
            com.a.a.c.o<Object> oVar = a2;
            if (a2 == null) {
                oVar = a(kVar, cls, aaVar);
            }
            oVar.a(obj, hVar, aaVar);
        }

        private com.a.a.c.o<Object> a(com.a.a.c.k.a.k kVar, Class<?> cls, com.a.a.c.aa aaVar) {
            if (cls == Object.class) {
                a aVar = new a(8, cls);
                this.f608a = kVar.b(cls, aVar);
                return aVar;
            }
            k.d c = kVar.c(cls, aaVar, null);
            if (kVar != c.f568b) {
                this.f608a = c.f568b;
            }
            return c.f567a;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/am$d.class */
    public static class d extends ao<Object> {
        public d() {
            super(String.class, (byte) 0);
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            hVar.a((String) obj);
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/am$c.class */
    public static class c extends ao<Object> {

        /* renamed from: a, reason: collision with root package name */
        private com.a.a.c.m.m f609a;

        private c(Class<?> cls, com.a.a.c.m.m mVar) {
            super(cls, (byte) 0);
            this.f609a = mVar;
        }

        public static c a(Class<?> cls, com.a.a.c.m.m mVar) {
            return new c(cls, mVar);
        }

        @Override // com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            if (aaVar.a(com.a.a.c.z.WRITE_ENUMS_USING_TO_STRING)) {
                hVar.a(obj.toString());
                return;
            }
            Enum<?> r0 = (Enum) obj;
            if (aaVar.a(com.a.a.c.z.WRITE_ENUM_KEYS_USING_INDEX)) {
                hVar.a(String.valueOf(r0.ordinal()));
            } else {
                hVar.b(this.f609a.a(r0));
            }
        }
    }
}
