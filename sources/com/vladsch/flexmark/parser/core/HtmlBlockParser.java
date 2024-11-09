package com.vladsch.flexmark.parser.core;

import com.vladsch.flexmark.ast.HtmlBlock;
import com.vladsch.flexmark.ast.HtmlBlockBase;
import com.vladsch.flexmark.ast.HtmlCommentBlock;
import com.vladsch.flexmark.ast.HtmlInnerBlock;
import com.vladsch.flexmark.ast.HtmlInnerBlockComment;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.util.Parsing;
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
import com.vladsch.flexmark.parser.core.HeadingParser;
import com.vladsch.flexmark.parser.core.IndentedCodeBlockParser;
import com.vladsch.flexmark.parser.core.ListBlockParser;
import com.vladsch.flexmark.parser.core.ThematicBreakParser;
import com.vladsch.flexmark.parser.internal.HtmlDeepParser;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/HtmlBlockParser.class */
public class HtmlBlockParser extends AbstractBlockParser {
    public static final String HTML_COMMENT_OPEN = "<!--";
    public static final String HTML_COMMENT_CLOSE = "-->";
    private final HtmlBlockBase block;
    private final Pattern closingPattern;
    private final HtmlDeepParser deepParser;
    private boolean finished = false;
    private BlockContent content = new BlockContent();
    private final boolean parseInnerHtmlComments;
    private final boolean myHtmlBlockDeepParseNonBlock;
    private final boolean myHtmlBlockDeepParseBlankLineInterrupts;
    private final boolean myHtmlBlockDeepParseMarkdownInterruptsClosed;
    private final boolean myHtmlBlockDeepParseBlankLineInterruptsPartialTag;
    private final boolean myHtmlBlockDeepParseIndentedCodeInterrupts;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/HtmlBlockParser$Patterns.class */
    private static class Patterns {
        public final int COMMENT_PATTERN_INDEX = 2;
        public final Pattern[][] BLOCK_PATTERNS;

        /* JADX WARN: Type inference failed for: r1v6, types: [java.util.regex.Pattern[], java.util.regex.Pattern[][]] */
        public Patterns(Parsing parsing, DataHolder dataHolder) {
            StringBuilder sb = new StringBuilder();
            String str = "";
            Iterator<String> it = Parser.HTML_BLOCK_TAGS.get(dataHolder).iterator();
            while (it.hasNext()) {
                sb.append(str).append("\\Q").append(it.next()).append("\\E");
                str = "|";
            }
            if (Parser.HTML_FOR_TRANSLATOR.get(dataHolder).booleanValue()) {
                sb.append(str).append(Parser.TRANSLATION_HTML_BLOCK_TAG_PATTERN.get(dataHolder));
            }
            this.BLOCK_PATTERNS = new Pattern[]{new Pattern[]{null, null}, new Pattern[]{Pattern.compile("^<(?:script|pre|style)(?:\\s|>|$)", 2), Pattern.compile("</(?:script|pre|style)>", 2)}, new Pattern[]{Pattern.compile("^<!--"), Pattern.compile("-->")}, new Pattern[]{Pattern.compile("^<[?]"), Pattern.compile("\\?>")}, new Pattern[]{Pattern.compile("^<![A-Z]"), Pattern.compile(">")}, new Pattern[]{Pattern.compile("^<!\\[CDATA\\["), Pattern.compile("\\]\\]>")}, new Pattern[]{Pattern.compile("^</?(?:" + sb.toString() + ")(?:\\s|[/]?[>]|$)", 2), null}, new Pattern[]{Pattern.compile("^(?:" + parsing.OPENTAG + '|' + parsing.CLOSETAG + ")\\s*$", 2), null}};
        }
    }

