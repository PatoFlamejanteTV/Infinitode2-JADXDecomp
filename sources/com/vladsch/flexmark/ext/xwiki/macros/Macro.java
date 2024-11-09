package com.vladsch.flexmark.ext.xwiki.macros;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/Macro.class */
public class Macro extends Node {
    protected BasedSequence openingMarker;
    protected BasedSequence name;
    protected BasedSequence attributeText;
    protected BasedSequence closingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.name, this.attributeText, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpanChars(sb, this.openingMarker, "open");
        segmentSpanChars(sb, this.name, Attribute.NAME_ATTR);
        segmentSpanChars(sb, this.attributeText, "attributes");
        segmentSpanChars(sb, this.closingMarker, "close");
        if (isClosedTag()) {
            sb.append(" isClosed");
        }
        if (isBlockMacro()) {
            sb.append(" isBlockMacro");
        }
        segmentSpanChars(sb, getMacroContentChars(), "macroContent");
    }

    public Macro() {
        this.openingMarker = BasedSequence.NULL;
        this.name = BasedSequence.NULL;
        this.attributeText = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public Macro(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.name = BasedSequence.NULL;
        this.attributeText = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public Macro(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        super(basedSequence.baseSubSequence(basedSequence.getStartOffset(), basedSequence3.getEndOffset()));
        this.openingMarker = BasedSequence.NULL;
        this.name = BasedSequence.NULL;
        this.attributeText = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.openingMarker = basedSequence;
        this.name = basedSequence2;
        this.closingMarker = basedSequence3;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getName() {
        return this.name;
    }

    public void setName(BasedSequence basedSequence) {
        this.name = basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }

    public BasedSequence getAttributeText() {
        return this.attributeText;
    }

    public void setAttributeText(BasedSequence basedSequence) {
        this.attributeText = basedSequence;
    }

    public boolean isBlockMacro() {
        Node parent = getParent();
        return (parent instanceof MacroBlock) && parent.getFirstChild() == this;
    }

    public Map<String, String> getAttributes() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Node firstChild = getFirstChild();
        while (true) {
            Node node = firstChild;
            if (node != null) {
                if (node instanceof MacroAttribute) {
                    MacroAttribute macroAttribute = (MacroAttribute) node;
                    linkedHashMap.put(macroAttribute.getAttribute().toString(), macroAttribute.getValue().toString());
                }
                firstChild = node.getNext();
            } else {
                return linkedHashMap;
            }
        }
    }

    public BasedSequence getMacroContentChars() {
        Node lastChild = getLastChild();
        return isClosedTag() ? BasedSequence.NULL : getChars().baseSubSequence(getClosingMarker().getEndOffset(), (lastChild == null || (lastChild instanceof MacroAttribute)) ? getEndOffset() : lastChild.getStartOffset());
    }

    public boolean isClosedTag() {
        return getClosingMarker().length() > 2;
    }
}
