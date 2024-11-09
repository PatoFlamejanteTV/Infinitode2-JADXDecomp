package com.d.g.a;

import com.d.e.aa;
import com.d.m.l;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/d/g/a/c.class */
public final class c implements com.d.d.f<InputStream> {

    /* renamed from: a, reason: collision with root package name */
    private final String f1205a;

    /* renamed from: b, reason: collision with root package name */
    private final aa f1206b;

    public c(aa aaVar, String str) {
        this.f1205a = str;
        this.f1206b = aaVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.d.d.f
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public InputStream a() {
        byte[] d = this.f1206b.n().d(this.f1205a);
        if (d != null) {
            return new ByteArrayInputStream(d);
        }
        l.c("Could not load @font-face font: " + this.f1205a);
        return null;
    }
}
