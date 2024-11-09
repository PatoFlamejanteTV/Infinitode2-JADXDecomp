package com.vladsch.flexmark.ext.enumerated.reference.internal;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceBlock;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceLink;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceRendering;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceRepository;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceText;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferences;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.HeaderIdGenerator;
import com.vladsch.flexmark.html.renderer.HtmlIdGenerator;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html.renderer.PhasedNodeRenderer;
import com.vladsch.flexmark.html.renderer.RenderingPhase;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceNodeRenderer.class */
public class EnumeratedReferenceNodeRenderer implements PhasedNodeRenderer {
    private final EnumeratedReferenceOptions options;
    private EnumeratedReferences enumeratedOrdinals;
    private Runnable ordinalRunnable = null;
    private final HtmlIdGenerator headerIdGenerator = new HeaderIdGenerator.Factory().create();

    public EnumeratedReferenceNodeRenderer(DataHolder dataHolder) {
        this.options = new EnumeratedReferenceOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.PhasedNodeRenderer
    public Set<RenderingPhase> getRenderingPhases() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(RenderingPhase.HEAD_TOP);
        linkedHashSet.add(RenderingPhase.BODY_TOP);
        return linkedHashSet;
    }

    @Override // com.vladsch.flexmark.html.renderer.PhasedNodeRenderer
    public void renderDocument(NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter, Document document, RenderingPhase renderingPhase) {
        if (renderingPhase == RenderingPhase.HEAD_TOP) {
            this.headerIdGenerator.generateIds(document);
        } else if (renderingPhase == RenderingPhase.BODY_TOP) {
            this.enumeratedOrdinals = EnumeratedReferenceExtension.ENUMERATED_REFERENCE_ORDINALS.get(document);
        }
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(EnumeratedReferenceText.class, this::render));
        hashSet.add(new NodeRenderingHandler(EnumeratedReferenceLink.class, this::render));
        hashSet.add(new NodeRenderingHandler(EnumeratedReferenceBlock.class, this::render));
        return hashSet;
    }

    private void render(final EnumeratedReferenceLink enumeratedReferenceLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        final String obj = enumeratedReferenceLink.getText().toString();
        if (!obj.isEmpty()) {
            this.enumeratedOrdinals.renderReferenceOrdinals(obj, new OrdinalRenderer(this, nodeRendererContext, htmlWriter) { // from class: com.vladsch.flexmark.ext.enumerated.reference.internal.EnumeratedReferenceNodeRenderer.1
                @Override // com.vladsch.flexmark.ext.enumerated.reference.internal.EnumeratedReferenceNodeRenderer.OrdinalRenderer, com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
                public void startRendering(EnumeratedReferenceRendering[] enumeratedReferenceRenderingArr) {
                    this.html.withAttr().attr((CharSequence) "href", (CharSequence) ("#" + obj)).attr((CharSequence) Attribute.TITLE_ATTR, (CharSequence) new EnumRefTextCollectingVisitor().collectAndGetText(enumeratedReferenceLink.getChars().getBaseSequence(), enumeratedReferenceRenderingArr, null)).tag((CharSequence) FlexmarkHtmlConverter.A_NODE);
                }

                @Override // com.vladsch.flexmark.ext.enumerated.reference.internal.EnumeratedReferenceNodeRenderer.OrdinalRenderer, com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
                public void endRendering() {
                    this.html.tag((CharSequence) "/a");
                }
            });
        } else if (this.ordinalRunnable != null) {
            this.ordinalRunnable.run();
        }
    }

    private void render(EnumeratedReferenceText enumeratedReferenceText, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String obj = enumeratedReferenceText.getText().toString();
        String str = obj;
        if (obj.isEmpty()) {
            if (this.ordinalRunnable != null) {
                this.ordinalRunnable.run();
                return;
            }
            return;
        }
        String type = EnumeratedReferenceRepository.getType(str.toString());
        if (type.isEmpty() || str.equals(type + ":")) {
            Node ancestorOfType = enumeratedReferenceText.getAncestorOfType(Heading.class);
            if (ancestorOfType instanceof Heading) {
                str = (type.isEmpty() ? str : type) + ":" + this.headerIdGenerator.getId(ancestorOfType);
            }
        }
        this.enumeratedOrdinals.renderReferenceOrdinals(str, new OrdinalRenderer(this, nodeRendererContext, htmlWriter));
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceNodeRenderer$OrdinalRenderer.class */
    private static class OrdinalRenderer implements EnumeratedOrdinalRenderer {
        final EnumeratedReferenceNodeRenderer renderer;
        final NodeRendererContext context;
        final HtmlWriter html;

        public OrdinalRenderer(EnumeratedReferenceNodeRenderer enumeratedReferenceNodeRenderer, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
            this.renderer = enumeratedReferenceNodeRenderer;
            this.context = nodeRendererContext;
            this.html = htmlWriter;
        }

        @Override // com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
        public void startRendering(EnumeratedReferenceRendering[] enumeratedReferenceRenderingArr) {
        }

        @Override // com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
        public void setEnumOrdinalRunnable(Runnable runnable) {
            this.renderer.ordinalRunnable = runnable;
        }

        @Override // com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
        public Runnable getEnumOrdinalRunnable() {
            return this.renderer.ordinalRunnable;
        }

        @Override // com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
        public void render(int i, EnumeratedReferenceBlock enumeratedReferenceBlock, String str, boolean z) {
            Runnable runnable = this.renderer.ordinalRunnable;
            if (enumeratedReferenceBlock != null) {
                this.renderer.ordinalRunnable = () -> {
                    if (runnable != null) {
                        runnable.run();
                    }
                    this.html.text((CharSequence) String.valueOf(i));
                    if (z) {
                        this.html.text((CharSequence) ".");
                    }
                };
                this.context.renderChildren(enumeratedReferenceBlock);
                return;
            }
            this.html.text((CharSequence) (str + SequenceUtils.SPACE));
            if (runnable != null) {
                runnable.run();
            }
            this.html.text((CharSequence) String.valueOf(i));
            if (z) {
                this.html.text((CharSequence) ".");
            }
        }

        @Override // com.vladsch.flexmark.ext.enumerated.reference.EnumeratedOrdinalRenderer
        public void endRendering() {
        }
    }

    private void render(EnumeratedReferenceBlock enumeratedReferenceBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new EnumeratedReferenceNodeRenderer(dataHolder);
        }
    }
}
