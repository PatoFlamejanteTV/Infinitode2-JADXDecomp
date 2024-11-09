package com.vladsch.flexmark.ext.jekyll.tag.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTag;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagBlock;
import com.vladsch.flexmark.ext.jekyll.tag.JekyllTagExtension;
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
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Set;
import java.util.regex.Matcher;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/JekyllTagBlockParser.class */
public class JekyllTagBlockParser extends AbstractBlockParser {
    public static final String INCLUDE_TAG = "include";
    final JekyllTagBlock block = new JekyllTagBlock();
    private BlockContent content = new BlockContent();

    JekyllTagBlockParser(DataHolder dataHolder) {
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
        this.content.add(basedSequence, parserState.getIndent());
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.block.setContent(this.content);
        this.content = null;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/JekyllTagBlockParser$Factory.class */
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
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/internal/JekyllTagBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private final JekyllTagParsing parsing;
        private final boolean listIncludesOnly;

        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.parsing = new JekyllTagParsing(new Parsing(dataHolder));
            this.listIncludesOnly = JekyllTagExtension.LIST_INCLUDES_ONLY.get(dataHolder).booleanValue();
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            BasedSequence line = parserState.getLine();
            if (parserState.getIndent() == 0 && !(matchedBlockParser.getBlockParser().getBlock() instanceof Paragraph)) {
                BasedSequence subSequence = line.subSequence(parserState.getIndex());
                Matcher matcher = this.parsing.MACRO_OPEN.matcher(subSequence);
                if (matcher.find()) {
                    BasedSequence subSequence2 = subSequence.subSequence(0, matcher.end());
                    BasedSequence subSequence3 = line.subSequence(matcher.start(1), matcher.end(1));
                    JekyllTag jekyllTag = new JekyllTag(subSequence2.subSequence(0, 2), subSequence3, subSequence.subSequence(matcher.end(1), matcher.end() - 2).trim(), subSequence2.endSequence(2));
                    jekyllTag.setCharsFromContent();
                    JekyllTagBlockParser jekyllTagBlockParser = new JekyllTagBlockParser(parserState.getProperties());
                    jekyllTagBlockParser.block.appendChild(jekyllTag);
                    if (!this.listIncludesOnly || subSequence3.equals(JekyllTagBlockParser.INCLUDE_TAG)) {
                        JekyllTagExtension.TAG_LIST.get(parserState.getProperties()).add(jekyllTag);
                    }
                    return BlockStart.of(jekyllTagBlockParser).atIndex(parserState.getLineEndIndex());
                }
            }
            return BlockStart.none();
        }
    }
}
