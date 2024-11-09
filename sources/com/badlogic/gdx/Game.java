package com.badlogic.gdx;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/Game.class */
public abstract class Game implements ApplicationListener {
    protected Screen screen;

    @Override // com.badlogic.gdx.ApplicationListener
    public void dispose() {
        if (this.screen != null) {
            this.screen.hide();
        }
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void pause() {
        if (this.screen != null) {
            this.screen.pause();
        }
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void resume() {
        if (this.screen != null) {
            this.screen.resume();
        }
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void render() {
        if (this.screen != null) {
            this.screen.render(Gdx.graphics.getDeltaTime());
        }
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void resize(int i, int i2) {
        if (this.screen != null) {
            this.screen.resize(i, i2);
        }
    }

    public void setScreen(Screen screen) {
        if (this.screen != null) {
            this.screen.hide();
        }
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    public Screen getScreen() {
        return this.screen;
    }
}
