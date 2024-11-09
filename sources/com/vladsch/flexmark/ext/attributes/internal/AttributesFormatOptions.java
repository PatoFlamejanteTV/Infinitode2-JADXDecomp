package com.vladsch.flexmark.ext.attributes.internal;

import com.vladsch.flexmark.ext.attributes.AttributeImplicitName;
import com.vladsch.flexmark.ext.attributes.AttributeValueQuotes;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;
import com.vladsch.flexmark.util.format.options.DiscretionaryText;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesFormatOptions.class */
public class AttributesFormatOptions implements MutableDataSetter {
    public final boolean attributesCombineConsecutive;
    public final boolean attributesSort;
    public final DiscretionaryText attributesSpaces;
    public final DiscretionaryText attributeEqualSpace;
    public final AttributeValueQuotes attributeValueQuotes;
    public final AttributeImplicitName attributeIdFormat;
    public final AttributeImplicitName attributeClassFormat;

    public AttributesFormatOptions(DataHolder dataHolder) {
        this.attributesCombineConsecutive = AttributesExtension.FORMAT_ATTRIBUTES_COMBINE_CONSECUTIVE.get(dataHolder).booleanValue();
        this.attributesSort = AttributesExtension.FORMAT_ATTRIBUTES_SORT.get(dataHolder).booleanValue();
        this.attributesSpaces = AttributesExtension.FORMAT_ATTRIBUTES_SPACES.get(dataHolder);
        this.attributeEqualSpace = AttributesExtension.FORMAT_ATTRIBUTE_EQUAL_SPACE.get(dataHolder);
        this.attributeValueQuotes = AttributesExtension.FORMAT_ATTRIBUTE_VALUE_QUOTES.get(dataHolder);
        this.attributeIdFormat = AttributesExtension.FORMAT_ATTRIBUTE_ID.get(dataHolder);
        this.attributeClassFormat = AttributesExtension.FORMAT_ATTRIBUTE_CLASS.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) AttributesExtension.FORMAT_ATTRIBUTES_COMBINE_CONSECUTIVE, (DataKey<Boolean>) Boolean.valueOf(this.attributesCombineConsecutive));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) AttributesExtension.FORMAT_ATTRIBUTES_SORT, (DataKey<Boolean>) Boolean.valueOf(this.attributesSort));
        mutableDataHolder.set((DataKey<DataKey<DiscretionaryText>>) AttributesExtension.FORMAT_ATTRIBUTES_SPACES, (DataKey<DiscretionaryText>) this.attributesSpaces);
        mutableDataHolder.set((DataKey<DataKey<DiscretionaryText>>) AttributesExtension.FORMAT_ATTRIBUTE_EQUAL_SPACE, (DataKey<DiscretionaryText>) this.attributeEqualSpace);
        mutableDataHolder.set((DataKey<DataKey<AttributeValueQuotes>>) AttributesExtension.FORMAT_ATTRIBUTE_VALUE_QUOTES, (DataKey<AttributeValueQuotes>) this.attributeValueQuotes);
        mutableDataHolder.set((DataKey<DataKey<AttributeImplicitName>>) AttributesExtension.FORMAT_ATTRIBUTE_ID, (DataKey<AttributeImplicitName>) this.attributeIdFormat);
        mutableDataHolder.set((DataKey<DataKey<AttributeImplicitName>>) AttributesExtension.FORMAT_ATTRIBUTE_CLASS, (DataKey<AttributeImplicitName>) this.attributeClassFormat);
        return mutableDataHolder;
    }
}
