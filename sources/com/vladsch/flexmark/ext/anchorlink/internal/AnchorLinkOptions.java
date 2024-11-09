package com.vladsch.flexmark.ext.anchorlink.internal;

import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.util.data.DataHolder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/anchorlink/internal/AnchorLinkOptions.class */
public class AnchorLinkOptions {
    public final boolean wrapText;
    public final String textPrefix;
    public final String textSuffix;
    public final String anchorClass;
    public final boolean setName;
    public final boolean setId;
    public final boolean noBlockQuotes;

    public AnchorLinkOptions(DataHolder dataHolder) {
        this.wrapText = AnchorLinkExtension.ANCHORLINKS_WRAP_TEXT.get(dataHolder).booleanValue();
        this.textPrefix = AnchorLinkExtension.ANCHORLINKS_TEXT_PREFIX.get(dataHolder);
        this.textSuffix = AnchorLinkExtension.ANCHORLINKS_TEXT_SUFFIX.get(dataHolder);
        this.anchorClass = AnchorLinkExtension.ANCHORLINKS_ANCHOR_CLASS.get(dataHolder);
        this.setName = AnchorLinkExtension.ANCHORLINKS_SET_NAME.get(dataHolder).booleanValue();
        this.setId = AnchorLinkExtension.ANCHORLINKS_SET_ID.get(dataHolder).booleanValue();
        this.noBlockQuotes = AnchorLinkExtension.ANCHORLINKS_NO_BLOCK_QUOTE.get(dataHolder).booleanValue();
    }
}
