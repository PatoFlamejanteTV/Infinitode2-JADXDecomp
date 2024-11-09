package com.prineside.luaj.ast;

import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/FuncName.class */
public class FuncName extends SyntaxElement {
    public final Name name;
    public List<String> dots;
    public String method;

    public FuncName(String str) {
        this.name = new Name(str);
    }

    public void adddot(String str) {
        if (this.dots == null) {
            this.dots = new ArrayList();
        }
        this.dots.add(str);
    }
}
