package org.a.b.h.a;

import com.badlogic.gdx.pay.PurchaseManagerConfig;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: infinitode-2.jar:org/a/b/h/a/b.class */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4349a = org.a.a.a.c.a(b.class);

    /* renamed from: b, reason: collision with root package name */
    private a f4350b = null;

    private static a b() {
        String property = System.getProperty("os.name");
        if (property.startsWith(PurchaseManagerConfig.STORE_NAME_DESKTOP_WINDOWS)) {
            return new g();
        }
        if (property.startsWith("Mac")) {
            return new c();
        }
        if (property.startsWith("OS/400")) {
            return new e();
        }
        return new f();
    }

    public final List<URI> a() {
        if (this.f4350b == null) {
            this.f4350b = b();
        }
        List<File> a2 = this.f4350b.a();
        ArrayList arrayList = new ArrayList();
        Iterator<File> it = a2.iterator();
        while (it.hasNext()) {
            a(it.next(), arrayList);
        }
        return arrayList;
    }

    private void a(File file, List<URI> list) {
        File[] listFiles;
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    if (!file2.getName().startsWith(".")) {
                        a(file2, list);
                    }
                } else {
                    if (f4349a.a()) {
                        new StringBuilder("checkFontfile check ").append(file2);
                    }
                    if (a(file2)) {
                        if (f4349a.a()) {
                            new StringBuilder("checkFontfile found ").append(file2);
                        }
                        list.add(file2.toURI());
                    }
                }
            }
        }
    }

    private static boolean a(File file) {
        String lowerCase = file.getName().toLowerCase(Locale.US);
        return (lowerCase.endsWith(".ttf") || lowerCase.endsWith(".otf") || lowerCase.endsWith(".pfb") || lowerCase.endsWith(".ttc")) && !lowerCase.startsWith("fonts.");
    }
}
