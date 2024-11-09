package org.a.b.f;

import java.io.File;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:org/a/b/f/ab.class */
public final class ab extends al {
    @Override // org.a.b.f.al
    final /* synthetic */ ap a(ak akVar) {
        return d(akVar);
    }

    public ab() {
    }

    public ab(boolean z) {
        this(true, false);
    }

    public ab(boolean z, boolean z2) {
        super(z, z2);
    }

    @Override // org.a.b.f.al
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final ad b(File file) {
        return (ad) super.b(file);
    }

    @Override // org.a.b.f.al
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final ad b(InputStream inputStream) {
        return (ad) super.b(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // org.a.b.f.al
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public ad b(ak akVar) {
        return (ad) super.b(akVar);
    }

    private static ad d(ak akVar) {
        return new ad(akVar);
    }

    @Override // org.a.b.f.al
    protected final an a(ap apVar, String str) {
        if (str.equals("BASE") || str.equals("GDEF") || str.equals("GPOS") || str.equals("GSUB") || str.equals("JSTF")) {
            return new ac(apVar);
        }
        if (str.equals("CFF ")) {
            return new b(apVar);
        }
        return super.a(apVar, str);
    }

    @Override // org.a.b.f.al
    protected final boolean a() {
        return true;
    }
}
