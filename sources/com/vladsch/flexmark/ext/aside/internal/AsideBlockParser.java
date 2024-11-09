package com.vladsch.flexmark.ext.aside.internal;

import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.ext.aside.AsideBlock;
import com.vladsch.flexmark.ext.aside.AsideExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.block.AbstractBlockParser;
import com.vladsch.flexmark.parser.block.AbstractBlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockContinue;
import com.vladsch.flexmark.parser.block.BlockParser;
import com.vladsch.flexmark.parser.block.BlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockStart;
import com.vladsch.flexmark.parser.block.CustomBlockParserFactory;
import com.vladsch.flexmark.parser.block.MatchedBlockParser;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.core.FencedCodeBlockParser;
import com.vladsch.flexmark.parser.core.HeadingParser;
import com.vladsch.flexmark.parser.core.HtmlBlockParser;
import com.vladsch.flexmark.parser.core.IndentedCodeBlockParser;
import com.vladsch.flexmark.parser.core.ListBlockParser;
import com.vladsch.flexmark.parser.core.ThematicBreakParser;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInStartsWithCharsHandler;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/aside/internal/AsideBlockParser.class */
public class AsideBlockParser extends AbstractBlockParser {
    public static final char MARKER_CHAR = '|';
    private final boolean allowLeadingSpace;
    private final boolean continueToBlankLine;
    private final boolean ignoreBlankLine;
    private final boolean interruptsParagraph;
    private final boolean interruptsItemParagraph;
    private final boolean withLeadSpacesInterruptsItemParagraph;
    private final AsideBlock block = new AsideBlock();
    private int lastWasBlankLine = 0;

    public AsideBlockParser(DataHolder dataHolder, BasedSequence basedSequence) {
        this.block.setOpeningMarker(basedSequence);
        this.continueToBlankLine = AsideExtension.EXTEND_TO_BLANK_LINE.get(dataHolder).booleanValue();
        this.allowLeadingSpace = AsideExtension.ALLOW_LEADING_SPACE.get(dataHolder).booleanValue();
        this.ignoreBlankLine = AsideExtension.IGNORE_BLANK_LINE.get(dataHolder).booleanValue();
        this.interruptsParagraph = AsideExtension.INTERRUPTS_PARAGRAPH.get(dataHolder).booleanValue();
        this.interruptsItemParagraph = AsideExtension.INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
        this.withLeadSpacesInterruptsItemParagraph = AsideExtension.WITH_LEAD_SPACES_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isPropagatingLastBlankLine(BlockParser blockParser) {
        return false;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canContain(ParserState parserState, BlockParser blockParser, Block block) {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public AsideBlock getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.block.setCharsFromContent();
        if (!Parser.BLANK_LINES_IN_AST.get(parserState.getProperties()).booleanValue()) {
            removeBlankLines();
        }
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        boolean isMarker;
        int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
        if (!parserState.isBlank() && ((isMarker = isMarker(parserState, nextNonSpaceIndex, false, false, this.allowLeadingSpace, this.interruptsParagraph, this.interruptsItemParagraph, this.withLeadSpacesInterruptsItemParagraph)) || (this.continueToBlankLine && this.lastWasBlankLine == 0))) {
            int column = parserState.getColumn() + parserState.getIndent();
            this.lastWasBlankLine = 0;
            if (isMarker) {
                column++;
                if (Parsing.isSpaceOrTab(parserState.getLine(), nextNonSpaceIndex + 1)) {
                    column++;
                }
            }
            return BlockContinue.atColumn(column);
        }
        if (this.ignoreBlankLine && parserState.isBlank()) {
            this.lastWasBlankLine++;
            return BlockContinue.atColumn(parserState.getColumn() + parserState.getIndent());
        }
        return BlockContinue.none();
    }

    static boolean isMarker(ParserState parserState, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        BasedSequence line = parserState.getLine();
        if ((!z || z4) && i < line.length() && line.charAt(i) == '|') {
            if (!z3 && parserState.getIndent() != 0) {
                return false;
            }
            if (!z2 || z5) {
                return (!z2 || z6) ? parserState.getIndent() < parserState.getParsing().CODE_BLOCK_INDENT : parserState.getIndent() == 0;
            }
            return false;
        }
        return false;
    }

    static boolean endsWithMarker(BasedSequence basedSequence) {
        int countTrailing = basedSequence.countTrailing(CharPredicate.WHITESPACE_NBSP);
        return countTrailing + 1 < basedSequence.length() && basedSequence.charAt((basedSequence.length() - countTrailing) - 1) == '|';
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/aside/internal/AsideBlockParser$Factory.class */
    public static class Factory implements CustomBlockParserFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return new HashSet();
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return new HashSet(Arrays.asList(HeadingParser.Factory.class, FencedCodeBlockParser.Factory.class, HtmlBlockParser.Factory.class, ThematicBreakParser.Factory.class, ListBlockParser.Factory.class, IndentedCodeBlockParser.Factory.class));
        }

        @Override // com.vladsch.flexmark.parser.block.CustomBlockParserFactory
        public SpecialLeadInHandler getLeadInHandler(DataHolder dataHolder) {
            return AsideLeadInHandler.HANDLER;
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

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/aside/internal/AsideBlockParser$AsideLeadInHandler.class */
    static class AsideLeadInHandler {
        static final SpecialLeadInHandler HANDLER = SpecialLeadInStartsWithCharsHandler.create('|');

        AsideLeadInHandler() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/aside/internal/AsideBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private final boolean allowLeadingSpace;
        private final boolean interruptsParagraph;
        private final boolean interruptsItemParagraph;
        private final boolean withLeadSpacesInterruptsItemParagraph;

        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.allowLeadingSpace = AsideExtension.ALLOW_LEADING_SPACE.get(dataHolder).booleanValue();
            this.interruptsParagraph = AsideExtension.INTERRUPTS_PARAGRAPH.get(dataHolder).booleanValue();
            this.interruptsItemParagraph = AsideExtension.INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
            this.withLeadSpacesInterruptsItemParagraph = AsideExtension.WITH_LEAD_SPACES_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
            BlockParser blockParser = matchedBlockParser.getBlockParser();
            boolean isParagraphParser = blockParser.isParagraphParser();
            boolean z = isParagraphParser && (blockParser.getBlock().getParent() instanceof ListItem) && blockParser.getBlock() == blockParser.getBlock().getParent().getFirstChild();
            if (!AsideBlockParser.endsWithMarker(parserState.getLine()) && AsideBlockParser.isMarker(parserState, nextNonSpaceIndex, isParagraphParser, z, this.allowLeadingSpace, this.interruptsParagraph, this.interruptsItemParagraph, this.withLeadSpacesInterruptsItemParagraph)) {
                int column = parserState.getColumn() + parserState.getIndent() + 1;
                if (Parsing.isSpaceOrTab(parserState.getLine(), nextNonSpaceIndex + 1)) {
                    column++;
                }
                return BlockStart.of(new AsideBlockParser(parserState.getProperties(), parserState.getLine().subSequence(nextNonSpaceIndex, nextNonSpaceIndex + 1))).atColumn(column);
            }
            return BlockStart.none();
        }
    }
}
