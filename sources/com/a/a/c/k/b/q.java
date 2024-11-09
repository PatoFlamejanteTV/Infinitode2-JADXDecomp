package com.a.a.c.k.b;

import com.a.a.a.l;
import java.net.InetAddress;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/q.class */
public final class q extends an<InetAddress> implements com.a.a.c.k.k {

    /* renamed from: a, reason: collision with root package name */
    private boolean f627a;

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        a((InetAddress) obj, hVar);
    }

    public q() {
        this(false);
    }

    private q(boolean z) {
        super(InetAddress.class);
        this.f627a = z;
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        l.d a2 = a(aaVar, cVar, (Class<?>) a());
        boolean z = false;
        if (a2 != null) {
            l.c c = a2.c();
            if (c.a() || c == l.c.ARRAY) {
                z = true;
            }
        }
        if (z != this.f627a) {
            return new q(z);
        }
        return this;
    }

    private void a(InetAddress inetAddress, com.a.a.b.h hVar) {
        String str;
        if (this.f627a) {
            str = inetAddress.getHostAddress();
        } else {
            String trim = inetAddress.toString().trim();
            str = trim;
            int indexOf = trim.indexOf(47);
            if (indexOf >= 0) {
                if (indexOf == 0) {
                    str = str.substring(1);
                } else {
                    str = str.substring(0, indexOf);
                }
            }
        }
        hVar.b(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.an, com.a.a.c.o
    public void a(InetAddress inetAddress, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(inetAddress, InetAddress.class, com.a.a.b.o.VALUE_STRING));
        a(inetAddress, hVar);
        iVar.b(hVar, a2);
    }
}
