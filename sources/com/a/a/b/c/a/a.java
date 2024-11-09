package com.a.a.b.c.a;

/* loaded from: infinitode-2.jar:com/a/a/b/c/a/a.class */
abstract class a {

    /* renamed from: a, reason: collision with root package name */
    static final byte[] f93a = new byte[128];

    static {
        char c = 0;
        while (true) {
            char c2 = c;
            if (c2 >= f93a.length) {
                break;
            }
            f93a[c2] = -1;
            c = (char) (c2 + 1);
        }
        char c3 = '0';
        while (true) {
            char c4 = c3;
            if (c4 > '9') {
                break;
            }
            f93a[c4] = (byte) (c4 - '0');
            c3 = (char) (c4 + 1);
        }
        char c5 = 'A';
        while (true) {
            char c6 = c5;
            if (c6 > 'F') {
                break;
            }
            f93a[c6] = (byte) ((c6 - 'A') + 10);
            c5 = (char) (c6 + 1);
        }
        char c7 = 'a';
        while (true) {
            char c8 = c7;
            if (c8 > 'f') {
                break;
            }
            f93a[c8] = (byte) ((c8 - 'a') + 10);
            c7 = (char) (c8 + 1);
        }
        for (char c9 = '.'; c9 <= '.'; c9 = '/') {
            f93a[46] = -4;
        }
    }
}
