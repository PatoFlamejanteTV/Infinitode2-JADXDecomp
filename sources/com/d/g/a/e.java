package com.d.g.a;

import java.util.Comparator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: infinitode-2.jar:com/d/g/a/e.class */
class e<T> implements Comparator<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public e(d dVar) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return a((f) obj, (f) obj2);
    }

    /* JADX WARN: Incorrect types in method signature: (TT;TT;)I */
    private static int a(f fVar, f fVar2) {
        return fVar.a() - fVar2.a();
    }
}
