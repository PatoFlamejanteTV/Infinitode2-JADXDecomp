package org.a.d.a;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.ArrayList;
import java.util.Iterator;
import org.a.d.b.am;
import org.a.d.b.ap;
import org.jsoup.parser.Parser;

/* loaded from: infinitode-2.jar:org/a/d/a/l.class */
public class l extends org.a.d.b.d {
    public l(org.a.d.b bVar, String str, String str2, String str3) {
        super(bVar, str, str2, str3);
        c(h(), i());
    }

    public l(org.a.d.b bVar) {
        this(bVar, null, null, null);
    }

    private org.a.d.b.b a(String str) {
        for (org.a.d.b.b bVar : c().a()) {
            if (bVar.e().equals(str)) {
                return bVar;
            }
        }
        return null;
    }

    public final String a() {
        org.a.d.b.g e = e("about");
        if (e != null) {
            return e.c();
        }
        return "";
    }

    public final void d(String str) {
        a(new org.a.d.b.g("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "about", str));
    }

    private void a(ap apVar, String str, Object obj) {
        if (obj == null) {
            for (org.a.d.b.b bVar : c().a()) {
                if (bVar.e().equals(str)) {
                    c().b(bVar);
                    return;
                }
            }
            return;
        }
        try {
            org.a.d.b.c a2 = g().b().a((String) null, i(), str, obj, apVar);
            for (org.a.d.b.b bVar2 : d()) {
                if (bVar2.e().equals(str)) {
                    b(bVar2);
                    a(a2);
                    return;
                }
            }
            a(a2);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to create property with the specified type given in parameters", e);
        }
    }

    public final void a(String str, String str2) {
        a(ap.Text, str, str2);
    }

    public final void b(String str, String str2) {
        org.a.d.b.f fVar = (org.a.d.b.f) a(str);
        am d = d(FlexmarkHtmlConverter.LI_NODE, str2);
        if (fVar != null) {
            fVar.c().a(d);
            return;
        }
        org.a.d.b.f a2 = a(str, org.a.d.b.k.Seq);
        a2.c().a(d);
        a(a2);
    }

    public final void a(String str, org.a.d.b.b bVar) {
        org.a.d.b.f fVar = (org.a.d.b.f) a(str);
        if (fVar != null) {
            fVar.c().a(bVar);
            return;
        }
        org.a.d.b.f a2 = a(str, org.a.d.b.k.Bag);
        a2.c().a(bVar);
        a(a2);
    }

    public final void b(String str, org.a.d.b.b bVar) {
        org.a.d.b.f fVar = (org.a.d.b.f) a(str);
        if (fVar != null) {
            fVar.c().a(bVar);
            return;
        }
        org.a.d.b.f a2 = a(str, org.a.d.b.k.Seq);
        a2.c().a(bVar);
        a(a2);
    }

    private static void a(org.a.d.b.m mVar) {
        Iterator<org.a.d.b.b> it = mVar.a().iterator();
        org.a.d.b.b bVar = null;
        boolean z = false;
        if (it.hasNext() && it.next().e("lang").c().equals("x-default")) {
            return;
        }
        while (it.hasNext() && !z) {
            org.a.d.b.b next = it.next();
            bVar = next;
            if (next.e("lang").c().equals("x-default")) {
                mVar.b(bVar);
                z = true;
            }
        }
        if (z) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList.add(bVar);
            for (org.a.d.b.b bVar2 : mVar.a()) {
                arrayList.add(bVar2);
                arrayList2.add(bVar2);
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                mVar.b((org.a.d.b.b) it2.next());
            }
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                mVar.a((org.a.d.b.b) it3.next());
            }
        }
    }

    public final void a(String str, String str2, String str3) {
        if (str2 == null || str2.isEmpty()) {
            str2 = "x-default";
        }
        org.a.d.b.b a2 = a(str);
        if (a2 != null) {
            if (a2 instanceof org.a.d.b.f) {
                org.a.d.b.f fVar = (org.a.d.b.f) a2;
                for (org.a.d.b.b bVar : fVar.c().a()) {
                    if (bVar.e("lang").c().equals(str2)) {
                        fVar.c().b(bVar);
                        if (str3 != null) {
                            am d = d(FlexmarkHtmlConverter.LI_NODE, str3);
                            d.a(new org.a.d.b.g(Parser.NamespaceXml, "lang", str2));
                            fVar.c().a(d);
                        }
                        a(fVar.c());
                        return;
                    }
                }
                am d2 = d(FlexmarkHtmlConverter.LI_NODE, str3);
                d2.a(new org.a.d.b.g(Parser.NamespaceXml, "lang", str2));
                fVar.c().a(d2);
                a(fVar.c());
                return;
            }
            return;
        }
        org.a.d.b.f a3 = a(str, org.a.d.b.k.Alt);
        am d3 = d(FlexmarkHtmlConverter.LI_NODE, str3);
        d3.a(new org.a.d.b.g(Parser.NamespaceXml, "lang", str2));
        a3.c().a(d3);
        a(a3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final org.a.d.b.c a(String str, Object obj) {
        return g().b().a(getClass(), (String) null, i(), str, obj);
    }
}
