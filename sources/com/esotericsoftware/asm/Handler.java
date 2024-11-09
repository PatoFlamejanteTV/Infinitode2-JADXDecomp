package com.esotericsoftware.asm;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/Handler.class */
class Handler {

    /* renamed from: a, reason: collision with root package name */
    Label f1450a;

    /* renamed from: b, reason: collision with root package name */
    Label f1451b;
    Label c;
    String d;
    int e;
    Handler f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Handler a(Handler handler, Label label, Label label2) {
        if (handler == null) {
            return null;
        }
        handler.f = a(handler.f, label, label2);
        int i = handler.f1450a.c;
        int i2 = handler.f1451b.c;
        int i3 = label.c;
        int i4 = label2 == null ? Integer.MAX_VALUE : label2.c;
        if (i3 < i2 && i4 > i) {
            if (i3 <= i) {
                if (i4 >= i2) {
                    handler = handler.f;
                } else {
                    handler.f1450a = label2;
                }
            } else if (i4 >= i2) {
                handler.f1451b = label;
            } else {
                Handler handler2 = new Handler();
                handler2.f1450a = label2;
                handler2.f1451b = handler.f1451b;
                handler2.c = handler.c;
                handler2.d = handler.d;
                handler2.e = handler.e;
                handler2.f = handler.f;
                handler.f1451b = label;
                handler.f = handler2;
            }
        }
        return handler;
    }
}
