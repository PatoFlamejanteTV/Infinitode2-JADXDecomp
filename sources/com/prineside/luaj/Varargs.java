package com.prineside.luaj;

import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/luaj/Varargs.class */
public abstract class Varargs {
    public abstract LuaValue arg(int i);

    public abstract int narg();

    public abstract LuaValue arg1();

    public abstract Varargs subargs(int i);

    public Varargs eval() {
        return this;
    }

    public boolean isTailcall() {
        return false;
    }

    public int type(int i) {
        return arg(i).type();
    }

    public boolean isnil(int i) {
        return arg(i).isnil();
    }

    public boolean isfunction(int i) {
        return arg(i).isfunction();
    }

    public boolean isnumber(int i) {
        return arg(i).isnumber();
    }

    public boolean isstring(int i) {
        return arg(i).isstring();
    }

    public boolean istable(int i) {
        return arg(i).istable();
    }

    public boolean isuserdata(int i) {
        return arg(i).isuserdata();
    }

    public boolean isvalue(int i) {
        return i > 0 && i <= narg();
    }

    public boolean optboolean(int i, boolean z) {
        return arg(i).optboolean(z);
    }

    public LuaClosure optclosure(int i, LuaClosure luaClosure) {
        return arg(i).optclosure(luaClosure);
    }

    public double optdouble(int i, double d) {
        return arg(i).optdouble(d);
    }

    public LuaFunction optfunction(int i, LuaFunction luaFunction) {
        return arg(i).optfunction(luaFunction);
    }

    public int optint(int i, int i2) {
        return arg(i).optint(i2);
    }

    public long optlong(int i, long j) {
        return arg(i).optlong(j);
    }

    public LuaNumber optnumber(int i, LuaNumber luaNumber) {
        return arg(i).optnumber(luaNumber);
    }

    public String optjstring(int i, String str) {
        return arg(i).optjstring(str);
    }

    public LuaString optstring(int i, LuaString luaString) {
        return arg(i).optstring(luaString);
    }

    public LuaTable opttable(int i, LuaTable luaTable) {
        return arg(i).opttable(luaTable);
    }

    public Object optuserdata(int i, Object obj) {
        return arg(i).optuserdata(obj);
    }

    public Object optuserdata(int i, Class cls, Object obj) {
        return arg(i).optuserdata(cls, obj);
    }

    public LuaValue optvalue(int i, LuaValue luaValue) {
        return (i <= 0 || i > narg()) ? luaValue : arg(i);
    }

    public boolean checkboolean(int i) {
        return arg(i).checkboolean();
    }

    public LuaClosure checkclosure(int i) {
        return arg(i).checkclosure();
    }

    public double checkdouble(int i) {
        return arg(i).checkdouble();
    }

    public LuaFunction checkfunction(int i) {
        return arg(i).checkfunction();
    }

    public int checkint(int i) {
        return arg(i).checkint();
    }

    public long checklong(int i) {
        return arg(i).checklong();
    }

    public LuaNumber checknumber(int i) {
        return arg(i).checknumber();
    }

    public String checkjstring(int i) {
        return arg(i).checkjstring();
    }

    public LuaString checkstring(int i) {
        return arg(i).checkstring();
    }

    public LuaTable checktable(int i) {
        return arg(i).checktable();
    }

    public Object checkuserdata(int i) {
        return arg(i).checkuserdata();
    }

    public Object checkuserdata(int i, Class cls) {
        return arg(i).checkuserdata(cls);
    }

    public LuaValue checkvalue(int i) {
        return i <= narg() ? arg(i) : LuaValue.argerror(i, "value expected");
    }

    public LuaValue checknotnil(int i) {
        return arg(i).checknotnil();
    }

    public void argcheck(boolean z, int i, String str) {
        if (z) {
            return;
        }
        LuaValue.argerror(i, str);
    }

    public boolean isnoneornil(int i) {
        return i > narg() || arg(i).isnil();
    }

    public boolean toboolean(int i) {
        return arg(i).toboolean();
    }

    public byte tobyte(int i) {
        return arg(i).tobyte();
    }

