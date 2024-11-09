package org.jsoup.nodes;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jsoup.helper.ChangeNotifyingArrayList;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.QueryParser;
import org.jsoup.select.Selector;

/* loaded from: infinitode-2.jar:org/jsoup/nodes/Element.class */
public class Element extends Node {
    private static final List<Element> EmptyChildren = Collections.emptyList();
    private static final Pattern ClassSplit = Pattern.compile("\\s+");
    private static final String BaseUriKey = Attributes.internalKey("baseUri");
    private Tag tag;
    private WeakReference<List<Element>> shadowChildrenRef;
    List<Node> childNodes;
    Attributes attributes;

    @Override // org.jsoup.nodes.Node
    public /* bridge */ /* synthetic */ Node forEachNode(Consumer consumer) {
        return forEachNode((Consumer<? super Node>) consumer);
    }

    public Element(String str, String str2) {
        this(Tag.valueOf(str, str2, ParseSettings.preserveCase), (String) null);
    }

    public Element(String str) {
        this(Tag.valueOf(str, Parser.NamespaceHtml, ParseSettings.preserveCase), "", null);
    }

    public Element(Tag tag, String str, Attributes attributes) {
        Validate.notNull(tag);
        this.childNodes = EmptyNodes;
        this.attributes = attributes;
        this.tag = tag;
        if (str != null) {
            setBaseUri(str);
        }
    }

    public Element(Tag tag, String str) {
        this(tag, str, null);
    }

