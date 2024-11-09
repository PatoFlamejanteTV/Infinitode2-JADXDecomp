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
import org.lwjgl.system.windows.User32;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/miners/InfiarMiner.class */
public class InfiarMiner extends Miner {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f2568a = {User32.VK_PLAY, 875, 1900, 2600, 3500, 5100, 6600, 8800, 11000, 14000};

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private float f2569b;

    /* synthetic */ InfiarMiner(byte b2) {
        this();
    }

    private InfiarMiner() {
        super(MinerType.INFIAR);
        this.f2569b = 0.0f;
    }

    @Override // com.prineside.tdi2.Miner
    public int getBaseUpgradePrice(int i) {
        return f2568a[i - 1];
    }

    @Override // com.prineside.tdi2.Miner
    public void drawBatch(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
        float f5 = f3 / 128.0f;
        if (isPrepared() && this.nextMinedResourceType != null) {
            float currentMiningSpeedFromSystem = getCurrentMiningSpeedFromSystem() * 120.0f;
            if (this.doubleSpeedTimeLeft > 0.0f) {
                currentMiningSpeedFromSystem *= 2.0f;
            }
            this.f2569b = (this.f2569b + (f4 * currentMiningSpeedFromSystem)) % 360.0f;
        } else {
            this.f2569b = 0.0f;
        }
        batch.draw(Game.i.minerManager.F.INFIAR.f2570a, f + (6.0f * f5), f2 + (6.0f * f5), 58.0f * f5, 58.0f * f5, 116.0f * f5, 116.0f * f5, 1.0f, 1.0f, this.f2569b);
        a(batch, f + (f3 * 0.5f), f2 + (f3 * 0.5f), f5, drawMode);
        b(batch, f, f2, f3, drawMode);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/miners/InfiarMiner$InfiarMinerFactory.class */
    public static class InfiarMinerFactory extends Miner.Factory<InfiarMiner> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2570a;

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f2571b;

        public InfiarMinerFactory() {
            super(MinerType.INFIAR, "miner-infiar");
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public float getBaseMiningSpeed(GameValueProvider gameValueProvider) {
            return (float) (4.5d * gameValueProvider.getPercentValueAsMultiplierSum(GameValueType.MINERS_SPEED, GameValueType.MINER_INFIAR_SPEED));
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public boolean canMineResource(ResourceType resourceType) {
            return true;
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public int getBaseBuildPrice(GameValueProvider gameValueProvider) {
            return 200;
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public void setupAssets() {
            this.f2570a = Game.i.assetManager.getTextureRegion("miner-infiar-blade");
            this.f2571b = Game.i.assetManager.getTextureRegion("miner-infiar-base");
        }

        @Override // com.prineside.tdi2.Miner.Factory
        public TextureRegion getTexture() {
            return this.f2571b;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Miner.Factory
        public InfiarMiner create() {
            return new InfiarMiner((byte) 0);
        }
    }
}
