package com.esotericsoftware.kryo.util;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.reflectasm.ConstructorAccess;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import org.c.b.a;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/DefaultInstantiatorStrategy.class */
public class DefaultInstantiatorStrategy implements a {
    private a fallbackStrategy;

    public DefaultInstantiatorStrategy() {
    }

    public DefaultInstantiatorStrategy(a aVar) {
        this.fallbackStrategy = aVar;
    }

    public void setFallbackInstantiatorStrategy(a aVar) {
        this.fallbackStrategy = aVar;
    }

    public a getFallbackInstantiatorStrategy() {
        return this.fallbackStrategy;
    }

    @Override // org.c.b.a
    public org.c.a.a newInstantiatorOf(final Class cls) {
        Constructor constructor;
        if (!Util.isAndroid) {
            if (!((cls.getEnclosingClass() == null || !cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) ? false : true)) {
                try {
                    final ConstructorAccess constructorAccess = ConstructorAccess.get(cls);
                    return new org.c.a.a() { // from class: com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy.1
                        @Override // org.c.a.a
                        public Object newInstance() {
                            try {
                                return constructorAccess.newInstance();
                            } catch (Exception e) {
                                throw new KryoException("Error constructing instance of class: " + Util.className(cls), e);
                            }
                        }
                    };
                } catch (Exception unused) {
                }
            }
        }
        try {
            try {
                constructor = cls.getConstructor((Class[]) null);
            } catch (Exception unused2) {
                Constructor declaredConstructor = cls.getDeclaredConstructor((Class[]) null);
                constructor = declaredConstructor;
                declaredConstructor.setAccessible(true);
            }
            final Constructor constructor2 = constructor;
            return new org.c.a.a() { // from class: com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy.2
                @Override // org.c.a.a
                public Object newInstance() {
                    try {
                        return constructor2.newInstance(new Object[0]);
                    } catch (Exception e) {
                        throw new KryoException("Error constructing instance of class: " + Util.className(cls), e);
                    }
                }
            };
        } catch (Exception unused3) {
            if (this.fallbackStrategy == null) {
                if (cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
                    throw new KryoException("Class cannot be created (non-static member class): " + Util.className(cls));
                }
                StringBuilder sb = new StringBuilder("Class cannot be created (missing no-arg constructor): " + Util.className(cls));
                if (cls.getSimpleName().equals("")) {
                    sb.append("\nNote: This is an anonymous class, which is not serializable by default in Kryo. Possible solutions:\n1. Remove uses of anonymous classes, including double brace initialization, from the containing\n").append("class. This is the safest solution, as anonymous classes don't have predictable names for serialization.\n2. Register a FieldSerializer for the containing class and call FieldSerializer\n").append("setIgnoreSyntheticFields(false) on it. This is not safe but may be sufficient temporarily.");
                }
                throw new KryoException(sb.toString());
            }
            return this.fallbackStrategy.newInstantiatorOf(cls);
        }
    }
}
