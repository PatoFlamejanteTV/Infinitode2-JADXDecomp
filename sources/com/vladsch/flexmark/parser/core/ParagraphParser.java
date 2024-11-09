package com.vladsch.flexmark.parser.core;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.block.AbstractBlockParser;
import com.vladsch.flexmark.parser.block.BlockContinue;
import com.vladsch.flexmark.parser.block.BlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockStart;
import com.vladsch.flexmark.parser.block.MatchedBlockParser;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.PrefixedSubSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/ParagraphParser.class */
public class ParagraphParser extends AbstractBlockParser {
    private final Paragraph block = new Paragraph();
    private BlockContent content = new BlockContent();

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public BlockContent getBlockContent() {
        return this.content;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Paragraph getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        if (!parserState.isBlank()) {
            return BlockContinue.atIndex(parserState.getIndex());
        }
        this.block.setTrailingBlankLine(parserState.isBlankLine());
        return BlockContinue.none();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
        int indent = parserState.getIndent();
        if (indent > 0) {
            this.content.add(PrefixedSubSequence.repeatOf(' ', indent, basedSequence), indent);
        } else {
            this.content.add(basedSequence, indent);
        }
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isParagraphParser() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isInterruptible() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.block.setContent(this.content);
        this.content = null;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
        inlineParser.parse(getBlock().getContentChars(), getBlock());
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/ParagraphParser$Factory.class */
    public static class Factory implements BlockParserFactory {
        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            return null;
        }
    }
}
