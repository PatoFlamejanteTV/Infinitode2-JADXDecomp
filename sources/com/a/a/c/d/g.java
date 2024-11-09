package com.a.a.c.d;

import com.a.a.b.j;
import com.a.a.b.l;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/a/a/c/d/g.class */
public abstract class g extends f {

    /* renamed from: b, reason: collision with root package name */
    private Collection<Object> f418b;
    private transient String c;

    /* JADX INFO: Access modifiers changed from: protected */
    public g(l lVar, String str, j jVar, Class<?> cls, String str2, Collection<Object> collection) {
        super(lVar, str, jVar);
        this.f418b = collection;
    }

    @Override // com.a.a.b.m
    public final String e() {
        String str = this.c;
        String str2 = str;
        if (str == null && this.f418b != null) {
            StringBuilder sb = new StringBuilder(100);
            int size = this.f418b.size();
            if (size != 1) {
                sb.append(" (").append(size).append(" known properties: ");
                Iterator<Object> it = this.f418b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append('\"');
                    sb.append(String.valueOf(it.next()));
                    sb.append('\"');
                    if (sb.length() > 1000) {
                        sb.append(" [truncated]");
                        break;
                    }
                    if (it.hasNext()) {
                        sb.append(", ");
                    }
                }
            } else {
                sb.append(" (one known property: \"");
                sb.append(String.valueOf(this.f418b.iterator().next()));
                sb.append('\"');
            }
            sb.append("])");
            String sb2 = sb.toString();
            str2 = sb2;
            this.c = sb2;
        }
        return str2;
    }
}
