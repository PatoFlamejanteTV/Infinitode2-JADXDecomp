package org.a.c.b;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/c/b/g.class */
public final class g extends FilterInputStream {

    /* renamed from: a, reason: collision with root package name */
    private final List<org.a.c.c.k> f4364a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static g a(List<org.a.c.c.l> list, d dVar, InputStream inputStream, org.a.c.d.g gVar, org.a.c.c.j jVar) {
        InputStream byteArrayInputStream;
        ArrayList arrayList = new ArrayList();
        InputStream inputStream2 = inputStream;
        if (list.isEmpty()) {
            inputStream2 = inputStream;
        } else {
            if (new HashSet(list).size() != list.size()) {
                throw new IOException("Duplicate");
            }
            for (int i = 0; i < list.size(); i++) {
                if (gVar != null) {
                    org.a.c.d.e d = gVar.d();
                    arrayList.add(list.get(i).a(inputStream2, new org.a.c.d.d(d), dVar, i, jVar));
                    byteArrayInputStream = new h(d, d);
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    arrayList.add(list.get(i).a(inputStream2, byteArrayOutputStream, dVar, i, jVar));
                    byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                }
                inputStream2 = byteArrayInputStream;
            }
        }
        return new g(inputStream2, arrayList);
    }

    private g(InputStream inputStream, List<org.a.c.c.k> list) {
        super(inputStream);
        this.f4364a = list;
    }

    public final org.a.c.c.k a() {
        if (this.f4364a.isEmpty()) {
            return org.a.c.c.k.f4402a;
        }
        return this.f4364a.get(this.f4364a.size() - 1);
    }
}
