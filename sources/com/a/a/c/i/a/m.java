package com.a.a.c.i.a;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/m.class */
public final class m extends k {
    private String c;
    private String d;

    private m(com.a.a.c.j jVar, com.a.a.c.l.o oVar, com.a.a.c.i.c cVar) {
        super(jVar, oVar, cVar);
        String name = jVar.b().getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf < 0) {
            this.c = "";
            this.d = ".";
        } else {
            this.d = name.substring(0, lastIndexOf + 1);
            this.c = name.substring(0, lastIndexOf);
        }
    }

    public static m b(com.a.a.c.j jVar, com.a.a.c.b.q<?> qVar, com.a.a.c.i.c cVar) {
        return new m(jVar, qVar.p(), cVar);
    }

    @Override // com.a.a.c.i.a.k, com.a.a.c.i.g
    public final String a(Object obj) {
        String name = obj.getClass().getName();
        if (name.startsWith(this.d)) {
            return name.substring(this.d.length() - 1);
        }
        return name;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.a.a.c.i.a.k
    public final com.a.a.c.j a(String str, com.a.a.c.d dVar) {
        if (str.startsWith(".")) {
            StringBuilder sb = new StringBuilder(str.length() + this.c.length());
            if (this.c.isEmpty()) {
                sb.append(str.substring(1));
            } else {
                sb.append(this.c).append(str);
            }
            str = sb.toString();
        }
        return super.a(str, dVar);
    }
}
