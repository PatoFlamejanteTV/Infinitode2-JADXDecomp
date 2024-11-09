package com.vladsch.flexmark.ext.macros.internal;

import com.vladsch.flexmark.ext.macros.MacrosExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacrosOptions.class */
public class MacrosOptions implements MutableDataSetter {
    public final boolean sourceWrapMacroReferences;

    public MacrosOptions(DataHolder dataHolder) {
        this.sourceWrapMacroReferences = MacrosExtension.SOURCE_WRAP_MACRO_REFERENCES.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) MacrosExtension.SOURCE_WRAP_MACRO_REFERENCES, (DataKey<Boolean>) Boolean.valueOf(this.sourceWrapMacroReferences));
        return mutableDataHolder;
    }
}
