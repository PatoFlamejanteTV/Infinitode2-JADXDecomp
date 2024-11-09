package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;

/* loaded from: infinitode-2.jar:org/jsoup/select/NodeTraversor.class */
public class NodeTraversor {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !NodeTraversor.class.desiredAssertionStatus();
    }

    public static void traverse(NodeVisitor nodeVisitor, Node node) {
        Validate.notNull(nodeVisitor);
        Validate.notNull(node);
        Node node2 = node;
        int i = 0;
        while (node2 != null) {
            Node parentNode = node2.parentNode();
            int childNodeSize = parentNode != null ? parentNode.childNodeSize() : 0;
            Node nextSibling = node2.nextSibling();
            nodeVisitor.head(node2, i);
            if (parentNode != null && !node2.hasParent()) {
                if (childNodeSize == parentNode.childNodeSize()) {
                    node2 = parentNode.childNode(node2.siblingIndex());
                } else {
                    node2 = nextSibling;
                    if (nextSibling == null) {
                        node2 = parentNode;
                        i--;
                    }
                }
            }
            if (node2.childNodeSize() > 0) {
                node2 = node2.childNode(0);
                i++;
            } else {
                while (true) {
                    if (!$assertionsDisabled && node2 == null) {
                        throw new AssertionError();
                    }
                    if (node2.nextSibling() != null || i <= 0) {
                        break;
                    }
                    nodeVisitor.tail(node2, i);
                    node2 = node2.parentNode();
                    i--;
                }
                nodeVisitor.tail(node2, i);
                if (node2 != node) {
                    node2 = node2.nextSibling();
                } else {
                    return;
                }
            }
        }
    }

    public static void traverse(NodeVisitor nodeVisitor, Elements elements) {
        Validate.notNull(nodeVisitor);
        Validate.notNull(elements);
        Iterator<Element> it = elements.iterator();
        while (it.hasNext()) {
            traverse(nodeVisitor, it.next());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x009d, code lost:            if (r8 == org.jsoup.select.NodeFilter.FilterResult.CONTINUE) goto L40;     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a5, code lost:            if (r8 != org.jsoup.select.NodeFilter.FilterResult.SKIP_CHILDREN) goto L44;     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00be, code lost:            if (r6 != r5) goto L48;     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00c4, code lost:            r0 = r6;        r6 = r6.nextSibling();     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d1, code lost:            if (r8 != org.jsoup.select.NodeFilter.FilterResult.REMOVE) goto L65;     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d4, code lost:            r0.remove();     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00c3, code lost:            return r8;     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00a8, code lost:            r0 = r4.tail(r6, r7);        r8 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00b6, code lost:            if (r0 != org.jsoup.select.NodeFilter.FilterResult.STOP) goto L44;     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00bb, code lost:            return r8;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.jsoup.select.NodeFilter.FilterResult filter(org.jsoup.select.NodeFilter r4, org.jsoup.nodes.Node r5) {
        /*
            Method dump skipped, instructions count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.select.NodeTraversor.filter(org.jsoup.select.NodeFilter, org.jsoup.nodes.Node):org.jsoup.select.NodeFilter$FilterResult");
    }

    public static void filter(NodeFilter nodeFilter, Elements elements) {
        Validate.notNull(nodeFilter);
        Validate.notNull(elements);
        Iterator<Element> it = elements.iterator();
        while (it.hasNext() && filter(nodeFilter, it.next()) != NodeFilter.FilterResult.STOP) {
        }
    }
}
