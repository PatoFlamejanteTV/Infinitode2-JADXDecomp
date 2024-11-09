package com.vladsch.flexmark.ext.definition.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ext.definition.DefinitionItem;
import com.vladsch.flexmark.ext.definition.DefinitionList;
import com.vladsch.flexmark.ext.definition.DefinitionTerm;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.block.BlockParser;
import com.vladsch.flexmark.parser.block.BlockPreProcessor;
import com.vladsch.flexmark.parser.block.BlockPreProcessorFactory;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.core.ParagraphParser;
import com.vladsch.flexmark.util.ast.BlankLine;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionListItemBlockPreProcessor.class */
public class DefinitionListItemBlockPreProcessor implements BlockPreProcessor {
    private final DefinitionOptions options;
    Boolean blankLinesInAst;

    public DefinitionListItemBlockPreProcessor(DataHolder dataHolder) {
        this.options = new DefinitionOptions(dataHolder);
        this.blankLinesInAst = Parser.BLANK_LINES_IN_AST.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.parser.block.BlockPreProcessor
    public void preProcess(ParserState parserState, Block block) {
        boolean z;
        if (block instanceof DefinitionItem) {
            DefinitionItem definitionItem = (DefinitionItem) block;
            Node previousAnyNot = block.getPreviousAnyNot(BlankLine.class);
            DefinitionList definitionList = new DefinitionList();
            Node next = definitionItem.getNext();
            if (next instanceof BlankLine) {
                next.extractChainTo(definitionList);
            }
            if (!(previousAnyNot instanceof Paragraph)) {
                if (previousAnyNot instanceof DefinitionList) {
                    DefinitionList definitionList2 = (DefinitionList) previousAnyNot;
                    definitionItem.unlink();
                    definitionList2.appendChild(definitionItem);
                    definitionList2.takeChildren(definitionList);
                    definitionList2.setCharsFromContent();
                    return;
                }
                return;
            }
            Paragraph paragraph = (Paragraph) previousAnyNot;
            Node next2 = previousAnyNot.getNext();
            Node previousAnyNot2 = paragraph.getPreviousAnyNot(BlankLine.class);
            Node previous = paragraph.getPrevious();
            Block parent = paragraph.getParent();
            definitionItem.unlink();
            paragraph.unlink();
            parserState.blockRemovedWithChildren(paragraph);
            if (this.options.doubleBlankLineBreaksList) {
                z = (previousAnyNot2 instanceof DefinitionList) && (previousAnyNot2 == null ? BasedSequence.NULL : BasedSequence.of(previousAnyNot2.baseSubSequence(previousAnyNot2.getEndOffset(), paragraph.getStartOffset()).normalizeEOL())).countLeading(CharPredicate.EOL) < 2;
            } else {
                z = previousAnyNot2 instanceof DefinitionList;
            }
            DefinitionList definitionList3 = new DefinitionList();
            definitionList3.setTight(true);
            int i = 0;
            for (BasedSequence basedSequence : paragraph.getContentLines()) {
                Block definitionTerm = new DefinitionTerm();
                BlockParser paragraphParser = new ParagraphParser();
                BlockContent blockContent = new BlockContent();
                int i2 = i;
                i++;
                blockContent.add(basedSequence, paragraph.getLineIndent(i2));
                paragraphParser.getBlock().setContent(blockContent);
                paragraphParser.getBlock().setCharsFromContent();
                definitionTerm.appendChild(paragraphParser.getBlock());
                definitionTerm.setCharsFromContent();
                parserState.blockParserAdded(paragraphParser);
                definitionList3.appendChild(definitionTerm);
                parserState.blockAdded(definitionTerm);
            }
            if (this.blankLinesInAst.booleanValue() && (next2 instanceof BlankLine)) {
                while (next2 instanceof BlankLine) {
                    Node next3 = next2.getNext();
                    next2.unlink();
                    definitionList3.appendChild(next2);
                    next2 = next3;
                }
            }
            definitionList3.appendChild(definitionItem);
            definitionList3.takeChildren(definitionList);
            if (z) {
                DefinitionList definitionList4 = (DefinitionList) previousAnyNot2;
                definitionList4.takeChildren(definitionList3);
                ReversiblePeekingIterator<Node> it = definitionList3.getChildren().iterator();
                while (it.hasNext()) {
                    Node next4 = it.next();
                    next4.unlink();
                    definitionList4.appendChild(next4);
                    parserState.blockAddedWithChildren((Block) next4);
                }
                definitionList4.setCharsFromContent();
                return;
            }
            if (previousAnyNot2 != null) {
                previous.insertAfter(definitionList3);
            } else if (parent.getFirstChild() != null) {
                parent.getFirstChild().insertBefore(definitionList3);
            } else {
                parent.appendChild(definitionList3);
            }
            definitionList3.setCharsFromContent();
            parserState.blockAddedWithChildren(definitionList3);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionListItemBlockPreProcessor$Factory.class */
    public static class Factory implements BlockPreProcessorFactory {
        @Override // com.vladsch.flexmark.parser.block.BlockPreProcessorFactory
        public Set<Class<? extends Block>> getBlockTypes() {
            HashSet hashSet = new HashSet();
            hashSet.add(DefinitionItem.class);
            return hashSet;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
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
            return new DefinitionListItemBlockPreProcessor(parserState.getProperties());
        }
    }
}
