package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.parser.block.CharacterNodeFactory;
import com.vladsch.flexmark.parser.core.delimiter.Bracket;
import com.vladsch.flexmark.parser.core.delimiter.Delimiter;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/InlineParser.class */
public interface InlineParser extends LightInlineParser {
    void initializeDocument(Document document);

    void finalizeDocument(Document document);

    void parse(BasedSequence basedSequence, Node node);

    Delimiter getLastDelimiter();

    Bracket getLastBracket();

    List<Node> parseCustom(BasedSequence basedSequence, Node node, BitSet bitSet, Map<Character, CharacterNodeFactory> map);

    void mergeTextNodes(Node node, Node node2);

    void mergeIfNeeded(Text text, Text text2);

    @Override // com.vladsch.flexmark.parser.LightInlineParser
    BasedSequence toEOL();

    boolean parseNewline();

    BasedSequence parseLinkDestination();

    BasedSequence parseLinkTitle();

    int parseLinkLabel();

    boolean parseAutolink();

    boolean parseHtmlInline();

    boolean parseEntity();

    void processDelimiters(Delimiter delimiter);

    void removeDelimitersBetween(Delimiter delimiter, Delimiter delimiter2);

    void removeDelimiterAndNode(Delimiter delimiter);

    void removeDelimiterKeepNode(Delimiter delimiter);

    void removeDelimiter(Delimiter delimiter);
}
