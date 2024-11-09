package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.HtmlBlock;
import com.vladsch.flexmark.ast.ListBlock;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.ext.toc.SimTocBlock;
import com.vladsch.flexmark.ext.toc.SimTocContent;
import com.vladsch.flexmark.ext.toc.SimTocOption;
import com.vladsch.flexmark.ext.toc.SimTocOptionList;
import com.vladsch.flexmark.ext.toc.TocExtension;
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
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.options.ParsedOption;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocBlockParser.class */
public class SimTocBlockParser extends AbstractBlockParser {
    static int HAVE_HTML = 1;
    static int HAVE_HEADING = 2;
    static int HAVE_LIST = 4;
    static int HAVE_BLANK_LINE = 8;
    private final SimTocBlock block;
    private final TocOptions options;
    private int haveChildren = 0;
    private BasedSequence blankLineSpacer = BasedSequence.NULL;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocBlockParser$TocParsing.class */
    static class TocParsing extends Parsing {
        final Pattern TOC_BLOCK_START;

        public TocParsing(DataHolder dataHolder) {
            super(dataHolder);
            if (TocExtension.CASE_SENSITIVE_TOC_TAG.get(dataHolder).booleanValue()) {
                this.TOC_BLOCK_START = Pattern.compile("^\\[TOC(?:\\s+([^\\]]+))?]:\\s*#(?:\\s+(" + this.LINK_TITLE_STRING + "))?\\s*$");
            } else {
                this.TOC_BLOCK_START = Pattern.compile("^\\[(?i:TOC)(?:\\s+([^\\]]+))?]:\\s*#(?:\\s+(" + this.LINK_TITLE_STRING + "))?\\s*$");
            }
        }
    }

    SimTocBlockParser(DataHolder dataHolder, BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        this.options = new TocOptions(dataHolder, true);
        this.block = new SimTocBlock(basedSequence, basedSequence2, basedSequence3);
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        if ((!this.options.isBlankLineSpacer || this.haveChildren != 0) && parserState.isBlank()) {
            return BlockContinue.none();
        }
        if (parserState.isBlank()) {
            this.haveChildren |= HAVE_BLANK_LINE;
            this.blankLineSpacer = parserState.getLine();
        }
        return BlockContinue.atIndex(parserState.getIndex());
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canContain(ParserState parserState, BlockParser blockParser, Block block) {
        if (block instanceof HtmlBlock) {
            if ((this.haveChildren & (HAVE_BLANK_LINE ^ (-1))) == 0) {
                this.haveChildren |= HAVE_HTML;
                return true;
            }
            return false;
        }
        if (block instanceof Heading) {
            if ((this.haveChildren & (HAVE_BLANK_LINE ^ (-1))) == 0) {
                this.haveChildren |= HAVE_HEADING;
                return true;
            }
            return false;
        }
        if ((block instanceof ListBlock) && (this.haveChildren & (HAVE_HTML | HAVE_LIST)) == 0) {
            this.haveChildren |= HAVE_LIST;
            return true;
        }
        return false;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        if (this.block.hasChildren()) {
            SimTocContent simTocContent = new SimTocContent();
            simTocContent.takeChildren(this.block);
            simTocContent.setCharsFromContent();
            if (this.blankLineSpacer.isNotNull()) {
                simTocContent.setChars(Node.spanningChars(this.blankLineSpacer, simTocContent.getChars()));
            }
            this.block.appendChild(simTocContent);
            this.block.setCharsFromContent();
            parserState.blockAddedWithChildren(simTocContent);
        }
        if (this.options.isAstAddOptions && !this.block.getStyle().isEmpty()) {
            List<ParsedOption<TocOptions>> second = new SimTocOptionsParser().parseOption(this.block.getStyle(), TocOptions.DEFAULT, null).getSecond();
            if (!second.isEmpty()) {
                SimTocOptionList simTocOptionList = new SimTocOptionList();
                Iterator<ParsedOption<TocOptions>> it = second.iterator();
                while (it.hasNext()) {
                    simTocOptionList.appendChild(new SimTocOption(it.next().getSource()));
                }
                simTocOptionList.setCharsFromContent();
                this.block.prependChild(simTocOptionList);
            }
        }
        this.block.setCharsFromContent();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocBlockParser$Factory.class */
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
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocBlockParser$BlockFactory.class */
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
                BasedSequence basedSequence2 = null;
                if (matcher.start(1) != -1) {
                    basedSequence = subSequence.subSequence(matcher.start(1), matcher.end(1));
                }
                if (matcher.start(2) != -1) {
                    basedSequence2 = subSequence.subSequence(matcher.start(2), matcher.end(2));
                }
                return BlockStart.of(new SimTocBlockParser(parserState.getProperties(), lineWithEOL, basedSequence, basedSequence2)).atIndex(parserState.getLineEndIndex() + parserState.getLineEolLength());
            }
            return BlockStart.none();
        }
    }
}
