package com.a.a.c.f;

import com.a.a.c.f.d;
import com.a.a.c.m.i;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/a/a/c/f/g.class */
public final class g extends u {
    private final an d;
    private final boolean e;
    private f f;

    private g(com.a.a.c.a aVar, an anVar, boolean z) {
        super(aVar);
        this.d = anVar;
        this.e = z;
    }

    public static d.a a(com.a.a.c.a aVar, com.a.a.c.l.o oVar, an anVar, com.a.a.c.j jVar, Class<?> cls, boolean z) {
        return new g(aVar, anVar, z | (cls != null)).a(oVar, jVar, cls);
    }

    private d.a a(com.a.a.c.l.o oVar, com.a.a.c.j jVar, Class<?> cls) {
        List<f> a2 = a(jVar, cls);
        List<k> b2 = b(oVar, jVar, cls);
        if (this.e) {
            if (this.f != null && this.c.d((j) this.f)) {
                this.f = null;
            }
            int size = a2.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                if (this.c.d((j) a2.get(size))) {
                    a2.remove(size);
                }
            }
            int size2 = b2.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    break;
                }
                if (this.c.d((j) b2.get(size2))) {
                    b2.remove(size2);
                }
            }
        }
        return new d.a(this.f, a2, b2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v49, types: [java.util.List] */
    private List<f> a(com.a.a.c.j jVar, Class<?> cls) {
        int size;
        ArrayList arrayList;
        i.a aVar = null;
        ArrayList arrayList2 = null;
        if (!jVar.h()) {
            for (i.a aVar2 : com.a.a.c.m.i.q(jVar.b())) {
                if (a(aVar2.a())) {
                    if (aVar2.b() == 0) {
                        aVar = aVar2;
                    } else {
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        arrayList2.add(aVar2);
                    }
                }
            }
        }
        if (arrayList2 == null) {
            arrayList = Collections.emptyList();
            if (aVar == null) {
                return arrayList;
            }
            size = 0;
        } else {
            size = arrayList2.size();
            arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                arrayList.add(null);
            }
        }
        if (cls != null) {
            z[] zVarArr = null;
            i.a[] q = com.a.a.c.m.i.q(cls);
            for (i.a aVar3 : q) {
                if (aVar3.b() == 0) {
                    if (aVar != null) {
                        this.f = a(aVar, aVar3);
                        aVar = null;
                    }
                } else if (arrayList2 != null) {
                    if (zVarArr == null) {
                        zVarArr = new z[size];
                        for (int i2 = 0; i2 < size; i2++) {
                            zVarArr[i2] = new z(((i.a) arrayList2.get(i2)).a());
                        }
                    }
                    z zVar = new z(aVar3.a());
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size) {
                            break;
                        }
                        if (!zVar.equals(zVarArr[i3])) {
                            i3++;
                        } else {
                            arrayList.set(i3, b((i.a) arrayList2.get(i3), aVar3));
                            break;
                        }
                    }
                }
            }
        }
        if (aVar != null) {
            this.f = a(aVar, (i.a) null);
        }
        for (int i4 = 0; i4 < size; i4++) {
            if (((f) arrayList.get(i4)) == null) {
                arrayList.set(i4, b((i.a) arrayList2.get(i4), (i.a) null));
            }
        }
        return arrayList;
    }

    private List<k> b(com.a.a.c.l.o oVar, com.a.a.c.j jVar, Class<?> cls) {
        ArrayList arrayList = null;
        for (Method method : com.a.a.c.m.i.p(jVar.b())) {
            if (a(method)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(method);
            }
        }
        if (arrayList == null) {
            return Collections.emptyList();
        }
        an anVar = this.d;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList2.add(null);
        }
        if (cls != null) {
            z[] zVarArr = null;
            for (Method method2 : cls.getDeclaredMethods()) {
                if (a(method2)) {
                    if (zVarArr == null) {
                        zVarArr = new z[size];
                        for (int i2 = 0; i2 < size; i2++) {
                            zVarArr[i2] = new z((Method) arrayList.get(i2));
                        }
                    }
                    z zVar = new z(method2);
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size) {
                            break;
                        }
                        if (!zVar.equals(zVarArr[i3])) {
                            i3++;
                        } else {
                            arrayList2.set(i3, a((Method) arrayList.get(i3), anVar, method2));
                            break;
                        }
                    }
                }
            }
        }
        for (int i4 = 0; i4 < size; i4++) {
            if (((k) arrayList2.get(i4)) == null) {
                Method method3 = (Method) arrayList.get(i4);
                arrayList2.set(i4, a(method3, aa.a(method3, jVar, oVar, anVar), (Method) null));
            }
        }
        return arrayList2;
    }

    private static boolean a(Method method) {
        return Modifier.isStatic(method.getModifiers()) && !method.isSynthetic();
    }

    private f a(i.a aVar, i.a aVar2) {
        return new f(this.d, aVar.a(), c(aVar, aVar2), f473a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v21, types: [java.lang.annotation.Annotation[]] */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.lang.annotation.Annotation[]] */
    private f b(i.a aVar, i.a aVar2) {
        aa[] a2;
        int b2 = aVar.b();
        if (this.c == null) {
            return new f(this.d, aVar.a(), a(), a(b2));
        }
        if (b2 == 0) {
            return new f(this.d, aVar.a(), c(aVar, aVar2), f473a);
        }
        Annotation[][] e = aVar.e();
        if (b2 != e.length) {
            a2 = null;
            Class<?> c = aVar.c();
            if (!com.a.a.c.m.i.k(c) || b2 != e.length + 2) {
                if (c.isMemberClass() && b2 == e.length + 1) {
                    e = new Annotation[e.length + 1];
                    System.arraycopy(e, 0, e, 1, e.length);
                    e[0] = f474b;
                    a2 = a(e, (Annotation[][]) null);
                }
            } else {
                e = new Annotation[e.length + 2];
                System.arraycopy(e, 0, e, 2, e.length);
                a2 = a(e, (Annotation[][]) null);
            }
            if (a2 == null) {
                throw new IllegalStateException(String.format("Internal error: constructor for %s has mismatch: %d parameters; %d sets of annotations", aVar.c().getName(), Integer.valueOf(b2), Integer.valueOf(e.length)));
            }
        } else {
            a2 = a(e, aVar2 == null ? (Annotation[][]) null : aVar2.e());
        }
        return new f(this.d, aVar.a(), c(aVar, aVar2), a2);
    }

    private k a(Method method, an anVar, Method method2) {
        int parameterCount = method.getParameterCount();
        if (this.c == null) {
            return new k(anVar, method, a(), a(parameterCount));
        }
        if (parameterCount == 0) {
            return new k(anVar, method, a(method, method2), f473a);
        }
        return new k(anVar, method, a(method, method2), a(method.getParameterAnnotations(), method2 == null ? (Annotation[][]) null : method2.getParameterAnnotations()));
    }

    private aa[] a(Annotation[][] annotationArr, Annotation[][] annotationArr2) {
        if (this.e) {
            int length = annotationArr.length;
            aa[] aaVarArr = new aa[length];
            for (int i = 0; i < length; i++) {
                p a2 = a(p.b(), annotationArr[i]);
                if (annotationArr2 != null) {
                    a2 = a(a2, annotationArr2[i]);
                }
                aaVarArr[i] = a2.d();
            }
            return aaVarArr;
        }
        return f473a;
    }

    private aa c(i.a aVar, i.a aVar2) {
        if (this.e) {
            p a2 = a(aVar.d());
            if (aVar2 != null) {
                a2 = a(a2, aVar2.d());
            }
            return a2.d();
        }
        return a();
    }

    private final aa a(AnnotatedElement annotatedElement, AnnotatedElement annotatedElement2) {
        p a2 = a(annotatedElement.getDeclaredAnnotations());
        if (annotatedElement2 != null) {
            a2 = a(a2, annotatedElement2.getDeclaredAnnotations());
        }
        return a2.d();
    }

    private static boolean a(Constructor<?> constructor) {
        return !constructor.isSynthetic();
    }
}
