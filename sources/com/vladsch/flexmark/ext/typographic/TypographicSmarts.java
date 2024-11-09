package com.vladsch.flexmark.ext.typographic;

import com.vladsch.flexmark.util.ast.DoNotAttributeDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.TypographicText;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.ReplacedTextMapper;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/TypographicSmarts.class */
public class TypographicSmarts extends Node implements DoNotAttributeDecorate, TypographicText {
    private String typographicText;

    public TypographicSmarts() {
    }

    public TypographicSmarts(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public TypographicSmarts(String str) {
        this.typographicText = str;
    }

    public TypographicSmarts(BasedSequence basedSequence, String str) {
        super(basedSequence);
        this.typographicText = str;
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
    public void getAstExtra(StringBuilder sb) {
        sb.append(" typographic: ").append(this.typographicText).append(SequenceUtils.SPACE);
    }

    public String getTypographicText() {
        return this.typographicText;
    }

    public void setTypographicText(String str) {
        this.typographicText = str;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    protected String toStringAttributes() {
        return "text=" + ((Object) getChars());
    }
}
