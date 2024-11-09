package org.a.c.h.e;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import org.a.b.f.al;
import org.a.b.f.ap;

/* loaded from: infinitode-2.jar:org/a/c/h/e/k.class */
final class k implements j {

    /* renamed from: a, reason: collision with root package name */
    private static final g f4542a = new g();

    /* renamed from: b, reason: collision with root package name */
    private n f4543b;
    private Map<String, i> c;
    private final ap d;
    private final Map<String, List<String>> e = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public k() {
        this.e.put("Courier", Arrays.asList("CourierNew", "CourierNewPSMT", "LiberationMono", "NimbusMonL-Regu"));
        this.e.put("Courier-Bold", Arrays.asList("CourierNewPS-BoldMT", "CourierNew-Bold", "LiberationMono-Bold", "NimbusMonL-Bold"));
        this.e.put("Courier-Oblique", Arrays.asList("CourierNewPS-ItalicMT", "CourierNew-Italic", "LiberationMono-Italic", "NimbusMonL-ReguObli"));
        this.e.put("Courier-BoldOblique", Arrays.asList("CourierNewPS-BoldItalicMT", "CourierNew-BoldItalic", "LiberationMono-BoldItalic", "NimbusMonL-BoldObli"));
        this.e.put("Helvetica", Arrays.asList("ArialMT", "Arial", "LiberationSans", "NimbusSanL-Regu"));
        this.e.put("Helvetica-Bold", Arrays.asList("Arial-BoldMT", "Arial-Bold", "LiberationSans-Bold", "NimbusSanL-Bold"));
        this.e.put("Helvetica-Oblique", Arrays.asList("Arial-ItalicMT", "Arial-Italic", "Helvetica-Italic", "LiberationSans-Italic", "NimbusSanL-ReguItal"));
        this.e.put("Helvetica-BoldOblique", Arrays.asList("Arial-BoldItalicMT", "Helvetica-BoldItalic", "LiberationSans-BoldItalic", "NimbusSanL-BoldItal"));
        this.e.put("Times-Roman", Arrays.asList("TimesNewRomanPSMT", "TimesNewRoman", "TimesNewRomanPS", "LiberationSerif", "NimbusRomNo9L-Regu"));
        this.e.put("Times-Bold", Arrays.asList("TimesNewRomanPS-BoldMT", "TimesNewRomanPS-Bold", "TimesNewRoman-Bold", "LiberationSerif-Bold", "NimbusRomNo9L-Medi"));
        this.e.put("Times-Italic", Arrays.asList("TimesNewRomanPS-ItalicMT", "TimesNewRomanPS-Italic", "TimesNewRoman-Italic", "LiberationSerif-Italic", "NimbusRomNo9L-ReguItal"));
        this.e.put("Times-BoldItalic", Arrays.asList("TimesNewRomanPS-BoldItalicMT", "TimesNewRomanPS-BoldItalic", "TimesNewRoman-BoldItalic", "LiberationSerif-BoldItalic", "NimbusRomNo9L-MediItal"));
        this.e.put("Symbol", Arrays.asList("Symbol", "SymbolMT", "StandardSymL"));
        this.e.put("ZapfDingbats", Arrays.asList("ZapfDingbatsITC", "Dingbats", "MS-Gothic"));
        for (String str : ah.a()) {
            if (!this.e.containsKey(str)) {
                this.e.put(str, b(ah.c(str)));
            }
        }
        try {
            InputStream resourceAsStream = j.class.getResourceAsStream("/org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
            if (resourceAsStream == null) {
                throw new IOException("Error loading resource: /org/apache/pdfbox/resources/ttf/LiberationSans-Regular.ttf");
            }
            this.d = new al().b(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/e/k$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final n f4544a = new d(k.f4542a);
    }

    private synchronized void a(n nVar) {
        this.c = a(nVar.a());
        this.f4543b = nVar;
    }

    private synchronized n b() {
        if (this.f4543b == null) {
            a(a.f4544a);
        }
        return this.f4543b;
    }

    private Map<String, i> a(List<? extends i> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (i iVar : list) {
            Iterator<String> it = a(iVar.a()).iterator();
            while (it.hasNext()) {
                linkedHashMap.put(it.next(), iVar);
            }
        }
        return linkedHashMap;
    }

    private static Set<String> a(String str) {
        HashSet hashSet = new HashSet();
        hashSet.add(str);
        hashSet.add(str.replaceAll("-", ""));
        return hashSet;
    }

    private List<String> b(String str) {
        return new ArrayList(this.e.get(str));
    }

    private List<String> c(String str) {
        List<String> list = this.e.get(str.replaceAll(SequenceUtils.SPACE, ""));
        if (list != null) {
            return list;
        }
        return Collections.emptyList();
    }

    private static String a(v vVar) {
        String str;
        if (vVar != null) {
            boolean z = false;
            if (vVar.g() != null) {
                String lowerCase = vVar.g().toLowerCase();
                z = lowerCase.contains("bold") || lowerCase.contains("black") || lowerCase.contains("heavy");
            }
            if (vVar.a()) {
                str = "Courier";
                if (z && vVar.d()) {
                    str = str + "-BoldOblique";
                } else if (z) {
                    str = str + "-Bold";
                } else if (vVar.d()) {
                    str = str + "-Oblique";
                }
            } else if (vVar.b()) {
                if (z && vVar.d()) {
                    str = "Times-BoldItalic";
                } else {
                    str = z ? "Times-Bold" : vVar.d() ? "Times-Italic" : "Times-Roman";
                }
            } else {
                str = "Helvetica";
                if (z && vVar.d()) {
                    str = str + "-BoldOblique";
                } else if (z) {
                    str = str + "-Bold";
                } else if (vVar.d()) {
                    str = str + "-Oblique";
                }
            }
        } else {
            str = "Times-Roman";
        }
        return str;
    }

    @Override // org.a.c.h.e.j
    public final m<ap> a(String str, v vVar) {
        ap apVar = (ap) a(h.TTF, str);
        if (apVar != null) {
            return new m<>(apVar, false);
        }
        ap apVar2 = (ap) a(h.TTF, a(vVar));
        ap apVar3 = apVar2;
        if (apVar2 == null) {
            apVar3 = this.d;
        }
        return new m<>(apVar3, true);
    }

    @Override // org.a.c.h.e.j
    public final m<org.a.b.b> b(String str, v vVar) {
        org.a.b.b d = d(str);
        if (d != null) {
            return new m<>(d, false);
        }
        org.a.b.b d2 = d(a(vVar));
        org.a.b.b bVar = d2;
        if (d2 == null) {
            bVar = this.d;
        }
        return new m<>(bVar, true);
    }

    private org.a.b.b d(String str) {
        org.a.b.g.d dVar = (org.a.b.g.d) a(h.PFB, str);
        if (dVar != null) {
            return dVar;
        }
        ap apVar = (ap) a(h.TTF, str);
        if (apVar != null) {
            return apVar;
        }
        org.a.b.f.ad adVar = (org.a.b.f.ad) a(h.OTF, str);
        if (adVar != null) {
            return adVar;
        }
        return null;
    }

    private org.a.b.b a(h hVar, String str) {
        if (str == null) {
            return null;
        }
        if (this.f4543b == null) {
            b();
        }
        i b2 = b(hVar, str);
        if (b2 != null) {
            return b2.d();
        }
        i b3 = b(hVar, str.replaceAll("-", ""));
        if (b3 != null) {
            return b3.d();
        }
        Iterator<String> it = c(str).iterator();
        while (it.hasNext()) {
            i b4 = b(hVar, it.next());
            if (b4 != null) {
                return b4.d();
            }
        }
        i b5 = b(hVar, str.replaceAll(",", "-"));
        if (b5 != null) {
            return b5.d();
        }
        i b6 = b(hVar, str + "-Regular");
        if (b6 != null) {
            return b6.d();
        }
        return null;
    }

    private i b(h hVar, String str) {
        if (str.contains("+")) {
            str = str.substring(str.indexOf(43) + 1);
        }
        i iVar = this.c.get(str);
        if (iVar != null && iVar.b() == hVar) {
            return iVar;
        }
        return null;
    }

    @Override // org.a.c.h.e.j
    public final org.a.c.h.e.a a(String str, v vVar, t tVar) {
        b poll;
        org.a.b.f.ad adVar = (org.a.b.f.ad) a(h.OTF, str);
        if (adVar != null) {
            return new org.a.c.h.e.a(adVar, null, false);
        }
        ap apVar = (ap) a(h.TTF, str);
        if (apVar != null) {
            return new org.a.c.h.e.a(null, apVar, false);
        }
        if (tVar != null) {
            String str2 = tVar.a() + "-" + tVar.b();
            if ((str2.equals("Adobe-GB1") || str2.equals("Adobe-CNS1") || str2.equals("Adobe-Japan1") || str2.equals("Adobe-Korea1")) && (poll = a(vVar, tVar).poll()) != null) {
                org.a.b.b d = poll.f4546b.d();
                if (d instanceof org.a.b.f.ad) {
                    return new org.a.c.h.e.a((org.a.b.f.ad) d, null, true);
                }
                if (d != null) {
                    return new org.a.c.h.e.a(null, d, true);
                }
            }
        }
        return new org.a.c.h.e.a(null, this.d, true);
    }

    private PriorityQueue<b> a(v vVar, t tVar) {
        PriorityQueue<b> priorityQueue = new PriorityQueue<>(20);
        for (i iVar : this.c.values()) {
            if (tVar == null || a(tVar, iVar)) {
                b bVar = new b(iVar);
                if (vVar.r() != null && iVar.j() != null) {
                    z a2 = vVar.r().a();
                    if (a2.a() == iVar.j().a()) {
                        if (a2.a() != 0 || ((!iVar.a().toLowerCase().contains("barcode") && !iVar.a().startsWith("Code")) || b(vVar))) {
                            if (a2.b() == iVar.j().b()) {
                                bVar.f4545a += 2.0d;
                            } else if (a2.b() >= 2 && a2.b() <= 5 && iVar.j().b() >= 2 && iVar.j().b() <= 5) {
                                bVar.f4545a += 1.0d;
                            } else if (a2.b() >= 11 && a2.b() <= 13 && iVar.j().b() >= 11 && iVar.j().b() <= 13) {
                                bVar.f4545a += 1.0d;
                            } else if (a2.b() != 0 && iVar.j().b() != 0) {
                                bVar.f4545a -= 1.0d;
                            }
                            int c = iVar.j().c();
                            int k = iVar.k();
                            if (Math.abs(c - k) > 2) {
                                c = k;
                            }
                            if (a2.c() == c) {
                                bVar.f4545a += 2.0d;
                            } else if (a2.c() > 1 && c > 1) {
                                bVar.f4545a += 1.0d - (Math.abs(a2.c() - c) * 0.5d);
                            }
                        }
                    }
                } else if (vVar.i() > 0.0f && iVar.f() > 0) {
                    bVar.f4545a += 1.0d - ((Math.abs(vVar.i() - iVar.f()) / 100.0f) * 0.5d);
                }
                priorityQueue.add(bVar);
            }
        }
        return priorityQueue;
    }

    private static boolean b(v vVar) {
        String h = vVar.h();
        String str = h;
        if (h == null) {
            str = "";
        }
        String g = vVar.g();
        String str2 = g;
        if (g == null) {
            str2 = "";
        }
        return str.startsWith("Code") || str.toLowerCase().contains("barcode") || str2.startsWith("Code") || str2.toLowerCase().contains("barcode");
    }

    private static boolean a(t tVar, i iVar) {
        if (iVar.c() != null) {
            return iVar.c().a().equals(tVar.a()) && iVar.c().b().equals(tVar.b());
        }
        long l = iVar.l();
        if (tVar.b().equals("GB1") && (l & 262144) == 262144) {
            return true;
        }
        if (tVar.b().equals("CNS1") && (l & 1048576) == 1048576) {
            return true;
        }
        if (tVar.b().equals("Japan1") && (l & 131072) == 131072) {
            return true;
        }
        return (tVar.b().equals("Korea1") && (l & 524288) == 524288) || (l & 2097152) == 2097152;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/e/k$b.class */
    public static class b implements Comparable<b> {

        /* renamed from: a, reason: collision with root package name */
        double f4545a;

        /* renamed from: b, reason: collision with root package name */
        final i f4546b;

        b(i iVar) {
            this.f4546b = iVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.lang.Comparable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            return Double.compare(bVar.f4545a, this.f4545a);
        }
    }
}
