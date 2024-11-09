package org.lwjgl.system.macosx;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.SharedLibrary;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/MacOSXLibrary.class */
public abstract class MacOSXLibrary extends SharedLibrary.Default {
    /* JADX INFO: Access modifiers changed from: protected */
    public MacOSXLibrary(String str, long j) {
        super(str, j);
    }

    public static MacOSXLibrary getWithIdentifier(String str) {
        APIUtil.apiLog("Loading library: " + str);
        MacOSXLibraryBundle withIdentifier = MacOSXLibraryBundle.getWithIdentifier(str);
        APIUtil.apiLogMore("Success");
        return withIdentifier;
    }

    public static MacOSXLibrary create(String str) {
        return str.endsWith(".framework") ? MacOSXLibraryBundle.create(str) : new MacOSXLibraryDL(str);
    }
}
