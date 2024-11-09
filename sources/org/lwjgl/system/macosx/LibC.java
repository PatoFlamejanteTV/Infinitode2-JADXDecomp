package org.lwjgl.system.macosx;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/LibC.class */
public class LibC {

    /* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/LibC$Functions.class */
    public static final class Functions {
        public static final long getpid = APIUtil.apiGetFunctionAddress(LibSystem.getLibrary(), "getpid");

        private Functions() {
        }
    }

    protected LibC() {
        throw new UnsupportedOperationException();
    }

    @NativeType("pid_t")
    public static long getpid() {
        return JNI.invokeP(Functions.getpid);
    }
}
