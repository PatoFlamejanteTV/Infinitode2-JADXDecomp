package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.DoNotLinkDecorate;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.ReplacedTextMapper;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/LinkNode.class */
public abstract class LinkNode extends LinkNodeBase implements DoNotLinkDecorate, TextContainer {
    public LinkNode() {
    }

    public LinkNode(BasedSequence basedSequence) {
        super(basedSequence);
    }

    @Override // com.vladsch.flexmark.util.ast.TextContainer
    public boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> iSequenceBuilder, int i, NodeVisitor nodeVisitor) {
        BasedSequence basedSequence;
        int i2 = i & F_LINK_TEXT_TYPE;
        switch (i2) {
            case 1:
                basedSequence = getPageRef();
                break;
            case 2:
                basedSequence = getAnchorRef();
                break;
            case 3:
                basedSequence = getUrl();
                break;
            case 4:
                basedSequence = BasedSequence.NULL;
                break;
            default:
                return true;
        }
        if (i2 == 4) {
            iSequenceBuilder.append((CharSequence) getChars());
            return false;
        }
        ReplacedTextMapper replacedTextMapper = new ReplacedTextMapper(basedSequence);
        iSequenceBuilder.append((CharSequence) Escaping.percentDecodeUrl(Escaping.unescape(basedSequence, replacedTextMapper), replacedTextMapper));
        return false;
    }
}
