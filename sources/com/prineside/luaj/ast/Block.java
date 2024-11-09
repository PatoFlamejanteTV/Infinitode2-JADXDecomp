package com.prineside.luaj.ast;

import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/Block.class */
public class Block extends Stat {
    public List<Stat> stats = new ArrayList();
    public NameScope scope;

    public void add(Stat stat) {
        if (stat == null) {
            return;
        }
        this.stats.add(stat);
    }

    @Override // com.prineside.luaj.ast.Stat
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
