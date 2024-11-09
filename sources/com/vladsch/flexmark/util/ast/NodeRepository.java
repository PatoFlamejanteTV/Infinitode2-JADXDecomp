package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.data.DataKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/NodeRepository.class */
public abstract class NodeRepository<T> implements Map<String, T> {
    protected final ArrayList<T> nodeList = new ArrayList<>();
    protected final Map<String, T> nodeMap = new HashMap();
    protected final KeepType keepType;

    public abstract DataKey<? extends NodeRepository<T>> getDataKey();

    public abstract DataKey<KeepType> getKeepDataKey();

    public abstract Set<T> getReferencedElements(Node node);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object put(String str, Object obj) {
        return put2(str, (String) obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SafeVarargs
    public final void visitNodes(Node node, Consumer<Node> consumer, Class<? extends Node>... clsArr) {
        NodeVisitor nodeVisitor = new NodeVisitor();
        for (Class<? extends Node> cls : clsArr) {
            consumer.getClass();
            nodeVisitor.addHandler(new VisitHandler(cls, (v1) -> {
                r4.accept(v1);
            }));
        }
        nodeVisitor.visit(node);
    }

    public NodeRepository(KeepType keepType) {
        this.keepType = keepType == null ? KeepType.LOCKED : keepType;
    }

    public String normalizeKey(CharSequence charSequence) {
        return charSequence.toString();
    }

    public T getFromRaw(CharSequence charSequence) {
        return this.nodeMap.get(normalizeKey(charSequence));
    }

    public T putRawKey(CharSequence charSequence, T t) {
        return put2(normalizeKey(charSequence), (String) t);
    }

    public Collection<T> getValues() {
        return this.nodeMap.values();
    }

    public static <T> boolean transferReferences(NodeRepository<T> nodeRepository, NodeRepository<T> nodeRepository2, boolean z, Map<String, String> map) {
        boolean z2 = false;
        for (Map.Entry<String, T> entry : nodeRepository2.entrySet()) {
            String key = entry.getKey();
            if (map != null) {
                map.getOrDefault(key, key);
            }
            if (!z || !nodeRepository.containsKey(key)) {
                nodeRepository.put2(key, (String) entry.getValue());
                z2 = true;
            }
        }
        return z2;
    }

    /* renamed from: put, reason: avoid collision after fix types in other method */
    public T put2(String str, T t) {
        T t2;
        this.nodeList.add(t);
        if (this.keepType == KeepType.LOCKED) {
            throw new IllegalStateException("Not allowed to modify LOCKED repository");
        }
        if (this.keepType != KeepType.LAST && (t2 = this.nodeMap.get(str)) != null) {
            if (this.keepType == KeepType.FAIL) {
                throw new IllegalStateException("Duplicate key " + str);
            }
            return t2;
        }
        return this.nodeMap.put(str, t);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends T> map) {
        if (this.keepType == KeepType.LOCKED) {
            throw new IllegalStateException("Not allowed to modify LOCKED repository");
        }
        if (this.keepType != KeepType.LAST) {
            for (String str : map.keySet()) {
                this.nodeMap.put(str, map.get(str));
            }
            return;
        }
        this.nodeMap.putAll(map);
    }

    @Override // java.util.Map
    public T remove(Object obj) {
        if (this.keepType == KeepType.LOCKED) {
            throw new IllegalStateException("Not allowed to modify LOCKED repository");
        }
        return this.nodeMap.remove(obj);
    }

    @Override // java.util.Map
    public void clear() {
        if (this.keepType == KeepType.LOCKED) {
            throw new IllegalStateException("Not allowed to modify LOCKED repository");
        }
        this.nodeMap.clear();
    }

    @Override // java.util.Map
    public int size() {
        return this.nodeMap.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.nodeMap.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.nodeMap.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.nodeMap.containsValue(obj);
    }

    @Override // java.util.Map
    public T get(Object obj) {
        return this.nodeMap.get(obj);
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.nodeMap.keySet();
    }

    @Override // java.util.Map
    public List<T> values() {
        return this.nodeList;
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, T>> entrySet() {
        return this.nodeMap.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.nodeMap.equals(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.nodeMap.hashCode();
    }
}
