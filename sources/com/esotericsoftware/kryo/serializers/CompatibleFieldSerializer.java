package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.InputChunked;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.OutputChunked;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/CompatibleFieldSerializer.class */
public class CompatibleFieldSerializer<T> extends FieldSerializer<T> {
    private static final int binarySearchThreshold = 32;
    private final CompatibleFieldSerializerConfig config;

    public CompatibleFieldSerializer(Kryo kryo, Class cls) {
        this(kryo, cls, new CompatibleFieldSerializerConfig());
    }

    public CompatibleFieldSerializer(Kryo kryo, Class cls, CompatibleFieldSerializerConfig compatibleFieldSerializerConfig) {
        super(kryo, cls, compatibleFieldSerializerConfig);
        this.config = compatibleFieldSerializerConfig;
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        Output output2;
        int pushTypeVariables = pushTypeVariables();
        FieldSerializer.CachedField[] cachedFieldArr = this.cachedFields.fields;
        ObjectMap graphContext = kryo.getGraphContext();
        if (!graphContext.containsKey(this)) {
            if (Log.TRACE) {
                Log.trace("kryo", "Write fields for class: " + this.type.getName());
            }
            graphContext.put(this, null);
            output.writeVarInt(cachedFieldArr.length, true);
            int length = cachedFieldArr.length;
            for (int i = 0; i < length; i++) {
                if (Log.TRACE) {
                    Log.trace("kryo", "Write field name: " + cachedFieldArr[i].name + Util.pos(output.position()));
                }
                output.writeString(cachedFieldArr[i].name);
            }
        }
        boolean z = this.config.chunked;
        boolean z2 = this.config.readUnknownFieldData;
        OutputChunked outputChunked = null;
        if (z) {
            OutputChunked outputChunked2 = new OutputChunked(output, this.config.chunkSize);
            outputChunked = outputChunked2;
            output2 = outputChunked2;
        } else {
            output2 = output;
        }
        for (FieldSerializer.CachedField cachedField : cachedFieldArr) {
            if (Log.TRACE) {
                log("Write", cachedField, output.position());
            }
            if (z2) {
                Class<?> cls = null;
                if (t != null) {
                    try {
                        Object obj = cachedField.field.get(t);
                        if (obj != null) {
                            cls = obj.getClass();
                        }
                    } catch (IllegalAccessException unused) {
                    }
                }
                kryo.writeClass(output2, cls);
                if (cls == null) {
                    if (z) {
                        outputChunked.endChunk();
                    }
                } else {
                    cachedField.setCanBeNull(false);
                    cachedField.setValueClass(cls);
                    cachedField.setReuseSerializer(false);
                }
            }
            cachedField.write(output2, t);
            if (z) {
                outputChunked.endChunk();
            }
        }
        popTypeVariables(pushTypeVariables);
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T read2(Kryo kryo, Input input, Class<? extends T> cls) {
        Input input2;
        int pushTypeVariables = pushTypeVariables();
        T create = create(kryo, input, cls);
        kryo.reference(create);
        FieldSerializer.CachedField[] cachedFieldArr = (FieldSerializer.CachedField[]) kryo.getGraphContext().get(this);
        FieldSerializer.CachedField[] cachedFieldArr2 = cachedFieldArr;
        if (cachedFieldArr == null) {
            cachedFieldArr2 = readFields(kryo, input);
        }
        boolean z = this.config.chunked;
        boolean z2 = this.config.readUnknownFieldData;
        InputChunked inputChunked = null;
        if (z) {
            InputChunked inputChunked2 = new InputChunked(input, this.config.chunkSize);
            inputChunked = inputChunked2;
            input2 = inputChunked2;
        } else {
            input2 = input;
        }
        for (FieldSerializer.CachedField cachedField : cachedFieldArr2) {
            if (z2) {
                try {
                    Registration readClass = kryo.readClass(input2);
                    if (readClass == null) {
                        if (z) {
                            inputChunked.nextChunk();
                        }
                    } else {
                        Class type = readClass.getType();
                        if (cachedField == null) {
                            if (Log.TRACE) {
                                Log.trace("kryo", "Read unknown data, type: " + Util.className(type) + Util.pos(input.position()));
                            }
                            try {
                                kryo.readObject(input2, type);
                            } catch (KryoException e) {
                                String str = "Unable to read unknown data, type: " + Util.className(type) + " (" + getType().getName() + "#" + cachedField + ")";
                                if (!z) {
                                    throw new KryoException(str, e);
                                }
                                if (Log.DEBUG) {
                                    Log.debug("kryo", str, e);
                                }
                            }
                            if (z) {
                                inputChunked.nextChunk();
                            }
                        } else if (cachedField.valueClass != null && !Util.isAssignableTo(type, cachedField.field.getType())) {
                            String str2 = "Read type is incompatible with the field type: " + Util.className(type) + " -> " + Util.className(cachedField.valueClass) + " (" + getType().getName() + "#" + cachedField + ")";
                            if (!z) {
                                throw new KryoException(str2);
                            }
                            if (Log.DEBUG) {
                                Log.debug("kryo", str2);
                            }
                            inputChunked.nextChunk();
                        } else {
                            cachedField.setCanBeNull(false);
                            cachedField.setValueClass(type);
                            cachedField.setReuseSerializer(false);
                        }
                    }
                } catch (KryoException e2) {
                    String str3 = "Unable to read unknown data (unknown type). (" + getType().getName() + "#" + cachedField + ")";
                    if (!z) {
                        throw new KryoException(str3, e2);
                    }
                    if (Log.DEBUG) {
                        Log.debug("kryo", str3, e2);
                    }
                    inputChunked.nextChunk();
                }
            } else if (cachedField == null) {
                if (!z) {
                    throw new KryoException("Unknown field. (" + getType().getName() + ")");
                }
                if (Log.TRACE) {
                    Log.trace("kryo", "Skip unknown field.");
                }
                inputChunked.nextChunk();
            }
            if (Log.TRACE) {
                log("Read", cachedField, input.position());
            }
            cachedField.read(input2, create);
            if (z) {
                inputChunked.nextChunk();
            }
        }
        popTypeVariables(pushTypeVariables);
        return create;
    }

    private FieldSerializer.CachedField[] readFields(Kryo kryo, Input input) {
        if (Log.TRACE) {
            Log.trace("kryo", "Read fields for class: " + this.type.getName());
        }
        int readVarInt = input.readVarInt(true);
        String[] strArr = new String[readVarInt];
        for (int i = 0; i < readVarInt; i++) {
            strArr[i] = input.readString();
            if (Log.TRACE) {
                Log.trace("kryo", "Read field name: " + strArr[i]);
            }
        }
        FieldSerializer.CachedField[] cachedFieldArr = new FieldSerializer.CachedField[readVarInt];
        FieldSerializer.CachedField[] cachedFieldArr2 = this.cachedFields.fields;
        if (readVarInt < 32) {
            for (int i2 = 0; i2 < readVarInt; i2++) {
                String str = strArr[i2];
                int i3 = 0;
                int length = cachedFieldArr2.length;
                while (true) {
                    if (i3 < length) {
                        if (!cachedFieldArr2[i3].name.equals(str)) {
                            i3++;
                        } else {
                            cachedFieldArr[i2] = cachedFieldArr2[i3];
                            break;
                        }
                    } else if (Log.TRACE) {
                        Log.trace("kryo", "Unknown field will be skipped: " + str);
                    }
                }
            }
        } else {
            int length2 = cachedFieldArr2.length - 1;
            for (int i4 = 0; i4 < readVarInt; i4++) {
                String str2 = strArr[i4];
                int i5 = 0;
                int i6 = length2;
                while (true) {
                    if (i5 <= i6) {
                        int i7 = (i5 + i6) >>> 1;
                        int compareTo = str2.compareTo(cachedFieldArr2[i7].name);
                        if (compareTo < 0) {
                            i6 = i7 - 1;
                        } else if (compareTo > 0) {
                            i5 = i7 + 1;
                        } else {
                            cachedFieldArr[i4] = cachedFieldArr2[i7];
                            break;
                        }
                    } else if (Log.TRACE) {
                        Log.trace("kryo", "Unknown field will be skipped: " + str2);
                    }
                }
            }
        }
        kryo.getGraphContext().put(this, cachedFieldArr);
        return cachedFieldArr;
    }

    public CompatibleFieldSerializerConfig getCompatibleFieldSerializerConfig() {
        return this.config;
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/CompatibleFieldSerializer$CompatibleFieldSerializerConfig.class */
    public static class CompatibleFieldSerializerConfig extends FieldSerializer.FieldSerializerConfig {
        boolean chunked;
        boolean readUnknownFieldData = true;
        int chunkSize = 1024;

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.FieldSerializerConfig
        /* renamed from: clone */
        public CompatibleFieldSerializerConfig mo707clone() {
            return (CompatibleFieldSerializerConfig) super.mo707clone();
        }

        public void setReadUnknownFieldData(boolean z) {
            this.readUnknownFieldData = z;
        }

        public boolean getReadUnknownTagData() {
            return this.readUnknownFieldData;
        }

        public void setChunkedEncoding(boolean z) {
            this.chunked = z;
            if (Log.TRACE) {
                Log.trace("kryo", "CompatibleFieldSerializerConfig setChunked: " + z);
            }
        }

        public boolean getChunkedEncoding() {
            return this.chunked;
        }

        public void setChunkSize(int i) {
            this.chunkSize = i;
            if (Log.TRACE) {
                Log.trace("kryo", "CompatibleFieldSerializerConfig setChunkSize: " + i);
            }
        }

        public int getChunkSize() {
            return this.chunkSize;
        }
    }
}
