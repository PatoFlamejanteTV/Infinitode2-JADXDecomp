package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.HardLineBreak;
import com.vladsch.flexmark.ast.HtmlEntity;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.SoftLineBreak;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.TextBase;
import com.vladsch.flexmark.util.ast.DoNotCollectText;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.ast.Visitor;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.BiConsumer;

@Deprecated
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/TextCollectingVisitor.class */
public class TextCollectingVisitor {
    SequenceBuilder out;
    private final NodeVisitor myVisitor;
    final HashSet<Class<?>> myLineBreakNodes;

    /* JADX INFO: Access modifiers changed from: protected */
    public static Class<?>[] concatArrays(Class<?>[]... clsArr) {
        int i = 0;
        for (Class<?>[] clsArr2 : clsArr) {
            i += clsArr2.length;
        }
        Class<?>[] clsArr3 = new Class[i];
        int i2 = 0;
        for (Class<?>[] clsArr4 : clsArr) {
            System.arraycopy(clsArr4, 0, clsArr3, i2, clsArr4.length);
            i2 += clsArr4.length;
        }
        return clsArr3;
    }

    public TextCollectingVisitor(Class<?>... clsArr) {
        this.myLineBreakNodes = clsArr.length == 0 ? null : new HashSet<>(Arrays.asList(clsArr));
        this.myVisitor = new NodeVisitor(new VisitHandler(Text.class, this::visit), new VisitHandler(TextBase.class, this::visit), new VisitHandler(HtmlEntity.class, this::visit), new VisitHandler(SoftLineBreak.class, this::visit), new VisitHandler(Paragraph.class, this::visit), new VisitHandler(HardLineBreak.class, this::visit)) { // from class: com.vladsch.flexmark.ast.util.TextCollectingVisitor.1
            @Override // com.vladsch.flexmark.util.visitor.AstActionHandler
            public void processNode(Node node, boolean z, BiConsumer<Node, Visitor<Node>> biConsumer) {
                Visitor<Node> action = getAction((AnonymousClass1) node);
                if (action != null) {
                    biConsumer.accept(node, action);
                    return;
                }
                processChildren(node, biConsumer);
                if (TextCollectingVisitor.this.myLineBreakNodes != null && TextCollectingVisitor.this.myLineBreakNodes.contains(node.getClass()) && !node.isOrDescendantOfType(DoNotCollectText.class)) {
                    TextCollectingVisitor.this.out.add(SequenceUtils.EOL);
                }
            }
        };
    }

    public String getText() {
        return this.out.toString();
    }

    public void collect(Node node) {
        this.out = SequenceBuilder.emptyBuilder(node.getChars());
        this.myVisitor.visit(node);
    }

    public String collectAndGetText(Node node) {
        collect(node);
        return this.out.toString();
    }

    public BasedSequence collectAndGetSequence(Node node) {
        collect(node);
        return this.out.toSequence();
    }

    private void visit(Paragraph paragraph) {
        if (!paragraph.isOrDescendantOfType(DoNotCollectText.class)) {
            if (!this.out.isEmpty()) {
                this.out.add("\n\n");
            }
            this.myVisitor.visitChildren(paragraph);
        }
    }

    private void visit(SoftLineBreak softLineBreak) {
        this.out.add(softLineBreak.getChars());
    }

    private void visit(HardLineBreak hardLineBreak) {
        BasedSequence chars = hardLineBreak.getChars();
        this.out.add(chars.subSequence(chars.length() - 1, chars.length()));
    }

    private void visit(HtmlEntity htmlEntity) {
        this.out.add(htmlEntity.getChars().unescape());
    }

    private void visit(Text text) {
        if (!text.isOrDescendantOfType(DoNotCollectText.class)) {
            this.out.add(text.getChars());
        }
    }

    private void visit(TextBase textBase) {
        this.out.add(textBase.getChars());
    }
}
