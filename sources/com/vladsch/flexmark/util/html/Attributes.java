package com.vladsch.flexmark.util.html;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/Attributes.class */
public class Attributes {
    public static final Attributes EMPTY = new Attributes();
    protected LinkedHashMap<String, Attribute> attributes;

    public Attributes() {
        this.attributes = null;
    }

    public Attributes(Attributes attributes) {
        this.attributes = (attributes == null || attributes.attributes == null) ? null : new LinkedHashMap<>(attributes.attributes);
    }

    public MutableAttributes toMutable() {
        return new MutableAttributes(this);
    }

    public Attributes toImmutable() {
        return this;
    }

    public Attribute get(CharSequence charSequence) {
        if (this.attributes == null || charSequence == null || charSequence.length() == 0) {
            return null;
        }
        return this.attributes.get(String.valueOf(charSequence));
    }

    public String getValue(CharSequence charSequence) {
        if (this.attributes == null || charSequence == null || charSequence.length() == 0) {
            return "";
        }
        Attribute attribute = this.attributes.get(String.valueOf(charSequence));
        return attribute == null ? "" : attribute.getValue();
    }

    public boolean contains(CharSequence charSequence) {
        if (this.attributes == null || charSequence == null || charSequence.length() == 0) {
            return false;
        }
        return this.attributes.containsKey(String.valueOf(charSequence));
    }

    public boolean containsValue(CharSequence charSequence, CharSequence charSequence2) {
        if (this.attributes == null) {
            return false;
        }
        Attribute attribute = this.attributes.get(String.valueOf(charSequence));
        return attribute != null && attribute.containsValue(charSequence2);
    }

    public boolean isEmpty() {
        return this.attributes == null || this.attributes.isEmpty();
    }

    public Set<String> keySet() {
        return this.attributes != null ? this.attributes.keySet() : Collections.EMPTY_SET;
    }

    public Collection<Attribute> values() {
        return this.attributes != null ? this.attributes.values() : Collections.EMPTY_LIST;
    }

    public Set<Map.Entry<String, Attribute>> entrySet() {
        return this.attributes != null ? this.attributes.entrySet() : Collections.EMPTY_SET;
    }

    public void forEach(BiConsumer<String, Attribute> biConsumer) {
        if (this.attributes != null) {
            for (Map.Entry<String, Attribute> entry : this.attributes.entrySet()) {
                biConsumer.accept(entry.getKey(), entry.getValue());
            }
        }
    }

    public int size() {
        if (this.attributes == null) {
            return 0;
        }
        return this.attributes.size();
    }

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
        return "Attributes{" + sb.toString() + '}';
    }
}
