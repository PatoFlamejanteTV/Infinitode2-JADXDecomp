package com.vladsch.flexmark.ext.admonition.internal;

import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.ext.admonition.AdmonitionBlock;
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
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/internal/AdmonitionBlockParser.class */
public class AdmonitionBlockParser extends AbstractBlockParser {
    private static final String ADMONITION_START_FORMAT = "^(\\?{3}\\+|\\?{3}|!{3})\\s+(%s)(?:\\s+(%s))?\\s*$";
    final AdmonitionBlock block = new AdmonitionBlock();
    private final AdmonitionOptions options;
    private final int contentIndent;
    private boolean hadBlankLine;

    AdmonitionBlockParser(AdmonitionOptions admonitionOptions, int i) {
        this.options = admonitionOptions;
        this.contentIndent = i;
    }

    private int getContentIndent() {
        return this.contentIndent;
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

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
        if (parserState.isBlank()) {
            this.hadBlankLine = true;
            return BlockContinue.atIndex(nextNonSpaceIndex);
        }
        if (!this.hadBlankLine && this.options.allowLazyContinuation) {
            return BlockContinue.atIndex(nextNonSpaceIndex);
        }
        if (parserState.getIndent() >= this.options.contentIndent) {
            return BlockContinue.atColumn(parserState.getColumn() + this.options.contentIndent);
        }
        return BlockContinue.none();
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.block.setCharsFromContent();
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/internal/AdmonitionBlockParser$Factory.class */
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
            return AdmonitionLeadInHandler.HANDLER;
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

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/internal/AdmonitionBlockParser$AdmonitionLeadInHandler.class */
    static class AdmonitionLeadInHandler implements SpecialLeadInHandler {
        static final SpecialLeadInHandler HANDLER = new AdmonitionLeadInHandler();

        AdmonitionLeadInHandler() {
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler
        public boolean escape(BasedSequence basedSequence, DataHolder dataHolder, Consumer<CharSequence> consumer) {
            if (basedSequence.length() != 3 && (basedSequence.length() != 4 || basedSequence.charAt(3) != '+')) {
                return false;
            }
            if (basedSequence.startsWith("???") || basedSequence.startsWith("!!!")) {
                consumer.accept("\\");
                consumer.accept(basedSequence);
                return true;
            }
            return false;
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler
        public boolean unEscape(BasedSequence basedSequence, DataHolder dataHolder, Consumer<CharSequence> consumer) {
            if (basedSequence.length() != 4 && (basedSequence.length() != 5 || basedSequence.charAt(4) != '+')) {
                return false;
            }
            if (basedSequence.startsWith("\\???") || basedSequence.startsWith("\\!!!")) {
                consumer.accept(basedSequence.subSequence(1));
                return true;
            }
            return false;
        }
    }

    static boolean isMarker(ParserState parserState, int i, boolean z, boolean z2, AdmonitionOptions admonitionOptions) {
        boolean z3 = admonitionOptions.allowLeadingSpace;
        boolean z4 = admonitionOptions.interruptsParagraph;
        boolean z5 = admonitionOptions.interruptsItemParagraph;
        boolean z6 = admonitionOptions.withSpacesInterruptsItemParagraph;
        parserState.getLine();
        if (!z || z4) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/internal/AdmonitionBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private final AdmonitionOptions options;

        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.options = new AdmonitionOptions(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            if (parserState.getIndent() >= 4) {
                return BlockStart.none();
            }
            int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
            BlockParser blockParser = matchedBlockParser.getBlockParser();
            boolean isParagraphParser = blockParser.isParagraphParser();
            if (AdmonitionBlockParser.isMarker(parserState, nextNonSpaceIndex, isParagraphParser, isParagraphParser && (blockParser.getBlock().getParent() instanceof ListItem) && blockParser.getBlock() == blockParser.getBlock().getParent().getFirstChild(), this.options)) {
                BasedSequence line = parserState.getLine();
                BasedSequence subSequence = line.subSequence(nextNonSpaceIndex, line.length());
                Parsing parsing = parserState.getParsing();
                Matcher matcher = Pattern.compile(String.format(AdmonitionBlockParser.ADMONITION_START_FORMAT, parsing.ATTRIBUTENAME, parsing.LINK_TITLE_STRING)).matcher(subSequence);
                if (matcher.find()) {
                    BasedSequence subSequence2 = line.subSequence(nextNonSpaceIndex + matcher.start(1), nextNonSpaceIndex + matcher.end(1));
                    BasedSequence subSequence3 = line.subSequence(nextNonSpaceIndex + matcher.start(2), nextNonSpaceIndex + matcher.end(2));
                    BasedSequence subSequence4 = matcher.group(3) == null ? BasedSequence.NULL : line.subSequence(nextNonSpaceIndex + matcher.start(3), nextNonSpaceIndex + matcher.end(3));
                    AdmonitionBlockParser admonitionBlockParser = new AdmonitionBlockParser(this.options, this.options.contentIndent);
                    admonitionBlockParser.block.setOpeningMarker(subSequence2);
                    admonitionBlockParser.block.setInfo(subSequence3);
                    admonitionBlockParser.block.setTitleChars(subSequence4);
                    return BlockStart.of(admonitionBlockParser).atIndex(line.length());
                }
                return BlockStart.none();
            }
            return BlockStart.none();
        }
    }
}
