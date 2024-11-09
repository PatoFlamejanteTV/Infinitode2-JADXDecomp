package com.d.m;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* loaded from: infinitode-2.jar:com/d/m/b.class */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private Properties f1415a;

    /* renamed from: b, reason: collision with root package name */
    private Level f1416b;
    private static b c;
    private List d;
    private Logger e;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.d.m.b] */
    /* JADX WARN: Type inference failed for: r0v13, types: [com.d.m.b] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Exception] */
    private b() {
        String str;
        ?? r0 = this;
        r0.d = new ArrayList();
        try {
            try {
                try {
                    str = System.getProperty("show-config");
                } catch (RuntimeException e) {
                    a((Exception) r0);
                    throw e;
                } catch (Exception e2) {
                    a((Exception) r0);
                    throw new RuntimeException(e2);
                }
            } catch (SecurityException unused) {
                str = null;
            }
            this.f1416b = Level.OFF;
            if (str != null) {
                this.f1416b = com.a.a.b.c.a.a(str, Level.OFF);
            }
        } catch (SecurityException e3) {
            System.err.println(e3.getLocalizedMessage());
        }
        a();
        String b2 = b();
        if (b2 != null) {
            g(b2);
        } else {
            String c2 = c();
            if (c2 != null) {
                g(c2);
            }
        }
        d();
        r0 = this;
        r0.e();
    }

    private static void a(Exception exc) {
        System.err.println("Could not initialize configuration for Flying Saucer library. Message is: " + exc.getMessage());
        exc.printStackTrace();
    }

    public static void a(Logger logger) {
        b f = f();
        f.e = logger;
        if (f.d != null) {
            for (LogRecord logRecord : f.d) {
                logger.log(logRecord.getLevel(), logRecord.getMessage());
            }
            f.d = null;
        }
    }

    private void a(Level level, String str) {
        if (this.f1416b != Level.OFF) {
            if (this.e == null) {
                this.d.add(new LogRecord(level, str));
            } else {
                this.e.log(level, str);
            }
        }
    }

    private void c(String str) {
        if (this.f1416b.intValue() <= Level.INFO.intValue()) {
            a(Level.INFO, str);
        }
    }

    private void d(String str) {
        if (this.f1416b.intValue() <= Level.WARNING.intValue()) {
            a(Level.WARNING, str);
        }
    }

    private void a(String str, Throwable th) {
        d(str);
        th.printStackTrace();
    }

    private void e(String str) {
        if (this.f1416b.intValue() <= Level.FINE.intValue()) {
            a(Level.FINE, str);
        }
    }

    private void f(String str) {
        if (this.f1416b.intValue() <= Level.FINER.intValue()) {
            a(Level.FINER, str);
        }
    }

    private void a() {
        try {
            InputStream resourceAsStream = b.class.getResourceAsStream("/resources/conf/xhtmlrenderer.conf");
            try {
                if (resourceAsStream == null) {
                    System.err.println("WARNING: Flying Saucer: No configuration files found in classpath using URL: /resources/conf/xhtmlrenderer.conf, resorting to hard-coded fallback properties.");
                    this.f1415a = g();
                } else {
                    this.f1415a = new Properties();
                    this.f1415a.load(resourceAsStream);
                    c("Configuration loaded from /resources/conf/xhtmlrenderer.conf");
                }
                if (resourceAsStream == null) {
                    return;
                }
                resourceAsStream.close();
            } finally {
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load properties file for configuration.", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0114 A[Catch: SecurityException -> 0x01f4, TryCatch #4 {SecurityException -> 0x01f4, blocks: (B:2:0x0000, B:4:0x0018, B:6:0x002f, B:8:0x003f, B:9:0x0045, B:10:0x00ee, B:11:0x010b, B:13:0x0114, B:15:0x0129, B:20:0x0158, B:21:0x018c, B:23:0x0195, B:25:0x01aa, B:30:0x01d9, B:35:0x004d, B:36:0x0053, B:38:0x0055, B:42:0x005f, B:45:0x0090, B:58:0x009a, B:61:0x00b3, B:68:0x00bc, B:71:0x00d6, B:55:0x00e3, B:53:0x00ed), top: B:1:0x0000, inners: #6, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0195 A[Catch: SecurityException -> 0x01f4, TryCatch #4 {SecurityException -> 0x01f4, blocks: (B:2:0x0000, B:4:0x0018, B:6:0x002f, B:8:0x003f, B:9:0x0045, B:10:0x00ee, B:11:0x010b, B:13:0x0114, B:15:0x0129, B:20:0x0158, B:21:0x018c, B:23:0x0195, B:25:0x01aa, B:30:0x01d9, B:35:0x004d, B:36:0x0053, B:38:0x0055, B:42:0x005f, B:45:0x0090, B:58:0x009a, B:61:0x00b3, B:68:0x00bc, B:71:0x00d6, B:55:0x00e3, B:53:0x00ed), top: B:1:0x0000, inners: #6, #8 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void g(java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 512
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.d.m.b.g(java.lang.String):void");
    }

    private static String b() {
        try {
            return System.getProperty("xr.conf");
        } catch (SecurityException unused) {
            return null;
        }
    }

    private static String c() {
        try {
            return System.getProperty("user.home") + File.separator + ".flyingsaucer" + File.separator + "local.xhtmlrenderer.conf";
        } catch (SecurityException unused) {
            return null;
        }
    }

    private void d() {
        ArrayList<String> list = Collections.list(this.f1415a.keys());
        Collections.sort(list);
        e("Overriding loaded configuration from System properties.");
        int i = 0;
        for (String str : list) {
            if (str.startsWith("xr.")) {
                try {
                    String property = System.getProperty(str);
                    if (property != null) {
                        this.f1415a.setProperty(str, property);
                        f("  Overrode value for " + str);
                        i++;
                    }
                } catch (SecurityException unused) {
                }
            }
        }
        e("Configuration: " + i + " properties overridden from System properties.");
        try {
            Properties properties = System.getProperties();
            Enumeration keys = properties.keys();
            i = 0;
            while (keys.hasMoreElements()) {
                String str2 = (String) keys.nextElement();
                if (str2.startsWith("xr.") && !this.f1415a.containsKey(str2)) {
                    this.f1415a.put(str2, properties.get(str2));
                    f("  (+) " + str2);
                    i++;
                }
            }
        } catch (SecurityException unused2) {
        }
        e("Configuration: " + i + " FS properties added from System properties.");
    }

    private void e() {
        ArrayList<String> list = Collections.list(this.f1415a.keys());
        Collections.sort(list);
        f("Configuration contains " + this.f1415a.size() + " keys.");
        f("List of configuration properties, after override:");
        for (String str : list) {
            f("  " + str + " = " + this.f1415a.getProperty(str));
        }
        f("Properties list complete.");
    }

    public static String a(String str) {
        b f = f();
        String property = f.f1415a.getProperty(str);
        if (property == null) {
            f.d("CONFIGURATION: no value found for key " + str);
        }
        return property;
    }

    public static String a(String str, String str2) {
        b f = f();
        String property = f.f1415a.getProperty(str);
        String str3 = property == null ? str2 : property;
        String str4 = str3;
        if (str3 == null) {
            f.d("CONFIGURATION: no value found for key " + str + " and no default given.");
        }
        return str4;
    }

    public static Iterator b(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : f().f1415a.keySet()) {
            if (str2.startsWith(str)) {
                arrayList.add(str2);
            }
        }
        return arrayList.iterator();
    }

    public static boolean a(String str, boolean z) {
        String a2 = a(str);
        if (a2 != null) {
            if ("true|false".indexOf(a2) == -1) {
                l.c("Property '" + str + "' was requested as a boolean, but value of '" + a2 + "' is not a boolean. Check configuration.");
                return z;
            }
            return Boolean.valueOf(a2).booleanValue();
        }
        return z;
    }

    public static boolean b(String str, boolean z) {
        return !a(str, false);
    }

    private static synchronized b f() {
        if (c == null) {
            c = new b();
        }
        return c;
    }

    public static Object a(String str, Object obj) {
        b f = f();
        String a2 = a(str);
        if (a2 == null) {
            return obj;
        }
        int lastIndexOf = a2.lastIndexOf(".");
        try {
            String substring = a2.substring(0, lastIndexOf);
            String substring2 = a2.substring(lastIndexOf + 1);
            try {
                Class<?> cls = Class.forName(substring);
                try {
                    try {
                        return cls.getDeclaredField(substring2).get(cls);
                    } catch (IllegalAccessException unused) {
                        f.d("Property for object value constant " + str + ", field is not public: " + substring + "." + substring2);
                        return obj;
                    }
                } catch (NoSuchFieldException unused2) {
                    f.d("Property for object value constant " + str + " is not a FQN: " + substring);
                    return obj;
                }
            } catch (ClassNotFoundException unused3) {
                f.d("Property for object value constant " + str + " is not a FQN: " + substring);
                return obj;
            }
        } catch (IndexOutOfBoundsException unused4) {
            f.d("Property key " + str + " for object value constant is not properly formatted; should be FQN<dot>constant, is " + a2);
            return obj;
        }
    }

    private static Properties g() {
        Properties properties = new Properties();
        properties.setProperty("xr.css.user-agent-default-css", "/resources/css/");
        properties.setProperty("xr.test.files.hamlet", "/demos/browser/xhtml/hamlet.xhtml");
        properties.setProperty("xr.simple-log-format", "{1} {2}:: {5}");
        properties.setProperty("xr.simple-log-format-throwable", "{1} {2}:: {5}");
        properties.setProperty("xr.test-config-byte", "8");
        properties.setProperty("xr.test-config-short", "16");
        properties.setProperty("xr.test-config-int", "100");
        properties.setProperty("xr.test-config-long", "2000");
        properties.setProperty("xr.test-config-float", "3000.25F");
        properties.setProperty("xr.test-config-double", "4000.50D");
        properties.setProperty("xr.test-config-boolean", "true");
        properties.setProperty("xr.util-logging.loggingEnabled", "false");
        properties.setProperty("xr.util-logging.handlers", "java.util.logging.ConsoleHandler");
        properties.setProperty("xr.util-logging.use-parent-handler", "false");
        properties.setProperty("xr.util-logging.java.util.logging.ConsoleHandler.level", "INFO");
        properties.setProperty("xr.util-logging.java.util.logging.ConsoleHandler.formatter", "org.xhtmlrenderer.util.XRSimpleLogFormatter");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.config.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.exception.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.general.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.init.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.load.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.load.xml-entities.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.match.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.cascade.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.css-parse.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.layout.level", "ALL");
        properties.setProperty("xr.util-logging.org.xhtmlrenderer.render.level", "ALL");
        properties.setProperty("xr.load.xml-reader", "default");
        properties.setProperty("xr.load.configure-features", "false");
        properties.setProperty("xr.load.validation", "false");
        properties.setProperty("xr.load.string-interning", "false");
        properties.setProperty("xr.load.namespaces", "false");
        properties.setProperty("xr.load.namespace-prefixes", "false");
        properties.setProperty("xr.layout.whitespace.experimental", "true");
        properties.setProperty("xr.layout.bad-sizing-hack", "false");
        properties.setProperty("xr.renderer.viewport-repaint", "true");
        properties.setProperty("xr.renderer.draw.backgrounds", "true");
        properties.setProperty("xr.renderer.draw.borders", "true");
        properties.setProperty("xr.renderer.debug.box-outlines", "false");
        properties.setProperty("xr.renderer.replace-missing-characters", "false");
        properties.setProperty("xr.renderer.missing-character-replacement", "false");
        properties.setProperty("xr.text.scale", "1.0");
        properties.setProperty("xr.text.aa-smoothing-level", "1");
        properties.setProperty("xr.text.aa-fontsize-threshhold", "25");
        properties.setProperty("xr.text.aa-rendering-hint", "RenderingHints.VALUE_TEXT_ANTIALIAS_HGRB");
        properties.setProperty("xr.cache.stylesheets", "false");
        properties.setProperty("xr.incremental.enabled", "false");
        properties.setProperty("xr.incremental.lazyimage", "false");
        properties.setProperty("xr.incremental.debug.layoutdelay", "0");
        properties.setProperty("xr.incremental.repaint.print-timing", "false");
        properties.setProperty("xr.use.threads", "false");
        properties.setProperty("xr.use.listeners", "true");
        properties.setProperty("xr.image.buffered", "false");
        properties.setProperty("xr.image.scale", "LOW");
        properties.setProperty("xr.image.render-quality", "java.awt.RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR");
        return properties;
    }
}
