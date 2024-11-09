package com.a.a.c.l;

import com.a.a.c.aa;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/l/m.class */
public abstract class m extends com.a.a.c.j implements com.a.a.c.n {
    private static final n e = n.a();
    protected final com.a.a.c.j g;
    protected final com.a.a.c.j[] h;
    protected final n i;
    private volatile transient String f;

    /* JADX INFO: Access modifiers changed from: protected */
    public m(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr, int i, Object obj, Object obj2, boolean z) {
        super(cls, i, obj, obj2, z);
        this.i = nVar == null ? e : nVar;
        this.g = jVar;
        this.h = jVarArr;
    }

    @Override // com.a.a.c.c.a.l
    public final String G() {
        String str = this.f;
        String str2 = str;
        if (str == null) {
            str2 = I();
        }
        return str2;
    }

    protected String I() {
        return this.f522a.getName();
    }

    @Override // com.a.a.c.j
    public n x() {
        return this.i;
    }

    @Override // com.a.a.c.j
    public final int w() {
        return this.i.c();
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j a(int i) {
        return this.i.a(i);
    }

    @Override // com.a.a.c.j
    public com.a.a.c.j y() {
        return this.g;
    }

    @Override // com.a.a.c.j
    public final List<com.a.a.c.j> z() {
        if (this.h == null) {
            return Collections.emptyList();
        }
        switch (this.h.length) {
            case 0:
                return Collections.emptyList();
            case 1:
                return Collections.singletonList(this.h[0]);
            default:
                return Arrays.asList(this.h);
        }
    }

    @Override // com.a.a.c.j
    public final com.a.a.c.j d(Class<?> cls) {
        com.a.a.c.j d;
        if (cls == this.f522a) {
            return this;
        }
        if (cls.isInterface() && this.h != null) {
            int length = this.h.length;
            for (int i = 0; i < length; i++) {
                com.a.a.c.j d2 = this.h[i].d(cls);
                if (d2 != null) {
                    return d2;
                }
            }
        }
        if (this.g != null && (d = this.g.d(cls)) != null) {
            return d;
        }
        return null;
    }

    @Override // com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        com.a.a.b.f.a aVar = new com.a.a.b.f.a(this, com.a.a.b.o.VALUE_STRING);
        iVar.a(hVar, aVar);
        a(hVar, aaVar);
        iVar.b(hVar, aVar);
    }

    @Override // com.a.a.c.n
    public final void a(com.a.a.b.h hVar, aa aaVar) {
        hVar.b(G());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static StringBuilder a(Class<?> cls, StringBuilder sb, boolean z) {
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                sb.append('Z');
            } else if (cls == Byte.TYPE) {
                sb.append('B');
            } else if (cls == Short.TYPE) {
                sb.append('S');
            } else if (cls == Character.TYPE) {
                sb.append('C');
            } else if (cls == Integer.TYPE) {
                sb.append('I');
            } else if (cls == Long.TYPE) {
                sb.append('J');
            } else if (cls == Float.TYPE) {
                sb.append('F');
            } else if (cls == Double.TYPE) {
                sb.append('D');
            } else if (cls == Void.TYPE) {
                sb.append('V');
            } else {
                throw new IllegalStateException("Unrecognized primitive type: " + cls.getName());
            }
        } else {
            sb.append('L');
            String name = cls.getName();
            int length = name.length();
            for (int i = 0; i < length; i++) {
                char charAt = name.charAt(i);
                char c = charAt;
                if (charAt == '.') {
                    c = '/';
                }
                sb.append(c);
            }
            if (z) {
                sb.append(';');
            }
        }
        return sb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean c(int i) {
        return this.f522a.getTypeParameters().length == i;
    }
}
