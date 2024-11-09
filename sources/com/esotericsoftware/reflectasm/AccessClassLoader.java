package com.esotericsoftware.reflectasm;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.WeakHashMap;

/* loaded from: infinitode-2.jar:com/esotericsoftware/reflectasm/AccessClassLoader.class */
class AccessClassLoader extends ClassLoader {
    private static final WeakHashMap<ClassLoader, WeakReference<AccessClassLoader>> accessClassLoaders = new WeakHashMap<>();
    private static final ClassLoader selfContextParentClassLoader = getParentClassLoader(AccessClassLoader.class);
    private static volatile AccessClassLoader selfContextAccessClassLoader = new AccessClassLoader(selfContextParentClassLoader);
    private static volatile Method defineClassMethod;
    private final HashSet<String> localClassNames;

    private AccessClassLoader(ClassLoader classLoader) {
        super(classLoader);
        this.localClassNames = new HashSet<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class loadAccessClass(String str) {
        if (this.localClassNames.contains(str)) {
            try {
                return loadClass(str, false);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class defineAccessClass(String str, byte[] bArr) {
        this.localClassNames.add(str);
        return defineClass(str, bArr);
    }

    @Override // java.lang.ClassLoader
    protected Class<?> loadClass(String str, boolean z) {
        return str.equals(FieldAccess.class.getName()) ? FieldAccess.class : str.equals(MethodAccess.class.getName()) ? MethodAccess.class : str.equals(ConstructorAccess.class.getName()) ? ConstructorAccess.class : str.equals(PublicConstructorAccess.class.getName()) ? PublicConstructorAccess.class : super.loadClass(str, z);
    }

    Class<?> defineClass(String str, byte[] bArr) {
        try {
            return (Class) getDefineClassMethod().invoke(getParent(), str, bArr, 0, Integer.valueOf(bArr.length), getClass().getProtectionDomain());
        } catch (Exception unused) {
            return defineClass(str, bArr, 0, bArr.length, getClass().getProtectionDomain());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean areInSameRuntimeClassLoader(Class cls, Class cls2) {
        if (cls.getPackage() != cls2.getPackage()) {
            return false;
        }
        ClassLoader classLoader = cls.getClassLoader();
        ClassLoader classLoader2 = cls2.getClassLoader();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        return classLoader == null ? classLoader2 == null || classLoader2 == systemClassLoader : classLoader2 == null ? classLoader == systemClassLoader : classLoader == classLoader2;
    }

    private static ClassLoader getParentClassLoader(Class cls) {
        ClassLoader classLoader = cls.getClassLoader();
        ClassLoader classLoader2 = classLoader;
        if (classLoader == null) {
            classLoader2 = ClassLoader.getSystemClassLoader();
        }
        return classLoader2;
    }

    private static Method getDefineClassMethod() {
        if (defineClassMethod == null) {
            synchronized (accessClassLoaders) {
                if (defineClassMethod == null) {
                    defineClassMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class);
                    try {
                        defineClassMethod.setAccessible(true);
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return defineClassMethod;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AccessClassLoader get(Class cls) {
        ClassLoader parentClassLoader = getParentClassLoader(cls);
        if (selfContextParentClassLoader.equals(parentClassLoader)) {
            if (selfContextAccessClassLoader == null) {
                synchronized (accessClassLoaders) {
                    if (selfContextAccessClassLoader == null) {
                        selfContextAccessClassLoader = new AccessClassLoader(selfContextParentClassLoader);
                    }
                }
            }
            return selfContextAccessClassLoader;
        }
        synchronized (accessClassLoaders) {
            WeakReference<AccessClassLoader> weakReference = accessClassLoaders.get(parentClassLoader);
            if (weakReference != null) {
                AccessClassLoader accessClassLoader = weakReference.get();
                if (accessClassLoader != null) {
                    return accessClassLoader;
                }
                accessClassLoaders.remove(parentClassLoader);
            }
            AccessClassLoader accessClassLoader2 = new AccessClassLoader(parentClassLoader);
            accessClassLoaders.put(parentClassLoader, new WeakReference<>(accessClassLoader2));
            return accessClassLoader2;
        }
    }

    public static void remove(ClassLoader classLoader) {
        if (selfContextParentClassLoader.equals(classLoader)) {
            selfContextAccessClassLoader = null;
            return;
        }
        synchronized (accessClassLoaders) {
            accessClassLoaders.remove(classLoader);
        }
    }

    public static int activeAccessClassLoaders() {
        int size = accessClassLoaders.size();
        if (selfContextAccessClassLoader != null) {
            size++;
        }
        return size;
    }
}
