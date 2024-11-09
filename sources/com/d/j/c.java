package com.d.j;

import com.d.h.w;
import com.d.m.l;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/* loaded from: infinitode-2.jar:com/d/j/c.class */
public class c {
    public final Map<String, String> a(String str) {
        Map<String, String> emptyMap;
        try {
            InputStream resourceAsStream = c.class.getResourceAsStream(str);
            try {
                emptyMap = a(new InputSource(new BufferedInputStream(resourceAsStream)));
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
            } finally {
            }
        } catch (Exception e) {
            l.b(Level.WARNING, "Could not open XML catalog from URI '" + str + "'", e);
            emptyMap = Collections.emptyMap();
        }
        return emptyMap;
    }

    private Map<String, String> a(InputSource inputSource) {
        XMLReader e = g.e();
        a aVar = new a();
        a(e, aVar);
        a(e, "http://xml.org/sax/features/validation", false);
        try {
            e.parse(inputSource);
            return aVar.a();
        } catch (Exception e2) {
            throw new RuntimeException("Failed on configuring SAX to DOM transformer.", e2);
        }
    }

    private void a(XMLReader xMLReader, ContentHandler contentHandler) {
        try {
            xMLReader.setContentHandler(contentHandler);
            xMLReader.setErrorHandler(new d(this));
        } catch (Exception e) {
            throw new w.a("Failed on configuring SAX parser/XMLReader.", (Throwable) e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/j/c$a.class */
    public static class a extends DefaultHandler {

        /* renamed from: a, reason: collision with root package name */
        private final Map<String, String> f1395a = new HashMap();

        public final Map<String, String> a() {
            return this.f1395a;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public final void startElement(String str, String str2, String str3, Attributes attributes) {
            if (str2.equalsIgnoreCase("public") || (str2.equals("") && str3.equalsIgnoreCase("public"))) {
                this.f1395a.put(attributes.getValue("publicId"), attributes.getValue("uri"));
            }
        }
    }

    private static void a(XMLReader xMLReader, String str, boolean z) {
        try {
            xMLReader.setFeature(str, false);
            l.b(Level.FINE, "SAX Parser feature: " + str.substring(str.lastIndexOf("/")) + " set to " + xMLReader.getFeature(str));
        } catch (SAXNotRecognizedException unused) {
            l.b(Level.WARNING, "SAX feature not recognized on this XMLReader: " + str + ". Feature may be properly named, but not recognized by this parser.");
        } catch (SAXNotSupportedException unused2) {
            l.b(Level.WARNING, "SAX feature not supported on this XMLReader: " + str);
        }
    }
}
