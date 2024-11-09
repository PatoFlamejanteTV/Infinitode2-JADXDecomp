package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/DelimitedNode.class */
public interface DelimitedNode extends TextContainer {
    BasedSequence getOpeningMarker();

    BasedSequence getChars();

    void setOpeningMarker(BasedSequence basedSequence);

    BasedSequence getText();

    void setText(BasedSequence basedSequence);

    BasedSequence getClosingMarker();

    void setClosingMarker(BasedSequence basedSequence);

    @Override // com.vladsch.flexmark.util.ast.TextContainer
    default boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> iSequenceBuilder, int i, NodeVisitor nodeVisitor) {
        if (BitFieldSet.any(i, F_NODE_TEXT)) {
            iSequenceBuilder.append((CharSequence) getText());
            return false;
        }
        return true;
    }
}
