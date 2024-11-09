package com.prineside.tdi2.serializers;

import com.badlogic.gdx.math.Rectangle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/RectangleSerializer.class */
public final class RectangleSerializer extends Serializer<Rectangle> {
    public RectangleSerializer() {
        setAcceptsNull(true);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, Rectangle rectangle) {
        output.writeFloat(rectangle.x);
        output.writeFloat(rectangle.y);
        output.writeFloat(rectangle.width);
        output.writeFloat(rectangle.height);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final Rectangle read2(Kryo kryo, Input input, Class<? extends Rectangle> cls) {
        return new Rectangle(input.readFloat(), input.readFloat(), input.readFloat(), input.readFloat());
    }
}
