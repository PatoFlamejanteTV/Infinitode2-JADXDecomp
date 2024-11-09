package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/JsonValueSerializer.class */
public final class JsonValueSerializer extends Serializer<JsonValue> {
    public JsonValueSerializer() {
        setAcceptsNull(true);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, JsonValue jsonValue) {
        output.writeString(jsonValue.toJson(JsonWriter.OutputType.minimal));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final JsonValue read2(Kryo kryo, Input input, Class<? extends JsonValue> cls) {
        return new JsonReader().parse(input.readString());
    }
}
