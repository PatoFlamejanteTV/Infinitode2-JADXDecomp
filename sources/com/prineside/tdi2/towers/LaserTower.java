package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.BonusCoinsBuff;
import com.prineside.tdi2.buffs.BurnBuff;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.projectiles.LaserProjectile;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/LaserTower.class */
public final class LaserTower extends Tower {
    public static final String[] ABILITY_ALIASES = {"HIGH_FREQUENCY", "MIRRORS_SYSTEM", "LARGE_BATTERIES"};
    private float e;
    private boolean f;
    private float g;
    private int h;
    private float i;
    private int j;
    public FloatArray ultDamageBonuses;
    private DelayedRemovalArray<ActiveLaserConfig> k;

    /* synthetic */ LaserTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.e);
        output.writeBoolean(this.f);
        output.writeFloat(this.g);
        output.writeVarInt(this.h, true);
        output.writeFloat(this.i);
        output.writeVarInt(this.j, true);
        kryo.writeObject(output, this.ultDamageBonuses);
        kryo.writeObject(output, this.k);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = input.readFloat();
        this.f = input.readBoolean();
        this.g = input.readFloat();
        this.h = input.readVarInt(true);
        this.i = input.readFloat();
        this.j = input.readVarInt(true);
        this.ultDamageBonuses = (FloatArray) kryo.readObject(input, FloatArray.class);
        this.k = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
    }

    private LaserTower() {
        super(TowerType.LASER);
        this.ultDamageBonuses = new FloatArray(false, 4);
        this.k = new DelayedRemovalArray<>(ActiveLaserConfig.class);
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(1)) {
            return Game.i.towerManager.F.LASER.getAbilityTextures(1);
        }
        return Game.i.towerManager.F.LASER.getWeaponTexture();
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAim() {
        return true;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean shouldSearchForTarget() {
        return true;
    }

    @Override // com.prineside.tdi2.Tower
    public final float getAttackDelay() {
        if (this.f) {
            return Float.MAX_VALUE;
        }
        return 100.0f / getStat(TowerStatType.CHARGING_SPEED);
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAttack() {
        return !this.f && !isOutOfOrder() && super.canAttack() && this.e >= 100.0f;
    }

    public final float getUltDamageMultiplier() {
        if (isAbilityInstalled(4)) {
            return (this.ultDamageBonuses.size * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_ULTIMATE_DAMAGE_BONUS))) + 1.0f;
        }
        return 1.0f;
    }

    @Override // com.prineside.tdi2.Tower
    public final void onAbilitySet(int i, boolean z) {
        if (z && i == 2) {
            for (int i2 = 0; i2 < this.k.size; i2++) {
                ActiveLaserConfig activeLaserConfig = this.k.get(i2);
                float duration = (activeLaserConfig.projectile.getDuration() * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_LARGE_DURATION))) - activeLaserConfig.projectile.getDuration();
                activeLaserConfig.durationLeft += duration;
                activeLaserConfig.projectile.setDuration(activeLaserConfig.projectile.getDuration() + duration);
            }
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void attack(int i) {
        if (getTarget() == null) {
            return;
        }
        this.f = true;
        this.g = 0.0f;
        this.e = 0.0f;
        Vector2 vector2 = new Vector2();
        Vector2 vector22 = new Vector2();
        float stat = getStat(TowerStatType.U_BATTERIES_CAPACITY);
        float stat2 = getStat(TowerStatType.DAMAGE);
        if (isAbilityInstalled(1)) {
            int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_LASER_A_MIRRORS_BEAM_COUNT);
            int i2 = intValue;
            if (intValue <= 0) {
                i2 = 1;
            }
            float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_MIRRORS_BEAM_ANGLE);
            float f = (-floatValue) * 0.5f;
            float f2 = floatValue / (i2 - 1);
            if (i2 == 1) {
                f = 0.0f;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                LaserProjectile obtain = this.S.projectile.F.LASER.obtain();
                this.S.projectile.register(obtain);
                vector2.set(0.0f, 20.0f).rotateDeg(this.angle).add(getTile().center);
                vector22.set(0.0f, 6144.0f).rotateDeg(this.angle + f).add(getTile().center);
                obtain.setup(this, stat, stat2, vector2.x, vector2.y, vector22.x, vector22.y, 1);
                ActiveLaserConfig activeLaserConfig = new ActiveLaserConfig();
                activeLaserConfig.startX = vector2.x;
                activeLaserConfig.startY = vector2.y;
                activeLaserConfig.endX = vector22.x;
                activeLaserConfig.endY = vector22.y;
                activeLaserConfig.baseDamage = stat2;
                activeLaserConfig.projectile = obtain;
                activeLaserConfig.durationLeft = stat;
                activeLaserConfig.angleDelta = f;
                this.k.add(activeLaserConfig);
                f += f2;
            }
        } else {
            LaserProjectile obtain2 = this.S.projectile.F.LASER.obtain();
            this.S.projectile.register(obtain2);
            vector2.set(0.0f, 20.0f).rotateDeg(this.angle).add(getTile().center);
            vector22.set(0.0f, 6144.0f).rotateDeg(this.angle).add(getTile().center);
            obtain2.setup(this, stat, stat2, vector2.x, vector2.y, vector22.x, vector22.y, 1);
            ActiveLaserConfig activeLaserConfig2 = new ActiveLaserConfig();
            activeLaserConfig2.startX = vector2.x;
            activeLaserConfig2.startY = vector2.y;
            activeLaserConfig2.endX = vector22.x;
            activeLaserConfig2.endY = vector22.y;
            activeLaserConfig2.baseDamage = stat2;
            activeLaserConfig2.projectile = obtain2;
            activeLaserConfig2.durationLeft = stat;
            activeLaserConfig2.angleDelta = 0.0f;
            this.k.add(activeLaserConfig2);
        }
        this.shotCount++;
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_LASER, this);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.U_BATTERIES_CAPACITY && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_LARGE_DURATION));
        }
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_MIRRORS_DAMAGE));
        }
        if (towerStatType == TowerStatType.CHARGING_SPEED && isAbilityInstalled(3)) {
            calculateStat = (float) (calculateStat * ((1.0d + this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_IONIZATION_SPEED)) - (this.h * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_IONIZATION_SPEED_REDUCTION))));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void updateCache() {
        if (getTile() != null) {
            this.h = 0;
            this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
                if (tile.type == TileType.PLATFORM) {
                    PlatformTile platformTile = (PlatformTile) tile;
                    if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER) {
                        this.h++;
                        return true;
                    }
                    return true;
                }
                return true;
            });
        } else {
            this.h = 0;
        }
        super.updateCache();
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        this.k.begin();
        float stat = getStat(TowerStatType.U_BATTERIES_CAPACITY);
        float stat2 = getStat(TowerStatType.DAMAGE);
        for (int i = 0; i < this.k.size; i++) {
            ActiveLaserConfig activeLaserConfig = this.k.items[i];
            activeLaserConfig.durationLeft -= f;
            if (activeLaserConfig.durationLeft > 0.0f) {
                activeLaserConfig.projectile.setDamage(activeLaserConfig.projectile.getDamage() + (activeLaserConfig.baseDamage * f * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_DAMAGE_PER_SECOND_SHOOTING))));
            } else {
                if (isAbilityInstalled(0)) {
                    int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_LASER_A_HIGH_ENEMY_COUNT);
                    float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_HIGH_FIRE_DURATION);
                    float percentValueAsMultiplier = ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_HIGH_DAMAGE)) * stat * stat2;
                    if (intValue <= 0) {
                        intValue = 1;
                    }
                    int[] iArr = {0};
                    int i2 = intValue;
                    float angleBetweenPoints = PMath.getAngleBetweenPoints(activeLaserConfig.startX, activeLaserConfig.startY, activeLaserConfig.endX, activeLaserConfig.endY);
                    Vector2 vector2 = new Vector2();
                    PMath.getPointByAngleFromPoint(activeLaserConfig.startX, activeLaserConfig.startY, angleBetweenPoints, 8192.0f, vector2);
                    this.S.map.rayCastEnemiesSorted(activeLaserConfig.startX, activeLaserConfig.startY, vector2.x, vector2.y, 5.0f, enemyReference -> {
                        Enemy enemy = enemyReference.enemy;
                        if (enemy != null) {
                            if (canAttackEnemy(enemy)) {
                                BurnBuff burnBuff = new BurnBuff();
                                burnBuff.setup(this, floatValue, floatValue * 10.0f, percentValueAsMultiplier, null);
                                this.S.buff.P_BURN.addBuff(enemy, burnBuff);
                                if (isAbilityInstalled(3)) {
                                    BonusCoinsBuff bonusCoinsBuff = new BonusCoinsBuff();
                                    float floatValue2 = this.S.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_IONIZATION_COINS_DURATION);
                                    bonusCoinsBuff.setup(floatValue2, floatValue2 * 10.0f, (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_IONIZATION_COINS), this);
                                    this.S.buff.P_BONUS_COINS.addBuff(enemy, bonusCoinsBuff);
                                }
                            }
                            iArr[0] = iArr[0] + 1;
                        }
                        return iArr[0] != i2;
                    });
                }
                this.k.removeIndex(i);
            }
        }
        this.k.end();
        this.g += f;
        if (this.f && this.g < stat) {
            if (isAbilityInstalled(2)) {
                float f2 = this.angle;
                a(f, getStat(TowerStatType.ROTATION_SPEED) * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_LARGE_ROTATION_SPEED)));
                if (f2 != this.angle) {
                    for (int i3 = 0; i3 < this.k.size; i3++) {
                        ActiveLaserConfig activeLaserConfig2 = this.k.items[i3];
                        Vector2 vector22 = new Vector2();
                        Vector2 vector23 = new Vector2();
                        vector22.set(0.0f, 20.0f).rotateDeg(this.angle).add(getTile().center);
                        vector23.set(0.0f, 6144.0f).rotateDeg(this.angle + activeLaserConfig2.angleDelta).add(getTile().center);
                        activeLaserConfig2.projectile.setStartPos(vector22.x, vector22.y);
                        activeLaserConfig2.projectile.setEndPos(vector23.x, vector23.y);
                        activeLaserConfig2.startX = vector22.x;
                        activeLaserConfig2.startY = vector22.y;
                        activeLaserConfig2.endX = vector23.x;
                        activeLaserConfig2.endY = vector23.y;
                        activeLaserConfig2.projectile.handleCollisions(0.0f);
                    }
                }
            }
        } else {
            this.f = false;
            if (!isOutOfOrder()) {
                this.e += f * getStat(TowerStatType.CHARGING_SPEED);
                if (this.e > 100.0f) {
                    this.e = 100.0f;
                }
                a(f, getStat(TowerStatType.ROTATION_SPEED));
            }
        }
        for (int i4 = this.ultDamageBonuses.size - 1; i4 >= 0; i4--) {
            float[] fArr = this.ultDamageBonuses.items;
            int i5 = i4;
            fArr[i5] = fArr[i5] - f;
            if (this.ultDamageBonuses.items[i4] <= 0.0f) {
                this.ultDamageBonuses.removeIndex(i4);
            }
        }
        if (isAbilityInstalled(4)) {
            int statistic = (int) this.S.statistics.getStatistic(StatisticsType.EK);
            float floatValue2 = this.S.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_ULTIMATE_PASSIVE_INTERVAL);
            if (this.j != statistic) {
                this.i = 0.0f;
                this.j = statistic;
            } else {
                this.i += f;
                if (this.i > floatValue2) {
                    this.i -= floatValue2;
                    this.ultDamageBonuses.add(this.S.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_ULTIMATE_DURATION));
                }
            }
        }
        super.update(f);
    }

    @Override // com.prineside.tdi2.Tower
    protected final void a(StringBuilder stringBuilder) {
        stringBuilder.append("chargingPercent: ").append(this.e).append(SequenceUtils.EOL);
        stringBuilder.append("isShooting: ").append(this.f).append(SequenceUtils.EOL);
        stringBuilder.append("shootingTime: ").append(this.g).append(SequenceUtils.EOL);
        stringBuilder.append("towersNearby: ").append(this.h).append(SequenceUtils.EOL);
        stringBuilder.append("ultNoDeathsTimeAccumulator: ").append(this.i).append(SequenceUtils.EOL);
        stringBuilder.append("ultNoDeathsLastEnemyKills: ").append(this.j).append(SequenceUtils.EOL);
    }

    @Override // com.prineside.tdi2.Tower
    public final void fillTowerMenu(Group group, ObjectMap<String, Object> objectMap) {
        if (isAbilityInstalled(4)) {
            if (objectMap.size == 0 || !objectMap.get("state", -1).equals(1)) {
                group.clear();
                Image image = new Image(Game.i.assetManager.getDrawable("icon-damage"));
                image.setPosition(40.0f, 0.0f);
                image.setSize(48.0f, 48.0f);
                group.addActor(image);
                Label label = new Label("", Game.i.assetManager.getLabelStyle(24));
                label.setPosition(102.0f, 24.0f);
                label.setSize(100.0f, 24.0f);
                group.addActor(label);
                Label label2 = new Label(Game.i.localeManager.i18n.get("tower_ability_name_ultimate"), Game.i.assetManager.getLabelStyle(21));
                label2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                label2.setPosition(102.0f, 0.0f);
                label2.setSize(100.0f, 20.0f);
                group.addActor(label2);
                objectMap.put("state", 1);
                objectMap.put("damageIcon", image);
                objectMap.put("bonusLabel", label);
            }
            Label label3 = (Label) objectMap.get("bonusLabel");
            Image image2 = (Image) objectMap.get("damageIcon");
            float ultDamageMultiplier = getUltDamageMultiplier();
            if (ultDamageMultiplier == 1.0f) {
                label3.setColor(MaterialColor.GREY.P500);
                image2.setColor(MaterialColor.GREY.P500);
            } else {
                label3.setColor(MaterialColor.LIGHT_GREEN.P500);
                image2.setColor(MaterialColor.LIGHT_GREEN.P500);
            }
            d.setLength(0);
            d.append('+').append(Math.round((ultDamageMultiplier - 1.0f) * 100.0f)).append('%');
            label3.setText(d);
        } else {
            group.clear();
        }
        super.fillTowerMenu(group, objectMap);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f) {
        super.drawBatch(batch, f);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_BUILDING_INFO) != 0.0d) {
            Game.i.assetManager.getDebugFont(false).draw(batch, (this.f ? "+" : "-") + SequenceUtils.SPACE + ((int) this.e), getTile().center.x - 50.0f, getTile().center.y - 30.0f, 100.0f, 1, false);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/LaserTower$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<GameSystemProvider> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(GameSystemProvider gameSystemProvider) {
            this.f1759a = gameSystemProvider;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            Tower tower = enemyDie.getLastDamage().getTower();
            if ((tower instanceof LaserTower) && tower.isAbilityInstalled(4)) {
                LaserTower laserTower = (LaserTower) tower;
                laserTower.ultDamageBonuses.add(((GameSystemProvider) this.f1759a).gameValue.getFloatValue(GameValueType.TOWER_LASER_A_ULTIMATE_DURATION));
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/LaserTower$LaserTowerFactory.class */
    public static class LaserTowerFactory extends Tower.Factory<LaserTower> {
        public LaserTowerFactory() {
            super("tower-laser", TowerType.LASER);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[1].descriptionArgs = new String[]{"", ""};
            this.f1784b[2].descriptionArgs = new String[]{"", ""};
            this.f1784b[3].descriptionArgs = new String[]{"", "", "", ""};
            this.f1784b[4].descriptionArgs = new String[]{"", "", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void configureSystems(GameSystemProvider gameSystemProvider) {
            gameSystemProvider.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(gameSystemProvider));
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            if (towerStatType == TowerStatType.U_BATTERIES_CAPACITY) {
                return Game.i.localeManager.i18n.format("tower_stat_more_info_LASER_U_BATTERIES_CAPACITY", StringFormatter.compactNumberWithPrecisionTrimZeros(gameValueProvider.getFloatValue(GameValueType.TOWER_LASER_DAMAGE_PER_SECOND_SHOOTING), 1, true).toString());
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_LASER_A_HIGH_ENEMY_COUNT), false).toString();
            abilityConfigs[0].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_HIGH_FIRE_DURATION), 2, true).toString();
            abilityConfigs[0].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_HIGH_DAMAGE), 1, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_LASER_A_MIRRORS_BEAM_COUNT), false).toString();
            abilityConfigs[1].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_MIRRORS_DAMAGE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_LARGE_DURATION), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_LARGE_ROTATION_SPEED), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_IONIZATION_SPEED), 2, true).toString();
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_IONIZATION_SPEED_REDUCTION), 2, true).toString();
            abilityConfigs[3].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_IONIZATION_COINS), 2, true).toString();
            abilityConfigs[3].descriptionArgs[3] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_IONIZATION_COINS_DURATION), 2, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_ULTIMATE_DAMAGE_BONUS), 2, true).toString();
            abilityConfigs[4].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_ULTIMATE_DURATION), 2, true).toString();
            abilityConfigs[4].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_ULTIMATE_PASSIVE_INTERVAL), 2, true).toString();
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
        public Color getColor() {
            return MaterialColor.LIGHT_BLUE.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 5;
                case ATTACK_SPEED:
                    return 1;
                case DAMAGE:
                    return 5;
                case CROWD_DAMAGE:
                    return 3;
                case AGILITY:
                    return 1;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return LaserTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 53;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public LaserTower create() {
            return new LaserTower((byte) 0);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/LaserTower$ActiveLaserConfig.class */
    public static class ActiveLaserConfig implements KryoSerializable {
        public LaserProjectile projectile;
        public float durationLeft;
        public float angleDelta;
        public float baseDamage;
        public float startX;
        public float startY;
        public float endX;
        public float endY;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.projectile);
            output.writeFloat(this.durationLeft);
            output.writeFloat(this.angleDelta);
            output.writeFloat(this.baseDamage);
            output.writeFloat(this.startX);
            output.writeFloat(this.startY);
            output.writeFloat(this.endX);
            output.writeFloat(this.endY);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.projectile = (LaserProjectile) kryo.readObject(input, LaserProjectile.class);
            this.durationLeft = input.readFloat();
            this.angleDelta = input.readFloat();
            this.baseDamage = input.readFloat();
            this.startX = input.readFloat();
            this.startY = input.readFloat();
            this.endX = input.readFloat();
            this.endY = input.readFloat();
        }
    }
}
