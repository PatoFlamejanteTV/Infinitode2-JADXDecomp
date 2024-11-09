package org.jsoup.nodes;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

/* loaded from: infinitode-2.jar:org/jsoup/nodes/Node.class */
public abstract class Node implements Cloneable {
    static final List<Node> EmptyNodes = Collections.emptyList();
    static final String EmptyString = "";
    Node parentNode;
    int siblingIndex;

    public abstract String nodeName();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean hasAttributes();

    public abstract Attributes attributes();

    public abstract String baseUri();

    protected abstract void doSetBaseUri(String str);

    protected abstract List<Node> ensureChildNodes();

    public abstract int childNodeSize();

    public abstract Node empty();

    abstract void outerHtmlHead(Appendable appendable, int i, Document.OutputSettings outputSettings);

    abstract void outerHtmlTail(Appendable appendable, int i, Document.OutputSettings outputSettings);

    public String normalName() {
        return nodeName();
    }

    public boolean nameIs(String str) {
        return normalName().equals(str);
    }

    public boolean parentNameIs(String str) {
        return this.parentNode != null && this.parentNode.normalName().equals(str);
    }

    public boolean parentElementIs(String str, String str2) {
        return this.parentNode != null && (this.parentNode instanceof Element) && ((Element) this.parentNode).elementIs(str, str2);
    }

    public boolean hasParent() {
        return this.parentNode != null;
    }

    public String attr(String str) {
        Validate.notNull(str);
        if (!hasAttributes()) {
            return "";
        }
        String ignoreCase = attributes().getIgnoreCase(str);
        if (ignoreCase.length() > 0) {
            return ignoreCase;
        }
        if (str.startsWith("abs:")) {
            return absUrl(str.substring(4));
        }
        return "";
    }

    public int attributesSize() {
        if (hasAttributes()) {
            return attributes().size();
        }
        return 0;
    }

    public Node attr(String str, String str2) {
        attributes().putIgnoreCase(NodeUtils.parser(this).settings().normalizeAttribute(str), str2);
        return this;
    }

    public boolean hasAttr(String str) {
        Validate.notNull(str);
        if (!hasAttributes()) {
            return false;
        }
        if (str.startsWith("abs:")) {
            String substring = str.substring(4);
            if (attributes().hasKeyIgnoreCase(substring) && !absUrl(substring).isEmpty()) {
                return true;
            }
        }
        return attributes().hasKeyIgnoreCase(str);
    }

    public Node removeAttr(String str) {
        Validate.notNull(str);
        if (hasAttributes()) {
            attributes().removeIgnoreCase(str);
        }
        return this;
    }

    public Node clearAttributes() {
        if (hasAttributes()) {
            Iterator<Attribute> it = attributes().iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
        }
        return this;
    }

    public void setBaseUri(String str) {
        Validate.notNull(str);
        doSetBaseUri(str);
    }

    public String absUrl(String str) {
        Validate.notEmpty(str);
        if (!hasAttributes() || !attributes().hasKeyIgnoreCase(str)) {
            return "";
        }
        return StringUtil.resolve(baseUri(), attributes().getIgnoreCase(str));
    }

    public Node childNode(int i) {
        return ensureChildNodes().get(i);
    }

    public List<Node> childNodes() {
        if (childNodeSize() == 0) {
            return EmptyNodes;
        }
        List<Node> ensureChildNodes = ensureChildNodes();
        ArrayList arrayList = new ArrayList(ensureChildNodes.size());
        arrayList.addAll(ensureChildNodes);
        return Collections.unmodifiableList(arrayList);
    }

