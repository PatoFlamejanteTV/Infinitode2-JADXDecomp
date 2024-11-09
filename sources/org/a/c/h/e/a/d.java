package org.a.c.h.e.a;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:org/a/c/h/e/a/d.class */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4520a = org.a.a.a.c.a(d.class);

    /* renamed from: b, reason: collision with root package name */
    private static final d f4521b = a("glyphlist.txt", 4281);
    private static final d c = a("zapfdingbats.txt", 201);
    private final Map<String, String> d;
    private final Map<String, String> e;
    private final Map<String, String> f = new ConcurrentHashMap();

    static {
        try {
            if (System.getProperty("glyphlist_ext") != null) {
                throw new UnsupportedOperationException("glyphlist_ext is no longer supported, use GlyphList.DEFAULT.addGlyphs(Properties) instead");
            }
        } catch (SecurityException unused) {
        }
    }

    private static d a(String str, int i) {
        try {
            return new d(d.class.getResourceAsStream("/org/apache/pdfbox/resources/glyphlist/" + str), i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static d a() {
        return f4521b;
    }

    public static d b() {
        return c;
    }

    private d(InputStream inputStream, int i) {
        this.d = new HashMap(i);
        this.e = new HashMap(i);
        a(inputStream);
    }

    private void a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
        while (bufferedReader.ready()) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null && !readLine.startsWith("#")) {
                    String[] split = readLine.split(";");
                    if (split.length < 2) {
                        throw new IOException("Invalid glyph list entry: " + readLine);
                    }
                    String str = split[0];
                    String[] split2 = split[1].split(SequenceUtils.SPACE);
                    if (this.d.containsKey(str)) {
                        new StringBuilder("duplicate value for ").append(str).append(" -> ").append(split[1]).append(SequenceUtils.SPACE).append(this.d.get(str));
                    }
                    int[] iArr = new int[split2.length];
                    int i = 0;
                    for (String str2 : split2) {
                        int i2 = i;
                        i++;
                        iArr[i2] = Integer.parseInt(str2, 16);
                    }
                    String str3 = new String(iArr, 0, iArr.length);
                    this.d.put(str, str3);
                    boolean z = k.c.a(str) || g.d.a(str) || e.c.a(str) || i.c.a(str) || l.c.a(str);
                    if (!this.e.containsKey(str3) || z) {
                        this.e.put(str3, str);
                    }
                }
            } finally {
                bufferedReader.close();
            }
        }
    }

    public final String a(int i) {
        String str = this.e.get(new String(new int[]{i}, 0, 1));
        if (str == null) {
            return ".notdef";
        }
        return str;
    }

    public final String a(String str) {
        if (str == null) {
            return null;
        }
        String str2 = this.d.get(str);
        if (str2 != null) {
            return str2;
        }
        String str3 = this.f.get(str);
        String str4 = str3;
        if (str3 == null) {
            if (str.indexOf(46) > 0) {
                str4 = a(str.substring(0, str.indexOf(46)));
            } else if (str.startsWith("uni") && str.length() == 7) {
                int length = str.length();
                StringBuilder sb = new StringBuilder();
                for (int i = 3; i + 4 <= length; i += 4) {
                    try {
                        int i2 = i;
                        int parseInt = Integer.parseInt(str.substring(i2, i2 + 4), 16);
                        if (parseInt > 55295 && parseInt < 57344) {
                            new StringBuilder("Unicode character name with disallowed code area: ").append(str);
                        } else {
                            sb.append((char) parseInt);
                        }
                    } catch (NumberFormatException unused) {
                        new StringBuilder("Not a number in Unicode character name: ").append(str);
                    }
                }
                str4 = sb.toString();
            } else if (str.startsWith(FlexmarkHtmlConverter.U_NODE) && str.length() == 5) {
                try {
                    int parseInt2 = Integer.parseInt(str.substring(1), 16);
                    if (parseInt2 > 55295 && parseInt2 < 57344) {
                        new StringBuilder("Unicode character name with disallowed code area: ").append(str);
                    } else {
                        str4 = String.valueOf((char) parseInt2);
                    }
                } catch (NumberFormatException unused2) {
                    new StringBuilder("Not a number in Unicode character name: ").append(str);
                }
            }
            if (str4 != null) {
                this.f.put(str, str4);
            }
        }
        return str4;
    }
}
