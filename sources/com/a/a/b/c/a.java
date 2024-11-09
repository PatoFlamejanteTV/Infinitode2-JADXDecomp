package com.a.a.b.c;

import com.a.a.b.g.o;
import com.d.e.m;
import com.d.e.v;
import com.d.i.u;
import com.d.i.x;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.lwjgl.system.windows.User32;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: infinitode-2.jar:com/a/a/b/c/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private d f91a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.b.e f92b;
    private boolean c;
    private com.a.a.b.g.a d;
    private byte[] e;
    private byte[] f;
    private char[] g;
    private char[] h;
    private char[] i;

    public static BigDecimal a(String str) {
        return d(str.toCharArray());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static BigDecimal a(char[] cArr, int i, int i2) {
        String str;
        try {
            if (i2 < 500) {
                return new BigDecimal(cArr, 0, i2);
            }
            return a(cArr, 0, i2, i2 / 10);
        } catch (ArithmeticException | NumberFormatException e) {
            String message = i2.getMessage();
            String str2 = message;
            if (message == null) {
                str2 = "Not a valid number representation";
            }
            if (i2 <= 1000) {
                str = new String(cArr, 0, i2);
            } else {
                str = new String(Arrays.copyOfRange(cArr, 0, 1000)) + "(truncated, full length is " + cArr.length + " chars)";
            }
            throw new NumberFormatException("Value \"" + str + "\" can not be represented as `java.math.BigDecimal`, reason: " + str2);
        }
    }

    private static BigDecimal d(char[] cArr) {
        return a(cArr, 0, cArr.length);
    }

    private static BigDecimal a(char[] cArr, int i, int i2, int i3) {
        int i4;
        BigDecimal a2;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i5 = i;
        int i6 = -1;
        int i7 = -1;
        int i8 = 0;
        int i9 = i + i2;
        for (int i10 = i; i10 < i9; i10++) {
            switch (cArr[i10]) {
                case '+':
                    if (i6 >= 0) {
                        if (z2) {
                            throw new NumberFormatException("Multiple signs in exponent");
                        }
                        z2 = true;
                        break;
                    } else {
                        if (z) {
                            throw new NumberFormatException("Multiple signs in number");
                        }
                        z = true;
                        i5 = i10 + 1;
                        break;
                    }
                case '-':
                    if (i6 >= 0) {
                        if (z2) {
                            throw new NumberFormatException("Multiple signs in exponent");
                        }
                        z2 = true;
                        break;
                    } else {
                        if (z) {
                            throw new NumberFormatException("Multiple signs in number");
                        }
                        z = true;
                        z3 = true;
                        i5 = i10 + 1;
                        break;
                    }
                case '.':
                    if (i7 >= 0) {
                        throw new NumberFormatException("Multiple decimal points");
                    }
                    i7 = i10;
                    break;
                case 'E':
                case 'e':
                    if (i6 >= 0) {
                        throw new NumberFormatException("Multiple exponent markers");
                    }
                    i6 = i10;
                    break;
                default:
                    if (i7 >= 0 && i6 == -1) {
                        i8++;
                        break;
                    }
                    break;
            }
        }
        int i11 = 0;
        if (i6 >= 0) {
            i4 = i6;
            i11 = Integer.parseInt(new String(cArr, i6 + 1, (i9 - i6) - 1));
            i8 = a(i8, i11);
        } else {
            i4 = i9;
        }
        if (i7 >= 0) {
            int i12 = (i4 - i7) - 1;
            a2 = a(cArr, i5, i7 - i5, i11, i3).add(a(cArr, i7 + 1, i12, i11 - i12, i3));
        } else {
            a2 = a(cArr, i5, i4 - i5, i11, i3);
        }
        if (i8 != 0) {
            a2 = a2.setScale(i8);
        }
        if (z3) {
            a2 = a2.negate();
        }
        return a2;
    }

    private static int a(int i, long j) {
        long j2 = i - j;
        if (j2 > 2147483647L || j2 < -2147483648L) {
            throw new NumberFormatException("Scale out of range: " + j2 + " while adjusting scale " + i + " to exponent " + j);
        }
        return (int) j2;
    }

    private static BigDecimal a(char[] cArr, int i, int i2, int i3, int i4) {
        if (i2 <= i4) {
            return i2 == 0 ? BigDecimal.ZERO : new BigDecimal(cArr, i, i2).movePointRight(i3);
        }
        int i5 = i2 / 2;
        return a(cArr, i, i5, (i3 + i2) - i5, i4).add(a(cArr, i + i5, i2 - i5, i3, i4));
    }

    public a(com.a.a.b.g.a aVar, d dVar, boolean z) {
        this.d = aVar;
        this.f91a = dVar;
        dVar.c();
        this.c = z;
    }

    public void a(com.a.a.b.e eVar) {
        this.f92b = eVar;
    }

    public com.a.a.b.e a() {
        return this.f92b;
    }

    public boolean b() {
        return this.c;
    }

    public d c() {
        return this.f91a;
    }

    public o d() {
        return new o(this.d);
    }

    public byte[] e() {
        a((Object) this.e);
        byte[] a2 = this.d.a(0);
        this.e = a2;
        return a2;
    }

    public byte[] f() {
        a((Object) this.f);
        byte[] a2 = this.d.a(3);
        this.f = a2;
        return a2;
    }

    public char[] g() {
        a((Object) this.g);
        char[] b2 = this.d.b(0);
        this.g = b2;
        return b2;
    }

    public char[] h() {
        a((Object) this.h);
        char[] b2 = this.d.b(1);
        this.h = b2;
        return b2;
    }

    public char[] a(int i) {
        a((Object) this.i);
        char[] a2 = this.d.a(3, i);
        this.i = a2;
        return a2;
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            a(bArr, this.e);
            this.e = null;
            this.d.a(0, bArr);
        }
    }

    public void b(byte[] bArr) {
        if (bArr != null) {
            a(bArr, this.f);
            this.f = null;
            this.d.a(3, bArr);
        }
    }

    public void a(char[] cArr) {
        if (cArr != null) {
            a(cArr, this.g);
            this.g = null;
            this.d.a(0, cArr);
        }
    }

    public void b(char[] cArr) {
        if (cArr != null) {
            a(cArr, this.h);
            this.h = null;
            this.d.a(1, cArr);
        }
    }

    public void c(char[] cArr) {
        if (cArr != null) {
            a(cArr, this.i);
            this.i = null;
            this.d.a(3, cArr);
        }
    }

    private static void a(Object obj) {
        if (obj != null) {
            throw new IllegalStateException("Trying to call same allocXxx() method second time");
        }
    }

    protected void a(byte[] bArr, byte[] bArr2) {
        if (bArr != bArr2 && bArr.length < bArr2.length) {
            throw i();
        }
    }

    protected void a(char[] cArr, char[] cArr2) {
        if (cArr != cArr2 && cArr.length < cArr2.length) {
            throw i();
        }
    }

    private static IllegalArgumentException i() {
        return new IllegalArgumentException("Trying to release buffer smaller than original");
    }

    public static com.d.c.f.g a(com.d.c.f.c cVar, com.d.c.a.a aVar, com.d.c.d.j jVar) {
        if (jVar.e() == 0) {
            return cVar.a().i(aVar);
        }
        switch (jVar.i()) {
            case 1:
                return new com.d.c.f.a.g(aVar, jVar);
            case 2:
                return new com.d.c.f.a.e(cVar, aVar, jVar);
            case 3:
                return new com.d.c.f.a.b(aVar, jVar);
            case 4:
                com.d.c.a.c h = jVar.h();
                com.d.c.a.c cVar2 = h;
                if (h == null) {
                    cVar2 = com.d.c.a.c.a(jVar.c());
                }
                return cVar2;
            case 5:
                return new com.d.c.f.a.i(aVar, jVar);
            case 6:
                return new com.d.c.f.a.f(aVar, jVar);
            case 7:
                return new com.d.c.f.a.d(aVar, jVar);
            case 8:
                return new com.d.c.f.a.c(aVar, jVar);
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void a(v vVar, u uVar, com.d.i.c cVar) {
        x u = vVar.u();
        vVar.a((x) null);
        if (cVar.a().B()) {
            cVar.g(vVar.p().f().Y());
        } else {
            cVar.g(vVar.o().f());
        }
        cVar.a(uVar);
        if (!vVar.r()) {
            cVar.b(vVar);
        } else {
            vVar.a(cVar);
            vVar.o().c(true);
            vVar.n();
        }
        vVar.a(u);
    }

    public static m a(v vVar, u uVar, com.d.i.c cVar, int i, List list) {
        m mVar = new m();
        x u = vVar.u();
        vVar.a((x) null);
        cVar.g(uVar.U());
        cVar.b(uVar.af());
        cVar.a(uVar);
        if (list != null) {
            cVar.o(uVar.an() + cVar.L().c());
        } else {
            cVar.o(uVar.an() + uVar.as());
        }
        cVar.h(vVar);
        int an = cVar.an();
        cVar.b(vVar);
        vVar.l().a(vVar, cVar);
        if (list != null && ((list.size() > 0 || cVar.Q() > i) && uVar.f())) {
            cVar.c(vVar);
            mVar.a(true);
        } else if (vVar.r()) {
            a(vVar, uVar, cVar, an != cVar.an());
            vVar.p().c(vVar, cVar);
        }
        mVar.a(cVar);
        vVar.a(u);
        return mVar;
    }

    private static void a(v vVar, u uVar, com.d.i.c cVar, boolean z) {
        if (cVar.a().ab() || cVar.i(vVar)) {
            cVar.a(vVar, cVar.a().e(com.d.c.a.a.af), false);
            cVar.B();
            b(vVar, uVar, cVar);
        } else {
            if (!cVar.a().ad() || !cVar.u(vVar)) {
                if (z) {
                    b(vVar, uVar, cVar);
                    return;
                }
                return;
            }
            int a2 = cVar.a(vVar, cVar.a().e(com.d.c.a.a.af), false);
            cVar.B();
            b(vVar, uVar, cVar);
            if (cVar.u(vVar)) {
                cVar.o(cVar.an() - a2);
                cVar.B();
                b(vVar, uVar, cVar);
            }
        }
    }

    private static void b(v vVar, u uVar, com.d.i.c cVar) {
        cVar.c(vVar);
        cVar.b(uVar.af());
        cVar.b(vVar);
        vVar.l().a(vVar, cVar);
    }

    public static int a(com.d.c.a.c cVar) {
        if (cVar == com.d.c.a.c.aq) {
            return 400;
        }
        if (cVar == com.d.c.a.c.i) {
            return 700;
        }
        if (cVar == com.d.c.a.c.C) {
            return 100;
        }
        if (cVar == com.d.c.a.c.D) {
            return 200;
        }
        if (cVar == com.d.c.a.c.E) {
            return 300;
        }
        if (cVar == com.d.c.a.c.F) {
            return 400;
        }
        if (cVar == com.d.c.a.c.G) {
            return 500;
        }
        if (cVar == com.d.c.a.c.H) {
            return 600;
        }
        if (cVar == com.d.c.a.c.I) {
            return 700;
        }
        if (cVar == com.d.c.a.c.J) {
            return User32.WM_DWMCOLORIZATIONCOLORCHANGED;
        }
        if (cVar == com.d.c.a.c.K) {
            return 900;
        }
        if (cVar == com.d.c.a.c.ab) {
            return 400;
        }
        if (cVar == com.d.c.a.c.j) {
            return 700;
        }
        throw new IllegalArgumentException();
    }

    public static Element a(Element element, String str) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                Element element2 = (Element) item;
                if (element2.getTagName().equals(str)) {
                    return element2;
                }
            }
        }
        return null;
    }

    public static List<Element> b(Element element, String str) {
        ArrayList arrayList = new ArrayList();
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                Element element2 = (Element) item;
                if (element2.getTagName().equals(str)) {
                    arrayList.add(element2);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public static Element a(Node node, String str) {
        while (true) {
            Node parentNode = node.getParentNode();
            if (parentNode != null) {
                if (parentNode.getNodeType() == 1 && parentNode.getNodeName().equals(str)) {
                    return (Element) parentNode;
                }
                node = parentNode;
            } else {
                return null;
            }
        }
    }

    public static String a(Element element) {
        StringBuilder sb = new StringBuilder();
        a(element, sb);
        return sb.toString();
    }

    private static void a(Element element, StringBuilder sb) {
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                a((Element) item, sb);
            } else if (item.getNodeType() == 3) {
                sb.append(item.getNodeValue());
            }
        }
    }

    public static Level a(String str, Level level) {
        if ("ALL".equals(str)) {
            return Level.ALL;
        }
        if ("CONFIG".equals(str)) {
            return Level.CONFIG;
        }
        if ("FINE".equals(str)) {
            return Level.FINE;
        }
        if ("FINER".equals(str)) {
            return Level.FINER;
        }
        if ("FINEST".equals(str)) {
            return Level.FINEST;
        }
        if ("INFO".equals(str)) {
            return Level.INFO;
        }
        if ("OFF".equals(str)) {
            return Level.OFF;
        }
        if ("SEVERE".equals(str)) {
            return Level.SEVERE;
        }
        if ("WARNING".equals(str)) {
            return Level.WARNING;
        }
        return level;
    }

    public static boolean b(int i) {
        int type;
        return (Character.isISOControl(i) || (type = Character.getType(i)) == 15 || type == 16 || type == 0 || type == 18 || type == 19) ? false : true;
    }

    public static Integer b(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
