package com.a.a.b;

/* loaded from: infinitode-2.jar:com/a/a/b/o.class */
public enum o {
    NOT_AVAILABLE(null, -1),
    START_OBJECT("{", 1),
    END_OBJECT("}", 2),
    START_ARRAY("[", 3),
    END_ARRAY("]", 4),
    FIELD_NAME(null, 5),
    VALUE_EMBEDDED_OBJECT(null, 12),
    VALUE_STRING(null, 6),
    VALUE_NUMBER_INT(null, 7),
    VALUE_NUMBER_FLOAT(null, 8),
    VALUE_TRUE("true", 9),
    VALUE_FALSE("false", 10),
    VALUE_NULL("null", 11);

    private String n;
    private char[] o;
    private byte[] p;
    private int q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;

    o(String str, int i) {
        if (str == null) {
            this.n = null;
            this.o = null;
            this.p = null;
        } else {
            this.n = str;
            this.o = str.toCharArray();
            int length = this.o.length;
            this.p = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                this.p[i2] = (byte) this.o[i2];
            }
        }
        this.q = i;
        this.t = i == 7 || i == 8;
        this.r = i == 1 || i == 3;
        this.s = i == 2 || i == 4;
        this.u = (this.r || this.s || i == 5 || i == -1) ? false : true;
    }

    public final int a() {
        return this.q;
    }

    public final String b() {
        return this.n;
    }

    public final char[] c() {
        return this.o;
    }

    public final boolean d() {
        return this.t;
    }

    public final boolean e() {
        return this.r;
    }

    public final boolean f() {
        return this.s;
    }

    public final boolean g() {
        return this.u;
    }
}
