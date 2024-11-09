package com.prineside.tdi2.components;

import com.badlogic.gdx.utils.IntIntMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/components/StunDebuffStats.class */
public final class StunDebuffStats implements KryoSerializable {
    public float passedTilesOnLastStun;

    /* renamed from: a, reason: collision with root package name */
    private IntIntMap f1828a = new IntIntMap();
    public byte totalCount = 0;
    public float immunity = 0.0f;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.f1828a);
        output.writeByte(this.totalCount);
        output.writeFloat(this.passedTilesOnLastStun);
        output.writeFloat(this.immunity);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f1828a = (IntIntMap) kryo.readObject(input, IntIntMap.class);
        this.totalCount = input.readByte();
        this.passedTilesOnLastStun = input.readFloat();
        this.immunity = input.readFloat();
    }

    public final int getCountByTower(int i) {
        return this.f1828a.get(i, 0);
    }

    public final void addStunnedBy(int i) {
        int i2 = this.f1828a.get(i, -1);
        if (i2 != -1) {
            this.f1828a.put(i, i2 + 1);
        } else {
            this.f1828a.put(i, 1);
        }
    }
}
