package org.a.c.h.e.a;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/c/h/e/a/c.class */
public abstract class c implements org.a.c.h.a.c {

    /* renamed from: a, reason: collision with root package name */
    protected final Map<Integer, String> f4518a = new HashMap(User32.VK_PLAY);

    /* renamed from: b, reason: collision with root package name */
    protected final Map<String, Integer> f4519b = new HashMap(User32.VK_PLAY);
    private Set<String> c;

    public abstract String a();

    public static c a(org.a.c.b.j jVar) {
        if (org.a.c.b.j.dt.equals(jVar)) {
            return h.c;
        }
        if (org.a.c.b.j.ec.equals(jVar)) {
            return k.c;
        }
        if (org.a.c.b.j.cd.equals(jVar)) {
            return g.d;
        }
        if (org.a.c.b.j.cc.equals(jVar)) {
            return e.c;
        }
        return null;
    }

    public final Map<Integer, String> d() {
        return Collections.unmodifiableMap(this.f4518a);
    }

    public final Map<String, Integer> e() {
        return Collections.unmodifiableMap(this.f4519b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(int i, String str) {
        this.f4518a.put(Integer.valueOf(i), str);
        if (!this.f4519b.containsKey(str)) {
            this.f4519b.put(str, Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(int i, String str) {
        Integer num;
        String str2 = this.f4518a.get(Integer.valueOf(i));
        if (str2 != null && (num = this.f4519b.get(str2)) != null && num.intValue() == i) {
            this.f4519b.remove(str2);
        }
        this.f4519b.put(str, Integer.valueOf(i));
        this.f4518a.put(Integer.valueOf(i), str);
    }

    public final boolean a(String str) {
        if (this.c == null) {
            synchronized (this) {
                this.c = new HashSet(this.f4518a.values());
            }
        }
        return this.c.contains(str);
    }

    public final String a(int i) {
        String str = this.f4518a.get(Integer.valueOf(i));
        if (str != null) {
            return str;
        }
        return ".notdef";
    }
}
