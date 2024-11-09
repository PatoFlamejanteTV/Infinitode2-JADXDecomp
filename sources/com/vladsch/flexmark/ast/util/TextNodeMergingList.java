package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/TextNodeMergingList.class */
public class TextNodeMergingList {
    private ArrayList<Node> list = new ArrayList<>();
    private boolean isMerged = true;

    public void add(Node node) {
        this.list.add(node);
        if (node instanceof Text) {
            this.isMerged = false;
        }
    }

    public void add(BasedSequence basedSequence) {
        if (!basedSequence.isEmpty()) {
            add(new Text(basedSequence));
        }
    }

    public void addChildrenOf(Node node) {
        Node firstChild = node.getFirstChild();
        while (true) {
            Node node2 = firstChild;
            if (node2 != null) {
                Node next = node2.getNext();
                node2.unlink();
                add(node2);
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
    }

    public void clear() {
        this.list.clear();
        this.isMerged = true;
    }

    public void insertMergedBefore(Node node) {
        mergeList();
        Iterator<Node> it = this.list.iterator();
        while (it.hasNext()) {
            node.insertBefore(it.next());
        }
        clear();
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
        if (!this.isMerged) {
            ArrayList<Node> arrayList = null;
            Node node = null;
            Iterator<Node> it = this.list.iterator();
            while (it.hasNext()) {
                Node next = it.next();
                if (next instanceof Text) {
                    if (!next.getChars().isEmpty()) {
                        if (node != null) {
                            if (node.getChars().isContinuedBy(next.getChars())) {
                                Node node2 = node;
                                node2.setChars(node2.getChars().spliceAtEnd(next.getChars()));
                            } else {
                                if (arrayList == null) {
                                    arrayList = new ArrayList<>();
                                }
                                arrayList.add(node);
                                node = next;
                            }
                        } else {
                            node = next;
                        }
                    }
                } else {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    if (node != null) {
                        arrayList.add(node);
                        node = null;
                    }
                    arrayList.add(next);
                }
            }
            if (node != null) {
                if (arrayList == null) {
                    this.list.clear();
                    this.list.add(node);
                } else {
                    arrayList.add(node);
                }
            }
            if (arrayList != null) {
                this.list = arrayList;
            }
        }
    }

    public List<Node> getMergedList() {
        mergeList();
        return this.list;
    }
}
