package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.html.MutableAttributes;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/ResolvedLink.class */
public class ResolvedLink {
    private final LinkType myLinkType;
    private final String myUrl;
    private final LinkStatus myStatus;
    private MutableAttributes myAttributes;

    public ResolvedLink(LinkType linkType, CharSequence charSequence) {
        this(linkType, charSequence, null, LinkStatus.UNKNOWN);
    }

    public ResolvedLink(LinkType linkType, CharSequence charSequence, Attributes attributes) {
        this(linkType, charSequence, attributes, LinkStatus.UNKNOWN);
    }

    public Attributes getAttributes() {
        if (this.myAttributes == null) {
            return null;
        }
        return this.myAttributes.toImmutable();
    }

    public Attributes getNonNullAttributes() {
        if (this.myAttributes == null) {
            this.myAttributes = new MutableAttributes();
        }
        return this.myAttributes.toImmutable();
    }

    public MutableAttributes getMutableAttributes() {
        if (this.myAttributes == null) {
            this.myAttributes = new MutableAttributes();
        }
        return this.myAttributes;
    }

    public ResolvedLink(LinkType linkType, CharSequence charSequence, Attributes attributes, LinkStatus linkStatus) {
        this.myLinkType = linkType;
        this.myUrl = String.valueOf(charSequence);
        this.myStatus = linkStatus;
        if (attributes != null) {
            getMutableAttributes().addValues(attributes);
        }
    }

    public ResolvedLink withLinkType(LinkType linkType) {
        return linkType == this.myLinkType ? this : new ResolvedLink(linkType, this.myUrl, this.myAttributes, this.myStatus);
    }

    public ResolvedLink withStatus(LinkStatus linkStatus) {
        return linkStatus == this.myStatus ? this : new ResolvedLink(this.myLinkType, this.myUrl, this.myAttributes, linkStatus);
    }

    public LinkType getLinkType() {
        return this.myLinkType;
    }

    public LinkStatus getStatus() {
        return this.myStatus;
    }

    public ResolvedLink withUrl(CharSequence charSequence) {
        String valueOf = String.valueOf(charSequence);
        return this.myUrl.equals(valueOf) ? this : new ResolvedLink(this.myLinkType, valueOf, this.myAttributes, this.myStatus);
    }

    public String getUrl() {
        return this.myUrl;
    }

    public String getPageRef() {
        int indexOf = this.myUrl.indexOf(35);
        if (indexOf >= 0) {
            return this.myUrl.substring(0, indexOf);
        }
        return this.myUrl;
    }

    public String getAnchorRef() {
        int indexOf = this.myUrl.indexOf(35);
        if (indexOf >= 0) {
            return this.myUrl.substring(indexOf + 1);
        }
        return null;
    }

    public ResolvedLink withTitle(CharSequence charSequence) {
        String value = this.myAttributes == null ? null : this.myAttributes.getValue(Attribute.TITLE_ATTR);
        if (charSequence == value || !(value == null || charSequence == null || !value.contentEquals(charSequence))) {
            return this;
        }
        MutableAttributes mutableAttributes = new MutableAttributes(this.myAttributes);
        if (charSequence == null) {
            mutableAttributes.remove(Attribute.TITLE_ATTR);
            if (mutableAttributes.isEmpty()) {
                mutableAttributes = null;
            }
        } else {
            mutableAttributes.replaceValue(Attribute.TITLE_ATTR, charSequence);
        }
        return new ResolvedLink(this.myLinkType, this.myUrl, mutableAttributes, this.myStatus);
    }

    public String getTitle() {
        if (this.myAttributes == null) {
            return null;
        }
        return this.myAttributes.getValue(Attribute.TITLE_ATTR);
    }

    public ResolvedLink withTarget(CharSequence charSequence) {
        String value = this.myAttributes == null ? null : this.myAttributes.getValue("target");
        if (charSequence == value || !(value == null || charSequence == null || !value.contentEquals(charSequence))) {
            return this;
        }
        MutableAttributes mutableAttributes = new MutableAttributes(this.myAttributes);
        if (charSequence == null) {
            mutableAttributes.remove("target");
            if (mutableAttributes.isEmpty()) {
                mutableAttributes = null;
            }
        } else {
            mutableAttributes.replaceValue("target", charSequence);
        }
        return new ResolvedLink(this.myLinkType, this.myUrl, mutableAttributes, this.myStatus);
    }

    public String getTarget() {
        if (this.myAttributes == null) {
            return null;
        }
        return this.myAttributes.getValue("target");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolvedLink)) {
            return false;
        }
        ResolvedLink resolvedLink = (ResolvedLink) obj;
        if (this.myLinkType.equals(resolvedLink.myLinkType) && this.myUrl.equals(resolvedLink.myUrl)) {
            return this.myStatus.equals(resolvedLink.myStatus);
        }
        return false;
    }

    public int hashCode() {
        return (((this.myLinkType.hashCode() * 31) + this.myUrl.hashCode()) * 31) + this.myStatus.hashCode();
    }
}
