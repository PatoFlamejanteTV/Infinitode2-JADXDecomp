package com.prineside.tdi2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.buffs.BlizzardBuff;
import com.prineside.tdi2.buffs.FreezingBuff;
import com.prineside.tdi2.buffs.SlippingBuff;
import com.prineside.tdi2.buffs.ThrowBackBuff;
import com.prineside.tdi2.buffs.VulnerabilityBuff;
import com.prineside.tdi2.components.StunDebuffStats;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.pathfinding.Path;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.utils.EnumKeyArray;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import com.prineside.tdi2.utils.MultiReasonBool;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS(classOnly = true, arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Enemy.class */
public abstract class Enemy extends Registrable {
    public static final String ATTACHED_PARTICLE_REGENERATION_BUFF = "RegenerationBuff";
    public static final int UNREGISTERED_ID = 0;
    public static final float SIZE = 25.6f;

    @NAGS
    public float drawAngle;

    @NAGS
    public float drawScale;

    @NAGS
    public boolean healthBarInvisible;
    public boolean chasedByCrusher;
    public boolean gaveMiningSpeedForGauss;
    public Array<ItemStack> loot;
    public IntSet thrownBackBy;
    public float ignitionProgress;
    public int ignitionIncreasedLastFrame;
    public IntSet caughtByCrushersSet;
    public int totalCatchesByCrushers;
    public StunDebuffStats stunDebuffStats;

    @NAGS
    private ObjectMap<String, ParticleEffectPool.PooledEffect> d;
    public EnemyType type;
    public int otherEnemiesNearby;

    @Null
    public Path graphPath;

    @Null
    public Vector2 velocity;
    public boolean ignorePathfinding;
    public int sideShiftIndex;
    public float passedTiles;
    public float sumPassedTiles;
    public float existsTime;

    @EnumKeyArray(enumerator = BuffType.class)
    public DelayedRemovalArray[] buffsByType;

    @Null
    public boolean[] buffsAppliedByType;

    @Null
    public SpawnTile spawnTile;
    public boolean doesNotDisableTowers;
    public boolean ignoredOnGameOverNoEnemies;
    public boolean canNotBeDisoriented;
    public boolean ignoredByAutoWaveCall;
    public float buffFreezingLightningLengthBonus;
    public float buffFreezingPoisonDurationBonus;
    public int buffSnowballHits;
    public IntIntMap multishotTowerHits;
    public boolean wasAimedAtWithChainReactionBuff;
    public boolean wasStunnedByGauss;
    private ObjectMap<String, Object> i;

    @NAGS
    private Color j;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1693a = TLog.forClass(Enemy.class);
    public static final Color HEALTH_BAR_BACKGROUND_COLOR = new Color(0.1f, 0.1f, 0.1f, 1.0f);
    private static final Color l = new Color(Color.WHITE);

    /* renamed from: b, reason: collision with root package name */
    private float f1694b = 1.0f;
    public int killScore = 1;
    public float angle = 0.0f;
    private Vector2 c = new Vector2();

    @NAGS
    public Vector2 drawPosition = new Vector2();
    public MultiReasonBool invisible = new MultiReasonBool();
    public MultiReasonBool disabled = new MultiReasonBool();
    public MultiReasonBool notAffectsWaveKillCounter = new MultiReasonBool();
    public MultiReasonBool lowAimPriority = new MultiReasonBool();
    private float e = 100.0f;
    public float maxHealth = 100.0f;
    private float f = 1.0f;
    public float bounty = 0.0f;
    private float g = 1.0f;

    @NAGS
    private float h = 0.0f;
    public float healthBarScale = 1.0f;
    public int id = 0;
    public int pathSearches = 0;

    @Null
    public Wave wave = null;
    public float buffFreezingPercent = 0.0f;

    @NAGS
    private float k = -1.0f;

    public abstract boolean hasDrawPriority();

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f1694b);
        output.writeInt(this.killScore);
        output.writeFloat(this.angle);
        kryo.writeObject(output, this.c);
        kryo.writeObject(output, this.invisible);
        kryo.writeObject(output, this.disabled);
        output.writeBoolean(this.chasedByCrusher);
        output.writeBoolean(this.gaveMiningSpeedForGauss);
        kryo.writeObjectOrNull(output, this.loot, Array.class);
        kryo.writeObjectOrNull(output, this.thrownBackBy, IntSet.class);
        output.writeFloat(this.ignitionProgress);
        output.writeVarInt(this.ignitionIncreasedLastFrame, true);
        kryo.writeObject(output, this.notAffectsWaveKillCounter);
        kryo.writeObject(output, this.lowAimPriority);
        kryo.writeObjectOrNull(output, this.caughtByCrushersSet, IntSet.class);
        output.writeVarInt(this.totalCatchesByCrushers, true);
        kryo.writeObjectOrNull(output, this.stunDebuffStats, StunDebuffStats.class);
        kryo.writeObjectOrNull(output, this.type, EnemyType.class);
        output.writeFloat(this.e);
        output.writeFloat(this.maxHealth);
        output.writeFloat(this.f);
        output.writeFloat(this.bounty);
        output.writeFloat(this.g);
        output.writeVarInt(this.otherEnemiesNearby, true);
        output.writeFloat(this.healthBarScale);
        output.writeVarInt(this.id, true);
        kryo.writeObjectOrNull(output, this.graphPath, Path.class);
        output.writeVarInt(this.pathSearches, true);
        kryo.writeObjectOrNull(output, this.velocity, Vector2.class);
        output.writeBoolean(this.ignorePathfinding);
        output.writeByte(this.sideShiftIndex);
        output.writeFloat(this.passedTiles);
        output.writeFloat(this.sumPassedTiles);
        output.writeFloat(this.existsTime);
        kryo.writeClassAndObject(output, this.buffsByType);
        kryo.writeObjectOrNull(output, this.buffsAppliedByType, boolean[].class);
        kryo.writeObjectOrNull(output, this.spawnTile, SpawnTile.class);
        output.writeBoolean(this.ignoredOnGameOverNoEnemies);
        output.writeBoolean(this.canNotBeDisoriented);
        output.writeBoolean(this.doesNotDisableTowers);
        kryo.writeObjectOrNull(output, this.wave, Wave.class);
        output.writeBoolean(this.ignoredByAutoWaveCall);
        output.writeFloat(this.buffFreezingPercent);
        output.writeFloat(this.buffFreezingLightningLengthBonus);
        output.writeFloat(this.buffFreezingPoisonDurationBonus);
        output.writeVarInt(this.buffSnowballHits, true);
        kryo.writeObjectOrNull(output, this.multishotTowerHits, IntIntMap.class);
        output.writeBoolean(this.wasAimedAtWithChainReactionBuff);
        output.writeBoolean(this.wasStunnedByGauss);
        kryo.writeObjectOrNull(output, this.i, ObjectMap.class);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1694b = input.readFloat();
        this.killScore = input.readInt();
        this.angle = input.readFloat();
        this.c = (Vector2) kryo.readObject(input, Vector2.class);
        this.invisible = (MultiReasonBool) kryo.readObject(input, MultiReasonBool.class);
        this.disabled = (MultiReasonBool) kryo.readObject(input, MultiReasonBool.class);
        this.chasedByCrusher = input.readBoolean();
        this.gaveMiningSpeedForGauss = input.readBoolean();
        this.loot = (Array) kryo.readObjectOrNull(input, Array.class);
        this.thrownBackBy = (IntSet) kryo.readObjectOrNull(input, IntSet.class);
        this.ignitionProgress = input.readFloat();
        this.ignitionIncreasedLastFrame = input.readVarInt(true);
        this.notAffectsWaveKillCounter = (MultiReasonBool) kryo.readObject(input, MultiReasonBool.class);
        this.lowAimPriority = (MultiReasonBool) kryo.readObject(input, MultiReasonBool.class);
        this.caughtByCrushersSet = (IntSet) kryo.readObjectOrNull(input, IntSet.class);
        this.totalCatchesByCrushers = input.readVarInt(true);
        this.stunDebuffStats = (StunDebuffStats) kryo.readObjectOrNull(input, StunDebuffStats.class);
        this.type = (EnemyType) kryo.readObjectOrNull(input, EnemyType.class);
        this.e = input.readFloat();
        this.maxHealth = input.readFloat();
        this.f = input.readFloat();
        this.bounty = input.readFloat();
        this.g = input.readFloat();
        this.otherEnemiesNearby = input.readVarInt(true);
        this.healthBarScale = input.readFloat();
        this.id = input.readVarInt(true);
        this.graphPath = (Path) kryo.readObjectOrNull(input, Path.class);
        this.pathSearches = input.readVarInt(true);
        this.velocity = (Vector2) kryo.readObjectOrNull(input, Vector2.class);
        this.ignorePathfinding = input.readBoolean();
        this.sideShiftIndex = input.readByte();
        this.passedTiles = input.readFloat();
        this.sumPassedTiles = input.readFloat();
        this.existsTime = input.readFloat();
        this.buffsByType = (DelayedRemovalArray[]) kryo.readClassAndObject(input);
        this.buffsAppliedByType = (boolean[]) kryo.readObjectOrNull(input, boolean[].class);
        this.spawnTile = (SpawnTile) kryo.readObjectOrNull(input, SpawnTile.class);
        this.ignoredOnGameOverNoEnemies = input.readBoolean();
        this.canNotBeDisoriented = input.readBoolean();
        this.doesNotDisableTowers = input.readBoolean();
        this.wave = (Wave) kryo.readObjectOrNull(input, Wave.class);
        this.ignoredByAutoWaveCall = input.readBoolean();
        this.buffFreezingPercent = input.readFloat();
        this.buffFreezingLightningLengthBonus = input.readFloat();
        this.buffFreezingPoisonDurationBonus = input.readFloat();
        this.buffSnowballHits = input.readVarInt(true);
        this.multishotTowerHits = (IntIntMap) kryo.readObjectOrNull(input, IntIntMap.class);
        this.wasAimedAtWithChainReactionBuff = input.readBoolean();
        this.wasStunnedByGauss = input.readBoolean();
        this.i = (ObjectMap) kryo.readObjectOrNull(input, ObjectMap.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Enemy(EnemyType enemyType) {
        this.type = enemyType;
    }

    @Null
    public Object getUserData(String str) {
        if (this.i != null) {
            return this.i.get(str, null);
        }
        return null;
    }

    @Null
    public ObjectMap<String, Object> getAllUserData() {
        return this.i;
    }

    @Null
    public ObjectMap<String, ParticleEffectPool.PooledEffect> getAttachedParticles() {
        return this.d;
    }

    @Null
    public ParticleEffectPool.PooledEffect attachParticle(String str, ParticleEffectPool.PooledEffect pooledEffect) {
        Preconditions.checkNotNull(str, "key can not be null");
        Preconditions.checkNotNull(pooledEffect, "effect can not be null");
        pooledEffect.setPosition(getPosition().x, getPosition().y);
        if (this.d == null) {
            this.d = new ObjectMap<>();
        }
        return this.d.put(str, pooledEffect);
    }

    @Null
    public ParticleEffectPool.PooledEffect detachParticle(String str) {
        Preconditions.checkNotNull(str);
        if (this.d == null) {
            return null;
        }
        ParticleEffectPool.PooledEffect remove = this.d.remove(str);
        if (this.d.size == 0) {
            this.d = null;
        }
        return remove;
    }

    @Null
    public ParticleEffectPool.PooledEffect getAttachedParticle(String str) {
        if (this.d == null) {
            return null;
        }
        return this.d.get(str, null);
    }

    public void setUserData(String str, @Null Object obj) {
        if (obj == null) {
            if (this.i != null) {
                this.i.remove(str);
                if (this.i.size == 0) {
                    this.i = null;
                    return;
                }
                return;
            }
            return;
        }
        if (this.i == null) {
            this.i = new ObjectMap<>();
        }
        this.i.put(str, obj);
    }

    public EnemyType getTypeForPathfinding() {
        return this.type;
    }

    @Null
    public Tile getCurrentTile() {
        return this.S.map.getMap().getTileByMapPosV(getPosition());
    }

    public Color getColor() {
        return this.S.enemy.getColor(this.type);
    }

    public void initBuffsByTypeArray() {
        if (this.buffsByType != null) {
            return;
        }
        this.buffsByType = new DelayedRemovalArray[BuffType.values.length];
        for (int i = 0; i < BuffType.values.length; i++) {
            this.buffsByType[i] = new DelayedRemovalArray(false, 0, BuffType.relevantClasses[i]);
        }
    }

    public boolean hasBuffsByType(BuffType buffType) {
        return (this.buffsByType == null || this.buffsByType[buffType.ordinal()].size == 0) ? false : true;
    }

    public DelayedRemovalArray getBuffsByTypeOrNull(BuffType buffType) {
        if (this.buffsByType == null) {
            return null;
        }
        return this.buffsByType[buffType.ordinal()];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void update(float f) {
        float f2;
        this.existsTime += f;
        DelayedRemovalArray buffsByTypeOrNull = getBuffsByTypeOrNull(BuffType.SLIPPING);
        float f3 = (buffsByTypeOrNull == null || buffsByTypeOrNull.size == 0) ? 1.0f : ((SlippingBuff) buffsByTypeOrNull.first()).speedMultiplier;
        float f4 = (buffsByTypeOrNull == null || buffsByTypeOrNull.size == 0) ? 1.0f : ((SlippingBuff) buffsByTypeOrNull.first()).throwBackDistance;
        DelayedRemovalArray buffsByTypeOrNull2 = getBuffsByTypeOrNull(BuffType.THROW_BACK);
        if (buffsByTypeOrNull2 != null && buffsByTypeOrNull2.size != 0) {
            f2 = (-((ThrowBackBuff) buffsByTypeOrNull2.first()).force) * f4;
        } else if (hasBuffsByType(BuffType.BLIZZARD) || hasBuffsByType(BuffType.SNOWBALL) || hasBuffsByType(BuffType.STUN)) {
            f2 = 0.0f;
        } else {
            f2 = this.f * (100.0f - this.buffFreezingPercent) * 0.01f * f3;
        }
        float percentValueAsMultiplier = (float) (f2 * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ENEMIES_SPEED));
        if (percentValueAsMultiplier != this.g) {
            float f5 = f * 8.0f;
            float f6 = percentValueAsMultiplier - this.g;
            float f7 = f6;
            float abs = StrictMath.abs(f6);
            if (abs > f5) {
                f7 = (f5 / abs) * f7;
            }
            this.g += f7;
        }
        if (this.S.gameState.updateNumber > this.ignitionIncreasedLastFrame + 1) {
            this.ignitionProgress -= f;
            if (this.ignitionProgress < 0.0f) {
                this.ignitionProgress = 0.0f;
            }
        }
        this.invisible.update(f);
        this.disabled.update(f);
        this.notAffectsWaveKillCounter.update(f);
        this.lowAimPriority.update(f);
    }

    public boolean canHaveRandomSideShift() {
        return true;
    }

    public float giveDamage(Tower tower, float f, DamageType damageType) {
        float buffedDamageMultiplier = f * getBuffedDamageMultiplier(tower == null ? null : tower.type, damageType);
        float f2 = buffedDamageMultiplier;
        if (buffedDamageMultiplier > getHealth()) {
            f2 = getHealth();
        }
        getHealth();
        setHealth(getHealth() - f2);
        this.h += 0.5f;
        if (this.h > 1.0f) {
            this.h = 1.0f;
        }
        return f2;
    }

    public float giveDamageRaw(float f, DamageType damageType) {
        if (f > getHealth()) {
            f = getHealth();
        }
        getHealth();
        setHealth(getHealth() - f);
        this.h += 0.5f;
        if (this.h > 1.0f) {
            this.h = 1.0f;
        }
        return f;
    }

    public float getSize() {
        return 25.6f;
    }

    public float getSquaredSize() {
        return 655.36005f;
    }

    public void drawBatch(Batch batch, float f) {
        drawBatch(batch, f, Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    public void drawBatchAdditive(Batch batch, float f) {
        if (this.h != 0.0f) {
            l.set(1.0f, 1.0f, 1.0f, this.h);
            drawBatch(batch, f, l);
            this.h -= f * 10.0f;
            if (this.h < 0.0f) {
                this.h = 0.0f;
            }
        }
    }

    public void setPositionToPath() {
        if (this.graphPath == null) {
            return;
        }
        this.angle = this.graphPath.getRotation(this.passedTiles, this.sideShiftIndex);
        this.graphPath.getPosition(this.passedTiles, this.sideShiftIndex, this.c);
        applyDrawInterpolation(0.0f);
        onPositionSetToPath();
    }

    public Vector2 getPosition() {
        return this.c;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final void setPosition(Vector2 vector2) {
        this.c.set(vector2);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public void setPosition(float f, float f2) {
        this.c.set(f, f2);
    }

    public void applyDrawInterpolation(float f) {
        if (this.S._gameUi == null) {
            return;
        }
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        if (this.disabled.isTrue()) {
            this.drawAngle = this.angle;
            this.drawPosition.set(getPosition());
        } else if (this.graphPath != null) {
            float passedTilesDelta = this.passedTiles + getPassedTilesDelta(f);
            float f2 = passedTilesDelta;
            if (passedTilesDelta < -0.49999f) {
                f2 = -0.499999f;
            }
            try {
                this.graphPath.getPosition(f2, this.sideShiftIndex, this.drawPosition);
                this.drawAngle = this.graphPath.getRotation(f2, this.sideShiftIndex);
            } catch (Exception e) {
                f1693a.e("passedTiles: " + this.passedTiles + ", interpolatedTime: " + f + ", " + getPassedTilesDelta(f), new Object[0]);
                throw e;
            }
        } else {
            this.drawAngle = this.angle;
            this.drawPosition.set(getPosition());
            if (this.velocity != null) {
                this.drawPosition.add(this.velocity.x * f * getBuffedSpeed() * 128.0f, this.velocity.y * f * getBuffedSpeed() * 128.0f);
                this.drawAngle = this.velocity.angleDeg();
            }
        }
        if (this.existsTime + f < 1.0f) {
            this.drawScale = Interpolation.pow2Out.apply(this.existsTime + f);
        } else {
            this.drawScale = 1.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void drawBatch(Batch batch, float f, Color color) {
        TextureRegion texture;
        float regionWidth;
        l.set(color);
        batch.setColor(l);
        if (this.S.enemy.isEmojiEnemies()) {
            TextureRegion emojiTexture = getEmojiTexture();
            regionWidth = emojiTexture.getRegionWidth() * this.drawScale;
            batch.draw(emojiTexture, this.drawPosition.x - (regionWidth * 0.5f), this.drawPosition.y - (regionWidth * 0.5f), regionWidth, regionWidth);
        } else {
            if (this.loot != null) {
                texture = getHighlightTexture();
            } else {
                texture = getTexture();
            }
            regionWidth = texture.getRegionWidth() * this.drawScale;
            batch.draw(texture, this.drawPosition.x - (regionWidth * 0.5f), this.drawPosition.y - (regionWidth * 0.5f), regionWidth * 0.5f, regionWidth * 0.5f, regionWidth, regionWidth, 1.0f, 1.0f, this.drawAngle);
        }
        if (hasBuffsByType(BuffType.BLIZZARD) || hasBuffsByType(BuffType.SNOWBALL)) {
            float f2 = regionWidth;
            batch.draw(Game.i.enemyManager.getIceOverlayTexture(this.id % 2), this.drawPosition.x - (regionWidth * 0.5f), this.drawPosition.y - (regionWidth * 0.5f), f2, f2);
        }
    }

    public ItemStack addLoot(Item item, int i) {
        if (this.loot == null) {
            this.loot = new Array<>(true, 1, ItemStack.class);
        }
        ItemStack addItemToStacksArray = ProgressManager.addItemToStacksArray(this.loot, item, i);
        addItemToStacksArray.setCovered((addItemToStacksArray.getItem() == Item.D.GREEN_PAPER || addItemToStacksArray.getItem() == Item.D.BIT_DUST) ? false : true);
        return addItemToStacksArray;
    }

    public TextureRegion getTexture() {
        return this.S.enemy.getTexture(this.type);
    }

    public TextureRegion getHighlightTexture() {
        return this.S.enemy.getHighlightTexture(this.type);
    }

    public TextureRegion getEmojiTexture() {
        return this.S.enemy.getEmojiTexture(this.type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void drawHealth(Batch batch) {
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        if (this.buffsByType != null) {
            int i = 0;
            for (BuffType buffType : BuffType.values) {
                if (this.buffsByType[buffType.ordinal()].size != 0) {
                    i++;
                }
            }
            float f = this.drawPosition.x - ((i * 16.0f) * 0.5f);
            for (BuffType buffType2 : BuffType.values) {
                DelayedRemovalArray delayedRemovalArray = this.buffsByType[buffType2.ordinal()];
                if (delayedRemovalArray.size != 0) {
                    int i2 = 0;
                    int i3 = delayedRemovalArray.size - 1;
                    int i4 = i3;
                    if (i3 > 6) {
                        i4 = 6;
                    }
                    for (int i5 = i4; i5 >= 0; i5--) {
                        i2++;
                        batch.draw(((Buff[]) delayedRemovalArray.items)[i5].getHealthBarIcon(), f, this.drawPosition.y + 68.0f + (i5 * 6.0f), 16.0f, 16.0f);
                        if (i2 == 5) {
                            break;
                        }
                    }
                    f += 16.0f;
                }
            }
        }
        float f2 = this.e / this.maxHealth;
        batch.setColor(HEALTH_BAR_BACKGROUND_COLOR);
        batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), this.drawPosition.x - (28.0f * this.healthBarScale), this.drawPosition.y + (52.0f * this.healthBarScale), 56.0f * this.healthBarScale, 8.0f * this.healthBarScale);
        if (this.j == null || this.k != f2) {
            if (this.j == null) {
                this.j = new Color();
            }
            this.k = f2;
            this.j.set(0.956f + ((-0.658f) * f2), 0.262f + (0.424f * f2), 0.211f + (0.102f * f2), 1.0f);
        }
        batch.setColor(this.j);
        batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), this.drawPosition.x - (26.0f * this.healthBarScale), this.drawPosition.y + (54.0f * this.healthBarScale), (int) (52.0f * f2 * this.healthBarScale), 4.0f * this.healthBarScale);
    }

    public void setHealth(float f) {
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException("HP is NaN, previously " + this.e);
        }
        this.e = f;
    }

    public void setKillExp(float f) {
        this.f1694b = f;
    }

    public float getKillExp() {
        return this.f1694b;
    }

    public int getKillScore() {
        return this.killScore;
    }

    public float getHealth() {
        return this.e;
    }

    public void setMaxHealth(float f) {
        this.maxHealth = f;
    }

    public void setSpeed(float f) {
        this.f = f;
    }

    public float getSpeed() {
        return this.f;
    }

    public final float getPassedTilesDelta(float f) {
        return this.graphPath.getPassedTilesDelta(f, this.passedTiles, this.sideShiftIndex, getBuffedSpeed());
    }

    public float getBuffedSpeed() {
        return this.g;
    }

    public float getTowerDamageMultiplier(TowerType towerType) {
        return this.S.tower.towerEnemyDamageMultiplier[this.type.ordinal()][towerType.ordinal()];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public float getBuffedDamageMultiplier(@Null TowerType towerType, DamageType damageType) {
        float towerDamageMultiplier = towerType == null ? 1.0f : getTowerDamageMultiplier(towerType);
        if (hasBuffsByType(BuffType.INVULNERABILITY)) {
            return 0.0f;
        }
        DelayedRemovalArray buffsByTypeOrNull = getBuffsByTypeOrNull(BuffType.FREEZING);
        int i = 0;
        if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
            int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_FREEZING_A_EVAPORATION_STACK);
            float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FREEZING_A_EVAPORATION_DAMAGE);
            for (int i2 = 0; i2 < buffsByTypeOrNull.size; i2++) {
                FreezingBuff freezingBuff = ((FreezingBuff[]) buffsByTypeOrNull.items)[i2];
                if (freezingBuff.tower != null && freezingBuff.tower.type == TowerType.FREEZING && freezingBuff.tower.isAbilityInstalled(0)) {
                    i++;
                    if (i == intValue) {
                        break;
                    }
                }
            }
            towerDamageMultiplier *= 1.0f + (percentValueAsMultiplier * i);
        }
        if (hasBuffsByType(BuffType.ARMOR)) {
            towerDamageMultiplier *= 0.5f;
        }
        DelayedRemovalArray buffsByTypeOrNull2 = getBuffsByTypeOrNull(BuffType.BLIZZARD);
        if (buffsByTypeOrNull2 != null && buffsByTypeOrNull2.size != 0) {
            towerDamageMultiplier *= ((BlizzardBuff) buffsByTypeOrNull2.first()).damageMultiplier;
        }
        DelayedRemovalArray buffsByTypeOrNull3 = getBuffsByTypeOrNull(BuffType.VULNERABILITY);
        if (buffsByTypeOrNull3 != null && buffsByTypeOrNull3.size != 0) {
            float f = 1.0f;
            for (int i3 = 0; i3 < buffsByTypeOrNull3.size; i3++) {
                VulnerabilityBuff vulnerabilityBuff = (VulnerabilityBuff) buffsByTypeOrNull3.items[i3];
                if (vulnerabilityBuff.damageMultiplier > f) {
                    f = vulnerabilityBuff.damageMultiplier;
                }
            }
            towerDamageMultiplier *= f;
        }
        float percentValueAsMultiplier2 = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.ENEMIES_VULNERABILITY);
        float f2 = percentValueAsMultiplier2;
        if (percentValueAsMultiplier2 < 0.0f) {
            f2 = 0.0f;
        }
        return towerDamageMultiplier * f2;
    }

    public float getBaseDamage() {
        return 1.0f;
    }

    public void onSpawned() {
    }

    public void onPreDie() {
    }

    public void onPositionSetToPath() {
    }

    public final boolean canBeBuffed(BuffType buffType) {
        return getBuffVulnerability(buffType) > 0.0f;
    }

    public float getBuffVulnerability(BuffType buffType) {
        if (hasBuffsByType(BuffType.INVULNERABILITY) && this.S.buff.getProcessor(buffType).isDebuff()) {
            return 0.0f;
        }
        return this.S.enemy.enemyBuffVulnerability[this.type.ordinal()][buffType.ordinal()];
    }

    public boolean isVulnerableTo(DamageType damageType) {
        if (hasBuffsByType(BuffType.INVULNERABILITY)) {
            return false;
        }
        return this.S.enemy.enemyDamageVulnerability[this.type.ordinal()][damageType.ordinal()];
    }

    public boolean isBossRelated() {
        return false;
    }

    public boolean isBossMainBodyPart() {
        return false;
    }

    public boolean isVulnerableToSpecial(SpecialDamageType specialDamageType) {
        if (hasBuffsByType(BuffType.INVULNERABILITY)) {
            return false;
        }
        return this.S.enemy.enemySpecialDamageVulnerability[this.type.ordinal()][specialDamageType.ordinal()];
    }

    public float getAbilityVulnerability(AbilityType abilityType) {
        if (hasBuffsByType(BuffType.INVULNERABILITY)) {
            return 0.0f;
        }
        return 1.0f;
    }

    public boolean canBeAttackedBy(Tower tower) {
        return true;
    }

    public boolean isAir() {
        return this.S.enemy.flyingEnemy[this.type.ordinal()];
    }

    public boolean dynamicPathfindingAllowed() {
        return true;
    }

    public ParticleEffectPool.PooledEffect getBreakParticle() {
        return ((Factory) Game.i.enemyManager.getFactory(this.type)).f1695a.obtain();
    }

    public ParticleEffectPool.PooledEffect getHitParticle() {
        return ((Factory) Game.i.enemyManager.getFactory(this.type)).f1696b.obtain();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Enemy$Factory.class */
    public static abstract class Factory<T extends Enemy> implements Disposable, EntityFactory {

        /* renamed from: a, reason: collision with root package name */
        private ParticleEffectPool f1695a;

        /* renamed from: b, reason: collision with root package name */
        private ParticleEffectPool f1696b;
        private final String c;
        private final String d;

        protected abstract T create();

        public abstract Color getColor();

        public abstract TextureRegion getTexture();

        public abstract TextureRegion getHighlightTexture();

        public Factory(EnemyType enemyType) {
            this.c = "enemy_name_" + enemyType.name();
            this.d = "enemy_description_" + enemyType.name();
            if (Game.i.assetManager != null) {
                ParticleEffect particleEffect = new ParticleEffect();
                particleEffect.load(Gdx.files.internal("particles/break.prt"), AssetManager.TextureRegions.i().blank.getAtlas());
                particleEffect.setEmittersCleanUpBlendFunction(false);
                particleEffect.getEmitters().get(0).getTint().setColors(new float[]{getColor().r, getColor().g, getColor().f888b});
                this.f1695a = Game.i.assetManager.getParticleEffectPoolWithTemplate("break.prt@enemyType:" + enemyType.name(), particleEffect);
                ParticleEffect particleEffect2 = new ParticleEffect();
                particleEffect2.load(Gdx.files.internal("particles/enemy-hit.prt"), AssetManager.TextureRegions.i().blank.getAtlas());
                particleEffect2.setEmittersCleanUpBlendFunction(false);
                particleEffect2.getEmitters().get(0).getTint().setColors(new float[]{getColor().r, getColor().g, getColor().f888b});
                this.f1696b = Game.i.assetManager.getParticleEffectPoolWithTemplate("enemy-hit.prt@enemyType:" + enemyType.name(), particleEffect2);
            }
        }

        public void setup() {
            if (Game.i.assetManager != null) {
                setupAssets();
            }
        }

        public void setupAssets() {
        }

        public final T obtain() {
            return create();
        }

        public String getTitle() {
            return Game.i.localeManager.i18n.get(this.c);
        }

        public String getDescription() {
            return Game.i.localeManager.i18n.get(this.d);
        }

        public TextureRegion getEmojiTexture() {
            return getTexture();
        }

        public int getTextureSize() {
            return getTexture().getRegionWidth();
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
        }
    }

    @REGS(arrayLevels = 1)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Enemy$EnemyReference.class */
    public static class EnemyReference implements KryoSerializable {
        public static final EnemyReference NULL;
        public Enemy enemy;

        static {
            EnemyReference enemyReference = new EnemyReference();
            NULL = enemyReference;
            SyncChecker.addSyncShareableObject(enemyReference);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.enemy);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.enemy = (Enemy) kryo.readClassAndObject(input);
        }
    }
}
