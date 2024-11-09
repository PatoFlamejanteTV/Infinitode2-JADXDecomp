package com.vladsch.flexmark.util.html;

import com.vladsch.flexmark.util.misc.Mutable;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/MutableAttribute.class */
public interface MutableAttribute extends Attribute, Mutable<MutableAttribute, Attribute> {
    MutableAttribute copy();

    @Override // com.vladsch.flexmark.util.html.Attribute
    boolean containsValue(CharSequence charSequence);

    @Override // com.vladsch.flexmark.util.html.Attribute
    MutableAttribute replaceValue(CharSequence charSequence);

    @Override // com.vladsch.flexmark.util.html.Attribute
    MutableAttribute setValue(CharSequence charSequence);

    @Override // com.vladsch.flexmark.util.html.Attribute
    MutableAttribute removeValue(CharSequence charSequence);
}
