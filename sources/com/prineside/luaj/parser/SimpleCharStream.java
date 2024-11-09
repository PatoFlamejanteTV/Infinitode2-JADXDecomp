package com.prineside.luaj.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/* loaded from: infinitode-2.jar:com/prineside/luaj/parser/SimpleCharStream.class */
public class SimpleCharStream {
    public static final boolean staticFlag = false;

    /* renamed from: a, reason: collision with root package name */
    private int f1645a;

    /* renamed from: b, reason: collision with root package name */
    private int f1646b;
    private int c;
    public int bufpos;
    private int[] d;
    private int[] e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;
    private Reader j;
    private char[] k;
    private int l;
    private int m;
    private int n;

    public void setTabSize(int i) {
        this.n = i;
    }

    public int getTabSize(int i) {
        return this.n;
    }

    private void a(boolean z) {
        char[] cArr = new char[this.f1645a + 2048];
        int[] iArr = new int[this.f1645a + 2048];
        int[] iArr2 = new int[this.f1645a + 2048];
        try {
            if (z) {
                System.arraycopy(this.k, this.c, cArr, 0, this.f1645a - this.c);
                System.arraycopy(this.k, 0, cArr, this.f1645a - this.c, this.bufpos);
                this.k = cArr;
                System.arraycopy(this.d, this.c, iArr, 0, this.f1645a - this.c);
                System.arraycopy(this.d, 0, iArr, this.f1645a - this.c, this.bufpos);
                this.d = iArr;
                System.arraycopy(this.e, this.c, iArr2, 0, this.f1645a - this.c);
                System.arraycopy(this.e, 0, iArr2, this.f1645a - this.c, this.bufpos);
                this.e = iArr2;
                int i = this.bufpos + (this.f1645a - this.c);
                this.bufpos = i;
                this.l = i;
            } else {
                System.arraycopy(this.k, this.c, cArr, 0, this.f1645a - this.c);
                this.k = cArr;
                System.arraycopy(this.d, this.c, iArr, 0, this.f1645a - this.c);
                this.d = iArr;
                System.arraycopy(this.e, this.c, iArr2, 0, this.f1645a - this.c);
                this.e = iArr2;
                int i2 = this.bufpos - this.c;
                this.bufpos = i2;
                this.l = i2;
            }
            this.f1645a += 2048;
            this.f1646b = this.f1645a;
            this.c = 0;
        } catch (Throwable th) {
            throw new Error(th.getMessage());
        }
    }

    private void a() {
        if (this.l == this.f1646b) {
            if (this.f1646b == this.f1645a) {
                if (this.c > 2048) {
                    this.l = 0;
                    this.bufpos = 0;
                    this.f1646b = this.c;
                } else if (this.c < 0) {
                    this.l = 0;
                    this.bufpos = 0;
                } else {
                    a(false);
                }
            } else if (this.f1646b > this.c) {
                this.f1646b = this.f1645a;
            } else if (this.c - this.f1646b < 2048) {
                a(true);
            } else {
                this.f1646b = this.c;
            }
        }
        try {
            int read = this.j.read(this.k, this.l, this.f1646b - this.l);
            if (read == -1) {
                this.j.close();
                throw new IOException();
            }
            this.l += read;
        } catch (IOException e) {
            this.bufpos--;
            backup(0);
            if (this.c == -1) {
                this.c = this.bufpos;
            }
            throw e;
        }
    }

