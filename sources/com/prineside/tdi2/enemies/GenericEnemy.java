package com.prineside.tdi2.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import java.util.Arrays;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/GenericEnemy.class */
public final class GenericEnemy extends Enemy {
    public float size;
    public boolean mayHaveRandomSideShift;
    public boolean drawPriority;
    public int baseDamage;
    public float[] towerDamageMultiplier;
    public float[] buffVulnerability;
    public boolean[] damageVulnerability;
    public boolean[] specialDamageVulnerability;
    public float[] abilityVulnerability;
    public boolean isFlying;
    public boolean allowsDynamicPathfinding;
    public Color color;

    @Null
    public EnemyType pathfindingEnemyType;

    @NAGS
    public TextureRegion texture;

    /* synthetic */ GenericEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.size);
        output.writeBoolean(this.mayHaveRandomSideShift);
        output.writeBoolean(this.drawPriority);
        kryo.writeObjectOrNull(output, this.towerDamageMultiplier, float[].class);
        output.writeVarInt(this.baseDamage, true);
        kryo.writeObjectOrNull(output, this.buffVulnerability, float[].class);
        kryo.writeObjectOrNull(output, this.damageVulnerability, boolean[].class);
        kryo.writeObjectOrNull(output, this.specialDamageVulnerability, boolean[].class);
        kryo.writeObjectOrNull(output, this.abilityVulnerability, float[].class);
        output.writeBoolean(this.isFlying);
        output.writeBoolean(this.allowsDynamicPathfinding);
        kryo.writeObjectOrNull(output, this.color, Color.class);
        kryo.writeObjectOrNull(output, this.pathfindingEnemyType, EnemyType.class);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.size = input.readFloat();
        this.mayHaveRandomSideShift = input.readBoolean();
        this.drawPriority = input.readBoolean();
        this.towerDamageMultiplier = (float[]) kryo.readObjectOrNull(input, float[].class);
        this.baseDamage = input.readVarInt(true);
        this.buffVulnerability = (float[]) kryo.readObjectOrNull(input, float[].class);
        this.damageVulnerability = (boolean[]) kryo.readObjectOrNull(input, boolean[].class);
        this.specialDamageVulnerability = (boolean[]) kryo.readObjectOrNull(input, boolean[].class);
        this.abilityVulnerability = (float[]) kryo.readObjectOrNull(input, float[].class);
        this.isFlying = input.readBoolean();
        this.allowsDynamicPathfinding = input.readBoolean();
        this.color = (Color) kryo.readObjectOrNull(input, Color.class);
        this.pathfindingEnemyType = (EnemyType) kryo.readObjectOrNull(input, EnemyType.class);
    }

    private GenericEnemy() {
        super(EnemyType.GENERIC);
        this.size = 25.6f;
        this.mayHaveRandomSideShift = true;
        this.drawPriority = false;
        this.baseDamage = 1;
        this.isFlying = false;
        this.allowsDynamicPathfinding = true;
    }

    @Override // com.prineside.tdi2.Enemy
    public final EnemyType getTypeForPathfinding() {
        return this.pathfindingEnemyType == null ? EnemyType.GENERIC : this.pathfindingEnemyType;
    }

    public final EnemyType getPathfindingEnemyTypeOverride() {
        return this.pathfindingEnemyType;
    }

    public final void setPathfindingEnemyTypeOverride(EnemyType enemyType) {
        this.pathfindingEnemyType = enemyType;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean canHaveRandomSideShift() {
        return this.mayHaveRandomSideShift;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getSize() {
        return this.size;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getSquaredSize() {
        return this.size * this.size;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return this.drawPriority;
    }

    @Override // com.prineside.tdi2.Enemy
    public final TextureRegion getTexture() {
        return this.texture == null ? this.S.enemy.getTexture(this.type) : this.texture;
    }

    @Override // com.prineside.tdi2.Enemy
    public final TextureRegion getHighlightTexture() {
        return getTexture();
    }

    @Override // com.prineside.tdi2.Enemy
    public final TextureRegion getEmojiTexture() {
        return getTexture();
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getTowerDamageMultiplier(TowerType towerType) {
        return this.towerDamageMultiplier == null ? this.S.tower.towerEnemyDamageMultiplier[this.type.ordinal()][towerType.ordinal()] : this.towerDamageMultiplier[towerType.ordinal()];
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean canBeAttackedBy(Tower tower) {
        if (getTowerDamageMultiplier(tower.type) <= 0.0f) {
            return false;
        }
        return super.canBeAttackedBy(tower);
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return this.baseDamage;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBuffVulnerability(BuffType buffType) {
        return this.buffVulnerability == null ? this.S.enemy.enemyBuffVulnerability[this.type.ordinal()][buffType.ordinal()] : this.buffVulnerability[buffType.ordinal()];
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isVulnerableTo(DamageType damageType) {
        return this.damageVulnerability == null ? this.S.enemy.enemyDamageVulnerability[this.type.ordinal()][damageType.ordinal()] : this.damageVulnerability[damageType.ordinal()];
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isVulnerableToSpecial(SpecialDamageType specialDamageType) {
        return this.specialDamageVulnerability == null ? this.S.enemy.enemySpecialDamageVulnerability[this.type.ordinal()][specialDamageType.ordinal()] : this.specialDamageVulnerability[specialDamageType.ordinal()];
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getAbilityVulnerability(AbilityType abilityType) {
        return this.abilityVulnerability == null ? super.getAbilityVulnerability(abilityType) : this.abilityVulnerability[abilityType.ordinal()];
    }

    public final void setTowerDamageMultiplier(TowerType towerType, float f) {
        if (this.towerDamageMultiplier == null) {
            this.towerDamageMultiplier = new float[TowerType.values.length];
            System.arraycopy(this.S.tower.towerEnemyDamageMultiplier[this.type.ordinal()], 0, this.towerDamageMultiplier, 0, this.towerDamageMultiplier.length);
        }
        this.towerDamageMultiplier[towerType.ordinal()] = f;
    }

    public final void setBuffVulnerability(BuffType buffType, float f) {
        if (this.buffVulnerability == null) {
            this.buffVulnerability = new float[BuffType.values.length];
            System.arraycopy(this.S.enemy.enemyBuffVulnerability[this.type.ordinal()], 0, this.buffVulnerability, 0, this.buffVulnerability.length);
        }
        this.buffVulnerability[buffType.ordinal()] = f;
    }

    public final void setDamageVulnerability(DamageType damageType, boolean z) {
        if (this.damageVulnerability == null) {
            this.damageVulnerability = new boolean[DamageType.values.length];
            System.arraycopy(this.S.enemy.enemyDamageVulnerability[this.type.ordinal()], 0, this.damageVulnerability, 0, this.damageVulnerability.length);
        }
        this.damageVulnerability[damageType.ordinal()] = z;
    }

    public final void setSpecialDamageVulnerability(SpecialDamageType specialDamageType, boolean z) {
        if (this.specialDamageVulnerability == null) {
            this.specialDamageVulnerability = new boolean[SpecialDamageType.values.length];
            System.arraycopy(this.S.enemy.enemySpecialDamageVulnerability[this.type.ordinal()], 0, this.specialDamageVulnerability, 0, this.specialDamageVulnerability.length);
        }
        this.specialDamageVulnerability[specialDamageType.ordinal()] = z;
    }

    public final void setAbilityVulnerability(AbilityType abilityType, float f) {
        if (this.abilityVulnerability == null) {
            this.abilityVulnerability = new float[AbilityType.values.length];
            Arrays.fill(this.abilityVulnerability, 1.0f);
        }
        this.abilityVulnerability[abilityType.ordinal()] = f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final ParticleEffectPool.PooledEffect getBreakParticle() {
        ParticleEffectPool.PooledEffect breakParticle = super.getBreakParticle();
        Color color = getColor();
        float[] colors = breakParticle.getEmitters().first().getTint().getColors();
        colors[0] = color.r;
        colors[1] = color.g;
        colors[2] = color.f888b;
        return breakParticle;
    }

    @Override // com.prineside.tdi2.Enemy
    public final ParticleEffectPool.PooledEffect getHitParticle() {
        ParticleEffectPool.PooledEffect hitParticle = super.getHitParticle();
        Color color = getColor();
        float[] colors = hitParticle.getEmitters().first().getTint().getColors();
        colors[0] = color.r;
        colors[1] = color.g;
        colors[2] = color.f888b;
        return hitParticle;
    }

    @Override // com.prineside.tdi2.Enemy
    public final Color getColor() {
        return this.color == null ? MaterialColor.GREY.P500 : this.color;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean isAir() {
        return this.isFlying;
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean dynamicPathfindingAllowed() {
        return this.allowsDynamicPathfinding;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/GenericEnemy$GenericEnemyFactory.class */
    public static class GenericEnemyFactory extends Enemy.Factory<GenericEnemy> {
        public GenericEnemyFactory() {
            super(EnemyType.GENERIC);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return Game.i.assetManager.getBlankWhiteTextureRegion();
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return Game.i.assetManager.getBlankWhiteTextureRegion();
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getEmojiTexture() {
            return Game.i.assetManager.getBlankWhiteTextureRegion();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public GenericEnemy create() {
            return new GenericEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.GREY.P500;
        }
    }
}
