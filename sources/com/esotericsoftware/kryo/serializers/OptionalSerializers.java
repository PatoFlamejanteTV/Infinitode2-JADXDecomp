package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Util;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/OptionalSerializers.class */
public final class OptionalSerializers {
    public static void addDefaultSerializers(Kryo kryo) {
        if (Util.isClassAvailable("java.util.Optional")) {
            kryo.addDefaultSerializer(Optional.class, OptionalSerializer.class);
        }
        if (Util.isClassAvailable("java.util.OptionalInt")) {
            kryo.addDefaultSerializer(OptionalInt.class, OptionalIntSerializer.class);
        }
        if (Util.isClassAvailable("java.util.OptionalLong")) {
            kryo.addDefaultSerializer(OptionalLong.class, OptionalLongSerializer.class);
        }
        if (Util.isClassAvailable("java.util.OptionalDouble")) {
            kryo.addDefaultSerializer(OptionalDouble.class, OptionalDoubleSerializer.class);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/OptionalSerializers$OptionalSerializer.class */
    public static class OptionalSerializer extends Serializer<Optional> {
        public OptionalSerializer() {
            setAcceptsNull(false);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Optional optional) {
            kryo.writeClassAndObject(output, optional.isPresent() ? optional.get() : null);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Optional read2(Kryo kryo, Input input, Class<? extends Optional> cls) {
            return Optional.ofNullable(kryo.readClassAndObject(input));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public Optional copy(Kryo kryo, Optional optional) {
            if (optional.isPresent()) {
                return Optional.of(kryo.copy(optional.get()));
            }
            return optional;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/OptionalSerializers$OptionalIntSerializer.class */
    public static class OptionalIntSerializer extends ImmutableSerializer<OptionalInt> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, OptionalInt optionalInt) {
            output.writeBoolean(optionalInt.isPresent());
            if (optionalInt.isPresent()) {
                output.writeInt(optionalInt.getAsInt());
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public OptionalInt read2(Kryo kryo, Input input, Class cls) {
            return input.readBoolean() ? OptionalInt.of(input.readInt()) : OptionalInt.empty();
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/OptionalSerializers$OptionalLongSerializer.class */
    public static class OptionalLongSerializer extends ImmutableSerializer<OptionalLong> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, OptionalLong optionalLong) {
            output.writeBoolean(optionalLong.isPresent());
            if (optionalLong.isPresent()) {
                output.writeLong(optionalLong.getAsLong());
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public OptionalLong read2(Kryo kryo, Input input, Class cls) {
            return input.readBoolean() ? OptionalLong.of(input.readLong()) : OptionalLong.empty();
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/OptionalSerializers$OptionalDoubleSerializer.class */
    public static class OptionalDoubleSerializer extends ImmutableSerializer<OptionalDouble> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, OptionalDouble optionalDouble) {
            output.writeBoolean(optionalDouble.isPresent());
            if (optionalDouble.isPresent()) {
                output.writeDouble(optionalDouble.getAsDouble());
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public OptionalDouble read2(Kryo kryo, Input input, Class cls) {
            return input.readBoolean() ? OptionalDouble.of(input.readDouble()) : OptionalDouble.empty();
        }
    }
}
