package com.esotericsoftware.kryo;

import com.esotericsoftware.kryo.SerializerFactory;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.ClosureSerializer;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.esotericsoftware.kryo.serializers.ImmutableCollectionsSerializers;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import com.esotericsoftware.kryo.serializers.OptionalSerializers;
import com.esotericsoftware.kryo.serializers.RecordSerializer;
import com.esotericsoftware.kryo.serializers.TimeSerializers;
import com.esotericsoftware.kryo.util.DefaultClassResolver;
import com.esotericsoftware.kryo.util.DefaultGenerics;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import com.esotericsoftware.kryo.util.Generics;
import com.esotericsoftware.kryo.util.IdentityMap;
import com.esotericsoftware.kryo.util.IntArray;
import com.esotericsoftware.kryo.util.MapReferenceResolver;
import com.esotericsoftware.kryo.util.NoGenerics;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListMap;
import org.c.b.a;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/Kryo.class */
public class Kryo {
    public static final byte NULL = 0;
    public static final byte NOT_NULL = 1;
    private static final int REF = -1;
    private static final int NO_REF = -2;
    private static final int DEFAULT_SERIALIZER_SIZE = 68;
    private SerializerFactory defaultSerializer;
    private final ArrayList<DefaultSerializerEntry> defaultSerializers;
    private final int lowPriorityDefaultSerializerCount;
    private final ClassResolver classResolver;
    private int nextRegisterID;
    private ClassLoader classLoader;
    private a strategy;
    private boolean registrationRequired;
    private boolean warnUnregisteredClasses;
    private int depth;
    private int maxDepth;
    private boolean autoReset;
    private volatile Thread thread;
    private ObjectMap context;
    private ObjectMap graphContext;
    private ReferenceResolver referenceResolver;
    private final IntArray readReferenceIds;
    private boolean references;
    private boolean copyReferences;
    private Object readObject;
    private int copyDepth;
    private boolean copyShallow;
    private IdentityMap originalToCopy;
    private Object needsCopyReference;
    private Generics generics;

    public Kryo() {
        this(new DefaultClassResolver(), null);
    }

    public Kryo(ReferenceResolver referenceResolver) {
        this(new DefaultClassResolver(), referenceResolver);
    }

