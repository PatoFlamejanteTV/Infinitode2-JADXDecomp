package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.collection.ClassificationBag;
import com.vladsch.flexmark.util.collection.SubClassingBag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/NodeCollectingVisitor.class */
public class NodeCollectingVisitor {
    public static final Function<Node, Class<?>> NODE_CLASSIFIER = (v0) -> {
        return v0.getClass();
    };
    private static final Class<?>[] EMPTY_CLASSES = new Class[0];
    private final HashMap<Class<?>, List<Class<?>>> subClassMap = new HashMap<>();
    private final HashSet<Class<?>> included = new HashSet<>();
    private final HashSet<Class<?>> excluded;
    private final ClassificationBag<Class<?>, Node> nodes;
    private final Class<?>[] classes;

    public NodeCollectingVisitor(Set<Class<?>> set) {
        this.classes = (Class[]) set.toArray(EMPTY_CLASSES);
        this.included.addAll(set);
        for (Class<?> cls : set) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(cls);
            this.subClassMap.put(cls, arrayList);
        }
        this.excluded = new HashSet<>();
        this.nodes = new ClassificationBag<>(NODE_CLASSIFIER);
    }

    public void collect(Node node) {
        visit(node);
    }

    public SubClassingBag<Node> getSubClassingBag() {
        return new SubClassingBag<>(this.nodes, this.subClassMap);
    }

    private void visit(Node node) {
        Class<?> cls = node.getClass();
        if (this.included.contains(cls)) {
            this.nodes.add(node);
        } else if (!this.excluded.contains(cls)) {
            for (Class<?> cls2 : this.classes) {
                if (cls2.isInstance(node)) {
                    this.included.add(cls);
                    List<Class<?>> list = this.subClassMap.get(cls2);
                    if (list == null) {
                        ArrayList arrayList = new ArrayList(2);
                        arrayList.add(cls2);
                        arrayList.add(cls);
                        this.subClassMap.put(cls2, arrayList);
                    } else {
                        list.add(cls);
                    }
                    this.nodes.add(node);
                    visitChildren(node);
                    return;
                }
            }
            this.excluded.add(cls);
        }
        visitChildren(node);
    }

    private void visitChildren(Node node) {
        Node firstChild = node.getFirstChild();
        while (true) {
            Node node2 = firstChild;
            if (node2 != null) {
                Node next = node2.getNext();
                visit(node2);
                firstChild = next;
            } else {
                return;
            }
        }
    }
}
