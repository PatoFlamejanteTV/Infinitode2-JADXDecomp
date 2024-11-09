package com.prineside.tdi2.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.ref.WeakReference;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/WeakReferenceSerializer.class */
public final class WeakReferenceSerializer extends Serializer<WeakReference> {
    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, WeakReference weakReference) {
        output.position();
        kryo.writeClassAndObject(output, weakReference.get());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final WeakReference read2(Kryo kryo, Input input, Class<? extends WeakReference> cls) {
        input.position();
        return new WeakReference(kryo.readClassAndObject(input));
    }
}
