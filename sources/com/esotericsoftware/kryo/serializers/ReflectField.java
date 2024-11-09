package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.util.Generics;
import com.esotericsoftware.kryo.util.Util;
import java.lang.reflect.Field;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ReflectField.class */
public class ReflectField extends FieldSerializer.CachedField {
    final FieldSerializer fieldSerializer;
    final Generics.GenericType genericType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReflectField(Field field, FieldSerializer fieldSerializer, Generics.GenericType genericType) {
        super(field);
        this.fieldSerializer = fieldSerializer;
        this.genericType = genericType;
    }

    public Object get(Object obj) {
        return this.field.get(obj);
    }

    public void set(Object obj, Object obj2) {
        this.field.set(obj, obj2);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.esotericsoftware.kryo.Kryo, com.esotericsoftware.kryo.KryoException] */
    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
    public void write(Output output, Object obj) {
        ?? r0 = this.fieldSerializer.kryo;
        try {
            Object obj2 = get(obj);
            Serializer serializer = this.serializer;
            Class resolveFieldClass = resolveFieldClass();
            if (resolveFieldClass == null) {
                if (obj2 == null) {
                    r0.writeClass(output, null);
                    return;
                }
                Registration writeClass = r0.writeClass(output, obj2.getClass());
                if (serializer == null) {
                    serializer = writeClass.getSerializer();
                }
                r0.getGenerics().pushGenericType(this.genericType);
            } else {
                if (serializer == null) {
                    serializer = r0.getSerializer(resolveFieldClass);
                    if (this.valueClass != null && this.reuseSerializer) {
                        this.serializer = serializer;
                    }
                }
                r0.getGenerics().pushGenericType(this.genericType);
                if (this.canBeNull) {
                    r0.writeObjectOrNull(output, obj2, serializer);
                    r0.getGenerics().popGenericType();
                } else if (obj2 == null) {
                    throw new KryoException("Field value cannot be null when canBeNull is false: " + this.name + " (" + obj.getClass().getName() + ")");
                }
            }
            r0.writeObject(output, obj2, serializer);
            r0.getGenerics().popGenericType();
        } catch (KryoException e) {
            r0.addTrace(this.name + " (" + obj.getClass().getName() + ")");
            throw e;
        } catch (IllegalAccessException e2) {
            throw new KryoException("Error accessing field: " + this.name + " (" + obj.getClass().getName() + ")", e2);
        } catch (StackOverflowError e3) {
            throw new KryoException("A StackOverflow occurred. The most likely cause is that your data has a circular reference resulting in infinite recursion. Try enabling references with Kryo.setReferences(true). If your data structure is really more than " + r0.getDepth() + " levels deep then try increasing your Java stack size.", e3);
        } catch (Throwable th) {
            KryoException kryoException = new KryoException(th);
            kryoException.addTrace(this.name + " (" + obj.getClass().getName() + ")");
            throw kryoException;
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.esotericsoftware.kryo.Kryo, com.esotericsoftware.kryo.KryoException] */
    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
    public void read(Input input, Object obj) {
        Object readObject;
        ?? r0 = this.fieldSerializer.kryo;
        try {
            Serializer serializer = this.serializer;
            Class resolveFieldClass = resolveFieldClass();
            if (resolveFieldClass == null) {
                Registration readClass = r0.readClass(input);
                if (readClass == null) {
                    set(obj, null);
                    return;
                }
                if (serializer == null) {
                    serializer = readClass.getSerializer();
                }
                r0.getGenerics().pushGenericType(this.genericType);
                readObject = r0.readObject(input, readClass.getType(), serializer);
            } else {
                if (serializer == null) {
                    serializer = r0.getSerializer(resolveFieldClass);
                    if (this.valueClass != null && this.reuseSerializer) {
                        this.serializer = serializer;
                    }
                }
                r0.getGenerics().pushGenericType(this.genericType);
                if (this.canBeNull) {
                    readObject = r0.readObjectOrNull(input, resolveFieldClass, serializer);
                } else {
                    readObject = r0.readObject(input, resolveFieldClass, serializer);
                }
            }
            r0.getGenerics().popGenericType();
            set(obj, readObject);
        } catch (KryoException e) {
            r0.addTrace(this.name + " (" + this.fieldSerializer.type.getName() + ")");
            throw e;
        } catch (IllegalAccessException e2) {
            throw new KryoException("Error accessing field: " + this.name + " (" + this.fieldSerializer.type.getName() + ")", e2);
        } catch (Throwable th) {
            KryoException kryoException = new KryoException(th);
            kryoException.addTrace(this.name + " (" + this.fieldSerializer.type.getName() + ")");
            throw kryoException;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class resolveFieldClass() {
        Class resolve;
        if (this.valueClass == null && (resolve = this.genericType.resolve(this.fieldSerializer.kryo.getGenerics())) != null && this.fieldSerializer.kryo.isFinal(resolve)) {
            return this.field.getType().isArray() ? Util.getArrayType(resolve) : resolve;
        }
        return this.valueClass;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
    public void copy(Object obj, Object obj2) {
        try {
            set(obj2, this.fieldSerializer.kryo.copy(get(obj)));
        } catch (KryoException e) {
            addTrace(this.name + " (" + this.fieldSerializer.type.getName() + ")");
            throw e;
        } catch (IllegalAccessException e2) {
            throw new KryoException("Error accessing field: " + this.name + " (" + this.fieldSerializer.type.getName() + ")", e2);
        } catch (Throwable th) {
            KryoException kryoException = new KryoException(th);
            kryoException.addTrace(this.name + " (" + this.fieldSerializer.type.getName() + ")");
            throw kryoException;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ReflectField$IntReflectField.class */
    public static final class IntReflectField extends FieldSerializer.CachedField {
        public IntReflectField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            try {
                if (this.varEncoding) {
                    output.writeVarInt(this.field.getInt(obj), false);
                } else {
                    output.writeInt(this.field.getInt(obj));
                }
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (int)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            try {
                if (this.varEncoding) {
                    this.field.setInt(obj, input.readVarInt(false));
                } else {
                    this.field.setInt(obj, input.readInt());
                }
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (int)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            try {
                this.field.setInt(obj2, this.field.getInt(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (int)");
                throw kryoException;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ReflectField$FloatReflectField.class */
    public static final class FloatReflectField extends FieldSerializer.CachedField {
        public FloatReflectField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            try {
                output.writeFloat(this.field.getFloat(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (float)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            try {
                this.field.setFloat(obj, input.readFloat());
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (float)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            try {
                this.field.setFloat(obj2, this.field.getFloat(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (float)");
                throw kryoException;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ReflectField$ShortReflectField.class */
    public static final class ShortReflectField extends FieldSerializer.CachedField {
        public ShortReflectField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            try {
                output.writeShort(this.field.getShort(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (short)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            try {
                this.field.setShort(obj, input.readShort());
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (short)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            try {
                this.field.setShort(obj2, this.field.getShort(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (short)");
                throw kryoException;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ReflectField$ByteReflectField.class */
    public static final class ByteReflectField extends FieldSerializer.CachedField {
        public ByteReflectField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            try {
                output.writeByte(this.field.getByte(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (byte)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            try {
                this.field.setByte(obj, input.readByte());
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (byte)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            try {
                this.field.setByte(obj2, this.field.getByte(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (byte)");
                throw kryoException;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ReflectField$BooleanReflectField.class */
    public static final class BooleanReflectField extends FieldSerializer.CachedField {
        public BooleanReflectField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            try {
                output.writeBoolean(this.field.getBoolean(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (boolean)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            try {
                this.field.setBoolean(obj, input.readBoolean());
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (boolean)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            try {
                this.field.setBoolean(obj2, this.field.getBoolean(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (boolean)");
                throw kryoException;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ReflectField$CharReflectField.class */
    public static final class CharReflectField extends FieldSerializer.CachedField {
        public CharReflectField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            try {
                output.writeChar(this.field.getChar(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (char)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            try {
                this.field.setChar(obj, input.readChar());
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (char)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            try {
                this.field.setChar(obj2, this.field.getChar(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (char)");
                throw kryoException;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ReflectField$LongReflectField.class */
    public static final class LongReflectField extends FieldSerializer.CachedField {
        public LongReflectField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            try {
                if (this.varEncoding) {
                    output.writeVarLong(this.field.getLong(obj), false);
                } else {
                    output.writeLong(this.field.getLong(obj));
                }
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (long)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            try {
                if (this.varEncoding) {
                    this.field.setLong(obj, input.readVarLong(false));
                } else {
                    this.field.setLong(obj, input.readLong());
                }
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (long)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            try {
                this.field.setLong(obj2, this.field.getLong(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (long)");
                throw kryoException;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ReflectField$DoubleReflectField.class */
    public static final class DoubleReflectField extends FieldSerializer.CachedField {
        public DoubleReflectField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            try {
                output.writeDouble(this.field.getDouble(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (double)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            try {
                this.field.setDouble(obj, input.readDouble());
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (double)");
                throw kryoException;
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            try {
                this.field.setDouble(obj2, this.field.getDouble(obj));
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(this.name + " (double)");
                throw kryoException;
            }
        }
    }
}
