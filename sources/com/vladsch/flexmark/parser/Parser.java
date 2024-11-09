package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.block.BlockPreProcessorFactory;
import com.vladsch.flexmark.parser.block.CustomBlockParserFactory;
import com.vladsch.flexmark.parser.block.ParagraphPreProcessorFactory;
import com.vladsch.flexmark.parser.delimiter.DelimiterProcessor;
import com.vladsch.flexmark.parser.internal.DocumentParser;
import com.vladsch.flexmark.parser.internal.InlineParserImpl;
import com.vladsch.flexmark.parser.internal.LinkRefProcessorData;
import com.vladsch.flexmark.parser.internal.PostProcessorManager;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.IParse;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeRepository;
import com.vladsch.flexmark.util.builder.BuilderBase;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.DataSet;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.data.SharedDataKeys;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.misc.Extension;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.ReplacedBasedSequence;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/Parser.class */
public class Parser implements IParse {
    public static final DataKey<Collection<Extension>> EXTENSIONS = SharedDataKeys.EXTENSIONS;
    public static final DataKey<KeepType> REFERENCES_KEEP = new DataKey<>("REFERENCES_KEEP", KeepType.FIRST);
    public static final DataKey<ReferenceRepository> REFERENCES = new DataKey<>("REFERENCES", new ReferenceRepository(null), ReferenceRepository::new);
    public static final DataKey<Boolean> ASTERISK_DELIMITER_PROCESSOR = new DataKey<>("ASTERISK_DELIMITER_PROCESSOR", Boolean.TRUE);
    public static final DataKey<Boolean> TRACK_DOCUMENT_LINES = new DataKey<>("TRACK_DOCUMENT_LINES", Boolean.FALSE);
    public static final DataKey<Boolean> BLOCK_QUOTE_PARSER = new DataKey<>("BLOCK_QUOTE_PARSER", Boolean.TRUE);
    public static final DataKey<Boolean> BLOCK_QUOTE_EXTEND_TO_BLANK_LINE = new DataKey<>("BLOCK_QUOTE_EXTEND_TO_BLANK_LINE", Boolean.FALSE);
    public static final DataKey<Boolean> BLOCK_QUOTE_IGNORE_BLANK_LINE = new DataKey<>("BLOCK_QUOTE_IGNORE_BLANK_LINE", Boolean.FALSE);
    public static final DataKey<Boolean> BLOCK_QUOTE_ALLOW_LEADING_SPACE = new DataKey<>("BLOCK_QUOTE_ALLOW_LEADING_SPACE", Boolean.TRUE);
    public static final DataKey<Boolean> BLOCK_QUOTE_INTERRUPTS_PARAGRAPH = new DataKey<>("BLOCK_QUOTE_INTERRUPTS_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> BLOCK_QUOTE_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("BLOCK_QUOTE_INTERRUPTS_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> BLOCK_QUOTE_WITH_LEAD_SPACES_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("BLOCK_QUOTE_WITH_LEAD_SPACES_INTERRUPTS_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> FENCED_CODE_BLOCK_PARSER = new DataKey<>("FENCED_CODE_BLOCK_PARSER", Boolean.TRUE);
    public static final DataKey<Boolean> MATCH_CLOSING_FENCE_CHARACTERS = new DataKey<>("MATCH_CLOSING_FENCE_CHARACTERS", Boolean.TRUE);
    public static final DataKey<Boolean> FENCED_CODE_CONTENT_BLOCK = new DataKey<>("FENCED_CODE_CONTENT_BLOCK", Boolean.FALSE);
    public static final DataKey<Boolean> CODE_SOFT_LINE_BREAKS = new DataKey<>("CODE_SOFT_LINE_BREAKS", Boolean.FALSE);
    public static final DataKey<Boolean> HARD_LINE_BREAK_LIMIT = new DataKey<>("HARD_LINE_BREAK_LIMIT", Boolean.FALSE);
    public static final DataKey<Boolean> HEADING_PARSER = new DataKey<>("HEADING_PARSER", Boolean.TRUE);
    public static final DataKey<Integer> HEADING_SETEXT_MARKER_LENGTH = new DataKey<>("HEADING_SETEXT_MARKER_LENGTH", 1);
    public static final DataKey<Boolean> HEADING_NO_ATX_SPACE = SharedDataKeys.HEADING_NO_ATX_SPACE;
    public static final DataKey<Boolean> ESCAPE_HEADING_NO_ATX_SPACE = SharedDataKeys.ESCAPE_HEADING_NO_ATX_SPACE;
    public static final DataKey<Boolean> HEADING_NO_EMPTY_HEADING_WITHOUT_SPACE = new DataKey<>("HEADING_NO_EMPTY_HEADING_WITHOUT_SPACE", Boolean.FALSE);
    public static final DataKey<Boolean> HEADING_NO_LEAD_SPACE = new DataKey<>("HEADING_NO_LEAD_SPACE", Boolean.FALSE);
    public static final DataKey<Boolean> HEADING_CAN_INTERRUPT_ITEM_PARAGRAPH = new DataKey<>("HEADING_CAN_INTERRUPT_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> HTML_BLOCK_PARSER = new DataKey<>("HTML_BLOCK_PARSER", Boolean.TRUE);
    public static final DataKey<Boolean> HTML_COMMENT_BLOCKS_INTERRUPT_PARAGRAPH = new DataKey<>("HTML_COMMENT_BLOCKS_INTERRUPT_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> HTML_FOR_TRANSLATOR = SharedDataKeys.HTML_FOR_TRANSLATOR;
    public static final DataKey<Boolean> INLINE_DELIMITER_DIRECTIONAL_PUNCTUATIONS = new DataKey<>("INLINE_DELIMITER_DIRECTIONAL_PUNCTUATIONS", Boolean.FALSE);
    public static final DataKey<Boolean> INDENTED_CODE_BLOCK_PARSER = new DataKey<>("INDENTED_CODE_BLOCK_PARSER", Boolean.TRUE);
    public static final DataKey<Boolean> INDENTED_CODE_NO_TRAILING_BLANK_LINES = new DataKey<>("INDENTED_CODE_NO_TRAILING_BLANK_LINES", Boolean.TRUE);
    public static final DataKey<Boolean> INTELLIJ_DUMMY_IDENTIFIER = SharedDataKeys.INTELLIJ_DUMMY_IDENTIFIER;
    public static final DataKey<Boolean> MATCH_NESTED_LINK_REFS_FIRST = new DataKey<>("MATCH_NESTED_LINK_REFS_FIRST", Boolean.TRUE);
    public static final DataKey<Boolean> PARSE_INNER_HTML_COMMENTS = SharedDataKeys.PARSE_INNER_HTML_COMMENTS;
    public static final DataKey<Boolean> PARSE_MULTI_LINE_IMAGE_URLS = new DataKey<>("PARSE_MULTI_LINE_IMAGE_URLS", Boolean.FALSE);
    public static final DataKey<Boolean> PARSE_JEKYLL_MACROS_IN_URLS = new DataKey<>("PARSE_JEKYLL_MACROS_IN_URLS", Boolean.FALSE);
    public static final DataKey<Boolean> SPACE_IN_LINK_URLS = new DataKey<>("SPACE_IN_LINK_URLS", Boolean.FALSE);
    public static final DataKey<Boolean> SPACE_IN_LINK_ELEMENTS = new DataKey<>("SPACE_IN_LINK_ELEMENTS", Boolean.FALSE);
    public static final DataKey<Boolean> WWW_AUTO_LINK_ELEMENT = new DataKey<>("WWW_AUTO_LINK_ELEMENT", Boolean.FALSE);
    public static final DataKey<Boolean> LINK_TEXT_PRIORITY_OVER_LINK_REF = new DataKey<>("LINK_TEXT_PRIORITY_OVER_LINK_REF", Boolean.FALSE);
    public static final DataKey<Boolean> REFERENCE_PARAGRAPH_PRE_PROCESSOR = new DataKey<>("REFERENCE_BLOCK_PRE_PROCESSOR", Boolean.TRUE);
    public static final DataKey<Boolean> THEMATIC_BREAK_PARSER = new DataKey<>("THEMATIC_BREAK_PARSER", Boolean.TRUE);
    public static final DataKey<Boolean> THEMATIC_BREAK_RELAXED_START = new DataKey<>("THEMATIC_BREAK_RELAXED_START", Boolean.TRUE);
    public static final DataKey<Boolean> UNDERSCORE_DELIMITER_PROCESSOR = new DataKey<>("UNDERSCORE_DELIMITER_PROCESSOR", Boolean.TRUE);
    public static final DataKey<Boolean> BLANK_LINES_IN_AST = SharedDataKeys.BLANK_LINES_IN_AST;
    public static final DataKey<Boolean> USE_HARDCODED_LINK_ADDRESS_PARSER = new DataKey<>("USE_HARDCODED_LINK_ADDRESS_PARSER", Boolean.TRUE);
    public static final DataKey<Boolean> STRONG_WRAPS_EMPHASIS = new DataKey<>("STRONG_WRAPS_EMPHASIS", Boolean.FALSE);
    public static final DataKey<Boolean> LINKS_ALLOW_MATCHED_PARENTHESES = new DataKey<>("LINKS_ALLOW_MATCHED_PARENTHESES", Boolean.TRUE);
    public static final DataKey<Boolean> LIST_BLOCK_PARSER = new DataKey<>("LIST_BLOCK_PARSER", Boolean.TRUE);
    public static final DataKey<ParserEmulationProfile> PARSER_EMULATION_PROFILE = new DataKey<>("PARSER_EMULATION_PROFILE", ParserEmulationProfile.COMMONMARK);
    public static final DataKey<Boolean> HTML_BLOCK_DEEP_PARSER = new DataKey<>("HTML_BLOCK_DEEP_PARSER", Boolean.FALSE);
    public static final DataKey<Boolean> HTML_BLOCK_DEEP_PARSE_NON_BLOCK = new DataKey<>("HTML_BLOCK_DEEP_PARSE_NON_BLOCK", Boolean.TRUE);
    public static final DataKey<Boolean> HTML_BLOCK_COMMENT_ONLY_FULL_LINE = new DataKey<>("HTML_BLOCK_COMMENT_ONLY_FULL_LINE", Boolean.FALSE);
    public static final DataKey<Boolean> HTML_BLOCK_START_ONLY_ON_BLOCK_TAGS = new DataKey<>("HTML_BLOCK_START_ONLY_ON_BLOCK_TAGS", (DataKey) HTML_BLOCK_DEEP_PARSER);
    public static final DataKey<List<String>> HTML_BLOCK_TAGS = new DataKey<>("HTML_BLOCK_TAGS", Arrays.asList("address", "article", FlexmarkHtmlConverter.ASIDE_NODE, "base", "basefont", FlexmarkHtmlConverter.BLOCKQUOTE_NODE, "body", FlexmarkHtmlConverter.CAPTION_NODE, "center", "col", "colgroup", FlexmarkHtmlConverter.DD_NODE, "details", "dialog", "dir", FlexmarkHtmlConverter.DIV_NODE, FlexmarkHtmlConverter.DL_NODE, FlexmarkHtmlConverter.DT_NODE, "fieldset", "figcaption", "figure", "footer", "form", "frame", "frameset", FlexmarkHtmlConverter.H1_NODE, FlexmarkHtmlConverter.H2_NODE, FlexmarkHtmlConverter.H3_NODE, FlexmarkHtmlConverter.H4_NODE, FlexmarkHtmlConverter.H5_NODE, FlexmarkHtmlConverter.H6_NODE, "head", "header", FlexmarkHtmlConverter.HR_NODE, "html", "iframe", "legend", FlexmarkHtmlConverter.LI_NODE, "link", "main", FlexmarkHtmlConverter.MATH_NODE, "menu", "menuitem", "meta", "nav", "noframes", FlexmarkHtmlConverter.OL_NODE, "optgroup", "option", FlexmarkHtmlConverter.P_NODE, "param", "section", "source", "summary", FlexmarkHtmlConverter.TABLE_NODE, FlexmarkHtmlConverter.TBODY_NODE, FlexmarkHtmlConverter.TD_NODE, "tfoot", FlexmarkHtmlConverter.TH_NODE, FlexmarkHtmlConverter.THEAD_NODE, Attribute.TITLE_ATTR, FlexmarkHtmlConverter.TR_NODE, "track", FlexmarkHtmlConverter.UL_NODE));
    public static final DataKey<Boolean> HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS = new DataKey<>("HTML_BL OCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS", Boolean.TRUE);
    public static final DataKey<Boolean> HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE = new DataKey<>("HTML_BL HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE", Boolean.FALSE);
    public static final DataKey<Boolean> HTML_BLOCK_DEEP_PARSE_MARKDOWN_INTERRUPTS_CLOSED = new DataKey<>("HTML_BLOCK_DEEP_PARSE_MARKDOWN_INTERRUPTS_CLOSED", Boolean.FALSE);
    public static final DataKey<Boolean> HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG = new DataKey<>("HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG", Boolean.TRUE);
    public static final DataKey<Boolean> HTML_BLOCK_DEEP_PARSE_INDENTED_CODE_INTERRUPTS = new DataKey<>("HTML_BLOCK_DEEP_PARSE_INDENTED_CODE_INTERRUPTS", Boolean.FALSE);
    public static final DataKey<String> TRANSLATION_HTML_BLOCK_TAG_PATTERN = SharedDataKeys.TRANSLATION_HTML_BLOCK_TAG_PATTERN;
    public static final DataKey<String> TRANSLATION_HTML_INLINE_TAG_PATTERN = SharedDataKeys.TRANSLATION_HTML_INLINE_TAG_PATTERN;
    public static final DataKey<String> TRANSLATION_AUTOLINK_TAG_PATTERN = SharedDataKeys.TRANSLATION_AUTOLINK_TAG_PATTERN;
    public static final DataKey<Integer> LISTS_CODE_INDENT = new DataKey<>("LISTS_CODE_INDENT", 4);
    public static final DataKey<Integer> LISTS_ITEM_INDENT = new DataKey<>("LISTS_ITEM_INDENT", 4);
    public static final DataKey<Integer> LISTS_NEW_ITEM_CODE_INDENT = new DataKey<>("LISTS_NEW_ITEM_CODE_INDENT", 4);
    public static final DataKey<Boolean> LISTS_ITEM_MARKER_SPACE = new DataKey<>("LISTS_ITEM_MARKER_SPACE", Boolean.FALSE);
    public static final DataKey<String[]> LISTS_ITEM_MARKER_SUFFIXES = new DataKey<>("LISTS_ITEM_MARKER_SUFFIXES", new String[0]);
    public static final DataKey<Boolean> LISTS_NUMBERED_ITEM_MARKER_SUFFIXED = new DataKey<>("LISTS_NUMBERED_ITEM_MARKER_SUFFIXED", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_AUTO_LOOSE = new DataKey<>("LISTS_AUTO_LOOSE", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_AUTO_LOOSE_ONE_LEVEL_LISTS = new DataKey<>("LISTS_AUTO_LOOSE_ONE_LEVEL_LISTS", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_LOOSE_WHEN_PREV_HAS_TRAILING_BLANK_LINE = new DataKey<>("LISTS_LOOSE_WHEN_PREV_HAS_TRAILING_BLANK_LINE", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_LOOSE_WHEN_LAST_ITEM_PREV_HAS_TRAILING_BLANK_LINE = new DataKey<>("LISTS_LOOSE_WHEN_LAST_ITEM_PREV_HAS_TRAILING_BLANK_LINE", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_LOOSE_WHEN_HAS_NON_LIST_CHILDREN = new DataKey<>("LISTS_LOOSE_WHEN_HAS_NON_LIST_CHILDREN", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_LOOSE_WHEN_BLANK_LINE_FOLLOWS_ITEM_PARAGRAPH = new DataKey<>("LISTS_LOOSE_WHEN_BLANK_LINE_FOLLOWS_ITEM_PARAGRAPH", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_LOOSE_WHEN_HAS_LOOSE_SUB_ITEM = new DataKey<>("LISTS_LOOSE_WHEN_HAS_LOOSE_SUB_ITEM", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_LOOSE_WHEN_HAS_TRAILING_BLANK_LINE = new DataKey<>("LISTS_LOOSE_WHEN_HAS_TRAILING_BLANK_LINE", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_LOOSE_WHEN_CONTAINS_BLANK_LINE = new DataKey<>("LISTS_LOOSE_WHEN_CONTAINS_BLANK_LINE", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_DELIMITER_MISMATCH_TO_NEW_LIST = new DataKey<>("LISTS_DELIMITER_MISMATCH_TO_NEW_LIST", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_END_ON_DOUBLE_BLANK = new DataKey<>("LISTS_END_ON_DOUBLE_BLANK", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_ITEM_TYPE_MISMATCH_TO_NEW_LIST = new DataKey<>("LISTS_ITEM_TYPE_MISMATCH_TO_NEW_LIST", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_ITEM_TYPE_MISMATCH_TO_SUB_LIST = new DataKey<>("LISTS_ITEM_TYPE_MISMATCH_TO_SUB_LIST", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_ORDERED_ITEM_DOT_ONLY = new DataKey<>("LISTS_ORDERED_ITEM_DOT_ONLY", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_ORDERED_LIST_MANUAL_START = new DataKey<>("LISTS_ORDERED_LIST_MANUAL_START", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_ITEM_CONTENT_AFTER_SUFFIX = new DataKey<>("LISTS_ITEM_CONTENT_AFTER_SUFFIX", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_BULLET_ITEM_INTERRUPTS_PARAGRAPH = new DataKey<>("LISTS_BULLET_ITEM_INTERRUPTS_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_ORDERED_ITEM_INTERRUPTS_PARAGRAPH = new DataKey<>("LISTS_ORDERED_ITEM_INTERRUPTS_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_ORDERED_NON_ONE_ITEM_INTERRUPTS_PARAGRAPH = new DataKey<>("LISTS_ORDERED_NON_ONE_ITEM_INTERRUPTS_PARAGRAPH", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_EMPTY_BULLET_ITEM_INTERRUPTS_PARAGRAPH = new DataKey<>("LISTS_EMPTY_BULLET_ITEM_INTERRUPTS_PARAGRAPH", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_EMPTY_ORDERED_ITEM_INTERRUPTS_PARAGRAPH = new DataKey<>("LISTS_EMPTY_ORDERED_ITEM_INTERRUPTS_PARAGRAPH", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_EMPTY_ORDERED_NON_ONE_ITEM_INTERRUPTS_PARAGRAPH = new DataKey<>("LISTS_EMPTY_ORDERED_NON_ONE_ITEM_INTERRUPTS_PARAGRAPH", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_BULLET_ITEM_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("LISTS_BULLET_ITEM_INTERRUPTS_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_ORDERED_ITEM_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("LISTS_ORDERED_ITEM_INTERRUPTS_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_ORDERED_NON_ONE_ITEM_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("LISTS_ORDERED_NON_ONE_ITEM_INTERRUPTS_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_EMPTY_BULLET_ITEM_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("LISTS_EMPTY_BULLET_ITEM_INTERRUPTS_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_EMPTY_ORDERED_ITEM_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("LISTS_EMPTY_ORDERED_ITEM_INTERRUPTS_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_EMPTY_ORDERED_NON_ONE_ITEM_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("LISTS_EMPTY_ORDERED_NON_ONE_ITEM_INTERRUPTS_ITEM_PARAGRAPH", Boolean.TRUE);
    public static final DataKey<Boolean> LISTS_EMPTY_BULLET_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("LISTS_EMPTY_BULLET_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_EMPTY_ORDERED_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("LISTS_EMPTY_ORDERED_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH", Boolean.FALSE);
    public static final DataKey<Boolean> LISTS_EMPTY_ORDERED_NON_ONE_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH = new DataKey<>("LISTS_EMPTY_ORDERED_NON_ONE_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH", Boolean.FALSE);
    public static final DataKey<String> LISTS_ITEM_PREFIX_CHARS = new DataKey<>("LISTS_ITEM_PREFIX_CHARS", "+*-");
    public static final DataKey<List<SpecialLeadInHandler>> SPECIAL_LEAD_IN_HANDLERS = new DataKey<>("SPECIAL_LEAD_IN_HANDLERS", Collections.emptyList());
    public static final DataKey<Integer> CODE_BLOCK_INDENT = new DataKey<>("CODE_BLOCK_INDENT", (DataKey) LISTS_ITEM_INDENT);
    private final List<CustomBlockParserFactory> blockParserFactories;
    private final Map<Character, DelimiterProcessor> delimiterProcessors;
    private final BitSet delimiterCharacters;
    private final BitSet specialCharacters;
    private final List<PostProcessorManager.PostProcessorDependencyStage> postProcessorDependencies;
    private final List<List<ParagraphPreProcessorFactory>> paragraphPreProcessorFactories;
    private final List<List<BlockPreProcessorFactory>> blockPreProcessorDependencies;
    private final LinkRefProcessorData linkRefProcessors;
    private final List<InlineParserExtensionFactory> inlineParserExtensionFactories;
    private final InlineParserFactory inlineParserFactory;
    private final DataHolder options;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/Parser$ParserExtension.class */
    public interface ParserExtension extends Extension {
        void parserOptions(MutableDataHolder mutableDataHolder);

        void extend(Builder builder);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/Parser$ReferenceHoldingExtension.class */
    public interface ReferenceHoldingExtension extends Extension {
        boolean transferReferences(MutableDataHolder mutableDataHolder, DataHolder dataHolder);
    }

    Parser(Builder builder) {
        DataSet immutable = builder.toImmutable();
        this.blockParserFactories = DocumentParser.calculateBlockParserFactories(immutable, builder.blockParserFactories);
        ArrayList arrayList = new ArrayList(builder.specialLeadInHandlers);
        Iterator<CustomBlockParserFactory> it = this.blockParserFactories.iterator();
        while (it.hasNext()) {
            SpecialLeadInHandler leadInHandler = it.next().getLeadInHandler(immutable);
            if (leadInHandler != null) {
                arrayList.add(leadInHandler);
            }
        }
        MutableDataSet mutableDataSet = new MutableDataSet(builder);
        mutableDataSet.set((DataKey<DataKey<List<SpecialLeadInHandler>>>) SPECIAL_LEAD_IN_HANDLERS, (DataKey<List<SpecialLeadInHandler>>) arrayList);
        this.options = mutableDataSet.toImmutable();
        this.inlineParserFactory = builder.inlineParserFactory == null ? DocumentParser.INLINE_PARSER_FACTORY : builder.inlineParserFactory;
        this.paragraphPreProcessorFactories = DocumentParser.calculateParagraphPreProcessors(immutable, builder.paragraphPreProcessorFactories, this.inlineParserFactory);
        this.blockPreProcessorDependencies = DocumentParser.calculateBlockPreProcessors(immutable, builder.blockPreProcessorFactories);
        this.delimiterProcessors = InlineParserImpl.calculateDelimiterProcessors(immutable, builder.delimiterProcessors);
        this.delimiterCharacters = InlineParserImpl.calculateDelimiterCharacters(immutable, this.delimiterProcessors.keySet());
        this.linkRefProcessors = InlineParserImpl.calculateLinkRefProcessors(immutable, builder.linkRefProcessors);
        this.specialCharacters = InlineParserImpl.calculateSpecialCharacters(immutable, this.delimiterCharacters);
        this.postProcessorDependencies = PostProcessorManager.calculatePostProcessors(immutable, builder.postProcessorFactories);
        this.inlineParserExtensionFactories = builder.inlineParserExtensionFactories;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(DataHolder dataHolder) {
        return new Builder(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.ast.IParse
    public Document parse(BasedSequence basedSequence) {
        if (basedSequence instanceof ReplacedBasedSequence) {
            throw new IllegalArgumentException("Parser.parse() does not support BasedSequences with replaced or non-contiguous segments.\nUse BasedSequence.of(input.toString()) to convert to contiguous based sequence.");
        }
        return postProcess(new DocumentParser(this.options, this.blockParserFactories, this.paragraphPreProcessorFactories, this.blockPreProcessorDependencies, this.inlineParserFactory.inlineParser(this.options, this.specialCharacters, this.delimiterCharacters, this.delimiterProcessors, this.linkRefProcessors, this.inlineParserExtensionFactories)).parse(basedSequence));
    }

    @Override // com.vladsch.flexmark.util.ast.IParse
    public Document parse(String str) {
        return postProcess(new DocumentParser(this.options, this.blockParserFactories, this.paragraphPreProcessorFactories, this.blockPreProcessorDependencies, this.inlineParserFactory.inlineParser(this.options, this.specialCharacters, this.delimiterCharacters, this.delimiterProcessors, this.linkRefProcessors, this.inlineParserExtensionFactories)).parse(BasedSequence.of(str)));
    }

    @Override // com.vladsch.flexmark.util.ast.IParse
    public Document parseReader(Reader reader) {
        return postProcess(new DocumentParser(this.options, this.blockParserFactories, this.paragraphPreProcessorFactories, this.blockPreProcessorDependencies, this.inlineParserFactory.inlineParser(this.options, this.specialCharacters, this.delimiterCharacters, this.delimiterProcessors, this.linkRefProcessors, this.inlineParserExtensionFactories)).parse(reader));
    }

    private Document postProcess(Document document) {
        return PostProcessorManager.processDocument(document, this.postProcessorDependencies);
    }

    @Override // com.vladsch.flexmark.util.ast.IParse
    public DataHolder getOptions() {
        return this.options;
    }

    @Override // com.vladsch.flexmark.util.ast.IParse
    public boolean transferReferences(Document document, Document document2, Boolean bool) {
        boolean z;
        boolean z2 = false;
        if (this.options.contains(EXTENSIONS)) {
            for (Extension extension : EXTENSIONS.get(this.options)) {
                if ((extension instanceof ReferenceHoldingExtension) && ((ReferenceHoldingExtension) extension).transferReferences(document, document2)) {
                    z2 = true;
                }
            }
        }
        if (document.contains(REFERENCES) && document2.contains(REFERENCES)) {
            ReferenceRepository referenceRepository = REFERENCES.get(document);
            ReferenceRepository referenceRepository2 = REFERENCES.get(document2);
            if (bool != null) {
                z = bool.booleanValue();
            } else {
                z = REFERENCES_KEEP.get(document) == KeepType.FIRST;
            }
            if (transferReferences(referenceRepository, referenceRepository2, z)) {
                z2 = true;
            }
        }
        if (z2) {
            document.set((DataKey<DataKey<Boolean>>) HtmlRenderer.RECHECK_UNDEFINED_REFERENCES, (DataKey<Boolean>) Boolean.TRUE);
        }
        return z2;
    }

    public static <T extends Node> boolean transferReferences(NodeRepository<T> nodeRepository, NodeRepository<T> nodeRepository2, boolean z) {
        return NodeRepository.transferReferences(nodeRepository, nodeRepository2, z, null);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/Parser$Builder.class */
    public static class Builder extends BuilderBase<Builder> {
        final List<CustomBlockParserFactory> blockParserFactories;
        final List<DelimiterProcessor> delimiterProcessors;
        final List<PostProcessorFactory> postProcessorFactories;
        final List<ParagraphPreProcessorFactory> paragraphPreProcessorFactories;
        final List<BlockPreProcessorFactory> blockPreProcessorFactories;
        final List<LinkRefProcessorFactory> linkRefProcessors;
        final List<InlineParserExtensionFactory> inlineParserExtensionFactories;
        InlineParserFactory inlineParserFactory;
        final List<SpecialLeadInHandler> specialLeadInHandlers;

        public Builder(DataHolder dataHolder) {
            super(dataHolder);
            this.blockParserFactories = new ArrayList();
            this.delimiterProcessors = new ArrayList();
            this.postProcessorFactories = new ArrayList();
            this.paragraphPreProcessorFactories = new ArrayList();
            this.blockPreProcessorFactories = new ArrayList();
            this.linkRefProcessors = new ArrayList();
            this.inlineParserExtensionFactories = new ArrayList();
            this.inlineParserFactory = null;
            this.specialLeadInHandlers = new ArrayList();
            loadExtensions();
        }

        public Builder() {
            this.blockParserFactories = new ArrayList();
            this.delimiterProcessors = new ArrayList();
            this.postProcessorFactories = new ArrayList();
            this.paragraphPreProcessorFactories = new ArrayList();
            this.blockPreProcessorFactories = new ArrayList();
            this.linkRefProcessors = new ArrayList();
            this.inlineParserExtensionFactories = new ArrayList();
            this.inlineParserFactory = null;
            this.specialLeadInHandlers = new ArrayList();
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        public Parser build() {
            return new Parser(this);
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        protected void removeApiPoint(Object obj) {
            if (obj instanceof CustomBlockParserFactory) {
                this.blockParserFactories.remove(obj);
                return;
            }
            if (obj instanceof DelimiterProcessor) {
                this.delimiterProcessors.remove(obj);
                return;
            }
            if (obj instanceof PostProcessorFactory) {
                this.postProcessorFactories.remove(obj);
                return;
            }
            if (obj instanceof ParagraphPreProcessorFactory) {
                this.paragraphPreProcessorFactories.remove(obj);
                return;
            }
            if (obj instanceof BlockPreProcessorFactory) {
                this.blockPreProcessorFactories.remove(obj);
                return;
            }
            if (obj instanceof LinkRefProcessorFactory) {
                this.linkRefProcessors.remove(obj);
                return;
            }
            if (obj instanceof SpecialLeadInHandler) {
                this.specialLeadInHandlers.remove(obj);
            } else if (obj instanceof InlineParserExtensionFactory) {
                this.inlineParserExtensionFactories.remove(obj);
            } else {
                if (obj instanceof InlineParserFactory) {
                    this.inlineParserFactory = null;
                    return;
                }
                throw new IllegalStateException("Unknown data point type: " + obj.getClass().getName());
            }
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        protected void preloadExtension(Extension extension) {
            if (extension instanceof ParserExtension) {
                ((ParserExtension) extension).parserOptions(this);
            }
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        protected boolean loadExtension(Extension extension) {
            if (extension instanceof ParserExtension) {
                ((ParserExtension) extension).extend(this);
                return true;
            }
            return false;
        }

        public Builder customBlockParserFactory(CustomBlockParserFactory customBlockParserFactory) {
            this.blockParserFactories.add(customBlockParserFactory);
            addExtensionApiPoint(customBlockParserFactory);
            return this;
        }

        public Builder customInlineParserExtensionFactory(InlineParserExtensionFactory inlineParserExtensionFactory) {
            this.inlineParserExtensionFactories.add(inlineParserExtensionFactory);
            addExtensionApiPoint(inlineParserExtensionFactory);
            return this;
        }

        public Builder customInlineParserFactory(InlineParserFactory inlineParserFactory) {
            if (this.inlineParserFactory != null) {
                throw new IllegalStateException("custom inline parser factory is already set to " + this.inlineParserFactory.getClass().getName());
            }
            this.inlineParserFactory = inlineParserFactory;
            addExtensionApiPoint(inlineParserFactory);
            return this;
        }

        public Builder customDelimiterProcessor(DelimiterProcessor delimiterProcessor) {
            this.delimiterProcessors.add(delimiterProcessor);
            addExtensionApiPoint(delimiterProcessor);
            return this;
        }

        public Builder postProcessorFactory(PostProcessorFactory postProcessorFactory) {
            this.postProcessorFactories.add(postProcessorFactory);
            addExtensionApiPoint(postProcessorFactory);
            return this;
        }

        public Builder paragraphPreProcessorFactory(ParagraphPreProcessorFactory paragraphPreProcessorFactory) {
            this.paragraphPreProcessorFactories.add(paragraphPreProcessorFactory);
            addExtensionApiPoint(paragraphPreProcessorFactory);
            return this;
        }

        public Builder blockPreProcessorFactory(BlockPreProcessorFactory blockPreProcessorFactory) {
            this.blockPreProcessorFactories.add(blockPreProcessorFactory);
            addExtensionApiPoint(blockPreProcessorFactory);
            return this;
        }

        public Builder linkRefProcessorFactory(LinkRefProcessorFactory linkRefProcessorFactory) {
            this.linkRefProcessors.add(linkRefProcessorFactory);
            addExtensionApiPoint(linkRefProcessorFactory);
            return this;
        }

        public Builder specialLeadInHandler(SpecialLeadInHandler specialLeadInHandler) {
            this.specialLeadInHandlers.add(specialLeadInHandler);
            addExtensionApiPoint(specialLeadInHandler);
            return this;
        }
    }

    public static MutableDataHolder addExtensions(MutableDataHolder mutableDataHolder, Extension... extensionArr) {
        Collection<Extension> collection = EXTENSIONS.get(mutableDataHolder);
        ArrayList arrayList = new ArrayList(Arrays.asList(extensionArr));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add((Extension) it.next());
        }
        mutableDataHolder.set((DataKey<DataKey<Collection<Extension>>>) EXTENSIONS, (DataKey<Collection<Extension>>) arrayList);
        return mutableDataHolder;
    }

    public static MutableDataHolder removeExtensions(MutableDataHolder mutableDataHolder, Class... clsArr) {
        Collection<Extension> collection = EXTENSIONS.get(mutableDataHolder);
        HashSet hashSet = new HashSet();
        for (Extension extension : collection) {
            boolean z = true;
            int length = clsArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (!clsArr[i].isInstance(extension)) {
                    i++;
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                hashSet.add(extension);
            }
        }
        mutableDataHolder.set((DataKey<DataKey<Collection<Extension>>>) EXTENSIONS, (DataKey<Collection<Extension>>) hashSet);
        return mutableDataHolder;
    }
}
