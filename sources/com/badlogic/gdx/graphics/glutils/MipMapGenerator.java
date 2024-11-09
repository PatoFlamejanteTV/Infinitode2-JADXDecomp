package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/MipMapGenerator.class */
public class MipMapGenerator {
    private static boolean useHWMipMap = true;

    private MipMapGenerator() {
    }

    public static void setUseHardwareMipMap(boolean z) {
        useHWMipMap = z;
    }

    public static void generateMipMap(Pixmap pixmap, int i, int i2) {
        generateMipMap(3553, pixmap, i, i2);
    }

    public static void generateMipMap(int i, Pixmap pixmap, int i2, int i3) {
        if (!useHWMipMap) {
            generateMipMapCPU(i, pixmap, i2, i3);
        } else if (Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.WebGL || Gdx.app.getType() == Application.ApplicationType.iOS) {
            generateMipMapGLES20(i, pixmap);
        } else {
            generateMipMapDesktop(i, pixmap, i2, i3);
        }
    }

    private static void generateMipMapGLES20(int i, Pixmap pixmap) {
        Gdx.gl.glTexImage2D(i, 0, pixmap.getGLInternalFormat(), pixmap.getWidth(), pixmap.getHeight(), 0, pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
        Gdx.gl20.glGenerateMipmap(i);
    }

    private static void generateMipMapDesktop(int i, Pixmap pixmap, int i2, int i3) {
        if (Gdx.graphics.supportsExtension("GL_ARB_framebuffer_object") || Gdx.graphics.supportsExtension("GL_EXT_framebuffer_object") || Gdx.gl20.getClass().getName().equals("com.badlogic.gdx.backends.lwjgl3.Lwjgl3GLES20") || Gdx.gl30 != null) {
            Gdx.gl.glTexImage2D(i, 0, pixmap.getGLInternalFormat(), pixmap.getWidth(), pixmap.getHeight(), 0, pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
            Gdx.gl20.glGenerateMipmap(i);
        } else {
            generateMipMapCPU(i, pixmap, i2, i3);
        }
    }

    private static void generateMipMapCPU(int i, Pixmap pixmap, int i2, int i3) {
        Gdx.gl.glTexImage2D(i, 0, pixmap.getGLInternalFormat(), pixmap.getWidth(), pixmap.getHeight(), 0, pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
        if (Gdx.gl20 == null && i2 != i3) {
            throw new GdxRuntimeException("texture width and height must be square when using mipmapping.");
        }
        int width = pixmap.getWidth() / 2;
        int height = pixmap.getHeight() / 2;
        int i4 = 1;
        while (width > 0 && height > 0) {
            Pixmap pixmap2 = new Pixmap(width, height, pixmap.getFormat());
            pixmap2.setBlending(Pixmap.Blending.None);
            pixmap2.drawPixmap(pixmap, 0, 0, pixmap.getWidth(), pixmap.getHeight(), 0, 0, width, height);
            if (i4 > 1) {
                pixmap.dispose();
            }
            pixmap = pixmap2;
            Gdx.gl.glTexImage2D(i, i4, pixmap.getGLInternalFormat(), pixmap.getWidth(), pixmap.getHeight(), 0, pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
            width = pixmap.getWidth() / 2;
            height = pixmap.getHeight() / 2;
            i4++;
        }
    }
}
