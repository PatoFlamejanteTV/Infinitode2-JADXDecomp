package com.vladsch.flexmark.ext.attributes;

import com.vladsch.flexmark.ext.attributes.internal.AttributesAttributeProvider;
import com.vladsch.flexmark.ext.attributes.internal.AttributesInlineParserExtension;
import com.vladsch.flexmark.ext.attributes.internal.AttributesNodeFormatter;
import com.vladsch.flexmark.ext.attributes.internal.AttributesNodePostProcessor;
import com.vladsch.flexmark.ext.attributes.internal.AttributesNodeRenderer;
import com.vladsch.flexmark.ext.attributes.internal.NodeAttributeRepository;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.AttributeProviderFactory;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.RendererBuilder;
import com.vladsch.flexmark.html.RendererExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.format.options.DiscretionaryText;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/AttributesExtension.class */
public class AttributesExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, RendererExtension, Parser.ParserExtension {
    public static final DataKey<NodeAttributeRepository> NODE_ATTRIBUTES = new DataKey<>("NODE_ATTRIBUTES", new NodeAttributeRepository(null), NodeAttributeRepository::new);
    public static final DataKey<KeepType> ATTRIBUTES_KEEP = new DataKey<>("ATTRIBUTES_KEEP", KeepType.FIRST);
    public static final DataKey<Boolean> ASSIGN_TEXT_ATTRIBUTES = new DataKey<>("ASSIGN_TEXT_ATTRIBUTES", Boolean.TRUE);
    public static final DataKey<Boolean> FENCED_CODE_INFO_ATTRIBUTES = new DataKey<>("FENCED_CODE_INFO_ATTRIBUTES", Boolean.FALSE);
    public static final DataKey<FencedCodeAddType> FENCED_CODE_ADD_ATTRIBUTES = new DataKey<>("FENCED_CODE_ADD_ATTRIBUTES", FencedCodeAddType.ADD_TO_PRE_CODE);
    public static final DataKey<Boolean> WRAP_NON_ATTRIBUTE_TEXT = new DataKey<>("WRAP_NON_ATTRIBUTE_TEXT", Boolean.TRUE);
    public static final DataKey<Boolean> USE_EMPTY_IMPLICIT_AS_SPAN_DELIMITER = new DataKey<>("USE_EMPTY_IMPLICIT_AS_SPAN_DELIMITER", Boolean.FALSE);
    public static final DataKey<Boolean> FORMAT_ATTRIBUTES_COMBINE_CONSECUTIVE = new DataKey<>("FORMAT_ATTRIBUTES_COMBINE_CONSECUTIVE", Boolean.FALSE);
    public static final DataKey<Boolean> FORMAT_ATTRIBUTES_SORT = new DataKey<>("FORMAT_ATTRIBUTES_SORT", Boolean.FALSE);
    public static final DataKey<DiscretionaryText> FORMAT_ATTRIBUTES_SPACES = new DataKey<>("FORMAT_ATTRIBUTES_SPACES", DiscretionaryText.AS_IS);
    public static final DataKey<DiscretionaryText> FORMAT_ATTRIBUTE_EQUAL_SPACE = new DataKey<>("FORMAT_ATTRIBUTE_EQUAL_SPACE", DiscretionaryText.AS_IS);
    public static final DataKey<AttributeValueQuotes> FORMAT_ATTRIBUTE_VALUE_QUOTES = new DataKey<>("FORMAT_ATTRIBUTE_VALUE_QUOTES", AttributeValueQuotes.AS_IS);
    public static final DataKey<AttributeImplicitName> FORMAT_ATTRIBUTE_ID = new DataKey<>("FORMAT_ATTRIBUTE_ID", AttributeImplicitName.AS_IS);
    public static final DataKey<AttributeImplicitName> FORMAT_ATTRIBUTE_CLASS = new DataKey<>("FORMAT_ATTRIBUTE_CLASS", AttributeImplicitName.AS_IS);

    private AttributesExtension() {
    }

    public static AttributesExtension create() {
        return new AttributesExtension();
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
        if (mutableDataHolder.contains(FENCED_CODE_INFO_ATTRIBUTES) && FENCED_CODE_INFO_ATTRIBUTES.get(mutableDataHolder).booleanValue() && !mutableDataHolder.contains(FENCED_CODE_ADD_ATTRIBUTES)) {
            mutableDataHolder.set((DataKey<DataKey<FencedCodeAddType>>) FENCED_CODE_ADD_ATTRIBUTES, (DataKey<FencedCodeAddType>) FencedCodeAddType.ADD_TO_PRE);
        }
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.postProcessorFactory(new AttributesNodePostProcessor.Factory());
        builder.customInlineParserExtensionFactory(new AttributesInlineParserExtension.Factory());
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(new AttributesNodeFormatter.Factory());
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (ASSIGN_TEXT_ATTRIBUTES.get(builder).booleanValue()) {
            builder.nodeRendererFactory(new AttributesNodeRenderer.Factory());
        }
        builder.attributeProviderFactory((AttributeProviderFactory) new AttributesAttributeProvider.Factory());
    }

    @Override // com.vladsch.flexmark.html.RendererExtension
    public void extend(RendererBuilder rendererBuilder, String str) {
        rendererBuilder.attributeProviderFactory(new AttributesAttributeProvider.Factory());
    }
}
