package org.b.a;

import java.util.Iterator;
import org.b.a.a;

/* loaded from: infinitode-2.jar:org/b/a/b.class */
class b implements Iterable<c> {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ CharSequence f4698a;

    /* renamed from: b, reason: collision with root package name */
    private /* synthetic */ a f4699b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar, CharSequence charSequence) {
        this.f4699b = aVar;
        this.f4698a = charSequence;
    }

    @Override // java.lang.Iterable
    public final Iterator<c> iterator() {
        return new a.b(this.f4698a);
    }
}
