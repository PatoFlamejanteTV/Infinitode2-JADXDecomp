package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.unsafe.UnsafeUtil;
import com.esotericsoftware.kryo.util.Generics;
import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/UnsafeField.class */
public class UnsafeField extends ReflectField {
    public UnsafeField(Field field, FieldSerializer fieldSerializer, Generics.GenericType genericType) {
        super(field, fieldSerializer, genericType);
        this.offset = UnsafeUtil.unsafe.objectFieldOffset(field);
    }

    @Override // com.esotericsoftware.kryo.serializers.ReflectField
    public Object get(Object obj) {
        return UnsafeUtil.unsafe.getObject(obj, this.offset);
    }

    @Override // com.esotericsoftware.kryo.serializers.ReflectField
    public void set(Object obj, Object obj2) {
        UnsafeUtil.unsafe.putObject(obj, this.offset, obj2);
    }

    @Override // com.esotericsoftware.kryo.serializers.ReflectField, com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
    public void copy(Object obj, Object obj2) {
        Unsafe unsafe;
        try {
            unsafe = UnsafeUtil.unsafe;
            unsafe.putObject(obj2, this.offset, this.fieldSerializer.kryo.copy(UnsafeUtil.unsafe.getObject(obj, this.offset)));
        } catch (KryoException e) {
            unsafe.addTrace(this + " (" + this.fieldSerializer.type.getName() + ")");
            throw e;
        } catch (Throwable th) {
            KryoException kryoException = new KryoException(th);
            kryoException.addTrace(this + " (" + this.fieldSerializer.type.getName() + ")");
            throw kryoException;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/UnsafeField$IntUnsafeField.class */
    public static final class IntUnsafeField extends FieldSerializer.CachedField {
        public IntUnsafeField(Field field) {
            super(field);
            this.offset = UnsafeUtil.unsafe.objectFieldOffset(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            if (this.varEncoding) {
                output.writeVarInt(UnsafeUtil.unsafe.getInt(obj, this.offset), false);
            } else {
                output.writeInt(UnsafeUtil.unsafe.getInt(obj, this.offset));
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            if (this.varEncoding) {
                UnsafeUtil.unsafe.putInt(obj, this.offset, input.readVarInt(false));
            } else {
                UnsafeUtil.unsafe.putInt(obj, this.offset, input.readInt());
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            UnsafeUtil.unsafe.putInt(obj2, this.offset, UnsafeUtil.unsafe.getInt(obj, this.offset));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/UnsafeField$FloatUnsafeField.class */
    public static final class FloatUnsafeField extends FieldSerializer.CachedField {
        public FloatUnsafeField(Field field) {
            super(field);
            this.offset = UnsafeUtil.unsafe.objectFieldOffset(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeFloat(UnsafeUtil.unsafe.getFloat(obj, this.offset));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            UnsafeUtil.unsafe.putFloat(obj, this.offset, input.readFloat());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            UnsafeUtil.unsafe.putFloat(obj2, this.offset, UnsafeUtil.unsafe.getFloat(obj, this.offset));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/UnsafeField$ShortUnsafeField.class */
    public static final class ShortUnsafeField extends FieldSerializer.CachedField {
        public ShortUnsafeField(Field field) {
            super(field);
            this.offset = UnsafeUtil.unsafe.objectFieldOffset(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeShort(UnsafeUtil.unsafe.getShort(obj, this.offset));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            UnsafeUtil.unsafe.putShort(obj, this.offset, input.readShort());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            UnsafeUtil.unsafe.putShort(obj2, this.offset, UnsafeUtil.unsafe.getShort(obj, this.offset));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/UnsafeField$ByteUnsafeField.class */
    public static final class ByteUnsafeField extends FieldSerializer.CachedField {
        public ByteUnsafeField(Field field) {
            super(field);
            this.offset = UnsafeUtil.unsafe.objectFieldOffset(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeByte(UnsafeUtil.unsafe.getByte(obj, this.offset));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            UnsafeUtil.unsafe.putByte(obj, this.offset, input.readByte());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            UnsafeUtil.unsafe.putByte(obj2, this.offset, UnsafeUtil.unsafe.getByte(obj, this.offset));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/UnsafeField$BooleanUnsafeField.class */
    public static final class BooleanUnsafeField extends FieldSerializer.CachedField {
        public BooleanUnsafeField(Field field) {
            super(field);
            this.offset = UnsafeUtil.unsafe.objectFieldOffset(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeBoolean(UnsafeUtil.unsafe.getBoolean(obj, this.offset));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            UnsafeUtil.unsafe.putBoolean(obj, this.offset, input.readBoolean());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            UnsafeUtil.unsafe.putBoolean(obj2, this.offset, UnsafeUtil.unsafe.getBoolean(obj, this.offset));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/UnsafeField$CharUnsafeField.class */
    public static final class CharUnsafeField extends FieldSerializer.CachedField {
        public CharUnsafeField(Field field) {
            super(field);
            this.offset = UnsafeUtil.unsafe.objectFieldOffset(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeChar(UnsafeUtil.unsafe.getChar(obj, this.offset));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            UnsafeUtil.unsafe.putChar(obj, this.offset, input.readChar());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            UnsafeUtil.unsafe.putChar(obj2, this.offset, UnsafeUtil.unsafe.getChar(obj, this.offset));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/UnsafeField$LongUnsafeField.class */
    public static final class LongUnsafeField extends FieldSerializer.CachedField {
        public LongUnsafeField(Field field) {
            super(field);
            this.offset = UnsafeUtil.unsafe.objectFieldOffset(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            if (this.varEncoding) {
                output.writeVarLong(UnsafeUtil.unsafe.getLong(obj, this.offset), false);
            } else {
                output.writeLong(UnsafeUtil.unsafe.getLong(obj, this.offset));
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            if (this.varEncoding) {
                UnsafeUtil.unsafe.putLong(obj, this.offset, input.readVarLong(false));
            } else {
                UnsafeUtil.unsafe.putLong(obj, this.offset, input.readLong());
            }
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            UnsafeUtil.unsafe.putLong(obj2, this.offset, UnsafeUtil.unsafe.getLong(obj, this.offset));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/UnsafeField$DoubleUnsafeField.class */
    public static final class DoubleUnsafeField extends FieldSerializer.CachedField {
        public DoubleUnsafeField(Field field) {
            super(field);
            this.offset = UnsafeUtil.unsafe.objectFieldOffset(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeDouble(UnsafeUtil.unsafe.getDouble(obj, this.offset));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            UnsafeUtil.unsafe.putDouble(obj, this.offset, input.readDouble());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            UnsafeUtil.unsafe.putDouble(obj2, this.offset, UnsafeUtil.unsafe.getDouble(obj, this.offset));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/UnsafeField$StringUnsafeField.class */
    public static final class StringUnsafeField extends FieldSerializer.CachedField {
        public StringUnsafeField(Field field) {
            super(field);
            this.offset = UnsafeUtil.unsafe.objectFieldOffset(field);
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void write(Output output, Object obj) {
            output.writeString((String) UnsafeUtil.unsafe.getObject(obj, this.offset));
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void read(Input input, Object obj) {
            UnsafeUtil.unsafe.putObject(obj, this.offset, input.readString());
        }

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.CachedField
        public final void copy(Object obj, Object obj2) {
            UnsafeUtil.unsafe.putObject(obj2, this.offset, UnsafeUtil.unsafe.getObject(obj, this.offset));
        }
    }
}
