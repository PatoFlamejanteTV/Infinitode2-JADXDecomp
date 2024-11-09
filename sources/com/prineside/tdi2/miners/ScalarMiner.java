package com.prineside.tdi2.miners;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/miners/ScalarMiner.class */
public class ScalarMiner extends Miner {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f2576a = {150, 400, 1000, 1500, 2100, 2800, 3700, 4800, 6000, 8000};

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private float f2577b;

    /* synthetic */ ScalarMiner(byte b2) {
        this();
    }

    private ScalarMiner() {
        super(MinerType.SCALAR);
        this.f2577b = 0.0f;
    }

    @Override // com.prineside.tdi2.Miner
    public int getBaseUpgradePrice(int i) {
        return f2576a[i - 1];
    }

    @Override // com.prineside.tdi2.Miner
    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
        float f5 = f3 / 128.0f;
        if (isPrepared() && this.nextMinedResourceType != null) {
            float currentMiningSpeedFromSystem = getCurrentMiningSpeedFromSystem() * 120.0f;
            if (this.doubleSpeedTimeLeft > 0.0f) {
                currentMiningSpeedFromSystem *= 2.0f;
            }
            this.f2577b = (this.f2577b + (f4 * currentMiningSpeedFromSystem)) % 360.0f;
        } else {
            this.f2577b = 0.0f;
        }
        batch.draw(Game.i.minerManager.F.SCALAR.f2578a, f + (15.0f * f5), f2 + (15.0f * f5), 29.0f * f5, 29.0f * f5, 58.0f * f5, 58.0f * f5, 1.0f, 1.0f, this.f2577b);
        batch.draw(Game.i.minerManager.F.SCALAR.f2578a, f + (55.0f * f5), f2 + (55.0f * f5), 29.0f * f5, 29.0f * f5, 58.0f * f5, 58.0f * f5, 1.0f, 1.0f, this.f2577b);
        a(batch, f + (f3 * 0.5f), f2 + (f3 * 0.5f), f5, drawMode);
        b(batch, f, f2, f3, drawMode);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/miners/ScalarMiner$ScalarMinerFactory.class */
    public static class ScalarMinerFactory extends Miner.Factory<ScalarMiner> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2578a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f2579b;

        public ScalarMinerFactory() {
            super(MinerType.SCALAR, "miner-scalar");
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public int getBaseBuildPrice(GameValueProvider gameValueProvider) {
            return 120;
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public float getBaseMiningSpeed(GameValueProvider gameValueProvider) {
            return (float) (10.0d * gameValueProvider.getPercentValueAsMultiplierSum(GameValueType.MINERS_SPEED, GameValueType.MINER_SCALAR_SPEED));
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public boolean canMineResource(ResourceType resourceType) {
            return resourceType == ResourceType.SCALAR;
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public void setupAssets() {
            this.f2578a = Game.i.assetManager.getTextureRegion("miner-scalar-blade");
            this.f2579b = Game.i.assetManager.getTextureRegion("miner-scalar-base");
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public TextureRegion getTexture() {
            return this.f2579b;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Miner.Factory
        public ScalarMiner create() {
            return new ScalarMiner((byte) 0);
        }
    }
}
