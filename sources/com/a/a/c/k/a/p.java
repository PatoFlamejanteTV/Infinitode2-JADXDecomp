package com.a.a.c.k.a;

import com.a.a.c.aa;
import com.a.a.c.k.b.ah;
import com.a.a.c.z;
import java.util.Collection;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/a/p.class */
public final class p extends ah<Collection<String>> {

    /* renamed from: a, reason: collision with root package name */
    public static final p f580a = new p();

    protected p() {
        super(Collection.class);
    }

    private p(p pVar, Boolean bool) {
        super(pVar, bool);
    }

    @Override // com.a.a.c.k.b.ah
    public final com.a.a.c.o<?> a(Boolean bool) {
        return new p(this, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(Collection<String> collection, com.a.a.b.h hVar, aa aaVar) {
        int size = collection.size();
        if (size == 1 && ((this.f602b == null && aaVar.a(z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this.f602b == Boolean.TRUE)) {
            b(collection, hVar, aaVar);
            return;
        }
        hVar.a(collection, size);
        b(collection, hVar, aaVar);
        hVar.h();
    }

    @Override // com.a.a.c.k.b.ah, com.a.a.c.o
    public final void a(Collection<String> collection, com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(collection, com.a.a.b.o.START_ARRAY));
        hVar.a(collection);
        b(collection, hVar, aaVar);
        iVar.b(hVar, a2);
    }

    private final void b(Collection<String> collection, com.a.a.b.h hVar, aa aaVar) {
        int i = 0;
        try {
            for (String str : collection) {
                if (str == null) {
                    aaVar.a(hVar);
                } else {
                    hVar.b(str);
                }
                i++;
            }
        } catch (Exception e) {
            a(aaVar, e, collection, i);
        }
    }
}
