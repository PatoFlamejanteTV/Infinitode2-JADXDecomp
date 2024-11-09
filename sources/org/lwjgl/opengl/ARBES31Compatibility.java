package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBES31Compatibility.class */
public class ARBES31Compatibility {
    static {
        GL.initialize();
    }

    protected ARBES31Compatibility() {
        throw new UnsupportedOperationException();
    }

    public static void glMemoryBarrierByRegion(@NativeType("GLbitfield") int i) {
        GL45C.glMemoryBarrierByRegion(i);
    }
}
