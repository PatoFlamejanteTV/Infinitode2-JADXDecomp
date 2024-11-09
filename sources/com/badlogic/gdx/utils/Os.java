package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Os.class */
public enum Os {
    Windows,
    Linux,
    MacOsX,
    Android,
    IOS;

    public final String getJniPlatform() {
        return this == Windows ? "win32" : this == Linux ? "linux" : this == MacOsX ? "mac" : "";
    }

    public final String getLibPrefix() {
        if (this == Linux || this == Android || this == MacOsX) {
            return "lib";
        }
        return "";
    }

    public final String getLibExtension() {
        return this == Windows ? "dll" : this == Linux ? "so" : this == MacOsX ? "dylib" : this == Android ? "so" : "";
    }
}
