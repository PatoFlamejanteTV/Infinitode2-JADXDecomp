package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.systems.ParticleSystem;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ParticlesCanvas.class */
public class ParticlesCanvas extends Actor {
    public boolean scissors;
    private final Array<ParticleConfig> j = new Array<>(true, 1, ParticleConfig.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ParticlesCanvas$ParticleConfig.class */
    public static class ParticleConfig {
        public ParticleEffect effect;
        public float x;
        public float y;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        float x = getX();
        float y = getY();
        if (this.j.size != 0) {
            boolean z = false;
            if (this.scissors) {
                batch.flush();
                z = clipBegin();
            }
            batch.setColor(Color.WHITE);
            for (int i = this.j.size - 1; i >= 0; i--) {
                ParticleConfig particleConfig = this.j.items[i];
                if (!particleConfig.effect.isComplete()) {
                    particleConfig.effect.setPosition(x + particleConfig.x, y + particleConfig.y);
                    Array.ArrayIterator<ParticleEmitter> it = particleConfig.effect.getEmitters().iterator();
                    while (it.hasNext()) {
                        it.next().getTransparency().setHigh(f);
                    }
                    particleConfig.effect.update(Gdx.graphics.getDeltaTime());
                    particleConfig.effect.draw(batch);
                } else {
                    this.j.removeIndex(i);
                    particleConfig.effect.reset();
                    ParticleSystem.freeParticle(particleConfig.effect);
                }
            }
            batch.setColor(Color.WHITE);
            batch.flush();
            if (z) {
                clipEnd();
            }
            batch.setBlendFunction(770, 771);
        }
    }

    public ParticleConfig addParticle(ParticleEffect particleEffect, float f, float f2) {
        return addParticleForeground(particleEffect, f, f2, false);
    }

    public ParticleConfig addParticleForeground(ParticleEffect particleEffect, float f, float f2, boolean z) {
        ParticleConfig particleConfig = new ParticleConfig();
        particleConfig.effect = particleEffect;
        particleConfig.x = f;
        particleConfig.y = f2;
        if (z) {
            this.j.insert(0, particleConfig);
        } else {
            this.j.add(particleConfig);
        }
        particleEffect.start();
        return particleConfig;
    }

    public void removeParticle(ParticleEffect particleEffect) {
        for (int i = 0; i < this.j.size; i++) {
            if (this.j.items[i].effect == particleEffect) {
                particleEffect.reset();
                this.j.removeIndex(i);
                ParticleSystem.freeParticle(particleEffect);
                return;
            }
        }
    }

    public void clearParticles() {
        for (int i = this.j.size - 1; i >= 0; i--) {
            ParticleConfig particleConfig = this.j.items[i];
            this.j.removeIndex(i);
            particleConfig.effect.reset();
            ParticleSystem.freeParticle(particleConfig.effect);
        }
    }
}
