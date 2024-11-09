package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.lib.MathLib;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/LuaNumber.class */
public final class LuaNumber extends LuaValue implements KryoSerializable {
    public static final LuaNumber NAN = new LuaNumber(Double.NaN);
    public static final LuaNumber POSINF = new LuaNumber(Double.POSITIVE_INFINITY);
    public static final LuaNumber NEGINF = new LuaNumber(Double.NEGATIVE_INFINITY);
    public static final String JSTR_NAN = "nan";
    public static final String JSTR_POSINF = "inf";
    public static final String JSTR_NEGINF = "-inf";

    /* renamed from: a, reason: collision with root package name */
    double f1483a;
    public static LuaValue s_metatable;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        if (((byte) this.f1483a) == this.f1483a) {
            output.writeByte(0);
            output.writeByte((byte) this.f1483a);
            return;
        }
        if (((short) this.f1483a) == this.f1483a) {
            output.writeByte(1);
            output.writeShort((short) this.f1483a);
        } else if (((int) this.f1483a) == this.f1483a) {
            output.writeByte(2);
            output.writeInt((int) this.f1483a);
        } else if (((float) this.f1483a) == this.f1483a) {
            output.writeByte(3);
            output.writeFloat((float) this.f1483a);
        } else {
            output.writeByte(4);
            output.writeDouble(this.f1483a);
        }
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        switch (input.readByte()) {
            case 0:
                this.f1483a = input.readByte();
                return;
            case 1:
                this.f1483a = input.readShort();
                return;
            case 2:
                this.f1483a = input.readInt();
                return;
            case 3:
                this.f1483a = input.readFloat();
                return;
            default:
                this.f1483a = input.readDouble();
                return;
        }
    }

    private LuaNumber() {
    }

    private LuaNumber(double d) {
        this.f1483a = d;
    }

    public static int hashCode(int i) {
        return i;
    }

    public static LuaNumber valueOf(double d) {
        return new LuaNumber(d);
    }

    public static LuaNumber valueOf(int i) {
        return valueOf(i);
    }

    public final void setDirectly(double d) {
        this.f1483a = d;
    }

    public final int hashCode() {
        if (isint()) {
            return (int) this.f1483a;
        }
        return super.hashCode();
    }

    public final int originalHashCode() {
        return super.hashCode();
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean islong() {
        return this.f1483a == ((double) ((long) this.f1483a));
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isinttype() {
        return ((double) ((int) this.f1483a)) == this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isint() {
        return ((double) ((int) this.f1483a)) == this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final byte tobyte() {
        return (byte) this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final char tochar() {
        return (char) this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final double todouble() {
        return this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final float tofloat() {
        return (float) this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final int toint() {
        return (int) this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final long tolong() {
        return (long) this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final short toshort() {
        return (short) this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final double optdouble(double d) {
        return this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final int optint(int i) {
        return (int) this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final long optlong(long j) {
        return (long) this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue neg() {
        return valueOf(-this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean equals(Object obj) {
        return (obj instanceof LuaNumber) && ((LuaNumber) obj).f1483a == this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue eq(LuaValue luaValue) {
        return luaValue.raweq(this.f1483a) ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean eq_b(LuaValue luaValue) {
        return luaValue.raweq(this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean raweq(LuaValue luaValue) {
        return luaValue.raweq(this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean raweq(double d) {
        return this.f1483a == d;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean raweq(int i) {
        return this.f1483a == ((double) i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue add(LuaValue luaValue) {
        return luaValue.add(this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue add(double d) {
        return valueOf(d + this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue sub(LuaValue luaValue) {
        return luaValue.subFrom(this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue sub(double d) {
        return valueOf(this.f1483a - d);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue sub(int i) {
        return valueOf(this.f1483a - i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue subFrom(double d) {
        return valueOf(d - this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mul(LuaValue luaValue) {
        return luaValue.mul(this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mul(double d) {
        return valueOf(d * this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mul(int i) {
        return valueOf(i * this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue pow(LuaValue luaValue) {
        return luaValue.powWith(this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue pow(double d) {
        return MathLib.dpow(this.f1483a, d);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue pow(int i) {
        return MathLib.dpow(this.f1483a, i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue powWith(double d) {
        return MathLib.dpow(d, this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue powWith(int i) {
        return MathLib.dpow(i, this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue div(LuaValue luaValue) {
        return luaValue.divInto(this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue div(double d) {
        return ddiv(this.f1483a, d);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue div(int i) {
        return ddiv(this.f1483a, i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue divInto(double d) {
        return ddiv(d, this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mod(LuaValue luaValue) {
        return luaValue.modFrom(this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mod(double d) {
        return dmod(this.f1483a, d);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mod(int i) {
        return dmod(this.f1483a, i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue modFrom(double d) {
        return dmod(d, this.f1483a);
    }

    public static LuaValue ddiv(double d, double d2) {
        return d2 != 0.0d ? valueOf(d / d2) : d > 0.0d ? POSINF : d == 0.0d ? NAN : NEGINF;
    }

    public static double ddiv_d(double d, double d2) {
        if (d2 != 0.0d) {
            return d / d2;
        }
        if (d > 0.0d) {
            return Double.POSITIVE_INFINITY;
        }
        return d == 0.0d ? Double.NaN : Double.NEGATIVE_INFINITY;
    }

    public static LuaValue dmod(double d, double d2) {
        if (d2 == 0.0d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY) {
            return NAN;
        }
        if (d2 == Double.POSITIVE_INFINITY) {
            return d < 0.0d ? POSINF : valueOf(d);
        }
        if (d2 == Double.NEGATIVE_INFINITY) {
            return d > 0.0d ? NEGINF : valueOf(d);
        }
        return valueOf(d - (d2 * Math.floor(d / d2)));
    }

    public static double dmod_d(double d, double d2) {
        if (d2 == 0.0d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY) {
            return Double.NaN;
        }
        if (d2 == Double.POSITIVE_INFINITY) {
            if (d < 0.0d) {
                return Double.POSITIVE_INFINITY;
            }
            return d;
        }
        if (d2 != Double.NEGATIVE_INFINITY) {
            return d - (d2 * Math.floor(d / d2));
        }
        if (d > 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        return d;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue lt(LuaValue luaValue) {
        return luaValue instanceof LuaNumber ? luaValue.gt_b(this.f1483a) ? TRUE : FALSE : super.lt(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue lt(double d) {
        return this.f1483a < d ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue lt(int i) {
        return this.f1483a < ((double) i) ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lt_b(LuaValue luaValue) {
        return luaValue instanceof LuaNumber ? luaValue.gt_b(this.f1483a) : super.lt_b(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lt_b(int i) {
        return this.f1483a < ((double) i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lt_b(double d) {
        return this.f1483a < d;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue lteq(LuaValue luaValue) {
        return luaValue instanceof LuaNumber ? luaValue.gteq_b(this.f1483a) ? TRUE : FALSE : super.lteq(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue lteq(double d) {
        return this.f1483a <= d ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue lteq(int i) {
        return this.f1483a <= ((double) i) ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lteq_b(LuaValue luaValue) {
        return luaValue instanceof LuaNumber ? luaValue.gteq_b(this.f1483a) : super.lteq_b(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lteq_b(int i) {
        return this.f1483a <= ((double) i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lteq_b(double d) {
        return this.f1483a <= d;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue gt(LuaValue luaValue) {
        return luaValue instanceof LuaNumber ? luaValue.lt_b(this.f1483a) ? TRUE : FALSE : super.gt(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue gt(double d) {
        return this.f1483a > d ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue gt(int i) {
        return this.f1483a > ((double) i) ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gt_b(LuaValue luaValue) {
        return luaValue instanceof LuaNumber ? luaValue.lt_b(this.f1483a) : super.gt_b(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gt_b(int i) {
        return this.f1483a > ((double) i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gt_b(double d) {
        return this.f1483a > d;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue gteq(LuaValue luaValue) {
        return luaValue instanceof LuaNumber ? luaValue.lteq_b(this.f1483a) ? TRUE : FALSE : super.gteq(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue gteq(double d) {
        return this.f1483a >= d ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue gteq(int i) {
        return this.f1483a >= ((double) i) ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gteq_b(LuaValue luaValue) {
        return luaValue instanceof LuaNumber ? luaValue.lteq_b(this.f1483a) : super.gteq_b(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gteq_b(int i) {
        return this.f1483a >= ((double) i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gteq_b(double d) {
        return this.f1483a >= d;
    }

    @Override // com.prineside.luaj.LuaValue
    public final int strcmp(LuaString luaString) {
        b("attempt to compare number with string");
        return 0;
    }

    @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public final String tojstring() {
        long j = (long) this.f1483a;
        if (j == this.f1483a) {
            return Long.toString(j);
        }
        if (Double.isNaN(this.f1483a)) {
            return JSTR_NAN;
        }
        if (Double.isInfinite(this.f1483a)) {
            return this.f1483a < 0.0d ? JSTR_NEGINF : JSTR_POSINF;
        }
        return Float.toString((float) this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaString strvalue() {
        return LuaString.valueOf(tojstring());
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaString optstring(LuaString luaString) {
        return LuaString.valueOf(tojstring());
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue tostring() {
        return LuaString.valueOf(tojstring());
    }

    @Override // com.prineside.luaj.LuaValue
    public final String optjstring(String str) {
        return tojstring();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaNumber optnumber(LuaNumber luaNumber) {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isnumber() {
        return true;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isstring() {
        return true;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue tonumber() {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final int checkint() {
        return (int) this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final long checklong() {
        return (long) this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaNumber checknumber() {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final double checkdouble() {
        return this.f1483a;
    }

    @Override // com.prineside.luaj.LuaValue
    public final String checkjstring() {
        return tojstring();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaString checkstring() {
        return LuaString.valueOf(tojstring());
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isvalidkey() {
        return !Double.isNaN(this.f1483a);
    }

    @Override // com.prineside.luaj.LuaValue
    public final int type() {
        return 3;
    }

    @Override // com.prineside.luaj.LuaValue
    public final String typename() {
        return "number";
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaNumber checknumber(String str) {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue getmetatable() {
        return s_metatable;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue concat(LuaValue luaValue) {
        return luaValue.concatTo(this);
    }

    @Override // com.prineside.luaj.LuaValue
    public final Buffer concat(Buffer buffer) {
        return buffer.concatTo(this);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue concatTo(LuaNumber luaNumber) {
        return strvalue().concatTo(luaNumber.strvalue());
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue concatTo(LuaString luaString) {
        return strvalue().concatTo(luaString);
    }
}
