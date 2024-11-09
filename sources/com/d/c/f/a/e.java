package com.d.c.f.a;

import com.d.c.d.j;
import com.d.m.l;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/c/f/a/e.class */
public final class e extends com.d.c.f.e {

    /* renamed from: a, reason: collision with root package name */
    private float f1078a;

    /* renamed from: b, reason: collision with root package name */
    private com.d.c.f.c f1079b;
    private short c;

    public e(com.d.c.f.c cVar, com.d.c.a.a aVar, j jVar) {
        super(aVar, jVar.a(), jVar.d(), jVar.d());
        this.f1079b = cVar;
        this.f1078a = jVar.f();
        this.c = jVar.a();
    }

    @Override // com.d.c.f.e, com.d.c.f.g
    public final float b() {
        return this.f1078a;
    }

    @Override // com.d.c.f.e, com.d.c.f.g
    public final float a(com.d.c.a.a aVar, float f, com.d.c.f.d dVar) {
        return a(j(), aVar, a(), this.f1078a, this.c, f, dVar);
    }

    @Override // com.d.c.f.e, com.d.c.f.g
    public final boolean g() {
        return com.d.c.a.f.b(i());
    }

    public static float a(com.d.c.f.c cVar, com.d.c.a.a aVar, String str, float f, short s, float f2, com.d.c.f.d dVar) {
        float b2;
        float f3 = Float.MIN_VALUE;
        switch (s) {
            case 1:
                f3 = f;
                break;
            case 2:
                if (aVar == com.d.c.a.a.as) {
                    f2 = cVar.a().c(dVar);
                } else if (aVar == com.d.c.a.a.M) {
                    f2 = dVar.a(cVar.a().b(dVar));
                } else if (aVar == com.d.c.a.a.N) {
                    f2 = dVar.a(cVar.b(dVar));
                }
                f3 = (f / 100.0f) * f2;
                break;
            case 3:
                if (aVar == com.d.c.a.a.M) {
                    f3 = f * cVar.a().b(dVar).f1094a;
                    break;
                } else {
                    f3 = f * cVar.b(dVar).f1094a;
                    break;
                }
            case 4:
                if (aVar == com.d.c.a.a.M) {
                    b2 = dVar.b(cVar.a().b(dVar));
                } else {
                    b2 = dVar.b(cVar.b(dVar));
                }
                f3 = f * b2;
                break;
            case 5:
                f3 = f * dVar.b();
                break;
            case 6:
                f3 = (f * 10.0f) / dVar.a();
                break;
            case 7:
                f3 = f / dVar.a();
                break;
            case 8:
                f3 = ((f * 2.54f) * 10.0f) / dVar.a();
                break;
            case 9:
                f3 = (((f * 0.013888889f) * 2.54f) * 10.0f) / dVar.a();
                break;
            case 10:
                f3 = ((((f * 12.0f) * 0.013888889f) * 2.54f) * 10.0f) / dVar.a();
                break;
            default:
                l.c(Level.WARNING, "Asked to convert " + aVar + " from relative to absolute,  don't recognize the datatype '" + com.d.c.a.f.a(s) + "' " + ((int) s) + "(" + str + ")");
                break;
        }
        if (l.b()) {
            if (aVar == com.d.c.a.a.M) {
                l.c(Level.FINEST, aVar + ", relative= " + f + " (" + str + "), absolute= " + f3);
            } else {
                l.c(Level.FINEST, aVar + ", relative= " + f + " (" + str + "), absolute= " + f3 + " using base=" + f2);
            }
        }
        return (float) Math.round(f3);
    }

    private com.d.c.f.c j() {
        return this.f1079b;
    }
}
