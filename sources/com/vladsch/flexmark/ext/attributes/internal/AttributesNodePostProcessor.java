package com.vladsch.flexmark.ext.attributes.internal;

import com.vladsch.flexmark.ast.AnchorRefTarget;
import com.vladsch.flexmark.ast.FencedCodeBlock;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.ParagraphItemContainer;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.TextBase;
import com.vladsch.flexmark.ext.attributes.AttributeNode;
import com.vladsch.flexmark.ext.attributes.AttributesDelimiter;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.attributes.AttributesNode;
import com.vladsch.flexmark.parser.LightInlineParser;
import com.vladsch.flexmark.parser.LightInlineParserImpl;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.ast.BlankLine;
import com.vladsch.flexmark.util.ast.DoNotAttributeDecorate;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesNodePostProcessor.class */
public class AttributesNodePostProcessor extends NodePostProcessor {
    private final NodeAttributeRepository nodeAttributeRepository;
    private final AttributesOptions myOptions;
    private LightInlineParser myLightInlineParser;
    private AttributesInlineParserExtension myParserExtension;

    public AttributesNodePostProcessor(Document document) {
        this.nodeAttributeRepository = AttributesExtension.NODE_ATTRIBUTES.get(document);
        this.myOptions = new AttributesOptions(document);
    }

    public Node getAttributeOwner(NodeTracker nodeTracker, AttributesNode attributesNode) {
        Node attributeOwner;
        Node previousAnyNot = attributesNode.getPreviousAnyNot(BlankLine.class, DoNotAttributeDecorate.class);
        Node parent = attributesNode.getParent();
        if (previousAnyNot == null) {
            if (parent instanceof Paragraph) {
                if (parent.getParent() instanceof ParagraphItemContainer) {
                    Node previousAnyNot2 = parent.getPreviousAnyNot(BlankLine.class);
                    if (previousAnyNot2 == null) {
                        attributeOwner = parent.getGrandParent();
                    } else if (attributesNode.getNextAnyNot(AttributesNode.class, BlankLine.class) == null) {
                        attributeOwner = previousAnyNot2;
                    } else {
                        attributeOwner = parent;
                    }
                } else if (attributesNode.getNextAnyNot(AttributesNode.class, BlankLine.class) == null) {
                    Node previousAnyNot3 = parent.getPreviousAnyNot(BlankLine.class);
                    if (previousAnyNot3 == null) {
                        attributeOwner = parent.getParent();
                    } else {
                        attributeOwner = previousAnyNot3;
                    }
                } else {
                    attributeOwner = parent;
                }
            } else {
                attributeOwner = parent;
            }
        } else if ((!this.myOptions.assignTextAttributes && ((previousAnyNot instanceof Text) || (previousAnyNot instanceof TextBase))) || previousAnyNot.getEndOffset() < attributesNode.getStartOffset()) {
            if (this.myOptions.useEmptyImplicitAsSpanDelimiter) {
                previousAnyNot = matchDelimitedSpans(nodeTracker, attributesNode, previousAnyNot);
            }
            if (!(previousAnyNot instanceof TextBase)) {
                if ((parent instanceof Paragraph) && (parent.getParent() instanceof ParagraphItemContainer)) {
                    attributeOwner = parent.getParent();
                } else {
                    attributeOwner = parent;
                }
            }
            attributeOwner = previousAnyNot;
        } else {
            if (this.myOptions.wrapNonAttributeText) {
                Node node = null;
                boolean z = false;
                for (Node previous = attributesNode.getPrevious(); previous != null && ((previous instanceof Text) || (previous instanceof DoNotAttributeDecorate)); previous = previous.getPrevious()) {
                    if (previous instanceof DoNotAttributeDecorate) {
                        z = true;
                    }
                    node = previous;
                }
                if (z) {
                    TextBase textBase = new TextBase();
                    textBaseWrap(nodeTracker, node, attributesNode, textBase);
                    previousAnyNot = textBase;
                }
            }
            if (this.myOptions.useEmptyImplicitAsSpanDelimiter) {
                previousAnyNot = matchDelimitedSpans(nodeTracker, attributesNode, previousAnyNot);
            }
            if (previousAnyNot instanceof Text) {
                TextBase textBase2 = new TextBase(previousAnyNot.getChars());
                previousAnyNot.insertBefore(textBase2);
                previousAnyNot.unlink();
                nodeTracker.nodeRemoved(previousAnyNot);
                textBase2.appendChild(previousAnyNot);
                nodeTracker.nodeAddedWithChildren(textBase2);
                attributeOwner = textBase2;
            } else if (previousAnyNot instanceof AttributesDelimiter) {
                attributeOwner = null;
            } else {
                if (previousAnyNot instanceof AttributesNode) {
                    attributeOwner = getAttributeOwner(nodeTracker, (AttributesNode) previousAnyNot);
                }
                attributeOwner = previousAnyNot;
            }
        }
        return attributeOwner;
    }

    static Node matchDelimitedSpans(NodeTracker nodeTracker, AttributesNode attributesNode, Node node) {
        Node previous = attributesNode.getPrevious();
        ArrayList arrayList = new ArrayList();
        while (true) {
            if (previous == null) {
                break;
            }
            if (previous instanceof AttributesDelimiter) {
                if (!arrayList.isEmpty()) {
                    Node node2 = (Node) arrayList.remove(arrayList.size() - 1);
                    Node next = previous.getNext();
                    if (node2 != next) {
                        textBaseWrap(nodeTracker, next, node2, new TextBase());
                    } else {
                        node = previous;
                    }
                } else {
                    TextBase textBase = new TextBase();
                    Node next2 = previous.getNext();
                    if (next2 != attributesNode) {
                        textBaseWrap(nodeTracker, next2, attributesNode, textBase);
                        node = textBase;
                    } else {
                        node = previous;
                    }
                }
            } else if (previous instanceof AttributesNode) {
                arrayList.add(previous);
            }
            previous = previous.getPrevious();
        }
        if (!arrayList.isEmpty()) {
            Node node3 = (Node) arrayList.get(0);
            node = node3;
            Node next3 = node3.getNext();
            if (next3 != null && next3 != attributesNode) {
                node = next3;
            }
        }
        return node;
    }

