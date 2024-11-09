package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.ReplacedTextMapper;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/TextBase.class */
public class TextBase extends Node implements TextContainer {
    public TextBase() {
    }

    public TextBase(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public TextBase(String str) {
        super(BasedSequence.of(str));
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        astExtraChars(sb);
    }

    @Override // com.vladsch.flexmark.util.ast.TextContainer
    public boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> iSequenceBuilder, int i, NodeVisitor nodeVisitor) {
        if (BitFieldSet.any(i, F_NODE_TEXT)) {
            iSequenceBuilder.append((CharSequence) getChars());
            return false;
        }
        iSequenceBuilder.append((CharSequence) Escaping.unescape(getChars(), new ReplacedTextMapper(getChars())));
        return false;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    protected String toStringAttributes() {
        return "text=" + ((Object) getChars());
    }
}
