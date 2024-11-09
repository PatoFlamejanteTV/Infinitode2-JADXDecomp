package com.prineside.tdi2.enemies.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/SnakeBossHeadEnemy.class */
public final class SnakeBossHeadEnemy extends Enemy {
    public static final float DEFAULT_MIN_SPEED = 0.3f;
    public static final float DEFAULT_MAX_SPEED = 0.85f;
    public float defaultMinSpeed;
    public float defaultMaxSpeed;
    public float damageResistance;

    /* synthetic */ SnakeBossHeadEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.defaultMinSpeed);
        output.writeFloat(this.defaultMaxSpeed);
        output.writeFloat(this.damageResistance);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.defaultMinSpeed = input.readFloat();
        this.defaultMaxSpeed = input.readFloat();
        this.damageResistance = input.readFloat();
    }

    private SnakeBossHeadEnemy() {
        super(EnemyType.SNAKE_BOSS_HEAD);
        this.defaultMinSpeed = 0.3f;
        this.defaultMaxSpeed = 0.85f;
        this.damageResistance = 1.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return false;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 50.0f;
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
    public final float getBuffedDamageMultiplier(TowerType towerType, DamageType damageType) {
        return super.getBuffedDamageMultiplier(towerType, damageType) * (1.0f - this.damageResistance);
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawHealth(Batch batch) {
        super.drawHealth(batch);
        if (this.damageResistance != 0.0f) {
            batch.setColor(HEALTH_BAR_BACKGROUND_COLOR);
            batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), this.drawPosition.x - 28.0f, this.drawPosition.y + 58.0f, 56.0f, 8.0f);
            batch.setColor(MaterialColor.LIGHT_BLUE.P500);
            batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), this.drawPosition.x - 26.0f, this.drawPosition.y + 60.0f, (int) (52.0f * this.damageResistance), 4.0f);
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawBatch(Batch batch, float f) {
        float f2 = this.angle;
        this.angle += calculateSwingRotation(this.passedTiles);
        this.drawAngle = this.angle;
        this.drawPosition.set(getPosition());
        super.drawBatch(batch, f);
        this.angle = f2;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void onPositionSetToPath() {
        transformPositionToSwing(this.passedTiles, this.angle, getPosition());
        setPosition(getPosition());
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean dynamicPathfindingAllowed() {
        return false;
    }

    public static float calculateSwingRotation(float f) {
        return PMath.sin(f * 3.0f) * 15.0f;
    }

    public static void transformPositionToSwing(float f, float f2, Vector2 vector2) {
        vector2.add(new Vector2(12.8f, 0.0f).rotate(f2).scl(-PMath.sin((f * 3.0f) - 45.0f)));
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/SnakeBossHeadEnemy$SnakeBossHeadEnemyFactory.class */
    public static class SnakeBossHeadEnemyFactory extends Enemy.Factory<SnakeBossHeadEnemy> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f1912a;

        public SnakeBossHeadEnemyFactory() {
            super(EnemyType.SNAKE_BOSS_HEAD);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1912a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1912a;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1912a = Game.i.assetManager.getTextureRegion("enemy-type-boss-snake-head");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public SnakeBossHeadEnemy create() {
            return new SnakeBossHeadEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.LIGHT_GREEN.P500;
        }
    }
}
