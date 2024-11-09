package com.vladsch.flexmark.ext.yaml.front.matter;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/YamlFrontMatterVisitor.class */
public interface YamlFrontMatterVisitor {
    void visit(YamlFrontMatterNode yamlFrontMatterNode);

    void visit(YamlFrontMatterBlock yamlFrontMatterBlock);
}
