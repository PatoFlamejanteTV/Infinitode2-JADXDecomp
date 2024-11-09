package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.ImageRef;
import com.vladsch.flexmark.ast.LinkRef;
import com.vladsch.flexmark.ast.RefNode;
import com.vladsch.flexmark.ast.Reference;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeRepository;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.sequence.Escaping;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/ReferenceRepository.class */
public class ReferenceRepository extends NodeRepository<Reference> {
    public ReferenceRepository(DataHolder dataHolder) {
        super(Parser.REFERENCES_KEEP.get(dataHolder));
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public DataKey<? extends NodeRepository<Reference>> getDataKey() {
        return Parser.REFERENCES;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public DataKey<KeepType> getKeepDataKey() {
        return Parser.REFERENCES_KEEP;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public String normalizeKey(CharSequence charSequence) {
        return Escaping.normalizeReference(charSequence, true);
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public Set<Reference> getReferencedElements(Node node) {
        HashSet hashSet = new HashSet();
        visitNodes(node, node2 -> {
            Reference referenceNode;
            if ((node2 instanceof RefNode) && (referenceNode = ((RefNode) node2).getReferenceNode(this)) != null) {
                hashSet.add(referenceNode);
            }
        }, LinkRef.class, ImageRef.class);
        return hashSet;
    }
}
