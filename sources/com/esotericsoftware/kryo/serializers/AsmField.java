package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.util.Generics;
import java.lang.reflect.Field;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/AsmField.class */
public class AsmField extends ReflectField {
    public AsmField(Field field, FieldSerializer fieldSerializer, Generics.GenericType genericType) {
        super(field, fieldSerializer, genericType);
    }

    @Override // com.esotericsoftware.kryo.serializers.ReflectField
    public Object get(Object obj) {
        return this.access.get(obj, this.accessIndex);
    }

    @Override // com.esotericsoftware.kryo.serializers.ReflectField
    public void set(Object obj, Object obj2) {
        this.access.set(obj, this.accessIndex, obj2);
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.esotericsoftware.reflectasm.FieldAccess, com.esotericsoftware.kryo.KryoException] */
    @Override // com.esotericsoftware.kryo.serializers.ReflectField, com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
    public void copy(Object obj, Object obj2) {
        ?? r0;
        try {
            r0 = this.access;
            r0.set(obj2, this.accessIndex, this.fieldSerializer.kryo.copy(this.access.get(obj, this.accessIndex)));
        } catch (KryoException e) {
            r0.addTrace(this + " (" + this.fieldSerializer.type.getName() + ")");
            throw e;
        } catch (Throwable th) {
            KryoException kryoException = new KryoException(th);
            kryoException.addTrace(this + " (" + this.fieldSerializer.type.getName() + ")");
            throw kryoException;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/AsmField$IntAsmField.class */
    public static final class IntAsmField extends FieldSerializer.CachedField {
        public IntAsmField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            if (this.varEncoding) {
                output.writeVarInt(this.access.getInt(obj, this.accessIndex), false);
            } else {
                output.writeInt(this.access.getInt(obj, this.accessIndex));
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            if (this.varEncoding) {
                this.access.setInt(obj, this.accessIndex, input.readVarInt(false));
            } else {
                this.access.setInt(obj, this.accessIndex, input.readInt());
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            this.access.setInt(obj2, this.accessIndex, this.access.getInt(obj, this.accessIndex));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/AsmField$FloatAsmField.class */
    public static final class FloatAsmField extends FieldSerializer.CachedField {
        public FloatAsmField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeFloat(this.access.getFloat(obj, this.accessIndex));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            this.access.setFloat(obj, this.accessIndex, input.readFloat());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            this.access.setFloat(obj2, this.accessIndex, this.access.getFloat(obj, this.accessIndex));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/AsmField$ShortAsmField.class */
    public static final class ShortAsmField extends FieldSerializer.CachedField {
        public ShortAsmField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeShort(this.access.getShort(obj, this.accessIndex));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            this.access.setShort(obj, this.accessIndex, input.readShort());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            this.access.setShort(obj2, this.accessIndex, this.access.getShort(obj, this.accessIndex));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/AsmField$ByteAsmField.class */
    public static final class ByteAsmField extends FieldSerializer.CachedField {
        public ByteAsmField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeByte(this.access.getByte(obj, this.accessIndex));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            this.access.setByte(obj, this.accessIndex, input.readByte());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            this.access.setByte(obj2, this.accessIndex, this.access.getByte(obj, this.accessIndex));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/AsmField$BooleanAsmField.class */
    public static final class BooleanAsmField extends FieldSerializer.CachedField {
        public BooleanAsmField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeBoolean(this.access.getBoolean(obj, this.accessIndex));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            this.access.setBoolean(obj, this.accessIndex, input.readBoolean());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            this.access.setBoolean(obj2, this.accessIndex, this.access.getBoolean(obj, this.accessIndex));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/AsmField$CharAsmField.class */
    public static final class CharAsmField extends FieldSerializer.CachedField {
        public CharAsmField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeChar(this.access.getChar(obj, this.accessIndex));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            this.access.setChar(obj, this.accessIndex, input.readChar());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            this.access.setChar(obj2, this.accessIndex, this.access.getChar(obj, this.accessIndex));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/AsmField$LongAsmField.class */
    public static final class LongAsmField extends FieldSerializer.CachedField {
        public LongAsmField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            if (this.varEncoding) {
                output.writeVarLong(this.access.getLong(obj, this.accessIndex), false);
            } else {
                output.writeLong(this.access.getLong(obj, this.accessIndex));
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            if (this.varEncoding) {
                this.access.setLong(obj, this.accessIndex, input.readVarLong(false));
            } else {
                this.access.setLong(obj, this.accessIndex, input.readLong());
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            this.access.setLong(obj2, this.accessIndex, this.access.getLong(obj, this.accessIndex));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/AsmField$DoubleAsmField.class */
    public static final class DoubleAsmField extends FieldSerializer.CachedField {
        public DoubleAsmField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeDouble(this.access.getDouble(obj, this.accessIndex));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            this.access.setDouble(obj, this.accessIndex, input.readDouble());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            this.access.setDouble(obj2, this.accessIndex, this.access.getDouble(obj, this.accessIndex));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/AsmField$StringAsmField.class */
    public static final class StringAsmField extends FieldSerializer.CachedField {
        public StringAsmField(Field field) {
            super(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeString(this.access.getString(obj, this.accessIndex));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            this.access.set(obj, this.accessIndex, input.readString());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            this.access.set(obj2, this.accessIndex, this.access.getString(obj, this.accessIndex));
        }
    }
}
