package com.prineside.luaj.lib;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.LuaNumber;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib.class */
public class MathLib extends TwoArgFunction {
    public static MathLib MATHLIB = null;

    public MathLib() {
        MATHLIB = this;
    }

    @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        LuaValue luaTable = new LuaTable(0, 30);
        luaTable.set("abs", new abs());
        luaTable.set("ceil", new ceil());
        luaTable.set("cos", new cos());
        luaTable.set("deg", new deg());
        luaTable.set("exp", new exp(this));
        luaTable.set("floor", new floor());
        luaTable.set("fmod", new fmod());
        luaTable.set("frexp", new frexp());
        luaTable.set("huge", LuaNumber.POSINF);
        luaTable.set("ldexp", new ldexp());
        luaTable.set("max", new max());
        luaTable.set("min", new min());
        luaTable.set("modf", new modf());
        luaTable.set("pi", 3.141592653589793d);
        luaTable.set("pow", new pow());
        random randomVar = new random();
        luaTable.set("random", randomVar);
        luaTable.set("randomseed", new randomseed(randomVar));
        luaTable.set("rad", new rad());
        luaTable.set("sin", new sin());
        luaTable.set("sqrt", new sqrt());
        luaTable.set("tan", new tan());
        luaValue2.set(FlexmarkHtmlConverter.MATH_NODE, luaTable);
        if (!luaValue2.get("package").isnil()) {
            luaValue2.get("package").get("loaded").set(FlexmarkHtmlConverter.MATH_NODE, luaTable);
        }
        return luaTable;
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$UnaryOp.class */
    protected static abstract class UnaryOp extends OneArgFunction {
        protected abstract double a(double d);

        @Override // com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public LuaValue call(LuaValue luaValue) {
            return valueOf(a(luaValue.checkdouble()));
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$BinaryOp.class */
    protected static abstract class BinaryOp extends TwoArgFunction {
        protected abstract double a(double d, double d2);

