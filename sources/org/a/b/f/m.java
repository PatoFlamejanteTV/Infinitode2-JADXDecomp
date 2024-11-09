package org.a.b.f;

import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/b/f/m.class */
public class m {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4304a = org.a.a.a.c.a(m.class);

    /* renamed from: b, reason: collision with root package name */
    private l f4305b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(l lVar) {
        this.f4305b = lVar;
    }

    public final GeneralPath a() {
        return a(a(this.f4305b));
    }

    private static a[] a(l lVar) {
        int i = 0;
        int i2 = -1;
        a[] aVarArr = new a[lVar.c()];
        int i3 = 0;
        while (i3 < lVar.c()) {
            if (i2 == -1) {
                i2 = lVar.a(i);
            }
            boolean z = i2 == i3;
            boolean z2 = z;
            if (z) {
                i++;
                i2 = -1;
            }
            aVarArr[i3] = new a(lVar.c(i3), lVar.d(i3), (lVar.b(i3) & 1) != 0, z2);
            i3++;
        }
        return aVarArr;
    }

    private GeneralPath a(a[] aVarArr) {
        GeneralPath generalPath = new GeneralPath();
        int i = 0;
        int length = aVarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (aVarArr[i2].d) {
                a aVar = aVarArr[i];
                a aVar2 = aVarArr[i2];
                ArrayList arrayList = new ArrayList();
                for (int i3 = i; i3 <= i2; i3++) {
                    arrayList.add(aVarArr[i3]);
                }
                if (aVarArr[i].c) {
                    arrayList.add(aVar);
                } else if (aVarArr[i2].c) {
                    arrayList.add(0, aVar2);
                } else {
                    a a2 = a(aVar, aVar2);
                    arrayList.add(0, a2);
                    arrayList.add(a2);
                }
                a(generalPath, (a) arrayList.get(0));
                int i4 = 1;
                int size = arrayList.size();
                while (i4 < size) {
                    a aVar3 = (a) arrayList.get(i4);
                    if (aVar3.c) {
                        b(generalPath, aVar3);
                    } else if (((a) arrayList.get(i4 + 1)).c) {
                        a(generalPath, aVar3, (a) arrayList.get(i4 + 1));
                        i4++;
                    } else {
                        a(generalPath, aVar3, a(aVar3, (a) arrayList.get(i4 + 1)));
                    }
                    i4++;
                }
                generalPath.closePath();
                i = i2 + 1;
            }
        }
        return generalPath;
    }

    private static void a(GeneralPath generalPath, a aVar) {
        generalPath.moveTo(aVar.f4306a, aVar.f4307b);
        if (f4304a.a()) {
            new StringBuilder("moveTo: ").append(String.format(Locale.US, "%d,%d", Integer.valueOf(aVar.f4306a), Integer.valueOf(aVar.f4307b)));
        }
    }

    private static void b(GeneralPath generalPath, a aVar) {
        generalPath.lineTo(aVar.f4306a, aVar.f4307b);
        if (f4304a.a()) {
            new StringBuilder("lineTo: ").append(String.format(Locale.US, "%d,%d", Integer.valueOf(aVar.f4306a), Integer.valueOf(aVar.f4307b)));
        }
    }

    private static void a(GeneralPath generalPath, a aVar, a aVar2) {
        generalPath.quadTo(aVar.f4306a, aVar.f4307b, aVar2.f4306a, aVar2.f4307b);
        if (f4304a.a()) {
            new StringBuilder("quadTo: ").append(String.format(Locale.US, "%d,%d %d,%d", Integer.valueOf(aVar.f4306a), Integer.valueOf(aVar.f4307b), Integer.valueOf(aVar2.f4306a), Integer.valueOf(aVar2.f4307b)));
        }
    }

    private static int a(int i, int i2) {
        return i + ((i2 - i) / 2);
    }

    private a a(a aVar, a aVar2) {
        return new a(a(aVar.f4306a, aVar2.f4306a), a(aVar.f4307b, aVar2.f4307b));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/m$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private int f4306a;

        /* renamed from: b, reason: collision with root package name */
        private int f4307b;
        private boolean c;
        private boolean d;

        a(int i, int i2, boolean z, boolean z2) {
            this.f4306a = 0;
            this.f4307b = 0;
            this.c = true;
            this.d = false;
            this.f4306a = i;
            this.f4307b = i2;
            this.c = z;
            this.d = z2;
        }

        a(int i, int i2) {
            this(i, i2, true, false);
        }

        public final String toString() {
            Locale locale = Locale.US;
            Object[] objArr = new Object[4];
            objArr[0] = Integer.valueOf(this.f4306a);
            objArr[1] = Integer.valueOf(this.f4307b);
            objArr[2] = this.c ? "onCurve" : "";
            objArr[3] = this.d ? "endOfContour" : "";
            return String.format(locale, "Point(%d,%d,%s,%s)", objArr);
        }
    }
}
