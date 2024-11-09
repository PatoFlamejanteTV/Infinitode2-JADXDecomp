package com.vladsch.flexmark.util.html.ui;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.html.AttributeImpl;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.html.HtmlAppendableBase;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import java.awt.Font;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.plaf.FontUIResource;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/ui/HtmlBuilder.class */
public class HtmlBuilder extends HtmlAppendableBase<HtmlBuilder> {
    public static final HashMap<Class, HtmlStyler> stylerMap = new HashMap<>();

    public HtmlBuilder() {
        super(0, LineAppendable.F_PASS_THROUGH);
    }

    public HtmlBuilder(int i, int i2) {
        super(i, i2);
    }

    public HtmlBuilder closeAllTags() {
        while (!getOpenTags().isEmpty()) {
            closeTag((CharSequence) getOpenTags().peek());
        }
        return this;
    }

    public String toFinalizedString() {
        closeAllTags();
        return toString(0, 0);
    }

    public HtmlBuilder attr(Object... objArr) {
        for (Object obj : objArr) {
            if (obj instanceof Attribute) {
                super.attr((Attribute) obj);
                super.withAttr();
            } else {
                HtmlStyler htmlStyler = getHtmlStyler(obj);
                if (htmlStyler == null) {
                    throw new IllegalStateException("Don't know how to style " + obj.getClass().getName().substring(getClass().getPackage().getName().length() + 1));
                }
                String style = htmlStyler.getStyle(htmlStyler.getStyleable(obj));
                if (style != null && !style.isEmpty()) {
                    super.attr(AttributeImpl.of(Attribute.STYLE_ATTR, style));
                    super.withAttr();
                }
            }
        }
        return this;
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendableBase, com.vladsch.flexmark.util.html.HtmlAppendable
    public HtmlBuilder attr(CharSequence charSequence, CharSequence charSequence2) {
        super.withAttr();
        return (HtmlBuilder) super.attr(charSequence, charSequence2);
    }

    public HtmlBuilder style(CharSequence charSequence) {
        super.withAttr();
        return (HtmlBuilder) super.attr(Attribute.STYLE_ATTR, charSequence);
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendableBase, com.vladsch.flexmark.util.html.HtmlAppendable
    public HtmlBuilder attr(Attribute... attributeArr) {
        super.withAttr();
        return (HtmlBuilder) super.attr(attributeArr);
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendableBase, com.vladsch.flexmark.util.html.HtmlAppendable
    public HtmlBuilder attr(Attributes attributes) {
        super.withAttr();
        return (HtmlBuilder) super.attr(attributes);
    }

    public HtmlBuilder span() {
        return tag(FlexmarkHtmlConverter.SPAN_NODE, false);
    }

    public HtmlBuilder span(CharSequence charSequence) {
        tag(FlexmarkHtmlConverter.SPAN_NODE, false);
        text(charSequence);
        return closeSpan();
    }

    public HtmlBuilder span(boolean z, Runnable runnable) {
        return tag(FlexmarkHtmlConverter.SPAN_NODE, false, z, runnable);
    }

    public HtmlBuilder span(Runnable runnable) {
        return span(false, runnable);
    }

    public HtmlBuilder spanLine(Runnable runnable) {
        return span(true, runnable);
    }

    public HtmlBuilder closeSpan() {
        return closeTag(FlexmarkHtmlConverter.SPAN_NODE);
    }

    static {
        ColorStyler colorStyler = new ColorStyler();
        stylerMap.put(BackgroundColor.class, colorStyler);
        stylerMap.put(Color.class, colorStyler);
        stylerMap.put(java.awt.Color.class, colorStyler);
        FontStyler fontStyler = new FontStyler();
        stylerMap.put(Font.class, fontStyler);
        stylerMap.put(FontUIResource.class, fontStyler);
        stylerMap.put(FontStyle.class, new FontStyleStyler());
    }

    public static void addColorStylerClass(Class cls) {
        stylerMap.put(cls, stylerMap.get(Color.class));
    }

    public static HtmlStyler getHtmlStyler(Object obj) {
        HtmlStyler htmlStyler = stylerMap.get(obj.getClass());
        HtmlStyler htmlStyler2 = htmlStyler;
        if (htmlStyler != null) {
            return htmlStyler2;
        }
        Iterator<Class> it = stylerMap.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Class next = it.next();
            if (next.isAssignableFrom(obj.getClass())) {
                htmlStyler2 = stylerMap.get(next);
                break;
            }
        }
        if (htmlStyler2 != null) {
            stylerMap.put(obj.getClass(), htmlStyler2);
        }
        return htmlStyler2;
    }

    public static Attribute getAttribute(Object obj) {
        String style;
        HtmlStyler htmlStyler = getHtmlStyler(obj);
        if (htmlStyler != null && (style = htmlStyler.getStyle(htmlStyler.getStyleable(obj))) != null && !style.isEmpty()) {
            return AttributeImpl.of(Attribute.STYLE_ATTR, style);
        }
        return null;
    }

    public HtmlBuilder append(Object obj) {
        return (HtmlBuilder) super.append((CharSequence) String.valueOf(obj));
    }

    public HtmlBuilder append(String str) {
        return (HtmlBuilder) super.append((CharSequence) str);
    }

    public HtmlBuilder append(StringBuffer stringBuffer) {
        return (HtmlBuilder) super.append((CharSequence) stringBuffer.toString());
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendableBase, com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Appendable
    public HtmlBuilder append(CharSequence charSequence) {
        return (HtmlBuilder) super.append(charSequence);
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendableBase, com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Appendable
    public HtmlBuilder append(CharSequence charSequence, int i, int i2) {
        return (HtmlBuilder) super.append(charSequence, i, i2);
    }

    public HtmlBuilder append(char[] cArr) {
        return (HtmlBuilder) super.append((CharSequence) String.valueOf(cArr));
    }

    public HtmlBuilder append(char[] cArr, int i, int i2) {
        return (HtmlBuilder) super.append((CharSequence) String.valueOf(cArr, i, i2));
    }

    public HtmlBuilder append(boolean z) {
        return (HtmlBuilder) super.append((CharSequence) (z ? "true" : "false"));
    }

    @Override // com.vladsch.flexmark.util.html.HtmlAppendableBase, com.vladsch.flexmark.util.sequence.LineAppendable, java.lang.Appendable
    public HtmlBuilder append(char c) {
        return (HtmlBuilder) super.append(c);
    }

    public HtmlBuilder append(int i) {
        return (HtmlBuilder) super.append((CharSequence) String.valueOf(i));
    }

    public HtmlBuilder append(long j) {
        return (HtmlBuilder) super.append((CharSequence) String.valueOf(j));
    }

    public HtmlBuilder append(float f) {
        return (HtmlBuilder) super.append((CharSequence) String.valueOf(f));
    }

    public HtmlBuilder append(double d) {
        return (HtmlBuilder) super.append((CharSequence) String.valueOf(d));
    }
}
