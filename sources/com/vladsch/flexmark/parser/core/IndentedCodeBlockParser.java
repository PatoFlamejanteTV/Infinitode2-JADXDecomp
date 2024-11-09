package com.vladsch.flexmark.parser.core;

import com.vladsch.flexmark.ast.CodeBlock;
import com.vladsch.flexmark.ast.IndentedCodeBlock;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.block.AbstractBlockParser;
import com.vladsch.flexmark.parser.block.AbstractBlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockContinue;
import com.vladsch.flexmark.parser.block.BlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockStart;
import com.vladsch.flexmark.parser.block.CustomBlockParserFactory;
import com.vladsch.flexmark.parser.block.MatchedBlockParser;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.core.BlockQuoteParser;
import com.vladsch.flexmark.parser.core.FencedCodeBlockParser;
import com.vladsch.flexmark.parser.core.HeadingParser;
import com.vladsch.flexmark.parser.core.HtmlBlockParser;
import com.vladsch.flexmark.parser.core.ListBlockParser;
import com.vladsch.flexmark.parser.core.ThematicBreakParser;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.collection.iteration.Reverse;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/IndentedCodeBlockParser.class */
public class IndentedCodeBlockParser extends AbstractBlockParser {
    private final IndentedCodeBlock block = new IndentedCodeBlock();
    private BlockContent content = new BlockContent();
    private final boolean trimTrailingBlankLines;
    private final boolean codeContentBlock;

    public IndentedCodeBlockParser(DataHolder dataHolder) {
        this.trimTrailingBlankLines = Parser.INDENTED_CODE_NO_TRAILING_BLANK_LINES.get(dataHolder).booleanValue();
        this.codeContentBlock = Parser.FENCED_CODE_CONTENT_BLOCK.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        if (parserState.getIndent() >= parserState.getParsing().CODE_BLOCK_INDENT) {
            return BlockContinue.atColumn(parserState.getColumn() + parserState.getParsing().CODE_BLOCK_INDENT);
        }
        if (parserState.isBlank()) {
            return BlockContinue.atIndex(parserState.getNextNonSpaceIndex());
        }
        return BlockContinue.none();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
        this.content.add(basedSequence, parserState.getIndent());
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        if (this.trimTrailingBlankLines) {
            int i = 0;
            List<BasedSequence> lines = this.content.getLines();
            ReversibleIterator it = new Reverse(lines).iterator();
            while (it.hasNext() && ((BasedSequence) it.next()).isBlank()) {
                i++;
            }
            if (i > 0) {
                this.block.setContent(lines.subList(0, lines.size() - i));
            } else {
                this.block.setContent(this.content);
            }
        } else {
            this.block.setContent(this.content);
        }
        if (this.codeContentBlock) {
            this.block.appendChild(new CodeBlock(this.block.getChars(), this.block.getContentLines()));
        }
        this.content = null;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/IndentedCodeBlockParser$Factory.class */
    public static class Factory implements CustomBlockParserFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return new HashSet(Arrays.asList(BlockQuoteParser.Factory.class, HeadingParser.Factory.class, FencedCodeBlockParser.Factory.class, HtmlBlockParser.Factory.class, ThematicBreakParser.Factory.class, ListBlockParser.Factory.class));
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return Collections.emptySet();
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

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/IndentedCodeBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            return (parserState.getIndent() < parserState.getParsing().CODE_BLOCK_INDENT || parserState.isBlank() || (parserState.getActiveBlockParser().getBlock() instanceof Paragraph)) ? BlockStart.none() : BlockStart.of(new IndentedCodeBlockParser(parserState.getProperties())).atColumn(parserState.getColumn() + parserState.getParsing().CODE_BLOCK_INDENT);
        }
    }
}
