package com.a.a.c.l;

import java.io.Serializable;
import java.lang.reflect.TypeVariable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/l/n.class */
public final class n implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f659a = new String[0];

    /* renamed from: b, reason: collision with root package name */
    private static final com.a.a.c.j[] f660b = new com.a.a.c.j[0];
    private static final n c = new n(f659a, f660b, null);
    private final String[] d;
    private final com.a.a.c.j[] e;
    private final String[] f;
    private final int g;

    private n(String[] strArr, com.a.a.c.j[] jVarArr, String[] strArr2) {
        this.d = strArr == null ? f659a : strArr;
        this.e = jVarArr == null ? f660b : jVarArr;
        if (this.d.length != this.e.length) {
            throw new IllegalArgumentException("Mismatching names (" + this.d.length + "), types (" + this.e.length + ")");
        }
        int i = 1;
        int length = this.e.length;
        for (int i2 = 0; i2 < length; i2++) {
            i += this.e[i2].hashCode();
        }
        this.f = strArr2;
        this.g = i;
    }

    public static n a() {
        return c;
    }

    public static n a(Class<?> cls, List<com.a.a.c.j> list) {
        return a(cls, (list == null || list.isEmpty()) ? f660b : (com.a.a.c.j[]) list.toArray(f660b));
    }

    public static n a(Class<?> cls, com.a.a.c.j[] jVarArr) {
        String[] strArr;
        if (jVarArr == null) {
            jVarArr = f660b;
        } else {
            switch (jVarArr.length) {
                case 1:
                    return a(cls, jVarArr[0]);
                case 2:
                    return a(cls, jVarArr[0], jVarArr[1]);
            }
        }
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        if (typeParameters == null || typeParameters.length == 0) {
            strArr = f659a;
        } else {
            int length = typeParameters.length;
            strArr = new String[length];
            for (int i = 0; i < length; i++) {
                strArr[i] = typeParameters[i].getName();
            }
        }
        if (strArr.length != jVarArr.length) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with " + jVarArr.length + " type parameter" + (jVarArr.length == 1 ? "" : "s") + ": class expects " + strArr.length);
        }
        return new n(strArr, jVarArr, null);
    }

    public static n a(Class<?> cls, com.a.a.c.j jVar) {
        TypeVariable<?>[] a2 = b.a(cls);
        int length = a2 == null ? 0 : a2.length;
        int i = length;
        if (length != 1) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with 1 type parameter: class expects " + i);
        }
        return new n(new String[]{a2[0].getName()}, new com.a.a.c.j[]{jVar}, null);
    }

    public static n a(Class<?> cls, com.a.a.c.j jVar, com.a.a.c.j jVar2) {
        TypeVariable<?>[] b2 = b.b(cls);
        int length = b2 == null ? 0 : b2.length;
        int i = length;
        if (length != 2) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with 2 type parameters: class expects " + i);
        }
        return new n(new String[]{b2[0].getName(), b2[1].getName()}, new com.a.a.c.j[]{jVar, jVar2}, null);
    }

    public static n a(List<String> list, List<com.a.a.c.j> list2) {
        if (list.isEmpty() || list2.isEmpty()) {
            return c;
        }
        return new n((String[]) list.toArray(f659a), (com.a.a.c.j[]) list2.toArray(f660b), null);
    }

    public static n b(Class<?> cls, com.a.a.c.j jVar) {
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        int length = typeParameters == null ? 0 : typeParameters.length;
        int i = length;
        if (length == 0) {
            return c;
        }
        if (i != 1) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with 1 type parameter: class expects " + i);
        }
        return new n(new String[]{typeParameters[0].getName()}, new com.a.a.c.j[]{jVar}, null);
    }

    public static n b(Class<?> cls, com.a.a.c.j[] jVarArr) {
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        if (typeParameters == null || typeParameters.length == 0) {
            return c;
        }
        int length = typeParameters.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = typeParameters[i].getName();
        }
        if (length != 2) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with 2 type parameters: class expects " + length);
        }
        return new n(strArr, jVarArr, null);
    }

    public final n a(String str) {
        int length = this.f == null ? 0 : this.f.length;
        int i = length;
        String[] strArr = length == 0 ? new String[1] : (String[]) Arrays.copyOf(this.f, i + 1);
        strArr[i] = str;
        return new n(this.d, this.e, strArr);
    }

    public final com.a.a.c.j b(String str) {
        com.a.a.c.j H;
        int length = this.d.length;
        for (int i = 0; i < length; i++) {
            if (str.equals(this.d[i])) {
                com.a.a.c.j jVar = this.e[i];
                com.a.a.c.j jVar2 = jVar;
                if ((jVar instanceof k) && (H = ((k) jVar2).H()) != null) {
                    jVar2 = H;
                }
                return jVar2;
            }
        }
        return null;
    }

    public final boolean b() {
        return this.e.length == 0;
    }

    public final int c() {
        return this.e.length;
    }

    public final com.a.a.c.j a(int i) {
        if (i < 0 || i >= this.e.length) {
            return null;
        }
        return this.e[i];
    }

    public final List<com.a.a.c.j> d() {
        if (this.e.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.asList(this.e);
    }

    public final boolean c(String str) {
        if (this.f != null) {
            int length = this.f.length;
            do {
                length--;
                if (length < 0) {
                    return false;
                }
            } while (!str.equals(this.f[length]));
            return true;
        }
        return false;
    }

    public final Object a(Class<?> cls) {
        return new a(cls, this.e, this.g);
    }

    public final String toString() {
        if (this.e.length == 0) {
            return "<>";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        int length = this.e.length;
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(this.e[i].D());
        }
        sb.append('>');
        return sb.toString();
    }

    public final int hashCode() {
        return this.g;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!com.a.a.c.m.i.a(obj, getClass())) {
            return false;
        }
        n nVar = (n) obj;
        int length = this.e.length;
        if (length != nVar.c()) {
            return false;
        }
        com.a.a.c.j[] jVarArr = nVar.e;
        for (int i = 0; i < length; i++) {
            if (!jVarArr[i].equals(this.e[i])) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.j[] e() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/l/n$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final TypeVariable<?>[] f663a = AbstractList.class.getTypeParameters();

        /* renamed from: b, reason: collision with root package name */
        private static final TypeVariable<?>[] f664b = Collection.class.getTypeParameters();
        private static final TypeVariable<?>[] c = Iterable.class.getTypeParameters();
        private static final TypeVariable<?>[] d = List.class.getTypeParameters();
        private static final TypeVariable<?>[] e = ArrayList.class.getTypeParameters();
        private static final TypeVariable<?>[] f = Map.class.getTypeParameters();
        private static final TypeVariable<?>[] g = HashMap.class.getTypeParameters();
        private static final TypeVariable<?>[] h = LinkedHashMap.class.getTypeParameters();

        public static TypeVariable<?>[] a(Class<?> cls) {
            if (cls == Collection.class) {
                return f664b;
            }
            if (cls == List.class) {
                return d;
            }
            if (cls == ArrayList.class) {
                return e;
            }
            if (cls == AbstractList.class) {
                return f663a;
            }
            if (cls == Iterable.class) {
                return c;
            }
            return cls.getTypeParameters();
        }

        public static TypeVariable<?>[] b(Class<?> cls) {
            if (cls == Map.class) {
                return f;
            }
            if (cls == HashMap.class) {
                return g;
            }
            if (cls == LinkedHashMap.class) {
                return h;
            }
            return cls.getTypeParameters();
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/l/n$a.class */
    static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final Class<?> f661a;

        /* renamed from: b, reason: collision with root package name */
        private final com.a.a.c.j[] f662b;
        private final int c;

        public a(Class<?> cls, com.a.a.c.j[] jVarArr, int i) {
            this.f661a = cls;
            this.f662b = jVarArr;
            this.c = i;
        }

        public final int hashCode() {
            return this.c;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (this.c == aVar.c && this.f661a == aVar.f661a) {
                com.a.a.c.j[] jVarArr = aVar.f662b;
                int length = this.f662b.length;
                if (length == jVarArr.length) {
                    for (int i = 0; i < length; i++) {
                        if (!this.f662b[i].equals(jVarArr[i])) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        }

        public final String toString() {
            return this.f661a.getName() + "<>";
        }
    }
}