    public char tochar(int i) {
        return arg(i).tochar();
    }

    public double todouble(int i) {
        return arg(i).todouble();
    }

    public float tofloat(int i) {
        return arg(i).tofloat();
    }

    public int toint(int i) {
        return arg(i).toint();
    }

    public long tolong(int i) {
        return arg(i).tolong();
    }

    public String tojstring(int i) {
        return arg(i).tojstring();
    }

    public short toshort(int i) {
        return arg(i).toshort();
    }

    public Object touserdata(int i) {
        return arg(i).touserdata();
    }

    public <T> T touserdata(int i, Class<T> cls) {
        return (T) arg(i).touserdata(cls);
    }

    public String tojstring() {
        Buffer buffer = new Buffer();
        buffer.append("(");
        int narg = narg();
        for (int i = 1; i <= narg; i++) {
            if (i > 1) {
                buffer.append(",");
            }
            buffer.append(arg(i).getClass().getSimpleName()).append(SequenceUtils.SPACE).append(arg(i).tojstring());
        }
        buffer.append(")");
        return buffer.tojstring();
    }

    public String toString() {
        return tojstring();
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/Varargs$SubVarargs.class */
    static class SubVarargs extends Varargs {

        /* renamed from: a, reason: collision with root package name */
        private final Varargs f1516a;

        /* renamed from: b, reason: collision with root package name */
        private final int f1517b;
        private final int c;

        public SubVarargs(Varargs varargs, int i, int i2) {
            this.f1516a = varargs;
            this.f1517b = i;
            this.c = i2;
        }

        @Override // com.prineside.luaj.Varargs
        public LuaValue arg(int i) {
            int i2 = i + (this.f1517b - 1);
            return (i2 < this.f1517b || i2 > this.c) ? LuaValue.NIL : this.f1516a.arg(i2);
        }

        @Override // com.prineside.luaj.Varargs
        public LuaValue arg1() {
            return this.f1516a.arg(this.f1517b);
        }

        @Override // com.prineside.luaj.Varargs
        public int narg() {
            return (this.c + 1) - this.f1517b;
        }

        @Override // com.prineside.luaj.Varargs
        public Varargs subargs(int i) {
            if (i == 1) {
                return this;
            }
            int i2 = (this.f1517b + i) - 1;
            if (i > 0) {
                if (i2 >= this.c) {
                    return LuaValue.NONE;
                }
                if (i2 == this.c) {
                    return this.f1516a.arg(this.c);
                }
                if (i2 == this.c - 1) {
                    return new PairVarargs(this.f1516a.arg(this.c - 1), this.f1516a.arg(this.c));
                }
                return new SubVarargs(this.f1516a, i2, this.c);
            }
            return new SubVarargs(this.f1516a, i2, this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/Varargs$PairVarargs.class */
    public static final class PairVarargs extends Varargs {

        /* renamed from: a, reason: collision with root package name */
        private final LuaValue f1514a;

        /* renamed from: b, reason: collision with root package name */
        private final Varargs f1515b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public PairVarargs(LuaValue luaValue, Varargs varargs) {
            this.f1514a = luaValue;
            this.f1515b = varargs;
        }

        @Override // com.prineside.luaj.Varargs
        public final LuaValue arg(int i) {
            return i == 1 ? this.f1514a : this.f1515b.arg(i - 1);
        }

        @Override // com.prineside.luaj.Varargs
        public final int narg() {
            return 1 + this.f1515b.narg();
        }

        @Override // com.prineside.luaj.Varargs
        public final LuaValue arg1() {
            return this.f1514a;
        }

        @Override // com.prineside.luaj.Varargs
        public final Varargs subargs(int i) {
            if (i == 1) {
                return this;
            }
            if (i == 2) {
                return this.f1515b;
            }
            if (i > 2) {
                return this.f1515b.subargs(i - 1);
            }
            return LuaValue.argerror(1, "start must be > 0");
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/Varargs$ArrayVarargs.class */
    public static final class ArrayVarargs extends Varargs {

        /* renamed from: a, reason: collision with root package name */
        private final LuaValue[] f1512a;

        /* renamed from: b, reason: collision with root package name */
        private final Varargs f1513b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ArrayVarargs(LuaValue[] luaValueArr, Varargs varargs) {
            this.f1512a = luaValueArr;
            this.f1513b = varargs;
        }

        @Override // com.prineside.luaj.Varargs
        public final LuaValue arg(int i) {
            return i <= 0 ? LuaValue.NIL : i <= this.f1512a.length ? this.f1512a[i - 1] : this.f1513b.arg(i - this.f1512a.length);
        }

        @Override // com.prineside.luaj.Varargs
        public final int narg() {
            return this.f1512a.length + this.f1513b.narg();
        }

        @Override // com.prineside.luaj.Varargs
        public final LuaValue arg1() {
            return this.f1512a.length > 0 ? this.f1512a[0] : this.f1513b.arg1();
        }

        @Override // com.prineside.luaj.Varargs
        public final Varargs subargs(int i) {
            if (i <= 0) {
                LuaValue.argerror(1, "start must be > 0");
            }
            if (i == 1) {
                return this;
            }
            if (i > this.f1512a.length) {
                return this.f1513b.subargs(i - this.f1512a.length);
            }
            return LuaValue.varargsOf(this.f1512a, i - 1, this.f1512a.length - (i - 1), this.f1513b);
        }

        @Override // com.prineside.luaj.Varargs
        final void a(LuaValue[] luaValueArr, int i, int i2) {
            int min = Math.min(this.f1512a.length, i2);
            System.arraycopy(this.f1512a, 0, luaValueArr, i, min);
            this.f1513b.a(luaValueArr, i + min, i2 - min);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/Varargs$ArrayPartVarargs.class */
    public static final class ArrayPartVarargs extends Varargs {

        /* renamed from: a, reason: collision with root package name */
        private final int f1510a;

        /* renamed from: b, reason: collision with root package name */
        private final LuaValue[] f1511b;
        private final int c;
        private final Varargs d;

        public ArrayPartVarargs(LuaValue[] luaValueArr, int i, int i2, Varargs varargs) {
            this.f1511b = luaValueArr;
            this.f1510a = i;
            this.c = i2;
            this.d = varargs;
        }

        @Override // com.prineside.luaj.Varargs
        public final LuaValue arg(int i) {
            return i <= 0 ? LuaValue.NIL : i <= this.c ? this.f1511b[(this.f1510a + i) - 1] : this.d.arg(i - this.c);
        }

        @Override // com.prineside.luaj.Varargs
        public final int narg() {
            return this.c + this.d.narg();
        }

        @Override // com.prineside.luaj.Varargs
        public final LuaValue arg1() {
            return this.c > 0 ? this.f1511b[this.f1510a] : this.d.arg1();
        }

        @Override // com.prineside.luaj.Varargs
        public final Varargs subargs(int i) {
            if (i <= 0) {
                LuaValue.argerror(1, "start must be > 0");
            }
            if (i == 1) {
                return this;
            }
            if (i > this.c) {
                return this.d.subargs(i - this.c);
            }
            return LuaValue.varargsOf(this.f1511b, (this.f1510a + i) - 1, this.c - (i - 1), this.d);
        }

        @Override // com.prineside.luaj.Varargs
        final void a(LuaValue[] luaValueArr, int i, int i2) {
            int min = Math.min(this.c, i2);
            System.arraycopy(this.f1511b, this.f1510a, luaValueArr, i, min);
            this.d.a(luaValueArr, i + min, i2 - min);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(LuaValue[] luaValueArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            luaValueArr[i + i3] = arg(i3 + 1);
        }
    }

    public Varargs dealias() {
        int narg = narg();
        switch (narg) {
            case 0:
                return LuaValue.NONE;
            case 1:
                return arg1();
            case 2:
                return new PairVarargs(arg1(), arg(2));
            default:
                LuaValue[] luaValueArr = new LuaValue[narg];
                a(luaValueArr, 0, narg);
                return new ArrayVarargs(luaValueArr, LuaValue.NONE);
        }
    }
}
