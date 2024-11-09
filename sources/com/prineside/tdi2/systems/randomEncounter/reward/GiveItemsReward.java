package com.prineside.tdi2.systems.randomEncounter.reward;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.managers.ItemManager;
import com.prineside.tdi2.systems.RandomEncounterSystem;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/reward/GiveItemsReward.class */
public class GiveItemsReward implements KryoSerializable, RandomEncounterSystem.RewardType {
    public int probability;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeVarInt(this.probability, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.probability = input.readVarInt(true);
    }

    private GiveItemsReward() {
    }

    public GiveItemsReward(int i) {
        Preconditions.checkArgument(i > 0, "invalid probability: %s", i);
        this.probability = i;
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.RewardType
    public int getProbability(GameSystemProvider gameSystemProvider) {
        return this.probability;
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.RewardType
    public void giveReward(GameSystemProvider gameSystemProvider, float f, float f2) {
        gameSystemProvider.randomEncounter.startFireworks(f, f2);
        Vector2 vector2 = new Vector2(f, f2);
        if (gameSystemProvider._input != null) {
            gameSystemProvider._input.cameraController.mapToStage(vector2);
        }
        float percentValueAsMultiplier = (float) gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.LOOT_FREQUENCY);
        float percentValueAsMultiplier2 = (float) gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.LOOT_RARITY);
        Array<ItemStack> array = new Array<>(true, 30, ItemStack.class);
        for (int i = 0; i < 30; i++) {
            int i2 = 0;
            if (gameSystemProvider.randomEncounter.getRandom().nextFloat() < 0.5f) {
                i2 = 0 + 1;
            }
            if (gameSystemProvider.randomEncounter.getRandom().nextFloat() < 0.25f) {
                i2++;
            }
            if (gameSystemProvider.randomEncounter.getRandom().nextFloat() < 0.125f) {
                i2++;
            }
            if (gameSystemProvider.randomEncounter.getRandom().nextFloat() < 0.05f) {
                i2++;
            }
            for (int i3 = 1; i3 < percentValueAsMultiplier2; i3++) {
                if (gameSystemProvider.randomEncounter.getRandom().nextFloat() < 0.3f) {
                    i2++;
                }
            }
            if (i2 >= RarityType.values.length) {
                i2 = RarityType.values.length - 1;
            }
            ItemStack generateItemByRarity = ItemManager.generateItemByRarity(gameSystemProvider.randomEncounter.getRandom(), RarityType.values[i2], gameSystemProvider.randomEncounter.getRandom().nextFloat(), percentValueAsMultiplier, 0.0f, 0.0f, (float) ((DifficultyMode.isEndless(gameSystemProvider.gameState.difficultyMode) ? 0.5f : 0.0f) * (((gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.BIT_DUST_DROP_RATE) - 1.0d) * 0.4000000059604645d) + 1.0d)), 1.0f, 1.0f, false, gameSystemProvider.loot.inventoryStatistics);
            generateItemByRarity.markFromRandomEncounter(true);
            array.add(generateItemByRarity);
        }
        gameSystemProvider.gameState.addLootIssuedPrizesArray(array, vector2.x, vector2.y);
    }
}
