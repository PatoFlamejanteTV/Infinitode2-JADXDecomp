package com.vladsch.flexmark.util.html;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/MutableAttributeImpl.class */
public class MutableAttributeImpl implements MutableAttribute {
    private final String name;
    private final char valueListDelimiter;
    private final char valueNameDelimiter;
    private String value;
    private LinkedHashMap<String, String> values;

    private MutableAttributeImpl(CharSequence charSequence, CharSequence charSequence2, char c, char c2) {
        this.name = String.valueOf(charSequence);
        this.valueListDelimiter = c;
        this.valueNameDelimiter = c2;
        this.value = charSequence2 == null ? "" : String.valueOf(charSequence2);
        this.values = null;
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public char getValueListDelimiter() {
        return this.valueListDelimiter;
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public char getValueNameDelimiter() {
        return this.valueNameDelimiter;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.misc.Mutable
    public Attribute toImmutable() {
        return AttributeImpl.of(this);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.misc.Immutable
    public MutableAttribute toMutable() {
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.MutableAttribute
    public MutableAttribute copy() {
        return of(this);
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public String getName() {
        return this.name;
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public String getValue() {
        if (this.value == null) {
            this.value = valueFromMap();
        }
        return this.value;
    }

    public void resetToValuesMap() {
        if (this.values == null) {
            throw new IllegalStateException("resetToValuesMap called when values is null");
        }
        this.value = null;
    }

    protected Map<String, String> getValueMap() {
        if (this.values == null) {
            this.values = new LinkedHashMap<>();
            if (this.valueListDelimiter != 0) {
                if (!this.value.isEmpty()) {
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 < this.value.length()) {
                            int indexOf = this.value.indexOf(this.valueListDelimiter, i2);
                            int length = indexOf == -1 ? this.value.length() : indexOf;
                            if (i2 < length) {
                                String substring = this.value.substring(i2, length);
                                int indexOf2 = this.valueNameDelimiter != 0 ? substring.indexOf(this.valueNameDelimiter) : -1;
                                int i3 = indexOf2;
                                if (indexOf2 != -1) {
                                    this.values.put(substring.substring(0, i3), substring.substring(i3 + 1));
                                } else {
                                    this.values.put(substring, "");
                                }
                            }
                            if (indexOf == -1) {
                                break;
                            }
                            i = length + 1;
                        } else {
                            break;
                        }
                    }
                }
            } else {
                this.values.put(this.value, "");
            }
        }
        return this.values;
    }

    protected String valueFromMap() {
        MutableAttributeImpl mutableAttributeImpl;
        String next;
        if (this.valueListDelimiter != 0) {
            StringBuilder sb = new StringBuilder();
            if (this.valueNameDelimiter != 0) {
                String str = "";
                String valueOf = String.valueOf(this.valueListDelimiter);
                for (Map.Entry<String, String> entry : this.values.entrySet()) {
                    if (!entry.getKey().isEmpty()) {
                        sb.append(str);
                        str = valueOf;
                        sb.append(entry.getKey()).append(this.valueNameDelimiter).append(entry.getValue());
                    }
                }
            } else {
                String str2 = "";
                String valueOf2 = String.valueOf(this.valueListDelimiter);
                for (String str3 : this.values.keySet()) {
                    if (!str3.isEmpty()) {
                        sb.append(str2);
                        sb.append(str3);
                        str2 = valueOf2;
                    }
                }
            }
            mutableAttributeImpl = this;
            next = sb.toString();
        } else {
            mutableAttributeImpl = this;
            next = (mutableAttributeImpl.values == null || this.values.isEmpty()) ? "" : this.values.keySet().iterator().next();
        }
        mutableAttributeImpl.value = next;
        return this.value;
    }

    @Override // com.vladsch.flexmark.util.html.Attribute
    public boolean isNonRendering() {
        if (this.name.indexOf(32) == -1) {
            return this.value.isEmpty() && NON_RENDERING_WHEN_EMPTY.contains(this.name);
        }
        return true;
    }

    @Override // com.vladsch.flexmark.util.html.MutableAttribute, com.vladsch.flexmark.util.html.Attribute
    public MutableAttributeImpl replaceValue(CharSequence charSequence) {
        String valueOf = charSequence == null ? "" : String.valueOf(charSequence);
        if (this.value == null || charSequence == null || !this.value.equals(valueOf)) {
            this.value = valueOf;
            this.values = null;
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.MutableAttribute, com.vladsch.flexmark.util.html.Attribute
    public MutableAttributeImpl setValue(CharSequence charSequence) {
        if (this.valueListDelimiter != 0) {
            if (charSequence != null && charSequence.length() != 0) {
                Map<String, String> valueMap = getValueMap();
                forEachValue(charSequence, (str, str2) -> {
                    if (this.valueNameDelimiter != 0 && str2.isEmpty()) {
                        valueMap.remove(str);
                    } else {
                        valueMap.put(str, str2);
                    }
                });
                this.value = null;
            }
        } else if (this.value == null || !this.value.contentEquals(charSequence)) {
            this.value = charSequence == null ? "" : String.valueOf(charSequence);
            this.values = null;
        }
        return this;
    }

    private void forEachValue(CharSequence charSequence, BiConsumer<String, String> biConsumer) {
        String valueOf = charSequence == null ? "" : String.valueOf(charSequence);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < valueOf.length()) {
                int indexOf = valueOf.indexOf(this.valueListDelimiter, i2);
                int length = indexOf == -1 ? valueOf.length() : indexOf;
                if (i2 < length) {
                    String trim = valueOf.substring(i2, length).trim();
                    if (!trim.isEmpty()) {
                        int indexOf2 = this.valueNameDelimiter == 0 ? -1 : trim.indexOf(this.valueNameDelimiter);
                        int i3 = indexOf2;
                        biConsumer.accept(indexOf2 == -1 ? trim : trim.substring(0, i3), i3 == -1 ? "" : trim.substring(i3 + 1));
                    }
                }
                if (indexOf != -1) {
                    i = length + 1;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    @Override // com.vladsch.flexmark.util.html.MutableAttribute, com.vladsch.flexmark.util.html.Attribute
    public MutableAttributeImpl removeValue(CharSequence charSequence) {
        if (this.valueListDelimiter != 0) {
            if (charSequence != null && charSequence.length() != 0) {
                Map<String, String> valueMap = getValueMap();
                boolean[] zArr = {false};
                forEachValue(charSequence, (str, str2) -> {
                    if (valueMap.remove(str) != null) {
                        zArr[0] = true;
                    }
                });
                if (zArr[0]) {
                    this.value = null;
                }
            }
        } else if (this.value == null || !this.value.contentEquals(charSequence)) {
            this.value = "";
            this.values = null;
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.MutableAttribute, com.vladsch.flexmark.util.html.Attribute
    public boolean containsValue(CharSequence charSequence) {
        return AttributeImpl.indexOfValue(this.value, charSequence, this.valueListDelimiter, this.valueNameDelimiter) != -1;
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
            return getValue().equals(attribute.getValue());
        }
        return false;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + getValue().hashCode();
    }

    public String toString() {
        return "MutableAttributeImpl { name='" + this.name + "', value='" + getValue() + "' }";
    }

    public static MutableAttributeImpl of(Attribute attribute) {
        return of(attribute.getName(), attribute.getValue(), attribute.getValueListDelimiter(), attribute.getValueNameDelimiter());
    }

    public static MutableAttributeImpl of(CharSequence charSequence) {
        return of(charSequence, charSequence, (char) 0, (char) 0);
    }

    public static MutableAttributeImpl of(CharSequence charSequence, CharSequence charSequence2) {
        return of(charSequence, charSequence2, (char) 0, (char) 0);
    }

    public static MutableAttributeImpl of(CharSequence charSequence, CharSequence charSequence2, char c) {
        return of(charSequence, charSequence2, c, (char) 0);
    }

    public static MutableAttributeImpl of(CharSequence charSequence, CharSequence charSequence2, char c, char c2) {
        if (Attribute.CLASS_ATTR.contentEquals(charSequence)) {
            return new MutableAttributeImpl(charSequence, charSequence2, ' ', (char) 0);
        }
        if (Attribute.STYLE_ATTR.contentEquals(charSequence)) {
            return new MutableAttributeImpl(charSequence, charSequence2, ';', ':');
        }
        return new MutableAttributeImpl(charSequence, charSequence2, c, c2);
    }
}
