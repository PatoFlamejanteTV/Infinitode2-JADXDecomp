package com.vladsch.flexmark.html.renderer;

import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/ResolvedContent.class */
public class ResolvedContent {
    private final ResolvedLink resolvedLink;
    private final LinkStatus status;
    private final byte[] content;

    public ResolvedContent(ResolvedLink resolvedLink, LinkStatus linkStatus, byte[] bArr) {
        this.resolvedLink = resolvedLink;
        this.status = linkStatus;
        this.content = bArr;
    }

    public ResolvedContent withStatus(LinkStatus linkStatus) {
        return linkStatus == this.status ? this : new ResolvedContent(this.resolvedLink, linkStatus, this.content);
    }

    public ResolvedContent withContent(byte[] bArr) {
        return Arrays.equals(this.content, bArr) ? this : new ResolvedContent(this.resolvedLink, this.status, bArr);
    }

    public ResolvedLink getResolvedLink() {
        return this.resolvedLink;
    }

    public LinkStatus getStatus() {
        return this.status;
    }

    public byte[] getContent() {
        return this.content;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ResolvedContent resolvedContent = (ResolvedContent) obj;
        if (this.resolvedLink.equals(resolvedContent.resolvedLink) && this.status.equals(resolvedContent.status)) {
            return Arrays.equals(this.content, resolvedContent.content);
        }
        return false;
    }

    public int hashCode() {
        return (((this.resolvedLink.hashCode() * 31) + this.status.hashCode()) * 31) + Arrays.hashCode(this.content);
    }
}
