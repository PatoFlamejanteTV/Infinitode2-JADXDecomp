package com.prineside.tdi2;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.waves.templates.ArmoredLow;
import com.prineside.tdi2.waves.templates.ArmoredRegular;
import com.prineside.tdi2.waves.templates.ArmoredStrong;
import com.prineside.tdi2.waves.templates.FastHigh;
import com.prineside.tdi2.waves.templates.FastLow;
import com.prineside.tdi2.waves.templates.FastMedium;
import com.prineside.tdi2.waves.templates.FighterArmored;
import com.prineside.tdi2.waves.templates.FighterLow;
import com.prineside.tdi2.waves.templates.FighterMedium;
import com.prineside.tdi2.waves.templates.HealerArmored;
import com.prineside.tdi2.waves.templates.HealerIcy;
import com.prineside.tdi2.waves.templates.HealerJet;
import com.prineside.tdi2.waves.templates.HealerRegular;
import com.prineside.tdi2.waves.templates.HealerSlow;
import com.prineside.tdi2.waves.templates.HealerStrong;
import com.prineside.tdi2.waves.templates.HeliMedium;
import com.prineside.tdi2.waves.templates.IcyHigh;
import com.prineside.tdi2.waves.templates.IcyToxic;
import com.prineside.tdi2.waves.templates.JetMedium;
import com.prineside.tdi2.waves.templates.LightFast;
import com.prineside.tdi2.waves.templates.LightHigh;
import com.prineside.tdi2.waves.templates.LightMedium;
import com.prineside.tdi2.waves.templates.RegularHigh;
import com.prineside.tdi2.waves.templates.RegularLow;
import com.prineside.tdi2.waves.templates.RegularMedium;
import com.prineside.tdi2.waves.templates.StrongLow;
import com.prineside.tdi2.waves.templates.StrongMedium;
import com.prineside.tdi2.waves.templates.ToxicArmored;
import com.prineside.tdi2.waves.templates.ToxicHigh;
import com.prineside.tdi2.waves.templates.ToxicMedium;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/WaveTemplates.class */
public class WaveTemplates {
    public static final RegularMedium REGULAR_MEDIUM = new RegularMedium();
    public static final RegularLow REGULAR_LOW = new RegularLow();
    public static final RegularHigh REGULAR_HIGH = new RegularHigh();
    public static final StrongMedium STRONG_MEDIUM = new StrongMedium();
    public static final StrongLow STRONG_LOW = new StrongLow();
    public static final FastMedium FAST_MEDIUM = new FastMedium();
    public static final FastHigh FAST_HIGH = new FastHigh();
    public static final FastLow FAST_LOW = new FastLow();
    public static final HeliMedium HELI_MEDIUM = new HeliMedium();
    public static final JetMedium JET_MEDIUM = new JetMedium();
    public static final ArmoredLow ARMORED_LOW = new ArmoredLow();
    public static final ArmoredRegular ARMORED_REGULAR = new ArmoredRegular();
    public static final ArmoredStrong ARMORED_STRONG = new ArmoredStrong();
    public static final HealerRegular HEALER_REGULAR = new HealerRegular();
    public static final HealerSlow HEALER_SLOW = new HealerSlow();
    public static final HealerStrong HEALER_STRONG = new HealerStrong();
    public static final HealerArmored HEALER_ARMORED = new HealerArmored();
    public static final HealerJet HEALER_JET = new HealerJet();
    public static final ToxicMedium TOXIC_MEDIUM = new ToxicMedium();
    public static final ToxicHigh TOXIC_HIGH = new ToxicHigh();
    public static final ToxicArmored TOXIC_ARMORED = new ToxicArmored();
    public static final IcyHigh ICY_HIGH = new IcyHigh();
    public static final IcyToxic ICY_TOXIC = new IcyToxic();
    public static final HealerIcy HEALER_ICY = new HealerIcy();
    public static final FighterLow FIGHTER_LOW = new FighterLow();
    public static final FighterMedium FIGHTER_MEDIUM = new FighterMedium();
    public static final FighterArmored FIGHTER_ARMORED = new FighterArmored();
    public static final LightMedium LIGHT_MEDIUM = new LightMedium();
    public static final LightHigh LIGHT_HIGH = new LightHigh();
    public static final LightFast LIGHT_FAST = new LightFast();
    public static final WaveTemplate[] WAVE_TEMPLATES = {REGULAR_MEDIUM, REGULAR_LOW, REGULAR_HIGH, STRONG_MEDIUM, STRONG_LOW, FAST_MEDIUM, FAST_HIGH, FAST_LOW, HELI_MEDIUM, JET_MEDIUM, ARMORED_LOW, ARMORED_REGULAR, ARMORED_STRONG, HEALER_REGULAR, HEALER_SLOW, HEALER_STRONG, HEALER_ARMORED, HEALER_JET, TOXIC_MEDIUM, TOXIC_HIGH, TOXIC_ARMORED, ICY_HIGH, ICY_TOXIC, HEALER_ICY, FIGHTER_LOW, FIGHTER_MEDIUM, FIGHTER_ARMORED, LIGHT_MEDIUM, LIGHT_HIGH, LIGHT_FAST};

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/WaveTemplates$EnemyGroupConfig.class */
    public interface EnemyGroupConfig {
        EnemyType getEnemyType();

