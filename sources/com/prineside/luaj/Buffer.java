package com.prineside.luaj;

/* loaded from: infinitode-2.jar:com/prineside/luaj/Buffer.class */
public final class Buffer {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f1466a = new byte[0];

    /* renamed from: b, reason: collision with root package name */
    private byte[] f1467b;
    private int c;
    private int d;
    private LuaValue e;

    public Buffer() {
        this(64);
    }

    public Buffer(int i) {
        this.f1467b = new byte[i];
        this.c = 0;
        this.d = 0;
        this.e = null;
    }

    public Buffer(LuaValue luaValue) {
        this.f1467b = f1466a;
        this.d = 0;
        this.c = 0;
        this.e = luaValue;
    }

    public final LuaValue value() {
        return this.e != null ? this.e : tostring();
    }

    public final Buffer setvalue(LuaValue luaValue) {
        this.f1467b = f1466a;
        this.c = 0;
        this.d = 0;
        this.e = luaValue;
        return this;
    }

    public final LuaString tostring() {
        a(this.c, 0);
        return LuaString.valueOf(this.f1467b, this.d, this.c);
    }

    public final String tojstring() {
        return value().tojstring();
    }

    public final String toString() {
        return tojstring();
    }

    public final Buffer append(byte b2) {
        makeroom(0, 1);
        byte[] bArr = this.f1467b;
        int i = this.d;
        int i2 = this.c;
        this.c = i2 + 1;
        bArr[i + i2] = b2;
        return this;
    }

    public final Buffer append(LuaValue luaValue) {
        append(luaValue.strvalue());
        return this;
    }

    public final Buffer append(LuaString luaString) {
        int i = luaString.m_length;
        makeroom(0, i);
        luaString.copyInto(0, this.f1467b, this.d + this.c, i);
        this.c += i;
        return this;
    }

    public final Buffer append(String str) {
        char[] charArray = str.toCharArray();
        int lengthAsUtf8 = LuaString.lengthAsUtf8(charArray);
        makeroom(0, lengthAsUtf8);
        LuaString.encodeToUtf8(charArray, charArray.length, this.f1467b, this.d + this.c);
        this.c += lengthAsUtf8;
        return this;
    }

    public final Buffer concatTo(LuaValue luaValue) {
        return setvalue(luaValue.concat(value()));
    }

    public final Buffer concatTo(LuaString luaString) {
        return (this.e == null || this.e.isstring()) ? prepend(luaString) : setvalue(luaString.concat(this.e));
    }

    public final Buffer concatTo(LuaNumber luaNumber) {
        return (this.e == null || this.e.isstring()) ? prepend(luaNumber.strvalue()) : setvalue(luaNumber.concat(this.e));
    }

    public final Buffer prepend(LuaString luaString) {
        int i = luaString.m_length;
        makeroom(i, 0);
        System.arraycopy(luaString.m_bytes, luaString.m_offset, this.f1467b, this.d - i, i);
        this.d -= i;
        this.c += i;
        this.e = null;
        return this;
    }

    public final void makeroom(int i, int i2) {
        int i3;
        if (this.e != null) {
            LuaString strvalue = this.e.strvalue();
            this.e = null;
            this.c = strvalue.m_length;
            this.d = i;
            this.f1467b = new byte[i + this.c + i2];
            System.arraycopy(strvalue.m_bytes, strvalue.m_offset, this.f1467b, this.d, this.c);
            return;
        }
        if (this.d + this.c + i2 > this.f1467b.length || this.d < i) {
            int i4 = i + this.c + i2;
            if (i4 < 32) {
                i3 = 32;
            } else {
                i3 = i4 < (this.c << 1) ? this.c << 1 : i4;
            }
            int i5 = i3;
            a(i5, i == 0 ? 0 : (i5 - this.c) - i2);
        }
    }

    private final void a(int i, int i2) {
        if (i != this.f1467b.length) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.f1467b, this.d, bArr, i2, this.c);
            this.f1467b = bArr;
            this.d = i2;
        }
    }
}
