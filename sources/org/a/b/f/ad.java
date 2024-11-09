package org.a.b.f;

import java.awt.geom.GeneralPath;

/* loaded from: infinitode-2.jar:org/a/b/f/ad.class */
public final class ad extends ap {

    /* renamed from: b, reason: collision with root package name */
    private boolean f4271b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ad(ak akVar) {
        super(akVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.a.b.f.ap
    public final void a(float f) {
        this.f4271b = Float.floatToIntBits(f) == 1184802985;
        super.a(f);
    }

    public final b a() {
        if (!this.f4271b) {
            throw new UnsupportedOperationException("TTF fonts do not have a CFF table");
        }
        return (b) d("CFF ");
    }

    @Override // org.a.b.f.ap
    public final p e() {
        if (this.f4271b) {
            throw new UnsupportedOperationException("OTF fonts do not have a glyf table");
        }
        return super.e();
    }

    @Override // org.a.b.f.ap
    public final GeneralPath c(String str) {
        return a().a().c(e(str)).b();
    }

    public final boolean f() {
        return this.f4288a.containsKey("CFF ");
    }

    public final boolean g() {
        return this.f4288a.containsKey("BASE") || this.f4288a.containsKey("GDEF") || this.f4288a.containsKey("GPOS") || this.f4288a.containsKey("GSUB") || this.f4288a.containsKey("JSTF");
    }
}
