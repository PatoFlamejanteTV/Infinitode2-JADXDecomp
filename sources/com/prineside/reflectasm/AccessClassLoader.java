package com.prineside.reflectasm;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.logging.TLog;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.HashSet;
import java.util.WeakHashMap;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.PackageDescription;
import net.bytebuddy.description.type.RecordComponentDescription;
import net.bytebuddy.description.type.RecordComponentList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

/* loaded from: infinitode-2.jar:com/prineside/reflectasm/AccessClassLoader.class */
class AccessClassLoader extends ClassLoader {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1647a = TLog.forClass(AccessClassLoader.class);

    /* renamed from: b, reason: collision with root package name */
    private static final WeakHashMap<ClassLoader, WeakReference<AccessClassLoader>> f1648b = new WeakHashMap<>();
    private static final ClassLoader c = b(AccessClassLoader.class);
    private static volatile AccessClassLoader d = new AccessClassLoader(c);
    private static volatile Method e;
    private final HashSet<String> f;

    private AccessClassLoader(ClassLoader classLoader) {
        super(classLoader);
        this.f = new HashSet<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Class a(String str) {
        if (this.f.contains(str)) {
            try {
                return loadClass(str, false);
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException(e2);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Class a(String str, byte[] bArr) {
        this.f.add(str);
        return b(str, bArr);
    }

    @Override // java.lang.ClassLoader
    protected Class<?> loadClass(String str, boolean z) {
        return str.equals(FieldAccess.class.getName()) ? FieldAccess.class : str.equals(MethodAccess.class.getName()) ? MethodAccess.class : str.equals(ConstructorAccess.class.getName()) ? ConstructorAccess.class : str.equals(PublicConstructorAccess.class.getName()) ? PublicConstructorAccess.class : super.loadClass(str, z);
    }

    private Class<?> b(final String str, byte[] bArr) {
        try {
            return (Class) a().invoke(getParent(), str, bArr, 0, Integer.valueOf(bArr.length), getClass().getProtectionDomain());
        } catch (Exception unused) {
            try {
                return defineClass(str, bArr, 0, bArr.length, getClass().getProtectionDomain());
            } catch (Exception e2) {
                f1647a.e("failed to define class in a regular way, using ByteBuddy ClassLoadingStrategy", new Object[0]);
                ClassLoadingStrategy byteBuddyClassLoadingStrategy = Game.i.actionResolver.getByteBuddyClassLoadingStrategy();
                if (byteBuddyClassLoadingStrategy == null) {
                    throw new IllegalStateException("Failed to define class in a regular way and no ClassLoadingStrategy provided by ActionResolver", e2);
                }
                HashMap hashMap = new HashMap();
                TypeDescription.AbstractBase.OfSimpleType ofSimpleType = new TypeDescription.AbstractBase.OfSimpleType(this) { // from class: com.prineside.reflectasm.AccessClassLoader.1
                    @Override // net.bytebuddy.description.ModifierReviewable
                    public int getModifiers() {
                        return 0;
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.TypeVariableSource
                    public TypeList.Generic getTypeVariables() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDefinition
                    public TypeDescription.Generic getSuperClass() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDefinition
                    public TypeList.Generic getInterfaces() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
                    public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
                    public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDefinition
                    public RecordComponentList<RecordComponentDescription.InDefinedShape> getRecordComponents() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDefinition
                    public boolean isRecord() {
                        return false;
                    }

                    @Override // net.bytebuddy.description.DeclaredByType
                    public TypeDescription getDeclaringType() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription
                    public TypeList getDeclaredTypes() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription
                    public MethodDescription.InDefinedShape getEnclosingMethod() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription
                    public TypeDescription getEnclosingType() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription
                    public boolean isAnonymousType() {
                        return false;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription
                    public boolean isLocalType() {
                        return false;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription
                    public PackageDescription getPackage() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription
                    public TypeDescription getNestHost() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription
                    public TypeList getNestMembers() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.type.TypeDescription
                    public TypeList getPermittedSubtypes() {
                        return null;
                    }

                    @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                    public String getName() {
                        return str;
                    }
                };
                hashMap.put(ofSimpleType, bArr);
                Class<?> cls = byteBuddyClassLoadingStrategy.load(AccessClassLoader.class.getClassLoader(), hashMap).get(ofSimpleType);
                f1647a.i("created new class: " + cls, new Object[0]);
                return cls;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Class cls, Class cls2) {
        if (cls.getPackage() != cls2.getPackage()) {
            return false;
        }
        ClassLoader classLoader = cls.getClassLoader();
        ClassLoader classLoader2 = cls2.getClassLoader();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        return classLoader == null ? classLoader2 == null || classLoader2 == systemClassLoader : classLoader2 == null ? classLoader == systemClassLoader : classLoader == classLoader2;
    }

    private static ClassLoader b(Class cls) {
        ClassLoader classLoader = cls.getClassLoader();
        ClassLoader classLoader2 = classLoader;
        if (classLoader == null) {
            classLoader2 = ClassLoader.getSystemClassLoader();
        }
        return classLoader2;
    }

    private static Method a() {
        if (e == null) {
            synchronized (f1648b) {
                if (e == null) {
                    e = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                    try {
                        e.setAccessible(true);
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AccessClassLoader a(Class cls) {
        ClassLoader b2 = b(cls);
        if (c.equals(b2)) {
            if (d == null) {
                synchronized (f1648b) {
                    if (d == null) {
                        d = new AccessClassLoader(c);
                    }
                }
            }
            return d;
        }
        synchronized (f1648b) {
            WeakReference<AccessClassLoader> weakReference = f1648b.get(b2);
            if (weakReference != null) {
                AccessClassLoader accessClassLoader = weakReference.get();
                if (accessClassLoader != null) {
                    return accessClassLoader;
                }
                f1648b.remove(b2);
            }
            AccessClassLoader accessClassLoader2 = new AccessClassLoader(b2);
            f1648b.put(b2, new WeakReference<>(accessClassLoader2));
            return accessClassLoader2;
        }
    }

    public static void remove(ClassLoader classLoader) {
        if (c.equals(classLoader)) {
            d = null;
            return;
        }
        synchronized (f1648b) {
            f1648b.remove(classLoader);
        }
    }

    public static int activeAccessClassLoaders() {
        int size = f1648b.size();
        if (d != null) {
            size++;
        }
        return size;
    }
}
