package com.prineside.tdi2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.abilities.LoopAbility;
import com.prineside.tdi2.components.PowerBonuses;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.TowerManager;
import com.prineside.tdi2.modifiers.BalanceModifier;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.shapes.RangeCircle;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.systems.MapSystem;
import com.prineside.tdi2.systems.TowerSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.MultiReasonBool;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectFilter;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Iterator;
import net.bytebuddy.utility.JavaConstant;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Tower.class */
public abstract class Tower extends Building {
    public static final int ABILITY_INDEX_SPECIAL = 3;
    public static final int ABILITY_INDEX_ULTIMATE = 4;
    public static final int ABILITY_INDEX_POWERFUL = 5;
    public static final int ABILITIES_COUNT = 6;
    public static final int DPS_STAT_SLOTS = 10;
    public static final float DPS_STAT_INTERVAL = 10.0f;
    public static final short MAX_LEVEL = 999;
    public static final byte MAX_UPGRADE_LEVEL = 10;
    public static final int[] LEVEL_EXPERIENCE;
    public static final int[] LEVEL_EXPERIENCE_MILESTONES;
    private static final AimStrategyEnemyComparator[] g;
    public int id;
    public TowerType type;
    public AimStrategy aimStrategy;
    public int moneySpentOn;
    private float h;

    /* renamed from: a, reason: collision with root package name */
    protected byte f1776a;
    public byte bountyModifiersNearby;
    private byte i;
    public PowerBonuses powerBonuses;

    @FrameAccumulatorForPerformance
    private byte j;

    /* renamed from: b, reason: collision with root package name */
    protected boolean f1777b;
    public float damageGiven;
    public int shotCount;
    public float loopAbilityDamageBuffer;

    @Null
    public LoopAbility affectedByLoopAbility;
    public float angle;
    public float experience;
    public float currentLevelExperience;
    public float nextLevelExperience;
    public short level;
    private byte k;
    public boolean[] installedAbilities;
    private byte l;
    protected float c;
    public float rangeInPixels;
    public float minRangeInPixels;
    public float experienceGeneration;
    public float experienceMultiplier;
    public MultiReasonBool outOfOrder;
    public boolean attackDisabled;
    private float[] m;
    private float n;
    private float o;
    private Enemy.EnemyReference p;

    @NAGS
    public ParticleEffectPool.PooledEffect abilityAvailableParticleEffect;
    public float[] dpsDamage;
    public float[] dpsTime;
    public float mdps;
    public int enemiesKilled;
    public float bonusCoinsBrought;
    protected static final StringBuilder d;
    private static final TLog e = TLog.forClass(Tower.class);
    private static final Color f = new Color(0.56f, 0.56f, 0.56f, 1.0f);
    public static final Color SHADOW_COLOR = new Color(0.0f, 0.0f, 0.0f, 0.21f);
    public static final String[] ABILITY_NAMES = {"", "", "", "SPECIAL", "ULTIMATE", "POWERFUL"};

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Tower$AimStrategy.class */
    public enum AimStrategy {
        FIRST,
        LAST,
        WEAKEST,
        STRONGEST,
        NEAREST,
        RANDOM;

        public static final AimStrategy[] values = values();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Tower$AimStrategyEnemyComparator.class */
    public interface AimStrategyEnemyComparator {
        boolean compare(Tower tower, Enemy enemy, Enemy enemy2);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Tower$FindEnemyFilter.class */
    public static abstract class FindEnemyFilter {
        public abstract boolean isValid(Enemy enemy);
    }

    public abstract Quad getWeaponTextures();

    public abstract boolean canAim();

    static {
        int[] iArr = new int[1000];
        LEVEL_EXPERIENCE = iArr;
        iArr[0] = 0;
        LEVEL_EXPERIENCE[1] = 0;
        LEVEL_EXPERIENCE[2] = 60;
        LEVEL_EXPERIENCE[3] = 80;
        LEVEL_EXPERIENCE[4] = 110;
        LEVEL_EXPERIENCE[5] = 140;
        LEVEL_EXPERIENCE[6] = 180;
        LEVEL_EXPERIENCE[7] = 220;
        LEVEL_EXPERIENCE[8] = 260;
        LEVEL_EXPERIENCE[9] = 300;
        LEVEL_EXPERIENCE[10] = 350;
        LEVEL_EXPERIENCE[11] = 400;
        LEVEL_EXPERIENCE[12] = 450;
        LEVEL_EXPERIENCE[13] = 500;
        LEVEL_EXPERIENCE[14] = 550;
        LEVEL_EXPERIENCE[15] = 600;
        LEVEL_EXPERIENCE[16] = 650;
        LEVEL_EXPERIENCE[17] = 700;
        LEVEL_EXPERIENCE[18] = 750;
        LEVEL_EXPERIENCE[19] = 800;
        LEVEL_EXPERIENCE[20] = 850;
        for (int i = 21; i < 1000; i++) {
            int i2 = i;
            LEVEL_EXPERIENCE[i2] = ((i2 - 20) * 50) + 850;
        }
        LEVEL_EXPERIENCE_MILESTONES = new int[1000];
        int i3 = 0;
        for (int i4 = 0; i4 <= 999; i4++) {
            i3 += LEVEL_EXPERIENCE[i4];
            LEVEL_EXPERIENCE_MILESTONES[i4] = i3;
        }
        AimStrategyEnemyComparator[] aimStrategyEnemyComparatorArr = new AimStrategyEnemyComparator[AimStrategy.values.length];
        g = aimStrategyEnemyComparatorArr;
        aimStrategyEnemyComparatorArr[AimStrategy.FIRST.ordinal()] = (tower, enemy, enemy2) -> {
            return (enemy.graphPath == null ? 9001.0f : ((float) enemy.graphPath.getLengthInTiles()) - enemy.passedTiles) < (enemy2.graphPath == null ? 9001.0f : ((float) enemy2.graphPath.getLengthInTiles()) - enemy2.passedTiles);
        };
        g[AimStrategy.LAST.ordinal()] = (tower2, enemy3, enemy4) -> {
            return (enemy3.graphPath == null ? 9001.0f : ((float) enemy3.graphPath.getLengthInTiles()) - enemy3.passedTiles) > (enemy4.graphPath == null ? 9001.0f : ((float) enemy4.graphPath.getLengthInTiles()) - enemy4.passedTiles);
        };
        g[AimStrategy.WEAKEST.ordinal()] = (tower3, enemy5, enemy6) -> {
            return enemy5.getHealth() < enemy6.getHealth();
        };
        g[AimStrategy.STRONGEST.ordinal()] = (tower4, enemy7, enemy8) -> {
            return enemy7.getHealth() > enemy8.getHealth();
        };
        g[AimStrategy.NEAREST.ordinal()] = (tower5, enemy9, enemy10) -> {
            return tower5.getTile().center.dst2(enemy9.getPosition()) < tower5.getTile().center.dst2(enemy10.getPosition());
        };
        d = new StringBuilder();
    }

