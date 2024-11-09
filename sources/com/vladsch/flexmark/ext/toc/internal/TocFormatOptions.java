package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.ext.toc.SimTocGenerateOnFormat;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocFormatOptions.class */
public class TocFormatOptions implements MutableDataSetter {
    public final SimTocGenerateOnFormat updateOnFormat;
    public final TocOptions options;

    public TocFormatOptions() {
        this(null);
    }

    public TocFormatOptions(DataHolder dataHolder) {
        this.updateOnFormat = TocExtension.FORMAT_UPDATE_ON_FORMAT.get(dataHolder);
        this.options = TocExtension.FORMAT_OPTIONS.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<SimTocGenerateOnFormat>>) TocExtension.FORMAT_UPDATE_ON_FORMAT, (DataKey<SimTocGenerateOnFormat>) this.updateOnFormat);
        mutableDataHolder.set((DataKey<DataKey<TocOptions>>) TocExtension.FORMAT_OPTIONS, (DataKey<TocOptions>) this.options);
        return mutableDataHolder;
    }
}
