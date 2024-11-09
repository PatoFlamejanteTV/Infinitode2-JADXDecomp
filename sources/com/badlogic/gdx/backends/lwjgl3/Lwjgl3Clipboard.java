package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Clipboard;
import org.lwjgl.glfw.GLFW;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Clipboard.class */
public class Lwjgl3Clipboard implements Clipboard {
    @Override // com.badlogic.gdx.utils.Clipboard
    public boolean hasContents() {
        String contents = getContents();
        return (contents == null || contents.isEmpty()) ? false : true;
    }

    @Override // com.badlogic.gdx.utils.Clipboard
    public String getContents() {
        return GLFW.glfwGetClipboardString(((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle());
    }

    @Override // com.badlogic.gdx.utils.Clipboard
    public void setContents(String str) {
        GLFW.glfwSetClipboardString(((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle(), str);
    }
}
