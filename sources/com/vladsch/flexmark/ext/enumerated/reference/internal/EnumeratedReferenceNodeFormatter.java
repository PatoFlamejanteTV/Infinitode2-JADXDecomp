package com.vladsch.flexmark.ext.enumerated.reference.internal;

import com.vladsch.flexmark.ext.attributes.internal.AttributesNodeFormatter;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceBlock;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceLink;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceRepository;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceText;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.formatter.NodeRepositoryFormatter;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceNodeFormatter.class */
public class EnumeratedReferenceNodeFormatter extends NodeRepositoryFormatter<EnumeratedReferenceRepository, EnumeratedReferenceBlock, EnumeratedReferenceText> {
    private final EnumeratedReferenceFormatOptions options;

    public EnumeratedReferenceNodeFormatter(DataHolder dataHolder) {
        super(dataHolder, null, AttributesNodeFormatter.ATTRIBUTE_UNIQUIFICATION_CATEGORY_MAP);
        this.options = new EnumeratedReferenceFormatOptions(dataHolder);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public EnumeratedReferenceRepository getRepository(DataHolder dataHolder) {
        return EnumeratedReferenceExtension.ENUMERATED_REFERENCES.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ElementPlacement getReferencePlacement() {
        return this.options.enumeratedReferencePlacement;
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ElementPlacementSort getReferenceSort() {
        return this.options.enumeratedReferenceSort;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public void renderReferenceBlock(EnumeratedReferenceBlock enumeratedReferenceBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.blankLine().append((CharSequence) "[@").appendNonTranslating(enumeratedReferenceBlock.getText()).append((CharSequence) "]: ");
        markdownWriter.pushPrefix().addPrefix((CharSequence) "    ", true);
        nodeFormatterContext.renderChildren(enumeratedReferenceBlock);
        markdownWriter.popPrefix();
        markdownWriter.blankLine();
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(EnumeratedReferenceText.class, this::render), new NodeFormattingHandler(EnumeratedReferenceLink.class, this::render), new NodeFormattingHandler(EnumeratedReferenceBlock.class, this::render)));
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        if (this.options.enumeratedReferencePlacement.isNoChange() || !this.options.enumeratedReferenceSort.isUnused()) {
            return null;
        }
        return new HashSet(Arrays.asList(EnumeratedReferenceBlock.class));
    }

    private void render(EnumeratedReferenceBlock enumeratedReferenceBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        renderReference(enumeratedReferenceBlock, nodeFormatterContext, markdownWriter);
    }

    private static void renderReferenceText(BasedSequence basedSequence, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        String obj;
        if (!basedSequence.isEmpty()) {
            int indexOf = basedSequence.indexOf(':');
            String str = null;
            if (indexOf == -1) {
                obj = basedSequence.toString();
            } else {
                obj = basedSequence.subSequence(0, indexOf).toString();
                str = basedSequence.subSequence(indexOf + 1).toString();
            }
            markdownWriter.append((CharSequence) AttributesNodeFormatter.getEncodedIdAttribute(obj, str, nodeFormatterContext, markdownWriter));
        }
    }

    private void render(EnumeratedReferenceText enumeratedReferenceText, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append("[#");
        if (nodeFormatterContext.isTransformingText()) {
            renderReferenceText(enumeratedReferenceText.getText(), nodeFormatterContext, markdownWriter);
        } else {
            nodeFormatterContext.renderChildren(enumeratedReferenceText);
        }
        markdownWriter.append("]");
    }

    private void render(EnumeratedReferenceLink enumeratedReferenceLink, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append("[@");
        if (nodeFormatterContext.isTransformingText()) {
            renderReferenceText(enumeratedReferenceLink.getText(), nodeFormatterContext, markdownWriter);
        } else {
            nodeFormatterContext.renderChildren(enumeratedReferenceLink);
        }
        markdownWriter.append("]");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new EnumeratedReferenceNodeFormatter(dataHolder);
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory, com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            HashSet hashSet = new HashSet();
            hashSet.add(AttributesNodeFormatter.Factory.class);
            return hashSet;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory, com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }
    }
}
