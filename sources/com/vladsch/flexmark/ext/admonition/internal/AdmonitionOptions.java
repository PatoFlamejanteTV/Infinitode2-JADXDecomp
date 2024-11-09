package com.vladsch.flexmark.ext.admonition.internal;

import com.vladsch.flexmark.ext.admonition.AdmonitionExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/internal/AdmonitionOptions.class */
public class AdmonitionOptions {
    public final int contentIndent;
    public final boolean allowLeadingSpace;
    public final boolean interruptsParagraph;
    public final boolean interruptsItemParagraph;
    public final boolean withSpacesInterruptsItemParagraph;
    public final boolean allowLazyContinuation;
    public final String unresolvedQualifier;
    public final Map<String, String> qualifierTypeMap;
    public final Map<String, String> qualifierTitleMap;
    public final Map<String, String> typeSvgMap;

    public AdmonitionOptions(DataHolder dataHolder) {
        this.contentIndent = AdmonitionExtension.CONTENT_INDENT.get(dataHolder).intValue();
        this.allowLeadingSpace = AdmonitionExtension.ALLOW_LEADING_SPACE.get(dataHolder).booleanValue();
        this.interruptsParagraph = AdmonitionExtension.INTERRUPTS_PARAGRAPH.get(dataHolder).booleanValue();
        this.interruptsItemParagraph = AdmonitionExtension.INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
        this.withSpacesInterruptsItemParagraph = AdmonitionExtension.WITH_SPACES_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
        this.allowLazyContinuation = AdmonitionExtension.ALLOW_LAZY_CONTINUATION.get(dataHolder).booleanValue();
        this.unresolvedQualifier = AdmonitionExtension.UNRESOLVED_QUALIFIER.get(dataHolder);
        this.qualifierTypeMap = AdmonitionExtension.QUALIFIER_TYPE_MAP.get(dataHolder);
        this.qualifierTitleMap = AdmonitionExtension.QUALIFIER_TITLE_MAP.get(dataHolder);
        this.typeSvgMap = AdmonitionExtension.TYPE_SVG_MAP.get(dataHolder);
    }
}
