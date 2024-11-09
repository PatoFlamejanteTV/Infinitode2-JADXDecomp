package com.vladsch.flexmark.ext.attributes.internal;

import com.vladsch.flexmark.ast.AnchorRefTarget;
import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.util.AnchorRefTargetBlockVisitor;
import com.vladsch.flexmark.ext.attributes.AttributeNode;
import com.vladsch.flexmark.ext.attributes.AttributesDelimiter;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.attributes.AttributesNode;
import com.vladsch.flexmark.formatter.ExplicitAttributeIdProvider;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.formatter.FormattingPhase;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.MergeContext;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.formatter.PhasedNodeFormatter;
import com.vladsch.flexmark.formatter.RenderPurpose;
import com.vladsch.flexmark.html.renderer.HtmlIdGenerator;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.html.MutableAttributes;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.PrefixedSubSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesNodeFormatter.class */
public class AttributesNodeFormatter implements ExplicitAttributeIdProvider, PhasedNodeFormatter {
    public static final DataKey<Map<String, String>> ATTRIBUTE_TRANSLATION_MAP = new DataKey<>("ATTRIBUTE_TRANSLATION_MAP", HashMap::new);
    public static final DataKey<Map<String, String>> ATTRIBUTE_TRANSLATED_MAP = new DataKey<>("ATTRIBUTE_TRANSLATED_MAP", HashMap::new);
    public static final DataKey<Map<String, String>> ATTRIBUTE_ORIGINAL_ID_MAP = new DataKey<>("ATTRIBUTE_ORIGINAL_ID_MAP", HashMap::new);
    public static final DataKey<Set<Node>> PROCESSED_ATTRIBUTES = new DataKey<>("PROCESSED_ATTRIBUTES", HashSet::new);
    public static final DataKey<Map<String, String>> ATTRIBUTE_UNIQUIFICATION_ID_MAP = Formatter.ATTRIBUTE_UNIQUIFICATION_ID_MAP;
    public static final DataKey<Map<String, String>> ATTRIBUTE_UNIQUIFICATION_CATEGORY_MAP = new DataKey<>("ATTRIBUTE_UNIQUIFICATION_CATEGORY_MAP", HashMap::new);
    public static final DataKey<Integer> ATTRIBUTE_TRANSLATION_ID = new DataKey<>("ATTRIBUTE_TRANSLATION_ID", 0);
    private Map<String, String> attributeTranslationMap;
    private Map<String, String> attributeTranslatedMap;
    private Map<String, String> attributeOriginalIdMap;
    private Map<String, String> attributeUniquificationIdMap;
    private int attributeOriginalId;
    private final AttributesFormatOptions formatOptions;

    public AttributesNodeFormatter(DataHolder dataHolder) {
        this.formatOptions = new AttributesFormatOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return null;
    }

