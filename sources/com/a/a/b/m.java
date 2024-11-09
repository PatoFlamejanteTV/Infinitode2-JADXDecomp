package com.a.a.b;

/* loaded from: infinitode-2.jar:com/a/a/b/m.class */
public class m extends d {

    /* renamed from: a, reason: collision with root package name */
    protected j f182a;

    /* JADX INFO: Access modifiers changed from: protected */
    public m(String str, j jVar, Throwable th) {
        super(str, th);
        this.f182a = jVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public m(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public m(String str, j jVar) {
        this(str, jVar, null);
    }

    @Override // com.a.a.b.d
    public final j a() {
        return this.f182a;
    }

    @Override // com.a.a.b.d
    public final String b() {
        return super.getMessage();
    }

    @Override // com.a.a.b.d
    public Object c() {
        return null;
    }

    protected String e() {
        return null;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        String str = message;
        if (message == null) {
            str = "N/A";
        }
        j a2 = a();
        String e = e();
        if (a2 != null || e != null) {
            StringBuilder sb = new StringBuilder(100);
            sb.append(str);
            if (e != null) {
                sb.append(e);
            }
            if (a2 != null) {
                sb.append('\n');
                sb.append(" at ");
                sb.append(a2.toString());
            }
            str = sb.toString();
        }
        return str;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }
}