        float getInterval(int i, float f, float f2);

        int getCount(int i, float f, float f2);

        float getHealth(int i, float f, float f2);

        float getBounty(int i, float f, float f2);

        float getDelay(int i, float f, float f2);

        float getSpeed(int i, float f, float f2);

        float getKillExp(int i, float f, float f2);

        int getKillScore(int i, float f, float f2);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/WaveTemplates$WaveTemplate.class */
    public static abstract class WaveTemplate {
        public abstract int getProbability(int i, float f, float f2);

        public abstract EnemyGroupConfig[] getEnemyGroupConfigs();

        public abstract String getWaveMessage();

        public abstract String getWaveName();
    }

    public static int calculateProbability(int i, float f, int i2, float f2, float f3, float f4, float f5, float f6, float f7, int i3) {
        if (f < i2) {
            return 1;
        }
        int abs = (int) ((f6 - ((int) (f * f7))) * ((1.0f - f3) + (StrictMath.abs(PMath.sin((i * f4) + f5)) * f3)) * Interpolation.pow2.apply(MathUtils.clamp((f - i2) * f2, 0.0f, 1.0f)));
        int i4 = abs;
        if (abs < i3 && f > 20.0f) {
            i4 = i3;
        }
        if (i4 <= 0) {
            i4 = 1;
        }
        return i4;
    }

