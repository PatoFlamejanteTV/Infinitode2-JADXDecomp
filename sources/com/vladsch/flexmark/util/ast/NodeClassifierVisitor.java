package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.collection.CopyOnWriteRef;
import com.vladsch.flexmark.util.collection.OrderedMap;
import com.vladsch.flexmark.util.collection.OrderedSet;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/NodeClassifierVisitor.class */
public class NodeClassifierVisitor extends NodeVisitorBase implements NodeTracker {
    private final OrderedMap<Class<?>, Set<Class<?>>> exclusionMap;
    private final OrderedSet<Class<?>> exclusionSet;
    private final HashMap<Integer, BitSet> nodeAncestryMap;
    private static final BitSet EMPTY_SET;
    private final ClassifyingNodeTracker classifyingNodeTracker;
    static final /* synthetic */ boolean $assertionsDisabled;
    private final Stack<BitSet> nodeAncestryBitSetStack = new Stack<>();
    private final CopyOnWriteRef<BitSet> nodeAncestryBitSet = new CopyOnWriteRef<>(new BitSet(), bitSet -> {
        return bitSet != null ? (BitSet) bitSet.clone() : new BitSet();
    });
    private boolean isClassificationDone = false;

    static {
        $assertionsDisabled = !NodeClassifierVisitor.class.desiredAssertionStatus();
        EMPTY_SET = new BitSet();
    }

    public NodeClassifierVisitor(Map<Class<? extends Node>, Set<Class<?>>> map) {
        this.classifyingNodeTracker = new ClassifyingNodeTracker(this, map);
        this.exclusionMap = this.classifyingNodeTracker.getExclusionMap();
        this.nodeAncestryMap = this.classifyingNodeTracker.getNodeAncestryMap();
        this.exclusionSet = this.classifyingNodeTracker.getExclusionSet();
    }

    public ClassifyingNodeTracker classify(Node node) {
        if (!$assertionsDisabled && this.isClassificationDone) {
            throw new AssertionError();
        }
        visit(node);
        this.isClassificationDone = true;
        return this.classifyingNodeTracker;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeVisitorBase
    public void visit(Node node) {
        visitChildren(node);
    }

    @Override // com.vladsch.flexmark.util.ast.NodeTracker
    public void nodeRemoved(Node node) {
    }

    @Override // com.vladsch.flexmark.util.ast.NodeTracker
    public void nodeRemovedWithChildren(Node node) {
    }

    @Override // com.vladsch.flexmark.util.ast.NodeTracker
    public void nodeRemovedWithDescendants(Node node) {
    }

    @Override // com.vladsch.flexmark.util.ast.NodeTracker
    public void nodeAddedWithChildren(Node node) {
        nodeAdded(node);
    }

    @Override // com.vladsch.flexmark.util.ast.NodeTracker
    public void nodeAddedWithDescendants(Node node) {
        nodeAdded(node);
    }

    @Override // com.vladsch.flexmark.util.ast.NodeTracker
    public void nodeAdded(Node node) {
        if (this.isClassificationDone) {
            if (node.getParent() == null) {
                throw new IllegalStateException("Node must be inserted into the document before calling node tracker nodeAdded functions");
            }
            if (!(node.getParent() instanceof Document)) {
                int indexOf = this.classifyingNodeTracker.getItems().indexOf(node.getParent());
                if (indexOf != -1) {
                    this.nodeAncestryBitSet.setValue(this.nodeAncestryMap.get(Integer.valueOf(indexOf)));
                } else {
                    throw new IllegalStateException("Parent node: " + node.getParent() + " of " + node + " is not tracked, some post processor forgot to call tracker.nodeAdded().");
                }
            }
            this.nodeAncestryBitSetStack.clear();
            visit(node);
        }
    }

    void pushNodeAncestry() {
        if (!this.exclusionMap.isEmpty()) {
            this.nodeAncestryBitSetStack.push(this.nodeAncestryBitSet.getImmutable());
        }
    }

    void popNodeAncestry() {
        this.nodeAncestryBitSet.setValue(this.nodeAncestryBitSetStack.pop());
    }

    boolean updateNodeAncestry(Node node, CopyOnWriteRef<BitSet> copyOnWriteRef) {
        BitSet bitSet;
        if (!this.exclusionMap.isEmpty() && !(node instanceof Document)) {
            BitSet peek = copyOnWriteRef.getPeek();
            if (!$assertionsDisabled && peek == null) {
                throw new AssertionError();
            }
            int indexOf = this.classifyingNodeTracker.getItems().indexOf(node);
            if (indexOf == -1) {
                throw new IllegalStateException("Node: " + node + " is not tracked, some post processor forgot to call tracker.nodeAdded().");
            }
            if (this.exclusionSet != null && !this.exclusionSet.isEmpty()) {
                for (Class<?> cls : this.exclusionSet) {
                    if (cls.isInstance(node)) {
                        int indexOf2 = this.exclusionSet.indexOf(cls);
                        if (!$assertionsDisabled && indexOf2 == -1) {
                            throw new AssertionError();
                        }
                        if (peek.get(indexOf2)) {
                            continue;
                        } else {
                            peek = copyOnWriteRef.getMutable();
                            if (!$assertionsDisabled && peek == null) {
                                throw new AssertionError();
                            }
                            peek.set(indexOf2);
                        }
                    }
                }
            }
            if (this.isClassificationDone && this.nodeAncestryBitSetStack.size() > 1 && (bitSet = this.nodeAncestryMap.get(Integer.valueOf(indexOf))) != null && bitSet.equals(peek)) {
                return false;
            }
            if (!peek.isEmpty()) {
                this.nodeAncestryMap.put(Integer.valueOf(indexOf), copyOnWriteRef.getImmutable());
                return true;
            }
            return true;
        }
        return true;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeVisitorBase
    public void visitChildren(Node node) {
        if (!this.isClassificationDone && !(node instanceof Document)) {
            this.classifyingNodeTracker.nodeAdded(node);
        }
        if (node.getFirstChild() != null) {
            pushNodeAncestry();
            if (updateNodeAncestry(node, this.nodeAncestryBitSet)) {
                super.visitChildren(node);
            }
            popNodeAncestry();
            return;
        }
        updateNodeAncestry(node, this.nodeAncestryBitSet);
    }
}
