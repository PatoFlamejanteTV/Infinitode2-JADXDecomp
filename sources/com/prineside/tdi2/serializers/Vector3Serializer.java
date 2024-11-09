package com.prineside.tdi2.serializers;

import com.badlogic.gdx.math.Vector3;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/Vector3Serializer.class */
public final class Vector3Serializer extends Serializer<Vector3> {
    public Vector3Serializer() {
        setAcceptsNull(true);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, Vector3 vector3) {
        output.writeFloat(vector3.x);
        output.writeFloat(vector3.y);
        output.writeFloat(vector3.z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final Vector3 read2(Kryo kryo, Input input, Class<? extends Vector3> cls) {
        return new Vector3(input.readFloat(), input.readFloat(), input.readFloat());
    }
}
