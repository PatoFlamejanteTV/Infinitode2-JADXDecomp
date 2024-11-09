package com.vladsch.flexmark.ext.enumerated.reference.internal;

import com.vladsch.flexmark.ast.HardLineBreak;
import com.vladsch.flexmark.ast.HtmlEntity;
import com.vladsch.flexmark.ast.SoftLineBreak;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.TextBase;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceBlock;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceLink;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceRendering;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceText;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferences;
import com.vladsch.flexmark.util.ast.DoNotCollectText;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumRefTextCollectingVisitor.class */
public class EnumRefTextCollectingVisitor {
    private SequenceBuilder out;
    private final NodeVisitor visitor;
    private Runnable ordinalRunnable;

    public EnumRefTextCollectingVisitor() {
        this(-1);
    }

    public EnumRefTextCollectingVisitor(int i) {
        this.ordinalRunnable = i < 0 ? null : () -> {
            this.out.add(String.valueOf(i));
        };
        this.visitor = new NodeVisitor(new VisitHandler(Text.class, this::visit), new VisitHandler(TextBase.class, this::visit), new VisitHandler(HtmlEntity.class, this::visit), new VisitHandler(SoftLineBreak.class, this::visit), new VisitHandler(HardLineBreak.class, this::visit), new VisitHandler(EnumeratedReferenceText.class, this::visit), new VisitHandler(EnumeratedReferenceLink.class, this::visit));
    }

    public String getText() {
        return this.out.toString();
    }

    public void collect(BasedSequence basedSequence, EnumeratedReferenceRendering[] enumeratedReferenceRenderingArr, String str) {
        this.out = SequenceBuilder.emptyBuilder(basedSequence);
        EnumeratedReferences.renderReferenceOrdinals(enumeratedReferenceRenderingArr, new OrdinalRenderer(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumRefTextCollectingVisitor$OrdinalRenderer.class */
    public static class OrdinalRenderer implements EnumeratedOrdinalRenderer {
        final EnumRefTextCollectingVisitor renderer;

        public OrdinalRenderer(EnumRefTextCollectingVisitor enumRefTextCollectingVisitor) {
            this.renderer = enumRefTextCollectingVisitor;
        }

        @Override // com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
        public void startRendering(EnumeratedReferenceRendering[] enumeratedReferenceRenderingArr) {
        }

        @Override // com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
        public void setEnumOrdinalRunnable(Runnable runnable) {
            this.renderer.ordinalRunnable = runnable;
        }

        @Override // com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
        public Runnable getEnumOrdinalRunnable() {
            return this.renderer.ordinalRunnable;
        }

        @Override // com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
        public void render(int i, EnumeratedReferenceBlock enumeratedReferenceBlock, String str, boolean z) {
            Runnable runnable = this.renderer.ordinalRunnable;
            if (enumeratedReferenceBlock == null) {
                this.renderer.out.add(str + SequenceUtils.SPACE);
                if (runnable != null) {
                    runnable.run();
                }
                this.renderer.out.add(String.valueOf(i));
                if (z) {
                    this.renderer.out.add(".");
                    return;
                }
                return;
            }
            this.renderer.ordinalRunnable = () -> {
                if (runnable != null) {
                    runnable.run();
                }
                this.renderer.out.add(String.valueOf(i));
                if (z) {
                    this.renderer.out.add(".");
                }
            };
            this.renderer.visitor.visitChildren(enumeratedReferenceBlock);
        }

        @Override // com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
        public void endRendering() {
        }
    }

    public String collectAndGetText(BasedSequence basedSequence, EnumeratedReferenceRendering[] enumeratedReferenceRenderingArr, String str) {
        collect(basedSequence, enumeratedReferenceRenderingArr, str);
        return this.out.toString();
    }

    private void visit(EnumeratedReferenceText enumeratedReferenceText) {
        if (!enumeratedReferenceText.getText().toString().isEmpty() || this.ordinalRunnable == null) {
            return;
        }
        this.ordinalRunnable.run();
    }

    private void visit(EnumeratedReferenceLink enumeratedReferenceLink) {
        if (!enumeratedReferenceLink.getText().toString().isEmpty() || this.ordinalRunnable == null) {
            return;
        }
        this.ordinalRunnable.run();
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
