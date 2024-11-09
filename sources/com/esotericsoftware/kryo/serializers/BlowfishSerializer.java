package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/BlowfishSerializer.class */
public class BlowfishSerializer extends Serializer {
    private final Serializer serializer;
    private static SecretKeySpec keySpec;

    public BlowfishSerializer(Serializer serializer, byte[] bArr) {
        this.serializer = serializer;
        keySpec = new SecretKeySpec(bArr, "Blowfish");
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, Object obj) {
        CipherOutputStream cipherOutputStream = new CipherOutputStream(output, getCipher(1));
        Output output2 = new Output(cipherOutputStream, 256) { // from class: com.esotericsoftware.kryo.serializers.BlowfishSerializer.1
            @Override // com.esotericsoftware.kryo.io.Output, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        };
        this.serializer.write(kryo, output2, obj);
        output2.flush();
        try {
            cipherOutputStream.close();
        } catch (IOException e) {
            throw new KryoException(e);
        }
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public Object read2(Kryo kryo, Input input, Class cls) {
        return this.serializer.read2(kryo, new Input(new CipherInputStream(input, getCipher(2)), 256), cls);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public Object copy(Kryo kryo, Object obj) {
        return this.serializer.copy(kryo, obj);
    }

    private static Cipher getCipher(int i) {
        try {
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(i, keySpec);
            return cipher;
        } catch (Exception e) {
            throw new KryoException(e);
        }
    }
}
