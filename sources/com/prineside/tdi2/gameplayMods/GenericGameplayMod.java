package com.prineside.tdi2.gameplayMods;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/GenericGameplayMod.class */
public abstract class GenericGameplayMod implements KryoSerializable, GameplayMod {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2082a = TLog.forClass(GenericGameplayMod.class);
    public int maxPower = 1;
    public int power = 1;
    public boolean multipleInstances = true;
    public IntArray powerLevelsUpgradedByMods = new IntArray();

    @Null
    public GameplayMod replacedMod;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeInt(this.maxPower);
        output.writeInt(this.power);
        output.writeBoolean(this.multipleInstances);
        kryo.writeObject(output, this.powerLevelsUpgradedByMods);
        kryo.writeClassAndObject(output, this.replacedMod);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.maxPower = input.readInt();
        this.power = input.readInt();
        this.multipleInstances = input.readBoolean();
        this.powerLevelsUpgradedByMods = (IntArray) kryo.readObject(input, IntArray.class);
        this.replacedMod = (GameplayMod) kryo.readClassAndObject(input);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public String getId() {
        return getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(GenericGameplayMod genericGameplayMod) {
        genericGameplayMod.maxPower = this.maxPower;
        genericGameplayMod.power = this.power;
        genericGameplayMod.multipleInstances = this.multipleInstances;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public void setReplacesUnsatisfiedMod(GameplayMod gameplayMod) {
        f2082a.i("setReplacesUnsatisfiedMod " + gameplayMod + SequenceUtils.SPACE + gameplayMod.getId(), new Object[0]);
        this.replacedMod = gameplayMod;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    @Null
    public GameplayMod getReplacesUnsatisfiedMod() {
        return this.replacedMod;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public void markPowerLevelUpgradedByOtherMod(int i) {
        if (!this.powerLevelsUpgradedByMods.contains(i)) {
            this.powerLevelsUpgradedByMods.add(i);
        }
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public boolean isPowerLevelUpgradedByOtherMod(int i) {
        return this.powerLevelsUpgradedByMods.contains(i);
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public boolean allowsMultipleInstancesFromDifferentSources() {
        return this.multipleInstances;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public int getPower() {
        return this.power;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public int getMaxPower() {
        return this.maxPower;
    }

    @Override // com.prineside.tdi2.gameplayMods.GameplayMod
    public void setRegisteredPower(int i) {
        this.power = i;
    }

    public GenericGameplayMod applyConfig(JsonValue jsonValue) {
        this.maxPower = jsonValue.getInt("maxPower", this.maxPower);
        this.power = jsonValue.getInt("power", this.power);
        this.multipleInstances = jsonValue.getBoolean("multipleInstances", this.multipleInstances);
        return this;
    }
}
