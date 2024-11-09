package com.prineside.luaj;

import com.prineside.luaj.Globals;
import java.io.DataInputStream;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/prineside/luaj/LoadState.class */
public final class LoadState {
    public static final int NUMBER_FORMAT_FLOATS_OR_DOUBLES = 0;
    public static final int NUMBER_FORMAT_INTS_ONLY = 1;
    public static final int NUMBER_FORMAT_NUM_PATCH_INT32 = 4;
    public static final int LUA_TINT = -2;
    public static final int LUA_TNONE = -1;
    public static final int LUA_TNIL = 0;
    public static final int LUA_TBOOLEAN = 1;
    public static final int LUA_TLIGHTUSERDATA = 2;
    public static final int LUA_TNUMBER = 3;
    public static final int LUA_TSTRING = 4;
    public static final int LUA_TTABLE = 5;
    public static final int LUA_TFUNCTION = 6;
    public static final int LUA_TUSERDATA = 7;
    public static final int LUA_TTHREAD = 8;
    public static final int LUA_TVALUE = 9;
    public static final String SOURCE_BINARY_STRING = "binary string";
    public static final int LUAC_VERSION = 82;
    public static final int LUAC_FORMAT = 0;
    public static final int LUAC_HEADERSIZE = 12;

    /* renamed from: a, reason: collision with root package name */
    private boolean f1474a;

    /* renamed from: b, reason: collision with root package name */
    private int f1475b;
    private int c;
    public final DataInputStream is;
    private byte[] i = new byte[512];
    public static final Globals.Undumper instance = new GlobalsUndumper();
    public static final byte[] LUA_SIGNATURE = {27, 76, 117, 97};
    public static final byte[] LUAC_TAIL = {25, -109, 13, 10, 26, 10};
    private static final LuaValue[] d = new LuaValue[0];
    private static final Prototype[] e = new Prototype[0];
    private static final LocVars[] f = new LocVars[0];
    private static final Upvaldesc[] g = new Upvaldesc[0];
    private static final int[] h = new int[0];

    public static void install(Globals globals) {
        globals.undumper = instance;
    }

    private int a() {
        this.is.readFully(this.i, 0, 4);
        if (this.f1474a) {
            return (this.i[3] << 24) | ((255 & this.i[2]) << 16) | ((255 & this.i[1]) << 8) | (255 & this.i[0]);
        }
        return (this.i[0] << 24) | ((255 & this.i[1]) << 16) | ((255 & this.i[2]) << 8) | (255 & this.i[3]);
    }

    private int[] b() {
        int i;
        byte b2;
        byte b3;
        int a2 = a();
        if (a2 == 0) {
            return h;
        }
        int i2 = a2 << 2;
        if (this.i.length < i2) {
            this.i = new byte[i2];
        }
        this.is.readFully(this.i, 0, i2);
        int[] iArr = new int[a2];
        int i3 = 0;
        int i4 = 0;
        while (i3 < a2) {
            int i5 = i3;
            if (this.f1474a) {
                i = (this.i[i4 + 3] << 24) | ((255 & this.i[i4 + 2]) << 16) | ((255 & this.i[i4 + 1]) << 8);
                b2 = 255;
                b3 = this.i[i4];
            } else {
                i = (this.i[i4] << 24) | ((255 & this.i[i4 + 1]) << 16) | ((255 & this.i[i4 + 2]) << 8);
                b2 = 255;
                b3 = this.i[i4 + 3];
            }
            iArr[i5] = i | (b2 & b3);
            i3++;
            i4 += 4;
        }
        return iArr;
    }

    private long c() {
        int a2;
        int a3;
        if (this.f1474a) {
            a3 = a();
            a2 = a();
        } else {
            a2 = a();
            a3 = a();
        }
        return (a2 << 32) | (a3 & 4294967295L);
    }

