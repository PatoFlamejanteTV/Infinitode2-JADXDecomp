package com.d.j;

import com.d.m.l;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/* loaded from: infinitode-2.jar:com/d/j/e.class */
public class e implements EntityResolver {

    /* renamed from: a, reason: collision with root package name */
    private static final e f1396a = new e();

    /* renamed from: b, reason: collision with root package name */
    private final Map<String, String> f1397b = new HashMap();

    private e() {
        this.f1397b.putAll(new c().a("/resources/schema/openhtmltopdf/catalog-special.xml"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.io.InputStream] */
    @Override // org.xml.sax.EntityResolver
    public InputSource resolveEntity(String str, String str2) {
        InputSource inputSource;
        String str3 = b().get(str);
        if (str3 != null) {
            URL resource = e.class.getResource(str3);
            ?? r0 = 0;
            InputStream inputStream = null;
            try {
                r0 = resource.openStream();
                inputStream = r0;
            } catch (IOException e) {
                r0.printStackTrace();
            }
            if (inputStream == null) {
                l.b(Level.WARNING, "Can't find a local reference for Entity for public ID: " + str + " and expected to. The local URL should be: " + str3 + ". Not finding this probably means a CLASSPATH configuration problem; this resource should be included with the renderer and so not finding it means it is not on the CLASSPATH, and should be. Will let parser use the default in this case.");
            }
            InputSource inputSource2 = new InputSource(inputStream);
            inputSource = inputSource2;
            inputSource2.setSystemId(resource.toExternalForm());
            l.b(Level.FINE, "Entity public: " + str + " -> " + str3 + " (local)");
        } else {
            l.b("Entity public: " + str + ", no local mapping. Returning empty entity to avoid pulling from network.");
            inputSource = new InputSource(new StringReader(""));
        }
        return inputSource;
    }

    public static e a() {
        return f1396a;
    }

    private Map<String, String> b() {
        return Collections.unmodifiableMap(this.f1397b);
    }
}
