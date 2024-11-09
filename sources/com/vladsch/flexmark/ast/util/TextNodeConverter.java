package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/TextNodeConverter.class */
public class TextNodeConverter {
    private final BasedSequence nodeChars;
    private BasedSequence remainingChars;
    private final ArrayList<Node> list = new ArrayList<>();
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !TextNodeConverter.class.desiredAssertionStatus();
    }

    public TextNodeConverter(BasedSequence basedSequence) {
        this.nodeChars = basedSequence;
        this.remainingChars = basedSequence;
    }

    public void appendChild(Node node) {
        BasedSequence chars = node.getChars();
        if (!$assertionsDisabled && !this.nodeChars.containsAllOf(chars)) {
            throw new AssertionError("child " + node.toAstString(false) + " is not within parent sequence " + Node.toSegmentSpan(this.nodeChars, null));
        }
        if (!$assertionsDisabled && !this.remainingChars.containsAllOf(chars)) {
            throw new AssertionError("child " + node.toAstString(false) + " is not within remaining sequence " + Node.toSegmentSpan(this.remainingChars, null));
        }
        node.unlink();
        if (!(node instanceof Text)) {
            if (this.remainingChars.getStartOffset() < chars.getStartOffset()) {
                this.list.add(new Text(this.remainingChars.subSequence(0, chars.getStartOffset() - this.remainingChars.getStartOffset())));
            }
            this.remainingChars = this.remainingChars.subSequence(chars.getEndOffset() - this.remainingChars.getStartOffset());
            this.list.add(node);
        }
    }

    public void addChildrenOf(Node node) {
        Node firstChild = node.getFirstChild();
        while (true) {
            Node node2 = firstChild;
            if (node2 != null) {
                Node next = node2.getNext();
                appendChild(node2);
                firstChild = next;
            } else {
                return;
            }
        }
    }

    public void appendMergedTo(Node node) {
        mergeList();
        Iterator<Node> it = this.list.iterator();
        while (it.hasNext()) {
            node.appendChild(it.next());
        }
        clear();
    }

    public void clear() {
        this.list.clear();
        this.remainingChars = BasedSequence.NULL;
    }

    public void insertMergedBefore(Node node) {
        mergeList();
        Iterator<Node> it = this.list.iterator();
        while (it.hasNext()) {
            node.insertBefore(it.next());
        }
        clear();
    }

    public static void mergeTextNodes(Node node) {
        Node node2 = null;
        Node firstChild = node.getFirstChild();
        while (true) {
            Node node3 = firstChild;
            if (node3 != null) {
                Node next = node3.getNext();
                if ((node2 instanceof Text) && (node3 instanceof Text) && node2.getChars().isContinuedBy(node3.getChars())) {
                    node3.setChars(node2.getChars().spliceAtEnd(node3.getChars()));
                    node2.unlink();
                }
                node2 = node3;
                firstChild = next;
            } else {
                return;
            }
        }
    }

    public void insertMergedAfter(Node node) {
        mergeList();
        Iterator<Node> it = this.list.iterator();
        while (it.hasNext()) {
            Node next = it.next();
            node.insertAfter(next);
            node = next;
        }
        clear();
    }

    private void mergeList() {
        if (!this.remainingChars.isEmpty()) {
            this.list.add(new Text(this.remainingChars));
            this.remainingChars = BasedSequence.NULL;
        }
    }

    public List<Node> getMergedList() {
        mergeList();
        return this.list;
    }
}
