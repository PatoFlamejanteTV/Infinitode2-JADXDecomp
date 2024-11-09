package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.PrefixedSubSequence;
import com.vladsch.flexmark.util.sequence.ReplacedTextMapper;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/Text.class */
public final class Text extends Node implements TextContainer {
    public Text() {
    }

    public Text(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public Text(String str) {
        super(BasedSequence.of(str));
    }

    public Text(String str, BasedSequence basedSequence) {
        super(PrefixedSubSequence.prefixOf(str, basedSequence));
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public final BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    @Override // com.vladsch.flexmark.util.ast.TextContainer
    public final boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> iSequenceBuilder, int i, NodeVisitor nodeVisitor) {
        if (BitFieldSet.any(i, F_NODE_TEXT)) {
            iSequenceBuilder.append((CharSequence) getChars());
            return false;
        }
        BasedSequence unescape = Escaping.unescape(getChars(), new ReplacedTextMapper(getChars()));
        if (!unescape.isEmpty()) {
            iSequenceBuilder.append((CharSequence) unescape);
            return false;
        }
        return false;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public final void getAstExtra(StringBuilder sb) {
        astExtraChars(sb);
        if (getChars() instanceof PrefixedSubSequence) {
            astChars(sb, getChars(), "text");
        }
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    protected final String toStringAttributes() {
        return "text=" + ((Object) getChars());
    }
}
