package com.vladsch.flexmark.parser.core;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.parser.InlineParser;
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
import com.vladsch.flexmark.parser.core.BlockQuoteParser;
import com.vladsch.flexmark.parser.core.FencedCodeBlockParser;
import com.vladsch.flexmark.parser.core.HtmlBlockParser;
import com.vladsch.flexmark.parser.core.IndentedCodeBlockParser;
import com.vladsch.flexmark.parser.core.ListBlockParser;
import com.vladsch.flexmark.parser.core.ThematicBreakParser;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInCharsHandler;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInStartsWithCharsHandler;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/HeadingParser.class */
public class HeadingParser extends AbstractBlockParser {
    final Heading block = new Heading();

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/HeadingParser$HeadingParsing.class */
    static class HeadingParsing extends Parsing {
        private final Pattern ATX_HEADING;
        private final Pattern ATX_TRAILING;
        private final Pattern SETEXT_HEADING;

        public HeadingParsing(DataHolder dataHolder) {
            super(dataHolder);
            this.ATX_HEADING = Parser.HEADING_NO_ATX_SPACE.get(dataHolder).booleanValue() ? Pattern.compile("^#{1,6}(?:[ \t]*|$)") : Parser.HEADING_NO_EMPTY_HEADING_WITHOUT_SPACE.get(dataHolder).booleanValue() ? Pattern.compile("^#{1,6}(?:[ \t]*(?=[^ \t#])|[ \t]+$)") : Pattern.compile("^#{1,6}(?:[ \t]+|$)");
            this.ATX_TRAILING = Parser.HEADING_NO_ATX_SPACE.get(dataHolder).booleanValue() ? Pattern.compile("[ \t]*#+[ \t]*$") : Pattern.compile("(^| |\t)[ \t]*#+[ \t]*$");
            int intValue = Parser.HEADING_SETEXT_MARKER_LENGTH.get(dataHolder).intValue();
            this.SETEXT_HEADING = intValue <= 1 ? Pattern.compile("^(?:=+|-+)[ \t]*$") : Pattern.compile("^(?:={" + intValue + ",}|-{" + intValue + ",})[ \t]*$");
        }
    }

    public HeadingParser(int i) {
        this.block.setLevel(i);
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        return BlockContinue.none();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
        inlineParser.parse(this.block.getText(), this.block);
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/HeadingParser$Factory.class */
    public static class Factory implements CustomBlockParserFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            HashSet hashSet = new HashSet();
            hashSet.add(BlockQuoteParser.Factory.class);
            return hashSet;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return new HashSet(Arrays.asList(FencedCodeBlockParser.Factory.class, HtmlBlockParser.Factory.class, ThematicBreakParser.Factory.class, ListBlockParser.Factory.class, IndentedCodeBlockParser.Factory.class));
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }

        @Override // com.vladsch.flexmark.parser.block.CustomBlockParserFactory
        public SpecialLeadInHandler getLeadInHandler(DataHolder dataHolder) {
            return Parser.ESCAPE_HEADING_NO_ATX_SPACE.get(dataHolder).booleanValue() || Parser.HEADING_NO_ATX_SPACE.get(dataHolder).booleanValue() ? HeadingLeadInHandler.HANDLER_NO_SPACE : HeadingLeadInHandler.HANDLER_SPACE;
        }

