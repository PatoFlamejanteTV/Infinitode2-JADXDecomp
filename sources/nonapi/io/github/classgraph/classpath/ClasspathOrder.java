package nonapi.io.github.classgraph.classpath;

import io.github.classgraph.ClassGraph;
import java.io.File;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.FastPathResolver;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classpath/ClasspathOrder.class */
public class ClasspathOrder {
    private final ScanSpec scanSpec;
    public ReflectionUtils reflectionUtils;
    private final Set<String> classpathEntryUniqueResolvedPaths = new HashSet();
    private final List<ClasspathEntry> order = new ArrayList();
    private static final List<String> AUTOMATIC_PACKAGE_ROOT_SUFFIXES = new ArrayList();
    private static final Pattern schemeMatcher = Pattern.compile("^[a-zA-Z][a-zA-Z+\\-.]+:");

    static {
        for (String str : ClassLoaderHandlerRegistry.AUTOMATIC_PACKAGE_ROOT_PREFIXES) {
            AUTOMATIC_PACKAGE_ROOT_SUFFIXES.add("!/" + str.substring(0, str.length() - 1));
        }
    }

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classpath/ClasspathOrder$ClasspathEntry.class */
    public static class ClasspathEntry {
        public final Object classpathEntryObj;
        public final ClassLoader classLoader;

        public ClasspathEntry(Object obj, ClassLoader classLoader) {
            this.classpathEntryObj = obj;
            this.classLoader = classLoader;
        }

        public int hashCode() {
            return Objects.hash(this.classpathEntryObj);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ClasspathEntry)) {
                return false;
            }
            return Objects.equals(this.classpathEntryObj, ((ClasspathEntry) obj).classpathEntryObj);
        }

        public String toString() {
            return this.classpathEntryObj + " [" + this.classLoader + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClasspathOrder(ScanSpec scanSpec, ReflectionUtils reflectionUtils) {
        this.scanSpec = scanSpec;
        this.reflectionUtils = reflectionUtils;
    }

    public List<ClasspathEntry> getOrder() {
        return this.order;
    }

    public Set<String> getClasspathEntryUniqueResolvedPaths() {
        return this.classpathEntryUniqueResolvedPaths;
    }

    private boolean filter(URL url, String str) {
        if (this.scanSpec.classpathElementFilters != null) {
            for (Object obj : this.scanSpec.classpathElementFilters) {
                if (url == null || !(obj instanceof ClassGraph.ClasspathElementURLFilter) || ((ClassGraph.ClasspathElementURLFilter) obj).includeClasspathElement(url)) {
                    if (str != null && (obj instanceof ClassGraph.ClasspathElementFilter) && !((ClassGraph.ClasspathElementFilter) obj).includeClasspathElement(str)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addSystemClasspathEntry(String str, ClassLoader classLoader) {
        if (this.classpathEntryUniqueResolvedPaths.add(str)) {
            this.order.add(new ClasspathEntry(str, classLoader));
            return true;
        }
        return false;
    }

    private boolean addClasspathEntry(Object obj, String str, ClassLoader classLoader, ScanSpec scanSpec) {
        Object obj2;
        String str2 = str;
        boolean z = false;
        Iterator<String> it = AUTOMATIC_PACKAGE_ROOT_SUFFIXES.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (str.endsWith(next)) {
                str2 = str.substring(0, str.length() - next.length());
                z = true;
                break;
            }
        }
        if ((obj instanceof URL) || (obj instanceof URI) || (obj instanceof Path) || (obj instanceof File)) {
            Object obj3 = obj;
            if (z) {
                try {
                    if (obj instanceof URL) {
                        obj2 = new URL(str2);
                    } else if (obj instanceof URI) {
                        obj2 = new URI(str2);
                    } else {
                        obj2 = obj instanceof Path ? Paths.get(str2, new String[0]) : str2;
                    }
                    obj3 = obj2;
                } catch (MalformedURLException | URISyntaxException | InvalidPathException unused) {
                    try {
                        obj3 = obj instanceof URL ? new URL("file:" + str2) : obj instanceof URI ? new URI("file:" + str2) : str2;
                    } catch (MalformedURLException | URISyntaxException | InvalidPathException unused2) {
                        return false;
                    }
                }
            }
            if (this.classpathEntryUniqueResolvedPaths.add(str2)) {
                this.order.add(new ClasspathEntry(obj3, classLoader));
                return true;
            }
            return false;
        }
        String resolve = FastPathResolver.resolve(FileUtils.currDirPath(), str2);
        if ((scanSpec.overrideClasspath != null || (!SystemJarFinder.getJreLibOrExtJars().contains(resolve) && !resolve.equals(SystemJarFinder.getJreRtJarPath()))) && this.classpathEntryUniqueResolvedPaths.add(resolve)) {
            this.order.add(new ClasspathEntry(resolve, classLoader));
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean addClasspathEntry(java.lang.Object r7, java.lang.ClassLoader r8, nonapi.io.github.classgraph.scanspec.ScanSpec r9, nonapi.io.github.classgraph.utils.LogNode r10) {
        /*
            Method dump skipped, instructions count: 1420
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: nonapi.io.github.classgraph.classpath.ClasspathOrder.addClasspathEntry(java.lang.Object, java.lang.ClassLoader, nonapi.io.github.classgraph.scanspec.ScanSpec, nonapi.io.github.classgraph.utils.LogNode):boolean");
    }

    public boolean addClasspathEntries(List<Object> list, ClassLoader classLoader, ScanSpec scanSpec, LogNode logNode) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            addClasspathEntry(it.next(), classLoader, scanSpec, logNode);
        }
        return true;
    }

    public boolean addClasspathPathStr(String str, ClassLoader classLoader, ScanSpec scanSpec, LogNode logNode) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String[] smartPathSplit = JarUtils.smartPathSplit(str, scanSpec);
        if (smartPathSplit.length == 0) {
            return false;
        }
        for (String str2 : smartPathSplit) {
            addClasspathEntry(str2, classLoader, scanSpec, logNode);
        }
        return true;
    }

    public boolean addClasspathEntryObject(Object obj, ClassLoader classLoader, ScanSpec scanSpec, LogNode logNode) {
        boolean z = false;
        if (obj != null) {
            if ((obj instanceof URL) || (obj instanceof URI) || (obj instanceof Path) || (obj instanceof File)) {
                z = false | addClasspathEntry(obj, classLoader, scanSpec, logNode);
            } else if (obj instanceof Iterable) {
                Iterator it = ((Iterable) obj).iterator();
                while (it.hasNext()) {
                    z |= addClasspathEntryObject(it.next(), classLoader, scanSpec, logNode);
                }
            } else if (obj.getClass().isArray()) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    z |= addClasspathEntryObject(Array.get(obj, i), classLoader, scanSpec, logNode);
                }
            } else {
                z = false | addClasspathPathStr(obj.toString(), classLoader, scanSpec, logNode);
            }
        }
        return z;
    }
}
