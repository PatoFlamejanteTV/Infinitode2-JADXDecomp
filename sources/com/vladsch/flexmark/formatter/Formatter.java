package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.formatter.internal.CoreNodeFormatter;
import com.vladsch.flexmark.formatter.internal.FormatControlProcessor;
import com.vladsch.flexmark.formatter.internal.MergeContextImpl;
import com.vladsch.flexmark.formatter.internal.MergeLinkResolver;
import com.vladsch.flexmark.formatter.internal.TranslationHandlerImpl;
import com.vladsch.flexmark.html.AttributeProviderFactory;
import com.vladsch.flexmark.html.LinkResolver;
import com.vladsch.flexmark.html.LinkResolverFactory;
import com.vladsch.flexmark.html.renderer.HeaderIdGenerator;
import com.vladsch.flexmark.html.renderer.HeaderIdGeneratorFactory;
import com.vladsch.flexmark.html.renderer.HtmlIdGenerator;
import com.vladsch.flexmark.html.renderer.HtmlIdGeneratorFactory;
import com.vladsch.flexmark.html.renderer.LinkResolverBasicContext;
import com.vladsch.flexmark.html.renderer.LinkResolverContext;
import com.vladsch.flexmark.html.renderer.LinkStatus;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.BlankLine;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.IRender;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeCollectingVisitor;
import com.vladsch.flexmark.util.builder.BuilderBase;
import com.vladsch.flexmark.util.collection.SubClassingBag;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.data.NullableDataKey;
import com.vladsch.flexmark.util.data.ScopedDataSet;
import com.vladsch.flexmark.util.data.SharedDataKeys;
import com.vladsch.flexmark.util.dependency.DependencyResolver;
import com.vladsch.flexmark.util.format.CharWidthProvider;
import com.vladsch.flexmark.util.format.TableFormatOptions;
import com.vladsch.flexmark.util.format.TrackedOffset;
import com.vladsch.flexmark.util.format.TrackedOffsetList;
import com.vladsch.flexmark.util.format.TrackedOffsetUtils;
import com.vladsch.flexmark.util.format.options.BlockQuoteMarker;
import com.vladsch.flexmark.util.format.options.CodeFenceMarker;
import com.vladsch.flexmark.util.format.options.DiscretionaryText;
import com.vladsch.flexmark.util.format.options.ElementAlignment;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;
import com.vladsch.flexmark.util.format.options.EqualizeTrailingMarker;
import com.vladsch.flexmark.util.format.options.HeadingStyle;
import com.vladsch.flexmark.util.format.options.ListBulletMarker;
import com.vladsch.flexmark.util.format.options.ListNumberedMarker;
import com.vladsch.flexmark.util.format.options.ListSpacing;
import com.vladsch.flexmark.util.format.options.TableCaptionHandling;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Extension;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/Formatter.class */
public class Formatter implements IRender {
    private final DataHolder options;
    final List<LinkResolverFactory> linkResolverFactories;
    final List<NodeFormatterFactory> nodeFormatterFactories;
    final HeaderIdGeneratorFactory idGeneratorFactory;
    public static final Document[] EMPTY_DOCUMENTS = new Document[0];
    public static final DataKey<Integer> FORMAT_FLAGS = new DataKey<>("FORMAT_FLAGS", Integer.valueOf(LineAppendable.F_TRIM_LEADING_WHITESPACE | LineAppendable.F_TRIM_LEADING_EOL));

    @Deprecated
    public static final int FORMAT_CONVERT_TABS = LineAppendable.F_CONVERT_TABS;

    @Deprecated
    public static final int FORMAT_COLLAPSE_WHITESPACE = LineAppendable.F_COLLAPSE_WHITESPACE;

    @Deprecated
    public static final int FORMAT_SUPPRESS_TRAILING_WHITESPACE = LineAppendable.F_TRIM_TRAILING_WHITESPACE;