    @Override // com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.id, true);
        kryo.writeObjectOrNull(output, this.type, TowerType.class);
        kryo.writeObject(output, this.aimStrategy);
        output.writeVarInt(this.moneySpentOn, true);
        output.writeFloat(this.h);
        output.writeByte(this.f1776a);
        output.writeByte(this.bountyModifiersNearby);
        output.writeByte(this.i);
        kryo.writeObjectOrNull(output, this.powerBonuses, PowerBonuses.class);
        output.writeByte(this.j);
        output.writeBoolean(this.f1777b);
        output.writeFloat(this.damageGiven);
        output.writeVarInt(this.shotCount, true);
        output.writeFloat(this.loopAbilityDamageBuffer);
        kryo.writeClassAndObject(output, this.affectedByLoopAbility);
        output.writeFloat(this.angle);
        output.writeFloat(this.experience);
        output.writeFloat(this.currentLevelExperience);
        output.writeFloat(this.nextLevelExperience);
        output.writeShort(this.level);
        output.writeByte(this.k);
        kryo.writeObject(output, this.installedAbilities);
        output.writeByte(this.l);
        output.writeFloat(this.c);
        output.writeFloat(this.rangeInPixels);
        output.writeFloat(this.minRangeInPixels);
        output.writeFloat(this.experienceGeneration);
        output.writeFloat(this.experienceMultiplier);
        kryo.writeObject(output, this.m);
        output.writeFloat(this.n);
        output.writeFloat(this.o);
        kryo.writeObjectOrNull(output, this.outOfOrder, MultiReasonBool.class);
        output.writeBoolean(this.attackDisabled);
        kryo.writeObject(output, this.p);
        kryo.writeObject(output, this.dpsDamage);
        kryo.writeObject(output, this.dpsTime);
        output.writeFloat(this.mdps);
        output.writeInt(this.enemiesKilled);
        output.writeFloat(this.bonusCoinsBrought);
    }

    @Override // com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.id = input.readVarInt(true);
        this.type = (TowerType) kryo.readObjectOrNull(input, TowerType.class);
        this.aimStrategy = (AimStrategy) kryo.readObject(input, AimStrategy.class);
        this.moneySpentOn = input.readVarInt(true);
        this.h = input.readFloat();
        this.f1776a = input.readByte();
        this.bountyModifiersNearby = input.readByte();
        this.i = input.readByte();
        this.powerBonuses = (PowerBonuses) kryo.readObjectOrNull(input, PowerBonuses.class);
        this.j = input.readByte();
        this.f1777b = input.readBoolean();
        this.damageGiven = input.readFloat();
        this.shotCount = input.readVarInt(true);
        this.loopAbilityDamageBuffer = input.readFloat();
        this.affectedByLoopAbility = (LoopAbility) kryo.readClassAndObject(input);
        this.angle = input.readFloat();
        this.experience = input.readFloat();
        this.currentLevelExperience = input.readFloat();
        this.nextLevelExperience = input.readFloat();
        this.level = input.readShort();
        this.k = input.readByte();
        this.installedAbilities = (boolean[]) kryo.readObject(input, boolean[].class);
        this.l = input.readByte();
        this.c = input.readFloat();
        this.rangeInPixels = input.readFloat();
        this.minRangeInPixels = input.readFloat();
        this.experienceGeneration = input.readFloat();
        this.experienceMultiplier = input.readFloat();
        this.m = (float[]) kryo.readObject(input, float[].class);
        this.n = input.readFloat();
        this.o = input.readFloat();
        this.outOfOrder = (MultiReasonBool) kryo.readObjectOrNull(input, MultiReasonBool.class);
        this.attackDisabled = input.readBoolean();
        this.p = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
        this.dpsDamage = (float[]) kryo.readObject(input, float[].class);
        this.dpsTime = (float[]) kryo.readObject(input, float[].class);
        this.mdps = input.readFloat();
        this.enemiesKilled = input.readInt();
        this.bonusCoinsBrought = input.readFloat();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Tower(TowerType towerType) {
        super(BuildingType.TOWER);
        this.aimStrategy = AimStrategy.FIRST;
        this.moneySpentOn = 0;
        this.damageGiven = 0.0f;
        this.shotCount = 0;
        this.angle = 0.0f;
        this.experience = 0.0f;
        this.currentLevelExperience = 0.0f;
        this.nextLevelExperience = 0.0f;
        this.level = (short) 1;
        this.k = (byte) 0;
        this.installedAbilities = new boolean[6];
        this.experienceGeneration = 0.0f;
        this.experienceMultiplier = 1.0f;
        this.outOfOrder = new MultiReasonBool();
        this.m = new float[TowerStatType.values.length];
        this.o = 0.0f;
        this.p = Enemy.EnemyReference.NULL;
        this.dpsDamage = new float[10];
        this.dpsTime = new float[10];
        this.mdps = 0.0f;
        this.enemiesKilled = 0;
        this.bonusCoinsBrought = 0.0f;
        this.type = towerType;
    }

    public void setAimStrategy(AimStrategy aimStrategy) {
        this.aimStrategy = aimStrategy;
    }

    @Override // com.prineside.tdi2.Registrable
    public void setRegistered(GameSystemProvider gameSystemProvider) {
        super.setRegistered(gameSystemProvider);
        setExperience(this.experience);
    }

    @Override // com.prineside.tdi2.Registrable
    public void setUnregistered() {
        this.p = Enemy.EnemyReference.NULL;
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.Building
    public float getWalkCost() {
        return 262144.0f;
    }

    @Override // com.prineside.tdi2.Building
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("type", this.type.name());
        json.writeValue("as", this.aimStrategy.name());
        json.writeValue("e", Float.valueOf(this.experience));
        json.writeValue(FlexmarkHtmlConverter.UL_NODE, Byte.valueOf(this.k));
        json.writeArrayStart("ia");
        for (boolean z : this.installedAbilities) {
            json.writeValue(Boolean.valueOf(z));
        }
        json.writeArrayEnd();
    }

    public void loadFromJson(JsonValue jsonValue) {
        try {
            this.aimStrategy = AimStrategy.valueOf(jsonValue.getString("as"));
            this.k = (byte) jsonValue.getInt(FlexmarkHtmlConverter.UL_NODE);
            this.experience = jsonValue.getFloat("e");
            int i = 0;
            Iterator<JsonValue> iterator2 = jsonValue.get("ia").iterator2();
            while (iterator2.hasNext()) {
                this.installedAbilities[i] = iterator2.next().asBoolean();
                i++;
            }
            calculateXpLevel(true);
        } catch (Exception e2) {
            e.e("failed to load tower from json", e2);
        }
    }

