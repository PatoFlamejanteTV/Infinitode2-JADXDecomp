package com.vladsch.flexmark.ext.footnotes.internal;

import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteOptions.class */
public class FootnoteOptions {
    final String footnoteRefPrefix;
    final String footnoteRefSuffix;
    final String footnoteBackRefString;
    final String footnoteLinkRefClass;
    final String footnoteBackLinkRefClass;
    final int contentIndent;

    public FootnoteOptions(DataHolder dataHolder) {
        this.footnoteRefPrefix = FootnoteExtension.FOOTNOTE_REF_PREFIX.get(dataHolder);
        this.footnoteRefSuffix = FootnoteExtension.FOOTNOTE_REF_SUFFIX.get(dataHolder);
        this.footnoteBackRefString = FootnoteExtension.FOOTNOTE_BACK_REF_STRING.get(dataHolder);
        this.footnoteLinkRefClass = FootnoteExtension.FOOTNOTE_LINK_REF_CLASS.get(dataHolder);
        this.footnoteBackLinkRefClass = FootnoteExtension.FOOTNOTE_BACK_LINK_REF_CLASS.get(dataHolder);
        this.contentIndent = Parser.LISTS_ITEM_INDENT.get(dataHolder).intValue();
    }
}
