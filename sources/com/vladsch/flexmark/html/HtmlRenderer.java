package com.vladsch.flexmark.html;

import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.CoreNodeRenderer;
import com.vladsch.flexmark.html.renderer.HeaderIdGenerator;
import com.vladsch.flexmark.html.renderer.HeaderIdGeneratorFactory;
import com.vladsch.flexmark.html.renderer.HtmlIdGenerator;
import com.vladsch.flexmark.html.renderer.LinkResolverBasicContext;
import com.vladsch.flexmark.html.renderer.LinkResolverContext;
import com.vladsch.flexmark.html.renderer.LinkStatus;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html.renderer.PhasedNodeRenderer;
import com.vladsch.flexmark.html.renderer.RenderingPhase;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.IRender;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.builder.BuilderBase;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.NullableDataKey;
import com.vladsch.flexmark.util.data.ScopedDataSet;
import com.vladsch.flexmark.util.data.SharedDataKeys;
import com.vladsch.flexmark.util.dependency.DependencyResolver;
import com.vladsch.flexmark.util.format.TrackedOffset;
import com.vladsch.flexmark.util.format.TrackedOffsetUtils;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.html.MutableAttributes;
import com.vladsch.flexmark.util.misc.Extension;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.TagRange;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/HtmlRenderer.class */
public class HtmlRenderer implements IRender {
    public static final DataKey<String> SOFT_BREAK = new DataKey<>("SOFT_BREAK", SequenceUtils.EOL);
    public static final DataKey<String> HARD_BREAK = new DataKey<>("HARD_BREAK", "<br />\n");
    public static final NullableDataKey<String> STRONG_EMPHASIS_STYLE_HTML_OPEN = new NullableDataKey<>("STRONG_EMPHASIS_STYLE_HTML_OPEN");
    public static final NullableDataKey<String> STRONG_EMPHASIS_STYLE_HTML_CLOSE = new NullableDataKey<>("STRONG_EMPHASIS_STYLE_HTML_CLOSE");
    public static final NullableDataKey<String> EMPHASIS_STYLE_HTML_OPEN = new NullableDataKey<>("EMPHASIS_STYLE_HTML_OPEN");
    public static final NullableDataKey<String> EMPHASIS_STYLE_HTML_CLOSE = new NullableDataKey<>("EMPHASIS_STYLE_HTML_CLOSE");
    public static final NullableDataKey<String> CODE_STYLE_HTML_OPEN = new NullableDataKey<>("CODE_STYLE_HTML_OPEN");
    public static final NullableDataKey<String> CODE_STYLE_HTML_CLOSE = new NullableDataKey<>("CODE_STYLE_HTML_CLOSE");
    public static final NullableDataKey<String> INLINE_CODE_SPLICE_CLASS = new NullableDataKey<>("INLINE_CODE_SPLICE_CLASS");
    public static final DataKey<Boolean> PERCENT_ENCODE_URLS = SharedDataKeys.PERCENT_ENCODE_URLS;
    public static final DataKey<Integer> INDENT_SIZE = SharedDataKeys.INDENT_SIZE;
    public static final DataKey<Boolean> ESCAPE_HTML = new DataKey<>("ESCAPE_HTML", Boolean.FALSE);
    public static final DataKey<Boolean> ESCAPE_HTML_BLOCKS = new DataKey<>("ESCAPE_HTML_BLOCKS", (DataKey) ESCAPE_HTML);
    public static final DataKey<Boolean> ESCAPE_HTML_COMMENT_BLOCKS = new DataKey<>("ESCAPE_HTML_COMMENT_BLOCKS", (DataKey) ESCAPE_HTML_BLOCKS);
    public static final DataKey<Boolean> ESCAPE_INLINE_HTML = new DataKey<>("ESCAPE_HTML_BLOCKS", (DataKey) ESCAPE_HTML);
    public static final DataKey<Boolean> ESCAPE_INLINE_HTML_COMMENTS = new DataKey<>("ESCAPE_INLINE_HTML_COMMENTS", (DataKey) ESCAPE_INLINE_HTML);
    public static final DataKey<Boolean> SUPPRESS_HTML = new DataKey<>("SUPPRESS_HTML", Boolean.FALSE);
    public static final DataKey<Boolean> SUPPRESS_HTML_BLOCKS = new DataKey<>("SUPPRESS_HTML_BLOCKS", (DataKey) SUPPRESS_HTML);
    public static final DataKey<Boolean> SUPPRESS_HTML_COMMENT_BLOCKS = new DataKey<>("SUPPRESS_HTML_COMMENT_BLOCKS", (DataKey) SUPPRESS_HTML_BLOCKS);
    public static final DataKey<Boolean> SUPPRESS_INLINE_HTML = new DataKey<>("SUPPRESS_INLINE_HTML", (DataKey) SUPPRESS_HTML);
    public static final DataKey<Boolean> SUPPRESS_INLINE_HTML_COMMENTS = new DataKey<>("SUPPRESS_INLINE_HTML_COMMENTS", (DataKey) SUPPRESS_INLINE_HTML);
    public static final DataKey<Boolean> SOURCE_WRAP_HTML = new DataKey<>("SOURCE_WRAP_HTML", Boolean.FALSE);
    public static final DataKey<Boolean> SOURCE_WRAP_HTML_BLOCKS = new DataKey<>("SOURCE_WRAP_HTML_BLOCKS", (DataKey) SOURCE_WRAP_HTML);
    public static final DataKey<Boolean> HEADER_ID_GENERATOR_RESOLVE_DUPES = SharedDataKeys.HEADER_ID_GENERATOR_RESOLVE_DUPES;
    public static final DataKey<String> HEADER_ID_GENERATOR_TO_DASH_CHARS = SharedDataKeys.HEADER_ID_GENERATOR_TO_DASH_CHARS;
    public static final DataKey<String> HEADER_ID_GENERATOR_NON_DASH_CHARS = SharedDataKeys.HEADER_ID_GENERATOR_NON_DASH_CHARS;
    public static final DataKey<Boolean> HEADER_ID_GENERATOR_NO_DUPED_DASHES = SharedDataKeys.HEADER_ID_GENERATOR_NO_DUPED_DASHES;
    public static final DataKey<Boolean> HEADER_ID_GENERATOR_NON_ASCII_TO_LOWERCASE = SharedDataKeys.HEADER_ID_GENERATOR_NON_ASCII_TO_LOWERCASE;
    public static final DataKey<Boolean> HEADER_ID_REF_TEXT_TRIM_LEADING_SPACES = SharedDataKeys.HEADER_ID_REF_TEXT_TRIM_LEADING_SPACES;
    public static final DataKey<Boolean> HEADER_ID_REF_TEXT_TRIM_TRAILING_SPACES = SharedDataKeys.HEADER_ID_REF_TEXT_TRIM_TRAILING_SPACES;
    public static final DataKey<Boolean> HEADER_ID_ADD_EMOJI_SHORTCUT = SharedDataKeys.HEADER_ID_ADD_EMOJI_SHORTCUT;
    public static final DataKey<Boolean> RENDER_HEADER_ID = SharedDataKeys.RENDER_HEADER_ID;
    public static final DataKey<Boolean> GENERATE_HEADER_ID = SharedDataKeys.GENERATE_HEADER_ID;
    public static final DataKey<Boolean> DO_NOT_RENDER_LINKS = SharedDataKeys.DO_NOT_RENDER_LINKS;
    public static final DataKey<String> FENCED_CODE_LANGUAGE_CLASS_PREFIX = new DataKey<>("FENCED_CODE_LANGUAGE_CLASS_PREFIX", "language-");
    public static final DataKey<HashMap<String, String>> FENCED_CODE_LANGUAGE_CLASS_MAP = new DataKey<>("FENCED_CODE_LANGUAGE_CLASS_MAP", HashMap::new);
    public static final DataKey<String> FENCED_CODE_NO_LANGUAGE_CLASS = new DataKey<>("FENCED_CODE_NO_LANGUAGE_CLASS", "");
    public static final DataKey<String> FENCED_CODE_LANGUAGE_DELIMITERS = new DataKey<>("FENCED_CODE_LANGUAGE_DELIMITERS", " \t");
    public static final DataKey<String> SOURCE_POSITION_ATTRIBUTE = new DataKey<>("SOURCE_POSITION_ATTRIBUTE", "");
    public static final DataKey<Boolean> SOURCE_POSITION_PARAGRAPH_LINES = new DataKey<>("SOURCE_POSITION_PARAGRAPH_LINES", Boolean.FALSE);
    public static final DataKey<String> TYPE = new DataKey<>("TYPE", "HTML");
    public static final DataKey<ArrayList<TagRange>> TAG_RANGES = new DataKey<>("TAG_RANGES", ArrayList::new);
    public static final DataKey<Boolean> RECHECK_UNDEFINED_REFERENCES = new DataKey<>("RECHECK_UNDEFINED_REFERENCES", Boolean.FALSE);
    public static final DataKey<Boolean> OBFUSCATE_EMAIL = new DataKey<>("OBFUSCATE_EMAIL", Boolean.FALSE);
    public static final DataKey<Boolean> OBFUSCATE_EMAIL_RANDOM = new DataKey<>("OBFUSCATE_EMAIL_RANDOM", Boolean.TRUE);
    public static final DataKey<Boolean> HTML_BLOCK_OPEN_TAG_EOL = new DataKey<>("HTML_BLOCK_OPEN_TAG_EOL", Boolean.TRUE);
    public static final DataKey<Boolean> HTML_BLOCK_CLOSE_TAG_EOL = new DataKey<>("HTML_BLOCK_CLOSE_TAG_EOL", Boolean.TRUE);
    public static final DataKey<Boolean> UNESCAPE_HTML_ENTITIES = new DataKey<>("UNESCAPE_HTML_ENTITIES", Boolean.TRUE);
    public static final DataKey<String> AUTOLINK_WWW_PREFIX = new DataKey<>("AUTOLINK_WWW_PREFIX", "http://");
    public static final DataKey<String> SUPPRESSED_LINKS = new DataKey<>("SUPPRESSED_LINKS", "javascript:.*");
    public static final DataKey<Boolean> NO_P_TAGS_USE_BR = new DataKey<>("NO_P_TAGS_USE_BR", Boolean.FALSE);
    public static final DataKey<Boolean> EMBEDDED_ATTRIBUTE_PROVIDER = new DataKey<>("EMBEDDED_ATTRIBUTE_PROVIDER", Boolean.TRUE);
    public static final DataKey<Integer> FORMAT_FLAGS = new DataKey<>("RENDERER_FORMAT_FLAGS", Integer.valueOf(LineAppendable.F_TRIM_LEADING_WHITESPACE));
    public static final DataKey<Integer> MAX_TRAILING_BLANK_LINES = SharedDataKeys.RENDERER_MAX_TRAILING_BLANK_LINES;
    public static final DataKey<Integer> MAX_BLANK_LINES = SharedDataKeys.RENDERER_MAX_BLANK_LINES;

