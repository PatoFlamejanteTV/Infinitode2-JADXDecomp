package com.d.e;

import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/d/e/u.class */
public class u implements Comparator<com.d.i.c> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public u(t tVar) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(com.d.i.c cVar, com.d.i.c cVar2) {
        return a(cVar, cVar2);
    }

    private static int a(com.d.i.c cVar, com.d.i.c cVar2) {
        return cVar.aa() - cVar2.aa();
    }
}
