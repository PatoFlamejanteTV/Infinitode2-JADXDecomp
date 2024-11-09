package org.a.b.f;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/b/f/z.class */
public final class z extends an {
    private List<y> c;
    private Map<Integer, Map<Integer, Map<Integer, Map<Integer, String>>>> d;
    private String e;
    private String f;
    private String g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z(ap apVar) {
        super(apVar);
        this.e = null;
        this.f = null;
        this.g = null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.a.b.f.an
    public final void a(ap apVar, ak akVar) {
        akVar.c();
        int c = akVar.c();
        akVar.c();
        this.c = new ArrayList(c);
        for (int i = 0; i < c; i++) {
            y yVar = new y();
            yVar.a(akVar);
            this.c.add(yVar);
        }
        for (y yVar2 : this.c) {
            if (yVar2.b() > C()) {
                yVar2.a((String) null);
            } else {
                akVar.a(D() + 6 + ((c << 1) * 6) + yVar2.b());
                int f = yVar2.f();
                int e = yVar2.e();
                Charset charset = org.a.b.h.b.f4351a;
                if (f == 3 && (e == 0 || e == 1)) {
                    charset = org.a.b.h.b.f4352b;
                } else if (f == 0) {
                    charset = org.a.b.h.b.f4352b;
                } else if (f == 2) {
                    switch (e) {
                        case 0:
                            charset = org.a.b.h.b.d;
                            break;
                        case 1:
                            charset = org.a.b.h.b.e;
                            break;
                        case 2:
                            charset = org.a.b.h.b.f4351a;
                            break;
                    }
                }
                yVar2.a(akVar.a(yVar2.a(), charset));
            }
        }
        this.d = new HashMap(this.c.size());
        for (y yVar3 : this.c) {
            Map<Integer, Map<Integer, Map<Integer, String>>> map = this.d.get(Integer.valueOf(yVar3.d()));
            Map<Integer, Map<Integer, Map<Integer, String>>> map2 = map;
            if (map == null) {
                map2 = new HashMap();
                this.d.put(Integer.valueOf(yVar3.d()), map2);
            }
            Map<Integer, Map<Integer, String>> map3 = map2.get(Integer.valueOf(yVar3.f()));
            Map<Integer, Map<Integer, String>> map4 = map3;
            if (map3 == null) {
                map4 = new HashMap();
                map2.put(Integer.valueOf(yVar3.f()), map4);
            }
            Map<Integer, String> map5 = map4.get(Integer.valueOf(yVar3.e()));
            Map<Integer, String> map6 = map5;
            if (map5 == null) {
                map6 = new HashMap();
                map4.put(Integer.valueOf(yVar3.e()), map6);
            }
            map6.put(Integer.valueOf(yVar3.c()), yVar3.g());
        }
        this.e = a(1);
        this.f = a(2);
        this.g = a(6, 1, 0, 0);
        if (this.g == null) {
            this.g = a(6, 3, 1, 1033);
        }
        if (this.g != null) {
            this.g = this.g.trim();
        }
        this.f4283a = true;
    }

    private String a(int i) {
        for (int i2 = 4; i2 >= 0; i2--) {
            String a2 = a(i, 0, i2, 0);
            if (a2 != null) {
                return a2;
            }
        }
        String a3 = a(i, 3, 1, 1033);
        if (a3 != null) {
            return a3;
        }
        String a4 = a(i, 1, 0, 0);
        if (a4 != null) {
            return a4;
        }
        return null;
    }

    private String a(int i, int i2, int i3, int i4) {
        Map<Integer, Map<Integer, String>> map;
        Map<Integer, String> map2;
        Map<Integer, Map<Integer, Map<Integer, String>>> map3 = this.d.get(Integer.valueOf(i));
        if (map3 == null || (map = map3.get(Integer.valueOf(i2))) == null || (map2 = map.get(Integer.valueOf(i3))) == null) {
            return null;
        }
        return map2.get(Integer.valueOf(i4));
    }

    public final List<y> a() {
        return this.c;
    }

    public final String b() {
        return this.e;
    }

    public final String c() {
        return this.f;
    }

    public final String d() {
        return this.g;
    }
}
