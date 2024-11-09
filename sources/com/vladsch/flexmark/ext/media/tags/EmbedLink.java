package com.vladsch.flexmark.ext.media.tags;

import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ext.media.tags.internal.AbstractMediaLink;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/EmbedLink.class */
public class EmbedLink extends AbstractMediaLink {
    public static final String PREFIX = "!E";
    private static final String TYPE = "Embed";

    public EmbedLink() {
        super(PREFIX, TYPE);
    }

    public EmbedLink(Link link) {
        super(PREFIX, TYPE, link);
    }
}
