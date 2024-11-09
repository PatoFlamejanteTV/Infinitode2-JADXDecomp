package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.nodes.LeafNode;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Token;

/* loaded from: infinitode-2.jar:org/jsoup/parser/XmlTreeBuilder.class */
public class XmlTreeBuilder extends TreeBuilder {
    private static final int maxQueueDepth = 256;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jsoup.parser.TreeBuilder
    public ParseSettings defaultSettings() {
        return ParseSettings.preserveCase;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.parser.TreeBuilder
    public void initialiseParse(Reader reader, String str, Parser parser) {
        super.initialiseParse(reader, str, parser);
        this.stack.add(this.doc);
        this.doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml).escapeMode(Entities.EscapeMode.xhtml).prettyPrint(false);
    }

    Document parse(Reader reader, String str) {
        return parse(reader, str, new Parser(this));
    }

    Document parse(String str, String str2) {
        return parse(new StringReader(str), str2, new Parser(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jsoup.parser.TreeBuilder
    public XmlTreeBuilder newInstance() {
        return new XmlTreeBuilder();
    }

    @Override // org.jsoup.parser.TreeBuilder
    public String defaultNamespace() {
        return Parser.NamespaceXml;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.parser.TreeBuilder
    public boolean process(Token token) {
        this.currentToken = token;
        switch (token.type) {
            case StartTag:
                insertElementFor(token.asStartTag());
                return true;
            case EndTag:
                popStackToClose(token.asEndTag());
                return true;
            case Comment:
                insertCommentFor(token.asComment());
                return true;
            case Character:
                insertCharacterFor(token.asCharacter());
                return true;
            case Doctype:
                insertDoctypeFor(token.asDoctype());
                return true;
            case EOF:
                return true;
            default:
                Validate.fail("Unexpected token type: " + token.type);
                return true;
        }
    }

    void insertElementFor(Token.StartTag startTag) {
        Tag tagFor = tagFor(startTag.name(), this.settings);
        if (startTag.attributes != null) {
            startTag.attributes.deduplicate(this.settings);
        }
        Element element = new Element(tagFor, null, this.settings.normalizeAttributes(startTag.attributes));
        currentElement().appendChild(element);
        push(element);
        if (startTag.isSelfClosing()) {
            tagFor.setSelfClosing();
            pop();
        }
    }

    void insertLeafNode(LeafNode leafNode) {
        currentElement().appendChild(leafNode);
        onNodeInserted(leafNode);
    }

    void insertCommentFor(Token.Comment comment) {
        XmlDeclaration asXmlDeclaration;
        Comment comment2 = new Comment(comment.getData());
        XmlDeclaration xmlDeclaration = comment2;
        if (comment.bogus && comment2.isXmlDeclaration() && (asXmlDeclaration = comment2.asXmlDeclaration()) != null) {
            xmlDeclaration = asXmlDeclaration;
        }
        insertLeafNode(xmlDeclaration);
    }

    void insertCharacterFor(Token.Character character) {
        String data = character.getData();
        insertLeafNode(character.isCData() ? new CDataNode(data) : new TextNode(data));
    }

    void insertDoctypeFor(Token.Doctype doctype) {
        DocumentType documentType = new DocumentType(this.settings.normalizeTag(doctype.getName()), doctype.getPublicIdentifier(), doctype.getSystemIdentifier());
        documentType.setPubSysKey(doctype.getPubSysKey());
        insertLeafNode(documentType);
    }

    @Deprecated
    protected void insertNode(Node node) {
        currentElement().appendChild(node);
        onNodeInserted(node);
    }

    @Deprecated
    protected void insertNode(Node node, Token token) {
        currentElement().appendChild(node);
        onNodeInserted(node);
    }

    protected void popStackToClose(Token.EndTag endTag) {
        String normalizeTag = this.settings.normalizeTag(endTag.tagName);
        Element element = null;
        int size = this.stack.size() - 1;
        int i = size >= 256 ? size - 256 : 0;
        int size2 = this.stack.size() - 1;
        while (true) {
            if (size2 < i) {
                break;
            }
            Element element2 = this.stack.get(size2);
            if (element2.nodeName().equals(normalizeTag)) {
                element = element2;
                break;
            }
            size2--;
        }
        if (element == null) {
            return;
        }
        for (int size3 = this.stack.size() - 1; size3 >= 0 && pop() != element; size3--) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Node> parseFragment(String str, String str2, Parser parser) {
        initialiseParse(new StringReader(str), str2, parser);
        runParser();
        return this.doc.childNodes();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jsoup.parser.TreeBuilder
    public List<Node> parseFragment(String str, Element element, String str2, Parser parser) {
        return parseFragment(str, str2, parser);
    }
}