        @Override // com.vladsch.flexmark.parser.block.CustomBlockParserFactory, java.util.function.Function
        public BlockParserFactory apply(DataHolder dataHolder) {
            return new BlockFactory(dataHolder);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/HeadingParser$HeadingLeadInHandler.class */
    static class HeadingLeadInHandler {
        static final SpecialLeadInHandler HANDLER_NO_SPACE = SpecialLeadInStartsWithCharsHandler.create('#');
        static final SpecialLeadInHandler HANDLER_SPACE = SpecialLeadInCharsHandler.create('#');

        HeadingLeadInHandler() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/HeadingParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private final HeadingOptions options;
        private final HeadingParsing myParsing;

        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.options = new HeadingOptions(dataHolder);
            this.myParsing = new HeadingParsing(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            if (parserState.getIndent() >= 4 || (this.options.noLeadSpace && parserState.getIndent() > 0)) {
                return BlockStart.none();
            }
            if (parserState.getActiveBlockParser() instanceof FencedCodeBlockParser) {
                return BlockStart.none();
            }
            if (!this.options.canInterruptItemParagraph) {
                BlockParser blockParser = matchedBlockParser.getBlockParser();
                if (blockParser.isParagraphParser() && (blockParser.getBlock().getParent() instanceof ListItem) && blockParser.getBlock() == blockParser.getBlock().getParent().getFirstChild()) {
                    return BlockStart.none();
                }
            }
            BasedSequence line = parserState.getLine();
            int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
            BasedSequence paragraphContent = matchedBlockParser.getParagraphContent();
            BasedSequence subSequence = line.subSequence(nextNonSpaceIndex, line.length());
            Matcher matcher = this.myParsing.ATX_HEADING.matcher(subSequence);
            if (!matcher.find()) {
                Matcher matcher2 = this.myParsing.SETEXT_HEADING.matcher(subSequence);
                if (matcher2.find()) {
                    if (paragraphContent != null) {
                        int i = matcher2.group(0).charAt(0) == '=' ? 1 : 2;
                        BlockContent blockContent = new BlockContent();
                        blockContent.addAll(matchedBlockParser.getParagraphLines(), matchedBlockParser.getParagraphEolLengths());
                        BasedSequence trim = blockContent.getContents().trim();
                        BasedSequence trim2 = line.trim();
                        HeadingParser headingParser = new HeadingParser(i);
                        headingParser.block.setText(trim);
                        headingParser.block.setClosingMarker(trim2);
                        headingParser.block.setCharsFromContent();
                        return BlockStart.of(headingParser).atIndex(line.length()).replaceActiveBlockParser();
                    }
                    return BlockStart.none();
                }
                return BlockStart.none();
            }
            int length = nextNonSpaceIndex + matcher.group(0).length();
            int start = matcher.start();
            int end = matcher.end();
            BasedSequence trim3 = subSequence.subSequence(start, end).trim();
            int length2 = trim3.length();
            new BlockContent().add(parserState.getLineWithEOL().subSequence(length), parserState.getIndent());
            BasedSequence subSequence2 = subSequence.subSequence(end);
            BasedSequence basedSequence = null;
            Matcher matcher3 = this.myParsing.ATX_TRAILING.matcher(subSequence2);
            if (matcher3.find()) {
                int start2 = matcher3.start();
                basedSequence = subSequence2.subSequence(start2, matcher3.end()).trim();
                subSequence2 = subSequence2.subSequence(0, start2);
            }
            HeadingParser headingParser2 = new HeadingParser(length2);
            headingParser2.block.setOpeningMarker(trim3);
            headingParser2.block.setText(subSequence2.trim());
            headingParser2.block.setClosingMarker(basedSequence);
            headingParser2.block.setCharsFromContent();
            return BlockStart.of(headingParser2).atIndex(line.length());
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/HeadingParser$HeadingOptions.class */
    static class HeadingOptions {
        final boolean noAtxSpace;
        final boolean noEmptyHeadingWithoutSpace;
        final boolean noLeadSpace;
        final boolean canInterruptItemParagraph;
        final int setextMarkerLength;

        public HeadingOptions(DataHolder dataHolder) {
            this.noAtxSpace = Parser.HEADING_NO_ATX_SPACE.get(dataHolder).booleanValue();
            this.noEmptyHeadingWithoutSpace = Parser.HEADING_NO_EMPTY_HEADING_WITHOUT_SPACE.get(dataHolder).booleanValue();
            this.noLeadSpace = Parser.HEADING_NO_LEAD_SPACE.get(dataHolder).booleanValue();
            this.canInterruptItemParagraph = Parser.HEADING_CAN_INTERRUPT_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
            this.setextMarkerLength = Parser.HEADING_SETEXT_MARKER_LENGTH.get(dataHolder).intValue();
        }
    }
}
