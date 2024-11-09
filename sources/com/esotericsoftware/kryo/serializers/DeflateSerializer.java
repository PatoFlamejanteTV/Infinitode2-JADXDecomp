package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.InputChunked;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.OutputChunked;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/DeflateSerializer.class */
public class DeflateSerializer extends Serializer {
    private final Serializer serializer;
    private boolean noHeaders = true;
    private int compressionLevel = 4;

    public DeflateSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, Object obj) {
        OutputChunked outputChunked = new OutputChunked(output, 256);
        Deflater deflater = new Deflater(this.compressionLevel, this.noHeaders);
        try {
            try {
                DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputChunked, deflater);
                Output output2 = new Output(deflaterOutputStream, 256);
                this.serializer.write(kryo, output2, obj);
                output2.flush();
                deflaterOutputStream.finish();
                deflater.end();
                outputChunked.endChunk();
            } catch (IOException e) {
                throw new KryoException(e);
            }
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public Object read2(Kryo kryo, Input input, Class cls) {
        Inflater inflater = new Inflater(this.noHeaders);
        try {
            return this.serializer.read2(kryo, new Input(new InflaterInputStream(new InputChunked(input, 256), inflater), 256), cls);
        } finally {
            inflater.end();
        }
    }

    public void setNoHeaders(boolean z) {
        this.noHeaders = z;
    }

    public void setCompressionLevel(int i) {
        this.compressionLevel = i;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public Object copy(Kryo kryo, Object obj) {
        return this.serializer.copy(kryo, obj);
    }
}
