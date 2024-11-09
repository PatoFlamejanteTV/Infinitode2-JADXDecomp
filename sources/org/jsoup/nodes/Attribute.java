package org.jsoup.nodes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Range;

/* loaded from: infinitode-2.jar:org/jsoup/nodes/Attribute.class */
public class Attribute implements Cloneable, Map.Entry<String, String> {
    private String key;
    private String val;
    Attributes parent;
    private static final String[] booleanAttributes = {"allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", "open", "readonly", "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};
    private static final Pattern xmlKeyValid = Pattern.compile("[a-zA-Z_:][-a-zA-Z0-9_:.]*");
    private static final Pattern xmlKeyReplace = Pattern.compile("[^-a-zA-Z0-9_:.]");
    private static final Pattern htmlKeyValid = Pattern.compile("[^\\x00-\\x1f\\x7f-\\x9f \"'/=]+");
    private static final Pattern htmlKeyReplace = Pattern.compile("[\\x00-\\x1f\\x7f-\\x9f \"'/=]");

    public Attribute(String str, String str2) {
        this(str, str2, null);
    }

    public Attribute(String str, String str2, Attributes attributes) {
        Validate.notNull(str);
        String trim = str.trim();
        Validate.notEmpty(trim);
        this.key = trim;
        this.val = str2;
        this.parent = attributes;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map.Entry
    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        int indexOfKey;
        Validate.notNull(str);
        String trim = str.trim();
        Validate.notEmpty(trim);
        if (this.parent != null && (indexOfKey = this.parent.indexOfKey(this.key)) != -1) {
            String str2 = this.parent.keys[indexOfKey];
            this.parent.keys[indexOfKey] = trim;
            Map<String, Range.AttributeRange> ranges = this.parent.getRanges();
            if (ranges != null) {
                ranges.put(trim, ranges.remove(str2));
            }
        }
        this.key = trim;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map.Entry
    public String getValue() {
        return Attributes.checkNotNull(this.val);
    }

    public boolean hasDeclaredValue() {
        return this.val != null;
    }

    @Override // java.util.Map.Entry
    public String setValue(String str) {
        int indexOfKey;
        String str2 = this.val;
        if (this.parent != null && (indexOfKey = this.parent.indexOfKey(this.key)) != -1) {
            str2 = this.parent.get(this.key);
            this.parent.vals[indexOfKey] = str;
        }
        this.val = str;
        return Attributes.checkNotNull(str2);
    }

    public String html() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        try {
            html(borrowBuilder, new Document("").outputSettings());
            return StringUtil.releaseBuilder(borrowBuilder);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public Range.AttributeRange sourceRange() {
        return this.parent == null ? Range.AttributeRange.UntrackedAttr : this.parent.sourceRange(this.key);
    }

    protected void html(Appendable appendable, Document.OutputSettings outputSettings) {
        html(this.key, this.val, appendable, outputSettings);
    }

    protected static void html(String str, String str2, Appendable appendable, Document.OutputSettings outputSettings) {
        String validKey = getValidKey(str, outputSettings.syntax());
        if (validKey == null) {
            return;
        }
        htmlNoValidate(validKey, str2, appendable, outputSettings);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void htmlNoValidate(String str, String str2, Appendable appendable, Document.OutputSettings outputSettings) {
        appendable.append(str);
        if (!shouldCollapseAttribute(str, str2, outputSettings)) {
            appendable.append("=\"");
            Entities.escape(appendable, Attributes.checkNotNull(str2), outputSettings, true, false, false, false);
            appendable.append('\"');
        }
    }

    public static String getValidKey(String str, Document.OutputSettings.Syntax syntax) {
        if (syntax == Document.OutputSettings.Syntax.xml && !xmlKeyValid.matcher(str).matches()) {
            String replaceAll = xmlKeyReplace.matcher(str).replaceAll("");
            if (xmlKeyValid.matcher(replaceAll).matches()) {
                return replaceAll;
            }
            return null;
        }
        if (syntax == Document.OutputSettings.Syntax.html && !htmlKeyValid.matcher(str).matches()) {
            String replaceAll2 = htmlKeyReplace.matcher(str).replaceAll("");
            if (htmlKeyValid.matcher(replaceAll2).matches()) {
                return replaceAll2;
            }
            return null;
        }
        return str;
    }

    public String toString() {
        return html();
    }

    public static Attribute createFromEncoded(String str, String str2) {
        return new Attribute(str, Entities.unescape(str2, true), null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDataAttribute() {
        return isDataAttribute(this.key);
    }

    protected static boolean isDataAttribute(String str) {
        return str.startsWith("data-") && str.length() > 5;
    }

    protected final boolean shouldCollapseAttribute(Document.OutputSettings outputSettings) {
        return shouldCollapseAttribute(this.key, this.val, outputSettings);
    }

    protected static boolean shouldCollapseAttribute(String str, String str2, Document.OutputSettings outputSettings) {
        if (outputSettings.syntax() != Document.OutputSettings.Syntax.html) {
            return false;
        }
        if (str2 != null) {
            return (str2.isEmpty() || str2.equalsIgnoreCase(str)) && isBooleanAttribute(str);
        }
        return true;
    }

    public static boolean isBooleanAttribute(String str) {
        return Arrays.binarySearch(booleanAttributes, Normalizer.lowerCase(str)) >= 0;
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) obj;
        if (this.key != null) {
            if (!this.key.equals(attribute.key)) {
                return false;
            }
        } else if (attribute.key != null) {
            return false;
        }
        return this.val != null ? this.val.equals(attribute.val) : attribute.val == null;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return ((this.key != null ? this.key.hashCode() : 0) * 31) + (this.val != null ? this.val.hashCode() : 0);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Attribute m2528clone() {
        try {
            return (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
