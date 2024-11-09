package com.vladsch.flexmark.util.html.ui;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/ui/HtmlStyler.class */
public interface HtmlStyler<T> {
    T getStyleable(Object obj);

    String getStyle(T t);
}
