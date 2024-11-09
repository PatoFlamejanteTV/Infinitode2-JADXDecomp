package com.vladsch.flexmark.ext.admonition.internal;

import com.vladsch.flexmark.ext.admonition.AdmonitionBlock;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html.renderer.PhasedNodeRenderer;
import com.vladsch.flexmark.html.renderer.RenderingPhase;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.jsoup.parser.Parser;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/internal/AdmonitionNodeRenderer.class */
public class AdmonitionNodeRenderer implements PhasedNodeRenderer {
    public static AttributablePart ADMONITION_SVG_OBJECT_PART = new AttributablePart("ADMONITION_SVG_OBJECT_PART");
    public static AttributablePart ADMONITION_HEADING_PART = new AttributablePart("ADMONITION_HEADING_PART");
    public static AttributablePart ADMONITION_ICON_PART = new AttributablePart("ADMONITION_ICON_PART");
    public static AttributablePart ADMONITION_TITLE_PART = new AttributablePart("ADMONITION_TITLE_PART");
    public static AttributablePart ADMONITION_BODY_PART = new AttributablePart("ADMONITION_BODY_PART");
    private final AdmonitionOptions options;

    public AdmonitionNodeRenderer(DataHolder dataHolder) {
        this.options = new AdmonitionOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(AdmonitionBlock.class, this::render));
        return hashSet;
    }

    @Override // com.vladsch.flexmark.html.renderer.PhasedNodeRenderer
    public Set<RenderingPhase> getRenderingPhases() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(RenderingPhase.BODY_TOP);
        return linkedHashSet;
    }

    @Override // com.vladsch.flexmark.html.renderer.PhasedNodeRenderer
    public void renderDocument(NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter, Document document, RenderingPhase renderingPhase) {
        if (renderingPhase == RenderingPhase.BODY_TOP) {
            HashSet hashSet = new HashSet();
            Iterator<String> it = new AdmonitionCollectingVisitor().collectAndGetQualifiers(document).iterator();
            while (it.hasNext()) {
                String str = this.options.qualifierTypeMap.get(it.next());
                String str2 = str;
                if (str == null) {
                    str2 = this.options.unresolvedQualifier;
                }
                hashSet.add(str2);
            }
            if (!hashSet.isEmpty()) {
                htmlWriter.line().attr((CharSequence) "xmlns", (CharSequence) Parser.NamespaceSvg).attr((CharSequence) Attribute.CLASS_ATTR, (CharSequence) "adm-hidden").withAttr(ADMONITION_SVG_OBJECT_PART).tag((CharSequence) FlexmarkHtmlConverter.SVG_NODE).indent().line();
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    String str3 = (String) it2.next();
                    String str4 = this.options.typeSvgMap.get(str3);
                    if (str4 != null && !str4.isEmpty()) {
                        htmlWriter.raw("<symbol id=\"adm-").raw((CharSequence) str3).raw((CharSequence) "\">").indent().line().raw((CharSequence) str4).line().unIndent().raw((CharSequence) "</symbol>").line();
                    }
                }
                htmlWriter.unIndent().closeTag((CharSequence) FlexmarkHtmlConverter.SVG_NODE).line();
            }
        }
    }

    private void render(AdmonitionBlock admonitionBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String obj;
        String str;
        String lowerCase = admonitionBlock.getInfo().toString().toLowerCase();
        String str2 = this.options.qualifierTypeMap.get(lowerCase);
        String str3 = str2;
        if (str2 == null) {
            str3 = this.options.unresolvedQualifier;
        }
        if (admonitionBlock.getTitle().isNull()) {
            String str4 = this.options.qualifierTitleMap.get(lowerCase);
            obj = str4;
            if (str4 == null) {
                obj = lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
            }
        } else {
            obj = admonitionBlock.getTitle().toString();
        }
        if (admonitionBlock.getOpeningMarker().equals("???")) {
            str = " adm-collapsed";
        } else {
            str = admonitionBlock.getOpeningMarker().equals("???+") ? "adm-open" : null;
        }
        if (obj.isEmpty()) {
            htmlWriter.srcPos(admonitionBlock.getChars()).withAttr().attr(Attribute.CLASS_ATTR, "adm-block").attr((CharSequence) Attribute.CLASS_ATTR, (CharSequence) ("adm-" + str3)).tag((CharSequence) FlexmarkHtmlConverter.DIV_NODE, false).line();
            htmlWriter.attr(Attribute.CLASS_ATTR, "adm-body").withAttr(ADMONITION_BODY_PART).tag((CharSequence) FlexmarkHtmlConverter.DIV_NODE).indent().line();
            nodeRendererContext.renderChildren(admonitionBlock);
            htmlWriter.unIndent().closeTag((CharSequence) FlexmarkHtmlConverter.DIV_NODE).line();
            htmlWriter.closeTag(FlexmarkHtmlConverter.DIV_NODE).line();
            return;
        }
        htmlWriter.srcPos(admonitionBlock.getChars()).attr(Attribute.CLASS_ATTR, "adm-block").attr((CharSequence) Attribute.CLASS_ATTR, (CharSequence) ("adm-" + str3));
        if (str != null) {
            htmlWriter.attr(Attribute.CLASS_ATTR, str).attr((CharSequence) Attribute.CLASS_ATTR, (CharSequence) ("adm-" + str3));
        }
        htmlWriter.withAttr().tag(FlexmarkHtmlConverter.DIV_NODE, false).line();
        htmlWriter.attr(Attribute.CLASS_ATTR, "adm-heading").withAttr(ADMONITION_HEADING_PART).tag((CharSequence) FlexmarkHtmlConverter.DIV_NODE).line();
        htmlWriter.attr(Attribute.CLASS_ATTR, "adm-icon").withAttr(ADMONITION_ICON_PART).tag((CharSequence) FlexmarkHtmlConverter.SVG_NODE).raw((CharSequence) "<use xlink:href=\"#adm-").raw((CharSequence) str3).raw((CharSequence) "\" />").closeTag((CharSequence) FlexmarkHtmlConverter.SVG_NODE);
        htmlWriter.withAttr(ADMONITION_TITLE_PART).tag(FlexmarkHtmlConverter.SPAN_NODE).text((CharSequence) obj).closeTag((CharSequence) FlexmarkHtmlConverter.SPAN_NODE).line();
        htmlWriter.closeTag(FlexmarkHtmlConverter.DIV_NODE).line();
        htmlWriter.attr(Attribute.CLASS_ATTR, "adm-body").withAttr(ADMONITION_BODY_PART).withCondIndent().tagLine((CharSequence) FlexmarkHtmlConverter.DIV_NODE, () -> {
            nodeRendererContext.renderChildren(admonitionBlock);
        });
        htmlWriter.closeTag(FlexmarkHtmlConverter.DIV_NODE).line();
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/internal/AdmonitionNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new AdmonitionNodeRenderer(dataHolder);
        }
    }
}
