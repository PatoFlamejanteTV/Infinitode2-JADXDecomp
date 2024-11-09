package com.a.a.c;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: infinitode-2.jar:com/a/a/c/l.class */
public class l extends e {

    /* renamed from: b, reason: collision with root package name */
    private LinkedList<a> f650b;
    private transient Closeable c;

    /* loaded from: infinitode-2.jar:com/a/a/c/l$a.class */
    public static class a implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private transient Object f651a;

        /* renamed from: b, reason: collision with root package name */
        private String f652b;
        private int c;
        private String d;

        protected a() {
            this.c = -1;
        }

        public a(Object obj, String str) {
            this.c = -1;
            this.f651a = obj;
            if (str == null) {
                throw new NullPointerException("Cannot pass null fieldName");
            }
            this.f652b = str;
        }

        public a(Object obj, int i) {
            this.c = -1;
            this.f651a = obj;
            this.c = i;
        }

        private String a() {
            if (this.d == null) {
                StringBuilder sb = new StringBuilder();
                if (this.f651a == null) {
                    sb.append("UNKNOWN");
                } else {
                    Class<?> cls = this.f651a instanceof Class ? (Class) this.f651a : this.f651a.getClass();
                    int i = 0;
                    while (cls.isArray()) {
                        cls = cls.getComponentType();
                        i++;
                    }
                    sb.append(cls.getName());
                    while (true) {
                        i--;
                        if (i < 0) {
                            break;
                        }
                        sb.append("[]");
                    }
                }
                sb.append('[');
                if (this.f652b != null) {
                    sb.append('\"');
                    sb.append(this.f652b);
                    sb.append('\"');
                } else if (this.c >= 0) {
                    sb.append(this.c);
                } else {
                    sb.append('?');
                }
                sb.append(']');
                this.d = sb.toString();
            }
            return this.d;
        }

        public final String toString() {
            return a();
        }
    }

    public l(Closeable closeable, String str) {
        super(str);
        this.c = closeable;
        if (closeable instanceof com.a.a.b.l) {
            this.f182a = ((com.a.a.b.l) closeable).f();
        }
    }

    public l(Closeable closeable, String str, Throwable th) {
        super(str, th);
        this.c = closeable;
        if (th instanceof com.a.a.b.d) {
            this.f182a = ((com.a.a.b.d) th).a();
        } else if (closeable instanceof com.a.a.b.l) {
            this.f182a = ((com.a.a.b.l) closeable).f();
        }
    }

    public l(Closeable closeable, String str, com.a.a.b.j jVar) {
        super(str, jVar);
        this.c = closeable;
    }

    public static l a(com.a.a.b.l lVar, String str) {
        return new l(lVar, str);
    }

    public static l a(com.a.a.b.l lVar, String str, Throwable th) {
        return new l(lVar, str, th);
    }

    public static l a(com.a.a.b.h hVar, String str) {
        return new l(hVar, str, (Throwable) null);
    }

    public static l a(com.a.a.b.h hVar, String str, Throwable th) {
        return new l(hVar, str, th);
    }

    public static l a(g gVar, String str) {
        return new l(a(gVar), str);
    }

    private static com.a.a.b.l a(g gVar) {
        if (gVar == null) {
            return null;
        }
        return gVar.j();
    }

    public static l a(IOException iOException) {
        return new l(null, String.format("Unexpected IOException (of type %s): %s", iOException.getClass().getName(), com.a.a.c.m.i.g(iOException)));
    }

    public static l a(Throwable th, Object obj, String str) {
        return a(th, new a(obj, str));
    }

    public static l a(Throwable th, Object obj, int i) {
        return a(th, new a(obj, i));
    }

    private static l a(Throwable th, a aVar) {
        l lVar;
        if (th instanceof l) {
            lVar = (l) th;
        } else {
            String g = com.a.a.c.m.i.g(th);
            String str = g;
            if (g == null || str.isEmpty()) {
                str = "(was " + th.getClass().getName() + ")";
            }
            Closeable closeable = null;
            if (th instanceof com.a.a.b.d) {
                Object c = ((com.a.a.b.d) th).c();
                if (c instanceof Closeable) {
                    closeable = (Closeable) c;
                }
            }
            lVar = new l(closeable, str, th);
        }
        lVar.a(aVar);
        return lVar;
    }

    public final l a(Throwable th) {
        initCause(th);
        return this;
    }

    private StringBuilder a(StringBuilder sb) {
        b(sb);
        return sb;
    }

    @Override // com.a.a.c.e
    public final void a(Object obj, String str) {
        a(new a(obj, str));
    }

    private void a(a aVar) {
        if (this.f650b == null) {
            this.f650b = new LinkedList<>();
        }
        if (this.f650b.size() < 1000) {
            this.f650b.addFirst(aVar);
        }
    }

    @Override // com.a.a.b.m, com.a.a.b.d
    @com.a.a.a.p
    public final Object c() {
        return this.c;
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return d();
    }

    @Override // com.a.a.b.m, java.lang.Throwable
    public String getMessage() {
        return d();
    }

    private String d() {
        String message = super.getMessage();
        if (this.f650b == null) {
            return message;
        }
        StringBuilder sb = message == null ? new StringBuilder() : new StringBuilder(message);
        sb.append(" (through reference chain: ");
        StringBuilder a2 = a(sb);
        a2.append(')');
        return a2.toString();
    }

    @Override // com.a.a.b.m, java.lang.Throwable
    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }

    private void b(StringBuilder sb) {
        if (this.f650b == null) {
            return;
        }
        Iterator<a> it = this.f650b.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append("->");
            }
        }
    }
}