    @Deprecated
    public static final int FORMAT_ALL_OPTIONS = LineAppendable.F_FORMAT_ALL;
    public static final DataKey<Boolean> GENERATE_HEADER_ID = new DataKey<>("GENERATE_HEADER_ID", Boolean.FALSE);
    public static final DataKey<Integer> MAX_BLANK_LINES = SharedDataKeys.FORMATTER_MAX_BLANK_LINES;
    public static final DataKey<Integer> MAX_TRAILING_BLANK_LINES = SharedDataKeys.FORMATTER_MAX_TRAILING_BLANK_LINES;
    public static final DataKey<Integer> RIGHT_MARGIN = new DataKey<>("RIGHT_MARGIN", 0);
    public static final DataKey<Boolean> APPLY_SPECIAL_LEAD_IN_HANDLERS = SharedDataKeys.APPLY_SPECIAL_LEAD_IN_HANDLERS;
    public static final DataKey<Boolean> ESCAPE_SPECIAL_CHARS = SharedDataKeys.ESCAPE_SPECIAL_CHARS;
    public static final DataKey<Boolean> ESCAPE_NUMBERED_LEAD_IN = SharedDataKeys.ESCAPE_NUMBERED_LEAD_IN;
    public static final DataKey<Boolean> UNESCAPE_SPECIAL_CHARS = SharedDataKeys.UNESCAPE_SPECIAL_CHARS;
    public static final DataKey<DiscretionaryText> SPACE_AFTER_ATX_MARKER = new DataKey<>("SPACE_AFTER_ATX_MARKER", DiscretionaryText.ADD);
    public static final DataKey<Boolean> SETEXT_HEADING_EQUALIZE_MARKER = new DataKey<>("SETEXT_HEADING_EQUALIZE_MARKER", Boolean.TRUE);
    public static final DataKey<EqualizeTrailingMarker> ATX_HEADING_TRAILING_MARKER = new DataKey<>("ATX_HEADING_TRAILING_MARKER", EqualizeTrailingMarker.AS_IS);
    public static final DataKey<HeadingStyle> HEADING_STYLE = new DataKey<>("HEADING_STYLE", HeadingStyle.AS_IS);
    public static final NullableDataKey<String> THEMATIC_BREAK = new NullableDataKey<>("THEMATIC_BREAK");
    public static final DataKey<Boolean> BLOCK_QUOTE_BLANK_LINES = SharedDataKeys.BLOCK_QUOTE_BLANK_LINES;
    public static final DataKey<BlockQuoteMarker> BLOCK_QUOTE_MARKERS = new DataKey<>("BLOCK_QUOTE_MARKERS", BlockQuoteMarker.ADD_COMPACT_WITH_SPACE);
    public static final DataKey<Boolean> INDENTED_CODE_MINIMIZE_INDENT = new DataKey<>("INDENTED_CODE_MINIMIZE_INDENT", Boolean.TRUE);
    public static final DataKey<Boolean> FENCED_CODE_MINIMIZE_INDENT = new DataKey<>("FENCED_CODE_MINIMIZE_INDENT", Boolean.TRUE);
    public static final DataKey<Boolean> FENCED_CODE_MATCH_CLOSING_MARKER = new DataKey<>("FENCED_CODE_MATCH_CLOSING_MARKER", Boolean.TRUE);
    public static final DataKey<Boolean> FENCED_CODE_SPACE_BEFORE_INFO = new DataKey<>("FENCED_CODE_SPACE_BEFORE_INFO", Boolean.FALSE);
    public static final DataKey<Integer> FENCED_CODE_MARKER_LENGTH = new DataKey<>("FENCED_CODE_MARKER_LENGTH", 3);
    public static final DataKey<CodeFenceMarker> FENCED_CODE_MARKER_TYPE = new DataKey<>("FENCED_CODE_MARKER_TYPE", CodeFenceMarker.ANY);
    public static final DataKey<Boolean> LIST_ADD_BLANK_LINE_BEFORE = new DataKey<>("LIST_ADD_BLANK_LINE_BEFORE", Boolean.FALSE);
    public static final DataKey<Boolean> LIST_RENUMBER_ITEMS = new DataKey<>("LIST_RENUMBER_ITEMS", Boolean.TRUE);
    public static final DataKey<Boolean> LIST_REMOVE_EMPTY_ITEMS = new DataKey<>("LIST_REMOVE_EMPTY_ITEMS", Boolean.FALSE);
    public static final DataKey<ElementAlignment> LIST_ALIGN_NUMERIC = new DataKey<>("LIST_ALIGN_NUMERIC", ElementAlignment.NONE);
    public static final DataKey<Boolean> LIST_RESET_FIRST_ITEM_NUMBER = new DataKey<>("LIST_RESET_FIRST_ITEM_NUMBER", Boolean.FALSE);
    public static final DataKey<ListBulletMarker> LIST_BULLET_MARKER = new DataKey<>("LIST_BULLET_MARKER", ListBulletMarker.ANY);
    public static final DataKey<ListNumberedMarker> LIST_NUMBERED_MARKER = new DataKey<>("LIST_NUMBERED_MARKER", ListNumberedMarker.ANY);
    public static final DataKey<ListSpacing> LIST_SPACING = new DataKey<>("LIST_SPACING", ListSpacing.AS_IS);
    public static final DataKey<Boolean> LISTS_ITEM_CONTENT_AFTER_SUFFIX = new DataKey<>("LISTS_ITEM_CONTENT_AFTER_SUFFIX", Boolean.FALSE);
    public static final DataKey<ElementPlacement> REFERENCE_PLACEMENT = new DataKey<>("REFERENCE_PLACEMENT", ElementPlacement.AS_IS);
    public static final DataKey<ElementPlacementSort> REFERENCE_SORT = new DataKey<>("REFERENCE_SORT", ElementPlacementSort.AS_IS);
    public static final DataKey<Boolean> KEEP_IMAGE_LINKS_AT_START = new DataKey<>("KEEP_IMAGE_LINKS_AT_START", Boolean.FALSE);
    public static final DataKey<Boolean> KEEP_EXPLICIT_LINKS_AT_START = new DataKey<>("KEEP_EXPLICIT_LINKS_AT_START", Boolean.FALSE);
    public static final DataKey<Boolean> OPTIMIZED_INLINE_RENDERING = new DataKey<>("OPTIMIZED_INLINE_RENDERING", Boolean.FALSE);
    public static final DataKey<CharWidthProvider> FORMAT_CHAR_WIDTH_PROVIDER = TableFormatOptions.FORMAT_CHAR_WIDTH_PROVIDER;
    public static final DataKey<Boolean> KEEP_HARD_LINE_BREAKS = new DataKey<>("KEEP_HARD_LINE_BREAKS", Boolean.TRUE);
    public static final DataKey<Boolean> KEEP_SOFT_LINE_BREAKS = new DataKey<>("KEEP_SOFT_LINE_BREAKS", Boolean.TRUE);
    public static final DataKey<String> FORMATTER_ON_TAG = new DataKey<>("FORMATTER_ON_TAG", "@formatter:on");
    public static final DataKey<String> FORMATTER_OFF_TAG = new DataKey<>("FORMATTER_OFF_TAG", "@formatter:off");
    public static final DataKey<Boolean> FORMATTER_TAGS_ENABLED = new DataKey<>("FORMATTER_TAGS_ENABLED", Boolean.FALSE);
    public static final DataKey<Boolean> FORMATTER_TAGS_ACCEPT_REGEXP = new DataKey<>("FORMATTER_TAGS_ACCEPT_REGEXP", Boolean.FALSE);
    public static final NullableDataKey<Pattern> LINK_MARKER_COMMENT_PATTERN = new NullableDataKey<>("FORMATTER_TAGS_ACCEPT_REGEXP", (Pattern) null);
    public static final DataKey<Boolean> APPEND_TRANSFERRED_REFERENCES = new DataKey<>("APPEND_TRANSFERRED_REFERENCES", Boolean.FALSE);
    public static final DataKey<String> TRANSLATION_ID_FORMAT = new DataKey<>("TRANSLATION_ID_FORMAT", "_%d_");
    public static final DataKey<String> TRANSLATION_HTML_BLOCK_PREFIX = new DataKey<>("TRANSLATION_HTML_BLOCK_PREFIX", "__");
    public static final DataKey<String> TRANSLATION_HTML_INLINE_PREFIX = new DataKey<>("TRANSLATION_HTML_INLINE_PREFIX", JavaConstant.Dynamic.DEFAULT_NAME);
    public static final DataKey<String> TRANSLATION_AUTOLINK_PREFIX = new DataKey<>("TRANSLATION_AUTOLINK_PREFIX", "___");
    public static final DataKey<String> TRANSLATION_EXCLUDE_PATTERN = new DataKey<>("TRANSLATION_EXCLUDE_PATTERN", "^[^\\p{IsAlphabetic}]*$");
    public static final DataKey<String> TRANSLATION_HTML_BLOCK_TAG_PATTERN = Parser.TRANSLATION_HTML_BLOCK_TAG_PATTERN;
    public static final DataKey<String> TRANSLATION_HTML_INLINE_TAG_PATTERN = Parser.TRANSLATION_HTML_INLINE_TAG_PATTERN;
    public static final DataKey<String> DOC_RELATIVE_URL = new DataKey<>("DOC_RELATIVE_URL", "");
    public static final DataKey<String> DOC_ROOT_URL = new DataKey<>("DOC_ROOT_URL", "");
    public static final DataKey<Boolean> DEFAULT_LINK_RESOLVER = new DataKey<>("DEFAULT_LINK_RESOLVER", Boolean.FALSE);
    public static final DataKey<ParserEmulationProfile> FORMATTER_EMULATION_PROFILE = new DataKey<>("FORMATTER_EMULATION_PROFILE", (DataKey) Parser.PARSER_EMULATION_PROFILE);
    public static final DataKey<List<TrackedOffset>> TRACKED_OFFSETS = new DataKey<>("TRACKED_OFFSETS", Collections.emptyList());
    public static final DataKey<BasedSequence> TRACKED_SEQUENCE = new DataKey<>("TRACKED_SEQUENCE", BasedSequence.NULL);
    public static final DataKey<Boolean> RESTORE_TRACKED_SPACES = new DataKey<>("RESTORE_END_SPACES", Boolean.FALSE);
    public static final DataKey<CharSequence> DOCUMENT_FIRST_PREFIX = new DataKey<>("DOCUMENT_FIRST_PREFIX", BasedSequence.NULL);
    public static final DataKey<CharSequence> DOCUMENT_PREFIX = new DataKey<>("DOCUMENT_PREFIX", BasedSequence.NULL);

