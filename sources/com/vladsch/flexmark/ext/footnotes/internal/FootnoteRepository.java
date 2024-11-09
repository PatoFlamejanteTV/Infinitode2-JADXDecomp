package com.vladsch.flexmark.ext.footnotes.internal;

import com.vladsch.flexmark.ext.footnotes.Footnote;
import com.vladsch.flexmark.ext.footnotes.FootnoteBlock;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeRepository;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteRepository.class */
public class FootnoteRepository extends NodeRepository<FootnoteBlock> {
    private ArrayList<FootnoteBlock> referencedFootnoteBlocks;

    public static void resolveFootnotes(Document document) {
        FootnoteRepository footnoteRepository = FootnoteExtension.FOOTNOTES.get(document);
        boolean[] zArr = {false};
        new NodeVisitor(new VisitHandler(Footnote.class, footnote -> {
            FootnoteBlock footnoteBlock;
            if (!footnote.isDefined() && (footnoteBlock = footnote.getFootnoteBlock(footnoteRepository)) != null) {
                footnoteRepository.addFootnoteReference(footnoteBlock, footnote);
                footnote.setFootnoteBlock(footnoteBlock);
                zArr[0] = true;
            }
        })).visit(document);
        if (zArr[0]) {
            footnoteRepository.resolveFootnoteOrdinals();
        }
    }

    public void addFootnoteReference(FootnoteBlock footnoteBlock, Footnote footnote) {
        if (!footnoteBlock.isReferenced()) {
            this.referencedFootnoteBlocks.add(footnoteBlock);
        }
        footnoteBlock.setFirstReferenceOffset(footnote.getStartOffset());
        int footnoteReferences = footnoteBlock.getFootnoteReferences();
        footnoteBlock.setFootnoteReferences(footnoteReferences + 1);
        footnote.setReferenceOrdinal(footnoteReferences);
    }

    public void resolveFootnoteOrdinals() {
        Collections.sort(this.referencedFootnoteBlocks, (footnoteBlock, footnoteBlock2) -> {
            return footnoteBlock.getFirstReferenceOffset() - footnoteBlock2.getFirstReferenceOffset();
        });
        int i = 0;
        Iterator<FootnoteBlock> it = this.referencedFootnoteBlocks.iterator();
        while (it.hasNext()) {
            i++;
            it.next().setFootnoteOrdinal(i);
        }
    }

    public List<FootnoteBlock> getReferencedFootnoteBlocks() {
        return this.referencedFootnoteBlocks;
    }

    public FootnoteRepository(DataHolder dataHolder) {
        super(FootnoteExtension.FOOTNOTES_KEEP.get(dataHolder));
        this.referencedFootnoteBlocks = new ArrayList<>();
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public DataKey<? extends NodeRepository<FootnoteBlock>> getDataKey() {
        return FootnoteExtension.FOOTNOTES;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public DataKey<KeepType> getKeepDataKey() {
        return FootnoteExtension.FOOTNOTES_KEEP;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public Set<FootnoteBlock> getReferencedElements(Node node) {
        HashSet hashSet = new HashSet();
        visitNodes(node, node2 -> {
            FootnoteBlock referenceNode;
            if ((node2 instanceof Footnote) && (referenceNode = ((Footnote) node2).getReferenceNode(this)) != null) {
                hashSet.add(referenceNode);
            }
        }, Footnote.class);
        return hashSet;
    }
}
