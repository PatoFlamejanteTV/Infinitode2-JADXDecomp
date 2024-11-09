package com.prineside.tdi2;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/WaveProcessor.class */
public interface WaveProcessor {
    Array<EnemyGroup> generateEnemyGroups(int i, int i2);

    Wave setup(GameSystemProvider gameSystemProvider, int i, int i2);

    void update(float f);

    void draw(Batch batch, float f);

    boolean isDone();

    default float getNextWaveDelayMultiplier() {
        return 7.0f;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/WaveProcessor$WaveProcessorFactory.class */
    public static abstract class WaveProcessorFactory<T extends WaveProcessor> {
        public final BossType bossType;

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1787a;

        public abstract T create();

        public WaveProcessorFactory(BossType bossType) {
            this.bossType = bossType;
        }

        public void setup() {
            if (Game.i.assetManager != null) {
                this.f1787a = Game.i.assetManager.getTextureRegion("boss-wave-icon-" + this.bossType.name());
            }
        }

        public TextureRegion getIconTexture() {
            return this.f1787a;
        }
    }
}
