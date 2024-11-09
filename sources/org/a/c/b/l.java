package org.a.c.b;

import java.io.IOException;

/* loaded from: infinitode-2.jar:org/a/c/b/l.class */
public abstract class l extends b {
    public abstract float a();

    public abstract int c();

    public abstract long b();

    static {
        i iVar = i.f4366a;
        i iVar2 = i.f4367b;
    }

    public static l a(String str) {
        if (str.length() == 1) {
            char charAt = str.charAt(0);
            if ('0' <= charAt && charAt <= '9') {
                return i.a(charAt - '0');
            }
            if (charAt == '-' || charAt == '.') {
                return i.f4366a;
            }
            throw new IOException("Not a number: " + str);
        }
        if (str.indexOf(46) == -1 && str.toLowerCase().indexOf(101) == -1) {
            try {
                if (str.charAt(0) == '+') {
                    return i.a(Long.parseLong(str.substring(1)));
                }
                return i.a(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                return new f(str);
            }
        }
        return new f(str);
    }
}
