package com.prineside.tdi2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/SpaceTileBonus.class */
public class SpaceTileBonus {
    public static final int MAX_LEVEL = 5;
    private static final SpaceTileBonusConfig[] c;

    /* renamed from: a, reason: collision with root package name */
    static final SpaceTileBonusType[] f1762a;

    /* renamed from: b, reason: collision with root package name */
    static final TowerStatType[] f1763b;
    private static final StringBuilder d;

    static {
        SpaceTileBonusConfig[] spaceTileBonusConfigArr = new SpaceTileBonusConfig[SpaceTileBonusType.values.length];
        c = spaceTileBonusConfigArr;
        spaceTileBonusConfigArr[SpaceTileBonusType.RANGE.ordinal()] = new SpaceTileBonusConfig(SpaceTileBonusType.RANGE, "icon-range", new float[]{1.1f, 1.2f, 1.3f, 1.4f, 1.5f}, MaterialColor.GREEN.P500, MaterialColor.GREEN.P300);
        c[SpaceTileBonusType.DAMAGE.ordinal()] = new SpaceTileBonusConfig(SpaceTileBonusType.DAMAGE, "icon-damage", new float[]{1.15f, 1.3f, 1.5f, 1.7f, 2.0f}, MaterialColor.RED.P500, MaterialColor.RED.P300);
        c[SpaceTileBonusType.ATTACK_SPEED.ordinal()] = new SpaceTileBonusConfig(SpaceTileBonusType.ATTACK_SPEED, "icon-attack-speed", new float[]{1.15f, 1.3f, 1.5f, 1.7f, 2.0f}, MaterialColor.ORANGE.P500, MaterialColor.ORANGE.P300);
        c[SpaceTileBonusType.ROTATION_SPEED.ordinal()] = new SpaceTileBonusConfig(SpaceTileBonusType.ROTATION_SPEED, "icon-rotation-speed", new float[]{1.3f, 1.6f, 2.0f, 2.4f, 3.0f}, MaterialColor.PURPLE.P500, MaterialColor.PURPLE.P300);
        c[SpaceTileBonusType.PROJECTILE_SPEED.ordinal()] = new SpaceTileBonusConfig(SpaceTileBonusType.PROJECTILE_SPEED, "icon-projectile-speed", new float[]{1.3f, 1.6f, 2.0f, 2.4f, 3.0f}, MaterialColor.DEEP_PURPLE.P500, MaterialColor.DEEP_PURPLE.P300);
        c[SpaceTileBonusType.BONUS_EXPERIENCE.ordinal()] = new SpaceTileBonusConfig(SpaceTileBonusType.BONUS_EXPERIENCE, "icon-experience-plus", new float[]{1.15f, 1.3f, 1.5f, 1.7f, 2.0f}, MaterialColor.CYAN.P500, MaterialColor.CYAN.P300);
        c[SpaceTileBonusType.BONUS_COINS.ordinal()] = new SpaceTileBonusConfig(SpaceTileBonusType.BONUS_COINS, "icon-coins", new float[]{1.1f, 1.2f, 1.3f, 1.4f, 1.5f}, MaterialColor.YELLOW.P500, MaterialColor.YELLOW.P300);
        c[SpaceTileBonusType.UPGRADE_DISCOUNT.ordinal()] = new SpaceTileBonusConfig(SpaceTileBonusType.UPGRADE_DISCOUNT, "icon-upgrade-money", new float[]{0.98f, 0.96f, 0.94f, 0.92f, 0.9f}, MaterialColor.TEAL.P500, MaterialColor.TEAL.P300);
        c[SpaceTileBonusType.SELL_REFUND.ordinal()] = new SpaceTileBonusConfig(SpaceTileBonusType.SELL_REFUND, "icon-sell-refund", new float[]{0.8f, 0.85f, 0.9f, 0.95f, 0.99f}, MaterialColor.INDIGO.P500, MaterialColor.INDIGO.P300);
        c[SpaceTileBonusType.PWR_MULTIPLIER.ordinal()] = new SpaceTileBonusConfig(SpaceTileBonusType.PWR_MULTIPLIER, "icon-power-plus", new float[]{1.05f, 1.1f, 1.15f, 1.2f, 1.25f}, MaterialColor.PINK.P500, MaterialColor.PINK.P300);
        SpaceTileBonusType[] spaceTileBonusTypeArr = new SpaceTileBonusType[TowerStatType.values.length];
        f1762a = spaceTileBonusTypeArr;
        spaceTileBonusTypeArr[TowerStatType.RANGE.ordinal()] = SpaceTileBonusType.RANGE;
        f1762a[TowerStatType.DAMAGE.ordinal()] = SpaceTileBonusType.DAMAGE;
        f1762a[TowerStatType.ATTACK_SPEED.ordinal()] = SpaceTileBonusType.ATTACK_SPEED;
        f1762a[TowerStatType.ROTATION_SPEED.ordinal()] = SpaceTileBonusType.ROTATION_SPEED;
        f1762a[TowerStatType.PROJECTILE_SPEED.ordinal()] = SpaceTileBonusType.PROJECTILE_SPEED;
        f1763b = new TowerStatType[TowerStatType.values.length];
        for (int i = 0; i < TowerStatType.values.length; i++) {
            if (f1762a[i] != null) {
                f1763b[f1762a[i].ordinal()] = TowerStatType.values[i];
            }
        }
        d = new StringBuilder();
    }

    public static float getEffect(SpaceTileBonusType spaceTileBonusType, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("level is 0 for " + spaceTileBonusType);
        }
        if (i <= 0 || i > 5) {
            throw new IllegalArgumentException("Max space tile bonus level is 5, " + i + " given");
        }
        return c[spaceTileBonusType.ordinal()].f1764a[i - 1];
    }

    public static Color getColor(SpaceTileBonusType spaceTileBonusType) {
        return c[spaceTileBonusType.ordinal()].d;
    }

    public static Color getBrightColor(SpaceTileBonusType spaceTileBonusType) {
        return c[spaceTileBonusType.ordinal()].e;
    }

    public static String getIconName(SpaceTileBonusType spaceTileBonusType) {
        return c[spaceTileBonusType.ordinal()].c;
    }

    public static StringBuilder getDetailedName(SpaceTileBonusType spaceTileBonusType, int i) {
        d.setLength(0);
        d.append(Game.i.localeManager.i18n.get(c[spaceTileBonusType.ordinal()].f1765b));
        if (i > 0) {
            d.append(SequenceUtils.SPACE).append(StringFormatter.romanNumber(i));
        }
        d.append(" (").append(MathUtils.round(getEffect(spaceTileBonusType, i) * 100.0f)).append("%)");
        return d;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/SpaceTileBonus$SpaceTileBonusConfig.class */
    public static class SpaceTileBonusConfig {

        /* renamed from: a, reason: collision with root package name */
        final float[] f1764a;

        /* renamed from: b, reason: collision with root package name */
        final String f1765b;
        final String c;
        final Color d;
        final Color e;

        SpaceTileBonusConfig(SpaceTileBonusType spaceTileBonusType, String str, float[] fArr, Color color, Color color2) {
            this.f1765b = "space_tile_bonus_" + spaceTileBonusType.name();
            this.c = str;
            this.f1764a = fArr;
            this.d = color;
            this.e = color2;
        }
    }
}
