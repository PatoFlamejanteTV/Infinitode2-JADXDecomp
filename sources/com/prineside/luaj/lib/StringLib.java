package com.prineside.luaj.lib;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.Buffer;
import com.prineside.luaj.LuaClosure;
import com.prineside.luaj.LuaFunction;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.luaj.compiler.DumpState;
import com.prineside.luaj.lib.jse.JavaClass;
import com.prineside.tdi2.managers.script.ReadOnlyLuaTable;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib.class */
public class StringLib extends TwoArgFunction {

    /* renamed from: b, reason: collision with root package name */
    private static final LuaString f1595b = valueOf("^$*+?.([%-");

    /* renamed from: a, reason: collision with root package name */
    static final byte[] f1596a = new byte[256];

    @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        LuaTable luaTable = new LuaTable();
        for (Map.Entry<LuaString, LuaValue> entry : JavaClass.forClass(String.class).getInstanceMethods().entrySet()) {
            luaTable.set(entry.getKey(), new _stringMethodWrapper(entry.getValue(), (byte) 0));
        }
        luaTable.set("byte", new _byte());
        luaTable.set("char", new _char());
        luaTable.set("dump", new dump());
        luaTable.set("find", new find());
        luaTable.set("format", new format(this, (byte) 0));
        luaTable.set("gmatch", new gmatch());
        luaTable.set("gsub", new gsub());
        luaTable.set("len", new len());
        luaTable.set("lower", new lower());
        luaTable.set("match", new match());
        luaTable.set("rep", new rep());
        luaTable.set("reverse", new reverse());
        luaTable.set(FlexmarkHtmlConverter.SUB_NODE, new sub());
        luaTable.set("upper", new upper());
        luaValue2.set("string", luaTable);
        if (!luaValue2.get("package").isnil()) {
            luaValue2.get("package").get("loaded").set("string", luaTable);
        }
        if (LuaString.s_metatable == null) {
            LuaString.s_metatable = new ReadOnlyLuaTable(LuaValue.tableOf(new LuaValue[]{INDEX, luaTable}));
        }
        return luaTable;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$_stringMethodWrapper.class */
    public static final class _stringMethodWrapper extends VarArgFunction {

        /* renamed from: a, reason: collision with root package name */
        @NAGS
        private LuaValue f1603a;

