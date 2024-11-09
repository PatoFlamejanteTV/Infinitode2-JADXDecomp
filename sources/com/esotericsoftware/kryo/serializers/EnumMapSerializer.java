package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import java.util.EnumMap;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/EnumMapSerializer.class */
public class EnumMapSerializer extends MapSerializer<EnumMap> {
    private final Class<? extends Enum> enumType;

    public EnumMapSerializer(Class<? extends Enum> cls) {
        this.enumType = cls;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.serializers.MapSerializer
    public EnumMap create(Kryo kryo, Input input, Class<? extends EnumMap> cls, int i) {
        return new EnumMap(this.enumType);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.esotericsoftware.kryo.serializers.MapSerializer
    public EnumMap createCopy(Kryo kryo, EnumMap enumMap) {
        return new EnumMap(enumMap);
    }
}
