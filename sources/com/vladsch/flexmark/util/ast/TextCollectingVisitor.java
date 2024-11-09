package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.function.BiConsumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/TextCollectingVisitor.class */
public class TextCollectingVisitor {
    private final NodeVisitor myVisitor = new NodeVisitor() { // from class: com.vladsch.flexmark.util.ast.TextCollectingVisitor.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.vladsch.flexmark.util.visitor.AstActionHandler
        public void processNode(Node node, boolean z, BiConsumer<Node, Visitor<Node>> biConsumer) {
            if (!node.isOrDescendantOfType(DoNotCollectText.class)) {
                if (node instanceof TextContainer) {
                    TextCollectingVisitor.this.out.setLastNode(node);
                    if (((TextContainer) node).collectText(TextCollectingVisitor.this.out, TextCollectingVisitor.this.flags, TextCollectingVisitor.this.myVisitor)) {
                        if ((node instanceof BlankLineBreakNode) && TextCollectingVisitor.this.out.isNotEmpty()) {
                            TextCollectingVisitor.this.out.appendEol();
                        }
                        processChildren(node, biConsumer);
                        if ((node instanceof LineBreakNode) && TextCollectingVisitor.this.out.needEol()) {
                            TextCollectingVisitor.this.out.appendEol();
                            return;
                        }
                        return;
                    }
                    return;
                }
                TextCollectingVisitor.this.out.setLastNode(node);
                if ((node instanceof BlankLineBreakNode) && TextCollectingVisitor.this.out.isNotEmpty()) {
                    TextCollectingVisitor.this.out.appendEol();
                }
                processChildren(node, biConsumer);
                if ((node instanceof LineBreakNode) && TextCollectingVisitor.this.out.needEol()) {
                    TextCollectingVisitor.this.out.appendEol();
                }
            }
        }
    };
    SpaceInsertingSequenceBuilder out;
    int flags;

    public String getText() {
        return this.out.toString();
    }

    public BasedSequence getSequence() {
        return this.out.toSequence();
    }

    public void collect(Node node) {
        collect(node, 0);
    }

    public String collectAndGetText(Node node) {
        return collectAndGetText(node, 0);
    }

    public BasedSequence collectAndGetSequence(Node node) {
        return collectAndGetSequence(node, 0);
    }

    public void collect(Node node, int i) {
        this.out = SpaceInsertingSequenceBuilder.emptyBuilder(node.getChars(), i);
        this.flags = i;
        this.myVisitor.visit(node);
    }

    public String collectAndGetText(Node node, int i) {
        collect(node, i);
        return this.out.toString();
    }

    public BasedSequence collectAndGetSequence(Node node, int i) {
        collect(node, i);
        return this.out.toSequence();
    }
}
