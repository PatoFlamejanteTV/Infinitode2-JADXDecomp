package org.a.c.h.e;

import com.b.a.a.o;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/h/e/ah.class */
public final class ah {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<String> f4523a = new HashSet(34);

    /* renamed from: b, reason: collision with root package name */
    private static final Map<String, String> f4524b = new HashMap(34);
    private static final Map<String, l> c = new HashMap(34);

    static {
        try {
            d("Courier-Bold");
            d("Courier-BoldOblique");
            d("Courier");
            d("Courier-Oblique");
            d("Helvetica");
            d("Helvetica-Bold");
            d("Helvetica-BoldOblique");
            d("Helvetica-Oblique");
            d("Symbol");
            d("Times-Bold");
            d("Times-BoldItalic");
            d("Times-Italic");
            d("Times-Roman");
            d("ZapfDingbats");
            a("CourierCourierNew", "Courier");
            a("CourierNew", "Courier");
            a("CourierNew,Italic", "Courier-Oblique");
            a("CourierNew,Bold", "Courier-Bold");
            a("CourierNew,BoldItalic", "Courier-BoldOblique");
            a("Arial", "Helvetica");
            a("Arial,Italic", "Helvetica-Oblique");
            a("Arial,Bold", "Helvetica-Bold");
            a("Arial,BoldItalic", "Helvetica-BoldOblique");
            a("TimesNewRoman", "Times-Roman");
            a("TimesNewRoman,Italic", "Times-Italic");
            a("TimesNewRoman,Bold", "Times-Bold");
            a("TimesNewRoman,BoldItalic", "Times-BoldItalic");
            a("Symbol,Italic", "Symbol");
            a("Symbol,Bold", "Symbol");
            a("Symbol,BoldItalic", "Symbol");
            a("Times", "Times-Roman");
            a("Times,Italic", "Times-Italic");
            a("Times,Bold", "Times-Bold");
            a("Times,BoldItalic", "Times-BoldItalic");
            a("ArialMT", "Helvetica");
            a("Arial-ItalicMT", "Helvetica-Oblique");
            a("Arial-BoldMT", "Helvetica-Bold");
            a("Arial-BoldItalicMT", "Helvetica-BoldOblique");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void d(String str) {
        a(str, str);
    }

    private static void a(String str, String str2) {
        f4523a.add(str);
        f4524b.put(str, str2);
        if (c.containsKey(str2)) {
            c.put(str, c.get(str2));
        }
        String str3 = "/org/apache/pdfbox/resources/afm/" + str2 + ".afm";
        InputStream resourceAsStream = ae.class.getResourceAsStream(str3);
        if (resourceAsStream != null) {
            try {
                c.put(str, new o.a(resourceAsStream).a(true));
                return;
            } finally {
                resourceAsStream.close();
            }
        }
        throw new IOException(str3 + " not found");
    }

    public static l a(String str) {
        return c.get(str);
    }

    public static boolean b(String str) {
        return f4523a.contains(str);
    }

    public static Set<String> a() {
        return Collections.unmodifiableSet(f4523a);
    }

    public static String c(String str) {
        return f4524b.get(str);
    }
}
