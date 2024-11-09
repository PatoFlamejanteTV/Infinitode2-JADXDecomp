package com.prineside.luaj;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.lib.MathLib;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.lwjgl.opengl.CGL;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/luaj/LuaString.class */
public final class LuaString extends LuaValue {
    public static LuaValue s_metatable;

    @NAGS
    public final byte[] m_bytes;
    public final int m_offset;
    public final int m_length;

    /* renamed from: a, reason: collision with root package name */
    private final int f1484a;

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private String f1485b;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaString$RecentShortStrings.class */
    public static final class RecentShortStrings {

        /* renamed from: a, reason: collision with root package name */
        private static final LuaString[] f1486a = new LuaString[256];

        private RecentShortStrings() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaString$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<LuaString> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, LuaString luaString) {
            output.writeString(luaString.tojstring());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        public LuaString read(Kryo kryo, Input input, Class<? extends LuaString> cls) {
            return LuaString.valueOf(input.readString());
        }
    }

    public static LuaString valueOf(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[lengthAsUtf8(charArray)];
        encodeToUtf8(charArray, charArray.length, bArr, 0);
        return valueUsing(bArr, 0, bArr.length);
    }

    public static LuaString valueOf(byte[] bArr, int i, int i2) {
        if (i2 > 64) {
            return a(bArr, i, i2);
        }
        int hashCode = hashCode(bArr, i, i2);
        int i3 = hashCode & 255;
        synchronized (RecentShortStrings.f1486a) {
            LuaString luaString = RecentShortStrings.f1486a[i3];
            if (luaString != null && luaString.f1484a == hashCode && luaString.b(bArr, i, i2)) {
                return luaString;
            }
            LuaString a2 = a(bArr, i, i2);
            RecentShortStrings.f1486a[i3] = a2;
            return a2;
        }
    }

