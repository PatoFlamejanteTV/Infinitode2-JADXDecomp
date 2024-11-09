package com.vladsch.flexmark.ext.aside.internal;

import com.vladsch.flexmark.ext.aside.AsideExtension;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/aside/internal/AsideOptions.class */
class AsideOptions {
    public final boolean extendToBlankLine;
    public final boolean ignoreBlankLine;
    public final boolean allowLeadingSpace;
    public final boolean interruptsParagraph;
    public final boolean interruptsItemParagraph;
    public final boolean withLeadSpacesInterruptsItemParagraph;

    public AsideOptions(DataHolder dataHolder) {
        this.extendToBlankLine = AsideExtension.EXTEND_TO_BLANK_LINE.get(dataHolder).booleanValue();
        this.ignoreBlankLine = AsideExtension.IGNORE_BLANK_LINE.get(dataHolder).booleanValue();
        this.allowLeadingSpace = AsideExtension.ALLOW_LEADING_SPACE.get(dataHolder).booleanValue();
        this.interruptsParagraph = AsideExtension.INTERRUPTS_PARAGRAPH.get(dataHolder).booleanValue();
        this.interruptsItemParagraph = AsideExtension.INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
        this.withLeadSpacesInterruptsItemParagraph = AsideExtension.WITH_LEAD_SPACES_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
    }
}
