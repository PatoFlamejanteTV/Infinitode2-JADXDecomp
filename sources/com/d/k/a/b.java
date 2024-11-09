package com.d.k.a;

import com.d.m.l;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.logging.Level;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* loaded from: infinitode-2.jar:com/d/k/a/b.class */
public final class b extends a {
    @Override // com.d.k.a, com.d.d.l
    public final String d(Element element) {
        String nodeName = element.getNodeName();
        boolean z = -1;
        switch (nodeName.hashCode()) {
            case -1003243718:
                if (nodeName.equals("textarea")) {
                    z = 7;
                    break;
                }
                break;
            case 112:
                if (nodeName.equals(FlexmarkHtmlConverter.P_NODE)) {
                    z = 5;
                    break;
                }
                break;
            case 3696:
                if (nodeName.equals(FlexmarkHtmlConverter.TD_NODE)) {
                    z = true;
                    break;
                }
                break;
            case 3700:
                if (nodeName.equals(FlexmarkHtmlConverter.TH_NODE)) {
                    z = 2;
                    break;
                }
                break;
            case 3710:
                if (nodeName.equals(FlexmarkHtmlConverter.TR_NODE)) {
                    z = 3;
                    break;
                }
                break;
            case 99473:
                if (nodeName.equals(FlexmarkHtmlConverter.DIV_NODE)) {
                    z = 6;
                    break;
                }
                break;
            case 104387:
                if (nodeName.equals(FlexmarkHtmlConverter.IMG_NODE)) {
                    z = 4;
                    break;
                }
                break;
            case 114276:
                if (nodeName.equals(FlexmarkHtmlConverter.SVG_NODE)) {
                    z = 9;
                    break;
                }
                break;
            case 100358090:
                if (nodeName.equals(FlexmarkHtmlConverter.INPUT_NODE)) {
                    z = 8;
                    break;
                }
                break;
            case 110115790:
                if (nodeName.equals(FlexmarkHtmlConverter.TABLE_NODE)) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return n(element);
            case true:
            case true:
                return m(element);
            case true:
                return o(element);
            case true:
                return l(element);
            case true:
            case true:
                return k(element);
            case true:
                return j(element);
            case true:
                return i(element);
            case true:
                return h(element);
            default:
                return "";
        }
    }

    private String h(Element element) {
        String attribute = element.getAttribute("width");
        String attribute2 = element.getAttribute("height");
        if (!attribute.isEmpty() || !attribute2.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            if (!attribute.isEmpty()) {
                sb.append("width: ");
                sb.append(attribute);
                if (b(attribute)) {
                    sb.append("px");
                }
                sb.append(';');
            }
            if (!attribute2.isEmpty()) {
                sb.append("height: ");
                sb.append(attribute2);
                if (b(attribute2)) {
                    sb.append("px");
                }
                sb.append(';');
            }
            return sb.toString();
        }
        String attribute3 = element.getAttribute("viewBox");
        String[] split = attribute3.split("\\s+");
        if (split.length != 4) {
            return "";
        }
        try {
            int parseInt = Integer.parseInt(split[2]);
            int parseInt2 = Integer.parseInt(split[3]);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("width: ");
            sb2.append(parseInt);
            sb2.append("px;");
            sb2.append("height: ");
            sb2.append(parseInt2);
            sb2.append("px;");
            return "";
        } catch (NumberFormatException unused) {
            l.d(Level.WARNING, "Invalid integer passed in viewBox attribute for SVG: " + attribute3);
            return "";
        }
    }

    private String i(Element element) {
        StringBuilder sb = new StringBuilder();
        if (element.hasAttribute("width") && b(element.getAttribute("width"))) {
            sb.append("width: ");
            sb.append(element.getAttribute("width"));
            sb.append("px;");
        } else if (element.hasAttribute("size") && b(element.getAttribute("size"))) {
            sb.append("width: ");
            sb.append(element.getAttribute("size"));
            sb.append("em;");
        }
        return sb.toString();
    }

    private String j(Element element) {
        StringBuilder sb = new StringBuilder();
        if (element.hasAttribute("cols") && b(element.getAttribute("cols"))) {
            sb.append("width: ");
            sb.append(element.getAttribute("cols"));
            sb.append("em;");
        }
        if (element.hasAttribute("rows") && b(element.getAttribute("rows"))) {
            sb.append("height: ");
            sb.append(element.getAttribute("rows"));
            sb.append("em;");
        }
        return sb.toString();
    }

    private String k(Element element) {
        StringBuilder sb = new StringBuilder();
        b(element, sb);
        return sb.toString();
    }

    private String l(Element element) {
        StringBuilder sb = new StringBuilder();
        a(element, sb);
        return sb.toString();
    }

