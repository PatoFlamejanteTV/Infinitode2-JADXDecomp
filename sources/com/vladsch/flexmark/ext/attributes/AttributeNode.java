package com.vladsch.flexmark.ext.attributes;

import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/AttributeNode.class */
public class AttributeNode extends Node implements DoNotDecorate {
    protected BasedSequence name;
    protected BasedSequence attributeSeparator;
    protected BasedSequence openingMarker;
    protected BasedSequence value;
    protected BasedSequence closingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.name, this.attributeSeparator, this.openingMarker, this.value, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpanChars(sb, this.name, Attribute.NAME_ATTR);
        segmentSpanChars(sb, this.attributeSeparator, "sep");
        delimitedSegmentSpanChars(sb, this.openingMarker, this.value, this.closingMarker, "value");
        if (isImplicitName()) {
            sb.append(" isImplicit");
        }
        if (isClass()) {
            sb.append(" isClass");
        }
        if (isId()) {
            sb.append(" isId");
        }
    }

    public AttributeNode() {
        this.name = BasedSequence.NULL;
        this.attributeSeparator = BasedSequence.NULL;
        this.openingMarker = BasedSequence.NULL;
        this.value = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public AttributeNode(BasedSequence basedSequence) {
        super(basedSequence);
        this.name = BasedSequence.NULL;
        this.attributeSeparator = BasedSequence.NULL;
        this.openingMarker = BasedSequence.NULL;
        this.value = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public AttributeNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5) {
        super(spanningChars(basedSequence, basedSequence2, basedSequence3, basedSequence4, basedSequence5));
        this.name = BasedSequence.NULL;
        this.attributeSeparator = BasedSequence.NULL;
        this.openingMarker = BasedSequence.NULL;
        this.value = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.name = basedSequence != null ? basedSequence : BasedSequence.NULL;
        this.attributeSeparator = basedSequence2 != null ? basedSequence2 : BasedSequence.NULL;
        this.openingMarker = basedSequence3 != null ? basedSequence3 : BasedSequence.NULL;
        this.value = basedSequence4 != null ? basedSequence4 : BasedSequence.NULL;
        this.closingMarker = basedSequence5 != null ? basedSequence5 : BasedSequence.NULL;
    }

    public static boolean isImplicitName(CharSequence charSequence) {
        if (charSequence.length() > 0) {
            return charSequence.charAt(0) == '.' || charSequence.charAt(0) == '#';
        }
        return false;
    }

    public boolean isImplicitName() {
        return this.value.isNotNull() && this.attributeSeparator.isNull() && this.name.isNotNull();
    }

    public boolean isClass() {
        if (isImplicitName() && this.name.equals(".")) {
            return true;
        }
        return !isImplicitName() && this.name.equals(Attribute.CLASS_ATTR);
    }

    public boolean isId() {
        if (isImplicitName() && this.name.equals("#")) {
            return true;
        }
        return !isImplicitName() && this.name.equals(Attribute.ID_ATTR);
    }

    public BasedSequence getName() {
        return this.name;
    }

    public void setName(BasedSequence basedSequence) {
        this.name = basedSequence;
    }

    public BasedSequence getAttributeSeparator() {
        return this.attributeSeparator;
    }

    public void setAttributeSeparator(BasedSequence basedSequence) {
        this.attributeSeparator = basedSequence;
    }

    public BasedSequence getValue() {
        return this.value;
    }

    public void setValue(BasedSequence basedSequence) {
        this.value = basedSequence;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }
}
