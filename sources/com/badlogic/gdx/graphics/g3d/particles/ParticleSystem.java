package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleSystem.class */
public final class ParticleSystem implements RenderableProvider {
    private static ParticleSystem instance;
    private Array<ParticleBatch<?>> batches = new Array<>();
    private Array<ParticleEffect> effects = new Array<>();

    @Deprecated
    public static ParticleSystem get() {
        if (instance == null) {
            instance = new ParticleSystem();
        }
        return instance;
    }

    public final void add(ParticleBatch<?> particleBatch) {
        this.batches.add(particleBatch);
    }

    public final void add(ParticleEffect particleEffect) {
        this.effects.add(particleEffect);
    }

    public final void remove(ParticleEffect particleEffect) {
        this.effects.removeValue(particleEffect, true);
    }

    public final void removeAll() {
        this.effects.clear();
    }

    public final void update() {
        Array.ArrayIterator<ParticleEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            it.next().update();
        }
    }

    public final void updateAndDraw() {
        Array.ArrayIterator<ParticleEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            ParticleEffect next = it.next();
            next.update();
            next.draw();
        }
    }

    public final void update(float f) {
        Array.ArrayIterator<ParticleEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            it.next().update(f);
        }
    }

    public final void updateAndDraw(float f) {
        Array.ArrayIterator<ParticleEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            ParticleEffect next = it.next();
            next.update(f);
            next.draw();
        }
    }

    public final void begin() {
        Array.ArrayIterator<ParticleBatch<?>> it = this.batches.iterator();
        while (it.hasNext()) {
            it.next().begin();
        }
    }

    public final void draw() {
        Array.ArrayIterator<ParticleEffect> it = this.effects.iterator();
        while (it.hasNext()) {
            it.next().draw();
        }
    }

    public final void end() {
        Array.ArrayIterator<ParticleBatch<?>> it = this.batches.iterator();
        while (it.hasNext()) {
            it.next().end();
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public final void getRenderables(Array<Renderable> array, Pool<Renderable> pool) {
        Array.ArrayIterator<ParticleBatch<?>> it = this.batches.iterator();
        while (it.hasNext()) {
            it.next().getRenderables(array, pool);
        }
    }

    public final Array<ParticleBatch<?>> getBatches() {
        return this.batches;
    }
}
