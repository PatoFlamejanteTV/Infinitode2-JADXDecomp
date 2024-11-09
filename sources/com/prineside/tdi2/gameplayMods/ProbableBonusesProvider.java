package com.prineside.tdi2.gameplayMods;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.systems.BonusSystem;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/ProbableBonusesProvider.class */
public interface ProbableBonusesProvider {
    public static final TLog logger = TLog.forClass(ProbableBonusesProvider.class);
    public static final String _TAG = "ProbableBonusesProvider";

    void provide(int i, BonusStagesConfig bonusStagesConfig, Array<GameplayModSystem.ActiveMod> array, Array<ProbableBonus> array2);

    @Null
    static <T extends GenericGameplayMod> ProbableBonus addOrModify(T t, int i, Array<GameplayModSystem.ActiveMod> array, BonusProviderConfig bonusProviderConfig) {
        if (i < bonusProviderConfig.getMinStage()) {
            return null;
        }
        if (bonusProviderConfig.maxStage > 0 && i > bonusProviderConfig.maxStage) {
            return null;
        }
        float pow = (float) (bonusProviderConfig.probability * (bonusProviderConfig.probabilityMultiplierPerStage <= 0.0f ? 1.0d : Math.pow(bonusProviderConfig.probabilityMultiplierPerStage, i - bonusProviderConfig.getMinStage())));
        if (pow <= 0.0f) {
            return null;
        }
        GenericGameplayMod genericGameplayMod = null;
        int i2 = 0;
        while (true) {
            if (i2 >= array.size) {
                break;
            }
            GameplayModSystem.ActiveMod activeMod = array.items[i2];
            if (!activeMod.getMod().getId().equals(t.getId()) || !activeMod.getSource().equals(BonusSystem.GAMEPLAY_MOD_SOURCE_NAME)) {
                i2++;
            } else {
                genericGameplayMod = (GenericGameplayMod) activeMod.getMod();
                break;
            }
        }
        if (genericGameplayMod != null) {
            int min = Math.min(genericGameplayMod.getMaxPower(), genericGameplayMod.getPower() + 1);
            if (min <= genericGameplayMod.getPower()) {
                logger.d("won't increase power (proto: " + t + ", was " + genericGameplayMod.getPower() + ", max " + genericGameplayMod.getMaxPower() + ", new " + min + "), skipping", new Object[0]);
                return null;
            }
            logger.d("found existing for proto (proto: " + t + ", existing: " + genericGameplayMod + ")", new Object[0]);
            GenericGameplayMod genericGameplayMod2 = (GenericGameplayMod) t.cpy();
            float pow2 = bonusProviderConfig.powerUpProbabilityMultiplier == 0.0f ? 1.0f : (float) Math.pow(bonusProviderConfig.powerUpProbabilityMultiplier, genericGameplayMod2.power);
            genericGameplayMod2.power = min;
            int i3 = (int) (100000.0f * pow * pow2);
            if (i3 > 0) {
                return new ProbableBonus(genericGameplayMod2, i3);
            }
            return null;
        }
        GenericGameplayMod genericGameplayMod3 = (GenericGameplayMod) t.cpy();
        if (!genericGameplayMod3.allowsMultipleInstancesFromDifferentSources()) {
            for (int i4 = 0; i4 < array.size; i4++) {
                GameplayModSystem.ActiveMod activeMod2 = array.items[i4];
                if (!activeMod2.getSource().equals(BonusSystem.GAMEPLAY_MOD_SOURCE_NAME) && t.getId().equals(activeMod2.getMod().getId())) {
                    return null;
                }
            }
        }
        int i5 = (int) (100000.0f * pow);
        if (i5 <= 0) {
            return null;
        }
        return new ProbableBonus(genericGameplayMod3, i5);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/ProbableBonusesProvider$BonusProviderConfig.class */
    public static class BonusProviderConfig {
        public float probability;
        public float powerUpProbabilityMultiplier;
        public int minStage;
        public int maxStage;
        public float probabilityMultiplierPerStage;

        public BonusProviderConfig() {
            this.probability = 1.0f;
        }

        public BonusProviderConfig(float f) {
            this.probability = 1.0f;
            this.probability = f;
        }

        public int getMinStage() {
            return Math.max(this.minStage, 1);
        }

        public BonusProviderConfig setProbability(float f) {
            this.probability = f;
            return this;
        }

        public BonusProviderConfig setPowerUpProbabilityMultiplier(float f) {
            this.powerUpProbabilityMultiplier = f;
            return this;
        }

        public BonusProviderConfig setProbabilityMultiplierPerStage(float f) {
            this.probabilityMultiplierPerStage = f;
            return this;
        }

        public BonusProviderConfig setMinStage(int i) {
            this.minStage = i;
            return this;
        }

        public BonusProviderConfig setMaxStage(int i) {
            this.maxStage = i;
            return this;
        }

        public BonusProviderConfig(float f, float f2, int i, int i2, float f3) {
            this.probability = 1.0f;
            this.probability = f;
            this.powerUpProbabilityMultiplier = f2;
            this.minStage = i;
            this.maxStage = i2;
            this.probabilityMultiplierPerStage = f3;
        }

        public BonusProviderConfig applyConfig(JsonValue jsonValue) {
            this.probability = jsonValue.getFloat("probability", this.probability);
            this.powerUpProbabilityMultiplier = jsonValue.getFloat("powerUpProbabilityMultiplier", this.powerUpProbabilityMultiplier);
            this.minStage = jsonValue.getInt("minStage", this.minStage);
            this.maxStage = jsonValue.getInt("maxStage", this.maxStage);
            this.probabilityMultiplierPerStage = jsonValue.getFloat("probabilityMultiplierPerStage", this.probabilityMultiplierPerStage);
            return this;
        }

        public BonusProviderConfig cpy() {
            return new BonusProviderConfig(this.probability, this.powerUpProbabilityMultiplier, this.minStage, this.maxStage, this.probabilityMultiplierPerStage);
        }
    }
}
