package com.badlogic.gdx;

import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/Screen.class */
public interface Screen extends Disposable {
    void show();

    void render(float f);

    void resize(int i, int i2);

    void pause();

    void resume();

    void hide();

    @Override // com.badlogic.gdx.utils.Disposable
    void dispose();
}
