package com.prineside.luaj;

import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/luaj/TailcallVarargs.class */
public final class TailcallVarargs extends Varargs {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1506a = TLog.forClass(TailcallVarargs.class);

    /* renamed from: b, reason: collision with root package name */
    private LuaValue f1507b;
    private Varargs c;
    private Varargs d;

    public TailcallVarargs(LuaValue luaValue, Varargs varargs) {
        this.f1507b = luaValue;
        this.c = varargs;
    }

    @Override // com.prineside.luaj.Varargs
    public final boolean isTailcall() {
        return true;
    }

    @Override // com.prineside.luaj.Varargs
    public final Varargs eval() {
        int i = 0;
        while (this.d == null) {
            Varargs onInvoke = this.f1507b.onInvoke(this.c);
            if (onInvoke.isTailcall()) {
                TailcallVarargs tailcallVarargs = (TailcallVarargs) onInvoke;
                this.f1507b = tailcallVarargs.f1507b;
                this.c = tailcallVarargs.c;
                i++;
                if (i == 5000000) {
                    f1506a.e("Tail call failed after 5,000,000 attempts", new Object[0]);
                    return LuaValue.NIL;
                }
            } else {
                this.d = onInvoke;
                this.f1507b = null;
                this.c = null;
            }
        }
        return this.d;
    }

    @Override // com.prineside.luaj.Varargs
    public final LuaValue arg(int i) {
        if (this.d == null) {
            eval();
        }
        return this.d.arg(i);
    }

    @Override // com.prineside.luaj.Varargs
    public final LuaValue arg1() {
        if (this.d == null) {
            eval();
        }
        return this.d.arg1();
    }

    @Override // com.prineside.luaj.Varargs
    public final int narg() {
        if (this.d == null) {
            eval();
        }
        return this.d.narg();
    }

    @Override // com.prineside.luaj.Varargs
    public final Varargs subargs(int i) {
        if (i <= 1) {
            LuaValue.argerror(1, "start must be > 1");
        }
        if (this.d == null) {
            eval();
        }
        return this.d.subargs(i);
    }
}
