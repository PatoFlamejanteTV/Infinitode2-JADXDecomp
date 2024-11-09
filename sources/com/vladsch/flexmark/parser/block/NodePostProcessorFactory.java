package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.parser.PostProcessorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/block/NodePostProcessorFactory.class */
public abstract class NodePostProcessorFactory implements PostProcessorFactory {
    private final HashMap<Class<?>, Set<Class<?>>> NODE_MAP = new HashMap<>();

    @Override // com.vladsch.flexmark.parser.PostProcessorFactory, java.util.function.Function
    public abstract NodePostProcessor apply(Document document);

    public NodePostProcessorFactory(boolean z) {
    }

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    public Set<Class<?>> getAfterDependents() {
        return null;
    }

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    public Set<Class<?>> getBeforeDependents() {
        return null;
    }

    @Override // com.vladsch.flexmark.util.dependency.Dependent
    public final boolean affectsGlobalScope() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addNodeWithExclusions(Class<? extends Node> cls, Class<?>... clsArr) {
        if (clsArr.length > 0) {
            this.NODE_MAP.put(cls, new HashSet(Arrays.asList(clsArr)));
        } else {
            addNodes(cls);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addNodes(Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            this.NODE_MAP.put(cls, Collections.EMPTY_SET);
        }
    }

    @Override // com.vladsch.flexmark.parser.PostProcessorFactory
    public final Map<Class<?>, Set<Class<?>>> getNodeTypes() {
        return this.NODE_MAP;
    }
}
