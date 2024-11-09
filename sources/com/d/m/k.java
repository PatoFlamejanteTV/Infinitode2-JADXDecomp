package com.d.m;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: infinitode-2.jar:com/d/m/k.class */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private PrintWriter f1427a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1428b = true;
    private static k c;
    private static k d;

    public k(OutputStream outputStream) {
        this.f1427a = null;
        this.f1427a = new PrintWriter(outputStream);
    }

    public final void a(Object obj) {
        a(obj, false);
    }

    private void a(Object obj, boolean z) {
        if (obj == null) {
            b("null");
            return;
        }
        if (obj instanceof Object[]) {
            a((Object[]) obj);
            return;
        }
        if (obj instanceof int[]) {
            a((int[]) obj);
        }
        if (obj instanceof String) {
            a((String) obj, false);
            return;
        }
        if (obj instanceof Exception) {
            b(a((Exception) obj));
            return;
        }
        if (obj instanceof Vector) {
            a((Vector) obj);
            return;
        }
        if (obj instanceof Hashtable) {
            a((Hashtable) obj);
            return;
        }
        if (obj instanceof Date) {
            a((Date) obj);
        } else if (obj instanceof Calendar) {
            a((Calendar) obj);
        } else {
            a(obj.toString(), false);
        }
    }

    private void a(Vector vector) {
        b("vector: size=" + vector.size());
        for (int i = 0; i < vector.size(); i++) {
            b(vector.elementAt(i).toString());
        }
    }

    private void a(Object[] objArr) {
        a((Object) ("array: size=" + objArr.length));
        for (Object obj : objArr) {
            a(SequenceUtils.SPACE + obj.toString(), false);
        }
    }

    private void a(int[] iArr) {
        a((Object) ("array: size=" + iArr.length));
        for (int i : iArr) {
            a(SequenceUtils.SPACE + i, false);
        }
    }

    private void a(Hashtable hashtable) {
        a((Object) ("hashtable size=" + hashtable.size()));
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            a((Object) (str + " = "));
            a((Object) hashtable.get(str).toString());
        }
    }

    private void a(Date date) {
        a((Object) DateFormat.getDateTimeInstance(1, 1).format(date));
    }

    private void a(Calendar calendar) {
        a((Object) calendar.getTime());
    }

    public final void a(PrintWriter printWriter) {
        this.f1427a = printWriter;
    }

    private void b(String str) {
        a(str, true);
    }

    private void a(String str, boolean z) {
        if (!this.f1428b) {
            return;
        }
        if (z) {
            if (this.f1427a == null) {
                System.out.println(str);
                return;
            } else {
                this.f1427a.println(str);
                return;
            }
        }
        if (this.f1427a == null) {
            System.out.print(str);
        } else {
            this.f1427a.print(str);
        }
    }

    private static String a(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exc.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static void b(Object obj) {
        a();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        d.a(printWriter);
        d.a(obj);
        printWriter.flush();
        if (l.b()) {
            l.d(stringWriter.getBuffer().toString());
        }
    }

    private static void a() {
        if (c == null) {
            c = new k(System.out);
        }
        if (d == null) {
            d = new k(System.out);
        }
    }
}
