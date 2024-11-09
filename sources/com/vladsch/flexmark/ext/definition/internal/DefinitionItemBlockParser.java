package com.vladsch.flexmark.ext.definition.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.definition.DefinitionItem;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.parser.block.AbstractBlockParser;
import com.vladsch.flexmark.parser.block.AbstractBlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockContinue;
import com.vladsch.flexmark.parser.block.BlockParser;
import com.vladsch.flexmark.parser.block.BlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockStart;
import com.vladsch.flexmark.parser.block.CustomBlockParserFactory;
import com.vladsch.flexmark.parser.block.MatchedBlockParser;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.core.DocumentBlockParser;
import com.vladsch.flexmark.parser.core.ParagraphParser;
import com.vladsch.flexmark.util.ast.BlankLine;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInCharsHandler;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionItemBlockParser.class */
public class DefinitionItemBlockParser extends AbstractBlockParser {
    private final DefinitionItem block = new DefinitionItem();
    private final DefinitionOptions options;
    private final ItemData itemData;
    private boolean hadBlankLine;

    DefinitionItemBlockParser(DataHolder dataHolder, ItemData itemData) {
        this.options = new DefinitionOptions(dataHolder);
        this.itemData = itemData;
        this.block.setOpeningMarker(itemData.itemMarker);
        this.block.setTight(itemData.isTight);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionItemBlockParser$ItemData.class */
    public static class ItemData {
        final boolean isEmpty;
        final boolean isTight;
        final int markerIndex;
        final int markerColumn;
        final int markerIndent;
        final int contentOffset;
        final BasedSequence itemMarker;

        ItemData(boolean z, boolean z2, int i, int i2, int i3, int i4, BasedSequence basedSequence) {
            this.isEmpty = z;
            this.isTight = z2;
            this.markerIndex = i;
            this.markerColumn = i2;
            this.markerIndent = i3;
            this.contentOffset = i4;
            this.itemMarker = basedSequence;
        }
    }

    private int getContentIndent() {
        return this.itemData.markerIndent + this.itemData.itemMarker.length() + this.itemData.contentOffset;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canContain(ParserState parserState, BlockParser blockParser, Block block) {
        return true;
    }

    static ItemData parseItemMarker(DefinitionOptions definitionOptions, ParserState parserState, boolean z) {
        BasedSequence line = parserState.getLine();
        int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
        int column = parserState.getColumn() + parserState.getIndent();
        int indent = parserState.getIndent();
        BasedSequence subSequence = line.subSequence(nextNonSpaceIndex, line.length());
        char firstChar = subSequence.firstChar();
        if ((firstChar != ':' || !definitionOptions.colonMarker) && (firstChar != '~' || !definitionOptions.tildeMarker)) {
            return null;
        }
        int i = 0;
        boolean z2 = false;
        int i2 = nextNonSpaceIndex + 1;
        while (true) {
            if (i2 >= line.length()) {
                break;
            }
            char charAt = line.charAt(i2);
            if (charAt == '\t') {
                i += Parsing.columnsToNextTabStop(column + 1 + i);
            } else if (charAt == ' ') {
                i++;
            } else {
                z2 = true;
                break;
            }
            i2++;
        }
        if (z2 && i < definitionOptions.markerSpaces) {
            return null;
        }
        if (!z2 || (definitionOptions.myParserEmulationProfile == ParserEmulationProfile.COMMONMARK && i > definitionOptions.newItemCodeIndent)) {
            i = 1;
        }
        return new ItemData(!z2, z, nextNonSpaceIndex, column, indent, i, subSequence.subSequence(0, 1));
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        Node firstChild = this.block.getFirstChild();
        boolean z = firstChild == null;
        if (parserState.isBlank()) {
            if (z || firstChild.getNext() == null) {
                this.block.setHadBlankAfterItemParagraph(true);
            }
            this.hadBlankLine = true;
            return BlockContinue.atIndex(parserState.getNextNonSpaceIndex());
        }
        ParserEmulationProfile parserEmulationProfile = this.options.myParserEmulationProfile.family;
        if (parserEmulationProfile != ParserEmulationProfile.COMMONMARK && parserEmulationProfile != ParserEmulationProfile.KRAMDOWN && parserEmulationProfile != ParserEmulationProfile.MARKDOWN) {
            if (parserEmulationProfile == ParserEmulationProfile.FIXED_INDENT) {
                int indent = parserState.getIndent();
                int column = parserState.getColumn() + this.options.itemIndent;
                if (indent >= this.options.itemIndent) {
                    return BlockContinue.atColumn(column);
                }
                if (z) {
                    return BlockContinue.atIndex(parserState.getIndex() + indent);
                }
                if (parseItemMarker(this.options, parserState, false) != null) {
                    return BlockContinue.none();
                }
                if (!this.hadBlankLine) {
                    return BlockContinue.atIndex(parserState.getIndex() + indent);
                }
            }
        } else {
            int indent2 = parserState.getIndent();
            int column2 = parserState.getColumn() + getContentIndent();
            if (indent2 >= getContentIndent()) {
                return BlockContinue.atColumn(column2);
            }
            if (z) {
                return BlockContinue.atIndex(parserState.getIndex() + indent2);
            }
            if (parseItemMarker(this.options, parserState, false) != null) {
                return BlockContinue.none();
            }
            if (!this.hadBlankLine) {
                return BlockContinue.atIndex(parserState.getIndex() + indent2);
            }
        }
        return BlockContinue.none();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.block.setCharsFromContent();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionItemBlockParser$Factory.class */
    public static class Factory implements CustomBlockParserFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.parser.block.CustomBlockParserFactory
        public SpecialLeadInHandler getLeadInHandler(DataHolder dataHolder) {
            boolean booleanValue = DefinitionExtension.COLON_MARKER.get(dataHolder).booleanValue();
            boolean booleanValue2 = DefinitionExtension.TILDE_MARKER.get(dataHolder).booleanValue();
            if (booleanValue && booleanValue2) {
                return DefinitionLeadInHandler.HANDLER_COLON_TILDE;
            }
            if (booleanValue) {
                return DefinitionLeadInHandler.HANDLER_COLON;
            }
            if (booleanValue2) {
                return DefinitionLeadInHandler.HANDLER_TILDE;
            }
            return null;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }

        @Override // com.vladsch.flexmark.parser.block.CustomBlockParserFactory, java.util.function.Function
        public BlockParserFactory apply(DataHolder dataHolder) {
            return new BlockFactory(dataHolder);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionItemBlockParser$DefinitionLeadInHandler.class */
    static class DefinitionLeadInHandler {
        static final SpecialLeadInHandler HANDLER_COLON_TILDE = SpecialLeadInCharsHandler.create(":~");
        static final SpecialLeadInHandler HANDLER_COLON = SpecialLeadInCharsHandler.create(":");
        static final SpecialLeadInHandler HANDLER_TILDE = SpecialLeadInCharsHandler.create("~");

        DefinitionLeadInHandler() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionItemBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private final DefinitionOptions options;

        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.options = new DefinitionOptions(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            ItemData parseItemMarker;
            BlockParser blockParser = matchedBlockParser.getBlockParser();
            if (!(blockParser instanceof DocumentBlockParser)) {
                if (!(blockParser instanceof DefinitionItemBlockParser) && !(blockParser instanceof ParagraphParser)) {
                    return BlockStart.none();
                }
            } else {
                Block block = (Block) ((DocumentBlockParser) blockParser).getBlock().getLastChildAnyNot(BlankLine.class);
                if (!(block instanceof Paragraph) && !(block instanceof DefinitionItem)) {
                    return BlockStart.none();
                }
                if (this.options.doubleBlankLineBreaksList) {
                    block.setCharsFromContent();
                    if (BasedSequence.of(parserState.getLine().baseSubSequence(block.getEndOffset(), parserState.getLine().getStartOffset()).normalizeEOL()).countLeading(CharPredicate.EOL) >= 2) {
                        return BlockStart.none();
                    }
                }
            }
            ParserEmulationProfile parserEmulationProfile = this.options.myParserEmulationProfile;
            if (parserState.getIndent() < ((parserEmulationProfile == ParserEmulationProfile.COMMONMARK || parserEmulationProfile == ParserEmulationProfile.FIXED_INDENT) ? this.options.codeIndent : this.options.itemIndent) && (parseItemMarker = DefinitionItemBlockParser.parseItemMarker(this.options, parserState, parserState.getActiveBlockParser() instanceof ParagraphParser)) != null) {
                return BlockStart.of(new DefinitionItemBlockParser(parserState.getProperties(), parseItemMarker)).atColumn(parseItemMarker.markerColumn + parseItemMarker.itemMarker.length() + parseItemMarker.contentOffset);
            }
            return BlockStart.none();
        }
    }
}
