package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/block/MatchedBlockParser.class */
public interface MatchedBlockParser {
    BlockParser getBlockParser();

    BasedSequence getParagraphContent();

    List<BasedSequence> getParagraphLines();

    List<Integer> getParagraphEolLengths();

    MutableDataHolder getParagraphDataHolder();
}
