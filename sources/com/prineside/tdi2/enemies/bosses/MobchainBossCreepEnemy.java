package com.prineside.tdi2.enemies.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MobchainBossCreepEnemy.class */
public final class MobchainBossCreepEnemy extends Enemy {
    public TowerType vulnerableTo;

    @NAGS
    public Color color;

    /* synthetic */ MobchainBossCreepEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.vulnerableTo, TowerType.class);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.vulnerableTo = (TowerType) kryo.readObjectOrNull(input, TowerType.class);
    }

    private MobchainBossCreepEnemy() {
        super(EnemyType.MOBCHAIN_BOSS_CREEP);
        this.color = Color.WHITE;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getAbilityVulnerability(AbilityType abilityType) {
        return super.getAbilityVulnerability(abilityType) * 0.1f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBuffedDamageMultiplier(TowerType towerType, DamageType damageType) {
        float f = 0.15f;
        if (this.vulnerableTo == towerType) {
            f = 1.0f;
        }
        return f * super.getBuffedDamageMultiplier(towerType, damageType);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isBossRelated() {
        return true;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isBossMainBodyPart() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawBatch(Batch batch, float f) {
        super.drawBatch(batch, f, this.color);
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawHealth(Batch batch) {
        super.drawHealth(batch);
        if (this.vulnerableTo != null) {
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            batch.draw(AssetManager.TextureRegions.i().enemyDialog, this.drawPosition.x - 44.0f, this.drawPosition.y + 56.0f, 88.0f, 80.0f);
            batch.draw(Game.i.towerManager.getFactory(this.vulnerableTo).getIconTexture(), this.drawPosition.x - 32.0f, this.drawPosition.y + 66.0f, 64.0f, 64.0f);
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 0.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean dynamicPathfindingAllowed() {
        return true;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MobchainBossCreepEnemy$MobchainBossCreepEnemyFactory.class */
    public static class MobchainBossCreepEnemyFactory extends Enemy.Factory<MobchainBossCreepEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1909a;

        public MobchainBossCreepEnemyFactory() {
            super(EnemyType.MOBCHAIN_BOSS_CREEP);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1909a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1909a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1909a = Game.i.assetManager.getTextureRegion("enemy-type-boss-mobchain-creep");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public MobchainBossCreepEnemy create() {
            return new MobchainBossCreepEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.DEEP_PURPLE.P500;
        }
    }
}
