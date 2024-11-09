package com.esotericsoftware.kryo.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.ReferenceResolver;
import java.util.ArrayList;
import java.util.IdentityHashMap;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/HashMapReferenceResolver.class */
public class HashMapReferenceResolver implements ReferenceResolver {
    protected Kryo kryo;
    protected final IdentityHashMap<Object, Integer> writtenObjects = new IdentityHashMap<>();
    protected final ArrayList readObjects = new ArrayList();

    @Override // com.esotericsoftware.kryo.ReferenceResolver
    public void setKryo(Kryo kryo) {
        this.kryo = kryo;
    }

    @Override // com.esotericsoftware.kryo.ReferenceResolver
    public int addWrittenObject(Object obj) {
        int size = this.writtenObjects.size();
        this.writtenObjects.put(obj, Integer.valueOf(size));
        return size;
    }

    @Override // com.esotericsoftware.kryo.ReferenceResolver
    public int getWrittenId(Object obj) {
        Integer num = this.writtenObjects.get(obj);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    @Override // com.esotericsoftware.kryo.ReferenceResolver
    public int nextReadId(Class cls) {
        int size = this.readObjects.size();
        this.readObjects.add(null);
        return size;
    }

    @Override // com.esotericsoftware.kryo.ReferenceResolver
    public void setReadObject(int i, Object obj) {
        this.readObjects.set(i, obj);
    }

    @Override // com.esotericsoftware.kryo.ReferenceResolver
    public Object getReadObject(Class cls, int i) {
        return this.readObjects.get(i);
    }

    @Override // com.esotericsoftware.kryo.ReferenceResolver
    public void reset() {
        this.readObjects.clear();
        this.writtenObjects.clear();
    }

    @Override // com.esotericsoftware.kryo.ReferenceResolver
    public boolean useReferences(Class cls) {
        return (Util.isWrapperClass(cls) || Util.isEnum(cls)) ? false : true;
    }
}
