package com.vladsch.flexmark.ext.macros;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/MacrosVisitor.class */
public interface MacrosVisitor {
    void visit(MacroReference macroReference);

    void visit(MacroDefinitionBlock macroDefinitionBlock);
}