    HtmlBlockParser(DataHolder dataHolder, Pattern pattern, boolean z, HtmlDeepParser htmlDeepParser) {
        this.closingPattern = pattern;
        this.block = z ? new HtmlCommentBlock() : new HtmlBlock();
        this.deepParser = htmlDeepParser;
        this.parseInnerHtmlComments = Parser.PARSE_INNER_HTML_COMMENTS.get(dataHolder).booleanValue();
        this.myHtmlBlockDeepParseNonBlock = Parser.HTML_BLOCK_DEEP_PARSE_NON_BLOCK.get(dataHolder).booleanValue();
        this.myHtmlBlockDeepParseBlankLineInterrupts = Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS.get(dataHolder).booleanValue();
        this.myHtmlBlockDeepParseMarkdownInterruptsClosed = Parser.HTML_BLOCK_DEEP_PARSE_MARKDOWN_INTERRUPTS_CLOSED.get(dataHolder).booleanValue();
        this.myHtmlBlockDeepParseBlankLineInterruptsPartialTag = Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG.get(dataHolder).booleanValue();
        this.myHtmlBlockDeepParseIndentedCodeInterrupts = Parser.HTML_BLOCK_DEEP_PARSE_INDENTED_CODE_INTERRUPTS.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        if (this.deepParser != null) {
            if (parserState.isBlank() && (this.deepParser.isHtmlClosed() || ((this.myHtmlBlockDeepParseBlankLineInterrupts && !this.deepParser.haveOpenRawTag()) || (this.myHtmlBlockDeepParseBlankLineInterruptsPartialTag && this.deepParser.isBlankLineInterruptible())))) {
                return BlockContinue.none();
            }
            return BlockContinue.atIndex(parserState.getIndex());
        }
        if (this.finished) {
            return BlockContinue.none();
        }
        if (parserState.isBlank() && this.closingPattern == null) {
            return BlockContinue.none();
        }
        return BlockContinue.atIndex(parserState.getIndex());
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
        if (this.deepParser != null) {
            if (this.content.getLineCount() > 0) {
                this.deepParser.parseHtmlChunk(basedSequence, false, this.myHtmlBlockDeepParseNonBlock, false);
            }
        } else if (this.closingPattern != null && this.closingPattern.matcher(basedSequence).find()) {
            this.finished = true;
        }
        this.content.add(basedSequence, parserState.getIndent());
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canInterruptBy(BlockParserFactory blockParserFactory) {
        if (!this.myHtmlBlockDeepParseMarkdownInterruptsClosed || this.deepParser == null || (blockParserFactory instanceof Factory)) {
            return false;
        }
        return (this.myHtmlBlockDeepParseIndentedCodeInterrupts || !(blockParserFactory instanceof IndentedCodeBlockParser.BlockFactory)) && this.deepParser.isHtmlClosed();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canContain(ParserState parserState, BlockParser blockParser, Block block) {
        return false;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isInterruptible() {
        return this.myHtmlBlockDeepParseMarkdownInterruptsClosed && this.deepParser != null && this.deepParser.isHtmlClosed();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isRawText() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        int indexOf;
        int indexOf2;
        this.block.setContent(this.content);
        this.content = null;
        if (!(this.block instanceof HtmlCommentBlock) && this.parseInnerHtmlComments) {
            int i = 0;
            BasedSequence contentChars = this.block.getContentChars();
            BasedSequence basedSequence = contentChars;
            if (contentChars.eolEndLength() > 0) {
                basedSequence = basedSequence.midSequence(0, -1);
            }
            int length = basedSequence.length();
            while (i < length && (indexOf = basedSequence.indexOf("<!--", i)) >= 0 && (indexOf2 = basedSequence.indexOf("-->", indexOf + 4)) >= 0) {
                if (i < indexOf) {
                    this.block.appendChild(new HtmlInnerBlock(basedSequence.subSequence(i, indexOf)));
                }
                i = indexOf2 + 3;
                this.block.appendChild(new HtmlInnerBlockComment(basedSequence.subSequence(indexOf, i)));
            }
            if (i > 0 && i < basedSequence.length()) {
                this.block.appendChild(new HtmlInnerBlock(basedSequence.subSequence(i, basedSequence.length())));
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/HtmlBlockParser$Factory.class */
    public static class Factory implements CustomBlockParserFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return new HashSet(Arrays.asList(BlockQuoteParser.Factory.class, HeadingParser.Factory.class, FencedCodeBlockParser.Factory.class));
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return new HashSet(Arrays.asList(ThematicBreakParser.Factory.class, ListBlockParser.Factory.class, IndentedCodeBlockParser.Factory.class));
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/HtmlBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private Patterns myPatterns;
        private final boolean myHtmlCommentBlocksInterruptParagraph;
        private final boolean myHtmlBlockDeepParser;
        private final boolean myHtmlBlockDeepParseNonBlock;
        private final boolean myHtmlBlockDeepParseFirstOpenTagOnOneLine;
        private final boolean myHtmlBlockCommentOnlyFullLine;
        private final boolean myHtmlBlockStartOnlyOnBlockTags;

        private BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.myPatterns = null;
            this.myHtmlCommentBlocksInterruptParagraph = Parser.HTML_COMMENT_BLOCKS_INTERRUPT_PARAGRAPH.get(dataHolder).booleanValue();
            this.myHtmlBlockDeepParser = Parser.HTML_BLOCK_DEEP_PARSER.get(dataHolder).booleanValue();
            this.myHtmlBlockDeepParseNonBlock = Parser.HTML_BLOCK_DEEP_PARSE_NON_BLOCK.get(dataHolder).booleanValue();
            this.myHtmlBlockDeepParseFirstOpenTagOnOneLine = Parser.HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE.get(dataHolder).booleanValue();
            this.myHtmlBlockCommentOnlyFullLine = Parser.HTML_BLOCK_COMMENT_ONLY_FULL_LINE.get(dataHolder).booleanValue();
            this.myHtmlBlockStartOnlyOnBlockTags = Parser.HTML_BLOCK_START_ONLY_ON_BLOCK_TAGS.get(dataHolder).booleanValue();
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
            BasedSequence line = parserState.getLine();
            if (parserState.getIndent() < 4 && line.charAt(nextNonSpaceIndex) == '<' && !(matchedBlockParser.getBlockParser() instanceof HtmlBlockParser)) {
                if (this.myHtmlBlockDeepParser) {
                    HtmlDeepParser htmlDeepParser = new HtmlDeepParser(Parser.HTML_BLOCK_TAGS.get(parserState.getProperties()));
                    htmlDeepParser.parseHtmlChunk(line.subSequence(nextNonSpaceIndex, line.length()), this.myHtmlBlockStartOnlyOnBlockTags, this.myHtmlBlockDeepParseNonBlock, this.myHtmlBlockDeepParseFirstOpenTagOnOneLine);
                    if (htmlDeepParser.hadHtml() && ((htmlDeepParser.getHtmlMatch() != HtmlDeepParser.HtmlMatch.OPEN_TAG && (this.myHtmlCommentBlocksInterruptParagraph || htmlDeepParser.getHtmlMatch() != HtmlDeepParser.HtmlMatch.COMMENT)) || htmlDeepParser.isFirstBlockTag() || !(matchedBlockParser.getBlockParser().getBlock() instanceof Paragraph))) {
                        BlockParser[] blockParserArr = new BlockParser[1];
                        blockParserArr[0] = new HtmlBlockParser(parserState.getProperties(), null, htmlDeepParser.getHtmlMatch() == HtmlDeepParser.HtmlMatch.COMMENT, htmlDeepParser);
                        return BlockStart.of(blockParserArr).atIndex(parserState.getIndex());
                    }
                } else {
                    int i = 1;
                    while (i <= 7) {
                        if (i != 7 || (!this.myHtmlBlockStartOnlyOnBlockTags && !(matchedBlockParser.getBlockParser().getBlock() instanceof Paragraph))) {
                            if (this.myPatterns == null) {
                                this.myPatterns = new Patterns(parserState.getParsing(), parserState.getProperties());
                            }
                            Pattern pattern = this.myPatterns.BLOCK_PATTERNS[i][0];
                            Pattern pattern2 = this.myPatterns.BLOCK_PATTERNS[i][1];
                            Matcher matcher = pattern.matcher(line.subSequence(nextNonSpaceIndex, line.length()));
                            if (matcher.find() && (this.myHtmlCommentBlocksInterruptParagraph || i != this.myPatterns.COMMENT_PATTERN_INDEX || !(matchedBlockParser.getBlockParser() instanceof ParagraphParser))) {
                                if (i == this.myPatterns.COMMENT_PATTERN_INDEX && this.myHtmlBlockCommentOnlyFullLine) {
                                    Matcher matcher2 = this.myPatterns.BLOCK_PATTERNS[this.myPatterns.COMMENT_PATTERN_INDEX][1].matcher(line.subSequence(matcher.end(), line.length()));
                                    if (matcher2.find() && !line.subSequence(matcher2.end(), line.length()).trim().equals("-->")) {
                                        return BlockStart.none();
                                    }
                                }
                                BlockParser[] blockParserArr2 = new BlockParser[1];
                                blockParserArr2[0] = new HtmlBlockParser(parserState.getProperties(), pattern2, i == this.myPatterns.COMMENT_PATTERN_INDEX, null);
                                return BlockStart.of(blockParserArr2).atIndex(parserState.getIndex());
                            }
                        }
                        i++;
                    }
                }
            }
            return BlockStart.none();
        }
    }
}
