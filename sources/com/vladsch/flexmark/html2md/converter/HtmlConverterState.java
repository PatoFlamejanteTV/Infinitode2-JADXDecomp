package com.vladsch.flexmark.html2md.converter;

import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.html.MutableAttributes;
import java.util.LinkedList;
import java.util.List;
import org.jsoup.nodes.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/HtmlConverterState.class */
public class HtmlConverterState {
    final Node myParent;
    final List<Node> myElements;
    int myIndex = 0;
    final MutableAttributes myAttributes = new MutableAttributes();
    private LinkedList<Runnable> myPrePopActions = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HtmlConverterState(Node node) {
        this.myParent = node;
        this.myElements = node.childNodes();
    }

    public Node getParent() {
        return this.myParent;
    }

    public List<Node> getElements() {
        return this.myElements;
    }

    public int getIndex() {
        return this.myIndex;
    }

    public Attributes getAttributes() {
        return this.myAttributes;
    }

    public LinkedList<Runnable> getPrePopActions() {
        return this.myPrePopActions;
    }

    public void addPrePopAction(Runnable runnable) {
        if (this.myPrePopActions == null) {
            this.myPrePopActions = new LinkedList<>();
        }
        this.myPrePopActions.add(runnable);
    }

    public void runPrePopActions() {
        if (this.myPrePopActions != null) {
            int size = this.myPrePopActions.size();
            while (true) {
                int i = size;
                size--;
                if (i > 0) {
                    this.myPrePopActions.get(size).run();
                } else {
                    return;
                }
            }
        }
    }

    public String toString() {
        return "State{myParent=" + this.myParent + ", myElements=" + this.myElements + ", myIndex=" + this.myIndex + ", myAttributes=" + this.myAttributes + '}';
    }
}