        /* synthetic */ _stringMethodWrapper(LuaValue luaValue, byte b2) {
            this(luaValue);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            super.write(kryo, output);
            kryo.writeClassAndObject(output, this.f1603a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            super.read(kryo, input);
            this.f1603a = (LuaValue) kryo.readClassAndObject(input);
        }

        private _stringMethodWrapper() {
        }

        private _stringMethodWrapper(LuaValue luaValue) {
            this.f1603a = luaValue;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return this.f1603a.invoke(LuaValue.varargsOf(LuaValue.userdataOf(varargs.tojstring(1)), varargs.subargs(2)));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$_byte.class */
    public static final class _byte extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaString checkstring = varargs.checkstring(1);
            int i = checkstring.m_length;
            int a2 = StringLib.a(varargs.optint(2, 1), i);
            int a3 = StringLib.a(varargs.optint(3, a2), i);
            if (a2 <= 0) {
                a2 = 1;
            }
            if (a3 > i) {
                a3 = i;
            }
            if (a2 > a3) {
                return NONE;
            }
            int i2 = (a3 - a2) + 1;
            if (a2 + i2 <= a3) {
                error("string slice too long");
            }
            LuaValue[] luaValueArr = new LuaValue[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                luaValueArr[i3] = valueOf(checkstring.luaByte((a2 + i3) - 1));
            }
            return varargsOf(luaValueArr);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$_char.class */
    public static final class _char extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            int narg = varargs.narg();
            byte[] bArr = new byte[narg];
            int i = 0;
            int i2 = 1;
            while (i < narg) {
                int checkint = varargs.checkint(i2);
                if (checkint < 0 || checkint >= 256) {
                    argerror(i2, "invalid value for string.char [0; 255]: " + checkint);
                }
                bArr[i] = (byte) checkint;
                i++;
                i2++;
            }
            return LuaString.valueUsing(bArr);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$dump.class */
    public static final class dump extends VarArgFunction {
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12, types: [com.prineside.luaj.LuaString, com.prineside.luaj.Varargs] */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.io.IOException] */
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaFunction checkfunction = varargs.checkfunction(1);
            ?? byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                DumpState.dump(((LuaClosure) checkfunction).p, byteArrayOutputStream, varargs.optboolean(2, true));
                byteArrayOutputStream = LuaString.valueUsing(byteArrayOutputStream.toByteArray());
                return byteArrayOutputStream;
            } catch (IOException e) {
                return error(byteArrayOutputStream.getMessage());
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$find.class */
    public static final class find extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return StringLib.a(varargs, true);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$format.class */
    public static final class format extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private StringLib f1604a;

        /* synthetic */ format(StringLib stringLib, byte b2) {
            this(stringLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f1604a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1604a = (StringLib) kryo.readObject(input, StringLib.class);
        }

        private format() {
        }

        private format(StringLib stringLib) {
            this.f1604a = stringLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaString checkstring = varargs.checkstring(1);
            int length = checkstring.length();
            Buffer buffer = new Buffer(length);
            int i = 1;
            int i2 = 0;
            while (i2 < length) {
                int i3 = i2;
                i2++;
                int luaByte = checkstring.luaByte(i3);
                switch (luaByte) {
                    case 10:
                        buffer.append(SequenceUtils.EOL);
                        break;
                    case 37:
                        if (i2 < length) {
                            if (checkstring.luaByte(i2) == 37) {
                                i2++;
                                buffer.append((byte) 37);
                                break;
                            } else {
                                i++;
                                FormatDesc formatDesc = new FormatDesc(varargs, checkstring, i2, this.f1604a);
                                i2 += formatDesc.length;
                                switch (formatDesc.conversion) {
                                    case 69:
                                    case 71:
                                    case 101:
                                    case 102:
                                    case 103:
                                        formatDesc.format(buffer, varargs.checkdouble(i));
                                        break;
                                    case 70:
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
                                    case 104:
                                    case 106:
                                    case 107:
                                    case 108:
                                    case 109:
                                    case 110:
                                    case 112:
                                    case 114:
                                    case 116:
                                    case 118:
                                    case 119:
                                    default:
                                        error("invalid option '%" + ((char) formatDesc.conversion) + "' to 'format'");
                                        break;
                                    case 88:
                                    case 111:
                                    case 117:
                                    case 120:
                                        formatDesc.format(buffer, varargs.checklong(i));
                                        break;
                                    case 99:
                                        formatDesc.format(buffer, (byte) varargs.checkint(i));
                                        break;
                                    case 100:
                                    case 105:
                                        formatDesc.format(buffer, varargs.checklong(i));
                                        break;
                                    case 113:
                                        StringLib.a(buffer, varargs.checkstring(i));
                                        break;
                                    case 115:
                                        LuaString checkstring2 = varargs.checkstring(i);
                                        if (formatDesc.f1598a == -1 && checkstring2.length() >= 100) {
                                            buffer.append(checkstring2);
                                            break;
                                        } else {
                                            formatDesc.format(buffer, checkstring2);
                                            break;
                                        }
                                }
                            }
                        } else {
                            break;
                        }
                    default:
                        buffer.append((byte) luaByte);
                        break;
                }
            }
            return buffer.tostring();
        }
    }

    static void a(Buffer buffer, LuaString luaString) {
        buffer.append((byte) 34);
        int length = luaString.length();
        for (int i = 0; i < length; i++) {
            int luaByte = luaString.luaByte(i);
            switch (luaByte) {
                case 10:
                case 34:
                case 92:
                    buffer.append((byte) 92);
                    buffer.append((byte) luaByte);
                    break;
                default:
                    if (luaByte <= 31 || luaByte == 127) {
                        buffer.append((byte) 92);
                        if (i + 1 == length || luaString.luaByte(i + 1) < 48 || luaString.luaByte(i + 1) > 57) {
                            buffer.append(Integer.toString(luaByte));
                            break;
                        } else {
                            buffer.append((byte) 48);
                            buffer.append((byte) (48 + (luaByte / 10)));
                            buffer.append((byte) (48 + (luaByte % 10)));
                            break;
                        }
                    } else {
                        buffer.append((byte) luaByte);
                        break;
                    }
                    break;
            }
        }
        buffer.append((byte) 34);
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$FormatDesc.class */
    static class FormatDesc {

        /* renamed from: b, reason: collision with root package name */
        private boolean f1597b;
        private boolean c;
        private boolean d;
        private boolean e;
        private int f;

        /* renamed from: a, reason: collision with root package name */
        int f1598a;
        public final int conversion;
        public final int length;
        public final String src;
        private StringLib g;

        public FormatDesc(Varargs varargs, LuaString luaString, int i, StringLib stringLib) {
            int i2;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            this.g = stringLib;
            int i8 = i;
            int length = luaString.length();
            int i9 = 0;
            boolean z = true;
            while (z) {
                if (i8 < length) {
                    int i10 = i8;
                    i8++;
                    i7 = luaString.luaByte(i10);
                } else {
                    i7 = 0;
                }
                i9 = i7;
                switch (i7) {
                    case 32:
                        this.e = true;
                        break;
                    case 35:
                        break;
                    case 43:
                        this.d = true;
                        break;
                    case 45:
                        this.f1597b = true;
                        break;
                    case 48:
                        this.c = true;
                        break;
                    default:
                        z = false;
                        break;
                }
            }
            if (i8 - i > 5) {
                LuaValue.error("invalid format (repeated flags)");
            }
            this.f = -1;
            if (Character.isDigit((char) i9)) {
                this.f = i9 - 48;
                if (i8 < length) {
                    int i11 = i8;
                    i8++;
                    i5 = luaString.luaByte(i11);
                } else {
                    i5 = 0;
                }
                i9 = i5;
                if (Character.isDigit((char) i5)) {
                    this.f = (this.f * 10) + (i9 - 48);
                    if (i8 < length) {
                        int i12 = i8;
                        i8++;
                        i6 = luaString.luaByte(i12);
                    } else {
                        i6 = 0;
                    }
                    i9 = i6;
                }
            }
            this.f1598a = -1;
            if (i9 == 46) {
                if (i8 < length) {
                    int i13 = i8;
                    i8++;
                    i2 = luaString.luaByte(i13);
                } else {
                    i2 = 0;
                }
                i9 = i2;
                if (Character.isDigit((char) i2)) {
                    this.f1598a = i9 - 48;
                    if (i8 < length) {
                        int i14 = i8;
                        i8++;
                        i3 = luaString.luaByte(i14);
                    } else {
                        i3 = 0;
                    }
                    i9 = i3;
                    if (Character.isDigit((char) i3)) {
                        this.f1598a = (this.f1598a * 10) + (i9 - 48);
                        if (i8 < length) {
                            int i15 = i8;
                            i8++;
                            i4 = luaString.luaByte(i15);
                        } else {
                            i4 = 0;
                        }
                        i9 = i4;
                    }
                }
            }
            if (Character.isDigit((char) i9)) {
                LuaValue.error("invalid format (width or precision too long)");
            }
            this.c &= !this.f1597b;
            this.conversion = i9;
            this.length = i8 - i;
            this.src = luaString.substring(i - 1, i8).tojstring();
        }

        public void format(Buffer buffer, byte b2) {
            buffer.append(b2);
        }

        public void format(Buffer buffer, long j) {
            int i;
            String l;
            int i2;
            if (j == 0 && this.f1598a == 0) {
                l = "";
            } else {
                switch (this.conversion) {
                    case 88:
                    case 120:
                        i = 16;
                        break;
                    case 111:
                        i = 8;
                        break;
                    default:
                        i = 10;
                        break;
                }
                l = Long.toString(j, i);
                if (this.conversion == 88) {
                    l = l.toUpperCase();
                }
            }
            int length = l.length();
            int i3 = length;
            int i4 = length;
            if (j < 0) {
                i4--;
            } else if (this.d || this.e) {
                i3++;
            }
            if (this.f1598a > i4) {
                i2 = this.f1598a - i4;
            } else if (this.f1598a == -1 && this.c && this.f > i3) {
                i2 = this.f - i3;
            } else {
                i2 = 0;
            }
            int i5 = i3 + i2;
            int i6 = this.f > i5 ? this.f - i5 : 0;
            if (!this.f1597b) {
                pad(buffer, ' ', i6);
            }
            if (j < 0) {
                if (i2 > 0) {
                    buffer.append((byte) 45);
                    l = l.substring(1);
                }
            } else if (this.d) {
                buffer.append((byte) 43);
            } else if (this.e) {
                buffer.append((byte) 32);
            }
            if (i2 > 0) {
                pad(buffer, '0', i2);
            }
            buffer.append(l);
            if (this.f1597b) {
                pad(buffer, ' ', i6);
            }
        }

        public void format(Buffer buffer, double d) {
            buffer.append(this.g.a(this.src, d));
        }

        public void format(Buffer buffer, LuaString luaString) {
            int indexOf = luaString.indexOf((byte) 0, 0);
            if (indexOf != -1) {
                luaString = luaString.substring(0, indexOf);
            }
            buffer.append(luaString);
        }

        public final void pad(Buffer buffer, char c, int i) {
            byte b2 = (byte) c;
            while (true) {
                int i2 = i;
                i--;
                if (i2 > 0) {
                    buffer.append(b2);
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String a(String str, double d) {
        return String.valueOf(d);
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$gmatch.class */
    public static final class gmatch extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return new GMatchAux(varargs, varargs.checkstring(1), varargs.checkstring(2));
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$GMatchAux.class */
    static class GMatchAux extends VarArgFunction {

        /* renamed from: a, reason: collision with root package name */
        private final int f1599a;

        /* renamed from: b, reason: collision with root package name */
        private final MatchState f1600b;
        private int e = 0;
        private int f = -1;

        public GMatchAux(Varargs varargs, LuaString luaString, LuaString luaString2) {
            this.f1599a = luaString.length();
            this.f1600b = new MatchState(varargs, luaString, luaString2);
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            while (this.e <= this.f1599a) {
                this.f1600b.a();
                int a2 = this.f1600b.a(this.e, 0);
                if (a2 < 0 || a2 == this.f) {
                    this.e++;
                } else {
                    int i = this.e;
                    this.e = a2;
                    this.f = a2;
                    return this.f1600b.a(true, i, a2);
                }
            }
            return NIL;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$gsub.class */
    public static final class gsub extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaString checkstring = varargs.checkstring(1);
            int length = checkstring.length();
            LuaString checkstring2 = varargs.checkstring(2);
            int i = -1;
            LuaValue arg = varargs.arg(3);
            int optint = varargs.optint(4, length + 1);
            boolean z = checkstring2.length() > 0 && checkstring2.charAt(0) == 94;
            Buffer buffer = new Buffer(length);
            MatchState matchState = new MatchState(varargs, checkstring, checkstring2);
            int i2 = 0;
            int i3 = 0;
            while (i3 < optint) {
                matchState.a();
                int a2 = matchState.a(i2, z ? 1 : 0);
                if (a2 != -1 && a2 != i) {
                    i3++;
                    matchState.add_value(buffer, i2, a2, arg);
                    i = a2;
                    i2 = a2;
                } else {
                    if (i2 >= length) {
                        break;
                    }
                    int i4 = i2;
                    i2++;
                    buffer.append((byte) checkstring.luaByte(i4));
                }
                if (z) {
                    break;
                }
            }
            buffer.append(checkstring.substring(i2, length));
            return varargsOf(buffer.tostring(), valueOf(i3));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$len.class */
    public static final class len extends OneArgFunction {
        @Override // com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return luaValue.checkstring().len();
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$lower.class */
    public static final class lower extends OneArgFunction {
        @Override // com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return valueOf(luaValue.checkjstring().toLowerCase());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$match.class */
    public static final class match extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return StringLib.a(varargs, false);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$rep.class */
    public static final class rep extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaString checkstring = varargs.checkstring(1);
            byte[] bArr = new byte[checkstring.length() * varargs.checkint(2)];
            int length = checkstring.length();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < bArr.length) {
                    checkstring.copyInto(0, bArr, i2, length);
                    i = i2 + length;
                } else {
                    return LuaString.valueUsing(bArr);
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$reverse.class */
    public static final class reverse extends OneArgFunction {
        @Override // com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            LuaString checkstring = luaValue.checkstring();
            int length = checkstring.length();
            byte[] bArr = new byte[length];
            int i = 0;
            int i2 = length - 1;
            while (i < length) {
                bArr[i2] = (byte) checkstring.luaByte(i);
                i++;
                i2--;
            }
            return LuaString.valueUsing(bArr);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$trim.class */
    public static final class trim extends OneArgFunction {
        @Override // com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return LuaString.valueOf(luaValue.checkjstring().trim());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$sub.class */
    public static final class sub extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaString checkstring = varargs.checkstring(1);
            int length = checkstring.length();
            int a2 = StringLib.a(varargs.checkint(2), length);
            int a3 = StringLib.a(varargs.optint(3, -1), length);
            if (a2 <= 0) {
                a2 = 1;
            }
            if (a3 > length) {
                a3 = length;
            }
            if (a2 <= a3) {
                return checkstring.substring(a2 - 1, a3);
            }
            return EMPTYSTRING;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$upper.class */
    public static final class upper extends OneArgFunction {
        @Override // com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return valueOf(luaValue.checkjstring().toUpperCase());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$split.class */
    public static final class split extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaString checkstring = varargs.checkstring(1);
            LuaString checkstring2 = varargs.checkstring(2);
            if (varargs.narg() == 2) {
                return LuaValue.cObject(checkstring.checkjstring().split(checkstring2.checkjstring()));
            }
            return LuaValue.cObject(checkstring.checkjstring().split(checkstring2.checkjstring(), varargs.checkint(3)));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$contains.class */
    public static final class contains extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return varargs.checkjstring(1).contains(varargs.checkjstring(2)) ? LuaValue.TRUE : LuaValue.FALSE;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$replace.class */
    public static final class replace extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return LuaString.valueOf(varargs.checkjstring(1).replace(varargs.checkjstring(2), varargs.checkjstring(3)));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$replaceAll.class */
    public static final class replaceAll extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return LuaString.valueOf(varargs.checkjstring(1).replaceAll(varargs.checkjstring(2), varargs.checkjstring(3)));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$endsWith.class */
    public static final class endsWith extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return varargs.checkjstring(1).endsWith(varargs.checkjstring(2)) ? LuaValue.TRUE : LuaValue.FALSE;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$startsWith.class */
    public static final class startsWith extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return varargs.checkjstring(1).startsWith(varargs.checkjstring(2)) ? LuaValue.TRUE : LuaValue.FALSE;
        }
    }

    static Varargs a(Varargs varargs, boolean z) {
        LuaString checkstring = varargs.checkstring(1);
        LuaString checkstring2 = varargs.checkstring(2);
        int optint = varargs.optint(3, 1);
        int i = optint;
        if (optint > 0) {
            i = Math.min(i - 1, checkstring.length());
        } else if (i < 0) {
            i = Math.max(0, checkstring.length() + i);
        }
        if (z && (varargs.arg(4).toboolean() || checkstring2.indexOfAny(f1595b) == -1)) {
            int indexOf = checkstring.indexOf(checkstring2, i);
            if (indexOf != -1) {
                return varargsOf(valueOf(indexOf + 1), valueOf(indexOf + checkstring2.length()));
            }
        } else {
            MatchState matchState = new MatchState(varargs, checkstring, checkstring2);
            boolean z2 = false;
            int i2 = 0;
            if (checkstring2.length() > 0 && checkstring2.luaByte(0) == 94) {
                z2 = true;
                i2 = 1;
            }
            int i3 = i;
            do {
                matchState.a();
                int a2 = matchState.a(i3, i2);
                if (a2 != -1) {
                    if (z) {
                        return LuaValue.varargsOf(valueOf(i3 + 1), valueOf(a2), matchState.a(false, i3, a2));
                    }
                    return matchState.a(true, i3, a2);
                }
                int i4 = i3;
                i3++;
                if (i4 >= checkstring.length()) {
                    break;
                }
            } while (!z2);
        }
        return NIL;
    }

    static int a(int i, int i2) {
        return i >= 0 ? i : i2 + i + 1;
    }

    static {
        for (int i = 0; i < 128; i++) {
            char c = (char) i;
            f1596a[i] = (byte) ((Character.isDigit(c) ? 8 : 0) | (Character.isLowerCase(c) ? 2 : 0) | (Character.isUpperCase(c) ? 4 : 0) | ((c < ' ' || c == 127) ? 64 : 0));
            if ((c >= 'a' && c <= 'f') || ((c >= 'A' && c <= 'F') || (c >= '0' && c <= '9'))) {
                byte[] bArr = f1596a;
                int i2 = i;
                bArr[i2] = (byte) (bArr[i2] | Byte.MIN_VALUE);
            }
            if ((c >= '!' && c <= '/') || ((c >= ':' && c <= '@') || ((c >= '[' && c <= '`') || (c >= '{' && c <= '~')))) {
                byte[] bArr2 = f1596a;
                int i3 = i;
                bArr2[i3] = (byte) (bArr2[i3] | 16);
            }
            if ((f1596a[i] & 6) != 0) {
                byte[] bArr3 = f1596a;
                int i4 = i;
                bArr3[i4] = (byte) (bArr3[i4] | 1);
            }
        }
        f1596a[32] = 32;
        byte[] bArr4 = f1596a;
        bArr4[13] = (byte) (bArr4[13] | 32);
        byte[] bArr5 = f1596a;
        bArr5[10] = (byte) (bArr5[10] | 32);
        byte[] bArr6 = f1596a;
        bArr6[9] = (byte) (bArr6[9] | 32);
        byte[] bArr7 = f1596a;
        bArr7[11] = (byte) (bArr7[11] | 32);
        byte[] bArr8 = f1596a;
        bArr8[12] = (byte) (bArr8[12] | 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/StringLib$MatchState.class */
    public static class MatchState {

        /* renamed from: b, reason: collision with root package name */
        private LuaString f1602b;
        private LuaString c;
        private int d = 0;
        private int[] e = new int[32];
        private int[] f = new int[32];

        /* renamed from: a, reason: collision with root package name */
        private int f1601a = 200;

        MatchState(Varargs varargs, LuaString luaString, LuaString luaString2) {
            this.f1602b = luaString;
            this.c = luaString2;
        }

        final void a() {
            this.d = 0;
            this.f1601a = 200;
        }

        private void a(Buffer buffer, LuaString luaString, int i, int i2) {
            String str;
            int length = luaString.length();
            int i3 = 0;
            while (i3 < length) {
                byte luaByte = (byte) luaString.luaByte(i3);
                if (luaByte != 37) {
                    buffer.append(luaByte);
                } else {
                    i3++;
                    byte luaByte2 = (byte) (i3 < length ? luaString.luaByte(i3) : 0);
                    if (!Character.isDigit((char) luaByte2)) {
                        if (luaByte2 != 37) {
                            StringBuilder sb = new StringBuilder("invalid use of '%' in replacement string: after '%' must be '0'-'9' or '%', but found ");
                            if (i3 < length) {
                                str = "symbol '" + ((char) luaByte2) + "' with code " + ((int) luaByte2) + " at pos " + (i3 + 1);
                            } else {
                                str = "end of string";
                            }
                            LuaValue.error(sb.append(str).toString());
                        }
                        buffer.append(luaByte2);
                    } else if (luaByte2 == 48) {
                        buffer.append(this.f1602b.substring(i, i2));
                    } else {
                        buffer.append(a(luaByte2 - 49, i, i2).strvalue());
                    }
                }
                i3++;
            }
        }

        public void add_value(Buffer buffer, int i, int i2, LuaValue luaValue) {
            LuaValue luaValue2;
            switch (luaValue.type()) {
                case 3:
                case 4:
                    a(buffer, luaValue.strvalue(), i, i2);
                    return;
                case 5:
                    luaValue2 = luaValue.get(a(0, i, i2));
                    break;
                case 6:
                    luaValue2 = luaValue.invoke(a(true, i, i2)).arg1();
                    break;
                default:
                    LuaValue.error("bad argument: string/function/table expected");
                    return;
            }
            if (!luaValue2.toboolean()) {
                luaValue2 = this.f1602b.substring(i, i2);
            } else if (!luaValue2.isstring()) {
                LuaValue.error("invalid replacement value (a " + luaValue2.typename() + ")");
            }
            buffer.append(luaValue2.strvalue());
        }

        final Varargs a(boolean z, int i, int i2) {
            int i3 = (this.d == 0 && z) ? 1 : this.d;
            int i4 = i3;
            switch (i3) {
                case 0:
                    return LuaValue.NONE;
                case 1:
                    return a(0, i, i2);
                default:
                    LuaValue[] luaValueArr = new LuaValue[i4];
                    for (int i5 = 0; i5 < i4; i5++) {
                        luaValueArr[i5] = a(i5, i, i2);
                    }
                    return LuaValue.varargsOf(luaValueArr);
            }
        }

        private LuaValue a(int i, int i2, int i3) {
            if (i >= this.d) {
                if (i == 0) {
                    return this.f1602b.substring(i2, i3);
                }
                return LuaValue.error("invalid capture index %" + (i + 1));
            }
            int i4 = this.f[i];
            if (i4 == -1) {
                return LuaValue.error("unfinished capture");
            }
            if (i4 == -2) {
                return LuaValue.valueOf(this.e[i] + 1);
            }
            int i5 = this.e[i];
            return this.f1602b.substring(i5, i5 + i4);
        }

        private int a(int i) {
            int i2 = i - 49;
            if (i2 < 0 || i2 >= this.d || this.f[i2] == -1) {
                LuaValue.error("invalid capture index %" + (i2 + 1));
            }
            return i2;
        }

        private int b() {
            int i = this.d;
            do {
                i--;
                if (i < 0) {
                    LuaValue.error("invalid pattern capture");
                    return 0;
                }
            } while (this.f[i] != -1);
            return i;
        }

        private int b(int i) {
            int i2 = i + 1;
            switch (this.c.luaByte(i)) {
                case 37:
                    if (i2 == this.c.length()) {
                        LuaValue.error("malformed pattern (ends with '%')");
                    }
                    return i2 + 1;
                case 91:
                    if (i2 != this.c.length() && this.c.luaByte(i2) == 94) {
                        i2++;
                    }
                    while (true) {
                        if (i2 == this.c.length()) {
                            LuaValue.error("malformed pattern (missing ']')");
                        }
                        int i3 = i2;
                        i2++;
                        if (this.c.luaByte(i3) == 37 && i2 < this.c.length()) {
                            i2++;
                        }
                        if (i2 != this.c.length() && this.c.luaByte(i2) == 93) {
                            return i2 + 1;
                        }
                    }
                    break;
                default:
                    return i2;
            }
        }

        private static boolean b(int i, int i2) {
            boolean z;
            char lowerCase = Character.toLowerCase((char) i2);
            byte b2 = StringLib.f1596a[i];
            switch (lowerCase) {
                case 'a':
                    z = (b2 & 1) != 0;
                    break;
                case 'b':
                case 'e':
                case 'f':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'm':
                case 'n':
                case 'o':
                case 'q':
                case 'r':
                case 't':
                case 'v':
                case 'y':
                default:
                    return i2 == i;
                case 'c':
                    z = (b2 & 64) != 0;
                    break;
                case 'd':
                    z = (b2 & 8) != 0;
                    break;
                case 'g':
                    z = (b2 & 25) != 0;
                    break;
                case 'l':
                    z = (b2 & 2) != 0;
                    break;
                case 'p':
                    z = (b2 & 16) != 0;
                    break;
                case 's':
                    z = (b2 & 32) != 0;
                    break;
                case 'u':
                    z = (b2 & 4) != 0;
                    break;
                case 'w':
                    z = (b2 & 9) != 0;
                    break;
                case 'x':
                    z = (b2 & Byte.MIN_VALUE) != 0;
                    break;
                case 'z':
                    z = i == 0;
                    break;
            }
            return lowerCase == i2 ? z : !z;
        }

        private boolean b(int i, int i2, int i3) {
            boolean z = true;
            if (this.c.luaByte(i2 + 1) == 94) {
                z = false;
                i2++;
            }
            while (true) {
                i2++;
                if (i2 >= i3) {
                    return !z;
                }
                if (this.c.luaByte(i2) == 37) {
                    i2++;
                    if (b(i, this.c.luaByte(i2))) {
                        return z;
                    }
                } else if (this.c.luaByte(i2 + 1) == 45 && i2 + 2 < i3) {
                    i2 += 2;
                    if (this.c.luaByte(i2 - 2) <= i && i <= this.c.luaByte(i2)) {
                        return z;
                    }
                } else if (this.c.luaByte(i2) == i) {
                    return z;
                }
            }
        }

        private boolean c(int i, int i2, int i3) {
            switch (this.c.luaByte(i2)) {
                case 37:
                    return b(i, this.c.luaByte(i2 + 1));
                case 46:
                    return true;
                case 91:
                    return b(i, i2, i3 - 1);
                default:
                    return this.c.luaByte(i2) == i;
            }
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:14:0x00d4. Please report as an issue. */
        /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0035. Please report as an issue. */
        /* JADX WARN: Removed duplicated region for block: B:49:0x020c A[Catch: all -> 0x02f2, TryCatch #0 {all -> 0x02f2, blocks: (B:6:0x0014, B:8:0x002d, B:9:0x0035, B:121:0x005c, B:123:0x006a, B:125:0x0077, B:128:0x008e, B:132:0x00a2, B:10:0x00b7, B:12:0x00c4, B:13:0x00ca, B:14:0x00d4, B:112:0x00f0, B:114:0x010a, B:16:0x0110, B:18:0x011e, B:20:0x0131, B:23:0x0149, B:26:0x0162, B:28:0x0171, B:39:0x015a, B:40:0x013f, B:41:0x012b, B:43:0x018f, B:103:0x01a2, B:108:0x01ba, B:45:0x01d1, B:95:0x01de, B:47:0x01fb, B:49:0x020c, B:52:0x0222, B:54:0x022f, B:56:0x023e, B:84:0x026d, B:90:0x028b, B:58:0x0292, B:64:0x02ab, B:70:0x02c5), top: B:5:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:54:0x022f A[Catch: all -> 0x02f2, TryCatch #0 {all -> 0x02f2, blocks: (B:6:0x0014, B:8:0x002d, B:9:0x0035, B:121:0x005c, B:123:0x006a, B:125:0x0077, B:128:0x008e, B:132:0x00a2, B:10:0x00b7, B:12:0x00c4, B:13:0x00ca, B:14:0x00d4, B:112:0x00f0, B:114:0x010a, B:16:0x0110, B:18:0x011e, B:20:0x0131, B:23:0x0149, B:26:0x0162, B:28:0x0171, B:39:0x015a, B:40:0x013f, B:41:0x012b, B:43:0x018f, B:103:0x01a2, B:108:0x01ba, B:45:0x01d1, B:95:0x01de, B:47:0x01fb, B:49:0x020c, B:52:0x0222, B:54:0x022f, B:56:0x023e, B:84:0x026d, B:90:0x028b, B:58:0x0292, B:64:0x02ab, B:70:0x02c5), top: B:5:0x0014 }] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0292 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x02a6 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:69:0x02c5 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:73:0x02d9 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:81:0x0268 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:92:0x023a  */
        /* JADX WARN: Removed duplicated region for block: B:94:0x01de A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        final int a(int r7, int r8) {
            /*
                Method dump skipped, instructions count: 767
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.lib.StringLib.MatchState.a(int, int):int");
        }

        private int d(int i, int i2, int i3) {
            int i4 = 0;
            while (i + i4 < this.f1602b.length() && c(this.f1602b.luaByte(i + i4), i2, i3)) {
                i4++;
            }
            while (i4 >= 0) {
                int a2 = a(i + i4, i3 + 1);
                if (a2 != -1) {
                    return a2;
                }
                i4--;
            }
            return -1;
        }

        private int e(int i, int i2, int i3) {
            while (true) {
                int a2 = a(i, i3 + 1);
                if (a2 != -1) {
                    return a2;
                }
                if (i < this.f1602b.length() && c(this.f1602b.luaByte(i), i2, i3)) {
                    i++;
                } else {
                    return -1;
                }
            }
        }

        private int f(int i, int i2, int i3) {
            int i4 = this.d;
            if (i4 >= 32) {
                LuaValue.error("too many captures");
            }
            this.e[i4] = i;
            this.f[i4] = i3;
            this.d = i4 + 1;
            int a2 = a(i, i2);
            if (a2 == -1) {
                this.d--;
            }
            return a2;
        }

        private int c(int i, int i2) {
            int b2 = b();
            this.f[b2] = i - this.e[b2];
            int a2 = a(i, i2);
            if (a2 == -1) {
                this.f[b2] = -1;
            }
            return a2;
        }

        private int d(int i, int i2) {
            int a2 = a(i2);
            int i3 = this.f[a2];
            if (this.f1602b.length() - i >= i3 && LuaString.equals(this.f1602b, this.e[a2], this.f1602b, i, i3)) {
                return i + i3;
            }
            return -1;
        }

        private int e(int i, int i2) {
            int luaByte;
            int length = this.c.length();
            if (i2 == length || i2 + 1 == length) {
                LuaValue.error("malformed pattern (missing arguments to '%b')");
            }
            int length2 = this.f1602b.length();
            if (i >= length2 || this.f1602b.luaByte(i) != (luaByte = this.c.luaByte(i2))) {
                return -1;
            }
            int luaByte2 = this.c.luaByte(i2 + 1);
            int i3 = 1;
            while (true) {
                i++;
                if (i < length2) {
                    if (this.f1602b.luaByte(i) == luaByte2) {
                        i3--;
                        if (i3 == 0) {
                            return i + 1;
                        }
                    } else if (this.f1602b.luaByte(i) == luaByte) {
                        i3++;
                    }
                } else {
                    return -1;
                }
            }
        }
    }
}
