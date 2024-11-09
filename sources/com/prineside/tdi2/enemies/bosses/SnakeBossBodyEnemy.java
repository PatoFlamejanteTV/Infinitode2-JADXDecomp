package com.prineside.tdi2.enemies.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/SnakeBossBodyEnemy.class */
public final class SnakeBossBodyEnemy extends Enemy {
    /* synthetic */ SnakeBossBodyEnemy(byte b2) {
        this();
    }

    private SnakeBossBodyEnemy() {
        super(EnemyType.SNAKE_BOSS_BODY);
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
        if (abilityType == AbilityType.LOIC) {
            return abilityVulnerability * 4.0f;
        }
        return abilityVulnerability;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 2.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawBatch(Batch batch, float f) {
        float f2 = this.angle;
        this.angle += SnakeBossHeadEnemy.calculateSwingRotation(this.passedTiles);
        this.drawAngle = this.angle;
        this.drawPosition.set(getPosition());
        super.drawBatch(batch, f);
        this.angle = f2;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void onPositionSetToPath() {
        SnakeBossHeadEnemy.transformPositionToSwing(this.passedTiles, this.angle, getPosition());
        setPosition(getPosition());
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
    public final boolean dynamicPathfindingAllowed() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/SnakeBossBodyEnemy$SnakeBossBodyEnemyFactory.class */
    public static class SnakeBossBodyEnemyFactory extends Enemy.Factory<SnakeBossBodyEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1911a;

        public SnakeBossBodyEnemyFactory() {
            super(EnemyType.SNAKE_BOSS_BODY);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1911a = Game.i.assetManager.getTextureRegion("enemy-type-boss-snake-body");
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1911a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1911a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public SnakeBossBodyEnemy create() {
            return new SnakeBossBodyEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.LIGHT_GREEN.P500;
        }
    }
}
