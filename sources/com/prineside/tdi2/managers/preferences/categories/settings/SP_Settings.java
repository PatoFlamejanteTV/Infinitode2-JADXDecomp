package com.prineside.tdi2.managers.preferences.categories.settings;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/settings/SP_Settings.class */
public class SP_Settings implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2550a = TLog.forClass(SP_Settings.class);
    public static final double CUSTOM_VALUE_NOT_SET = -9740019.0d;

    /* renamed from: b, reason: collision with root package name */
    private static final double[] f2551b;
    public Array<AbilityType> lastSelectedAbilities = new Array<>(true, 1, AbilityType.class);
    public final ObjectSet<String> shownTooltipTags = new ObjectSet<>();
    public final double[] customValues = new double[SettingsManager.CustomValueType.values().length];
    public final int[][] hotKeys;

    /* JADX WARN: Type inference failed for: r1v8, types: [int[], int[][]] */
    public SP_Settings() {
        Arrays.fill(this.customValues, -9740019.0d);
        this.hotKeys = new int[SettingsManager.HotkeyAction.values.length];
    }

    static {
        double[] dArr = new double[SettingsManager.CustomValueType.values().length];
        f2551b = dArr;
        dArr[SettingsManager.CustomValueType.UI_SCALE.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.DBG_CONSOLE_LINE_COUNT.ordinal()] = 200.0d;
        f2551b[SettingsManager.CustomValueType.SEND_NOTIFICATIONS.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.MUSIC_CACHE_MAX_SIZE.ordinal()] = 100.0d;
        f2551b[SettingsManager.CustomValueType.ENABLE_REWARDING_ADS.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.ENABLE_PAUSE_AD_ICON.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.SOUND_VOLUME.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.MUSIC_VOLUME.ordinal()] = 0.7d;
        f2551b[SettingsManager.CustomValueType.SHOOTING_SOUNDS_VOLUME.ordinal()] = 0.5d;
        f2551b[SettingsManager.CustomValueType.LIVE_LEADERBOARDS.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.GRAPHICS_INTERPOLATION_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.CAMERA_SHAKE_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.SMOOTH_MUSIC.ordinal()] = 0.0d;
        f2551b[SettingsManager.CustomValueType.STATISTICS_CHART_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.SLOW_MOTION_PAUSE.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.BACKGROUND_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.LOOT_ICONS_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.STATE_AUTO_SAVE_INTERVAL.ordinal()] = 3.0d;
        f2551b[SettingsManager.CustomValueType.UI_QUEST_LIST_VISIBLE.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.UI_LIVE_LEADERBOARDS_VISIBLE.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.PP_GRAPHICS_SCALE.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.PP_EFFECTS_SCALE.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.DAMAGE_PARTICLES_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.MUSIC_SPECTRUM_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.INSTANT_HOLD_BUTTON_ON_RMB.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.DAMAGE_PARTICLES_MORE.ordinal()] = 0.0d;
        f2551b[SettingsManager.CustomValueType.PARTICLE_COUNT.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.SHOW_BONUS_SELECTION_IMMEDIATELY.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.THREE_DEE_MODELS_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.BUG_REPORTS_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.EXPLOSIONS_DRAWING.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.PROJECTILES_DRAWING.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.PROJECTILE_TRAILS_DRAWING.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.PARTICLES_DRAWING.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.UI_ANIMATIONS_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.FLYING_COINS_ENABLED.ordinal()] = 1.0d;
        f2551b[SettingsManager.CustomValueType.STAINS_ENABLED.ordinal()] = 1.0d;
    }

    public static double getDefaultCustomValue(SettingsManager.CustomValueType customValueType) {
        switch (customValueType) {
            case UI_SCALE:
                try {
                    if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                        if (Gdx.graphics.getHeight() >= 1440) {
                            return 0.8d;
                        }
                        if (Gdx.graphics.getHeight() >= 1080) {
                            return 0.9d;
                        }
                        return 1.0d;
                    }
                    return 1.0d;
                } catch (Exception unused) {
                    return 1.0d;
                }
            default:
                return f2551b[customValueType.ordinal()];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public void load(PrefMap prefMap) {
        Arrays.fill(this.customValues, -9740019.0d);
        ObjectPair[] objectPairArr = {new ObjectPair(SettingsManager.CustomValueType.BUG_REPORTS_ENABLED, "bugReportsEnabled"), new ObjectPair(SettingsManager.CustomValueType.EXPLOSIONS_DRAWING, "explosionsDrawing"), new ObjectPair(SettingsManager.CustomValueType.PROJECTILES_DRAWING, "projectilesDrawing"), new ObjectPair(SettingsManager.CustomValueType.PROJECTILE_TRAILS_DRAWING, "projectileTrailsDrawing"), new ObjectPair(SettingsManager.CustomValueType.PARTICLES_DRAWING, "particlesDrawing"), new ObjectPair(SettingsManager.CustomValueType.UI_ANIMATIONS_ENABLED, "uiAnimationsEnabled"), new ObjectPair(SettingsManager.CustomValueType.FLYING_COINS_ENABLED, "flyingCoinsEnabled"), new ObjectPair(SettingsManager.CustomValueType.INSTANT_AUTO_WAVE_CALL, "instantAutoWaveCall"), new ObjectPair(SettingsManager.CustomValueType.STAINS_ENABLED, "stainsEnabled"), new ObjectPair(SettingsManager.CustomValueType.LARGE_FONTS_ENABLED, "largeFonts"), new ObjectPair(SettingsManager.CustomValueType.DEBUG_MODE, "debugMode"), new ObjectPair(SettingsManager.CustomValueType.THREE_DEE_MODELS_ENABLED, "threeDeeModelsEnabled"), new ObjectPair(SettingsManager.CustomValueType.DEBUG_DETAILED_MODE, "debugDetailedMode")};
        for (int i = 0; i < 13; i++) {
            ObjectPair objectPair = objectPairArr[i];
            String str = prefMap.get((String) objectPair.second, null);
            if (str != null) {
                this.customValues[((SettingsManager.CustomValueType) objectPair.first).ordinal()] = str.equals("true") ? 1.0d : 0.0d;
                prefMap.set((String) objectPair.second, null);
            }
        }
        String str2 = prefMap.get("customValues", null);
        if (str2 != null) {
            Iterator<JsonValue> iterator2 = new JsonReader().parse(str2).iterator2();
            while (iterator2.hasNext()) {
                JsonValue next = iterator2.next();
                try {
                    this.customValues[SettingsManager.CustomValueType.valueOf(next.name).ordinal()] = next.asDouble();
                } catch (Exception unused) {
                    f2550a.i("failed to load custom value " + next.name, new Object[0]);
                }
            }
        }
        String str3 = prefMap.get("hotkeys", null);
        if (str3 != null) {
            try {
                Iterator<JsonValue> iterator22 = new JsonReader().parse(str3).iterator2();
                while (iterator22.hasNext()) {
                    JsonValue next2 = iterator22.next();
                    try {
                        this.hotKeys[SettingsManager.HotkeyAction.valueOf(next2.name).ordinal()] = next2.asIntArray();
                    } catch (Exception e) {
                        f2550a.e("failed to load hotkey " + next2.name, e);
                    }
                }
            } catch (Exception e2) {
                f2550a.e("failed to load hotkeys", e2);
            }
        }
        this.lastSelectedAbilities.clear();
        String str4 = prefMap.get("lastAbilitiesConfiguration", null);
        if (str4 != null) {
            try {
                Iterator<JsonValue> iterator23 = new JsonReader().parse(str4).iterator2();
                while (iterator23.hasNext()) {
                    JsonValue next3 = iterator23.next();
                    if (next3.type() == JsonValue.ValueType.stringValue) {
                        try {
                            this.lastSelectedAbilities.add(AbilityType.valueOf(next3.asString()));
                        } catch (Exception unused2) {
                        }
                    } else {
                        this.lastSelectedAbilities.add(null);
                    }
                }
            } catch (Exception e3) {
                f2550a.e("failed to load previous abilities configuration", e3);
            }
        }
        this.shownTooltipTags.clear();
        String str5 = prefMap.get("TooltipsOverlay.shownTags", null);
        if (str5 != null) {
            try {
                for (String str6 : str5.split("\\|")) {
                    if (str6.length() != 0) {
                        this.shownTooltipTags.add(str6);
                    }
                }
            } catch (Exception e4) {
                f2550a.e("failed to load shown tags", e4);
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public synchronized void save(PrefMap prefMap) {
        Json json = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        for (SettingsManager.CustomValueType customValueType : SettingsManager.CustomValueType.values) {
            if (this.customValues[customValueType.ordinal()] != -9740019.0d && this.customValues[customValueType.ordinal()] != getDefaultCustomValue(customValueType)) {
                json.writeValue(customValueType.name(), Double.valueOf(this.customValues[customValueType.ordinal()]));
            }
        }
        json.writeObjectEnd();
        prefMap.set("customValues", stringWriter.toString());
        Json json2 = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter2 = new StringWriter();
        json2.setWriter(stringWriter2);
        json2.writeObjectStart();
        for (SettingsManager.HotkeyAction hotkeyAction : SettingsManager.HotkeyAction.values) {
            if (this.hotKeys[hotkeyAction.ordinal()] != null) {
                json2.writeArrayStart(hotkeyAction.name());
                for (int i = 0; i < this.hotKeys[hotkeyAction.ordinal()].length; i++) {
                    json2.writeValue(Integer.valueOf(this.hotKeys[hotkeyAction.ordinal()][i]));
                }
                json2.writeArrayEnd();
            }
        }
        json2.writeObjectEnd();
        prefMap.set("hotkeys", stringWriter2.toString());
        prefMap.set("lastAbilitiesConfiguration", null);
        StringBuilder stringBuilder = new StringBuilder();
        ObjectSet.ObjectSetIterator<String> it = this.shownTooltipTags.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (stringBuilder.length != 0) {
                stringBuilder.append('|');
            }
            stringBuilder.append(next);
        }
        prefMap.set("TooltipsOverlay.shownTags", stringBuilder.length == 0 ? null : stringBuilder.toString());
    }
}
