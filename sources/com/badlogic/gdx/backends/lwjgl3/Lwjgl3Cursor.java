package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Cursor.class */
public class Lwjgl3Cursor implements Cursor {
    static final Array<Lwjgl3Cursor> cursors = new Array<>();
    static final Map<Cursor.SystemCursor, Long> systemCursors = new HashMap();
    private static int inputModeBeforeNoneCursor = -1;
    final Lwjgl3Window window;
    Pixmap pixmapCopy;
    GLFWImage glfwImage;
    final long glfwCursor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Lwjgl3Cursor(Lwjgl3Window lwjgl3Window, Pixmap pixmap, int i, int i2) {
        this.window = lwjgl3Window;
        if (pixmap.getFormat() != Pixmap.Format.RGBA8888) {
            throw new GdxRuntimeException("Cursor image pixmap is not in RGBA8888 format.");
        }
        if ((pixmap.getWidth() & (pixmap.getWidth() - 1)) != 0) {
            throw new GdxRuntimeException("Cursor image pixmap width of " + pixmap.getWidth() + " is not a power-of-two greater than zero.");
        }
        if ((pixmap.getHeight() & (pixmap.getHeight() - 1)) != 0) {
            throw new GdxRuntimeException("Cursor image pixmap height of " + pixmap.getHeight() + " is not a power-of-two greater than zero.");
        }
        if (i < 0 || i >= pixmap.getWidth()) {
            throw new GdxRuntimeException("xHotspot coordinate of " + i + " is not within image width bounds: [0, " + pixmap.getWidth() + ").");
        }
        if (i2 < 0 || i2 >= pixmap.getHeight()) {
            throw new GdxRuntimeException("yHotspot coordinate of " + i2 + " is not within image height bounds: [0, " + pixmap.getHeight() + ").");
        }
        this.pixmapCopy = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), Pixmap.Format.RGBA8888);
        this.pixmapCopy.setBlending(Pixmap.Blending.None);
        this.pixmapCopy.drawPixmap(pixmap, 0, 0);
        this.glfwImage = GLFWImage.malloc();
        this.glfwImage.width(this.pixmapCopy.getWidth());
        this.glfwImage.height(this.pixmapCopy.getHeight());
        this.glfwImage.pixels(this.pixmapCopy.getPixels());
        this.glfwCursor = GLFW.glfwCreateCursor(this.glfwImage, i, i2);
        cursors.add(this);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.pixmapCopy == null) {
            throw new GdxRuntimeException("Cursor already disposed");
        }
        cursors.removeValue(this, true);
        this.pixmapCopy.dispose();
        this.pixmapCopy = null;
        this.glfwImage.free();
        GLFW.glfwDestroyCursor(this.glfwCursor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void dispose(Lwjgl3Window lwjgl3Window) {
        for (int i = cursors.size - 1; i >= 0; i--) {
            if (cursors.get(i).window.equals(lwjgl3Window)) {
                cursors.removeIndex(i).dispose();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void disposeSystemCursors() {
        Iterator<Long> it = systemCursors.values().iterator();
        while (it.hasNext()) {
            GLFW.glfwDestroyCursor(it.next().longValue());
        }
        systemCursors.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setSystemCursor(long j, Cursor.SystemCursor systemCursor) {
        long glfwCreateStandardCursor;
        if (systemCursor == Cursor.SystemCursor.None) {
            if (inputModeBeforeNoneCursor == -1) {
                inputModeBeforeNoneCursor = GLFW.glfwGetInputMode(j, GLFW.GLFW_CURSOR);
            }
            GLFW.glfwSetInputMode(j, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
            return;
        }
        if (inputModeBeforeNoneCursor != -1) {
            GLFW.glfwSetInputMode(j, GLFW.GLFW_CURSOR, inputModeBeforeNoneCursor);
            inputModeBeforeNoneCursor = -1;
        }
        Long l = systemCursors.get(systemCursor);
        Long l2 = l;
        if (l == null) {
            if (systemCursor == Cursor.SystemCursor.Arrow) {
                glfwCreateStandardCursor = GLFW.glfwCreateStandardCursor(221185);
            } else if (systemCursor == Cursor.SystemCursor.Crosshair) {
                glfwCreateStandardCursor = GLFW.glfwCreateStandardCursor(221187);
            } else if (systemCursor == Cursor.SystemCursor.Hand) {
                glfwCreateStandardCursor = GLFW.glfwCreateStandardCursor(221188);
            } else if (systemCursor == Cursor.SystemCursor.HorizontalResize) {
                glfwCreateStandardCursor = GLFW.glfwCreateStandardCursor(221189);
            } else if (systemCursor == Cursor.SystemCursor.VerticalResize) {
                glfwCreateStandardCursor = GLFW.glfwCreateStandardCursor(221190);
            } else if (systemCursor == Cursor.SystemCursor.Ibeam) {
                glfwCreateStandardCursor = GLFW.glfwCreateStandardCursor(221186);
            } else if (systemCursor == Cursor.SystemCursor.NWSEResize) {
                glfwCreateStandardCursor = GLFW.glfwCreateStandardCursor(GLFW.GLFW_RESIZE_NWSE_CURSOR);
            } else if (systemCursor == Cursor.SystemCursor.NESWResize) {
                glfwCreateStandardCursor = GLFW.glfwCreateStandardCursor(GLFW.GLFW_RESIZE_NESW_CURSOR);
            } else if (systemCursor == Cursor.SystemCursor.AllResize) {
                glfwCreateStandardCursor = GLFW.glfwCreateStandardCursor(GLFW.GLFW_RESIZE_ALL_CURSOR);
            } else if (systemCursor == Cursor.SystemCursor.NotAllowed) {
                glfwCreateStandardCursor = GLFW.glfwCreateStandardCursor(GLFW.GLFW_NOT_ALLOWED_CURSOR);
            } else {
                throw new GdxRuntimeException("Unknown system cursor " + systemCursor);
            }
            if (glfwCreateStandardCursor == 0) {
                return;
            }
            l2 = Long.valueOf(glfwCreateStandardCursor);
            systemCursors.put(systemCursor, l2);
        }
        GLFW.glfwSetCursor(j, l2.longValue());
    }
}
