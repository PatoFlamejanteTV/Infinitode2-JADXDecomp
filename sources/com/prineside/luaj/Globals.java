package com.prineside.luaj;

import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.LoadState;
import com.prineside.luaj.compiler.LuaC;
import com.prineside.luaj.debug.CallStack;
import com.prineside.luaj.lib.BaseLib;
import com.prineside.luaj.lib.DebugLib;
import com.prineside.luaj.lib.PackageLib;
import com.prineside.luaj.lib.ResourceFinder;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/Globals.class */
public final class Globals extends LuaTable {
    public ResourceFinder finder;
    public BaseLib baselib;
    public PackageLib package_;

    /* renamed from: a, reason: collision with root package name */
    private DebugLib f1468a;
    public Loader loader;
    public Compiler compiler;

    @NAGS
    public Undumper undumper;

    @NAGS
    public InputStream STDIN = null;

    @NAGS
    public PrintStream STDOUT = System.out;

    @NAGS
    public PrintStream STDERR = System.err;

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private final ThreadLocal<CallStack> f1469b = new ThreadLocal<>();

    @NAGS
    private final ThreadLocal<LuaValue> c = new ThreadLocal<>();

    /* loaded from: infinitode-2.jar:com/prineside/luaj/Globals$Compiler.class */
    public interface Compiler {
        Prototype compile(InputStream inputStream, String str);
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/Globals$Loader.class */
    public interface Loader {
        LuaFunction load(Prototype prototype, String str, LuaValue luaValue);
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/Globals$Undumper.class */
    public interface Undumper {
        Prototype undump(InputStream inputStream, String str);
    }

    @Override // com.prineside.luaj.LuaValue
    public final Globals checkglobals() {
        return this;
    }

    @Override // com.prineside.luaj.LuaTable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.finder);
        kryo.writeClassAndObject(output, this.baselib);
        kryo.writeClassAndObject(output, this.package_);
        kryo.writeClassAndObject(output, this.f1468a);
    }

    @Override // com.prineside.luaj.LuaTable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.finder = (ResourceFinder) kryo.readClassAndObject(input);
        this.baselib = (BaseLib) kryo.readClassAndObject(input);
        this.package_ = (PackageLib) kryo.readClassAndObject(input);
        this.f1468a = (DebugLib) kryo.readClassAndObject(input);
        LuaC luaC = new LuaC();
        this.loader = luaC;
        this.compiler = luaC;
        this.undumper = new LoadState.GlobalsUndumper();
    }

    public final void setDebugLib(@Null DebugLib debugLib) {
        this.f1468a = debugLib;
    }

    @Null
    public final DebugLib getDebugLib() {
        return this.f1468a;
    }

    public final CallStack getCallstack() {
        CallStack callStack = this.f1469b.get();
        CallStack callStack2 = callStack;
        if (callStack == null) {
            callStack2 = new CallStack();
            this.f1469b.set(callStack2);
        }
        return callStack2;
    }

    @Null
    public final LuaValue getErrorFunc() {
        return this.c.get();
    }

    public final void setErrorFunc(@Null LuaValue luaValue) {
        this.c.set(luaValue);
    }

    public final LuaValue loadfile(String str) {
        try {
            return load(this.finder.findResource(str), "@" + str, "bt", this);
        } catch (Exception e) {
            return error("load " + str + ": " + e);
        }
    }

    public final LuaValue load(String str, String str2) {
        return load(new StrReader(str), str2);
    }

    public final LuaValue load(String str) {
        return load(new StrReader(str), str);
    }

    public final LuaValue load(String str, String str2, LuaTable luaTable) {
        return load(new StrReader(str), str2, luaTable);
    }

    public final LuaValue load(Reader reader, String str) {
        return load(new UTF8Stream(reader), str, "t", this);
    }

    public final LuaValue load(Reader reader, String str, LuaTable luaTable) {
        return load(new UTF8Stream(reader), str, "t", luaTable);
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Throwable, com.prineside.luaj.LuaValue, com.prineside.luaj.LuaFunction] */
    public final LuaValue load(InputStream inputStream, String str, String str2, LuaValue luaValue) {
        ?? load;
        try {
            load = this.loader.load(loadPrototype(inputStream, str, str2), str, luaValue);
            return load;
        } catch (LuaError e) {
            throw load;
        } catch (Exception e2) {
            return error("load " + str + ": " + e2);
        }
    }

    public final Prototype loadPrototype(InputStream inputStream, String str, String str2) {
        if (str2.indexOf(98) >= 0) {
            if (this.undumper == null) {
                error("No undumper.");
            }
            if (!inputStream.markSupported()) {
                inputStream = new BufferedStream(inputStream);
            }
            inputStream.mark(4);
            Prototype undump = this.undumper.undump(inputStream, str);
            if (undump != null) {
                return undump;
            }
            inputStream.reset();
        }
        if (str2.indexOf(116) >= 0) {
            return compilePrototype(inputStream, str);
        }
        error("Failed to load prototype " + str + " using mode '" + str2 + "'");
        return null;
    }

