package com.vladsch.flexmark.ext.typographic.internal;

import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/internal/TypographicOptions.class */
public class TypographicOptions {
    public final boolean typographicQuotes;
    public final boolean typographicSmarts;
    public final String ellipsis;
    public final String ellipsisSpaced;
    public final String enDash;
    public final String emDash;
    public final String singleQuoteOpen;
    public final String singleQuoteClose;
    public final String singleQuoteUnmatched;
    public final String doubleQuoteOpen;
    public final String doubleQuoteClose;
    public final String doubleQuoteUnmatched;
    public final String angleQuoteOpen;
    public final String angleQuoteClose;
    public final String angleQuoteUnmatched;

    public TypographicOptions(DataHolder dataHolder) {
        this.typographicQuotes = TypographicExtension.ENABLE_QUOTES.get(dataHolder).booleanValue();
        this.typographicSmarts = TypographicExtension.ENABLE_SMARTS.get(dataHolder).booleanValue();
        this.ellipsis = TypographicExtension.ELLIPSIS.get(dataHolder);
        this.ellipsisSpaced = TypographicExtension.ELLIPSIS_SPACED.get(dataHolder);
        this.enDash = TypographicExtension.EN_DASH.get(dataHolder);
        this.emDash = TypographicExtension.EM_DASH.get(dataHolder);
        this.singleQuoteOpen = TypographicExtension.SINGLE_QUOTE_OPEN.get(dataHolder);
        this.singleQuoteClose = TypographicExtension.SINGLE_QUOTE_CLOSE.get(dataHolder);
        this.singleQuoteUnmatched = TypographicExtension.SINGLE_QUOTE_UNMATCHED.get(dataHolder);
        this.doubleQuoteOpen = TypographicExtension.DOUBLE_QUOTE_OPEN.get(dataHolder);
        this.doubleQuoteClose = TypographicExtension.DOUBLE_QUOTE_CLOSE.get(dataHolder);
        this.doubleQuoteUnmatched = TypographicExtension.DOUBLE_QUOTE_UNMATCHED.get(dataHolder);
        this.angleQuoteOpen = TypographicExtension.ANGLE_QUOTE_OPEN.get(dataHolder);
        this.angleQuoteClose = TypographicExtension.ANGLE_QUOTE_CLOSE.get(dataHolder);
        this.angleQuoteUnmatched = TypographicExtension.ANGLE_QUOTE_UNMATCHED.get(dataHolder);
    }
}
