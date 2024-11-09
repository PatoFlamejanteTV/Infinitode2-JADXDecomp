package com.vladsch.flexmark.html2md.converter;

import com.vladsch.flexmark.util.data.DataHolder;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/HtmlNodeRendererFactory.class */
public interface HtmlNodeRendererFactory extends Function<DataHolder, HtmlNodeRenderer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.function.Function
    HtmlNodeRenderer apply(DataHolder dataHolder);
}
