package com.prineside.tdi2.enemies.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MobchainBossBodyEnemy.class */
public final class MobchainBossBodyEnemy extends Enemy {
    /* synthetic */ MobchainBossBodyEnemy(byte b2) {
        this();
    }

    private MobchainBossBodyEnemy() {
        super(EnemyType.MOBCHAIN_BOSS_BODY);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
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
    public final float getBuffedDamageMultiplier(TowerType towerType, DamageType damageType) {
        float health = getHealth() / this.maxHealth;
        float f = 1.0f;
        if (health < 0.66f && health > 0.33f) {
            if (towerType == TowerType.SPLASH || towerType == TowerType.SNIPER || towerType == TowerType.MULTISHOT || towerType == TowerType.MINIGUN || towerType == TowerType.MISSILE || towerType == TowerType.CANNON || towerType == TowerType.BASIC) {
                f = 0.1f;
            }
        } else if (health <= 0.33f && (towerType == TowerType.VENOM || towerType == TowerType.TESLA || towerType == TowerType.LASER || towerType == TowerType.FREEZING || towerType == TowerType.FLAMETHROWER || towerType == TowerType.BLAST)) {
            f = 0.1f;
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
    public final float getBaseDamage() {
        return 2.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean dynamicPathfindingAllowed() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/MobchainBossBodyEnemy$MobchainBossBodyEnemyFactory.class */
    public static class MobchainBossBodyEnemyFactory extends Enemy.Factory<MobchainBossBodyEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1908a;

        public MobchainBossBodyEnemyFactory() {
            super(EnemyType.SNAKE_BOSS_BODY);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1908a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1908a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1908a = Game.i.assetManager.getTextureRegion("enemy-type-boss-mobchain-body");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public MobchainBossBodyEnemy create() {
            return new MobchainBossBodyEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.DEEP_PURPLE.P500;
        }
    }
}
