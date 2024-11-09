package com.a.a.b.f;

import com.a.a.b.o;

/* loaded from: infinitode-2.jar:com/a/a/b/f/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public Object f144a;

    /* renamed from: b, reason: collision with root package name */
    public Class<?> f145b;
    public Object c;
    public String d;
    public EnumC0003a e;
    public o f;
    public boolean g;

    /* renamed from: com.a.a.b.f.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/a/a/b/f/a$a.class */
    public enum EnumC0003a {
        WRAPPER_ARRAY,
        WRAPPER_OBJECT,
        METADATA_PROPERTY,
        PAYLOAD_PROPERTY,
        PARENT_PROPERTY;

        public final boolean a() {
            return this == METADATA_PROPERTY || this == PAYLOAD_PROPERTY;
        }
    }

    public a() {
    }

    public a(Object obj, o oVar) {
        this(obj, oVar, null);
    }

    private a(Object obj, o oVar, Object obj2) {
        this.f144a = obj;
        this.c = obj2;
        this.f = oVar;
    }
}
