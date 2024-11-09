package com.prineside.tdi2.miners;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.configs.HeadlessConfig;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/miners/TensorMiner.class */
public class TensorMiner extends Miner {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f2580a = {User32.VK_OEM_ATTN, 750, HeadlessConfig.REPORT_INTERVAL, 2400, GL11.GL_SHADE_MODEL, 4000, 5800, 7400, 9500, 12500};

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private float f2581b;

    /* synthetic */ TensorMiner(byte b2) {
        this();
    }

    private TensorMiner() {
        super(MinerType.TENSOR);
        this.f2581b = 0.0f;
    }

    @Override // com.prineside.tdi2.Miner
    public int getBaseUpgradePrice(int i) {
        return f2580a[i - 1];
    }

    @Override // com.prineside.tdi2.Miner
    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
        float f5 = f3 / 128.0f;
        if (isPrepared() && this.nextMinedResourceType != null) {
            float currentMiningSpeedFromSystem = getCurrentMiningSpeedFromSystem() * 120.0f;
            if (this.doubleSpeedTimeLeft > 0.0f) {
                currentMiningSpeedFromSystem *= 2.0f;
            }
            this.f2581b = (this.f2581b + (f4 * currentMiningSpeedFromSystem)) % 360.0f;
        } else {
            this.f2581b = 0.0f;
        }
        batch.draw(Game.i.minerManager.F.TENSOR.f2582a, f, f2 + (34.0f * f5), 30.0f * f5, 30.0f * f5, 60.0f * f5, 60.0f * f5, 1.0f, 1.0f, this.f2581b);
        batch.draw(Game.i.minerManager.F.TENSOR.f2582a, f + (68.0f * f5), f2 + (34.0f * f5), 30.0f * f5, 30.0f * f5, 60.0f * f5, 60.0f * f5, 1.0f, 1.0f, this.f2581b);
        batch.draw(Game.i.minerManager.F.TENSOR.f2582a, f + (34.0f * f5), f2, 30.0f * f5, 30.0f * f5, 60.0f * f5, 60.0f * f5, 1.0f, 1.0f, this.f2581b + 90.0f);
        batch.draw(Game.i.minerManager.F.TENSOR.f2582a, f + (34.0f * f5), f2 + (68.0f * f5), 30.0f * f5, 30.0f * f5, 60.0f * f5, 60.0f * f5, 1.0f, 1.0f, this.f2581b + 90.0f);
        a(batch, f + (f3 * 0.5f), f2 + (f3 * 0.5f), f5, drawMode);
        b(batch, f, f2, f3, drawMode);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/miners/TensorMiner$TensorMinerFactory.class */
    public static class TensorMinerFactory extends Miner.Factory<TensorMiner> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2582a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f2583b;

        public TensorMinerFactory() {
            super(MinerType.TENSOR, "miner-tensor");
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public int getBaseBuildPrice(GameValueProvider gameValueProvider) {
            return 185;
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public float getBaseMiningSpeed(GameValueProvider gameValueProvider) {
            return (float) (6.0d * gameValueProvider.getPercentValueAsMultiplierSum(GameValueType.MINERS_SPEED, GameValueType.MINER_TENSOR_SPEED));
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public boolean canMineResource(ResourceType resourceType) {
            return resourceType.ordinal() <= ResourceType.TENSOR.ordinal();
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public void setupAssets() {
            this.f2582a = Game.i.assetManager.getTextureRegion("miner-tensor-blade");
            this.f2583b = Game.i.assetManager.getTextureRegion("miner-tensor-base");
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public TextureRegion getTexture() {
            return this.f2583b;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Miner.Factory
        public TensorMiner create() {
            return new TensorMiner((byte) 0);
        }
    }
}
