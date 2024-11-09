package org.a.a.a;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.AccessController;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

/* loaded from: infinitode-2.jar:org/a/a/a/c.class */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    private static PrintStream f4182a;

    /* renamed from: b, reason: collision with root package name */
    private static final String f4183b;
    private static final ClassLoader c;
    private static Hashtable d;
    private static volatile c e = null;
    private static Class f;

    public abstract a a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(String str) {
        c(str);
    }

    static {
        Class cls;
        String str;
        Class cls2;
        f4182a = null;
        d = null;
        if (f == null) {
            cls = d("org.a.a.a.c");
            f = cls;
        } else {
            cls = f;
        }
        c = b(cls);
        try {
            ClassLoader classLoader = c;
            if (c == null) {
                str = "BOOTLOADER";
            } else {
                str = a((Object) classLoader);
            }
        } catch (SecurityException unused) {
            str = "UNKNOWN";
        }
        f4183b = new StringBuffer("[LogFactory from ").append(str).append("] ").toString();
        f4182a = g();
        if (f == null) {
            cls2 = d("org.a.a.a.c");
            f = cls2;
        } else {
            cls2 = f;
        }
        d(cls2);
        d = d();
        if (c()) {
            c("BOOTSTRAP COMPLETED");
        }
    }

    protected c() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.util.Hashtable] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
    private static final Hashtable d() {
        String str;
        Hashtable hashtable = null;
        try {
            str = a("org.apache.commons.logging.LogFactory.HashtableImpl", (String) null);
        } catch (SecurityException unused) {
            str = null;
        }
        String str2 = str;
        ?? r0 = str2;
        if (str2 == null) {
            str = "org.apache.commons.logging.impl.WeakHashtable";
            r0 = "org.apache.commons.logging.impl.WeakHashtable";
        }
        try {
            r0 = (Hashtable) Class.forName(str).newInstance();
            hashtable = r0;
        } catch (Throwable th) {
            a((Throwable) r0);
            if (!"org.apache.commons.logging.impl.WeakHashtable".equals(str)) {
                if (c()) {
                    c("[ERROR] LogFactory: Load of custom hashtable failed");
                } else {
                    System.err.println("[ERROR] LogFactory: Load of custom hashtable failed");
                }
            }
        }
        if (hashtable == null) {
            hashtable = new Hashtable();
        }
        return hashtable;
    }

    private static String b(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    private static void a(Throwable th) {
        if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        }
        if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        }
    }

    private static c e() {
        BufferedReader bufferedReader;
        String property;
        ClassLoader f2 = f();
        if (f2 == null && c()) {
            c("Context classloader is null.");
        }
        c a2 = a(f2);
        c cVar = a2;
        if (a2 != null) {
            return cVar;
        }
        if (c()) {
            c(new StringBuffer("[LOOKUP] LogFactory implementation requested for the first time for context classloader ").append(a((Object) f2)).toString());
            b("[LOOKUP] ", f2);
        }
        Properties c2 = c(f2, "commons-logging.properties");
        ClassLoader classLoader = f2;
        if (c2 != null && (property = c2.getProperty("use_tccl")) != null && !Boolean.valueOf(property).booleanValue()) {
            classLoader = c;
        }
        if (c()) {
            c("[LOOKUP] Looking for system property [org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
        }
        try {
            String a3 = a("org.a.a.a.c", (String) null);
            if (a3 != null) {
                if (c()) {
                    c(new StringBuffer("[LOOKUP] Creating an instance of LogFactory class '").append(a3).append("' as specified by system property org.apache.commons.logging.LogFactory").toString());
                }
                cVar = a(a3, classLoader, f2);
            } else if (c()) {
                c("[LOOKUP] No system property [org.apache.commons.logging.LogFactory] defined.");
            }
        } catch (SecurityException e2) {
            if (c()) {
                c(new StringBuffer("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [").append(b(e2.getMessage())).append("]. Trying alternative implementations...").toString());
            }
        } catch (RuntimeException e3) {
            if (c()) {
                c(new StringBuffer("[LOOKUP] An exception occurred while trying to create an instance of the custom factory class: [").append(b(e3.getMessage())).append("] as specified by a system property.").toString());
            }
            throw e3;
        }
        if (cVar == null) {
            if (c()) {
                c("[LOOKUP] Looking for a resource file of name [META-INF/services/org.apache.commons.logging.LogFactory] to define the LogFactory subclass to use...");
            }
            try {
                InputStream a4 = a(f2, "META-INF/services/org.apache.commons.logging.LogFactory");
                if (a4 != null) {
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(a4, "UTF-8"));
                    } catch (UnsupportedEncodingException unused) {
                        bufferedReader = new BufferedReader(new InputStreamReader(a4));
                    }
                    String readLine = bufferedReader.readLine();
                    bufferedReader.close();
                    if (readLine != null && !"".equals(readLine)) {
                        if (c()) {
                            c(new StringBuffer("[LOOKUP]  Creating an instance of LogFactory class ").append(readLine).append(" as specified by file 'META-INF/services/org.apache.commons.logging.LogFactory").append("' which was present in the path of the context classloader.").toString());
                        }
                        cVar = a(readLine, classLoader, f2);
                    }
                } else if (c()) {
                    c("[LOOKUP] No resource file with name 'META-INF/services/org.apache.commons.logging.LogFactory' found.");
                }
            } catch (Exception e4) {
                if (c()) {
                    c(new StringBuffer("[LOOKUP] A security exception occurred while trying to create an instance of the custom factory class: [").append(b(e4.getMessage())).append("]. Trying alternative implementations...").toString());
                }
            }
        }
        if (cVar == null) {
            if (c2 != null) {
                if (c()) {
                    c("[LOOKUP] Looking in properties file for entry with key 'org.apache.commons.logging.LogFactory' to define the LogFactory subclass to use...");
                }
                String property2 = c2.getProperty("org.a.a.a.c");
                if (property2 != null) {
                    if (c()) {
                        c(new StringBuffer("[LOOKUP] Properties file specifies LogFactory subclass '").append(property2).append("'").toString());
                    }
                    cVar = a(property2, classLoader, f2);
                } else if (c()) {
                    c("[LOOKUP] Properties file has no entry specifying LogFactory subclass.");
                }
            } else if (c()) {
                c("[LOOKUP] No properties file available to determine LogFactory subclass from..");
            }
        }
        if (cVar == null) {
            if (c()) {
                c("[LOOKUP] Loading the default LogFactory implementation 'org.apache.commons.logging.impl.LogFactoryImpl' via the same classloader that loaded this LogFactory class (ie not looking in the context classloader).");
            }
            cVar = a("org.apache.commons.logging.impl.LogFactoryImpl", c, f2);
        }
        if (cVar != null) {
            a(f2, cVar);
            if (c2 != null) {
                Enumeration<?> propertyNames = c2.propertyNames();
                while (propertyNames.hasMoreElements()) {
                    c2.getProperty((String) propertyNames.nextElement());
                }
            }
        }
        return cVar;
    }

    public static a a(Class cls) {
        return e().a();
    }

    private static ClassLoader b(Class cls) {
        try {
            return cls.getClassLoader();
        } catch (SecurityException e2) {
            if (c()) {
                c(new StringBuffer("Unable to get classloader for class '").append(cls).append("' due to security restrictions - ").append(e2.getMessage()).toString());
            }
            throw e2;
        }
    }

    private static ClassLoader f() {
        return (ClassLoader) AccessController.doPrivileged(new d());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static ClassLoader b() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (SecurityException unused) {
        }
        return classLoader;
    }

    private static c a(ClassLoader classLoader) {
        if (classLoader == null) {
            return e;
        }
        return (c) d.get(classLoader);
    }

    private static void a(ClassLoader classLoader, c cVar) {
        if (cVar != null) {
            if (classLoader == null) {
                e = cVar;
            } else {
                d.put(classLoader, cVar);
            }
        }
    }

    private static c a(String str, ClassLoader classLoader, ClassLoader classLoader2) {
        Object doPrivileged = AccessController.doPrivileged(new e(str, classLoader));
        if (doPrivileged instanceof b) {
            b bVar = (b) doPrivileged;
            if (c()) {
                c(new StringBuffer("An error occurred while loading the factory class:").append(bVar.getMessage()).toString());
            }
            throw bVar;
        }
        if (c()) {
            c(new StringBuffer("Created object ").append(a(doPrivileged)).append(" to manage classloader ").append(a((Object) classLoader2)).toString());
        }
        return (c) doPrivileged;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object a(String str, ClassLoader classLoader) {
        Class cls;
        Class cls2;
        Class cls3;
        Class cls4;
        Class<?> cls5 = null;
        try {
            if (classLoader != null) {
                try {
                    try {
                        cls5 = classLoader.loadClass(str);
                        if (f == null) {
                            cls3 = d("org.a.a.a.c");
                            f = cls3;
                        } else {
                            cls3 = f;
                        }
                        if (cls3.isAssignableFrom(cls5)) {
                            if (c()) {
                                c(new StringBuffer("Loaded class ").append(cls5.getName()).append(" from classloader ").append(a((Object) classLoader)).toString());
                            }
                        } else if (c()) {
                            StringBuffer append = new StringBuffer("Factory class ").append(cls5.getName()).append(" loaded from classloader ").append(a((Object) cls5.getClassLoader())).append(" does not extend '");
                            if (f == null) {
                                cls4 = d("org.a.a.a.c");
                                f = cls4;
                            } else {
                                cls4 = f;
                            }
                            c(append.append(cls4.getName()).append("' as loaded by this classloader.").toString());
                            b("[BAD CL TREE] ", classLoader);
                        }
                        return (c) cls5.newInstance();
                    } catch (ClassCastException unused) {
                        if (classLoader == c) {
                            boolean c2 = c(cls5);
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("The application has specified that a custom LogFactory implementation ");
                            stringBuffer.append("should be used but Class '");
                            stringBuffer.append(str);
                            stringBuffer.append("' cannot be converted to '");
                            if (f == null) {
                                cls2 = d("org.a.a.a.c");
                                f = cls2;
                            } else {
                                cls2 = f;
                            }
                            stringBuffer.append(cls2.getName());
                            stringBuffer.append("'. ");
                            if (c2) {
                                stringBuffer.append("The conflict is caused by the presence of multiple LogFactory classes ");
                                stringBuffer.append("in incompatible classloaders. ");
                                stringBuffer.append("Background can be found in http://commons.apache.org/logging/tech.html. ");
                                stringBuffer.append("If you have not explicitly specified a custom LogFactory then it is likely ");
                                stringBuffer.append("that the container has set one without your knowledge. ");
                                stringBuffer.append("In this case, consider using the commons-logging-adapters.jar file or ");
                                stringBuffer.append("specifying the standard LogFactory from the command line. ");
                            } else {
                                stringBuffer.append("Please check the custom implementation. ");
                            }
                            stringBuffer.append("Help can be found @http://commons.apache.org/logging/troubleshooting.html.");
                            if (c()) {
                                c(stringBuffer.toString());
                            }
                            throw new ClassCastException(stringBuffer.toString());
                        }
                    } catch (NoClassDefFoundError e2) {
                        if (classLoader == c) {
                            if (c()) {
                                c(new StringBuffer("Class '").append(str).append("' cannot be loaded via classloader ").append(a((Object) classLoader)).append(" - it depends on some other class that cannot be found.").toString());
                            }
                            throw e2;
                        }
                    }
                } catch (ClassNotFoundException e3) {
                    if (classLoader == c) {
                        if (c()) {
                            c(new StringBuffer("Unable to locate any class called '").append(str).append("' via classloader ").append(a((Object) classLoader)).toString());
                        }
                        throw e3;
                    }
                }
            }
            if (c()) {
                c(new StringBuffer("Unable to load factory class via classloader ").append(a((Object) classLoader)).append(" - trying the classloader associated with this LogFactory.").toString());
            }
            return (c) Class.forName(str).newInstance();
        } catch (Exception e4) {
            if (c()) {
                c("Unable to create LogFactory instance.");
            }
            if (cls5 != null) {
                if (f == null) {
                    cls = d("org.a.a.a.c");
                    f = cls;
                } else {
                    cls = f;
                }
                if (!cls.isAssignableFrom(cls5)) {
                    return new b("The chosen LogFactory implementation does not extend LogFactory. Please check your configuration.", e4);
                }
            }
            return new b(e4);
        }
    }

    private static Class d(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    private static boolean c(Class cls) {
        boolean z = false;
        if (cls != null) {
            try {
                ClassLoader classLoader = cls.getClassLoader();
                if (classLoader != null) {
                    b("[CUSTOM LOG FACTORY] ", classLoader);
                    boolean isAssignableFrom = Class.forName("org.a.a.a.c", false, classLoader).isAssignableFrom(cls);
                    z = isAssignableFrom;
                    if (isAssignableFrom) {
                        c(new StringBuffer("[CUSTOM LOG FACTORY] ").append(cls.getName()).append(" implements LogFactory but was loaded by an incompatible classloader.").toString());
                    } else {
                        c(new StringBuffer("[CUSTOM LOG FACTORY] ").append(cls.getName()).append(" does not implement LogFactory.").toString());
                    }
                } else {
                    c("[CUSTOM LOG FACTORY] was loaded by the boot classloader");
                }
            } catch (ClassNotFoundException unused) {
                c("[CUSTOM LOG FACTORY] LogFactory class cannot be loaded by classloader which loaded the custom LogFactory implementation. Is the custom factory in the right classloader?");
            } catch (LinkageError e2) {
                c(new StringBuffer("[CUSTOM LOG FACTORY] LinkageError thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: ").append(e2.getMessage()).toString());
            } catch (SecurityException e3) {
                c(new StringBuffer("[CUSTOM LOG FACTORY] SecurityException thrown whilst trying to determine whether the compatibility was caused by a classloader conflict: ").append(e3.getMessage()).toString());
            }
        }
        return z;
    }

    private static InputStream a(ClassLoader classLoader, String str) {
        return (InputStream) AccessController.doPrivileged(new f(classLoader, str));
    }

    private static Enumeration b(ClassLoader classLoader, String str) {
        return (Enumeration) AccessController.doPrivileged(new g(classLoader, str));
    }

    private static Properties a(URL url) {
        return (Properties) AccessController.doPrivileged(new h(url));
    }

    private static final Properties c(ClassLoader classLoader, String str) {
        Enumeration b2;
        Properties properties = null;
        double d2 = 0.0d;
        URL url = null;
        try {
            b2 = b(classLoader, str);
        } catch (SecurityException unused) {
            if (c()) {
                c("SecurityException thrown while trying to find/read config files.");
            }
        }
        if (b2 == null) {
            return null;
        }
        while (b2.hasMoreElements()) {
            URL url2 = (URL) b2.nextElement();
            Properties a2 = a(url2);
            if (a2 != null) {
                if (properties == null) {
                    url = url2;
                    properties = a2;
                    String property = a2.getProperty("priority");
                    d2 = 0.0d;
                    if (property != null) {
                        d2 = Double.parseDouble(property);
                    }
                    if (c()) {
                        c(new StringBuffer("[LOOKUP] Properties file found at '").append(url2).append("' with priority ").append(d2).toString());
                    }
                } else {
                    String property2 = a2.getProperty("priority");
                    double d3 = 0.0d;
                    if (property2 != null) {
                        d3 = Double.parseDouble(property2);
                    }
                    if (d3 > d2) {
                        if (c()) {
                            c(new StringBuffer("[LOOKUP] Properties file at '").append(url2).append("' with priority ").append(d3).append(" overrides file at '").append(url).append("' with priority ").append(d2).toString());
                        }
                        url = url2;
                        properties = a2;
                        d2 = d3;
                    } else if (c()) {
                        c(new StringBuffer("[LOOKUP] Properties file at '").append(url2).append("' with priority ").append(d3).append(" does not override file at '").append(url).append("' with priority ").append(d2).toString());
                    }
                }
            }
        }
        if (c()) {
            if (properties == null) {
                c(new StringBuffer("[LOOKUP] No properties file of name '").append(str).append("' found.").toString());
            } else {
                c(new StringBuffer("[LOOKUP] Properties file of name '").append(str).append("' found at '").append(url).append('\"').toString());
            }
        }
        return properties;
    }

    private static String a(String str, String str2) {
        return (String) AccessController.doPrivileged(new i(str, str2));
    }

    private static PrintStream g() {
        try {
            String a2 = a("org.apache.commons.logging.diagnostics.dest", (String) null);
            if (a2 == null) {
                return null;
            }
            if (a2.equals("STDOUT")) {
                return System.out;
            }
            if (a2.equals("STDERR")) {
                return System.err;
            }
            try {
                return new PrintStream(new FileOutputStream(a2, true));
            } catch (IOException unused) {
                return null;
            }
        } catch (SecurityException unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean c() {
        return f4182a != null;
    }

    private static final void c(String str) {
        if (f4182a != null) {
            f4182a.print(f4183b);
            f4182a.println(str);
            f4182a.flush();
        }
    }

    private static void d(Class cls) {
        if (!c()) {
            return;
        }
        try {
            c(new StringBuffer("[ENV] Extension directories (java.ext.dir): ").append(System.getProperty("java.ext.dir")).toString());
            c(new StringBuffer("[ENV] Application classpath (java.class.path): ").append(System.getProperty("java.class.path")).toString());
        } catch (SecurityException unused) {
            c("[ENV] Security setting prevent interrogation of system classpaths.");
        }
        String name = cls.getName();
        try {
            ClassLoader b2 = b(cls);
            c(new StringBuffer("[ENV] Class ").append(name).append(" was loaded via classloader ").append(a((Object) b2)).toString());
            b(new StringBuffer("[ENV] Ancestry of classloader which loaded ").append(name).append(" is ").toString(), b2);
        } catch (SecurityException unused2) {
            c(new StringBuffer("[ENV] Security forbids determining the classloader for ").append(name).toString());
        }
    }

    private static void b(String str, ClassLoader classLoader) {
        if (!c()) {
            return;
        }
        if (classLoader != null) {
            c(new StringBuffer().append(str).append(a((Object) classLoader)).append(" == '").append(classLoader.toString()).append("'").toString());
        }
        try {
            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
            if (classLoader != null) {
                StringBuffer stringBuffer = new StringBuffer(new StringBuffer().append(str).append("ClassLoader tree:").toString());
                do {
                    stringBuffer.append(a((Object) classLoader));
                    if (classLoader == systemClassLoader) {
                        stringBuffer.append(" (SYSTEM) ");
                    }
                    try {
                        classLoader = classLoader.getParent();
                        stringBuffer.append(" --> ");
                    } catch (SecurityException unused) {
                        stringBuffer.append(" --> SECRET");
                    }
                } while (classLoader != null);
                stringBuffer.append("BOOT");
                c(stringBuffer.toString());
            }
        } catch (SecurityException unused2) {
            c(new StringBuffer().append(str).append("Security forbids determining the system classloader.").toString());
        }
    }

    private static String a(Object obj) {
        if (obj == null) {
            return "null";
        }
        return new StringBuffer().append(obj.getClass().getName()).append("@").append(System.identityHashCode(obj)).toString();
    }
}
