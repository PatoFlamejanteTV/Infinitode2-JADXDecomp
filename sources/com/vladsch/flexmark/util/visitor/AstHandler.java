package com.vladsch.flexmark.util.visitor;

import com.vladsch.flexmark.util.visitor.AstAction;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/visitor/AstHandler.class */
public abstract class AstHandler<N, A extends AstAction<? super N>> {
    private final Class<? extends N> aClass;
    private final A adapter;

    public AstHandler(Class<? extends N> cls, A a2) {
        this.aClass = cls;
        this.adapter = a2;
    }

    public Class<? extends N> getNodeType() {
        return this.aClass;
    }

    public A getAdapter() {
        return this.adapter;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AstHandler astHandler = (AstHandler) obj;
        return this.aClass == astHandler.aClass && this.adapter == astHandler.adapter;
    }

    public int hashCode() {
        return (this.aClass.hashCode() * 31) + this.adapter.hashCode();
    }
}
