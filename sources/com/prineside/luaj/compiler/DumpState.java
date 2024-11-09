package com.prineside.luaj.compiler;

import com.prineside.luaj.FPrototype;
import com.prineside.luaj.LoadState;
import com.prineside.luaj.LocVars;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaValue;
import java.io.DataOutputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/DumpState.class */
public class DumpState {
    public static boolean ALLOW_INTEGER_CASTING = false;
    public static final int NUMBER_FORMAT_FLOATS_OR_DOUBLES = 0;
    public static final int NUMBER_FORMAT_INTS_ONLY = 1;
    public static final int NUMBER_FORMAT_NUM_PATCH_INT32 = 4;
    public static final int NUMBER_FORMAT_DEFAULT = 0;
    private DataOutputStream d;
    private boolean e;

    /* renamed from: a, reason: collision with root package name */
    private boolean f1526a = true;

    /* renamed from: b, reason: collision with root package name */
    private int f1527b = 0;
    private int c = 8;
    private int f = 0;

    public DumpState(OutputStream outputStream, boolean z) {
        this.d = new DataOutputStream(outputStream);
        this.e = z;
    }

    private void a(int i) {
        this.d.write(i);
    }

    private void b(int i) {
        if (this.f1526a) {
            this.d.writeByte(i & 255);
            this.d.writeByte((i >> 8) & 255);
            this.d.writeByte((i >> 16) & 255);
            this.d.writeByte(i >>> 24);
            return;
        }
        this.d.writeInt(i);
    }

    private void a(LuaString luaString) {
        int i = luaString.len().toint();
        b(i + 1);
        luaString.write(this.d, 0, i);
        this.d.write(0);
    }

    private void a(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        if (this.f1526a) {
            b((int) doubleToLongBits);
            b((int) (doubleToLongBits >> 32));
        } else {
            this.d.writeLong(doubleToLongBits);
        }
    }

    private void a(FPrototype fPrototype) {
        int[] iArr = fPrototype.code;
        b(iArr.length);
        for (int i : iArr) {
            b(i);
        }
    }

    private void b(FPrototype fPrototype) {
        LuaValue[] luaValueArr = fPrototype.k;
        b(luaValueArr.length);
        for (LuaValue luaValue : luaValueArr) {
            switch (luaValue.type()) {
                case 0:
                    this.d.write(0);
                    break;
                case 1:
                    this.d.write(1);
                    a(luaValue.toboolean() ? 1 : 0);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("bad type for " + luaValue);
                case 3:
                    switch (this.f1527b) {
                        case 0:
                            this.d.write(3);
                            a(luaValue.todouble());
                            break;
                        case 1:
                            if (!ALLOW_INTEGER_CASTING && !luaValue.isint()) {
                                throw new IllegalArgumentException("not an integer: " + luaValue);
                            }
                            this.d.write(3);
                            b(luaValue.toint());
                            break;
                            break;
                        case 2:
                        case 3:
                        default:
                            throw new IllegalArgumentException("number format not supported: " + this.f1527b);
                        case 4:
                            if (luaValue.isint()) {
                                this.d.write(-2);
                                b(luaValue.toint());
                                break;
                            } else {
                                this.d.write(3);
                                a(luaValue.todouble());
                                break;
                            }
                    }
                case 4:
                    this.d.write(4);
                    a((LuaString) luaValue);
                    break;
            }
        }
        int length = fPrototype.p.length;
        b(length);
        for (int i = 0; i < length; i++) {
            e(fPrototype.p[i]);
        }
    }

    private void c(FPrototype fPrototype) {
        int length = fPrototype.upvalues.length;
        b(length);
        for (int i = 0; i < length; i++) {
            this.d.writeByte(fPrototype.upvalues[i].instack ? 1 : 0);
            this.d.writeByte(fPrototype.upvalues[i].idx);
        }
    }

    private void d(FPrototype fPrototype) {
        if (this.e) {
            b(0);
        } else {
            a(fPrototype.source);
        }
        int length = this.e ? 0 : fPrototype.lineinfo.length;
        b(length);
        for (int i = 0; i < length; i++) {
            b(fPrototype.lineinfo[i]);
        }
        int length2 = this.e ? 0 : fPrototype.locvars.length;
        b(length2);
        for (int i2 = 0; i2 < length2; i2++) {
            LocVars locVars = fPrototype.locvars[i2];
            a(locVars.varname);
            b(locVars.startpc);
            b(locVars.endpc);
        }
        int length3 = this.e ? 0 : fPrototype.upvalues.length;
        b(length3);
        for (int i3 = 0; i3 < length3; i3++) {
            a(fPrototype.upvalues[i3].name);
        }
    }

    private void e(FPrototype fPrototype) {
        b(fPrototype.linedefined);
        b(fPrototype.lastlinedefined);
        a((int) fPrototype.numparams);
        a(fPrototype.is_vararg ? 1 : 0);
        a((int) fPrototype.maxstacksize);
        a(fPrototype);
        b(fPrototype);
        c(fPrototype);
        d(fPrototype);
    }

    private void a() {
        this.d.write(LoadState.LUA_SIGNATURE);
        this.d.write(82);
        this.d.write(0);
        this.d.write(this.f1526a ? 1 : 0);
        this.d.write(4);
        this.d.write(4);
        this.d.write(4);
        this.d.write(this.c);
        this.d.write(this.f1527b);
        this.d.write(LoadState.LUAC_TAIL);
    }

    public static int dump(FPrototype fPrototype, OutputStream outputStream, boolean z) {
        DumpState dumpState = new DumpState(outputStream, z);
        dumpState.a();
        dumpState.e(fPrototype);
        return 0;
    }

    public static int dump(FPrototype fPrototype, OutputStream outputStream, boolean z, int i, boolean z2) {
        switch (i) {
            case 0:
            case 1:
            case 4:
                DumpState dumpState = new DumpState(outputStream, z);
                dumpState.f1526a = z2;
                dumpState.f1527b = i;
                dumpState.c = i == 1 ? 4 : 8;
                dumpState.a();
                dumpState.e(fPrototype);
                return 0;
            case 2:
            case 3:
            default:
                throw new IllegalArgumentException("number format not supported: " + i);
        }
    }
}
