package nonapi.io.github.classgraph.scanspec;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ModulePathInfo;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nonapi.io.github.classgraph.scanspec.AcceptReject;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/scanspec/ScanSpec.class */
public class ScanSpec {
    public boolean enableClassInfo;
    public boolean enableFieldInfo;
    public boolean enableMethodInfo;
    public boolean enableAnnotationInfo;
    public boolean enableStaticFinalFieldConstantInitializerValues;
    public boolean enableInterClassDependencies;
    public boolean enableExternalClasses;
    public boolean enableSystemJarsAndModules;
    public boolean ignoreClassVisibility;
    public boolean ignoreFieldVisibility;
    public boolean ignoreMethodVisibility;
    public boolean disableRuntimeInvisibleAnnotations;
    public Set<String> allowedURLSchemes;
    public transient List<ClassLoader> addedClassLoaders;
    public transient List<ClassLoader> overrideClassLoaders;
    public transient List<Object> addedModuleLayers;
    public transient List<Object> overrideModuleLayers;
    public List<Object> overrideClasspath;
    public transient List<Object> classpathElementFilters;
    public boolean initializeLoadedClasses;
    public boolean removeTemporaryFilesAfterScan;
    public boolean ignoreParentClassLoaders;
    public boolean ignoreParentModuleLayers;
    public boolean enableMemoryMapping;
    public boolean enableMultiReleaseVersions;
    public AcceptReject.AcceptRejectWholeString packageAcceptReject = new AcceptReject.AcceptRejectWholeString('.');
    public AcceptReject.AcceptRejectPrefix packagePrefixAcceptReject = new AcceptReject.AcceptRejectPrefix('.');
    public AcceptReject.AcceptRejectWholeString pathAcceptReject = new AcceptReject.AcceptRejectWholeString('/');
    public AcceptReject.AcceptRejectPrefix pathPrefixAcceptReject = new AcceptReject.AcceptRejectPrefix('/');
    public AcceptReject.AcceptRejectWholeString classAcceptReject = new AcceptReject.AcceptRejectWholeString('.');
    public AcceptReject.AcceptRejectWholeString classfilePathAcceptReject = new AcceptReject.AcceptRejectWholeString('/');
    public AcceptReject.AcceptRejectWholeString classPackageAcceptReject = new AcceptReject.AcceptRejectWholeString('.');
    public AcceptReject.AcceptRejectWholeString classPackagePathAcceptReject = new AcceptReject.AcceptRejectWholeString('/');
    public AcceptReject.AcceptRejectWholeString moduleAcceptReject = new AcceptReject.AcceptRejectWholeString('.');
    public AcceptReject.AcceptRejectLeafname jarAcceptReject = new AcceptReject.AcceptRejectLeafname('/');
    public AcceptReject.AcceptRejectWholeString classpathElementResourcePathAcceptReject = new AcceptReject.AcceptRejectWholeString('/');
    public AcceptReject.AcceptRejectLeafname libOrExtJarAcceptReject = new AcceptReject.AcceptRejectLeafname('/');
    public boolean scanJars = true;
    public boolean scanNestedJars = true;
    public boolean scanDirs = true;
    public boolean scanModules = true;
    public boolean extendScanningUpwardsToExternalClasses = true;
    public ModulePathInfo modulePathInfo = new ModulePathInfo();
    public int maxBufferedJarRAMSize = 67108864;

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch.class */
    public enum ScanSpecPathMatch {
        HAS_REJECTED_PATH_PREFIX,
        HAS_ACCEPTED_PATH_PREFIX,
        AT_ACCEPTED_PATH,
        ANCESTOR_OF_ACCEPTED_PATH,
        AT_ACCEPTED_CLASS_PACKAGE,
        NOT_WITHIN_ACCEPTED_PATH
    }

    public void sortPrefixes() {
        for (Field field : ScanSpec.class.getDeclaredFields()) {
            if (AcceptReject.class.isAssignableFrom(field.getType())) {
                try {
                    ((AcceptReject) field.get(this)).sortPrefixes();
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException("Field is not accessible: " + field, e);
                }
            }
        }
    }

    public void addClasspathOverride(Object obj) {
        if (this.overrideClasspath == null) {
            this.overrideClasspath = new ArrayList();
        }
        if (obj instanceof ClassLoader) {
            throw new IllegalArgumentException("Need to pass ClassLoader instances to overrideClassLoaders, not overrideClasspath");
        }
        this.overrideClasspath.add(((obj instanceof String) || (obj instanceof URL) || (obj instanceof URI)) ? obj : obj.toString());
    }

