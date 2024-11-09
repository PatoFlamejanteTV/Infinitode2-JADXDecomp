package com.vladsch.flexmark.ext.abbreviation.internal;

import com.vladsch.flexmark.ext.abbreviation.Abbreviation;
import com.vladsch.flexmark.ext.abbreviation.AbbreviationBlock;
import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeRepository;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationRepository.class */
public class AbbreviationRepository extends NodeRepository<AbbreviationBlock> {
    public AbbreviationRepository(DataHolder dataHolder) {
        super(AbbreviationExtension.ABBREVIATIONS_KEEP.get(dataHolder));
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public DataKey<? extends NodeRepository<AbbreviationBlock>> getDataKey() {
        return AbbreviationExtension.ABBREVIATIONS;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public DataKey<KeepType> getKeepDataKey() {
        return AbbreviationExtension.ABBREVIATIONS_KEEP;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public Set<AbbreviationBlock> getReferencedElements(Node node) {
        HashSet hashSet = new HashSet();
        visitNodes(node, node2 -> {
            AbbreviationBlock referenceNode;
            if ((node2 instanceof Abbreviation) && (referenceNode = ((Abbreviation) node2).getReferenceNode(this)) != null) {
                hashSet.add(referenceNode);
            }
        }, Abbreviation.class);
        return hashSet;
    }
}
