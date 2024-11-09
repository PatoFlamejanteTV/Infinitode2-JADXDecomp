package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/Symbol.class */
abstract class Symbol {

    /* renamed from: a, reason: collision with root package name */
    final int f4146a;

    /* renamed from: b, reason: collision with root package name */
    final int f4147b;
    final String c;
    final String d;
    final String e;
    final long f;
    int g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Symbol(int i, int i2, String str, String str2, String str3, long j) {
        this.f4146a = i;
        this.f4147b = i2;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        if (this.g == 0) {
            this.g = Type.getArgumentsAndReturnSizes(this.e);
        }
        return this.g;
    }
}
