package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.misc.Utils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/format/FormattedCounter.class */
public class FormattedCounter {
    private final NumberFormat numberFormat;
    private final Boolean isLowercase;
    private final String delimiter;
    private int count;

    public FormattedCounter(NumberFormat numberFormat, Boolean bool, String str) {
        this.numberFormat = numberFormat;
        this.isLowercase = bool;
        this.delimiter = str;
        reset();
    }

    public void reset() {
        this.count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public int nextCount() {
        int i = this.count + 1;
        this.count = i;
        return i;
    }

    public String getFormatted(boolean z) {
        String format = NumberFormat.getFormat(this.numberFormat, Utils.minLimit(this.count, 1));
        String lowerCase = this.isLowercase == null ? format : this.isLowercase.booleanValue() ? format.toLowerCase() : format.toUpperCase();
        return (!z || this.delimiter == null || this.delimiter.isEmpty()) ? lowerCase : lowerCase + this.delimiter;
    }
}
