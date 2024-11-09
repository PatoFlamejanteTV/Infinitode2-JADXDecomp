package org.jsoup.nodes;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.DataUtil;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.Evaluator;

/* loaded from: infinitode-2.jar:org/jsoup/nodes/Document.class */
public class Document extends Element {
    private Connection connection;
    private OutputSettings outputSettings;
    private Parser parser;
    private QuirksMode quirksMode;
    private final String location;
    private boolean updateMetaCharset;
    private static final Evaluator titleEval = new Evaluator.Tag(com.vladsch.flexmark.util.html.Attribute.TITLE_ATTR);

    /* loaded from: infinitode-2.jar:org/jsoup/nodes/Document$QuirksMode.class */
    public enum QuirksMode {
        noQuirks,
        quirks,
        limitedQuirks
    }

    public Document(String str, String str2) {
        super(Tag.valueOf("#root", str, ParseSettings.htmlDefault), str2);
        this.outputSettings = new OutputSettings();
        this.quirksMode = QuirksMode.noQuirks;
        this.updateMetaCharset = false;
        this.location = str2;
        this.parser = Parser.htmlParser();
    }

    public Document(String str) {
        this(Parser.NamespaceHtml, str);
    }

    public static Document createShell(String str) {
        Validate.notNull(str);
        Document document = new Document(str);
        document.parser = document.parser();
        Element appendElement = document.appendElement("html");
        appendElement.appendElement("head");
        appendElement.appendElement("body");
        return document;
    }

    public String location() {
        return this.location;
    }

    public Connection connection() {
        if (this.connection == null) {
            return Jsoup.newSession();
        }
        return this.connection;
    }

    public DocumentType documentType() {
        for (Node node : this.childNodes) {
            if (node instanceof DocumentType) {
                return (DocumentType) node;
            }
            if (!(node instanceof LeafNode)) {
                return null;
            }
        }
        return null;
    }

    private Element htmlEl() {
        Element firstElementChild = firstElementChild();
        while (true) {
            Element element = firstElementChild;
            if (element != null) {
                if (element.nameIs("html")) {
                    return element;
                }
                firstElementChild = element.nextElementSibling();
            } else {
                return appendElement("html");
            }
        }
    }

    public Element head() {
        Element htmlEl = htmlEl();
        Element firstElementChild = htmlEl.firstElementChild();
        while (true) {
            Element element = firstElementChild;
            if (element != null) {
                if (element.nameIs("head")) {
                    return element;
                }
                firstElementChild = element.nextElementSibling();
            } else {
                return htmlEl.prependElement("head");
            }
        }
    }

    public Element body() {
        Element element;
        Element htmlEl = htmlEl();
        Element firstElementChild = htmlEl.firstElementChild();
        while (true) {
            element = firstElementChild;
            if (element != null) {
                if (element.nameIs("body") || element.nameIs("frameset")) {
                    break;
                }
                firstElementChild = element.nextElementSibling();
            } else {
                return htmlEl.appendElement("body");
            }
        }
        return element;
    }

    public List<FormElement> forms() {
        return select("form").forms();
    }

    public FormElement expectForm(String str) {
        Iterator<Element> it = select(str).iterator();
        while (it.hasNext()) {
            Element next = it.next();
            if (next instanceof FormElement) {
                return (FormElement) next;
            }
        }
        Validate.fail("No form elements matched the query '%s' in the document.", str);
        return null;
    }

    public String title() {
        Element selectFirst = head().selectFirst(titleEval);
        return selectFirst != null ? StringUtil.normaliseWhitespace(selectFirst.text()).trim() : "";
    }

    public void title(String str) {
        Validate.notNull(str);
        Element selectFirst = head().selectFirst(titleEval);
        Element element = selectFirst;
        if (selectFirst == null) {
            element = head().appendElement(com.vladsch.flexmark.util.html.Attribute.TITLE_ATTR);
        }
        element.text(str);
    }

    public Element createElement(String str) {
        return new Element(Tag.valueOf(str, this.parser.defaultNamespace(), ParseSettings.preserveCase), baseUri());
    }

    @Override // org.jsoup.nodes.Node
    public String outerHtml() {
        return super.html();
    }