    public char BeginToken() {
        this.c = -1;
        char readChar = readChar();
        this.c = this.bufpos;
        return readChar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(char r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r0
            int r1 = r1.f
            r2 = 1
            int r1 = r1 + r2
            r0.f = r1
            r0 = r6
            boolean r0 = r0.i
            if (r0 == 0) goto L19
            r0 = r6
            r1 = 0
            r0.i = r1
            goto L33
        L19:
            r0 = r6
            boolean r0 = r0.h
            if (r0 == 0) goto L42
            r0 = r6
            r1 = 0
            r0.h = r1
            r0 = r7
            r1 = 10
            if (r0 != r1) goto L33
            r0 = r6
            r1 = 1
            r0.i = r1
            goto L42
        L33:
            r0 = r6
            r1 = r0
            int r1 = r1.g
            r2 = r6
            r3 = 1
            r4 = r3; r3 = r2; r2 = r4; 
            r3.f = r4
            int r1 = r1 + r2
            r0.g = r1
        L42:
            r0 = r7
            switch(r0) {
                case 9: goto L74;
                case 10: goto L6c;
                case 11: goto L95;
                case 12: goto L95;
                case 13: goto L64;
                default: goto L95;
            }
        L64:
            r0 = r6
            r1 = 1
            r0.h = r1
            goto L95
        L6c:
            r0 = r6
            r1 = 1
            r0.i = r1
            goto L95
        L74:
            r0 = r6
            r1 = r0
            int r1 = r1.f
            r2 = 1
            int r1 = r1 - r2
            r0.f = r1
            r0 = r6
            r1 = r0
            int r1 = r1.f
            r2 = r6
            int r2 = r2.n
            r3 = r6
            int r3 = r3.f
            r4 = r6
            int r4 = r4.n
            int r3 = r3 % r4
            int r2 = r2 - r3
            int r1 = r1 + r2
            r0.f = r1
        L95:
            r0 = r6
            int[] r0 = r0.d
            r1 = r6
            int r1 = r1.bufpos
            r2 = r6
            int r2 = r2.g
            r0[r1] = r2
            r0 = r6
            int[] r0 = r0.e
            r1 = r6
            int r1 = r1.bufpos
            r2 = r6
            int r2 = r2.f
            r0[r1] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.SimpleCharStream.a(char):void");
    }

    public char readChar() {
        if (this.m > 0) {
            this.m--;
            int i = this.bufpos + 1;
            this.bufpos = i;
            if (i == this.f1645a) {
                this.bufpos = 0;
            }
            return this.k[this.bufpos];
        }
        int i2 = this.bufpos + 1;
        this.bufpos = i2;
        if (i2 >= this.l) {
            a();
        }
        char c = this.k[this.bufpos];
        a(c);
        return c;
    }

    public int getColumn() {
        return this.e[this.bufpos];
    }

    public int getLine() {
        return this.d[this.bufpos];
    }

    public int getEndColumn() {
        return this.e[this.bufpos];
    }

    public int getEndLine() {
        return this.d[this.bufpos];
    }

    public int getBeginColumn() {
        return this.e[this.c];
    }

    public int getBeginLine() {
        return this.d[this.c];
    }

    public void backup(int i) {
        this.m += i;
        int i2 = this.bufpos - i;
        this.bufpos = i2;
        if (i2 < 0) {
            this.bufpos += this.f1645a;
        }
    }

    public SimpleCharStream(Reader reader, int i, int i2, int i3) {
        this.bufpos = -1;
        this.f = 0;
        this.g = 1;
        this.h = false;
        this.i = false;
        this.l = 0;
        this.m = 0;
        this.n = 1;
        this.j = reader;
        this.g = i;
        this.f = i2 - 1;
        this.f1645a = i3;
        this.f1646b = i3;
        this.k = new char[i3];
        this.d = new int[i3];
        this.e = new int[i3];
    }

    public SimpleCharStream(Reader reader, int i, int i2) {
        this(reader, i, i2, 4096);
    }

    public SimpleCharStream(Reader reader) {
        this(reader, 1, 1, 4096);
    }

    public void ReInit(Reader reader, int i, int i2, int i3) {
        this.j = reader;
        this.g = i;
        this.f = i2 - 1;
        if (this.k == null || i3 != this.k.length) {
            this.f1645a = i3;
            this.f1646b = i3;
            this.k = new char[i3];
            this.d = new int[i3];
            this.e = new int[i3];
        }
        this.h = false;
        this.i = false;
        this.l = 0;
        this.m = 0;
        this.c = 0;
        this.bufpos = -1;
    }

    public void ReInit(Reader reader, int i, int i2) {
        ReInit(reader, i, i2, 4096);
    }

    public void ReInit(Reader reader) {
        ReInit(reader, 1, 1, 4096);
    }

    public SimpleCharStream(InputStream inputStream, String str, int i, int i2, int i3) {
        this(str == null ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, str), i, i2, i3);
    }

