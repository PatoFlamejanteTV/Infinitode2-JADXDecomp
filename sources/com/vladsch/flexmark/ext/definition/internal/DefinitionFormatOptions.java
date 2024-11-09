package com.vladsch.flexmark.ext.definition.internal;

import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.options.DefinitionMarker;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionFormatOptions.class */
public class DefinitionFormatOptions {
    public final int markerSpaces;
    public final DefinitionMarker markerType;

    public DefinitionFormatOptions(DataHolder dataHolder) {
        this.markerSpaces = DefinitionExtension.FORMAT_MARKER_SPACES.get(dataHolder).intValue();
        this.markerType = DefinitionExtension.FORMAT_MARKER_TYPE.get(dataHolder);
    }
}
