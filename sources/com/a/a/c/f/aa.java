package com.a.a.c.f;

import com.a.a.c.f.an;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/a/a/c/f/aa.class */
public class aa implements com.a.a.c.m.b {

    /* renamed from: a, reason: collision with root package name */
    private HashMap<Class<?>, Annotation> f425a;

    public static an a(Method method, com.a.a.c.j jVar, com.a.a.c.l.o oVar, an anVar) {
        com.a.a.c.l.n a2 = a(method, jVar, anVar);
        return a2 == null ? anVar : new an.a(oVar, a2);
    }

    private static com.a.a.c.l.n a(Method method, com.a.a.c.j jVar, an anVar) {
        com.a.a.c.j a2;
        TypeVariable<?> a3;
        TypeVariable<Method>[] typeParameters = method.getTypeParameters();
        if (typeParameters.length == 0 || jVar.x().b()) {
            return null;
        }
        Type genericReturnType = method.getGenericReturnType();
        if (!(genericReturnType instanceof ParameterizedType)) {
            return null;
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
        if (!Objects.equals(jVar.b(), parameterizedType.getRawType())) {
            return null;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        ArrayList arrayList = new ArrayList(typeParameters.length);
        ArrayList arrayList2 = new ArrayList(typeParameters.length);
        for (int i = 0; i < actualTypeArguments.length; i++) {
            TypeVariable<?> a4 = a(actualTypeArguments[i]);
            if (a4 != null) {
                String name = a4.getName();
                if (name == null || (a2 = jVar.x().a(i)) == null || (a3 = a(typeParameters, name)) == null) {
                    return null;
                }
                if (a(anVar, a2, a3.getBounds())) {
                    int indexOf = arrayList.indexOf(name);
                    if (indexOf != -1) {
                        com.a.a.c.j jVar2 = (com.a.a.c.j) arrayList2.get(indexOf);
                        if (a2.equals(jVar2)) {
                            continue;
                        } else {
                            boolean b2 = jVar2.b(a2.b());
                            boolean b3 = a2.b(jVar2.b());
                            if (!b2 && !b3) {
                                return null;
                            }
                            if ((b2 ^ b3) && b3) {
                                arrayList2.set(indexOf, a2);
                            }
                        }
                    } else {
                        arrayList.add(name);
                        arrayList2.add(a2);
                    }
                } else {
                    continue;
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return com.a.a.c.l.n.a(arrayList, arrayList2);
    }

    private static TypeVariable<?> a(Type type) {
        if (type instanceof TypeVariable) {
            return (TypeVariable) type;
        }
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            if (wildcardType.getLowerBounds().length != 0) {
                return null;
            }
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (upperBounds.length == 1) {
                return a(upperBounds[0]);
            }
            return null;
        }
        return null;
    }

    private static ParameterizedType b(Type type) {
        if (type instanceof ParameterizedType) {
            return (ParameterizedType) type;
        }
        if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            if (wildcardType.getLowerBounds().length != 0) {
                return null;
            }
            Type[] upperBounds = wildcardType.getUpperBounds();
            if (upperBounds.length == 1) {
                return b(upperBounds[0]);
            }
            return null;
        }
        return null;
    }

    private static boolean a(an anVar, com.a.a.c.j jVar, Type[] typeArr) {
        for (Type type : typeArr) {
            if (!a(anVar, jVar, type)) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(an anVar, com.a.a.c.j jVar, Type type) {
        if (!jVar.b(anVar.a(type).b())) {
            return false;
        }
        ParameterizedType b2 = b(type);
        if (b2 != null && Objects.equals(jVar.b(), b2.getRawType())) {
            Type[] actualTypeArguments = b2.getActualTypeArguments();
            com.a.a.c.l.n x = jVar.x();
            if (x.c() != actualTypeArguments.length) {
                return false;
            }
            for (int i = 0; i < x.c(); i++) {
                if (!a(anVar, x.a(i), actualTypeArguments[i])) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    private static TypeVariable<?> a(TypeVariable<?>[] typeVariableArr, String str) {
        if (typeVariableArr == null || str == null) {
            return null;
        }
        for (TypeVariable<?> typeVariable : typeVariableArr) {
            if (str.equals(typeVariable.getName())) {
                return typeVariable;
            }
        }
        return null;
    }

    public aa() {
    }

    public static aa a(Class<?> cls, Annotation annotation) {
        HashMap hashMap = new HashMap(4);
        hashMap.put(cls, annotation);
        return new aa(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public aa(HashMap<Class<?>, Annotation> hashMap) {
        this.f425a = hashMap;
    }

    @Override // com.a.a.c.m.b
    public <A extends Annotation> A a(Class<A> cls) {
        if (this.f425a == null) {
            return null;
        }
        return (A) this.f425a.get(cls);
    }

    @Override // com.a.a.c.m.b
    public boolean b(Class<?> cls) {
        if (this.f425a == null) {
            return false;
        }
        return this.f425a.containsKey(cls);
    }

    @Override // com.a.a.c.m.b
    public boolean a(Class<? extends Annotation>[] clsArr) {
        if (this.f425a != null) {
            for (Class<? extends Annotation> cls : clsArr) {
                if (this.f425a.containsKey(cls)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static aa a(aa aaVar, aa aaVar2) {
        if (aaVar == null || aaVar.f425a == null || aaVar.f425a.isEmpty()) {
            return aaVar2;
        }
        if (aaVar2 == null || aaVar2.f425a == null || aaVar2.f425a.isEmpty()) {
            return aaVar;
        }
        HashMap hashMap = new HashMap();
        for (Annotation annotation : aaVar2.f425a.values()) {
            hashMap.put(annotation.annotationType(), annotation);
        }
        for (Annotation annotation2 : aaVar.f425a.values()) {
            hashMap.put(annotation2.annotationType(), annotation2);
        }
        return new aa(hashMap);
    }

    @Override // com.a.a.c.m.b
    public int a() {
        if (this.f425a == null) {
            return 0;
        }
        return this.f425a.size();
    }

    public boolean a(Annotation annotation) {
        return b(annotation);
    }

    public String toString() {
        if (this.f425a == null) {
            return "[null]";
        }
        return this.f425a.toString();
    }

    protected boolean b(Annotation annotation) {
        if (this.f425a == null) {
            this.f425a = new HashMap<>();
        }
        Annotation put = this.f425a.put(annotation.annotationType(), annotation);
        return put == null || !put.equals(annotation);
    }
}
