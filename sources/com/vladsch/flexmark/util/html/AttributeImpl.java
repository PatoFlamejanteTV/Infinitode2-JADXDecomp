package com.vladsch.flexmark.util.html;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/AttributeImpl.class */
public class AttributeImpl implements Attribute {
    private final String name;
    private final char valueListDelimiter;
    private final char valueNameDelimiter;
    private final String value;

    private AttributeImpl(CharSequence charSequence, CharSequence charSequence2, char c, char c2) {
        this.name = String.valueOf(charSequence);
        this.valueListDelimiter = c;
        this.valueNameDelimiter = c2;
        this.value = charSequence2 == null ? "" : String.valueOf(charSequence2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.misc.Immutable
    public MutableAttribute toMutable() {
        return MutableAttributeImpl.of(this);
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public char getValueListDelimiter() {
        return this.valueListDelimiter;
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public char getValueNameDelimiter() {
        return this.valueNameDelimiter;
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public String getName() {
        return this.name;
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public String getValue() {
        return this.value;
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public boolean isNonRendering() {
        if (this.name.indexOf(32) == -1) {
            return this.value.isEmpty() && NON_RENDERING_WHEN_EMPTY.contains(this.name);
        }
        return true;
    }

    public static int indexOfValue(CharSequence charSequence, CharSequence charSequence2, char c, char c2) {
        int indexOf;
        if (charSequence2.length() == 0 || charSequence.length() == 0) {
            return -1;
        }
        if (c == 0) {
            return charSequence.equals(charSequence2) ? 0 : -1;
        }
        int i = 0;
        BasedSequence of = BasedSequence.of(charSequence);
        while (i < charSequence.length() && (indexOf = of.indexOf(charSequence2, i)) != -1) {
            int length = indexOf + charSequence2.length();
            if ((indexOf == 0 || charSequence.charAt(indexOf - 1) == c || (c2 != 0 && charSequence.charAt(indexOf - 1) == c2)) && (length >= charSequence.length() || charSequence.charAt(length) == c || (c2 != 0 && charSequence.charAt(length) == c2))) {
                return indexOf;
            }
            i = length + 1;
        }
        return -1;
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public boolean containsValue(CharSequence charSequence) {
        return indexOfValue(this.value, charSequence, this.valueListDelimiter, this.valueNameDelimiter) != -1;
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public Attribute replaceValue(CharSequence charSequence) {
        return charSequence.equals(this.value) ? this : of(this.name, charSequence, this.valueListDelimiter, this.valueNameDelimiter);
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public Attribute setValue(CharSequence charSequence) {
        MutableAttribute value = toMutable().setValue(charSequence);
        return value.equals(this) ? this : value.toImmutable();
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public Attribute removeValue(CharSequence charSequence) {
        MutableAttribute removeValue = toMutable().removeValue(charSequence);
        return removeValue.equals(this) ? this : removeValue.toImmutable();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Attribute)) {
            return false;
        }
        Attribute attribute = (Attribute) obj;
        if (this.name.equals(attribute.getName())) {
            return this.value.equals(attribute.getValue());
        }
        return false;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.value.hashCode();
    }

    public String toString() {
        return "AttributeImpl { name='" + this.name + "', value='" + this.value + "' }";
    }

    public static AttributeImpl of(Attribute attribute) {
        return of(attribute.getName(), attribute.getValue(), attribute.getValueListDelimiter(), attribute.getValueNameDelimiter());
    }

    public static AttributeImpl of(CharSequence charSequence) {
        return of(charSequence, charSequence, (char) 0, (char) 0);
    }

    public static AttributeImpl of(CharSequence charSequence, CharSequence charSequence2) {
        return of(charSequence, charSequence2, (char) 0, (char) 0);
    }

    public static AttributeImpl of(CharSequence charSequence, CharSequence charSequence2, char c) {
        return of(charSequence, charSequence2, c, (char) 0);
    }

    public static AttributeImpl of(CharSequence charSequence, CharSequence charSequence2, char c, char c2) {
        if (charSequence.equals(Attribute.CLASS_ATTR)) {
            return new AttributeImpl(charSequence, charSequence2, ' ', (char) 0);
        }
        if (charSequence.equals(Attribute.STYLE_ATTR)) {
            return new AttributeImpl(charSequence, charSequence2, ';', ':');
        }
        return new AttributeImpl(charSequence, charSequence2, c, c2);
    }
}
