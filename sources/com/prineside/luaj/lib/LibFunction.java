package com.prineside.luaj.lib;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaFunction;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/LibFunction.class */
public abstract class LibFunction extends LuaFunction implements KryoSerializable {
    protected int c;
    protected String d;

    public void write(Kryo kryo, Output output) {
        output.writeInt(this.c);
        kryo.writeObjectOrNull(output, this.d, String.class);
    }

    public void read(Kryo kryo, Input input) {
        this.c = input.readInt();
        this.d = (String) kryo.readObjectOrNull(input, String.class);
    }

    @Override // com.prineside.luaj.LuaFunction, com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public String tojstring() {
        return this.d != null ? "function: " + this.d : super.tojstring();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(LuaValue luaValue, Class cls, String[] strArr) {
        a(luaValue, cls, strArr, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Array<LibFunction> a(LuaValue luaValue, Class<?> cls, String[] strArr, int i) {
        try {
            Array<LibFunction> array = new Array<>(LibFunction.class);
            int length = strArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                LibFunction libFunction = (LibFunction) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                libFunction.c = i + i2;
                libFunction.d = strArr[i2];
                luaValue.set(libFunction.d, libFunction);
                array.add(libFunction);
            }
            return array;
        } catch (Exception e) {
            throw new LuaError("bind failed: " + e);
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public LuaValue call() {
        return argerror(1, "value expected");
    }

    @Override // com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue) {
        return call();
    }

    @Override // com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        return call(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
        return call(luaValue, luaValue2);
    }

    public LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3, LuaValue luaValue4) {
        return call(luaValue, luaValue2, luaValue3);
    }

    @Override // com.prineside.luaj.LuaValue
    public Varargs invoke(Varargs varargs) {
        switch (varargs.narg()) {
            case 0:
                return call();
            case 1:
                return call(varargs.arg1());
            case 2:
                return call(varargs.arg1(), varargs.arg(2));
            case 3:
                return call(varargs.arg1(), varargs.arg(2), varargs.arg(3));
            default:
                return call(varargs.arg1(), varargs.arg(2), varargs.arg(3), varargs.arg(4));
        }
    }
}
