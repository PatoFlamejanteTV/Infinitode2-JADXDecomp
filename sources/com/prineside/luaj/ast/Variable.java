package com.prineside.luaj.ast;

import com.prineside.luaj.LuaValue;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Variable.class */
public class Variable {
    public final String name;
    public final NameScope definingScope;
    public boolean isupvalue;
    public boolean hasassignments;
    public LuaValue initialValue;

    public Variable(String str) {
        this.name = str;
        this.definingScope = null;
    }

    public Variable(String str, NameScope nameScope) {
        this.name = str;
        this.definingScope = nameScope;
    }

    public boolean isLocal() {
        return this.definingScope != null;
    }

    public boolean isConstant() {
        return (this.hasassignments || this.initialValue == null) ? false : true;
    }
}
