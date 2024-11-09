package com.vladsch.flexmark.util.html;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.LinkedHashMap;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/MutableAttributes.class */
public class MutableAttributes extends Attributes {
    public MutableAttributes() {
    }

    public MutableAttributes(Attributes attributes) {
        super(attributes);
    }

    @Override // com.vladsch.flexmark.util.html.Attributes
    public MutableAttributes toMutable() {
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.Attributes
    public Attributes toImmutable() {
        return new Attributes(this);
    }

    protected LinkedHashMap<String, Attribute> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new LinkedHashMap<>();
        }
        return this.attributes;
    }

    public Attribute replaceValue(Attribute attribute) {
        return replaceValue(attribute.getName(), attribute.getValue());
    }

    public Attribute replaceValue(CharSequence charSequence, CharSequence charSequence2) {
        Attribute of;
        Attribute attribute;
        String valueOf = String.valueOf(charSequence);
        if (this.attributes != null && (attribute = this.attributes.get(valueOf)) != null) {
            of = attribute.replaceValue(charSequence2);
        } else {
            of = AttributeImpl.of(valueOf, charSequence2);
        }
        getAttributes().put(valueOf, of);
        return of;
    }

    public Attribute addValue(Attribute attribute) {
        return addValue(attribute.getName(), attribute.getValue());
    }

    public MutableAttributes addValues(Attributes attributes) {
        for (Attribute attribute : attributes.values()) {
            addValue(attribute.getName(), attribute.getValue());
        }
        return this;
    }

    public Attribute addValue(CharSequence charSequence, CharSequence charSequence2) {
        Attribute of;
        String valueOf = String.valueOf(charSequence);
        if (this.attributes == null) {
            of = AttributeImpl.of(charSequence, charSequence2);
        } else {
            Attribute attribute = this.attributes.get(valueOf);
            if (attribute != null) {
                of = attribute.setValue(charSequence2);
            } else {
                of = AttributeImpl.of(valueOf, charSequence2);
            }
        }
        getAttributes().put(valueOf, of);
        return of;
    }

    public Attribute removeValue(Attribute attribute) {
        return removeValue(attribute.getName(), attribute.getValue());
    }

    public Attribute remove(Attribute attribute) {
        return remove(attribute.getName());
    }

    public Attribute removeValue(CharSequence charSequence, CharSequence charSequence2) {
        if (this.attributes == null || charSequence == null || charSequence.length() == 0) {
            return null;
        }
        String valueOf = String.valueOf(charSequence);
        Attribute removeValue = this.attributes.get(valueOf).removeValue(charSequence2);
        getAttributes().put(valueOf, removeValue);
        return removeValue;
    }

    public void clear() {
        this.attributes = null;
    }

    public Attribute remove(CharSequence charSequence) {
        if (this.attributes == null || charSequence == null || charSequence.length() == 0) {
            return null;
        }
        String valueOf = String.valueOf(charSequence);
        Attribute attribute = this.attributes.get(valueOf);
        this.attributes.remove(valueOf);
        return attribute;
    }

    public void replaceValues(MutableAttributes mutableAttributes) {
        if (this.attributes == null) {
            this.attributes = new LinkedHashMap<>(mutableAttributes.attributes);
        } else {
            this.attributes.putAll(mutableAttributes.attributes);
        }
    }

    @Override // com.vladsch.flexmark.util.html.Attributes
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = "";
        for (String str2 : keySet()) {
            sb.append(str).append(str2);
            Attribute attribute = this.attributes.get(str2);
            if (!attribute.getValue().isEmpty()) {
                sb.append("=\"").append(attribute.getValue().replace("\"", "\\\"")).append("\"");
            }
            str = SequenceUtils.SPACE;
        }
        return "MutableAttributes{" + sb.toString() + '}';
    }
}
