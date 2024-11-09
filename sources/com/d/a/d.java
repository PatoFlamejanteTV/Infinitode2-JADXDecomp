package com.d.a;

import com.a.a.c.k.b.aa;
import com.d.d.o;
import com.d.e.v;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/* loaded from: infinitode-2.jar:com/d/a/d.class */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private List<b> f954a;

    /* renamed from: b, reason: collision with root package name */
    private Map<Text, b> f955b;
    private Map<Element, b> c;

    /* loaded from: infinitode-2.jar:com/d/a/d$b.class */
    public static class b {

        /* renamed from: b, reason: collision with root package name */
        private final StringBuilder f956b;
        private final TreeMap<Integer, aa> c;
        private Map<Text, Integer> d;

        /* renamed from: a, reason: collision with root package name */
        protected final com.d.c.a.c f957a;
        private byte e;

        /* synthetic */ b(com.d.c.a.c cVar, boolean z, byte b2) {
            this(cVar, false);
        }

        /* synthetic */ b(com.d.c.a.c cVar, byte b2) {
            this(cVar);
        }

        private b(com.d.c.a.c cVar) {
            this(cVar, true);
        }

        private b(com.d.c.a.c cVar, boolean z) {
            this.d = new HashMap();
            this.e = (byte) 0;
            this.f956b = z ? new StringBuilder() : null;
            this.c = z ? new TreeMap<>() : null;
            this.f957a = cVar;
        }

        protected void a(String str, Text text) {
            int length = this.f956b.length();
            this.f956b.append(str);
            this.d.put(text, Integer.valueOf(length));
        }

        protected void a(com.d.a.b bVar) {
            byte b2 = 0;
            String sb = this.f956b.toString();
            if (this.f957a == com.d.c.a.c.aK) {
                b2 = 1;
            } else if (this.f957a == com.d.c.a.c.e) {
                b2 = bVar.a(sb);
            }
            this.e = b2 == 3 ? (byte) 0 : b2;
            bVar.a(sb, this.e);
            b(bVar);
        }

        public final int a(Text text) {
            Integer num;
            if (this.d.isEmpty() || (num = this.d.get(text)) == null) {
                return -1;
            }
            return num.intValue();
        }

        private void b(com.d.a.b bVar) {
            int a2 = bVar.a();
            for (int i = 0; i < a2; i++) {
                aa a3 = bVar.a(i);
                this.c.put(Integer.valueOf(a3.a()), a3);
            }
        }

        public aa a(int i) {
            Map.Entry<Integer, aa> ceilingEntry = this.c.ceilingEntry(Integer.valueOf(i));
            if (ceilingEntry != null) {
                return ceilingEntry.getValue();
            }
            return null;
        }

        public aa b(int i) {
            Map.Entry<Integer, aa> floorEntry = this.c.floorEntry(Integer.valueOf(i));
            if (floorEntry != null) {
                return floorEntry.getValue();
            }
            return null;
        }

        public byte a() {
            return this.e;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/a/d$a.class */
    public static class a extends b {
        /* synthetic */ a(com.d.c.a.c cVar, byte b2) {
            this(cVar);
        }

        private a(com.d.c.a.c cVar) {
            super(cVar, false, (byte) 0);
        }

        @Override // com.d.a.d.b
        protected final void a(String str, Text text) {
        }

        @Override // com.d.a.d.b
        public final byte a() {
            return this.f957a == com.d.c.a.c.aK ? (byte) 1 : (byte) 0;
        }

        @Override // com.d.a.d.b
        public final aa a(int i) {
            return null;
        }

        @Override // com.d.a.d.b
        public final aa b(int i) {
            return null;
        }

        @Override // com.d.a.d.b
        protected final void a(com.d.a.b bVar) {
        }
    }

    public final b a(Text text) {
        return this.f955b.isEmpty() ? this.f954a.get(0) : this.f955b.get(text);
    }

    public final b a(Element element) {
        return this.c.isEmpty() ? this.f954a.get(0) : this.c.get(element);
    }

    public final void a(v vVar, Document document) {
        boolean b2 = vVar.g().b();
        com.d.c.a.c az = vVar.y().a(document.getDocumentElement()).az();
        b bVar = b2 ? new b(az, (byte) 0) : new a(az, (byte) 0);
        if (b2) {
            this.f954a = new ArrayList();
            this.f955b = new HashMap();
            this.c = new HashMap();
            a(vVar, document, bVar);
            return;
        }
        this.f954a = Collections.singletonList(bVar);
        this.f955b = Collections.emptyMap();
        this.c = Collections.emptyMap();
    }

    public final void a(v vVar) {
        Iterator<b> it = this.f954a.iterator();
        while (it.hasNext()) {
            it.next().a(vVar.h().a());
        }
    }

    private void a(v vVar, Node node, b bVar) {
        Node nextSibling;
        o v = vVar.v();
        Node firstChild = node.getFirstChild();
        Node node2 = firstChild;
        if (firstChild == null) {
            return;
        }
        do {
            if (node2.getNodeType() == 3 || node2.getNodeType() == 4) {
                bVar.a(((Text) node2).getData(), (Text) node2);
                this.f955b.put((Text) node2, bVar);
            } else if (node2.getNodeType() == 1) {
                Element element = (Element) node2;
                if (!element.getNodeName().equals("head") && !v.a(element)) {
                    com.d.c.f.c a2 = vVar.y().a(element);
                    if (!a2.aB() && !element.hasAttribute("dir") && !element.getNodeName().equals("bdi")) {
                        this.c.put(element, bVar);
                        a(vVar, element, bVar);
                    } else {
                        b bVar2 = new b(a2.az(), (byte) 0);
                        this.f954a.add(bVar2);
                        this.c.put(element, bVar2);
                        a(vVar, element, bVar2);
                    }
                }
            }
            nextSibling = node2.getNextSibling();
            node2 = nextSibling;
        } while (nextSibling != null);
    }
}
