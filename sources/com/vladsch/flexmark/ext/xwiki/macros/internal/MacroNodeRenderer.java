package com.vladsch.flexmark.ext.xwiki.macros.internal;

import com.vladsch.flexmark.ext.xwiki.macros.Macro;
import com.vladsch.flexmark.ext.xwiki.macros.MacroAttribute;
import com.vladsch.flexmark.ext.xwiki.macros.MacroBlock;
import com.vladsch.flexmark.ext.xwiki.macros.MacroClose;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/internal/MacroNodeRenderer.class */
public class MacroNodeRenderer implements NodeRenderer {
    private final MacroOptions options;

    public MacroNodeRenderer(DataHolder dataHolder) {
        this.options = new MacroOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(Macro.class, this::render));
        hashSet.add(new NodeRenderingHandler(MacroAttribute.class, this::render));
        hashSet.add(new NodeRenderingHandler(MacroClose.class, this::render));
        hashSet.add(new NodeRenderingHandler(MacroBlock.class, this::render));
        return hashSet;
    }

    private void render(Macro macro, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.options.enableRendering) {
            htmlWriter.text((CharSequence) Node.spanningChars(macro.getOpeningMarker(), macro.getClosingMarker()));
            nodeRendererContext.renderChildren(macro);
        }
    }

    private void render(MacroAttribute macroAttribute, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    private void render(MacroClose macroClose, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.options.enableRendering) {
            htmlWriter.text((CharSequence) Node.spanningChars(macroClose.getOpeningMarker(), macroClose.getClosingMarker()));
        }
    }

    private void render(MacroBlock macroBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.options.enableRendering) {
            nodeRendererContext.renderChildren(macroBlock);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/internal/MacroNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new MacroNodeRenderer(dataHolder);
        }
    }
}
