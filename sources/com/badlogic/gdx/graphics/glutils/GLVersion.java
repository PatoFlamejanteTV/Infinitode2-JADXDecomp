package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/GLVersion.class */
public class GLVersion {
    private int majorVersion;
    private int minorVersion;
    private int releaseVersion;
    private final String versionString;
    private final String vendorString;
    private final String rendererString;
    private final Type type;
    private final String TAG = "GLVersion";

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/GLVersion$Type.class */
    public enum Type {
        OpenGL,
        GLES,
        WebGL,
        NONE
    }

    public GLVersion(Application.ApplicationType applicationType, String str, String str2, String str3) {
        if (applicationType == Application.ApplicationType.Android) {
            this.type = Type.GLES;
        } else if (applicationType == Application.ApplicationType.iOS) {
            this.type = Type.GLES;
        } else if (applicationType == Application.ApplicationType.Desktop) {
            this.type = Type.OpenGL;
        } else if (applicationType == Application.ApplicationType.Applet) {
            this.type = Type.OpenGL;
        } else if (applicationType == Application.ApplicationType.WebGL) {
            this.type = Type.WebGL;
        } else {
            this.type = Type.NONE;
        }
        if (this.type == Type.GLES) {
            extractVersion("OpenGL ES (\\d(\\.\\d){0,2})", str);
        } else if (this.type == Type.WebGL) {
            extractVersion("WebGL (\\d(\\.\\d){0,2})", str);
        } else if (this.type == Type.OpenGL) {
            extractVersion("(\\d(\\.\\d){0,2})", str);
        } else {
            this.majorVersion = -1;
            this.minorVersion = -1;
            this.releaseVersion = -1;
            str2 = "";
            str3 = "";
        }
        this.versionString = str;
        this.vendorString = str2;
        this.rendererString = str3;
    }

    private void extractVersion(String str, String str2) {
        Matcher matcher = Pattern.compile(str).matcher(str2);
        if (matcher.find()) {
            String[] split = matcher.group(1).split("\\.");
            this.majorVersion = parseInt(split[0], 2);
            this.minorVersion = split.length < 2 ? 0 : parseInt(split[1], 0);
            this.releaseVersion = split.length < 3 ? 0 : parseInt(split[2], 0);
            return;
        }
        Gdx.app.log("GLVersion", "Invalid version string: " + str2);
        this.majorVersion = 2;
        this.minorVersion = 0;
        this.releaseVersion = 0;
    }

    private int parseInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            Gdx.app.error("libGDX GL", "Error parsing number: " + str + ", assuming: " + i);
            return i;
        }
    }

    public Type getType() {
        return this.type;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public int getMinorVersion() {
        return this.minorVersion;
    }

    public int getReleaseVersion() {
        return this.releaseVersion;
    }

    public String getVersionString() {
        return this.versionString;
    }

    public String getVendorString() {
        return this.vendorString;
    }

    public String getRendererString() {
        return this.rendererString;
    }

    public boolean isVersionEqualToOrHigher(int i, int i2) {
        if (this.majorVersion <= i) {
            return this.majorVersion == i && this.minorVersion >= i2;
        }
        return true;
    }

    public String getDebugVersionString() {
        return "Type: " + this.type + "\nVersion: " + this.majorVersion + ":" + this.minorVersion + ":" + this.releaseVersion + "\nVendor: " + this.vendorString + "\nRenderer: " + this.rendererString;
    }
}
