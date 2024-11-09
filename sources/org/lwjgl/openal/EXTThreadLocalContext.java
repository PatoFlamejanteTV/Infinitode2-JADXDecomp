package org.lwjgl.openal;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/EXTThreadLocalContext.class */
public class EXTThreadLocalContext {
    protected EXTThreadLocalContext() {
        throw new UnsupportedOperationException();
    }

    @NativeType("ALCboolean")
    public static boolean alcSetThreadContext(@NativeType("ALCcontext *") long j) {
        long j2 = ALC.getICD().alcSetThreadContext;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.invokePZ(j, j2);
    }

    @NativeType("ALCcontext *")
    public static long alcGetThreadContext() {
        long j = ALC.getICD().alcGetThreadContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokeP(j);
    }
}