    public Kryo(ClassResolver classResolver, ReferenceResolver referenceResolver) {
        this.defaultSerializer = new SerializerFactory.FieldSerializerFactory();
        this.defaultSerializers = new ArrayList<>(68);
        this.classLoader = getClass().getClassLoader();
        this.strategy = new DefaultInstantiatorStrategy();
        this.registrationRequired = true;
        this.maxDepth = Integer.MAX_VALUE;
        this.autoReset = true;
        this.readReferenceIds = new IntArray(0);
        this.copyReferences = true;
        this.generics = new DefaultGenerics(this);
        if (classResolver == null) {
            throw new IllegalArgumentException("classResolver cannot be null.");
        }
        this.classResolver = classResolver;
        classResolver.setKryo(this);
        this.referenceResolver = referenceResolver;
        if (referenceResolver != null) {
            referenceResolver.setKryo(this);
            this.references = true;
        }
        addDefaultSerializer(byte[].class, DefaultArraySerializers.ByteArraySerializer.class);
        addDefaultSerializer(char[].class, DefaultArraySerializers.CharArraySerializer.class);
        addDefaultSerializer(short[].class, DefaultArraySerializers.ShortArraySerializer.class);
        addDefaultSerializer(int[].class, DefaultArraySerializers.IntArraySerializer.class);
        addDefaultSerializer(long[].class, DefaultArraySerializers.LongArraySerializer.class);
        addDefaultSerializer(float[].class, DefaultArraySerializers.FloatArraySerializer.class);
        addDefaultSerializer(double[].class, DefaultArraySerializers.DoubleArraySerializer.class);
        addDefaultSerializer(boolean[].class, DefaultArraySerializers.BooleanArraySerializer.class);
        addDefaultSerializer(String[].class, DefaultArraySerializers.StringArraySerializer.class);
        addDefaultSerializer(Object[].class, DefaultArraySerializers.ObjectArraySerializer.class);
        addDefaultSerializer(BigInteger.class, DefaultSerializers.BigIntegerSerializer.class);
        addDefaultSerializer(BigDecimal.class, DefaultSerializers.BigDecimalSerializer.class);
        addDefaultSerializer(Class.class, DefaultSerializers.ClassSerializer.class);
        addDefaultSerializer(Date.class, DefaultSerializers.DateSerializer.class);
        addDefaultSerializer(Enum.class, DefaultSerializers.EnumSerializer.class);
        addDefaultSerializer(EnumSet.class, DefaultSerializers.EnumSetSerializer.class);
        addDefaultSerializer(Currency.class, DefaultSerializers.CurrencySerializer.class);
        addDefaultSerializer(StringBuffer.class, DefaultSerializers.StringBufferSerializer.class);
        addDefaultSerializer(StringBuilder.class, DefaultSerializers.StringBuilderSerializer.class);
        addDefaultSerializer(Collections.EMPTY_LIST.getClass(), DefaultSerializers.CollectionsEmptyListSerializer.class);
        addDefaultSerializer(Collections.EMPTY_MAP.getClass(), DefaultSerializers.CollectionsEmptyMapSerializer.class);
        addDefaultSerializer(Collections.EMPTY_SET.getClass(), DefaultSerializers.CollectionsEmptySetSerializer.class);
        addDefaultSerializer(Collections.singletonList(null).getClass(), DefaultSerializers.CollectionsSingletonListSerializer.class);
        addDefaultSerializer(Collections.singletonMap(null, null).getClass(), DefaultSerializers.CollectionsSingletonMapSerializer.class);
        addDefaultSerializer(Collections.singleton(null).getClass(), DefaultSerializers.CollectionsSingletonSetSerializer.class);
        addDefaultSerializer(TreeSet.class, DefaultSerializers.TreeSetSerializer.class);
        addDefaultSerializer(Collection.class, CollectionSerializer.class);
        addDefaultSerializer(ConcurrentSkipListMap.class, DefaultSerializers.ConcurrentSkipListMapSerializer.class);
        addDefaultSerializer(TreeMap.class, DefaultSerializers.TreeMapSerializer.class);
        addDefaultSerializer(Map.class, MapSerializer.class);
        addDefaultSerializer(TimeZone.class, DefaultSerializers.TimeZoneSerializer.class);
        addDefaultSerializer(Calendar.class, DefaultSerializers.CalendarSerializer.class);
        addDefaultSerializer(Locale.class, DefaultSerializers.LocaleSerializer.class);
        addDefaultSerializer(Charset.class, DefaultSerializers.CharsetSerializer.class);
        addDefaultSerializer(URL.class, DefaultSerializers.URLSerializer.class);
        addDefaultSerializer(Arrays.asList(new Object[0]).getClass(), DefaultSerializers.ArraysAsListSerializer.class);
        addDefaultSerializer(Void.TYPE, new DefaultSerializers.VoidSerializer());
        addDefaultSerializer(PriorityQueue.class, new DefaultSerializers.PriorityQueueSerializer());
        addDefaultSerializer(BitSet.class, new DefaultSerializers.BitSetSerializer());
        addDefaultSerializer(KryoSerializable.class, DefaultSerializers.KryoSerializableSerializer.class);
        OptionalSerializers.addDefaultSerializers(this);
        TimeSerializers.addDefaultSerializers(this);
        ImmutableCollectionsSerializers.addDefaultSerializers(this);
        if (Util.isClassAvailable("java.lang.Record")) {
            addDefaultSerializer("java.lang.Record", RecordSerializer.class);
        }
        this.lowPriorityDefaultSerializerCount = this.defaultSerializers.size();
        register(Integer.TYPE, new DefaultSerializers.IntSerializer());
        register(String.class, new DefaultSerializers.StringSerializer());
        register(Float.TYPE, new DefaultSerializers.FloatSerializer());
        register(Boolean.TYPE, new DefaultSerializers.BooleanSerializer());
        register(Byte.TYPE, new DefaultSerializers.ByteSerializer());
        register(Character.TYPE, new DefaultSerializers.CharSerializer());
        register(Short.TYPE, new DefaultSerializers.ShortSerializer());
        register(Long.TYPE, new DefaultSerializers.LongSerializer());
        register(Double.TYPE, new DefaultSerializers.DoubleSerializer());
    }

    public void setDefaultSerializer(SerializerFactory serializerFactory) {
        if (serializerFactory == null) {
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        this.defaultSerializer = serializerFactory;
    }

    public void setDefaultSerializer(Class<? extends Serializer> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        this.defaultSerializer = new SerializerFactory.ReflectionSerializerFactory(cls);
    }

    public void addDefaultSerializer(Class cls, Serializer serializer) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if (serializer == null) {
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        insertDefaultSerializer(cls, new SerializerFactory.SingletonSerializerFactory(serializer));
    }

    public void addDefaultSerializer(Class cls, SerializerFactory serializerFactory) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if (serializerFactory == null) {
            throw new IllegalArgumentException("serializerFactory cannot be null.");
        }
        insertDefaultSerializer(cls, serializerFactory);
    }

    private void addDefaultSerializer(String str, Class<? extends Serializer> cls) {
        try {
            addDefaultSerializer(Class.forName(str), cls);
        } catch (ClassNotFoundException unused) {
            throw new KryoException("default serializer cannot be added: " + str);
        }
    }

