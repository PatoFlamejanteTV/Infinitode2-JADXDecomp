package org.lwjgl.openal;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTDeferredUpdates.class */
public class SOFTDeferredUpdates {
    public static final int AL_DEFERRED_UPDATES_SOFT = 49154;

    protected SOFTDeferredUpdates() {
        throw new UnsupportedOperationException();
    }

    @NativeType("ALvoid")
    public static void alDeferUpdatesSOFT() {
        long j = AL.getICD().alDeferUpdatesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(j);
    }

    @NativeType("ALvoid")
    public static void alProcessUpdatesSOFT() {
        long j = AL.getICD().alProcessUpdatesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(j);
    }
}
