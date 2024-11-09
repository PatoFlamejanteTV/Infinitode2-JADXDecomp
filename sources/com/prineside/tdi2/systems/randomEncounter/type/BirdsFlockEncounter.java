package com.prineside.tdi2.systems.randomEncounter.type;

import com.badlogic.gdx.math.RandomXS128;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.managers.ItemManager;
import com.prineside.tdi2.systems.RandomEncounterSystem;
import com.prineside.tdi2.systems.randomEncounter.EncounterBird;
import com.prineside.tdi2.systems.randomEncounter.reward.GiveItemReward;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/BirdsFlockEncounter.class */
public class BirdsFlockEncounter implements KryoSerializable, RandomEncounterSystem.BirdAction, RandomEncounterSystem.EncounterType {
    static {
        TLog.forClass(BirdsFlockEncounter.class);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.EncounterType
    public int getProbability(GameSystemProvider gameSystemProvider) {
        return 10;
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.EncounterType
    public EncounterBird spawnBird(GameSystemProvider gameSystemProvider) {
        return gameSystemProvider.randomEncounter.prepareDefaultBird(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v37, types: [com.prineside.tdi2.systems.RandomEncounterSystem$RewardType] */
    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.BirdAction
    public void performAction(GameSystemProvider gameSystemProvider, EncounterBird encounterBird) {
        GiveItemReward giveItemReward;
        int nextInt = gameSystemProvider.randomEncounter.getRandom().nextInt(40);
        for (int i = 0; i < 40; i++) {
            RandomXS128 random = gameSystemProvider.randomEncounter.getRandom();
            if (i == nextInt) {
                giveItemReward = gameSystemProvider.randomEncounter.selectRandomReward();
            } else {
                GiveItemReward giveItemReward2 = new GiveItemReward(1, ItemManager.generateItemByRarity(random, RarityType.values[random.nextInt(5)], 1.0f, 1.0f, 0.0f, 0.2f, 1.0f, 1.0f, 1.0f, true, gameSystemProvider.loot.inventoryStatistics));
                giveItemReward = giveItemReward2;
                giveItemReward2.withFireworks = false;
            }
            EncounterBird prepareDefaultBird = gameSystemProvider.randomEncounter.prepareDefaultBird(new BirdActionGiveReward(giveItemReward));
            prepareDefaultBird.hp = 1;
            prepareDefaultBird.maxHp = 1;
            prepareDefaultBird.requiresConfirmation = false;
            prepareDefaultBird.baseColor = MaterialColor.GREY.P100;
            prepareDefaultBird.overlayColor = MaterialColor.GREY.P50;
            prepareDefaultBird.position.set(encounterBird.position);
            prepareDefaultBird.maxVelocity *= 1.5f;
            prepareDefaultBird.setLifeTime(10.0f + random.nextFloat());
            gameSystemProvider.randomEncounter.addBird(prepareDefaultBird);
        }
        if (gameSystemProvider._gameUi != null) {
            gameSystemProvider._gameUi.sideMenu.setOffscreen(true);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/BirdsFlockEncounter$BirdActionGiveReward.class */
    public static class BirdActionGiveReward implements KryoSerializable, RandomEncounterSystem.BirdAction {
        public RandomEncounterSystem.RewardType rewardType;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.rewardType);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.rewardType = (RandomEncounterSystem.RewardType) kryo.readClassAndObject(input);
        }

        private BirdActionGiveReward() {
        }

        public BirdActionGiveReward(RandomEncounterSystem.RewardType rewardType) {
            Preconditions.checkNotNull(rewardType);
            this.rewardType = rewardType;
        }

        @Override // com.prineside.tdi2.systems.RandomEncounterSystem.BirdAction
        public void performAction(GameSystemProvider gameSystemProvider, EncounterBird encounterBird) {
            this.rewardType.giveReward(gameSystemProvider, encounterBird.position.x, encounterBird.position.y);
        }
    }
}
