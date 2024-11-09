package org.a.b.b;

import java.util.HashMap;
import java.util.Map;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/b/b/c.class */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f4196a;

    /* renamed from: b, reason: collision with root package name */
    private final Map<Integer, Integer> f4197b = new HashMap(User32.VK_PLAY);
    private final Map<Integer, Integer> c = new HashMap(User32.VK_PLAY);
    private final Map<String, Integer> d = new HashMap(User32.VK_PLAY);
    private final Map<Integer, Integer> e = new HashMap();
    private final Map<Integer, String> f = new HashMap(User32.VK_PLAY);

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(boolean z) {
        this.f4196a = z;
    }

    public final boolean a() {
        return this.f4196a;
    }

    public final void a(int i, int i2, String str) {
        if (this.f4196a) {
            throw new IllegalStateException("Not a Type 1-equivalent font");
        }
        this.f4197b.put(Integer.valueOf(i2), Integer.valueOf(i));
        this.c.put(Integer.valueOf(i), Integer.valueOf(i2));
        this.d.put(str, Integer.valueOf(i2));
        this.f.put(Integer.valueOf(i), str);
    }

    public final void a(int i, int i2) {
        if (!this.f4196a) {
            throw new IllegalStateException("Not a CIDFont");
        }
        this.f4197b.put(Integer.valueOf(i2), Integer.valueOf(i));
        this.e.put(Integer.valueOf(i), Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(int i) {
        if (this.f4196a) {
            throw new IllegalStateException("Not a Type 1-equivalent font");
        }
        Integer num = this.c.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b(int i) {
        if (this.f4196a) {
            throw new IllegalStateException("Not a Type 1-equivalent font");
        }
        Integer num = this.f4197b.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public int c(int i) {
        if (!this.f4196a) {
            throw new IllegalStateException("Not a CIDFont");
        }
        Integer num = this.f4197b.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(String str) {
        if (this.f4196a) {
            throw new IllegalStateException("Not a Type 1-equivalent font");
        }
        Integer num = this.d.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }
}