    public void filterClasspathElements(Object obj) {
        if (!(obj instanceof ClassGraph.ClasspathElementFilter) && !(obj instanceof ClassGraph.ClasspathElementURLFilter)) {
            throw new IllegalArgumentException();
        }
        if (this.classpathElementFilters == null) {
            this.classpathElementFilters = new ArrayList(2);
        }
        this.classpathElementFilters.add(obj);
    }

    public void addClassLoader(ClassLoader classLoader) {
        if (this.addedClassLoaders == null) {
            this.addedClassLoaders = new ArrayList();
        }
        if (classLoader != null) {
            this.addedClassLoaders.add(classLoader);
        }
    }

    public void enableURLScheme(String str) {
        if (str == null || str.length() < 2) {
            throw new IllegalArgumentException("URL schemes must contain at least two characters");
        }
        if (this.allowedURLSchemes == null) {
            this.allowedURLSchemes = new HashSet();
        }
        this.allowedURLSchemes.add(str.toLowerCase());
    }

    public void overrideClassLoaders(ClassLoader... classLoaderArr) {
        if (classLoaderArr.length == 0) {
            throw new IllegalArgumentException("At least one override ClassLoader must be provided");
        }
        this.addedClassLoaders = null;
        this.overrideClassLoaders = new ArrayList();
        for (ClassLoader classLoader : classLoaderArr) {
            if (classLoader != null) {
                this.overrideClassLoaders.add(classLoader);
            }
        }
    }

    private static boolean isModuleLayer(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("ModuleLayer references must not be null");
        }
        Class<?> cls = obj.getClass();
        while (true) {
            Class<?> cls2 = cls;
            if (cls2 != null) {
                if (!cls2.getName().equals("java.lang.ModuleLayer")) {
                    cls = cls2.getSuperclass();
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public void addModuleLayer(Object obj) {
        if (!isModuleLayer(obj)) {
            throw new IllegalArgumentException("moduleLayer must be of type java.lang.ModuleLayer");
        }
        if (this.addedModuleLayers == null) {
            this.addedModuleLayers = new ArrayList();
        }
        this.addedModuleLayers.add(obj);
    }

    public void overrideModuleLayers(Object... objArr) {
        if (objArr == null) {
            throw new IllegalArgumentException("overrideModuleLayers cannot be null");
        }
        if (objArr.length == 0) {
            throw new IllegalArgumentException("At least one override ModuleLayer must be provided");
        }
        for (Object obj : objArr) {
            if (!isModuleLayer(obj)) {
                throw new IllegalArgumentException("moduleLayer must be of type java.lang.ModuleLayer");
            }
        }
        this.addedModuleLayers = null;
        this.overrideModuleLayers = new ArrayList();
        Collections.addAll(this.overrideModuleLayers, objArr);
    }

    public ScanSpecPathMatch dirAcceptMatchStatus(String str) {
        if (this.pathAcceptReject.isRejected(str) || this.pathPrefixAcceptReject.isRejected(str)) {
            return ScanSpecPathMatch.HAS_REJECTED_PATH_PREFIX;
        }
        if (this.pathAcceptReject.acceptIsEmpty() && this.classPackagePathAcceptReject.acceptIsEmpty()) {
            return (str.isEmpty() || str.equals("/")) ? ScanSpecPathMatch.AT_ACCEPTED_PATH : ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX;
        }
        if (this.pathAcceptReject.isSpecificallyAcceptedAndNotRejected(str)) {
            return ScanSpecPathMatch.AT_ACCEPTED_PATH;
        }
        if (this.classPackagePathAcceptReject.isSpecificallyAcceptedAndNotRejected(str)) {
            return ScanSpecPathMatch.AT_ACCEPTED_CLASS_PACKAGE;
        }
        if (this.pathPrefixAcceptReject.isSpecificallyAccepted(str)) {
            return ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX;
        }
        if (str.equals("/") || this.pathAcceptReject.acceptHasPrefix(str) || this.classfilePathAcceptReject.acceptHasPrefix(str)) {
            return ScanSpecPathMatch.ANCESTOR_OF_ACCEPTED_PATH;
        }
        return ScanSpecPathMatch.NOT_WITHIN_ACCEPTED_PATH;
    }

    public boolean classfileIsSpecificallyAccepted(String str) {
        return this.classfilePathAcceptReject.isSpecificallyAcceptedAndNotRejected(str);
    }

    public boolean classOrPackageIsRejected(String str) {
        return this.classAcceptReject.isRejected(str) || this.packagePrefixAcceptReject.isRejected(str);
    }

    public void log(LogNode logNode) {
        if (logNode != null) {
            LogNode log = logNode.log("ScanSpec:");
            for (Field field : ScanSpec.class.getDeclaredFields()) {
                try {
                    log.log(field.getName() + ": " + field.get(this));
                } catch (ReflectiveOperationException unused) {
                }
            }
        }
    }
}
