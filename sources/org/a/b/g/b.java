package org.a.b.g;

/* loaded from: infinitode-2.jar:org/a/b/g/b.class */
final class b {

    /* renamed from: a, reason: collision with root package name */
    static final a f4337a = a.STRING;

    /* renamed from: b, reason: collision with root package name */
    static final a f4338b = a.NAME;
    static final a c = a.LITERAL;
    static final a d = a.REAL;
    static final a e = a.INTEGER;
    static final a f = a.START_ARRAY;
    static final a g = a.END_ARRAY;
    static final a h = a.START_PROC;
    static final a i = a.END_PROC;
    static final a j = a.CHARSTRING;
    static final a k = a.START_DICT;
    static final a l = a.END_DICT;
    private String m;
    private byte[] n;
    private final a o;

    /* loaded from: infinitode-2.jar:org/a/b/g/b$a.class */
    enum a {
        NONE,
        STRING,
        NAME,
        LITERAL,
        REAL,
        INTEGER,
        START_ARRAY,
        END_ARRAY,
        START_PROC,
        END_PROC,
        START_DICT,
        END_DICT,
        CHARSTRING
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str, a aVar) {
        this.m = str;
        this.o = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(char c2, a aVar) {
        this.m = Character.toString(c2);
        this.o = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(byte[] bArr, a aVar) {
        this.n = bArr;
        this.o = aVar;
    }

    public final String a() {
        return this.m;
    }

    public final a b() {
        return this.o;
    }

    public final int c() {
        return (int) Float.parseFloat(this.m);
    }

    public final float d() {
        return Float.parseFloat(this.m);
    }

    public final boolean e() {
        return this.m.equals("true");
    }

    public final byte[] f() {
        return this.n;
    }

    public final String toString() {
        if (this.o == j) {
            return "Token[kind=CHARSTRING, data=" + this.n.length + " bytes]";
        }
        return "Token[kind=" + this.o + ", text=" + this.m + "]";
    }
}
