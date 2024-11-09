package com.prineside.tdi2.serializers;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/Vector2Serializer.class */
public final class Vector2Serializer extends Serializer<Vector2> {
    public Vector2Serializer() {
        setAcceptsNull(true);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, Vector2 vector2) {
        output.writeFloat(vector2.x);
        output.writeFloat(vector2.y);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final Vector2 read2(Kryo kryo, Input input, Class<? extends Vector2> cls) {
        return new Vector2(input.readFloat(), input.readFloat());
    }
}
