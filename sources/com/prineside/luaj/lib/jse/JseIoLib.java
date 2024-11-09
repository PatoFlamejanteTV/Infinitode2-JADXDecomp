package com.prineside.luaj.lib.jse;

import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.lib.IoLib;
import com.prineside.luaj.lib.OsLib;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JseIoLib.class */
public class JseIoLib extends IoLib {
    @Override // com.prineside.luaj.lib.IoLib
    protected final IoLib.File d() {
        return new StdinFile(this, (byte) 0);
    }

    @Override // com.prineside.luaj.lib.IoLib
    protected final IoLib.File e() {
        return new StdoutFile(this, 1, (byte) 0);
    }

    @Override // com.prineside.luaj.lib.IoLib
    protected final IoLib.File f() {
        return new StdoutFile(this, 2, (byte) 0);
    }

    @Override // com.prineside.luaj.lib.IoLib
    protected final IoLib.File a(String str, boolean z, boolean z2) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, z ? "r" : "rw");
        if (z2) {
            randomAccessFile.seek(randomAccessFile.length());
        } else if (!z) {
            randomAccessFile.setLength(0L);
        }
        return new FileImpl(this, randomAccessFile, (byte) 0);
    }

    @Override // com.prineside.luaj.lib.IoLib
    protected final IoLib.File b(String str, String str2) {
        Process exec = Runtime.getRuntime().exec(str);
        if ("w".equals(str2)) {
            return new FileImpl(this, exec.getOutputStream(), (byte) 0);
        }
        return new FileImpl(this, exec.getInputStream(), (byte) 0);
    }

    @Override // com.prineside.luaj.lib.IoLib
    protected final IoLib.File g() {
        File createTempFile = File.createTempFile(OsLib.TMP_PREFIX, "bin");
        createTempFile.deleteOnExit();
        return new FileImpl(this, new RandomAccessFile(createTempFile, "rw"), (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i() {
        throw new LuaError("not implemented");
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JseIoLib$FileImpl.class */
    private final class FileImpl extends IoLib.File {

        /* renamed from: a, reason: collision with root package name */
        private final RandomAccessFile f1627a;

        /* renamed from: b, reason: collision with root package name */
        private final InputStream f1628b;
        private final OutputStream c;
        private boolean d;
        private boolean e;

        /* synthetic */ FileImpl(JseIoLib jseIoLib, RandomAccessFile randomAccessFile, byte b2) {
            this(jseIoLib, randomAccessFile);
        }

        /* synthetic */ FileImpl(JseIoLib jseIoLib, OutputStream outputStream, byte b2) {
            this(jseIoLib, outputStream);
        }

        /* synthetic */ FileImpl(JseIoLib jseIoLib, InputStream inputStream, byte b2) {
            this(jseIoLib, inputStream);
        }

        private FileImpl(JseIoLib jseIoLib, RandomAccessFile randomAccessFile, InputStream inputStream, OutputStream outputStream) {
            super();
            this.d = false;
            this.e = false;
            this.f1627a = randomAccessFile;
            this.f1628b = inputStream != null ? inputStream.markSupported() ? inputStream : new BufferedInputStream(inputStream) : null;
            this.c = outputStream;
        }

        private FileImpl(JseIoLib jseIoLib, RandomAccessFile randomAccessFile) {
            this(jseIoLib, randomAccessFile, null, null);
        }

        private FileImpl(JseIoLib jseIoLib, InputStream inputStream) {
            this(jseIoLib, null, inputStream, null);
        }

        private FileImpl(JseIoLib jseIoLib, OutputStream outputStream) {
            this(jseIoLib, null, null, outputStream);
        }

        @Override // com.prineside.luaj.lib.IoLib.File, com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public final String tojstring() {
            return "file (" + (this.d ? "closed" : String.valueOf(hashCode())) + ")";
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final boolean isstdfile() {
            return this.f1627a == null;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void close() {
            this.d = true;
            if (this.f1627a != null) {
                this.f1627a.close();
            }
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void flush() {
            if (this.c != null) {
                this.c.flush();
            }
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void write(LuaString luaString) {
            if (this.c != null) {
                this.c.write(luaString.m_bytes, luaString.m_offset, luaString.m_length);
            } else if (this.f1627a == null) {
                JseIoLib.i();
            } else {
                this.f1627a.write(luaString.m_bytes, luaString.m_offset, luaString.m_length);
            }
            if (this.e) {
                flush();
            }
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final boolean isclosed() {
            return this.d;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int seek(String str, int i) {
            if (this.f1627a == null) {
                JseIoLib.i();
                return 0;
            }
            if ("set".equals(str)) {
                this.f1627a.seek(i);
            } else if ("end".equals(str)) {
                this.f1627a.seek(this.f1627a.length() + i);
            } else {
                this.f1627a.seek(this.f1627a.getFilePointer() + i);
            }
            return (int) this.f1627a.getFilePointer();
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void setvbuf(String str, int i) {
            this.e = "no".equals(str);
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int remaining() {
            if (this.f1627a != null) {
                return (int) (this.f1627a.length() - this.f1627a.getFilePointer());
            }
            return -1;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int peek() {
            if (this.f1628b != null) {
                this.f1628b.mark(1);
                int read = this.f1628b.read();
                this.f1628b.reset();
                return read;
            }
            if (this.f1627a == null) {
                JseIoLib.i();
                return 0;
            }
            long filePointer = this.f1627a.getFilePointer();
            int read2 = this.f1627a.read();
            this.f1627a.seek(filePointer);
            return read2;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int read() {
            if (this.f1628b != null) {
                return this.f1628b.read();
            }
            if (this.f1627a == null) {
                JseIoLib.i();
                return 0;
            }
            return this.f1627a.read();
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int read(byte[] bArr, int i, int i2) {
            if (this.f1627a != null) {
                return this.f1627a.read(bArr, i, i2);
            }
            if (this.f1628b == null) {
                JseIoLib.i();
                return i2;
            }
            return this.f1628b.read(bArr, i, i2);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JseIoLib$StdoutFile.class */
    private final class StdoutFile extends IoLib.File {

        /* renamed from: a, reason: collision with root package name */
        private final int f1630a;

        /* synthetic */ StdoutFile(JseIoLib jseIoLib, int i, byte b2) {
            this(i);
        }

        private StdoutFile(int i) {
            super();
            this.f1630a = i;
        }

        @Override // com.prineside.luaj.lib.IoLib.File, com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public final String tojstring() {
            return "file (" + hashCode() + ")";
        }

        private final PrintStream d() {
            return this.f1630a == 2 ? JseIoLib.this.f1579b.STDERR : JseIoLib.this.f1579b.STDOUT;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void write(LuaString luaString) {
            d().write(luaString.m_bytes, luaString.m_offset, luaString.m_length);
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void flush() {
            d().flush();
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final boolean isstdfile() {
            return true;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void close() {
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final boolean isclosed() {
            return false;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int seek(String str, int i) {
            return 0;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void setvbuf(String str, int i) {
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int remaining() {
            return 0;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int peek() {
            return 0;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int read() {
            return 0;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int read(byte[] bArr, int i, int i2) {
            return 0;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JseIoLib$StdinFile.class */
    private final class StdinFile extends IoLib.File {
        /* synthetic */ StdinFile(JseIoLib jseIoLib, byte b2) {
            this();
        }

        private StdinFile() {
            super();
        }

        @Override // com.prineside.luaj.lib.IoLib.File, com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public final String tojstring() {
            return "file (" + hashCode() + ")";
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void write(LuaString luaString) {
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void flush() {
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final boolean isstdfile() {
            return true;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void close() {
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final boolean isclosed() {
            return false;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int seek(String str, int i) {
            return 0;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final void setvbuf(String str, int i) {
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int remaining() {
            return -1;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int peek() {
            JseIoLib.this.f1579b.STDIN.mark(1);
            int read = JseIoLib.this.f1579b.STDIN.read();
            JseIoLib.this.f1579b.STDIN.reset();
            return read;
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int read() {
            return JseIoLib.this.f1579b.STDIN.read();
        }

        @Override // com.prineside.luaj.lib.IoLib.File
        public final int read(byte[] bArr, int i, int i2) {
            return JseIoLib.this.f1579b.STDIN.read(bArr, i, i2);
        }
    }
}
