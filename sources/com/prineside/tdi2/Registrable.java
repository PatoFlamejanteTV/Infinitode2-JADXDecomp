package com.prineside.tdi2;

import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Registrable.class */
public abstract class Registrable implements KryoSerializable {

    @Null
    public GameSystemProvider S;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.S, GameSystemProvider.class);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.S = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    public void setRegistered(GameSystemProvider gameSystemProvider) {
        this.S = gameSystemProvider;
    }

    public void setUnregistered() {
        this.S = null;
    }

    public boolean isRegistered() {
        return this.S != null;
    }
}
