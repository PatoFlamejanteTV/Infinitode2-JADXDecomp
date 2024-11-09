package com.vladsch.flexmark.ext.media.tags;

import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ext.media.tags.internal.AbstractMediaLink;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/PictureLink.class */
public class PictureLink extends AbstractMediaLink {
    public static final String PREFIX = "!P";
    private static final String TYPE = "Picture";

    public PictureLink() {
        super(PREFIX, TYPE);
    }

    public PictureLink(Link link) {
        super(PREFIX, TYPE, link);
    }
}