    private static LuaString a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new LuaString(bArr2, 0, i2);
    }

    public static LuaString valueUsing(byte[] bArr, int i, int i2) {
        LuaString luaString;
        if (bArr.length > 64) {
            return new LuaString(bArr, i, i2);
        }
        int hashCode = hashCode(bArr, i, i2);
        int i3 = hashCode & 255;
        LuaString luaString2 = RecentShortStrings.f1486a[i3];
        if (luaString2 == null || luaString2.f1484a != hashCode || !luaString2.b(bArr, i, i2)) {
            synchronized (RecentShortStrings.f1486a) {
                luaString = new LuaString(bArr, i, i2);
                RecentShortStrings.f1486a[i3] = luaString;
            }
            return luaString;
        }
        return luaString2;
    }

    public static LuaString valueOf(char[] cArr) {
        return valueOf(cArr, 0, cArr.length);
    }

    public static LuaString valueOf(char[] cArr, int i, int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) cArr[i3 + i];
        }
        return valueUsing(bArr, 0, i2);
    }

    public static LuaString valueOf(byte[] bArr) {
        return valueOf(bArr, 0, bArr.length);
    }

    public static LuaString valueUsing(byte[] bArr) {
        return valueUsing(bArr, 0, bArr.length);
    }

    private LuaString(byte[] bArr, int i, int i2) {
        this.m_bytes = bArr;
        this.m_offset = i;
        this.m_length = i2;
        this.f1484a = hashCode(bArr, i, i2);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isstring() {
        return true;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue getmetatable() {
        return s_metatable;
    }

    @Override // com.prineside.luaj.LuaValue
    public final int type() {
        return 4;
    }

    @Override // com.prineside.luaj.LuaValue
    public final String typename() {
        return "string";
    }

    @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public final String tojstring() {
        if (this.f1485b == null) {
            this.f1485b = decodeAsUtf8(this.m_bytes, this.m_offset, this.m_length);
        }
        return this.f1485b;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue neg() {
        double scannumber = scannumber();
        return Double.isNaN(scannumber) ? super.neg() : valueOf(-scannumber);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue add(LuaValue luaValue) {
        double scannumber = scannumber();
        return Double.isNaN(scannumber) ? b(ADD, luaValue) : luaValue.add(scannumber);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue add(double d) {
        return valueOf(d() + d);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue add(int i) {
        return valueOf(d() + i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue sub(LuaValue luaValue) {
        double scannumber = scannumber();
        return Double.isNaN(scannumber) ? b(SUB, luaValue) : luaValue.subFrom(scannumber);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue sub(double d) {
        return valueOf(d() - d);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue sub(int i) {
        return valueOf(d() - i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue subFrom(double d) {
        return valueOf(d - d());
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mul(LuaValue luaValue) {
        double scannumber = scannumber();
        return Double.isNaN(scannumber) ? b(MUL, luaValue) : luaValue.mul(scannumber);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mul(double d) {
        return valueOf(d() * d);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mul(int i) {
        return valueOf(d() * i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue pow(LuaValue luaValue) {
        double scannumber = scannumber();
        return Double.isNaN(scannumber) ? b(POW, luaValue) : luaValue.powWith(scannumber);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue pow(double d) {
        return MathLib.dpow(d(), d);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue pow(int i) {
        return MathLib.dpow(d(), i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue powWith(double d) {
        return MathLib.dpow(d, d());
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue powWith(int i) {
        return MathLib.dpow(i, d());
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue div(LuaValue luaValue) {
        double scannumber = scannumber();
        return Double.isNaN(scannumber) ? b(DIV, luaValue) : luaValue.divInto(scannumber);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue div(double d) {
        return LuaNumber.ddiv(d(), d);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue div(int i) {
        return LuaNumber.ddiv(d(), i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue divInto(double d) {
        return LuaNumber.ddiv(d, d());
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mod(LuaValue luaValue) {
        double scannumber = scannumber();
        return Double.isNaN(scannumber) ? b(MOD, luaValue) : luaValue.modFrom(scannumber);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mod(double d) {
        return LuaNumber.dmod(d(), d);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue mod(int i) {
        return LuaNumber.dmod(d(), i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue modFrom(double d) {
        return LuaNumber.dmod(d, d());
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue lt(LuaValue luaValue) {
        return luaValue.isstring() ? luaValue.strcmp(this) > 0 ? LuaValue.TRUE : FALSE : super.lt(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lt_b(LuaValue luaValue) {
        return luaValue.isstring() ? luaValue.strcmp(this) > 0 : super.lt_b(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lt_b(int i) {
        b("attempt to compare string with number");
        return false;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lt_b(double d) {
        b("attempt to compare string with number");
        return false;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue lteq(LuaValue luaValue) {
        return luaValue.isstring() ? luaValue.strcmp(this) >= 0 ? LuaValue.TRUE : FALSE : super.lteq(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lteq_b(LuaValue luaValue) {
        return luaValue.isstring() ? luaValue.strcmp(this) >= 0 : super.lteq_b(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lteq_b(int i) {
        b("attempt to compare string with number");
        return false;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean lteq_b(double d) {
        b("attempt to compare string with number");
        return false;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue gt(LuaValue luaValue) {
        return luaValue.isstring() ? luaValue.strcmp(this) < 0 ? LuaValue.TRUE : FALSE : super.gt(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gt_b(LuaValue luaValue) {
        return luaValue.isstring() ? luaValue.strcmp(this) < 0 : super.gt_b(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gt_b(int i) {
        b("attempt to compare string with number");
        return false;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gt_b(double d) {
        b("attempt to compare string with number");
        return false;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue gteq(LuaValue luaValue) {
        return luaValue.isstring() ? luaValue.strcmp(this) <= 0 ? LuaValue.TRUE : FALSE : super.gteq(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gteq_b(LuaValue luaValue) {
        return luaValue.isstring() ? luaValue.strcmp(this) <= 0 : super.gteq_b(luaValue);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gteq_b(int i) {
        b("attempt to compare string with number");
        return false;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean gteq_b(double d) {
        b("attempt to compare string with number");
        return false;
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
        return concatTo(luaNumber.strvalue());
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue concatTo(LuaString luaString) {
        byte[] bArr = new byte[luaString.m_length + this.m_length];
        System.arraycopy(luaString.m_bytes, luaString.m_offset, bArr, 0, luaString.m_length);
        System.arraycopy(this.m_bytes, this.m_offset, bArr, luaString.m_length, this.m_length);
        return valueUsing(bArr, 0, bArr.length);
    }

    @Override // com.prineside.luaj.LuaValue
    public final int strcmp(LuaValue luaValue) {
        return -luaValue.strcmp(this);
    }

    @Override // com.prineside.luaj.LuaValue
    public final int strcmp(LuaString luaString) {
        int i = 0;
        for (int i2 = 0; i < this.m_length && i2 < luaString.m_length; i2++) {
            if (this.m_bytes[this.m_offset + i] == luaString.m_bytes[luaString.m_offset + i2]) {
                i++;
            } else {
                return this.m_bytes[this.m_offset + i] - luaString.m_bytes[luaString.m_offset + i2];
            }
        }
        return this.m_length - luaString.m_length;
    }

    private double d() {
        double scannumber = scannumber();
        if (Double.isNaN(scannumber)) {
            b();
        }
        return scannumber;
    }

    @Override // com.prineside.luaj.LuaValue
    public final int checkint() {
        return (int) checkdouble();
    }

    @Override // com.prineside.luaj.LuaValue
    public final long checklong() {
        return (long) checkdouble();
    }

    @Override // com.prineside.luaj.LuaValue
    public final double checkdouble() {
        double scannumber = scannumber();
        if (Double.isNaN(scannumber)) {
            a("number");
        }
        return scannumber;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaNumber checknumber() {
        return valueOf(checkdouble());
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaNumber checknumber(String str) {
        double scannumber = scannumber();
        if (Double.isNaN(scannumber)) {
            error(str);
        }
        return valueOf(scannumber);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isnumber() {
        return !Double.isNaN(scannumber());
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean isint() {
        double scannumber = scannumber();
        return !Double.isNaN(scannumber) && ((double) ((int) scannumber)) == scannumber;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean islong() {
        double scannumber = scannumber();
        return !Double.isNaN(scannumber) && ((double) ((long) scannumber)) == scannumber;
    }

    @Override // com.prineside.luaj.LuaValue
    public final byte tobyte() {
        return (byte) toint();
    }

    @Override // com.prineside.luaj.LuaValue
    public final char tochar() {
        return (char) toint();
    }

    @Override // com.prineside.luaj.LuaValue
    public final double todouble() {
        double scannumber = scannumber();
        if (Double.isNaN(scannumber)) {
            return 0.0d;
        }
        return scannumber;
    }

    @Override // com.prineside.luaj.LuaValue
    public final float tofloat() {
        return (float) todouble();
    }

    @Override // com.prineside.luaj.LuaValue
    public final int toint() {
        return (int) tolong();
    }

    @Override // com.prineside.luaj.LuaValue
    public final long tolong() {
        return (long) todouble();
    }

    @Override // com.prineside.luaj.LuaValue
    public final short toshort() {
        return (short) toint();
    }

    @Override // com.prineside.luaj.LuaValue
    public final double optdouble(double d) {
        return checkdouble();
    }

    @Override // com.prineside.luaj.LuaValue
    public final int optint(int i) {
        return checkint();
    }

    @Override // com.prineside.luaj.LuaValue
    public final long optlong(long j) {
        return checklong();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaNumber optnumber(LuaNumber luaNumber) {
        return checknumber();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaString optstring(LuaString luaString) {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue tostring() {
        return this;
    }

    @Override // com.prineside.luaj.LuaValue
    public final String optjstring(String str) {
        return tojstring();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaString strvalue() {
        return this;
    }

    public final LuaString substring(int i, int i2) {
        int i3 = this.m_offset + i;
        int i4 = i2 - i;
        return i4 >= this.m_length / 2 ? valueUsing(this.m_bytes, i3, i4) : valueOf(this.m_bytes, i3, i4);
    }

    public final int hashCode() {
        return this.f1484a;
    }

    public static int hashCode(byte[] bArr, int i, int i2) {
        int i3 = i2;
        int i4 = (i2 >> 5) + 1;
        int i5 = i2;
        while (true) {
            int i6 = i5;
            if (i6 >= i4) {
                int i7 = i3;
                i3 = i7 ^ (((i7 << 5) + (i3 >> 2)) + (bArr[(i + i6) - 1] & 255));
                i5 = i6 - i4;
            } else {
                return i3;
            }
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean equals(Object obj) {
        if (obj instanceof LuaString) {
            return raweq((LuaString) obj);
        }
        return false;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue eq(LuaValue luaValue) {
        return luaValue.raweq(this) ? TRUE : FALSE;
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean eq_b(LuaValue luaValue) {
        return luaValue.raweq(this);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean raweq(LuaValue luaValue) {
        return luaValue.raweq(this);
    }

    @Override // com.prineside.luaj.LuaValue
    public final boolean raweq(LuaString luaString) {
        if (this == luaString) {
            return true;
        }
        if (luaString.m_length != this.m_length) {
            return false;
        }
        if (luaString.m_bytes == this.m_bytes && luaString.m_offset == this.m_offset) {
            return true;
        }
        if (luaString.hashCode() != hashCode()) {
            return false;
        }
        for (int i = 0; i < this.m_length; i++) {
            if (luaString.m_bytes[luaString.m_offset + i] != this.m_bytes[this.m_offset + i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(LuaString luaString, int i, LuaString luaString2, int i2, int i3) {
        return equals(luaString.m_bytes, luaString.m_offset + i, luaString2.m_bytes, luaString2.m_offset + i2, i3);
    }

    private boolean b(byte[] bArr, int i, int i2) {
        return this.m_length == i2 && equals(this.m_bytes, this.m_offset, bArr, i, i2);
    }

    public static boolean equals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4;
        int i5;
        if (bArr.length < i + i3 || bArr2.length < i2 + i3) {
            return false;
        }
        do {
            i3--;
            if (i3 < 0) {
                return true;
            }
            i4 = i;
            i++;
            i5 = i2;
            i2++;
        } while (bArr[i4] == bArr2[i5]);
        return false;
    }

    public final void write(DataOutputStream dataOutputStream, int i, int i2) {
        dataOutputStream.write(this.m_bytes, this.m_offset + i, i2);
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue len() {
        return LuaNumber.valueOf(this.m_length);
    }

    @Override // com.prineside.luaj.LuaValue
    public final int length() {
        return this.m_length;
    }

    @Override // com.prineside.luaj.LuaValue
    public final int rawlen() {
        return this.m_length;
    }

    public final int luaByte(int i) {
        return this.m_bytes[this.m_offset + i] & 255;
    }

    public final int charAt(int i) {
        if (i < 0 || i >= this.m_length) {
            throw new IndexOutOfBoundsException();
        }
        return luaByte(i);
    }

    @Override // com.prineside.luaj.LuaValue
    public final String checkjstring() {
        return tojstring();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaString checkstring() {
        return this;
    }

    public final InputStream toInputStream() {
        return new ByteArrayInputStream(this.m_bytes, this.m_offset, this.m_length);
    }

    public final void copyInto(int i, byte[] bArr, int i2, int i3) {
        System.arraycopy(this.m_bytes, this.m_offset + i, bArr, i2, i3);
    }

    public final int indexOfAny(LuaString luaString) {
        int i = this.m_offset + this.m_length;
        int i2 = luaString.m_offset + luaString.m_length;
        for (int i3 = this.m_offset; i3 < i; i3++) {
            for (int i4 = luaString.m_offset; i4 < i2; i4++) {
                if (this.m_bytes[i3] == luaString.m_bytes[i4]) {
                    return i3 - this.m_offset;
                }
            }
        }
        return -1;
    }

    public final int indexOf(byte b2, int i) {
        for (int i2 = i; i2 < this.m_length; i2++) {
            if (this.m_bytes[this.m_offset + i2] == b2) {
                return i2;
            }
        }
        return -1;
    }

    public final int indexOf(LuaString luaString, int i) {
        int length = luaString.length();
        int i2 = this.m_length - length;
        for (int i3 = i; i3 <= i2; i3++) {
            if (equals(this.m_bytes, this.m_offset + i3, luaString.m_bytes, luaString.m_offset, length)) {
                return i3;
            }
        }
        return -1;
    }

    public final int lastIndexOf(LuaString luaString) {
        int length = luaString.length();
        for (int i = this.m_length - length; i >= 0; i--) {
            if (equals(this.m_bytes, this.m_offset + i, luaString.m_bytes, luaString.m_offset, length)) {
                return i;
            }
        }
        return -1;
    }

    public static String decodeAsUtf8(byte[] bArr, int i, int i2) {
        int i3;
        int i4 = i;
        int i5 = i + i2;
        int i6 = 0;
        while (i4 < i5) {
            int i7 = i4;
            i4++;
            switch (224 & bArr[i7]) {
                case 192:
                    break;
                case CGL.kCGLCPDispatchTableSize /* 224 */:
                    i4++;
                    break;
            }
            i4++;
            i6++;
        }
        char[] cArr = new char[i6];
        int i8 = i;
        int i9 = 0;
        while (i8 < i5) {
            int i10 = i9;
            i9++;
            int i11 = i8;
            i8++;
            byte b2 = bArr[i11];
            if (b2 >= 0 || i8 >= i5) {
                i3 = b2;
            } else if (b2 < -32 || i8 + 1 >= i5) {
                i8++;
                i3 = ((b2 & 63) << 6) | (bArr[i8] & 63);
            } else {
                int i12 = i8 + 1;
                int i13 = ((b2 & 15) << 12) | ((bArr[i8] & 63) << 6);
                i8 = i12 + 1;
                i3 = i13 | (bArr[i12] & 63);
            }
            cArr[i10] = (char) i3;
        }
        return new String(cArr);
    }

    public static int lengthAsUtf8(char[] cArr) {
        int length = cArr.length;
        int i = length;
        int i2 = length;
        while (true) {
            i2--;
            if (i2 >= 0) {
                char c = cArr[i2];
                if (c >= 128) {
                    i += c >= 2048 ? 2 : 1;
                }
            } else {
                return i;
            }
        }
    }

    public static int encodeToUtf8(char[] cArr, int i, byte[] bArr, int i2) {
        int i3 = i2;
        for (int i4 = 0; i4 < i; i4++) {
            char c = cArr[i4];
            if (c < 128) {
                int i5 = i3;
                i3++;
                bArr[i5] = (byte) c;
            } else if (c < 2048) {
                int i6 = i3;
                int i7 = i3 + 1;
                bArr[i6] = (byte) (192 | ((c >> 6) & 31));
                i3 = i7 + 1;
                bArr[i7] = (byte) (128 | (c & '?'));
            } else {
                int i8 = i3;
                int i9 = i3 + 1;
                bArr[i8] = (byte) (224 | ((c >> '\f') & 15));
                int i10 = i9 + 1;
                bArr[i9] = (byte) (128 | ((c >> 6) & 63));
                i3 = i10 + 1;
                bArr[i10] = (byte) (128 | (c & '?'));
            }
        }
        return i3 - i2;
    }

    public final boolean isValidUtf8() {
        int i = this.m_offset;
        int i2 = this.m_offset + this.m_length;
        while (i < i2) {
            int i3 = i;
            i++;
            byte b2 = this.m_bytes[i3];
            if (b2 < 0) {
                if ((b2 & 224) == 192 && i < i2) {
                    i++;
                    if ((this.m_bytes[i] & 192) == 128) {
                        continue;
                    }
                }
                if ((b2 & 240) != 224 || i + 1 >= i2) {
                    return false;
                }
                int i4 = i;
                int i5 = i + 1;
                if ((this.m_bytes[i4] & 192) != 128) {
                    return false;
                }
                i = i5 + 1;
                if ((this.m_bytes[i5] & 192) != 128) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue tonumber() {
        double scannumber = scannumber();
        return Double.isNaN(scannumber) ? NIL : valueOf(scannumber);
    }

    public final LuaValue tonumber(int i) {
        double scannumber = scannumber(i);
        return Double.isNaN(scannumber) ? NIL : valueOf(scannumber);
    }

    public final double scannumber() {
        int i = this.m_offset;
        int i2 = this.m_offset + this.m_length;
        while (i < i2 && this.m_bytes[i] == 32) {
            i++;
        }
        while (i < i2 && this.m_bytes[i2 - 1] == 32) {
            i2--;
        }
        if (i >= i2) {
            return Double.NaN;
        }
        if (this.m_bytes[i] == 48 && i + 1 < i2 && (this.m_bytes[i + 1] == 120 || this.m_bytes[i + 1] == 88)) {
            return a(16, i + 2, i2);
        }
        double a2 = a(10, i, i2);
        return Double.isNaN(a2) ? a(i, i2) : a2;
    }

    public final double scannumber(int i) {
        if (i < 2 || i > 36) {
            return Double.NaN;
        }
        int i2 = this.m_offset;
        int i3 = this.m_offset + this.m_length;
        while (i2 < i3 && this.m_bytes[i2] == 32) {
            i2++;
        }
        while (i2 < i3 && this.m_bytes[i3 - 1] == 32) {
            i3--;
        }
        if (i2 >= i3) {
            return Double.NaN;
        }
        return a(i, i2, i3);
    }

    private double a(int i, int i2, int i3) {
        byte b2;
        long j = 0;
        boolean z = this.m_bytes[i2] == 45;
        boolean z2 = z;
        for (int i4 = z ? i2 + 1 : i2; i4 < i3; i4++) {
            byte b3 = this.m_bytes[i4];
            if (i <= 10 || (this.m_bytes[i4] >= 48 && this.m_bytes[i4] <= 57)) {
                b2 = 48;
            } else {
                b2 = (this.m_bytes[i4] < 65 || this.m_bytes[i4] > 90) ? (byte) 87 : (byte) 55;
            }
            int i5 = b3 - b2;
            if (i5 >= 0 && i5 < i) {
                long j2 = (j * i) + i5;
                j = j2;
                if (j2 < 0) {
                    return Double.NaN;
                }
            } else {
                return Double.NaN;
            }
        }
        return z2 ? -j : j;
    }

    private double a(int i, int i2) {
        if (i2 > i + 64) {
            i2 = i + 64;
        }
        for (int i3 = i; i3 < i2; i3++) {
            switch (this.m_bytes[i3]) {
                case 43:
                case 45:
                case 46:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 69:
                case 101:
                case 44:
                case 47:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                default:
                    return Double.NaN;
            }
        }
        char[] cArr = new char[i2 - i];
        for (int i4 = i; i4 < i2; i4++) {
            cArr[i4 - i] = (char) this.m_bytes[i4];
        }
        try {
            return Double.parseDouble(new String(cArr));
        } catch (Exception unused) {
            return Double.NaN;
        }
    }

    public final void printToStream(PrintStream printStream) {
        int i = this.m_length;
        for (int i2 = 0; i2 < i; i2++) {
            printStream.print((char) this.m_bytes[this.m_offset + i2]);
        }
    }
}