    @Deprecated
    public static final int CONVERT_TABS = LineAppendable.F_CONVERT_TABS;

    @Deprecated
    public static final int COLLAPSE_WHITESPACE = LineAppendable.F_COLLAPSE_WHITESPACE;

    @Deprecated
    public static final int SUPPRESS_TRAILING_WHITESPACE = LineAppendable.F_TRIM_TRAILING_WHITESPACE;

    @Deprecated
    public static final int PASS_THROUGH = LineAppendable.F_PASS_THROUGH;

    @Deprecated
    public static final int FORMAT_ALL = LineAppendable.F_FORMAT_ALL;
    public static final DataKey<List<Pair<String, String>>> RENDERER_TYPE_EQUIVALENCE = new DataKey<>("RENDERER_TYPE_EQUIVALENCE", Collections.emptyList());

    @Deprecated
    public static final int FORMAT_CONVERT_TABS = LineAppendable.F_CONVERT_TABS;

    @Deprecated
    public static final int FORMAT_COLLAPSE_WHITESPACE = LineAppendable.F_COLLAPSE_WHITESPACE;

    @Deprecated
    public static final int FORMAT_SUPPRESS_TRAILING_WHITESPACE = LineAppendable.F_TRIM_TRAILING_WHITESPACE;

    @Deprecated
    public static final int FORMAT_ALL_OPTIONS = LineAppendable.F_FORMAT_ALL;
    public static final DataKey<List<TrackedOffset>> TRACKED_OFFSETS = new DataKey<>("TRACKED_OFFSETS", Collections.emptyList());
    final List<AttributeProviderFactory> attributeProviderFactories;
    final List<DelegatingNodeRendererFactoryWrapper> nodeRendererFactories;
    final List<LinkResolverFactory> linkResolverFactories;
    final HeaderIdGeneratorFactory htmlIdGeneratorFactory;
    final HtmlRendererOptions htmlOptions;
    final DataHolder options;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/HtmlRenderer$HtmlRendererExtension.class */
    public interface HtmlRendererExtension extends Extension {
        void rendererOptions(MutableDataHolder mutableDataHolder);

