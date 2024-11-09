package com.d.e;

import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/e/l.class */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    private com.d.c.a.c f1143a;

    /* renamed from: b, reason: collision with root package name */
    private int f1144b;
    private List c;
    private String d;

    public l(int i, com.d.c.a.c cVar) {
        this.f1144b = i;
        this.f1143a = cVar;
    }

    public l(List list, String str, com.d.c.a.c cVar) {
        this.c = list;
        this.d = str;
        this.f1143a = cVar;
    }

    private static String a(int i) {
        int[] iArr = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strArr = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 13; i2++) {
            int i3 = i / iArr[i2];
            for (int i4 = 0; i4 < i3; i4++) {
                sb.append(strArr[i2]);
            }
            i -= iArr[i2] * i3;
        }
        return sb.toString();
    }

    public static String a(com.d.c.a.c cVar, int i) {
        String lowerCase;
        if (cVar == com.d.c.a.c.ah || cVar == com.d.c.a.c.af) {
            lowerCase = b(i).toLowerCase();
        } else if (cVar == com.d.c.a.c.bm || cVar == com.d.c.a.c.bl) {
            lowerCase = b(i).toUpperCase();
        } else if (cVar == com.d.c.a.c.ai) {
            lowerCase = a(i).toLowerCase();
        } else if (cVar == com.d.c.a.c.bn) {
            lowerCase = a(i).toUpperCase();
        } else if (cVar == com.d.c.a.c.w) {
            lowerCase = (i >= 10 ? "" : "0") + i;
        } else {
            lowerCase = Integer.toString(i);
        }
        return lowerCase;
    }

    private static String b(int i) {
        String str = "";
        int i2 = i - 1;
        while (i2 >= 0) {
            int i3 = i2 % 26;
            i2 = (i2 / 26) - 1;
            str = ((char) (i3 + 65)) + str;
        }
        return str;
    }

    public final String a() {
        if (this.c == null) {
            return a(this.f1143a, this.f1144b);
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            sb.append(a(this.f1143a, ((Integer) it.next()).intValue()));
            if (it.hasNext()) {
                sb.append(this.d);
            }
        }
        return sb.toString();
    }
}
