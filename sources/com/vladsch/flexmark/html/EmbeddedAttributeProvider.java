package com.vladsch.flexmark.html;

import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.LinkResolverContext;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.html.MutableAttributes;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/EmbeddedAttributeProvider.class */
public class EmbeddedAttributeProvider implements AttributeProvider {
    public static final IndependentAttributeProviderFactory Factory = new IndependentAttributeProviderFactory() { // from class: com.vladsch.flexmark.html.EmbeddedAttributeProvider.1
        @Override // com.vladsch.flexmark.html.AttributeProviderFactory, java.util.function.Function
        public final AttributeProvider apply(LinkResolverContext linkResolverContext) {
            return new EmbeddedAttributeProvider();
        }
    };

    EmbeddedAttributeProvider() {
    }

    @Override // com.vladsch.flexmark.html.AttributeProvider
    public void setAttributes(Node node, AttributablePart attributablePart, MutableAttributes mutableAttributes) {
        if (attributablePart == AttributablePart.NODE) {
            Node childOfType = node.getChildOfType(EmbeddedNodeAttributes.class);
            if (childOfType instanceof EmbeddedNodeAttributes) {
                mutableAttributes.addValues(((EmbeddedNodeAttributes) childOfType).attributes);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/EmbeddedAttributeProvider$EmbeddedNodeAttributes.class */
    public static class EmbeddedNodeAttributes extends Node {
        final Attributes attributes;

        public EmbeddedNodeAttributes(Node node, Attributes attributes) {
            super(node.getChars().subSequence(0, 0));
            this.attributes = attributes;
        }

        @Override // com.vladsch.flexmark.util.ast.Node
        public BasedSequence[] getSegments() {
            return Node.EMPTY_SEGMENTS;
        }

        @Override // com.vladsch.flexmark.util.ast.Node
        public void astString(StringBuilder sb, boolean z) {
            sb.append(EmbeddedNodeAttributes.class.getSimpleName());
            sb.append("[").append(getStartOffset()).append(", ").append(getEndOffset()).append("]");
            sb.append(", attributes: ").append(this.attributes.toString());
            if (z) {
                getAstExtra(sb);
            }
        }

        @Override // com.vladsch.flexmark.util.ast.Node
        public void astExtraChars(StringBuilder sb) {
        }
    }
}