    protected boolean hasChildNodes() {
        return this.childNodes != EmptyNodes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.nodes.Node
    public List<Node> ensureChildNodes() {
        if (this.childNodes == EmptyNodes) {
            this.childNodes = new NodeList(this, 4);
        }
        return this.childNodes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.nodes.Node
    public boolean hasAttributes() {
        return this.attributes != null;
    }

    @Override // org.jsoup.nodes.Node
    public Attributes attributes() {
        if (this.attributes == null) {
            this.attributes = new Attributes();
        }
        return this.attributes;
    }

    @Override // org.jsoup.nodes.Node
    public String baseUri() {
        return searchUpForAttribute(this, BaseUriKey);
    }

    private static String searchUpForAttribute(Element element, String str) {
        Element element2 = element;
        while (true) {
            Element element3 = element2;
            if (element3 != null) {
                if (element3.attributes != null && element3.attributes.hasKey(str)) {
                    return element3.attributes.get(str);
                }
                element2 = element3.parent();
            } else {
                return "";
            }
        }
    }

    @Override // org.jsoup.nodes.Node
    protected void doSetBaseUri(String str) {
        attributes().put(BaseUriKey, str);
    }

    @Override // org.jsoup.nodes.Node
    public int childNodeSize() {
        return this.childNodes.size();
    }

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return this.tag.getName();
    }

    public String tagName() {
        return this.tag.getName();
    }

    @Override // org.jsoup.nodes.Node
    public String normalName() {
        return this.tag.normalName();
    }

    public boolean elementIs(String str, String str2) {
        return this.tag.normalName().equals(str) && this.tag.namespace().equals(str2);
    }

    public Element tagName(String str) {
        return tagName(str, this.tag.namespace());
    }

    public Element tagName(String str, String str2) {
        Validate.notEmptyParam(str, "tagName");
        Validate.notEmptyParam(str2, "namespace");
        this.tag = Tag.valueOf(str, str2, NodeUtils.parser(this).settings());
        return this;
    }

    public Tag tag() {
        return this.tag;
    }

    public boolean isBlock() {
        return this.tag.isBlock();
    }

    public String id() {
        return this.attributes != null ? this.attributes.getIgnoreCase(com.vladsch.flexmark.util.html.Attribute.ID_ATTR) : "";
    }

    public Element id(String str) {
        Validate.notNull(str);
        attr(com.vladsch.flexmark.util.html.Attribute.ID_ATTR, str);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element attr(String str, String str2) {
        super.attr(str, str2);
        return this;
    }

    public Element attr(String str, boolean z) {
        attributes().put(str, z);
        return this;
    }

    public Attribute attribute(String str) {
        if (hasAttributes()) {
            return attributes().attribute(str);
        }
        return null;
    }

    public Map<String, String> dataset() {
        return attributes().dataset();
    }

    @Override // org.jsoup.nodes.Node
    public final Element parent() {
        return (Element) this.parentNode;
    }

    public Elements parents() {
        Elements elements = new Elements();
        Element parent = parent();
        while (true) {
            Element element = parent;
            if (element == null || element.nameIs("#root")) {
                break;
            }
            elements.add(element);
            parent = element.parent();
        }
        return elements;
    }

    public Element child(int i) {
        return childElementsList().get(i);
    }

    public int childrenSize() {
        return childElementsList().size();
    }

    public Elements children() {
        return new Elements(childElementsList());
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:            if (r0 == 0) goto L10;     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.util.List<org.jsoup.nodes.Element> childElementsList() {
        /*
            r5 = this;
            r0 = r5
            int r0 = r0.childNodeSize()
            if (r0 != 0) goto Lb
            java.util.List<org.jsoup.nodes.Element> r0 = org.jsoup.nodes.Element.EmptyChildren
            return r0
        Lb:
            r0 = r5
            java.lang.ref.WeakReference<java.util.List<org.jsoup.nodes.Element>> r0 = r0.shadowChildrenRef
            if (r0 == 0) goto L21
            r0 = r5
            java.lang.ref.WeakReference<java.util.List<org.jsoup.nodes.Element>> r0 = r0.shadowChildrenRef
            java.lang.Object r0 = r0.get()
            java.util.List r0 = (java.util.List) r0
            r1 = r0
            r6 = r1
            if (r0 != 0) goto L6f
        L21:
            r0 = r5
            java.util.List<org.jsoup.nodes.Node> r0 = r0.childNodes
            int r0 = r0.size()
            r7 = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r2 = r7
            r1.<init>(r2)
            r6 = r0
            r0 = 0
            r8 = r0
        L36:
            r0 = r8
            r1 = r7
            if (r0 >= r1) goto L63
            r0 = r5
            java.util.List<org.jsoup.nodes.Node> r0 = r0.childNodes
            r1 = r8
            java.lang.Object r0 = r0.get(r1)
            org.jsoup.nodes.Node r0 = (org.jsoup.nodes.Node) r0
            r1 = r0
            r9 = r1
            boolean r0 = r0 instanceof org.jsoup.nodes.Element
            if (r0 == 0) goto L5d
            r0 = r6
            r1 = r9
            org.jsoup.nodes.Element r1 = (org.jsoup.nodes.Element) r1
            boolean r0 = r0.add(r1)
        L5d:
            int r8 = r8 + 1
            goto L36
        L63:
            r0 = r5
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference
            r2 = r1
            r3 = r6
            r2.<init>(r3)
            r0.shadowChildrenRef = r1
        L6f:
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.nodes.Element.childElementsList():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.jsoup.nodes.Node
    public void nodelistChanged() {
        super.nodelistChanged();
        this.shadowChildrenRef = null;
    }

    public Stream<Element> stream() {
        return NodeUtils.stream(this, Element.class);
    }

    private <T> List<T> filterNodes(Class<T> cls) {
        Stream<Node> stream = this.childNodes.stream();
        Objects.requireNonNull(cls);
        Stream<Node> filter = stream.filter((v1) -> {
            return r1.isInstance(v1);
        });
        Objects.requireNonNull(cls);
        return (List) filter.map((v1) -> {
            return r1.cast(v1);
        }).collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public List<TextNode> textNodes() {
        return filterNodes(TextNode.class);
    }

    public List<DataNode> dataNodes() {
        return filterNodes(DataNode.class);
    }

    public Elements select(String str) {
        return Selector.select(str, this);
    }

    public Elements select(Evaluator evaluator) {
        return Selector.select(evaluator, this);
    }

    public Element selectFirst(String str) {
        return Selector.selectFirst(str, this);
    }

    public Element selectFirst(Evaluator evaluator) {
        return Collector.findFirst(evaluator, this);
    }

    public Element expectFirst(String str) {
        String str2;
        Element selectFirst = Selector.selectFirst(str, this);
        if (parent() != null) {
            str2 = "No elements matched the query '%s' on element '%s'.";
        } else {
            str2 = "No elements matched the query '%s' in the document.";
        }
        return (Element) Validate.ensureNotNull(selectFirst, str2, str, tagName());
    }

    public boolean is(String str) {
        return is(QueryParser.parse(str));
    }

    public boolean is(Evaluator evaluator) {
        return evaluator.matches(root(), this);
    }

    public Element closest(String str) {
        return closest(QueryParser.parse(str));
    }

    public Element closest(Evaluator evaluator) {
        Validate.notNull(evaluator);
        Element element = this;
        Element root = root();
        while (!evaluator.matches(root, element)) {
            Element parent = element.parent();
            element = parent;
            if (parent == null) {
                return null;
            }
        }
        return element;
    }

    public Elements selectXpath(String str) {
        return new Elements((List<Element>) NodeUtils.selectXpath(str, this, Element.class));
    }

    public <T extends Node> List<T> selectXpath(String str, Class<T> cls) {
        return NodeUtils.selectXpath(str, this, cls);
    }

    public Element appendChild(Node node) {
        Validate.notNull(node);
        reparentChild(node);
        ensureChildNodes();
        this.childNodes.add(node);
        node.setSiblingIndex(this.childNodes.size() - 1);
        return this;
    }

    public Element appendChildren(Collection<? extends Node> collection) {
        insertChildren(-1, collection);
        return this;
    }

    public Element appendTo(Element element) {
        Validate.notNull(element);
        element.appendChild(this);
        return this;
    }

    public Element prependChild(Node node) {
        Validate.notNull(node);
        addChildren(0, node);
        return this;
    }

    public Element prependChildren(Collection<? extends Node> collection) {
        insertChildren(0, collection);
        return this;
    }

    public Element insertChildren(int i, Collection<? extends Node> collection) {
        Validate.notNull(collection, "Children collection to be inserted must not be null.");
        int childNodeSize = childNodeSize();
        if (i < 0) {
            i += childNodeSize + 1;
        }
        Validate.isTrue(i >= 0 && i <= childNodeSize, "Insert position out of bounds.");
        addChildren(i, (Node[]) new ArrayList(collection).toArray(new Node[0]));
        return this;
    }

    public Element insertChildren(int i, Node... nodeArr) {
        Validate.notNull(nodeArr, "Children collection to be inserted must not be null.");
        int childNodeSize = childNodeSize();
        if (i < 0) {
            i += childNodeSize + 1;
        }
        Validate.isTrue(i >= 0 && i <= childNodeSize, "Insert position out of bounds.");
        addChildren(i, nodeArr);
        return this;
    }

    public Element appendElement(String str) {
        return appendElement(str, this.tag.namespace());
    }

    public Element appendElement(String str, String str2) {
        Element element = new Element(Tag.valueOf(str, str2, NodeUtils.parser(this).settings()), baseUri());
        appendChild(element);
        return element;
    }

    public Element prependElement(String str) {
        return prependElement(str, this.tag.namespace());
    }

    public Element prependElement(String str, String str2) {
        Element element = new Element(Tag.valueOf(str, str2, NodeUtils.parser(this).settings()), baseUri());
        prependChild(element);
        return element;
    }

    public Element appendText(String str) {
        Validate.notNull(str);
        appendChild(new TextNode(str));
        return this;
    }

    public Element prependText(String str) {
        Validate.notNull(str);
        prependChild(new TextNode(str));
        return this;
    }

    public Element append(String str) {
        Validate.notNull(str);
        addChildren((Node[]) NodeUtils.parser(this).parseFragmentInput(str, this, baseUri()).toArray(new Node[0]));
        return this;
    }

    public Element prepend(String str) {
        Validate.notNull(str);
        addChildren(0, (Node[]) NodeUtils.parser(this).parseFragmentInput(str, this, baseUri()).toArray(new Node[0]));
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element before(String str) {
        return (Element) super.before(str);
    }

    @Override // org.jsoup.nodes.Node
    public Element before(Node node) {
        return (Element) super.before(node);
    }

    @Override // org.jsoup.nodes.Node
    public Element after(String str) {
        return (Element) super.after(str);
    }

    @Override // org.jsoup.nodes.Node
    public Element after(Node node) {
        return (Element) super.after(node);
    }

    @Override // org.jsoup.nodes.Node
    public Element empty() {
        Iterator<Node> it = this.childNodes.iterator();
        while (it.hasNext()) {
            it.next().parentNode = null;
        }
        this.childNodes.clear();
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element wrap(String str) {
        return (Element) super.wrap(str);
    }

    public String cssSelector() {
        if (id().length() > 0) {
            String str = "#" + TokenQueue.escapeCssIdentifier(id());
            Document ownerDocument = ownerDocument();
            if (ownerDocument != null) {
                Elements select = ownerDocument.select(str);
                if (select.size() == 1 && select.get(0) == this) {
                    return str;
                }
            } else {
                return str;
            }
        }
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        Element element = this;
        while (true) {
            Element element2 = element;
            if (element2 == null || (element2 instanceof Document)) {
                break;
            }
            borrowBuilder.insert(0, element2.cssSelectorComponent());
            element = element2.parent();
        }
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    private String cssSelectorComponent() {
        StringBuilder append = StringUtil.borrowBuilder().append(TokenQueue.escapeCssIdentifier(tagName()).replace("\\:", "|"));
        StringUtil.StringJoiner stringJoiner = new StringUtil.StringJoiner(".");
        Iterator<String> it = classNames().iterator();
        while (it.hasNext()) {
            stringJoiner.add(TokenQueue.escapeCssIdentifier(it.next()));
        }
        String complete = stringJoiner.complete();
        if (complete.length() > 0) {
            append.append('.').append(complete);
        }
        if (parent() == null || (parent() instanceof Document)) {
            return StringUtil.releaseBuilder(append);
        }
        append.insert(0, " > ");
        if (parent().select(append.toString()).size() > 1) {
            append.append(String.format(":nth-child(%d)", Integer.valueOf(elementSiblingIndex() + 1)));
        }
        return StringUtil.releaseBuilder(append);
    }

    public Elements siblingElements() {
        if (this.parentNode == null) {
            return new Elements(0);
        }
        List<Element> childElementsList = parent().childElementsList();
        Elements elements = new Elements(childElementsList.size() - 1);
        for (Element element : childElementsList) {
            if (element != this) {
                elements.add(element);
            }
        }
        return elements;
    }

    public Element nextElementSibling() {
        Element element = this;
        do {
            Node nextSibling = element.nextSibling();
            element = nextSibling;
            if (nextSibling == null) {
                return null;
            }
        } while (!(element instanceof Element));
        return element;
    }

    public Elements nextElementSiblings() {
        return nextElementSiblings(true);
    }

    public Element previousElementSibling() {
        Element element = this;
        do {
            Node previousSibling = element.previousSibling();
            element = previousSibling;
            if (previousSibling == null) {
                return null;
            }
        } while (!(element instanceof Element));
        return element;
    }

    public Elements previousElementSiblings() {
        return nextElementSiblings(false);
    }

    private Elements nextElementSiblings(boolean z) {
        Elements elements = new Elements();
        if (this.parentNode == null) {
            return elements;
        }
        elements.add(this);
        return z ? elements.nextAll() : elements.prevAll();
    }

    public Element firstElementSibling() {
        if (parent() != null) {
            return parent().firstElementChild();
        }
        return this;
    }

    public int elementSiblingIndex() {
        if (parent() == null) {
            return 0;
        }
        return indexInList(this, parent().childElementsList());
    }

    public Element lastElementSibling() {
        if (parent() != null) {
            return parent().lastElementChild();
        }
        return this;
    }

    private static <E extends Element> int indexInList(Element element, List<E> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i) == element) {
                return i;
            }
        }
        return 0;
    }

    public Element firstElementChild() {
        Node firstChild = firstChild();
        while (true) {
            Node node = firstChild;
            if (node != null) {
                if (node instanceof Element) {
                    return (Element) node;
                }
                firstChild = node.nextSibling();
            } else {
                return null;
            }
        }
    }

    public Element lastElementChild() {
        Node lastChild = lastChild();
        while (true) {
            Node node = lastChild;
            if (node != null) {
                if (node instanceof Element) {
                    return (Element) node;
                }
                lastChild = node.previousSibling();
            } else {
                return null;
            }
        }
    }

    public Elements getElementsByTag(String str) {
        Validate.notEmpty(str);
        return Collector.collect(new Evaluator.Tag(Normalizer.normalize(str)), this);
    }

    public Element getElementById(String str) {
        Validate.notEmpty(str);
        Elements collect = Collector.collect(new Evaluator.Id(str), this);
        if (collect.size() > 0) {
            return collect.get(0);
        }
        return null;
    }

    public Elements getElementsByClass(String str) {
        Validate.notEmpty(str);
        return Collector.collect(new Evaluator.Class(str), this);
    }

    public Elements getElementsByAttribute(String str) {
        Validate.notEmpty(str);
        return Collector.collect(new Evaluator.Attribute(str.trim()), this);
    }

    public Elements getElementsByAttributeStarting(String str) {
        Validate.notEmpty(str);
        return Collector.collect(new Evaluator.AttributeStarting(str.trim()), this);
    }

    public Elements getElementsByAttributeValue(String str, String str2) {
        return Collector.collect(new Evaluator.AttributeWithValue(str, str2), this);
    }

    public Elements getElementsByAttributeValueNot(String str, String str2) {
        return Collector.collect(new Evaluator.AttributeWithValueNot(str, str2), this);
    }

    public Elements getElementsByAttributeValueStarting(String str, String str2) {
        return Collector.collect(new Evaluator.AttributeWithValueStarting(str, str2), this);
    }

    public Elements getElementsByAttributeValueEnding(String str, String str2) {
        return Collector.collect(new Evaluator.AttributeWithValueEnding(str, str2), this);
    }

    public Elements getElementsByAttributeValueContaining(String str, String str2) {
        return Collector.collect(new Evaluator.AttributeWithValueContaining(str, str2), this);
    }

    public Elements getElementsByAttributeValueMatching(String str, Pattern pattern) {
        return Collector.collect(new Evaluator.AttributeWithValueMatching(str, pattern), this);
    }

    public Elements getElementsByAttributeValueMatching(String str, String str2) {
        try {
            return getElementsByAttributeValueMatching(str, Pattern.compile(str2));
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + str2, e);
        }
    }

    public Elements getElementsByIndexLessThan(int i) {
        return Collector.collect(new Evaluator.IndexLessThan(i), this);
    }

    public Elements getElementsByIndexGreaterThan(int i) {
        return Collector.collect(new Evaluator.IndexGreaterThan(i), this);
    }

    public Elements getElementsByIndexEquals(int i) {
        return Collector.collect(new Evaluator.IndexEquals(i), this);
    }

    public Elements getElementsContainingText(String str) {
        return Collector.collect(new Evaluator.ContainsText(str), this);
    }

    public Elements getElementsContainingOwnText(String str) {
        return Collector.collect(new Evaluator.ContainsOwnText(str), this);
    }

    public Elements getElementsMatchingText(Pattern pattern) {
        return Collector.collect(new Evaluator.Matches(pattern), this);
    }

    public Elements getElementsMatchingText(String str) {
        try {
            return getElementsMatchingText(Pattern.compile(str));
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + str, e);
        }
    }

    public Elements getElementsMatchingOwnText(Pattern pattern) {
        return Collector.collect(new Evaluator.MatchesOwn(pattern), this);
    }

    public Elements getElementsMatchingOwnText(String str) {
        try {
            return getElementsMatchingOwnText(Pattern.compile(str));
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + str, e);
        }
    }

    public Elements getAllElements() {
        return Collector.collect(new Evaluator.AllElements(), this);
    }

    public String text() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        NodeTraversor.traverse(new TextAccumulator(borrowBuilder), this);
        return StringUtil.releaseBuilder(borrowBuilder).trim();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/jsoup/nodes/Element$TextAccumulator.class */
    public static class TextAccumulator implements NodeVisitor {
        private final StringBuilder accum;

        public TextAccumulator(StringBuilder sb) {
            this.accum = sb;
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int i) {
            if (node instanceof TextNode) {
                Element.appendNormalisedText(this.accum, (TextNode) node);
            } else if (node instanceof Element) {
                Element element = (Element) node;
                if (this.accum.length() > 0) {
                    if ((element.isBlock() || element.nameIs(FlexmarkHtmlConverter.BR_NODE)) && !TextNode.lastCharIsWhitespace(this.accum)) {
                        this.accum.append(' ');
                    }
                }
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int i) {
            if (node instanceof Element) {
                Node nextSibling = node.nextSibling();
                if (((Element) node).isBlock()) {
                    if (((nextSibling instanceof TextNode) || ((nextSibling instanceof Element) && !((Element) nextSibling).tag.formatAsBlock())) && !TextNode.lastCharIsWhitespace(this.accum)) {
                        this.accum.append(' ');
                    }
                }
            }
        }
    }

    public String wholeText() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        nodeStream().forEach(node -> {
            appendWholeText(node, borrowBuilder);
        });
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendWholeText(Node node, StringBuilder sb) {
        if (node instanceof TextNode) {
            sb.append(((TextNode) node).getWholeText());
        } else if (node.nameIs(FlexmarkHtmlConverter.BR_NODE)) {
            sb.append(SequenceUtils.EOL);
        }
    }

    public String wholeOwnText() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        int childNodeSize = childNodeSize();
        for (int i = 0; i < childNodeSize; i++) {
            appendWholeText(this.childNodes.get(i), borrowBuilder);
        }
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    public String ownText() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        ownText(borrowBuilder);
        return StringUtil.releaseBuilder(borrowBuilder).trim();
    }

    private void ownText(StringBuilder sb) {
        for (int i = 0; i < childNodeSize(); i++) {
            Node node = this.childNodes.get(i);
            if (!(node instanceof TextNode)) {
                if (node.nameIs(FlexmarkHtmlConverter.BR_NODE) && !TextNode.lastCharIsWhitespace(sb)) {
                    sb.append(SequenceUtils.SPACE);
                }
            } else {
                appendNormalisedText(sb, (TextNode) node);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendNormalisedText(StringBuilder sb, TextNode textNode) {
        String wholeText = textNode.getWholeText();
        if (preserveWhitespace(textNode.parentNode) || (textNode instanceof CDataNode)) {
            sb.append(wholeText);
        } else {
            StringUtil.appendNormalisedWhitespace(sb, wholeText, TextNode.lastCharIsWhitespace(sb));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean preserveWhitespace(Node node) {
        if (node instanceof Element) {
            Element element = (Element) node;
            int i = 0;
            while (!element.tag.preserveWhitespace()) {
                element = element.parent();
                i++;
                if (i >= 6 || element == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Element text(String str) {
        Validate.notNull(str);
        empty();
        Document ownerDocument = ownerDocument();
        if (ownerDocument != null && ownerDocument.parser().isContentForTagData(normalName())) {
            appendChild(new DataNode(str));
        } else {
            appendChild(new TextNode(str));
        }
        return this;
    }

    public boolean hasText() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        filter((node, i) -> {
            if ((node instanceof TextNode) && !((TextNode) node).isBlank()) {
                atomicBoolean.set(true);
                return NodeFilter.FilterResult.STOP;
            }
            return NodeFilter.FilterResult.CONTINUE;
        });
        return atomicBoolean.get();
    }

    public String data() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        traverse((node, i) -> {
            if (node instanceof DataNode) {
                borrowBuilder.append(((DataNode) node).getWholeData());
            } else if (node instanceof Comment) {
                borrowBuilder.append(((Comment) node).getData());
            } else if (node instanceof CDataNode) {
                borrowBuilder.append(((CDataNode) node).getWholeText());
            }
        });
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    public String className() {
        return attr(com.vladsch.flexmark.util.html.Attribute.CLASS_ATTR).trim();
    }

    public Set<String> classNames() {
        LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(ClassSplit.split(className())));
        linkedHashSet.remove("");
        return linkedHashSet;
    }

    public Element classNames(Set<String> set) {
        Validate.notNull(set);
        if (set.isEmpty()) {
            attributes().remove(com.vladsch.flexmark.util.html.Attribute.CLASS_ATTR);
        } else {
            attributes().put(com.vladsch.flexmark.util.html.Attribute.CLASS_ATTR, StringUtil.join(set, SequenceUtils.SPACE));
        }
        return this;
    }

    public boolean hasClass(String str) {
        if (this.attributes == null) {
            return false;
        }
        String ignoreCase = this.attributes.getIgnoreCase(com.vladsch.flexmark.util.html.Attribute.CLASS_ATTR);
        int length = ignoreCase.length();
        int length2 = str.length();
        if (length == 0 || length < length2) {
            return false;
        }
        if (length == length2) {
            return str.equalsIgnoreCase(ignoreCase);
        }
        boolean z = false;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (Character.isWhitespace(ignoreCase.charAt(i2))) {
                if (!z) {
                    continue;
                } else {
                    if (i2 - i == length2 && ignoreCase.regionMatches(true, i, str, 0, length2)) {
                        return true;
                    }
                    z = false;
                }
            } else if (!z) {
                z = true;
                i = i2;
            }
        }
        if (z && length - i == length2) {
            return ignoreCase.regionMatches(true, i, str, 0, length2);
        }
        return false;
    }

    public Element addClass(String str) {
        Validate.notNull(str);
        Set<String> classNames = classNames();
        classNames.add(str);
        classNames(classNames);
        return this;
    }

    public Element removeClass(String str) {
        Validate.notNull(str);
        Set<String> classNames = classNames();
        classNames.remove(str);
        classNames(classNames);
        return this;
    }

    public Element toggleClass(String str) {
        Validate.notNull(str);
        Set<String> classNames = classNames();
        if (classNames.contains(str)) {
            classNames.remove(str);
        } else {
            classNames.add(str);
        }
        classNames(classNames);
        return this;
    }

    public String val() {
        if (elementIs("textarea", Parser.NamespaceHtml)) {
            return text();
        }
        return attr("value");
    }

    public Element val(String str) {
        if (elementIs("textarea", Parser.NamespaceHtml)) {
            text(str);
        } else {
            attr("value", str);
        }
        return this;
    }

    public Range endSourceRange() {
        return Range.of(this, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldIndent(Document.OutputSettings outputSettings) {
        return outputSettings.prettyPrint() && isFormatAsBlock(outputSettings) && !isInlineable(outputSettings) && !preserveWhitespace(this.parentNode);
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings) {
        if (shouldIndent(outputSettings) && (!(appendable instanceof StringBuilder) || ((StringBuilder) appendable).length() > 0)) {
            indent(appendable, i, outputSettings);
        }
        appendable.append('<').append(tagName());
        if (this.attributes != null) {
            this.attributes.html(appendable, outputSettings);
        }
        if (this.childNodes.isEmpty() && this.tag.isSelfClosing()) {
            if (outputSettings.syntax() == Document.OutputSettings.Syntax.html && this.tag.isEmpty()) {
                appendable.append('>');
                return;
            } else {
                appendable.append(" />");
                return;
            }
        }
        appendable.append('>');
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings) {
        if (!this.childNodes.isEmpty() || !this.tag.isSelfClosing()) {
            if (outputSettings.prettyPrint() && !this.childNodes.isEmpty() && ((this.tag.formatAsBlock() && !preserveWhitespace(this.parentNode)) || (outputSettings.outline() && (this.childNodes.size() > 1 || (this.childNodes.size() == 1 && (this.childNodes.get(0) instanceof Element)))))) {
                indent(appendable, i, outputSettings);
            }
            appendable.append("</").append(tagName()).append('>');
        }
    }

    public String html() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        html((Element) borrowBuilder);
        String releaseBuilder = StringUtil.releaseBuilder(borrowBuilder);
        return NodeUtils.outputSettings(this).prettyPrint() ? releaseBuilder.trim() : releaseBuilder;
    }

    @Override // org.jsoup.nodes.Node
    public <T extends Appendable> T html(T t) {
        int size = this.childNodes.size();
        for (int i = 0; i < size; i++) {
            this.childNodes.get(i).outerHtml(t);
        }
        return t;
    }

    public Element html(String str) {
        empty();
        append(str);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    /* renamed from: clone */
    public Element mo2530clone() {
        return (Element) super.mo2530clone();
    }

    @Override // org.jsoup.nodes.Node
    public Element shallowClone() {
        String baseUri = baseUri();
        String str = baseUri;
        if (baseUri.isEmpty()) {
            str = null;
        }
        return new Element(this.tag, str, this.attributes == null ? null : this.attributes.m2529clone());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jsoup.nodes.Node
    public Element doClone(Node node) {
        Element element = (Element) super.doClone(node);
        element.attributes = this.attributes != null ? this.attributes.m2529clone() : null;
        element.childNodes = new NodeList(element, this.childNodes.size());
        element.childNodes.addAll(this.childNodes);
        return element;
    }

    @Override // org.jsoup.nodes.Node
    public Element clearAttributes() {
        if (this.attributes != null) {
            super.clearAttributes();
            if (this.attributes.size() == 0) {
                this.attributes = null;
            }
        }
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element removeAttr(String str) {
        return (Element) super.removeAttr(str);
    }

    @Override // org.jsoup.nodes.Node
    public Element root() {
        return (Element) super.root();
    }

    @Override // org.jsoup.nodes.Node
    public Element traverse(NodeVisitor nodeVisitor) {
        return (Element) super.traverse(nodeVisitor);
    }

    @Override // org.jsoup.nodes.Node
    public Element forEachNode(Consumer<? super Node> consumer) {
        return (Element) super.forEachNode(consumer);
    }

    @Deprecated
    public Element forEach(Consumer<? super Element> consumer) {
        stream().forEach(consumer);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    public Element filter(NodeFilter nodeFilter) {
        return (Element) super.filter(nodeFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/jsoup/nodes/Element$NodeList.class */
    public static final class NodeList extends ChangeNotifyingArrayList<Node> {
        private final Element owner;

        NodeList(Element element, int i) {
            super(i);
            this.owner = element;
        }

        @Override // org.jsoup.helper.ChangeNotifyingArrayList
        public final void onContentsChanged() {
            this.owner.nodelistChanged();
        }
    }

    private boolean isFormatAsBlock(Document.OutputSettings outputSettings) {
        if (this.tag.isBlock()) {
            return true;
        }
        return (parent() != null && parent().tag().formatAsBlock()) || outputSettings.outline();
    }

    private boolean isInlineable(Document.OutputSettings outputSettings) {
        if (this.tag.isInline()) {
            return ((parent() != null && !parent().isBlock()) || isEffectivelyFirst() || outputSettings.outline() || nameIs(FlexmarkHtmlConverter.BR_NODE)) ? false : true;
        }
        return false;
    }
}
