package com.esotericsoftware.kryo.util;

import com.esotericsoftware.kryo.ClassResolver;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/util/DefaultClassResolver.class */
public class DefaultClassResolver implements ClassResolver {
    public static final byte NAME = -1;
    protected Kryo kryo;
    protected IdentityObjectIntMap<Class> classToNameId;
    protected IntMap<Class> nameIdToClass;
    protected ObjectMap<String, Class> nameToClass;
    protected int nextNameId;
    private Registration memoizedClassIdValue;
    private Class memoizedClass;
    private Registration memoizedClassValue;
    protected final IntMap<Registration> idToRegistration = new IntMap<>();
    protected final IdentityMap<Class, Registration> classToRegistration = new IdentityMap<>();
    private int memoizedClassId = -1;

    @Override // com.esotericsoftware.kryo.ClassResolver
    public void setKryo(Kryo kryo) {
        this.kryo = kryo;
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration register(Registration registration) {
        this.memoizedClassId = -1;
        this.memoizedClass = null;
        if (registration == null) {
            throw new IllegalArgumentException("registration cannot be null.");
        }
        if (registration.getId() != -1) {
            if (Log.TRACE) {
                Log.trace("kryo", "Register class ID " + registration.getId() + ": " + Util.className(registration.getType()) + " (" + registration.getSerializer().getClass().getName() + ")");
            }
            this.idToRegistration.put(registration.getId(), registration);
        } else if (Log.TRACE) {
            Log.trace("kryo", "Register class name: " + Util.className(registration.getType()) + " (" + registration.getSerializer().getClass().getName() + ")");
        }
        this.classToRegistration.put(registration.getType(), registration);
        Class wrapperClass = Util.getWrapperClass(registration.getType());
        if (wrapperClass != registration.getType()) {
            this.classToRegistration.put(wrapperClass, registration);
        }
        return registration;
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration unregister(int i) {
        Registration remove = this.idToRegistration.remove(i);
        if (remove != null) {
            this.classToRegistration.remove(remove.getType());
            this.memoizedClassId = -1;
            this.memoizedClass = null;
            Class wrapperClass = Util.getWrapperClass(remove.getType());
            if (wrapperClass != remove.getType()) {
                this.classToRegistration.remove(wrapperClass);
            }
        }
        return remove;
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration registerImplicit(Class cls) {
        return register(new Registration(cls, this.kryo.getDefaultSerializer(cls), -1));
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration getRegistration(Class cls) {
        if (cls == this.memoizedClass) {
            return this.memoizedClassValue;
        }
        Registration registration = this.classToRegistration.get(cls);
        if (registration != null) {
            this.memoizedClass = cls;
            this.memoizedClassValue = registration;
        }
        return registration;
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration getRegistration(int i) {
        return this.idToRegistration.get(i);
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration writeClass(Output output, Class cls) {
        if (cls == null) {
            if (Log.TRACE || (Log.DEBUG && this.kryo.getDepth() == 1)) {
                Util.log("Write", null, output.position());
            }
            output.writeByte((byte) 0);
            return null;
        }
        Registration registration = this.kryo.getRegistration(cls);
        if (registration.getId() == -1) {
            writeName(output, cls, registration);
        } else {
            if (Log.TRACE) {
                Log.trace("kryo", "Write class " + registration.getId() + ": " + Util.className(cls) + Util.pos(output.position()));
            }
            output.writeVarInt(registration.getId() + 2, true);
        }
        return registration;
    }

    protected void writeName(Output output, Class cls, Registration registration) {
        int i;
        output.writeByte(1);
        if (this.classToNameId != null && (i = this.classToNameId.get(cls, -1)) != -1) {
            if (Log.TRACE) {
                Log.trace("kryo", "Write class name reference " + i + ": " + Util.className(cls) + Util.pos(output.position()));
            }
            output.writeVarInt(i, true);
            return;
        }
        if (Log.TRACE) {
            Log.trace("kryo", "Write class name: " + Util.className(cls) + Util.pos(output.position()));
        }
        int i2 = this.nextNameId;
        this.nextNameId = i2 + 1;
        if (this.classToNameId == null) {
            this.classToNameId = new IdentityObjectIntMap<>();
        }
        this.classToNameId.put(cls, i2);
        output.writeVarInt(i2, true);
        if (registration.isTypeNameAscii()) {
            output.writeAscii(cls.getName());
        } else {
            output.writeString(cls.getName());
        }
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration readClass(Input input) {
        int readVarInt = input.readVarInt(true);
        switch (readVarInt) {
            case 0:
                if (Log.TRACE || (Log.DEBUG && this.kryo.getDepth() == 1)) {
                    Util.log("Read", null, input.position());
                    return null;
                }
                return null;
            case 1:
                return readName(input);
            default:
                if (readVarInt != this.memoizedClassId) {
                    Registration registration = this.idToRegistration.get(readVarInt - 2);
                    if (registration == null) {
                        throw new KryoException("Encountered unregistered class ID: " + (readVarInt - 2));
                    }
                    if (Log.TRACE) {
                        Log.trace("kryo", "Read class " + (readVarInt - 2) + ": " + Util.className(registration.getType()) + Util.pos(input.position()));
                    }
                    this.memoizedClassId = readVarInt;
                    this.memoizedClassIdValue = registration;
                    return registration;
                }
                if (Log.TRACE) {
                    Log.trace("kryo", "Read class " + (readVarInt - 2) + ": " + Util.className(this.memoizedClassIdValue.getType()) + Util.pos(input.position()));
                }
                return this.memoizedClassIdValue;
        }
    }

    protected Registration readName(Input input) {
        int readVarInt = input.readVarInt(true);
        if (this.nameIdToClass == null) {
            this.nameIdToClass = new IntMap<>();
        }
        Class<?> cls = this.nameIdToClass.get(readVarInt);
        Class<?> cls2 = cls;
        if (cls == null) {
            String readString = input.readString();
            Class<?> typeByName = getTypeByName(readString);
            cls2 = typeByName;
            if (typeByName == null) {
                try {
                    cls2 = Class.forName(readString, false, this.kryo.getClassLoader());
                } catch (ClassNotFoundException e) {
                    try {
                        cls2 = Class.forName(readString, false, Kryo.class.getClassLoader());
                    } catch (ClassNotFoundException unused) {
                        throw new KryoException("Unable to find class: " + readString, e);
                    }
                }
                if (this.nameToClass == null) {
                    this.nameToClass = new ObjectMap<>();
                }
                this.nameToClass.put(readString, cls2);
            }
            this.nameIdToClass.put(readVarInt, cls2);
            if (Log.TRACE) {
                Log.trace("kryo", "Read class name: " + readString + Util.pos(input.position()));
            }
        } else if (Log.TRACE) {
            Log.trace("kryo", "Read class name reference " + readVarInt + ": " + Util.className(cls2) + Util.pos(input.position()));
        }
        return this.kryo.getRegistration(cls2);
    }

    protected Class getTypeByName(String str) {
        if (this.nameToClass != null) {
            return this.nameToClass.get(str);
        }
        return null;
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public void reset() {
        if (!this.kryo.isRegistrationRequired()) {
            if (this.classToNameId != null) {
                this.classToNameId.clear(2048);
            }
            if (this.nameIdToClass != null) {
                this.nameIdToClass.clear();
            }
            this.nextNameId = 0;
        }
    }
}