    @Override // com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public Set<FormattingPhase> getFormattingPhases() {
        return Collections.singleton(FormattingPhase.COLLECT);
    }

    @Override // com.vladsch.flexmark.formatter.ExplicitAttributeIdProvider
    public void addExplicitId(Node node, String str, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (str != null && (node instanceof Heading) && nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATED && hasNoIdAttribute(node) && this.attributeUniquificationIdMap != null) {
            String orDefault = this.attributeUniquificationIdMap.getOrDefault(str, str);
            if (!orDefault.equals(str)) {
                markdownWriter.append(" {.");
                markdownWriter.append((CharSequence) orDefault);
                markdownWriter.append("}");
            }
        }
    }

    boolean hasNoIdAttribute(Node node) {
        boolean z = false;
        ReversiblePeekingIterator<Node> it = node.getChildren().iterator();
        while (it.hasNext()) {
            Node next = it.next();
            if (next instanceof AttributesNode) {
                ReversiblePeekingIterator<Node> it2 = next.getChildren().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Node next2 = it2.next();
                    if ((next2 instanceof AttributeNode) && ((AttributeNode) next2).isId()) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    break;
                }
            }
        }
        return !z;
    }

    @Override // com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public void renderDocument(NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, Document document, FormattingPhase formattingPhase) {
        String str;
        if (nodeFormatterContext.isTransformingText()) {
            nodeFormatterContext.getTranslationStore().set((DataKey<DataKey<Integer>>) ATTRIBUTE_TRANSLATION_ID, (DataKey<Integer>) 0);
            this.attributeOriginalId = 0;
            if (formattingPhase == FormattingPhase.COLLECT) {
                nodeFormatterContext.getDocument().remove((DataKeyBase<?>) PROCESSED_ATTRIBUTES);
                if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATION_SPANS) {
                    nodeFormatterContext.getTranslationStore().set((DataKey<DataKey<Map<String, String>>>) ATTRIBUTE_TRANSLATION_MAP, (DataKey<Map<String, String>>) new HashMap());
                    nodeFormatterContext.getTranslationStore().set((DataKey<DataKey<Map<String, String>>>) ATTRIBUTE_TRANSLATED_MAP, (DataKey<Map<String, String>>) new HashMap());
                    nodeFormatterContext.getTranslationStore().set((DataKey<DataKey<Map<String, String>>>) ATTRIBUTE_ORIGINAL_ID_MAP, (DataKey<Map<String, String>>) new HashMap());
                    MergeContext mergeContext = nodeFormatterContext.getMergeContext();
                    if (mergeContext != null) {
                        final HashSet hashSet = new HashSet();
                        mergeContext.forEachPrecedingDocument(document, (translationContext, document2, i) -> {
                            NodeAttributeRepository nodeAttributeRepository = AttributesExtension.NODE_ATTRIBUTES.get(document2);
                            final Map<String, String> map = ATTRIBUTE_UNIQUIFICATION_ID_MAP.get(translationContext.getTranslationStore());
                            Iterator<ArrayList<AttributesNode>> it = nodeAttributeRepository.values().iterator();
                            while (it.hasNext()) {
                                Iterator<AttributesNode> it2 = it.next().iterator();
                                while (it2.hasNext()) {
                                    ReversiblePeekingIterator<Node> it3 = it2.next().getChildren().iterator();
                                    while (it3.hasNext()) {
                                        Node next = it3.next();
                                        if (next instanceof AttributeNode) {
                                            AttributeNode attributeNode = (AttributeNode) next;
                                            if (attributeNode.isId()) {
                                                String obj = attributeNode.getValue().toString();
                                                String orDefault = map.getOrDefault(obj, obj);
                                                if (!hashSet.contains(orDefault)) {
                                                    hashSet.add(orDefault);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            final HtmlIdGenerator idGenerator = nodeFormatterContext.getIdGenerator();
                            if (idGenerator != null) {
                                new AnchorRefTargetBlockVisitor() { // from class: com.vladsch.flexmark.ext.attributes.internal.AttributesNodeFormatter.1
                                    /* JADX WARN: Multi-variable type inference failed */
                                    @Override // com.vladsch.flexmark.ast.util.AnchorRefTargetBlockVisitor
                                    protected void visit(AnchorRefTarget anchorRefTarget) {
                                        Node node = (Node) anchorRefTarget;
                                        if (AttributesNodeFormatter.this.hasNoIdAttribute(node)) {
                                            String id = idGenerator.getId(node);
                                            String str2 = id;
                                            if (id == null) {
                                                str2 = idGenerator.getId(anchorRefTarget.getAnchorRefText());
                                                anchorRefTarget.setAnchorRefId(str2);
                                            }
                                            if (str2 != null) {
                                                String str3 = str2;
                                                String str4 = (String) map.getOrDefault(str3, str3);
                                                if (!hashSet.contains(str4)) {
                                                    hashSet.add(str4);
                                                }
                                            }
                                        }
                                    }
                                }.visit(document);
                            }
                        });
                        NodeAttributeRepository nodeAttributeRepository = AttributesExtension.NODE_ATTRIBUTES.get(document);
                        Map<String, String> map = ATTRIBUTE_UNIQUIFICATION_CATEGORY_MAP.get(nodeFormatterContext.getTranslationStore());
                        final HashMap hashMap = new HashMap();
                        Iterator<ArrayList<AttributesNode>> it = nodeAttributeRepository.values().iterator();
                        while (it.hasNext()) {
                            Iterator<AttributesNode> it2 = it.next().iterator();
                            while (it2.hasNext()) {
                                ReversiblePeekingIterator<Node> it3 = it2.next().getChildren().iterator();
                                while (it3.hasNext()) {
                                    Node next = it3.next();
                                    if (next instanceof AttributeNode) {
                                        AttributeNode attributeNode = (AttributeNode) next;
                                        if (attributeNode.isId()) {
                                            BasedSequence value = attributeNode.getValue();
                                            String obj = value.toString();
                                            String str2 = obj;
                                            int indexOf = value.indexOf(':');
                                            if (indexOf != -1) {
                                                String obj2 = value.subSequence(0, indexOf).toString();
                                                str2 = String.format("%s:%s", map.getOrDefault(obj2, obj2), value.subSequence(indexOf + 1).toString());
                                            }
                                            int i2 = 0;
                                            String str3 = str2;
                                            while (true) {
                                                str = str3;
                                                if (!hashSet.contains(str)) {
                                                    break;
                                                }
                                                i2++;
                                                str3 = String.format("%s%d", str2, Integer.valueOf(i2));
                                            }
                                            if (i2 > 0 || !str.equals(obj)) {
                                                hashMap.put(obj, str);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        final HtmlIdGenerator idGenerator = nodeFormatterContext.getIdGenerator();
                        if (idGenerator != null) {
                            new AnchorRefTargetBlockVisitor() { // from class: com.vladsch.flexmark.ext.attributes.internal.AttributesNodeFormatter.2
                                /* JADX WARN: Multi-variable type inference failed */
                                @Override // com.vladsch.flexmark.ast.util.AnchorRefTargetBlockVisitor
                                protected void visit(AnchorRefTarget anchorRefTarget) {
                                    String str4;
                                    Node node = (Node) anchorRefTarget;
                                    if (AttributesNodeFormatter.this.hasNoIdAttribute(node)) {
                                        String id = idGenerator.getId(node);
                                        Object obj3 = id;
                                        if (id == null) {
                                            obj3 = idGenerator.getId(anchorRefTarget.getAnchorRefText());
                                            anchorRefTarget.setAnchorRefId(obj3);
                                        }
                                        if (obj3 != null) {
                                            int i3 = 0;
                                            String str5 = obj3;
                                            while (true) {
                                                str4 = str5;
                                                if (!hashSet.contains(str4)) {
                                                    break;
                                                }
                                                i3++;
                                                str5 = String.format("%s%d", obj3, Integer.valueOf(i3));
                                            }
                                            if (i3 > 0 || !str4.equals(obj3)) {
                                                hashMap.put(obj3, str4);
                                            }
                                        }
                                    }
                                }
                            }.visit(document);
                        }
                        if (!hashMap.isEmpty()) {
                            nodeFormatterContext.getTranslationStore().set((DataKey<DataKey<Map<String, String>>>) ATTRIBUTE_UNIQUIFICATION_ID_MAP, (DataKey<Map<String, String>>) hashMap);
                        }
                    }
                }
            }
        }
        this.attributeUniquificationIdMap = ATTRIBUTE_UNIQUIFICATION_ID_MAP.get(nodeFormatterContext.getTranslationStore());
        this.attributeTranslationMap = ATTRIBUTE_TRANSLATION_MAP.get(nodeFormatterContext.getTranslationStore());
        this.attributeTranslatedMap = ATTRIBUTE_TRANSLATED_MAP.get(nodeFormatterContext.getTranslationStore());
        this.attributeOriginalIdMap = ATTRIBUTE_ORIGINAL_ID_MAP.get(nodeFormatterContext.getTranslationStore());
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeFormattingHandler(AttributesNode.class, this::render));
        hashSet.add(new NodeFormattingHandler(AttributesDelimiter.class, (v1, v2, v3) -> {
            render(v1, v2, v3);
        }));
        return hashSet;
    }

    public static String getEncodedIdAttribute(String str, String str2, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        String encodedIdAttribute = getEncodedIdAttribute(str, str2, nodeFormatterContext, markdownWriter, ATTRIBUTE_TRANSLATION_MAP.get(nodeFormatterContext.getTranslationStore()), ATTRIBUTE_TRANSLATED_MAP.get(nodeFormatterContext.getTranslationStore()));
        if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATED) {
            Map<String, String> map = ATTRIBUTE_UNIQUIFICATION_ID_MAP.get(nodeFormatterContext.getTranslationStore());
            if (!map.isEmpty()) {
                return map.getOrDefault(encodedIdAttribute, encodedIdAttribute);
            }
        }
        return encodedIdAttribute;
    }

    private static String getEncodedIdAttribute(String str, String str2, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, Map<String, String> map, Map<String, String> map2) {
        String str3 = str;
        String str4 = str2;
        int intValue = ATTRIBUTE_TRANSLATION_ID.get(nodeFormatterContext.getTranslationStore()).intValue();
        switch (nodeFormatterContext.getRenderPurpose()) {
            case TRANSLATION_SPANS:
                if (!map.containsKey(str)) {
                    intValue++;
                    str3 = String.format(nodeFormatterContext.getFormatterOptions().translationIdFormat, Integer.valueOf(intValue));
                    map.put(str, str3);
                    map2.put(str3, str);
                } else {
                    str3 = map.get(str);
                }
                if (str2 != null && !map.containsKey(str2)) {
                    intValue++;
                    str4 = String.format(nodeFormatterContext.getFormatterOptions().translationIdFormat, Integer.valueOf(intValue));
                    map.put(str2, str4);
                    map2.put(str4, str2);
                    break;
                } else {
                    str4 = map.get(str2);
                    break;
                }
                break;
            case TRANSLATED:
                str3 = map2.get(str);
                if (str2 != null) {
                    str4 = map2.get(str2);
                    break;
                }
                break;
        }
        nodeFormatterContext.getTranslationStore().set((DataKey<DataKey<Integer>>) ATTRIBUTE_TRANSLATION_ID, (DataKey<Integer>) Integer.valueOf(intValue));
        if (str4 == null) {
            return str3;
        }
        return str3 + ':' + str4;
    }

    private String getEncodedOriginalId(String str, NodeFormatterContext nodeFormatterContext) {
        switch (nodeFormatterContext.getRenderPurpose()) {
            case TRANSLATION_SPANS:
                StringBuilder sb = new StringBuilder("#");
                String str2 = nodeFormatterContext.getFormatterOptions().translationIdFormat;
                int i = this.attributeOriginalId + 1;
                this.attributeOriginalId = i;
                String sb2 = sb.append(String.format(str2, Integer.valueOf(i))).toString();
                this.attributeOriginalIdMap.put(sb2, str);
                return sb2;
            case TRANSLATED_SPANS:
                StringBuilder sb3 = new StringBuilder("#");
                String str3 = nodeFormatterContext.getFormatterOptions().translationIdFormat;
                int i2 = this.attributeOriginalId + 1;
                this.attributeOriginalId = i2;
                return sb3.append(String.format(str3, Integer.valueOf(i2))).toString();
            case TRANSLATED:
                this.attributeOriginalId++;
                String str4 = this.attributeOriginalIdMap.get(str);
                if (this.attributeUniquificationIdMap != null) {
                    return this.attributeUniquificationIdMap.getOrDefault(str4, str4);
                }
                return str4;
            default:
                return str;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:141:0x05da. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:179:0x069f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0599  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x05be  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0751  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0770  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x07b1  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x067c  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x059e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void render(com.vladsch.flexmark.ext.attributes.AttributesNode r8, com.vladsch.flexmark.formatter.NodeFormatterContext r9, com.vladsch.flexmark.formatter.MarkdownWriter r10) {
        /*
            Method dump skipped, instructions count: 2070
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.ext.attributes.internal.AttributesNodeFormatter.render(com.vladsch.flexmark.ext.attributes.AttributesNode, com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.formatter.MarkdownWriter):void");
    }

    static AttributeNode combineAttributes(LinkedHashMap<String, AttributeNode> linkedHashMap, AttributeNode attributeNode) {
        if (attributeNode.isId()) {
            linkedHashMap.remove(Attribute.ID_ATTR);
            linkedHashMap.remove("#");
            return attributeNode;
        }
        if (attributeNode.isClass()) {
            AttributeNode attributeNode2 = attributeNode;
            AttributeNode remove = linkedHashMap.remove(Attribute.CLASS_ATTR);
            AttributeNode remove2 = linkedHashMap.remove(".");
            if (remove != null || remove2 != null) {
                MutableAttributes mutableAttributes = new MutableAttributes();
                if (remove != null) {
                    mutableAttributes.addValue(Attribute.CLASS_ATTR, remove.getValue());
                }
                if (remove2 != null) {
                    mutableAttributes.addValue(Attribute.CLASS_ATTR, remove2.getValue());
                }
                String value = mutableAttributes.getValue(Attribute.CLASS_ATTR);
                if (!attributeNode.getValue().equals(value)) {
                    attributeNode2 = new AttributeNode(attributeNode.getName(), attributeNode.getAttributeSeparator(), attributeNode.getOpeningMarker(), PrefixedSubSequence.prefixOf(value + SequenceUtils.SPACE, attributeNode.getValue()), attributeNode.getClosingMarker());
                }
            }
            return attributeNode2;
        }
        if (attributeNode.getName().equals(Attribute.STYLE_ATTR)) {
            AttributeNode attributeNode3 = attributeNode;
            AttributeNode remove3 = linkedHashMap.remove(Attribute.STYLE_ATTR);
            if (remove3 != null) {
                MutableAttributes mutableAttributes2 = new MutableAttributes();
                mutableAttributes2.addValue(Attribute.STYLE_ATTR, remove3.getValue());
                String value2 = mutableAttributes2.getValue(Attribute.STYLE_ATTR);
                if (!attributeNode.getValue().equals(value2)) {
                    attributeNode3 = new AttributeNode(attributeNode.getName(), attributeNode.getAttributeSeparator(), attributeNode.getOpeningMarker(), PrefixedSubSequence.prefixOf(value2 + ";", attributeNode.getValue()), attributeNode.getClosingMarker());
                }
            }
            return attributeNode3;
        }
        return attributeNode;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/internal/AttributesNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new AttributesNodeFormatter(dataHolder);
        }
    }
}
