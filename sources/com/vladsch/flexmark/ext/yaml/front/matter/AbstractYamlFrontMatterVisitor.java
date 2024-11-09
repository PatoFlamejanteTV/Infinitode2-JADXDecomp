package com.vladsch.flexmark.ext.yaml.front.matter;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/AbstractYamlFrontMatterVisitor.class */
public class AbstractYamlFrontMatterVisitor implements YamlFrontMatterVisitor {
    private final NodeVisitor myVisitor = new NodeVisitor(YamlFrontMatterVisitorExt.VISIT_HANDLERS(this));
    private final Map<String, List<String>> data = new LinkedHashMap();

    public void visit(Node node) {
        this.myVisitor.visit(node);
    }

    @Override // com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterVisitor
    public void visit(YamlFrontMatterNode yamlFrontMatterNode) {
        this.data.put(yamlFrontMatterNode.getKey(), yamlFrontMatterNode.getValues());
    }

    @Override // com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterVisitor
    public void visit(YamlFrontMatterBlock yamlFrontMatterBlock) {
        this.myVisitor.visitChildren(yamlFrontMatterBlock);
    }

    public Map<String, List<String>> getData() {
        return this.data;
    }
}
