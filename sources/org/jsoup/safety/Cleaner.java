package org.jsoup.safety;

import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.ParseErrorList;
import org.jsoup.parser.Parser;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

/* loaded from: infinitode-2.jar:org/jsoup/safety/Cleaner.class */
public class Cleaner {
    private final Safelist safelist;

    public Cleaner(Safelist safelist) {
        Validate.notNull(safelist);
        this.safelist = safelist;
    }

    public Document clean(Document document) {
        Validate.notNull(document);
        Document createShell = Document.createShell(document.baseUri());
        copySafeNodes(document.body(), createShell.body());
        createShell.outputSettings(document.outputSettings().m2532clone());
        return createShell;
    }

    public boolean isValid(Document document) {
        Validate.notNull(document);
        return copySafeNodes(document.body(), Document.createShell(document.baseUri()).body()) == 0 && document.head().childNodes().isEmpty();
    }

    public boolean isValidBodyHtml(String str) {
        Document createShell = Document.createShell("");
        Document createShell2 = Document.createShell("");
        ParseErrorList tracking = ParseErrorList.tracking(1);
        createShell2.body().insertChildren(0, Parser.parseFragment(str, createShell2.body(), "", tracking));
        return copySafeNodes(createShell2.body(), createShell.body()) == 0 && tracking.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/jsoup/safety/Cleaner$CleaningVisitor.class */
    public final class CleaningVisitor implements NodeVisitor {
        private int numDiscarded;
        private final Element root;
        private Element destination;

        private CleaningVisitor(Element element, Element element2) {
            this.numDiscarded = 0;
            this.root = element;
            this.destination = element2;
        }

        @Override // org.jsoup.select.NodeVisitor
        public final void head(Node node, int i) {
            if (!(node instanceof Element)) {
                if (!(node instanceof TextNode)) {
                    if ((node instanceof DataNode) && Cleaner.this.safelist.isSafeTag(node.parent().normalName())) {
                        this.destination.appendChild(new DataNode(((DataNode) node).getWholeData()));
                        return;
                    } else {
                        this.numDiscarded++;
                        return;
                    }
                }
                this.destination.appendChild(new TextNode(((TextNode) node).getWholeText()));
                return;
            }
            Element element = (Element) node;
            if (!Cleaner.this.safelist.isSafeTag(element.normalName())) {
                if (node != this.root) {
                    this.numDiscarded++;
                }
            } else {
                ElementMeta createSafeElement = Cleaner.this.createSafeElement(element);
                Element element2 = createSafeElement.el;
                this.destination.appendChild(element2);
                this.numDiscarded += createSafeElement.numAttribsDiscarded;
                this.destination = element2;
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public final void tail(Node node, int i) {
            if ((node instanceof Element) && Cleaner.this.safelist.isSafeTag(node.normalName())) {
                this.destination = this.destination.parent();
            }
        }
    }

    private int copySafeNodes(Element element, Element element2) {
        CleaningVisitor cleaningVisitor = new CleaningVisitor(element, element2);
        NodeTraversor.traverse(cleaningVisitor, element);
        return cleaningVisitor.numDiscarded;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ElementMeta createSafeElement(Element element) {
        Element shallowClone = element.shallowClone();
        String tagName = element.tagName();
        Attributes attributes = shallowClone.attributes();
        shallowClone.clearAttributes();
        int i = 0;
        Iterator<Attribute> it = element.attributes().iterator();
        while (it.hasNext()) {
            Attribute next = it.next();
            if (this.safelist.isSafeAttribute(tagName, element, next)) {
                attributes.put(next);
            } else {
                i++;
            }
        }
        attributes.addAll(this.safelist.getEnforcedAttributes(tagName));
        shallowClone.attributes().addAll(attributes);
        return new ElementMeta(shallowClone, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/jsoup/safety/Cleaner$ElementMeta.class */
    public static class ElementMeta {
        Element el;
        int numAttribsDiscarded;

        ElementMeta(Element element, int i) {
            this.el = element;
            this.numAttribsDiscarded = i;
        }
    }
}
