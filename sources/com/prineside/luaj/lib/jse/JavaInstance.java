package com.prineside.luaj.lib.jse;

import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaNumber;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaUserdata;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.lib.jse.JavaClass;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaInstance.class */
public class JavaInstance extends LuaUserdata {

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    @Null
    protected JavaClass f1621a;

    @Override // com.prineside.luaj.LuaUserdata, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.f1621a, JavaClass.class);
    }

    @Override // com.prineside.luaj.LuaUserdata, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1621a = (JavaClass) kryo.readObjectOrNull(input, JavaClass.class);
    }

    protected JavaInstance() {
    }

    public JavaInstance(Object obj) {
        super(obj);
    }

    public JavaClass getJavaClass() {
        if (this.f1621a == null) {
            this.f1621a = JavaClass.forClass(this.m_instance.getClass());
        }
        return this.f1621a;
    }

    @Override // com.prineside.luaj.LuaValue
    public LuaValue len() {
        JavaClass javaClass = getJavaClass();
        if (javaClass == null) {
            return super.len();
        }
        if (((Class) javaClass.m_instance).isArray()) {
            return valueOf(Array.getLength(this.m_instance));
        }
        if (this.m_instance instanceof com.badlogic.gdx.utils.Array) {
            return valueOf(((com.badlogic.gdx.utils.Array) this.m_instance).size);
        }
        return super.len();
    }

    @Override // com.prineside.luaj.LuaUserdata, com.prineside.luaj.LuaValue
    public LuaValue get(LuaValue luaValue) {
        JavaClass javaClass = getJavaClass();
        if (!(luaValue instanceof LuaString)) {
            return super.get(luaValue);
        }
        JavaClass.NamedClassEntry objectFieldOrMethod = javaClass.getObjectFieldOrMethod(luaValue);
        if (objectFieldOrMethod != null) {
            if (objectFieldOrMethod.isField) {
                Field field = (Field) objectFieldOrMethod.entry;
                try {
                    switch (objectFieldOrMethod.fieldType) {
                        case 0:
                            return LuaNumber.valueOf(field.getInt(this.m_instance));
                        case 1:
                            return LuaNumber.valueOf(field.getFloat(this.m_instance));
                        case 2:
                            return LuaNumber.valueOf(field.getDouble(this.m_instance));
                        case 3:
                            return field.getBoolean(this.m_instance) ? LuaValue.TRUE : LuaValue.FALSE;
                        case 4:
                            return LuaNumber.valueOf((int) field.getByte(this.m_instance));
                        case 5:
                            return LuaNumber.valueOf((int) field.getShort(this.m_instance));
                        case 6:
                            return LuaNumber.valueOf((int) field.getChar(this.m_instance));
                        case 7:
                            return LuaNumber.valueOf(field.getLong(this.m_instance));
                        default:
                            return CoerceJavaToLua.coerce(field.get(this.m_instance));
                    }
                } catch (Exception e) {
                    throw new LuaError("Failed to access field " + field, 1, e);
                }
            }
            return (LuaValue) objectFieldOrMethod.entry;
        }
        if (this.m_metatable == null) {
            throw new LuaError("Field / method / inner class '" + luaValue.typename() + SequenceUtils.SPACE + luaValue + "' not found in " + this.m_instance.getClass() + SequenceUtils.SPACE + this + " and no metatable is set");
        }
        return super.get(luaValue);
    }

    @Override // com.prineside.luaj.LuaUserdata, com.prineside.luaj.LuaValue
    public void set(LuaValue luaValue, LuaValue luaValue2) {
        if (!(luaValue instanceof LuaString)) {
            super.set(luaValue, luaValue2);
            return;
        }
        Field field = getJavaClass().getInstanceFields().get((LuaString) luaValue);
        if (field != null) {
            if (Modifier.isFinal(field.getModifiers())) {
                throw new LuaError("Final field " + field.getName() + " of class " + field.getDeclaringClass() + " can not be changed");
            }
            try {
                Class<?> type = field.getType();
                if (type == Integer.TYPE) {
                    field.setInt(this.m_instance, luaValue2.toint());
                    return;
                }
                if (type == Float.TYPE) {
                    field.setFloat(this.m_instance, luaValue2.tofloat());
                    return;
                }
                if (type == Double.TYPE) {
                    field.setDouble(this.m_instance, luaValue2.todouble());
                    return;
                }
                if (type == Boolean.TYPE) {
                    field.setBoolean(this.m_instance, luaValue2.toboolean());
                    return;
                }
                if (type == Byte.TYPE) {
                    field.setByte(this.m_instance, luaValue2.tobyte());
                    return;
                }
                if (type == Short.TYPE) {
                    field.setShort(this.m_instance, luaValue2.toshort());
                    return;
                }
                if (type == Character.TYPE) {
                    field.setChar(this.m_instance, luaValue2.tochar());
                    return;
                } else if (type == Long.TYPE) {
                    field.setLong(this.m_instance, luaValue2.tolong());
                    return;
                } else {
                    field.set(this.m_instance, CoerceLuaToJava.coerce(luaValue2, type));
                    return;
                }
            } catch (Exception e) {
                throw new LuaError(e);
            }
        }
        super.set(luaValue, luaValue2);
    }
}
