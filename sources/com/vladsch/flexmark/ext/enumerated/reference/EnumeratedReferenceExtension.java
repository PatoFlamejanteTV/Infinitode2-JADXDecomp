package com.vladsch.flexmark.ext.enumerated.reference;

import com.vladsch.flexmark.ext.enumerated.reference.internal.EnumeratedReferenceBlockParser;
import com.vladsch.flexmark.ext.enumerated.reference.internal.EnumeratedReferenceLinkRefProcessor;
import com.vladsch.flexmark.ext.enumerated.reference.internal.EnumeratedReferenceNodeFormatter;
import com.vladsch.flexmark.ext.enumerated.reference.internal.EnumeratedReferenceNodePostProcessor;
import com.vladsch.flexmark.ext.enumerated.reference.internal.EnumeratedReferenceNodeRenderer;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/EnumeratedReferenceExtension.class */
public class EnumeratedReferenceExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension, Parser.ReferenceHoldingExtension {
    public static final DataKey<KeepType> ENUMERATED_REFERENCES_KEEP = new DataKey<>("ENUMERATED_REFERENCES_KEEP", KeepType.FIRST);
    public static final DataKey<EnumeratedReferenceRepository> ENUMERATED_REFERENCES = new DataKey<>("ENUMERATED_REFERENCES", new EnumeratedReferenceRepository(null), EnumeratedReferenceRepository::new);
    public static final DataKey<EnumeratedReferences> ENUMERATED_REFERENCE_ORDINALS = new DataKey<>("ENUMERATED_REFERENCE_ORDINALS", new EnumeratedReferences(null), EnumeratedReferences::new);
    public static final DataKey<ElementPlacement> ENUMERATED_REFERENCE_PLACEMENT = new DataKey<>("ENUMERATED_REFERENCE_PLACEMENT", ElementPlacement.AS_IS);
    public static final DataKey<ElementPlacementSort> ENUMERATED_REFERENCE_SORT = new DataKey<>("ENUMERATED_REFERENCE_SORT", ElementPlacementSort.AS_IS);

    private EnumeratedReferenceExtension() {
    }

    public static EnumeratedReferenceExtension create() {
        return new EnumeratedReferenceExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ReferenceHoldingExtension
    public boolean transferReferences(MutableDataHolder mutableDataHolder, DataHolder dataHolder) {
        if (mutableDataHolder.contains(ENUMERATED_REFERENCES) && dataHolder.contains(ENUMERATED_REFERENCES)) {
            return Parser.transferReferences(ENUMERATED_REFERENCES.get(mutableDataHolder), ENUMERATED_REFERENCES.get(dataHolder), ENUMERATED_REFERENCES_KEEP.get(mutableDataHolder) == KeepType.FIRST);
        }
        return false;
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.postProcessorFactory(new EnumeratedReferenceNodePostProcessor.Factory());
        builder.customBlockParserFactory(new EnumeratedReferenceBlockParser.Factory());
        builder.linkRefProcessorFactory(new EnumeratedReferenceLinkRefProcessor.Factory());
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new EnumeratedReferenceNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new EnumeratedReferenceNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
