package com.badlogic.gdx.graphics.g3d.particles.renderers;

import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/renderers/ParticleControllerRenderer.class */
public abstract class ParticleControllerRenderer<D extends ParticleControllerRenderData, T extends ParticleBatch<D>> extends ParticleControllerComponent {
    protected T batch;
    protected D renderData;

    public abstract boolean isCompatible(ParticleBatch<?> particleBatch);

    /* JADX INFO: Access modifiers changed from: protected */
    public ParticleControllerRenderer() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ParticleControllerRenderer(D d) {
        this.renderData = d;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void update() {
        this.batch.draw(this.renderData);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean setBatch(ParticleBatch<?> particleBatch) {
        if (isCompatible(particleBatch)) {
            this.batch = particleBatch;
            return true;
        }
        return false;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleControllerComponent
    public void set(ParticleController particleController) {
        super.set(particleController);
        if (this.renderData != null) {
            this.renderData.controller = this.controller;
        }
    }
}