    static void textBaseWrap(NodeTracker nodeTracker, Node node, Node node2, TextBase textBase) {
        while (node != node2) {
            Node next = node.getNext();
            node.unlink();
            nodeTracker.nodeRemoved(node);
            textBase.appendChild(node);
            node = next;
        }
        textBase.setCharsFromContent();
        node2.insertBefore(textBase);
        nodeTracker.nodeAddedWithDescendants(textBase);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vladsch.flexmark.parser.PostProcessor
    public void process(NodeTracker nodeTracker, Node node) {
        if (node instanceof AttributesNode) {
            AttributesNode attributesNode = (AttributesNode) node;
            Node previous = attributesNode.getPrevious();
            Node next = attributesNode.getNext();
            if (previous == null && next != null && !(next instanceof AttributesNode)) {
                if (next.getChars().isBlank()) {
                    next = next.getNext();
                    next.unlink();
                    nodeTracker.nodeRemoved(next);
                } else {
                    next.setChars(next.getChars().trimStart());
                }
            }
            if (next == null && previous != null && !(previous instanceof AttributesNode)) {
                if (previous.getChars().isBlank()) {
                    previous.getPrevious();
                    previous.unlink();
                    nodeTracker.nodeRemoved(previous);
                } else {
                    previous.setChars(previous.getChars().trimEnd());
                }
            }
            Node attributeOwner = getAttributeOwner(nodeTracker, attributesNode);
            if (attributeOwner != 0) {
                this.nodeAttributeRepository.put(attributeOwner, attributesNode);
                if (attributeOwner instanceof AnchorRefTarget) {
                    ReversiblePeekingIterator<Node> it = attributesNode.getReversedChildren().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Node next2 = it.next();
                        if ((next2 instanceof AttributeNode) && ((AttributeNode) next2).isId()) {
                            ((AnchorRefTarget) attributeOwner).setAnchorRefId(((AttributeNode) next2).getValue().toString());
                            ((AnchorRefTarget) attributeOwner).setExplicitAnchorRefId(true);
                            break;
                        }
                    }
                }
            }
        }
        if ((node instanceof FencedCodeBlock) && this.myOptions.fencedCodeInfoAttributes) {
            FencedCodeBlock fencedCodeBlock = (FencedCodeBlock) node;
            BasedSequence info = fencedCodeBlock.getInfo();
            BasedSequence trimStart = info.subSequence(fencedCodeBlock.getInfoDelimitedByAny(CharPredicate.SPACE_TAB).length()).trimStart();
            int indexOf = trimStart.indexOf('{');
            if (indexOf >= 0) {
                if (this.myLightInlineParser == null) {
                    this.myLightInlineParser = new LightInlineParserImpl(node.getDocument());
                    this.myParserExtension = new AttributesInlineParserExtension(this.myLightInlineParser);
                }
                this.myLightInlineParser.setInput(trimStart);
                this.myLightInlineParser.setIndex(indexOf);
                AttributesNode attributesNode2 = new AttributesNode();
                this.myLightInlineParser.setBlock(attributesNode2);
                while (true) {
                    int index = this.myLightInlineParser.getIndex();
                    boolean parse = this.myParserExtension.parse(this.myLightInlineParser);
                    this.myLightInlineParser.spnl();
                    int index2 = this.myLightInlineParser.getIndex() + (this.myLightInlineParser.getIndex() == index ? 1 : 0);
                    int indexOf2 = trimStart.indexOf('{', index2);
                    if (indexOf2 == -1) {
                        break;
                    }
                    if (!parse || !trimStart.subSequence(index2, indexOf2).isBlank()) {
                        attributesNode2.removeChildren();
                    }
                    this.myLightInlineParser.setIndex(indexOf2);
                }
                if (attributesNode2.hasChildren()) {
                    Node firstChild = attributesNode2.getFirstChild();
                    Node lastChild = attributesNode2.getLastChild();
                    fencedCodeBlock.setInfo(fencedCodeBlock.baseSubSequence(info.getStartOffset(), firstChild.getStartOffset()));
                    fencedCodeBlock.setAttributes(fencedCodeBlock.baseSubSequence(firstChild.getStartOffset(), lastChild.getEndOffset()));
                    ReversiblePeekingIterator<Node> it2 = attributesNode2.getChildren().iterator();
                    while (it2.hasNext()) {
                        Node next3 = it2.next();
                        if (this.myLightInlineParser.getIndex() >= this.myLightInlineParser.getInput().length()) {
                            if (fencedCodeBlock.hasChildren()) {
                                fencedCodeBlock.getLastChild().insertBefore(next3);
                            } else {
                                fencedCodeBlock.appendChild(next3);
                            }
                            this.nodeAttributeRepository.put((Node) fencedCodeBlock, (AttributesNode) next3);
                        }
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesNodePostProcessor$Factory.class */
    public static class Factory extends NodePostProcessorFactory {
        public Factory() {
            super(false);
            addNodes(AttributesNode.class, FencedCodeBlock.class);
        }

        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.parser.PostProcessorFactory, java.util.function.Function
        public NodePostProcessor apply(Document document) {
            return new AttributesNodePostProcessor(document);
        }
    }
}
