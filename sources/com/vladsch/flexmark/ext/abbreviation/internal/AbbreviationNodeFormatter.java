package com.vladsch.flexmark.ext.abbreviation.internal;

import com.vladsch.flexmark.ext.abbreviation.Abbreviation;
import com.vladsch.flexmark.ext.abbreviation.AbbreviationBlock;
import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.formatter.NodeRepositoryFormatter;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationNodeFormatter.class */
public class AbbreviationNodeFormatter extends NodeRepositoryFormatter<AbbreviationRepository, AbbreviationBlock, Abbreviation> {
    public static final DataKey<Map<String, String>> ABBREVIATION_TRANSLATION_MAP = new DataKey<>("ABBREVIATION_TRANSLATION_MAP", new HashMap());
    public static final DataKey<Map<String, String>> ABBREVIATION_UNIQUIFICATION_MAP = new DataKey<>("ABBREVIATION_UNIQUIFICATION_MAP", new HashMap());
    private final AbbreviationFormatOptions options;
    private final boolean transformUnderscores;
    private final boolean makeMergedAbbreviationsUnique;

    public AbbreviationNodeFormatter(DataHolder dataHolder) {
        super(dataHolder, ABBREVIATION_TRANSLATION_MAP, ABBREVIATION_UNIQUIFICATION_MAP);
        this.options = new AbbreviationFormatOptions(dataHolder);
        String format = String.format(Formatter.TRANSLATION_ID_FORMAT.get(dataHolder), 1);
        this.transformUnderscores = format.startsWith(JavaConstant.Dynamic.DEFAULT_NAME) && format.endsWith(JavaConstant.Dynamic.DEFAULT_NAME);
        this.makeMergedAbbreviationsUnique = AbbreviationExtension.MAKE_MERGED_ABBREVIATIONS_UNIQUE.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    protected boolean makeReferencesUnique() {
        return this.makeMergedAbbreviationsUnique;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public AbbreviationRepository getRepository(DataHolder dataHolder) {
        return AbbreviationExtension.ABBREVIATIONS.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ElementPlacement getReferencePlacement() {
        return this.options.abbreviationsPlacement;
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ElementPlacementSort getReferenceSort() {
        return this.options.abbreviationsSort;
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public String modifyTransformedReference(String str, NodeFormatterContext nodeFormatterContext) {
        if (this.transformUnderscores && nodeFormatterContext.isTransformingText()) {
            if (str.startsWith("-") && str.endsWith("-")) {
                str = JavaConstant.Dynamic.DEFAULT_NAME + str.substring(1, str.length() - 1) + JavaConstant.Dynamic.DEFAULT_NAME;
            } else if (str.startsWith(JavaConstant.Dynamic.DEFAULT_NAME) && str.endsWith(JavaConstant.Dynamic.DEFAULT_NAME)) {
                str = "-" + str.substring(1, str.length() - 1) + "-";
            }
        }
        return str;
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public void renderReferenceBlock(AbbreviationBlock abbreviationBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append((CharSequence) abbreviationBlock.getOpeningMarker());
        markdownWriter.append((CharSequence) transformReferenceId(abbreviationBlock.getText().toString(), nodeFormatterContext));
        markdownWriter.append((CharSequence) abbreviationBlock.getClosingMarker()).append(' ');
        markdownWriter.appendTranslating(abbreviationBlock.getAbbreviation()).line();
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(Abbreviation.class, this::render), new NodeFormattingHandler(AbbreviationBlock.class, this::render)));
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        if (this.options.abbreviationsPlacement.isNoChange() || !this.options.abbreviationsSort.isUnused()) {
            return null;
        }
        return new HashSet(Arrays.asList(Abbreviation.class));
    }

    private void render(AbbreviationBlock abbreviationBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        renderReference(abbreviationBlock, nodeFormatterContext, markdownWriter);
    }

    private void render(Abbreviation abbreviation, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (nodeFormatterContext.isTransformingText()) {
            markdownWriter.append((CharSequence) transformReferenceId(abbreviation.getChars().toString(), nodeFormatterContext));
        } else {
            markdownWriter.append((CharSequence) abbreviation.getChars());
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new AbbreviationNodeFormatter(dataHolder);
        }
    }
}
