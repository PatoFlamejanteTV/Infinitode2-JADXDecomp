package org.a.c.h.e.a;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/c/h/e/a/f.class */
public final class f extends g {
    private static final Object[][] e = {new Object[]{255, "notequal"}, new Object[]{260, "infinity"}, new Object[]{262, "lessequal"}, new Object[]{263, "greaterequal"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_PAGE_UP), "partialdiff"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_PAGE_DOWN), "summation"}, new Object[]{Integer.valueOf(User32.WM_IME_ENDCOMPOSITION), "product"}, new Object[]{271, "pi"}, new Object[]{272, "integral"}, new Object[]{Integer.valueOf(User32.WM_TIMER), "Omega"}, new Object[]{303, "radical"}, new Object[]{305, "approxequal"}, new Object[]{306, "Delta"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_7), "lozenge"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_SUBTRACT), "Euro"}, new Object[]{360, "apple"}};
    public static final f c = new f();

    public f() {
        for (Object[] objArr : e) {
            a(((Integer) objArr[0]).intValue(), objArr[1].toString());
        }
    }

    @Override // org.a.c.h.e.a.g, org.a.c.h.a.c
    public final org.a.c.b.b f() {
        return null;
    }
}
