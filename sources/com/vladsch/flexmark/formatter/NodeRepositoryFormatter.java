package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeRepository;
import com.vladsch.flexmark.util.ast.ReferenceNode;
import com.vladsch.flexmark.util.ast.ReferencingNode;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.DataSet;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/NodeRepositoryFormatter.class */
public abstract class NodeRepositoryFormatter<R extends NodeRepository<B>, B extends Node & ReferenceNode<R, B, N>, N extends Node & ReferencingNode<R, B>> implements PhasedNodeFormatter {
    public static final HashSet<FormattingPhase> FORMATTING_PHASES = new HashSet<>(Arrays.asList(FormattingPhase.COLLECT, FormattingPhase.DOCUMENT_TOP, FormattingPhase.DOCUMENT_BOTTOM));
    protected final R referenceRepository;
    protected final List<B> referenceList;
    protected final HashSet<Node> unusedReferences;
    protected final B lastReference;
    protected boolean recheckUndefinedReferences;
    protected boolean repositoryNodesDone;
    protected final Comparator<B> myComparator;
    private Map<String, String> referenceTranslationMap;
    protected Map<String, String> referenceUniqificationMap;
    private final DataKey<Map<String, String>> myReferenceMapKey;
    private final DataKey<Map<String, String>> myReferenceUniqificationMapKey;

    public abstract R getRepository(DataHolder dataHolder);

    public abstract ElementPlacement getReferencePlacement();

    public abstract ElementPlacementSort getReferenceSort();

    protected abstract void renderReferenceBlock(B b2, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter);

    public Comparator<B> getReferenceComparator() {
        return this.myComparator;
    }

    protected boolean makeReferencesUnique() {
        return true;
    }

    protected ElementPlacement getTranslationReferencePlacement(NodeFormatterContext nodeFormatterContext) {
        if (nodeFormatterContext.isTransformingText()) {
            return ElementPlacement.AS_IS;
        }
        return getReferencePlacement();
    }

    public String modifyTransformedReference(String str, NodeFormatterContext nodeFormatterContext) {
        return str;
    }

    private void renderReferenceBlockUnique(B b2, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATED) {
            nodeFormatterContext.postProcessNonTranslating(str -> {
                if (this.referenceUniqificationMap != null) {
                    return this.referenceUniqificationMap.getOrDefault(str, str);
                }
                return str;
            }, () -> {
                renderReferenceBlock(b2, nodeFormatterContext, markdownWriter);
            });
        } else {
            renderReferenceBlock(b2, nodeFormatterContext, markdownWriter);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String transformReferenceId(String str, NodeFormatterContext nodeFormatterContext) {
        String charSequence;
        if (nodeFormatterContext.isTransformingText()) {
            switch (nodeFormatterContext.getRenderPurpose()) {
                case TRANSLATION_SPANS:
                case TRANSLATED_SPANS:
                    if (this.referenceTranslationMap != null) {
                        if (this.referenceTranslationMap.containsKey(str)) {
                            charSequence = this.referenceTranslationMap.get(str);
                        } else {
                            charSequence = nodeFormatterContext.transformNonTranslating(null, str, null, null).toString();
                            this.referenceTranslationMap.put(str, charSequence);
                        }
                    } else {
                        charSequence = nodeFormatterContext.transformNonTranslating(null, str, null, null).toString();
                    }
                    return modifyTransformedReference(charSequence, nodeFormatterContext);
                case TRANSLATED:
                    String charSequence2 = nodeFormatterContext.transformNonTranslating(null, modifyTransformedReference(str, nodeFormatterContext), null, null).toString();
                    if (!nodeFormatterContext.isPostProcessingNonTranslating() && this.referenceUniqificationMap != null && this.referenceUniqificationMap.containsKey(charSequence2)) {
                        return this.referenceUniqificationMap.get(charSequence2);
                    }
                    return charSequence2;
            }
        }
        return str;
    }

    public NodeRepositoryFormatter(DataHolder dataHolder, DataKey<Map<String, String>> dataKey, DataKey<Map<String, String>> dataKey2) {
        this.myReferenceMapKey = dataKey;
        this.myReferenceUniqificationMapKey = dataKey2;
        this.referenceRepository = getRepository(dataHolder);
        this.referenceList = this.referenceRepository.values();
        this.lastReference = this.referenceList.isEmpty() ? null : this.referenceList.get(this.referenceList.size() - 1);
        this.unusedReferences = new HashSet<>();
        this.recheckUndefinedReferences = HtmlRenderer.RECHECK_UNDEFINED_REFERENCES.get(dataHolder).booleanValue();
        this.repositoryNodesDone = false;
        this.myComparator = (obj, obj2) -> {
            return ((Comparable) obj).compareTo(obj2);
        };
    }

    @Override // com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public Set<FormattingPhase> getFormattingPhases() {
        return FORMATTING_PHASES;
    }

