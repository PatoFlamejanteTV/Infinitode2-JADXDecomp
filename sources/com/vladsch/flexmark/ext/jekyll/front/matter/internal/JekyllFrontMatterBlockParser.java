package com.vladsch.flexmark.ext.jekyll.front.matter.internal;

import com.vladsch.flexmark.ext.jekyll.front.matter.JekyllFrontMatterBlock;
import com.vladsch.flexmark.parser.InlineParser;
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
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/front/matter/internal/JekyllFrontMatterBlockParser.class */
public class JekyllFrontMatterBlockParser extends AbstractBlockParser {
    static final Pattern JEKYLL_FRONT_MATTER_BLOCK_START = Pattern.compile("^-{3}(\\s.*)?");
    static final Pattern JEKYLL_FRONT_MATTER_BLOCK_END = Pattern.compile("^(-{3}|\\.{3})(\\s.*)?");
    private final JekyllFrontMatterBlock block = new JekyllFrontMatterBlock();
    private BlockContent content = new BlockContent();
    private boolean inYAMLBlock = true;

    JekyllFrontMatterBlockParser(DataHolder dataHolder, BasedSequence basedSequence) {
        this.block.setOpeningMarker(basedSequence);
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        BasedSequence line = parserState.getLine();
        if (this.inYAMLBlock) {
            Matcher matcher = JEKYLL_FRONT_MATTER_BLOCK_END.matcher(line);
            if (matcher.matches()) {
                this.block.setClosingMarker(line.subSequence(matcher.start(1), matcher.end(1)));
                return BlockContinue.finished();
            }
            return BlockContinue.atIndex(parserState.getIndex());
        }
        if (JEKYLL_FRONT_MATTER_BLOCK_START.matcher(line).matches()) {
            this.inYAMLBlock = true;
            return BlockContinue.atIndex(parserState.getIndex());
        }
        return BlockContinue.none();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
        this.content.add(basedSequence, parserState.getIndent());
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.block.setContent(this.content.getLines().subList(1, this.content.getLineCount()));
        this.block.setCharsFromContent();
        this.content = null;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/front/matter/internal/JekyllFrontMatterBlockParser$Factory.class */
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
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/front/matter/internal/JekyllFrontMatterBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            BasedSequence line = parserState.getLine();
            BlockParser blockParser = matchedBlockParser.getBlockParser();
            return ((blockParser instanceof DocumentBlockParser) && blockParser.getBlock().getFirstChild() == null && JekyllFrontMatterBlockParser.JEKYLL_FRONT_MATTER_BLOCK_START.matcher(line).matches()) ? BlockStart.of(new JekyllFrontMatterBlockParser(parserState.getProperties(), line.subSequence(0, 3))).atIndex(-1) : BlockStart.none();
        }
    }
}
