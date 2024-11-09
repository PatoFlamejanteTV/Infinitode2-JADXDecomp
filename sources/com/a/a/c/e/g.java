package com.a.a.c.e;

import com.a.a.c.f.w;
import com.a.a.c.j;
import com.a.a.c.k;
import com.a.a.c.k.b.l;
import com.a.a.c.m.i;
import com.a.a.c.o;
import com.a.a.c.y;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/* loaded from: infinitode-2.jar:com/a/a/c/e/g.class */
public final class g implements Serializable {

    /* renamed from: b, reason: collision with root package name */
    private static final Class<?> f423b = Node.class;
    private static final Class<?> c = Document.class;
    private static final a d;

    /* renamed from: a, reason: collision with root package name */
    public static final g f424a;
    private final Map<String, String> e = new HashMap();
    private final Map<String, Object> f;

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    static {
        a aVar = null;
        try {
            aVar = a.a();
        } catch (Throwable unused) {
        }
        d = aVar;
        f424a = new g();
    }

    protected g() {
        this.e.put("java.sql.Date", "com.fasterxml.jackson.databind.deser.std.DateDeserializers$SqlDateDeserializer");
        this.e.put("java.sql.Timestamp", "com.fasterxml.jackson.databind.deser.std.DateDeserializers$TimestampDeserializer");
        this.f = new HashMap();
        this.f.put("java.sql.Timestamp", l.f622a);
        this.f.put("java.sql.Date", "com.fasterxml.jackson.databind.ser.std.SqlDateSerializer");
        this.f.put("java.sql.Time", "com.fasterxml.jackson.databind.ser.std.SqlTimeSerializer");
        this.f.put("java.sql.Blob", "com.fasterxml.jackson.databind.ext.SqlBlobSerializer");
        this.f.put("javax.sql.rowset.serial.SerialBlob", "com.fasterxml.jackson.databind.ext.SqlBlobSerializer");
    }

    public final o<?> a(y yVar, j jVar, com.a.a.c.b bVar) {
        Object a2;
        o<?> b2;
        Class<?> b3 = jVar.b();
        if (a(b3, f423b)) {
            return (o) a("com.fasterxml.jackson.databind.ext.DOMSerializer", jVar);
        }
        if (d != null && (b2 = d.b(b3)) != null) {
            return b2;
        }
        String name = b3.getName();
        Object obj = this.f.get(name);
        if (obj != null) {
            if (obj instanceof o) {
                return (o) obj;
            }
            return (o) a((String) obj, jVar);
        }
        if ((!name.startsWith("javax.xml.") && !a(b3, "javax.xml.")) || (a2 = a("com.fasterxml.jackson.databind.ext.CoreXMLSerializers", jVar)) == null) {
            return null;
        }
        return ((w.a) a2).m();
    }

    public final k<?> a(j jVar, com.a.a.c.f fVar, com.a.a.c.b bVar) {
        Object a2;
        k<?> a3;
        Class<?> b2 = jVar.b();
        if (d != null && (a3 = d.a(b2)) != null) {
            return a3;
        }
        if (a(b2, f423b)) {
            return (k) a("com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer", jVar);
        }
        if (a(b2, c)) {
            return (k) a("com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer", jVar);
        }
        String name = b2.getName();
        String str = this.e.get(name);
        if (str != null) {
            return (k) a(str, jVar);
        }
        if ((!name.startsWith("javax.xml.") && !a(b2, "javax.xml.")) || (a2 = a("com.fasterxml.jackson.databind.ext.CoreXMLDeserializers", jVar)) == null) {
            return null;
        }
        return ((w.a) a2).e();
    }

    private static boolean a(Class<?> cls, Class<?> cls2) {
        return cls2 != null && cls2.isAssignableFrom(cls);
    }

    private Object a(String str, j jVar) {
        try {
            return a(Class.forName(str), jVar);
        } catch (Throwable th) {
            throw new IllegalStateException("Failed to find class `" + str + "` for handling values of type " + i.b(jVar) + ", problem: (" + th.getClass().getName() + ") " + th.getMessage());
        }
    }

    private static Object a(Class<?> cls, j jVar) {
        try {
            return i.b((Class) cls, false);
        } catch (Throwable th) {
            throw new IllegalStateException("Failed to create instance of `" + cls.getName() + "` for handling values of type " + i.b(jVar) + ", problem: (" + th.getClass().getName() + ") " + th.getMessage());
        }
    }

    private static boolean a(Class<?> cls, String str) {
        Class<? super Object> superclass = cls.getSuperclass();
        while (true) {
            Class<? super Object> cls2 = superclass;
            if (cls2 == null || cls2 == Object.class) {
                return false;
            }
            if (!cls2.getName().startsWith(str)) {
                superclass = cls2.getSuperclass();
            } else {
                return true;
            }
        }
    }
}
