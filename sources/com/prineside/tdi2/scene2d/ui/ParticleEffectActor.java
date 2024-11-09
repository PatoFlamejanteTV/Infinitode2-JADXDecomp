package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.scene2d.Actor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/ParticleEffectActor.class */
public class ParticleEffectActor extends Actor implements Disposable {
    private final ParticleEffect j;
    private float k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;

    public ParticleEffectActor(ParticleEffect particleEffect, boolean z) {
        this.j = particleEffect;
        this.n = z;
    }

    public ParticleEffectActor(FileHandle fileHandle, TextureAtlas textureAtlas) {
        this.j = new ParticleEffect();
        this.j.load(fileHandle, textureAtlas);
        this.m = true;
    }

    public ParticleEffectActor(FileHandle fileHandle, FileHandle fileHandle2) {
        this.j = new ParticleEffect();
        this.j.load(fileHandle, fileHandle2);
        this.m = true;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        this.j.setPosition(getX(), getY());
        if (this.k > 0.0f) {
            this.j.update(this.k);
            this.k = 0.0f;
        }
        if (this.l) {
            this.j.draw(batch);
            this.l = !this.j.isComplete();
        }
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void act(float f) {
        super.act(f);
        this.k += f;
        if (this.o && this.j.isComplete()) {
            remove();
        }
    }

    public void start() {
        this.l = true;
        if (this.n) {
            this.j.reset(false);
        }
        this.j.start();
    }

    public boolean isResetOnStart() {
        return this.n;
    }

    public ParticleEffectActor setResetOnStart(boolean z) {
        this.n = z;
        return this;
    }

    public boolean isAutoRemove() {
        return this.o;
    }

    public ParticleEffectActor setAutoRemove(boolean z) {
        this.o = z;
        return this;
    }

    public boolean isRunning() {
        return this.l;
    }

    public ParticleEffect getEffect() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.Actor
    public final void a() {
        super.a();
        this.j.scaleEffect(getScaleX(), getScaleY(), getScaleY());
    }

    public void cancel() {
        this.l = true;
    }

    public void allowCompletion() {
        this.j.allowCompletion();
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.m) {
            this.j.dispose();
        }
    }
}
