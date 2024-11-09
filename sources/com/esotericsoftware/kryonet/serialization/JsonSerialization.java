package com.esotericsoftware.kryonet.serialization;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonException;
import com.esotericsoftware.kryo.io.ByteBufferInputStream;
import com.esotericsoftware.kryo.io.ByteBufferOutputStream;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.minlog.Log;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/serialization/JsonSerialization.class */
public class JsonSerialization implements Serialization {
    private final Json json = new Json();
    private final ByteBufferInputStream byteBufferInputStream = new ByteBufferInputStream();
    private final ByteBufferOutputStream byteBufferOutputStream = new ByteBufferOutputStream();
    private final OutputStreamWriter writer = new OutputStreamWriter(this.byteBufferOutputStream);
    private boolean logging = true;
    private boolean prettyPrint = true;
    private byte[] logBuffer = new byte[0];

    public JsonSerialization() {
        this.json.addClassTag("RegisterTCP", FrameworkMessage.RegisterTCP.class);
        this.json.addClassTag("RegisterUDP", FrameworkMessage.RegisterUDP.class);
        this.json.addClassTag("KeepAlive", FrameworkMessage.KeepAlive.class);
        this.json.addClassTag("DiscoverHost", FrameworkMessage.DiscoverHost.class);
        this.json.addClassTag("Ping", FrameworkMessage.Ping.class);
        this.json.setWriter(this.writer);
    }

    public void setLogging(boolean z, boolean z2) {
        this.logging = z;
        this.prettyPrint = z2;
    }

    @Override // com.esotericsoftware.kryonet.serialization.Serialization
    public synchronized void write(Connection connection, ByteBuffer byteBuffer, Object obj) {
        this.byteBufferOutputStream.setByteBuffer(byteBuffer);
        int position = byteBuffer.position();
        try {
            this.json.writeValue(obj, Object.class, (Class) null);
            this.writer.flush();
            if (Log.INFO && this.logging) {
                int position2 = byteBuffer.position();
                byteBuffer.position(position);
                byteBuffer.limit(position2);
                int i = position2 - position;
                if (this.logBuffer.length < i) {
                    this.logBuffer = new byte[i];
                }
                byteBuffer.get(this.logBuffer, 0, i);
                byteBuffer.position(position2);
                byteBuffer.limit(byteBuffer.capacity());
                String str = new String(this.logBuffer, 0, i);
                if (this.prettyPrint) {
                    str = this.json.prettyPrint(str);
                }
                Log.info("Wrote: " + str);
            }
        } catch (Exception e) {
            throw new JsonException("Error writing object: " + obj, e);
        }
    }

    @Override // com.esotericsoftware.kryonet.serialization.Serialization
    public synchronized Object read(Connection connection, ByteBuffer byteBuffer) {
        this.byteBufferInputStream.setByteBuffer(byteBuffer);
        return this.json.fromJson(Object.class, this.byteBufferInputStream);
    }

    @Override // com.esotericsoftware.kryonet.serialization.Serialization
    public void writeLength(ByteBuffer byteBuffer, int i) {
        byteBuffer.putInt(i);
    }

    @Override // com.esotericsoftware.kryonet.serialization.Serialization
    public int readLength(ByteBuffer byteBuffer) {
        return byteBuffer.getInt();
    }

    @Override // com.esotericsoftware.kryonet.serialization.Serialization
    public int getLengthLength() {
        return 4;
    }
}
