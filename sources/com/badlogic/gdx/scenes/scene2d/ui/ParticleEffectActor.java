package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/ParticleEffectActor.class */
public class ParticleEffectActor extends Actor implements Disposable {
    private final ParticleEffect particleEffect;
    protected float lastDelta;
    protected boolean isRunning;
    protected boolean ownsEffect;
    private boolean resetOnStart;
    private boolean autoRemove;

    public ParticleEffectActor(ParticleEffect particleEffect, boolean z) {
        this.particleEffect = particleEffect;
        this.resetOnStart = z;
    }

    public ParticleEffectActor(FileHandle fileHandle, TextureAtlas textureAtlas) {
        this.particleEffect = new ParticleEffect();
        this.particleEffect.load(fileHandle, textureAtlas);
        this.ownsEffect = true;
    }

    public ParticleEffectActor(FileHandle fileHandle, FileHandle fileHandle2) {
        this.particleEffect = new ParticleEffect();
        this.particleEffect.load(fileHandle, fileHandle2);
        this.ownsEffect = true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        this.particleEffect.setPosition(getX(), getY());
        if (this.lastDelta > 0.0f) {
            this.particleEffect.update(this.lastDelta);
            this.lastDelta = 0.0f;
        }
        if (this.isRunning) {
            this.particleEffect.draw(batch);
            this.isRunning = !this.particleEffect.isComplete();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f) {
        super.act(f);
        this.lastDelta += f;
        if (this.autoRemove && this.particleEffect.isComplete()) {
            remove();
        }
    }

    public void start() {
        this.isRunning = true;
        if (this.resetOnStart) {
            this.particleEffect.reset(false);
        }
        this.particleEffect.start();
    }

    public boolean isResetOnStart() {
        return this.resetOnStart;
    }

    public ParticleEffectActor setResetOnStart(boolean z) {
        this.resetOnStart = z;
        return this;
    }

    public boolean isAutoRemove() {
        return this.autoRemove;
    }

    public ParticleEffectActor setAutoRemove(boolean z) {
        this.autoRemove = z;
        return this;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public ParticleEffect getEffect() {
        return this.particleEffect;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void scaleChanged() {
        super.scaleChanged();
        this.particleEffect.scaleEffect(getScaleX(), getScaleY(), getScaleY());
    }

    public void cancel() {
        this.isRunning = true;
    }

    public void allowCompletion() {
        this.particleEffect.allowCompletion();
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.ownsEffect) {
            this.particleEffect.dispose();
        }
    }
}