    private String m(Element element) {
        StringBuilder sb = new StringBuilder();
        Element p = p(element);
        if (p != null) {
            String b2 = b(p, "cellpadding");
            if (b2 != null) {
                sb.append("padding: ");
                sb.append(a(b2));
                sb.append(";");
            }
            String b3 = b(p, "border");
            if (b3 != null && !b3.equals("0")) {
                sb.append("border: 1px outset black;");
            }
        }
        String b4 = b(element, "width");
        if (b4 != null) {
            sb.append("width: ");
            sb.append(a(b4));
            sb.append(";");
        }
        String b5 = b(element, "height");
        if (b5 != null) {
            sb.append("height: ");
            sb.append(a(b5));
            sb.append(";");
        }
        c(element, sb);
        String b6 = b(element, "bgcolor");
        if (b6 != null) {
            String lowerCase = b6.toLowerCase();
            sb.append("background-color: ");
            if (c(lowerCase)) {
                sb.append('#');
                sb.append(lowerCase);
            } else {
                sb.append(lowerCase);
            }
            sb.append(';');
        }
        String b7 = b(element, "background");
        if (b7 != null) {
            sb.append("background-image: url(");
            sb.append(b7);
            sb.append(");");
        }
        return sb.toString();
    }

    private String n(Element element) {
        StringBuilder sb = new StringBuilder();
        String b2 = b(element, "width");
        if (b2 != null) {
            sb.append("width: ");
            sb.append(a(b2));
            sb.append(";");
        }
        String b3 = b(element, "border");
        if (b3 != null) {
            sb.append("border: ");
            sb.append(a(b3));
            sb.append(" inset black;");
        }
        String b4 = b(element, "cellspacing");
        if (b4 != null) {
            sb.append("border-collapse: separate; border-spacing: ");
            sb.append(a(b4));
            sb.append(";");
        }
        String b5 = b(element, "bgcolor");
        if (b5 != null) {
            String lowerCase = b5.toLowerCase();
            sb.append("background-color: ");
            if (c(lowerCase)) {
                sb.append('#');
                sb.append(lowerCase);
            } else {
                sb.append(lowerCase);
            }
            sb.append(';');
        }
        String b6 = b(element, "background");
        if (b6 != null) {
            sb.append("background-image: url(");
            sb.append(b6);
            sb.append(");");
        }
        a(element, sb);
        return sb.toString();
    }

    private String o(Element element) {
        StringBuilder sb = new StringBuilder();
        c(element, sb);
        return sb.toString();
    }

    private void a(Element element, StringBuilder sb) {
        String b2 = b(element, "align");
        if (b2 != null) {
            String trim = b2.toLowerCase().trim();
            if (trim.equals("left")) {
                sb.append("float: left;");
            } else if (trim.equals("right")) {
                sb.append("float: right;");
            } else if (trim.equals("center")) {
                sb.append("margin-left: auto; margin-right: auto;");
            }
        }
    }

    private void b(Element element, StringBuilder sb) {
        String b2 = b(element, "align");
        if (b2 != null) {
            String trim = b2.toLowerCase().trim();
            if (trim.equals("left") || trim.equals("right") || trim.equals("center") || trim.equals("justify")) {
                sb.append("text-align: ");
                sb.append(trim);
                sb.append(";");
            }
        }
    }

    private void c(Element element, StringBuilder sb) {
        String b2 = b(element, "align");
        if (b2 != null) {
            sb.append("text-align: ");
            sb.append(b2.toLowerCase());
            sb.append(";");
        }
        String b3 = b(element, "valign");
        if (b3 != null) {
            sb.append("vertical-align: ");
            sb.append(b3.toLowerCase());
            sb.append(";");
        }
    }

    private static boolean c(String str) {
        if (str.length() != 6) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!((charAt >= '0' && charAt <= '9') || (charAt >= 'a' && charAt <= 'f'))) {
                return false;
            }
        }
        return true;
    }

    private static Element p(Element element) {
        Node parentNode = element.getParentNode();
        if (parentNode.getNodeType() == 1) {
            Element element2 = (Element) parentNode;
            if (element2.getNodeName().equals(FlexmarkHtmlConverter.TR_NODE)) {
                Node parentNode2 = element2.getParentNode();
                if (parentNode2.getNodeType() == 1) {
                    Element element3 = (Element) parentNode2;
                    String nodeName = element3.getNodeName();
                    if (nodeName.equals(FlexmarkHtmlConverter.TABLE_NODE)) {
                        return element3;
                    }
                    if (nodeName.equals(FlexmarkHtmlConverter.TBODY_NODE) || nodeName.equals("tfoot") || nodeName.equals(FlexmarkHtmlConverter.THEAD_NODE)) {
                        Node parentNode3 = element3.getParentNode();
                        if (parentNode3.getNodeType() == 1) {
                            Element element4 = (Element) parentNode3;
                            if (element4.getNodeName().equals(FlexmarkHtmlConverter.TABLE_NODE)) {
                                return element4;
                            }
                            return null;
                        }
                        return null;
                    }
                    return null;
                }
                return null;
            }
            return null;
        }
        return null;
    }
}
