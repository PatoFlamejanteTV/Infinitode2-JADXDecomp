package com.prineside.luaj.lib;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.Globals;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/IoLib.class */
public abstract class IoLib extends TwoArgFunction {
    private File e = null;
    private File f = null;
    private File g = null;
    private static final LuaValue h = valueOf("stdin");
    private static final LuaValue i = valueOf("stdout");
    private static final LuaValue j = valueOf("stderr");
    private static final LuaValue k = valueOf("file");
    private static final LuaValue l = valueOf("closed file");
    public static final String[] IO_NAMES = {"close", "flush", FlexmarkHtmlConverter.INPUT_NODE, "lines", "open", "output", "popen", "read", "tmpfile", "type", "write"};
    public static final String[] FILE_NAMES = {"close", "flush", "lines", "read", "seek", "setvbuf", "write"};

    /* renamed from: a, reason: collision with root package name */
    LuaTable f1578a;

    /* renamed from: b, reason: collision with root package name */
    protected Globals f1579b;

    protected abstract File d();

    protected abstract File e();

    protected abstract File f();

    protected abstract File a(String str, boolean z, boolean z2);

    protected abstract File g();

    protected abstract File b(String str, String str2);

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/IoLib$File.class */
    public abstract class File extends LuaValue {
        public abstract void write(LuaString luaString);

        public abstract void flush();

        public abstract boolean isstdfile();

        public abstract void close();

        public abstract boolean isclosed();

        public abstract int seek(String str, int i);

        public abstract void setvbuf(String str, int i);

        public abstract int remaining();

        public abstract int peek();

        public abstract int read();

        public abstract int read(byte[] bArr, int i, int i2);

        /* JADX INFO: Access modifiers changed from: protected */
        public File() {
        }

        public boolean eof() {
            try {
                return peek() < 0;
            } catch (EOFException unused) {
                return true;
            }
        }

        @Override // com.prineside.luaj.LuaValue
        public LuaValue get(LuaValue luaValue) {
            return IoLib.this.f1578a.get(luaValue);
        }

        @Override // com.prineside.luaj.LuaValue
        public final int type() {
            return 7;
        }

        @Override // com.prineside.luaj.LuaValue
        public final String typename() {
            return "userdata";
        }

        @Override // com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public String tojstring() {
            return "file: " + Integer.toHexString(hashCode());
        }

