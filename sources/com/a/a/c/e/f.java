package com.a.a.c.e;

import com.a.a.b.h;
import com.a.a.b.o;
import com.a.a.c.aa;
import com.a.a.c.i.i;
import com.a.a.c.k.b.an;
import java.nio.file.Path;

/* loaded from: infinitode-2.jar:com/a/a/c/e/f.class */
public final class f extends an<Path> {
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final /* bridge */ /* synthetic */ void a(Object obj, h hVar, aa aaVar) {
        a((Path) obj, hVar);
    }

    public f() {
        super(Path.class);
    }

    private static void a(Path path, h hVar) {
        hVar.b(path.toUri().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.an, com.a.a.c.o
    public void a(Path path, h hVar, aa aaVar, i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(path, Path.class, o.VALUE_STRING));
        a(path, hVar);
        iVar.b(hVar, a2);
    }
}
