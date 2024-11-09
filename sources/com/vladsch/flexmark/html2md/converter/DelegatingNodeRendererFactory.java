package com.vladsch.flexmark.html2md.converter;

import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/DelegatingNodeRendererFactory.class */
public interface DelegatingNodeRendererFactory extends HtmlNodeRendererFactory {
    Set<Class<?>> getDelegates();
}
