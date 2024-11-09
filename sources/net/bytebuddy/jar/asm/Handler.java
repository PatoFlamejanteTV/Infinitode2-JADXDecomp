package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/Handler.class */
final class Handler {

    /* renamed from: a, reason: collision with root package name */
    final Label f4142a;

    /* renamed from: b, reason: collision with root package name */
    final Label f4143b;
    final Label c;
    private int f;
    final String d;
    Handler e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Handler(Label label, Label label2, Label label3, int i, String str) {
        this.f4142a = label;
        this.f4143b = label2;
        this.c = label3;
        this.f = i;
        this.d = str;
    }

    private Handler(Handler handler, Label label, Label label2) {
        this(label, label2, handler.c, handler.f, handler.d);
        this.e = handler.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Handler a(Handler handler, Label label, Label label2) {
        if (handler == null) {
            return null;
        }
        handler.e = a(handler.e, label, label2);
        int i = handler.f4142a.c;
        int i2 = handler.f4143b.c;
        int i3 = label.c;
        int i4 = label2 == null ? Integer.MAX_VALUE : label2.c;
        if (i3 >= i2 || i4 <= i) {
            return handler;
        }
        if (i3 <= i) {
            if (i4 >= i2) {
                return handler.e;
            }
            return new Handler(handler, label2, handler.f4143b);
        }
        if (i4 >= i2) {
            return new Handler(handler, handler.f4142a, label);
        }
        handler.e = new Handler(handler, label2, handler.f4143b);
        return new Handler(handler, handler.f4142a, label);
    }

    private static int b(Handler handler) {
        int i = 0;
        Handler handler2 = handler;
        while (true) {
            Handler handler3 = handler2;
            if (handler3 != null) {
                i++;
                handler2 = handler3.e;
            } else {
                return i;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(Handler handler) {
        return 2 + (8 * b(handler));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Handler handler, ByteVector byteVector) {
        byteVector.putShort(b(handler));
        Handler handler2 = handler;
        while (true) {
            Handler handler3 = handler2;
            if (handler3 != null) {
                byteVector.putShort(handler3.f4142a.c).putShort(handler3.f4143b.c).putShort(handler3.c.c).putShort(handler3.f);
                handler2 = handler3.e;
            } else {
                return;
            }
        }
    }
}
