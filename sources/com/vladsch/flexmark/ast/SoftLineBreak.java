package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.DoNotAttributeDecorate;
import com.vladsch.flexmark.util.ast.DoNotTrim;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/SoftLineBreak.class */
public class SoftLineBreak extends Node implements DoNotAttributeDecorate, DoNotTrim, TextContainer {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SoftLineBreak.class.desiredAssertionStatus();
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public SoftLineBreak() {
    }

    public SoftLineBreak(BasedSequence basedSequence) {
        super(basedSequence);
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void setChars(BasedSequence basedSequence) {
        super.setChars(basedSequence);
        if (!$assertionsDisabled && !getChars().isNotEmpty()) {
            throw new AssertionError();
        }
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void setCharsFromContentOnly() {
        super.setCharsFromContentOnly();
        if (!$assertionsDisabled && !getChars().isNotEmpty()) {
            throw new AssertionError();
        }
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void setCharsFromContent() {
        super.setCharsFromContent();
        if (!$assertionsDisabled && !getChars().isNotEmpty()) {
            throw new AssertionError();
        }
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void setCharsFromSegments() {
        super.setCharsFromSegments();
        if (!$assertionsDisabled && !getChars().isNotEmpty()) {
            throw new AssertionError();
        }
    }

    @Override // com.vladsch.flexmark.util.ast.TextContainer
    public boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> iSequenceBuilder, int i, NodeVisitor nodeVisitor) {
        iSequenceBuilder.add(getChars());
        return false;
    }
}
