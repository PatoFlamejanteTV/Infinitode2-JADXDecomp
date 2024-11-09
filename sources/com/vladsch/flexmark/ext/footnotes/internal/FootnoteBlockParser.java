package com.vladsch.flexmark.ext.footnotes.internal;

import com.vladsch.flexmark.ext.footnotes.FootnoteBlock;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.parser.block.AbstractBlockParser;
import com.vladsch.flexmark.parser.block.AbstractBlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockContinue;
import com.vladsch.flexmark.parser.block.BlockParser;
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
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteBlockParser.class */
public class FootnoteBlockParser extends AbstractBlockParser {
    static String FOOTNOTE_ID = ".*";
    static Pattern FOOTNOTE_ID_PATTERN = Pattern.compile("\\[\\^\\s*(" + FOOTNOTE_ID + ")\\s*\\]");
    static Pattern FOOTNOTE_DEF_PATTERN = Pattern.compile("^\\[\\^\\s*(" + FOOTNOTE_ID + ")\\s*\\]:");
    private final FootnoteOptions options;
    private final int contentOffset;
    private final FootnoteBlock block = new FootnoteBlock();
    private BlockContent content = new BlockContent();

    public FootnoteBlockParser(FootnoteOptions footnoteOptions, int i) {
        this.options = footnoteOptions;
        this.contentOffset = i;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public BlockContent getBlockContent() {
        return this.content;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
        if (parserState.isBlank()) {
            if (this.block.getFirstChild() == null) {
                return BlockContinue.none();
            }
            return BlockContinue.atIndex(nextNonSpaceIndex);
        }
        if (parserState.getIndent() >= this.options.contentIndent) {
            return BlockContinue.atIndex(parserState.getIndex() + this.options.contentIndent);
        }
        return BlockContinue.none();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
        this.content.add(basedSequence, parserState.getIndent());
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.block.setCharsFromContent();
        this.block.setFootnote(this.block.getChars().subSequence(this.block.getClosingMarker().getEndOffset() - this.block.getStartOffset()).trimStart());
        FootnoteRepository footnoteRepository = FootnoteExtension.FOOTNOTES.get(parserState.getProperties());
        footnoteRepository.put2(footnoteRepository.normalizeKey(this.block.getText()), (String) this.block);
        this.content = null;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canContain(ParserState parserState, BlockParser blockParser, Block block) {
        return true;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteBlockParser$Factory.class */
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
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private final FootnoteOptions options;

        private BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.options = new FootnoteOptions(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            if (parserState.getIndent() >= 4) {
                return BlockStart.none();
            }
            BasedSequence line = parserState.getLine();
            int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
            Matcher matcher = FootnoteBlockParser.FOOTNOTE_DEF_PATTERN.matcher(line.subSequence(nextNonSpaceIndex, line.length()));
            if (matcher.find()) {
                int start = nextNonSpaceIndex + matcher.start();
                int end = nextNonSpaceIndex + matcher.end();
                BasedSequence subSequence = line.subSequence(start, start + 2);
                BasedSequence trim = line.subSequence(start + 2, end - 2).trim();
                BasedSequence subSequence2 = line.subSequence(end - 2, end);
                FootnoteBlockParser footnoteBlockParser = new FootnoteBlockParser(this.options, this.options.contentIndent);
                footnoteBlockParser.block.setOpeningMarker(subSequence);
                footnoteBlockParser.block.setText(trim);
                footnoteBlockParser.block.setClosingMarker(subSequence2);
                return BlockStart.of(footnoteBlockParser).atIndex(end);
            }
            return BlockStart.none();
        }
    }
}
