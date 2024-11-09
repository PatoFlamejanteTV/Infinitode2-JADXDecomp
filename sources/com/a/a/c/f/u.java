package com.a.a.c.f;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/* loaded from: infinitode-2.jar:com/a/a/c/f/u.class */
class u {

    /* renamed from: a, reason: collision with root package name */
    protected static final aa[] f473a = new aa[0];

    /* renamed from: b, reason: collision with root package name */
    protected static final Annotation[] f474b = new Annotation[0];
    protected final com.a.a.c.a c;

    /* JADX INFO: Access modifiers changed from: protected */
    public u(com.a.a.c.a aVar) {
        this.c = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final p a(Annotation[] annotationArr) {
        p b2 = p.b();
        for (Annotation annotation : annotationArr) {
            b2 = b2.b(annotation);
            if (this.c.a(annotation)) {
                b2 = a(b2, annotation);
            }
        }
        return b2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final p a(p pVar, Annotation[] annotationArr) {
        for (Annotation annotation : annotationArr) {
            pVar = pVar.b(annotation);
            if (this.c.a(annotation)) {
                pVar = a(pVar, annotation);
            }
        }
        return pVar;
    }

    private p a(p pVar, Annotation annotation) {
        for (Annotation annotation2 : com.a.a.c.m.i.o(annotation.annotationType())) {
            if (!a(annotation2)) {
                if (this.c.a(annotation2)) {
                    if (!pVar.a(annotation2)) {
                        pVar = a(pVar.b(annotation2), annotation2);
                    }
                } else {
                    pVar = pVar.b(annotation2);
                }
            }
        }
        return pVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final p b(p pVar, Annotation[] annotationArr) {
        for (Annotation annotation : annotationArr) {
            if (!pVar.a(annotation)) {
                pVar = pVar.b(annotation);
                if (this.c.a(annotation)) {
                    pVar = b(pVar, annotation);
                }
            }
        }
        return pVar;
    }

    private p b(p pVar, Annotation annotation) {
        for (Annotation annotation2 : com.a.a.c.m.i.o(annotation.annotationType())) {
            if (!a(annotation2) && !pVar.a(annotation2)) {
                pVar = pVar.b(annotation2);
                if (this.c.a(annotation2)) {
                    pVar = a(pVar, annotation2);
                }
            }
        }
        return pVar;
    }

    private static boolean a(Annotation annotation) {
        return (annotation instanceof Target) || (annotation instanceof Retention);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static aa a() {
        return new aa();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static aa[] a(int i) {
        if (i == 0) {
            return f473a;
        }
        aa[] aaVarArr = new aa[i];
        for (int i2 = 0; i2 < i; i2++) {
            aaVarArr[i2] = a();
        }
        return aaVarArr;
    }
}
