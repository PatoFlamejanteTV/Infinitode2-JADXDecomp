package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.reflect.Array;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers.class */
public class DefaultArraySerializers {

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers$ByteArraySerializer.class */
    public static class ByteArraySerializer extends Serializer<byte[]> {
        public ByteArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, byte[] bArr) {
            if (bArr == null) {
                output.writeByte((byte) 0);
            } else {
                output.writeVarInt(bArr.length + 1, true);
                output.writeBytes(bArr);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public byte[] read2(Kryo kryo, Input input, Class<? extends byte[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readBytes(readVarInt - 1);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public byte[] copy(Kryo kryo, byte[] bArr) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
            return bArr2;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers$IntArraySerializer.class */
    public static class IntArraySerializer extends Serializer<int[]> {
        public IntArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, int[] iArr) {
            if (iArr == null) {
                output.writeByte((byte) 0);
            } else {
                output.writeVarInt(iArr.length + 1, true);
                output.writeInts(iArr, 0, iArr.length, false);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public int[] read2(Kryo kryo, Input input, Class<? extends int[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readInts(readVarInt - 1, false);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public int[] copy(Kryo kryo, int[] iArr) {
            int[] iArr2 = new int[iArr.length];
            System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
            return iArr2;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers$FloatArraySerializer.class */
    public static class FloatArraySerializer extends Serializer<float[]> {
        public FloatArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, float[] fArr) {
            if (fArr == null) {
                output.writeByte((byte) 0);
            } else {
                output.writeVarInt(fArr.length + 1, true);
                output.writeFloats(fArr, 0, fArr.length);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public float[] read2(Kryo kryo, Input input, Class<? extends float[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readFloats(readVarInt - 1);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public float[] copy(Kryo kryo, float[] fArr) {
            float[] fArr2 = new float[fArr.length];
            System.arraycopy(fArr, 0, fArr2, 0, fArr2.length);
            return fArr2;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers$LongArraySerializer.class */
    public static class LongArraySerializer extends Serializer<long[]> {
        public LongArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, long[] jArr) {
            if (jArr == null) {
                output.writeByte((byte) 0);
            } else {
                output.writeVarInt(jArr.length + 1, true);
                output.writeLongs(jArr, 0, jArr.length, false);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public long[] read2(Kryo kryo, Input input, Class<? extends long[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readLongs(readVarInt - 1, false);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public long[] copy(Kryo kryo, long[] jArr) {
            long[] jArr2 = new long[jArr.length];
            System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
            return jArr2;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers$ShortArraySerializer.class */
    public static class ShortArraySerializer extends Serializer<short[]> {
        public ShortArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, short[] sArr) {
            if (sArr == null) {
                output.writeByte((byte) 0);
            } else {
                output.writeVarInt(sArr.length + 1, true);
                output.writeShorts(sArr, 0, sArr.length);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public short[] read2(Kryo kryo, Input input, Class<? extends short[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readShorts(readVarInt - 1);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public short[] copy(Kryo kryo, short[] sArr) {
            short[] sArr2 = new short[sArr.length];
            System.arraycopy(sArr, 0, sArr2, 0, sArr2.length);
            return sArr2;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers$CharArraySerializer.class */
    public static class CharArraySerializer extends Serializer<char[]> {
        public CharArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, char[] cArr) {
            if (cArr == null) {
                output.writeByte((byte) 0);
            } else {
                output.writeVarInt(cArr.length + 1, true);
                output.writeChars(cArr, 0, cArr.length);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public char[] read2(Kryo kryo, Input input, Class<? extends char[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readChars(readVarInt - 1);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public char[] copy(Kryo kryo, char[] cArr) {
            char[] cArr2 = new char[cArr.length];
            System.arraycopy(cArr, 0, cArr2, 0, cArr2.length);
            return cArr2;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers$DoubleArraySerializer.class */
    public static class DoubleArraySerializer extends Serializer<double[]> {
        public DoubleArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, double[] dArr) {
            if (dArr == null) {
                output.writeByte((byte) 0);
            } else {
                output.writeVarInt(dArr.length + 1, true);
                output.writeDoubles(dArr, 0, dArr.length);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public double[] read2(Kryo kryo, Input input, Class<? extends double[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return input.readDoubles(readVarInt - 1);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public double[] copy(Kryo kryo, double[] dArr) {
            double[] dArr2 = new double[dArr.length];
            System.arraycopy(dArr, 0, dArr2, 0, dArr2.length);
            return dArr2;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers$BooleanArraySerializer.class */
    public static class BooleanArraySerializer extends Serializer<boolean[]> {
        public BooleanArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, boolean[] zArr) {
            if (zArr == null) {
                output.writeByte((byte) 0);
                return;
            }
            output.writeVarInt(zArr.length + 1, true);
            for (boolean z : zArr) {
                output.writeBoolean(z);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public boolean[] read2(Kryo kryo, Input input, Class<? extends boolean[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            int i = readVarInt - 1;
            boolean[] zArr = new boolean[i];
            for (int i2 = 0; i2 < i; i2++) {
                zArr[i2] = input.readBoolean();
            }
            return zArr;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public boolean[] copy(Kryo kryo, boolean[] zArr) {
            boolean[] zArr2 = new boolean[zArr.length];
            System.arraycopy(zArr, 0, zArr2, 0, zArr2.length);
            return zArr2;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers$StringArraySerializer.class */
    public static class StringArraySerializer extends Serializer<String[]> {
        public StringArraySerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, String[] strArr) {
            if (strArr == null) {
                output.writeByte((byte) 0);
                return;
            }
            output.writeVarInt(strArr.length + 1, true);
            if (kryo.getReferences() && kryo.getReferenceResolver().useReferences(String.class)) {
                Serializer serializer = kryo.getSerializer(String.class);
                for (String str : strArr) {
                    kryo.writeObjectOrNull(output, str, serializer);
                }
                return;
            }
            for (String str2 : strArr) {
                output.writeString(str2);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public String[] read2(Kryo kryo, Input input, Class<? extends String[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            int i = readVarInt - 1;
            String[] strArr = new String[i];
            if (kryo.getReferences() && kryo.getReferenceResolver().useReferences(String.class)) {
                Serializer serializer = kryo.getSerializer(String.class);
                for (int i2 = 0; i2 < i; i2++) {
                    strArr[i2] = (String) kryo.readObjectOrNull(input, String.class, serializer);
                }
            } else {
                for (int i3 = 0; i3 < i; i3++) {
                    strArr[i3] = input.readString();
                }
            }
            return strArr;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public String[] copy(Kryo kryo, String[] strArr) {
            String[] strArr2 = new String[strArr.length];
            System.arraycopy(strArr, 0, strArr2, 0, strArr2.length);
            return strArr2;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DefaultArraySerializers$ObjectArraySerializer.class */
    public static class ObjectArraySerializer extends Serializer<Object[]> {
        private boolean elementsAreSameType;
        private boolean elementsCanBeNull = true;
        private final Class type;

        public ObjectArraySerializer(Kryo kryo, Class cls) {
            setAcceptsNull(true);
            this.type = cls;
            if (0 != (cls.getComponentType().getModifiers() & 16)) {
                setElementsAreSameType(true);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Object[] objArr) {
            if (objArr == null) {
                output.writeByte((byte) 0);
                return;
            }
            output.writeVarInt(objArr.length + 1, true);
            Class<?> componentType = objArr.getClass().getComponentType();
            if (this.elementsAreSameType || kryo.isFinal(componentType)) {
                Serializer serializer = kryo.getSerializer(componentType);
                if (this.elementsCanBeNull) {
                    for (Object obj : objArr) {
                        kryo.writeObjectOrNull(output, obj, serializer);
                    }
                    return;
                }
                for (Object obj2 : objArr) {
                    kryo.writeObject(output, obj2, serializer);
                }
                return;
            }
            for (Object obj3 : objArr) {
                kryo.writeClassAndObject(output, obj3);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Object[] read2(Kryo kryo, Input input, Class<? extends Object[]> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            int i = readVarInt - 1;
            Object[] objArr = (Object[]) Array.newInstance(cls.getComponentType(), i);
            kryo.reference(objArr);
            Class componentType = cls.getComponentType();
            if (this.elementsAreSameType || kryo.isFinal(componentType)) {
                Serializer serializer = kryo.getSerializer(componentType);
                if (this.elementsCanBeNull) {
                    for (int i2 = 0; i2 < i; i2++) {
                        objArr[i2] = kryo.readObjectOrNull(input, componentType, serializer);
                    }
                } else {
                    for (int i3 = 0; i3 < i; i3++) {
                        objArr[i3] = kryo.readObject(input, componentType, serializer);
                    }
                }
            } else {
                for (int i4 = 0; i4 < i; i4++) {
                    objArr[i4] = kryo.readClassAndObject(input);
                }
            }
            return objArr;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public Object[] copy(Kryo kryo, Object[] objArr) {
            int length = objArr.length;
            Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), length);
            kryo.reference(objArr2);
            for (int i = 0; i < length; i++) {
                objArr2[i] = kryo.copy(objArr[i]);
            }
            return objArr2;
        }

        public void setElementsCanBeNull(boolean z) {
            this.elementsCanBeNull = z;
        }

        public void setElementsAreSameType(boolean z) {
            this.elementsAreSameType = z;
        }
    }
}
