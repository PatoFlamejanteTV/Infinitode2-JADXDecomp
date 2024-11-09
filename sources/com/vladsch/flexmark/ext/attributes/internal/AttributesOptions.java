package com.vladsch.flexmark.ext.attributes.internal;

import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.attributes.FencedCodeAddType;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesOptions.class */
public class AttributesOptions implements MutableDataSetter {
    public final boolean assignTextAttributes;
    public final boolean wrapNonAttributeText;
    public final boolean useEmptyImplicitAsSpanDelimiter;
    public final boolean fencedCodeInfoAttributes;
    public final FencedCodeAddType fencedCodeAddAttributes;

    public AttributesOptions(DataHolder dataHolder) {
        this.assignTextAttributes = AttributesExtension.ASSIGN_TEXT_ATTRIBUTES.get(dataHolder).booleanValue();
        this.wrapNonAttributeText = AttributesExtension.WRAP_NON_ATTRIBUTE_TEXT.get(dataHolder).booleanValue();
        this.useEmptyImplicitAsSpanDelimiter = AttributesExtension.USE_EMPTY_IMPLICIT_AS_SPAN_DELIMITER.get(dataHolder).booleanValue();
        this.fencedCodeInfoAttributes = AttributesExtension.FENCED_CODE_INFO_ATTRIBUTES.get(dataHolder).booleanValue();
        this.fencedCodeAddAttributes = AttributesExtension.FENCED_CODE_ADD_ATTRIBUTES.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) AttributesExtension.ASSIGN_TEXT_ATTRIBUTES, (DataKey<Boolean>) Boolean.valueOf(this.assignTextAttributes));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) AttributesExtension.WRAP_NON_ATTRIBUTE_TEXT, (DataKey<Boolean>) Boolean.valueOf(this.wrapNonAttributeText));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) AttributesExtension.USE_EMPTY_IMPLICIT_AS_SPAN_DELIMITER, (DataKey<Boolean>) Boolean.valueOf(this.useEmptyImplicitAsSpanDelimiter));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) AttributesExtension.FENCED_CODE_INFO_ATTRIBUTES, (DataKey<Boolean>) Boolean.valueOf(this.fencedCodeInfoAttributes));
        mutableDataHolder.set((DataKey<DataKey<FencedCodeAddType>>) AttributesExtension.FENCED_CODE_ADD_ATTRIBUTES, (DataKey<FencedCodeAddType>) this.fencedCodeAddAttributes);
        return mutableDataHolder;
    }
}
