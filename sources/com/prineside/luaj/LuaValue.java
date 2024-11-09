package com.prineside.luaj;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.luaj.Varargs;
import com.prineside.luaj.lib.jse.CoerceJavaToLua;
import com.prineside.luaj.lib.jse.JavaClass;
import com.prineside.luaj.lib.jse.JavaInstance;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.NetworkManager;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

@REGS(arrayLevels = 1, classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/luaj/LuaValue.class */
public abstract class LuaValue extends Varargs {
    public static final int TINT = -2;
    public static final int TNONE = -1;
    public static final int TNIL = 0;
    public static final int TBOOLEAN = 1;
    public static final int TLIGHTUSERDATA = 2;
    public static final int TNUMBER = 3;
    public static final int TSTRING = 4;
    public static final int TTABLE = 5;
    public static final int TFUNCTION = 6;
    public static final int TUSERDATA = 7;
    public static final int TTHREAD = 8;
    public static final int TVALUE = 9;
    public static final LuaValue[] NOVALS;
    public static LuaString ENV;
    public static final LuaString INDEX;
    public static final LuaString NEWINDEX;
    public static final LuaString CALL;
    public static final LuaString MODE;
    public static final LuaString METATABLE;
    public static final LuaString ADD;
    public static final LuaString SUB;
    public static final LuaString DIV;
    public static final LuaString MUL;
    public static final LuaString POW;
    public static final LuaString MOD;
    public static final LuaString UNM;
    public static final LuaString LEN;
    public static final LuaString EQ;
    public static final LuaString LT;
    public static final LuaString LE;
    public static final LuaString TOSTRING;
    public static final LuaString CONCAT;
    public static final LuaString EMPTYSTRING;
    public static final LuaString IPAIRS;
    public static final LuaString PAIRS;

    /* renamed from: b, reason: collision with root package name */
    private static int f1500b;
    public static final LuaValue[] NILS;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1499a = TLog.forClass(LuaValue.class);
    public static final NillableSerializer NILLABLE_SERIALIZER = new NillableSerializer();
    public static final String[] TYPE_NAMES = {"nil", "boolean", "lightuserdata", "number", "string", FlexmarkHtmlConverter.TABLE_NODE, "function", "userdata", "thread", "value"};
    public static final LuaValue NIL = LuaNil.f1482a;
    public static final LuaBoolean TRUE = LuaBoolean.f1476a;
    public static final LuaBoolean FALSE = LuaBoolean.f1477b;
    public static final LuaValue NONE = None.f1502b;
    public static final LuaNumber ZERO = LuaNumber.valueOf(0);
    public static final LuaNumber ONE = LuaNumber.valueOf(1);
    public static final LuaNumber MINUSONE = LuaNumber.valueOf(-1);

    public abstract int type();

    public abstract String typename();

    static {
        LuaValue[] luaValueArr = new LuaValue[0];
        NOVALS = luaValueArr;
        SyncChecker.addSyncShareableObject(luaValueArr);
        ENV = valueOf("_ENV");
        INDEX = valueOf("__index");
        NEWINDEX = valueOf("__newindex");
        CALL = valueOf("__call");
        MODE = valueOf("__mode");
        METATABLE = valueOf("__metatable");
        ADD = valueOf("__add");
        SUB = valueOf("__sub");
        DIV = valueOf("__div");
        MUL = valueOf("__mul");
        POW = valueOf("__pow");
        MOD = valueOf("__mod");
        UNM = valueOf("__unm");
        LEN = valueOf("__len");
        EQ = valueOf("__eq");
        LT = valueOf("__lt");
        LE = valueOf("__le");
        TOSTRING = valueOf("__tostring");
        CONCAT = valueOf("__concat");
        EMPTYSTRING = valueOf("");
        IPAIRS = valueOf("__ipairs");
        PAIRS = valueOf("__pairs");
        f1500b = User32.VK_PLAY;
        NILS = new LuaValue[User32.VK_PLAY];
        for (int i = 0; i < f1500b; i++) {
            NILS[i] = NIL;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaValue$NillableSerializer.class */
    public static class NillableSerializer {

        /* renamed from: a, reason: collision with root package name */
        private static final Array<LuaValue> f1501a = new Array<>(LuaValue.class);

        public void writeClassAndObject(Kryo kryo, Output output, LuaValue luaValue) {
            if ((luaValue instanceof LuaUserdata) && !(luaValue instanceof JavaClass)) {
                LuaUserdata luaUserdata = (LuaUserdata) luaValue;
                if (luaUserdata.m_instance instanceof Class) {
                    throw new IllegalStateException(luaValue + " contains a class instance but is not a JavaClass, instead: " + luaValue.getClass());
                }
                Class<?> cls = luaUserdata.m_instance.getClass();
                if (!((NetworkManager.KryoForState) kryo).hasRegistration(cls)) {
                    if (Game.i.settingsManager.isInDebugDetailedMode()) {
                        String luaValue2 = luaValue.toString();
                        LuaValue.f1499a.i("skipping serialization of " + (luaValue2.length() < 64 ? luaValue2 : luaValue2.substring(0, 63)).replace(SequenceUtils.EOL, SequenceUtils.SPACE) + " - " + cls + " not registered", new Object[0]);
                    }
                    kryo.writeClassAndObject(output, LuaValue.NIL);
                    return;
                }
                kryo.writeClassAndObject(output, luaValue);
                return;
            }
            kryo.writeClassAndObject(output, luaValue);
        }

        public void writeClassAndObject(Kryo kryo, Output output, LuaValue[] luaValueArr) {
            if (luaValueArr == null) {
                kryo.writeClassAndObject(output, null);
                return;
            }
            f1501a.clear();
            f1501a.setSize(luaValueArr.length);
            for (int i = 0; i < luaValueArr.length; i++) {
                if (!(luaValueArr[i] instanceof JavaClass)) {
                    if (luaValueArr[i] instanceof JavaInstance) {
                        JavaInstance javaInstance = (JavaInstance) luaValueArr[i];
                        if (javaInstance.m_instance instanceof Class) {
                            throw new IllegalStateException(luaValueArr[i] + " contains a class instance but is not a JavaClass, instead: " + luaValueArr[i].getClass());
                        }
                        if (!((NetworkManager.KryoForState) kryo).hasRegistration(javaInstance.m_instance.getClass())) {
                            f1501a.set(i, luaValueArr[i]);
                            luaValueArr[i] = LuaValue.NIL;
                        }
                    } else if (luaValueArr[i] != null && !((NetworkManager.KryoForState) kryo).hasRegistration(luaValueArr[i].getClass())) {
                        f1501a.set(i, luaValueArr[i]);
                        luaValueArr[i] = LuaValue.NIL;
                    }
                }
            }
            kryo.writeClassAndObject(output, luaValueArr);
            for (int i2 = 0; i2 < f1501a.size; i2++) {
                LuaValue luaValue = f1501a.items[i2];
                if (luaValue != null) {
                    luaValueArr[i2] = luaValue;
                }
            }
            f1501a.clear();
        }
    }

    public boolean isboolean() {
        return false;
    }

    public boolean isclosure() {
        return false;
    }

    public boolean isfunction() {
        return false;
    }

    public boolean isint() {
        return false;
    }

    public boolean isinttype() {
        return false;
    }

    public boolean islong() {
        return false;
    }

    public boolean isnil() {
        return false;
    }

    public boolean isnumber() {
        return false;
    }

    public boolean isstring() {
        return false;
    }

    public boolean istable() {
        return false;
    }

    public boolean isuserdata() {
        return false;
    }

    public boolean isuserdata(Class cls) {
        return false;
    }

    public boolean toboolean() {
        return true;
    }

    public byte tobyte() {
        return (byte) 0;
    }

    public char tochar() {
        return (char) 0;
    }

    public double todouble() {
        return 0.0d;
    }

    public float tofloat() {
        return 0.0f;
    }

    public int toint() {
        return 0;
    }

    public long tolong() {
        return 0L;
    }

    public short toshort() {
        return (short) 0;
    }

    @Override // com.prineside.luaj.Varargs
    public String tojstring() {
        return typename() + ": " + Integer.toHexString(hashCode());
    }

    public Object touserdata() {
        return null;
    }

    public <T> T touserdata(Class<T> cls) {
        return null;
    }

    @Override // com.prineside.luaj.Varargs
    public String toString() {
        return tojstring();
    }

    public LuaValue tonumber() {
        return NIL;
    }

    public LuaValue tostring() {
        return NIL;
    }

    public boolean optboolean(boolean z) {
        a("boolean");
        return false;
    }

    public LuaClosure optclosure(LuaClosure luaClosure) {
        a("closure");
        return null;
    }

    public double optdouble(double d) {
        a("number");
        return 0.0d;
    }

    public LuaFunction optfunction(LuaFunction luaFunction) {
        a("function");
        return null;
    }

    public int optint(int i) {
        a("int");
        return 0;
    }

    public long optlong(long j) {
        a("long");
        return 0L;
    }

    public LuaNumber optnumber(LuaNumber luaNumber) {
        a("number");
        return null;
    }

    public String optjstring(String str) {
        a("String");
        return null;
    }

    public LuaString optstring(LuaString luaString) {
        a("string");
        return null;
    }

    public LuaTable opttable(LuaTable luaTable) {
        a(FlexmarkHtmlConverter.TABLE_NODE);
        return null;
    }

    public Object optuserdata(Object obj) {
        a("object");
        return null;
    }

    public Object optuserdata(Class cls, Object obj) {
        a(cls.getName());
        return null;
    }

    public LuaValue optvalue(LuaValue luaValue) {
        return this;
    }

    public boolean checkboolean() {
        a("boolean");
        return false;
    }

    public LuaClosure checkclosure() {
        a("closure");
        return null;
    }

    public double checkdouble() {
        a("number");
        return 0.0d;
    }

    public LuaFunction checkfunction() {
        a("function");
        return null;
    }

    public Globals checkglobals() {
        a("globals");
        return null;
    }

    public int checkint() {
        a("int");
        return 0;
    }

    public long checklong() {
        a("long");
        return 0L;
    }

    public LuaNumber checknumber() {
        a("number");
        return null;
    }

    public LuaNumber checknumber(String str) {
        throw new LuaError(str);
    }

    public String checkjstring() {
        a("string");
        return null;
    }

    public LuaString checkstring() {
        a("string");
        return null;
    }

    public LuaTable checktable() {
        a(FlexmarkHtmlConverter.TABLE_NODE);
        return null;
    }

    public Object checkuserdata() {
        a("userdata");
        return null;
    }

    public Object checkuserdata(Class cls) {
        a("userdata");
        return null;
    }

    public LuaValue checknotnil() {
        return this;
    }

    public boolean isvalidkey() {
        return true;
    }

    public static LuaValue error(String str) {
        throw new LuaError(str);
    }

    public static void assert_(boolean z, String str) {
        if (!z) {
            throw new LuaError(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LuaValue a(String str) {
        throw new LuaError("bad argument: " + str + " expected, got " + typename());
    }

    public static LuaValue argerror(int i, String str) {
        throw new LuaError("bad argument #" + i + ": " + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LuaValue b(String str) {
        throw new LuaError(str + " expected, got " + typename());
    }

    private LuaValue c(String str) {
        throw new LuaError("'" + str + "' not implemented for " + typename());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static LuaValue a(String str, String str2) {
        throw new LuaError("illegal operation '" + str + "' for " + str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LuaValue b() {
        throw new LuaError("attempt to perform arithmetic on " + typename());
    }

    private LuaValue d(String str) {
        throw new LuaError("attempt to perform arithmetic '" + str + "' on " + typename());
    }

    private LuaValue e(String str) {
        throw new LuaError("attempt to compare " + typename() + " with " + str);
    }

    public LuaValue get(LuaValue luaValue) {
        return c(this, luaValue);
    }

    public LuaValue get(int i) {
        return get(LuaNumber.valueOf(i));
    }

    public LuaValue get(String str) {
        return get(valueOf(str));
    }

    public void set(LuaValue luaValue, LuaValue luaValue2) {
        a(this, luaValue, luaValue2);
    }

    public void set(int i, LuaValue luaValue) {
        set(LuaNumber.valueOf(i), luaValue);
    }

    public void set(int i, String str) {
        set(i, valueOf(str));
    }

    public void set(String str, LuaValue luaValue) {
        set(valueOf(str), luaValue);
    }

    public void set(String str, double d) {
        set(valueOf(str), valueOf(d));
    }

    public void set(String str, int i) {
        set(valueOf(str), valueOf(i));
    }

    public void set(String str, String str2) {
        set(valueOf(str), valueOf(str2));
    }

    public LuaValue rawget(LuaValue luaValue) {
        return c("rawget");
    }

    public LuaValue rawget(int i) {
        return rawget(valueOf(i));
    }

    public LuaValue rawget(String str) {
        return rawget(valueOf(str));
    }

    public void rawset(LuaValue luaValue, LuaValue luaValue2) {
        c("rawset");
    }

    public void rawset(int i, LuaValue luaValue) {
        rawset(valueOf(i), luaValue);
    }

    public void rawset(int i, String str) {
        rawset(i, valueOf(str));
    }

    public void rawset(String str, LuaValue luaValue) {
        rawset(valueOf(str), luaValue);
    }

    public void rawset(String str, double d) {
        rawset(valueOf(str), valueOf(d));
    }

    public void rawset(String str, int i) {
        rawset(valueOf(str), valueOf(i));
    }

    public void rawset(String str, String str2) {
        rawset(valueOf(str), valueOf(str2));
    }

    public void rawsetlist(int i, Varargs varargs) {
        int narg = varargs.narg();
        for (int i2 = 0; i2 < narg; i2++) {
            rawset(i + i2, varargs.arg(i2 + 1));
        }
    }

    public void presize(int i) {
        b(FlexmarkHtmlConverter.TABLE_NODE);
    }

    public Varargs next(LuaValue luaValue) {
        return b(FlexmarkHtmlConverter.TABLE_NODE);
    }

    public Varargs inext(LuaValue luaValue) {
        return b(FlexmarkHtmlConverter.TABLE_NODE);
    }

    public LuaValue load(LuaValue luaValue) {
        return luaValue.call(EMPTYSTRING, this);
    }

    @Override // com.prineside.luaj.Varargs
    public LuaValue arg(int i) {
        return i == 1 ? this : NIL;
    }

    @Override // com.prineside.luaj.Varargs
    public int narg() {
        return 1;
    }

    @Override // com.prineside.luaj.Varargs
    public LuaValue arg1() {
        return this;
    }

    public LuaValue getmetatable() {
        return null;
    }

    public LuaValue setmetatable(LuaValue luaValue) {
        return a(FlexmarkHtmlConverter.TABLE_NODE);
    }

    public LuaValue call() {
        return a().call(this);
    }

    public LuaValue call(LuaValue luaValue) {
        return a().call(this, luaValue);
    }

    public LuaValue call(String str) {
        return call(valueOf(str));
    }

    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        return a().call(this, luaValue, luaValue2);
    }

    public LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
        return a().invoke(new LuaValue[]{this, luaValue, luaValue2, luaValue3}).arg1();
    }

    public LuaValue method(String str) {
        return get(str).call(this);
    }

    public LuaValue method(LuaValue luaValue) {
        return get(luaValue).call(this);
    }

    public LuaValue method(String str, LuaValue luaValue) {
        return get(str).call(this, luaValue);
    }

    public LuaValue method(LuaValue luaValue, LuaValue luaValue2) {
        return get(luaValue).call(this, luaValue2);
    }

    public LuaValue method(String str, LuaValue luaValue, LuaValue luaValue2) {
        return get(str).call(this, luaValue, luaValue2);
    }

    public LuaValue method(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
        return get(luaValue).call(this, luaValue2, luaValue3);
    }

    public Varargs invoke() {
        return invoke(NONE);
    }

    public Varargs invoke(Varargs varargs) {
        return a().invoke(this, varargs);
    }

    public Varargs invoke(LuaValue[] luaValueArr) {
        return invoke(varargsOf(luaValueArr));
    }

    public Varargs invoke(LuaValue luaValue, Varargs varargs) {
        return invoke(varargsOf(luaValue, varargs));
    }

    public Varargs invoke(LuaValue luaValue, LuaValue luaValue2, Varargs varargs) {
        return invoke(varargsOf(luaValue, luaValue2, varargs));
    }

    public Varargs invokemethod(String str) {
        return get(str).invoke(this);
    }

    public Varargs invokemethod(LuaValue luaValue) {
        return get(luaValue).invoke(this);
    }

    public Varargs invokemethod(String str, Varargs varargs) {
        return get(str).invoke(varargsOf(this, varargs));
    }

    public Varargs invokemethod(LuaValue luaValue, Varargs varargs) {
        return get(luaValue).invoke(varargsOf(this, varargs));
    }

    protected LuaValue a() {
        return a(CALL, "attempt to call ");
    }

    public LuaValue not() {
        return FALSE;
    }

    public LuaValue neg() {
        return a(UNM, "attempt to perform arithmetic on ").call(this);
    }

    public LuaValue len() {
        return a(LEN, "attempt to get length of ").call(this);
    }

    public int length() {
        return len().toint();
    }

    public int rawlen() {
        b("table or string");
        return 0;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public LuaValue eq(LuaValue luaValue) {
        return eq_b(luaValue) ? TRUE : FALSE;
    }

    public boolean eq_b(LuaValue luaValue) {
        return this == luaValue;
    }

    public LuaValue neq(LuaValue luaValue) {
        return eq_b(luaValue) ? FALSE : TRUE;
    }

    public boolean neq_b(LuaValue luaValue) {
        return !eq_b(luaValue);
    }

    public boolean raweq(LuaValue luaValue) {
        return this == luaValue;
    }

    public boolean raweq(LuaUserdata luaUserdata) {
        return false;
    }

    public boolean raweq(LuaString luaString) {
        return false;
    }

    public boolean raweq(double d) {
        return false;
    }

    public boolean raweq(int i) {
        return false;
    }

    public static boolean eqmtcall(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3, LuaValue luaValue4) {
        LuaValue rawget = luaValue2.rawget(EQ);
        return !rawget.isnil() && rawget == luaValue4.rawget(EQ) && rawget.call(luaValue, luaValue3).toboolean();
    }

    public LuaValue add(LuaValue luaValue) {
        return b(ADD, luaValue);
    }

    public LuaValue add(double d) {
        return a(ADD, d);
    }

    public LuaValue add(int i) {
        return add(i);
    }

    public LuaValue sub(LuaValue luaValue) {
        return b(SUB, luaValue);
    }

    public LuaValue sub(double d) {
        return d(FlexmarkHtmlConverter.SUB_NODE);
    }

    public LuaValue sub(int i) {
        return d(FlexmarkHtmlConverter.SUB_NODE);
    }

    public LuaValue subFrom(double d) {
        return a(SUB, d);
    }

    public LuaValue subFrom(int i) {
        return subFrom(i);
    }

    public LuaValue mul(LuaValue luaValue) {
        return b(MUL, luaValue);
    }

    public LuaValue mul(double d) {
        return a(MUL, d);
    }

    public LuaValue mul(int i) {
        return mul(i);
    }

    public LuaValue pow(LuaValue luaValue) {
        return b(POW, luaValue);
    }

    public LuaValue pow(double d) {
        return d("pow");
    }

    public LuaValue pow(int i) {
        return d("pow");
    }

    public LuaValue powWith(double d) {
        return a(POW, d);
    }

    public LuaValue powWith(int i) {
        return powWith(i);
    }

    public LuaValue div(LuaValue luaValue) {
        return b(DIV, luaValue);
    }

    public LuaValue div(double d) {
        return d(FlexmarkHtmlConverter.DIV_NODE);
    }

    public LuaValue div(int i) {
        return d(FlexmarkHtmlConverter.DIV_NODE);
    }

    public LuaValue divInto(double d) {
        return a(DIV, d);
    }

    public LuaValue mod(LuaValue luaValue) {
        return b(MOD, luaValue);
    }

    public LuaValue mod(double d) {
        return d("mod");
    }

    public LuaValue mod(int i) {
        return d("mod");
    }

    public LuaValue modFrom(double d) {
        return a(MOD, d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LuaValue b(LuaValue luaValue, LuaValue luaValue2) {
        LuaValue metatag = metatag(luaValue);
        LuaValue luaValue3 = metatag;
        if (metatag.isnil()) {
            LuaValue metatag2 = luaValue2.metatag(luaValue);
            luaValue3 = metatag2;
            if (metatag2.isnil()) {
                error("attempt to perform arithmetic " + luaValue + " on " + typename() + " and " + luaValue2.typename());
            }
        }
        return luaValue3.call(this, luaValue2);
    }

    private LuaValue a(LuaValue luaValue, double d) {
        LuaValue metatag = metatag(luaValue);
        if (metatag.isnil()) {
            error("attempt to perform arithmetic " + luaValue + " on number and " + typename());
        }
        return metatag.call(valueOf(d), this);
    }

    public LuaValue lt(LuaValue luaValue) {
        return comparemt(LT, luaValue);
    }

    public LuaValue lt(double d) {
        return e("number");
    }

    public LuaValue lt(int i) {
        return e("number");
    }

    public boolean lt_b(LuaValue luaValue) {
        return comparemt(LT, luaValue).toboolean();
    }

    public boolean lt_b(int i) {
        e("number");
        return false;
    }

    public boolean lt_b(double d) {
        e("number");
        return false;
    }

    public LuaValue lteq(LuaValue luaValue) {
        return comparemt(LE, luaValue);
    }

    public LuaValue lteq(double d) {
        return e("number");
    }

    public LuaValue lteq(int i) {
        return e("number");
    }

    public boolean lteq_b(LuaValue luaValue) {
        return comparemt(LE, luaValue).toboolean();
    }

    public boolean lteq_b(int i) {
        e("number");
        return false;
    }

    public boolean lteq_b(double d) {
        e("number");
        return false;
    }

    public LuaValue gt(LuaValue luaValue) {
        return luaValue.comparemt(LE, this);
    }

    public LuaValue gt(double d) {
        return e("number");
    }

    public LuaValue gt(int i) {
        return e("number");
    }

    public boolean gt_b(LuaValue luaValue) {
        return luaValue.comparemt(LE, this).toboolean();
    }

    public boolean gt_b(int i) {
        e("number");
        return false;
    }

    public boolean gt_b(double d) {
        e("number");
        return false;
    }

    public LuaValue gteq(LuaValue luaValue) {
        return luaValue.comparemt(LT, this);
    }

    public LuaValue gteq(double d) {
        return e("number");
    }

    public LuaValue gteq(int i) {
        return valueOf(todouble() >= ((double) i));
    }

    public boolean gteq_b(LuaValue luaValue) {
        return luaValue.comparemt(LT, this).toboolean();
    }

    public boolean gteq_b(int i) {
        e("number");
        return false;
    }

    public boolean gteq_b(double d) {
        e("number");
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0046, code lost:            if (r0.isnil() == false) goto L14;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.prineside.luaj.LuaValue comparemt(com.prineside.luaj.LuaValue r5, com.prineside.luaj.LuaValue r6) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            com.prineside.luaj.LuaValue r0 = r0.metatag(r1)
            r1 = r0
            r7 = r1
            boolean r0 = r0.isnil()
            if (r0 == 0) goto L1a
            r0 = r6
            r1 = r5
            com.prineside.luaj.LuaValue r0 = r0.metatag(r1)
            r1 = r0
            r7 = r1
            boolean r0 = r0.isnil()
            if (r0 != 0) goto L21
        L1a:
            r0 = r7
            r1 = r4
            r2 = r6
            com.prineside.luaj.LuaValue r0 = r0.call(r1, r2)
            return r0
        L21:
            com.prineside.luaj.LuaString r0 = com.prineside.luaj.LuaValue.LE
            r1 = r5
            boolean r0 = r0.raweq(r1)
            if (r0 == 0) goto L53
            r0 = r4
            com.prineside.luaj.LuaString r1 = com.prineside.luaj.LuaValue.LT
            com.prineside.luaj.LuaValue r0 = r0.metatag(r1)
            r1 = r0
            r7 = r1
            boolean r0 = r0.isnil()
            if (r0 == 0) goto L49
            r0 = r6
            com.prineside.luaj.LuaString r1 = com.prineside.luaj.LuaValue.LT
            com.prineside.luaj.LuaValue r0 = r0.metatag(r1)
            r1 = r0
            r7 = r1
            boolean r0 = r0.isnil()
            if (r0 != 0) goto L53
        L49:
            r0 = r7
            r1 = r6
            r2 = r4
            com.prineside.luaj.LuaValue r0 = r0.call(r1, r2)
            com.prineside.luaj.LuaValue r0 = r0.not()
            return r0
        L53:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            java.lang.String r2 = "attempt to compare "
            r1.<init>(r2)
            r1 = r5
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = " on "
            java.lang.StringBuilder r0 = r0.append(r1)
            r1 = r4
            java.lang.String r1 = r1.typename()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = " and "
            java.lang.StringBuilder r0 = r0.append(r1)
            r1 = r6
            java.lang.String r1 = r1.typename()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.prineside.luaj.LuaValue r0 = error(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.LuaValue.comparemt(com.prineside.luaj.LuaValue, com.prineside.luaj.LuaValue):com.prineside.luaj.LuaValue");
    }

    public int strcmp(LuaValue luaValue) {
        error("attempt to compare " + typename());
        return 0;
    }

    public int strcmp(LuaString luaString) {
        error("attempt to compare " + typename());
        return 0;
    }

    public LuaValue concat(LuaValue luaValue) {
        return concatmt(luaValue);
    }

    public LuaValue concatTo(LuaValue luaValue) {
        return luaValue.concatmt(this);
    }

    public LuaValue concatTo(LuaNumber luaNumber) {
        return luaNumber.concatmt(this);
    }

    public LuaValue concatTo(LuaString luaString) {
        return luaString.concatmt(this);
    }

    public Buffer buffer() {
        return new Buffer(this);
    }

    public Buffer concat(Buffer buffer) {
        return buffer.concatTo(this);
    }

    public LuaValue concatmt(LuaValue luaValue) {
        LuaValue metatag = metatag(CONCAT);
        LuaValue luaValue2 = metatag;
        if (metatag.isnil()) {
            LuaValue metatag2 = luaValue.metatag(CONCAT);
            luaValue2 = metatag2;
            if (metatag2.isnil()) {
                error("attempt to concatenate " + typename() + " and " + luaValue.typename());
            }
        }
        return luaValue2.call(this, luaValue);
    }

    public LuaValue and(LuaValue luaValue) {
        return toboolean() ? luaValue : this;
    }

    public LuaValue or(LuaValue luaValue) {
        return toboolean() ? this : luaValue;
    }

    public boolean testfor_b(LuaValue luaValue, LuaValue luaValue2) {
        return luaValue2.gt_b(0) ? lteq_b(luaValue) : gteq_b(luaValue);
    }

    public LuaString strvalue() {
        b("string or number");
        return null;
    }

    public LuaValue strongvalue() {
        return this;
    }

    public static LuaBoolean valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    public static LuaNumber valueOf(int i) {
        return LuaNumber.valueOf(i);
    }

    public static LuaNumber valueOf(double d) {
        return LuaNumber.valueOf(d);
    }

    public static LuaString valueOf(String str) {
        return LuaString.valueOf(str);
    }

    public static LuaString valueOf(byte[] bArr) {
        return LuaString.valueOf(bArr);
    }

    public static LuaString valueOf(byte[] bArr, int i, int i2) {
        return LuaString.valueOf(bArr, i, i2);
    }

    public static LuaTable tableOf() {
        return new LuaTable();
    }

    public static LuaTable tableOf(Varargs varargs, int i) {
        return new LuaTable(varargs, i);
    }

    public static LuaTable tableOf(int i, int i2) {
        return new LuaTable(i, i2);
    }

    public static LuaTable listOf(LuaValue[] luaValueArr) {
        return new LuaTable(null, luaValueArr, null);
    }

    public static LuaTable listOf(LuaValue[] luaValueArr, Varargs varargs) {
        return new LuaTable(null, luaValueArr, varargs);
    }

    public static LuaTable tableOf(LuaValue[] luaValueArr) {
        return new LuaTable(luaValueArr, null, null);
    }

    public static LuaTable tableOf(LuaValue[] luaValueArr, LuaValue[] luaValueArr2) {
        return new LuaTable(luaValueArr, luaValueArr2, null);
    }

    public static LuaTable tableOf(LuaValue[] luaValueArr, LuaValue[] luaValueArr2, Varargs varargs) {
        return new LuaTable(luaValueArr, luaValueArr2, varargs);
    }

    public static LuaUserdata userdataOf(Object obj) {
        return new LuaUserdata(obj);
    }

    public static LuaUserdata userdataOf(Object obj, LuaValue luaValue) {
        return new LuaUserdata(obj, luaValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static LuaValue c(LuaValue luaValue, LuaValue luaValue2) {
        LuaValue luaValue3;
        int i = 0;
        do {
            if (luaValue.istable()) {
                LuaValue rawget = luaValue.rawget(luaValue2);
                if (rawget.isnil()) {
                    LuaValue metatag = luaValue.metatag(INDEX);
                    luaValue3 = metatag;
                    if (metatag.isnil()) {
                    }
                }
                return rawget;
            }
            LuaValue metatag2 = luaValue.metatag(INDEX);
            luaValue3 = metatag2;
            if (metatag2.isnil()) {
                luaValue.f(luaValue2.tojstring());
            }
            if (luaValue3.isfunction()) {
                return luaValue3.call(luaValue, luaValue2);
            }
            luaValue = luaValue3;
            i++;
        } while (i < 100);
        error("loop in gettable");
        return NIL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
        LuaValue luaValue4;
        int i = 0;
        do {
            if (luaValue.istable()) {
                if (luaValue.rawget(luaValue2).isnil()) {
                    LuaValue metatag = luaValue.metatag(NEWINDEX);
                    luaValue4 = metatag;
                    if (metatag.isnil()) {
                    }
                }
                luaValue.rawset(luaValue2, luaValue3);
                return true;
            }
            LuaValue metatag2 = luaValue.metatag(NEWINDEX);
            luaValue4 = metatag2;
            if (metatag2.isnil()) {
                throw new LuaError("table expected for set index ('" + luaValue2 + "') value, got " + luaValue.typename());
            }
            if (luaValue4.isfunction()) {
                luaValue4.call(luaValue, luaValue2, luaValue3);
                return true;
            }
            luaValue = luaValue4;
            i++;
        } while (i < 100);
        error("loop in settable");
        return false;
    }

    public LuaValue metatag(LuaValue luaValue) {
        LuaValue luaValue2 = getmetatable();
        if (luaValue2 == null) {
            return NIL;
        }
        return luaValue2.rawget(luaValue);
    }

    private LuaValue a(LuaValue luaValue, String str) {
        LuaValue metatag = metatag(luaValue);
        if (metatag.isnil()) {
            throw new LuaError(str + "a " + typename() + " value on " + getClass().getSimpleName() + SequenceUtils.SPACE + tojstring());
        }
        return metatag;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Metatable b(LuaValue luaValue) {
        if (luaValue != null && luaValue.istable()) {
            LuaValue rawget = luaValue.rawget(MODE);
            if (rawget.isstring()) {
                String str = rawget.tojstring();
                boolean z = str.indexOf(107) >= 0;
                boolean z2 = str.indexOf(118) >= 0;
                if (z || z2) {
                    return new WeakTable(z, z2, luaValue);
                }
            }
            return (LuaTable) luaValue;
        }
        if (luaValue != null) {
            return new NonTableMetatable(luaValue);
        }
        return null;
    }

    private void f(String str) {
        error("attempt to index ? (a " + typename() + " value) with key '" + str + "'");
    }

    public static LuaNumber cFloat(float f) {
        return LuaNumber.valueOf(f);
    }

    public static LuaNumber cDouble(double d) {
        return LuaNumber.valueOf(d);
    }

    public static LuaNumber cInt(int i) {
        return LuaNumber.valueOf(i);
    }

    public static LuaNumber cNcInt(int i) {
        return LuaNumber.valueOf(i);
    }

    public static LuaNumber cNcFloat(float f) {
        return LuaNumber.valueOf(f);
    }

    public static LuaNumber cNcDouble(double d) {
        return LuaNumber.valueOf(d);
    }

    public static LuaBoolean cBool(boolean z) {
        return z ? TRUE : FALSE;
    }

    public static LuaValue cRegObject(Object obj) {
        return obj == null ? NIL : CoerceJavaToLua.instanceCoercion.coerce(obj);
    }

    public static boolean isRegObject(Class<?> cls) {
        Preconditions.checkNotNull(cls);
        return (cls == String.class || cls == Object.class || cls == Class.class || cls.isAssignableFrom(LuaValue.class)) ? false : true;
    }

    public static LuaValue cObject(Object obj) {
        return CoerceJavaToLua.coerce(obj);
    }

    public static Varargs varargsOf(LuaValue luaValue, LuaValue luaValue2, Varargs varargs) {
        switch (varargs.narg()) {
            case 0:
                return new Varargs.PairVarargs(luaValue, luaValue2);
            default:
                return new Varargs.ArrayPartVarargs(new LuaValue[]{luaValue, luaValue2}, 0, 2, varargs);
        }
    }

    public static Varargs varargsOf(LuaValue luaValue, Varargs varargs) {
        switch (varargs.narg()) {
            case 0:
                return luaValue;
            default:
                return new Varargs.PairVarargs(luaValue, varargs);
        }
    }

    public static Varargs varargsOf(LuaValue[] luaValueArr, int i, int i2, Varargs varargs) {
        switch (i2) {
            case 0:
                return varargs;
            case 1:
                if (varargs.narg() > 0) {
                    return new Varargs.PairVarargs(luaValueArr[i], varargs);
                }
                return luaValueArr[i];
            case 2:
                if (varargs.narg() > 0) {
                    return new Varargs.ArrayPartVarargs(luaValueArr, i, i2, varargs);
                }
                return new Varargs.PairVarargs(luaValueArr[i], luaValueArr[i + 1]);
            default:
                return new Varargs.ArrayPartVarargs(luaValueArr, i, i2, varargs);
        }
    }

    public static Varargs varargsOf(LuaValue[] luaValueArr, int i, int i2) {
        switch (i2) {
            case 0:
                return NONE;
            case 1:
                return luaValueArr[i];
            case 2:
                return new Varargs.PairVarargs(luaValueArr[i], luaValueArr[i + 1]);
            default:
                return new Varargs.ArrayPartVarargs(luaValueArr, i, i2, NONE);
        }
    }

    public static Varargs varargsOf(LuaValue[] luaValueArr, Varargs varargs) {
        switch (luaValueArr.length) {
            case 0:
                return varargs;
            case 1:
                if (varargs.narg() > 0) {
                    return new Varargs.PairVarargs(luaValueArr[0], varargs);
                }
                return luaValueArr[0];
            case 2:
                if (varargs.narg() > 0) {
                    return new Varargs.ArrayVarargs(luaValueArr, varargs);
                }
                return new Varargs.PairVarargs(luaValueArr[0], luaValueArr[1]);
            default:
                return new Varargs.ArrayVarargs(luaValueArr, varargs);
        }
    }

    public static Varargs varargsOf(LuaValue[] luaValueArr) {
        switch (luaValueArr.length) {
            case 0:
                return NONE;
            case 1:
                return luaValueArr[0];
            case 2:
                return new Varargs.PairVarargs(luaValueArr[0], luaValueArr[1]);
            default:
                return new Varargs.ArrayVarargs(luaValueArr, NONE);
        }
    }

    public static Varargs tailcallOf(LuaValue luaValue, Varargs varargs) {
        return new TailcallVarargs(luaValue, varargs);
    }

    public Varargs onInvoke(Varargs varargs) {
        return invoke(varargs);
    }

    public void initupvalue1(LuaValue luaValue) {
    }

    @REGS(serializer = Serializer.class)
    /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaValue$None.class */
    public static final class None extends LuaNil {

        /* renamed from: b, reason: collision with root package name */
        static None f1502b = new None();

        /* loaded from: infinitode-2.jar:com/prineside/luaj/LuaValue$None$Serializer.class */
        public static class Serializer extends SingletonSerializer<None> {
            public Serializer() {
                setImmutable(true);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.prineside.tdi2.serializers.SingletonSerializer
            public None read() {
                return None.f1502b;
            }
        }

        @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public final LuaValue arg(int i) {
            return NIL;
        }

        @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public final int narg() {
            return 0;
        }

        @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public final LuaValue arg1() {
            return NIL;
        }

        @Override // com.prineside.luaj.LuaNil, com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public final String tojstring() {
            return "none";
        }

        @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public final Varargs subargs(int i) {
            return i > 0 ? this : argerror(1, "start must be > 0");
        }

        @Override // com.prineside.luaj.Varargs
        final void a(LuaValue[] luaValueArr, int i, int i2) {
            while (i2 > 0) {
                int i3 = i;
                i++;
                luaValueArr[i3] = NIL;
                i2--;
            }
        }
    }

    @Override // com.prineside.luaj.Varargs
    public Varargs subargs(int i) {
        if (i == 1) {
            return this;
        }
        if (i > 1) {
            return NONE;
        }
        return argerror(1, "start must be > 0");
    }
}