    public SimpleCharStream(InputStream inputStream, int i, int i2, int i3) {
        this(new InputStreamReader(inputStream), i, i2, i3);
    }

    public SimpleCharStream(InputStream inputStream, String str, int i, int i2) {
        this(inputStream, str, i, i2, 4096);
    }

    public SimpleCharStream(InputStream inputStream, int i, int i2) {
        this(inputStream, i, i2, 4096);
    }

    public SimpleCharStream(InputStream inputStream, String str) {
        this(inputStream, str, 1, 1, 4096);
    }

    public SimpleCharStream(InputStream inputStream) {
        this(inputStream, 1, 1, 4096);
    }

    public void ReInit(InputStream inputStream, String str, int i, int i2, int i3) {
        ReInit(str == null ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, str), i, i2, i3);
    }

    public void ReInit(InputStream inputStream, int i, int i2, int i3) {
        ReInit(new InputStreamReader(inputStream), i, i2, i3);
    }

    public void ReInit(InputStream inputStream, String str) {
        ReInit(inputStream, str, 1, 1, 4096);
    }

    public void ReInit(InputStream inputStream) {
        ReInit(inputStream, 1, 1, 4096);
    }

    public void ReInit(InputStream inputStream, String str, int i, int i2) {
        ReInit(inputStream, str, i, i2, 4096);
    }

    public void ReInit(InputStream inputStream, int i, int i2) {
        ReInit(inputStream, i, i2, 4096);
    }

    public String GetImage() {
        if (this.bufpos >= this.c) {
            return new String(this.k, this.c, (this.bufpos - this.c) + 1);
        }
        return new String(this.k, this.c, this.f1645a - this.c) + new String(this.k, 0, this.bufpos + 1);
    }

    public char[] GetSuffix(int i) {
        char[] cArr = new char[i];
        if (this.bufpos + 1 >= i) {
            System.arraycopy(this.k, (this.bufpos - i) + 1, cArr, 0, i);
        } else {
            System.arraycopy(this.k, this.f1645a - ((i - this.bufpos) - 1), cArr, 0, (i - this.bufpos) - 1);
            System.arraycopy(this.k, 0, cArr, (i - this.bufpos) - 1, this.bufpos + 1);
        }
        return cArr;
    }

    public void Done() {
        this.k = null;
        this.d = null;
        this.e = null;
    }

    public void adjustBeginLineColumn(int i, int i2) {
        int i3;
        int i4 = this.c;
        if (this.bufpos >= this.c) {
            i3 = (this.bufpos - this.c) + this.m + 1;
        } else {
            i3 = (this.f1645a - this.c) + this.bufpos + 1 + this.m;
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i3) {
            int[] iArr = this.d;
            int i8 = i4 % this.f1645a;
            i6 = i8;
            int i9 = iArr[i8];
            int[] iArr2 = this.d;
            i4++;
            int i10 = i4 % this.f1645a;
            if (i9 != iArr2[i10]) {
                break;
            }
            this.d[i6] = i;
            int i11 = (i7 + this.e[i10]) - this.e[i6];
            this.e[i6] = i2 + i7;
            i7 = i11;
            i5++;
        }
        if (i5 < i3) {
            int i12 = i + 1;
            this.d[i6] = i;
            this.e[i6] = i2 + i7;
            while (true) {
                int i13 = i5;
                i5++;
                if (i13 >= i3) {
                    break;
                }
                int[] iArr3 = this.d;
                int i14 = i4 % this.f1645a;
                i6 = i14;
                i4++;
                if (iArr3[i14] != this.d[i4 % this.f1645a]) {
                    int i15 = i12;
                    i12++;
                    this.d[i6] = i15;
                } else {
                    this.d[i6] = i12;
                }
            }
        }
        this.g = this.d[i6];
        this.f = this.e[i6];
    }
}
