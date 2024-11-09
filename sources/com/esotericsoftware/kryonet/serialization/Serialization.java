package com.esotericsoftware.kryonet.serialization;

import com.esotericsoftware.kryonet.Connection;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/serialization/Serialization.class */
public interface Serialization {
    void write(Connection connection, ByteBuffer byteBuffer, Object obj);

    Object read(Connection connection, ByteBuffer byteBuffer);

    int getLengthLength();

    void writeLength(ByteBuffer byteBuffer, int i);

    int readLength(ByteBuffer byteBuffer);
}
