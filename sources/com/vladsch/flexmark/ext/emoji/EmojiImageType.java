package com.vladsch.flexmark.ext.emoji;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/EmojiImageType.class */
public enum EmojiImageType {
    IMAGE_ONLY(false, true),
    UNICODE_FALLBACK_TO_IMAGE(true, true),
    UNICODE_ONLY(true, false);

    public final boolean isUnicode;
    public final boolean isImage;

    EmojiImageType(boolean z, boolean z2) {
        this.isUnicode = z;
        this.isImage = z2;
    }
}
