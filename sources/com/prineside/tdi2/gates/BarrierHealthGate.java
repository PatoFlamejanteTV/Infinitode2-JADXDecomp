package com.prineside.tdi2.gates;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.GateBarrier;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GateType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gates/BarrierHealthGate.class */
public class BarrierHealthGate extends GateBarrier {
    public boolean moreThanHalf;

    /* synthetic */ BarrierHealthGate(byte b2) {
        this();
    }

    private BarrierHealthGate() {
        super(GateType.BARRIER_HEALTH);
    }

    @Override // com.prineside.tdi2.Gate
    public RarityType getRarity() {
        return RarityType.COMMON;
    }

    @Override // com.prineside.tdi2.Gate
    public Gate cloneGate() {
        BarrierHealthGate create = Game.i.gateManager.F.BARRIER_HEALTH.create();
        create.setPosition(getX(), getY(), isLeftSide());
        create.moreThanHalf = this.moreThanHalf;
        return create;
    }

    @Override // com.prineside.tdi2.Gate
    public void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.GREEN_PAPER, 200));
    }

    @Override // com.prineside.tdi2.Gate
    public double getPrestigeScore() {
        return 0.05d;
    }

    @Override // com.prineside.tdi2.Gate
    public int getSortingScore(ItemSortingType itemSortingType) {
        return 1;
    }

    @Override // com.prineside.tdi2.GateBarrier
    public boolean canEnemyPass(EnemyType enemyType) {
        return true;
    }

    @Override // com.prineside.tdi2.Gate
    public Actor generateIcon(float f, boolean z) {
        Image image;
        if (this.moreThanHalf) {
            image = new Image(Game.i.assetManager.getDrawable("icon-gate-health-high"));
        } else {
            image = new Image(Game.i.assetManager.getDrawable("icon-gate-health-low"));
        }
        image.setSize(f, f);
        return image;
    }

    @Override // com.prineside.tdi2.Gate
    public void drawStatic(Batch batch, float f, float f2, float f3, float f4) {
        float f5 = f3 * 0.0078125f;
        float f6 = f4 * 0.0078125f;
        if (isLeftSide()) {
            if (this.moreThanHalf) {
                batch.draw(Game.i.gateManager.F.BARRIER_HEALTH.f2191a, ((getX() << 7) - 16.0f) * f5, (getY() << 7) * f6, 32.0f * f5, 128.0f * f6);
                return;
            } else {
                batch.draw(Game.i.gateManager.F.BARRIER_HEALTH.c, ((getX() << 7) - 16.0f) * f5, (getY() << 7) * f6, 32.0f * f5, 128.0f * f6);
                return;
            }
        }
        if (this.moreThanHalf) {
            batch.draw(Game.i.gateManager.F.BARRIER_HEALTH.f2192b, (getX() << 7) * f5, ((getY() << 7) - 16.0f) * f6, 128.0f * f5, 32.0f * f6);
        } else {
            batch.draw(Game.i.gateManager.F.BARRIER_HEALTH.d, (getX() << 7) * f5, ((getY() << 7) - 16.0f) * f6, 128.0f * f5, 32.0f * f6);
        }
    }

    @Override // com.prineside.tdi2.Gate
    public boolean sameAs(Gate gate) {
        return super.sameAs(gate) && ((BarrierHealthGate) gate).moreThanHalf == this.moreThanHalf;
    }

    @Override // com.prineside.tdi2.Gate
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("moreThanHalf", Boolean.valueOf(this.moreThanHalf));
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (" + this.moreThanHalf + ")";
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gates/BarrierHealthGate$BarrierHealthGateFactory.class */
    public static class BarrierHealthGateFactory extends Gate.Factory.AbstractFactory<BarrierHealthGate> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2191a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion f2192b;
        TextureRegion c;
        TextureRegion d;

        public BarrierHealthGateFactory() {
            super(GateType.BARRIER_HEALTH);
        }

        @Override // com.prineside.tdi2.Gate.Factory.AbstractFactory
        public void setupAssets() {
            this.f2191a = Game.i.assetManager.getTextureRegion("gate-barrier-health-high-vertical");
            this.f2192b = Game.i.assetManager.getTextureRegion("gate-barrier-health-high-horizontal");
            this.c = Game.i.assetManager.getTextureRegion("gate-barrier-health-low-vertical");
            this.d = Game.i.assetManager.getTextureRegion("gate-barrier-health-low-horizontal");
        }

        @Override // com.prineside.tdi2.Gate.Factory
        public BarrierHealthGate create() {
            return new BarrierHealthGate((byte) 0);
        }

        @Override // com.prineside.tdi2.Gate.Factory
        public BarrierHealthGate createRandom(float f, RandomXS128 randomXS128) {
            if (randomXS128 == null) {
                randomXS128 = FastRandom.random;
            }
            BarrierHealthGate create = create();
            create.moreThanHalf = randomXS128.nextFloat() < 0.5f;
            return create;
        }

        @Override // com.prineside.tdi2.Gate.Factory.AbstractFactory, com.prineside.tdi2.Gate.Factory
        public BarrierHealthGate fromJson(JsonValue jsonValue) {
            BarrierHealthGate barrierHealthGate = (BarrierHealthGate) super.fromJson(jsonValue);
            barrierHealthGate.moreThanHalf = jsonValue.getBoolean("moreThanHalf", false);
            return barrierHealthGate;
        }
    }
}
