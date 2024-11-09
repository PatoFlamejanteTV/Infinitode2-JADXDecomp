package com.vladsch.flexmark.ext.definition;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/DefinitionVisitor.class */
public interface DefinitionVisitor {
    void visit(DefinitionList definitionList);

    void visit(DefinitionTerm definitionTerm);

    void visit(DefinitionItem definitionItem);
}
