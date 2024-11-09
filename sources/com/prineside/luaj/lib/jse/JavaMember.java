package com.prineside.luaj.lib.jse;

import com.badlogic.gdx.utils.Null;
import com.prineside.luaj.Varargs;
import com.prineside.luaj.lib.VarArgFunction;
import com.prineside.luaj.lib.jse.CoerceLuaToJava;
import com.prineside.tdi2.utils.logging.TLog;
import java.lang.reflect.Array;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaMember.class */
public abstract class JavaMember extends VarArgFunction {

    /* renamed from: a, reason: collision with root package name */
    private CoerceLuaToJava.Coercion[] f1622a;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private CoerceLuaToJava.ArrayCoercion f1623b;

    static {
        TLog.forClass(JavaMember.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JavaMember(Class<?>[] clsArr, int i) {
        boolean z = (i & 128) != 0;
        this.f1622a = new CoerceLuaToJava.Coercion[z ? clsArr.length - 1 : clsArr.length];
        for (int i2 = 0; i2 < this.f1622a.length; i2++) {
            this.f1622a[i2] = CoerceLuaToJava.a(clsArr[i2]);
        }
        if (z) {
            Class<?> cls = clsArr[clsArr.length - 1];
            if (cls.isArray()) {
                this.f1623b = new CoerceLuaToJava.ArrayCoercion(cls.getComponentType());
                return;
            }
            throw new IllegalArgumentException("Last parameter '" + cls + "' is not an array in a vararg member");
        }
        this.f1623b = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long a(Varargs varargs) {
        long j;
        int narg = varargs.narg();
        if (narg == 0 && this.f1622a.length == 0 && this.f1623b == null) {
            return 0L;
        }
        if (narg > this.f1622a.length) {
            j = CoerceLuaToJava.f1608b * (narg - this.f1622a.length);
        } else {
            j = 0;
        }
        if (narg == 0 && this.f1623b != null) {
            j++;
        }
        if (narg < this.f1622a.length) {
            j += (this.f1622a.length - narg) * CoerceLuaToJava.d;
        }
        for (int i = 0; i < this.f1622a.length; i++) {
            j += this.f1622a[i].score(varargs.arg(i + 1));
        }
        if (this.f1623b != null) {
            for (int length = this.f1622a.length; length < narg; length++) {
                j += this.f1623b.score(varargs.arg(length + 1));
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object[] b(Varargs varargs) {
        Object[] objArr;
        if (this.f1623b == null) {
            objArr = new Object[this.f1622a.length];
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = this.f1622a[i].coerce(varargs.arg(i + 1));
            }
        } else {
            int max = Math.max(this.f1622a.length, varargs.narg());
            objArr = new Object[this.f1622a.length + 1];
            for (int i2 = 0; i2 < this.f1622a.length; i2++) {
                objArr[i2] = this.f1622a[i2].coerce(varargs.arg(i2 + 1));
            }
            Object newInstance = Array.newInstance((Class<?>) this.f1623b.f1609a, max - this.f1622a.length);
            objArr[objArr.length - 1] = newInstance;
            int i3 = 0;
            for (int length = this.f1622a.length; length < max; length++) {
                Array.set(newInstance, i3, this.f1623b.f1610b.coerce(varargs.arg(length + 1)));
                i3++;
            }
        }
        return objArr;
    }
}
