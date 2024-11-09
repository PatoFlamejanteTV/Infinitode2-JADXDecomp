package com.vladsch.flexmark.html;

import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.LinkStatus;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.html.HtmlAppendableBase;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.TagRange;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/HtmlWriter.class */
public class HtmlWriter extends HtmlAppendableBase<HtmlWriter> {
    private NodeRendererContext context;
    private AttributablePart useAttributes;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !HtmlWriter.class.desiredAssertionStatus();
    }

    public HtmlWriter(int i, int i2) {
        super(i, i2);
    }

    public HtmlWriter(HtmlWriter htmlWriter, boolean z) {
        super(htmlWriter, z);
        this.context = htmlWriter.context;
    }

    public HtmlWriter(int i, int i2, boolean z, boolean z2) {
        this(null, i, i2, z, z2);
    }

    public HtmlWriter(Appendable appendable, int i, int i2, boolean z, boolean z2) {
        super(appendable, i, i2);
        setSuppressOpenTagLine(z);
        setSuppressCloseTagLine(z2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContext(NodeRendererContext nodeRendererContext) {
        this.context = nodeRendererContext;
    }

    public NodeRendererContext getContext() {
        if ($assertionsDisabled || this.context != null) {
            return this.context;
        }
        throw new AssertionError();
    }

    public HtmlWriter srcPos() {
        return this.context == null ? this : srcPos(this.context.getCurrentNode().getChars());
    }

    public HtmlWriter srcPosWithEOL() {
        return this.context == null ? this : srcPosWithEOL(this.context.getCurrentNode().getChars());
    }

    public HtmlWriter srcPosWithTrailingEOL() {
        return this.context == null ? this : srcPosWithTrailingEOL(this.context.getCurrentNode().getChars());
    }

    public HtmlWriter srcPos(BasedSequence basedSequence) {
        if (basedSequence.isNotNull()) {
            BasedSequence trimEOL = basedSequence.trimEOL();
            return srcPos(trimEOL.getStartOffset(), trimEOL.getEndOffset());
        }
        return this;
    }

    public HtmlWriter srcPosWithEOL(BasedSequence basedSequence) {
        if (basedSequence.isNotNull()) {
            return srcPos(basedSequence.getStartOffset(), basedSequence.getEndOffset());
        }
        return this;
    }

    public HtmlWriter srcPosWithTrailingEOL(BasedSequence basedSequence) {
        char charAt;
        if (basedSequence.isNotNull()) {
            int endOffset = basedSequence.getEndOffset();
            BasedSequence baseSequence = basedSequence.getBaseSequence();
            while (endOffset < baseSequence.length() && ((charAt = baseSequence.charAt(endOffset)) == ' ' || charAt == '\t')) {
                endOffset++;
            }
            if (endOffset < baseSequence.length() && baseSequence.charAt(endOffset) == '\r') {
                endOffset++;
            }
            if (endOffset < baseSequence.length() && baseSequence.charAt(endOffset) == '\n') {
                endOffset++;
            }
            return srcPos(basedSequence.getStartOffset(), endOffset);
        }
        return this;
    }

    public HtmlWriter srcPos(int i, int i2) {
        if (i <= i2 && this.context != null && !this.context.getHtmlOptions().sourcePositionAttribute.isEmpty()) {
            super.attr((CharSequence) this.context.getHtmlOptions().sourcePositionAttribute, (CharSequence) (i + "-" + i2));
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendableBase, com.vladsch.flexmark.util.html.HtmlAppendable
    public HtmlWriter withAttr() {
        return withAttr(AttributablePart.NODE);
    }

    public HtmlWriter withAttr(AttributablePart attributablePart) {
        super.withAttr();
        this.useAttributes = attributablePart;
        return this;
    }

    public HtmlWriter withAttr(LinkStatus linkStatus) {
        attr(Attribute.LINK_STATUS_ATTR, (CharSequence) linkStatus.getName());
        return withAttr(AttributablePart.LINK);
    }

    public HtmlWriter withAttr(ResolvedLink resolvedLink) {
        return withAttr(resolvedLink.getStatus());
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendableBase, com.vladsch.flexmark.util.html.HtmlAppendable
    public HtmlWriter tag(CharSequence charSequence, boolean z) {
        String str;
        Attributes attributes;
        if (this.useAttributes != null) {
            if (this.context != null) {
                attributes = this.context.extendRenderingNodeAttributes(this.useAttributes, getAttributes());
                str = attributes.getValue(this.context.getHtmlOptions().sourcePositionAttribute);
            } else {
                str = "";
                attributes = new Attributes();
            }
            if (!str.isEmpty()) {
                int indexOf = str.indexOf(45);
                int i = -1;
                int i2 = -1;
                if (indexOf != -1) {
                    try {
                        i = Integer.parseInt(str.substring(0, indexOf));
                    } catch (Throwable unused) {
                    }
                    try {
                        i2 = Integer.parseInt(str.substring(indexOf + 1));
                    } catch (Throwable unused2) {
                    }
                }
                if (i >= 0 && i < i2) {
                    HtmlRenderer.TAG_RANGES.get(this.context.getDocument()).add(new TagRange(charSequence, i, i2));
                }
            }
            setAttributes(attributes);
            this.useAttributes = null;
        }
        super.tag(charSequence, z);
        return this;
    }
}
