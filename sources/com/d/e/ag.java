package com.d.e;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/d/e/ag.class */
public final class ag {

    /* renamed from: a, reason: collision with root package name */
    private static Pattern f1122a;

    /* renamed from: b, reason: collision with root package name */
    private static Pattern f1123b;
    private static Pattern c;

    static {
        Pattern.compile("\\s+\\n\\s+");
        Pattern.compile("\\n");
        f1122a = Pattern.compile("\\t");
        f1123b = Pattern.compile("(?: )+");
        c = Pattern.compile("[\\s&&[^\\n]]\\n");
    }

    public static void a(List<ac> list) {
        boolean z = false;
        boolean z2 = true;
        for (ac acVar : list) {
            if (acVar.a().o()) {
                com.d.i.q qVar = (com.d.i.q) acVar;
                boolean a2 = a(qVar, z);
                if (!qVar.e()) {
                    z2 = false;
                }
                z = a2;
            } else if (!a(acVar)) {
                z2 = false;
                z = false;
            }
        }
        if (z2) {
            b(list);
        }
    }

    private static boolean a(ac acVar) {
        com.d.c.f.c a2 = acVar.a();
        return a2.C() || a2.A() || a2.B() || a2.P();
    }

    private static void b(List<ac> list) {
        boolean z = true;
        for (ac acVar : list) {
            if (acVar.a().o()) {
                com.d.i.q qVar = (com.d.i.q) acVar;
                if (qVar.h() != null) {
                    z = false;
                }
                qVar.p();
            }
        }
        if (z) {
            Iterator<ac> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().a().o()) {
                    it.remove();
                }
            }
        }
    }

    private static boolean a(com.d.i.q qVar, boolean z) {
        com.d.c.a.c e = qVar.a().e(com.d.c.a.a.au);
        String a2 = a(qVar, e, qVar.c(), z);
        boolean z2 = a2.endsWith(SequenceUtils.SPACE) && (e == com.d.c.a.c.aq || e == com.d.c.a.c.ar || e == com.d.c.a.c.aB);
        qVar.a(a2);
        if (a2.trim().equals("")) {
            if (e == com.d.c.a.c.aq || e == com.d.c.a.c.ar) {
                qVar.a(true);
            } else if (e == com.d.c.a.c.aB) {
                qVar.a(false);
            } else if (a2.indexOf(SequenceUtils.EOL) < 0) {
                qVar.a(true);
            }
        }
        return a2.equals("") ? z : z2;
    }

    private static String a(String str, boolean z) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder(charArray.length);
        boolean z2 = z;
        for (char c2 : charArray) {
            if (z2) {
                if (c2 != '\n' && c2 != '\t' && c2 != ' ') {
                    sb.append(c2);
                    z2 = false;
                }
            } else if (c2 == '\n' || c2 == '\t' || c2 == ' ') {
                sb.append(' ');
                z2 = true;
            } else {
                sb.append(c2);
            }
        }
        return sb.toString();
    }

    private static String a(com.d.i.q qVar, com.d.c.a.c cVar, String str, boolean z) {
        if (cVar == com.d.c.a.c.aq || cVar == com.d.c.a.c.ar) {
            return a(str, z);
        }
        if (cVar == com.d.c.a.c.aB) {
            str = c.matcher(str).replaceAll(SequenceUtils.EOL);
        }
        if (cVar == com.d.c.a.c.aB || cVar == com.d.c.a.c.aD) {
            char[] cArr = new char[(int) qVar.a().b(com.d.c.a.a.al)];
            Arrays.fill(cArr, ' ');
            str = f1122a.matcher(str).replaceAll(new String(cArr));
        } else if (cVar == com.d.c.a.c.aC) {
            str = f1123b.matcher(f1122a.matcher(str).replaceAll(SequenceUtils.SPACE)).replaceAll(SequenceUtils.SPACE);
        }
        return str;
    }
}
