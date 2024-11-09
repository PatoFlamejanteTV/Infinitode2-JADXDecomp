package com.vladsch.flexmark.html2md.converter;

import com.vladsch.flexmark.html2md.converter.internal.HtmlConverterCoreNodeRenderer;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/HtmlConverterCoreNodeRendererFactory.class */
public class HtmlConverterCoreNodeRendererFactory implements HtmlNodeRendererFactory {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeRendererFactory, java.util.function.Function
    public HtmlNodeRenderer apply(DataHolder dataHolder) {
        return new HtmlConverterCoreNodeRenderer(dataHolder);
    }
}
