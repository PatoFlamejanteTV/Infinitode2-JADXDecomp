package io.github.classgraph;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.VersionFinder;

/* loaded from: infinitode-2.jar:io/github/classgraph/ClassGraphClassLoader.class */
public class ClassGraphClassLoader extends ClassLoader {
    private final ScanResult scanResult;
    private final boolean initializeLoadedClasses;
    private Set<ClassLoader> environmentClassLoaderDelegationOrder;
    private List<ClassLoader> overrideClassLoaders;
    private final ClassLoader classpathClassLoader;
    private Set<ClassLoader> addedClassLoaderDelegationOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassGraphClassLoader(ScanResult scanResult) {
        super(null);
        registerAsParallelCapable();
        this.scanResult = scanResult;
        ScanSpec scanSpec = scanResult.scanSpec;
        this.initializeLoadedClasses = scanSpec.initializeLoadedClasses;
        boolean z = (scanSpec.overrideClasspath == null || scanSpec.overrideClasspath.isEmpty()) ? false : true;
        boolean z2 = (scanSpec.overrideClassLoaders == null || scanSpec.overrideClassLoaders.isEmpty()) ? false : true;
        boolean z3 = (scanSpec.addedClassLoaders == null || scanSpec.addedClassLoaders.isEmpty()) ? false : true;
        if (!z && !z2) {
            this.environmentClassLoaderDelegationOrder = new LinkedHashSet();
            this.environmentClassLoaderDelegationOrder.add(null);
            ClassLoader[] classLoaderOrderRespectingParentDelegation = scanResult.getClassLoaderOrderRespectingParentDelegation();
            if (classLoaderOrderRespectingParentDelegation != null) {
                this.environmentClassLoaderDelegationOrder.addAll(Arrays.asList(classLoaderOrderRespectingParentDelegation));
            }
        }
        List<URL> classpathURLs = scanResult.getClasspathURLs();
        this.classpathClassLoader = classpathURLs.isEmpty() ? null : new URLClassLoader((URL[]) classpathURLs.toArray(new URL[0]));
        this.overrideClassLoaders = z2 ? scanSpec.overrideClassLoaders : null;
        if (this.overrideClassLoaders == null && z && this.classpathClassLoader != null) {
            this.overrideClassLoaders = Collections.singletonList(this.classpathClassLoader);
        }
        if (z3) {
            this.addedClassLoaderDelegationOrder = new LinkedHashSet();
            this.addedClassLoaderDelegationOrder.addAll(scanSpec.addedClassLoaders);
            if (this.environmentClassLoaderDelegationOrder != null) {
                this.addedClassLoaderDelegationOrder.removeAll(this.environmentClassLoaderDelegationOrder);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Class<?>, java.lang.Class] */
    @Override // java.lang.ClassLoader
    protected Class<?> findClass(String str) {
        String message;
        int indexOf;
        ClassGraphClassLoader delegateClassGraphClassLoader = this.scanResult.classpathFinder.getDelegateClassGraphClassLoader();
        LinkageError linkageError = null;
        ?? r0 = delegateClassGraphClassLoader;
        if (r0 != 0) {
            try {
                r0 = Class.forName(str, this.initializeLoadedClasses, delegateClassGraphClassLoader);
                return r0;
            } catch (ClassNotFoundException unused) {
            } catch (LinkageError e) {
                linkageError = r0;
            }
        }
        if (this.overrideClassLoaders != null) {
            Iterator<ClassLoader> it = this.overrideClassLoaders.iterator();
            while (it.hasNext()) {
                try {
                    return Class.forName(str, this.initializeLoadedClasses, it.next());
                } catch (ClassNotFoundException unused2) {
                } catch (LinkageError e2) {
                    if (linkageError == null) {
                        linkageError = e2;
                    }
                }
            }
        }
        if (this.overrideClassLoaders == null && this.environmentClassLoaderDelegationOrder != null && !this.environmentClassLoaderDelegationOrder.isEmpty()) {
            Iterator<ClassLoader> it2 = this.environmentClassLoaderDelegationOrder.iterator();
            while (it2.hasNext()) {
                try {
                    return Class.forName(str, this.initializeLoadedClasses, it2.next());
                } catch (ClassNotFoundException unused3) {
                } catch (LinkageError e3) {
                    if (linkageError == null) {
                        linkageError = e3;
                    }
                }
            }
        }
        ClassLoader classLoader = null;
        ClassInfo classInfo = this.scanResult.classNameToClassInfo == null ? null : this.scanResult.classNameToClassInfo.get(str);
        ClassInfo classInfo2 = classInfo;
        if (classInfo != null) {
            ClassLoader classLoader2 = classInfo2.classLoader;
            classLoader = classLoader2;
            if (classLoader2 != null && (this.environmentClassLoaderDelegationOrder == null || !this.environmentClassLoaderDelegationOrder.contains(classLoader))) {
                try {
                    return Class.forName(str, this.initializeLoadedClasses, classLoader);
                } catch (ClassNotFoundException unused4) {
                } catch (LinkageError e4) {
                    if (linkageError == null) {
                        linkageError = e4;
                    }
                }
            }
            if ((classInfo2.classpathElement instanceof ClasspathElementModule) && !classInfo2.isPublic()) {
                throw new ClassNotFoundException("Classfile for class " + str + " was found in a module, but the context and system classloaders could not load the class, probably because the class is not public.");
            }
        }
        if (this.overrideClassLoaders == null && this.classpathClassLoader != null) {
            try {
                return Class.forName(str, this.initializeLoadedClasses, this.classpathClassLoader);
            } catch (ClassNotFoundException unused5) {
            } catch (LinkageError e5) {
                if (linkageError == null) {
                    linkageError = e5;
                }
            }
        }
        if (this.addedClassLoaderDelegationOrder != null && !this.addedClassLoaderDelegationOrder.isEmpty()) {
            for (ClassLoader classLoader3 : this.addedClassLoaderDelegationOrder) {
                if (classLoader3 != classLoader) {
                    try {
                        return Class.forName(str, this.initializeLoadedClasses, classLoader3);
                    } catch (ClassNotFoundException unused6) {
                    } catch (LinkageError e6) {
                        if (linkageError == null) {
                            linkageError = e6;
                        }
                    }
                }
            }
        }
        ResourceList resourcesWithPath = this.scanResult.getResourcesWithPath(JarUtils.classNameToClassfilePath(str));
        if (resourcesWithPath != null) {
            Iterator it3 = resourcesWithPath.iterator();
            while (it3.hasNext()) {
                Resource resource = (Resource) it3.next();
                Throwable th = null;
                try {
                    try {
                        try {
                            Class<?> defineClass = defineClass(str, resource.read(), (ProtectionDomain) null);
                            if (resource != null) {
                                if (0 != 0) {
                                    try {
                                        resource.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                } else {
                                    resource.close();
                                }
                            }
                            return defineClass;
                        } catch (IOException e7) {
                            throw new ClassNotFoundException("Could not load classfile for class " + str + " : " + e7);
                        } catch (LinkageError e8) {
                            if (linkageError == null) {
                                linkageError = e8;
                            }
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    th = null;
                    throw th3;
                    break;
                }
            }
        }
        if (linkageError != null) {
            if (VersionFinder.OS == VersionFinder.OperatingSystem.Windows && (message = linkageError.getMessage()) != null && (indexOf = message.indexOf("(wrong name: ")) >= 0 && message.substring(indexOf + 13, message.length() - 1).replace('/', '.').equalsIgnoreCase(str)) {
                throw new LinkageError("You appear to have two classfiles with the same case-insensitive name in the same directory on a case-insensitive filesystem -- this is not allowed on Windows, and therefore your code is not portable. Class name: " + str, linkageError);
            }
            throw linkageError;
        }
        throw new ClassNotFoundException("Could not find or load classfile for class " + str);
    }

    public URL[] getURLs() {
        return (URL[]) this.scanResult.getClasspathURLs().toArray(new URL[0]);
    }

    @Override // java.lang.ClassLoader
    public URL getResource(String str) {
        if (!this.environmentClassLoaderDelegationOrder.isEmpty()) {
            Iterator<ClassLoader> it = this.environmentClassLoaderDelegationOrder.iterator();
            while (it.hasNext()) {
                URL resource = it.next().getResource(str);
                if (resource != null) {
                    return resource;
                }
            }
        }
        if (!this.addedClassLoaderDelegationOrder.isEmpty()) {
            Iterator<ClassLoader> it2 = this.addedClassLoaderDelegationOrder.iterator();
            while (it2.hasNext()) {
                URL resource2 = it2.next().getResource(str);
                if (resource2 != null) {
                    return resource2;
                }
            }
        }
        ResourceList resourcesWithPath = this.scanResult.getResourcesWithPath(str);
        if (resourcesWithPath == null || resourcesWithPath.isEmpty()) {
            return super.getResource(str);
        }
        return ((Resource) resourcesWithPath.get(0)).getURL();
    }

    @Override // java.lang.ClassLoader
    public Enumeration<URL> getResources(String str) {
        if (!this.environmentClassLoaderDelegationOrder.isEmpty()) {
            Iterator<ClassLoader> it = this.environmentClassLoaderDelegationOrder.iterator();
            while (it.hasNext()) {
                Enumeration<URL> resources = it.next().getResources(str);
                if (resources != null && resources.hasMoreElements()) {
                    return resources;
                }
            }
        }
        if (!this.addedClassLoaderDelegationOrder.isEmpty()) {
            Iterator<ClassLoader> it2 = this.addedClassLoaderDelegationOrder.iterator();
            while (it2.hasNext()) {
                Enumeration<URL> resources2 = it2.next().getResources(str);
                if (resources2 != null && resources2.hasMoreElements()) {
                    return resources2;
                }
            }
        }
        final ResourceList resourcesWithPath = this.scanResult.getResourcesWithPath(str);
        if (resourcesWithPath != null && !resourcesWithPath.isEmpty()) {
            return new Enumeration<URL>() { // from class: io.github.classgraph.ClassGraphClassLoader.1
                int idx;

                @Override // java.util.Enumeration
                public boolean hasMoreElements() {
                    return this.idx < resourcesWithPath.size();
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Enumeration
                public URL nextElement() {
                    ResourceList resourceList = resourcesWithPath;
                    int i = this.idx;
                    this.idx = i + 1;
                    return ((Resource) resourceList.get(i)).getURL();
                }
            };
        }
        return Collections.emptyEnumeration();
    }

    @Override // java.lang.ClassLoader
    public InputStream getResourceAsStream(String str) {
        if (!this.environmentClassLoaderDelegationOrder.isEmpty()) {
            Iterator<ClassLoader> it = this.environmentClassLoaderDelegationOrder.iterator();
            while (it.hasNext()) {
                InputStream resourceAsStream = it.next().getResourceAsStream(str);
                if (resourceAsStream != null) {
                    return resourceAsStream;
                }
            }
        }
        if (!this.addedClassLoaderDelegationOrder.isEmpty()) {
            Iterator<ClassLoader> it2 = this.addedClassLoaderDelegationOrder.iterator();
            while (it2.hasNext()) {
                InputStream resourceAsStream2 = it2.next().getResourceAsStream(str);
                if (resourceAsStream2 != null) {
                    return resourceAsStream2;
                }
            }
        }
        ResourceList resourcesWithPath = this.scanResult.getResourcesWithPath(str);
        if (resourcesWithPath == null || resourcesWithPath.isEmpty()) {
            return super.getResourceAsStream(str);
        }
        try {
            return ((Resource) resourcesWithPath.get(0)).open();
        } catch (IOException unused) {
            return null;
        }
    }
}