    private LuaString d() {
        int c = this.f1475b == 8 ? (int) c() : a();
        int i = c;
        if (c == 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        this.is.readFully(bArr, 0, i);
        return LuaString.valueUsing(bArr, 0, bArr.length - 1);
    }

    public static LuaValue longBitsToLuaNumber(long j) {
        if ((j & Long.MAX_VALUE) == 0) {
            return LuaValue.ZERO;
        }
        int i = ((int) ((j >> 52) & 2047)) - 1023;
        if (i >= 0 && i < 31) {
            long j2 = j & 4503599627370495L;
            int i2 = 52 - i;
            if ((j2 & ((1 << i2) - 1)) == 0) {
                int i3 = ((int) (j2 >> i2)) | (1 << i);
                return LuaNumber.valueOf((j >> 63) != 0 ? -i3 : i3);
            }
        }
        return LuaValue.valueOf(Double.longBitsToDouble(j));
    }

    private LuaValue e() {
        if (this.c == 1) {
            return LuaNumber.valueOf(a());
        }
        return longBitsToLuaNumber(c());
    }

    private void a(Prototype prototype) {
        int a2 = a();
        LuaValue[] luaValueArr = a2 > 0 ? new LuaValue[a2] : d;
        for (int i = 0; i < a2; i++) {
            switch (this.is.readByte()) {
                case -2:
                    luaValueArr[i] = LuaNumber.valueOf(a());
                    break;
                case -1:
                case 2:
                default:
                    throw new IllegalStateException("bad constant");
                case 0:
                    luaValueArr[i] = LuaValue.NIL;
                    break;
                case 1:
                    luaValueArr[i] = 0 != this.is.readUnsignedByte() ? LuaValue.TRUE : LuaValue.FALSE;
                    break;
                case 3:
                    luaValueArr[i] = e();
                    break;
                case 4:
                    luaValueArr[i] = d();
                    break;
            }
        }
        prototype.k = luaValueArr;
        int a3 = a();
        Prototype[] prototypeArr = a3 > 0 ? new Prototype[a3] : e;
        for (int i2 = 0; i2 < a3; i2++) {
            prototypeArr[i2] = loadFunction(prototype.source);
        }
        prototype.p = prototypeArr;
    }

    private void b(Prototype prototype) {
        int a2 = a();
        prototype.upvalues = a2 > 0 ? new Upvaldesc[a2] : g;
        for (int i = 0; i < a2; i++) {
            prototype.upvalues[i] = new Upvaldesc(null, this.is.readByte() != 0, this.is.readByte() & 255);
        }
    }

    private void c(Prototype prototype) {
        prototype.source = d();
        prototype.lineinfo = b();
        int a2 = a();
        prototype.locvars = a2 > 0 ? new LocVars[a2] : f;
        for (int i = 0; i < a2; i++) {
            prototype.locvars[i] = new LocVars(d(), a(), a());
        }
        int a3 = a();
        for (int i2 = 0; i2 < a3; i2++) {
            prototype.upvalues[i2].name = d();
        }
    }

    public final Prototype loadFunction(LuaString luaString) {
        Prototype prototype = new Prototype();
        prototype.linedefined = a();
        prototype.lastlinedefined = a();
        prototype.numparams = this.is.readUnsignedByte();
        prototype.is_vararg = this.is.readUnsignedByte();
        prototype.maxstacksize = this.is.readUnsignedByte();
        prototype.code = b();
        a(prototype);
        b(prototype);
        c(prototype);
        return prototype;
    }

    public final void loadHeader() {
        this.is.readByte();
        this.is.readByte();
        this.f1474a = 0 != this.is.readByte();
        this.is.readByte();
        this.f1475b = this.is.readByte();
        this.is.readByte();
        this.is.readByte();
        this.c = this.is.readByte();
        for (int i = 0; i < LUAC_TAIL.length; i++) {
            if (this.is.readByte() != LUAC_TAIL[i]) {
                throw new LuaError("Unexpeted byte in luac tail of header, index=" + i);
            }
        }
    }

    public static Prototype undump(InputStream inputStream, String str) {
        if (inputStream.read() != LUA_SIGNATURE[0] || inputStream.read() != LUA_SIGNATURE[1] || inputStream.read() != LUA_SIGNATURE[2] || inputStream.read() != LUA_SIGNATURE[3]) {
            return null;
        }
        String sourceName = getSourceName(str);
        LoadState loadState = new LoadState(inputStream, sourceName);
        loadState.loadHeader();
        switch (loadState.c) {
            case 0:
            case 1:
            case 4:
                return loadState.loadFunction(LuaString.valueOf(sourceName));
            case 2:
            case 3:
            default:
                throw new LuaError("unsupported int size");
        }
    }

    public static String getSourceName(String str) {
        String str2 = str;
        if (str.startsWith("@") || str.startsWith("=")) {
            str2 = str.substring(1);
        } else if (str.startsWith("\u001b")) {
            str2 = SOURCE_BINARY_STRING;
        }
        return str2;
    }

    private LoadState(InputStream inputStream, String str) {
        this.is = new DataInputStream(inputStream);
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/LoadState$GlobalsUndumper.class */
    public static final class GlobalsUndumper implements Globals.Undumper {
        @Override // com.prineside.luaj.Globals.Undumper
        public final Prototype undump(InputStream inputStream, String str) {
            return LoadState.undump(inputStream, str);
        }
    }
}
