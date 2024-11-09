package com.d.l;

import com.d.e.aa;
import com.d.i.a.r;
import com.d.m.l;
import com.vladsch.flexmark.util.html.Attribute;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: infinitode-2.jar:com/d/l/b.class */
public class b {

    /* renamed from: b, reason: collision with root package name */
    private String f1409b;
    private String e;

    /* renamed from: a, reason: collision with root package name */
    private r f1408a = null;
    private int c = 0;
    private List<String> d = new ArrayList();

    public static Map<Shape, String> a(Element element, aa aaVar) {
        String attribute = element.getAttribute("usemap");
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        String substring = attribute.substring(1);
        Element elementById = element.getOwnerDocument().getElementById(substring);
        if (elementById == null) {
            NodeList elementsByTagName = element.getOwnerDocument().getElementsByTagName("map");
            int i = 0;
            while (true) {
                if (i >= elementsByTagName.getLength()) {
                    break;
                }
                if (!a(substring, a(elementsByTagName.item(i).getAttributes(), Attribute.NAME_ATTR))) {
                    i++;
                } else {
                    elementById = elementsByTagName.item(i);
                    break;
                }
            }
            if (elementById == null) {
                l.g(Level.INFO, "No map named: '" + substring + "'");
                return null;
            }
        }
        return a((Node) elementById, aaVar);
    }

    private static boolean a(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        return str != null && str.equals(str2);
    }

    private static boolean b(String str, String str2) {
        return str.equalsIgnoreCase(str2);
    }

    private static Map<Shape, String> a(Node node, aa aaVar) {
        if (node == null) {
            return Collections.emptyMap();
        }
        if (node.hasChildNodes()) {
            AffineTransform scaleInstance = AffineTransform.getScaleInstance(aaVar.s(), aaVar.s());
            NodeList childNodes = node.getChildNodes();
            HashMap hashMap = new HashMap(childNodes.getLength());
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (b("area", item.getNodeName()) && item.hasAttributes()) {
                    NamedNodeMap attributes = item.getAttributes();
                    String a2 = a(attributes, "shape");
                    String a3 = a(attributes, "coords");
                    if (a3 != null) {
                        String[] split = a3.split(",");
                        String a4 = a(attributes, "href");
                        if (b("rect", a2) || b("rectangle", a2)) {
                            Shape a5 = a(split, 4);
                            if (a5 != null) {
                                hashMap.put(scaleInstance.createTransformedShape(a5), a4);
                            }
                        } else if (b("circ", a2) || b("circle", a2)) {
                            Shape a6 = a(split, 3);
                            if (a6 != null) {
                                hashMap.put(scaleInstance.createTransformedShape(a6), a4);
                            }
                        } else if (b("poly", a2) || b("polygon", a2)) {
                            Shape a7 = a(split, -1);
                            if (a7 != null) {
                                hashMap.put(scaleInstance.createTransformedShape(a7), a4);
                            }
                        } else if (l.b()) {
                            l.g(Level.INFO, "Unsupported shape: '" + a2 + "'");
                        }
                    }
                }
            }
            return hashMap;
        }
        return Collections.emptyMap();
    }

    private static String a(NamedNodeMap namedNodeMap, String str) {
        Node namedItem = namedNodeMap.getNamedItem(str);
        if (namedItem == null) {
            return null;
        }
        return namedItem.getNodeValue();
    }

    private static Shape a(String[] strArr, int i) {
        if ((-1 == i && 0 == strArr.length % 2) || i == strArr.length) {
            float[] fArr = new float[strArr.length];
            int i2 = 0;
            for (String str : strArr) {
                try {
                    int i3 = i2;
                    i2++;
                    fArr[i3] = Float.parseFloat(str.trim());
                } catch (NumberFormatException e) {
                    l.f(Level.WARNING, "Error while parsing shape coords", e);
                    return null;
                }
            }
            if (4 == i) {
                return new Rectangle2D.Float(fArr[0], fArr[1], fArr[2] - fArr[0], fArr[3] - fArr[1]);
            }
            if (3 == i) {
                float f = fArr[2];
                return new Ellipse2D.Float(fArr[0] - f, fArr[1] - f, f * 2.0f, f * 2.0f);
            }
            if (-1 == i) {
                int length = fArr.length / 2;
                int[] iArr = new int[length];
                int[] iArr2 = new int[length];
                int i4 = 0;
                for (int i5 = 0; i5 < length; i5++) {
                    int i6 = i4;
                    int i7 = i4 + 1;
                    iArr[i5] = (int) fArr[i6];
                    i4 = i7 + 1;
                    iArr2[i5] = (int) fArr[i7];
                }
                return new Polygon(iArr, iArr2, length);
            }
            l.g(Level.INFO, "Unsupported shape: '" + i + "'");
            return null;
        }
        return null;
    }

    public boolean a(String str) {
        return str.toLowerCase(Locale.US).equals("all") || this.d.contains("all") || this.d.contains(str.toLowerCase(Locale.US));
    }

    public void b(String str) {
        this.f1409b = str;
    }

    public void c(String str) {
        String[] split = str.split(",");
        this.d.clear();
        for (String str2 : split) {
            this.d.add(str2.trim().toLowerCase(Locale.US));
        }
    }

    public void d(String str) {
        this.d.add(str);
    }

    public void a(int i) {
        this.c = i;
    }

    public void a(r rVar) {
        this.f1408a = rVar;
    }

    public String a() {
        return this.f1409b;
    }

    public List<String> b() {
        return this.d;
    }

    public int c() {
        return this.c;
    }

    public r d() {
        return this.f1408a;
    }

    public String e() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public boolean f() {
        return this.e != null;
    }
}