    @Override // com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public void renderDocument(NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, Document document, FormattingPhase formattingPhase) {
        Node node;
        if (nodeFormatterContext.isTransformingText() && this.myReferenceMapKey != null) {
            if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATION_SPANS) {
                nodeFormatterContext.getTranslationStore().set((DataKey<DataKey<Map<String, String>>>) this.myReferenceMapKey, (DataKey<Map<String, String>>) new HashMap());
            }
            this.referenceTranslationMap = this.myReferenceMapKey.get(nodeFormatterContext.getTranslationStore());
        }
        switch (formattingPhase) {
            case COLLECT:
                this.referenceUniqificationMap = null;
                if (nodeFormatterContext.isTransformingText() && this.myReferenceUniqificationMapKey != null && makeReferencesUnique()) {
                    if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATION_SPANS && nodeFormatterContext.getMergeContext() != null) {
                        uniquifyIds(nodeFormatterContext, markdownWriter, document);
                    }
                    this.referenceUniqificationMap = this.myReferenceUniqificationMapKey.get(nodeFormatterContext.getTranslationStore());
                }
                if (getTranslationReferencePlacement(nodeFormatterContext).isChange() && getReferenceSort().isUnused()) {
                    this.unusedReferences.addAll(this.referenceList);
                    Set<Class<?>> nodeClasses = getNodeClasses();
                    if (nodeClasses != null) {
                        Iterator<? extends Node> it = nodeFormatterContext.nodesOfType(nodeClasses).iterator();
                        while (it.hasNext()) {
                            Node referencingNode = this.lastReference == null ? null : ((ReferenceNode) this.lastReference).getReferencingNode(it.next());
                            Object obj = referencingNode;
                            if (referencingNode != null && (node = (Node) ((ReferencingNode) obj).getReferenceNode((ReferencingNode) this.referenceRepository)) != null) {
                                this.unusedReferences.remove(node);
                            }
                        }
                        return;
                    }
                    return;
                }
                return;
            case DOCUMENT_TOP:
                if (getTranslationReferencePlacement(nodeFormatterContext) == ElementPlacement.DOCUMENT_TOP) {
                    formatReferences(nodeFormatterContext, markdownWriter);
                    return;
                }
                return;
            case DOCUMENT_BOTTOM:
                if (getTranslationReferencePlacement(nodeFormatterContext) == ElementPlacement.DOCUMENT_BOTTOM) {
                    formatReferences(nodeFormatterContext, markdownWriter);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void formatReferences(NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        ArrayList arrayList = new ArrayList(this.referenceList);
        ElementPlacementSort referenceSort = getReferenceSort();
        switch (referenceSort) {
            case AS_IS:
                break;
            case SORT:
                arrayList.sort(getReferenceComparator());
                break;
            case SORT_UNUSED_LAST:
            case SORT_DELETE_UNUSED:
            case DELETE_UNUSED:
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Node node = (Node) it.next();
                    if (!this.unusedReferences.contains(node)) {
                        arrayList2.add(node);
                    } else if (!referenceSort.isDeleteUnused()) {
                        arrayList3.add(node);
                    }
                }
                if (referenceSort.isSort()) {
                    arrayList2.sort(getReferenceComparator());
                    if (!referenceSort.isDeleteUnused()) {
                        arrayList3.sort(getReferenceComparator());
                    }
                }
                arrayList.clear();
                arrayList.addAll(arrayList2);
                if (!referenceSort.isDeleteUnused()) {
                    arrayList.addAll(arrayList3);
                    break;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + referenceSort);
        }
        markdownWriter.blankLine();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            renderReferenceBlockUnique((Node) it2.next(), nodeFormatterContext, markdownWriter);
        }
        markdownWriter.blankLine();
        this.repositoryNodesDone = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void renderReference(B b2, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (!this.repositoryNodesDone) {
            switch (getTranslationReferencePlacement(nodeFormatterContext)) {
                case AS_IS:
                    renderReferenceBlockUnique(b2, nodeFormatterContext, markdownWriter);
                    if (b2.getNext() == null || b2.getNext().getClass() != b2.getClass()) {
                        markdownWriter.blankLine();
                        return;
                    }
                    return;
                case GROUP_WITH_FIRST:
                    formatReferences(nodeFormatterContext, markdownWriter);
                    return;
                case GROUP_WITH_LAST:
                    if (b2 == this.lastReference) {
                        formatReferences(nodeFormatterContext, markdownWriter);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    protected void uniquifyIds(NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, Document document) {
        R repository = getRepository(new DataSet());
        HashMap hashMap = new HashMap();
        nodeFormatterContext.getMergeContext().forEachPrecedingDocument(document, (translationContext, document2, i) -> {
            NodeRepository.transferReferences(repository, getRepository(document2), true, this.myReferenceUniqificationMapKey.get(translationContext.getTranslationStore()));
        });
        Iterator it = getRepository(document).entrySet().iterator();
        while (it.hasNext()) {
            String str = (String) ((Map.Entry) it.next()).getKey();
            String str2 = str;
            int i2 = 0;
            while (repository.containsKey(str2)) {
                i2++;
                str2 = String.format("%s%d", str, Integer.valueOf(i2));
            }
            if (i2 > 0) {
                hashMap.put(str, str2);
            }
        }
        if (!hashMap.isEmpty()) {
            nodeFormatterContext.getTranslationStore().set((DataKey<DataKey<Map<String, String>>>) this.myReferenceUniqificationMapKey, (DataKey<Map<String, String>>) hashMap);
        }
    }
}
