package org.a.d.a;

import com.vladsch.flexmark.util.html.Attribute;
import org.a.d.b.al;

@al(b = "dc", a = "http://purl.org/dc/elements/1.1/")
/* loaded from: infinitode-2.jar:org/a/d/a/b.class */
public class b extends l {
    public b(org.a.d.b bVar) {
        super(bVar);
    }

    public final void a(String str) {
        b("creator", str);
    }

    private void e(String str, String str2) {
        a("description", str, str2);
    }

    public final void b(String str) {
        e(null, str);
    }

    private void f(String str, String str2) {
        a(Attribute.TITLE_ATTR, str, str2);
    }

    public final void c(String str) {
        f(null, str);
    }
}
