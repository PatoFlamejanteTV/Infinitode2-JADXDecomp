package com.prineside.luaj.ast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/NameScope.class */
public class NameScope {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<String> f1525a = new HashSet();
    public final Map<String, Variable> namedVariables;
    public final NameScope outerScope;
    public int functionNestingCount;

    static {
        String[] strArr = {"and", "break", "do", "else", "elseif", "end", "false", "for", "function", "if", "in", "local", "nil", "not", "or", "repeat", "return", "then", "true", "until", "while"};
        for (int i = 0; i < 21; i++) {
            f1525a.add(strArr[i]);
        }
    }

    public NameScope() {
        this.namedVariables = new HashMap();
        this.outerScope = null;
        this.functionNestingCount = 0;
    }

    public NameScope(NameScope nameScope) {
        this.namedVariables = new HashMap();
        this.outerScope = nameScope;
        this.functionNestingCount = nameScope != null ? nameScope.functionNestingCount : 0;
    }

    public Variable find(String str) {
        a(str);
        NameScope nameScope = this;
        while (true) {
            NameScope nameScope2 = nameScope;
            if (nameScope2 != null) {
                if (!nameScope2.namedVariables.containsKey(str)) {
                    nameScope = nameScope2.outerScope;
                } else {
                    return nameScope2.namedVariables.get(str);
                }
            } else {
                Variable variable = new Variable(str);
                this.namedVariables.put(str, variable);
                return variable;
            }
        }
    }

    public Variable define(String str) {
        a(str);
        Variable variable = new Variable(str, this);
        this.namedVariables.put(str, variable);
        return variable;
    }

    private static void a(String str) {
        if (f1525a.contains(str)) {
            throw new IllegalArgumentException("name is a keyword: '" + str + "'");
        }
    }
}
