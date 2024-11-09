package com.prineside.tdi2.enemies.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MobchainBossHeadEnemy.class */
public final class MobchainBossHeadEnemy extends Enemy {
    public boolean vulnerable;

    /* synthetic */ MobchainBossHeadEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeBoolean(this.vulnerable);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.vulnerable = input.readBoolean();
    }

    private MobchainBossHeadEnemy() {
        super(EnemyType.MOBCHAIN_BOSS_HEAD);
        this.vulnerable = false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 100.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getAbilityVulnerability(AbilityType abilityType) {
        float abilityVulnerability = super.getAbilityVulnerability(abilityType);
        if (abilityType == AbilityType.BALL_LIGHTNING) {
            return abilityVulnerability * 0.1f;
        }
        return abilityVulnerability;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isBossRelated() {
        return true;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isBossMainBodyPart() {
        return true;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean canBeAttackedBy(Tower tower) {
        if (this.vulnerable) {
            return super.canBeAttackedBy(tower);
        }
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBuffedDamageMultiplier(TowerType towerType, DamageType damageType) {
        if (this.vulnerable) {
            return super.getBuffedDamageMultiplier(towerType, damageType);
        }
        return 0.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawHealth(Batch batch) {
        if (this.vulnerable) {
            super.drawHealth(batch);
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean dynamicPathfindingAllowed() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MobchainBossHeadEnemy$MobchainBossHeadEnemyFactory.class */
    public static class MobchainBossHeadEnemyFactory extends Enemy.Factory<MobchainBossHeadEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1910a;

        public MobchainBossHeadEnemyFactory() {
            super(EnemyType.MOBCHAIN_BOSS_HEAD);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1910a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1910a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1910a = Game.i.assetManager.getTextureRegion("enemy-type-boss-mobchain-head");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public MobchainBossHeadEnemy create() {
            return new MobchainBossHeadEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.DEEP_PURPLE.P500;
        }
    }
}
