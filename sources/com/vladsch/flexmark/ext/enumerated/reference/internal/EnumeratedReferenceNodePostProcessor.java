package com.vladsch.flexmark.ext.enumerated.reference.internal;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ext.attributes.AttributeNode;
import com.vladsch.flexmark.ext.attributes.AttributesNode;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceRepository;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceText;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferences;
import com.vladsch.flexmark.html.renderer.HeaderIdGenerator;
import com.vladsch.flexmark.html.renderer.HtmlIdGenerator;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceNodePostProcessor.class */
public class EnumeratedReferenceNodePostProcessor extends NodePostProcessor {
    private final EnumeratedReferences enumeratedReferences;
    private final HtmlIdGenerator headerIdGenerator = new HeaderIdGenerator.Factory().create();

    public EnumeratedReferenceNodePostProcessor(Document document) {
        this.enumeratedReferences = EnumeratedReferenceExtension.ENUMERATED_REFERENCE_ORDINALS.get(document);
        this.headerIdGenerator.generateIds(document);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vladsch.flexmark.parser.PostProcessor
    public void process(NodeTracker nodeTracker, Node node) {
        BasedSequence basedSequence;
        if (node instanceof AttributesNode) {
            ReversiblePeekingIterator<Node> it = ((AttributesNode) node).getChildren().iterator();
            while (it.hasNext()) {
                Node next = it.next();
                if ((next instanceof AttributeNode) && ((AttributeNode) next).isId()) {
                    this.enumeratedReferences.add(((AttributeNode) next).getValue().toString());
                    return;
                }
            }
            return;
        }
        if (node instanceof Heading) {
            ReversiblePeekingIterator<Node> it2 = node.getChildren().iterator();
            while (it2.hasNext()) {
                Node next2 = it2.next();
                if (next2 instanceof EnumeratedReferenceText) {
                    BasedSequence text = ((EnumeratedReferenceText) next2).getText();
                    String type = EnumeratedReferenceRepository.getType(text.toString());
                    if (type.isEmpty() || text.equals(type + ":")) {
                        StringBuilder sb = new StringBuilder();
                        if (type.isEmpty()) {
                            basedSequence = text;
                        } else {
                            basedSequence = type;
                        }
                        this.enumeratedReferences.add(sb.append((Object) basedSequence).append(":").append(this.headerIdGenerator.getId(node)).toString());
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceNodePostProcessor$Factory.class */
    public static class Factory extends NodePostProcessorFactory {
        public Factory() {
            super(false);
            addNodes(AttributesNode.class, Heading.class);
        }

        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.parser.PostProcessorFactory, java.util.function.Function
        public NodePostProcessor apply(Document document) {
            return new EnumeratedReferenceNodePostProcessor(document);
        }
    }
}
