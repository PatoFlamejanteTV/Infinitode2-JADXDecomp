package com.vladsch.flexmark.ext.emoji.internal;

import com.vladsch.flexmark.ext.emoji.Emoji;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/internal/EmojiJiraRenderer.class */
public class EmojiJiraRenderer implements NodeRenderer {
    public static final HashMap<String, String> shortCutMap;

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        shortCutMap = hashMap;
        hashMap.put("smile", ":)");
        shortCutMap.put("frowning", ":(");
        shortCutMap.put("stuck_out_tongue", ":P");
        shortCutMap.put("grinning", ":D");
        shortCutMap.put("wink", ";)");
        shortCutMap.put("thumbsup", "(y)");
        shortCutMap.put("thumbsdown", "(n)");
        shortCutMap.put("information_source", "(i)");
        shortCutMap.put("white_check_mark", "(/)");
        shortCutMap.put("x", "(x)");
        shortCutMap.put("warning", "(!)");
        shortCutMap.put("heavy_plus_sign", "(+)");
        shortCutMap.put("heavy_minus_sign", "(-)");
        shortCutMap.put("question", "(?)");
        shortCutMap.put("bulb", "(on)");
        shortCutMap.put("star", "(*)");
        shortCutMap.put("triangular_flag_on_post", "(flag)");
        shortCutMap.put("crossed_flags", "(flagoff)");
    }

    public EmojiJiraRenderer(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(Emoji.class, this::render));
        return hashSet;
    }

    private void render(Emoji emoji, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String str = shortCutMap.get(emoji.getText().toString());
        if (str == null) {
            htmlWriter.text(":");
            nodeRendererContext.renderChildren(emoji);
            htmlWriter.text(":");
            return;
        }
        htmlWriter.raw((CharSequence) str);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/internal/EmojiJiraRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new EmojiJiraRenderer(dataHolder);
        }
    }
}
