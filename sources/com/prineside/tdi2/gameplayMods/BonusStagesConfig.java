package com.prineside.tdi2.gameplayMods;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.gameplayMods.mods.AddAllAbilityCharges;
import com.prineside.tdi2.gameplayMods.mods.AddRandomCoreTile;
import com.prineside.tdi2.gameplayMods.mods.AddRandomPlatform;
import com.prineside.tdi2.gameplayMods.mods.AllAbilitiesForRandomTower;
import com.prineside.tdi2.gameplayMods.mods.BaseExplodesOnEnemyPass;
import com.prineside.tdi2.gameplayMods.mods.BoostExistingEnemiesWithLoot;
import com.prineside.tdi2.gameplayMods.mods.BuildRandomMiner;
import com.prineside.tdi2.gameplayMods.mods.CriticalDamage;
import com.prineside.tdi2.gameplayMods.mods.DebuffsLastLonger;
import com.prineside.tdi2.gameplayMods.mods.DepositCoinsGeneration;
import com.prineside.tdi2.gameplayMods.mods.DoubleMiningSpeed;
import com.prineside.tdi2.gameplayMods.mods.EnemiesDropResources;
import com.prineside.tdi2.gameplayMods.mods.ExtraDamagePerBuff;
import com.prineside.tdi2.gameplayMods.mods.FirstEnemiesInWaveExplode;
import com.prineside.tdi2.gameplayMods.mods.GV_AbilitiesEnergy;
import com.prineside.tdi2.gameplayMods.mods.GV_AbilitiesMaxEnergy;
import com.prineside.tdi2.gameplayMods.mods.GV_BountiesNearby;
import com.prineside.tdi2.gameplayMods.mods.GV_DisableBountyModifierHarm;
import com.prineside.tdi2.gameplayMods.mods.GV_MinersMaxUpgradeLevel;
import com.prineside.tdi2.gameplayMods.mods.GV_TowersMaxExpLevel;
import com.prineside.tdi2.gameplayMods.mods.IncreaseSelectedBonusesPower;
import com.prineside.tdi2.gameplayMods.mods.IncreasedTowerToEnemyEfficiency;
import com.prineside.tdi2.gameplayMods.mods.LastEnemiesInWaveDealNoDamage;
import com.prineside.tdi2.gameplayMods.mods.LightningStrikeOnTowerLevelUp;
import com.prineside.tdi2.gameplayMods.mods.LowHpEnemiesDealNoDamage;
import com.prineside.tdi2.gameplayMods.mods.MineLegendaryItems;
import com.prineside.tdi2.gameplayMods.mods.MinedItemsTurnIntoDust;
import com.prineside.tdi2.gameplayMods.mods.MinersSpawnEnemies;
import com.prineside.tdi2.gameplayMods.mods.MoreBonusVariantsNextTime;
import com.prineside.tdi2.gameplayMods.mods.MultiplyLootedItems;
import com.prineside.tdi2.gameplayMods.mods.MultiplyMdps;
import com.prineside.tdi2.gameplayMods.mods.NukeOnBonusStage;
import com.prineside.tdi2.gameplayMods.mods.ReceiveCoins;
import com.prineside.tdi2.gameplayMods.mods.SellAllTowers;
import com.prineside.tdi2.gameplayMods.mods.SpawnZombiesFromBase;
import com.prineside.tdi2.gameplayMods.mods.SummonLootBoss;
import com.prineside.tdi2.gameplayMods.mods.TowersAttackSpeed;
import com.prineside.tdi2.gameplayMods.mods.TowersDamage;
import com.prineside.tdi2.gameplayMods.mods.TriggerRandomAbility;
import com.prineside.tdi2.systems.BonusSystem;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.utils.JsonHandler;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.IOException;
import java.util.Iterator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/BonusStagesConfig.class */
public class BonusStagesConfig implements KryoSerializable {
    public static final int SEED_RANDOM = 0;
    public static final int SEED_TAKE_FROM_LEVEL = -1;
    public static final int SEED_TAKE_FROM_DAILY_QUEST = -2;
    public static final String DEFAULT_CONFIG_FILE_PATH = "res/default-bonus-stages-config.json";
    public static final String GENERIC_PROVIDER_CLASS_PATH = "com.prineside.tdi2.gameplayMods.mods.";
    public boolean reRollEnabled;
    public boolean forceImmediateSelection;
    public boolean replaceBonusesWithNotSatisfiedPreconditions;
    public int maxReRollsPerStage;
    public int maxReRollsAllTime;
    public boolean chainReRoll;
    public boolean ignoreImpossiblePreconditions;
    public boolean selectedBonusAffectsRandom;
    public int seed;
    public boolean isFillWithDefaultBonusProviders;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2080a = TLog.forClass(BonusStagesConfig.class);
    public static final ProbableBonusesProvider[] DEFAULT_BONUS_PROVIDERS = {DepositCoinsGeneration.BonusProvider.getInstance(), EnemiesDropResources.BonusProvider.getInstance(), ExtraDamagePerBuff.BonusProvider.getInstance(), GV_AbilitiesEnergy.BonusProvider.getInstance(), GV_BountiesNearby.BonusProvider.getInstance(), GV_DisableBountyModifierHarm.BonusProvider.getInstance(), GV_TowersMaxExpLevel.BonusProvider.getInstance(), GV_AbilitiesMaxEnergy.BonusProvider.getInstance(), GV_MinersMaxUpgradeLevel.BonusProvider.getInstance(), IncreasedTowerToEnemyEfficiency.BonusProvider.getInstance(), IncreaseSelectedBonusesPower.BonusProvider.getInstance(), LastEnemiesInWaveDealNoDamage.BonusProvider.getInstance(), LowHpEnemiesDealNoDamage.BonusProvider.getInstance(), MineLegendaryItems.BonusProvider.getInstance(), DoubleMiningSpeed.BonusProvider.getInstance(), AllAbilitiesForRandomTower.BonusProvider.getInstance(), DebuffsLastLonger.BonusProvider.getInstance(), ReceiveCoins.BonusProvider.getInstance(), BoostExistingEnemiesWithLoot.BonusProvider.getInstance(), FirstEnemiesInWaveExplode.BonusProvider.getInstance(), BaseExplodesOnEnemyPass.BonusProvider.getInstance(), MinersSpawnEnemies.BonusProvider.getInstance(), MinedItemsTurnIntoDust.BonusProvider.getInstance(), TriggerRandomAbility.BonusProvider.getInstance(), BuildRandomMiner.BonusProvider.getInstance(), MultiplyMdps.BonusProvider.getInstance(), AddRandomCoreTile.BonusProvider.getInstance(), AddAllAbilityCharges.BonusProvider.getInstance(), SummonLootBoss.BonusProvider.getInstance(), SpawnZombiesFromBase.BonusProvider.getInstance(), AddRandomPlatform.BonusProvider.getInstance(), MoreBonusVariantsNextTime.BonusProvider.getInstance(), CriticalDamage.BonusProvider.getInstance(), SellAllTowers.BonusProvider.getInstance(), NukeOnBonusStage.BonusProvider.getInstance(), TowersDamage.BonusProvider.getInstance(), TowersAttackSpeed.BonusProvider.getInstance(), MultiplyLootedItems.BonusProvider.getInstance(), LightningStrikeOnTowerLevelUp.BonusProvider.getInstance()};
    public float reRollPrice = 0.15f;
    public float reRollMinPrice = 0.05f;
    public float reRollMaxPrice = 0.15f;
    public float reRollPricePerStage = -0.005f;
    public float immediateBonusesChance = 1.0f;
    public float persistentBonusesChance = 1.0f;
    public int activeBonusesSlotLimit = 0;
    public IntArray stageRequirements = new IntArray(true, new int[]{200, 300, 400}, 0, 3);
    public int endlessStageRequirement = 0;
    public int endlessStageRequirementPerStage = 0;
    public Array<String> bonusProviderListClassNames = new Array<>();
    public Array<ProbableBonusesProvider> probableBonusesProviders = new Array<>();
    public JsonValue bonusesConfig = new JsonValue(JsonValue.ValueType.object);

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeBoolean(this.reRollEnabled);
        output.writeBoolean(this.forceImmediateSelection);
        output.writeBoolean(this.replaceBonusesWithNotSatisfiedPreconditions);
        output.writeVarInt(this.maxReRollsPerStage, true);
        output.writeVarInt(this.maxReRollsAllTime, true);
        output.writeFloat(this.reRollPrice);
        output.writeFloat(this.reRollMinPrice);
        output.writeFloat(this.reRollMaxPrice);
        output.writeFloat(this.reRollPricePerStage);
        output.writeFloat(this.immediateBonusesChance);
        output.writeFloat(this.persistentBonusesChance);
        output.writeInt(this.activeBonusesSlotLimit);
        output.writeBoolean(this.chainReRoll);
        output.writeBoolean(this.ignoreImpossiblePreconditions);
        output.writeBoolean(this.selectedBonusAffectsRandom);
        kryo.writeObject(output, this.stageRequirements);
        output.writeInt(this.seed);
        output.writeInt(this.endlessStageRequirement);
        output.writeInt(this.endlessStageRequirementPerStage);
        output.writeBoolean(this.isFillWithDefaultBonusProviders);
        kryo.writeObject(output, this.bonusProviderListClassNames);
        kryo.writeObject(output, this.probableBonusesProviders);
        kryo.writeObject(output, this.bonusesConfig);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.reRollEnabled = input.readBoolean();
        this.forceImmediateSelection = input.readBoolean();
        this.replaceBonusesWithNotSatisfiedPreconditions = input.readBoolean();
        this.maxReRollsPerStage = input.readVarInt(true);
        this.maxReRollsAllTime = input.readVarInt(true);
        this.reRollPrice = input.readFloat();
        this.reRollMinPrice = input.readFloat();
        this.reRollMaxPrice = input.readFloat();
        this.reRollPricePerStage = input.readFloat();
        this.immediateBonusesChance = input.readFloat();
        this.persistentBonusesChance = input.readFloat();
        this.activeBonusesSlotLimit = input.readInt();
        this.chainReRoll = input.readBoolean();
        this.ignoreImpossiblePreconditions = input.readBoolean();
        this.selectedBonusAffectsRandom = input.readBoolean();
        this.stageRequirements = (IntArray) kryo.readObject(input, IntArray.class);
        this.seed = input.readInt();
        this.endlessStageRequirement = input.readInt();
        this.endlessStageRequirementPerStage = input.readInt();
        this.isFillWithDefaultBonusProviders = input.readBoolean();
        this.bonusProviderListClassNames = (Array) kryo.readObject(input, Array.class);
        this.probableBonusesProviders = (Array) kryo.readObject(input, Array.class);
        this.bonusesConfig = (JsonValue) kryo.readObject(input, JsonValue.class);
    }

    public BonusStagesConfig cpy() {
        BonusStagesConfig bonusStagesConfig = new BonusStagesConfig();
        bonusStagesConfig.reRollEnabled = this.reRollEnabled;
        bonusStagesConfig.forceImmediateSelection = this.forceImmediateSelection;
        bonusStagesConfig.replaceBonusesWithNotSatisfiedPreconditions = this.replaceBonusesWithNotSatisfiedPreconditions;
        bonusStagesConfig.maxReRollsPerStage = this.maxReRollsPerStage;
        bonusStagesConfig.maxReRollsAllTime = this.maxReRollsAllTime;
        bonusStagesConfig.reRollPrice = this.reRollPrice;
        bonusStagesConfig.reRollMinPrice = this.reRollMinPrice;
        bonusStagesConfig.reRollMaxPrice = this.reRollMaxPrice;
        bonusStagesConfig.reRollPricePerStage = this.reRollPricePerStage;
        bonusStagesConfig.immediateBonusesChance = this.immediateBonusesChance;
        bonusStagesConfig.persistentBonusesChance = this.persistentBonusesChance;
        bonusStagesConfig.activeBonusesSlotLimit = this.activeBonusesSlotLimit;
        bonusStagesConfig.chainReRoll = this.chainReRoll;
        bonusStagesConfig.ignoreImpossiblePreconditions = this.ignoreImpossiblePreconditions;
        bonusStagesConfig.selectedBonusAffectsRandom = this.selectedBonusAffectsRandom;
        bonusStagesConfig.stageRequirements.clear();
        bonusStagesConfig.stageRequirements.addAll(this.stageRequirements);
        bonusStagesConfig.seed = this.seed;
        bonusStagesConfig.endlessStageRequirement = this.endlessStageRequirement;
        bonusStagesConfig.endlessStageRequirementPerStage = this.endlessStageRequirementPerStage;
        bonusStagesConfig.isFillWithDefaultBonusProviders = this.isFillWithDefaultBonusProviders;
        bonusStagesConfig.bonusProviderListClassNames.addAll(this.bonusProviderListClassNames);
        bonusStagesConfig.probableBonusesProviders.addAll(this.probableBonusesProviders);
        bonusStagesConfig.bonusesConfig = JsonHandler.cloneJsonValue(this.bonusesConfig);
        return bonusStagesConfig;
    }

    public static BonusStagesConfig fromJsonString(String str) {
        return fromJson(new JsonReader().parse(str));
    }

    public static BonusStagesConfig fromJson(JsonValue jsonValue) {
        BonusStagesConfig bonusStagesConfig = new BonusStagesConfig();
        bonusStagesConfig.forceImmediateSelection = jsonValue.getBoolean("forceImmediateSelection", bonusStagesConfig.forceImmediateSelection);
        bonusStagesConfig.replaceBonusesWithNotSatisfiedPreconditions = jsonValue.getBoolean("replaceBonusesWithNotSatisfiedPreconditions", bonusStagesConfig.replaceBonusesWithNotSatisfiedPreconditions);
        bonusStagesConfig.reRollEnabled = jsonValue.getBoolean("reRollEnabled", bonusStagesConfig.reRollEnabled);
        bonusStagesConfig.maxReRollsPerStage = jsonValue.getInt("reRollEnabled", bonusStagesConfig.maxReRollsPerStage);
        bonusStagesConfig.maxReRollsAllTime = jsonValue.getInt("maxReRollsAllTime", bonusStagesConfig.maxReRollsAllTime);
        bonusStagesConfig.reRollPrice = jsonValue.getFloat("reRollPrice", bonusStagesConfig.reRollPrice);
        bonusStagesConfig.reRollMinPrice = jsonValue.getFloat("reRollMinPrice", bonusStagesConfig.reRollMinPrice);
        bonusStagesConfig.reRollMaxPrice = jsonValue.getFloat("reRollMaxPrice", bonusStagesConfig.reRollMaxPrice);
        bonusStagesConfig.reRollPricePerStage = jsonValue.getFloat("reRollMaxPrice", bonusStagesConfig.reRollPricePerStage);
        bonusStagesConfig.immediateBonusesChance = jsonValue.getFloat("immediateBonusesChance", bonusStagesConfig.immediateBonusesChance);
        bonusStagesConfig.persistentBonusesChance = jsonValue.getFloat("persistentBonusesChance", bonusStagesConfig.persistentBonusesChance);
        bonusStagesConfig.activeBonusesSlotLimit = jsonValue.getInt("activeBonusesSlotLimit", bonusStagesConfig.activeBonusesSlotLimit);
        bonusStagesConfig.chainReRoll = jsonValue.getBoolean("reRollMaxPrice", bonusStagesConfig.chainReRoll);
        bonusStagesConfig.ignoreImpossiblePreconditions = jsonValue.getBoolean("ignoreImpossiblePreconditions", bonusStagesConfig.ignoreImpossiblePreconditions);
        bonusStagesConfig.selectedBonusAffectsRandom = jsonValue.getBoolean("reRollMaxPrice", bonusStagesConfig.selectedBonusAffectsRandom);
        if (jsonValue.get("stageRequirements") != null) {
            bonusStagesConfig.stageRequirements.clear();
            bonusStagesConfig.stageRequirements.addAll(jsonValue.get("stageRequirements").asIntArray());
        }
        bonusStagesConfig.seed = jsonValue.getInt("seed", bonusStagesConfig.seed);
        bonusStagesConfig.endlessStageRequirement = jsonValue.getInt("endlessStageRequirement", bonusStagesConfig.endlessStageRequirement);
        bonusStagesConfig.endlessStageRequirementPerStage = jsonValue.getInt("endlessStageRequirementPerStage", bonusStagesConfig.endlessStageRequirementPerStage);
        bonusStagesConfig.isFillWithDefaultBonusProviders = jsonValue.getBoolean("fillWithDefaultBonusProviders", false);
        if (bonusStagesConfig.isFillWithDefaultBonusProviders) {
            bonusStagesConfig.fillWithDefaultBonusProviders();
        }
        JsonValue jsonValue2 = jsonValue.get("bonusProviderList");
        if (jsonValue2 != null) {
            if (bonusStagesConfig.isFillWithDefaultBonusProviders) {
                Iterator<JsonValue> iterator2 = jsonValue2.iterator2();
                while (iterator2.hasNext()) {
                    String asString = iterator2.next().asString();
                    String str = asString;
                    if (!asString.contains(".")) {
                        str = GENERIC_PROVIDER_CLASS_PATH + str;
                    }
                    try {
                        Class<?> classByName = ReflectionUtils.getClassByName(str);
                        boolean z = false;
                        int i = 0;
                        while (true) {
                            if (i >= bonusStagesConfig.probableBonusesProviders.size) {
                                break;
                            }
                            if (bonusStagesConfig.probableBonusesProviders.get(i).getClass() != classByName) {
                                i++;
                            } else {
                                bonusStagesConfig.probableBonusesProviders.removeIndex(i);
                                z = true;
                                break;
                            }
                        }
                        if (!z) {
                            f2080a.e("active provider not found and can't be disabled: " + str, new Object[0]);
                        }
                        bonusStagesConfig.bonusProviderListClassNames.add(str);
                    } catch (Exception unused) {
                        f2080a.e("failed to init probable bonus provider: " + str, new Object[0]);
                    }
                }
            } else {
                Iterator<JsonValue> iterator22 = jsonValue2.iterator2();
                while (iterator22.hasNext()) {
                    String asString2 = iterator22.next().asString();
                    String str2 = asString2;
                    if (!asString2.contains(".")) {
                        str2 = GENERIC_PROVIDER_CLASS_PATH + str2;
                    }
                    try {
                        ProbableBonusesProvider probableBonusesProvider = (ProbableBonusesProvider) ReflectionUtils.getClassByName(str2).getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                        boolean z2 = false;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= bonusStagesConfig.probableBonusesProviders.size) {
                                break;
                            }
                            if (bonusStagesConfig.probableBonusesProviders.get(i2) != probableBonusesProvider) {
                                i2++;
                            } else {
                                z2 = true;
                                break;
                            }
                        }
                        if (z2) {
                            f2080a.e("can't add bonus provider " + probableBonusesProvider + " - already added", new Object[0]);
                        } else {
                            bonusStagesConfig.bonusProviderListClassNames.add(str2);
                            bonusStagesConfig.probableBonusesProviders.add(probableBonusesProvider);
                        }
                    } catch (Exception unused2) {
                        f2080a.e("failed to init probable bonus provider: " + str2, new Object[0]);
                    }
                }
            }
        }
        bonusStagesConfig.bonusesConfig = jsonValue.get("bonusesConfig");
        return bonusStagesConfig;
    }

    public void toJson(Json json) {
        json.writeValue("forceImmediateSelection", Boolean.valueOf(this.forceImmediateSelection));
        json.writeValue("replaceBonusesWithNotSatisfiedPreconditions", Boolean.valueOf(this.replaceBonusesWithNotSatisfiedPreconditions));
        json.writeValue("reRollEnabled", Boolean.valueOf(this.reRollEnabled));
        json.writeValue("maxReRollsPerStage", Integer.valueOf(this.maxReRollsPerStage));
        json.writeValue("maxReRollsAllTime", Integer.valueOf(this.maxReRollsAllTime));
        json.writeValue("reRollPrice", Float.valueOf(this.reRollPrice));
        json.writeValue("reRollMinPrice", Float.valueOf(this.reRollMinPrice));
        json.writeValue("reRollMaxPrice", Float.valueOf(this.reRollMaxPrice));
        json.writeValue("reRollPricePerStage", Float.valueOf(this.reRollPricePerStage));
        json.writeValue("immediateBonusesChance", Float.valueOf(this.immediateBonusesChance));
        json.writeValue("persistentBonusesChance", Float.valueOf(this.persistentBonusesChance));
        json.writeValue("activeBonusesSlotLimit", Integer.valueOf(this.activeBonusesSlotLimit));
        json.writeValue("chainReRoll", Boolean.valueOf(this.chainReRoll));
        json.writeValue("ignoreImpossiblePreconditions", Boolean.valueOf(this.ignoreImpossiblePreconditions));
        json.writeValue("selectedBonusAffectsRandom", Boolean.valueOf(this.selectedBonusAffectsRandom));
        json.writeValue("stageRequirements", this.stageRequirements.toArray());
        json.writeValue("seed", Integer.valueOf(this.seed));
        json.writeValue("endlessStageRequirement", Integer.valueOf(this.endlessStageRequirement));
        json.writeValue("endlessStageRequirementPerStage", Integer.valueOf(this.endlessStageRequirementPerStage));
        json.writeValue("fillWithDefaultBonusProviders", Boolean.valueOf(this.isFillWithDefaultBonusProviders));
        json.writeArrayStart("bonusProviderList");
        for (int i = 0; i < this.bonusProviderListClassNames.size; i++) {
            String str = this.bonusProviderListClassNames.get(i);
            String str2 = str;
            if (str.startsWith(GENERIC_PROVIDER_CLASS_PATH)) {
                str2 = str2.substring(37);
            }
            json.writeValue(str2);
        }
        json.writeArrayEnd();
        try {
            json.getWriter().name("bonusesConfig").json(this.bonusesConfig.toJson(JsonWriter.OutputType.json));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillWithDefaultBonusProviders() {
        for (ProbableBonusesProvider probableBonusesProvider : DEFAULT_BONUS_PROVIDERS) {
            if (!this.probableBonusesProviders.contains(probableBonusesProvider, true)) {
                this.probableBonusesProviders.add(probableBonusesProvider);
            }
        }
    }

    public void setBonusesConfig(JsonValue jsonValue) {
        this.bonusesConfig = jsonValue;
    }

    public DelayedRemovalArray<ProbableBonus> getProbableBonuses(int i, GameSystemProvider gameSystemProvider) {
        gameSystemProvider.gameState.checkGameplayUpdateAllowed();
        DelayedRemovalArray<ProbableBonus> delayedRemovalArray = new DelayedRemovalArray<>(true, 1, ProbableBonus.class);
        for (int i2 = 0; i2 < this.probableBonusesProviders.size; i2++) {
            this.probableBonusesProviders.get(i2).provide(i, gameSystemProvider.bonus.getStagesConfig(), gameSystemProvider.gameplayMod.getActiveMods(), delayedRemovalArray);
        }
        if (this.immediateBonusesChance != 1.0f) {
            delayedRemovalArray.begin();
            for (int i3 = 0; i3 < delayedRemovalArray.size; i3++) {
                ProbableBonus probableBonus = delayedRemovalArray.get(i3);
                if (probableBonus.getBonus().isImmediateAndNotListed()) {
                    probableBonus.setProbability((int) (probableBonus.getProbability() * this.immediateBonusesChance));
                    if (probableBonus.getProbability() <= 0) {
                        delayedRemovalArray.removeIndex(i3);
                    }
                }
            }
            delayedRemovalArray.end();
        }
        if (this.persistentBonusesChance != 1.0f) {
            delayedRemovalArray.begin();
            for (int i4 = 0; i4 < delayedRemovalArray.size; i4++) {
                ProbableBonus probableBonus2 = delayedRemovalArray.get(i4);
                if (!probableBonus2.getBonus().isImmediateAndNotListed()) {
                    probableBonus2.setProbability((int) (probableBonus2.getProbability() * this.persistentBonusesChance));
                    if (probableBonus2.getProbability() <= 0) {
                        delayedRemovalArray.removeIndex(i4);
                    }
                }
            }
            delayedRemovalArray.end();
        }
        if (this.activeBonusesSlotLimit > 0) {
            DelayedRemovalArray<GameplayModSystem.ActiveMod> activeMods = gameSystemProvider.gameplayMod.getActiveMods();
            int i5 = 0;
            for (int i6 = 0; i6 < activeMods.size; i6++) {
                GameplayModSystem.ActiveMod activeMod = activeMods.items[i6];
                if (activeMod.getSource().equals(BonusSystem.GAMEPLAY_MOD_SOURCE_NAME) && !activeMod.getMod().isImmediateAndNotListed()) {
                    i5++;
                }
            }
            if (i5 >= this.activeBonusesSlotLimit) {
                delayedRemovalArray.begin();
                for (int i7 = 0; i7 < delayedRemovalArray.size; i7++) {
                    ProbableBonus probableBonus3 = delayedRemovalArray.get(i7);
                    if (!probableBonus3.getBonus().isImmediateAndNotListed()) {
                        boolean z = false;
                        int i8 = 0;
                        while (true) {
                            if (i8 >= activeMods.size) {
                                break;
                            }
                            GameplayModSystem.ActiveMod activeMod2 = activeMods.items[i8];
                            if (!activeMod2.getSource().equals(BonusSystem.GAMEPLAY_MOD_SOURCE_NAME) || activeMod2.getMod().isImmediateAndNotListed() || !activeMod2.getMod().getId().equals(probableBonus3.getBonus().getId())) {
                                i8++;
                            } else {
                                z = true;
                                break;
                            }
                        }
                        if (!z) {
                            delayedRemovalArray.removeIndex(i7);
                        }
                    }
                }
                delayedRemovalArray.end();
            }
        }
        if (!this.ignoreImpossiblePreconditions) {
            delayedRemovalArray.begin();
            for (int i9 = 0; i9 < delayedRemovalArray.size; i9++) {
                ProbableBonus probableBonus4 = delayedRemovalArray.get(i9);
                if (probableBonus4.getBonus().isAlwaysUseless(gameSystemProvider)) {
                    f2080a.i("exclude useless: " + probableBonus4.getBonus().getId(), new Object[0]);
                    delayedRemovalArray.removeIndex(i9);
                }
            }
            delayedRemovalArray.end();
        }
        if (!Config.isHeadless()) {
            f2080a.i("getProbableBonuses " + i, new Object[0]);
            for (int i10 = 0; i10 < delayedRemovalArray.size; i10++) {
                ProbableBonus probableBonus5 = delayedRemovalArray.get(i10);
                f2080a.i((probableBonus5.getBonus().getNotSatisfiedPreconditions(gameSystemProvider) == null) + SequenceUtils.SPACE + probableBonus5.getProbability() + ": " + probableBonus5.getBonus().getId(), new Object[0]);
            }
        }
        return delayedRemovalArray;
    }

    public JsonValue getBonusConfig(String str) {
        if (str.contains(".")) {
            throw new IllegalArgumentException("Dots are not allowed in path. Provide a correct name of the bonus");
        }
        return JsonHandler.orEmptyObject(this.bonusesConfig.get(str));
    }

    public int getStageRequirement(int i) {
        return i > this.stageRequirements.size ? this.endlessStageRequirement + (this.endlessStageRequirementPerStage * (i - this.stageRequirements.size)) : this.stageRequirements.get(i - 1);
    }

    public int getMaxStages() {
        if (this.endlessStageRequirement <= 0) {
            return this.stageRequirements.size;
        }
        return 0;
    }

    public int getReRollPrice(int i, GameSystemProvider gameSystemProvider) {
        if (this.reRollMaxPrice <= 0.0f) {
            return 0;
        }
        return MathUtils.ceil(((float) gameSystemProvider.statistics.getStatistic(StatisticsType.CG)) * MathUtils.clamp(this.reRollPrice + (i * this.reRollPricePerStage), this.reRollMinPrice, this.reRollMaxPrice));
    }

    public int getMaxReRollsPerStage() {
        return this.maxReRollsPerStage;
    }

    public int getMaxReRollsAllTime() {
        return this.maxReRollsAllTime;
    }
}
