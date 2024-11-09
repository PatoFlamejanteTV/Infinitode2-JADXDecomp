package com.vladsch.flexmark.ext.xwiki.macros.internal;

import com.vladsch.flexmark.ext.xwiki.macros.MacroExtension;
import com.vladsch.flexmark.util.data.DataHolder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/internal/MacroOptions.class */
public class MacroOptions {
    public final boolean enableInlineMacros;
    public final boolean enableBlockMacros;
    public final boolean enableRendering;

    public MacroOptions(DataHolder dataHolder) {
        this.enableInlineMacros = MacroExtension.ENABLE_INLINE_MACROS.get(dataHolder).booleanValue();
        this.enableBlockMacros = MacroExtension.ENABLE_BLOCK_MACROS.get(dataHolder).booleanValue();
        this.enableRendering = MacroExtension.ENABLE_RENDERING.get(dataHolder).booleanValue();
    }
}
