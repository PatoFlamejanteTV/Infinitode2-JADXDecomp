package com.a.a.c.k.a;

import com.a.a.c.aa;
import com.a.a.c.k.b.ah;
import com.a.a.c.z;
import java.util.List;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/a/f.class */
public final class f extends ah<List<String>> {

    /* renamed from: a, reason: collision with root package name */
    public static final f f558a = new f();

    protected f() {
        super(List.class);
    }

    private f(f fVar, Boolean bool) {
        super(fVar, bool);
    }

    @Override // com.a.a.c.k.b.ah
    public final com.a.a.c.o<?> a(Boolean bool) {
        return new f(this, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(List<String> list, com.a.a.b.h hVar, aa aaVar) {
        int size = list.size();
        if (size == 1 && ((this.f602b == null && aaVar.a(z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this.f602b == Boolean.TRUE)) {
            a(list, hVar, aaVar, 1);
            return;
        }
        hVar.a(list, size);
        a(list, hVar, aaVar, size);
        hVar.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.ah, com.a.a.c.o
    public void a(List<String> list, com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(list, com.a.a.b.o.START_ARRAY));
        hVar.a(list);
        a(list, hVar, aaVar, list.size());
        iVar.b(hVar, a2);
    }

    private final void a(List<String> list, com.a.a.b.h hVar, aa aaVar, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            try {
                String str = list.get(i2);
                if (str == null) {
                    aaVar.a(hVar);
                } else {
                    hVar.b(str);
                }
            } catch (Exception e) {
                a(aaVar, e, list, i2);
                return;
            }
        }
    }
}
