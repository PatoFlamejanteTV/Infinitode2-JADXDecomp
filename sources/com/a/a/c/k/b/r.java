package com.a.a.c.k.b;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/r.class */
public final class r extends an<InetSocketAddress> {
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        a((InetSocketAddress) obj, hVar);
    }

    public r() {
        super(InetSocketAddress.class);
    }

    private static void a(InetSocketAddress inetSocketAddress, com.a.a.b.h hVar) {
        String substring;
        InetAddress address = inetSocketAddress.getAddress();
        String hostName = address == null ? inetSocketAddress.getHostName() : address.toString().trim();
        String str = hostName;
        int indexOf = hostName.indexOf(47);
        if (indexOf >= 0) {
            if (indexOf == 0) {
                if (address instanceof Inet6Address) {
                    substring = "[" + str.substring(1) + "]";
                } else {
                    substring = str.substring(1);
                }
                str = substring;
            } else {
                str = str.substring(0, indexOf);
            }
        }
        hVar.b(str + ":" + inetSocketAddress.getPort());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.an, com.a.a.c.o
    public void a(InetSocketAddress inetSocketAddress, com.a.a.b.h hVar, com.a.a.c.aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a a2 = iVar.a(hVar, iVar.a(inetSocketAddress, InetSocketAddress.class, com.a.a.b.o.VALUE_STRING));
        a(inetSocketAddress, hVar);
        iVar.b(hVar, a2);
    }
}
