package com.b.a.a;

import java.io.IOException;

/* loaded from: infinitode-2.jar:com/b/a/a/c.class */
public final class c {
    static {
        c.class.desiredAssertionStatus();
    }

    private static int a(Appendable appendable, int i) {
        if (i <= 65535) {
            appendable.append((char) i);
            return 1;
        }
        appendable.append((char) (55232 + (i >> 10)));
        appendable.append((char) (56320 + (i & 1023)));
        return 2;
    }

    private static void a(int i, Appendable appendable, int i2, int i3, com.b.a.c.g gVar) {
        if (i < 0) {
            if (gVar != null) {
                gVar.a(i2);
                if ((i3 & 16384) != 0) {
                    return;
                }
            }
            a(appendable, i ^ (-1));
            return;
        }
        if (i <= 31) {
            if (gVar != null) {
                gVar.a(i2, i);
            }
        } else {
            int a2 = a(appendable, i);
            if (gVar != null) {
                gVar.a(i2, a2);
            }
        }
    }

    public static <A extends Appendable> A a(int i, CharSequence charSequence, A a2, com.b.a.c.g gVar) {
        if (gVar != null) {
            try {
                gVar.a();
            } catch (IOException e) {
                throw new com.b.a.d.c(e);
            }
        }
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(charSequence, i2);
            int charCount = Character.charCount(codePointAt);
            i2 += charCount;
            a(aa.f779b.a(codePointAt, a2, i), a2, charCount, i, gVar);
        }
        return a2;
    }
}
