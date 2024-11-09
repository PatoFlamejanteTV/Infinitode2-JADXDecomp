package com.vladsch.flexmark.html;

import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.misc.Extension;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/RendererExtension.class */
public interface RendererExtension extends Extension {
    void rendererOptions(MutableDataHolder mutableDataHolder);

    void extend(RendererBuilder rendererBuilder, String str);
}
