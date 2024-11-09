package com.vladsch.flexmark.ext.macros.internal;

import com.vladsch.flexmark.ext.gitlab.GitLabBlockQuote;
import com.vladsch.flexmark.ext.gitlab.internal.GitLabBlockQuoteParser;
import com.vladsch.flexmark.ext.macros.MacroDefinitionBlock;
import com.vladsch.flexmark.ext.macros.MacrosExtension;
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
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacroDefinitionBlockParser.class */
public class MacroDefinitionBlockParser extends AbstractBlockParser {
    static Pattern MACRO_BLOCK_START = Pattern.compile(">>>([\\w_-]+)(\\s*$)");
    static Pattern MACRO_BLOCK_START_INTELLIJ = Pattern.compile(">>>([\u001f\\w_-]+)(\\s*$)");
    static Pattern MACRO_BLOCK_END = Pattern.compile("<<<(\\s*$)");
    private final MacroDefinitionBlock block = new MacroDefinitionBlock();
    private BlockContent content = new BlockContent();
    private boolean hadClose = false;

    MacroDefinitionBlockParser(DataHolder dataHolder, BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        this.block.setOpeningMarker(basedSequence);
        this.block.setName(basedSequence2);
        this.block.setOpeningTrailing(basedSequence3);
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        if (this.hadClose) {
            return BlockContinue.none();
        }
        int index = parserState.getIndex();
        Matcher matcher = MACRO_BLOCK_END.matcher(parserState.getLineWithEOL());
        if (!matcher.matches()) {
            return BlockContinue.atIndex(index);
        }
        Node lastChild = this.block.getLastChild();
        if ((lastChild instanceof GitLabBlockQuote) && ((GitLabBlockQuote) lastChild).getClosingMarker().isEmpty()) {
            return BlockContinue.atIndex(index);
        }
        this.hadClose = true;
        this.block.setClosingMarker(parserState.getLine().subSequence(index, index + 3));
        this.block.setClosingTrailing(parserState.getLineWithEOL().subSequence(matcher.start(1), matcher.end(1)));
        return BlockContinue.atIndex(parserState.getLineEndIndex());
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
        this.content.add(basedSequence, parserState.getIndent());
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.block.setContent(this.content);
        this.block.setCharsFromContent();
        this.content = null;
        this.block.setCharsFromContent();
        MacroDefinitionRepository macroDefinitionRepository = MacrosExtension.MACRO_DEFINITIONS.get(parserState.getProperties());
        macroDefinitionRepository.put2(macroDefinitionRepository.normalizeKey(this.block.getName()), (String) this.block);
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canContain(ParserState parserState, BlockParser blockParser, Block block) {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacroDefinitionBlockParser$Factory.class */
    public static class Factory implements CustomBlockParserFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return new HashSet(Collections.singletonList(GitLabBlockQuoteParser.Factory.class));
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
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacroDefinitionBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
        }

        boolean haveBlockQuoteParser(ParserState parserState) {
            List<BlockParser> activeBlockParsers = parserState.getActiveBlockParsers();
            int size = activeBlockParsers.size();
            do {
                int i = size;
                size--;
                if (i <= 0) {
                    return false;
                }
            } while (!(activeBlockParsers.get(size) instanceof MacroDefinitionBlockParser));
            return true;
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            if (parserState.getIndex() == 0 && !haveBlockQuoteParser(parserState)) {
                BasedSequence lineWithEOL = parserState.getLineWithEOL();
                Matcher matcher = (parserState.getParsing().intellijDummyIdentifier ? MacroDefinitionBlockParser.MACRO_BLOCK_START_INTELLIJ : MacroDefinitionBlockParser.MACRO_BLOCK_START).matcher(lineWithEOL);
                if (matcher.matches()) {
                    return BlockStart.of(new MacroDefinitionBlockParser(parserState.getProperties(), lineWithEOL.subSequence(0, 3), lineWithEOL.subSequence(matcher.start(1), matcher.end(1)), lineWithEOL.subSequence(matcher.start(2), matcher.end(1)))).atIndex(parserState.getLineEndIndex());
                }
            }
            return BlockStart.none();
        }
    }
}
