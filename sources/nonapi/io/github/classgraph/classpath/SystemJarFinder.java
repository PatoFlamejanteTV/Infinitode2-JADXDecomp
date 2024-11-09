package nonapi.io.github.classgraph.classpath;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import nonapi.io.github.classgraph.utils.FastPathResolver;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.VersionFinder;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classpath/SystemJarFinder.class */
public final class SystemJarFinder {
    private static final String RT_JAR;
    private static final Set<String> RT_JARS = new LinkedHashSet();
    private static final Set<String> JRE_LIB_OR_EXT_JARS = new LinkedHashSet();

    static {
        String property = VersionFinder.getProperty("java.home");
        String str = property;
        if (property == null || str.isEmpty()) {
            str = System.getenv("JAVA_HOME");
        }
        if (str != null && !str.isEmpty()) {
            File file = new File(str);
            addJREPath(file);
            if (!file.getName().equals("jre")) {
                addJREPath(new File(file, "jre"));
            } else {
                File parentFile = file.getParentFile();
                addJREPath(parentFile);
                addJREPath(new File(parentFile, "lib"));
                addJREPath(new File(parentFile, "lib/ext"));
            }
            addJREPath(new File(file, "lib"));
            addJREPath(new File(file, "lib/ext"));
            addJREPath(new File(file, "jre/lib"));
            addJREPath(new File(file, "jre/lib/ext"));
            addJREPath(new File(file, "packages"));
            addJREPath(new File(file, "packages/lib"));
            addJREPath(new File(file, "packages/lib/ext"));
        }
        String property2 = VersionFinder.getProperty("java.ext.dirs");
        if (property2 != null && !property2.isEmpty()) {
            for (String str2 : JarUtils.smartPathSplit(property2, null)) {
                if (!str2.isEmpty()) {
                    addJREPath(new File(str2));
                }
            }
        }
        switch (VersionFinder.OS) {
            case Linux:
            case Unix:
            case BSD:
            case Unknown:
                addJREPath(new File("/usr/java/packages"));
                addJREPath(new File("/usr/java/packages/lib"));
                addJREPath(new File("/usr/java/packages/lib/ext"));
                break;
            case MacOSX:
                addJREPath(new File("/System/Library/Java"));
                addJREPath(new File("/System/Library/Java/Libraries"));
                addJREPath(new File("/System/Library/Java/Extensions"));
                break;
            case Windows:
                String str3 = File.separatorChar == '\\' ? System.getenv("SystemRoot") : null;
                String str4 = str3;
                if (str3 != null) {
                    addJREPath(new File(str4, "Sun\\Java"));
                    addJREPath(new File(str4, "Sun\\Java\\lib"));
                    addJREPath(new File(str4, "Sun\\Java\\lib\\ext"));
                    addJREPath(new File(str4, "Oracle\\Java"));
                    addJREPath(new File(str4, "Oracle\\Java\\lib"));
                    addJREPath(new File(str4, "Oracle\\Java\\lib\\ext"));
                    break;
                }
                break;
            case Solaris:
                addJREPath(new File("/usr/jdk/packages"));
                addJREPath(new File("/usr/jdk/packages/lib"));
                addJREPath(new File("/usr/jdk/packages/lib/ext"));
                break;
        }
        RT_JAR = RT_JARS.isEmpty() ? null : FastPathResolver.resolve(RT_JARS.iterator().next());
    }

    private SystemJarFinder() {
    }

    private static boolean addJREPath(File file) {
        File[] listFiles;
        if (file != null && !file.getPath().isEmpty() && FileUtils.canReadAndIsDir(file) && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                String path = file2.getPath();
                if (path.endsWith(".jar")) {
                    String resolve = FastPathResolver.resolve(FileUtils.currDirPath(), path);
                    if (resolve.endsWith("/rt.jar")) {
                        RT_JARS.add(resolve);
                    } else {
                        JRE_LIB_OR_EXT_JARS.add(resolve);
                    }
                    try {
                        if (!file2.getCanonicalFile().getPath().equals(path)) {
                            JRE_LIB_OR_EXT_JARS.add(FastPathResolver.resolve(FileUtils.currDirPath(), path));
                        }
                    } catch (IOException | SecurityException unused) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static String getJreRtJarPath() {
        return RT_JAR;
    }

    public static Set<String> getJreLibOrExtJars() {
        return JRE_LIB_OR_EXT_JARS;
    }
}
