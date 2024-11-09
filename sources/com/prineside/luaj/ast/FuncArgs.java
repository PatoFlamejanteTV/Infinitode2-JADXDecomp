package com.prineside.luaj.ast;

import com.prineside.luaj.LuaString;
import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/FuncArgs.class */
public class FuncArgs extends SyntaxElement {
    public final List<Exp> exps;

    public static FuncArgs explist(List<Exp> list) {
        return new FuncArgs(list);
    }

    public static FuncArgs tableconstructor(TableConstructor tableConstructor) {
        return new FuncArgs(tableConstructor);
    }

    public static FuncArgs string(LuaString luaString) {
        return new FuncArgs(luaString);
    }

    public FuncArgs(List<Exp> list) {
        this.exps = list;
    }

    public FuncArgs(LuaString luaString) {
        this.exps = new ArrayList();
        this.exps.add(Exp.constant(luaString));
    }

    public FuncArgs(TableConstructor tableConstructor) {
        this.exps = new ArrayList();
        this.exps.add(tableConstructor);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
