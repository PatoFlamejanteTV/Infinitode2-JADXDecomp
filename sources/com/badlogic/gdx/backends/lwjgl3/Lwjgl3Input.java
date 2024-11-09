package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3Input.class */
public interface Lwjgl3Input extends Input, Disposable {
    void windowHandleChanged(long j);

    void update();

    void prepareNext();

    void resetPollingStates();
}
