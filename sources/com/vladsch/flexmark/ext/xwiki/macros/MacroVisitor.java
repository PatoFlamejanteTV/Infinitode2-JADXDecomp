package com.vladsch.flexmark.ext.xwiki.macros;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/MacroVisitor.class */
public interface MacroVisitor {
    void visit(Macro macro);

    void visit(MacroClose macroClose);

    void visit(MacroBlock macroBlock);
}
