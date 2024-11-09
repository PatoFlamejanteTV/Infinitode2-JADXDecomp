package com.vladsch.flexmark.ext.abbreviation.internal;

import com.vladsch.flexmark.ext.abbreviation.AbbreviationBlock;
import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
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

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationBlockParser.class */
public class AbbreviationBlockParser extends AbstractBlockParser {
    static Pattern ABBREVIATION_BLOCK = Pattern.compile("^\\*\\[\\s*.*\\s*\\]:");
    final AbbreviationBlock block = new AbbreviationBlock();

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
        throw new IllegalStateException("Abbreviation Blocks hold a single line");
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        AbbreviationRepository abbreviationRepository = AbbreviationExtension.ABBREVIATIONS.get(parserState.getProperties());
        abbreviationRepository.put2(abbreviationRepository.normalizeKey(this.block.getText()), (String) this.block);
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationBlockParser$Factory.class */
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
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            if (parserState.getIndent() >= 4) {
                return BlockStart.none();
            }
            BasedSequence line = parserState.getLine();
            int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
            BasedSequence subSequence = line.subSequence(nextNonSpaceIndex, line.length());
            Matcher matcher = AbbreviationBlockParser.ABBREVIATION_BLOCK.matcher(subSequence);
            if (matcher.find()) {
                int start = nextNonSpaceIndex + matcher.start();
                int end = nextNonSpaceIndex + matcher.end();
                BasedSequence subSequence2 = subSequence.subSequence(start, start + 2);
                BasedSequence trim = subSequence.subSequence(start + 2, end - 2).trim();
                BasedSequence subSequence3 = subSequence.subSequence(end - 2, end);
                AbbreviationBlockParser abbreviationBlockParser = new AbbreviationBlockParser();
                abbreviationBlockParser.block.setOpeningMarker(subSequence2);
                abbreviationBlockParser.block.setText(trim);
                abbreviationBlockParser.block.setClosingMarker(subSequence3);
                abbreviationBlockParser.block.setAbbreviation(subSequence.subSequence(matcher.end()).trim());
                abbreviationBlockParser.block.setCharsFromContent();
                return BlockStart.of(abbreviationBlockParser).atIndex(line.length());
            }
            return BlockStart.none();
        }
    }
}
