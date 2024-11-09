package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterable;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Range;
import com.vladsch.flexmark.util.sequence.SegmentedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;
import com.vladsch.flexmark.util.visitor.AstNode;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/Node.class */
public abstract class Node {
    public static final BasedSequence[] EMPTY_SEGMENTS;
    public static final String SPLICE = " … ";
    public static final AstNode<Node> AST_ADAPTER;
    private Node parent;
    Node firstChild;
    private Node lastChild;
    private Node prev;
    Node next;
    private BasedSequence chars;
    static final /* synthetic */ boolean $assertionsDisabled;

    public abstract BasedSequence[] getSegments();

    static {
        $assertionsDisabled = !Node.class.desiredAssertionStatus();
        EMPTY_SEGMENTS = BasedSequence.EMPTY_ARRAY;
        AST_ADAPTER = new AstNode<Node>() { // from class: com.vladsch.flexmark.util.ast.Node.1
            @Override // com.vladsch.flexmark.util.visitor.AstNode
            public final Node getFirstChild(Node node) {
                return node.firstChild;
            }

            @Override // com.vladsch.flexmark.util.visitor.AstNode
            public final Node getNext(Node node) {
                return node.next;
            }
        };
    }

    public Node() {
        this.parent = null;
        this.firstChild = null;
        this.lastChild = null;
        this.prev = null;
        this.next = null;
        this.chars = BasedSequence.NULL;
    }

    public Node(BasedSequence basedSequence) {
        this.parent = null;
        this.firstChild = null;
        this.lastChild = null;
        this.prev = null;
        this.next = null;
        this.chars = BasedSequence.NULL;
        this.chars = basedSequence;
    }

    public int getStartOffset() {
        return this.chars.getStartOffset();
    }

    public int getEndOffset() {
        return this.chars.getEndOffset();
    }

    public int getTextLength() {
        return this.chars.length();
    }

    public BasedSequence getBaseSequence() {
        return this.chars.getBaseSequence();
    }

    public Range getSourceRange() {
        return this.chars.getSourceRange();
    }

    public BasedSequence baseSubSequence(int i, int i2) {
        return this.chars.baseSubSequence(i, i2);
    }

    public BasedSequence baseSubSequence(int i) {
        return this.chars.baseSubSequence(i);
    }

    public BasedSequence getEmptyPrefix() {
        return this.chars.getEmptyPrefix();
    }

    public BasedSequence getEmptySuffix() {
        return this.chars.getEmptySuffix();
    }

    public int getStartOfLine() {
        return this.chars.baseStartOfLine();
    }

    public int getEndOfLine() {
        return this.chars.baseEndOfLine();
    }

    public int startOfLine(int i) {
        return this.chars.baseStartOfLine(i);
    }

    public int endOfLine(int i) {
        return this.chars.baseEndOfLine(i);
    }

    public Pair<Integer, Integer> lineColumnAtIndex(int i) {
        return this.chars.baseLineColumnAtIndex(i);
    }

    public Pair<Integer, Integer> lineColumnAtStart() {
        return this.chars.baseLineColumnAtStart();
    }

    public Pair<Integer, Integer> getLineColumnAtEnd() {
        return this.chars.baseLineColumnAtEnd();
    }

    public Node getAncestorOfType(Class<?>... clsArr) {
        Node parent = getParent();
        while (true) {
            Node node = parent;
            if (node != null) {
                for (Class<?> cls : clsArr) {
                    if (cls.isInstance(node)) {
                        return node;
                    }
                }
                parent = node.getParent();
            } else {
                return null;
            }
        }
    }

