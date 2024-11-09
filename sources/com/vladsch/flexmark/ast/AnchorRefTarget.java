package com.vladsch.flexmark.ast;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/AnchorRefTarget.class */
public interface AnchorRefTarget {
    String getAnchorRefText();

    String getAnchorRefId();

    void setAnchorRefId(String str);

    boolean isExplicitAnchorRefId();

    void setExplicitAnchorRefId(boolean z);
}
