package com.vladsch.flexmark.util.html.ui;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/ui/HtmlStylerBase.class */
public abstract class HtmlStylerBase<T> implements HtmlStyler<T> {
    @Override // com.vladsch.flexmark.util.html.ui.HtmlStyler
    public abstract String getStyle(T t);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vladsch.flexmark.util.html.ui.HtmlStyler
    public T getStyleable(Object obj) {
        return obj;
    }
}
