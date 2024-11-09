package com.d.m;

import com.d.h.w;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: infinitode-2.jar:com/d/m/g.class */
public class g implements m {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f1422a = true;

    @Override // com.d.m.m
    public final void a(String str, Level level, String str2) {
        if (f1422a) {
            a();
        }
        a(str).log(level, str2);
    }

    @Override // com.d.m.m
    public final void a(String str, Level level, String str2, Throwable th) {
        if (f1422a) {
            a();
        }
        a(str).log(level, str2, th);
    }

    private static Logger a(String str) {
        return Logger.getLogger(str);
    }

    private static void a() {
        Properties b2;
        synchronized (g.class) {
            if (f1422a) {
                f1422a = false;
                try {
                    try {
                        b2 = b();
                    } catch (IOException e) {
                        throw new w.a("Could not initialize logs. " + e.getLocalizedMessage(), (Throwable) e);
                    } catch (SecurityException unused) {
                    }
                    if (!l.b()) {
                        b.a(Logger.getLogger(l.f1430a));
                    } else {
                        a(b2);
                        b.a(Logger.getLogger(l.f1430a));
                    }
                } catch (FileNotFoundException e2) {
                    throw new w.a("Could not initialize logs. " + e2.getLocalizedMessage(), (Throwable) e2);
                }
            }
        }
    }

    private static Properties b() {
        Iterator b2 = b.b("xr.util-logging.");
        Properties properties = new Properties();
        while (b2.hasNext()) {
            String str = (String) b2.next();
            properties.setProperty(str.substring("xr.util-logging.".length()), b.a(str));
        }
        return properties;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.util.Map] */
    private static void a(Properties properties) {
        List c = c();
        a(properties, c);
        Enumeration keys = properties.keys();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            String property = properties.getProperty(str);
            if (str.endsWith("level")) {
                a(str.substring(0, str.lastIndexOf(".")), property);
            } else if (str.endsWith("handlers")) {
                hashMap = a(c, property);
            } else if (str.endsWith("formatter")) {
                hashMap2.put(str.substring(0, str.length() - 10), property);
            }
        }
        for (String str2 : hashMap2.keySet()) {
            a(hashMap, str2, (String) hashMap2.get(str2));
        }
    }

    private static void a(Properties properties, List list) {
        String property = properties.getProperty("use-parent-handler");
        boolean booleanValue = property == null ? false : Boolean.valueOf(property).booleanValue();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Logger) it.next()).setUseParentHandlers(booleanValue);
        }
    }

    private static void a(Map map, String str, String str2) {
        Handler handler = (Handler) map.get(str);
        if (handler != null) {
            try {
                handler.setFormatter((Formatter) Class.forName(str2).newInstance());
            } catch (ClassNotFoundException unused) {
                throw new w.a("Could not initialize logging properties; Formatter class not found: " + str2);
            } catch (IllegalAccessException unused2) {
                throw new w.a("Could not initialize logging properties; Can't instantiate Formatter class (IllegalAccessException): " + str2);
            } catch (InstantiationException unused3) {
                throw new w.a("Could not initialize logging properties; Can't instantiate Formatter class (InstantiationException): " + str2);
            }
        }
    }

    private static List c() {
        List<String> a2 = l.a();
        ArrayList arrayList = new ArrayList(a2.size());
        Iterator<String> it = a2.iterator();
        while (it.hasNext()) {
            arrayList.add(Logger.getLogger(it.next()));
        }
        return arrayList;
    }

    private static Map a(List list, String str) {
        String[] split = str.split(SequenceUtils.SPACE);
        HashMap hashMap = new HashMap(split.length);
        for (String str2 : split) {
            try {
                Handler handler = (Handler) Class.forName(str2).newInstance();
                hashMap.put(str2, handler);
                handler.setLevel(com.a.a.b.c.a.a(b.a("xr.util-logging." + str2 + ".level", "INFO"), Level.INFO));
            } catch (ClassNotFoundException unused) {
                throw new w.a("Could not initialize logging properties; Handler class not found: " + str2);
            } catch (IllegalAccessException unused2) {
                throw new w.a("Could not initialize logging properties; Can't instantiate Handler class (IllegalAccessException): " + str2);
            } catch (InstantiationException unused3) {
                throw new w.a("Could not initialize logging properties; Can't instantiate Handler class (InstantiationException): " + str2);
            }
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Logger logger = (Logger) it.next();
            Iterator it2 = hashMap.values().iterator();
            while (it2.hasNext()) {
                logger.addHandler((Handler) it2.next());
            }
        }
        return hashMap;
    }

    private static void a(String str, String str2) {
        Logger.getLogger(str).setLevel(com.a.a.b.c.a.a(str2, Level.OFF));
    }
}
