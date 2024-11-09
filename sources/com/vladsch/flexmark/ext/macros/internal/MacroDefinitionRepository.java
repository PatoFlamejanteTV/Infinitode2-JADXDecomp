package com.vladsch.flexmark.ext.macros.internal;

import com.vladsch.flexmark.ext.macros.MacroDefinitionBlock;
import com.vladsch.flexmark.ext.macros.MacroReference;
import com.vladsch.flexmark.ext.macros.MacrosExtension;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeRepository;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacroDefinitionRepository.class */
public class MacroDefinitionRepository extends NodeRepository<MacroDefinitionBlock> {
    private final ArrayList<MacroDefinitionBlock> myReferencedMacroDefinitionBlocks;

    public void addMacrosReference(MacroDefinitionBlock macroDefinitionBlock, MacroReference macroReference) {
        if (!macroDefinitionBlock.isReferenced()) {
            this.myReferencedMacroDefinitionBlocks.add(macroDefinitionBlock);
        }
        macroDefinitionBlock.setFirstReferenceOffset(macroReference.getStartOffset());
    }

    public void resolveMacrosOrdinals() {
        this.myReferencedMacroDefinitionBlocks.sort(Comparator.comparing((v0) -> {
            return v0.getFirstReferenceOffset();
        }));
        int i = 0;
        Iterator<MacroDefinitionBlock> it = this.myReferencedMacroDefinitionBlocks.iterator();
        while (it.hasNext()) {
            i++;
            it.next().setOrdinal(i);
        }
    }

    public List<MacroDefinitionBlock> getReferencedMacroDefinitionBlocks() {
        return this.myReferencedMacroDefinitionBlocks;
    }

    public MacroDefinitionRepository(DataHolder dataHolder) {
        super(MacrosExtension.MACRO_DEFINITIONS_KEEP.get(dataHolder));
        this.myReferencedMacroDefinitionBlocks = new ArrayList<>();
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public DataKey<? extends NodeRepository<MacroDefinitionBlock>> getDataKey() {
        return MacrosExtension.MACRO_DEFINITIONS;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public DataKey<KeepType> getKeepDataKey() {
        return MacrosExtension.MACRO_DEFINITIONS_KEEP;
    }

    @Override // com.vladsch.flexmark.util.ast.NodeRepository
    public Set<MacroDefinitionBlock> getReferencedElements(Node node) {
        HashSet hashSet = new HashSet();
        visitNodes(node, node2 -> {
            MacroDefinitionBlock referenceNode;
            if ((node2 instanceof MacroReference) && (referenceNode = ((MacroReference) node2).getReferenceNode(this)) != null) {
                hashSet.add(referenceNode);
            }
        }, MacroReference.class);
        return hashSet;
    }
}
