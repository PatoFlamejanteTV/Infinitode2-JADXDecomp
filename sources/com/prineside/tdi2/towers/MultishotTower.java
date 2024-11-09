package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.projectiles.MultishotProjectile;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.shapes.PieChart;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PieChartActor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/MultishotTower.class */
public final class MultishotTower extends Tower {
    public static final int ABILITY_PENETRATING_BULLETS = 0;
    public static final int ABILITY_BUCKSHOT = 1;
    public static final int ABILITY_COMPACT_WEAPONS = 2;
    public static final String[] ABILITY_ALIASES = {"PENETRATING_BULLETS", "BUCKSHOT", "COMPACT_WEAPONS"};
    public int notHitBackProjectileCount;

    /* synthetic */ MultishotTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.notHitBackProjectileCount, true);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.notHitBackProjectileCount = input.readVarInt(true);
    }

    private MultishotTower() {
        super(TowerType.MULTISHOT);
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            return Game.i.towerManager.F.MULTISHOT.getAbilityTextures(0);
        }
        return Game.i.towerManager.F.MULTISHOT.getWeaponTexture();
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
    public final boolean canAttack() {
        if (getTarget() != null && !this.attackDisabled) {
            Vector2 vector2 = new Vector2();
            vector2.set(getTarget().getPosition());
            return StrictMath.abs(PMath.getDistanceBetweenAngles(this.angle, PMath.getAngleBetweenPoints(getTile().center.x, getTile().center.y, vector2.x, vector2.y))) < 3.0f + (getStat(TowerStatType.U_SHOOT_ANGLE) / 2.0f);
        }
        return false;
    }

    @Override // com.prineside.tdi2.Tower
    public final void attack(int i) {
        if (getTarget() == null) {
            return;
        }
        float stat = getStat(TowerStatType.U_PROJECTILE_COUNT);
        float stat2 = getStat(TowerStatType.U_SHOOT_ANGLE);
        float stat3 = getStat(TowerStatType.DAMAGE);
        float stat4 = getStat(TowerStatType.PROJECTILE_SPEED);
        int i2 = (int) stat;
        if (stat % 1.0f != 0.0f && this.S.gameState.randomFloat() < stat % 1.0f) {
            i2++;
        }
        float f = stat2 / stat;
        float f2 = this.angle - (stat2 / 2.0f);
        for (int i3 = 0; i3 < i2; i3++) {
            Vector2 vector2 = new Vector2();
            PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, f2, 12.0f, vector2);
            Vector2 vector22 = new Vector2();
            PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, f2, this.rangeInPixels, vector22);
            MultishotProjectile obtain = this.S.projectile.F.MULTISHOT.obtain();
            this.S.projectile.register(obtain);
            obtain.setup(this, stat3 * i, vector2, vector22, stat4, isAbilityInstalled(0), isAbilityInstalled(3), 1.0f);
            f2 += f;
        }
        if (isAbilityInstalled(4)) {
            float percentValueAsMultiplier = stat3 * i * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MULTISHOT_A_BACK_SHOT_DAMAGE)) * (this.notHitBackProjectileCount + 1);
            Vector2 vector23 = new Vector2();
            PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, this.angle + 180.0f, 12.0f, vector23);
            Vector2 vector24 = new Vector2();
            PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, this.angle + 180.0f, this.rangeInPixels, vector24);
            MultishotProjectile obtain2 = this.S.projectile.F.MULTISHOT.obtain();
            this.S.projectile.register(obtain2);
            obtain2.setup(this, percentValueAsMultiplier, vector23, vector24, stat4, isAbilityInstalled(0), isAbilityInstalled(3), 1.5f);
            obtain2.flyingBack = true;
        }
        this.shotCount += i;
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_MULTISHOT, this);
        }
    }

    public final float getBuckshotCoinBonusMult() {
        return ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MULTISHOT_A_BUCKSHOT_COINS)) * (0.2f + (0.8f * (getUpgradeLevel() / 10.0f)));
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.U_SHOOT_ANGLE && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MULTISHOT_A_COMPACT_ARC_SIZE));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower
    public final void fillTowerMenu(Group group, ObjectMap<String, Object> objectMap) {
        int i = 1;
        if (isAbilityInstalled(4)) {
            i = 32;
        }
        if (objectMap.size == 0 || !objectMap.get("state", -1).equals(Integer.valueOf(i))) {
            group.clear();
            if (isAbilityInstalled(4)) {
                int scaledViewportHeight = (Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT) + 8;
                PieChartActor pieChartActor = new PieChartActor();
                Array<PieChart.ChartEntryConfig> array = new Array<>(PieChart.ChartEntryConfig.class);
                array.add(new PieChart.ChartEntryConfig(MaterialColor.AMBER.P500, 20.0f, 0.0f));
                array.add(new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.28f), 30.0f, 0.0f));
                pieChartActor.setPosition(310.0f, scaledViewportHeight + 8);
                pieChartActor.setSize(48.0f, 48.0f);
                pieChartActor.setSegmentCount(150);
                pieChartActor.setConfigs(array);
                group.addActor(pieChartActor);
                Actor image = new Image(Game.i.assetManager.getDrawable("circle"));
                image.setColor(new Color(724249599));
                image.setSize(28.0f, 28.0f);
                image.setPosition(320.0f, 17.0f + scaledViewportHeight);
                group.addActor(image);
                Actor image2 = new Image(Game.i.assetManager.getDrawable("icon-projectile-count"));
                image2.setSize(22.0f, 22.0f);
                image2.setPosition(323.0f, 19.0f + scaledViewportHeight);
                group.addActor(image2);
                Actor label = new Label("", Game.i.assetManager.getLabelStyle(21));
                label.setColor(MaterialColor.ORANGE.P500);
                label.setPosition(372.0f, 37.0f + scaledViewportHeight);
                label.setSize(100.0f, 14.0f);
                group.addActor(label);
                Actor label2 = new Label(Game.i.localeManager.i18n.get("back_shot_damage_multiplier"), Game.i.assetManager.getLabelStyle(18));
                label2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.56f));
                label2.setPosition(372.0f, 13.0f + scaledViewportHeight);
                label2.setSize(100.0f, 13.0f);
                group.addActor(label2);
                objectMap.put("backShotStackChart", pieChartActor);
                objectMap.put("projStackTitle", label);
            }
            objectMap.put("state", Integer.valueOf(i));
        }
        if (isAbilityInstalled(4)) {
            Label label3 = (Label) objectMap.get("projStackTitle");
            PieChartActor pieChartActor2 = (PieChartActor) objectMap.get("backShotStackChart");
            Array<PieChart.ChartEntryConfig> configs = pieChartActor2.getConfigs();
            configs.get(0).setValue(this.notHitBackProjectileCount / this.S.gameValue.getFloatValue(GameValueType.TOWER_MULTISHOT_A_BACK_MAX_STACK));
            configs.get(1).setValue(1.0f - configs.get(0).getValue());
            pieChartActor2.setConfigs(configs);
            d.setLength(0);
            d.append(Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-damage>"));
            d.append(MathUtils.round(((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MULTISHOT_A_BACK_SHOT_DAMAGE)) * getStat(TowerStatType.DAMAGE) * (this.notHitBackProjectileCount + 1)));
            d.append(" (x").append(this.notHitBackProjectileCount + 1).append(")");
            label3.setText(d);
        }
        super.fillTowerMenu(group, objectMap);
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        if (isOutOfOrder()) {
            super.update(f);
        } else {
            a(f, getStat(TowerStatType.ROTATION_SPEED));
            super.update(f);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/MultishotTower$MultishotTowerFactory.class */
    public static class MultishotTowerFactory extends Tower.Factory<MultishotTower> {
        public MultishotTowerFactory() {
            super("tower-multishot", TowerType.MULTISHOT);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{""};
            this.f1784b[2].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[1].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[3].descriptionArgs = new String[]{""};
            this.f1784b[4].descriptionArgs = new String[]{"", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MULTISHOT_A_PENETRATING_DAMAGE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MULTISHOT_A_COMPACT_ARC_SIZE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MULTISHOT_A_COMPACT_DAMAGE_PER_HIT), 2, true).toString();
            abilityConfigs[2].descriptionArgs[2] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_MULTISHOT_A_COMPACT_MAX_HIT_COUNT), false).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MULTISHOT_A_BUCKSHOT_DAMAGE) - 100.0f, 1, true).toString();
            abilityConfigs[1].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(((MultishotTower) tower).getBuckshotCoinBonusMult() * 100.0f, 1, true).toString();
            abilityConfigs[1].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MULTISHOT_A_BUCKSHOT_COINS_DURATION), 1, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MULTISHOT_A_COUNTER_DAMAGE), 2, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MULTISHOT_A_BACK_SHOT_DAMAGE), 2, true).toString();
            abilityConfigs[4].descriptionArgs[1] = String.valueOf(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_MULTISHOT_A_BACK_MAX_STACK));
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
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 4;
                case ATTACK_SPEED:
                    return 3;
                case DAMAGE:
                    return 2;
                case CROWD_DAMAGE:
                    return 4;
                case AGILITY:
                    return 3;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 34;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return MultishotTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.YELLOW.P500;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public MultishotTower create() {
            return new MultishotTower((byte) 0);
        }
    }
}