    @Null
    public static WaveTemplate getByName(String str) {
        boolean z = -1;
        switch (str.hashCode()) {
            case -2107746985:
                if (str.equals("ICY_TOXIC")) {
                    z = 22;
                    break;
                }
                break;
            case -2073689062:
                if (str.equals("HELI_MEDIUM")) {
                    z = 8;
                    break;
                }
                break;
            case -1950139246:
                if (str.equals("FIGHTER_LOW")) {
                    z = 24;
                    break;
                }
                break;
            case -1506043995:
                if (str.equals("FAST_HIGH")) {
                    z = 6;
                    break;
                }
                break;
            case -828427880:
                if (str.equals("REGULAR_MEDIUM")) {
                    z = false;
                    break;
                }
                break;
            case -766453765:
                if (str.equals("JET_MEDIUM")) {
                    z = 9;
                    break;
                }
                break;
            case -744736554:
                if (str.equals("TOXIC_ARMORED")) {
                    z = 20;
                    break;
                }
                break;
            case -658711468:
                if (str.equals("HEALER_ARMORED")) {
                    z = 16;
                    break;
                }
                break;
            case -335212159:
                if (str.equals("HEALER_STRONG")) {
                    z = 15;
                    break;
                }
                break;
            case -56648308:
                if (str.equals("STRONG_LOW")) {
                    z = 4;
                    break;
                }
                break;
            case 65710072:
                if (str.equals("ARMORED_STRONG")) {
                    z = 12;
                    break;
                }
                break;
            case 111205468:
                if (str.equals("FIGHTER_ARMORED")) {
                    z = 26;
                    break;
                }
                break;
            case 129252203:
                if (str.equals("HEALER_SLOW")) {
                    z = 14;
                    break;
                }
                break;
            case 235065528:
                if (str.equals("FAST_MEDIUM")) {
                    z = 5;
                    break;
                }
                break;
            case 305657349:
                if (str.equals("LIGHT_FAST")) {
                    z = 29;
                    break;
                }
                break;
            case 305724235:
                if (str.equals("LIGHT_HIGH")) {
                    z = 28;
                    break;
                }
                break;
            case 307246493:
                if (str.equals("TOXIC_MEDIUM")) {
                    z = 18;
                    break;
                }
                break;
            case 331304477:
                if (str.equals("STRONG_MEDIUM")) {
                    z = 3;
                    break;
                }
                break;
            case 347286370:
                if (str.equals("ICY_HIGH")) {
                    z = 21;
                    break;
                }
                break;
            case 538037619:
                if (str.equals("ARMORED_LOW")) {
                    z = 10;
                    break;
                }
                break;
            case 710089275:
                if (str.equals("ARMORED_REGULAR")) {
                    z = 11;
                    break;
                }
                break;
            case 737604042:
                if (str.equals("TOXIC_HIGH")) {
                    z = 19;
                    break;
                }
                break;
            case 973990869:
                if (str.equals("HEALER_ICY")) {
                    z = 23;
                    break;
                }
                break;
            case 973991887:
                if (str.equals("HEALER_JET")) {
                    z = 17;
                    break;
                }
                break;
            case 1166402002:
                if (str.equals("HEALER_REGULAR")) {
                    z = 13;
                    break;
                }
                break;
            case 1304421361:
                if (str.equals("REGULAR_LOW")) {
                    z = true;
                    break;
                }
                break;
            case 1443236183:
                if (str.equals("FIGHTER_MEDIUM")) {
                    z = 25;
                    break;
                }
                break;
            case 1752537297:
                if (str.equals("FAST_LOW")) {
                    z = 7;
                    break;
                }
                break;
            case 1782231173:
                if (str.equals("REGULAR_HIGH")) {
                    z = 2;
                    break;
                }
                break;
            case 1882579678:
                if (str.equals("LIGHT_MEDIUM")) {
                    z = 27;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return REGULAR_MEDIUM;
            case true:
                return REGULAR_LOW;
            case true:
                return REGULAR_HIGH;
            case true:
                return STRONG_MEDIUM;
            case true:
                return STRONG_LOW;
            case true:
                return FAST_MEDIUM;
            case true:
                return FAST_HIGH;
            case true:
                return FAST_LOW;
            case true:
                return HELI_MEDIUM;
            case true:
                return JET_MEDIUM;
            case true:
                return ARMORED_LOW;
            case true:
                return ARMORED_REGULAR;
            case true:
                return ARMORED_STRONG;
            case true:
                return HEALER_REGULAR;
            case true:
                return HEALER_SLOW;
            case true:
                return HEALER_STRONG;
            case true:
                return HEALER_ARMORED;
            case true:
                return HEALER_JET;
            case true:
                return TOXIC_MEDIUM;
            case true:
                return TOXIC_HIGH;
            case true:
                return TOXIC_ARMORED;
            case true:
                return ICY_HIGH;
            case true:
                return ICY_TOXIC;
            case true:
                return HEALER_ICY;
            case true:
                return FIGHTER_LOW;
            case true:
                return FIGHTER_MEDIUM;
            case true:
                return FIGHTER_ARMORED;
            case true:
                return LIGHT_MEDIUM;
            case true:
                return LIGHT_HIGH;
            case true:
                return LIGHT_FAST;
            default:
                return null;
        }
    }

    @REGS(arrayLevels = 1)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/WaveTemplates$PredefinedWaveTemplate.class */
    public static class PredefinedWaveTemplate implements KryoSerializable {
        public String waveMessage;
        public EnemyGroup[] enemyGroups;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObjectOrNull(output, this.waveMessage, String.class);
            kryo.writeObjectOrNull(output, this.enemyGroups, EnemyGroup[].class);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.waveMessage = (String) kryo.readObjectOrNull(input, String.class);
            this.enemyGroups = (EnemyGroup[]) kryo.readObjectOrNull(input, EnemyGroup[].class);
        }

        public PredefinedWaveTemplate() {
        }

        public PredefinedWaveTemplate(EnemyGroup enemyGroup) {
            this.enemyGroups = new EnemyGroup[1];
            this.enemyGroups[0] = enemyGroup;
        }

        public PredefinedWaveTemplate(EnemyGroup enemyGroup, EnemyGroup enemyGroup2) {
            this.enemyGroups = new EnemyGroup[2];
            this.enemyGroups[0] = enemyGroup;
            this.enemyGroups[1] = enemyGroup2;
        }

        public PredefinedWaveTemplate(EnemyGroup enemyGroup, EnemyGroup enemyGroup2, EnemyGroup enemyGroup3) {
            this.enemyGroups = new EnemyGroup[3];
            this.enemyGroups[0] = enemyGroup;
            this.enemyGroups[1] = enemyGroup2;
            this.enemyGroups[2] = enemyGroup3;
        }

        public void toJson(Json json) {
            if (this.waveMessage != null) {
                json.writeValue("message", this.waveMessage);
            }
            json.writeArrayStart("enemyGroups");
            for (EnemyGroup enemyGroup : this.enemyGroups) {
                json.writeObjectStart();
                enemyGroup.toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }

        public static PredefinedWaveTemplate fromJson(JsonValue jsonValue) {
            PredefinedWaveTemplate predefinedWaveTemplate = new PredefinedWaveTemplate();
            predefinedWaveTemplate.waveMessage = jsonValue.getString("message", null);
            JsonValue jsonValue2 = jsonValue.get("enemyGroups");
            predefinedWaveTemplate.enemyGroups = new EnemyGroup[jsonValue2.size];
            int i = 0;
            Iterator<JsonValue> iterator2 = jsonValue2.iterator2();
            while (iterator2.hasNext()) {
                predefinedWaveTemplate.enemyGroups[i] = EnemyGroup.fromJson(iterator2.next());
                i++;
            }
            return predefinedWaveTemplate;
        }
    }
}
