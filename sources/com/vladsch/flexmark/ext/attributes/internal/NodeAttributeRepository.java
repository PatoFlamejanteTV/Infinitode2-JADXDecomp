package com.vladsch.flexmark.ext.attributes.internal;

import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.attributes.AttributesNode;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/NodeAttributeRepository.class */
public class NodeAttributeRepository implements Map<Node, ArrayList<AttributesNode>> {
    protected final HashMap<Node, ArrayList<AttributesNode>> nodeAttributesHashMap = new HashMap<>();

    public NodeAttributeRepository(DataHolder dataHolder) {
    }

    public DataKey<NodeAttributeRepository> getDataKey() {
        return AttributesExtension.NODE_ATTRIBUTES;
    }

    public DataKey<KeepType> getKeepDataKey() {
        return AttributesExtension.ATTRIBUTES_KEEP;
    }

    @Override // java.util.Map
    public int size() {
        return this.nodeAttributesHashMap.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.nodeAttributesHashMap.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.nodeAttributesHashMap.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.nodeAttributesHashMap.containsValue(obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    public ArrayList<AttributesNode> get(Object obj) {
        return this.nodeAttributesHashMap.get(obj);
    }

    @Override // java.util.Map
    public ArrayList<AttributesNode> put(Node node, ArrayList<AttributesNode> arrayList) {
        return this.nodeAttributesHashMap.put(node, arrayList);
    }

    public ArrayList<AttributesNode> put(Node node, AttributesNode attributesNode) {
        ArrayList<AttributesNode> arrayList = this.nodeAttributesHashMap.get(node);
        ArrayList<AttributesNode> arrayList2 = arrayList;
        if (arrayList == null) {
            arrayList2 = new ArrayList<>();
            this.nodeAttributesHashMap.put(node, arrayList2);
        }
        arrayList2.add(attributesNode);
        return arrayList2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    public ArrayList<AttributesNode> remove(Object obj) {
        return this.nodeAttributesHashMap.remove(obj);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends Node, ? extends ArrayList<AttributesNode>> map) {
        this.nodeAttributesHashMap.putAll(map);
    }

    @Override // java.util.Map
    public void clear() {
        this.nodeAttributesHashMap.clear();
    }

    @Override // java.util.Map
    public Set<Node> keySet() {
        return this.nodeAttributesHashMap.keySet();
    }

    @Override // java.util.Map
    public Collection<ArrayList<AttributesNode>> values() {
        return this.nodeAttributesHashMap.values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<Node, ArrayList<AttributesNode>>> entrySet() {
        return this.nodeAttributesHashMap.entrySet();
    }
}
