package com.d.c.d;

import com.d.e.ad;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/j.class */
public final class j implements d {

    /* renamed from: a, reason: collision with root package name */
    private final short f1060a;

    /* renamed from: b, reason: collision with root package name */
    private final short f1061b;
    private String c;
    private float d;
    private String[] e;
    private final String f;
    private g g;
    private com.d.c.a.c h;
    private short i;
    private k j;
    private List<j> k;
    private List<ad> l;
    private com.d.i.e m;

    public j(short s, float f, String str) {
        this.f1060a = s;
        this.d = f;
        this.f1061b = (short) 1;
        this.f = str;
        if (s == 1 && f != 0.0f) {
            this.i = (short) 1;
        } else {
            this.i = (short) 2;
        }
    }

    public j(g gVar) {
        this.f1060a = (short) 25;
        this.f1061b = (short) 1;
        this.f = gVar.toString();
        this.g = gVar;
        this.i = (short) 3;
    }

    public j(short s, String str, String str2) {
        this.f1060a = s;
        this.c = str;
        this.f1061b = this.c.equalsIgnoreCase("inherit") ? (short) 0 : (short) 1;
        this.f = str2;
        if (s == 21) {
            this.i = (short) 4;
        } else {
            this.i = (short) 5;
        }
    }

    public j(com.d.c.a.c cVar) {
        this.f1060a = (short) 21;
        this.c = cVar.toString();
        this.f1061b = this.c.equals("inherit") ? (short) 0 : (short) 1;
        this.f = cVar.toString();
        this.i = (short) 4;
        this.h = cVar;
    }

    public j(List<j> list) {
        this.f1060a = (short) 0;
        this.f1061b = (short) 3;
        this.f = list.toString();
        this.k = list;
        this.i = (short) 6;
    }

    public j(List<ad> list, byte b2) {
        this.f1060a = (short) 0;
        this.f1061b = (short) 3;
        this.f = list.toString();
        this.l = list;
        this.i = (short) 8;
    }

    public j(com.d.i.e eVar) {
        this.f1060a = (short) 0;
        this.f1061b = (short) 3;
        this.f = eVar.toString();
        this.m = eVar;
        this.i = (short) 7;
    }

    @Override // com.d.c.d.d
    public final float b() {
        return this.d;
    }

    public final float f() {
        return this.d;
    }

    @Override // com.d.c.d.d
    public final short a() {
        return this.f1060a;
    }

    @Override // com.d.c.d.d
    public final String c() {
        return this.c;
    }

    @Override // com.d.c.d.e
    public final String d() {
        return this.f;
    }

    @Override // com.d.c.d.e
    public final short e() {
        return this.f1061b;
    }

    public final g g() {
        return this.g;
    }

    public final com.d.c.a.c h() {
        return this.h;
    }

    public final void a(com.d.c.a.c cVar) {
        this.h = cVar;
    }

    public final short i() {
        return this.i;
    }

    public final k j() {
        return this.j;
    }

    public final void a(k kVar) {
        this.j = kVar;
    }

    public final String[] k() {
        return com.d.m.a.a(this.e);
    }

    public final void a(String[] strArr) {
        this.e = com.d.m.a.a(strArr);
    }

    public final String toString() {
        return this.f;
    }

    public final List<j> l() {
        return new ArrayList(this.k);
    }

    public final List<ad> m() {
        return new ArrayList(this.l);
    }

    public final com.d.i.e n() {
        return this.m;
    }

    public final String o() {
        if (i() == 4) {
            if (this.h == null) {
                this.h = com.d.c.a.c.a(c());
            }
            return "I" + this.h.f968a;
        }
        return d();
    }
}