        public void finalize() {
            if (!isclosed()) {
                try {
                    close();
                } catch (IOException unused) {
                }
            }
        }
    }

    @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        this.f1579b = luaValue2.checkglobals();
        LuaTable luaTable = new LuaTable();
        a(luaTable, IoLibV.class, IO_NAMES);
        this.f1578a = new LuaTable();
        a(this.f1578a, IoLibV.class, FILE_NAMES, 11);
        LuaTable luaTable2 = new LuaTable();
        a(luaTable2, IoLibV.class, new String[]{"__index"}, 18);
        luaTable.setmetatable(luaTable2);
        a(luaTable);
        a(this.f1578a);
        a(luaTable2);
        luaValue2.set("io", luaTable);
        if (!luaValue2.get("package").isnil()) {
            luaValue2.get("package").get("loaded").set("io", luaTable);
        }
        return luaTable;
    }

    private void a(LuaTable luaTable) {
        for (LuaValue luaValue : luaTable.keys()) {
            ((IoLibV) luaTable.get(luaValue)).iolib = this;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/IoLib$IoLibV.class */
    public static final class IoLibV extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private File f1581a;
        public IoLib iolib;

        /* renamed from: b, reason: collision with root package name */
        private boolean f1582b;
        private Varargs e;

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
        }

        public IoLibV() {
        }

        public IoLibV(File file, String str, int i, IoLib ioLib, boolean z, Varargs varargs) {
            this(file, str, i, ioLib);
            this.f1582b = z;
            this.e = varargs.dealias();
        }

        public IoLibV(File file, String str, int i, IoLib ioLib) {
            this.f1581a = file;
            this.d = str;
            this.c = i;
            this.iolib = ioLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            try {
                switch (this.c) {
                    case 0:
                        return this.iolib._io_close(varargs.arg1());
                    case 1:
                        return this.iolib._io_flush();
                    case 2:
                        return this.iolib._io_input(varargs.arg1());
                    case 3:
                        return this.iolib._io_lines(varargs);
                    case 4:
                        return this.iolib._io_open(varargs.checkjstring(1), varargs.optjstring(2, "r"));
                    case 5:
                        return this.iolib._io_output(varargs.arg1());
                    case 6:
                        return this.iolib._io_popen(varargs.checkjstring(1), varargs.optjstring(2, "r"));
                    case 7:
                        return this.iolib._io_read(varargs);
                    case 8:
                        return this.iolib._io_tmpfile();
                    case 9:
                        return this.iolib._io_type(varargs.arg1());
                    case 10:
                        return this.iolib._io_write(varargs);
                    case 11:
                        return this.iolib._file_close(varargs.arg1());
                    case 12:
                        return this.iolib._file_flush(varargs.arg1());
                    case 13:
                        return this.iolib._file_lines(varargs);
                    case 14:
                        return this.iolib._file_read(varargs.arg1(), varargs.subargs(2));
                    case 15:
                        return this.iolib._file_seek(varargs.arg1(), varargs.optjstring(2, "cur"), varargs.optint(3, 0));
                    case 16:
                        return this.iolib._file_setvbuf(varargs.arg1(), varargs.checkjstring(2), varargs.optint(3, 8192));
                    case 17:
                        return this.iolib._file_write(varargs.arg1(), varargs.subargs(2));
                    case 18:
                        return this.iolib._io_index(varargs.arg(2));
                    case 19:
                        return this.iolib._lines_iter(this.f1581a, this.f1582b, this.e);
                    default:
                        return NONE;
                }
            } catch (IOException e) {
                if (this.c == 19) {
                    String message = e.getMessage();
                    error(message != null ? message : e.toString());
                }
                return IoLib.a(e);
            }
        }
    }

    private File h() {
        if (this.e != null) {
            return this.e;
        }
        File a2 = a(0, "-", "r");
        this.e = a2;
        return a2;
    }

    public Varargs _io_flush() {
        b(i());
        this.f.flush();
        return LuaValue.TRUE;
    }

    public Varargs _io_tmpfile() {
        return g();
    }

    public Varargs _io_close(LuaValue luaValue) {
        File i2 = luaValue.isnil() ? i() : a(luaValue);
        b(i2);
        return a(i2);
    }

    public Varargs _io_input(LuaValue luaValue) {
        File a2;
        if (luaValue.isnil()) {
            a2 = h();
        } else {
            a2 = luaValue.isstring() ? a(3, luaValue.checkjstring(), "r") : a(luaValue);
        }
        this.e = a2;
        return this.e;
    }

    public Varargs _io_output(LuaValue luaValue) {
        File a2;
        if (luaValue.isnil()) {
            a2 = i();
        } else {
            a2 = luaValue.isstring() ? a(3, luaValue.checkjstring(), "w") : a(luaValue);
        }
        this.f = a2;
        return this.f;
    }

    public Varargs _io_type(LuaValue luaValue) {
        File c = c(luaValue);
        if (c != null) {
            return c.isclosed() ? l : k;
        }
        return NIL;
    }

    public Varargs _io_popen(String str, String str2) {
        if (!"r".equals(str2) && !"w".equals(str2)) {
            argerror(2, "invalid value: '" + str2 + "'; must be one of 'r' or 'w'");
        }
        return b(str, str2);
    }

    public Varargs _io_open(String str, String str2) {
        return b(3, str, str2);
    }

    public Varargs _io_lines(Varargs varargs) {
        String optjstring = varargs.optjstring(1, null);
        File h2 = optjstring == null ? h() : a(3, optjstring, "r");
        File file = h2;
        b(h2);
        return a(file, optjstring != null, varargs.subargs(2));
    }

    public Varargs _io_read(Varargs varargs) {
        b(h());
        return b(this.e, varargs);
    }

    public Varargs _io_write(Varargs varargs) {
        b(i());
        return a(this.f, varargs);
    }

    public Varargs _file_close(LuaValue luaValue) {
        return a(a(luaValue));
    }

    public Varargs _file_flush(LuaValue luaValue) {
        a(luaValue).flush();
        return LuaValue.TRUE;
    }

    public Varargs _file_setvbuf(LuaValue luaValue, String str, int i2) {
        if (!"no".equals(str) && !"full".equals(str) && !"line".equals(str)) {
            argerror(1, "invalid value: '" + str + "'; must be one of 'no', 'full' or 'line'");
        }
        a(luaValue).setvbuf(str, i2);
        return LuaValue.TRUE;
    }

    public Varargs _file_lines(Varargs varargs) {
        return a(a(varargs.arg1()), false, varargs.subargs(2));
    }

    public Varargs _file_read(LuaValue luaValue, Varargs varargs) {
        return b(a(luaValue), varargs);
    }

    public Varargs _file_seek(LuaValue luaValue, String str, int i2) {
        if (!"set".equals(str) && !"end".equals(str) && !"cur".equals(str)) {
            argerror(1, "invalid value: '" + str + "'; must be one of 'set', 'cur' or 'end'");
        }
        return valueOf(a(luaValue).seek(str, i2));
    }

    public Varargs _file_write(LuaValue luaValue, Varargs varargs) {
        return a(a(luaValue), varargs);
    }

    public Varargs _io_index(LuaValue luaValue) {
        return luaValue.equals(i) ? i() : luaValue.equals(h) ? h() : luaValue.equals(j) ? j() : NIL;
    }

    public Varargs _lines_iter(LuaValue luaValue, boolean z, Varargs varargs) {
        File c = c(luaValue);
        if (c == null) {
            argerror(1, "not a file: " + luaValue);
        }
        if (c.isclosed()) {
            error("file is already closed");
        }
        Varargs b2 = b(c, varargs);
        if (z && b2.isnil(1) && c.eof()) {
            c.close();
        }
        return b2;
    }

    private File i() {
        if (this.f != null) {
            return this.f;
        }
        File a2 = a(1, "-", "w");
        this.f = a2;
        return a2;
    }

    private File j() {
        if (this.g != null) {
            return this.g;
        }
        File a2 = a(2, "-", "w");
        this.g = a2;
        return a2;
    }

    private File a(int i2, String str, String str2) {
        try {
            return b(i2, str, str2);
        } catch (Exception e) {
            error("io error: " + e.getMessage());
            return null;
        }
    }

    private static Varargs a(File file) {
        if (file.isstdfile()) {
            return c("cannot close standard file");
        }
        file.close();
        return k();
    }

    private static Varargs k() {
        return LuaValue.TRUE;
    }

    static Varargs a(Exception exc) {
        String message = exc.getMessage();
        return c("io error: " + (message != null ? message : exc.toString()));
    }

    private static Varargs c(String str) {
        return varargsOf(NIL, valueOf(str));
    }

    private Varargs a(File file, boolean z, Varargs varargs) {
        try {
            return new IoLibV(file, "lnext", 19, this, z, varargs);
        } catch (Exception e) {
            return error("lines: " + e);
        }
    }

    private static Varargs a(File file, Varargs varargs) {
        int narg = varargs.narg();
        for (int i2 = 1; i2 <= narg; i2++) {
            file.write(varargs.checkstring(i2));
        }
        return file;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0029. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x007e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00e7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[LOOP:0: B:7:0x0017->B:21:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.prineside.luaj.Varargs b(com.prineside.luaj.lib.IoLib.File r5, com.prineside.luaj.Varargs r6) {
        /*
            r0 = r6
            int r0 = r0.narg()
            r1 = r0
            r8 = r1
            if (r0 != 0) goto Lf
            r0 = r5
            r1 = 0
            com.prineside.luaj.LuaValue r0 = freadline(r0, r1)
            return r0
        Lf:
            r0 = r8
            com.prineside.luaj.LuaValue[] r0 = new com.prineside.luaj.LuaValue[r0]
            r9 = r0
            r0 = 0
            r7 = r0
        L17:
            r0 = r7
            r1 = r8
            if (r0 >= r1) goto Le7
            r0 = r6
            r1 = r7
            r2 = 1
            int r1 = r1 + r2
            com.prineside.luaj.LuaValue r0 = r0.arg(r1)
            r1 = r0
            r10 = r1
            int r0 = r0.type()
            switch(r0) {
                case 3: goto L44;
                case 4: goto L52;
                default: goto Lce;
            }
        L44:
            r0 = r5
            r1 = r10
            int r1 = r1.toint()
            com.prineside.luaj.LuaValue r0 = freadbytes(r0, r1)
            r10 = r0
            goto Ld7
        L52:
            r0 = r10
            com.prineside.luaj.LuaString r0 = r0.checkstring()
            r1 = r0
            r10 = r1
            int r0 = r0.m_length
            r1 = 2
            if (r0 < r1) goto Lce
            r0 = r10
            byte[] r0 = r0.m_bytes
            r1 = r10
            int r1 = r1.m_offset
            r0 = r0[r1]
            r1 = 42
            if (r0 != r1) goto Lce
            r0 = r10
            byte[] r0 = r0.m_bytes
            r1 = r10
            int r1 = r1.m_offset
            r2 = 1
            int r1 = r1 + r2
            r0 = r0[r1]
            switch(r0) {
                case 76: goto Lbb;
                case 97: goto Lc5;
                case 108: goto Lb1;
                case 110: goto La8;
                default: goto Lce;
            }
        La8:
            r0 = r5
            com.prineside.luaj.LuaValue r0 = freadnumber(r0)
            r10 = r0
            goto Ld7
        Lb1:
            r0 = r5
            r1 = 0
            com.prineside.luaj.LuaValue r0 = freadline(r0, r1)
            r10 = r0
            goto Ld7
        Lbb:
            r0 = r5
            r1 = 1
            com.prineside.luaj.LuaValue r0 = freadline(r0, r1)
            r10 = r0
            goto Ld7
        Lc5:
            r0 = r5
            com.prineside.luaj.LuaValue r0 = freadall(r0)
            r10 = r0
            goto Ld7
        Lce:
            r0 = r7
            r1 = 1
            int r0 = r0 + r1
            java.lang.String r1 = "(invalid format)"
            com.prineside.luaj.LuaValue r0 = argerror(r0, r1)
            return r0
        Ld7:
            r0 = r9
            r1 = r7
            int r7 = r7 + 1
            r2 = r10
            r3 = r2; r2 = r1; r1 = r0; r0 = r3; 
            r1[r2] = r3
            boolean r0 = r0.isnil()
            if (r0 == 0) goto L17
        Le7:
            r0 = r7
            if (r0 != 0) goto Lef
            com.prineside.luaj.LuaValue r0 = com.prineside.luaj.lib.IoLib.NIL
            return r0
        Lef:
            r0 = r9
            r1 = 0
            r2 = r7
            com.prineside.luaj.Varargs r0 = varargsOf(r0, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.lib.IoLib.b(com.prineside.luaj.lib.IoLib$File, com.prineside.luaj.Varargs):com.prineside.luaj.Varargs");
    }

    private static File a(LuaValue luaValue) {
        File c = c(luaValue);
        if (c == null) {
            argerror(1, "file");
        }
        b(c);
        return c;
    }

    private static File c(LuaValue luaValue) {
        if (luaValue instanceof File) {
            return (File) luaValue;
        }
        return null;
    }

    private static File b(File file) {
        if (file.isclosed()) {
            error("attempt to use a closed file");
        }
        return file;
    }

    private File b(int i2, String str, String str2) {
        int length = str2.length();
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str2.charAt(i3);
            if ((i3 != 0 || "rwa".indexOf(charAt) < 0) && ((i3 != 1 || charAt != '+') && (i3 <= 0 || charAt != 'b'))) {
                length = -1;
                break;
            }
        }
        if (length <= 0) {
            argerror(2, "invalid mode: '" + str2 + "'");
        }
        switch (i2) {
            case 0:
                return d();
            case 1:
                return e();
            case 2:
                return f();
            default:
                boolean startsWith = str2.startsWith("r");
                boolean startsWith2 = str2.startsWith(FlexmarkHtmlConverter.A_NODE);
                str2.indexOf(43);
                str2.endsWith(FlexmarkHtmlConverter.B_NODE);
                return a(str, startsWith, startsWith2);
        }
    }

    public static LuaValue freadbytes(File file, int i2) {
        if (i2 == 0) {
            return file.eof() ? NIL : EMPTYSTRING;
        }
        byte[] bArr = new byte[i2];
        int read = file.read(bArr, 0, i2);
        if (read < 0) {
            return NIL;
        }
        return LuaString.valueUsing(bArr, 0, read);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0016. Please report as an issue. */
    public static LuaValue freaduntil(File file, boolean z, boolean z2) {
        int i2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            if (!z) {
                while (true) {
                    int read = file.read();
                    i2 = read;
                    if (read < 0) {
                        break;
                    }
                    byteArrayOutputStream.write(i2);
                }
            } else {
                while (true) {
                    int read2 = file.read();
                    i2 = read2;
                    if (read2 >= 0) {
                        switch (i2) {
                            case 10:
                                if (z2) {
                                    byteArrayOutputStream.write(i2);
                                    break;
                                }
                                break;
                            case 13:
                                if (z2) {
                                    byteArrayOutputStream.write(i2);
                                }
                            default:
                                byteArrayOutputStream.write(i2);
                        }
                    }
                }
            }
        } catch (EOFException unused) {
            i2 = -1;
        }
        if (i2 < 0 && byteArrayOutputStream.size() == 0) {
            return NIL;
        }
        return LuaString.valueUsing(byteArrayOutputStream.toByteArray());
    }

    public static LuaValue freadline(File file, boolean z) {
        return freaduntil(file, true, z);
    }

    public static LuaValue freadall(File file) {
        int remaining = file.remaining();
        if (remaining >= 0) {
            return remaining == 0 ? EMPTYSTRING : freadbytes(file, remaining);
        }
        return freaduntil(file, false, false);
    }

    public static LuaValue freadnumber(File file) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(file, " \t\r\n", (ByteArrayOutputStream) null);
        a(file, "-+", byteArrayOutputStream);
        a(file, "0123456789", byteArrayOutputStream);
        a(file, ".", byteArrayOutputStream);
        a(file, "0123456789", byteArrayOutputStream);
        String byteArrayOutputStream2 = byteArrayOutputStream.toString();
        return byteArrayOutputStream2.length() > 0 ? valueOf(Double.parseDouble(byteArrayOutputStream2)) : NIL;
    }

    private static void a(File file, String str, ByteArrayOutputStream byteArrayOutputStream) {
        while (true) {
            int peek = file.peek();
            if (str.indexOf(peek) < 0) {
                return;
            }
            file.read();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.write(peek);
            }
        }
    }
}
