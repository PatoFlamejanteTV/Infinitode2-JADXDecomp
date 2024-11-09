package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.explosions.CannonExplosion;
import com.prineside.tdi2.projectiles.CannonProjectile;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.shapes.PieChart;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PieChartActor;
import com.prineside.tdi2.units.MineUnit;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.MovingAverageFloat;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/CannonTower.class */
public final class CannonTower extends Tower {
    public static final int ABILITY_FOUNDATION = 2;
    public static final String[] ABILITY_ALIASES = {"SHRAPNEL", "LONG_BARREL", "FOUNDATION"};
    private float e;
    private float f;
    private float g;
    private MovingAverageFloat h;

    /* synthetic */ CannonTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
        output.writeFloat(this.g);
        kryo.writeObject(output, this.h);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = input.readFloat();
        this.f = input.readFloat();
        this.g = input.readFloat();
        this.h = (MovingAverageFloat) kryo.readObject(input, MovingAverageFloat.class);
    }

    private CannonTower() {
        super(TowerType.CANNON);
        this.h = new MovingAverageFloat(10);
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(1)) {
            return Game.i.towerManager.F.CANNON.getAbilityTextures(1);
        }
        return Game.i.towerManager.F.CANNON.getWeaponTexture();
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAim() {
        return true;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean shouldSearchForTarget() {
        return true;
    }

    public final float getRotationSinceShot() {
        return Math.min(180.0f, this.g);
    }

    public final float getDamageBonusForFoundationRotation() {
        if (isAbilityInstalled(2)) {
            return getRotationSinceShot() * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_FOUNDATION_DAMAGE_PER_DEG));
        }
        return 0.0f;
    }

    @Override // com.prineside.tdi2.Tower
    public final void attack(int i) {
        if (getTarget() == null) {
            return;
        }
        Vector2 vector2 = new Vector2();
        float f = 30.0f;
        if (isAbilityInstalled(1)) {
            f = 43.0f;
        }
        PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, this.angle, f, vector2);
        CannonProjectile obtain = this.S.projectile.F.CANNON.obtain();
        this.S.projectile.register(obtain);
        int i2 = 0;
        float f2 = 0.0f;
        float f3 = 0.0f;
        if (isAbilityInstalled(0)) {
            i2 = this.S.gameValue.getIntValue(GameValueType.TOWER_CANNON_A_SHRAPNEL_COUNT);
            f2 = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_SHRAPNEL_DAMAGE);
            f3 = ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_SHRAPNEL_DISTANCE)) * getStat(TowerStatType.U_EXPLOSION_RANGE) * 128.0f;
        }
        obtain.setup(this, getTarget(), getStat(TowerStatType.DAMAGE) * i * (1.0f + getDamageBonusForFoundationRotation()), getStat(TowerStatType.U_EXPLOSION_RANGE), vector2, getStat(TowerStatType.PROJECTILE_SPEED), i2 * i, f2, f3);
        this.shotCount += i;
        this.h.push(this.g);
        this.g = 0.0f;
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_CANNON, this);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void fillTowerMenu(Group group, ObjectMap<String, Object> objectMap) {
        int i = 1;
        if (isAbilityInstalled(2)) {
            i = 32;
        }
        if (objectMap.size == 0 || !objectMap.get("state", -1).equals(Integer.valueOf(i))) {
            group.clear();
            if (isAbilityInstalled(2)) {
                PieChartActor pieChartActor = new PieChartActor();
                Array<PieChart.ChartEntryConfig> array = new Array<>(PieChart.ChartEntryConfig.class);
                array.add(new PieChart.ChartEntryConfig(MaterialColor.RED.P500, 20.0f, 0.0f));
                array.add(new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.28f), 30.0f, 0.0f));
                pieChartActor.setPosition(40.0f, 0.0f);
                pieChartActor.setSize(64.0f, 64.0f);
                pieChartActor.setSegmentCount(200);
                pieChartActor.setConfigs(array);
                group.addActor(pieChartActor);
                Actor image = new Image(Game.i.assetManager.getDrawable("circle"));
                image.setColor(new Color(724249599));
                image.setSize(36.0f, 36.0f);
                image.setPosition(54.0f, 14.0f);
                group.addActor(image);
                Actor image2 = new Image(Game.i.assetManager.getDrawable("icon-rotation-speed"));
                image2.setSize(28.0f, 28.0f);
                image2.setPosition(58.0f, 18.0f);
                group.addActor(image2);
                Actor label = new Label("", Game.i.assetManager.getLabelStyle(21));
                label.setPosition(120.0f, 38.0f);
                label.setSize(100.0f, 18.0f);
                group.addActor(label);
                Actor label2 = new Label(Game.i.localeManager.i18n.get("average_rotation_since_shot"), Game.i.assetManager.getLabelStyle(21));
                label2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.56f));
                label2.setPosition(120.0f, 12.0f);
                label2.setSize(100.0f, 16.0f);
                group.addActor(label2);
                objectMap.put("foundationAngleChart", pieChartActor);
                objectMap.put("rotationTitle", label);
            }
            objectMap.put("state", Integer.valueOf(i));
        }
        if (isAbilityInstalled(2)) {
            Label label3 = (Label) objectMap.get("rotationTitle");
            PieChartActor pieChartActor2 = (PieChartActor) objectMap.get("foundationAngleChart");
            float average = this.h.getAverage();
            float f = average;
            if (average > 180.0f) {
                f = 180.0f;
            }
            Array<PieChart.ChartEntryConfig> configs = pieChartActor2.getConfigs();
            configs.get(0).setValue(f);
            configs.get(1).setValue((180.0f - f) + 0.01f);
            pieChartActor2.setConfigs(configs);
            d.setLength(0);
            d.append(MathUtils.round(f));
            d.append(Game.i.localeManager.i18n.get("measure_units_degrees"));
            d.append(", +");
            d.append(StringFormatter.compactNumberWithPrecision(f * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_FOUNDATION_DAMAGE_PER_DEG)) * 100.0f, 1));
            d.append("% ");
            d.append(Game.i.localeManager.i18n.get("tower_stat_DAMAGE"));
            label3.setText(d);
        }
        super.fillTowerMenu(group, objectMap);
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.RANGE && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_LONG_BARREL_RANGE));
        }
        if (towerStatType == TowerStatType.ROTATION_SPEED && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_FOUNDATION_SPEED));
        }
        if (towerStatType == TowerStatType.PROJECTILE_SPEED && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_FOUNDATION_SPEED));
        }
        if (towerStatType == TowerStatType.U_EXPLOSION_RANGE && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_LONG_EXPLOSION_RANGE));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        if (isOutOfOrder()) {
            super.update(f);
            return;
        }
        a(f, getStat(TowerStatType.ROTATION_SPEED));
        this.g += Math.abs(PMath.getDistanceBetweenAngles(this.f, this.angle));
        this.f = this.angle;
        if (isAbilityInstalled(4)) {
            float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_CANNON_A_MINE_INTERVAL);
            int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_CANNON_A_MINE_COUNT);
            float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_MINE_DAMAGE);
            this.e += f;
            if (this.e >= floatValue) {
                this.e -= floatValue;
                int i = 0;
                for (int i2 = 0; i2 < this.S.map.spawnedUnits.size; i2++) {
                    Unit unit = this.S.map.spawnedUnits.items[i2];
                    if (unit.type == UnitType.MINE && ((MineUnit) unit).owner == this) {
                        i++;
                    }
                }
                if (i < intValue) {
                    Array<Tile> tileArray = this.S.TSH.getTileArray();
                    if (this.S.gameValue.getBooleanValue(GameValueType.ENEMIES_WALK_ON_PLATFORMS)) {
                        this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
                            if (tile.enemyCount != 0) {
                                return true;
                            }
                            if (tile.type != TileType.PLATFORM) {
                                if (tile.type == TileType.ROAD) {
                                    tileArray.add(tile);
                                    return true;
                                }
                                return true;
                            }
                            PlatformTile platformTile = (PlatformTile) tile;
                            if (platformTile.building == null) {
                                tileArray.add(platformTile);
                                return true;
                            }
                            return true;
                        });
                    } else {
                        this.S.map.traverseNeighborTilesAroundTile(getTile(), tile2 -> {
                            if (tile2.enemyCount == 0 && tile2.type == TileType.ROAD) {
                                tileArray.add(tile2);
                                return true;
                            }
                            return true;
                        });
                    }
                    if (tileArray.size != 0) {
                        Tile tile3 = tileArray.items[this.S.gameState.randomInt(tileArray.size)];
                        float randomFloat = tile3.center.x + ((this.S.gameState.randomFloat() - 0.5f) * 64.0f);
                        float randomFloat2 = tile3.center.y + ((this.S.gameState.randomFloat() - 0.5f) * 64.0f);
                        int i3 = 0;
                        float f2 = 0.0f;
                        float f3 = 0.0f;
                        if (isAbilityInstalled(0)) {
                            i3 = this.S.gameValue.getIntValue(GameValueType.TOWER_CANNON_A_SHRAPNEL_COUNT);
                            f2 = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_SHRAPNEL_DAMAGE);
                            f3 = ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_SHRAPNEL_DISTANCE)) * getStat(TowerStatType.U_EXPLOSION_RANGE) * 128.0f;
                        }
                        CannonExplosion obtain = this.S.explosion.F.CANNON.obtain();
                        obtain.setup(this, 0.0f, 0.0f, getStat(TowerStatType.DAMAGE) * (1.0f / getAttackDelay()) * percentValueAsMultiplier, getStat(TowerStatType.U_EXPLOSION_RANGE) * 2.0f, i3, f2, f3);
                        obtain.piercingMultiplier = 1.5f;
                        obtain.throwBackDistance = (this.level * 0.1f * 1.5f) + 1.5f;
                        MineUnit create = Game.i.unitManager.F.MINE.create();
                        create.setup(this, getTile().center.x, getTile().center.y, randomFloat, randomFloat2, obtain, MaterialColor.RED.P500);
                        this.S.unit.register(create);
                        this.S.map.spawnUnit(create);
                    }
                    this.S.TSH.freeTileArray(tileArray);
                }
            }
        }
        super.update(f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/CannonTower$CannonTowerFactory.class */
    public static class CannonTowerFactory extends Tower.Factory<CannonTower> {
        public CannonTowerFactory() {
            super("tower-cannon", TowerType.CANNON);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{"", ""};
            this.f1784b[1].descriptionArgs = new String[]{"", ""};
            this.f1784b[2].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[3].descriptionArgs = new String[]{"", ""};
            this.f1784b[4].descriptionArgs = new String[]{"", "", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_CANNON_A_SHRAPNEL_COUNT), false).toString();
            abilityConfigs[0].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_CANNON_A_SHRAPNEL_DAMAGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_LONG_BARREL_RANGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_LONG_EXPLOSION_RANGE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_FOUNDATION_SPEED), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_FOUNDATION_PIERCING), 2, true).toString();
            abilityConfigs[2].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_CANNON_A_FOUNDATION_DAMAGE_PER_DEG), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_CANNON_A_PRESSURE_HEALTH), false).toString();
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_CANNON_A_PRESSURE_DAMAGE), false).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_CANNON_A_MINE_DAMAGE), 2, true).toString();
            abilityConfigs[4].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_CANNON_A_MINE_INTERVAL), 2, true).toString();
            abilityConfigs[4].descriptionArgs[2] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_CANNON_A_MINE_COUNT), false).toString();
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
            return MaterialColor.RED.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 2;
                case ATTACK_SPEED:
                    return 2;
                case DAMAGE:
                    return 2;
                case CROWD_DAMAGE:
                    return 5;
                case AGILITY:
                    return 4;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return CannonTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 33;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public CannonTower create() {
            return new CannonTower((byte) 0);
        }
    }
}
