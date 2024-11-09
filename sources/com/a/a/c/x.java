package com.a.a.c;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/x.class */
public class x implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @Deprecated
    private static x f772a;

    /* renamed from: b, reason: collision with root package name */
    @Deprecated
    private static x f773b;

    static {
        new x();
        f772a = new f();
        f773b = new e();
        new b();
        new a();
        new c();
    }

    public String a(String str) {
        return str;
    }

    public String b(String str) {
        return str;
    }

    public String c(String str) {
        return str;
    }

    public String d(String str) {
        return str;
    }

    @Deprecated
    /* loaded from: infinitode-2.jar:com/a/a/c/x$d.class */
    public static abstract class d extends x {
        public abstract String e(String str);

        @Override // com.a.a.c.x
        public final String a(String str) {
            return e(str);
        }

        @Override // com.a.a.c.x
        public final String b(String str) {
            return e(str);
        }

        @Override // com.a.a.c.x
        public final String c(String str) {
            return e(str);
        }

        @Override // com.a.a.c.x
        public final String d(String str) {
            return e(str);
        }

        protected static String a(String str, char c) {
            if (str == null) {
                return str;
            }
            int length = str.length();
            if (length == 0) {
                return str;
            }
            StringBuilder sb = new StringBuilder(length + (length >> 1));
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                char lowerCase = Character.toLowerCase(charAt);
                if (lowerCase == charAt) {
                    if (i > 1) {
                        sb.insert(sb.length() - 1, c);
                    }
                    i = 0;
                } else {
                    if (i == 0 && i2 > 0) {
                        sb.append(c);
                    }
                    i++;
                }
                sb.append(lowerCase);
            }
            return sb.toString();
        }
    }

    @Deprecated
    /* loaded from: infinitode-2.jar:com/a/a/c/x$e.class */
    public static class e extends d {
        @Override // com.a.a.c.x.d
        public final String e(String str) {
            if (str == null) {
                return str;
            }
            int length = str.length();
            StringBuilder sb = new StringBuilder(length << 1);
            int i = 0;
            boolean z = false;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (i2 > 0 || charAt != '_') {
                    if (Character.isUpperCase(charAt)) {
                        if (!z && i > 0 && sb.charAt(i - 1) != '_') {
                            sb.append('_');
                            i++;
                        }
                        charAt = Character.toLowerCase(charAt);
                        z = true;
                    } else {
                        z = false;
                    }
                    sb.append(charAt);
                    i++;
                }
            }
            return i > 0 ? sb.toString() : str;
        }
    }

    @Deprecated
    /* loaded from: infinitode-2.jar:com/a/a/c/x$f.class */
    public static class f extends d {
        @Override // com.a.a.c.x.d
        public final String e(String str) {
            if (str == null || str.isEmpty()) {
                return str;
            }
            char charAt = str.charAt(0);
            char upperCase = Character.toUpperCase(charAt);
            if (charAt == upperCase) {
                return str;
            }
            StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(0, upperCase);
            return sb.toString();
        }
    }

    @Deprecated
    /* loaded from: infinitode-2.jar:com/a/a/c/x$b.class */
    public static class b extends d {
        @Override // com.a.a.c.x.d
        public final String e(String str) {
            return str.toLowerCase();
        }
    }

    @Deprecated
    /* loaded from: infinitode-2.jar:com/a/a/c/x$a.class */
    public static class a extends d {
        @Override // com.a.a.c.x.d
        public final String e(String str) {
            return a(str, '-');
        }
    }

    @Deprecated
    /* loaded from: infinitode-2.jar:com/a/a/c/x$c.class */
    public static class c extends d {
        @Override // com.a.a.c.x.d
        public final String e(String str) {
            return a(str, '.');
        }
    }
}
