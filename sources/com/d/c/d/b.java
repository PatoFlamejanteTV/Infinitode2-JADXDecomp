package com.d.c.d;

/* loaded from: infinitode-2.jar:com/d/c/d/b.class */
public final class b extends RuntimeException {

    /* renamed from: a, reason: collision with root package name */
    private final k f1048a;

    /* renamed from: b, reason: collision with root package name */
    private final k[] f1049b;
    private int c;
    private final String d;
    private boolean e;

    public b(String str, int i) {
        this.f1048a = null;
        this.f1049b = null;
        this.c = i;
        this.d = str;
    }

    public b(k kVar, k kVar2, int i) {
        this.f1048a = kVar;
        this.f1049b = new k[]{kVar2};
        this.c = i;
        this.d = null;
    }

    public b(k kVar, k[] kVarArr, int i) {
        this.f1048a = kVar;
        this.f1049b = (k[]) kVarArr.clone();
        this.c = i;
        this.d = null;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        if (this.d != null) {
            return this.d + " at line " + (this.c + 1) + ".";
        }
        return "Found " + (this.f1048a == null ? "end of file" : this.f1048a.b()) + " where " + a(this.f1049b) + " was expected at line " + (this.c + 1) + ".";
    }

    private static String a(k[] kVarArr) {
        if (kVarArr.length == 1) {
            return kVarArr[0].b();
        }
        StringBuilder sb = new StringBuilder();
        if (kVarArr.length > 2) {
            sb.append("one of ");
        }
        for (int i = 0; i < kVarArr.length; i++) {
            sb.append(kVarArr[i].b());
            if (i < kVarArr.length - 2) {
                sb.append(", ");
            } else if (i == kVarArr.length - 2) {
                if (kVarArr.length > 2) {
                    sb.append(", or ");
                } else {
                    sb.append(" or ");
                }
            }
        }
        return sb.toString();
    }

    public final void a(int i) {
        this.c = i;
    }

    public final boolean a() {
        return this.f1048a == k.aa;
    }

    public final boolean b() {
        return this.e;
    }

    public final void a(boolean z) {
        this.e = true;
    }
}
