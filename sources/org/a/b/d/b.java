package org.a.b.d;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/b/d/b.class */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    private Map<Integer, String> f4263a = new HashMap(User32.VK_PLAY);

    /* renamed from: b, reason: collision with root package name */
    private Map<String, Integer> f4264b = new HashMap(User32.VK_PLAY);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(int i, String str) {
        this.f4263a.put(Integer.valueOf(i), str);
        this.f4264b.put(str, Integer.valueOf(i));
    }

    public String a(int i) {
        String str = this.f4263a.get(Integer.valueOf(i));
        if (str != null) {
            return str;
        }
        return ".notdef";
    }

    public final Map<Integer, String> b() {
        return Collections.unmodifiableMap(this.f4263a);
    }
}
