package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/block/BlockParser.class */
public interface BlockParser {
    boolean isContainer();

    boolean canContain(ParserState parserState, BlockParser blockParser, Block block);

    Block getBlock();

    BlockContinue tryContinue(ParserState parserState);

    void addLine(ParserState parserState, BasedSequence basedSequence);

    void closeBlock(ParserState parserState);

    boolean isClosed();

    boolean isPropagatingLastBlankLine(BlockParser blockParser);

    boolean breakOutOnDoubleBlankLine();

    boolean isParagraphParser();

    BlockContent getBlockContent();

    void finalizeClosedBlock();

    void parseInlines(InlineParser inlineParser);

    boolean isInterruptible();

    boolean isRawText();

    boolean canInterruptBy(BlockParserFactory blockParserFactory);

    MutableDataHolder getDataHolder();
}