    public final Prototype compilePrototype(Reader reader, String str) {
        return compilePrototype(new UTF8Stream(reader), str);
    }

    public final Prototype compilePrototype(InputStream inputStream, String str) {
        if (this.compiler == null) {
            error("No compiler.");
        }
        return this.compiler.compile(inputStream, str);
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/Globals$StrReader.class */
    static class StrReader extends Reader {

        /* renamed from: a, reason: collision with root package name */
        private String f1472a;

        /* renamed from: b, reason: collision with root package name */
        private int f1473b = 0;
        private int c;

        StrReader(String str) {
            this.f1472a = str;
            this.c = str.length();
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f1473b = this.c;
        }

        @Override // java.io.Reader
        public int read() {
            if (this.f1473b >= this.c) {
                return -1;
            }
            String str = this.f1472a;
            int i = this.f1473b;
            this.f1473b = i + 1;
            return str.charAt(i);
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) {
            int i3 = 0;
            while (i3 < i2 && this.f1473b < this.c) {
                cArr[i + i3] = this.f1472a.charAt(this.f1473b);
                i3++;
                this.f1473b++;
            }
            if (i3 > 0 || i2 == 0) {
                return i3;
            }
            return -1;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/Globals$AbstractBufferedStream.class */
    static abstract class AbstractBufferedStream extends InputStream {

        /* renamed from: a, reason: collision with root package name */
        protected byte[] f1470a;

        /* renamed from: b, reason: collision with root package name */
        protected int f1471b = 0;
        protected int c = 0;

        protected abstract int a();

        protected AbstractBufferedStream(int i) {
            this.f1470a = new byte[i];
        }

        @Override // java.io.InputStream
        public int read() {
            if (a() <= 0) {
                return -1;
            }
            byte[] bArr = this.f1470a;
            int i = this.f1471b;
            this.f1471b = i + 1;
            return 255 & bArr[i];
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            int a2 = a();
            if (a2 <= 0) {
                return -1;
            }
            int min = Math.min(a2, i2);
            System.arraycopy(this.f1470a, this.f1471b, bArr, i, min);
            this.f1471b += min;
            return min;
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            long min = Math.min(j, this.c - this.f1471b);
            this.f1471b = (int) (this.f1471b + min);
            return min;
        }

        @Override // java.io.InputStream
        public int available() {
            return this.c - this.f1471b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/Globals$UTF8Stream.class */
    public static class UTF8Stream extends AbstractBufferedStream {
        private final char[] d;
        private final Reader e;

        UTF8Stream(Reader reader) {
            super(96);
            this.d = new char[32];
            this.e = reader;
        }

        @Override // com.prineside.luaj.Globals.AbstractBufferedStream
        protected final int a() {
            if (this.f1471b < this.c) {
                return this.c - this.f1471b;
            }
            int read = this.e.read(this.d);
            int i = read;
            if (read < 0) {
                return -1;
            }
            if (i == 0) {
                int read2 = this.e.read();
                if (read2 >= 0) {
                    this.d[0] = (char) read2;
                    i = 1;
                } else {
                    return -1;
                }
            }
            byte[] bArr = this.f1470a;
            this.f1471b = 0;
            this.c = LuaString.encodeToUtf8(this.d, i, bArr, 0);
            return this.c;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.e.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/Globals$BufferedStream.class */
    public static class BufferedStream extends AbstractBufferedStream {
        private final InputStream d;

        public BufferedStream(InputStream inputStream) {
            this(128, inputStream);
        }

        private BufferedStream(int i, InputStream inputStream) {
            super(128);
            this.d = inputStream;
        }

        @Override // com.prineside.luaj.Globals.AbstractBufferedStream
        protected final int a() {
            if (this.f1471b < this.c) {
                return this.c - this.f1471b;
            }
            if (this.c >= this.f1470a.length) {
                this.c = 0;
                this.f1471b = 0;
            }
            int read = this.d.read(this.f1470a, this.c, this.f1470a.length - this.c);
            int i = read;
            if (read < 0) {
                return -1;
            }
            if (i == 0) {
                int read2 = this.d.read();
                if (read2 >= 0) {
                    this.f1470a[this.c] = (byte) read2;
                    i = 1;
                } else {
                    return -1;
                }
            }
            this.c += i;
            return i;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.d.close();
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            if (this.f1471b > 0 || i > this.f1470a.length) {
                byte[] bArr = i > this.f1470a.length ? new byte[i] : this.f1470a;
                System.arraycopy(this.f1470a, this.f1471b, bArr, 0, this.c - this.f1471b);
                this.c -= this.f1471b;
                this.f1471b = 0;
                this.f1470a = bArr;
            }
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public void reset() {
            this.f1471b = 0;
        }
    }
}