        @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            return valueOf(a(luaValue.checkdouble(), luaValue2.checkdouble()));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$abs.class */
    public static final class abs extends UnaryOp {
        @Override // com.prineside.luaj.lib.MathLib.UnaryOp, com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue) {
            return super.call(luaValue);
        }

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp
        protected final double a(double d) {
            return StrictMath.abs(d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$ceil.class */
    public static final class ceil extends UnaryOp {
        @Override // com.prineside.luaj.lib.MathLib.UnaryOp, com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue) {
            return super.call(luaValue);
        }

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp
        protected final double a(double d) {
            return StrictMath.ceil(d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$cos.class */
    public static final class cos extends UnaryOp {
        @Override // com.prineside.luaj.lib.MathLib.UnaryOp, com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue) {
            return super.call(luaValue);
        }

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp
        protected final double a(double d) {
            return MathUtils.cos((float) d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$deg.class */
    public static final class deg extends UnaryOp {
        @Override // com.prineside.luaj.lib.MathLib.UnaryOp, com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue) {
            return super.call(luaValue);
        }

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp
        protected final double a(double d) {
            return StrictMath.toDegrees(d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$floor.class */
    public static final class floor extends UnaryOp {
        @Override // com.prineside.luaj.lib.MathLib.UnaryOp, com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue) {
            return super.call(luaValue);
        }

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp
        protected final double a(double d) {
            return StrictMath.floor(d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$rad.class */
    public static final class rad extends UnaryOp {
        @Override // com.prineside.luaj.lib.MathLib.UnaryOp, com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue) {
            return super.call(luaValue);
        }

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp
        protected final double a(double d) {
            return StrictMath.toRadians(d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$sin.class */
    public static final class sin extends UnaryOp {
        @Override // com.prineside.luaj.lib.MathLib.UnaryOp, com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue) {
            return super.call(luaValue);
        }

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp
        protected final double a(double d) {
            return MathUtils.sin((float) d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$sqrt.class */
    public static final class sqrt extends UnaryOp {
        @Override // com.prineside.luaj.lib.MathLib.UnaryOp, com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue) {
            return super.call(luaValue);
        }

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp
        protected final double a(double d) {
            return StrictMath.sqrt(d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$tan.class */
    public static final class tan extends UnaryOp {
        @Override // com.prineside.luaj.lib.MathLib.UnaryOp, com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue) {
            return super.call(luaValue);
        }

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp
        protected final double a(double d) {
            return StrictMath.tan(d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$exp.class */
    public static final class exp extends UnaryOp implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private MathLib f1583a;

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp, com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue) {
            return super.call(luaValue);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1583a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1583a = (MathLib) kryo.readClassAndObject(input);
        }

        private exp() {
        }

        exp(MathLib mathLib) {
            this.f1583a = mathLib;
        }

        @Override // com.prineside.luaj.lib.MathLib.UnaryOp
        protected final double a(double d) {
            return this.f1583a.dpow_lib(2.718281828459045d, d);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$fmod.class */
    public static final class fmod extends TwoArgFunction {
        @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            if (luaValue.islong() && luaValue2.islong()) {
                return valueOf(luaValue.tolong() % luaValue2.tolong());
            }
            return valueOf(luaValue.checkdouble() % luaValue2.checkdouble());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$ldexp.class */
    public static final class ldexp extends BinaryOp {
        @Override // com.prineside.luaj.lib.MathLib.BinaryOp, com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            return super.call(luaValue, luaValue2);
        }

        @Override // com.prineside.luaj.lib.MathLib.BinaryOp
        protected final double a(double d, double d2) {
            return d * Double.longBitsToDouble((((long) d2) + 1023) << 52);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$pow.class */
    public static final class pow extends BinaryOp {
        @Override // com.prineside.luaj.lib.MathLib.BinaryOp, com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            return super.call(luaValue, luaValue2);
        }

        @Override // com.prineside.luaj.lib.MathLib.BinaryOp
        protected final double a(double d, double d2) {
            return MathLib.a(d, d2);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$frexp.class */
    public static class frexp extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            double checkdouble = varargs.checkdouble(1);
            if (checkdouble == 0.0d) {
                LuaNumber luaNumber = ZERO;
                return varargsOf(luaNumber, luaNumber);
            }
            return varargsOf(valueOf(((r0 & 4503599627370495L) + 4503599627370496L) * (Double.doubleToLongBits(checkdouble) >= 0 ? 1.1102230246251565E-16d : -1.1102230246251565E-16d)), valueOf((((int) (r0 >> 52)) & 2047) - 1022));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$max.class */
    public static class max extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            LuaValue checkvalue = varargs.checkvalue(1);
            int narg = varargs.narg();
            for (int i = 2; i <= narg; i++) {
                LuaValue checkvalue2 = varargs.checkvalue(i);
                if (checkvalue.lt_b(checkvalue2)) {
                    checkvalue = checkvalue2;
                }
            }
            return checkvalue;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$min.class */
    public static class min extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            LuaValue checkvalue = varargs.checkvalue(1);
            int narg = varargs.narg();
            for (int i = 2; i <= narg; i++) {
                LuaValue checkvalue2 = varargs.checkvalue(i);
                if (checkvalue2.lt_b(checkvalue)) {
                    checkvalue = checkvalue2;
                }
            }
            return checkvalue;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$modf.class */
    public static class modf extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            LuaValue arg1 = varargs.arg1();
            if (arg1.islong()) {
                return varargsOf(arg1, valueOf(0.0d));
            }
            double checkdouble = arg1.checkdouble();
            double floor = checkdouble > 0.0d ? Math.floor(checkdouble) : Math.ceil(checkdouble);
            return varargsOf(valueOf(floor), valueOf(checkdouble == floor ? 0.0d : checkdouble - floor));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$random.class */
    public static class random extends LibFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        @NAGS
        RandomXS128 f1584a = new RandomXS128();

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f1584a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f1584a = (RandomXS128) kryo.readObject(input, RandomXS128.class);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public LuaValue call() {
            return valueOf(this.f1584a.nextDouble());
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public LuaValue call(LuaValue luaValue) {
            int checkint = luaValue.checkint();
            if (checkint <= 0) {
                argerror(1, "interval is empty");
            }
            return valueOf(1 + this.f1584a.nextInt(checkint));
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            int checkint = luaValue.checkint();
            int checkint2 = luaValue2.checkint();
            if (checkint2 < checkint) {
                argerror(2, "interval is empty");
            }
            return valueOf(checkint + this.f1584a.nextInt((checkint2 + 1) - checkint));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/MathLib$randomseed.class */
    public static class randomseed extends OneArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private random f1585a;

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f1585a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f1585a = (random) kryo.readObject(input, random.class);
        }

        randomseed() {
        }

        randomseed(random randomVar) {
            this.f1585a = randomVar;
        }

        @Override // com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public LuaValue call(LuaValue luaValue) {
            long checklong = luaValue.checklong();
            this.f1585a.f1584a = new RandomXS128(checklong);
            return NONE;
        }
    }

    public static LuaValue dpow(double d, double d2) {
        double a2;
        if (MATHLIB != null) {
            a2 = MATHLIB.dpow_lib(d, d2);
        } else {
            a2 = a(d, d2);
        }
        return LuaNumber.valueOf(a2);
    }

    public static double dpow_d(double d, double d2) {
        if (MATHLIB != null) {
            return MATHLIB.dpow_lib(d, d2);
        }
        return a(d, d2);
    }

    public double dpow_lib(double d, double d2) {
        return a(d, d2);
    }

    protected static double a(double d, double d2) {
        if (d2 < 0.0d) {
            return 1.0d / a(d, -d2);
        }
        double d3 = 1.0d;
        int i = (int) d2;
        double d4 = d;
        while (true) {
            double d5 = d4;
            if (i <= 0) {
                break;
            }
            if ((i & 1) != 0) {
                d3 *= d5;
            }
            i >>= 1;
            d4 = d5 * d5;
        }
        double d6 = d2 - i;
        if (d6 > 0.0d) {
            int i2 = (int) (d6 * 65536.0d);
            while (true) {
                int i3 = i2;
                if ((i3 & 65535) == 0) {
                    break;
                }
                d = Math.sqrt(d);
                if ((i3 & 32768) != 0) {
                    d3 *= d;
                }
                i2 = i3 << 1;
            }
        }
        return d3;
    }
}
