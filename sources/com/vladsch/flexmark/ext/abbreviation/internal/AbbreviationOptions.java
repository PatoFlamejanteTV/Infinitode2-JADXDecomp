package com.vladsch.flexmark.ext.abbreviation.internal;

import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationOptions.class */
public class AbbreviationOptions {
    protected final boolean useLinks;

    public AbbreviationOptions(DataHolder dataHolder) {
        this.useLinks = AbbreviationExtension.USE_LINKS.get(dataHolder).booleanValue();
    }
}
