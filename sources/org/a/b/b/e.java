package org.a.b.b;

import java.util.HashMap;
import java.util.Map;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/b/b/e.class */
public abstract class e extends org.a.b.d.b {

    /* renamed from: a, reason: collision with root package name */
    private final Map<Integer, String> f4198a = new HashMap(User32.VK_PLAY);

    @Override // org.a.b.d.b
    public final String a(int i) {
        String str = this.f4198a.get(Integer.valueOf(i));
        if (str == null) {
            return ".notdef";
        }
        return str;
    }

    public final void a(int i, String str) {
        this.f4198a.put(Integer.valueOf(i), str);
        b(i, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(int i, int i2) {
        String a2 = n.a(i2);
        this.f4198a.put(Integer.valueOf(i), a2);
        b(i, a2);
    }
}
