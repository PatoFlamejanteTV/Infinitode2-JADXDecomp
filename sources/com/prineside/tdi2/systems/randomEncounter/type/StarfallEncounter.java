package com.prineside.tdi2.systems.randomEncounter.type;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.systems.RandomEncounterSystem;
import com.prineside.tdi2.systems.randomEncounter.EncounterBird;
import com.prineside.tdi2.systems.randomEncounter.type.starfall.StarfallEncounterHandler;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/randomEncounter/type/StarfallEncounter.class */
public class StarfallEncounter implements KryoSerializable, RandomEncounterSystem.BirdAction, RandomEncounterSystem.EncounterType {
    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.EncounterType
    public int getProbability(GameSystemProvider gameSystemProvider) {
        return 25;
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.EncounterType
    public EncounterBird spawnBird(GameSystemProvider gameSystemProvider) {
        return gameSystemProvider.randomEncounter.prepareDefaultBird(this);
    }

    @Override // com.prineside.tdi2.systems.RandomEncounterSystem.BirdAction
    public void performAction(GameSystemProvider gameSystemProvider, EncounterBird encounterBird) {
        new StarfallEncounterHandler(gameSystemProvider);
    }
}