        void extend(Builder builder, String str);
    }

    HtmlRenderer(Builder builder) {
        this.options = builder.toImmutable();
        this.htmlOptions = new HtmlRendererOptions(this.options);
        this.htmlIdGeneratorFactory = builder.htmlIdGeneratorFactory;
        ArrayList arrayList = new ArrayList(builder.nodeRendererFactories.size());
        for (int size = builder.nodeRendererFactories.size() - 1; size >= 0; size--) {
            arrayList.add(new DelegatingNodeRendererFactoryWrapper(arrayList, builder.nodeRendererFactories.get(size)));
        }
        arrayList.add(new DelegatingNodeRendererFactoryWrapper(arrayList, new CoreNodeRenderer.Factory()));
        this.nodeRendererFactories = DependencyResolver.resolveFlatDependencies(arrayList, null, delegatingNodeRendererFactoryWrapper -> {
            return delegatingNodeRendererFactoryWrapper.getFactory().getClass();
        });
        boolean z = !builder.attributeProviderFactories.containsKey(EmbeddedAttributeProvider.Factory.getClass());
        ArrayList arrayList2 = new ArrayList(builder.attributeProviderFactories.values());
        if (z && EMBEDDED_ATTRIBUTE_PROVIDER.get(this.options).booleanValue()) {
            arrayList2.add(0, EmbeddedAttributeProvider.Factory);
        }
        this.attributeProviderFactories = DependencyResolver.resolveFlatDependencies(arrayList2, null, null);
        this.linkResolverFactories = DependencyResolver.resolveFlatDependencies(builder.linkResolverFactories, null, null);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(DataHolder dataHolder) {
        return new Builder(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.ast.IRender
    public DataHolder getOptions() {
        return this.options;
    }

    @Override // com.vladsch.flexmark.util.ast.IRender
    public void render(Node node, Appendable appendable) {
        render(node, appendable, this.htmlOptions.maxTrailingBlankLines);
    }

    public void render(Node node, Appendable appendable, int i) {
        HtmlWriter htmlWriter = new HtmlWriter(appendable, this.htmlOptions.indentSize, this.htmlOptions.formatFlags, !this.htmlOptions.htmlBlockOpenTagEol, !this.htmlOptions.htmlBlockCloseTagEol);
        MainNodeRenderer mainNodeRenderer = new MainNodeRenderer(this.options, htmlWriter, node.getDocument());
        if (mainNodeRenderer.htmlIdGenerator != HtmlIdGenerator.NULL && !(node instanceof Document)) {
            mainNodeRenderer.htmlIdGenerator.generateIds(node.getDocument());
        }
        mainNodeRenderer.render(node);
        htmlWriter.appendToSilently(appendable, this.htmlOptions.maxBlankLines, i);
        TrackedOffsetUtils.resolveTrackedOffsets(node.getChars(), htmlWriter, TRACKED_OFFSETS.get(mainNodeRenderer.getDocument()), i, SharedDataKeys.RUNNING_TESTS.get(this.options).booleanValue());
        mainNodeRenderer.dispose();
    }

    @Override // com.vladsch.flexmark.util.ast.IRender
    public String render(Node node) {
        StringBuilder sb = new StringBuilder();
        render(node, sb);
        return sb.toString();
    }

    public static boolean isCompatibleRendererType(MutableDataHolder mutableDataHolder, String str) {
        return isCompatibleRendererType(mutableDataHolder, TYPE.get(mutableDataHolder), str);
    }

    public static boolean isCompatibleRendererType(MutableDataHolder mutableDataHolder, String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        for (Pair<String, String> pair : RENDERER_TYPE_EQUIVALENCE.get(mutableDataHolder)) {
            if (str.equals(pair.getFirst()) && str2.equals(pair.getSecond())) {
                return true;
            }
        }
        return false;
    }

    public static MutableDataHolder addRenderTypeEquivalence(MutableDataHolder mutableDataHolder, String str, String str2) {
        if (!isCompatibleRendererType(mutableDataHolder, str, str2)) {
            ArrayList arrayList = new ArrayList(RENDERER_TYPE_EQUIVALENCE.get(mutableDataHolder));
            arrayList.add(new Pair(str, str2));
            mutableDataHolder.set((DataKey<DataKey<List<Pair<String, String>>>>) RENDERER_TYPE_EQUIVALENCE, (DataKey<List<Pair<String, String>>>) arrayList);
        }
        return mutableDataHolder;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/HtmlRenderer$Builder.class */
    public static class Builder extends BuilderBase<Builder> implements RendererBuilder {
        Map<Class<?>, AttributeProviderFactory> attributeProviderFactories;
        List<NodeRendererFactory> nodeRendererFactories;
        List<LinkResolverFactory> linkResolverFactories;
        HeaderIdGeneratorFactory htmlIdGeneratorFactory;

        public Builder() {
            this.attributeProviderFactories = new LinkedHashMap();
            this.nodeRendererFactories = new ArrayList();
            this.linkResolverFactories = new ArrayList();
            this.htmlIdGeneratorFactory = null;
        }

        public Builder(DataHolder dataHolder) {
            super(dataHolder);
            this.attributeProviderFactories = new LinkedHashMap();
            this.nodeRendererFactories = new ArrayList();
            this.linkResolverFactories = new ArrayList();
            this.htmlIdGeneratorFactory = null;
            loadExtensions();
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        protected void removeApiPoint(Object obj) {
            if (obj instanceof AttributeProviderFactory) {
                this.attributeProviderFactories.remove(obj.getClass());
                return;
            }
            if (obj instanceof NodeRendererFactory) {
                this.nodeRendererFactories.remove(obj);
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
            if (extension instanceof HtmlRendererExtension) {
                ((HtmlRendererExtension) extension).rendererOptions(this);
            } else if (extension instanceof RendererExtension) {
                ((RendererExtension) extension).rendererOptions(this);
            }
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        protected boolean loadExtension(Extension extension) {
            if (extension instanceof HtmlRendererExtension) {
                ((HtmlRendererExtension) extension).extend(this, HtmlRenderer.TYPE.get(this));
                return true;
            }
            if (extension instanceof RendererExtension) {
                ((RendererExtension) extension).extend(this, HtmlRenderer.TYPE.get(this));
                return true;
            }
            return false;
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        public HtmlRenderer build() {
            return new HtmlRenderer(this);
        }

        public Builder softBreak(String str) {
            set((DataKey<DataKey<String>>) HtmlRenderer.SOFT_BREAK, (DataKey<String>) str);
            return this;
        }

        public Builder indentSize(int i) {
            set((DataKey<DataKey<Integer>>) HtmlRenderer.INDENT_SIZE, (DataKey<Integer>) Integer.valueOf(i));
            return this;
        }

        public Builder escapeHtml(boolean z) {
            set((DataKey<DataKey<Boolean>>) HtmlRenderer.ESCAPE_HTML, (DataKey<Boolean>) Boolean.valueOf(z));
            return this;
        }

        public boolean isRendererType(String str) {
            return HtmlRenderer.isCompatibleRendererType(this, HtmlRenderer.TYPE.get(this), str);
        }

        public Builder percentEncodeUrls(boolean z) {
            set((DataKey<DataKey<Boolean>>) HtmlRenderer.PERCENT_ENCODE_URLS, (DataKey<Boolean>) Boolean.valueOf(z));
            return this;
        }

        @Override // com.vladsch.flexmark.html.RendererBuilder
        public Builder attributeProviderFactory(AttributeProviderFactory attributeProviderFactory) {
            this.attributeProviderFactories.put(attributeProviderFactory.getClass(), attributeProviderFactory);
            addExtensionApiPoint(attributeProviderFactory);
            return this;
        }

        public Builder nodeRendererFactory(NodeRendererFactory nodeRendererFactory) {
            this.nodeRendererFactories.add(nodeRendererFactory);
            addExtensionApiPoint(nodeRendererFactory);
            return this;
        }

        @Override // com.vladsch.flexmark.html.RendererBuilder
        public Builder linkResolverFactory(LinkResolverFactory linkResolverFactory) {
            this.linkResolverFactories.add(linkResolverFactory);
            addExtensionApiPoint(linkResolverFactory);
            return this;
        }

        @Override // com.vladsch.flexmark.html.RendererBuilder
        public Builder contentResolverFactory(UriContentResolverFactory uriContentResolverFactory) {
            throw new IllegalStateException("Not implemented");
        }

        @Override // com.vladsch.flexmark.html.RendererBuilder
        public Builder htmlIdGeneratorFactory(HeaderIdGeneratorFactory headerIdGeneratorFactory) {
            if (this.htmlIdGeneratorFactory != null) {
                throw new IllegalStateException("custom header id factory is already set to " + headerIdGeneratorFactory.getClass().getName());
            }
            this.htmlIdGeneratorFactory = headerIdGeneratorFactory;
            addExtensionApiPoint(headerIdGeneratorFactory);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/HtmlRenderer$MainNodeRenderer.class */
    public class MainNodeRenderer extends NodeRendererSubContext implements Disposable, NodeRendererContext {
        private Document document;
        private Map<Class<?>, NodeRenderingHandlerWrapper> renderers;
        private List<PhasedNodeRenderer> phasedRenderers;
        private LinkResolver[] myLinkResolvers;
        private Set<RenderingPhase> renderingPhases;
        private DataHolder options;
        private RenderingPhase phase;
        HtmlIdGenerator htmlIdGenerator;
        private HashMap<LinkType, HashMap<String, ResolvedLink>> resolvedLinkMap;
        private AttributeProvider[] attributeProviders;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !HtmlRenderer.class.desiredAssertionStatus();
        }

        @Override // com.vladsch.flexmark.html.Disposable
        public void dispose() {
            this.document = null;
            this.renderers = null;
            this.phasedRenderers = null;
            for (LinkResolver linkResolver : this.myLinkResolvers) {
                if (linkResolver instanceof Disposable) {
                    ((Disposable) linkResolver).dispose();
                }
            }
            this.myLinkResolvers = null;
            this.renderingPhases = null;
            this.options = null;
            if (this.htmlIdGenerator instanceof Disposable) {
                ((Disposable) this.htmlIdGenerator).dispose();
            }
            this.htmlIdGenerator = null;
            this.resolvedLinkMap = null;
            for (AttributeProvider attributeProvider : this.attributeProviders) {
                if (attributeProvider instanceof Disposable) {
                    ((Disposable) attributeProvider).dispose();
                }
            }
            this.attributeProviders = null;
        }

        MainNodeRenderer(DataHolder dataHolder, HtmlWriter htmlWriter, Document document) {
            super(htmlWriter);
            HtmlIdGenerator create;
            this.resolvedLinkMap = new HashMap<>();
            this.options = new ScopedDataSet(document, dataHolder);
            this.document = document;
            this.renderers = new HashMap(32);
            this.renderingPhases = new HashSet(RenderingPhase.values().length);
            this.phasedRenderers = new ArrayList(HtmlRenderer.this.nodeRendererFactories.size());
            this.myLinkResolvers = new LinkResolver[HtmlRenderer.this.linkResolverFactories.size()];
            this.doNotRenderLinksNesting = HtmlRenderer.this.htmlOptions.doNotRenderLinksInDocument ? 0 : 1;
            if (HtmlRenderer.this.htmlIdGeneratorFactory != null) {
                create = HtmlRenderer.this.htmlIdGeneratorFactory.create(this);
            } else {
                create = (HtmlRenderer.this.htmlOptions.renderHeaderId || HtmlRenderer.this.htmlOptions.generateHeaderIds) ? new HeaderIdGenerator.Factory().create((LinkResolverContext) this) : HtmlIdGenerator.NULL;
            }
            this.htmlIdGenerator = create;
            htmlWriter.setContext(this);
            for (int size = HtmlRenderer.this.nodeRendererFactories.size() - 1; size >= 0; size--) {
                NodeRenderer apply = HtmlRenderer.this.nodeRendererFactories.get(size).apply(getOptions());
                Set<NodeRenderingHandler<?>> nodeRenderingHandlers = apply.getNodeRenderingHandlers();
                if (!$assertionsDisabled && nodeRenderingHandlers == null) {
                    throw new AssertionError();
                }
                for (NodeRenderingHandler<?> nodeRenderingHandler : nodeRenderingHandlers) {
                    this.renderers.put(nodeRenderingHandler.getNodeType(), new NodeRenderingHandlerWrapper(nodeRenderingHandler, this.renderers.get(nodeRenderingHandler.getNodeType())));
                }
                if (apply instanceof PhasedNodeRenderer) {
                    Set<RenderingPhase> renderingPhases = ((PhasedNodeRenderer) apply).getRenderingPhases();
                    if (!$assertionsDisabled && renderingPhases == null) {
                        throw new AssertionError();
                    }
                    this.renderingPhases.addAll(renderingPhases);
                    this.phasedRenderers.add((PhasedNodeRenderer) apply);
                }
            }
            for (int i = 0; i < HtmlRenderer.this.linkResolverFactories.size(); i++) {
                this.myLinkResolvers[i] = HtmlRenderer.this.linkResolverFactories.get(i).apply((LinkResolverBasicContext) this);
            }
            this.attributeProviders = new AttributeProvider[HtmlRenderer.this.attributeProviderFactories.size()];
            for (int i2 = 0; i2 < HtmlRenderer.this.attributeProviderFactories.size(); i2++) {
                this.attributeProviders[i2] = HtmlRenderer.this.attributeProviderFactories.get(i2).apply((LinkResolverContext) this);
            }
        }

        @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext, com.vladsch.flexmark.util.format.NodeContext
        public Node getCurrentNode() {
            return this.renderingNode;
        }

        @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
        public ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Attributes attributes, Boolean bool) {
            HashMap<String, ResolvedLink> computeIfAbsent = this.resolvedLinkMap.computeIfAbsent(linkType, linkType2 -> {
                return new HashMap();
            });
            String valueOf = String.valueOf(charSequence);
            ResolvedLink resolvedLink = computeIfAbsent.get(valueOf);
            ResolvedLink resolvedLink2 = resolvedLink;
            if (resolvedLink == null) {
                resolvedLink2 = new ResolvedLink(linkType, valueOf, attributes);
                if (!valueOf.isEmpty()) {
                    Node currentNode = getCurrentNode();
                    for (LinkResolver linkResolver : this.myLinkResolvers) {
                        ResolvedLink resolveLink = linkResolver.resolveLink(currentNode, this, resolvedLink2);
                        resolvedLink2 = resolveLink;
                        if (resolveLink.getStatus() != LinkStatus.UNKNOWN) {
                            break;
                        }
                    }
                    if ((bool == null && HtmlRenderer.this.htmlOptions.percentEncodeUrls) || (bool != null && bool.booleanValue())) {
                        ResolvedLink resolvedLink3 = resolvedLink2;
                        resolvedLink2 = resolvedLink3.withUrl(Escaping.percentEncodeUrl(resolvedLink3.getUrl()));
                    }
                }
                computeIfAbsent.put(valueOf, resolvedLink2);
            }
            return resolvedLink2;
        }

        @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
        public String getNodeId(Node node) {
            String id = this.htmlIdGenerator.getId(node);
            if (HtmlRenderer.this.attributeProviderFactories.size() != 0) {
                MutableAttributes mutableAttributes = new MutableAttributes();
                if (id != null) {
                    mutableAttributes.replaceValue(Attribute.ID_ATTR, id);
                }
                for (AttributeProvider attributeProvider : this.attributeProviders) {
                    attributeProvider.setAttributes(this.renderingNode, AttributablePart.ID, mutableAttributes);
                }
                id = mutableAttributes.getValue(Attribute.ID_ATTR);
            }
            return id;
        }

        @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext, com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
        public DataHolder getOptions() {
            return this.options;
        }

        @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
        public HtmlRendererOptions getHtmlOptions() {
            return HtmlRenderer.this.htmlOptions;
        }

        @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext, com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
        public Document getDocument() {
            return this.document;
        }

        @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
        public RenderingPhase getRenderingPhase() {
            return this.phase;
        }

        @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
        public String encodeUrl(CharSequence charSequence) {
            if (HtmlRenderer.this.htmlOptions.percentEncodeUrls) {
                return Escaping.percentEncodeUrl(charSequence);
            }
            return String.valueOf(charSequence);
        }

        @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
        public MutableAttributes extendRenderingNodeAttributes(AttributablePart attributablePart, Attributes attributes) {
            MutableAttributes mutable = attributes != null ? attributes.toMutable() : new MutableAttributes();
            for (AttributeProvider attributeProvider : this.attributeProviders) {
                attributeProvider.setAttributes(this.renderingNode, attributablePart, mutable);
            }
            return mutable;
        }

        @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
        public MutableAttributes extendRenderingNodeAttributes(Node node, AttributablePart attributablePart, Attributes attributes) {
            MutableAttributes mutable = attributes != null ? attributes.toMutable() : new MutableAttributes();
            for (AttributeProvider attributeProvider : this.attributeProviders) {
                attributeProvider.setAttributes(node, attributablePart, mutable);
            }
            return mutable;
        }

        @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
        public void render(Node node) {
            renderNode(node, this);
        }

        @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
        public void delegateRender() {
            renderByPreviousHandler(this);
        }

        void renderByPreviousHandler(NodeRendererSubContext nodeRendererSubContext) {
            if (nodeRendererSubContext.renderingNode != null) {
                NodeRenderingHandlerWrapper nodeRenderingHandlerWrapper = nodeRendererSubContext.renderingHandlerWrapper.myPreviousRenderingHandler;
                if (nodeRenderingHandlerWrapper != null) {
                    Node node = nodeRendererSubContext.renderingNode;
                    int i = nodeRendererSubContext.doNotRenderLinksNesting;
                    NodeRenderingHandlerWrapper nodeRenderingHandlerWrapper2 = nodeRendererSubContext.renderingHandlerWrapper;
                    try {
                        nodeRendererSubContext.renderingHandlerWrapper = nodeRenderingHandlerWrapper;
                        nodeRenderingHandlerWrapper.myRenderingHandler.render(node, nodeRendererSubContext, nodeRendererSubContext.htmlWriter);
                        return;
                    } finally {
                        nodeRendererSubContext.renderingNode = node;
                        nodeRendererSubContext.doNotRenderLinksNesting = i;
                        nodeRendererSubContext.renderingHandlerWrapper = nodeRenderingHandlerWrapper2;
                    }
                }
                return;
            }
            throw new IllegalStateException("renderingByPreviousHandler called outside node rendering code");
        }

        @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
        public NodeRendererContext getSubContext(boolean z) {
            HtmlWriter htmlWriter = new HtmlWriter(getHtmlWriter(), z);
            htmlWriter.setContext(this);
            return new SubNodeRenderer(this, htmlWriter, false);
        }

        @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
        public NodeRendererContext getDelegatedSubContext(boolean z) {
            HtmlWriter htmlWriter = new HtmlWriter(getHtmlWriter(), z);
            htmlWriter.setContext(this);
            return new SubNodeRenderer(this, htmlWriter, true);
        }

        void renderNode(Node node, NodeRendererSubContext nodeRendererSubContext) {
            NodeRenderingHandlerWrapper nodeRenderingHandlerWrapper;
            if (node instanceof Document) {
                int doNotRenderLinksNesting = nodeRendererSubContext.getDoNotRenderLinksNesting();
                int i = getHtmlOptions().doNotRenderLinksInDocument ? 1 : 0;
                this.htmlIdGenerator.generateIds(this.document);
                for (RenderingPhase renderingPhase : RenderingPhase.values()) {
                    if (renderingPhase == RenderingPhase.BODY || this.renderingPhases.contains(renderingPhase)) {
                        this.phase = renderingPhase;
                        for (PhasedNodeRenderer phasedNodeRenderer : this.phasedRenderers) {
                            if (((Set) Objects.requireNonNull(phasedNodeRenderer.getRenderingPhases())).contains(renderingPhase)) {
                                nodeRendererSubContext.doNotRenderLinksNesting = i;
                                nodeRendererSubContext.renderingNode = node;
                                phasedNodeRenderer.renderDocument(nodeRendererSubContext, nodeRendererSubContext.htmlWriter, (Document) node, renderingPhase);
                                nodeRendererSubContext.renderingNode = null;
                                nodeRendererSubContext.doNotRenderLinksNesting = doNotRenderLinksNesting;
                            }
                        }
                        if (getRenderingPhase() == RenderingPhase.BODY && (nodeRenderingHandlerWrapper = this.renderers.get(node.getClass())) != null) {
                            nodeRendererSubContext.doNotRenderLinksNesting = i;
                            NodeRenderingHandlerWrapper nodeRenderingHandlerWrapper2 = nodeRendererSubContext.renderingHandlerWrapper;
                            try {
                                nodeRendererSubContext.renderingNode = node;
                                nodeRendererSubContext.renderingHandlerWrapper = nodeRenderingHandlerWrapper;
                                nodeRenderingHandlerWrapper.myRenderingHandler.render(node, nodeRendererSubContext, nodeRendererSubContext.htmlWriter);
                            } finally {
                                nodeRendererSubContext.renderingHandlerWrapper = nodeRenderingHandlerWrapper2;
                                nodeRendererSubContext.renderingNode = null;
                                nodeRendererSubContext.doNotRenderLinksNesting = doNotRenderLinksNesting;
                            }
                        }
                    }
                }
                return;
            }
            NodeRenderingHandlerWrapper nodeRenderingHandlerWrapper3 = this.renderers.get(node.getClass());
            if (nodeRenderingHandlerWrapper3 != null) {
                Node node2 = this.renderingNode;
                int i2 = nodeRendererSubContext.doNotRenderLinksNesting;
                NodeRenderingHandlerWrapper nodeRenderingHandlerWrapper4 = nodeRendererSubContext.renderingHandlerWrapper;
                try {
                    nodeRendererSubContext.renderingNode = node;
                    nodeRendererSubContext.renderingHandlerWrapper = nodeRenderingHandlerWrapper3;
                    nodeRenderingHandlerWrapper3.myRenderingHandler.render(node, nodeRendererSubContext, nodeRendererSubContext.htmlWriter);
                } finally {
                    nodeRendererSubContext.renderingNode = node2;
                    nodeRendererSubContext.doNotRenderLinksNesting = i2;
                    nodeRendererSubContext.renderingHandlerWrapper = nodeRenderingHandlerWrapper4;
                }
            }
        }

        @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
        public void renderChildren(Node node) {
            renderChildrenNode(node, this);
        }

        protected void renderChildrenNode(Node node, NodeRendererSubContext nodeRendererSubContext) {
            Node firstChild = node.getFirstChild();
            while (true) {
                Node node2 = firstChild;
                if (node2 != null) {
                    Node next = node2.getNext();
                    renderNode(node2, nodeRendererSubContext);
                    firstChild = next;
                } else {
                    return;
                }
            }
        }

        /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/HtmlRenderer$MainNodeRenderer$SubNodeRenderer.class */
        private class SubNodeRenderer extends NodeRendererSubContext implements NodeRendererContext {
            private final MainNodeRenderer myMainNodeRenderer;

            public SubNodeRenderer(MainNodeRenderer mainNodeRenderer, HtmlWriter htmlWriter, boolean z) {
                super(htmlWriter);
                this.myMainNodeRenderer = mainNodeRenderer;
                this.doNotRenderLinksNesting = mainNodeRenderer.getHtmlOptions().doNotRenderLinksInDocument ? 1 : 0;
                if (z) {
                    this.renderingNode = mainNodeRenderer.renderingNode;
                    this.renderingHandlerWrapper = mainNodeRenderer.renderingHandlerWrapper;
                }
            }

            @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
            public String getNodeId(Node node) {
                return this.myMainNodeRenderer.getNodeId(node);
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext, com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
            public DataHolder getOptions() {
                return this.myMainNodeRenderer.getOptions();
            }

            @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
            public HtmlRendererOptions getHtmlOptions() {
                return this.myMainNodeRenderer.getHtmlOptions();
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext, com.vladsch.flexmark.html.renderer.LinkResolverBasicContext
            public Document getDocument() {
                return this.myMainNodeRenderer.getDocument();
            }

            @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
            public RenderingPhase getRenderingPhase() {
                return this.myMainNodeRenderer.getRenderingPhase();
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
            public String encodeUrl(CharSequence charSequence) {
                return this.myMainNodeRenderer.encodeUrl(charSequence);
            }

            @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
            public MutableAttributes extendRenderingNodeAttributes(AttributablePart attributablePart, Attributes attributes) {
                return this.myMainNodeRenderer.extendRenderingNodeAttributes(attributablePart, attributes);
            }

            @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
            public MutableAttributes extendRenderingNodeAttributes(Node node, AttributablePart attributablePart, Attributes attributes) {
                return this.myMainNodeRenderer.extendRenderingNodeAttributes(node, attributablePart, attributes);
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
            public void render(Node node) {
                this.myMainNodeRenderer.renderNode(node, this);
            }

            @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
            public void delegateRender() {
                this.myMainNodeRenderer.renderByPreviousHandler(this);
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext, com.vladsch.flexmark.util.format.NodeContext
            public Node getCurrentNode() {
                return this.myMainNodeRenderer.getCurrentNode();
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
            public ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Boolean bool) {
                return this.myMainNodeRenderer.resolveLink(linkType, charSequence, bool);
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
            public ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Attributes attributes, Boolean bool) {
                return this.myMainNodeRenderer.resolveLink(linkType, charSequence, attributes, bool);
            }

            @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
            public NodeRendererContext getSubContext(boolean z) {
                HtmlWriter htmlWriter = new HtmlWriter(this.htmlWriter, z);
                htmlWriter.setContext(this);
                return new SubNodeRenderer(this.myMainNodeRenderer, htmlWriter, false);
            }

            @Override // com.vladsch.flexmark.html.renderer.NodeRendererContext
            public NodeRendererContext getDelegatedSubContext(boolean z) {
                HtmlWriter htmlWriter = new HtmlWriter(this.htmlWriter, z);
                htmlWriter.setContext(this);
                return new SubNodeRenderer(this.myMainNodeRenderer, htmlWriter, true);
            }

            @Override // com.vladsch.flexmark.html.renderer.LinkResolverContext
            public void renderChildren(Node node) {
                this.myMainNodeRenderer.renderChildrenNode(node, this);
            }

            @Override // com.vladsch.flexmark.html.NodeRendererSubContext, com.vladsch.flexmark.html.renderer.NodeRendererContext
            public HtmlWriter getHtmlWriter() {
                return this.htmlWriter;
            }

            @Override // com.vladsch.flexmark.html.NodeRendererSubContext
            protected int getDoNotRenderLinksNesting() {
                return super.getDoNotRenderLinksNesting();
            }

            @Override // com.vladsch.flexmark.html.NodeRendererSubContext, com.vladsch.flexmark.html.renderer.NodeRendererContext
            public boolean isDoNotRenderLinks() {
                return super.isDoNotRenderLinks();
            }

            @Override // com.vladsch.flexmark.html.NodeRendererSubContext, com.vladsch.flexmark.html.renderer.NodeRendererContext
            public void doNotRenderLinks(boolean z) {
                super.doNotRenderLinks(z);
            }

            @Override // com.vladsch.flexmark.html.NodeRendererSubContext, com.vladsch.flexmark.html.renderer.NodeRendererContext
            public void doNotRenderLinks() {
                super.doNotRenderLinks();
            }

            @Override // com.vladsch.flexmark.html.NodeRendererSubContext, com.vladsch.flexmark.html.renderer.NodeRendererContext
            public void doRenderLinks() {
                super.doRenderLinks();
            }
        }
    }
}
