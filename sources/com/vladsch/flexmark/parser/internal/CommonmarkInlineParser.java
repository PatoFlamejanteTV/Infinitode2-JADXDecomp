package com.vladsch.flexmark.parser.internal;

import com.vladsch.flexmark.parser.InlineParserExtensionFactory;
import com.vladsch.flexmark.parser.delimiter.DelimiterProcessor;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/CommonmarkInlineParser.class */
public class CommonmarkInlineParser extends InlineParserImpl {
    public CommonmarkInlineParser(DataHolder dataHolder, BitSet bitSet, BitSet bitSet2, Map<Character, DelimiterProcessor> map, LinkRefProcessorData linkRefProcessorData, List<InlineParserExtensionFactory> list) {
        super(dataHolder, bitSet, bitSet2, map, linkRefProcessorData, list);
    }

    @Override // com.vladsch.flexmark.parser.internal.InlineParserImpl, com.vladsch.flexmark.parser.InlineParser
    public void initializeDocument(Document document) {
        super.initializeDocument(document);
    }
}
