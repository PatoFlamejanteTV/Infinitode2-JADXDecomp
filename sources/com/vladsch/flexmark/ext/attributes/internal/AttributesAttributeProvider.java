package com.vladsch.flexmark.ext.attributes.internal;

import com.vladsch.flexmark.ast.AnchorRefTarget;
import com.vladsch.flexmark.ext.attributes.AttributeNode;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.attributes.AttributesNode;
import com.vladsch.flexmark.html.AttributeProvider;
import com.vladsch.flexmark.html.IndependentAttributeProviderFactory;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.CoreNodeRenderer;
import com.vladsch.flexmark.html.renderer.LinkResolverContext;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.html.MutableAttributes;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesAttributeProvider.class */
public class AttributesAttributeProvider implements AttributeProvider {
    private final NodeAttributeRepository nodeAttributeRepository;
    private final AttributesOptions attributeOptions;

    public AttributesAttributeProvider(LinkResolverContext linkResolverContext) {
        DataHolder options = linkResolverContext.getOptions();
        this.attributeOptions = new AttributesOptions(options);
        this.nodeAttributeRepository = AttributesExtension.NODE_ATTRIBUTES.get(options);
    }

    @Override // com.vladsch.flexmark.html.AttributeProvider
    public void setAttributes(Node node, AttributablePart attributablePart, MutableAttributes mutableAttributes) {
        if (attributablePart == CoreNodeRenderer.CODE_CONTENT) {
            if (!this.attributeOptions.fencedCodeAddAttributes.addToCode) {
                return;
            }
        } else if (!this.attributeOptions.fencedCodeAddAttributes.addToPre) {
            return;
        }
        ArrayList<AttributesNode> arrayList = this.nodeAttributeRepository.get((Object) node);
        if (arrayList != null) {
            Iterator<AttributesNode> it = arrayList.iterator();
            while (it.hasNext()) {
                ReversiblePeekingIterator<Node> it2 = it.next().getChildren().iterator();
                while (it2.hasNext()) {
                    Node next = it2.next();
                    if (next instanceof AttributeNode) {
                        AttributeNode attributeNode = (AttributeNode) next;
                        if (!attributeNode.isImplicitName()) {
                            BasedSequence name = attributeNode.getName();
                            if (name.isNotNull() && !name.isBlank()) {
                                if (!name.equals(Attribute.CLASS_ATTR)) {
                                    mutableAttributes.remove(name);
                                }
                                mutableAttributes.addValue(name, attributeNode.getValue());
                            }
                        } else if (attributeNode.isClass()) {
                            mutableAttributes.addValue(Attribute.CLASS_ATTR, attributeNode.getValue());
                        } else if (attributeNode.isId()) {
                            if (!(node instanceof AnchorRefTarget)) {
                                mutableAttributes.remove(Attribute.ID_ATTR);
                                mutableAttributes.addValue(Attribute.ID_ATTR, attributeNode.getValue());
                            }
                        } else {
                            throw new IllegalStateException("Implicit attribute yet not class or id");
                        }
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesAttributeProvider$Factory.class */
    public static class Factory extends IndependentAttributeProviderFactory {
        @Override // com.vladsch.flexmark.html.AttributeProviderFactory, java.util.function.Function
        public AttributeProvider apply(LinkResolverContext linkResolverContext) {
            return new AttributesAttributeProvider(linkResolverContext);
        }
    }
}
