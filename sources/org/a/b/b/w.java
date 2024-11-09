package org.a.b.b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/b/b/w.class */
public class w extends t {
    private float c;
    private float d;
    private int e;

    public w(org.a.b.g.c cVar, String str, String str2, int i, List<Object> list, int i2, int i3) {
        super(cVar, str, str2);
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0;
        this.c = i2;
        this.d = i3;
        a(list);
    }

    private void a(List<Object> list) {
        this.f4246a = new ArrayList();
        this.e = 0;
        new x(this).a(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Number> a(List<Number> list, q qVar) {
        this.f4247b++;
        String str = q.f4244b.get(qVar.a());
        if ("hstem".equals(str)) {
            a(list, list.size() % 2 != 0);
            return null;
        }
        if ("vstem".equals(str)) {
            a(list, list.size() % 2 != 0);
            return null;
        }
        if ("vmoveto".equals(str)) {
            list = a(list, list.size() > 1);
            c();
        } else {
            if ("rlineto".equals(str)) {
                b(a(list, 2), qVar);
                return null;
            }
            if ("hlineto".equals(str)) {
                b(list, true);
                return null;
            }
            if ("vlineto".equals(str)) {
                b(list, false);
                return null;
            }
            if ("rrcurveto".equals(str)) {
                b(a(list, 6), qVar);
                return null;
            }
            if ("endchar".equals(str)) {
                List<Number> a2 = a(list, list.size() == 5 || list.size() == 1);
                d();
                if (a2.size() == 4) {
                    a2.add(0, 0);
                    c(a2, new q(12, 6));
                    return null;
                }
                c(a2, qVar);
                return null;
            }
            if ("rmoveto".equals(str)) {
                list = a(list, list.size() > 2);
                c();
            } else if ("hmoveto".equals(str)) {
                list = a(list, list.size() > 1);
                c();
            } else {
                if ("vhcurveto".equals(str)) {
                    c(list, false);
                    return null;
                }
                if ("hvcurveto".equals(str)) {
                    c(list, true);
                    return null;
                }
                if ("hflex".equals(str)) {
                    b(Arrays.asList(Arrays.asList(list.get(0), 0, list.get(1), list.get(2), list.get(3), 0), Arrays.asList(list.get(4), 0, list.get(5), Float.valueOf(-list.get(2).floatValue()), list.get(6), 0)), new q(8));
                    return null;
                }
                if ("flex".equals(str)) {
                    b(Arrays.asList(list.subList(0, 6), list.subList(6, 12)), new q(8));
                    return null;
                }
                if ("hflex1".equals(str)) {
                    b(Arrays.asList(Arrays.asList(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), 0), Arrays.asList(list.get(5), 0, list.get(6), list.get(7), list.get(8), 0)), new q(8));
                    return null;
                }
                if ("flex1".equals(str)) {
                    int i = 0;
                    int i2 = 0;
                    for (int i3 = 0; i3 < 5; i3++) {
                        i += list.get(i3 << 1).intValue();
                        i2 += list.get((i3 << 1) + 1).intValue();
                    }
                    List<Number> subList = list.subList(0, 6);
                    Number[] numberArr = new Number[6];
                    numberArr[0] = list.get(6);
                    numberArr[1] = list.get(7);
                    numberArr[2] = list.get(8);
                    numberArr[3] = list.get(9);
                    numberArr[4] = Math.abs(i) > Math.abs(i2) ? list.get(10) : Integer.valueOf(-i);
                    numberArr[5] = Math.abs(i) > Math.abs(i2) ? Integer.valueOf(-i2) : list.get(10);
                    b(Arrays.asList(subList, Arrays.asList(numberArr)), new q(8));
                    return null;
                }
                if ("hstemhm".equals(str)) {
                    a(list, list.size() % 2 != 0);
                    return null;
                }
                if ("hintmask".equals(str) || "cntrmask".equals(str)) {
                    if (a(list, list.size() % 2 != 0).size() > 0) {
                    }
                    return null;
                }
                if ("vstemhm".equals(str)) {
                    a(list, list.size() % 2 != 0);
                    return null;
                }
                if ("rcurveline".equals(str)) {
                    if (list.size() >= 2) {
                        b(a(list.subList(0, list.size() - 2), 6), new q(8));
                        c(list.subList(list.size() - 2, list.size()), new q(5));
                        return null;
                    }
                    return null;
                }
                if ("rlinecurve".equals(str)) {
                    if (list.size() >= 6) {
                        b(a(list.subList(0, list.size() - 6), 2), new q(5));
                        c(list.subList(list.size() - 6, list.size()), new q(8));
                        return null;
                    }
                    return null;
                }
                if ("vvcurveto".equals(str)) {
                    d(list, false);
                    return null;
                }
                if ("hhcurveto".equals(str)) {
                    d(list, true);
                    return null;
                }
            }
        }
        c(list, qVar);
        return null;
    }

    private List<Number> a(List<Number> list, boolean z) {
        if (this.f4246a.isEmpty()) {
            if (z) {
                c(Arrays.asList(Float.valueOf(0.0f), Float.valueOf(list.get(0).floatValue() + this.d)), new q(13));
                list = list.subList(1, list.size());
            } else {
                c(Arrays.asList(Float.valueOf(0.0f), Float.valueOf(this.c)), new q(13));
            }
        }
        return list;
    }

    private void c() {
        if (this.e > 0) {
            d();
        }
        this.e++;
    }

    private void d() {
        q qVar = this.e > 0 ? (q) this.f4246a.get(this.f4246a.size() - 1) : null;
        q qVar2 = new q(9);
        if (qVar != null && !qVar2.equals(qVar)) {
            c(Collections.emptyList(), qVar2);
        }
    }

    private void b(List<Number> list, boolean z) {
        while (list.size() > 0) {
            c(list.subList(0, 1), new q(z ? 6 : 7));
            list = list.subList(1, list.size());
            z = !z;
        }
    }

    private void c(List<Number> list, boolean z) {
        while (list.size() >= 4) {
            boolean z2 = list.size() == 5;
            if (z) {
                Number[] numberArr = new Number[6];
                numberArr[0] = list.get(0);
                numberArr[1] = 0;
                numberArr[2] = list.get(1);
                numberArr[3] = list.get(2);
                numberArr[4] = z2 ? list.get(4) : 0;
                numberArr[5] = list.get(3);
                c(Arrays.asList(numberArr), new q(8));
            } else {
                Number[] numberArr2 = new Number[6];
                numberArr2[0] = 0;
                numberArr2[1] = list.get(0);
                numberArr2[2] = list.get(1);
                numberArr2[3] = list.get(2);
                numberArr2[4] = list.get(3);
                numberArr2[5] = z2 ? list.get(4) : 0;
                c(Arrays.asList(numberArr2), new q(8));
            }
            list = list.subList(z2 ? 5 : 4, list.size());
            z = !z;
        }
    }

    private void d(List<Number> list, boolean z) {
        while (list.size() >= 4) {
            boolean z2 = list.size() % 4 == 1;
            if (z) {
                Number[] numberArr = new Number[6];
                numberArr[0] = list.get(z2 ? 1 : 0);
                numberArr[1] = z2 ? list.get(0) : 0;
                numberArr[2] = list.get(z2 ? 2 : 1);
                numberArr[3] = list.get(z2 ? 3 : 2);
                numberArr[4] = list.get(z2 ? 4 : 3);
                numberArr[5] = 0;
                c(Arrays.asList(numberArr), new q(8));
            } else {
                Number[] numberArr2 = new Number[6];
                numberArr2[0] = z2 ? list.get(0) : 0;
                numberArr2[1] = list.get(z2 ? 1 : 0);
                numberArr2[2] = list.get(z2 ? 2 : 1);
                numberArr2[3] = list.get(z2 ? 3 : 2);
                numberArr2[4] = 0;
                numberArr2[5] = list.get(z2 ? 4 : 3);
                c(Arrays.asList(numberArr2), new q(8));
            }
            list = list.subList(z2 ? 5 : 4, list.size());
        }
    }

    private void b(List<List<Number>> list, q qVar) {
        Iterator<List<Number>> it = list.iterator();
        while (it.hasNext()) {
            c(it.next(), qVar);
        }
    }

    private void c(List<Number> list, q qVar) {
        this.f4246a.addAll(list);
        this.f4246a.add(qVar);
    }

    private static <E> List<List<E>> a(List<E> list, int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size() / i; i2++) {
            arrayList.add(list.subList(i2 * i, (i2 + 1) * i));
        }
        return arrayList;
    }
}
