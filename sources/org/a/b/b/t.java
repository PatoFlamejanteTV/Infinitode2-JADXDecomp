package org.a.b.b;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/b/b/t.class */
public class t {
    private static final org.a.a.a.a c = org.a.a.a.c.a(t.class);
    private org.a.b.g.c d;
    private final String e;
    private final String f;
    private GeneralPath g;
    private int h;
    private Point2D.Float i;
    private Point2D.Float j;
    private boolean k;
    private final List<Point2D.Float> l;

    /* renamed from: a, reason: collision with root package name */
    protected List<Object> f4246a;

    /* renamed from: b, reason: collision with root package name */
    protected int f4247b;

    public t(org.a.b.g.c cVar, String str, String str2, List<Object> list) {
        this(cVar, str, str2);
        this.f4246a = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public t(org.a.b.g.c cVar, String str, String str2) {
        this.g = null;
        this.h = 0;
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = new ArrayList();
        this.d = cVar;
        this.e = str;
        this.f = str2;
        this.j = new Point2D.Float(0.0f, 0.0f);
    }

    public final int a() {
        synchronized (c) {
            if (this.g == null) {
                c();
            }
        }
        return this.h;
    }

    public final GeneralPath b() {
        synchronized (c) {
            if (this.g == null) {
                c();
            }
        }
        return this.g;
    }

    private void c() {
        this.g = new GeneralPath();
        this.i = new Point2D.Float(0.0f, 0.0f);
        this.h = 0;
        new u(this).a(this.f4246a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Number> a(List<Number> list, q qVar) {
        this.f4247b++;
        String str = q.f4243a.get(qVar.a());
        if ("rmoveto".equals(str)) {
            if (list.size() >= 2) {
                if (this.k) {
                    this.l.add(new Point2D.Float(list.get(0).floatValue(), list.get(1).floatValue()));
                    return null;
                }
                b(list.get(0), list.get(1));
                return null;
            }
            return null;
        }
        if ("vmoveto".equals(str)) {
            if (list.size() > 0) {
                if (this.k) {
                    this.l.add(new Point2D.Float(0.0f, list.get(0).floatValue()));
                    return null;
                }
                b(0, list.get(0));
                return null;
            }
            return null;
        }
        if ("hmoveto".equals(str)) {
            if (list.size() > 0) {
                if (this.k) {
                    this.l.add(new Point2D.Float(list.get(0).floatValue(), 0.0f));
                    return null;
                }
                b(list.get(0), 0);
                return null;
            }
            return null;
        }
        if ("rlineto".equals(str)) {
            if (list.size() >= 2) {
                c(list.get(0), list.get(1));
                return null;
            }
            return null;
        }
        if ("hlineto".equals(str)) {
            if (list.size() > 0) {
                c(list.get(0), 0);
                return null;
            }
            return null;
        }
        if ("vlineto".equals(str)) {
            if (list.size() > 0) {
                c(0, list.get(0));
                return null;
            }
            return null;
        }
        if ("rrcurveto".equals(str)) {
            if (list.size() >= 6) {
                a(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5));
                return null;
            }
            return null;
        }
        if ("closepath".equals(str)) {
            d();
            return null;
        }
        if ("sbw".equals(str)) {
            if (list.size() >= 3) {
                this.i = new Point2D.Float(list.get(0).floatValue(), list.get(1).floatValue());
                this.h = list.get(2).intValue();
                this.j.setLocation(this.i);
                return null;
            }
            return null;
        }
        if ("hsbw".equals(str)) {
            if (list.size() >= 2) {
                this.i = new Point2D.Float(list.get(0).floatValue(), 0.0f);
                this.h = list.get(1).intValue();
                this.j.setLocation(this.i);
                return null;
            }
            return null;
        }
        if ("vhcurveto".equals(str)) {
            if (list.size() >= 4) {
                a(0, list.get(0), list.get(1), list.get(2), list.get(3), 0);
                return null;
            }
            return null;
        }
        if ("hvcurveto".equals(str)) {
            if (list.size() >= 4) {
                a(list.get(0), 0, list.get(1), list.get(2), 0, list.get(3));
                return null;
            }
            return null;
        }
        if ("seac".equals(str)) {
            if (list.size() >= 5) {
                a(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
                return null;
            }
            return null;
        }
        if ("setcurrentpoint".equals(str)) {
            if (list.size() >= 2) {
                a(list.get(0), list.get(1));
                return null;
            }
            return null;
        }
        if ("callothersubr".equals(str)) {
            if (list.size() > 0) {
                a(list.get(0).intValue());
                return null;
            }
            return null;
        }
        if (FlexmarkHtmlConverter.DIV_NODE.equals(str)) {
            float floatValue = list.get(list.size() - 2).floatValue() / list.get(list.size() - 1).floatValue();
            ArrayList arrayList = new ArrayList(list);
            arrayList.remove(arrayList.size() - 1);
            arrayList.remove(arrayList.size() - 1);
            arrayList.add(Float.valueOf(floatValue));
            return arrayList;
        }
        if (!"hstem".equals(str) && !"vstem".equals(str) && !"hstem3".equals(str) && !"vstem3".equals(str) && !"dotsection".equals(str) && !"endchar".equals(str)) {
            if ("return".equals(str)) {
                new StringBuilder("Unexpected charstring command: ").append(qVar.a()).append(" in glyph ").append(this.f).append(" of font ").append(this.e);
                return null;
            }
            if (str != null) {
                throw new IllegalArgumentException("Unhandled command: " + str);
            }
            new StringBuilder("Unknown charstring command: ").append(qVar.a()).append(" in glyph ").append(this.f).append(" of font ").append(this.e);
            return null;
        }
        return null;
    }

    private void a(Number number, Number number2) {
        this.j.setLocation(number.floatValue(), number2.floatValue());
    }

    private void a(int i) {
        if (i == 0) {
            this.k = false;
            if (this.l.size() < 7) {
                new StringBuilder("flex without moveTo in font ").append(this.e).append(", glyph ").append(this.f).append(", command ").append(this.f4247b);
                return;
            }
            Point2D.Float r0 = this.l.get(0);
            r0.setLocation(this.j.getX() + r0.getX(), this.j.getY() + r0.getY());
            Point2D.Float r02 = this.l.get(1);
            r02.setLocation(r0.getX() + r02.getX(), r0.getY() + r02.getY());
            r02.setLocation(r02.getX() - this.j.getX(), r02.getY() - this.j.getY());
            a(Double.valueOf(this.l.get(1).getX()), Double.valueOf(this.l.get(1).getY()), Double.valueOf(this.l.get(2).getX()), Double.valueOf(this.l.get(2).getY()), Double.valueOf(this.l.get(3).getX()), Double.valueOf(this.l.get(3).getY()));
            a(Double.valueOf(this.l.get(4).getX()), Double.valueOf(this.l.get(4).getY()), Double.valueOf(this.l.get(5).getX()), Double.valueOf(this.l.get(5).getY()), Double.valueOf(this.l.get(6).getX()), Double.valueOf(this.l.get(6).getY()));
            this.l.clear();
            return;
        }
        if (i == 1) {
            this.k = true;
            return;
        }
        throw new IllegalArgumentException("Unexpected other subroutine: " + i);
    }

    private void b(Number number, Number number2) {
        float x = ((float) this.j.getX()) + number.floatValue();
        float y = ((float) this.j.getY()) + number2.floatValue();
        this.g.moveTo(x, y);
        this.j.setLocation(x, y);
    }

    private void c(Number number, Number number2) {
        float x = ((float) this.j.getX()) + number.floatValue();
        float y = ((float) this.j.getY()) + number2.floatValue();
        if (this.g.getCurrentPoint() == null) {
            new StringBuilder("rlineTo without initial moveTo in font ").append(this.e).append(", glyph ").append(this.f);
            this.g.moveTo(x, y);
        } else {
            this.g.lineTo(x, y);
        }
        this.j.setLocation(x, y);
    }

    private void a(Number number, Number number2, Number number3, Number number4, Number number5, Number number6) {
        float x = ((float) this.j.getX()) + number.floatValue();
        float y = ((float) this.j.getY()) + number2.floatValue();
        float floatValue = x + number3.floatValue();
        float floatValue2 = y + number4.floatValue();
        float floatValue3 = floatValue + number5.floatValue();
        float floatValue4 = floatValue2 + number6.floatValue();
        if (this.g.getCurrentPoint() == null) {
            new StringBuilder("rrcurveTo without initial moveTo in font ").append(this.e).append(", glyph ").append(this.f);
            this.g.moveTo(floatValue3, floatValue4);
        } else {
            this.g.curveTo(x, y, floatValue, floatValue2, floatValue3, floatValue4);
        }
        this.j.setLocation(floatValue3, floatValue4);
    }

    private void d() {
        if (this.g.getCurrentPoint() == null) {
            new StringBuilder("closepath without initial moveTo in font ").append(this.e).append(", glyph ").append(this.f);
        } else {
            this.g.closePath();
        }
        this.g.moveTo(this.j.getX(), this.j.getY());
    }

    private void a(Number number, Number number2, Number number3, Number number4, Number number5) {
        try {
            this.g.append(this.d.a_(org.a.b.d.c.f4266a.a(number4.intValue())).b().getPathIterator((AffineTransform) null), false);
        } catch (IOException unused) {
            new StringBuilder("invalid seac character in glyph ").append(this.f).append(" of font ").append(this.e);
        }
        try {
            this.g.append(this.d.a_(org.a.b.d.c.f4266a.a(number5.intValue())).b().getPathIterator(AffineTransform.getTranslateInstance((this.i.getX() + number2.floatValue()) - number.floatValue(), this.i.getY() + number3.floatValue())), false);
        } catch (IOException unused2) {
            new StringBuilder("invalid seac character in glyph ").append(this.f).append(" of font ").append(this.e);
        }
    }

    public String toString() {
        return this.f4246a.toString().replace("|", SequenceUtils.EOL).replace(",", SequenceUtils.SPACE);
    }
}
