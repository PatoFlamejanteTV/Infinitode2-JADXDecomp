package com.a.a.b.b;

import com.a.a.b.l;
import com.a.a.b.m;

/* loaded from: infinitode-2.jar:com/a/a/b/b/b.class */
public abstract class b extends m {
    private transient l c;

    /* renamed from: b, reason: collision with root package name */
    protected com.a.a.b.g.m f89b;

    /* JADX INFO: Access modifiers changed from: protected */
    public b(l lVar, String str) {
        super(str, lVar == null ? null : lVar.e());
        this.c = lVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b(l lVar, String str, Throwable th) {
        super(str, lVar == null ? null : lVar.e(), th);
        this.c = lVar;
    }

    @Override // com.a.a.b.m, com.a.a.b.d
    /* renamed from: d */
    public l c() {
        return this.c;
    }

    @Override // com.a.a.b.m, java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (this.f89b != null) {
            new StringBuilder().append(message).append("\nRequest payload : ");
            com.a.a.b.g.m mVar = this.f89b;
            throw null;
        }
        return message;
    }
}
