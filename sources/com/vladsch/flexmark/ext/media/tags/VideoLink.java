package com.vladsch.flexmark.ext.media.tags;

import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ext.media.tags.internal.AbstractMediaLink;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/VideoLink.class */
public class VideoLink extends AbstractMediaLink {
    public static final String PREFIX = "!V";
    private static final String TYPE = "Video";

    public VideoLink() {
        super(PREFIX, TYPE);
    }

    public VideoLink(Link link) {
        super(PREFIX, TYPE, link);
    }
}
