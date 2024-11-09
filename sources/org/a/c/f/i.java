package org.a.c.f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.a.c.b.n;
import org.a.c.b.p;

/* loaded from: infinitode-2.jar:org/a/c/f/i.class */
public final class i extends a {
    private final p c;
    private final l d;

    public i(p pVar, org.a.c.b.e eVar, l lVar) {
        super(new d(pVar.k()));
        this.c = pVar;
        this.f4430b = eVar;
        this.d = lVar;
    }

    public final void p() {
        org.a.c.b.a aVar;
        int i;
        org.a.c.b.b a2 = this.c.a(org.a.c.b.j.dX);
        if (!(a2 instanceof org.a.c.b.a)) {
            throw new IOException("/W array is missing in Xref stream");
        }
        org.a.c.b.a aVar2 = (org.a.c.b.a) a2;
        org.a.c.b.b a3 = this.c.a(org.a.c.b.j.bG);
        if (a3 instanceof org.a.c.b.a) {
            aVar = (org.a.c.b.a) a3;
        } else {
            org.a.c.b.a aVar3 = new org.a.c.b.a();
            aVar = aVar3;
            aVar3.a((org.a.c.b.b) org.a.c.b.i.f4366a);
            aVar.a((org.a.c.b.b) org.a.c.b.i.a(this.c.b(org.a.c.b.j.dr, 0)));
        }
        ArrayList arrayList = new ArrayList();
        Iterator<org.a.c.b.b> it = aVar.iterator();
        while (it.hasNext()) {
            org.a.c.b.b next = it.next();
            if (!(next instanceof org.a.c.b.i)) {
                throw new IOException("Xref stream must have integer in /Index array");
            }
            long b2 = ((org.a.c.b.i) next).b();
            if (!it.hasNext()) {
                break;
            }
            org.a.c.b.b next2 = it.next();
            if (!(next2 instanceof org.a.c.b.i)) {
                throw new IOException("Xref stream must have integer in /Index array");
            }
            int c = ((org.a.c.b.i) next2).c();
            for (int i2 = 0; i2 < c; i2++) {
                arrayList.add(Long.valueOf(b2 + i2));
            }
        }
        Iterator it2 = arrayList.iterator();
        int a4 = aVar2.a(0, 0);
        int a5 = aVar2.a(1, 0);
        int a6 = aVar2.a(2, 0);
        int i3 = a4 + a5 + a6;
        while (!this.f4429a.d() && it2.hasNext()) {
            byte[] bArr = new byte[i3];
            this.f4429a.a(bArr);
            if (a4 == 0) {
                i = 1;
            } else {
                i = 0;
                for (int i4 = 0; i4 < a4; i4++) {
                    i += (bArr[i4] & 255) << (((a4 - i4) - 1) << 3);
                }
            }
            Long l = (Long) it2.next();
            switch (i) {
                case 1:
                    int i5 = 0;
                    for (int i6 = 0; i6 < a5; i6++) {
                        i5 += (bArr[i6 + a4] & 255) << (((a5 - i6) - 1) << 3);
                    }
                    int i7 = 0;
                    for (int i8 = 0; i8 < a6; i8++) {
                        i7 += (bArr[(i8 + a4) + a5] & 255) << (((a6 - i8) - 1) << 3);
                    }
                    this.d.a(new n(l.longValue(), i7), i5);
                    break;
                case 2:
                    int i9 = 0;
                    for (int i10 = 0; i10 < a5; i10++) {
                        i9 += (bArr[i10 + a4] & 255) << (((a5 - i10) - 1) << 3);
                    }
                    this.d.a(new n(l.longValue(), 0), -i9);
                    break;
            }
        }
    }
}
