package org.a.b.f;

import java.util.Comparator;
import java.util.List;
import org.a.b.f.n;

/* loaded from: infinitode-2.jar:org/a/b/f/o.class */
class o implements Comparator<n.d> {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ List f4328a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(n nVar, List list) {
        this.f4328a = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(n.d dVar, n.d dVar2) {
        int indexOf = this.f4328a.indexOf(dVar.f4311a);
        int indexOf2 = this.f4328a.indexOf(dVar2.f4311a);
        if (indexOf < indexOf2) {
            return -1;
        }
        return indexOf == indexOf2 ? 0 : 1;
    }
}
