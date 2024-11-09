package org.jsoup.parser;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.helper.Validate;
import org.jsoup.internal.SharedConstants;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.Range;
import org.jsoup.parser.Token;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/jsoup/parser/TreeBuilder.class */
public abstract class TreeBuilder {
    protected Parser parser;
    CharacterReader reader;
    Tokeniser tokeniser;
    Document doc;
    ArrayList<Element> stack;
    String baseUri;
    Token currentToken;
    ParseSettings settings;
    Map<String, Tag> seenTags;
    private Token.StartTag start;
    private final Token.EndTag end = new Token.EndTag(this);
    boolean trackSourceRange;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ParseSettings defaultSettings();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract TreeBuilder newInstance();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract List<Node> parseFragment(String str, Element element, String str2, Parser parser);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean process(Token token);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initialiseParse(Reader reader, String str, Parser parser) {
        Validate.notNullParam(reader, FlexmarkHtmlConverter.INPUT_NODE);
        Validate.notNullParam(str, "baseUri");
        Validate.notNull(parser);
        this.doc = new Document(parser.defaultNamespace(), str);
        this.doc.parser(parser);
        this.parser = parser;
        this.settings = parser.settings();
        this.reader = new CharacterReader(reader);
        this.trackSourceRange = parser.isTrackPosition();
        this.reader.trackNewlines(parser.isTrackErrors() || this.trackSourceRange);
        this.tokeniser = new Tokeniser(this);
        this.stack = new ArrayList<>(32);
        this.seenTags = new HashMap();
        this.start = new Token.StartTag(this);
        this.currentToken = this.start;
        this.baseUri = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Document parse(Reader reader, String str, Parser parser) {
        initialiseParse(reader, str, parser);
        runParser();
        this.reader.close();
        this.reader = null;
        this.tokeniser = null;
        this.stack = null;
        this.seenTags = null;
        return this.doc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void runParser() {
        Tokeniser tokeniser = this.tokeniser;
        Token.TokenType tokenType = Token.TokenType.EOF;
        while (true) {
            Token read = tokeniser.read();
            this.currentToken = read;
            process(read);
            if (read.type == tokenType) {
                break;
            } else {
                read.reset();
            }
        }
        while (!this.stack.isEmpty()) {
            pop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean processStartTag(String str) {
        Token.StartTag startTag = this.start;
        if (this.currentToken == startTag) {
            return process(new Token.StartTag(this).name(str));
        }
        return process(startTag.reset().name(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean processStartTag(String str, Attributes attributes) {
        Token.StartTag startTag = this.start;
        if (this.currentToken == startTag) {
            return process(new Token.StartTag(this).nameAttr(str, attributes));
        }
        startTag.reset();
        startTag.nameAttr(str, attributes);
        return process(startTag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean processEndTag(String str) {
        if (this.currentToken == this.end) {
            return process(new Token.EndTag(this).name(str));
        }
        return process(this.end.reset().name(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Element pop() {
        Element remove = this.stack.remove(this.stack.size() - 1);
        onNodeClosed(remove);
        return remove;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void push(Element element) {
        this.stack.add(element);
        onNodeInserted(element);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element currentElement() {
        int size = this.stack.size();
        return size > 0 ? this.stack.get(size - 1) : this.doc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean currentElementIs(String str) {
        Element currentElement;
        return this.stack.size() != 0 && (currentElement = currentElement()) != null && currentElement.normalName().equals(str) && currentElement.tag().namespace().equals(Parser.NamespaceHtml);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean currentElementIs(String str, String str2) {
        Element currentElement;
        return this.stack.size() != 0 && (currentElement = currentElement()) != null && currentElement.normalName().equals(str) && currentElement.tag().namespace().equals(str2);
    }

    void error(String str) {
        error(str, (Object[]) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void error(String str, Object... objArr) {
        ParseErrorList errors = this.parser.getErrors();
        if (errors.canAddError()) {
            errors.add(new ParseError(this.reader, str, objArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isContentForTagData(String str) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Tag tagFor(String str, String str2, ParseSettings parseSettings) {
        Tag tag = this.seenTags.get(str);
        if (tag == null || !tag.namespace().equals(str2)) {
            Tag valueOf = Tag.valueOf(str, str2, parseSettings);
            this.seenTags.put(str, valueOf);
            return valueOf;
        }
        return tag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Tag tagFor(String str, ParseSettings parseSettings) {
        return tagFor(str, defaultNamespace(), parseSettings);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String defaultNamespace() {
        return Parser.NamespaceHtml;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onNodeInserted(Node node) {
        trackNodePosition(node, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onNodeClosed(Node node) {
        trackNodePosition(node, false);
    }

    private void trackNodePosition(Node node, boolean z) {
        if (this.trackSourceRange) {
            Token token = this.currentToken;
            int startPos = token.startPos();
            int endPos = token.endPos();
            if (node instanceof Element) {
                Element element = (Element) node;
                if (token.isEOF()) {
                    if (element.endSourceRange().isTracked()) {
                        return;
                    }
                    int pos = this.reader.pos();
                    endPos = pos;
                    startPos = pos;
                } else if (z) {
                    if (!token.isStartTag() || !element.normalName().equals(token.asStartTag().normalName)) {
                        endPos = startPos;
                    }
                } else if (!element.tag().isEmpty() && !element.tag().isSelfClosing() && (!token.isEndTag() || !element.normalName().equals(token.asEndTag().normalName))) {
                    endPos = startPos;
                }
            }
            node.attributes().userData(z ? SharedConstants.RangeKey : SharedConstants.EndRangeKey, new Range(new Range.Position(startPos, this.reader.lineNumber(startPos), this.reader.columnNumber(startPos)), new Range.Position(endPos, this.reader.lineNumber(endPos), this.reader.columnNumber(endPos))));
        }
    }
}
