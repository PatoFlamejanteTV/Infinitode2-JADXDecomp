package com.d.h;

import com.d.h.f;
import com.d.h.t;
import com.d.h.w;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/h/u.class */
public final class u implements com.d.d.r {

    /* renamed from: a, reason: collision with root package name */
    private static float f1271a = 0.01f;

    /* renamed from: b, reason: collision with root package name */
    private com.d.a.a f1272b;

    public final void a(com.d.a.a aVar) {
        this.f1272b = aVar;
    }

    @Override // com.d.d.r
    public final void a(com.d.d.m mVar, String str, float f, float f2) {
        ((m) mVar).a(str, f, f2, (com.d.i.t) null);
    }

    @Override // com.d.d.r
    public final void a(com.d.d.m mVar, String str, float f, float f2, com.d.i.t tVar) {
        ((m) mVar).a(str, f, f2, tVar);
    }

    @Override // com.d.d.r
    public final com.d.i.l a(com.d.i.k kVar) {
        List<f.b> b2 = ((b) kVar).b();
        float a2 = kVar.a();
        c cVar = new c();
        float f = -3.4028235E38f;
        float f2 = -3.4028235E38f;
        float f3 = -3.4028235E38f;
        float f4 = -3.4028235E38f;
        float f5 = -3.4028235E38f;
        float f6 = -3.4028235E38f;
        Iterator<f.b> it = b2.iterator();
        while (it.hasNext()) {
            p e = it.next().e();
            if (e == null) {
                com.d.m.l.c("Font metrics not available. Probably a bug.");
            } else {
                float f7 = e.f1255a;
                float f8 = e.f1256b;
                float f9 = e.c;
                float f10 = e.d;
                float f11 = e.e;
                float f12 = e.f;
                if (f7 > f) {
                    f = f7;
                }
                if (f8 > f2) {
                    f2 = f8;
                }
                if (f9 > f3) {
                    f3 = f9;
                }
                if (f10 > f4) {
                    f4 = f10;
                }
                if (f11 > f5) {
                    f5 = f11;
                }
                if (f12 > f6) {
                    f6 = f12;
                }
            }
        }
        cVar.a((f / 1000.0f) * a2);
        cVar.b((f2 / 1000.0f) * a2);
        cVar.c((f3 / 1000.0f) * a2);
        if (f4 > 0.0f) {
            cVar.d((f4 / 1000.0f) * a2);
        } else {
            cVar.d(a2 / 12.0f);
        }
        cVar.e((f5 / 1000.0f) * a2);
        cVar.f((f6 / 1000.0f) * a2);
        return cVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/h/u$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        String f1273a;

        /* renamed from: b, reason: collision with root package name */
        f.b f1274b;

        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }
    }

    private static boolean a(int i) {
        return i == 32 || i == 160 || i == 12288;
    }

    private static a b(com.d.i.k kVar) {
        String x = com.d.m.i.a().a().x();
        List<f.b> b2 = ((b) kVar).b();
        for (f.b bVar : b2) {
            try {
                bVar.c().b(x);
                a aVar = new a((byte) 0);
                aVar.f1273a = x;
                aVar.f1274b = bVar;
                return aVar;
            } catch (Exception unused) {
            }
        }
        for (f.b bVar2 : b2) {
            try {
                bVar2.c().b(SequenceUtils.SPACE);
                a aVar2 = new a((byte) 0);
                aVar2.f1273a = SequenceUtils.SPACE;
                aVar2.f1274b = bVar2;
                return aVar2;
            } catch (Exception unused2) {
            }
        }
        com.d.m.l.d("Specified fonts don't contain a space character!");
        a aVar3 = new a((byte) 0);
        aVar3.f1273a = "";
        aVar3.f1274b = b2.get(0);
        return aVar3;
    }

    public static List<t.b> a(com.d.i.k kVar, String str, com.d.a.a aVar) {
        StringBuilder sb = new StringBuilder();
        a b2 = b(kVar);
        List<f.b> b3 = ((b) kVar).b();
        ArrayList arrayList = new ArrayList();
        t.b bVar = new t.b();
        int i = 0;
        while (i < str.length()) {
            int codePointAt = str.codePointAt(i);
            i += Character.charCount(codePointAt);
            String valueOf = String.valueOf(Character.toChars(codePointAt));
            boolean z = false;
            Iterator<f.b> it = b3.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                f.b next = it.next();
                try {
                    next.c().b(valueOf);
                    if (bVar.f1268b == null) {
                        bVar.f1268b = next;
                    } else if (next != bVar.f1268b) {
                        bVar.f1267a = sb.toString();
                        arrayList.add(bVar);
                        t.b bVar2 = new t.b();
                        bVar = bVar2;
                        bVar2.f1268b = next;
                        sb = new StringBuilder();
                    }
                    if (a(codePointAt)) {
                        bVar.c++;
                    } else {
                        bVar.d++;
                    }
                    sb.append(valueOf);
                    z = true;
                } catch (Exception unused) {
                    if (aVar.b()) {
                        String c = aVar.c(valueOf);
                        try {
                            next.c().b(c);
                            if (bVar.f1268b == null) {
                                bVar.f1268b = next;
                            } else if (next != bVar.f1268b) {
                                bVar.f1267a = sb.toString();
                                arrayList.add(bVar);
                                t.b bVar3 = new t.b();
                                bVar = bVar3;
                                bVar3.f1268b = next;
                                sb = new StringBuilder();
                            }
                            if (a(codePointAt)) {
                                bVar.c++;
                            } else {
                                bVar.d++;
                            }
                            sb.append(c);
                            z = true;
                        } catch (Exception unused2) {
                        }
                    }
                }
            }
            if (!z) {
                if (bVar.f1268b == null) {
                    bVar.f1268b = b2.f1274b;
                } else if (b2.f1274b != bVar.f1268b) {
                    bVar.f1267a = sb.toString();
                    arrayList.add(bVar);
                    t.b bVar4 = new t.b();
                    bVar = bVar4;
                    bVar4.f1268b = b2.f1274b;
                    sb = new StringBuilder();
                }
                if (Character.isSpaceChar(codePointAt) || Character.isWhitespace(codePointAt)) {
                    bVar.c++;
                    sb.append(' ');
                } else if (com.a.a.b.c.a.b(codePointAt)) {
                    bVar.d++;
                    sb.append(b2.f1273a);
                }
            }
        }
        if (sb.length() > 0) {
            bVar.f1267a = sb.toString();
            arrayList.add(bVar);
        }
        return arrayList;
    }

    private float b(com.d.i.k kVar, String str) {
        List<t.b> a2 = a(kVar, str, this.f1272b);
        float f = 0.0f;
        for (t.b bVar : a2) {
            try {
                f += bVar.f1268b.c().b(bVar.f1267a);
            } catch (Exception e) {
                com.d.m.l.g(Level.WARNING, "BUG. Font didn't contain expected character.", e);
            }
        }
        return f;
    }

    @Override // com.d.d.r
    public final int a(com.d.i.k kVar, String str) {
        float f = 0.0f;
        try {
            if (((b) kVar).b() == null || ((b) kVar).b().isEmpty()) {
                com.d.m.l.h(Level.WARNING, "Font list is empty.");
            } else {
                Iterator<f.b> it = ((b) kVar).b().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    f.b next = it.next();
                    if (next.c() != null) {
                        f = (next.c().b(str) / 1000.0f) * kVar.a();
                        break;
                    }
                    com.d.m.l.h(Level.WARNING, "Font is null.");
                }
            }
        } catch (IOException e) {
            throw new w.a("getWidth", (Exception) e);
        } catch (IllegalArgumentException unused) {
            f = (b(kVar, str) / 1000.0f) * kVar.a();
        }
        if (f - Math.floor(f) < f1271a) {
            return (int) f;
        }
        return (int) Math.ceil(f);
    }
}
