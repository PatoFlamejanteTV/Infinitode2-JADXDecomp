package com.prineside.luaj.debug;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.FPrototype;
import com.prineside.luaj.Lua;
import com.prineside.luaj.LuaClosure;
import com.prineside.luaj.LuaFunction;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.utils.REGS;
import net.bytebuddy.description.type.TypeDescription;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/luaj/debug/CallFrame.class */
public final class CallFrame implements KryoSerializable {

    /* renamed from: b, reason: collision with root package name */
    private static final LuaString f1558b = LuaValue.valueOf(TypeDescription.Generic.OfWildcardType.SYMBOL);
    public LuaFunction f;
    public int pc;
    private int c;
    private Varargs d;
    private LuaValue[] e;

    /* renamed from: a, reason: collision with root package name */
    CallFrame f1559a;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeClassAndObject(output, this.f);
        output.writeInt(this.pc);
        output.writeInt(this.c);
        kryo.writeClassAndObject(output, this.d);
        LuaValue.NILLABLE_SERIALIZER.writeClassAndObject(kryo, output, this.e);
        kryo.writeObjectOrNull(output, this.f1559a, CallFrame.class);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f = (LuaFunction) kryo.readClassAndObject(input);
        this.pc = input.readInt();
        this.c = input.readInt();
        this.d = (Varargs) kryo.readClassAndObject(input);
        this.e = (LuaValue[]) kryo.readClassAndObject(input);
        this.f1559a = (CallFrame) kryo.readObjectOrNull(input, CallFrame.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(LuaClosure luaClosure, Varargs varargs, LuaValue[] luaValueArr) {
        this.f = luaClosure;
        this.d = varargs;
        this.e = luaValueArr;
    }

    public final String shortsource() {
        return this.f.isclosure() ? this.f.checkclosure().p.shortsource() : "[Java]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(LuaFunction luaFunction) {
        this.f = luaFunction;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        this.f = null;
        this.d = null;
        this.e = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, Varargs varargs, int i2) {
        this.pc = i;
        this.d = varargs;
        this.c = i2;
    }

    public final int currentline() {
        short[] sArr;
        if (this.f.isclosure() && (sArr = this.f.checkclosure().p.lineinfo) != null && this.pc >= 0 && this.pc < sArr.length) {
            return sArr[this.pc];
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        if (this.f.isclosure()) {
            return this.f.checkclosure().p.linedefined;
        }
        return -1;
    }

    public static NameWhat getfuncname(CallFrame callFrame) {
        LuaString luaString;
        if (!callFrame.f.isclosure()) {
            return new NameWhat(callFrame.f.classnamestub(), "Java");
        }
        FPrototype fPrototype = callFrame.f.checkclosure().p;
        int i = callFrame.pc;
        int i2 = fPrototype.code[i];
        switch (Lua.GET_OPCODE(i2)) {
            case 6:
            case 7:
            case 12:
                luaString = LuaValue.INDEX;
                break;
            case 8:
            case 10:
                luaString = LuaValue.NEWINDEX;
                break;
            case 9:
            case 11:
            case 20:
            case 23:
            case 27:
            case 28:
            case 31:
            case 32:
            case 33:
            default:
                return null;
            case 13:
                luaString = LuaValue.ADD;
                break;
            case 14:
                luaString = LuaValue.SUB;
                break;
            case 15:
                luaString = LuaValue.MUL;
                break;
            case 16:
                luaString = LuaValue.DIV;
                break;
            case 17:
                luaString = LuaValue.MOD;
                break;
            case 18:
                luaString = LuaValue.POW;
                break;
            case 19:
                luaString = LuaValue.UNM;
                break;
            case 21:
                luaString = LuaValue.LEN;
                break;
            case 22:
                luaString = LuaValue.CONCAT;
                break;
            case 24:
                luaString = LuaValue.EQ;
                break;
            case 25:
                luaString = LuaValue.LT;
                break;
            case 26:
                luaString = LuaValue.LE;
                break;
            case 29:
            case 30:
                return getobjname(fPrototype, i, Lua.GETARG_A(i2));
            case 34:
                return new NameWhat("(for iterator)", "(for iterator");
        }
        return new NameWhat(luaString.tojstring(), "metamethod");
    }

    public static NameWhat getobjname(FPrototype fPrototype, int i, int i2) {
        int GETARG_Ax;
        LuaString luaString;
        LuaString luaString2 = fPrototype.getlocalname(i2 + 1, i);
        if (luaString2 != null) {
            return new NameWhat(luaString2.tojstring(), "local");
        }
        int findsetreg = findsetreg(fPrototype, i, i2);
        if (findsetreg != -1) {
            int i3 = fPrototype.code[findsetreg];
            switch (Lua.GET_OPCODE(i3)) {
                case 0:
                    int GETARG_A = Lua.GETARG_A(i3);
                    int GETARG_B = Lua.GETARG_B(i3);
                    if (GETARG_B < GETARG_A) {
                        return getobjname(fPrototype, findsetreg, GETARG_B);
                    }
                    return null;
                case 1:
                case 2:
                    if (Lua.GET_OPCODE(i3) != 1) {
                        GETARG_Ax = Lua.GETARG_Ax(fPrototype.code[findsetreg + 1]);
                    } else {
                        GETARG_Ax = Lua.GETARG_Bx(i3);
                    }
                    int i4 = GETARG_Ax;
                    if (fPrototype.k[i4].isstring()) {
                        return new NameWhat(fPrototype.k[i4].strvalue().tojstring(), "constant");
                    }
                    return null;
                case 3:
                case 4:
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    return null;
                case 5:
                    int GETARG_B2 = Lua.GETARG_B(i3);
                    LuaString luaString3 = GETARG_B2 < fPrototype.upvalues.length ? fPrototype.upvalues[GETARG_B2].name : f1558b;
                    LuaString luaString4 = luaString3;
                    if (luaString3 == null) {
                        return null;
                    }
                    return new NameWhat(luaString4.tojstring(), "upvalue");
                case 6:
                case 7:
                    int GETARG_C = Lua.GETARG_C(i3);
                    int GETARG_B3 = Lua.GETARG_B(i3);
                    if (Lua.GET_OPCODE(i3) == 7) {
                        luaString = fPrototype.getlocalname(GETARG_B3 + 1, findsetreg);
                    } else {
                        luaString = GETARG_B3 < fPrototype.upvalues.length ? fPrototype.upvalues[GETARG_B3].name : f1558b;
                    }
                    LuaString luaString5 = luaString;
                    return new NameWhat(kname(fPrototype, findsetreg, GETARG_C), (luaString5 == null || !luaString5.eq_b(LuaValue.ENV)) ? "field" : "global");
                case 12:
                    return new NameWhat(kname(fPrototype, findsetreg, Lua.GETARG_C(i3)), "method");
            }
        }
        return null;
    }

    public static String kname(FPrototype fPrototype, int i, int i2) {
        if (Lua.ISK(i2)) {
            LuaValue luaValue = fPrototype.k[Lua.INDEXK(i2)];
            if (luaValue.isstring()) {
                return luaValue.tojstring();
            }
            return TypeDescription.Generic.OfWildcardType.SYMBOL;
        }
        NameWhat nameWhat = getobjname(fPrototype, i, i2);
        if (nameWhat != null && "constant".equals(nameWhat.namewhat)) {
            return nameWhat.name;
        }
        return TypeDescription.Generic.OfWildcardType.SYMBOL;
    }

    public static int findsetreg(FPrototype fPrototype, int i, int i2) {
        int i3 = -1;
        int i4 = 0;
        while (i4 < i) {
            int i5 = fPrototype.code[i4];
            int GET_OPCODE = Lua.GET_OPCODE(i5);
            int GETARG_A = Lua.GETARG_A(i5);
            switch (GET_OPCODE) {
                case 4:
                    int GETARG_B = Lua.GETARG_B(i5);
                    if (GETARG_A <= i2 && i2 <= GETARG_A + GETARG_B) {
                        i3 = i4;
                        break;
                    }
                    break;
                case 23:
                    int GETARG_sBx = Lua.GETARG_sBx(i5);
                    int i6 = i4 + 1 + GETARG_sBx;
                    if (i4 < i6 && i6 <= i) {
                        i4 += GETARG_sBx;
                        break;
                    }
                    break;
                case 27:
                    if (i2 != GETARG_A) {
                        break;
                    } else {
                        i3 = i4;
                        break;
                    }
                case 29:
                case 30:
                    if (i2 < GETARG_A) {
                        break;
                    } else {
                        i3 = i4;
                        break;
                    }
                case 34:
                    if (i2 < GETARG_A + 2) {
                        break;
                    } else {
                        i3 = i4;
                        break;
                    }
                case 36:
                    if (((i5 >> 14) & 511) != 0) {
                        break;
                    } else {
                        i4++;
                        break;
                    }
                default:
                    if (Lua.testAMode(GET_OPCODE) && i2 == GETARG_A) {
                        i3 = i4;
                        break;
                    }
                    break;
            }
            i4++;
        }
        return i3;
    }
}
