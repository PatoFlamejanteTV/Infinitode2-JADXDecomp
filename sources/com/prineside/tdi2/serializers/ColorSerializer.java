package com.prineside.tdi2.serializers;

import com.badlogic.gdx.graphics.Color;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/ColorSerializer.class */
public final class ColorSerializer extends Serializer<Color> {
    public ColorSerializer() {
        setAcceptsNull(true);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, Color color) {
        output.writeInt(Color.rgba8888(color));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final Color read2(Kryo kryo, Input input, Class<? extends Color> cls) {
        return new Color(input.readInt());
    }
}
