package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/EnumNameSerializer.class */
public class EnumNameSerializer extends ImmutableSerializer<Enum> {
    private final Class<? extends Enum> enumType;

    public EnumNameSerializer(Class<? extends Enum> cls) {
        this.enumType = cls;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, Enum r6) {
        output.writeString(r6.name());
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public Enum read2(Kryo kryo, Input input, Class cls) {
        String readString = input.readString();
        try {
            return Enum.valueOf(this.enumType, readString);
        } catch (IllegalArgumentException e) {
            throw new KryoException("Enum value not found with name: " + readString, e);
        }
    }
}
