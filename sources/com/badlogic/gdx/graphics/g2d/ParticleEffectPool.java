package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEffectPool.class */
public class ParticleEffectPool extends Pool<PooledEffect> {
    private final ParticleEffect effect;

    public ParticleEffectPool(ParticleEffect particleEffect, int i, int i2) {
        super(i, i2);
        this.effect = particleEffect;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.utils.Pool
    public PooledEffect newObject() {
        PooledEffect pooledEffect = new PooledEffect(this.effect);
        pooledEffect.start();
        return pooledEffect;
    }

    @Override // com.badlogic.gdx.utils.Pool
    public void free(PooledEffect pooledEffect) {
        super.free((ParticleEffectPool) pooledEffect);
        pooledEffect.reset(false);
        if (pooledEffect.xSizeScale != this.effect.xSizeScale || pooledEffect.ySizeScale != this.effect.ySizeScale || pooledEffect.motionScale != this.effect.motionScale) {
            Array<ParticleEmitter> emitters = pooledEffect.getEmitters();
            Array<ParticleEmitter> emitters2 = this.effect.getEmitters();
            for (int i = 0; i < emitters.size; i++) {
                ParticleEmitter particleEmitter = emitters.get(i);
                ParticleEmitter particleEmitter2 = emitters2.get(i);
                particleEmitter.matchSize(particleEmitter2);
                particleEmitter.matchMotion(particleEmitter2);
            }
            pooledEffect.xSizeScale = this.effect.xSizeScale;
            pooledEffect.ySizeScale = this.effect.ySizeScale;
            pooledEffect.motionScale = this.effect.motionScale;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/ParticleEffectPool$PooledEffect.class */
    public class PooledEffect extends ParticleEffect {
        PooledEffect(ParticleEffect particleEffect) {
            super(particleEffect);
        }

        public void free() {
            ParticleEffectPool.this.free(this);
        }
    }
}
