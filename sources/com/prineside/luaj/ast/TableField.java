package com.prineside.luaj.ast;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/TableField.class */
public class TableField extends SyntaxElement {
    public final Exp index;
    public final String name;
    public final Exp rhs;

    public TableField(Exp exp, String str, Exp exp2) {
        this.index = exp;
        this.name = str;
        this.rhs = exp2;
    }

    public static TableField keyedField(Exp exp, Exp exp2) {
        return new TableField(exp, null, exp2);
    }

    public static TableField namedField(String str, Exp exp) {
        return new TableField(null, str, exp);
    }

    public static TableField listField(Exp exp) {
        return new TableField(null, null, exp);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
