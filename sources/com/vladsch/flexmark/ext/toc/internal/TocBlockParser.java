package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.ext.toc.TocBlock;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.block.AbstractBlockParser;
import com.vladsch.flexmark.parser.block.AbstractBlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockContinue;
import com.vladsch.flexmark.parser.block.BlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockStart;
import com.vladsch.flexmark.parser.block.CustomBlockParserFactory;
import com.vladsch.flexmark.parser.block.MatchedBlockParser;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocBlockParser.class */
public class TocBlockParser extends AbstractBlockParser {
    private final TocBlock block;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocBlockParser$TocParsing.class */
    static class TocParsing extends Parsing {
        final Pattern TOC_BLOCK_START;

        public TocParsing(DataHolder dataHolder) {
            super(dataHolder);
            if (TocExtension.CASE_SENSITIVE_TOC_TAG.get(dataHolder).booleanValue()) {
                this.TOC_BLOCK_START = Pattern.compile("^\\[TOC(?:\\s+([^\\]]+))?]\\s*$");
            } else {
                this.TOC_BLOCK_START = Pattern.compile("^\\[(?i:TOC)(?:\\s+([^\\]]+))?]\\s*$");
            }
        }
    }

    TocBlockParser(DataHolder dataHolder, BasedSequence basedSequence, BasedSequence basedSequence2) {
        this.block = new TocBlock(basedSequence, basedSequence2);
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
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocBlockParser$Factory.class */
    public static class Factory implements CustomBlockParserFactory {
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
            return false;
        }

        @Override // com.vladsch.flexmark.parser.block.CustomBlockParserFactory, java.util.function.Function
        public BlockParserFactory apply(DataHolder dataHolder) {
            return new BlockFactory(dataHolder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private final TocParsing myParsing;

        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.myParsing = new TocParsing(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            if (parserState.getIndent() >= 4) {
                return BlockStart.none();
            }
            BasedSequence line = parserState.getLine();
            BasedSequence subSequence = line.subSequence(parserState.getNextNonSpaceIndex(), line.length());
            Matcher matcher = this.myParsing.TOC_BLOCK_START.matcher(line);
            if (matcher.matches()) {
                BasedSequence lineWithEOL = parserState.getLineWithEOL();
                BasedSequence basedSequence = null;
                if (matcher.start(1) != -1) {
                    basedSequence = subSequence.subSequence(matcher.start(1), matcher.end(1));
                }
                return BlockStart.of(new TocBlockParser(parserState.getProperties(), lineWithEOL, basedSequence)).atIndex(parserState.getIndex());
            }
            return BlockStart.none();
        }
    }
}
