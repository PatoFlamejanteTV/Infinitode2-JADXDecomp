package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.InputChunked;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.OutputChunked;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.util.IntMap;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TaggedFieldSerializer.class */
public class TaggedFieldSerializer<T> extends FieldSerializer<T> {
    private FieldSerializer.CachedField[] writeTags;
    private IntMap<FieldSerializer.CachedField> readTags;
    private final TaggedFieldSerializerConfig config;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TaggedFieldSerializer$Tag.class */
    public @interface Tag {
        int value();
    }

    public TaggedFieldSerializer(Kryo kryo, Class cls) {
        this(kryo, cls, new TaggedFieldSerializerConfig());
    }

    public TaggedFieldSerializer(Kryo kryo, Class cls, TaggedFieldSerializerConfig taggedFieldSerializerConfig) {
        super(kryo, cls, taggedFieldSerializerConfig);
        this.config = taggedFieldSerializerConfig;
        setAcceptsNull(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer
    public void initializeCachedFields() {
        FieldSerializer.CachedField[] cachedFieldArr = this.cachedFields.fields;
        int length = cachedFieldArr.length;
        for (int i = 0; i < length; i++) {
            if (cachedFieldArr[i].field.getAnnotation(Tag.class) == null) {
                if (Log.TRACE) {
                    Log.trace("kryo", "Ignoring field without tag: " + cachedFieldArr[i]);
                }
                super.removeField(cachedFieldArr[i]);
            }
        }
        FieldSerializer.CachedField[] cachedFieldArr2 = this.cachedFields.fields;
        ArrayList arrayList = new ArrayList(cachedFieldArr2.length);
        this.readTags = new IntMap<>((int) (cachedFieldArr2.length / 0.8f));
        for (FieldSerializer.CachedField cachedField : cachedFieldArr2) {
            Field field = cachedField.field;
            int value = ((Tag) field.getAnnotation(Tag.class)).value();
            if (!this.readTags.containsKey(value)) {
                this.readTags.put(value, cachedField);
                if (field.getAnnotation(Deprecated.class) == null) {
                    arrayList.add(cachedField);
                }
                cachedField.tag = value;
            } else {
                throw new KryoException(String.format("Duplicate tag %d on fields: %s and %s", Integer.valueOf(value), field, arrayList.get(value)));
            }
        }
        this.writeTags = (FieldSerializer.CachedField[]) arrayList.toArray(new FieldSerializer.CachedField[arrayList.size()]);
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer
    public void removeField(String str) {
        super.removeField(str);
        initializeCachedFields();
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer
    public void removeField(FieldSerializer.CachedField cachedField) {
        super.removeField(cachedField);
        initializeCachedFields();
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        Output output2;
        if (t == null) {
            output.writeByte((byte) 0);
            return;
        }
        int pushTypeVariables = pushTypeVariables();
        FieldSerializer.CachedField[] cachedFieldArr = this.writeTags;
        output.writeVarInt(cachedFieldArr.length + 1, true);
        writeHeader(kryo, output, t);
        boolean z = this.config.chunked;
        boolean z2 = this.config.readUnknownTagData;
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
            output.writeVarInt(cachedField.tag, true);
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

    protected void writeHeader(Kryo kryo, Output output, T t) {
    }

    @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T read2(Kryo kryo, Input input, Class<? extends T> cls) {
        Input input2;
        int readVarInt = input.readVarInt(true);
        if (readVarInt == 0) {
            return null;
        }
        int i = readVarInt - 1;
        int pushTypeVariables = pushTypeVariables();
        T create = create(kryo, input, cls);
        kryo.reference(create);
        boolean z = this.config.chunked;
        boolean z2 = this.config.readUnknownTagData;
        InputChunked inputChunked = null;
        if (z) {
            InputChunked inputChunked2 = new InputChunked(input, this.config.chunkSize);
            inputChunked = inputChunked2;
            input2 = inputChunked2;
        } else {
            input2 = input;
        }
        IntMap<FieldSerializer.CachedField> intMap = this.readTags;
        for (int i2 = 0; i2 < i; i2++) {
            int readVarInt2 = input.readVarInt(true);
            FieldSerializer.CachedField cachedField = intMap.get(readVarInt2);
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
                                Log.trace("kryo", "Read unknown tag " + readVarInt2 + " data, type: " + Util.className(type));
                            }
                            try {
                                kryo.readObject(input2, type);
                            } catch (KryoException e) {
                                String str = "Unable to read unknown tag " + readVarInt2 + " data, type: " + Util.className(type) + " (" + getType().getName() + "#" + cachedField + ")";
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
                        } else {
                            cachedField.setCanBeNull(false);
                            cachedField.setValueClass(type);
                            cachedField.setReuseSerializer(false);
                        }
                    }
                } catch (KryoException e2) {
                    String str2 = "Unable to read unknown tag " + readVarInt2 + " data (unknown type). (" + getType().getName() + "#" + cachedField + ")";
                    if (!z) {
                        throw new KryoException(str2, e2);
                    }
                    if (Log.DEBUG) {
                        Log.debug("kryo", str2, e2);
                    }
                    inputChunked.nextChunk();
                }
            } else if (cachedField == null) {
                if (!z) {
                    throw new KryoException("Unknown field tag: " + readVarInt2 + " (" + getType().getName() + ")");
                }
                if (Log.TRACE) {
                    Log.trace("kryo", "Skip unknown field tag: " + readVarInt2);
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

    public TaggedFieldSerializerConfig getTaggedFieldSerializerConfig() {
        return this.config;
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/TaggedFieldSerializer$TaggedFieldSerializerConfig.class */
    public static class TaggedFieldSerializerConfig extends FieldSerializer.FieldSerializerConfig {
        boolean readUnknownTagData;
        boolean chunked;
        int chunkSize = 1024;

        @Override // com.esotericsoftware.kryo.serializers.FieldSerializer.FieldSerializerConfig
        /* renamed from: clone */
        public TaggedFieldSerializerConfig mo707clone() {
            return (TaggedFieldSerializerConfig) super.mo707clone();
        }

        public void setReadUnknownTagData(boolean z) {
            this.readUnknownTagData = z;
        }

        public boolean getReadUnknownTagData() {
            return this.readUnknownTagData;
        }

        public void setChunkedEncoding(boolean z) {
            this.chunked = z;
            if (Log.TRACE) {
                Log.trace("kryo", "TaggedFieldSerializerConfig setChunked: " + z);
            }
        }

        public boolean getChunkedEncoding() {
            return this.chunked;
        }

        public void setChunkSize(int i) {
            this.chunkSize = i;
            if (Log.TRACE) {
                Log.trace("kryo", "TaggedFieldSerializerConfig setChunkSize: " + i);
            }
        }

        public int getChunkSize() {
            return this.chunkSize;
        }
    }
}
