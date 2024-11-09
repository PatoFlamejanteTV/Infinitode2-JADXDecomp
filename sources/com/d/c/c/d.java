package com.d.c.c;

import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/d/c/c/d.class */
class d implements Comparator<com.d.c.e.c> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public d(c cVar) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(com.d.c.e.c cVar, com.d.c.e.c cVar2) {
        return a(cVar, cVar2);
    }

    private static int a(com.d.c.e.c cVar, com.d.c.e.c cVar2) {
        if (cVar.d() - cVar2.d() < 0) {
            return -1;
        }
        if (cVar.d() == cVar2.d()) {
            return 0;
        }
        return 1;
    }
}
