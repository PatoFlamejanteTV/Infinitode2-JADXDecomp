package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/LinkNodeBase.class */
public abstract class LinkNodeBase extends Node {
    protected BasedSequence urlOpeningMarker;
    protected BasedSequence url;
    protected BasedSequence pageRef;
    protected BasedSequence anchorMarker;
    protected BasedSequence anchorRef;
    protected BasedSequence urlClosingMarker;
    protected BasedSequence titleOpeningMarker;
    protected BasedSequence title;
    protected BasedSequence titleClosingMarker;

    public LinkNodeBase() {
        this.urlOpeningMarker = BasedSequence.NULL;
        this.url = BasedSequence.NULL;
        this.pageRef = BasedSequence.NULL;
        this.anchorMarker = BasedSequence.NULL;
        this.anchorRef = BasedSequence.NULL;
        this.urlClosingMarker = BasedSequence.NULL;
        this.titleOpeningMarker = BasedSequence.NULL;
        this.title = BasedSequence.NULL;
        this.titleClosingMarker = BasedSequence.NULL;
    }

    public LinkNodeBase(BasedSequence basedSequence) {
        super(basedSequence);
        this.urlOpeningMarker = BasedSequence.NULL;
        this.url = BasedSequence.NULL;
        this.pageRef = BasedSequence.NULL;
        this.anchorMarker = BasedSequence.NULL;
        this.anchorRef = BasedSequence.NULL;
        this.urlClosingMarker = BasedSequence.NULL;
        this.titleOpeningMarker = BasedSequence.NULL;
        this.title = BasedSequence.NULL;
        this.titleClosingMarker = BasedSequence.NULL;
    }

    public void setTitleChars(BasedSequence basedSequence) {
        if (basedSequence != null && basedSequence != BasedSequence.NULL) {
            int length = basedSequence.length();
            this.titleOpeningMarker = basedSequence.subSequence(0, 1);
            this.title = basedSequence.subSequence(1, length - 1);
            this.titleClosingMarker = basedSequence.subSequence(length - 1, length);
            return;
        }
        this.titleOpeningMarker = BasedSequence.NULL;
        this.title = BasedSequence.NULL;
        this.titleClosingMarker = BasedSequence.NULL;
    }

    public void setUrlChars(BasedSequence basedSequence) {
        if (basedSequence != null && basedSequence != BasedSequence.NULL) {
            if (basedSequence.startsWith("<") && basedSequence.endsWith(">")) {
                this.urlOpeningMarker = basedSequence.subSequence(0, 1);
                this.url = basedSequence.subSequence(1, basedSequence.length() - 1);
                this.urlClosingMarker = basedSequence.subSequence(basedSequence.length() - 1);
            } else {
                this.url = basedSequence;
            }
            int indexOf = this.url.indexOf('#');
            if (indexOf < 0) {
                this.pageRef = this.url;
                return;
            }
            this.pageRef = this.url.subSequence(0, indexOf);
            this.anchorMarker = this.url.subSequence(indexOf, indexOf + 1);
            this.anchorRef = this.url.subSequence(indexOf + 1);
            return;
        }
        this.urlOpeningMarker = BasedSequence.NULL;
        this.url = BasedSequence.NULL;
        this.urlClosingMarker = BasedSequence.NULL;
    }

    public BasedSequence getPageRef() {
        return this.pageRef;
    }

    public void setPageRef(BasedSequence basedSequence) {
        this.pageRef = basedSequence;
    }

    public BasedSequence getAnchorMarker() {
        return this.anchorMarker;
    }

    public void setAnchorMarker(BasedSequence basedSequence) {
        this.anchorMarker = basedSequence;
    }

    public BasedSequence getAnchorRef() {
        return this.anchorRef;
    }

    public void setAnchorRef(BasedSequence basedSequence) {
        this.anchorRef = basedSequence;
    }

    public BasedSequence getUrl() {
        return this.url;
    }

    public BasedSequence getTitle() {
        return this.title;
    }

    public BasedSequence getUrlOpeningMarker() {
        return this.urlOpeningMarker;
    }

    public void setUrlOpeningMarker(BasedSequence basedSequence) {
        this.urlOpeningMarker = basedSequence;
    }

    public void setUrl(BasedSequence basedSequence) {
        this.url = basedSequence;
    }

    public BasedSequence getUrlClosingMarker() {
        return this.urlClosingMarker;
    }

    public void setUrlClosingMarker(BasedSequence basedSequence) {
        this.urlClosingMarker = basedSequence;
    }

    public BasedSequence getTitleOpeningMarker() {
        return this.titleOpeningMarker;
    }

    public void setTitleOpeningMarker(BasedSequence basedSequence) {
        this.titleOpeningMarker = basedSequence;
    }

    public void setTitle(BasedSequence basedSequence) {
        this.title = basedSequence;
    }

    public BasedSequence getTitleClosingMarker() {
        return this.titleClosingMarker;
    }

    public void setTitleClosingMarker(BasedSequence basedSequence) {
        this.titleClosingMarker = basedSequence;
    }
}
