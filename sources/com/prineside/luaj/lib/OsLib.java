package com.prineside.luaj.lib;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.Buffer;
import com.prineside.luaj.Globals;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.lwjgl.system.macosx.CoreGraphics;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/OsLib.class */
public class OsLib extends TwoArgFunction {
    public static final String TMP_PREFIX = ".luaj";
    public static final String TMP_SUFFIX = "tmp";
    private static final long e;
    private static long f;
    private Globals g;
    private static final String[] h;
    private static final String[] i;
    private static final String[] j;
    private static final String[] k;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1586a = TLog.forClass(OsLib.class);

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f1587b = {"clock", "date", "difftime", "execute", "exit", "getenv", "remove", "rename", "setlocale", "time", "tmpname"};

    static {
        long currentTimeMillis = System.currentTimeMillis();
        e = currentTimeMillis;
        f = currentTimeMillis;
        h = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        i = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        j = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        k = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.g);
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.g = (Globals) kryo.readClassAndObject(input);
    }

    @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        this.g = luaValue2.checkglobals();
        LuaTable luaTable = new LuaTable();
        for (int i2 = 0; i2 < f1587b.length; i2++) {
            luaTable.set(f1587b[i2], new OsLibFunc(this, i2, f1587b[i2]));
        }
        luaValue2.set("os", luaTable);
        if (!luaValue2.get("package").isnil()) {
            luaValue2.get("package").get("loaded").set("os", luaTable);
        }
        return luaTable;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/OsLib$OsLibFunc.class */
    public static class OsLibFunc extends VarArgFunction {

        /* renamed from: a, reason: collision with root package name */
        private OsLib f1588a;

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            super.write(kryo, output);
            kryo.writeClassAndObject(output, this.f1588a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            super.read(kryo, input);
            this.f1588a = (OsLib) kryo.readClassAndObject(input);
        }

        private OsLibFunc() {
        }

        public OsLibFunc(OsLib osLib, int i, String str) {
            this.c = i;
            this.d = str;
            this.f1588a = osLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            try {
                switch (this.c) {
                    case 0:
                        return valueOf(OsLib.d());
                    case 1:
                        String optjstring = varargs.optjstring(1, "%c");
                        double a2 = varargs.isnumber(2) ? varargs.todouble(2) : OsLib.a((LuaTable) null);
                        if (!optjstring.equals("*t")) {
                            return valueOf(this.f1588a.date(optjstring, a2 == -1.0d ? OsLib.a((LuaTable) null) : a2));
                        }
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date((long) (a2 * 1000.0d)));
                        LuaTable tableOf = LuaValue.tableOf();
                        tableOf.set("year", LuaValue.valueOf(calendar.get(1)));
                        tableOf.set("month", LuaValue.valueOf(calendar.get(2) + 1));
                        tableOf.set("day", LuaValue.valueOf(calendar.get(5)));
                        tableOf.set("hour", LuaValue.valueOf(calendar.get(11)));
                        tableOf.set("min", LuaValue.valueOf(calendar.get(12)));
                        tableOf.set("sec", LuaValue.valueOf(calendar.get(13)));
                        tableOf.set("wday", LuaValue.valueOf(calendar.get(7)));
                        tableOf.set("yday", LuaValue.valueOf(calendar.get(6)));
                        tableOf.set("isdst", LuaValue.valueOf(this.f1588a.c(calendar)));
                        return tableOf;
                    case 2:
                        return valueOf(OsLib.a(varargs.checkdouble(1), varargs.checkdouble(2)));
                    case 3:
                        varargs.optjstring(1, null);
                        return OsLib.e();
                    case 4:
                        OsLib.a(varargs.optint(1, 0));
                        return NONE;
                    case 5:
                        String c = this.f1588a.c(varargs.checkjstring(1));
                        return c != null ? valueOf(c) : NIL;
                    case 6:
                        OsLib osLib = this.f1588a;
                        varargs.checkjstring(1);
                        osLib.f();
                        return LuaValue.TRUE;
                    case 7:
                        OsLib osLib2 = this.f1588a;
                        varargs.checkjstring(1);
                        varargs.checkjstring(2);
                        osLib2.g();
                        return LuaValue.TRUE;
                    case 8:
                        varargs.optjstring(1, null);
                        varargs.optjstring(2, "all");
                        return valueOf(OsLib.h());
                    case 9:
                        return valueOf(OsLib.a(varargs.opttable(1, null)));
                    case 10:
                        return valueOf(this.f1588a.i());
                    default:
                        return NONE;
                }
            } catch (IOException e) {
                return varargsOf(NIL, valueOf(e.getMessage()));
            }
        }
    }

    protected static double d() {
        return (System.currentTimeMillis() - e) / 1000.0d;
    }

    protected static double a(double d, double d2) {
        return d - d2;
    }

    public String date(String str, double d) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date((long) (d * 1000.0d)));
        if (str.startsWith("!")) {
            d -= b(calendar);
            calendar.setTime(new Date((long) (d * 1000.0d)));
            str = str.substring(1);
        }
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        Buffer buffer = new Buffer(length);
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2;
            i2++;
            byte b2 = bytes[i3];
            switch (b2) {
                case 10:
                    buffer.append(SequenceUtils.EOL);
                    break;
                case 37:
                    if (i2 >= length) {
                        break;
                    } else {
                        i2++;
                        byte b3 = bytes[i2];
                        switch (b3) {
                            case 37:
                                buffer.append((byte) 37);
                                break;
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
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
                            case 58:
                            case 59:
                            case 60:
                            case 61:
                            case 62:
                            case 63:
                            case 64:
                            case 67:
                            case 68:
                            case 69:
                            case 70:
                            case 71:
                            case 74:
                            case 75:
                            case 76:
                            case 78:
                            case 79:
                            case 80:
                            case 81:
                            case 82:
                            case 84:
                            case 86:
                            case 90:
                            case 91:
                            case 92:
                            case 93:
                            case 94:
                            case 95:
                            case 96:
                            case 101:
                            case 102:
                            case 103:
                            case 104:
                            case 105:
                            case 107:
                            case 108:
                            case 110:
                            case 111:
                            case 113:
                            case 114:
                            case 115:
                            case 116:
                            case 117:
                            case 118:
                            default:
                                LuaValue.argerror(1, "invalid conversion specifier '%" + ((int) b3) + "'");
                                break;
                            case 65:
                                buffer.append(i[calendar.get(7) - 1]);
                                break;
                            case 66:
                                buffer.append(k[calendar.get(2)]);
                                break;
                            case 72:
                                buffer.append(String.valueOf(100 + calendar.get(11)).substring(1));
                                break;
                            case 73:
                                buffer.append(String.valueOf(100 + (calendar.get(11) % 12)).substring(1));
                                break;
                            case 77:
                                buffer.append(String.valueOf(100 + calendar.get(12)).substring(1));
                                break;
                            case 83:
                                buffer.append(String.valueOf(100 + calendar.get(13)).substring(1));
                                break;
                            case 85:
                                buffer.append(String.valueOf(a(calendar, 0)));
                                break;
                            case 87:
                                buffer.append(String.valueOf(a(calendar, 1)));
                                break;
                            case 88:
                                buffer.append(date("%H:%M:%S", d));
                                break;
                            case 89:
                                buffer.append(String.valueOf(calendar.get(1)));
                                break;
                            case 97:
                                buffer.append(h[calendar.get(7) - 1]);
                                break;
                            case 98:
                                buffer.append(j[calendar.get(2)]);
                                break;
                            case 99:
                                buffer.append(date("%a %b %d %H:%M:%S %Y", d));
                                break;
                            case 100:
                                buffer.append(String.valueOf(100 + calendar.get(5)).substring(1));
                                break;
                            case 106:
                                buffer.append(String.valueOf(((int) ((calendar.getTime().getTime() - a(calendar).getTime().getTime()) / 86400000)) + CoreGraphics.kCGErrorIllegalArgument).substring(1));
                                break;
                            case 109:
                                buffer.append(String.valueOf(101 + calendar.get(2)).substring(1));
                                break;
                            case 112:
                                buffer.append(calendar.get(11) < 12 ? "AM" : "PM");
                                break;
                            case 119:
                                buffer.append(String.valueOf((calendar.get(7) + 6) % 7));
                                break;
                            case 120:
                                buffer.append(date("%m/%d/%y", d));
                                break;
                            case 121:
                                buffer.append(String.valueOf(calendar.get(1)).substring(2));
                                break;
                            case 122:
                                int b4 = b(calendar) / 60;
                                int abs = Math.abs(b4);
                                buffer.append((b4 >= 0 ? "+" : "-") + String.valueOf(100 + (abs / 60)).substring(1) + String.valueOf(100 + (abs % 60)).substring(1));
                                break;
                        }
                    }
                default:
                    buffer.append(b2);
                    break;
            }
        }
        return buffer.tojstring();
    }

    private static Calendar a(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(calendar.getTime());
        calendar2.set(2, 0);
        calendar2.set(5, 1);
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        return calendar2;
    }

    private int a(Calendar calendar, int i2) {
        Calendar a2 = a(calendar);
        a2.set(5, 1 + (((i2 + 8) - a2.get(7)) % 7));
        if (a2.after(calendar)) {
            a2.set(1, a2.get(1) - 1);
            a2.set(5, 1 + (((i2 + 8) - a2.get(7)) % 7));
        }
        return 1 + ((int) ((calendar.getTime().getTime() - a2.getTime().getTime()) / 604800000));
    }

    private static int b(Calendar calendar) {
        return calendar.getTimeZone().getOffset(1, calendar.get(1), calendar.get(2), calendar.get(5), calendar.get(7), (((calendar.get(11) * 3600) + (calendar.get(12) * 60)) + calendar.get(13)) * 1000) / 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(Calendar calendar) {
        return b(calendar) != calendar.getTimeZone().getRawOffset() / 1000;
    }

    protected static Varargs e() {
        f1586a.e("os.execute is not allowed", new Object[0]);
        return LuaValue.varargsOf(NIL, valueOf("exit"), ONE);
    }

    protected static void a(int i2) {
        f1586a.i("os.exit(" + i2 + ") called by the script", new Object[0]);
        Game.exit();
    }

    protected String c(String str) {
        return System.getProperty(str);
    }

    protected void f() {
        throw new IOException("not implemented");
    }

    protected void g() {
        throw new IOException("not implemented");
    }

    protected static String h() {
        f1586a.e("os.setlocale is not available - use LocaleManager if you really need to change the game's locale", new Object[0]);
        return "C";
    }

    protected static double a(LuaTable luaTable) {
        Date time;
        if (luaTable == null) {
            time = new Date();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1, luaTable.get("year").checkint());
            calendar.set(2, luaTable.get("month").checkint() - 1);
            calendar.set(5, luaTable.get("day").checkint());
            calendar.set(11, luaTable.get("hour").optint(12));
            calendar.set(12, luaTable.get("min").optint(0));
            calendar.set(13, luaTable.get("sec").optint(0));
            calendar.set(14, 0);
            time = calendar.getTime();
        }
        return time.getTime() / 1000.0d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String i() {
        String sb;
        synchronized (OsLib.class) {
            StringBuilder sb2 = new StringBuilder(TMP_PREFIX);
            long j2 = f;
            f = j2 + 1;
            sb = sb2.append(j2).append(TMP_SUFFIX).toString();
        }
        return sb;
    }
}
