package org.a.c.h.e;

import java.io.File;
import org.a.b.f.ao;
import org.a.b.f.ap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/h/e/e.class */
public class e implements ao.a {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ File f4536a;

    /* renamed from: b, reason: collision with root package name */
    private /* synthetic */ d f4537b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(d dVar, File file) {
        this.f4537b = dVar;
        this.f4536a = file;
    }

    @Override // org.a.b.f.ao.a
    public final void a(ap apVar) {
        this.f4537b.a(apVar, this.f4536a);
    }
}