    public List<Node> childNodesCopy() {
        List<Node> ensureChildNodes = ensureChildNodes();
        ArrayList arrayList = new ArrayList(ensureChildNodes.size());
        Iterator<Node> it = ensureChildNodes.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().mo2530clone());
        }
        return arrayList;
    }

    protected Node[] childNodesAsArray() {
        return (Node[]) ensureChildNodes().toArray(new Node[0]);
    }

    public Node parent() {
        return this.parentNode;
    }

    public final Node parentNode() {
        return this.parentNode;
    }

    public Node root() {
        Node node = this;
        while (true) {
            Node node2 = node;
            if (node2.parentNode != null) {
                node = node2.parentNode;
            } else {
                return node2;
            }
        }
    }

    public Document ownerDocument() {
        Node root = root();
        if (root instanceof Document) {
            return (Document) root;
        }
        return null;
    }

    public void remove() {
        if (this.parentNode != null) {
            this.parentNode.removeChild(this);
        }
    }

    public Node before(String str) {
        addSiblingHtml(this.siblingIndex, str);
        return this;
    }

    public Node before(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        if (node.parentNode == this.parentNode) {
            node.remove();
        }
        this.parentNode.addChildren(this.siblingIndex, node);
        return this;
    }

    public Node after(String str) {
        addSiblingHtml(this.siblingIndex + 1, str);
        return this;
    }

    public Node after(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        if (node.parentNode == this.parentNode) {
            node.remove();
        }
        this.parentNode.addChildren(this.siblingIndex + 1, node);
        return this;
    }

    private void addSiblingHtml(int i, String str) {
        Validate.notNull(str);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(i, (Node[]) NodeUtils.parser(this).parseFragmentInput(str, parent() instanceof Element ? (Element) parent() : null, baseUri()).toArray(new Node[0]));
    }

    public Node wrap(String str) {
        Element element;
        Validate.notEmpty(str);
        if (this.parentNode == null || !(this.parentNode instanceof Element)) {
            element = this instanceof Element ? (Element) this : null;
        } else {
            element = (Element) this.parentNode;
        }
        List<Node> parseFragmentInput = NodeUtils.parser(this).parseFragmentInput(str, element, baseUri());
        Node node = parseFragmentInput.get(0);
        if (!(node instanceof Element)) {
            return this;
        }
        Element element2 = (Element) node;
        Element deepChild = getDeepChild(element2);
        if (this.parentNode != null) {
            this.parentNode.replaceChild(this, element2);
        }
        deepChild.addChildren(this);
        if (parseFragmentInput.size() > 0) {
            for (int i = 0; i < parseFragmentInput.size(); i++) {
                Node node2 = parseFragmentInput.get(i);
                if (element2 != node2) {
                    if (node2.parentNode != null) {
                        node2.parentNode.removeChild(node2);
                    }
                    element2.after(node2);
                }
            }
        }
        return this;
    }

    public Node unwrap() {
        Validate.notNull(this.parentNode);
        Node firstChild = firstChild();
        this.parentNode.addChildren(this.siblingIndex, childNodesAsArray());
        remove();
        return firstChild;
    }

    private Element getDeepChild(Element element) {
        Element firstElementChild = element.firstElementChild();
        while (true) {
            Element element2 = firstElementChild;
            if (element2 != null) {
                element = element2;
                firstElementChild = element2.firstElementChild();
            } else {
                return element;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void nodelistChanged() {
    }

    public void replaceWith(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.replaceChild(this, node);
    }

    protected void setParentNode(Node node) {
        Validate.notNull(node);
        if (this.parentNode != null) {
            this.parentNode.removeChild(this);
        }
        this.parentNode = node;
    }

    protected void replaceChild(Node node, Node node2) {
        Validate.isTrue(node.parentNode == this);
        Validate.notNull(node2);
        if (node == node2) {
            return;
        }
        if (node2.parentNode != null) {
            node2.parentNode.removeChild(node2);
        }
        int i = node.siblingIndex;
        ensureChildNodes().set(i, node2);
        node2.parentNode = this;
        node2.setSiblingIndex(i);
        node.parentNode = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeChild(Node node) {
        Validate.isTrue(node.parentNode == this);
        int i = node.siblingIndex;
        ensureChildNodes().remove(i);
        reindexChildren(i);
        node.parentNode = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addChildren(Node... nodeArr) {
        List<Node> ensureChildNodes = ensureChildNodes();
        for (Node node : nodeArr) {
            reparentChild(node);
            ensureChildNodes.add(node);
            node.setSiblingIndex(ensureChildNodes.size() - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addChildren(int i, Node... nodeArr) {
        Validate.notNull(nodeArr);
        if (nodeArr.length == 0) {
            return;
        }
        List<Node> ensureChildNodes = ensureChildNodes();
        Node parent = nodeArr[0].parent();
        if (parent != null && parent.childNodeSize() == nodeArr.length) {
            boolean z = true;
            List<Node> ensureChildNodes2 = parent.ensureChildNodes();
            int length = nodeArr.length;
            while (true) {
                int i2 = length;
                length--;
                if (i2 > 0) {
                    if (nodeArr[length] != ensureChildNodes2.get(length)) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                boolean z2 = childNodeSize() == 0;
                parent.empty();
                ensureChildNodes.addAll(i, Arrays.asList(nodeArr));
                int length2 = nodeArr.length;
                while (true) {
                    int i3 = length2;
                    length2--;
                    if (i3 <= 0) {
                        break;
                    } else {
                        nodeArr[length2].parentNode = this;
                    }
                }
                if (!z2 || nodeArr[0].siblingIndex != 0) {
                    reindexChildren(i);
                    return;
                }
                return;
            }
        }
        Validate.noNullElements(nodeArr);
        for (Node node : nodeArr) {
            reparentChild(node);
        }
        ensureChildNodes.addAll(i, Arrays.asList(nodeArr));
        reindexChildren(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reparentChild(Node node) {
        node.setParentNode(this);
    }

    private void reindexChildren(int i) {
        int childNodeSize = childNodeSize();
        if (childNodeSize == 0) {
            return;
        }
        List<Node> ensureChildNodes = ensureChildNodes();
        for (int i2 = i; i2 < childNodeSize; i2++) {
            ensureChildNodes.get(i2).setSiblingIndex(i2);
        }
    }

    public List<Node> siblingNodes() {
        if (this.parentNode == null) {
            return Collections.emptyList();
        }
        List<Node> ensureChildNodes = this.parentNode.ensureChildNodes();
        ArrayList arrayList = new ArrayList(ensureChildNodes.size() - 1);
        for (Node node : ensureChildNodes) {
            if (node != this) {
                arrayList.add(node);
            }
        }
        return arrayList;
    }

    public Node nextSibling() {
        if (this.parentNode == null) {
            return null;
        }
        List<Node> ensureChildNodes = this.parentNode.ensureChildNodes();
        int i = this.siblingIndex + 1;
        if (ensureChildNodes.size() > i) {
            return ensureChildNodes.get(i);
        }
        return null;
    }

    public Node previousSibling() {
        if (this.parentNode != null && this.siblingIndex > 0) {
            return this.parentNode.ensureChildNodes().get(this.siblingIndex - 1);
        }
        return null;
    }

    public int siblingIndex() {
        return this.siblingIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSiblingIndex(int i) {
        this.siblingIndex = i;
    }

    public Node firstChild() {
        if (childNodeSize() == 0) {
            return null;
        }
        return ensureChildNodes().get(0);
    }

    public Node lastChild() {
        int childNodeSize = childNodeSize();
        if (childNodeSize == 0) {
            return null;
        }
        return ensureChildNodes().get(childNodeSize - 1);
    }

    public Node traverse(NodeVisitor nodeVisitor) {
        Validate.notNull(nodeVisitor);
        NodeTraversor.traverse(nodeVisitor, this);
        return this;
    }

    public Node forEachNode(Consumer<? super Node> consumer) {
        Validate.notNull(consumer);
        nodeStream().forEach(consumer);
        return this;
    }

    public Node filter(NodeFilter nodeFilter) {
        Validate.notNull(nodeFilter);
        NodeTraversor.filter(nodeFilter, this);
        return this;
    }

    public Stream<Node> nodeStream() {
        return NodeUtils.stream(this, Node.class);
    }

    public <T extends Node> Stream<T> nodeStream(Class<T> cls) {
        return NodeUtils.stream(this, cls);
    }

    public String outerHtml() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        outerHtml(borrowBuilder);
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void outerHtml(Appendable appendable) {
        NodeTraversor.traverse(new OuterHtmlVisitor(appendable, NodeUtils.outputSettings(this)), this);
    }

    public <T extends Appendable> T html(T t) {
        outerHtml(t);
        return t;
    }

    public Range sourceRange() {
        return Range.of(this, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isEffectivelyFirst() {
        if (this.siblingIndex == 0) {
            return true;
        }
        if (this.siblingIndex == 1) {
            Node previousSibling = previousSibling();
            return (previousSibling instanceof TextNode) && ((TextNode) previousSibling).isBlank();
        }
        return false;
    }

    public String toString() {
        return outerHtml();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void indent(Appendable appendable, int i, Document.OutputSettings outputSettings) {
        appendable.append('\n').append(StringUtil.padding(i * outputSettings.indentAmount(), outputSettings.maxPaddingWidth()));
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean hasSameValue(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return outerHtml().equals(((Node) obj).outerHtml());
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Node mo2530clone() {
        Node doClone = doClone(null);
        LinkedList linkedList = new LinkedList();
        linkedList.add(doClone);
        while (!linkedList.isEmpty()) {
            Node node = (Node) linkedList.remove();
            int childNodeSize = node.childNodeSize();
            for (int i = 0; i < childNodeSize; i++) {
                List<Node> ensureChildNodes = node.ensureChildNodes();
                Node doClone2 = ensureChildNodes.get(i).doClone(node);
                ensureChildNodes.set(i, doClone2);
                linkedList.add(doClone2);
            }
        }
        return doClone;
    }

    public Node shallowClone() {
        return doClone(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node doClone(Node node) {
        Document ownerDocument;
        try {
            Node node2 = (Node) super.clone();
            node2.parentNode = node;
            node2.siblingIndex = node == null ? 0 : this.siblingIndex;
            if (node == null && !(this instanceof Document) && (ownerDocument = ownerDocument()) != null) {
                Document shallowClone = ownerDocument.shallowClone();
                node2.parentNode = shallowClone;
                shallowClone.ensureChildNodes().add(node2);
            }
            return node2;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/jsoup/nodes/Node$OuterHtmlVisitor.class */
    public static class OuterHtmlVisitor implements NodeVisitor {
        private final Appendable accum;
        private final Document.OutputSettings out;

        OuterHtmlVisitor(Appendable appendable, Document.OutputSettings outputSettings) {
            this.accum = appendable;
            this.out = outputSettings;
            outputSettings.prepareEncoder();
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int i) {
            try {
                node.outerHtmlHead(this.accum, i, this.out);
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int i) {
            if (!node.nodeName().equals(FlexmarkHtmlConverter.TEXT_NODE)) {
                try {
                    node.outerHtmlTail(this.accum, i, this.out);
                } catch (IOException e) {
                    throw new SerializationException(e);
                }
            }
        }
    }
}
