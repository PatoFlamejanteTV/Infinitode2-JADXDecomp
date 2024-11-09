package com.vladsch.flexmark.ext.macros.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ext.macros.MacroDefinitionBlock;
import com.vladsch.flexmark.ext.macros.MacroReference;
import com.vladsch.flexmark.ext.macros.MacrosExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
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
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.data.DataHolder;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacrosNodeRenderer.class */
public class MacrosNodeRenderer implements PhasedNodeRenderer {
    private final MacrosOptions options;
    final MacroDefinitionRepository repository;
    private final boolean recheckUndefinedReferences;

    public MacrosNodeRenderer(DataHolder dataHolder) {
        this.options = new MacrosOptions(dataHolder);
        this.repository = MacrosExtension.MACRO_DEFINITIONS.get(dataHolder);
        this.recheckUndefinedReferences = HtmlRenderer.RECHECK_UNDEFINED_REFERENCES.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(MacroReference.class, this::render));
        hashSet.add(new NodeRenderingHandler(MacroDefinitionBlock.class, this::render));
        return hashSet;
    }

    @Override // com.vladsch.flexmark.html.renderer.PhasedNodeRenderer
    public Set<RenderingPhase> getRenderingPhases() {
        HashSet hashSet = new HashSet();
        hashSet.add(RenderingPhase.BODY_TOP);
        return hashSet;
    }

    @Override // com.vladsch.flexmark.html.renderer.PhasedNodeRenderer
    public void renderDocument(NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter, Document document, RenderingPhase renderingPhase) {
        if (renderingPhase == RenderingPhase.BODY_TOP && this.recheckUndefinedReferences) {
            boolean[] zArr = {false};
            new NodeVisitor(new VisitHandler(MacroReference.class, macroReference -> {
                MacroDefinitionBlock macroDefinitionBlock;
                if (!macroReference.isDefined() && (macroDefinitionBlock = macroReference.getMacroDefinitionBlock(this.repository)) != null) {
                    this.repository.addMacrosReference(macroDefinitionBlock, macroReference);
                    macroReference.setMacroDefinitionBlock(macroDefinitionBlock);
                    zArr[0] = true;
                }
            })).visit(document);
            if (zArr[0]) {
                this.repository.resolveMacrosOrdinals();
            }
        }
    }

    private void render(MacroReference macroReference, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        MacroDefinitionBlock macroDefinitionBlock = this.repository.get(this.repository.normalizeKey(macroReference.getText()));
        if (macroDefinitionBlock != null) {
            if (macroDefinitionBlock.hasChildren() && !macroDefinitionBlock.isInExpansion()) {
                try {
                    macroDefinitionBlock.setInExpansion(true);
                    Node firstChild = macroDefinitionBlock.getFirstChild();
                    if ((firstChild instanceof Paragraph) && firstChild == macroDefinitionBlock.getLastChild()) {
                        if (this.options.sourceWrapMacroReferences) {
                            htmlWriter.srcPos(macroReference.getChars()).withAttr(AttributablePart.NODE_POSITION).tag(FlexmarkHtmlConverter.SPAN_NODE);
                            nodeRendererContext.renderChildren(firstChild);
                            htmlWriter.tag("/span");
                        } else {
                            nodeRendererContext.renderChildren(firstChild);
                        }
                    } else if (this.options.sourceWrapMacroReferences) {
                        htmlWriter.srcPos(macroReference.getChars()).withAttr(AttributablePart.NODE_POSITION).tag(FlexmarkHtmlConverter.DIV_NODE).indent().line();
                        nodeRendererContext.renderChildren(macroDefinitionBlock);
                        htmlWriter.unIndent().tag((CharSequence) "/div");
                    } else {
                        nodeRendererContext.renderChildren(macroDefinitionBlock);
                    }
                    return;
                } finally {
                    macroDefinitionBlock.setInExpansion(false);
                }
            }
            return;
        }
        htmlWriter.text((CharSequence) macroReference.getChars());
    }

    private void render(MacroDefinitionBlock macroDefinitionBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacrosNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new MacrosNodeRenderer(dataHolder);
        }
    }
}
