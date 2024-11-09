package org.a.b.h.a;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/b/h/a/d.class */
public abstract class d implements a {
    protected abstract String[] b();

    @Override // org.a.b.h.a.a
    public final List<File> a() {
        ArrayList arrayList = new ArrayList();
        for (String str : b()) {
            File file = new File(str);
            try {
                if (file.exists() && file.canRead()) {
                    arrayList.add(file);
                }
            } catch (SecurityException unused) {
            }
        }
        return arrayList;
    }
}
