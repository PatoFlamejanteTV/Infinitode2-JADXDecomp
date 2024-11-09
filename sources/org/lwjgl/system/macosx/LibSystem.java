package org.lwjgl.system.macosx;

import org.lwjgl.system.Library;
import org.lwjgl.system.SharedLibrary;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/LibSystem.class */
public final class LibSystem {
    private static final SharedLibrary SYSTEM = Library.loadNative(LibSystem.class, "org.lwjgl", "System");

    public static SharedLibrary getLibrary() {
        return SYSTEM;
    }

    private LibSystem() {
        throw new UnsupportedOperationException();
    }
}
