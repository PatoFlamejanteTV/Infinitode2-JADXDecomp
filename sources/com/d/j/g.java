package com.d.j;

import com.d.h.w;
import com.d.m.i;
import com.d.m.l;
import java.io.Reader;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/* loaded from: infinitode-2.jar:com/d/j/g.class */
public final class g extends com.d.j.a {

    /* renamed from: a, reason: collision with root package name */
    private Document f1400a;

    /* renamed from: b, reason: collision with root package name */
    private static final a f1401b = new a(0);
    private static boolean c = true;

    private g(InputSource inputSource) {
        super(inputSource);
    }

    public static g a(InputSource inputSource) {
        return f1401b.a(new g(inputSource));
    }

    public static g a(Reader reader) {
        return f1401b.a(new g(new InputSource(reader)));
    }

    public final Document d() {
        return this.f1400a;
    }

    final void a(Document document) {
        this.f1400a = document;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v19, types: [org.xml.sax.XMLReader] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v26, types: [org.xml.sax.XMLReader] */
    public static final XMLReader e() {
        XMLReader xMLReader = null;
        String a2 = com.d.m.b.a("xr.load.xml-reader");
        if (a2 != null) {
            try {
                if (!a2.toLowerCase().equals("default") && c) {
                    try {
                        Class.forName(a2);
                    } catch (Exception unused) {
                        c = false;
                        l.e(Level.WARNING, "The XMLReader class you specified as a configuration property could not be found. Class.forName() failed on " + a2 + ". Please check classpath. Use value 'default' in FS configuration if necessary. Will now try JDK default.");
                    }
                    if (c) {
                        xMLReader = XMLReaderFactory.createXMLReader(a2);
                    }
                }
            } catch (Exception e) {
                l.e(Level.WARNING, "Could not instantiate custom XMLReader class for XML parsing: " + a2 + ". Please check classpath. Use value 'default' in FS configuration if necessary. Will now try JDK default.", e);
            }
        }
        ?? r0 = xMLReader;
        if (r0 == 0) {
            try {
                r0 = XMLReaderFactory.createXMLReader();
                xMLReader = r0;
            } catch (Exception e2) {
                l.d(r0.getMessage());
            }
        }
        ?? r02 = xMLReader;
        if (r02 == 0) {
            try {
                l.e(Level.WARNING, "falling back on the default parser");
                r02 = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
                xMLReader = r02;
            } catch (Exception e3) {
                l.d(r02.getMessage());
            }
        }
        if (xMLReader == null) {
            throw new w.a("Could not instantiate any SAX 2 parser, including JDK default. The name of the class to use should have been read from the org.xml.sax.driver System property, which is set to: ");
        }
        l.e("SAX XMLReader in use (parser): " + xMLReader.getClass().getName());
        return xMLReader;
    }

    /* loaded from: infinitode-2.jar:com/d/j/g$a.class */
    static class a {
        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }

        private static void a(XMLReader xMLReader) {
            try {
                xMLReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
                xMLReader.setFeature("http://xml.org/sax/features/external-general-entities", false);
                xMLReader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
                xMLReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", true);
                xMLReader.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            } catch (SAXNotRecognizedException e) {
                l.e(Level.SEVERE, "Unable to disable XML External Entities, which might put you at risk to XXE attacks", e);
            } catch (SAXNotSupportedException e2) {
                l.e(Level.SEVERE, "Unable to disable XML External Entities, which might put you at risk to XXE attacks", e2);
            }
        }

        private static void a(DocumentBuilderFactory documentBuilderFactory) {
            try {
                documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
                documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
                documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
                documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
                documentBuilderFactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            } catch (ParserConfigurationException e) {
                l.e(Level.SEVERE, "Unable to disable XML External Entities, which might put you at risk to XXE attacks", e);
            }
        }

        private static void a(TransformerFactory transformerFactory) {
            try {
                transformerFactory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
                transformerFactory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
            } catch (IllegalArgumentException e) {
                l.e(Level.SEVERE, "Unable to disable XML External Entities, which might put you at risk to XXE attacks", e);
            }
        }

        private static TransformerFactory a(String str) {
            try {
                return TransformerFactory.newInstance(str, null);
            } catch (TransformerFactoryConfigurationError unused) {
                l.e(Level.SEVERE, "Could not load preferred XML transformer, using default which may not be secure.");
                return TransformerFactory.newInstance();
            }
        }

        private static DocumentBuilderFactory b(String str) {
            try {
                return str == null ? DocumentBuilderFactory.newInstance() : DocumentBuilderFactory.newInstance(str, null);
            } catch (FactoryConfigurationError unused) {
                l.e(Level.SEVERE, "Could not load preferred XML document builder, using default which may not be secure.");
                return DocumentBuilderFactory.newInstance();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public g a(g gVar) {
            TransformerFactory a2;
            XMLReader e = g.e();
            a(e);
            b(e);
            c(e);
            long currentTimeMillis = System.currentTimeMillis();
            try {
                SAXSource sAXSource = new SAXSource(e, gVar.a());
                DocumentBuilderFactory b2 = b(i.a().a().f1107b);
                a(b2);
                b2.setNamespaceAware(true);
                b2.setValidating(false);
                DOMResult dOMResult = new DOMResult(b2.newDocumentBuilder().newDocument());
                String str = i.a().a().f1106a;
                if (str == null) {
                    a2 = TransformerFactory.newInstance();
                } else {
                    a2 = a(str);
                }
                a(a2);
                try {
                    a2.newTransformer().transform(sAXSource, dOMResult);
                    gVar.a(System.currentTimeMillis() - currentTimeMillis);
                    l.e("Loaded document in ~" + gVar.c() + "ms");
                    gVar.a((Document) dOMResult.getNode());
                    return gVar;
                } catch (Exception e2) {
                    throw new w.a("Can't load the XML resource (using TRaX transformer). " + e2.getMessage(), (Throwable) e2);
                }
            } catch (Exception e3) {
                throw new w.a("Failed on configuring SAX to DOM transformer.", (Throwable) e3);
            }
        }

        private void b(XMLReader xMLReader) {
            try {
                xMLReader.setEntityResolver(e.a());
                xMLReader.setErrorHandler(new h(this));
            } catch (Exception e) {
                throw new w.a("Failed on configuring SAX parser/XMLReader.", (Throwable) e);
            }
        }

        private void c(XMLReader xMLReader) {
            try {
                xMLReader.setFeature("http://xml.org/sax/features/validation", false);
                xMLReader.setFeature("http://xml.org/sax/features/namespaces", true);
            } catch (SAXException e) {
                l.e(Level.WARNING, "Could not set validation/namespace features for XML parser,exception thrown.", e);
            }
            if (com.d.m.b.b("xr.load.configure-features", false)) {
                l.e(Level.FINE, "SAX Parser: by request, not changing any parser features.");
                return;
            }
            a(xMLReader, "http://xml.org/sax/features/validation", "xr.load.validation");
            a(xMLReader, "http://xml.org/sax/features/string-interning", "xr.load.string-interning");
            a(xMLReader, "http://xml.org/sax/features/namespaces", "xr.load.namespaces");
            a(xMLReader, "http://xml.org/sax/features/namespace-prefixes", "xr.load.namespace-prefixes");
        }

        private static void a(XMLReader xMLReader, String str, String str2) {
            try {
                xMLReader.setFeature(str, com.d.m.b.a(str2, false));
                l.e(Level.FINE, "SAX Parser feature: " + str.substring(str.lastIndexOf("/")) + " set to " + xMLReader.getFeature(str));
            } catch (SAXNotRecognizedException unused) {
                l.e(Level.WARNING, "SAX feature not recognized on this XMLReader: " + str + ". Feature may be properly named, but not recognized by this parser.");
            } catch (SAXNotSupportedException unused2) {
                l.e(Level.WARNING, "SAX feature not supported on this XMLReader: " + str);
            }
        }
    }
}
