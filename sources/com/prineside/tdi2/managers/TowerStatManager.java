package com.prineside.tdi2.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TowerStatManager.class */
public class TowerStatManager extends Manager.ManagerAdapter {

    /* renamed from: b, reason: collision with root package name */
    private final TowerStat[] f2480b = new TowerStat[TowerStatType.values.length];

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2479a = TLog.forClass(TowerStatManager.class);
    private static final StringBuilder c = new StringBuilder();
    private static final StringBuilder d = new StringBuilder();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TowerStatManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<TowerStatManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public TowerStatManager read() {
            return Game.i.towerStatManager;
        }
    }

    public TowerStatManager() {
        a(TowerStatType.RANGE, "icon-range", MaterialColor.GREEN.P500, "measure_units_tiles");
        a(TowerStatType.MIN_RANGE, "icon-range-minimum", MaterialColor.GREEN.P500, "measure_units_tiles").setVisible(false);
        a(TowerStatType.DAMAGE, "icon-damage", MaterialColor.RED.P500, null);
        a(TowerStatType.ATTACK_SPEED, "icon-attack-speed", MaterialColor.ORANGE.P500, "measure_units_units_per_second");
        a(TowerStatType.ROTATION_SPEED, "icon-rotation-speed", MaterialColor.PURPLE.P500, "measure_units_degrees_per_second");
        a(TowerStatType.PROJECTILE_SPEED, "icon-projectile-speed", MaterialColor.DEEP_PURPLE.P500, "measure_units_tiles_per_second");
        a(TowerStatType.AIM_SPEED, "icon-aim-time", MaterialColor.CYAN.P500, "measure_units_percent_per_second");
        a(TowerStatType.CHARGING_SPEED, "icon-aim-time", MaterialColor.CYAN.P500, "measure_units_percent_per_second");
        a(TowerStatType.FREEZE_PERCENT, "icon-freeze-percent", MaterialColor.LIGHT_BLUE.P500, "measure_units_percent");
        a(TowerStatType.FREEZE_SPEED, "icon-freeze-in-time", MaterialColor.INDIGO.P500, "measure_units_percent_per_second");
        a(TowerStatType.STUN_CHANCE, "icon-stun", MaterialColor.TEAL.P500, "measure_units_percent");
        a(TowerStatType.CHAIN_LIGHTNING_DAMAGE, "icon-lightning-damage", MaterialColor.DEEP_ORANGE.P500, "measure_units_percent");
        a(TowerStatType.RESOURCE_CONSUMPTION, "icon-cubes-stacked-flame", MaterialColor.RED.P500, null);
        a(TowerStatType.DURATION, "icon-clock", MaterialColor.PINK.P500, "measure_units_seconds");
        a(TowerStatType.PRICE, "icon-dollar", Color.WHITE, null);
        a(TowerStatType.STARTING_LEVEL, "icon-experience-bar", Color.WHITE, null);
        a(TowerStatType.STARTING_POWER, "icon-power", Color.WHITE, null);
        a(TowerStatType.MAX_EXP_LEVEL, "icon-experience-max", Color.WHITE, null);
        a(TowerStatType.MAX_UPGRADE_LEVEL, "icon-upgrade-max", Color.WHITE, null);
        a(TowerStatType.EXPERIENCE_MULTIPLIER, "icon-experience-plus", Color.WHITE, null);
        a(TowerStatType.EXPERIENCE_GENERATION, "icon-experience-generation", Color.WHITE, null);
        a(TowerStatType.UPGRADE_PRICE, "icon-upgrade-money", Color.WHITE, null);
        a(TowerStatType.U_PIERCING, "icon-piercing-projectile", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_DAMAGE_MULTIPLY, "icon-damage-multiplier", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_CRIT_CHANCE, "icon-critical-damage-percent", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_CRIT_MULTIPLIER, "icon-critical-damage", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_EXPLOSION_RANGE, "icon-explosion-range", MaterialColor.AMBER.P600, "measure_units_tiles");
        a(TowerStatType.U_POISON_DURATION_BONUS, "icon-skull-and-bones-clock-plus", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_CHAIN_LIGHTNING_BONUS_LENGTH, "icon-lightning-length", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_POISON_DURATION, "icon-skull-and-bones-clock", MaterialColor.AMBER.P600, "measure_units_seconds");
        a(TowerStatType.U_PROJECTILE_COUNT, "icon-projectile-count", MaterialColor.AMBER.P600, null);
        a(TowerStatType.U_STUN_DURATION, "icon-stun-clock", MaterialColor.AMBER.P600, "measure_units_seconds");
        a(TowerStatType.U_QUAKE_CHARGE_SPEED, "icon-quake", MaterialColor.AMBER.P600, "measure_units_percent_per_second");
        a(TowerStatType.U_BURN_CHANCE, "icon-flame-percent", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_BURN_DAMAGE, "icon-flame-damage", MaterialColor.AMBER.P600, "measure_units_percent_per_second");
        a(TowerStatType.U_ACCELERATION, "icon-speedometer-clock", MaterialColor.AMBER.P600, "measure_units_percent_per_second");
        a(TowerStatType.U_SHOOT_ANGLE, "icon-shot-angle", MaterialColor.AMBER.P600, "measure_units_degrees");
        a(TowerStatType.U_CHAIN_LIGHTNING_LENGTH, "icon-lightning-length", MaterialColor.AMBER.P600, null);
        a(TowerStatType.U_LRM_AIM_SPEED, "icon-rocket-aim-time", MaterialColor.AMBER.P600, "measure_units_percent_per_second");
        a(TowerStatType.U_BURNING_TIME, "icon-flame-clock", MaterialColor.AMBER.P600, "measure_units_seconds");
        a(TowerStatType.U_BATTERIES_CAPACITY, "icon-battery-max", MaterialColor.AMBER.P600, "measure_units_seconds");
        a(TowerStatType.U_BONUS_COINS, "icon-coin", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_BONUS_EXPERIENCE, "icon-experience-plus", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_SHARED_DAMAGE, "icon-damage-split", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_DIRECT_FIRE_DAMAGE, "icon-flame-damage", MaterialColor.AMBER.P600, "measure_units_percent");
        a(TowerStatType.U_MAGAZINE_SIZE, "icon-magazine-size", MaterialColor.AMBER.P600, null);
        for (TowerStatType towerStatType : TowerStatType.values) {
            if (this.f2480b[towerStatType.ordinal()] == null) {
                throw new RuntimeException("Tower stat type " + towerStatType.name() + " is not initialized");
            }
        }
    }

    private TowerStat a(TowerStatType towerStatType, String str, Color color, String str2) {
        TowerStat towerStat = new TowerStat();
        towerStat.f2483b = towerStatType;
        towerStat.f2482a = "tower_stat_" + towerStatType.name();
        TowerStat.b(towerStat, "tower_stat_short_" + towerStatType.name());
        towerStat.unitsAlias = str2;
        towerStat.d = str;
        towerStat.c = color;
        this.f2480b[towerStatType.ordinal()] = towerStat;
        return towerStat;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
        for (TowerStatType towerStatType : TowerStatType.values) {
            if (Game.i.assetManager.getTextureRegion(this.f2480b[towerStatType.ordinal()].getIconDrawableAlias()) == null) {
                f2479a.e("Icon texture region is null for stat type " + towerStatType.name(), new Object[0]);
            }
        }
    }

    public TowerStat getInstance(TowerStatType towerStatType) {
        return this.f2480b[towerStatType.ordinal()];
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TowerStatManager$TowerStat.class */
    public static class TowerStat {

        /* renamed from: a, reason: collision with root package name */
        private String f2482a;
        public String unitsAlias;

        /* renamed from: b, reason: collision with root package name */
        private TowerStatType f2483b;
        private Color c;
        private String d;
        private boolean e = true;

        static /* synthetic */ String b(TowerStat towerStat, String str) {
            return str;
        }

        public TowerStatType getType() {
            return this.f2483b;
        }

        public Color getColor() {
            return this.c;
        }

        public String getIconDrawableAlias() {
            return this.d;
        }

        public String getName() {
            return Game.i.localeManager.i18n.get(this.f2482a);
        }

        public boolean isVisible() {
            return this.e;
        }

        public TowerStat setVisible(boolean z) {
            this.e = z;
            return this;
        }

        public StringBuilder getFormattedValue(float f, boolean z) {
            TowerStatManager.c.setLength(0);
            TowerStatManager.c.append(StringFormatter.compactNumber(f, true));
            if (z) {
                switch (this.f2483b) {
                    case ATTACK_SPEED:
                    case ROTATION_SPEED:
                        TowerStatManager.c.append("/").append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
                        break;
                    case AIM_SPEED:
                    case U_ACCELERATION:
                    case U_LRM_AIM_SPEED:
                    case U_QUAKE_CHARGE_SPEED:
                    case U_BURN_DAMAGE:
                        TowerStatManager.c.append("%/").append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
                        break;
                    case FREEZE_SPEED:
                    case U_POISON_DURATION:
                    case U_STUN_DURATION:
                        TowerStatManager.c.append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
                        break;
                    case FREEZE_PERCENT:
                    case STUN_CHANCE:
                    case CHAIN_LIGHTNING_DAMAGE:
                    case U_CRIT_CHANCE:
                    case U_BURN_CHANCE:
                        TowerStatManager.c.append("%");
                        break;
                    case U_DAMAGE_MULTIPLY:
                    case U_CRIT_MULTIPLIER:
                        TowerStatManager.d.setLength(0);
                        TowerStatManager.d.append(TowerStatManager.c);
                        TowerStatManager.c.setLength(0);
                        TowerStatManager.c.append('x').append(TowerStatManager.d);
                        break;
                    case U_CHAIN_LIGHTNING_BONUS_LENGTH:
                        TowerStatManager.d.setLength(0);
                        TowerStatManager.d.append(TowerStatManager.c);
                        TowerStatManager.c.setLength(0);
                        TowerStatManager.c.append('+').append(TowerStatManager.d);
                        break;
                    case U_POISON_DURATION_BONUS:
                        TowerStatManager.d.setLength(0);
                        TowerStatManager.d.append(TowerStatManager.c);
                        TowerStatManager.c.setLength(0);
                        TowerStatManager.c.append('+').append(TowerStatManager.d).append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
                        break;
                }
            }
            return TowerStatManager.c;
        }
    }
}
