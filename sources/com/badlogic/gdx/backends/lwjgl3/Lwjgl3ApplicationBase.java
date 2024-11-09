package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.lwjgl3.audio.Lwjgl3Audio;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/Lwjgl3ApplicationBase.class */
public interface Lwjgl3ApplicationBase extends Application {
    Lwjgl3Audio createAudio(Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration);

    Lwjgl3Input createInput(Lwjgl3Window lwjgl3Window);
}
