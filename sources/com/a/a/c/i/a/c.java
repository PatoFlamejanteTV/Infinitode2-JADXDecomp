package com.a.a.c.i.a;

import com.a.a.c.m.ac;
import java.lang.reflect.Type;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/i/a/c.class */
public final class c extends g {
    private static final BitSet g = new BitSet(0);
    private final Map<String, Integer> h;
    private final Map<BitSet, String> i;

    public c(com.a.a.c.j jVar, com.a.a.c.i.g gVar, com.a.a.c.j jVar2, com.a.a.c.f fVar, Collection<com.a.a.c.i.b> collection) {
        super(jVar, gVar, null, false, jVar2, null);
        this.h = new HashMap();
        this.i = a(fVar, collection);
    }

    private c(c cVar, com.a.a.c.c cVar2) {
        super(cVar, cVar2);
        this.h = cVar.h;
        this.i = cVar.i;
    }

    @Override // com.a.a.c.i.a.g, com.a.a.c.i.a.a, com.a.a.c.i.e
    public final com.a.a.c.i.e a(com.a.a.c.c cVar) {
        return cVar == this.c ? this : new c(this, cVar);
    }

    private Map<BitSet, String> a(com.a.a.c.f fVar, Collection<com.a.a.c.i.b> collection) {
        boolean a2 = fVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        int i = 0;
        HashMap hashMap = new HashMap();
        for (com.a.a.c.i.b bVar : collection) {
            List<com.a.a.c.f.s> h = fVar.a(fVar.p().a((Type) bVar.a())).h();
            BitSet bitSet = new BitSet(i + h.size());
            Iterator<com.a.a.c.f.s> it = h.iterator();
            while (it.hasNext()) {
                String a3 = it.next().a();
                if (a2) {
                    a3 = a3.toLowerCase();
                }
                Integer num = this.h.get(a3);
                Integer num2 = num;
                if (num == null) {
                    num2 = Integer.valueOf(i);
                    int i2 = i;
                    i++;
                    this.h.put(a3, Integer.valueOf(i2));
                }
                bitSet.set(num2.intValue());
            }
            String str = (String) hashMap.put(bitSet, bVar.a().getName());
            if (str != null) {
                throw new IllegalStateException(String.format("Subtypes %s and %s have the same signature and cannot be uniquely deduced.", str, bVar.a().getName()));
            }
        }
        return hashMap;
    }

    @Override // com.a.a.c.i.a.g, com.a.a.c.i.a.a, com.a.a.c.i.e
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        String str;
        com.a.a.b.o k = lVar.k();
        com.a.a.b.o oVar = k;
        if (k == com.a.a.b.o.START_OBJECT) {
            oVar = lVar.g();
        } else if (oVar != com.a.a.b.o.FIELD_NAME) {
            return b(lVar, gVar, null, "Unexpected input");
        }
        if (oVar == com.a.a.b.o.END_OBJECT && (str = this.i.get(g)) != null) {
            return a(lVar, gVar, null, str);
        }
        LinkedList linkedList = new LinkedList(this.i.keySet());
        ac a2 = gVar.a(lVar);
        boolean a3 = gVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        while (oVar == com.a.a.b.o.FIELD_NAME) {
            String v = lVar.v();
            if (a3) {
                v = v.toLowerCase();
            }
            a2.b(lVar);
            Integer num = this.h.get(v);
            if (num != null) {
                a(linkedList, num.intValue());
                if (linkedList.size() == 1) {
                    return a(lVar, gVar, a2, this.i.get(linkedList.get(0)));
                }
            }
            oVar = lVar.g();
        }
        return b(lVar, gVar, a2, String.format("Cannot deduce unique subtype of %s (%d candidates match)", com.a.a.c.m.i.b(this.f512b), Integer.valueOf(linkedList.size())));
    }

    private static void a(List<BitSet> list, int i) {
        Iterator<BitSet> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().get(i)) {
                it.remove();
            }
        }
    }
}
