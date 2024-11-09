package org.jsoup.parser;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;

/* loaded from: infinitode-2.jar:org/jsoup/parser/Tag.class */
public class Tag implements Cloneable {
    private String tagName;
    private final String normalName;
    private String namespace;
    private boolean isBlock = true;
    private boolean formatAsBlock = true;
    private boolean empty = false;
    private boolean selfClosing = false;
    private boolean preserveWhitespace = false;
    private boolean formList = false;
    private boolean formSubmit = false;
    private static final Map<String, String[]> namespaces;
    private static final Map<String, Tag> Tags = new HashMap();
    private static final String[] blockTags = {"html", "head", "body", "frameset", "script", "noscript", Attribute.STYLE_ATTR, "meta", "link", Attribute.TITLE_ATTR, "frame", "noframes", "section", "nav", FlexmarkHtmlConverter.ASIDE_NODE, "hgroup", "header", "footer", FlexmarkHtmlConverter.P_NODE, FlexmarkHtmlConverter.H1_NODE, FlexmarkHtmlConverter.H2_NODE, FlexmarkHtmlConverter.H3_NODE, FlexmarkHtmlConverter.H4_NODE, FlexmarkHtmlConverter.H5_NODE, FlexmarkHtmlConverter.H6_NODE, FlexmarkHtmlConverter.UL_NODE, FlexmarkHtmlConverter.OL_NODE, FlexmarkHtmlConverter.PRE_NODE, FlexmarkHtmlConverter.DIV_NODE, FlexmarkHtmlConverter.BLOCKQUOTE_NODE, FlexmarkHtmlConverter.HR_NODE, "address", "figure", "figcaption", "form", "fieldset", FlexmarkHtmlConverter.INS_NODE, FlexmarkHtmlConverter.DEL_NODE, FlexmarkHtmlConverter.DL_NODE, FlexmarkHtmlConverter.DT_NODE, FlexmarkHtmlConverter.DD_NODE, FlexmarkHtmlConverter.LI_NODE, FlexmarkHtmlConverter.TABLE_NODE, FlexmarkHtmlConverter.CAPTION_NODE, FlexmarkHtmlConverter.THEAD_NODE, "tfoot", FlexmarkHtmlConverter.TBODY_NODE, "colgroup", "col", FlexmarkHtmlConverter.TR_NODE, FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.TD_NODE, "video", "audio", "canvas", "details", "menu", "plaintext", "template", "article", "main", FlexmarkHtmlConverter.SVG_NODE, FlexmarkHtmlConverter.MATH_NODE, "center", "template", "dir", "applet", "marquee", "listing"};
    private static final String[] inlineTags = {"object", "base", "font", "tt", FlexmarkHtmlConverter.I_NODE, FlexmarkHtmlConverter.B_NODE, FlexmarkHtmlConverter.U_NODE, "big", "small", FlexmarkHtmlConverter.EM_NODE, FlexmarkHtmlConverter.STRONG_NODE, "dfn", FlexmarkHtmlConverter.CODE_NODE, "samp", "kbd", "var", "cite", FlexmarkHtmlConverter.ABBR_NODE, "time", "acronym", "mark", "ruby", "rt", "rp", "rtc", FlexmarkHtmlConverter.A_NODE, FlexmarkHtmlConverter.IMG_NODE, FlexmarkHtmlConverter.BR_NODE, "wbr", "map", "q", FlexmarkHtmlConverter.SUB_NODE, FlexmarkHtmlConverter.SUP_NODE, "bdo", "iframe", "embed", FlexmarkHtmlConverter.SPAN_NODE, FlexmarkHtmlConverter.INPUT_NODE, "select", "textarea", "label", "button", "optgroup", "option", "legend", "datalist", "keygen", "output", "progress", "meter", "area", "param", "source", "track", "summary", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track", "data", "bdi", "s", FlexmarkHtmlConverter.STRIKE_NODE, "nobr", "rb", "text", "mi", "mo", "msup", "mn", "mtext"};
    private static final String[] emptyTags = {"meta", "link", "base", "frame", FlexmarkHtmlConverter.IMG_NODE, FlexmarkHtmlConverter.BR_NODE, "wbr", "embed", FlexmarkHtmlConverter.HR_NODE, FlexmarkHtmlConverter.INPUT_NODE, "keygen", "col", "command", "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track"};
    private static final String[] formatAsInlineTags = {Attribute.TITLE_ATTR, FlexmarkHtmlConverter.A_NODE, FlexmarkHtmlConverter.P_NODE, FlexmarkHtmlConverter.H1_NODE, FlexmarkHtmlConverter.H2_NODE, FlexmarkHtmlConverter.H3_NODE, FlexmarkHtmlConverter.H4_NODE, FlexmarkHtmlConverter.H5_NODE, FlexmarkHtmlConverter.H6_NODE, FlexmarkHtmlConverter.PRE_NODE, "address", FlexmarkHtmlConverter.LI_NODE, FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.TD_NODE, "script", Attribute.STYLE_ATTR, FlexmarkHtmlConverter.INS_NODE, FlexmarkHtmlConverter.DEL_NODE, "s"};
    private static final String[] preserveWhitespaceTags = {FlexmarkHtmlConverter.PRE_NODE, "plaintext", Attribute.TITLE_ATTR, "textarea"};
    private static final String[] formListedTags = {"button", "fieldset", FlexmarkHtmlConverter.INPUT_NODE, "keygen", "object", "output", "select", "textarea"};
    private static final String[] formSubmitTags = {FlexmarkHtmlConverter.INPUT_NODE, "keygen", "object", "select", "textarea"};

    static {
        HashMap hashMap = new HashMap();
        namespaces = hashMap;
        hashMap.put(Parser.NamespaceMathml, new String[]{FlexmarkHtmlConverter.MATH_NODE, "mi", "mo", "msup", "mn", "mtext"});
        namespaces.put(Parser.NamespaceSvg, new String[]{FlexmarkHtmlConverter.SVG_NODE, "text"});
        setupTags(blockTags, tag -> {
            tag.isBlock = true;
            tag.formatAsBlock = true;
        });
        setupTags(inlineTags, tag2 -> {
            tag2.isBlock = false;
            tag2.formatAsBlock = false;
        });
        setupTags(emptyTags, tag3 -> {
            tag3.empty = true;
        });
        setupTags(formatAsInlineTags, tag4 -> {
            tag4.formatAsBlock = false;
        });
        setupTags(preserveWhitespaceTags, tag5 -> {
            tag5.preserveWhitespace = true;
        });
        setupTags(formListedTags, tag6 -> {
            tag6.formList = true;
        });
        setupTags(formSubmitTags, tag7 -> {
            tag7.formSubmit = true;
        });
        for (Map.Entry<String, String[]> entry : namespaces.entrySet()) {
            setupTags(entry.getValue(), tag8 -> {
                tag8.namespace = (String) entry.getKey();
            });
        }
    }

    private Tag(String str, String str2) {
        this.tagName = str;
        this.normalName = Normalizer.lowerCase(str);
        this.namespace = str2;
    }

    public String getName() {
        return this.tagName;
    }

    public String normalName() {
        return this.normalName;
    }

    public String namespace() {
        return this.namespace;
    }

    public static Tag valueOf(String str, String str2, ParseSettings parseSettings) {
        Validate.notEmpty(str);
        Validate.notNull(str2);
        Tag tag = Tags.get(str);
        if (tag != null && tag.namespace.equals(str2)) {
            return tag;
        }
        String normalizeTag = parseSettings.normalizeTag(str);
        Validate.notEmpty(normalizeTag);
        String lowerCase = Normalizer.lowerCase(normalizeTag);
        Tag tag2 = Tags.get(lowerCase);
        Tag tag3 = tag2;
        if (tag2 == null || !tag3.namespace.equals(str2)) {
            Tag tag4 = new Tag(normalizeTag, str2);
            tag4.isBlock = false;
            return tag4;
        }
        if (parseSettings.preserveTagCase() && !normalizeTag.equals(lowerCase)) {
            Tag m2550clone = tag3.m2550clone();
            tag3 = m2550clone;
            m2550clone.tagName = normalizeTag;
        }
        return tag3;
    }

    public static Tag valueOf(String str) {
        return valueOf(str, Parser.NamespaceHtml, ParseSettings.preserveCase);
    }

    public static Tag valueOf(String str, ParseSettings parseSettings) {
        return valueOf(str, Parser.NamespaceHtml, parseSettings);
    }

    public boolean isBlock() {
        return this.isBlock;
    }

    public boolean formatAsBlock() {
        return this.formatAsBlock;
    }

    public boolean isInline() {
        return !this.isBlock;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isSelfClosing() {
        return this.empty || this.selfClosing;
    }

    public boolean isKnownTag() {
        return Tags.containsKey(this.tagName);
    }

    public static boolean isKnownTag(String str) {
        return Tags.containsKey(str);
    }

    public boolean preserveWhitespace() {
        return this.preserveWhitespace;
    }

    public boolean isFormListed() {
        return this.formList;
    }

    public boolean isFormSubmittable() {
        return this.formSubmit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Tag setSelfClosing() {
        this.selfClosing = true;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) obj;
        return this.tagName.equals(tag.tagName) && this.empty == tag.empty && this.formatAsBlock == tag.formatAsBlock && this.isBlock == tag.isBlock && this.preserveWhitespace == tag.preserveWhitespace && this.selfClosing == tag.selfClosing && this.formList == tag.formList && this.formSubmit == tag.formSubmit;
    }

    public int hashCode() {
        return (((((((((((((this.tagName.hashCode() * 31) + (this.isBlock ? 1 : 0)) * 31) + (this.formatAsBlock ? 1 : 0)) * 31) + (this.empty ? 1 : 0)) * 31) + (this.selfClosing ? 1 : 0)) * 31) + (this.preserveWhitespace ? 1 : 0)) * 31) + (this.formList ? 1 : 0)) * 31) + (this.formSubmit ? 1 : 0);
    }

    public String toString() {
        return this.tagName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Tag m2550clone() {
        try {
            return (Tag) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setupTags(String[] strArr, Consumer<Tag> consumer) {
        for (String str : strArr) {
            Tag tag = Tags.get(str);
            Tag tag2 = tag;
            if (tag == null) {
                tag2 = new Tag(str, Parser.NamespaceHtml);
                Tags.put(tag2.tagName, tag2);
            }
            consumer.accept(tag2);
        }
    }
}
