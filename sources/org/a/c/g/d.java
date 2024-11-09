package org.a.c.g;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.a.c.b.f;
import org.a.c.b.i;
import org.a.c.b.j;
import org.a.c.b.s;

/* loaded from: infinitode-2.jar:org/a/c/g/d.class */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private final OutputStream f4454a;

    /* renamed from: b, reason: collision with root package name */
    private static byte[] f4455b = {32};
    private static byte[] c = {10};

    public d(OutputStream outputStream) {
        this.f4454a = outputStream;
    }

    public final void a(Object... objArr) {
        for (Object obj : objArr) {
            a(obj);
        }
        this.f4454a.write(SequenceUtils.EOL.getBytes(org.a.c.i.a.f4651a));
    }

    public final void a(List<?> list) {
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    private void a(Object obj) {
        if (obj instanceof s) {
            b.a((s) obj, this.f4454a);
            this.f4454a.write(f4455b);
            return;
        }
        if (obj instanceof f) {
            ((f) obj).a(this.f4454a);
            this.f4454a.write(f4455b);
            return;
        }
        if (obj instanceof i) {
            ((i) obj).a(this.f4454a);
            this.f4454a.write(f4455b);
            return;
        }
        if (obj instanceof org.a.c.b.c) {
            ((org.a.c.b.c) obj).a(this.f4454a);
            this.f4454a.write(f4455b);
            return;
        }
        if (obj instanceof j) {
            ((j) obj).a(this.f4454a);
            this.f4454a.write(f4455b);
            return;
        }
        if (obj instanceof org.a.c.b.a) {
            org.a.c.b.a aVar = (org.a.c.b.a) obj;
            this.f4454a.write(b.c);
            for (int i = 0; i < aVar.b(); i++) {
                a(aVar.b(i));
                this.f4454a.write(f4455b);
            }
            this.f4454a.write(b.d);
            return;
        }
        if (obj instanceof org.a.c.b.d) {
            this.f4454a.write(b.f4450a);
            for (Map.Entry<j, org.a.c.b.b> entry : ((org.a.c.b.d) obj).e()) {
                if (entry.getValue() != null) {
                    a(entry.getKey());
                    this.f4454a.write(f4455b);
                    a(entry.getValue());
                    this.f4454a.write(f4455b);
                }
            }
            this.f4454a.write(b.f4451b);
            this.f4454a.write(f4455b);
            return;
        }
        if (obj instanceof org.a.c.a.a.a) {
            org.a.c.a.a.a aVar2 = (org.a.c.a.a.a) obj;
            if (!aVar2.a().equals("BI")) {
                this.f4454a.write(aVar2.a().getBytes(org.a.c.i.a.d));
                this.f4454a.write(c);
                return;
            }
            this.f4454a.write("BI".getBytes(org.a.c.i.a.d));
            org.a.c.b.d c2 = aVar2.c();
            for (j jVar : c2.d()) {
                org.a.c.b.b a2 = c2.a(jVar);
                jVar.a(this.f4454a);
                this.f4454a.write(f4455b);
                a(a2);
                this.f4454a.write(c);
            }
            this.f4454a.write("ID".getBytes(org.a.c.i.a.d));
            this.f4454a.write(c);
            this.f4454a.write(aVar2.b());
            this.f4454a.write(c);
            this.f4454a.write("EI".getBytes(org.a.c.i.a.d));
            this.f4454a.write(c);
            return;
        }
        throw new IOException("Error:Unknown type in content stream:" + obj);
    }
}