    public void addDefaultSerializer(Class cls, Class<? extends Serializer> cls2) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if (cls2 == null) {
            throw new IllegalArgumentException("serializerClass cannot be null.");
        }
        insertDefaultSerializer(cls, new SerializerFactory.ReflectionSerializerFactory(cls2));
    }

    private int insertDefaultSerializer(Class cls, SerializerFactory serializerFactory) {
        int i = 0;
        int size = this.defaultSerializers.size() - this.lowPriorityDefaultSerializerCount;
        for (int i2 = 0; i2 < size; i2++) {
            if (cls.isAssignableFrom(this.defaultSerializers.get(i2).type)) {
                i = i2 + 1;
            }
        }
        this.defaultSerializers.add(i, new DefaultSerializerEntry(cls, serializerFactory));
        return i;
    }

    public Serializer getDefaultSerializer(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        Serializer defaultSerializerForAnnotatedType = getDefaultSerializerForAnnotatedType(cls);
        if (defaultSerializerForAnnotatedType != null) {
            return defaultSerializerForAnnotatedType;
        }
        int size = this.defaultSerializers.size();
        for (int i = 0; i < size; i++) {
            DefaultSerializerEntry defaultSerializerEntry = this.defaultSerializers.get(i);
            if (defaultSerializerEntry.type.isAssignableFrom(cls) && defaultSerializerEntry.serializerFactory.isSupported(cls)) {
                return defaultSerializerEntry.serializerFactory.newSerializer(this, cls);
            }
        }
        return newDefaultSerializer(cls);
    }

    protected Serializer getDefaultSerializerForAnnotatedType(Class cls) {
        if (cls.isAnnotationPresent(DefaultSerializer.class)) {
            DefaultSerializer defaultSerializer = (DefaultSerializer) cls.getAnnotation(DefaultSerializer.class);
            return Util.newFactory(defaultSerializer.serializerFactory(), defaultSerializer.value()).newSerializer(this, cls);
        }
        return null;
    }

    protected Serializer newDefaultSerializer(Class cls) {
        return this.defaultSerializer.newSerializer(this, cls);
    }

    public Registration register(Class cls) {
        Registration registration = this.classResolver.getRegistration(cls);
        if (registration != null) {
            return registration;
        }
        return register(cls, getDefaultSerializer(cls));
    }

    public Registration register(Class cls, int i) {
        Registration registration = this.classResolver.getRegistration(cls);
        if (registration != null) {
            return registration;
        }
        return register(cls, getDefaultSerializer(cls), i);
    }

    public Registration register(Class cls, Serializer serializer) {
        Registration registration = this.classResolver.getRegistration(cls);
        if (registration != null) {
            registration.setSerializer(serializer);
            return registration;
        }
        return this.classResolver.register(new Registration(cls, serializer, getNextRegistrationId()));
    }

    public Registration register(Class cls, Serializer serializer, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("id must be >= 0: " + i);
        }
        return register(new Registration(cls, serializer, i));
    }

    public Registration register(Registration registration) {
        int id = registration.getId();
        if (id < 0) {
            throw new IllegalArgumentException("id must be > 0: " + id);
        }
        Registration unregister = this.classResolver.unregister(id);
        if (Log.DEBUG && unregister != null && unregister.getType() != registration.getType()) {
            Log.debug("kryo", "Registration overwritten: " + unregister + " -> " + registration);
        }
        return this.classResolver.register(registration);
    }

    public int getNextRegistrationId() {
        while (this.nextRegisterID != -2) {
            if (this.classResolver.getRegistration(this.nextRegisterID) == null) {
                return this.nextRegisterID;
            }
            this.nextRegisterID++;
        }
        throw new KryoException("No registration IDs are available.");
    }

    public Registration getRegistration(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        Registration registration = this.classResolver.getRegistration(cls);
        Registration registration2 = registration;
        if (registration == null) {
            if (isProxy(cls)) {
                registration2 = getRegistration(InvocationHandler.class);
            } else {
                if (cls.isEnum() || !Enum.class.isAssignableFrom(cls) || cls == Enum.class) {
                    if (EnumSet.class.isAssignableFrom(cls)) {
                        registration2 = this.classResolver.getRegistration(EnumSet.class);
                    } else if (isClosure(cls)) {
                        registration2 = this.classResolver.getRegistration(ClosureSerializer.Closure.class);
                    }
                }
                while (true) {
                    Class superclass = cls.getSuperclass();
                    cls = superclass;
                    if (superclass == null) {
                        break;
                    }
                    if (cls.isEnum()) {
                        registration2 = this.classResolver.getRegistration(cls);
                        break;
                    }
                }
            }
            if (registration2 == null) {
                if (this.registrationRequired) {
                    throw new IllegalArgumentException(unregisteredClassMessage(cls));
                }
                if (Log.WARN && this.warnUnregisteredClasses) {
                    Log.warn(unregisteredClassMessage(cls));
                }
                registration2 = this.classResolver.registerImplicit(cls);
            }
        }
        return registration2;
    }

    protected String unregisteredClassMessage(Class cls) {
        return "Class is not registered: " + Util.className(cls) + "\nNote: To register this class use: kryo.register(" + Util.canonicalName(cls) + ".class);";
    }

    public Registration getRegistration(int i) {
        return this.classResolver.getRegistration(i);
    }

    public Serializer getSerializer(Class cls) {
        return getRegistration(cls).getSerializer();
    }

    public Registration writeClass(Output output, Class cls) {
        if (output == null) {
            throw new IllegalArgumentException("output cannot be null.");
        }
        try {
            return this.classResolver.writeClass(output, cls);
        } finally {
            if (this.depth == 0 && this.autoReset) {
                reset();
            }
        }
    }

    public void writeObject(Output output, Object obj) {
        int i;
        boolean z;
        if (output == null) {
            throw new IllegalArgumentException("output cannot be null.");
        }
        if (obj == null) {
            throw new IllegalArgumentException("object cannot be null.");
        }
        beginObject();
        try {
            if (this.references && writeReferenceOrNull(output, obj, false)) {
                if (i == 0) {
                    if (z) {
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Write", obj, output.position());
            }
            getRegistration(obj.getClass()).getSerializer().write(this, output, obj);
            int i2 = this.depth - 1;
            this.depth = i2;
            if (i2 == 0 && this.autoReset) {
                reset();
            }
        } finally {
            i = this.depth - 1;
            this.depth = i;
            if (i == 0 && this.autoReset) {
                reset();
            }
        }
    }

    public void writeObject(Output output, Object obj, Serializer serializer) {
        int i;
        boolean z;
        if (output == null) {
            throw new IllegalArgumentException("output cannot be null.");
        }
        if (obj == null) {
            throw new IllegalArgumentException("object cannot be null.");
        }
        if (serializer == null) {
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        beginObject();
        try {
            if (this.references && writeReferenceOrNull(output, obj, false)) {
                if (i == 0) {
                    if (z) {
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Write", obj, output.position());
            }
            serializer.write(this, output, obj);
            int i2 = this.depth - 1;
            this.depth = i2;
            if (i2 == 0 && this.autoReset) {
                reset();
            }
        } finally {
            i = this.depth - 1;
            this.depth = i;
            if (i == 0 && this.autoReset) {
                reset();
            }
        }
    }

    public void writeObjectOrNull(Output output, Object obj, Class cls) {
        int i;
        boolean z;
        if (output == null) {
            throw new IllegalArgumentException("output cannot be null.");
        }
        beginObject();
        try {
            Serializer serializer = getRegistration(cls).getSerializer();
            if (this.references) {
                if (writeReferenceOrNull(output, obj, true)) {
                    if (i == 0) {
                        if (z) {
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                }
            } else if (!serializer.getAcceptsNull()) {
                if (obj == null) {
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log("Write", obj, output.position());
                    }
                    output.writeByte((byte) 0);
                    int i2 = this.depth - 1;
                    this.depth = i2;
                    if (i2 != 0 || !this.autoReset) {
                        return;
                    }
                    reset();
                    return;
                }
                if (Log.TRACE) {
                    Log.trace("kryo", "Write: <not null>" + Util.pos(output.position()));
                }
                output.writeByte((byte) 1);
            }
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Write", obj, output.position());
            }
            serializer.write(this, output, obj);
            int i3 = this.depth - 1;
            this.depth = i3;
            if (i3 == 0 && this.autoReset) {
                reset();
            }
        } finally {
            i = this.depth - 1;
            this.depth = i;
            if (i == 0 && this.autoReset) {
                reset();
            }
        }
    }

    public void writeObjectOrNull(Output output, Object obj, Serializer serializer) {
        int i;
        boolean z;
        if (output == null) {
            throw new IllegalArgumentException("output cannot be null.");
        }
        if (serializer == null) {
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        beginObject();
        try {
            if (this.references) {
                if (writeReferenceOrNull(output, obj, true)) {
                    if (i == 0) {
                        if (z) {
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                }
            } else if (!serializer.getAcceptsNull()) {
                if (obj == null) {
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log("Write", null, output.position());
                    }
                    output.writeByte((byte) 0);
                    int i2 = this.depth - 1;
                    this.depth = i2;
                    if (i2 != 0 || !this.autoReset) {
                        return;
                    }
                    reset();
                    return;
                }
                if (Log.TRACE) {
                    Log.trace("kryo", "Write: <not null>" + Util.pos(output.position()));
                }
                output.writeByte((byte) 1);
            }
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Write", obj, output.position());
            }
            serializer.write(this, output, obj);
            int i3 = this.depth - 1;
            this.depth = i3;
            if (i3 == 0 && this.autoReset) {
                reset();
            }
        } finally {
            i = this.depth - 1;
            this.depth = i;
            if (i == 0 && this.autoReset) {
                reset();
            }
        }
    }

    public void writeClassAndObject(Output output, Object obj) {
        int i;
        boolean z;
        if (output == null) {
            throw new IllegalArgumentException("output cannot be null.");
        }
        beginObject();
        try {
            if (obj == null) {
                writeClass(output, null);
                if (i == 0) {
                    if (z) {
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            Registration writeClass = writeClass(output, obj.getClass());
            if (!this.references || !writeReferenceOrNull(output, obj, false)) {
                if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                    Util.log("Write", obj, output.position());
                }
                writeClass.getSerializer().write(this, output, obj);
                int i2 = this.depth - 1;
                this.depth = i2;
                if (i2 == 0 && this.autoReset) {
                    reset();
                    return;
                }
                return;
            }
            int i3 = this.depth - 1;
            this.depth = i3;
            if (i3 != 0 || !this.autoReset) {
                return;
            }
            reset();
        } finally {
            i = this.depth - 1;
            this.depth = i;
            if (i == 0 && this.autoReset) {
                reset();
            }
        }
    }

    boolean writeReferenceOrNull(Output output, Object obj, boolean z) {
        if (obj == null) {
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Write", null, output.position());
            }
            output.writeByte((byte) 0);
            return true;
        }
        if (!this.referenceResolver.useReferences(obj.getClass())) {
            if (z) {
                if (Log.TRACE) {
                    Log.trace("kryo", "Write: <not null>" + Util.pos(output.position()));
                }
                output.writeByte((byte) 1);
                return false;
            }
            return false;
        }
        int writtenId = this.referenceResolver.getWrittenId(obj);
        if (writtenId != -1) {
            if (Log.DEBUG) {
                Log.debug("kryo", "Write reference " + writtenId + ": " + Util.string(obj) + Util.pos(output.position()));
            }
            output.writeVarInt(writtenId + 2, true);
            return true;
        }
        int addWrittenObject = this.referenceResolver.addWrittenObject(obj);
        if (Log.TRACE) {
            Log.trace("kryo", "Write: <not null>" + Util.pos(output.position()));
        }
        output.writeByte((byte) 1);
        if (Log.TRACE) {
            Log.trace("kryo", "Write initial reference " + addWrittenObject + ": " + Util.string(obj) + Util.pos(output.position()));
            return false;
        }
        return false;
    }

    public Registration readClass(Input input) {
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null.");
        }
        try {
            return this.classResolver.readClass(input);
        } finally {
            if (this.depth == 0 && this.autoReset) {
                reset();
            }
        }
    }

    public <T> T readObject(Input input, Class<T> cls) {
        Object read2;
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null.");
        }
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        beginObject();
        try {
            if (this.references) {
                int readReferenceOrNull = readReferenceOrNull(input, cls, false);
                if (readReferenceOrNull == -1) {
                    return (T) this.readObject;
                }
                read2 = getRegistration(cls).getSerializer().read2(this, input, cls);
                if (readReferenceOrNull == this.readReferenceIds.size) {
                    reference(read2);
                }
            } else {
                read2 = getRegistration(cls).getSerializer().read2(this, input, cls);
            }
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Read", read2, input.position());
            }
            T t = (T) read2;
            int i = this.depth - 1;
            this.depth = i;
            if (i == 0 && this.autoReset) {
                reset();
            }
            return t;
        } finally {
            int i2 = this.depth - 1;
            this.depth = i2;
            if (i2 == 0 && this.autoReset) {
                reset();
            }
        }
    }

    public <T> T readObject(Input input, Class<T> cls, Serializer serializer) {
        Object read2;
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null.");
        }
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if (serializer == null) {
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        beginObject();
        try {
            if (this.references) {
                int readReferenceOrNull = readReferenceOrNull(input, cls, false);
                if (readReferenceOrNull == -1) {
                    return (T) this.readObject;
                }
                read2 = serializer.read2(this, input, cls);
                if (readReferenceOrNull == this.readReferenceIds.size) {
                    reference(read2);
                }
            } else {
                read2 = serializer.read2(this, input, cls);
            }
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Read", read2, input.position());
            }
            T t = (T) read2;
            int i = this.depth - 1;
            this.depth = i;
            if (i == 0 && this.autoReset) {
                reset();
            }
            return t;
        } finally {
            int i2 = this.depth - 1;
            this.depth = i2;
            if (i2 == 0 && this.autoReset) {
                reset();
            }
        }
    }

    public <T> T readObjectOrNull(Input input, Class<T> cls) {
        Object read2;
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null.");
        }
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        beginObject();
        try {
            if (this.references) {
                int readReferenceOrNull = readReferenceOrNull(input, cls, true);
                if (readReferenceOrNull == -1) {
                    return (T) this.readObject;
                }
                read2 = getRegistration(cls).getSerializer().read2(this, input, cls);
                if (readReferenceOrNull == this.readReferenceIds.size) {
                    reference(read2);
                }
            } else {
                Serializer serializer = getRegistration(cls).getSerializer();
                if (!serializer.getAcceptsNull() && input.readByte() == 0) {
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log("Read", null, input.position());
                    }
                    int i = this.depth - 1;
                    this.depth = i;
                    if (i == 0 && this.autoReset) {
                        reset();
                    }
                    return null;
                }
                read2 = serializer.read2(this, input, cls);
            }
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Read", read2, input.position());
            }
            T t = (T) read2;
            int i2 = this.depth - 1;
            this.depth = i2;
            if (i2 == 0 && this.autoReset) {
                reset();
            }
            return t;
        } finally {
            int i3 = this.depth - 1;
            this.depth = i3;
            if (i3 == 0 && this.autoReset) {
                reset();
            }
        }
    }

    public <T> T readObjectOrNull(Input input, Class<T> cls, Serializer serializer) {
        Object read2;
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null.");
        }
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if (serializer == null) {
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        beginObject();
        try {
            if (this.references) {
                int readReferenceOrNull = readReferenceOrNull(input, cls, true);
                if (readReferenceOrNull == -1) {
                    return (T) this.readObject;
                }
                read2 = serializer.read2(this, input, cls);
                if (readReferenceOrNull == this.readReferenceIds.size) {
                    reference(read2);
                }
            } else {
                if (!serializer.getAcceptsNull() && input.readByte() == 0) {
                    if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                        Util.log("Read", null, input.position());
                    }
                    int i = this.depth - 1;
                    this.depth = i;
                    if (i == 0 && this.autoReset) {
                        reset();
                    }
                    return null;
                }
                read2 = serializer.read2(this, input, cls);
            }
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Read", read2, input.position());
            }
            T t = (T) read2;
            int i2 = this.depth - 1;
            this.depth = i2;
            if (i2 == 0 && this.autoReset) {
                reset();
            }
            return t;
        } finally {
            int i3 = this.depth - 1;
            this.depth = i3;
            if (i3 == 0 && this.autoReset) {
                reset();
            }
        }
    }

    public Object readClassAndObject(Input input) {
        Object read2;
        if (input == null) {
            throw new IllegalArgumentException("input cannot be null.");
        }
        beginObject();
        try {
            Registration readClass = readClass(input);
            if (readClass == null) {
                return null;
            }
            Class type = readClass.getType();
            if (this.references) {
                int readReferenceOrNull = readReferenceOrNull(input, type, false);
                if (readReferenceOrNull == -1) {
                    Object obj = this.readObject;
                    int i = this.depth - 1;
                    this.depth = i;
                    if (i == 0 && this.autoReset) {
                        reset();
                    }
                    return obj;
                }
                read2 = readClass.getSerializer().read2(this, input, type);
                if (readReferenceOrNull == this.readReferenceIds.size) {
                    reference(read2);
                }
            } else {
                read2 = readClass.getSerializer().read2(this, input, type);
            }
            if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                Util.log("Read", read2, input.position());
            }
            Object obj2 = read2;
            int i2 = this.depth - 1;
            this.depth = i2;
            if (i2 == 0 && this.autoReset) {
                reset();
            }
            return obj2;
        } finally {
            int i3 = this.depth - 1;
            this.depth = i3;
            if (i3 == 0 && this.autoReset) {
                reset();
            }
        }
    }

    int readReferenceOrNull(Input input, Class cls, boolean z) {
        int readVarInt;
        if (cls.isPrimitive()) {
            cls = Util.getWrapperClass(cls);
        }
        boolean useReferences = this.referenceResolver.useReferences(cls);
        if (z) {
            int readVarInt2 = input.readVarInt(true);
            readVarInt = readVarInt2;
            if (readVarInt2 == 0) {
                if (Log.TRACE || (Log.DEBUG && this.depth == 1)) {
                    Util.log("Read", null, input.position());
                }
                this.readObject = null;
                return -1;
            }
            if (!useReferences) {
                this.readReferenceIds.add(-2);
                return this.readReferenceIds.size;
            }
        } else {
            if (!useReferences) {
                this.readReferenceIds.add(-2);
                return this.readReferenceIds.size;
            }
            readVarInt = input.readVarInt(true);
        }
        if (readVarInt == 1) {
            if (Log.TRACE) {
                Log.trace("kryo", "Read: <not null>" + Util.pos(input.position()));
            }
            int nextReadId = this.referenceResolver.nextReadId(cls);
            if (Log.TRACE) {
                Log.trace("kryo", "Read initial reference " + nextReadId + ": " + Util.className(cls) + Util.pos(input.position()));
            }
            this.readReferenceIds.add(nextReadId);
            return this.readReferenceIds.size;
        }
        int i = readVarInt - 2;
        try {
            this.readObject = this.referenceResolver.getReadObject(cls, i);
            if (Log.DEBUG) {
                Log.debug("kryo", "Read reference " + i + ": " + Util.string(this.readObject) + Util.pos(input.position()));
                return -1;
            }
            return -1;
        } catch (Exception e) {
            throw new KryoException("Unable to resolve reference for " + Util.className(cls) + " with id: " + i, e);
        }
    }

    public void reference(Object obj) {
        int pop;
        if (this.copyDepth > 0) {
            if (this.needsCopyReference != null) {
                if (obj == null) {
                    throw new IllegalArgumentException("object cannot be null.");
                }
                this.originalToCopy.put(this.needsCopyReference, obj);
                this.needsCopyReference = null;
                return;
            }
            return;
        }
        if (this.references && obj != null && (pop = this.readReferenceIds.pop()) != -2) {
            this.referenceResolver.setReadObject(pop, obj);
        }
    }

    public void reset() {
        this.depth = 0;
        if (this.graphContext != null) {
            this.graphContext.clear(2048);
        }
        this.classResolver.reset();
        if (this.references) {
            this.referenceResolver.reset();
            this.readObject = null;
        }
        this.copyDepth = 0;
        if (this.originalToCopy != null) {
            this.originalToCopy.clear(2048);
        }
        if (Log.TRACE) {
            Log.trace("kryo", "Object graph complete.");
        }
    }

    public <T> T copy(T t) {
        Object copy;
        if (t == null) {
            return null;
        }
        if (this.copyShallow) {
            return t;
        }
        this.copyDepth++;
        try {
            if (this.originalToCopy == null) {
                this.originalToCopy = new IdentityMap();
            }
            T t2 = (T) this.originalToCopy.get(t);
            if (t2 != null) {
                return t2;
            }
            if (this.copyReferences) {
                this.needsCopyReference = t;
            }
            if (t instanceof KryoCopyable) {
                copy = ((KryoCopyable) t).copy(this);
            } else {
                copy = getSerializer(t.getClass()).copy(this, t);
            }
            if (this.needsCopyReference != null) {
                reference(copy);
            }
            if (Log.TRACE || (Log.DEBUG && this.copyDepth == 1)) {
                Util.log("Copy", copy, -1);
            }
            T t3 = (T) copy;
            int i = this.copyDepth - 1;
            this.copyDepth = i;
            if (i == 0) {
                reset();
            }
            return t3;
        } finally {
            int i2 = this.copyDepth - 1;
            this.copyDepth = i2;
            if (i2 == 0) {
                reset();
            }
        }
    }

    public <T> T copy(T t, Serializer serializer) {
        Object copy;
        if (t == null) {
            return null;
        }
        if (this.copyShallow) {
            return t;
        }
        this.copyDepth++;
        try {
            if (this.originalToCopy == null) {
                this.originalToCopy = new IdentityMap();
            }
            T t2 = (T) this.originalToCopy.get(t);
            if (t2 != null) {
                return t2;
            }
            if (this.copyReferences) {
                this.needsCopyReference = t;
            }
            if (t instanceof KryoCopyable) {
                copy = ((KryoCopyable) t).copy(this);
            } else {
                copy = serializer.copy(this, t);
            }
            if (this.needsCopyReference != null) {
                reference(copy);
            }
            if (Log.TRACE || (Log.DEBUG && this.copyDepth == 1)) {
                Util.log("Copy", copy, -1);
            }
            T t3 = (T) copy;
            int i = this.copyDepth - 1;
            this.copyDepth = i;
            if (i == 0) {
                reset();
            }
            return t3;
        } finally {
            int i2 = this.copyDepth - 1;
            this.copyDepth = i2;
            if (i2 == 0) {
                reset();
            }
        }
    }

    public <T> T copyShallow(T t) {
        Object copy;
        if (t == null) {
            return null;
        }
        this.copyDepth++;
        this.copyShallow = true;
        try {
            if (this.originalToCopy == null) {
                this.originalToCopy = new IdentityMap();
            }
            T t2 = (T) this.originalToCopy.get(t);
            if (t2 != null) {
                return t2;
            }
            if (this.copyReferences) {
                this.needsCopyReference = t;
            }
            if (t instanceof KryoCopyable) {
                copy = ((KryoCopyable) t).copy(this);
            } else {
                copy = getSerializer(t.getClass()).copy(this, t);
            }
            if (this.needsCopyReference != null) {
                reference(copy);
            }
            if (Log.TRACE || (Log.DEBUG && this.copyDepth == 1)) {
                Util.log("Shallow copy", copy, -1);
            }
            T t3 = (T) copy;
            this.copyShallow = false;
            int i = this.copyDepth - 1;
            this.copyDepth = i;
            if (i == 0) {
                reset();
            }
            return t3;
        } finally {
            this.copyShallow = false;
            int i2 = this.copyDepth - 1;
            this.copyDepth = i2;
            if (i2 == 0) {
                reset();
            }
        }
    }

    public <T> T copyShallow(T t, Serializer serializer) {
        Object copy;
        if (t == null) {
            return null;
        }
        this.copyDepth++;
        this.copyShallow = true;
        try {
            if (this.originalToCopy == null) {
                this.originalToCopy = new IdentityMap();
            }
            T t2 = (T) this.originalToCopy.get(t);
            if (t2 != null) {
                return t2;
            }
            if (this.copyReferences) {
                this.needsCopyReference = t;
            }
            if (t instanceof KryoCopyable) {
                copy = ((KryoCopyable) t).copy(this);
            } else {
                copy = serializer.copy(this, t);
            }
            if (this.needsCopyReference != null) {
                reference(copy);
            }
            if (Log.TRACE || (Log.DEBUG && this.copyDepth == 1)) {
                Util.log("Shallow copy", copy, -1);
            }
            T t3 = (T) copy;
            this.copyShallow = false;
            int i = this.copyDepth - 1;
            this.copyDepth = i;
            if (i == 0) {
                reset();
            }
            return t3;
        } finally {
            this.copyShallow = false;
            int i2 = this.copyDepth - 1;
            this.copyDepth = i2;
            if (i2 == 0) {
                reset();
            }
        }
    }

    private void beginObject() {
        if (Log.DEBUG) {
            if (this.depth == 0) {
                this.thread = Thread.currentThread();
            } else if (this.thread != Thread.currentThread()) {
                throw new ConcurrentModificationException("Kryo must not be accessed concurrently by multiple threads.");
            }
        }
        if (this.depth == this.maxDepth) {
            throw new KryoException("Max depth exceeded: " + this.depth);
        }
        this.depth++;
    }

    public ClassResolver getClassResolver() {
        return this.classResolver;
    }

    public ReferenceResolver getReferenceResolver() {
        return this.referenceResolver;
    }

    public void setClassLoader(ClassLoader classLoader) {
        if (classLoader == null) {
            throw new IllegalArgumentException("classLoader cannot be null.");
        }
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public void setRegistrationRequired(boolean z) {
        this.registrationRequired = z;
        if (Log.TRACE) {
            Log.trace("kryo", "Registration required: " + z);
        }
    }

    public boolean isRegistrationRequired() {
        return this.registrationRequired;
    }

    public void setWarnUnregisteredClasses(boolean z) {
        this.warnUnregisteredClasses = z;
        if (Log.TRACE) {
            Log.trace("kryo", "Warn unregistered classes: " + z);
        }
    }

    public boolean getWarnUnregisteredClasses() {
        return this.warnUnregisteredClasses;
    }

    public boolean setReferences(boolean z) {
        boolean z2 = this.references;
        if (z == z2) {
            return z;
        }
        if (z2) {
            this.referenceResolver.reset();
            this.readObject = null;
        }
        this.references = z;
        if (z && this.referenceResolver == null) {
            this.referenceResolver = new MapReferenceResolver();
        }
        if (Log.TRACE) {
            Log.trace("kryo", "References: " + z);
        }
        return !z;
    }

    public void setCopyReferences(boolean z) {
        this.copyReferences = z;
    }

    public void setReferenceResolver(ReferenceResolver referenceResolver) {
        if (referenceResolver == null) {
            throw new IllegalArgumentException("referenceResolver cannot be null.");
        }
        this.references = true;
        this.referenceResolver = referenceResolver;
        if (Log.TRACE) {
            Log.trace("kryo", "Reference resolver: " + referenceResolver.getClass().getName());
        }
    }

    public boolean getReferences() {
        return this.references;
    }

    public void setInstantiatorStrategy(a aVar) {
        this.strategy = aVar;
    }

    public a getInstantiatorStrategy() {
        return this.strategy;
    }

    protected org.c.a.a newInstantiator(Class cls) {
        return this.strategy.newInstantiatorOf(cls);
    }

    public <T> T newInstance(Class<T> cls) {
        Registration registration = getRegistration(cls);
        org.c.a.a instantiator = registration.getInstantiator();
        org.c.a.a aVar = instantiator;
        if (instantiator == null) {
            aVar = newInstantiator(cls);
            registration.setInstantiator(aVar);
        }
        return (T) aVar.newInstance();
    }

    public ObjectMap getContext() {
        if (this.context == null) {
            this.context = new ObjectMap();
        }
        return this.context;
    }

    public ObjectMap getGraphContext() {
        if (this.graphContext == null) {
            this.graphContext = new ObjectMap();
        }
        return this.graphContext;
    }

    public int getDepth() {
        return this.depth;
    }

    public IdentityMap getOriginalToCopyMap() {
        return this.originalToCopy;
    }

    public void setAutoReset(boolean z) {
        this.autoReset = z;
    }

    public void setMaxDepth(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxDepth must be > 0.");
        }
        this.maxDepth = i;
    }

    public boolean isFinal(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        return cls.isArray() ? Modifier.isFinal(Util.getElementClass(cls).getModifiers()) : Modifier.isFinal(cls.getModifiers());
    }

    public boolean isClosure(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        return cls.isSynthetic() && cls.getSimpleName().indexOf(47) >= 0;
    }

    public boolean isProxy(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        return Proxy.isProxyClass(cls);
    }

    public Generics getGenerics() {
        return this.generics;
    }

    public void setOptimizedGenerics(boolean z) {
        this.generics = z ? new DefaultGenerics(this) : NoGenerics.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/Kryo$DefaultSerializerEntry.class */
    public static final class DefaultSerializerEntry {
        final Class type;
        final SerializerFactory serializerFactory;

        DefaultSerializerEntry(Class cls, SerializerFactory serializerFactory) {
            this.type = cls;
            this.serializerFactory = serializerFactory;
        }
    }
}