    @Deprecated
    public static final DataKey<Boolean> SETEXT_HEADER_EQUALIZE_MARKER = SETEXT_HEADING_EQUALIZE_MARKER;

    @Deprecated
    public static final DataKey<EqualizeTrailingMarker> ATX_HEADER_TRAILING_MARKER = ATX_HEADING_TRAILING_MARKER;

    @Deprecated
    public static final DataKey<TableCaptionHandling> FORMAT_TABLE_CAPTION = TableFormatOptions.FORMAT_TABLE_CAPTION;

    @Deprecated
    public static final DataKey<DiscretionaryText> FORMAT_TABLE_CAPTION_SPACES = TableFormatOptions.FORMAT_TABLE_CAPTION_SPACES;

    @Deprecated
    public static final DataKey<String> FORMAT_TABLE_INDENT_PREFIX = TableFormatOptions.FORMAT_TABLE_INDENT_PREFIX;
    public static final DataKey<Map<String, String>> UNIQUIFICATION_MAP = new DataKey<>("REFERENCES_UNIQUIFICATION_MAP", HashMap::new);
    public static final DataKey<Map<String, String>> ATTRIBUTE_UNIQUIFICATION_ID_MAP = new DataKey<>("ATTRIBUTE_UNIQUIFICATION_ID_MAP", HashMap::new);
    private static final Iterator<Node> NULL_ITERATOR = new Iterator<Node>() { // from class: com.vladsch.flexmark.formatter.Formatter.1
        @Override // java.util.Iterator
        public final boolean hasNext() {
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public final Node next() {
            return null;
        }

        @Override // java.util.Iterator
        public final void remove() {
        }
    };
    public static final Iterable<Node> NULL_ITERABLE = () -> {
        return NULL_ITERATOR;
    };

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/Formatter$FormatterExtension.class */
    public interface FormatterExtension extends Extension {
        void rendererOptions(MutableDataHolder mutableDataHolder);

        void extend(Builder builder);
    }

    Formatter(Builder builder) {
        this.options = builder.toImmutable();
        this.idGeneratorFactory = builder.htmlIdGeneratorFactory == null ? new HeaderIdGenerator.Factory() : builder.htmlIdGeneratorFactory;
        this.linkResolverFactories = DependencyResolver.resolveFlatDependencies(builder.linkResolverFactories, null, null);
        this.nodeFormatterFactories = calculateNodeFormatterFactories(builder.nodeFormatterFactories);
    }

    private static List<NodeFormatterFactory> calculateNodeFormatterFactories(List<NodeFormatterFactory> list) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.add(new CoreNodeFormatter.Factory());
        return DependencyResolver.resolveFlatDependencies(arrayList, null, null);
    }

    public TranslationHandler getTranslationHandler(TranslationHandlerFactory translationHandlerFactory, HtmlIdGeneratorFactory htmlIdGeneratorFactory) {
        return translationHandlerFactory.create(this.options, htmlIdGeneratorFactory);
    }

    public TranslationHandler getTranslationHandler(HtmlIdGeneratorFactory htmlIdGeneratorFactory) {
        return new TranslationHandlerImpl(this.options, htmlIdGeneratorFactory);
    }

    public TranslationHandler getTranslationHandler() {
        return new TranslationHandlerImpl(this.options, this.idGeneratorFactory);
    }

