package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.components.PowerBonuses;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.projectiles.BasicProjectile;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/BasicTower.class */
public final class BasicTower extends Tower {
    public static final int ABILITY_FOUNDATION = 2;
    public static final String[] ABILITY_ALIASES = {"DOUBLE_GUN", "LARGE_CALIBER", "FOUNDATION"};
    private int e;
    private boolean f;
    private DelayedRemovalArray<Tower> g;

    @FrameAccumulatorForPerformance
    private byte h;

    /* synthetic */ BasicTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.e, true);
        output.writeBoolean(this.f);
        kryo.writeObjectOrNull(output, this.g, DelayedRemovalArray.class);
        output.writeByte(this.h);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = input.readVarInt(true);
        this.f = input.readBoolean();
        this.g = (DelayedRemovalArray) kryo.readObjectOrNull(input, DelayedRemovalArray.class);
        this.h = input.readByte();
    }

    private BasicTower() {
        super(TowerType.BASIC);
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            return Game.i.towerManager.F.BASIC.getAbilityTextures(0);
        }
        return Game.i.towerManager.F.BASIC.getWeaponTexture();
    }

    @Override // com.prineside.tdi2.Tower
    public final int getSellPrice() {
        if (isAbilityInstalled(3)) {
            this.moneySpentOn -= this.e;
            int sellPrice = super.getSellPrice();
            this.moneySpentOn += this.e;
            return this.e + sellPrice;
        }
        return super.getSellPrice();
    }

    @Override // com.prineside.tdi2.Tower
    public final void addExperience(float f) {
        if (this.g != null && this.g.size != 0) {
            int a2 = a();
            if (this.g == null) {
                super.addExperience(f);
                return;
            } else if (a2 == 0) {
                b(f / this.g.size);
                return;
            } else {
                a(f / a2);
                return;
            }
        }
        super.addExperience(f);
    }

    private int a() {
        int i = 0;
        this.g.begin();
        for (int i2 = 0; i2 < this.g.size; i2++) {
            Tower tower = this.g.items[i2];
            if (tower.isRegistered()) {
                if (tower.getLevel() < tower.getMaxTowerLevel()) {
                    i++;
                }
            } else {
                this.g.removeIndex(i2);
            }
        }
        this.g.end();
        if (this.g.size == 0) {
            this.g = null;
            return 0;
        }
        return i;
    }

    private void a(float f) {
        this.g.begin();
        for (int i = 0; i < this.g.size; i++) {
            Tower tower = this.g.items[i];
            if (tower.getLevel() < tower.getMaxTowerLevel()) {
                this.S.experience.addExperienceRaw(tower, f);
                if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && !this.S.gameState.canSkipMediaUpdate() && tower.getTile() != null) {
                    this.S._particle.addXpOrbParticle(f, getTile().getX(), getTile().getY(), tower.getTile().getX(), tower.getTile().getY());
                }
            }
        }
        this.g.end();
    }

    private void b(float f) {
        this.g.begin();
        for (int i = 0; i < this.g.size; i++) {
            Tower tower = this.g.items[i];
            this.S.experience.addExperienceRaw(tower, f);
            if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && !this.S.gameState.canSkipMediaUpdate() && tower.getTile() != null) {
                this.S._particle.addXpOrbParticle(f, getTile().getX(), getTile().getY(), tower.getTile().getX(), tower.getTile().getY());
            }
        }
        this.g.end();
    }

    @Override // com.prineside.tdi2.Tower
    public final void onAbilitySet(int i, boolean z) {
        super.onAbilitySet(i, z);
        if (i == 4) {
            int round = MathUtils.round(this.S.gameValue.getFloatValue(GameValueType.TOWER_BASIC_A_COPY_COUNT));
            float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BASIC_A_COPY_UPGRADE_LEVEL);
            for (int i2 = 0; i2 < round; i2++) {
                Array<Tower> towerArray = this.S.TSH.getTowerArray();
                this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
                    if (tile.type == TileType.PLATFORM) {
                        PlatformTile platformTile = (PlatformTile) tile;
                        if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER) {
                            towerArray.add((Tower) platformTile.building);
                            return true;
                        }
                        return true;
                    }
                    return true;
                });
                if (towerArray.size == 0) {
                    towerArray.add(this);
                }
                Tower tower = towerArray.items[this.S.gameState.randomInt(towerArray.size)];
                this.S.TSH.freeTowerArray(towerArray);
                byte floor = (byte) MathUtils.floor(tower.getUpgradeLevel() * percentValueAsMultiplier);
                byte b2 = floor;
                if (floor > 10) {
                    b2 = 10;
                }
                byte b3 = b2;
                this.S.map.traverseNeighborTilesAroundTile(getTile(), tile2 -> {
                    Tower buildTowerIgnorePrice;
                    if (tile2.type == TileType.PLATFORM && ((PlatformTile) tile2).building == null && (buildTowerIgnorePrice = this.S.tower.buildTowerIgnorePrice(tower.type, this.aimStrategy, tile2.getX(), tile2.getY(), true)) != null) {
                        if (b3 != 0) {
                            buildTowerIgnorePrice.upgradeToLevel(b3);
                        }
                        if (this.g == null) {
                            this.g = new DelayedRemovalArray<>(true, 2, Tower.class);
                        }
                        this.g.add(buildTowerIgnorePrice);
                        return false;
                    }
                    return true;
                });
            }
            return;
        }
        if (i == 3 && z) {
            this.e = this.moneySpentOn;
        }
    }

    private void b() {
        if (isAbilityInstalled(3)) {
            Array<Tower> towerArray = this.S.TSH.getTowerArray();
            Map map = this.S.map.getMap();
            int x = getTile().getX();
            int y = getTile().getY();
            for (int i = -1; i <= 1; i++) {
                for (int i2 = -1; i2 <= 1; i2++) {
                    if (i != 0 || i2 != 0) {
                        Tile tile = map.getTile(x + i, y + i2);
                        if (tile instanceof PlatformTile) {
                            PlatformTile platformTile = (PlatformTile) tile;
                            if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER) {
                                Tower tower = (Tower) platformTile.building;
                                if (tower.type != TowerType.BASIC) {
                                    towerArray.add(tower);
                                }
                            }
                        }
                    }
                }
            }
            if (towerArray.size != 0) {
                float powerCombinedMultiplier = ((getPowerCombinedMultiplier() - 1.0f) / towerArray.size) * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BASIC_A_SPECIAL_PWR_SHARE));
                for (int i3 = 0; i3 < towerArray.size; i3++) {
                    Tower tower2 = towerArray.items[i3];
                    if (tower2.powerBonuses == null) {
                        tower2.powerBonuses = new PowerBonuses();
                    }
                    if (tower2.powerBonuses.addOrReplaceBonus(this.id, 0, powerCombinedMultiplier)) {
                        this.S.map.markTilesDirtyNearTile(tower2.getTile(), 1);
                    }
                }
            }
            this.S.TSH.freeTowerArray(towerArray);
        }
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
    public final void attack(int i) {
        if (getTarget() == null) {
            return;
        }
        Vector2 vector2 = new Vector2();
        PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, this.angle, 28.5f, vector2);
        if (isAbilityInstalled(0)) {
            if (this.f) {
                PMath.getPointByAngleFromPoint(vector2.x, vector2.y, this.angle - 90.0f, 7.0f, vector2);
            } else {
                PMath.getPointByAngleFromPoint(vector2.x, vector2.y, this.angle + 90.0f, 7.0f, vector2);
            }
            this.f = !this.f;
        }
        BasicProjectile obtain = this.S.projectile.F.BASIC.obtain();
        this.S.projectile.register(obtain);
        obtain.setup(this, getTarget(), getStat(TowerStatType.DAMAGE) * i, vector2, getStat(TowerStatType.PROJECTILE_SPEED));
        if (this.S._particle != null) {
            this.S._particle.addFlashParticle(AssetManager.TextureRegions.i().muzzleFlashSmall, vector2.x, vector2.y, 10.4f, 3.8999999f, 20.8f, 31.199999f, this.angle);
        }
        this.shotCount += i;
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_BASIC, this);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        switch (towerStatType) {
            case DAMAGE:
                calculateStat *= calculateStat(TowerStatType.U_DAMAGE_MULTIPLY) * 0.01f;
                if (isAbilityInstalled(1)) {
                    calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BASIC_A_LARGE_CALIBER_DAMAGE));
                    break;
                }
                break;
            case ATTACK_SPEED:
                if (isAbilityInstalled(0)) {
                    calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BASIC_A_DOUBLE_GUN_ATTACK_SPEED));
                    break;
                }
                break;
            case PROJECTILE_SPEED:
            case ROTATION_SPEED:
                if (isAbilityInstalled(2)) {
                    calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BASIC_A_FOUNDATION_SPEED));
                    break;
                }
                break;
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower
    public final void onPreSell() {
        if (isAbilityInstalled(3)) {
            Array<Tower> towerArray = this.S.TSH.getTowerArray();
            Map map = this.S.map.getMap();
            int x = getTile().getX();
            int y = getTile().getY();
            for (int i = -1; i <= 1; i++) {
                for (int i2 = -1; i2 <= 1; i2++) {
                    if (i != 0 || i2 != 0) {
                        Tile tile = map.getTile(x + i, y + i2);
                        if (tile instanceof PlatformTile) {
                            PlatformTile platformTile = (PlatformTile) tile;
                            if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER) {
                                Tower tower = (Tower) platformTile.building;
                                if (tower.type != TowerType.BASIC) {
                                    towerArray.add(tower);
                                }
                            }
                        }
                    }
                }
            }
            if (towerArray.size != 0) {
                for (int i3 = 0; i3 < towerArray.size; i3++) {
                    Tower tower2 = towerArray.items[i3];
                    if (tower2.powerBonuses != null) {
                        tower2.powerBonuses.removeBonus(this.id);
                    }
                }
            }
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        if (isOutOfOrder()) {
            super.update(f);
            return;
        }
        a(f, getStat(TowerStatType.ROTATION_SPEED));
        this.h = (byte) (this.h + 1);
        if (this.h == 5) {
            float f2 = 5.0f * f;
            this.h = (byte) 0;
            if (this.g != null && this.g.size != 0 && getLevel() >= getMaxTowerLevel()) {
                double d = this.experience - Tower.LEVEL_EXPERIENCE_MILESTONES[getLevel()];
                if (d >= 1.0d) {
                    int a2 = a();
                    if (this.g != null) {
                        float min = (float) Math.min(d - 9.999999747378752E-5d, f2 * 1000.0d);
                        if (a2 == 0) {
                            b(min / this.g.size);
                        } else {
                            a(min / a2);
                        }
                        setExperience(this.experience - min);
                    }
                }
            }
            b();
        }
        super.update(f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/BasicTower$BasicTowerFactory.class */
    public static class BasicTowerFactory extends Tower.Factory<BasicTower> {
        public BasicTowerFactory() {
            super("tower-basic", TowerType.BASIC);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{""};
            this.f1784b[1].descriptionArgs = new String[]{""};
            this.f1784b[2].descriptionArgs = new String[]{"", ""};
            this.f1784b[3].descriptionArgs = new String[]{""};
            this.f1784b[4].descriptionArgs = new String[]{"", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return BasicTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BASIC_A_DOUBLE_GUN_ATTACK_SPEED), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BASIC_A_LARGE_CALIBER_DAMAGE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BASIC_A_FOUNDATION_SPEED), 2, true).toString();
            float floatValue = gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_BASIC_A_FOUNDATION_RICOCHET_CHANCE);
            float f = floatValue;
            if (floatValue > 99.0f) {
                f = 99.0f;
            }
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(f, 2, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_BASIC_A_COPY_COUNT), false).toString();
            abilityConfigs[4].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_BASIC_A_COPY_UPGRADE_LEVEL), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_BASIC_A_SPECIAL_PWR_SHARE), 1, true).toString();
            return abilityConfigs;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public boolean shouldDrawAbilityToCache(int i) {
            if (i == 0) {
                return false;
            }
            return true;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.TEAL.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 3;
                case ATTACK_SPEED:
                    return 4;
                case DAMAGE:
                    return 3;
                case CROWD_DAMAGE:
                    return 1;
                case AGILITY:
                    return 4;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 8;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public BasicTower create() {
            return new BasicTower((byte) 0);
        }
    }
}
