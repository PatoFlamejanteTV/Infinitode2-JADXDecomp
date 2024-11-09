package com.prineside.tdi2.systems.randomEncounter.reward;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.systems.RandomEncounterSystem;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/reward/UpgradeAllTowersReward.class */
public class UpgradeAllTowersReward implements KryoSerializable, RandomEncounterSystem.RewardType {
    public int probability;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeVarInt(this.probability, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.probability = input.readVarInt(true);
    }

    private UpgradeAllTowersReward() {
    }

    public UpgradeAllTowersReward(int i) {
        Preconditions.checkArgument(i > 0, "invalid probability: %s", i);
        this.probability = i;
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
        gameSystemProvider.randomEncounter.startFireworks(f, f2);
        for (int i = 0; i < gameSystemProvider.tower.towers.size; i++) {
            Tower tower = gameSystemProvider.tower.towers.get(i);
            if (tower.getUpgradeLevel() < tower.getMaxUpgradeLevel()) {
                gameSystemProvider.tower.upgradeTowerTakeCoins(tower, false);
            }
        }
    }
}
