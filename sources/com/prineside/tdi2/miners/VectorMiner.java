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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/miners/VectorMiner.class */
public class VectorMiner extends Miner {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f2584a = {170, 500, 1200, 1750, 2300, 3100, 4300, 5700, 7200, 9600};

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private float f2585b;

    /* synthetic */ VectorMiner(byte b2) {
        this();
    }

    private VectorMiner() {
        super(MinerType.VECTOR);
        this.f2585b = 0.0f;
    }

    @Override // com.prineside.tdi2.Miner
    public int getBaseUpgradePrice(int i) {
        return f2584a[i - 1];
    }

    @Override // com.prineside.tdi2.Miner
    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
        float f5 = f3 / 128.0f;
        if (isPrepared() && this.nextMinedResourceType != null) {
            float currentMiningSpeedFromSystem = getCurrentMiningSpeedFromSystem() * 120.0f;
            if (this.doubleSpeedTimeLeft > 0.0f) {
                currentMiningSpeedFromSystem *= 2.0f;
            }
            this.f2585b = (this.f2585b + (f4 * currentMiningSpeedFromSystem)) % 360.0f;
        } else {
            this.f2585b = 0.0f;
        }
        batch.draw(Game.i.minerManager.F.VECTOR.f2586a, f + (15.0f * f5), f2 + (15.0f * f5), 29.0f * f5, 29.0f * f5, 58.0f * f5, 58.0f * f5, 1.0f, 1.0f, this.f2585b);
        batch.draw(Game.i.minerManager.F.VECTOR.f2586a, f + (55.0f * f5), f2 + (55.0f * f5), 29.0f * f5, 29.0f * f5, 58.0f * f5, 58.0f * f5, 1.0f, 1.0f, this.f2585b);
        batch.draw(Game.i.minerManager.F.VECTOR.f2586a, f + (15.0f * f5), f2 + (55.0f * f5), 29.0f * f5, 29.0f * f5, 58.0f * f5, 58.0f * f5, 1.0f, 1.0f, this.f2585b + 90.0f);
        batch.draw(Game.i.minerManager.F.VECTOR.f2586a, f + (55.0f * f5), f2 + (15.0f * f5), 29.0f * f5, 29.0f * f5, 58.0f * f5, 58.0f * f5, 1.0f, 1.0f, this.f2585b + 90.0f);
        a(batch, f + (f3 * 0.5f), f2 + (f3 * 0.5f), f5, drawMode);
        b(batch, f, f2, f3, drawMode);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/miners/VectorMiner$VectorMinerFactory.class */
    public static class VectorMinerFactory extends Miner.Factory<VectorMiner> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2586a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f2587b;

        public VectorMinerFactory() {
            super(MinerType.VECTOR, "miner-vector");
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public TextureRegion getTexture() {
            return this.f2587b;
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public int getBaseBuildPrice(GameValueProvider gameValueProvider) {
            return 140;
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public float getBaseMiningSpeed(GameValueProvider gameValueProvider) {
            return (float) (8.699999809265137d * gameValueProvider.getPercentValueAsMultiplierSum(GameValueType.MINERS_SPEED, GameValueType.MINER_VECTOR_SPEED));
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public boolean canMineResource(ResourceType resourceType) {
            return resourceType.ordinal() <= ResourceType.VECTOR.ordinal();
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public void setupAssets() {
            this.f2586a = Game.i.assetManager.getTextureRegion("miner-vector-blade");
            this.f2587b = Game.i.assetManager.getTextureRegion("miner-vector-base");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Miner.Factory
        public VectorMiner create() {
            return new VectorMiner((byte) 0);
        }
    }
}
