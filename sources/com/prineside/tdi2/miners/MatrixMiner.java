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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/miners/MatrixMiner.class */
public class MatrixMiner extends Miner {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f2572a = {185, 600, 1400, 2000, 2500, 3400, 5000, 6600, 8400, 11000};

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private float f2573b;

    /* synthetic */ MatrixMiner(byte b2) {
        this();
    }

    private MatrixMiner() {
        super(MinerType.MATRIX);
        this.f2573b = 0.0f;
    }

    @Override // com.prineside.tdi2.Miner
    public int getBaseUpgradePrice(int i) {
        return f2572a[i - 1];
    }

    @Override // com.prineside.tdi2.Miner
    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
        float f5 = f3 / 128.0f;
        if (isPrepared() && this.nextMinedResourceType != null) {
            float currentMiningSpeedFromSystem = getCurrentMiningSpeedFromSystem() * 120.0f;
            if (this.doubleSpeedTimeLeft > 0.0f) {
                currentMiningSpeedFromSystem *= 2.0f;
            }
            this.f2573b = (this.f2573b + (f4 * currentMiningSpeedFromSystem)) % 360.0f;
        } else {
            this.f2573b = 0.0f;
        }
        batch.draw(Game.i.minerManager.F.MATRIX.f2574a, f, f2 + (34.0f * f5), 30.0f * f5, 30.0f * f5, 60.0f * f5, 60.0f * f5, 1.0f, 1.0f, this.f2573b);
        batch.draw(Game.i.minerManager.F.MATRIX.f2574a, f + (68.0f * f5), f2 + (34.0f * f5), 30.0f * f5, 30.0f * f5, 60.0f * f5, 60.0f * f5, 1.0f, 1.0f, this.f2573b);
        a(batch, f + (f3 * 0.5f), f2 + (f3 * 0.5f), f5, drawMode);
        b(batch, f, f2, f3, drawMode);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/miners/MatrixMiner$MatrixMinerFactory.class */
    public static class MatrixMinerFactory extends Miner.Factory<MatrixMiner> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2574a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f2575b;

        public MatrixMinerFactory() {
            super(MinerType.MATRIX, "miner-matrix");
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public void setupAssets() {
            this.f2574a = Game.i.assetManager.getTextureRegion("miner-matrix-blade");
            this.f2575b = Game.i.assetManager.getTextureRegion("miner-matrix-base");
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public TextureRegion getTexture() {
            return this.f2575b;
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public int getBaseBuildPrice(GameValueProvider gameValueProvider) {
            return 170;
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public float getBaseMiningSpeed(GameValueProvider gameValueProvider) {
            return (float) (7.400000095367432d * gameValueProvider.getPercentValueAsMultiplierSum(GameValueType.MINERS_SPEED, GameValueType.MINER_MATRIX_SPEED));
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public boolean canMineResource(ResourceType resourceType) {
            return resourceType.ordinal() <= ResourceType.MATRIX.ordinal();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Miner.Factory
        public MatrixMiner create() {
            return new MatrixMiner((byte) 0);
        }
    }
}
