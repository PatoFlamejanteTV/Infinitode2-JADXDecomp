package com.prineside.luaj.compiler;

import com.prineside.luaj.Globals;
import com.prineside.luaj.LuaClosure;
import com.prineside.luaj.LuaFunction;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Prototype;
import java.io.InputStream;
import java.util.Hashtable;

/* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LuaC.class */
public class LuaC extends Constants implements Globals.Compiler, Globals.Loader {
    public static final LuaC instance = new LuaC();

    public static void install(Globals globals) {
        globals.compiler = instance;
        globals.loader = instance;
    }

    @Override // com.prineside.luaj.Globals.Compiler
    public Prototype compile(InputStream inputStream, String str) {
        return new CompileState().a(inputStream, str);
    }

    @Override // com.prineside.luaj.Globals.Loader
    public LuaFunction load(Prototype prototype, String str, LuaValue luaValue) {
        return new LuaClosure(prototype.toFixedProto(), luaValue);
    }

    public LuaValue load(InputStream inputStream, String str, Globals globals) {
        return new LuaClosure(compile(inputStream, str).toFixedProto(), globals);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LuaC$CompileState.class */
    public static class CompileState {

        /* renamed from: a, reason: collision with root package name */
        int f1556a = 0;

        /* renamed from: b, reason: collision with root package name */
        private Hashtable f1557b = new Hashtable();

        protected CompileState() {
        }

        final Prototype a(InputStream inputStream, String str) {
            LexState lexState = new LexState(this, inputStream);
            FuncState funcState = new FuncState();
            lexState.f1536b = funcState;
            lexState.a(this, inputStream.read(), inputStream, LuaValue.valueOf(str));
            funcState.f1528a = new Prototype();
            funcState.f1528a.source = LuaValue.valueOf(str);
            lexState.mainfunc(funcState);
            LuaC.a(funcState.f1529b == null);
            LuaC.a(lexState.d == null || (lexState.d.f1540b == 0 && lexState.d.d == 0 && lexState.d.f == 0));
            return funcState.f1528a;
        }

        public LuaString newTString(String str) {
            return cachedLuaString(LuaString.valueOf(str));
        }

        public LuaString newTString(LuaString luaString) {
            return cachedLuaString(luaString);
        }

        public LuaString cachedLuaString(LuaString luaString) {
            LuaString luaString2 = (LuaString) this.f1557b.get(luaString);
            if (luaString2 != null) {
                return luaString2;
            }
            this.f1557b.put(luaString, luaString);
            return luaString;
        }

        public String pushfstring(String str) {
            return str;
        }
    }
}
