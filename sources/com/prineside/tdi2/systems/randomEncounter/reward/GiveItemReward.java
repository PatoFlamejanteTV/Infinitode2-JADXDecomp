package com.prineside.tdi2.systems.randomEncounter.reward;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.systems.RandomEncounterSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/reward/GiveItemReward.class */
public class GiveItemReward implements KryoSerializable, RandomEncounterSystem.RewardType {
    public int probability;

    @NAGS
    public ItemStack loot;
    public boolean withFireworks = true;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeVarInt(this.probability, true);
        kryo.writeClassAndObject(output, this.loot);
        output.writeBoolean(this.withFireworks);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.probability = input.readVarInt(true);
        this.loot = (ItemStack) kryo.readClassAndObject(input);
        this.withFireworks = input.readBoolean();
    }

    private GiveItemReward() {
    }

    public GiveItemReward(int i, ItemStack itemStack) {
        Preconditions.checkArgument(i > 0, "invalid probability: %s", i);
        Preconditions.checkNotNull(itemStack, "stack can not be null");
        this.loot = itemStack;
        this.probability = i;
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.RewardType
    public int getProbability(GameSystemProvider gameSystemProvider) {
        return this.probability;
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.RewardType
    public void giveReward(GameSystemProvider gameSystemProvider, float f, float f2) {
        if (this.withFireworks) {
            gameSystemProvider.randomEncounter.startFireworks(f, f2);
        }
        Vector2 vector2 = new Vector2(f, f2);
        if (gameSystemProvider._input != null) {
            gameSystemProvider._input.cameraController.mapToStage(vector2);
        }
        ItemStack itemStack = new ItemStack(this.loot);
        itemStack.markFromRandomEncounter(true);
        gameSystemProvider.gameState.addLootIssuedPrizes(itemStack, vector2.x, vector2.y, 2);
    }
}
