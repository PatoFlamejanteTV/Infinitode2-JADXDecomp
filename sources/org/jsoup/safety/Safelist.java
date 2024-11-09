package org.jsoup.safety;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

/* loaded from: infinitode-2.jar:org/jsoup/safety/Safelist.class */
public class Safelist {
    private static final String All = ":all";
    private final Set<TagName> tagNames;
    private final Map<TagName, Set<AttributeKey>> attributes;
    private final Map<TagName, Map<AttributeKey, AttributeValue>> enforcedAttributes;
    private final Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols;
    private boolean preserveRelativeLinks;

    public static Safelist none() {
        return new Safelist();
    }

    public static Safelist simpleText() {
        return new Safelist().addTags(FlexmarkHtmlConverter.B_NODE, FlexmarkHtmlConverter.EM_NODE, FlexmarkHtmlConverter.I_NODE, FlexmarkHtmlConverter.STRONG_NODE, FlexmarkHtmlConverter.U_NODE);
    }

    public static Safelist basic() {
        return new Safelist().addTags(FlexmarkHtmlConverter.A_NODE, FlexmarkHtmlConverter.B_NODE, FlexmarkHtmlConverter.BLOCKQUOTE_NODE, FlexmarkHtmlConverter.BR_NODE, "cite", FlexmarkHtmlConverter.CODE_NODE, FlexmarkHtmlConverter.DD_NODE, FlexmarkHtmlConverter.DL_NODE, FlexmarkHtmlConverter.DT_NODE, FlexmarkHtmlConverter.EM_NODE, FlexmarkHtmlConverter.I_NODE, FlexmarkHtmlConverter.LI_NODE, FlexmarkHtmlConverter.OL_NODE, FlexmarkHtmlConverter.P_NODE, FlexmarkHtmlConverter.PRE_NODE, "q", "small", FlexmarkHtmlConverter.SPAN_NODE, FlexmarkHtmlConverter.STRIKE_NODE, FlexmarkHtmlConverter.STRONG_NODE, FlexmarkHtmlConverter.SUB_NODE, FlexmarkHtmlConverter.SUP_NODE, FlexmarkHtmlConverter.U_NODE, FlexmarkHtmlConverter.UL_NODE).addAttributes(FlexmarkHtmlConverter.A_NODE, "href").addAttributes(FlexmarkHtmlConverter.BLOCKQUOTE_NODE, "cite").addAttributes("q", "cite").addProtocols(FlexmarkHtmlConverter.A_NODE, "href", "ftp", "http", "https", "mailto").addProtocols(FlexmarkHtmlConverter.BLOCKQUOTE_NODE, "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addEnforcedAttribute(FlexmarkHtmlConverter.A_NODE, "rel", "nofollow");
    }

    public static Safelist basicWithImages() {
        return basic().addTags(FlexmarkHtmlConverter.IMG_NODE).addAttributes(FlexmarkHtmlConverter.IMG_NODE, "align", "alt", "height", "src", Attribute.TITLE_ATTR, "width").addProtocols(FlexmarkHtmlConverter.IMG_NODE, "src", "http", "https");
    }

    public static Safelist relaxed() {
        return new Safelist().addTags(FlexmarkHtmlConverter.A_NODE, FlexmarkHtmlConverter.B_NODE, FlexmarkHtmlConverter.BLOCKQUOTE_NODE, FlexmarkHtmlConverter.BR_NODE, FlexmarkHtmlConverter.CAPTION_NODE, "cite", FlexmarkHtmlConverter.CODE_NODE, "col", "colgroup", FlexmarkHtmlConverter.DD_NODE, FlexmarkHtmlConverter.DIV_NODE, FlexmarkHtmlConverter.DL_NODE, FlexmarkHtmlConverter.DT_NODE, FlexmarkHtmlConverter.EM_NODE, FlexmarkHtmlConverter.H1_NODE, FlexmarkHtmlConverter.H2_NODE, FlexmarkHtmlConverter.H3_NODE, FlexmarkHtmlConverter.H4_NODE, FlexmarkHtmlConverter.H5_NODE, FlexmarkHtmlConverter.H6_NODE, FlexmarkHtmlConverter.I_NODE, FlexmarkHtmlConverter.IMG_NODE, FlexmarkHtmlConverter.LI_NODE, FlexmarkHtmlConverter.OL_NODE, FlexmarkHtmlConverter.P_NODE, FlexmarkHtmlConverter.PRE_NODE, "q", "small", FlexmarkHtmlConverter.SPAN_NODE, FlexmarkHtmlConverter.STRIKE_NODE, FlexmarkHtmlConverter.STRONG_NODE, FlexmarkHtmlConverter.SUB_NODE, FlexmarkHtmlConverter.SUP_NODE, FlexmarkHtmlConverter.TABLE_NODE, FlexmarkHtmlConverter.TBODY_NODE, FlexmarkHtmlConverter.TD_NODE, "tfoot", FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.THEAD_NODE, FlexmarkHtmlConverter.TR_NODE, FlexmarkHtmlConverter.U_NODE, FlexmarkHtmlConverter.UL_NODE).addAttributes(FlexmarkHtmlConverter.A_NODE, "href", Attribute.TITLE_ATTR).addAttributes(FlexmarkHtmlConverter.BLOCKQUOTE_NODE, "cite").addAttributes("col", FlexmarkHtmlConverter.SPAN_NODE, "width").addAttributes("colgroup", FlexmarkHtmlConverter.SPAN_NODE, "width").addAttributes(FlexmarkHtmlConverter.IMG_NODE, "align", "alt", "height", "src", Attribute.TITLE_ATTR, "width").addAttributes(FlexmarkHtmlConverter.OL_NODE, "start", "type").addAttributes("q", "cite").addAttributes(FlexmarkHtmlConverter.TABLE_NODE, "summary", "width").addAttributes(FlexmarkHtmlConverter.TD_NODE, FlexmarkHtmlConverter.ABBR_NODE, "axis", "colspan", "rowspan", "width").addAttributes(FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.ABBR_NODE, "axis", "colspan", "rowspan", "scope", "width").addAttributes(FlexmarkHtmlConverter.UL_NODE, "type").addProtocols(FlexmarkHtmlConverter.A_NODE, "href", "ftp", "http", "https", "mailto").addProtocols(FlexmarkHtmlConverter.BLOCKQUOTE_NODE, "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addProtocols(FlexmarkHtmlConverter.IMG_NODE, "src", "http", "https").addProtocols("q", "cite", "http", "https");
    }

    public Safelist() {
        this.tagNames = new HashSet();
        this.attributes = new HashMap();
        this.enforcedAttributes = new HashMap();
        this.protocols = new HashMap();
        this.preserveRelativeLinks = false;
    }

    public Safelist(Safelist safelist) {
        this();
        this.tagNames.addAll(safelist.tagNames);
        for (Map.Entry<TagName, Set<AttributeKey>> entry : safelist.attributes.entrySet()) {
            this.attributes.put(entry.getKey(), new HashSet(entry.getValue()));
        }
        for (Map.Entry<TagName, Map<AttributeKey, AttributeValue>> entry2 : safelist.enforcedAttributes.entrySet()) {
            this.enforcedAttributes.put(entry2.getKey(), new HashMap(entry2.getValue()));
        }
        for (Map.Entry<TagName, Map<AttributeKey, Set<Protocol>>> entry3 : safelist.protocols.entrySet()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<AttributeKey, Set<Protocol>> entry4 : entry3.getValue().entrySet()) {
                hashMap.put(entry4.getKey(), new HashSet(entry4.getValue()));
            }
            this.protocols.put(entry3.getKey(), hashMap);
        }
        this.preserveRelativeLinks = safelist.preserveRelativeLinks;
    }

    public Safelist addTags(String... strArr) {
        Validate.notNull(strArr);
        for (String str : strArr) {
            Validate.notEmpty(str);
            Validate.isFalse(str.equalsIgnoreCase("noscript"), "noscript is unsupported in Safelists, due to incompatibilities between parsers with and without script-mode enabled");
            this.tagNames.add(TagName.valueOf(str));
        }
        return this;
    }

    public Safelist removeTags(String... strArr) {
        Validate.notNull(strArr);
        for (String str : strArr) {
            Validate.notEmpty(str);
            TagName valueOf = TagName.valueOf(str);
            if (this.tagNames.remove(valueOf)) {
                this.attributes.remove(valueOf);
                this.enforcedAttributes.remove(valueOf);
                this.protocols.remove(valueOf);
            }
        }
        return this;
    }

    public Safelist addAttributes(String str, String... strArr) {
        Validate.notEmpty(str);
        Validate.notNull(strArr);
        Validate.isTrue(strArr.length > 0, "No attribute names supplied.");
        addTags(str);
        TagName valueOf = TagName.valueOf(str);
        HashSet hashSet = new HashSet();
        for (String str2 : strArr) {
            Validate.notEmpty(str2);
            hashSet.add(AttributeKey.valueOf(str2));
        }
        if (this.attributes.containsKey(valueOf)) {
            this.attributes.get(valueOf).addAll(hashSet);
        } else {
            this.attributes.put(valueOf, hashSet);
        }
        return this;
    }

    public Safelist removeAttributes(String str, String... strArr) {
        Validate.notEmpty(str);
        Validate.notNull(strArr);
        Validate.isTrue(strArr.length > 0, "No attribute names supplied.");
        TagName valueOf = TagName.valueOf(str);
        HashSet hashSet = new HashSet();
        for (String str2 : strArr) {
            Validate.notEmpty(str2);
            hashSet.add(AttributeKey.valueOf(str2));
        }
        if (this.tagNames.contains(valueOf) && this.attributes.containsKey(valueOf)) {
            Set<AttributeKey> set = this.attributes.get(valueOf);
            set.removeAll(hashSet);
            if (set.isEmpty()) {
                this.attributes.remove(valueOf);
            }
        }
        if (str.equals(All)) {
            Iterator<Map.Entry<TagName, Set<AttributeKey>>> it = this.attributes.entrySet().iterator();
            while (it.hasNext()) {
                Set<AttributeKey> value = it.next().getValue();
                value.removeAll(hashSet);
                if (value.isEmpty()) {
                    it.remove();
                }
            }
        }
        return this;
    }

    public Safelist addEnforcedAttribute(String str, String str2, String str3) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notEmpty(str3);
        TagName valueOf = TagName.valueOf(str);
        this.tagNames.add(valueOf);
        AttributeKey valueOf2 = AttributeKey.valueOf(str2);
        AttributeValue valueOf3 = AttributeValue.valueOf(str3);
        if (this.enforcedAttributes.containsKey(valueOf)) {
            this.enforcedAttributes.get(valueOf).put(valueOf2, valueOf3);
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put(valueOf2, valueOf3);
            this.enforcedAttributes.put(valueOf, hashMap);
        }
        return this;
    }

