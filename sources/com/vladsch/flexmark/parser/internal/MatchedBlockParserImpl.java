package com.vladsch.flexmark.parser.internal;

import com.vladsch.flexmark.parser.block.BlockParser;
import com.vladsch.flexmark.parser.block.MatchedBlockParser;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/MatchedBlockParserImpl.class */
public class MatchedBlockParserImpl implements MatchedBlockParser {
    private final BlockParser matchedBlockParser;

    @Override // com.vladsch.flexmark.parser.block.MatchedBlockParser
    public List<BasedSequence> getParagraphLines() {
        if (this.matchedBlockParser.isParagraphParser()) {
            return this.matchedBlockParser.getBlockContent().getLines();
        }
        return null;
    }

    @Override // com.vladsch.flexmark.parser.block.MatchedBlockParser
    public List<Integer> getParagraphEolLengths() {
        if (this.matchedBlockParser.isParagraphParser()) {
            return this.matchedBlockParser.getBlockContent().getLineIndents();
        }
        return null;
    }

    public MatchedBlockParserImpl(BlockParser blockParser) {
        this.matchedBlockParser = blockParser;
    }

    @Override // com.vladsch.flexmark.parser.block.MatchedBlockParser
    public BlockParser getBlockParser() {
        return this.matchedBlockParser;
    }

    @Override // com.vladsch.flexmark.parser.block.MatchedBlockParser
    public BasedSequence getParagraphContent() {
        if (this.matchedBlockParser.isParagraphParser()) {
            return this.matchedBlockParser.getBlockContent().getContents();
        }
        return null;
    }

    @Override // com.vladsch.flexmark.parser.block.MatchedBlockParser
    public MutableDataHolder getParagraphDataHolder() {
        if (this.matchedBlockParser.isParagraphParser()) {
            return this.matchedBlockParser.getDataHolder();
        }
        return null;
    }
}
