package com.vladsch.flexmark.ext.media.tags;

import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ext.media.tags.internal.AbstractMediaLink;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/AudioLink.class */
public class AudioLink extends AbstractMediaLink {
    public static final String PREFIX = "!A";
    private static final String TYPE = "Audio";

    public AudioLink() {
        super(PREFIX, TYPE);
    }

    public AudioLink(Link link) {
        super(PREFIX, TYPE, link);
    }
}
