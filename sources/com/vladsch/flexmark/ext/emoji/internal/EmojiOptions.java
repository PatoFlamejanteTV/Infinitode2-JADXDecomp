package com.vladsch.flexmark.ext.emoji.internal;

import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.emoji.EmojiImageType;
import com.vladsch.flexmark.ext.emoji.EmojiShortcutType;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/internal/EmojiOptions.class */
public class EmojiOptions {
    public final String rootImagePath;
    public final EmojiShortcutType useShortcutType;
    public final EmojiImageType useImageType;
    public final String attrImageSize;
    public final String attrAlign;
    public final String attrImageClass;

    public EmojiOptions(DataHolder dataHolder) {
        this.useShortcutType = EmojiExtension.USE_SHORTCUT_TYPE.get(dataHolder);
        this.attrAlign = EmojiExtension.ATTR_ALIGN.get(dataHolder);
        this.attrImageSize = EmojiExtension.ATTR_IMAGE_SIZE.get(dataHolder);
        this.rootImagePath = EmojiExtension.ROOT_IMAGE_PATH.get(dataHolder);
        this.useImageType = EmojiExtension.USE_IMAGE_TYPE.get(dataHolder);
        this.attrImageClass = EmojiExtension.ATTR_IMAGE_CLASS.get(dataHolder);
    }
}
