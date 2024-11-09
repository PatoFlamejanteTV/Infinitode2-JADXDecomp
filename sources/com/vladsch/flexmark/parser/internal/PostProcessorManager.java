package com.vladsch.flexmark.parser.internal;

import com.vladsch.flexmark.parser.PostProcessor;
import com.vladsch.flexmark.parser.PostProcessorFactory;
import com.vladsch.flexmark.util.ast.ClassifyingNodeTracker;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeClassifierVisitor;
import com.vladsch.flexmark.util.collection.OrderedSet;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.dependency.DependencyResolver;
import com.vladsch.flexmark.util.dependency.DependentItem;
import com.vladsch.flexmark.util.dependency.DependentItemMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/PostProcessorManager.class */
public class PostProcessorManager {
    private final List<PostProcessorDependencyStage> postProcessorDependencies;
    private final OrderedSet<Node> allPostProcessNodes = new OrderedSet<>();
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !PostProcessorManager.class.desiredAssertionStatus();
    }

    public PostProcessorManager(List<PostProcessorDependencyStage> list) {
        this.postProcessorDependencies = list;
    }

    public static List<PostProcessorDependencyStage> calculatePostProcessors(DataHolder dataHolder, List<PostProcessorFactory> list) {
        List resolveDependencies = DependencyResolver.resolveDependencies(list, PostProcessorManager::prioritizePostProcessors, null);
        ArrayList arrayList = new ArrayList(resolveDependencies.size());
        Iterator it = resolveDependencies.iterator();
        while (it.hasNext()) {
            arrayList.add(new PostProcessorDependencyStage((List) it.next()));
        }
        return arrayList;
    }

    public static Document processDocument(Document document, List<PostProcessorDependencyStage> list) {
        if (!list.isEmpty()) {
            document = new PostProcessorManager(list).postProcess(document);
        }
        return document;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Document postProcess(Document document) {
        int indexOf;
        BitSet bitSet;
        ClassifyingNodeTracker classifyingNodeTracker = null;
        for (PostProcessorDependencyStage postProcessorDependencyStage : this.postProcessorDependencies) {
            boolean z = false;
            for (PostProcessorFactory postProcessorFactory : postProcessorDependencyStage.dependents) {
                if (postProcessorFactory.affectsGlobalScope()) {
                    document = postProcessorFactory.apply(document).processDocument(document);
                    z = true;
                    classifyingNodeTracker = null;
                } else {
                    if (!$assertionsDisabled && z) {
                        throw new AssertionError();
                    }
                    if (classifyingNodeTracker == null) {
                        classifyingNodeTracker = new NodeClassifierVisitor(postProcessorDependencyStage.myNodeMap).classify(document);
                    }
                    Map<Class<?>, Set<Class<?>>> nodeTypes = postProcessorFactory.getNodeTypes();
                    PostProcessor apply = postProcessorFactory.apply(document);
                    BitSet bitSet2 = new BitSet();
                    if (nodeTypes != null) {
                        Iterator<Set<Class<?>>> it = nodeTypes.values().iterator();
                        while (it.hasNext()) {
                            bitSet2.or(classifyingNodeTracker.getExclusionSet().indexBitSet(it.next()));
                        }
                        ReversibleIterator it2 = classifyingNodeTracker.getCategoryItems(Node.class, nodeTypes.keySet()).iterator();
                        while (it2.hasNext()) {
                            Node node = (Node) it2.next();
                            if (node.getParent() != null) {
                                Set<Class<?>> set = nodeTypes.get(node.getClass());
                                if (set != null && (indexOf = classifyingNodeTracker.getItems().indexOf(node)) != -1 && (bitSet = classifyingNodeTracker.getNodeAncestryMap().get(Integer.valueOf(indexOf))) != null) {
                                    BitSet indexBitSet = classifyingNodeTracker.getExclusionSet().indexBitSet(set);
                                    indexBitSet.and(bitSet);
                                    if (indexBitSet.isEmpty()) {
                                    }
                                }
                                apply.process(classifyingNodeTracker, node);
                            }
                        }
                    }
                }
            }
        }
        return document;
    }

    static DependentItemMap<PostProcessorFactory> prioritizePostProcessors(DependentItemMap<PostProcessorFactory> dependentItemMap) {
        List<Map.Entry<Class<?>, DependentItem<D>>> entries = dependentItemMap.entries();
        entries.sort((entry, entry2) -> {
            return (((DependentItem) entry.getValue()).isGlobalScope ? 1 : 0) - (((DependentItem) entry2.getValue()).isGlobalScope ? 1 : 0);
        });
        if (dependentItemMap.keySet().keyDifferenceBitSet(entries).isEmpty()) {
            return dependentItemMap;
        }
        DependentItemMap<PostProcessorFactory> dependentItemMap2 = new DependentItemMap<>(entries.size());
        dependentItemMap2.addAll(entries);
        return dependentItemMap2;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/PostProcessorManager$PostProcessorDependencyStage.class */
    public static class PostProcessorDependencyStage {
        final Map<Class<? extends Node>, Set<Class<?>>> myNodeMap;
        final List<PostProcessorFactory> dependents;

        public PostProcessorDependencyStage(List<PostProcessorFactory> list) {
            HashMap hashMap = new HashMap();
            for (PostProcessorFactory postProcessorFactory : list) {
                Map<Class<?>, Set<Class<?>>> nodeTypes = postProcessorFactory.getNodeTypes();
                if ((nodeTypes == null || nodeTypes.isEmpty()) && !postProcessorFactory.affectsGlobalScope()) {
                    throw new IllegalStateException("PostProcessorFactory " + postProcessorFactory + " is not document post processor and has empty node map, does nothing, should not be registered.");
                }
                if (nodeTypes != null) {
                    for (Map.Entry<Class<?>, Set<Class<?>>> entry : nodeTypes.entrySet()) {
                        if (Node.class.isAssignableFrom(entry.getKey())) {
                            Set set = (Set) hashMap.get(entry.getKey());
                            Set<Class<?>> value = entry.getValue();
                            if (set == null) {
                                hashMap.put(entry.getKey(), new HashSet(value));
                            } else {
                                try {
                                    set.addAll(value);
                                } catch (UnsupportedOperationException unused) {
                                    new HashSet(set).addAll(value);
                                }
                            }
                        }
                    }
                }
            }
            this.dependents = list;
            this.myNodeMap = hashMap;
        }
    }
}
