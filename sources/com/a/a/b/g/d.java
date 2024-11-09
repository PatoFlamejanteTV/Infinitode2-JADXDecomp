package com.a.a.b.g;

import com.a.a.b.g.e;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/a/a/b/g/d.class */
public final class d extends e.c {

    /* renamed from: b, reason: collision with root package name */
    private static String f156b;

    /* renamed from: a, reason: collision with root package name */
    public static final d f157a;
    private final char[] c;
    private final int d;
    private final String e;

    static {
        String str;
        try {
            str = System.getProperty("line.separator");
        } catch (Throwable unused) {
            str = SequenceUtils.EOL;
        }
        f156b = str;
        f157a = new d("  ", f156b);
    }

    public d() {
        this("  ", f156b);
    }

    private d(String str, String str2) {
        this.d = str.length();
        this.c = new char[str.length() << 4];
        int i = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            str.getChars(0, str.length(), this.c, i);
            i += str.length();
        }
        this.e = str2;
    }

    @Override // com.a.a.b.g.e.c, com.a.a.b.g.e.b
    public final boolean a() {
        return false;
    }

    @Override // com.a.a.b.g.e.c, com.a.a.b.g.e.b
    public final void a(com.a.a.b.h hVar, int i) {
        hVar.c(this.e);
        if (i > 0) {
            int i2 = i * this.d;
            while (true) {
                int i3 = i2;
                if (i3 > this.c.length) {
                    hVar.b(this.c, 0, this.c.length);
                    i2 = i3 - this.c.length;
                } else {
                    hVar.b(this.c, 0, i3);
                    return;
                }
            }
        }
    }
}