    public int countAncestorsOfType(Class<?>... clsArr) {
        int i = 0;
        for (Node parent = getParent(); parent != null; parent = parent.getParent()) {
            int length = clsArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (!clsArr[i2].isInstance(parent)) {
                    i2++;
                } else {
                    i++;
                    break;
                }
            }
        }
        return i;
    }

    public int countDirectAncestorsOfType(Class<?> cls, Class<?>... clsArr) {
        int i = 0;
        for (Node parent = getParent(); parent != null; parent = parent.getParent()) {
            boolean z = false;
            int length = clsArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (clsArr[i2].isInstance(parent)) {
                    i++;
                    z = true;
                    break;
                }
                if (cls == null || !cls.isInstance(parent)) {
                    i2++;
                } else {
                    z = true;
                    break;
                }
            }
            if (!z) {
                break;
            }
        }
        return i;
    }

    public Node getOldestAncestorOfTypeAfter(Class<?> cls, Class<?> cls2) {
        Node node = null;
        for (Node parent = getParent(); parent != null; parent = parent.getParent()) {
            if (!cls.isInstance(parent)) {
                if (cls2.isInstance(parent)) {
                    break;
                }
            } else {
                node = parent;
            }
        }
        return node;
    }

    public Node getChildOfType(Class<?>... clsArr) {
        Node firstChild = getFirstChild();
        while (true) {
            Node node = firstChild;
            if (node != null) {
                for (Class<?> cls : clsArr) {
                    if (cls.isInstance(node)) {
                        return node;
                    }
                }
                firstChild = node.getNext();
            } else {
                return null;
            }
        }
    }

    public static int getNodeOfTypeIndex(Node node, Class<?>... clsArr) {
        int i = 0;
        for (Class<?> cls : clsArr) {
            if (cls.isInstance(node)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean isOrDescendantOfType(Class<?>... clsArr) {
        Node node = this;
        while (true) {
            Node node2 = node;
            if (node2 != null) {
                if (node2.getNodeOfTypeIndex(clsArr) != -1) {
                    return true;
                }
                node = node2.getParent();
            } else {
                return false;
            }
        }
    }

    public int getNodeOfTypeIndex(Class<?>... clsArr) {
        return getNodeOfTypeIndex(this, clsArr);
    }

    public Node getLastBlankLineChild() {
        return null;
    }

    public ReversiblePeekingIterable<Node> getChildren() {
        if (this.firstChild == null) {
            return NodeIterable.EMPTY;
        }
        return new NodeIterable(this.firstChild, this.lastChild, false);
    }

    public ReversiblePeekingIterable<Node> getReversedChildren() {
        if (this.firstChild == null) {
            return NodeIterable.EMPTY;
        }
        return new NodeIterable(this.firstChild, this.lastChild, true);
    }

    public ReversiblePeekingIterable<Node> getDescendants() {
        if (this.firstChild == null) {
            return NodeIterable.EMPTY;
        }
        return new DescendantNodeIterable(getChildren());
    }

    public ReversiblePeekingIterable<Node> getReversedDescendants() {
        if (this.firstChild == null) {
            return NodeIterable.EMPTY;
        }
        return new DescendantNodeIterable(getReversedChildren());
    }

    public ReversiblePeekingIterator<Node> getChildIterator() {
        if (this.firstChild == null) {
            return NodeIterator.EMPTY;
        }
        return new NodeIterator(this.firstChild, this.lastChild, false);
    }

    public ReversiblePeekingIterator<Node> getReversedChildIterator() {
        if (this.firstChild == null) {
            return NodeIterator.EMPTY;
        }
        return new NodeIterator(this.firstChild, this.lastChild, true);
    }

    public BasedSequence getChars() {
        return this.chars;
    }

    public void removeChildren() {
        Node node = this.firstChild;
        while (true) {
            Node node2 = node;
            if (node2 != null) {
                Node next = node2.getNext();
                node2.unlink();
                node = next;
            } else {
                return;
            }
        }
    }

    public boolean hasChildren() {
        return this.firstChild != null;
    }

    public boolean hasOrMoreChildren(int i) {
        if (this.firstChild != null) {
            int i2 = 0;
            ReversiblePeekingIterator<Node> it = getChildren().iterator();
            while (it.hasNext()) {
                it.next();
                i2++;
                if (i2 >= i) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public Document getDocument() {
        Node node;
        Node node2 = this;
        while (true) {
            node = node2;
            if (node == null || (node instanceof Document)) {
                break;
            }
            node2 = node.getParent();
        }
        if ($assertionsDisabled || node != null) {
            return (Document) node;
        }
        throw new AssertionError("Node should always have Document ancestor");
    }

    public void setChars(BasedSequence basedSequence) {
        this.chars = basedSequence;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getLastInChain() {
        Node node = this;
        while (true) {
            Node node2 = node;
            if (!getClass().isInstance(node2.getNext())) {
                return node2;
            }
            node = node2.getNext();
        }
    }

    public Node getPrevious() {
        return this.prev;
    }

    public void extractToFirstInChain(Node node) {
        getFirstInChain().extractChainTo(node);
    }

    public void extractChainTo(Node node) {
        Node node2 = this;
        do {
            Node next = node2.getNext();
            node.appendChild(node2);
            node2 = next;
        } while (getClass().isInstance(node2));
    }

    public Node getFirstInChain() {
        Node node = this;
        while (true) {
            Node node2 = node;
            if (!getClass().isInstance(node2.getPrevious())) {
                return node2;
            }
            node = node2.getPrevious();
        }
    }

    public Node getPreviousAnyNot(Class<?>... clsArr) {
        Node node = this.prev;
        if (clsArr.length > 0) {
            while (node != null && getNodeOfTypeIndex(node, clsArr) != -1) {
                node = node.prev;
            }
        }
        return node;
    }

    public Node getPreviousAny(Class<?>... clsArr) {
        Node node = this.prev;
        if (clsArr.length > 0) {
            while (node != null && getNodeOfTypeIndex(node, clsArr) == -1) {
                node = node.prev;
            }
        }
        return node;
    }

    public Node getNextAnyNot(Class<?>... clsArr) {
        Node node = this.next;
        if (clsArr.length > 0) {
            while (node != null && getNodeOfTypeIndex(node, clsArr) != -1) {
                node = node.next;
            }
        }
        return node;
    }

    public Node getNextAny(Class<?>... clsArr) {
        Node node = this.next;
        if (clsArr.length > 0) {
            while (node != null && getNodeOfTypeIndex(node, clsArr) == -1) {
                node = node.next;
            }
        }
        return node;
    }

    public Node getFirstChild() {
        return this.firstChild;
    }

    public Node getFirstChildAnyNot(Class<?>... clsArr) {
        Node node = this.firstChild;
        if (clsArr.length > 0) {
            while (node != null && getNodeOfTypeIndex(node, clsArr) != -1) {
                node = node.next;
            }
        }
        return node;
    }

    public Node getFirstChildAny(Class<?>... clsArr) {
        Node node = this.firstChild;
        if (clsArr.length > 0) {
            while (node != null && getNodeOfTypeIndex(node, clsArr) == -1) {
                node = node.next;
            }
        }
        return node;
    }

    public Node getLastChild() {
        return this.lastChild;
    }

    public Node getLastChildAnyNot(Class<?>... clsArr) {
        Node node = this.lastChild;
        if (clsArr.length > 0) {
            while (node != null && getNodeOfTypeIndex(node, clsArr) != -1) {
                node = node.prev;
            }
        }
        return node;
    }

    public Node getLastChildAny(Class<?>... clsArr) {
        Node node = this.lastChild;
        if (clsArr.length > 0) {
            while (node != null && getNodeOfTypeIndex(node, clsArr) == -1) {
                node = node.prev;
            }
        }
        return node;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getGrandParent() {
        if (this.parent == null) {
            return null;
        }
        return this.parent.getParent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setParent(Node node) {
        this.parent = node;
    }

    public void appendChild(Node node) {
        node.unlink();
        node.setParent(this);
        if (this.lastChild != null) {
            this.lastChild.next = node;
            node.prev = this.lastChild;
            this.lastChild = node;
        } else {
            this.firstChild = node;
            this.lastChild = node;
        }
    }

    public void prependChild(Node node) {
        node.unlink();
        node.setParent(this);
        if (this.firstChild != null) {
            this.firstChild.prev = node;
            node.next = this.firstChild;
            this.firstChild = node;
        } else {
            this.firstChild = node;
            this.lastChild = node;
        }
    }

    public void unlink() {
        if (this.prev != null) {
            this.prev.next = this.next;
        } else if (this.parent != null) {
            this.parent.firstChild = this.next;
        }
        if (this.next != null) {
            this.next.prev = this.prev;
        } else if (this.parent != null) {
            this.parent.lastChild = this.prev;
        }
        this.parent = null;
        this.next = null;
        this.prev = null;
    }

    public void insertAfter(Node node) {
        node.unlink();
        node.next = this.next;
        if (node.next != null) {
            node.next.prev = node;
        }
        node.prev = this;
        this.next = node;
        node.parent = this.parent;
        if (node.next == null) {
            if (!$assertionsDisabled && node.parent == null) {
                throw new AssertionError();
            }
            node.parent.lastChild = node;
        }
    }

    public void insertBefore(Node node) {
        node.unlink();
        node.prev = this.prev;
        if (node.prev != null) {
            node.prev.next = node;
        }
        node.next = this;
        this.prev = node;
        node.parent = this.parent;
        if (node.prev == null) {
            if (!$assertionsDisabled && node.parent == null) {
                throw new AssertionError();
            }
            node.parent.firstChild = node;
        }
    }

    public String toString() {
        return getClass().getName().substring(getClass().getPackage().getName().length() + 1) + "{" + toStringAttributes() + "}";
    }

    public void getAstExtra(StringBuilder sb) {
    }

    public void astExtraChars(StringBuilder sb) {
        if (getChars().length() > 0) {
            if (getChars().length() <= 10) {
                segmentSpanChars(sb, getChars(), "chars");
            } else {
                segmentSpanChars(sb, getChars().getStartOffset(), getChars().getEndOffset(), "chars", getChars().subSequence(0, 5).toVisibleWhitespaceString(), SPLICE, getChars().subSequence(getChars().length() - 5).toVisibleWhitespaceString());
            }
        }
    }

    public static void astChars(StringBuilder sb, CharSequence charSequence, String str) {
        if (charSequence.length() > 0) {
            if (charSequence.length() <= 10) {
                sb.append(' ').append(str).append(" \"").append(charSequence).append("\"");
            } else {
                sb.append(' ').append(str).append(" \"").append(charSequence.subSequence(0, 5)).append(SPLICE).append(charSequence.subSequence(charSequence.length() - 5, charSequence.length())).append("\"");
            }
        }
    }

    protected String toStringAttributes() {
        return "";
    }

    public static BasedSequence getLeadSegment(BasedSequence[] basedSequenceArr) {
        for (BasedSequence basedSequence : basedSequenceArr) {
            if (basedSequence != BasedSequence.NULL) {
                return basedSequence;
            }
        }
        return BasedSequence.NULL;
    }

    public static BasedSequence getTrailSegment(BasedSequence[] basedSequenceArr) {
        BasedSequence basedSequence;
        int length = basedSequenceArr.length;
        do {
            int i = length;
            length--;
            if (i > 0) {
                basedSequence = basedSequenceArr[length];
            } else {
                return BasedSequence.NULL;
            }
        } while (basedSequence == BasedSequence.NULL);
        return basedSequence;
    }

    public static BasedSequence spanningChars(BasedSequence... basedSequenceArr) {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        BasedSequence basedSequence = null;
        BasedSequence basedSequence2 = null;
        for (BasedSequence basedSequence3 : basedSequenceArr) {
            if (basedSequence3 != BasedSequence.NULL) {
                if (i > basedSequence3.getStartOffset()) {
                    i = basedSequence3.getStartOffset();
                    basedSequence = basedSequence3;
                }
                if (i2 <= basedSequence3.getEndOffset()) {
                    i2 = basedSequence3.getEndOffset();
                    basedSequence2 = basedSequence3;
                }
            }
        }
        if (basedSequence != null && basedSequence2 != null) {
            BasedSequence basedSequence4 = basedSequence;
            return basedSequence4.baseSubSequence(basedSequence4.getStartOffset(), basedSequence2.getEndOffset());
        }
        return BasedSequence.NULL;
    }

    public void setCharsFromContentOnly() {
        this.chars = BasedSequence.NULL;
        setCharsFromContent();
    }

    public void setCharsFromContent() {
        BasedSequence[] segments = getSegments();
        BasedSequence basedSequence = null;
        if (segments.length > 0) {
            BasedSequence leadSegment = getLeadSegment(segments);
            BasedSequence trailSegment = getTrailSegment(segments);
            basedSequence = (this.firstChild == null || this.lastChild == null) ? spanningChars(leadSegment, trailSegment) : spanningChars(leadSegment, trailSegment, this.firstChild.chars, this.lastChild.chars);
        } else if (this.firstChild != null && this.lastChild != null) {
            basedSequence = spanningChars(this.firstChild.chars, this.lastChild.chars);
        }
        if (basedSequence != null) {
            if (this.chars.isNull()) {
                setChars(basedSequence);
            } else {
                setChars(this.chars.baseSubSequence(Utils.min(this.chars.getStartOffset(), basedSequence.getStartOffset()), Utils.max(this.chars.getEndOffset(), basedSequence.getEndOffset())));
            }
        }
    }

    public static void segmentSpan(StringBuilder sb, int i, int i2, String str) {
        if (str != null && !str.trim().isEmpty()) {
            sb.append(SequenceUtils.SPACE).append(str).append(":");
        }
        sb.append("[").append(i).append(", ").append(i2).append("]");
    }

    public static void segmentSpanChars(StringBuilder sb, int i, int i2, String str, String str2) {
        segmentSpanChars(sb, i, i2, str, str2, "", "");
    }

    public static void segmentSpanChars(StringBuilder sb, int i, int i2, String str, String str2, String str3, String str4) {
        if (str != null && !str.trim().isEmpty()) {
            sb.append(SequenceUtils.SPACE).append(str).append(":");
        }
        sb.append("[").append(i).append(", ").append(i2);
        if (!str2.isEmpty() || !str4.isEmpty()) {
            sb.append(", \"");
            Utils.escapeJavaString(sb, str2);
            sb.append(str3);
            Utils.escapeJavaString(sb, str4);
            sb.append("\"");
        }
        sb.append("]");
    }

    public static void segmentSpan(StringBuilder sb, BasedSequence basedSequence, String str) {
        if (basedSequence.isNotNull()) {
            segmentSpan(sb, basedSequence.getStartOffset(), basedSequence.getEndOffset(), str);
        }
    }

    public static void segmentSpanChars(StringBuilder sb, BasedSequence basedSequence, String str) {
        if (basedSequence.isNotNull()) {
            segmentSpanChars(sb, basedSequence.getStartOffset(), basedSequence.getEndOffset(), str, basedSequence.toString());
        }
    }

    public static void segmentSpanCharsToVisible(StringBuilder sb, BasedSequence basedSequence, String str) {
        if (basedSequence.isNotNull()) {
            if (basedSequence.length() <= 10) {
                segmentSpanChars(sb, basedSequence.getStartOffset(), basedSequence.getEndOffset(), str, basedSequence.toVisibleWhitespaceString());
            } else {
                segmentSpanChars(sb, basedSequence.getStartOffset(), basedSequence.getEndOffset(), str, basedSequence.subSequence(0, 5).toVisibleWhitespaceString(), SPLICE, basedSequence.endSequence(basedSequence.length() - 5).toVisibleWhitespaceString());
            }
        }
    }

    public static void delimitedSegmentSpan(StringBuilder sb, BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, String str) {
        segmentSpanChars(sb, basedSequence.getStartOffset(), basedSequence.getEndOffset(), str + "Open", basedSequence.toString());
        if (basedSequence2.length() <= 10) {
            segmentSpanChars(sb, basedSequence2.getStartOffset(), basedSequence2.getEndOffset(), str, basedSequence2.toVisibleWhitespaceString());
        } else {
            segmentSpanChars(sb, basedSequence2.getStartOffset(), basedSequence2.getEndOffset(), str, basedSequence2.subSequence(0, 5).toVisibleWhitespaceString(), SPLICE, basedSequence2.endSequence(basedSequence2.length() - 5).toVisibleWhitespaceString());
        }
        segmentSpanChars(sb, basedSequence3.getStartOffset(), basedSequence3.getEndOffset(), str + "Close", basedSequence3.toString());
    }

    public static void delimitedSegmentSpanChars(StringBuilder sb, BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, String str) {
        if (basedSequence.isNotNull()) {
            segmentSpanChars(sb, basedSequence.getStartOffset(), basedSequence.getEndOffset(), str + "Open", basedSequence.toString());
        }
        if (basedSequence2.isNotNull()) {
            segmentSpanChars(sb, basedSequence2.getStartOffset(), basedSequence2.getEndOffset(), str, basedSequence2.toVisibleWhitespaceString());
        }
        if (basedSequence3.isNotNull()) {
            segmentSpanChars(sb, basedSequence3.getStartOffset(), basedSequence3.getEndOffset(), str + "Close", basedSequence3.toString());
        }
    }

    public void takeChildren(Node node) {
        if (node.firstChild != null) {
            Node node2 = node.firstChild;
            Node node3 = node.lastChild;
            if (!$assertionsDisabled && node3 == null) {
                throw new AssertionError();
            }
            if (node3 != node2) {
                node.firstChild = null;
                node.lastChild = null;
                node2.parent = this;
                node3.parent = this;
                if (this.lastChild != null) {
                    this.lastChild.next = node2;
                    node2.prev = this.lastChild;
                } else {
                    this.firstChild = node2;
                }
                this.lastChild = node3;
                return;
            }
            appendChild(node2);
        }
    }

    public String getNodeName() {
        return getClass().getName().substring(getClass().getPackage().getName().length() + 1);
    }

    public void astString(StringBuilder sb, boolean z) {
        sb.append(getNodeName());
        sb.append("[").append(getStartOffset()).append(", ").append(getEndOffset()).append("]");
        if (z) {
            getAstExtra(sb);
        }
    }

    public String toAstString(boolean z) {
        StringBuilder sb = new StringBuilder();
        astString(sb, z);
        return sb.toString();
    }

    public static String toSegmentSpan(BasedSequence basedSequence, String str) {
        StringBuilder sb = new StringBuilder();
        segmentSpan(sb, basedSequence, str);
        return sb.toString();
    }

    public BasedSequence getChildChars() {
        if (this.firstChild == null || this.lastChild == null) {
            return BasedSequence.NULL;
        }
        return this.firstChild.baseSubSequence(this.firstChild.getStartOffset(), this.lastChild.getEndOffset());
    }

    public BasedSequence getExactChildChars() {
        if (this.firstChild == null || this.lastChild == null) {
            return BasedSequence.NULL;
        }
        SequenceBuilder emptyBuilder = SequenceBuilder.emptyBuilder(getChars());
        for (Node firstChild = getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
            firstChild.getChars().addSegments(emptyBuilder.getSegmentBuilder());
        }
        return emptyBuilder.toSequence();
    }

    public Node getBlankLineSibling() {
        if (!$assertionsDisabled && this.parent == null) {
            throw new AssertionError();
        }
        Node node = this.parent;
        Node node2 = this;
        Node node3 = this;
        while (node.parent != null) {
            Node node4 = node;
            if (!(node4 == node4.parent.getLastChildAnyNot(BlankLine.class))) {
                break;
            }
            node2 = node3;
            if (node instanceof BlankLineContainer) {
                node3 = node;
            }
            Node node5 = node.parent;
            node = node5;
            if (node5 == null) {
                break;
            }
        }
        return node2;
    }

    public void moveTrailingBlankLines() {
        Node lastChild = getLastChild();
        if (lastChild instanceof BlankLine) {
            Node blankLineSibling = getBlankLineSibling();
            blankLineSibling.insertChainAfter(lastChild.getFirstInChain());
            Node node = this;
            do {
                node.setCharsFromContentOnly();
                Node node2 = node.parent;
                node = node2;
                if (node2 == null) {
                    return;
                }
            } while (node != blankLineSibling.getParent());
        }
    }

    public int getLineNumber() {
        return getStartLineNumber();
    }

    public int getStartLineNumber() {
        return getDocument().getLineNumber(this.chars.getStartOffset());
    }

    public int getEndLineNumber() {
        int endOffset = this.chars.getEndOffset();
        return getDocument().getLineNumber(endOffset > 0 ? endOffset - 1 : endOffset);
    }

    public BasedSequence[] getSegmentsForChars() {
        return getSegments();
    }

    public BasedSequence getCharsFromSegments() {
        BasedSequence[] segmentsForChars = getSegmentsForChars();
        return segmentsForChars.length == 0 ? BasedSequence.NULL : SegmentedSequence.create(segmentsForChars[0], Arrays.asList(segmentsForChars));
    }

    public void setCharsFromSegments() {
        setChars(getCharsFromSegments());
    }

    public void appendChain(Node node) {
        Node node2 = node;
        while (true) {
            Node node3 = node2;
            if (node3 != null) {
                Node node4 = node3.next;
                appendChild(node3);
                node2 = node4;
            } else {
                return;
            }
        }
    }

    public void insertChainAfter(Node node) {
        Node node2 = this;
        Node node3 = node;
        while (true) {
            Node node4 = node3;
            if (node4 != null) {
                Node node5 = node4.next;
                node2.insertAfter(node4);
                node2 = node4;
                node3 = node5;
            } else {
                return;
            }
        }
    }

    public void insertChainBefore(Node node) {
        Node node2 = node;
        while (true) {
            Node node3 = node2;
            if (node3 != null) {
                Node node4 = node3.next;
                insertBefore(node3);
                node2 = node4;
            } else {
                return;
            }
        }
    }
}
