package com.d.m;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/m/l.class */
public class l {

    /* renamed from: b, reason: collision with root package name */
    private static final List<String> f1429b = new ArrayList(20);

    /* renamed from: a, reason: collision with root package name */
    public static final String f1430a = g("com.openhtmltopdf.config");
    private static String c = g("com.openhtmltopdf.exception");
    private static String d = g("com.openhtmltopdf.general");
    private static String e = g("com.openhtmltopdf.init");
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static boolean m;
    private static m n;
    private static volatile Boolean o;

    static {
        g("com.openhtmltopdf.junit");
        f = g("com.openhtmltopdf.load");
        g = g("com.openhtmltopdf.match");
        h = g("com.openhtmltopdf.cascade");
        i = g("com.openhtmltopdf.load.xml-entities");
        j = g("com.openhtmltopdf.css-parse");
        k = g("com.openhtmltopdf.layout");
        l = g("com.openhtmltopdf.render");
        m = true;
    }

    private static String g(String str) {
        f1429b.add(str);
        return str;
    }

    public static List<String> a() {
        return new ArrayList(f1429b);
    }

    public static void a(String str) {
        a(Level.INFO, str);
    }

    public static void a(Level level, String str) {
        a(j, level, str);
    }

    public static void a(Level level, String str, Throwable th) {
        a(j, level, str, th);
    }

    public static void b(String str) {
        b(Level.INFO, str);
    }

    public static void b(Level level, String str) {
        a(i, level, str);
    }

    public static void b(Level level, String str, Throwable th) {
        a(i, level, str, th);
    }

    public static void c(Level level, String str) {
        a(h, level, str);
    }

    public static void c(String str) {
        a(str, (Throwable) null);
    }

    public static void a(String str, Throwable th) {
        a(c, Level.WARNING, str, th);
    }

    public static void d(String str) {
        d(Level.INFO, str);
    }

    public static void d(Level level, String str) {
        a(d, level, str);
    }

    public static void c(Level level, String str, Throwable th) {
        a(d, level, str, th);
    }

    public static void d(Level level, String str, Throwable th) {
        a(e, level, str, th);
    }

    public static void e(String str) {
        e(Level.INFO, str);
    }

    public static void e(Level level, String str) {
        a(f, level, str);
    }

    public static void e(Level level, String str, Throwable th) {
        a(f, level, str, th);
    }

    public static void f(String str) {
        f(Level.INFO, str);
    }

    public static void f(Level level, String str) {
        a(g, level, str);
    }

    public static void g(Level level, String str) {
        a(k, level, str);
    }

    public static void f(Level level, String str, Throwable th) {
        a(k, level, str, th);
    }

    public static void h(Level level, String str) {
        a(l, level, str);
    }

    public static void g(Level level, String str, Throwable th) {
        a(l, level, str, th);
    }

    private static synchronized void a(String str, Level level, String str2) {
        if (m) {
            c();
        }
        if (b()) {
            n.a(str, level, str2);
        }
    }

    private static synchronized void a(String str, Level level, String str2, Throwable th) {
        if (m) {
            c();
        }
        if (b()) {
            n.a(str, level, str2, th);
        }
    }

    private static void c() {
        synchronized (l.class) {
            if (m) {
                if (o == null) {
                    a(b.a("xr.util-logging.loggingEnabled", true));
                }
                if (n == null) {
                    n = new g();
                }
                m = false;
            }
        }
    }

    public static boolean b() {
        return o.booleanValue();
    }

    private static void a(boolean z) {
        o = Boolean.valueOf(z);
    }
}
