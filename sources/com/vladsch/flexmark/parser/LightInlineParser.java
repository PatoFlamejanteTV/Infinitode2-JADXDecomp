package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/LightInlineParser.class */
public interface LightInlineParser {
    ArrayList<BasedSequence> getCurrentText();

    BasedSequence getInput();

    void setInput(BasedSequence basedSequence);

    int getIndex();

    void setIndex(int i);

    Node getBlock();

    BasedSequence match(Pattern pattern);

    BasedSequence[] matchWithGroups(Pattern pattern);

    Matcher matcher(Pattern pattern);

    char peek();

    char peek(int i);

    boolean flushTextNode();

    Document getDocument();

    void setDocument(Document document);

    InlineParserOptions getOptions();

    Parsing getParsing();

    void appendText(BasedSequence basedSequence);

    void appendText(BasedSequence basedSequence, int i, int i2);

    void appendNode(Node node);

    Text appendSeparateText(BasedSequence basedSequence);

    void setBlock(Node node);

    void moveNodes(Node node, Node node2);

    boolean spnl();

    boolean nonIndentSp();

    boolean sp();

    boolean spnlUrl();

    BasedSequence toEOL();
}
