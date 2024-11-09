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
import com.prineside.tdi2.systems.RandomEncounterSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/reward/GiveItemIndividuallyReward.class */
public class GiveItemIndividuallyReward implements KryoSerializable, RandomEncounterSystem.RewardType {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3080a = TLog.forClass(GiveItemIndividuallyReward.class);
    public int probability;

    @NAGS
    public ItemStack loot;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeVarInt(this.probability, true);
        kryo.writeClassAndObject(output, this.loot);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.probability = input.readVarInt(true);
        this.loot = (ItemStack) kryo.readClassAndObject(input);
    }

    private GiveItemIndividuallyReward() {
    }

    public GiveItemIndividuallyReward(int i, ItemStack itemStack) {
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
        gameSystemProvider.randomEncounter.startFireworks(f, f2);
        Vector2 vector2 = new Vector2(f, f2);
        if (gameSystemProvider._input != null) {
            gameSystemProvider._input.cameraController.mapToStage(vector2);
        }
        Array<ItemStack> array = new Array<>(true, 1, ItemStack.class);
        for (int i = 0; i < this.loot.getCount(); i++) {
            ItemStack itemStack = new ItemStack(this.loot.getItem(), 1);
            itemStack.markFromRandomEncounter(true);
            array.add(itemStack);
        }
        gameSystemProvider.gameState.addLootIssuedPrizesArray(array, vector2.x, vector2.y);
        f3080a.d("giveItemIndividually " + this.loot, new Object[0]);
    }
}