    @Override // com.vladsch.flexmark.util.ast.IRender
    public DataHolder getOptions() {
        return this.options;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(DataHolder dataHolder) {
        return new Builder(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.ast.IRender
    public void render(Node node, Appendable appendable) {
        render(node, appendable, MAX_TRAILING_BLANK_LINES.get(this.options).intValue());
    }

    public void render(Node node, Appendable appendable, int i) {
        MarkdownWriter markdownWriter = new MarkdownWriter(appendable, FORMAT_FLAGS.get(this.options).intValue());
        MainNodeFormatter mainNodeFormatter = new MainNodeFormatter(this.options, markdownWriter, node.getDocument(), null);
        mainNodeFormatter.render(node);
        markdownWriter.appendToSilently(appendable, MAX_BLANK_LINES.get(this.options).intValue(), i);
        BasedSequence chars = node.getDocument().getChars();
        if ((appendable instanceof SequenceBuilder) && node.getDocument().getChars() != mainNodeFormatter.trackedSequence) {
            chars = ((SequenceBuilder) appendable).toSequence(mainNodeFormatter.trackedSequence);
        }
        TrackedOffsetUtils.resolveTrackedOffsets(chars, markdownWriter, mainNodeFormatter.trackedOffsets.getUnresolvedOffsets(), i, SharedDataKeys.RUNNING_TESTS.get(this.options).booleanValue());
    }

    @Override // com.vladsch.flexmark.util.ast.IRender
    public String render(Node node) {
        StringBuilder sb = new StringBuilder();
        render(node, sb);
        return sb.toString();
    }

    public void translationRender(Node node, Appendable appendable, TranslationHandler translationHandler, RenderPurpose renderPurpose) {
        translationRender(node, appendable, MAX_TRAILING_BLANK_LINES.get(this.options).intValue(), translationHandler, renderPurpose);
    }

    public String translationRender(Node node, TranslationHandler translationHandler, RenderPurpose renderPurpose) {
        StringBuilder sb = new StringBuilder();
        translationRender(node, sb, translationHandler, renderPurpose);
        return sb.toString();
    }

    public void translationRender(Node node, Appendable appendable, int i, TranslationHandler translationHandler, RenderPurpose renderPurpose) {
        translationHandler.setRenderPurpose(renderPurpose);
        MainNodeFormatter mainNodeFormatter = new MainNodeFormatter(this.options, new MarkdownWriter(FORMAT_FLAGS.get(this.options).intValue() & (LineAppendable.F_TRIM_LEADING_WHITESPACE ^ (-1))), node.getDocument(), translationHandler);
        mainNodeFormatter.render(node);
        mainNodeFormatter.flushTo(appendable, MAX_BLANK_LINES.get(this.options).intValue(), i);
    }

    public void mergeRender(Document[] documentArr, Appendable appendable) {
        mergeRender(documentArr, appendable, MAX_TRAILING_BLANK_LINES.get(this.options).intValue());
    }

    public void mergeRender(List<Document> list, Appendable appendable) {
        mergeRender((Document[]) list.toArray(EMPTY_DOCUMENTS), appendable);
    }

    public String mergeRender(Document[] documentArr, int i) {
        StringBuilder sb = new StringBuilder();
        mergeRender(documentArr, sb, i);
        return sb.toString();
    }

    public String mergeRender(List<Document> list, int i) {
        return mergeRender((Document[]) list.toArray(EMPTY_DOCUMENTS), i);
    }

    public void mergeRender(List<Document> list, Appendable appendable, int i) {
        mergeRender((Document[]) list.toArray(EMPTY_DOCUMENTS), appendable, i);
    }

    public void mergeRender(Document[] documentArr, Appendable appendable, int i) {
        MutableDataSet mutableDataSet = new MutableDataSet(this.options);
        mutableDataSet.set((DataKey<DataKey<Boolean>>) Parser.HTML_FOR_TRANSLATOR, (DataKey<Boolean>) Boolean.TRUE);
        TranslationHandler[] translationHandlerArr = new TranslationHandler[documentArr.length];
        List[] listArr = new List[documentArr.length];
        int length = documentArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            translationHandlerArr[i2] = getTranslationHandler(this.idGeneratorFactory == null ? new HeaderIdGenerator.Factory() : this.idGeneratorFactory);
        }
        MergeContextImpl mergeContextImpl = new MergeContextImpl(documentArr, translationHandlerArr);
        int intValue = FORMAT_FLAGS.get(this.options).intValue();
        int intValue2 = MAX_BLANK_LINES.get(this.options).intValue();
        mergeContextImpl.forEachPrecedingDocument(null, (translationContext, document, i3) -> {
            TranslationHandler translationHandler = (TranslationHandler) translationContext;
            translationHandler.setRenderPurpose(RenderPurpose.TRANSLATION_SPANS);
            new MainNodeFormatter(mutableDataSet, new MarkdownWriter(intValue), document, translationHandler).render(document);
            listArr[i3] = translationHandler.getTranslatingTexts();
        });
        Document[] documentArr2 = new Document[documentArr.length];
        mergeContextImpl.forEachPrecedingDocument(null, (translationContext2, document2, i4) -> {
            TranslationHandler translationHandler = (TranslationHandler) translationContext2;
            translationHandler.setRenderPurpose(RenderPurpose.TRANSLATED_SPANS);
            translationHandler.setTranslatedTexts(listArr[i4]);
            MainNodeFormatter mainNodeFormatter = new MainNodeFormatter(mutableDataSet, new MarkdownWriter(intValue), document2, translationHandler);
            mainNodeFormatter.render(document2);
            StringBuilder sb = new StringBuilder();
            mainNodeFormatter.flushTo(sb, intValue2, i);
            documentArr2[i4] = Parser.builder(mutableDataSet).build().parse(sb.toString());
        });
        mergeContextImpl.setDocuments(documentArr2);
        mergeContextImpl.forEachPrecedingDocument(null, (translationContext3, document3, i5) -> {
            TranslationHandler translationHandler = (TranslationHandler) translationContext3;
            translationHandler.setRenderPurpose(RenderPurpose.TRANSLATED);
            MarkdownWriter markdownWriter = new MarkdownWriter(intValue);
            MainNodeFormatter mainNodeFormatter = new MainNodeFormatter(mutableDataSet, markdownWriter, document3, translationHandler);
            mainNodeFormatter.render(document3);
            markdownWriter.blankLine();
            mainNodeFormatter.flushTo(appendable, intValue2, i);
        });
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/Formatter$Builder.class */
    public static class Builder extends BuilderBase<Builder> {
        List<AttributeProviderFactory> attributeProviderFactories;
        List<NodeFormatterFactory> nodeFormatterFactories;
        List<LinkResolverFactory> linkResolverFactories;
        HeaderIdGeneratorFactory htmlIdGeneratorFactory;

        public Builder() {
            this.attributeProviderFactories = new ArrayList();
            this.nodeFormatterFactories = new ArrayList();
            this.linkResolverFactories = new ArrayList();
            this.htmlIdGeneratorFactory = null;
        }

        public Builder(DataHolder dataHolder) {
            super(dataHolder);
            this.attributeProviderFactories = new ArrayList();
            this.nodeFormatterFactories = new ArrayList();
            this.linkResolverFactories = new ArrayList();
            this.htmlIdGeneratorFactory = null;
            loadExtensions();
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        public Formatter build() {
            return new Formatter(this);
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        protected void removeApiPoint(Object obj) {
            if (obj instanceof AttributeProviderFactory) {
                this.attributeProviderFactories.remove(obj);
                return;
            }
            if (obj instanceof NodeFormatterFactory) {
                this.nodeFormatterFactories.remove(obj);
            } else if (obj instanceof LinkResolverFactory) {
                this.linkResolverFactories.remove(obj);
            } else {
                if (!(obj instanceof HeaderIdGeneratorFactory)) {
                    throw new IllegalStateException("Unknown data point type: " + obj.getClass().getName());
                }
                this.htmlIdGeneratorFactory = null;
            }
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        protected void preloadExtension(Extension extension) {
            if (extension instanceof FormatterExtension) {
                ((FormatterExtension) extension).rendererOptions(this);
            }
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        protected boolean loadExtension(Extension extension) {
            if (extension instanceof FormatterExtension) {
                ((FormatterExtension) extension).extend(this);
                return true;
            }
            return false;
        }

        public Builder nodeFormatterFactory(NodeFormatterFactory nodeFormatterFactory) {
            this.nodeFormatterFactories.add(nodeFormatterFactory);
            return this;
        }

        public Builder htmlIdGeneratorFactory(HeaderIdGeneratorFactory headerIdGeneratorFactory) {
            if (this.htmlIdGeneratorFactory != null) {
                throw new IllegalStateException("custom header id factory is already set to " + headerIdGeneratorFactory.getClass().getName());
            }
            this.htmlIdGeneratorFactory = headerIdGeneratorFactory;
            addExtensionApiPoint(headerIdGeneratorFactory);
            return this;
        }

        public Builder linkResolverFactory(LinkResolverFactory linkResolverFactory) {
            this.linkResolverFactories.add(linkResolverFactory);
            addExtensionApiPoint(linkResolverFactory);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/Formatter$MainNodeFormatter.class */
    public class MainNodeFormatter extends NodeFormatterSubContext {
        private final Document document;
        private final Map<Class<?>, List<NodeFormattingHandler<?>>> renderers;
        private final SubClassingBag<Node> collectedNodes;
        private final List<PhasedNodeFormatter> phasedFormatters;
        private final Set<FormattingPhase> renderingPhases;
        private final DataHolder options;
        private final Boolean isFormatControlEnabled;
        private FormattingPhase phase;
        final TranslationHandler translationHandler;
        private final LinkResolver[] linkResolvers;
        private final HashMap<LinkType, HashMap<String, ResolvedLink>> resolvedLinkMap;
        private final ExplicitAttributeIdProvider explicitAttributeIdProvider;
        private final HtmlIdGenerator idGenerator;
        private FormatControlProcessor controlProcessor;
        private final CharPredicate blockQuoteLikePredicate;
        private final BasedSequence blockQuoteLikeChars;
        final TrackedOffsetList trackedOffsets;
        final BasedSequence trackedSequence;
        final boolean restoreTrackedSpaces;
        final FormatterOptions formatterOptions;
        static final /* synthetic */ boolean $assertionsDisabled;

        @Override // com.vladsch.flexmark.util.format.NodeContext
        public /* bridge */ /* synthetic */ NodeFormatterContext getSubContext(DataHolder dataHolder, ISequenceBuilder iSequenceBuilder) {
            return getSubContext(dataHolder, (ISequenceBuilder<?, ?>) iSequenceBuilder);
        }

        static {
            $assertionsDisabled = !Formatter.class.desiredAssertionStatus();
        }

        MainNodeFormatter(DataHolder dataHolder, MarkdownWriter markdownWriter, Document document, TranslationHandler translationHandler) {
            super(markdownWriter);
            this.resolvedLinkMap = new HashMap<>();
            this.translationHandler = translationHandler;
            this.options = new ScopedDataSet(document, dataHolder);
            this.formatterOptions = new FormatterOptions(this.options);
            this.document = document;
            this.renderers = new HashMap(32);
            this.renderingPhases = new HashSet(FormattingPhase.values().length);
            HashSet hashSet = new HashSet(100);
            Boolean bool = Formatter.DEFAULT_LINK_RESOLVER.get(this.options);
            this.linkResolvers = new LinkResolver[Formatter.this.linkResolverFactories.size() + (bool.booleanValue() ? 1 : 0)];
            this.isFormatControlEnabled = Formatter.FORMATTER_TAGS_ENABLED.get(this.options);
            for (int i = 0; i < Formatter.this.linkResolverFactories.size(); i++) {
                this.linkResolvers[i] = Formatter.this.linkResolverFactories.get(i).apply((LinkResolverBasicContext) this);
            }
            if (bool.booleanValue()) {
                this.linkResolvers[Formatter.this.linkResolverFactories.size()] = new MergeLinkResolver.Factory().apply((LinkResolverBasicContext) this);
            }
            markdownWriter.setContext(this);
            List<NodeFormatterFactory> list = Formatter.this.nodeFormatterFactories;
            this.phasedFormatters = new ArrayList(list.size());
            ExplicitAttributeIdProvider explicitAttributeIdProvider = null;
            StringBuilder sb = new StringBuilder();
            for (int size = list.size() - 1; size >= 0; size--) {
                NodeFormatter create = list.get(size).create(this.options);
                explicitAttributeIdProvider = create instanceof ExplicitAttributeIdProvider ? (ExplicitAttributeIdProvider) create : explicitAttributeIdProvider;
                char blockQuoteLikePrefixChar = create.getBlockQuoteLikePrefixChar();
                if (blockQuoteLikePrefixChar != 0) {
                    sb.append(blockQuoteLikePrefixChar);
                }
                Set<NodeFormattingHandler<?>> nodeFormattingHandlers = create.getNodeFormattingHandlers();
                if (nodeFormattingHandlers != null) {
                    for (NodeFormattingHandler<?> nodeFormattingHandler : nodeFormattingHandlers) {
                        this.renderers.computeIfAbsent(nodeFormattingHandler.getNodeType(), cls -> {
                            return new ArrayList();
                        }).add(0, nodeFormattingHandler);
                    }
                    Set<Class<?>> nodeClasses = create.getNodeClasses();
                    if (nodeClasses != null) {
                        hashSet.addAll(nodeClasses);
                    }
                    if (create instanceof PhasedNodeFormatter) {
                        Set<FormattingPhase> formattingPhases = ((PhasedNodeFormatter) create).getFormattingPhases();
                        if (formattingPhases != null) {
                            if (formattingPhases.isEmpty()) {
                                throw new IllegalStateException("PhasedNodeFormatter with empty Phases");
                            }
                            this.renderingPhases.addAll(formattingPhases);
                            this.phasedFormatters.add((PhasedNodeFormatter) create);
                        } else {
                            throw new IllegalStateException("PhasedNodeFormatter with null Phases");
                        }
                    } else {
                        continue;
                    }
                }
            }
            this.restoreTrackedSpaces = Formatter.RESTORE_TRACKED_SPACES.get(this.options).booleanValue();
            BasedSequence basedSequence = Formatter.TRACKED_SEQUENCE.get(this.options);
            List<TrackedOffset> list2 = Formatter.TRACKED_OFFSETS.get(this.options);
            this.trackedSequence = basedSequence.isEmpty() ? document.getChars() : basedSequence;
            this.trackedOffsets = list2.isEmpty() ? TrackedOffsetList.EMPTY_LIST : TrackedOffsetList.create(this.trackedSequence, list2);
            if (!$assertionsDisabled && !this.trackedSequence.equals(document.getChars())) {
                throw new AssertionError(String.format("TRACKED_SEQUENCE must be character identical to document.getChars()\nTRACKED_SEQUENCE: '%s'\n altSeq: '%s'\n", this.trackedSequence.toVisibleWhitespaceString(), document.getChars().toVisibleWhitespaceString()));
            }
            String sb2 = sb.toString();
            this.blockQuoteLikeChars = BasedSequence.of(sb2);
            this.blockQuoteLikePredicate = CharPredicate.anyOf(sb2);
            this.idGenerator = Formatter.GENERATE_HEADER_ID.get(this.options).booleanValue() ? Formatter.this.idGeneratorFactory != null ? Formatter.this.idGeneratorFactory.create(this) : new HeaderIdGenerator.Factory().create((LinkResolverContext) this) : null;
            if (this.idGenerator != null) {
                this.idGenerator.generateIds(document);
            }
            this.explicitAttributeIdProvider = explicitAttributeIdProvider;
            if (!hashSet.isEmpty()) {
                NodeCollectingVisitor nodeCollectingVisitor = new NodeCollectingVisitor(hashSet);
                nodeCollectingVisitor.collect(document);
                this.collectedNodes = nodeCollectingVisitor.getSubClassingBag();
                return;
            }
            this.collectedNodes = null;
        }

        @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
        public String encodeUrl(CharSequence charSequence) {
            return String.valueOf(charSequence);
        }

        @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
        public ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Attributes attributes, Boolean bool) {
            return resolveLink(this, linkType, charSequence, attributes);
        }

        ResolvedLink resolveLink(NodeFormatterSubContext nodeFormatterSubContext, LinkType linkType, CharSequence charSequence, Attributes attributes) {
            HashMap<String, ResolvedLink> computeIfAbsent = this.resolvedLinkMap.computeIfAbsent(linkType, linkType2 -> {
                return new HashMap();
            });
            String valueOf = String.valueOf(charSequence);
            ResolvedLink resolvedLink = computeIfAbsent.get(valueOf);
            ResolvedLink resolvedLink2 = resolvedLink;
            if (resolvedLink == null) {
                resolvedLink2 = new ResolvedLink(linkType, valueOf, attributes);
                if (!valueOf.isEmpty()) {
                    Node node = nodeFormatterSubContext.renderingNode;
                    for (LinkResolver linkResolver : this.linkResolvers) {
                        ResolvedLink resolveLink = linkResolver.resolveLink(node, this, resolvedLink2);
                        resolvedLink2 = resolveLink;
                        if (resolveLink.getStatus() != LinkStatus.UNKNOWN) {
                            break;
                        }
                    }
                }
                computeIfAbsent.put(valueOf, resolvedLink2);
            }
            return resolvedLink2;
        }

        @Override // com.vladsch.flexmark.formatter.ExplicitAttributeIdProvider
        public void addExplicitId(Node node, String str, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
            if (str != null && this.explicitAttributeIdProvider != null) {
                this.explicitAttributeIdProvider.addExplicitId(node, str, nodeFormatterContext, markdownWriter);
            }
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public RenderPurpose getRenderPurpose() {
            return this.translationHandler == null ? RenderPurpose.FORMAT : this.translationHandler.getRenderPurpose();
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public boolean isTransformingText() {
            return this.translationHandler != null && this.translationHandler.isTransformingText();
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public CharSequence transformNonTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
            return this.translationHandler == null ? charSequence2 : this.translationHandler.transformNonTranslating(charSequence, charSequence2, charSequence3, charSequence4);
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public CharSequence transformTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
            return this.translationHandler == null ? charSequence2 : this.translationHandler.transformTranslating(charSequence, charSequence2, charSequence3, charSequence4);
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public CharSequence transformAnchorRef(CharSequence charSequence, CharSequence charSequence2) {
            return this.translationHandler == null ? charSequence2 : this.translationHandler.transformAnchorRef(charSequence, charSequence2);
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public void postProcessNonTranslating(Function<String, CharSequence> function, Runnable runnable) {
            if (this.translationHandler != null) {
                this.translationHandler.postProcessNonTranslating(function, runnable);
            } else {
                runnable.run();
            }
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public <T> T postProcessNonTranslating(Function<String, CharSequence> function, Supplier<T> supplier) {
            return this.translationHandler != null ? (T) this.translationHandler.postProcessNonTranslating(function, supplier) : supplier.get();
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public boolean isPostProcessingNonTranslating() {
            return this.translationHandler != null && this.translationHandler.isPostProcessingNonTranslating();
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public MergeContext getMergeContext() {
            if (this.translationHandler == null) {
                return null;
            }
            return this.translationHandler.getMergeContext();
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public HtmlIdGenerator getIdGenerator() {
            return this.translationHandler == null ? this.idGenerator : this.translationHandler.getIdGenerator();
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public void translatingSpan(TranslatingSpanRender translatingSpanRender) {
            if (this.translationHandler != null) {
                this.translationHandler.translatingSpan(translatingSpanRender);
            } else {
                translatingSpanRender.render(this, this.markdown);
            }
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public void nonTranslatingSpan(TranslatingSpanRender translatingSpanRender) {
            if (this.translationHandler != null) {
                this.translationHandler.nonTranslatingSpan(translatingSpanRender);
            } else {
                translatingSpanRender.render(this, this.markdown);
            }
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public void translatingRefTargetSpan(Node node, TranslatingSpanRender translatingSpanRender) {
            if (this.translationHandler != null) {
                this.translationHandler.translatingRefTargetSpan(node, translatingSpanRender);
            } else {
                translatingSpanRender.render(this, this.markdown);
            }
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public MutableDataHolder getTranslationStore() {
            if (this.translationHandler != null) {
                return this.translationHandler.getTranslationStore();
            }
            return this.document;
        }

        @Override // com.vladsch.flexmark.formatter.TranslationContext
        public void customPlaceholderFormat(TranslationPlaceholderGenerator translationPlaceholderGenerator, TranslatingSpanRender translatingSpanRender) {
            if (this.translationHandler != null) {
                this.translationHandler.customPlaceholderFormat(translationPlaceholderGenerator, translatingSpanRender);
            } else {
                translatingSpanRender.render(this, this.markdown);
            }
        }

        @Override // com.vladsch.flexmark.util.format.NodeContext
        public Node getCurrentNode() {
            return this.renderingNode;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.html.renderer.LinkResolverContext, com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
        public DataHolder getOptions() {
            return this.options;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public FormatterOptions getFormatterOptions() {
            return this.formatterOptions;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.html.renderer.LinkResolverContext, com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
        public Document getDocument() {
            return this.document;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public CharPredicate getBlockQuoteLikePrefixPredicate() {
            return this.blockQuoteLikePredicate;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public BasedSequence getBlockQuoteLikePrefixChars() {
            return this.blockQuoteLikeChars;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public TrackedOffsetList getTrackedOffsets() {
            return this.trackedOffsets;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public boolean isRestoreTrackedSpaces() {
            return this.restoreTrackedSpaces;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public BasedSequence getTrackedSequence() {
            return this.trackedSequence;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public FormattingPhase getFormattingPhase() {
            return this.phase;
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.html.renderer.LinkResolverContext
        public void render(Node node) {
            renderNode(node, this);
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public final Iterable<? extends Node> nodesOfType(Class<?>[] clsArr) {
            return this.collectedNodes == null ? Formatter.NULL_ITERABLE : this.collectedNodes.itemsOfType(Node.class, clsArr);
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public final Iterable<? extends Node> nodesOfType(Collection<Class<?>> collection) {
            return this.collectedNodes == null ? Formatter.NULL_ITERABLE : this.collectedNodes.itemsOfType(Node.class, collection);
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public final Iterable<? extends Node> reversedNodesOfType(Class<?>[] clsArr) {
            return this.collectedNodes == null ? Formatter.NULL_ITERABLE : this.collectedNodes.reversedItemsOfType(Node.class, clsArr);
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public final Iterable<? extends Node> reversedNodesOfType(Collection<Class<?>> collection) {
            return this.collectedNodes == null ? Formatter.NULL_ITERABLE : this.collectedNodes.reversedItemsOfType(Node.class, collection);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.util.format.NodeContext
        public NodeFormatterContext getSubContext() {
            return getSubContextRaw(null, this.markdown.getBuilder());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.util.format.NodeContext
        public NodeFormatterContext getSubContext(DataHolder dataHolder) {
            return getSubContextRaw(dataHolder, this.markdown.getBuilder());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.util.format.NodeContext
        public NodeFormatterContext getSubContext(DataHolder dataHolder, ISequenceBuilder<?, ?> iSequenceBuilder) {
            return getSubContextRaw(dataHolder, iSequenceBuilder);
        }

        NodeFormatterContext getSubContextRaw(DataHolder dataHolder, ISequenceBuilder<?, ?> iSequenceBuilder) {
            MarkdownWriter markdownWriter = new MarkdownWriter(iSequenceBuilder, getMarkdown().getOptions());
            markdownWriter.setContext(this);
            return new SubNodeFormatter(this, markdownWriter, dataHolder);
        }

        void renderNode(Node node, NodeFormatterSubContext nodeFormatterSubContext) {
            if (node instanceof Document) {
                if (this.translationHandler != null) {
                    this.translationHandler.beginRendering((Document) node, nodeFormatterSubContext, nodeFormatterSubContext.markdown);
                }
                for (FormattingPhase formattingPhase : FormattingPhase.values()) {
                    if (formattingPhase == FormattingPhase.DOCUMENT || this.renderingPhases.contains(formattingPhase)) {
                        this.phase = formattingPhase;
                        if (this.phase == FormattingPhase.DOCUMENT) {
                            nodeFormatterSubContext.markdown.pushPrefix().setPrefix(Formatter.DOCUMENT_FIRST_PREFIX.get((Document) node), false).setPrefix(Formatter.DOCUMENT_PREFIX.get((Document) node), true);
                            List<NodeFormattingHandler<?>> list = this.renderers.get(node.getClass());
                            if (list != null) {
                                nodeFormatterSubContext.rendererList = list;
                                nodeFormatterSubContext.rendererIndex = 0;
                                nodeFormatterSubContext.renderingNode = node;
                                list.get(0).render(node, nodeFormatterSubContext, nodeFormatterSubContext.markdown);
                                nodeFormatterSubContext.renderingNode = null;
                                nodeFormatterSubContext.rendererList = null;
                                nodeFormatterSubContext.rendererIndex = -1;
                            }
                            nodeFormatterSubContext.markdown.popPrefix();
                        } else {
                            for (PhasedNodeFormatter phasedNodeFormatter : this.phasedFormatters) {
                                if (phasedNodeFormatter.getFormattingPhases().contains(formattingPhase)) {
                                    nodeFormatterSubContext.renderingNode = node;
                                    phasedNodeFormatter.renderDocument(nodeFormatterSubContext, nodeFormatterSubContext.markdown, (Document) node, formattingPhase);
                                    nodeFormatterSubContext.renderingNode = null;
                                }
                            }
                        }
                    }
                }
                return;
            }
            if (this.isFormatControlEnabled.booleanValue()) {
                if (this.controlProcessor == null) {
                    this.controlProcessor = new FormatControlProcessor(this.document, this.options);
                    this.controlProcessor.initializeFrom(node);
                } else {
                    this.controlProcessor.processFormatControl(node);
                }
            }
            if (this.isFormatControlEnabled.booleanValue() && this.controlProcessor.isFormattingOff()) {
                if (node instanceof BlankLine) {
                    nodeFormatterSubContext.markdown.blankLine();
                    return;
                } else {
                    nodeFormatterSubContext.markdown.append((CharSequence) node.getChars());
                    return;
                }
            }
            List<NodeFormattingHandler<?>> list2 = this.renderers.get(node.getClass());
            List<NodeFormattingHandler<?>> list3 = list2;
            if (list2 == null) {
                list3 = this.renderers.get(Node.class);
            }
            if (list3 != null) {
                List<NodeFormattingHandler<?>> list4 = nodeFormatterSubContext.rendererList;
                int i = nodeFormatterSubContext.rendererIndex;
                Node node2 = nodeFormatterSubContext.renderingNode;
                nodeFormatterSubContext.rendererList = list3;
                nodeFormatterSubContext.rendererIndex = 0;
                nodeFormatterSubContext.renderingNode = node;
                list3.get(0).render(node, nodeFormatterSubContext, nodeFormatterSubContext.markdown);
                nodeFormatterSubContext.renderingNode = node2;
                nodeFormatterSubContext.rendererList = list4;
                nodeFormatterSubContext.rendererIndex = i;
                return;
            }
            throw new IllegalStateException("Core Node Formatter should implement generic Node renderer");
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.html.renderer.LinkResolverContext
        public void renderChildren(Node node) {
            renderChildrenNode(node, this);
        }

        @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
        public void delegateRender() {
            delegateRender(this);
        }

        protected void delegateRender(NodeFormatterSubContext nodeFormatterSubContext) {
            if (nodeFormatterSubContext.getFormattingPhase() != FormattingPhase.DOCUMENT) {
                throw new IllegalStateException("Delegate rendering only supported in document rendering phase");
            }
            if (nodeFormatterSubContext.rendererList == null || nodeFormatterSubContext.renderingNode == null) {
                throw new IllegalStateException("Delegate rendering can only be called from node render handler");
            }
            Node node = nodeFormatterSubContext.renderingNode;
            List<NodeFormattingHandler<?>> list = nodeFormatterSubContext.rendererList;
            List<NodeFormattingHandler<?>> list2 = list;
            int i = nodeFormatterSubContext.rendererIndex;
            int i2 = i + 1;
            int i3 = i2;
            if (i2 >= list2.size()) {
                if (node instanceof Document) {
                    return;
                }
                List<NodeFormattingHandler<?>> list3 = this.renderers.get(Node.class);
                if (list3 == null) {
                    throw new IllegalStateException("Core Node Formatter should implement generic Node renderer");
                }
                if (list == list3) {
                    throw new IllegalStateException("Core Node Formatter should not delegate generic Node renderer");
                }
                list2 = list3;
                i3 = 0;
            }
            nodeFormatterSubContext.rendererList = list2;
            nodeFormatterSubContext.rendererIndex = i3;
            list2.get(i3).render(node, nodeFormatterSubContext, nodeFormatterSubContext.markdown);
            nodeFormatterSubContext.rendererIndex = i;
            nodeFormatterSubContext.rendererList = list;
        }

        protected void renderChildrenNode(Node node, NodeFormatterSubContext nodeFormatterSubContext) {
            Node firstChild = node.getFirstChild();
            while (true) {
                Node node2 = firstChild;
                if (node2 != null) {
                    Node next = node2.getNext();
                    renderNode(node2, nodeFormatterSubContext);
                    firstChild = next;
                } else {
                    return;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/Formatter$MainNodeFormatter$SubNodeFormatter.class */
        public class SubNodeFormatter extends NodeFormatterSubContext implements NodeFormatterContext {
            private final MainNodeFormatter myMainNodeRenderer;
            private final DataHolder myOptions;
            private final FormatterOptions myFormatterOptions;

            @Override // com.vladsch.flexmark.util.format.NodeContext
            public /* bridge */ /* synthetic */ NodeFormatterContext getSubContext(DataHolder dataHolder, ISequenceBuilder iSequenceBuilder) {
                return getSubContext(dataHolder, (ISequenceBuilder<?, ?>) iSequenceBuilder);
            }

            public SubNodeFormatter(MainNodeFormatter mainNodeFormatter, MarkdownWriter markdownWriter, DataHolder dataHolder) {
                super(markdownWriter);
                this.myMainNodeRenderer = mainNodeFormatter;
                this.myOptions = (dataHolder == null || dataHolder == this.myMainNodeRenderer.getOptions()) ? this.myMainNodeRenderer.getOptions() : new ScopedDataSet(this.myMainNodeRenderer.getOptions(), dataHolder);
                this.myFormatterOptions = new FormatterOptions(this.myOptions);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public MutableDataHolder getTranslationStore() {
                return this.myMainNodeRenderer.getTranslationStore();
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public final Iterable<? extends Node> nodesOfType(Class<?>[] clsArr) {
                return this.myMainNodeRenderer.nodesOfType(clsArr);
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public final Iterable<? extends Node> nodesOfType(Collection<Class<?>> collection) {
                return this.myMainNodeRenderer.nodesOfType(collection);
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public final Iterable<? extends Node> reversedNodesOfType(Class<?>[] clsArr) {
                return this.myMainNodeRenderer.reversedNodesOfType(clsArr);
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public final Iterable<? extends Node> reversedNodesOfType(Collection<Class<?>> collection) {
                return this.myMainNodeRenderer.reversedNodesOfType(collection);
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.html.renderer.LinkResolverContext, com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
            public DataHolder getOptions() {
                return this.myOptions;
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public FormatterOptions getFormatterOptions() {
                return this.myFormatterOptions;
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.html.renderer.LinkResolverContext, com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
            public Document getDocument() {
                return this.myMainNodeRenderer.getDocument();
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public CharPredicate getBlockQuoteLikePrefixPredicate() {
                return this.myMainNodeRenderer.getBlockQuoteLikePrefixPredicate();
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public BasedSequence getBlockQuoteLikePrefixChars() {
                return this.myMainNodeRenderer.getBlockQuoteLikePrefixChars();
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public TrackedOffsetList getTrackedOffsets() {
                return TrackedOffsetList.EMPTY_LIST;
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public boolean isRestoreTrackedSpaces() {
                return false;
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public BasedSequence getTrackedSequence() {
                return this.myMainNodeRenderer.getTrackedSequence();
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public FormattingPhase getFormattingPhase() {
                return this.myMainNodeRenderer.getFormattingPhase();
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.html.renderer.LinkResolverContext
            public void render(Node node) {
                this.myMainNodeRenderer.renderNode(node, this);
            }

            @Override // com.vladsch.flexmark.util.format.NodeContext
            public Node getCurrentNode() {
                return this.renderingNode;
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext
            public void delegateRender() {
                this.myMainNodeRenderer.delegateRender(this);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.vladsch.flexmark.util.format.NodeContext
            public NodeFormatterContext getSubContext() {
                return getSubContext((DataHolder) null, this.markdown.getBuilder());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.vladsch.flexmark.util.format.NodeContext
            public NodeFormatterContext getSubContext(DataHolder dataHolder) {
                return getSubContext(dataHolder, this.markdown.getBuilder());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.vladsch.flexmark.util.format.NodeContext
            public NodeFormatterContext getSubContext(DataHolder dataHolder, ISequenceBuilder<?, ?> iSequenceBuilder) {
                MarkdownWriter markdownWriter = new MarkdownWriter(iSequenceBuilder, this.markdown.getOptions());
                markdownWriter.setContext(this);
                return new SubNodeFormatter(this.myMainNodeRenderer, markdownWriter, (dataHolder == null || dataHolder == this.myOptions) ? this.myOptions : new ScopedDataSet(this.myOptions, dataHolder));
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.html.renderer.LinkResolverContext
            public void renderChildren(Node node) {
                this.myMainNodeRenderer.renderChildrenNode(node, this);
            }

            @Override // com.vladsch.flexmark.formatter.NodeFormatterSubContext, com.vladsch.flexmark.formatter.NodeFormatterContext
            public MarkdownWriter getMarkdown() {
                return this.markdown;
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public RenderPurpose getRenderPurpose() {
                return this.myMainNodeRenderer.getRenderPurpose();
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public boolean isTransformingText() {
                return this.myMainNodeRenderer.isTransformingText();
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public CharSequence transformNonTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
                return this.myMainNodeRenderer.transformNonTranslating(charSequence, charSequence2, charSequence3, charSequence4);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public CharSequence transformTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
                return this.myMainNodeRenderer.transformTranslating(charSequence, charSequence2, charSequence3, charSequence4);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public CharSequence transformAnchorRef(CharSequence charSequence, CharSequence charSequence2) {
                return this.myMainNodeRenderer.transformAnchorRef(charSequence, charSequence2);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public void translatingSpan(TranslatingSpanRender translatingSpanRender) {
                this.myMainNodeRenderer.translatingSpan(translatingSpanRender);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public void nonTranslatingSpan(TranslatingSpanRender translatingSpanRender) {
                this.myMainNodeRenderer.nonTranslatingSpan(translatingSpanRender);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public void translatingRefTargetSpan(Node node, TranslatingSpanRender translatingSpanRender) {
                this.myMainNodeRenderer.translatingRefTargetSpan(node, translatingSpanRender);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public void customPlaceholderFormat(TranslationPlaceholderGenerator translationPlaceholderGenerator, TranslatingSpanRender translatingSpanRender) {
                this.myMainNodeRenderer.customPlaceholderFormat(translationPlaceholderGenerator, translatingSpanRender);
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
            public String encodeUrl(CharSequence charSequence) {
                return this.myMainNodeRenderer.encodeUrl(charSequence);
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
            public ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Boolean bool) {
                return this.myMainNodeRenderer.resolveLink(this, linkType, charSequence, (Attributes) null);
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
            public ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Attributes attributes, Boolean bool) {
                return this.myMainNodeRenderer.resolveLink(this, linkType, charSequence, attributes);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public void postProcessNonTranslating(Function<String, CharSequence> function, Runnable runnable) {
                this.myMainNodeRenderer.postProcessNonTranslating(function, runnable);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public <T> T postProcessNonTranslating(Function<String, CharSequence> function, Supplier<T> supplier) {
                return (T) this.myMainNodeRenderer.postProcessNonTranslating(function, supplier);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public boolean isPostProcessingNonTranslating() {
                return this.myMainNodeRenderer.isPostProcessingNonTranslating();
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public MergeContext getMergeContext() {
                return this.myMainNodeRenderer.getMergeContext();
            }

            @Override // com.vladsch.flexmark.formatter.ExplicitAttributeIdProvider
            public void addExplicitId(Node node, String str, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
                this.myMainNodeRenderer.addExplicitId(node, str, nodeFormatterContext, markdownWriter);
            }

            @Override // com.vladsch.flexmark.formatter.TranslationContext
            public HtmlIdGenerator getIdGenerator() {
                return this.myMainNodeRenderer.getIdGenerator();
            }
        }
    }
}
