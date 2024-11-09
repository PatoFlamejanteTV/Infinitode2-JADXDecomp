package com.vladsch.flexmark.ext.enumerated.reference;

import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeRepository;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/EnumeratedReferenceRepository.class */
public class EnumeratedReferenceRepository extends NodeRepository<EnumeratedReferenceBlock> {
    private ArrayList<EnumeratedReferenceBlock> referencedEnumeratedReferenceBlocks;

    public static String getType(String str) {
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf > 0) {
            return str.subSequence(0, lastIndexOf).toString();
        }
        return "";
    }

    public List<EnumeratedReferenceBlock> getReferencedEnumeratedReferenceBlocks() {
        return this.referencedEnumeratedReferenceBlocks;
    }

    public EnumeratedReferenceRepository(DataHolder dataHolder) {
        super(EnumeratedReferenceExtension.ENUMERATED_REFERENCES_KEEP.get(dataHolder));
        this.referencedEnumeratedReferenceBlocks = new ArrayList<>();
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public DataKey<? extends NodeRepository<EnumeratedReferenceBlock>> getDataKey() {
        return EnumeratedReferenceExtension.ENUMERATED_REFERENCES;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public DataKey<KeepType> getKeepDataKey() {
        return EnumeratedReferenceExtension.ENUMERATED_REFERENCES_KEEP;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public Set<EnumeratedReferenceBlock> getReferencedElements(Node node) {
        HashSet hashSet = new HashSet();
        visitNodes(node, node2 -> {
            EnumeratedReferenceBlock referenceNode;
            if ((node2 instanceof EnumeratedReferenceBase) && (referenceNode = ((EnumeratedReferenceBase) node2).getReferenceNode(this)) != null) {
                hashSet.add(referenceNode);
            }
        }, EnumeratedReferenceText.class, EnumeratedReferenceLink.class);
        return hashSet;
    }
}
