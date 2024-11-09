package com.vladsch.flexmark.ext.xwiki.macros;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/MacroBlock.class */
public class MacroBlock extends Block {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !MacroBlock.class.desiredAssertionStatus();
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        if (isClosedTag()) {
            sb.append(" isClosed");
        }
        segmentSpanChars(sb, getMacroContentChars(), "macroContent");
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return Node.EMPTY_SEGMENTS;
    }

    public MacroBlock() {
    }

    public MacroBlock(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public MacroBlock(Node node) {
        appendChild(node);
        setCharsFromContent();
    }

    public Map<String, String> getAttributes() {
        return getMacroNode().getAttributes();
    }

    public Macro getMacroNode() {
        Node firstChild = getFirstChild();
        if ($assertionsDisabled || (firstChild instanceof Macro)) {
            return (Macro) firstChild;
        }
        throw new AssertionError();
    }

    public boolean isClosedTag() {
        return getMacroNode().isClosedTag();
    }

    public BasedSequence getMacroContentChars() {
        Node firstChild = getFirstChild();
        Node lastChild = getLastChild();
        Node next = firstChild.getNext();
        Node previous = lastChild instanceof MacroClose ? lastChild.getPrevious() : lastChild;
        BasedSequence[] basedSequenceArr = new BasedSequence[2];
        basedSequenceArr[0] = (next == null || (next instanceof MacroClose)) ? BasedSequence.NULL : next.getChars();
        basedSequenceArr[1] = (previous == null || previous == firstChild) ? BasedSequence.NULL : previous.getChars();
        return Node.spanningChars(basedSequenceArr);
    }
}
