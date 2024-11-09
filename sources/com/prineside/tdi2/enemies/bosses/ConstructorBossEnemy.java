package com.prineside.tdi2.enemies.bosses;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.waves.processors.ConstructorBossWaveProcessor;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/ConstructorBossEnemy.class */
public final class ConstructorBossEnemy extends Enemy {

    /* renamed from: a, reason: collision with root package name */
    private static final Color f1894a = MaterialColor.BLUE_GREY.P500;
    public Array<Enemy> enemiesToSpawn;
    public int enemiesToSpawnStartCount;
    public float spawningTime;
    public float timeSinceCreepSpawn;
    public float spawnDelayBeforeTime;
    public float spawnDelayAfterTime;
    public boolean groupSpawned75hp;
    public boolean groupSpawned50hp;
    public boolean groupSpawned25hp;
    public boolean invulnerable;
    public ConstructorBossWaveProcessor processor;

    /* synthetic */ ConstructorBossEnemy(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.enemiesToSpawn);
        output.writeVarInt(this.enemiesToSpawnStartCount, true);
        output.writeFloat(this.spawningTime);
        output.writeFloat(this.timeSinceCreepSpawn);
        output.writeFloat(this.spawnDelayBeforeTime);
        output.writeFloat(this.spawnDelayAfterTime);
        output.writeBoolean(this.groupSpawned75hp);
        output.writeBoolean(this.groupSpawned50hp);
        output.writeBoolean(this.groupSpawned25hp);
        output.writeBoolean(this.invulnerable);
        kryo.writeObjectOrNull(output, this.processor, ConstructorBossWaveProcessor.class);
    }

    @Override // com.prineside.tdi2.Enemy, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.enemiesToSpawn = (Array) kryo.readObject(input, Array.class);
        this.enemiesToSpawnStartCount = input.readVarInt(true);
        this.spawningTime = input.readFloat();
        this.timeSinceCreepSpawn = input.readFloat();
        this.spawnDelayBeforeTime = input.readFloat();
        this.spawnDelayAfterTime = input.readFloat();
        this.groupSpawned75hp = input.readBoolean();
        this.groupSpawned50hp = input.readBoolean();
        this.groupSpawned25hp = input.readBoolean();
        this.invulnerable = input.readBoolean();
        this.processor = (ConstructorBossWaveProcessor) kryo.readObjectOrNull(input, ConstructorBossWaveProcessor.class);
    }

    private ConstructorBossEnemy() {
        super(EnemyType.CONSTRUCTOR_BOSS);
        this.enemiesToSpawn = new Array<>(Enemy.class);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean hasDrawPriority() {
        return true;
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
        return abilityVulnerability;
    }

    public final void changeSpeedTo(float f, float f2) {
        float speed = f - getSpeed();
        if (speed != 0.0f) {
            float f3 = f2 * 2.0f;
            float abs = StrictMath.abs(speed);
            if (f3 < abs) {
                setSpeed(getSpeed() + (speed * (f3 / abs)));
            } else {
                setSpeed(f);
            }
        }
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getSize() {
        return isInvulnerable() ? 96.0f : 64.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getSquaredSize() {
        return isInvulnerable() ? 4096.0f : 4096.0f;
    }

    @Override // com.prineside.tdi2.Enemy
    public final float giveDamage(Tower tower, float f, DamageType damageType) {
        if (isInvulnerable()) {
            return 0.0f;
        }
        if (!this.groupSpawned25hp && this.processor != null) {
            float buffedDamageMultiplier = getBuffedDamageMultiplier(tower == null ? null : tower.type, damageType);
            float f2 = f * buffedDamageMultiplier;
            float health = getHealth() + 0.01f;
            if (!this.groupSpawned75hp) {
                health = getHealth() - (this.maxHealth * 0.74f);
            } else if (!this.groupSpawned50hp) {
                health = getHealth() - (this.maxHealth * 0.49f);
            } else if (!this.groupSpawned25hp) {
                health = getHealth() - (this.maxHealth * 0.24f);
            }
            if (f2 > health) {
                f2 = health;
            }
            return super.giveDamage(tower, f2 / buffedDamageMultiplier, damageType);
        }
        return super.giveDamage(tower, f, damageType);
    }

    @Override // com.prineside.tdi2.Enemy
    public final float getBaseDamage() {
        return 100.0f;
    }

    public final boolean isInvulnerable() {
        return this.invulnerable;
    }

    @Override // com.prineside.tdi2.Enemy
    public final void drawBatch(Batch batch, float f) {
        if (isInvulnerable()) {
            batch.setColor(f1894a);
            batch.draw(Game.i.enemyManager.F.CONSTRUCTOR_BOSS.f1896a, this.drawPosition.x - 96.0f, this.drawPosition.y - 96.0f, 192.0f, 192.0f);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
        super.drawBatch(batch, f);
    }

    @Override // com.prineside.tdi2.Enemy
    public final boolean dynamicPathfindingAllowed() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/enemies/bosses/ConstructorBossEnemy$ConstructorBossBodyEnemyFactory.class */
    public static class ConstructorBossBodyEnemyFactory extends Enemy.Factory<ConstructorBossEnemy> {

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion f1895b;

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f1896a;

        public ConstructorBossBodyEnemyFactory() {
            super(EnemyType.CONSTRUCTOR_BOSS);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getTexture() {
            return this.f1895b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public TextureRegion getHighlightTexture() {
            return this.f1895b;
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public void setupAssets() {
            this.f1895b = Game.i.assetManager.getTextureRegion("enemy-type-boss-constructor");
            this.f1896a = Game.i.assetManager.getTextureRegion("aura-range");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Enemy.Factory
        public ConstructorBossEnemy create() {
            return new ConstructorBossEnemy((byte) 0);
        }

        @Override // com.prineside.tdi2.Enemy.Factory
        public Color getColor() {
            return MaterialColor.BLUE_GREY.P500;
        }
    }
}
