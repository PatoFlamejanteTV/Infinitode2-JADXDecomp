package com.prineside.tdi2.systems.randomEncounter.reward;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.systems.RandomEncounterSystem;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/reward/CoinsReward.class */
public class CoinsReward implements KryoSerializable, RandomEncounterSystem.RewardType {
    public int probability;
    public int count;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeVarInt(this.probability, true);
        output.writeVarInt(this.count, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.probability = input.readVarInt(true);
        this.count = input.readVarInt(true);
    }

    private CoinsReward() {
    }

    public CoinsReward(int i, int i2) {
        Preconditions.checkArgument(i > 0, "invalid probability: %s", i);
        Preconditions.checkArgument(i2 > 0, "invalid count: %s", i2);
        this.probability = i;
        this.count = i2;
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.RewardType
    public int getProbability(GameSystemProvider gameSystemProvider) {
        if (gameSystemProvider.randomEncounter.canGiveStateAffectingRewards()) {
            return this.probability;
        }
        return 0;
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.RewardType
    public void giveReward(GameSystemProvider gameSystemProvider, float f, float f2) {
        gameSystemProvider.gameState.addMoney(this.count, false);
        gameSystemProvider.randomEncounter.startFireworks(f, f2);
        if (gameSystemProvider._particle != null) {
            gameSystemProvider._particle.addCoinParticle(f, f2, this.count);
        }
    }
}
