package com.esotericsoftware.kryo;

import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import org.c.a.a;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/Registration.class */
public class Registration {
    private final Class type;
    private final boolean typeNameAscii;
    private final int id;
    private Serializer serializer;
    private a instantiator;

    public Registration(Class cls, Serializer serializer, int i) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if (serializer == null) {
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        this.type = cls;
        this.serializer = serializer;
        this.id = i;
        this.typeNameAscii = Util.isAscii(cls.getName());
    }

    public Class getType() {
        return this.type;
    }

    public boolean isTypeNameAscii() {
        return this.typeNameAscii;
    }

    public int getId() {
        return this.id;
    }

    public Serializer getSerializer() {
        return this.serializer;
    }

    public void setSerializer(Serializer serializer) {
        if (serializer == null) {
            throw new IllegalArgumentException("serializer cannot be null.");
        }
        this.serializer = serializer;
        if (Log.TRACE) {
            Log.trace("kryo", "Update registered serializer: " + this.type.getName() + " (" + serializer.getClass().getName() + ")");
        }
    }

    public a getInstantiator() {
        return this.instantiator;
    }

    public void setInstantiator(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("instantiator cannot be null.");
        }
        this.instantiator = aVar;
    }

    public String toString() {
        return "[" + this.id + ", " + Util.className(this.type) + "]";
    }
}
