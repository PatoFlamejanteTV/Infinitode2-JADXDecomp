package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.FreezingBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.shapes.Circle;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.units.SnowballUnit;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/FreezingTower.class */
public final class FreezingTower extends Tower {
    public static final float SPECIAL_ABILITY_RANGE = 2.5f;
    public static final float MONITORED_TARGET_FREEZING_BONUS = 15.0f;
    private static final Color e;
    private static final Color f;
    public static final int ABILITY_COLD_EVAPORATION = 0;
    public static final int ABILITY_SLOW_FREEZING = 1;
    public static final int ABILITY_MONITORING_SYSTEM = 2;
    public static final String[] ABILITY_ALIASES;
    public static final float GAIN_EXP_COEFF = 0.02f;
    public static final float SNOWBALL_ACCUMULATION_TIME = 8.0f;
    private float g;

    @FrameAccumulatorForPerformance
    private byte h;

    @NAGS
    private ParticleEffectPool.PooledEffect i;

    @NAGS
    private Circle j;

    @NAGS
    private Circle k;

    /* synthetic */ FreezingTower(byte b2) {
        this();
    }

    static {
        new Color(563540772);
        e = new Color(MaterialColor.LIGHT_BLUE.P500).mul(1.0f, 1.0f, 1.0f, 0.14f);
        f = new Color(MaterialColor.LIGHT_BLUE.P500).mul(1.0f, 1.0f, 1.0f, 0.56f);
        ABILITY_ALIASES = new String[]{"COLD_EVAPORATION", "SLOW_FREEZING", "MONITORING_SYSTEM"};
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.g);
        output.writeByte(this.h);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.g = input.readFloat();
        this.h = input.readByte();
    }

    private FreezingTower() {
        super(TowerType.FREEZING);
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(1)) {
            return Game.i.towerManager.F.FREEZING.getAbilityTextures(1);
        }
        return Game.i.towerManager.F.FREEZING.getWeaponTexture();
    }

    private float a() {
        return 8.0f * ((float) Math.pow(1.0f + ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FREEZING_A_PER_SNOWBALL_PENALTY)), this.S.unit.spawnedSnowballs));
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAim() {
        return isAbilityInstalled(2);
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean shouldSearchForTarget() {
        return isAbilityInstalled(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float b() {
        float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_FREEZING_A_SNOWBALL_MIN_DURATION);
        return floatValue + ((this.S.gameValue.getFloatValue(GameValueType.TOWER_FREEZING_A_SNOWBALL_MAX_DURATION) - floatValue) * (getUpgradeLevel() / 10.0f));
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.FREEZE_PERCENT && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FREEZING_A_SLOW_PERCENT));
        }
        if (towerStatType == TowerStatType.FREEZE_SPEED && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FREEZING_A_SLOW_SPEED));
        }
        return calculateStat;
    }

    private void c() {
        Color cpy = MaterialColor.LIGHT_BLUE.P500.cpy();
        Color cpy2 = MaterialColor.LIGHT_BLUE.P500.cpy();
        cpy.f889a = 0.0f;
        cpy2.f889a = 0.07f;
        this.j.setup(getTile().center.x, getTile().center.y, this.rangeInPixels * 0.5f, this.rangeInPixels, 48, cpy.toFloatBits(), cpy2.toFloatBits());
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void updateCache() {
        super.updateCache();
        if (this.j != null && getTile() != null) {
            c();
        }
        if (isAbilityInstalled(2)) {
            this.experienceMultiplier = (float) (this.experienceMultiplier * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FREEZING_A_MONITORING_XP));
        }
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void removedFromMap() {
        super.removedFromMap();
        if (this.i != null) {
            this.i.allowCompletion();
        }
    }

    private void a(float f2) {
        if (isAbilityInstalled(3) && !isOutOfOrder()) {
            boolean[] zArr = {false};
            float f3 = 1.0f;
            if (isAbilityInstalled(4)) {
                f3 = (float) (1.0d + this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FREEZING_A_ULTIMATE_SNOW_BONUS));
            } else {
                this.S.map.getEnemiesInCircle(getTile().center.x, getTile().center.y, 320.0f, (enemyReference, f4, f5, f6) -> {
                    if (enemyReference.enemy == null) {
                        return true;
                    }
                    zArr[0] = true;
                    return false;
                });
            }
            if (!zArr[0]) {
                this.g += f2 * f3;
                if (this.g >= a()) {
                    SnowballUnit create = Game.i.unitManager.F.SNOWBALL.create();
                    create.setup(this, b());
                    if (this.S.unit.preparePathToRandomSpawn(create, getTile())) {
                        this.S.unit.register(create);
                        this.S.map.spawnUnit(create);
                    } else if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                        ParticleEffectPool.PooledEffect breakParticle = Game.i.unitManager.F.SNOWBALL.getBreakParticle();
                        breakParticle.setPosition(getTile().center.x, getTile().center.y);
                        this.S._particle.addParticle(breakParticle, true);
                    }
                    this.g = 0.0f;
                    return;
                }
                return;
            }
            if (this.S._particle != null && this.g > a() * 0.25f && Game.i.settingsManager.isParticlesDrawing()) {
                ParticleEffectPool.PooledEffect breakParticle2 = Game.i.unitManager.F.SNOWBALL.getBreakParticle();
                breakParticle2.setPosition(getTile().center.x, getTile().center.y);
                this.S._particle.addParticle(breakParticle2, true);
            }
            this.g = 0.0f;
            return;
        }
        this.g = 0.0f;
    }

    private void d() {
        if (!isOutOfOrder()) {
            float stat = getStat(TowerStatType.FREEZE_PERCENT);
            float stat2 = getStat(TowerStatType.FREEZE_SPEED);
            float stat3 = getStat(TowerStatType.U_POISON_DURATION_BONUS);
            float stat4 = getStat(TowerStatType.U_CHAIN_LIGHTNING_BONUS_LENGTH);
            this.S.map.getEnemiesInCircle(getTile().center.x, getTile().center.y, this.rangeInPixels, (enemyReference, f2, f3, f4) -> {
                Enemy enemy = enemyReference.enemy;
                if (enemy != null && canAttackEnemy(enemy)) {
                    DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.FREEZING);
                    FreezingBuff freezingBuff = null;
                    if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
                        int i = 0;
                        while (true) {
                            if (i >= buffsByTypeOrNull.size) {
                                break;
                            }
                            FreezingBuff freezingBuff2 = (FreezingBuff) buffsByTypeOrNull.items[i];
                            if (freezingBuff2.tower != this) {
                                i++;
                            } else {
                                freezingBuff = freezingBuff2;
                                break;
                            }
                        }
                    }
                    float towerDamageMultiplier = enemy.getTowerDamageMultiplier(TowerType.FREEZING) * stat;
                    float tickRateDeltaTime = this.S.gameValue.getTickRateDeltaTime() * 9.0f;
                    if (freezingBuff == null) {
                        FreezingBuff freezingBuff3 = new FreezingBuff();
                        freezingBuff3.setup(this, stat2, towerDamageMultiplier, tickRateDeltaTime, 5.0f, stat3, stat4);
                        freezingBuff3.copyDisabled = true;
                        this.S.buff.P_FREEZING.addBuff(enemy, freezingBuff3);
                        freezingBuff = freezingBuff3;
                    } else {
                        freezingBuff.duration = tickRateDeltaTime;
                        freezingBuff.speed = stat2;
                        freezingBuff.maxPercent = towerDamageMultiplier;
                        freezingBuff.poisonDurationBonus = stat3;
                        freezingBuff.lightningLengthBonus = stat4;
                    }
                    if (isAbilityInstalled(2) && enemy == getTarget()) {
                        freezingBuff.maxPercent = stat * 1.15f;
                        if (freezingBuff.maxPercent > 95.0f) {
                            freezingBuff.maxPercent = 95.0f;
                        }
                        enemy.buffFreezingPercent = Math.max(enemy.buffFreezingPercent, freezingBuff.maxPercent);
                        return true;
                    }
                    freezingBuff.maxPercent = towerDamageMultiplier;
                    return true;
                }
                return true;
            });
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f2) {
        this.angle += f2 * 360.0f;
        this.angle %= 360.0f;
        if (this.i == null && this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
            this.i = Game.i.towerManager.F.FREEZING.d.obtain();
            this.i.setPosition(getTile().center.x, getTile().center.y);
            Array<ParticleEmitter> emitters = this.i.getEmitters();
            float range = getRange() * 2.0f;
            ParticleEmitter particleEmitter = emitters.get(0);
            particleEmitter.getXScale().setHigh(6.0f * range, 12.0f * range);
            particleEmitter.getYScale().setHigh(6.0f * range, 12.0f * range);
            particleEmitter.getXScale().setLow(4.0f * range);
            particleEmitter.getYScale().setLow(4.0f * range);
            particleEmitter.getVelocity().setHigh(8.0f * range, 16.0f * range);
            particleEmitter.getVelocity().setLow(8.0f * range);
            emitters.get(1).getXScale().setHigh(140.0f * range);
            emitters.get(1).getYScale().setHigh(140.0f * range);
            this.S._particle.addParticle(this.i, false);
        }
        a(f2);
        this.h = (byte) (this.h + 1);
        if (this.h == 8) {
            this.h = (byte) 0;
            d();
        }
        super.update(f2);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f2) {
        Enemy target;
        if (isAbilityInstalled(2) && (target = getTarget()) != null) {
            DrawUtils.texturedLineD(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), getTile().center.x, getTile().center.y, target.getPosition().x, target.getPosition().y, 1.7f, 6.0f, e, f);
        }
        if (this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED) {
            if (this.j == null && Game.i.shapeManager != null) {
                this.j = (Circle) Game.i.shapeManager.getFactory(ShapeType.CIRCLE).obtain();
                c();
            }
            if (this.j != null) {
                this.j.draw(batch);
            }
        }
        if (this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED && this.S._gameMapSelection.getSelectedTile() == getTile() && isAbilityInstalled(3) && !isAbilityInstalled(4)) {
            if (this.k == null && Game.i.shapeManager != null) {
                this.k = Game.i.shapeManager.F.CIRCLE.obtain();
                float floatBits = new Color(1.0f, 1.0f, 1.0f, 0.07f).toFloatBits();
                this.k.setup(getTile().center.x, getTile().center.y, 316.0f, 320.0f, 112, floatBits, floatBits);
                this.k.setSkipOddSegments(true);
            }
            if (this.k != null) {
                this.k.draw(batch);
            }
        } else if (this.k != null) {
            Game.i.shapeManager.F.CIRCLE.free(this.k);
            this.k = null;
        }
        if (this.g > 0.0f) {
            float regionWidth = Game.i.towerManager.F.FREEZING.c.getRegionWidth() * (this.g / a());
            batch.draw(Game.i.towerManager.F.FREEZING.c, getTile().center.x - (regionWidth * 0.5f), getTile().center.y - (regionWidth * 0.5f), regionWidth, regionWidth);
        }
        super.drawBatch(batch, f2);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/FreezingTower$FreezingTowerFactory.class */
    public static class FreezingTowerFactory extends Tower.Factory<FreezingTower> {
        TextureRegion c;
        public TextureRegion monitoringTraceTexture;
        ParticleEffectPool d;

        public FreezingTowerFactory() {
            super("tower-freezing", TowerType.FREEZING);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{"", ""};
            this.f1784b[1].descriptionArgs = new String[]{"", ""};
            this.f1784b[2].descriptionArgs = new String[]{"", ""};
            this.f1784b[3].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[4].descriptionArgs = new String[]{""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 3;
                case ATTACK_SPEED:
                    return 5;
                case DAMAGE:
                    return 0;
                case CROWD_DAMAGE:
                    return 0;
                case AGILITY:
                    return 5;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_FREEZING_A_EVAPORATION_DAMAGE), 2, true).toString();
            abilityConfigs[0].descriptionArgs[1] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_FREEZING_A_EVAPORATION_STACK), false).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FREEZING_A_SLOW_SPEED), 2, true).toString();
            abilityConfigs[1].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FREEZING_A_SLOW_PERCENT), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(15.0d, 1, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FREEZING_A_MONITORING_XP), 2, true).toString();
            float b2 = ((FreezingTower) tower).b();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(2.5d, 1, true).toString();
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(b2, 1, true).toString();
            abilityConfigs[3].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_FREEZING_A_PER_SNOWBALL_PENALTY), 1, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_FREEZING_A_ULTIMATE_SNOW_BONUS), 1, true).toString();
            return abilityConfigs;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public boolean shouldDrawAbilityToCache(int i) {
            if (i == 1) {
                return false;
            }
            return true;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public boolean canKillEnemies() {
            return false;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 46;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.BLUE.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setupAssets() {
            this.monitoringTraceTexture = Game.i.assetManager.getTextureRegion("freezing-monitoring-trace");
            this.d = Game.i.assetManager.getParticleEffectPool("snowflakes.prt");
            this.c = Game.i.assetManager.getTextureRegion("unit-type-snowball");
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return FreezingTower.ABILITY_ALIASES;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public FreezingTower create() {
            return new FreezingTower((byte) 0);
        }
    }
}