    public Safelist removeEnforcedAttribute(String str, String str2) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        TagName valueOf = TagName.valueOf(str);
        if (this.tagNames.contains(valueOf) && this.enforcedAttributes.containsKey(valueOf)) {
            AttributeKey valueOf2 = AttributeKey.valueOf(str2);
            Map<AttributeKey, AttributeValue> map = this.enforcedAttributes.get(valueOf);
            map.remove(valueOf2);
            if (map.isEmpty()) {
                this.enforcedAttributes.remove(valueOf);
            }
        }
        return this;
    }

    public Safelist preserveRelativeLinks(boolean z) {
        this.preserveRelativeLinks = z;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r0v36, types: [java.util.Map] */
    public Safelist addProtocols(String str, String str2, String... strArr) {
        HashMap hashMap;
        HashSet hashSet;
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notNull(strArr);
        TagName valueOf = TagName.valueOf(str);
        AttributeKey valueOf2 = AttributeKey.valueOf(str2);
        if (this.protocols.containsKey(valueOf)) {
            hashMap = (Map) this.protocols.get(valueOf);
        } else {
            hashMap = new HashMap();
            this.protocols.put(valueOf, hashMap);
        }
        if (hashMap.containsKey(valueOf2)) {
            hashSet = (Set) hashMap.get(valueOf2);
        } else {
            hashSet = new HashSet();
            hashMap.put(valueOf2, hashSet);
        }
        for (String str3 : strArr) {
            Validate.notEmpty(str3);
            hashSet.add(Protocol.valueOf(str3));
        }
        return this;
    }

    public Safelist removeProtocols(String str, String str2, String... strArr) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notNull(strArr);
        TagName valueOf = TagName.valueOf(str);
        AttributeKey valueOf2 = AttributeKey.valueOf(str2);
        Validate.isTrue(this.protocols.containsKey(valueOf), "Cannot remove a protocol that is not set.");
        Map<AttributeKey, Set<Protocol>> map = this.protocols.get(valueOf);
        Validate.isTrue(map.containsKey(valueOf2), "Cannot remove a protocol that is not set.");
        Set<Protocol> set = map.get(valueOf2);
        for (String str3 : strArr) {
            Validate.notEmpty(str3);
            set.remove(Protocol.valueOf(str3));
        }
        if (set.isEmpty()) {
            map.remove(valueOf2);
            if (map.isEmpty()) {
                this.protocols.remove(valueOf);
            }
        }
        return this;
    }

    public boolean isSafeTag(String str) {
        return this.tagNames.contains(TagName.valueOf(str));
    }

    public boolean isSafeAttribute(String str, Element element, org.jsoup.nodes.Attribute attribute) {
        TagName valueOf = TagName.valueOf(str);
        AttributeKey valueOf2 = AttributeKey.valueOf(attribute.getKey());
        Set<AttributeKey> set = this.attributes.get(valueOf);
        if (set != null && set.contains(valueOf2)) {
            if (this.protocols.containsKey(valueOf)) {
                Map<AttributeKey, Set<Protocol>> map = this.protocols.get(valueOf);
                return !map.containsKey(valueOf2) || testValidProtocol(element, attribute, map.get(valueOf2));
            }
            return true;
        }
        if (this.enforcedAttributes.get(valueOf) != null) {
            Attributes enforcedAttributes = getEnforcedAttributes(str);
            String key = attribute.getKey();
            if (enforcedAttributes.hasKeyIgnoreCase(key)) {
                return enforcedAttributes.getIgnoreCase(key).equals(attribute.getValue());
            }
        }
        return !str.equals(All) && isSafeAttribute(All, element, attribute);
    }

    private boolean testValidProtocol(Element element, org.jsoup.nodes.Attribute attribute, Set<Protocol> set) {
        String absUrl = element.absUrl(attribute.getKey());
        String str = absUrl;
        if (absUrl.length() == 0) {
            str = attribute.getValue();
        }
        if (!this.preserveRelativeLinks) {
            attribute.setValue(str);
        }
        Iterator<Protocol> it = set.iterator();
        while (it.hasNext()) {
            String protocol = it.next().toString();
            if (!protocol.equals("#")) {
                if (Normalizer.lowerCase(str).startsWith(protocol + ":")) {
                    return true;
                }
            } else if (isValidAnchor(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidAnchor(String str) {
        return str.startsWith("#") && !str.matches(".*\\s.*");
    }

    public Attributes getEnforcedAttributes(String str) {
        Attributes attributes = new Attributes();
        TagName valueOf = TagName.valueOf(str);
        if (this.enforcedAttributes.containsKey(valueOf)) {
            for (Map.Entry<AttributeKey, AttributeValue> entry : this.enforcedAttributes.get(valueOf).entrySet()) {
                attributes.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        return attributes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/safety/Safelist$TagName.class */
    public static class TagName extends TypedValue {
        TagName(String str) {
            super(str);
        }

        static TagName valueOf(String str) {
            return new TagName(Normalizer.lowerCase(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/safety/Safelist$AttributeKey.class */
    public static class AttributeKey extends TypedValue {
        AttributeKey(String str) {
            super(str);
        }

        static AttributeKey valueOf(String str) {
            return new AttributeKey(Normalizer.lowerCase(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/safety/Safelist$AttributeValue.class */
    public static class AttributeValue extends TypedValue {
        AttributeValue(String str) {
            super(str);
        }

        static AttributeValue valueOf(String str) {
            return new AttributeValue(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/safety/Safelist$Protocol.class */
    public static class Protocol extends TypedValue {
        Protocol(String str) {
            super(str);
        }

        static Protocol valueOf(String str) {
            return new Protocol(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/jsoup/safety/Safelist$TypedValue.class */
    public static abstract class TypedValue {
        private final String value;

        TypedValue(String str) {
            Validate.notNull(str);
            this.value = str;
        }

        public int hashCode() {
            return 31 + (this.value == null ? 0 : this.value.hashCode());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TypedValue typedValue = (TypedValue) obj;
            if (this.value == null) {
                return typedValue.value == null;
            }
            return this.value.equals(typedValue.value);
        }

        public String toString() {
            return this.value;
        }
    }
}
