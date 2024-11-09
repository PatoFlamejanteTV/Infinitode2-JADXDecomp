package com.vladsch.flexmark.html.renderer;

import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/DelegatingNodeRendererFactory.class */
public interface DelegatingNodeRendererFactory extends NodeRendererFactory {
    Set<Class<?>> getDelegates();
}
