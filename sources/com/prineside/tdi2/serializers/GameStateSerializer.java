package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.EnumKeyArray;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/GameStateSerializer.class */
public final class GameStateSerializer extends Serializer {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2894a = TLog.forClass(GameStateSerializer.class);
    public static Serializer CLASS_ONLY_SERIALIZER = new Serializer() { // from class: com.prineside.tdi2.serializers.GameStateSerializer.1
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Object obj) {
            throw new IllegalStateException("can't be used to serialize objects");
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Object read2(Kryo kryo, Input input, Class cls) {
            throw new IllegalStateException("can't be used to serialize objects");
        }
    };

    /* renamed from: b, reason: collision with root package name */
    private static ObjectMap<Class, Array<Field>> f2895b = new ObjectMap<>();
    private static final ObjectSet<Class> c;
    private static final ObjectSet<Class> d;
    private final Array<Field> e;
    public int writeCount;
    private static final ObjectMap<Field, EnumKeyArray> f;
    private static final Comparator<Field> g;

    static {
        ObjectSet<Class> objectSet = new ObjectSet<>();
        c = objectSet;
        objectSet.add(byte[].class);
        c.add(char[].class);
        c.add(short[].class);
        c.add(int[].class);
        c.add(long[].class);
        c.add(float[].class);
        c.add(double[].class);
        c.add(boolean[].class);
        c.add(String[].class);
        c.add(Enum[].class);
        c.add(Integer.TYPE);
        c.add(String.class);
        c.add(Float.TYPE);
        c.add(Boolean.TYPE);
        c.add(Byte.TYPE);
        c.add(Character.TYPE);
        c.add(Short.TYPE);
        c.add(Long.TYPE);
        c.add(Double.TYPE);
        ObjectSet<Class> objectSet2 = new ObjectSet<>();
        d = objectSet2;
        objectSet2.add(Integer.TYPE);
        d.add(Float.TYPE);
        d.add(Boolean.TYPE);
        d.add(Byte.TYPE);
        d.add(Character.TYPE);
        d.add(Short.TYPE);
        d.add(Long.TYPE);
        d.add(Double.TYPE);
        f = new ObjectMap<>();
        g = (field, field2) -> {
            return field.getName().compareTo(field2.getName());
        };
    }

    public static EnumKeyArray getEnumKeyArrayFieldAnnotationCached(Field field) {
        if (field.isAnnotationPresent(EnumKeyArray.class)) {
            if (f.containsKey(field)) {
                return f.get(field);
            }
            EnumKeyArray enumKeyArray = (EnumKeyArray) field.getAnnotation(EnumKeyArray.class);
            f.put(field, enumKeyArray);
            return enumKeyArray;
        }
        return null;
    }

    public static Array<Field> getAllFields(Class cls, Array<String> array) {
        Array<Field> array2 = f2895b.get(cls, null);
        if (array2 != null) {
            return array2;
        }
        Array<Field> array3 = new Array<>(Field.class);
        while (cls != null && cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                if (!a(field)) {
                    if (!array3.contains(field, true)) {
                        try {
                            field.setAccessible(true);
                            array3.add(field);
                        } catch (Exception unused) {
                        }
                    }
                } else if (array != null) {
                    String str = cls.getName() + "." + field.getName();
                    String str2 = str;
                    if (str.startsWith("com.prineside.tdi2.")) {
                        str2 = str2.substring(19);
                    }
                    if (!array.contains(str2, false)) {
                        f2894a.e("skipped " + str2 + SequenceUtils.SPACE + field.getType().getSimpleName(), new Object[0]);
                        array.add(str2);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        array3.sort(g);
        f2895b.put(cls, array3);
        return array3;
    }

    public GameStateSerializer(Class cls) {
        throw new IllegalStateException("Should not be used anymore - implement individual serializers");
    }

    private static boolean a(Field field) {
        return Modifier.isStatic(field.getModifiers()) || field.isAnnotationPresent(NAGS.class) || field.getType().isAnnotationPresent(NAGS.class);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, Object obj) {
        this.writeCount++;
        for (int i = 0; i < this.e.size; i++) {
            Field field = this.e.items[i];
            try {
                Class<?> type = field.getType();
                if (c.contains(type)) {
                    if (d.contains(type)) {
                        if (type == Integer.TYPE) {
                            output.writeInt(field.getInt(obj), false);
                        } else if (type == Float.TYPE) {
                            output.writeFloat(field.getFloat(obj));
                        } else if (type == Boolean.TYPE) {
                            output.writeBoolean(field.getBoolean(obj));
                        } else if (type == Byte.TYPE) {
                            output.writeByte(field.getByte(obj));
                        } else if (type == Character.TYPE) {
                            output.writeChar(field.getChar(obj));
                        } else if (type == Short.TYPE) {
                            output.writeShort(field.getShort(obj));
                        } else if (type == Long.TYPE) {
                            output.writeLong(field.getLong(obj), false);
                        } else {
                            output.writeDouble(field.getDouble(obj));
                        }
                    } else {
                        kryo.writeObjectOrNull(output, field.get(obj), kryo.getSerializer(type));
                    }
                } else {
                    kryo.writeClassAndObject(output, field.get(obj));
                }
            } catch (Exception e) {
                throw new IllegalStateException("failed to write field " + field.toGenericString(), e);
            }
        }
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final Object read2(Kryo kryo, Input input, Class cls) {
        Constructor constructor = null;
        try {
            try {
                constructor = cls.getDeclaredConstructor(new Class[0]);
            } catch (Exception e) {
                f2894a.e("failed to obtain constructor", e);
                for (Constructor<?> constructor2 : cls.getDeclaredConstructors()) {
                    f2894a.i(new StringBuilder().append(constructor2).toString(), new Object[0]);
                }
            }
            constructor.setAccessible(true);
            Object newInstance = constructor.newInstance(new Object[0]);
            kryo.reference(newInstance);
            for (int i = 0; i < this.e.size; i++) {
                Field field = this.e.items[i];
                Class<?> type = field.getType();
                if (c.contains(type)) {
                    if (d.contains(type)) {
                        if (type == Integer.TYPE) {
                            field.setInt(newInstance, input.readInt(false));
                        } else if (type == Float.TYPE) {
                            field.setFloat(newInstance, input.readFloat());
                        } else if (type == Boolean.TYPE) {
                            field.setBoolean(newInstance, input.readBoolean());
                        } else if (type == Byte.TYPE) {
                            field.setByte(newInstance, input.readByte());
                        } else if (type == Character.TYPE) {
                            field.setChar(newInstance, input.readChar());
                        } else if (type == Short.TYPE) {
                            field.setShort(newInstance, input.readShort());
                        } else if (type == Long.TYPE) {
                            field.setLong(newInstance, input.readLong(false));
                        } else {
                            field.setDouble(newInstance, input.readDouble());
                        }
                    } else {
                        field.set(newInstance, kryo.readObjectOrNull(input, type, kryo.getSerializer(type)));
                    }
                } else {
                    field.set(newInstance, kryo.readClassAndObject(input));
                }
            }
            return newInstance;
        } catch (Exception e2) {
            throw new RuntimeException("Failed to create new instance of " + cls.getName(), e2);
        }
    }
}
