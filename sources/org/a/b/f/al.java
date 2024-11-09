package org.a.b.f;

import com.vladsch.flexmark.util.html.Attribute;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:org/a/b/f/al.class */
public class al {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4279a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4280b;

    public al() {
        this(false);
    }

    public al(boolean z) {
        this(z, false);
    }

    public al(boolean z, boolean z2) {
        this.f4279a = false;
        this.f4280b = false;
        this.f4279a = z;
        this.f4280b = z2;
    }

    public ap b(File file) {
        ah ahVar = new ah(file, "r");
        try {
            return b(ahVar);
        } catch (IOException e) {
            ahVar.close();
            throw e;
        }
    }

    public ap b(InputStream inputStream) {
        return b(new x(inputStream));
    }

    public final ap c(InputStream inputStream) {
        this.f4279a = true;
        return b(new x(inputStream));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ap b(ak akVar) {
        ap a2 = a(akVar);
        a2.a(akVar.h());
        int c = akVar.c();
        akVar.c();
        akVar.c();
        akVar.c();
        for (int i = 0; i < c; i++) {
            an a3 = a(a2, akVar);
            if (a3 != null) {
                a2.a(a3);
            }
        }
        if (!this.f4280b) {
            a(a2);
        }
        return a2;
    }

    ap a(ak akVar) {
        return new ap(akVar);
    }

    private void a(ap apVar) {
        for (an anVar : apVar.h()) {
            if (!anVar.F()) {
                apVar.c(anVar);
            }
        }
        boolean z = a() && apVar.f4288a.containsKey("CFF ");
        if (apVar.n() == null) {
            throw new IOException("head is mandatory");
        }
        if (apVar.o() == null) {
            throw new IOException("hhead is mandatory");
        }
        if (apVar.m() == null) {
            throw new IOException("maxp is mandatory");
        }
        if (apVar.k() == null && !this.f4279a) {
            throw new IOException("post is mandatory");
        }
        if (!z) {
            if (apVar.q() == null) {
                throw new IOException("loca is mandatory");
            }
            if (apVar.e() == null) {
                throw new IOException("glyf is mandatory");
            }
        }
        if (apVar.j() == null && !this.f4279a) {
            throw new IOException("name is mandatory");
        }
        if (apVar.p() == null) {
            throw new IOException("hmtx is mandatory");
        }
        if (!this.f4279a && apVar.r() == null) {
            throw new IOException("cmap is mandatory");
        }
    }

    protected boolean a() {
        return false;
    }

    private an a(ap apVar, ak akVar) {
        an a2;
        String a3 = akVar.a(4);
        if (a3.equals("cmap")) {
            a2 = new f(apVar);
        } else if (a3.equals("glyf")) {
            a2 = new p(apVar);
        } else if (a3.equals("head")) {
            a2 = new q(apVar);
        } else if (a3.equals("hhea")) {
            a2 = new r(apVar);
        } else if (a3.equals("hmtx")) {
            a2 = new s(apVar);
        } else if (a3.equals("loca")) {
            a2 = new t(apVar);
        } else if (a3.equals("maxp")) {
            a2 = new w(apVar);
        } else if (a3.equals(Attribute.NAME_ATTR)) {
            a2 = new z(apVar);
        } else if (a3.equals("OS/2")) {
            a2 = new aa(apVar);
        } else if (a3.equals("post")) {
            a2 = new ag(apVar);
        } else if (a3.equals("DSIG")) {
            a2 = new g(apVar);
        } else if (a3.equals("kern")) {
            a2 = new v(apVar);
        } else if (a3.equals("vhea")) {
            a2 = new aq(apVar);
        } else if (a3.equals("vmtx")) {
            a2 = new ar(apVar);
        } else if (a3.equals("VORG")) {
            a2 = new as(apVar);
        } else if (a3.equals("GSUB")) {
            a2 = new n(apVar);
        } else {
            a2 = a(apVar, a3);
        }
        a2.a(a3);
        akVar.k();
        a2.b(akVar.k());
        a2.a(akVar.k());
        if (a2.C() == 0 && !a3.equals("glyf")) {
            return null;
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public an a(ap apVar, String str) {
        return new an(apVar);
    }
}
