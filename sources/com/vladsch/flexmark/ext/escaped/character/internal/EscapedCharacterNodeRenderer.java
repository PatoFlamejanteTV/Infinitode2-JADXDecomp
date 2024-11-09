package com.vladsch.flexmark.ext.escaped.character.internal;

import com.vladsch.flexmark.ext.escaped.character.EscapedCharacter;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/escaped/character/internal/EscapedCharacterNodeRenderer.class */
public class EscapedCharacterNodeRenderer implements NodeRenderer {
    private final EscapedCharacterOptions options;

    public EscapedCharacterNodeRenderer(DataHolder dataHolder) {
        this.options = new EscapedCharacterOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(EscapedCharacter.class, this::render));
        return hashSet;
    }

    private void render(EscapedCharacter escapedCharacter, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.text((CharSequence) escapedCharacter.getChars().unescape());
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/escaped/character/internal/EscapedCharacterNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new EscapedCharacterNodeRenderer(dataHolder);
        }
    }
}
