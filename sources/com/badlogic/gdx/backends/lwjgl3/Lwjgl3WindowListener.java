package com.badlogic.gdx.backends.lwjgl3;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3WindowListener.class */
public interface Lwjgl3WindowListener {
    void created(Lwjgl3Window lwjgl3Window);

    void iconified(boolean z);

    void maximized(boolean z);

    void focusLost();

    void focusGained();

    boolean closeRequested();

    void filesDropped(String[] strArr);

    void refreshRequested();
}
