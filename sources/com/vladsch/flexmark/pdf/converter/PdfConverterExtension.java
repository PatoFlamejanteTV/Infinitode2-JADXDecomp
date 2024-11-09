package com.vladsch.flexmark.pdf.converter;

import com.d.a;
import com.d.a.a.b;
import com.d.g.a.b;
import com.d.h.q;
import com.d.h.x;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.NullableDataKey;
import com.vladsch.flexmark.util.misc.Utils;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.a.c.h.c.e;
import org.jsoup.Jsoup;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/pdf/converter/PdfConverterExtension.class */
public class PdfConverterExtension {
    public static final String DEFAULT_CSS_RESOURCE_PATH = "/default.css";
    public static final String DEFAULT_TOC_LIST_CLASS = "toc";
    public static final NullableDataKey<b.d> DEFAULT_TEXT_DIRECTION = new NullableDataKey<>("DEFAULT_TEXT_DIRECTION");
    public static final NullableDataKey<e> PROTECTION_POLICY = new NullableDataKey<>("PROTECTION_POLICY");
    public static final DataKey<String> DEFAULT_CSS = new DataKey<>("DEFAULT_CSS", () -> {
        return Utils.getResourceAsString(PdfConverterExtension.class, DEFAULT_CSS_RESOURCE_PATH);
    });

    public static String embedCss(String str, String str2) {
        if (str2 != null && !str2.isEmpty()) {
            int indexOf = str.indexOf("</head>");
            String str3 = "<style>\n";
            String str4 = "\n</style>";
            String str5 = "";
            if (indexOf == -1) {
                int indexOf2 = str.indexOf("<html>");
                if (indexOf2 != -1) {
                    indexOf = indexOf2 + 6;
                    str3 = "<head>" + str3;
                    str4 = str4 + "</head>";
                } else {
                    int indexOf3 = str.indexOf("<body>");
                    indexOf = indexOf3;
                    if (indexOf3 != -1) {
                        str3 = "<html><head>" + str3;
                        str4 = str4 + "</head>";
                        str5 = "</html>\n";
                    } else {
                        indexOf = 0;
                        str3 = "<html><head>" + str3;
                        str4 = str4 + "</head><body>\n";
                        str5 = "</body></html>\n";
                    }
                }
            }
            return ((Object) str.subSequence(0, indexOf)) + str3 + str2 + str4 + ((Object) str.subSequence(indexOf, str.length())) + str5;
        }
        return str;
    }

    public static void exportToPdf(String str, String str2, String str3, DataHolder dataHolder) {
        exportToPdf(str, embedCss(str2, DEFAULT_CSS.get(dataHolder)), str3, DEFAULT_TEXT_DIRECTION.get(dataHolder), PROTECTION_POLICY.get(dataHolder));
    }

    public static void exportToPdf(String str, String str2, String str3, b.d dVar) {
        exportToPdf(str, str2, str3, dVar, (e) null);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.OutputStream, java.io.FileOutputStream, java.io.FileNotFoundException] */
    public static void exportToPdf(String str, String str2, String str3, b.d dVar, e eVar) {
        ?? fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(str);
            exportToPdf((OutputStream) fileOutputStream, str2, str3, dVar, eVar);
        } catch (FileNotFoundException e) {
            fileOutputStream.printStackTrace();
        }
    }

    public static void exportToPdf(OutputStream outputStream, String str, String str2, DataHolder dataHolder) {
        exportToPdf(outputStream, str, str2, DEFAULT_TEXT_DIRECTION.get(dataHolder), PROTECTION_POLICY.get(dataHolder));
    }

    public static void exportToPdf(OutputStream outputStream, String str, String str2, b.d dVar) {
        exportToPdf(outputStream, str, str2, dVar, (e) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v19, types: [com.d.h.q] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Exception] */
    public static void exportToPdf(OutputStream outputStream, String str, String str2, b.d dVar, e eVar) {
        ?? r0 = 0;
        q qVar = null;
        try {
            try {
                x xVar = new x();
                handleTextDirection(dVar, xVar);
                handleW3cDocument(str, str2, xVar);
                xVar.a(outputStream);
                q a2 = xVar.a();
                qVar = a2;
                org.a.c.h.b a3 = a2.a();
                if (eVar != null) {
                    a3.a(eVar);
                }
                qVar.c();
                r0 = qVar;
                r0.d();
                try {
                    qVar.close();
                    outputStream.close();
                } catch (IOException unused) {
                }
            } catch (Exception e) {
                r0.printStackTrace();
                if (qVar != null) {
                    try {
                        qVar.close();
                    } catch (IOException unused2) {
                        return;
                    }
                }
                outputStream.close();
            }
        } catch (Throwable th) {
            if (qVar != null) {
                try {
                    qVar.close();
                } catch (IOException unused3) {
                    throw th;
                }
            }
            outputStream.close();
            throw th;
        }
    }

    private static void handleW3cDocument(String str, String str2, x xVar) {
        xVar.a(a.a(Jsoup.parse(str)), str2);
    }

    private static void handleTextDirection(b.d dVar, x xVar) {
        if (dVar != null) {
            xVar.a(new b.a());
            xVar.a(new com.d.a.a.a());
            xVar.a(dVar);
        }
    }
}
