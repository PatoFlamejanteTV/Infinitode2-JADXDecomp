package com.prineside.luaj.ast;

import java.util.List;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/TableConstructor.class */
public class TableConstructor extends Exp {
    public List<TableField> fields;

    @Override // com.prineside.luaj.ast.Exp
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
