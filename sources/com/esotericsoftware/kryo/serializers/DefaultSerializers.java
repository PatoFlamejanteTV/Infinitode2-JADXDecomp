package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Util;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers.class */
public class DefaultSerializers {

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$VoidSerializer.class */
    public static class VoidSerializer extends ImmutableSerializer {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Object obj) {
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Object read2(Kryo kryo, Input input, Class cls) {
            return null;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$BooleanSerializer.class */
    public static class BooleanSerializer extends ImmutableSerializer<Boolean> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Boolean>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Boolean bool) {
            output.writeBoolean(bool.booleanValue());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Boolean read2(Kryo kryo, Input input, Class<? extends Boolean> cls) {
            return Boolean.valueOf(input.readBoolean());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$ByteSerializer.class */
    public static class ByteSerializer extends ImmutableSerializer<Byte> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Byte>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Byte b2) {
            output.writeByte(b2.byteValue());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Byte read2(Kryo kryo, Input input, Class<? extends Byte> cls) {
            return Byte.valueOf(input.readByte());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$CharSerializer.class */
    public static class CharSerializer extends ImmutableSerializer<Character> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Character>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Character ch) {
            output.writeChar(ch.charValue());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Character read2(Kryo kryo, Input input, Class<? extends Character> cls) {
            return Character.valueOf(input.readChar());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$ShortSerializer.class */
    public static class ShortSerializer extends ImmutableSerializer<Short> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Short>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Short sh) {
            output.writeShort(sh.shortValue());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Short read2(Kryo kryo, Input input, Class<? extends Short> cls) {
            return Short.valueOf(input.readShort());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$IntSerializer.class */
    public static class IntSerializer extends ImmutableSerializer<Integer> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Integer>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Integer num) {
            output.writeInt(num.intValue(), false);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Integer read2(Kryo kryo, Input input, Class<? extends Integer> cls) {
            return Integer.valueOf(input.readInt(false));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$LongSerializer.class */
    public static class LongSerializer extends ImmutableSerializer<Long> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Long>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Long l) {
            output.writeVarLong(l.longValue(), false);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Long read2(Kryo kryo, Input input, Class<? extends Long> cls) {
            return Long.valueOf(input.readVarLong(false));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$FloatSerializer.class */
    public static class FloatSerializer extends ImmutableSerializer<Float> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Float>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Float f) {
            output.writeFloat(f.floatValue());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Float read2(Kryo kryo, Input input, Class<? extends Float> cls) {
            return Float.valueOf(input.readFloat());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$DoubleSerializer.class */
    public static class DoubleSerializer extends ImmutableSerializer<Double> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Double>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Double d) {
            output.writeDouble(d.doubleValue());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Double read2(Kryo kryo, Input input, Class<? extends Double> cls) {
            return Double.valueOf(input.readDouble());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$StringSerializer.class */
    public static class StringSerializer extends ImmutableSerializer<String> {
        public StringSerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends String>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, String str) {
            output.writeString(str);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public String read2(Kryo kryo, Input input, Class<? extends String> cls) {
            return input.readString();
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$BigIntegerSerializer.class */
    public static class BigIntegerSerializer extends ImmutableSerializer<BigInteger> {
        public BigIntegerSerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends BigInteger>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, BigInteger bigInteger) {
            if (bigInteger == null) {
                output.writeByte((byte) 0);
                return;
            }
            if (bigInteger == BigInteger.ZERO) {
                output.writeByte(2);
                output.writeByte(0);
            } else {
                byte[] byteArray = bigInteger.toByteArray();
                output.writeVarInt(byteArray.length + 1, true);
                output.writeBytes(byteArray);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public BigInteger read2(Kryo kryo, Input input, Class<? extends BigInteger> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            byte[] readBytes = input.readBytes(readVarInt - 1);
            if (cls != BigInteger.class && cls != null) {
                try {
                    Constructor<? extends BigInteger> constructor = cls.getConstructor(byte[].class);
                    if (!constructor.isAccessible()) {
                        try {
                            constructor.setAccessible(true);
                        } catch (SecurityException unused) {
                        }
                    }
                    return constructor.newInstance(readBytes);
                } catch (Exception e) {
                    throw new KryoException(e);
                }
            }
            if (readVarInt == 2) {
                switch (readBytes[0]) {
                    case 0:
                        return BigInteger.ZERO;
                    case 1:
                        return BigInteger.ONE;
                    case 10:
                        return BigInteger.TEN;
                }
            }
            return new BigInteger(readBytes);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$BigDecimalSerializer.class */
    public static class BigDecimalSerializer extends ImmutableSerializer<BigDecimal> {
        private final BigIntegerSerializer bigIntegerSerializer = new BigIntegerSerializer();

        public BigDecimalSerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends BigDecimal>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, BigDecimal bigDecimal) {
            if (bigDecimal == null) {
                output.writeByte((byte) 0);
            } else if (bigDecimal == BigDecimal.ZERO) {
                this.bigIntegerSerializer.write(kryo, output, BigInteger.ZERO);
                output.writeInt(0, false);
            } else {
                this.bigIntegerSerializer.write(kryo, output, bigDecimal.unscaledValue());
                output.writeInt(bigDecimal.scale(), false);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public BigDecimal read2(Kryo kryo, Input input, Class<? extends BigDecimal> cls) {
            BigInteger read2 = this.bigIntegerSerializer.read2(kryo, input, BigInteger.class);
            if (read2 == null) {
                return null;
            }
            int readInt = input.readInt(false);
            if (cls != BigDecimal.class && cls != null) {
                try {
                    Constructor<? extends BigDecimal> constructor = cls.getConstructor(BigInteger.class, Integer.TYPE);
                    if (!constructor.isAccessible()) {
                        try {
                            constructor.setAccessible(true);
                        } catch (SecurityException unused) {
                        }
                    }
                    return constructor.newInstance(read2, Integer.valueOf(readInt));
                } catch (Exception e) {
                    throw new KryoException(e);
                }
            }
            if (read2 != BigInteger.ZERO || readInt != 0) {
                return new BigDecimal(read2, readInt);
            }
            return BigDecimal.ZERO;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$ClassSerializer.class */
    public static class ClassSerializer extends ImmutableSerializer<Class> {
        public ClassSerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Class>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Class cls) {
            kryo.writeClass(output, cls);
            if (cls != null) {
                if (cls.isPrimitive() || Util.isWrapperClass(cls)) {
                    output.writeBoolean(cls.isPrimitive());
                }
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Class read2(Kryo kryo, Input input, Class<? extends Class> cls) {
            Registration readClass = kryo.readClass(input);
            if (readClass == null) {
                return null;
            }
            Class type = readClass.getType();
            if (!type.isPrimitive() || input.readBoolean()) {
                return type;
            }
            return Util.getWrapperClass(type);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$DateSerializer.class */
    public static class DateSerializer extends Serializer<Date> {
        private Date create(Kryo kryo, Class<? extends Date> cls, long j) {
            if (cls == Date.class || cls == null) {
                return new Date(j);
            }
            if (cls == Timestamp.class) {
                return new Timestamp(j);
            }
            if (cls == java.sql.Date.class) {
                return new java.sql.Date(j);
            }
            if (cls == Time.class) {
                return new Time(j);
            }
            try {
                Constructor<? extends Date> constructor = cls.getConstructor(Long.TYPE);
                if (!constructor.isAccessible()) {
                    try {
                        constructor.setAccessible(true);
                    } catch (SecurityException unused) {
                    }
                }
                return constructor.newInstance(Long.valueOf(j));
            } catch (Exception unused2) {
                Date date = (Date) kryo.newInstance(cls);
                date.setTime(j);
                return date;
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Date date) {
            output.writeVarLong(date.getTime(), true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Date read2(Kryo kryo, Input input, Class<? extends Date> cls) {
            return create(kryo, cls, input.readVarLong(true));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.esotericsoftware.kryo.Serializer
        public Date copy(Kryo kryo, Date date) {
            return create(kryo, date.getClass(), date.getTime());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$TimestampSerializer.class */
    public static class TimestampSerializer extends Serializer<Timestamp> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Timestamp timestamp) {
            output.writeVarLong(integralTimeComponent(timestamp), true);
            output.writeVarInt(timestamp.getNanos(), true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Timestamp read2(Kryo kryo, Input input, Class<? extends Timestamp> cls) {
            return create(input.readVarLong(true), input.readVarInt(true));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public Timestamp copy(Kryo kryo, Timestamp timestamp) {
            return create(integralTimeComponent(timestamp), timestamp.getNanos());
        }

        private long integralTimeComponent(Timestamp timestamp) {
            return timestamp.getTime() - (timestamp.getNanos() / 1000000);
        }

        private Timestamp create(long j, int i) {
            Timestamp timestamp = new Timestamp(j);
            timestamp.setNanos(i);
            return timestamp;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$EnumSerializer.class */
    public static class EnumSerializer extends ImmutableSerializer<Enum> {
        private Object[] enumConstants;

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Enum>) cls);
        }

        public EnumSerializer(Class<? extends Enum> cls) {
            setAcceptsNull(true);
            this.enumConstants = cls.getEnumConstants();
            if (this.enumConstants == null && !Enum.class.equals(cls)) {
                throw new IllegalArgumentException("The type must be an enum: " + cls);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Enum r7) {
            if (r7 == null) {
                output.writeVarInt(0, true);
            } else {
                output.writeVarInt(r7.ordinal() + 1, true);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Enum read2(Kryo kryo, Input input, Class<? extends Enum> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            int i = readVarInt - 1;
            if (i < 0 || i > this.enumConstants.length - 1) {
                throw new KryoException("Invalid ordinal for enum \"" + cls.getName() + "\": " + i);
            }
            return (Enum) this.enumConstants[i];
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$EnumSetSerializer.class */
    public static class EnumSetSerializer extends Serializer<EnumSet> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, EnumSet enumSet) {
            Serializer serializer;
            if (enumSet.isEmpty()) {
                EnumSet complementOf = EnumSet.complementOf(enumSet);
                if (complementOf.isEmpty()) {
                    throw new KryoException("An EnumSet must have a defined Enum to be serialized.");
                }
                serializer = kryo.writeClass(output, complementOf.iterator().next().getClass()).getSerializer();
            } else {
                serializer = kryo.writeClass(output, enumSet.iterator().next().getClass()).getSerializer();
            }
            output.writeVarInt(enumSet.size(), true);
            Iterator it = enumSet.iterator();
            while (it.hasNext()) {
                serializer.write(kryo, output, it.next());
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public EnumSet read2(Kryo kryo, Input input, Class<? extends EnumSet> cls) {
            Registration readClass = kryo.readClass(input);
            EnumSet noneOf = EnumSet.noneOf(readClass.getType());
            Serializer serializer = readClass.getSerializer();
            int readVarInt = input.readVarInt(true);
            for (int i = 0; i < readVarInt; i++) {
                noneOf.add(serializer.read2(kryo, input, null));
            }
            return noneOf;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public EnumSet copy(Kryo kryo, EnumSet enumSet) {
            return EnumSet.copyOf(enumSet);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$CurrencySerializer.class */
    public static class CurrencySerializer extends ImmutableSerializer<Currency> {
        public CurrencySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Currency>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Currency currency) {
            output.writeString(currency == null ? null : currency.getCurrencyCode());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Currency read2(Kryo kryo, Input input, Class<? extends Currency> cls) {
            String readString = input.readString();
            if (readString == null) {
                return null;
            }
            return Currency.getInstance(readString);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$StringBufferSerializer.class */
    public static class StringBufferSerializer extends Serializer<StringBuffer> {
        public StringBufferSerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, StringBuffer stringBuffer) {
            output.writeString(stringBuffer == null ? null : stringBuffer.toString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public StringBuffer read2(Kryo kryo, Input input, Class<? extends StringBuffer> cls) {
            String readString = input.readString();
            if (readString == null) {
                return null;
            }
            return new StringBuffer(readString);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public StringBuffer copy(Kryo kryo, StringBuffer stringBuffer) {
            return new StringBuffer(stringBuffer);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$StringBuilderSerializer.class */
    public static class StringBuilderSerializer extends Serializer<StringBuilder> {
        public StringBuilderSerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, StringBuilder sb) {
            output.writeString(sb == null ? null : sb.toString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public StringBuilder read2(Kryo kryo, Input input, Class<? extends StringBuilder> cls) {
            return input.readStringBuilder();
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public StringBuilder copy(Kryo kryo, StringBuilder sb) {
            return new StringBuilder(sb);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$KryoSerializableSerializer.class */
    public static class KryoSerializableSerializer extends Serializer<KryoSerializable> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, KryoSerializable kryoSerializable) {
            kryoSerializable.write(kryo, output);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public KryoSerializable read2(Kryo kryo, Input input, Class<? extends KryoSerializable> cls) {
            KryoSerializable kryoSerializable = (KryoSerializable) kryo.newInstance(cls);
            kryo.reference(kryoSerializable);
            kryoSerializable.read(kryo, input);
            return kryoSerializable;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$CollectionsEmptyListSerializer.class */
    public static class CollectionsEmptyListSerializer extends ImmutableSerializer<Collection> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Collection>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Collection collection) {
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Collection read2(Kryo kryo, Input input, Class<? extends Collection> cls) {
            return Collections.EMPTY_LIST;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$CollectionsEmptyMapSerializer.class */
    public static class CollectionsEmptyMapSerializer extends ImmutableSerializer<Map> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Map>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Map map) {
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Map read2(Kryo kryo, Input input, Class<? extends Map> cls) {
            return Collections.EMPTY_MAP;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$CollectionsEmptySetSerializer.class */
    public static class CollectionsEmptySetSerializer extends ImmutableSerializer<Set> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Set>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Set set) {
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Set read2(Kryo kryo, Input input, Class<? extends Set> cls) {
            return Collections.EMPTY_SET;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$CollectionsSingletonListSerializer.class */
    public static class CollectionsSingletonListSerializer extends Serializer<List> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, List list) {
            kryo.writeClassAndObject(output, list.get(0));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public List read2(Kryo kryo, Input input, Class<? extends List> cls) {
            return Collections.singletonList(kryo.readClassAndObject(input));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public List copy(Kryo kryo, List list) {
            return Collections.singletonList(kryo.copy(list.get(0)));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$CollectionsSingletonMapSerializer.class */
    public static class CollectionsSingletonMapSerializer extends Serializer<Map> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Map map) {
            Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
            kryo.writeClassAndObject(output, entry.getKey());
            kryo.writeClassAndObject(output, entry.getValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Map read2(Kryo kryo, Input input, Class<? extends Map> cls) {
            return Collections.singletonMap(kryo.readClassAndObject(input), kryo.readClassAndObject(input));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public Map copy(Kryo kryo, Map map) {
            Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
            return Collections.singletonMap(kryo.copy(entry.getKey()), kryo.copy(entry.getValue()));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$CollectionsSingletonSetSerializer.class */
    public static class CollectionsSingletonSetSerializer extends Serializer<Set> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Set set) {
            kryo.writeClassAndObject(output, set.iterator().next());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Set read2(Kryo kryo, Input input, Class<? extends Set> cls) {
            return Collections.singleton(kryo.readClassAndObject(input));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public Set copy(Kryo kryo, Set set) {
            return Collections.singleton(kryo.copy(set.iterator().next()));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$TimeZoneSerializer.class */
    public static class TimeZoneSerializer extends ImmutableSerializer<TimeZone> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends TimeZone>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, TimeZone timeZone) {
            output.writeString(timeZone.getID());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public TimeZone read2(Kryo kryo, Input input, Class<? extends TimeZone> cls) {
            return TimeZone.getTimeZone(input.readString());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$CalendarSerializer.class */
    public static class CalendarSerializer extends Serializer<Calendar> {
        private static final long DEFAULT_GREGORIAN_CUTOVER = -12219292800000L;
        TimeZoneSerializer timeZoneSerializer = new TimeZoneSerializer();

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Calendar calendar) {
            this.timeZoneSerializer.write(kryo, output, calendar.getTimeZone());
            output.writeVarLong(calendar.getTimeInMillis(), true);
            output.writeBoolean(calendar.isLenient());
            output.writeInt(calendar.getFirstDayOfWeek(), true);
            output.writeInt(calendar.getMinimalDaysInFirstWeek(), true);
            if (calendar instanceof GregorianCalendar) {
                output.writeVarLong(((GregorianCalendar) calendar).getGregorianChange().getTime(), false);
            } else {
                output.writeVarLong(DEFAULT_GREGORIAN_CUTOVER, false);
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Calendar read2(Kryo kryo, Input input, Class<? extends Calendar> cls) {
            Calendar calendar = Calendar.getInstance(this.timeZoneSerializer.read2(kryo, input, TimeZone.class));
            calendar.setTimeInMillis(input.readVarLong(true));
            calendar.setLenient(input.readBoolean());
            calendar.setFirstDayOfWeek(input.readInt(true));
            calendar.setMinimalDaysInFirstWeek(input.readInt(true));
            long readVarLong = input.readVarLong(false);
            if (TimeZone.class != DEFAULT_GREGORIAN_CUTOVER && (calendar instanceof GregorianCalendar)) {
                ((GregorianCalendar) calendar).setGregorianChange(new Date(readVarLong));
            }
            return calendar;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public Calendar copy(Kryo kryo, Calendar calendar) {
            return (Calendar) calendar.clone();
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$TreeMapSerializer.class */
    public static class TreeMapSerializer extends MapSerializer<TreeMap> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.esotericsoftware.kryo.serializers.MapSerializer
        public void writeHeader(Kryo kryo, Output output, TreeMap treeMap) {
            kryo.writeClassAndObject(output, treeMap.comparator());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.serializers.MapSerializer
        public TreeMap create(Kryo kryo, Input input, Class<? extends TreeMap> cls, int i) {
            return createTreeMap(cls, (Comparator) kryo.readClassAndObject(input));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.esotericsoftware.kryo.serializers.MapSerializer
        public TreeMap createCopy(Kryo kryo, TreeMap treeMap) {
            return createTreeMap(treeMap.getClass(), treeMap.comparator());
        }

        private TreeMap createTreeMap(Class<? extends TreeMap> cls, Comparator comparator) {
            if (cls == TreeMap.class || cls == null) {
                return new TreeMap(comparator);
            }
            try {
                Constructor<? extends TreeMap> constructor = cls.getConstructor(Comparator.class);
                if (!constructor.isAccessible()) {
                    try {
                        constructor.setAccessible(true);
                    } catch (SecurityException unused) {
                    }
                }
                return constructor.newInstance(comparator);
            } catch (Exception e) {
                throw new KryoException(e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$ConcurrentSkipListMapSerializer.class */
    public static class ConcurrentSkipListMapSerializer extends MapSerializer<ConcurrentSkipListMap> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.esotericsoftware.kryo.serializers.MapSerializer
        public void writeHeader(Kryo kryo, Output output, ConcurrentSkipListMap concurrentSkipListMap) {
            kryo.writeClassAndObject(output, concurrentSkipListMap.comparator());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.serializers.MapSerializer
        public ConcurrentSkipListMap create(Kryo kryo, Input input, Class<? extends ConcurrentSkipListMap> cls, int i) {
            return createConcurrentSkipListMap(cls, (Comparator) kryo.readClassAndObject(input));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.esotericsoftware.kryo.serializers.MapSerializer
        public ConcurrentSkipListMap createCopy(Kryo kryo, ConcurrentSkipListMap concurrentSkipListMap) {
            return createConcurrentSkipListMap(concurrentSkipListMap.getClass(), concurrentSkipListMap.comparator());
        }

        private ConcurrentSkipListMap createConcurrentSkipListMap(Class<? extends ConcurrentSkipListMap> cls, Comparator comparator) {
            if (cls == ConcurrentSkipListMap.class || cls == null) {
                return new ConcurrentSkipListMap(comparator);
            }
            try {
                Constructor<? extends ConcurrentSkipListMap> constructor = cls.getConstructor(Comparator.class);
                if (!constructor.isAccessible()) {
                    try {
                        constructor.setAccessible(true);
                    } catch (SecurityException unused) {
                    }
                }
                return constructor.newInstance(comparator);
            } catch (Exception e) {
                throw new KryoException(e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$TreeSetSerializer.class */
    public static class TreeSetSerializer extends CollectionSerializer<TreeSet> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        public void writeHeader(Kryo kryo, Output output, TreeSet treeSet) {
            kryo.writeClassAndObject(output, treeSet.comparator());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        /* renamed from: create */
        public TreeSet create2(Kryo kryo, Input input, Class<? extends TreeSet> cls, int i) {
            return createTreeSet(cls, (Comparator) kryo.readClassAndObject(input));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        public TreeSet createCopy(Kryo kryo, TreeSet treeSet) {
            return createTreeSet(treeSet.getClass(), treeSet.comparator());
        }

        private TreeSet createTreeSet(Class<? extends Collection> cls, Comparator comparator) {
            if (cls == TreeSet.class || cls == null) {
                return new TreeSet(comparator);
            }
            try {
                Constructor<? extends Collection> constructor = cls.getConstructor(Comparator.class);
                if (!constructor.isAccessible()) {
                    try {
                        constructor.setAccessible(true);
                    } catch (SecurityException unused) {
                    }
                }
                return (TreeSet) constructor.newInstance(comparator);
            } catch (Exception e) {
                throw new KryoException(e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$PriorityQueueSerializer.class */
    public static class PriorityQueueSerializer extends CollectionSerializer<PriorityQueue> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        public void writeHeader(Kryo kryo, Output output, PriorityQueue priorityQueue) {
            kryo.writeClassAndObject(output, priorityQueue.comparator());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        /* renamed from: create */
        public PriorityQueue create2(Kryo kryo, Input input, Class<? extends PriorityQueue> cls, int i) {
            return createPriorityQueue(cls, i, (Comparator) kryo.readClassAndObject(input));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        public PriorityQueue createCopy(Kryo kryo, PriorityQueue priorityQueue) {
            return createPriorityQueue(priorityQueue.getClass(), priorityQueue.size(), priorityQueue.comparator());
        }

        private PriorityQueue createPriorityQueue(Class<? extends Collection> cls, int i, Comparator comparator) {
            int max = Math.max(i, 1);
            if (cls == PriorityQueue.class || cls == null) {
                return new PriorityQueue(max, comparator);
            }
            try {
                Constructor<? extends Collection> constructor = cls.getConstructor(Integer.TYPE, Comparator.class);
                if (!constructor.isAccessible()) {
                    try {
                        constructor.setAccessible(true);
                    } catch (SecurityException unused) {
                    }
                }
                return (PriorityQueue) constructor.newInstance(Integer.valueOf(max), comparator);
            } catch (Exception e) {
                throw new KryoException(e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$LocaleSerializer.class */
    public static class LocaleSerializer extends ImmutableSerializer<Locale> {
        public static final Locale SPANISH = new Locale("es", "", "");
        public static final Locale SPAIN = new Locale("es", "ES", "");

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Locale>) cls);
        }

        protected Locale create(String str, String str2, String str3) {
            Locale locale = Locale.getDefault();
            if (isSameLocale(locale, str, str2, str3)) {
                return locale;
            }
            return (locale == Locale.US || !isSameLocale(Locale.US, str, str2, str3)) ? isSameLocale(Locale.ENGLISH, str, str2, str3) ? Locale.ENGLISH : isSameLocale(Locale.GERMAN, str, str2, str3) ? Locale.GERMAN : isSameLocale(SPANISH, str, str2, str3) ? SPANISH : isSameLocale(Locale.FRENCH, str, str2, str3) ? Locale.FRENCH : isSameLocale(Locale.ITALIAN, str, str2, str3) ? Locale.ITALIAN : isSameLocale(Locale.JAPANESE, str, str2, str3) ? Locale.JAPANESE : isSameLocale(Locale.KOREAN, str, str2, str3) ? Locale.KOREAN : isSameLocale(Locale.SIMPLIFIED_CHINESE, str, str2, str3) ? Locale.SIMPLIFIED_CHINESE : isSameLocale(Locale.CHINESE, str, str2, str3) ? Locale.CHINESE : isSameLocale(Locale.TRADITIONAL_CHINESE, str, str2, str3) ? Locale.TRADITIONAL_CHINESE : isSameLocale(Locale.UK, str, str2, str3) ? Locale.UK : isSameLocale(Locale.GERMANY, str, str2, str3) ? Locale.GERMANY : isSameLocale(SPAIN, str, str2, str3) ? SPAIN : isSameLocale(Locale.FRANCE, str, str2, str3) ? Locale.FRANCE : isSameLocale(Locale.ITALY, str, str2, str3) ? Locale.ITALY : isSameLocale(Locale.JAPAN, str, str2, str3) ? Locale.JAPAN : isSameLocale(Locale.KOREA, str, str2, str3) ? Locale.KOREA : isSameLocale(Locale.CANADA, str, str2, str3) ? Locale.CANADA : isSameLocale(Locale.CANADA_FRENCH, str, str2, str3) ? Locale.CANADA_FRENCH : new Locale(str, str2, str3) : Locale.US;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Locale locale) {
            output.writeAscii(locale.getLanguage());
            output.writeAscii(locale.getCountry());
            output.writeString(locale.getVariant());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Locale read2(Kryo kryo, Input input, Class<? extends Locale> cls) {
            return create(input.readString(), input.readString(), input.readString());
        }

        protected static boolean isSameLocale(Locale locale, String str, String str2, String str3) {
            return locale.getLanguage().equals(str) && locale.getCountry().equals(str2) && locale.getVariant().equals(str3);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$CharsetSerializer.class */
    public static class CharsetSerializer extends ImmutableSerializer<Charset> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Charset>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Charset charset) {
            output.writeString(charset.name());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Charset read2(Kryo kryo, Input input, Class<? extends Charset> cls) {
            return Charset.forName(input.readString());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$URLSerializer.class */
    public static class URLSerializer extends ImmutableSerializer<URL> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends URL>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, URL url) {
            output.writeString(url.toExternalForm());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public URL read2(Kryo kryo, Input input, Class<? extends URL> cls) {
            try {
                return new URL(input.readString());
            } catch (MalformedURLException e) {
                throw new KryoException(e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$ArraysAsListSerializer.class */
    public static class ArraysAsListSerializer extends CollectionSerializer<List> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        /* renamed from: create, reason: merged with bridge method [inline-methods] */
        public List create2(Kryo kryo, Input input, Class<? extends List> cls, int i) {
            return new ArrayList(i);
        }

        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public List read2(Kryo kryo, Input input, Class cls) {
            List list = (List) super.read2(kryo, input, cls);
            if (list == null) {
                return null;
            }
            return Arrays.asList(list.toArray());
        }

        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        public List copy(Kryo kryo, List list) {
            Object[] objArr = new Object[list.size()];
            List asList = Arrays.asList(objArr);
            kryo.reference(asList);
            for (int i = 0; i < list.size(); i++) {
                objArr[i] = kryo.copy(list.get(i));
            }
            return asList;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$BitSetSerializer.class */
    public static class BitSetSerializer extends Serializer<BitSet> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, BitSet bitSet) {
            long[] longArray = bitSet.toLongArray();
            output.writeVarInt(longArray.length, true);
            output.writeLongs(longArray, 0, longArray.length);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read, reason: merged with bridge method [inline-methods] */
        public BitSet read2(Kryo kryo, Input input, Class<? extends BitSet> cls) {
            return BitSet.valueOf(input.readLongs(input.readVarInt(true)));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public BitSet copy(Kryo kryo, BitSet bitSet) {
            return BitSet.valueOf(bitSet.toLongArray());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$PatternSerializer.class */
    public static class PatternSerializer extends ImmutableSerializer<Pattern> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends Pattern>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Pattern pattern) {
            output.writeString(pattern.pattern());
            output.writeInt(pattern.flags(), true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Pattern read2(Kryo kryo, Input input, Class<? extends Pattern> cls) {
            return Pattern.compile(input.readString(), input.readInt(true));
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$URISerializer.class */
    public static class URISerializer extends ImmutableSerializer<URI> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends URI>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, URI uri) {
            output.writeString(uri.toString());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public URI read2(Kryo kryo, Input input, Class<? extends URI> cls) {
            try {
                return new URI(input.readString());
            } catch (URISyntaxException e) {
                throw new KryoException(e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$UUIDSerializer.class */
    public static class UUIDSerializer extends ImmutableSerializer<UUID> {
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends UUID>) cls);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, UUID uuid) {
            output.writeLong(uuid.getMostSignificantBits());
            output.writeLong(uuid.getLeastSignificantBits());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public UUID read2(Kryo kryo, Input input, Class<? extends UUID> cls) {
            return new UUID(input.readLong(), input.readLong());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$AtomicBooleanSerializer.class */
    public static class AtomicBooleanSerializer extends Serializer<AtomicBoolean> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, AtomicBoolean atomicBoolean) {
            output.writeBoolean(atomicBoolean.get());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public AtomicBoolean read2(Kryo kryo, Input input, Class<? extends AtomicBoolean> cls) {
            return new AtomicBoolean(input.readBoolean());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public AtomicBoolean copy(Kryo kryo, AtomicBoolean atomicBoolean) {
            return new AtomicBoolean(atomicBoolean.get());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$AtomicIntegerSerializer.class */
    public static class AtomicIntegerSerializer extends Serializer<AtomicInteger> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, AtomicInteger atomicInteger) {
            output.writeInt(atomicInteger.get());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public AtomicInteger read2(Kryo kryo, Input input, Class<? extends AtomicInteger> cls) {
            return new AtomicInteger(input.readInt());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public AtomicInteger copy(Kryo kryo, AtomicInteger atomicInteger) {
            return new AtomicInteger(atomicInteger.get());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$AtomicLongSerializer.class */
    public static class AtomicLongSerializer extends Serializer<AtomicLong> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, AtomicLong atomicLong) {
            output.writeLong(atomicLong.get());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public AtomicLong read2(Kryo kryo, Input input, Class<? extends AtomicLong> cls) {
            return new AtomicLong(input.readLong());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public AtomicLong copy(Kryo kryo, AtomicLong atomicLong) {
            return new AtomicLong(atomicLong.get());
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultSerializers$AtomicReferenceSerializer.class */
    public static class AtomicReferenceSerializer extends Serializer<AtomicReference> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, AtomicReference atomicReference) {
            kryo.writeClassAndObject(output, atomicReference.get());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public AtomicReference read2(Kryo kryo, Input input, Class<? extends AtomicReference> cls) {
            return new AtomicReference(kryo.readClassAndObject(input));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public AtomicReference copy(Kryo kryo, AtomicReference atomicReference) {
            return new AtomicReference(kryo.copy(atomicReference.get()));
        }
    }
}
