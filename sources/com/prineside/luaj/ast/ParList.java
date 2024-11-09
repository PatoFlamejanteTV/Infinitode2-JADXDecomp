package com.prineside.luaj.ast;

import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/prineside/luaj/ast/ParList.class */
public class ParList extends SyntaxElement {
    public static final List<Name> EMPTY_NAMELIST = new ArrayList();
    public static final ParList EMPTY_PARLIST = new ParList(EMPTY_NAMELIST, false);
    public final List<Name> names;
    public final boolean isvararg;

    public ParList(List<Name> list, boolean z) {
        this.names = list;
        this.isvararg = z;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
