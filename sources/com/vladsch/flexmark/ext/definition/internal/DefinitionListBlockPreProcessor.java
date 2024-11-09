package com.vladsch.flexmark.ext.definition.internal;

import com.vladsch.flexmark.ext.definition.DefinitionItem;
import com.vladsch.flexmark.ext.definition.DefinitionList;
import com.vladsch.flexmark.ext.definition.internal.DefinitionListItemBlockPreProcessor;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.block.BlockPreProcessor;
import com.vladsch.flexmark.parser.block.BlockPreProcessorFactory;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionListBlockPreProcessor.class */
public class DefinitionListBlockPreProcessor implements BlockPreProcessor {
    private final DefinitionOptions options;

    public DefinitionListBlockPreProcessor(DataHolder dataHolder) {
        this.options = new DefinitionOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.parser.block.BlockPreProcessor
    public void preProcess(ParserState parserState, Block block) {
        Boolean bool = Parser.BLANK_LINES_IN_AST.get(parserState.getProperties());
        if (block instanceof DefinitionList) {
            DefinitionList definitionList = (DefinitionList) block;
            boolean isTight = definitionList.isTight();
            if (this.options.autoLoose && isTight) {
                ReversiblePeekingIterator<Node> it = definitionList.getChildren().iterator();
                while (it.hasNext()) {
                    Node next = it.next();
                    if (next instanceof DefinitionItem) {
                        if (((DefinitionItem) next).isLoose()) {
                            isTight = false;
                            if (!bool.booleanValue()) {
                                break;
                            }
                        }
                        if (bool.booleanValue()) {
                            next.moveTrailingBlankLines();
                        }
                    }
                }
                definitionList.setTight(isTight);
            }
            if (bool.booleanValue()) {
                definitionList.moveTrailingBlankLines();
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionListBlockPreProcessor$Factory.class */
    public static class Factory implements BlockPreProcessorFactory {
        @Override // com.vladsch.flexmark.parser.block.BlockPreProcessorFactory
        public Set<Class<? extends Block>> getBlockTypes() {
            HashSet hashSet = new HashSet();
            hashSet.add(DefinitionList.class);
            return hashSet;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            HashSet hashSet = new HashSet();
            hashSet.add(DefinitionListItemBlockPreProcessor.Factory.class);
            return hashSet;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return true;
        }

        @Override // com.vladsch.flexmark.parser.block.BlockPreProcessorFactory, java.util.function.Function
        public BlockPreProcessor apply(ParserState parserState) {
            return new DefinitionListBlockPreProcessor(parserState.getProperties());
        }
    }
}
