package com.vladsch.flexmark.ext.yaml.front.matter;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/YamlFrontMatterValue.class */
public class YamlFrontMatterValue extends Node {
    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public YamlFrontMatterValue() {
    }

    public YamlFrontMatterValue(BasedSequence basedSequence) {
        super(basedSequence);
    }
}