    @Override // com.prineside.tdi2.Building
    public boolean sameAs(Building building) {
        if (!super.sameAs(building)) {
            return false;
        }
        Tower tower = (Tower) building;
        if (tower.type != this.type || tower.aimStrategy != this.aimStrategy || tower.k != this.k) {
            return false;
        }
        for (int i = 0; i < this.installedAbilities.length; i++) {
            if (this.installedAbilities[i] != tower.installedAbilities[i]) {
                return false;
            }
        }
        return tower.angle == this.angle && tower.currentLevelExperience == this.currentLevelExperience && tower.experience == this.experience && tower.level == this.level && tower.nextLevelExperience == this.nextLevelExperience && tower.moneySpentOn == this.moneySpentOn;
    }

    @Override // com.prineside.tdi2.Building
    public Tower cloneBuilding() {
        Tower create = Game.i.towerManager.getFactory(this.type).create();
        create.aimStrategy = this.aimStrategy;
        create.k = this.k;
        System.arraycopy(this.installedAbilities, 0, create.installedAbilities, 0, this.installedAbilities.length);
        create.angle = this.angle;
        create.currentLevelExperience = this.currentLevelExperience;
        create.experience = this.experience;
        create.level = this.level;
        create.nextLevelExperience = this.nextLevelExperience;
        create.moneySpentOn = this.moneySpentOn;
        return create;
    }

    public final boolean isOutOfOrder() {
        return this.outOfOrder.isTrue();
    }

    @Null
    public Enemy getTarget() {
        return this.p.enemy;
    }

    public void setTarget(Enemy enemy) {
        this.p = this.S.enemy.getReference(enemy);
        if (this.p == null) {
            throw new IllegalArgumentException("Reference is null");
        }
    }