    @Override // org.jsoup.nodes.Element
    public Element text(String str) {
        body().text(str);
        return this;
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    public String nodeName() {
        return "#document";
    }

    public void charset(Charset charset) {
        updateMetaCharsetElement(true);
        this.outputSettings.charset(charset);
        ensureMetaCharsetElement();
    }

    public Charset charset() {
        return this.outputSettings.charset();
    }

    public void updateMetaCharsetElement(boolean z) {
        this.updateMetaCharset = z;
    }

    public boolean updateMetaCharsetElement() {
        return this.updateMetaCharset;
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    /* renamed from: clone */
    public Document mo2530clone() {
        Document document = (Document) super.mo2530clone();
        document.outputSettings = this.outputSettings.m2532clone();
        return document;
    }

    @Override // org.jsoup.nodes.Element, org.jsoup.nodes.Node
    public Document shallowClone() {
        Document document = new Document(tag().namespace(), baseUri());
        if (this.attributes != null) {
            document.attributes = this.attributes.m2529clone();
        }
        document.outputSettings = this.outputSettings.m2532clone();
        return document;
    }

    private void ensureMetaCharsetElement() {
        if (this.updateMetaCharset) {
            OutputSettings.Syntax syntax = outputSettings().syntax();
            if (syntax != OutputSettings.Syntax.html) {
                if (syntax == OutputSettings.Syntax.xml) {
                    Node node = ensureChildNodes().get(0);
                    if (node instanceof XmlDeclaration) {
                        XmlDeclaration xmlDeclaration = (XmlDeclaration) node;
                        if (xmlDeclaration.name().equals("xml")) {
                            xmlDeclaration.attr("encoding", charset().displayName());
                            if (xmlDeclaration.hasAttr("version")) {
                                xmlDeclaration.attr("version", "1.0");
                                return;
                            }
                            return;
                        }
                        XmlDeclaration xmlDeclaration2 = new XmlDeclaration("xml", false);
                        xmlDeclaration2.attr("version", "1.0");
                        xmlDeclaration2.attr("encoding", charset().displayName());
                        prependChild(xmlDeclaration2);
                        return;
                    }
                    XmlDeclaration xmlDeclaration3 = new XmlDeclaration("xml", false);
                    xmlDeclaration3.attr("version", "1.0");
                    xmlDeclaration3.attr("encoding", charset().displayName());
                    prependChild(xmlDeclaration3);
                    return;
                }
                return;
            }
            Element selectFirst = selectFirst("meta[charset]");
            if (selectFirst != null) {
                selectFirst.attr("charset", charset().displayName());
            } else {
                head().appendElement("meta").attr("charset", charset().displayName());
            }
            select("meta[name=charset]").remove();
        }
    }

    /* loaded from: infinitode-2.jar:org/jsoup/nodes/Document$OutputSettings.class */
    public static class OutputSettings implements Cloneable {
        private Charset charset;
        Entities.CoreCharset coreCharset;
        private Entities.EscapeMode escapeMode = Entities.EscapeMode.base;
        private final ThreadLocal<CharsetEncoder> encoderThreadLocal = new ThreadLocal<>();
        private boolean prettyPrint = true;
        private boolean outline = false;
        private int indentAmount = 1;
        private int maxPaddingWidth = 30;
        private Syntax syntax = Syntax.html;

        /* loaded from: infinitode-2.jar:org/jsoup/nodes/Document$OutputSettings$Syntax.class */
        public enum Syntax {
            html,
            xml
        }

        public OutputSettings() {
            charset(DataUtil.UTF_8);
        }

        public Entities.EscapeMode escapeMode() {
            return this.escapeMode;
        }

        public OutputSettings escapeMode(Entities.EscapeMode escapeMode) {
            this.escapeMode = escapeMode;
            return this;
        }

        public Charset charset() {
            return this.charset;
        }

        public OutputSettings charset(Charset charset) {
            this.charset = charset;
            this.coreCharset = Entities.CoreCharset.byName(charset.name());
            return this;
        }

        public OutputSettings charset(String str) {
            charset(Charset.forName(str));
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public CharsetEncoder prepareEncoder() {
            CharsetEncoder newEncoder = this.charset.newEncoder();
            this.encoderThreadLocal.set(newEncoder);
            return newEncoder;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public CharsetEncoder encoder() {
            CharsetEncoder charsetEncoder = this.encoderThreadLocal.get();
            return charsetEncoder != null ? charsetEncoder : prepareEncoder();
        }

        public Syntax syntax() {
            return this.syntax;
        }

        public OutputSettings syntax(Syntax syntax) {
            this.syntax = syntax;
            if (syntax == Syntax.xml) {
                escapeMode(Entities.EscapeMode.xhtml);
            }
            return this;
        }

        public boolean prettyPrint() {
            return this.prettyPrint;
        }

        public OutputSettings prettyPrint(boolean z) {
            this.prettyPrint = z;
            return this;
        }

        public boolean outline() {
            return this.outline;
        }

        public OutputSettings outline(boolean z) {
            this.outline = z;
            return this;
        }

        public int indentAmount() {
            return this.indentAmount;
        }

        public OutputSettings indentAmount(int i) {
            Validate.isTrue(i >= 0);
            this.indentAmount = i;
            return this;
        }

        public int maxPaddingWidth() {
            return this.maxPaddingWidth;
        }

        public OutputSettings maxPaddingWidth(int i) {
            Validate.isTrue(i >= -1);
            this.maxPaddingWidth = i;
            return this;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public OutputSettings m2532clone() {
            try {
                OutputSettings outputSettings = (OutputSettings) super.clone();
                outputSettings.charset(this.charset.name());
                outputSettings.escapeMode = Entities.EscapeMode.valueOf(this.escapeMode.name());
                return outputSettings;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public OutputSettings outputSettings() {
        return this.outputSettings;
    }

    public Document outputSettings(OutputSettings outputSettings) {
        Validate.notNull(outputSettings);
        this.outputSettings = outputSettings;
        return this;
    }

    public QuirksMode quirksMode() {
        return this.quirksMode;
    }

    public Document quirksMode(QuirksMode quirksMode) {
        this.quirksMode = quirksMode;
        return this;
    }

    public Parser parser() {
        return this.parser;
    }

    public Document parser(Parser parser) {
        this.parser = parser;
        return this;
    }

    public Document connection(Connection connection) {
        Validate.notNull(connection);
        this.connection = connection;
        return this;
    }
}