    public boolean canNewAbilityBeInstalled() {
        for (TowerSystem.TowerAbilityCategoryRule towerAbilityCategoryRule : this.S.tower.towerAbilityCategoryRules) {
            if (!towerAbilityCategoryRule.autoInstallSingleVariant) {
                int i = 0;
                for (int i2 = 0; i2 < towerAbilityCategoryRule.requiredXpLevels.size; i2++) {
                    if (this.level >= towerAbilityCategoryRule.requiredXpLevels.get(i2)) {
                        i++;
                    }
                }
                int i3 = 0;
                for (int i4 = 0; i4 < 6; i4++) {
                    if (this.installedAbilities[i4] && this.S.tower.towerAbilityIdxToCategory[i4] == towerAbilityCategoryRule.categoryId) {
                        i3++;
                    }
                }
                if (i > i3) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean canAbilityBeInstalled(int i) {
        int i2 = this.S.tower.towerAbilityIdxToCategory[i];
        TowerSystem.TowerAbilityCategoryRule towerAbilityCategoryRule = this.S.tower.towerAbilityCategoryRules[i2];
        if (this.installedAbilities[i] || towerAbilityCategoryRule.autoInstallSingleVariant) {
            return false;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < 6; i4++) {
            if (this.installedAbilities[i4] && this.S.tower.towerAbilityIdxToCategory[i4] == i2) {
                i3++;
            }
        }
        if (i3 >= towerAbilityCategoryRule.requiredXpLevels.size) {
            return false;
        }
        int i5 = 0;
        for (int i6 = 0; i6 < towerAbilityCategoryRule.requiredXpLevels.size; i6++) {
            if (this.level >= towerAbilityCategoryRule.requiredXpLevels.get(i6)) {
                i5++;
            }
        }
        return i5 > i3;
    }

    public final boolean isAbilityInstalled(int i) {
        if (this.installedAbilities[i]) {
            return true;
        }
        if (this.S == null) {
            return false;
        }
        TowerSystem.TowerAbilityCategoryRule towerAbilityCategoryRule = this.S.tower.towerAbilityCategoryRules[this.S.tower.towerAbilityIdxToCategory[i]];
        return towerAbilityCategoryRule.autoInstallSingleVariant && this.level >= towerAbilityCategoryRule.requiredXpLevels.items[0];
    }

    public static int getLevelForExperience(float f2) {
        int i = 1;
        for (int i2 = 1; i2 <= 999 && f2 >= LEVEL_EXPERIENCE_MILESTONES[i2]; i2++) {
            i = i2;
        }
        return i;
    }

    public static int getLevelForExperienceFast(float f2) {
        int[] iArr = LEVEL_EXPERIENCE_MILESTONES;
        if (f2 >= iArr[iArr.length - 1]) {
            return LEVEL_EXPERIENCE_MILESTONES.length - 1;
        }
        if (f2 <= LEVEL_EXPERIENCE_MILESTONES[0]) {
            return 1;
        }
        int i = (int) f2;
        int i2 = 0;
        int i3 = 998;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = LEVEL_EXPERIENCE_MILESTONES[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 > i) {
                i3 = i4 - 1;
            } else {
                return i4;
            }
        }
        return i2 - 1;
    }

    public void addExperience(float f2) {
        setExperience(this.experience + f2);
    }

    public static float getLevelExperienceMilestone(int i) {
        return LEVEL_EXPERIENCE_MILESTONES[i];
    }

    public final void setExperience(float f2) {
        this.experience = f2;
    }

    public short getMaxTowerLevel() {
        return (short) Math.max(1, Math.min(this.S.gameValue.getIntValueSum(GameValueType.TOWERS_MAX_EXP_LEVEL, Game.i.towerManager.getMaxExpLevelGameValueType(this.type)) + this.i, MAX_LEVEL));
    }

    public void calculateXpLevel(boolean z) {
        short maxTowerLevel;
        short level = getLevel();
        if (z) {
            maxTowerLevel = 999;
            short s = 1;
            while (true) {
                short s2 = s;
                if (s2 > 999 || ((int) this.experience) < LEVEL_EXPERIENCE_MILESTONES[s2]) {
                    break;
                }
                this.level = s2;
                s = (short) (s2 + 1);
            }
        } else {
            maxTowerLevel = getMaxTowerLevel();
            if (getLevel() < maxTowerLevel) {
                short level2 = getLevel();
                while (true) {
                    short s3 = (short) (level2 + 1);
                    if (s3 > maxTowerLevel || ((int) this.experience) < LEVEL_EXPERIENCE_MILESTONES[s3]) {
                        break;
                    }
                    this.level = s3;
                    level2 = s3;
                }
            } else if (getLevel() > maxTowerLevel) {
                setLevel(maxTowerLevel);
            }
        }
        if (getLevel() != maxTowerLevel) {
            this.nextLevelExperience = LEVEL_EXPERIENCE[getLevel() + 1];
        } else {
            this.nextLevelExperience = 0.0f;
        }
        this.currentLevelExperience = this.experience - LEVEL_EXPERIENCE_MILESTONES[getLevel()];
        if (level > getLevel() && this.S != null && this.S._mapRendering != null && this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED) {
            this.S._mapRendering.forceBuildingsRedraw();
        }
    }

    public byte getMaxUpgradeLevel() {
        return (byte) Math.min(this.S.gameValue.getIntValueSum(GameValueType.TOWERS_MAX_UPGRADE_LEVEL, Game.i.towerManager.getMaxUpgradeLevelGameValueType(this.type)), 10);
    }

    public short getLevel() {
        return this.level;
    }

    public void setLevel(short s) {
        this.level = s;
    }

    public float getPowerCombinedMultiplier() {
        return this.n;
    }

    private void a() {
        Enemy target = getTarget();
        if (target != null) {
            if (!PMath.circleIntersectsCircleV(getTile().center, this.rangeInPixels, target.getPosition(), target.getSize())) {
                setTarget(null);
            } else if (this.minRangeInPixels != 0.0f && getTile().center.dst2(target.getPosition()) < this.minRangeInPixels * this.minRangeInPixels) {
                setTarget(null);
            }
        }
    }

    private void b() {
        if (getTarget() == null) {
            this.l = (byte) (this.l + 1);
            if (this.l == 6) {
                this.l = (byte) 0;
                setTarget(findTarget());
            }
        }
    }

    private void c() {
        if (this.f1776a != 0) {
            this.j = (byte) (this.j + 1);
            if (this.j >= 7 && this.f1777b) {
                Enemy findTarget = findTarget();
                if (getTarget() != findTarget) {
                    setTarget(findTarget);
                }
                this.j = (byte) 0;
                this.f1777b = false;
            }
        }
    }

    private void d() {
        if (getTarget() == null || !canAttack()) {
            this.c = StrictMath.min(this.c, getAttackDelay());
        }
    }

    private void e() {
        if (getTarget() != null && canAttack()) {
            float attackDelay = getAttackDelay();
            int i = (int) (this.c / attackDelay);
            if (i > 0) {
                attack(i);
                this.f1777b = true;
                this.c -= attackDelay * i;
                if (this.c < 0.0f) {
                    this.c = 0.0f;
                }
            }
        }
    }

    public void update(float f2) {
        this.h += f2;
        this.outOfOrder.update(f2);
        if (!isOutOfOrder() && shouldSearchForTarget()) {
            this.c += f2;
            c();
            d();
            a();
            b();
            e();
        }
    }

    public int getSellPrice() {
        if (isSellFullRefundStillActive()) {
            return this.moneySpentOn;
        }
        float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWERS_SELL_REFUND);
        if (getTile() != null && getTile().bonusType == SpaceTileBonusType.SELL_REFUND && getTile().bonusLevel > 0) {
            float effect = SpaceTileBonus.getEffect(SpaceTileBonusType.SELL_REFUND, getTile().bonusLevel);
            if (effect > percentValueAsMultiplier) {
                percentValueAsMultiplier = effect;
            }
        }
        if (percentValueAsMultiplier > 1.0f) {
            percentValueAsMultiplier = 1.0f;
        }
        return (int) (this.moneySpentOn * percentValueAsMultiplier);
    }

    public boolean isSellFullRefundStillActive() {
        return this.h < 15.0f && this.k == 0 && this.damageGiven == 0.0f && this.shotCount == 0;
    }

    public Enemy findTarget() {
        return findTargetFiltered(null);
    }

    public Enemy findTargetFiltered(@Null ObjectFilter<Enemy> objectFilter) {
        Enemy enemy = null;
        if (objectFilter == null) {
            objectFilter = enemy2 -> {
                return true;
            };
        }
        Array<Enemy> enemyArray = this.S.TSH.getEnemyArray();
        int[] iArr = {-1};
        float f2 = getTile().center.x;
        float f3 = getTile().center.y;
        float minRange = getMinRange();
        ObjectFilter<Enemy> objectFilter2 = objectFilter;
        this.S.map.getEnemiesInCircle(f2, f3, this.rangeInPixels, (enemyReference, f4, f5, f6) -> {
            Enemy enemy3 = enemyReference.enemy;
            if (enemy3 == null || !canAttackEnemy(enemy3) || !objectFilter2.test(enemy3)) {
                return true;
            }
            if (this.minRangeInPixels == 0.0f || PMath.getSquareDistanceBetweenPoints(f2, f3, f4, f5) > minRange * minRange * 128.0f * 128.0f) {
                int enemyPriority = getEnemyPriority(enemy3);
                if (enemyPriority > iArr[0]) {
                    iArr[0] = enemyPriority;
                    enemyArray.clear();
                    enemyArray.add(enemy3);
                    return true;
                }
                if (enemyPriority == iArr[0]) {
                    enemyArray.add(enemy3);
                    return true;
                }
                return true;
            }
            return true;
        });
        if (this.aimStrategy == AimStrategy.RANDOM) {
            if (enemyArray.size != 0) {
                enemy = enemyArray.get(this.S.gameState.randomInt(enemyArray.size));
            }
        } else {
            AimStrategyEnemyComparator aimStrategyEnemyComparator = g[this.aimStrategy.ordinal()];
            for (int i = 0; i < enemyArray.size; i++) {
                Enemy enemy3 = enemyArray.items[i];
                if (enemy == null || aimStrategyEnemyComparator.compare(this, enemy3, enemy)) {
                    enemy = enemy3;
                }
            }
        }
        this.S.TSH.freeEnemyArray(enemyArray);
        return enemy;
    }

    public byte getUpgradeLevel() {
        return this.k;
    }

    public void upgradeToLevel(byte b2) {
        if (b2 > getMaxUpgradeLevel()) {
            b2 = getMaxUpgradeLevel();
        }
        this.k = b2;
        this.S.map.markTilesDirtyNearTile(getTile(), 1);
    }

    public void setUpgradeLevel(byte b2) {
        if (b2 < 0) {
            b2 = 0;
        }
        if (b2 > 10) {
            b2 = 10;
        }
        this.k = b2;
    }

    public void upgrade() {
        upgradeToLevel((byte) (this.k + 1));
    }

    @Override // com.prineside.tdi2.Building
    public void updateCache() {
        SpaceTileBonusType spaceTileBonusType;
        this.f1776a = (byte) 0;
        int[] iArr = {0};
        int[] iArr2 = {0};
        this.bountyModifiersNearby = (byte) 0;
        int[] iArr3 = {0};
        int[] iArr4 = {0};
        int[] iArr5 = {0};
        int[] iArr6 = {0};
        this.i = (byte) 0;
        this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
            if (tile instanceof PlatformTile) {
                PlatformTile platformTile = (PlatformTile) tile;
                if (platformTile.building instanceof Modifier) {
                    Modifier modifier = (Modifier) platformTile.building;
                    switch (modifier.type) {
                        case BALANCE:
                            BalanceModifier balanceModifier = (BalanceModifier) modifier;
                            if (this.i + balanceModifier.getLevel() < 127) {
                                this.i = (byte) (this.i + balanceModifier.getLevel());
                                return true;
                            }
                            this.i = Byte.MAX_VALUE;
                            return true;
                        case SEARCH:
                            this.f1776a = (byte) (this.f1776a + 1);
                            return true;
                        case POWER:
                            iArr[0] = iArr[0] + 1;
                            return true;
                        case MINING_SPEED:
                            iArr2[0] = iArr2[0] + 1;
                            return true;
                        case BOUNTY:
                            this.bountyModifiersNearby = (byte) (this.bountyModifiersNearby + 1);
                            return true;
                        case DAMAGE:
                            if (tile.getY() == getTile().getY() || tile.getX() == getTile().getX()) {
                                iArr3[0] = iArr3[0] + 1;
                                return true;
                            }
                            iArr4[0] = iArr4[0] + 1;
                            return true;
                        case ATTACK_SPEED:
                            if (tile.getY() == getTile().getY() || tile.getX() == getTile().getX()) {
                                iArr5[0] = iArr5[0] + 1;
                                return true;
                            }
                            iArr6[0] = iArr6[0] + 1;
                            return true;
                        default:
                            return true;
                    }
                }
                return true;
            }
            return true;
        });
        float startingPwr = (getStartingPwr(this.type, this.S.gameValue) * 0.01f) + 1.0f + (((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.MODIFIER_POWER_VALUE)) * iArr[0]) + getExpLevelStatBonusPercentage(getLevel(), this.type, this.S.gameValue);
        if (getTile() != null && getTile().bonusType == SpaceTileBonusType.PWR_MULTIPLIER && getTile().bonusLevel != 0) {
            startingPwr *= SpaceTileBonus.getEffect(SpaceTileBonusType.PWR_MULTIPLIER, getTile().bonusLevel);
        }
        if (isAbilityInstalled(5)) {
            startingPwr = (float) (startingPwr * (this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWERS_POWERFUL_ABILITY_PWR) + this.S.gameValue.getPercentValueAsMultiplier(Game.i.towerManager.getPowerfulAbilityGameValueType(this.type))));
        }
        if (this.powerBonuses != null) {
            startingPwr += this.powerBonuses.getBonusesSum();
        }
        this.n = startingPwr;
        for (TowerStatType towerStatType : Game.i.towerManager.getStatTypes(this.type)) {
            float calculateStat = calculateStat(towerStatType);
            if (getTile() != null && (spaceTileBonusType = SpaceTileBonus.f1762a[towerStatType.ordinal()]) != null && spaceTileBonusType == getTile().bonusType && getTile().bonusLevel > 0) {
                calculateStat *= SpaceTileBonus.getEffect(spaceTileBonusType, getTile().bonusLevel);
            }
            if (isStatAffectedByPower(towerStatType)) {
                TowerManager.TowerStatConfig statConfig = Game.i.towerManager.getStatConfig(this.type, towerStatType);
                calculateStat *= (((float) StrictMath.pow((this.n - 1.0f) * 100.0f * statConfig.pwrFactor, statConfig.pwrPowerFactor)) * 0.01f) + 1.0f;
            }
            switch (towerStatType) {
                case DAMAGE:
                    float percentValueAsMultiplier = (float) (calculateStat + (calculateStat * iArr3[0] * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.MODIFIER_DAMAGE_VALUE)));
                    calculateStat = percentValueAsMultiplier - (percentValueAsMultiplier * (iArr6[0] * 0.05f));
                    break;
                case ATTACK_SPEED:
                    float percentValueAsMultiplier2 = (float) (calculateStat + (calculateStat * iArr5[0] * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.MODIFIER_ATTACK_SPEED_VALUE)));
                    calculateStat = percentValueAsMultiplier2 - (percentValueAsMultiplier2 * (iArr4[0] * 0.05f));
                    break;
                case RANGE:
                    calculateStat = (float) (calculateStat + (calculateStat * this.f1776a * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.MODIFIER_SEARCH_RANGE_VALUE)));
                    break;
                case ROTATION_SPEED:
                case PROJECTILE_SPEED:
                    float f2 = calculateStat;
                    calculateStat = f2 - (f2 * (iArr2[0] * 0.05f));
                    break;
            }
            this.m[towerStatType.ordinal()] = Game.i.towerManager.clampStat(this.type, towerStatType, calculateStat);
        }
        float stat = getStat(TowerStatType.ATTACK_SPEED);
        if (stat == 0.0f) {
            this.o = 0.0f;
        } else {
            this.o = 1.0f / stat;
        }
        this.rangeInPixels = getRange() * 128.0f;
        this.minRangeInPixels = getMinRange() * 128.0f;
        this.experienceGeneration = this.S.gameValue.getFloatValueSum(GameValueType.TOWERS_EXPERIENCE_GENERATION, Game.i.towerManager.getExperienceGenerationGameValueType(this.type));
        this.experienceGeneration *= this.k / 10.0f;
        this.experienceMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplierSum(GameValueType.TOWERS_EXPERIENCE_MULTIPLIER, Game.i.towerManager.getExperienceMultiplierGameValueType(this.type));
        if (getTile() != null && getTile().bonusType == SpaceTileBonusType.BONUS_EXPERIENCE && getTile().bonusLevel > 0) {
            this.experienceMultiplier *= SpaceTileBonus.getEffect(getTile().bonusType, getTile().bonusLevel);
        }
    }

    public static float getExpLevelStatBonusPercentage(int i, TowerType towerType, GameValueProvider gameValueProvider) {
        double percentValueAsMultiplier = gameValueProvider.getPercentValueAsMultiplier(GameValueType.TOWERS_POWER_PER_LEVEL_TILL_10) + gameValueProvider.getPercentValueAsMultiplier(Game.i.towerManager.getPplTill10GameValueType(towerType));
        if (i <= 10) {
            return (float) (percentValueAsMultiplier * (i - 1));
        }
        return (float) ((percentValueAsMultiplier * 9.0d) + ((i - 10) * (gameValueProvider.getPercentValueAsMultiplier(GameValueType.TOWERS_POWER_PER_LEVEL_AFTER_10) + gameValueProvider.getPercentValueAsMultiplier(Game.i.towerManager.getPplAfter10GameValueType(towerType)))));
    }

    public boolean isStatAffectedByPower(TowerStatType towerStatType) {
        return (Game.i.towerManager.getStatConfig(this.type, towerStatType).unique || towerStatType == TowerStatType.RANGE || towerStatType == TowerStatType.MIN_RANGE) ? false : true;
    }

    public final float getStat(TowerStatType towerStatType) {
        return this.m[towerStatType.ordinal()];
    }

    public void dispose() {
    }

    public void applyDrawInterpolation(float f2) {
    }

    public void drawBatch(Batch batch, float f2) {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_TOWER_XP) != 0.0d && getTile().visibleOnScreen) {
            ResourcePack.ResourcePackBitmapFont font = Game.i.assetManager.getFont(21);
            font.draw(batch, "xp: " + (((int) (this.experience * 10.0f)) / 10.0f), getTile().center.x - 32.0f, (getTile().center.y - 64.0f) + 40.0f);
            font.draw(batch, "clxp: " + (((int) (this.currentLevelExperience * 10.0f)) / 10.0f), getTile().center.x - 32.0f, (getTile().center.y - 64.0f) + 20.0f);
        }
        if (this.S != null && this.S._mapRendering != null && this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED && this.affectedByLoopAbility != null) {
            float x = (getTile().getX() << 7) + 7.0f;
            float y = (getTile().getY() << 7) + 85.0f;
            batch.setColor(Config.BACKGROUND_COLOR);
            batch.draw(AssetManager.TextureRegions.i().roundedSmallRect, x, y, 36.0f, 36.0f);
            batch.setColor(MaterialColor.GREEN.P900);
            batch.draw(AssetManager.TextureRegions.i().iconLoop, x, y, 36.0f, 36.0f);
            batch.setColor(Color.WHITE);
            ResourcePack.ResourcePackBitmapFont font2 = Game.i.assetManager.getFont(18);
            StringBuilder compactNumber = StringFormatter.compactNumber(this.loopAbilityDamageBuffer, false);
            font2.setColor(Config.BACKGROUND_COLOR);
            font2.draw(batch, compactNumber, x, y + 24.0f + 1.5f, 36.0f, 1, false);
            font2.draw(batch, compactNumber, x, (y + 24.0f) - 1.5f, 36.0f, 1, false);
            font2.draw(batch, compactNumber, x - 1.5f, y + 24.0f, 36.0f, 1, false);
            font2.draw(batch, compactNumber, x + 1.5f, y + 24.0f, 36.0f, 1, false);
            font2.setColor(MaterialColor.GREEN.P200);
            font2.draw(batch, compactNumber, x, y + 24.0f, 36.0f, 1, false);
            font2.setColor(Color.WHITE);
        }
    }

    public void drawBatchAdditive(Batch batch, float f2) {
    }

    private void a(Batch batch, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        Factory<? extends Tower> factory = Game.i.towerManager.getFactory(this.type);
        batch.setColor(SHADOW_COLOR);
        factory.getShadowTextures().draw(batch, f2, f3, f4, f5);
        if (drawMode == MapRenderingSystem.DrawMode.DEFAULT) {
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
            factory.getBaseTextures().draw(batch, f2, f3, f4, f5);
            if (factory.f1783a != null) {
                factory.f1783a.draw(batch, f2, f3, f4, f5);
                return;
            }
            return;
        }
        if (drawMode == MapRenderingSystem.DrawMode.DETAILED || drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR) {
            batch.setColor(f);
            factory.getBaseTextures().draw(batch, f2, f3, f4, f5);
            batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        }
    }

    private void b(Batch batch, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        Factory<? extends Tower> factory = Game.i.towerManager.getFactory(this.type);
        if (drawMode == MapRenderingSystem.DrawMode.DETAILED || drawMode == MapRenderingSystem.DrawMode.MAP_EDITOR) {
            float f6 = f4 * 0.0078125f;
            float f7 = f5 * 0.0078125f;
            if (getTile().bonusLevel != 0) {
                batch.setColor(Config.BACKGROUND_COLOR);
                batch.draw(factory.roundedSmallRectTextureRegion, f2 + (7.0f * f6), f3 + (7.0f * f7), 36.0f * f6, 36.0f * f7);
                batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
                getTile().drawBonusExtras(batch, f2 + (3.0f * f6), f3 + (3.0f * f7), 44.0f * f6, 44.0f * f7, false, true);
            }
            if (canAim()) {
                batch.setColor(Game.i.towerManager.getAimStrategyColor(this.aimStrategy));
                batch.draw(factory.roundedSmallRectTextureRegion, f2 + (85.0f * f6), f3 + (85.0f * f7), 36.0f * f6, 36.0f * f7);
                batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
                batch.draw(Game.i.towerManager.getAimStrategyIcon(this.aimStrategy), f2 + (85.0f * f6), f3 + (85.0f * f7), 36.0f * f6, 36.0f * f7);
            }
            ResourcePack.ResourcePackBitmapFont font = Game.i.assetManager.getFont((int) (36.0f * f6));
            d.setLength(0);
            d.append((int) this.k);
            font.setColor(0.0f, 0.0f, 0.0f, 0.56f);
            font.draw(batch, d, f2 + (3.0f * f6), f3 + (71.0f * f7), f4, 1, false);
            font.setColor(Color.WHITE);
            font.draw(batch, d, f2, f3 + 74.0f, 128.0f, 1, false);
            ResourcePack.ResourcePackBitmapFont font2 = Game.i.assetManager.getFont((int) (24.0f * f6));
            d.setLength(0);
            d.append("L").append((int) this.level);
            font2.setColor(0.0f, 0.0f, 0.0f, 0.56f);
            font2.draw(batch, d, f2 + (f6 * 2.0f), f3 + (32.0f * f7), f4, 1, false);
            font2.setColor(Color.WHITE);
            font2.draw(batch, d, f2, f3 + (34.0f * f7), f4, 1, false);
            font2.setColor(Color.WHITE);
        }
    }

    @Override // com.prineside.tdi2.Building
    public final void drawBase(Batch batch, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        a(batch, f2, f3, f4, f5, drawMode);
        drawAbilitiesToCache(batch, f2, f3, f4, f5, drawMode);
        b(batch, f2, f3, f4, f5, drawMode);
    }

    public void drawAbilitiesToCache(Batch batch, float f2, float f3, float f4, float f5, MapRenderingSystem.DrawMode drawMode) {
        Factory<? extends Tower> factory = Game.i.towerManager.getFactory(this.type);
        for (int i = 0; i < 6; i++) {
            if (isAbilityInstalled(i) && factory.shouldDrawAbilityToCache(i)) {
                factory.getAbilityTextures(i).draw(batch, f2, f3, f4, f5);
            }
        }
    }

    public void drawWeapon(Batch batch, float f2, float f3, float f4, float f5) {
        if (getTile().visibleOnScreen && !isOutOfOrder()) {
            getWeaponTextures().draw(batch, f2, f3, f4, f4, 1.0f, 1.0f, this.angle);
        }
    }

    public void drawGlitch(Batch batch) {
        Factory<? extends Tower> factory = Game.i.towerManager.getFactory(this.type);
        batch.setColor(1.0f, 0.0f, 0.0f, 0.8f);
        batch.draw(((Factory) factory).f, (((getTile().getX() << 7) - 6.4f) + (FastRandom.getFloat() * 12.8f)) - 6.4f, (((getTile().getY() << 7) - 6.4f) + (FastRandom.getFloat() * 12.8f)) - 6.4f, 140.8f, 140.8f);
        batch.setColor(0.0f, 1.0f, 1.0f, 0.8f);
        batch.draw(((Factory) factory).f, (((getTile().getX() << 7) - 6.4f) + (FastRandom.getFloat() * 12.8f)) - 6.4f, (((getTile().getY() << 7) - 6.4f) + (FastRandom.getFloat() * 12.8f)) - 6.4f, 140.8f, 140.8f);
        batch.setColor(1.0f, 1.0f, 0.0f, 0.8f);
        batch.draw(((Factory) factory).f, (((getTile().getX() << 7) - 6.4f) + (FastRandom.getFloat() * 12.8f)) - 6.4f, (((getTile().getY() << 7) - 6.4f) + (FastRandom.getFloat() * 12.8f)) - 6.4f, 140.8f, 140.8f);
        batch.setColor(Color.WHITE);
        batch.draw(((Factory) factory).f, (getTile().getX() << 7) - 6.4f, (getTile().getY() << 7) - 6.4f, 140.8f, 140.8f);
    }

    @Override // com.prineside.tdi2.Building
    public void drawSelectedRange(Batch batch, RangeCircle rangeCircle) {
        float f2 = getTile().center.x;
        float f3 = getTile().center.y;
        float minRange = getMinRange() * 128.0f;
        float range = getRange() * 128.0f;
        if (rangeCircle.getX() != f2 || rangeCircle.getY() != f3 || rangeCircle.getMinRadius() != minRange || rangeCircle.getMaxRadius() != range) {
            rangeCircle.setup(f2, f3, minRange, range, MapSystem.TOWER_RANGE_SELECTED_COLOR);
        }
        rangeCircle.draw(batch);
    }

    @Override // com.prineside.tdi2.Building
    public void drawHoveredRange(Batch batch, RangeCircle rangeCircle) {
        float f2 = getTile().center.x;
        float f3 = getTile().center.y;
        float minRange = getMinRange() * 128.0f;
        float range = getRange() * 128.0f;
        if (rangeCircle.getX() != f2 || rangeCircle.getY() != f3 || rangeCircle.getMinRadius() != minRange || rangeCircle.getMaxRadius() != range) {
            rangeCircle.setup(f2, f3, minRange, range, MapSystem.TOWER_RANGE_HOVER_COLOR);
        }
        rangeCircle.draw(batch);
    }

    @Override // com.prineside.tdi2.Building
    public void placedOnMap() {
    }

    @Override // com.prineside.tdi2.Building
    public void removedFromMap() {
    }

    public int getEnemyPriority(Enemy enemy) {
        return enemy.lowAimPriority.isTrue() ? 0 : 10;
    }

    public static int getStartingLevel(TowerType towerType, GameValueProvider gameValueProvider) {
        return StrictMath.min(gameValueProvider.getIntValueSum(GameValueType.TOWERS_STARTING_LEVEL, Game.i.towerManager.getStartingLevelGameValueType(towerType)), MAX_LEVEL);
    }

    public static float getStartingPwr(TowerType towerType, GameValueProvider gameValueProvider) {
        return gameValueProvider.getIntValueSum(GameValueType.TOWERS_STARTING_PWR, Game.i.towerManager.getStartingPwrGameValueType(towerType));
    }

    public float getRange() {
        return getStat(TowerStatType.RANGE);
    }

    public float getMinRange() {
        return getStat(TowerStatType.MIN_RANGE);
    }

    public String getUniqueStatDescription() {
        return Game.i.towerManager.getUniqueStatDescription(this.type);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float calculateStat(TowerStatType towerStatType) {
        return Game.i.towerManager.getStatFromConfig(this.type, towerStatType, getUpgradeLevel(), getLevel(), this.S.gameValue) * this.S.gameValue.getGlobalStatMultiplier(towerStatType);
    }

    public boolean hasCustomButton() {
        return false;
    }

    public boolean isCustomButtonNeedMapPoint() {
        return false;
    }

    public void customButtonAction(int i, int i2) {
    }

    public void updateCustomButton(ComplexButton complexButton, boolean z) {
    }

    protected void a(StringBuilder stringBuilder) {
    }

    public void fillTowerMenu(Group group, ObjectMap<String, Object> objectMap) {
        Label label;
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_BUILDING_INFO) != 0.0d) {
            if (objectMap.containsKey("_dbgBuildingInfo")) {
                Label label2 = (Label) objectMap.get("_dbgBuildingInfo");
                label = label2;
                if (label2.getParent() != group) {
                    group.addActor(label);
                }
            } else {
                label = new Label("", Game.i.assetManager.getLabelStyle(21));
                group.addActor(label);
                objectMap.put("_dbgBuildingInfo", label);
            }
            d.setLength(0);
            d.append("target: ").append(String.valueOf(getTarget() == null ? "-" : Integer.valueOf(getTarget().id))).append(SequenceUtils.EOL);
            d.append("targetSearchFrames: ").append((int) this.l).append(SequenceUtils.EOL);
            d.append("shotCount: ").append(this.shotCount).append(SequenceUtils.EOL);
            d.append("timeSinceLastAttack: ").append(this.c).append(SequenceUtils.EOL);
            d.append("framesSinceConstantEnemySeeking: ").append((int) this.j).append(SequenceUtils.EOL);
            d.append("attackDelay: ").append(getAttackDelay()).append(SequenceUtils.EOL);
            d.append("powerCombinedMultiplier: ").append(getPowerCombinedMultiplier()).append(SequenceUtils.EOL);
            for (TowerStatType towerStatType : TowerStatType.values) {
                float stat = getStat(towerStatType);
                if (stat != 0.0f) {
                    d.append("stat|").append(String.valueOf(towerStatType)).append(": ").append(stat).append(SequenceUtils.EOL);
                }
            }
            a(d);
            label.setText(d);
            label.setPosition(-200.0f, 128.0f);
            label.setSize(180.0f, 200.0f);
            label.setAlignment(16);
        }
    }

    public boolean shouldSearchForTarget() {
        return canAim();
    }

    public boolean canAttackEnemy(Enemy enemy) {
        return this.S.tower.canTowerAttackEnemy[enemy.type.ordinal()][this.type.ordinal()] && enemy.canBeAttackedBy(this);
    }

    public boolean canAttack() {
        Enemy target = getTarget();
        if (target == null || this.attackDisabled) {
            return false;
        }
        return StrictMath.abs(PMath.getDistanceBetweenAngles(this.angle, PMath.getAngleBetweenPoints(getTile().center, target.getPosition()))) < 3.0f;
    }

    public void rotateAtPoint(float f2, float f3, float f4, float f5) {
        rotateToAngle(PMath.getAngleBetweenPoints(getTile().center.x, getTile().center.y, f2, f3), f4, f5);
    }

    public void rotateToAngle(float f2, float f3, float f4) {
        if (f2 == this.angle) {
            return;
        }
        float distanceBetweenAngles = PMath.getDistanceBetweenAngles(this.angle, f2);
        float f5 = f3 * f4;
        if (f5 >= StrictMath.abs(distanceBetweenAngles)) {
            this.angle = f2;
        } else if (distanceBetweenAngles < 0.0f) {
            this.angle -= f5;
        } else {
            this.angle += f5;
        }
    }

    public void onPreSell() {
    }

    public void onAbilitySet(int i, boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(float f2, float f3) {
        Enemy target = getTarget();
        if (target != null && !isOutOfOrder()) {
            rotateAtPoint(target.getPosition().x, target.getPosition().y, f2, f3);
        }
    }

    public float getAttackDelay() {
        return this.o;
    }

    public void attack(int i) {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Tower$AbilityConfig.class */
    public static class AbilityConfig {

        /* renamed from: a, reason: collision with root package name */
        private final String f1780a;

        /* renamed from: b, reason: collision with root package name */
        private final String f1781b;
        public Object[] descriptionArgs = new Object[0];

        public AbilityConfig(String str, String str2) {
            this.f1780a = str;
            this.f1781b = str2;
        }

        public String getName() {
            return Game.i.localeManager.i18n.get(this.f1780a);
        }

        public CharSequence getDescription() {
            return Game.i.localeManager.i18n.format(this.f1781b, this.descriptionArgs);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Tower$Factory.class */
    public static abstract class Factory<T extends Tower> implements EntityFactory {
        private String c;
        private TowerType d;
        private TextureRegion e;
        private TextureRegion f;
        private Quad g;
        private Quad h;

        @Null
        private Quad i;

        /* renamed from: a, reason: collision with root package name */
        @Null
        protected Quad f1783a;
        private Quad[] j;
        public TextureRegion roundedSmallRectTextureRegion;

        /* renamed from: b, reason: collision with root package name */
        protected final AbilityConfig[] f1784b = new AbilityConfig[6];

        public abstract int getBuildHotKey();

        public abstract String[] getAbilityAliases();

        public abstract T create();

        public abstract Color getColor();

        public abstract int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType);

        /* JADX INFO: Access modifiers changed from: protected */
        public Factory(String str, TowerType towerType) {
            this.c = str;
            this.d = towerType;
        }

        public boolean isAvailable(GameValueProvider gameValueProvider) {
            return gameValueProvider.getBooleanValue(Game.i.towerManager.getTowerGameValueType(this.d));
        }

        public final Quad getAbilityTextures(int i) {
            return this.j[i];
        }

        public final Quad getShadowTextures() {
            return this.h;
        }

        public final Quad getBaseTextures() {
            return this.g;
        }

        @Null
        public Quad getWeaponTexture() {
            return this.i;
        }

        @Null
        public Quad getWeaponShadowTexture() {
            return this.f1783a;
        }

        public void configureSystems(GameSystemProvider gameSystemProvider) {
        }

        public void setup() {
            String[] abilityAliases = getAbilityAliases();
            if (Game.i.assetManager != null) {
                this.roundedSmallRectTextureRegion = Game.i.assetManager.getTextureRegion("rounded-small-rect");
                this.e = Game.i.assetManager.getTextureRegion(this.c);
                this.f = Game.i.assetManager.getTextureRegion(this.c + "-shape");
                this.g = Game.i.assetManager.getQuad("towers." + this.d.name() + ".base");
                this.h = Game.i.assetManager.getQuad("towers." + this.d.name() + ".baseShadow");
                this.j = new Quad[6];
                for (int i = 0; i < 3; i++) {
                    this.j[i] = Game.i.assetManager.getQuad("towers." + this.d.name() + ".abilities." + abilityAliases[i]);
                }
                this.j[3] = Game.i.assetManager.getQuad("towers." + this.d.name() + ".abilities.SPECIAL");
                this.j[4] = Game.i.assetManager.getQuad("towers." + this.d.name() + ".abilities.ULTIMATE");
                this.j[5] = Game.i.assetManager.getQuad("towers." + this.d.name() + ".abilities.POWERFUL");
                this.i = Game.i.assetManager.getQuadOrNull("towers." + this.d.name() + ".weapon");
                this.f1783a = Game.i.assetManager.getQuadOrNull("towers." + this.d.name() + ".weaponShadow");
                setupAssets();
            }
            for (int i2 = 0; i2 < abilityAliases.length; i2++) {
                this.f1784b[i2] = new AbilityConfig("tower_ability_" + this.d.name() + JavaConstant.Dynamic.DEFAULT_NAME + abilityAliases[i2] + "_name", "tower_ability_" + this.d.name() + JavaConstant.Dynamic.DEFAULT_NAME + abilityAliases[i2] + "_description");
            }
            this.f1784b[3] = new AbilityConfig("tower_ability_" + this.d.name() + "_SPECIAL_name", "tower_ability_" + this.d.name() + "_SPECIAL_description");
            this.f1784b[4] = new AbilityConfig("tower_ability_" + this.d.name() + "_ULTIMATE_name", "tower_ability_" + this.d.name() + "_ULTIMATE_description");
            this.f1784b[5] = new AbilityConfig("tower_ability_" + this.d.name() + "_POWERFUL_name", "tower_ability_" + this.d.name() + "_POWERFUL_description");
            this.f1784b[5].descriptionArgs = new String[1];
        }

        public boolean shouldDrawAbilityToCache(int i) {
            return true;
        }

        public AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            this.f1784b[5].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros((float) (gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWERS_POWERFUL_ABILITY_PWR) + gameSystemProvider.gameValue.getPercentValueAsMultiplier(Game.i.towerManager.getPowerfulAbilityGameValueType(tower.type))), 2, true).toString();
            return this.f1784b;
        }

        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            return null;
        }

        public void setupAssets() {
        }

        public String getTitle() {
            return Game.i.towerManager.getTitle(this.d);
        }

        public String getDescription() {
            return Game.i.towerManager.getDescription(this.d);
        }

        public int getBuildPrice(GameSystemProvider gameSystemProvider) {
            return gameSystemProvider.gameValue.getIntValue(Game.i.towerManager.getPriceGameValueType(this.d));
        }

        public boolean canKillEnemies() {
            return true;
        }

        public boolean receivesSpaceTileBonus(SpaceTileBonusType spaceTileBonusType) {
            if (spaceTileBonusType == null) {
                return false;
            }
            TowerStatType towerStatType = SpaceTileBonus.f1763b[spaceTileBonusType.ordinal()];
            if (towerStatType != null && Game.i.towerManager.hasStat(this.d, towerStatType)) {
                return true;
            }
            if (towerStatType == null) {
                if (spaceTileBonusType == SpaceTileBonusType.BONUS_COINS && !canKillEnemies()) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public Actor createIconActor(float f) {
            Image image = new Image(Game.i.assetManager.getDrawable(this.c));
            image.setSize(f, f);
            return image;
        }

        public Drawable getIconDrawable() {
            return Game.i.assetManager.getDrawable(this.c);
        }

        public TextureRegion getIconTexture() {
            return this.e;
        }
    }
}
