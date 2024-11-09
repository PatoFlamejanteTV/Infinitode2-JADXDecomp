package com.vladsch.flexmark.html2md.converter;

import com.vladsch.flexmark.ast.Reference;
import com.vladsch.flexmark.html.renderer.HeaderIdGeneratorFactory;
import com.vladsch.flexmark.html.renderer.LinkStatus;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.builder.BuilderBase;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.ScopedDataSet;
import com.vladsch.flexmark.util.dependency.DependencyResolver;
import com.vladsch.flexmark.util.format.TableFormatOptions;
import com.vladsch.flexmark.util.format.options.TableCaptionHandling;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.html.CellAlignment;
import com.vladsch.flexmark.util.html.MutableAttributes;
import com.vladsch.flexmark.util.misc.Extension;
import com.vladsch.flexmark.util.misc.Ref;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.LineAppendableImpl;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import com.vladsch.flexmark.util.sequence.builder.StringSequenceBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.bytebuddy.utility.JavaConstant;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/FlexmarkHtmlConverter.class */
public class FlexmarkHtmlConverter {
    public static final DataKey<Integer> FORMAT_FLAGS;
    public static final DataKey<Integer> MAX_BLANK_LINES;
    public static final DataKey<Integer> MAX_TRAILING_BLANK_LINES;
    public static final DataKey<Boolean> LIST_CONTENT_INDENT;
    public static final DataKey<Boolean> SETEXT_HEADINGS;
    public static final DataKey<Boolean> OUTPUT_UNKNOWN_TAGS;
    public static final DataKey<Boolean> TYPOGRAPHIC_QUOTES;
    public static final DataKey<Boolean> TYPOGRAPHIC_SMARTS;
    public static final DataKey<Boolean> EXTRACT_AUTO_LINKS;
    public static final DataKey<Boolean> OUTPUT_ATTRIBUTES_ID;
    public static final DataKey<String> OUTPUT_ATTRIBUTES_NAMES_REGEX;
    public static final DataKey<Boolean> WRAP_AUTO_LINKS;
    public static final DataKey<Boolean> RENDER_COMMENTS;
    public static final DataKey<Boolean> DOT_ONLY_NUMERIC_LISTS;
    public static final DataKey<Boolean> COMMENT_ORIGINAL_NON_NUMERIC_LIST_ITEM;
    public static final DataKey<Boolean> PRE_CODE_PRESERVE_EMPHASIS;
    public static final DataKey<Character> ORDERED_LIST_DELIMITER;
    public static final DataKey<Character> UNORDERED_LIST_DELIMITER;
    public static final DataKey<Integer> DEFINITION_MARKER_SPACES;
    public static final DataKey<Integer> MIN_SETEXT_HEADING_MARKER_LENGTH;
    public static final DataKey<String> CODE_INDENT;
    public static final DataKey<String> NBSP_TEXT;
    public static final DataKey<String> EOL_IN_TITLE_ATTRIBUTE;
    public static final DataKey<String> THEMATIC_BREAK;
    public static final DataKey<String[]> UNWRAPPED_TAGS;
    public static final DataKey<String[]> WRAPPED_TAGS;
    public static final DataKey<String> OUTPUT_ID_ATTRIBUTE_REGEX;

    @Deprecated
    public static final DataKey<Integer> TABLE_MIN_SEPARATOR_COLUMN_WIDTH;

    @Deprecated
    public static final DataKey<Integer> TABLE_MIN_SEPARATOR_DASHES;

    @Deprecated
    public static final DataKey<Boolean> TABLE_LEAD_TRAIL_PIPES;

    @Deprecated
    public static final DataKey<Boolean> TABLE_SPACE_AROUND_PIPES;

    @Deprecated
    public static final DataKey<TableCaptionHandling> TABLE_CAPTION;
    public static final DataKey<Boolean> LISTS_END_ON_DOUBLE_BLANK;
    public static final DataKey<Boolean> DIV_AS_PARAGRAPH;
    public static final DataKey<Boolean> BR_AS_PARA_BREAKS;
    public static final DataKey<Boolean> BR_AS_EXTRA_BLANK_LINES;
    public static final DataKey<Boolean> DIV_TABLE_PROCESSING;
    public static final DataKey<String[]> DIV_TABLE_HDR_CLASSES;
    public static final DataKey<String[]> DIV_TABLE_ROW_CLASSES;
    public static final DataKey<String[]> DIV_TABLE_CELL_CLASSES;
    public static final DataKey<Boolean> ADD_TRAILING_EOL;
    public static final DataKey<Boolean> SKIP_HEADING_1;
    public static final DataKey<Boolean> SKIP_HEADING_2;
    public static final DataKey<Boolean> SKIP_HEADING_3;
    public static final DataKey<Boolean> SKIP_HEADING_4;
    public static final DataKey<Boolean> SKIP_HEADING_5;
    public static final DataKey<Boolean> SKIP_HEADING_6;
    public static final DataKey<Boolean> SKIP_ATTRIBUTES;
    public static final DataKey<Boolean> SKIP_FENCED_CODE;
    public static final DataKey<Boolean> SKIP_CHAR_ESCAPE;
    public static final DataKey<ExtensionConversion> EXT_INLINE_STRONG;
    public static final DataKey<ExtensionConversion> EXT_INLINE_EMPHASIS;
    public static final DataKey<ExtensionConversion> EXT_INLINE_CODE;
    public static final DataKey<ExtensionConversion> EXT_INLINE_DEL;
    public static final DataKey<ExtensionConversion> EXT_INLINE_INS;
    public static final DataKey<ExtensionConversion> EXT_INLINE_SUB;
    public static final DataKey<ExtensionConversion> EXT_INLINE_SUP;
    public static final DataKey<ExtensionConversion> EXT_MATH;
    public static final DataKey<ExtensionConversion> EXT_TABLES;
    public static final DataKey<LinkConversion> EXT_INLINE_LINK;
    public static final DataKey<LinkConversion> EXT_INLINE_IMAGE;
    public static final DataKey<Ref<Document>> FOR_DOCUMENT;
    public static final DataKey<Map<String, String>> TYPOGRAPHIC_REPLACEMENT_MAP;
    public static final DataKey<Boolean> DUMP_HTML_TREE;
    public static final DataKey<Boolean> IGNORE_TABLE_HEADING_AFTER_ROWS;
    public static final String A_NODE = "a";
    public static final String ABBR_NODE = "abbr";
    public static final String ASIDE_NODE = "aside";
    public static final String BR_NODE = "br";
    public static final String BLOCKQUOTE_NODE = "blockquote";
    public static final String CODE_NODE = "code";
    public static final String IMG_NODE = "img";
    public static final String DEL_NODE = "del";
    public static final String STRIKE_NODE = "strike";
    public static final String DIV_NODE = "div";
    public static final String DD_NODE = "dd";
    public static final String DL_NODE = "dl";
    public static final String DT_NODE = "dt";
    public static final String I_NODE = "i";
    public static final String EM_NODE = "em";
    public static final String B_NODE = "b";
    public static final String STRONG_NODE = "strong";
    public static final String EMOJI_NODE = "g-emoji";
    public static final String INPUT_NODE = "input";
    public static final String INS_NODE = "ins";
    public static final String U_NODE = "u";
    public static final String SUB_NODE = "sub";
    public static final String SUP_NODE = "sup";
    public static final String HR_NODE = "hr";
    public static final String OL_NODE = "ol";
    public static final String UL_NODE = "ul";
    public static final String LI_NODE = "li";
    public static final String TABLE_NODE = "table";
    public static final String TBODY_NODE = "tbody";
    public static final String TD_NODE = "td";
    public static final String TH_NODE = "th";
    public static final String THEAD_NODE = "thead";
    public static final String TR_NODE = "tr";
    public static final String CAPTION_NODE = "caption";
    public static final String SVG_NODE = "svg";
    public static final String P_NODE = "p";
    public static final String PRE_NODE = "pre";
    public static final String MATH_NODE = "math";
    public static final String SPAN_NODE = "span";
    public static final String TEXT_NODE = "#text";
    public static final String COMMENT_NODE = "#comment";
    public static final String H1_NODE = "h1";
    public static final String H2_NODE = "h2";
    public static final String H3_NODE = "h3";
    public static final String H4_NODE = "h4";
    public static final String H5_NODE = "h5";
    public static final String H6_NODE = "h6";
    public static final String DEFAULT_NODE = "";
    public static final String[] HEADING_NODES;
    public static String[] EXPLICIT_LINK_TEXT_TAGS;
    private static final Map<Object, CellAlignment> TABLE_CELL_ALIGNMENTS;
    static final Map<String, String> SPECIAL_CHARS_MAP;
    private static final String TYPOGRAPHIC_QUOTES_PIPED = "“|”|‘|’|«|»|&ldquo;|&rdquo;|&lsquo;|&rsquo;|&apos;|&laquo;|&raquo;";
    private static final String TYPOGRAPHIC_SMARTS_PIPED = "…|–|—|&hellip;|&endash;|&emdash;";
    public static final DataKey<Map<Object, CellAlignment>> TABLE_CELL_ALIGNMENT_MAP;
    final HtmlConverterOptions htmlConverterOptions;
    private final DataHolder options;
    final List<DelegatingNodeRendererFactoryWrapper> nodeRendererFactories;
    final List<HtmlLinkResolverFactory> linkResolverFactories;
    private static final Iterator<Node> NULL_ITERATOR;
    public static final Iterable<Node> NULL_ITERABLE;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/FlexmarkHtmlConverter$HtmlConverterExtension.class */
    public interface HtmlConverterExtension extends Extension {
        void rendererOptions(MutableDataHolder mutableDataHolder);

        void extend(Builder builder);
    }

    static {
        $assertionsDisabled = !FlexmarkHtmlConverter.class.desiredAssertionStatus();
        FORMAT_FLAGS = new DataKey<>("FORMAT_FLAGS", Integer.valueOf(LineAppendable.F_TRIM_TRAILING_WHITESPACE | LineAppendable.F_TRIM_LEADING_WHITESPACE | LineAppendable.F_COLLAPSE_WHITESPACE | LineAppendable.F_TRIM_LEADING_EOL | LineAppendable.F_PREFIX_PRE_FORMATTED));
        MAX_BLANK_LINES = new DataKey<>("MAX_BLANK_LINES", 2);
        MAX_TRAILING_BLANK_LINES = new DataKey<>("MAX_TRAILING_BLANK_LINES", 1);
        LIST_CONTENT_INDENT = new DataKey<>("LIST_CONTENT_INDENT", Boolean.TRUE);
        SETEXT_HEADINGS = new DataKey<>("SETEXT_HEADINGS", Boolean.TRUE);
        OUTPUT_UNKNOWN_TAGS = new DataKey<>("OUTPUT_UNKNOWN_TAGS", Boolean.FALSE);
        TYPOGRAPHIC_QUOTES = new DataKey<>("TYPOGRAPHIC_QUOTES", Boolean.TRUE);
        TYPOGRAPHIC_SMARTS = new DataKey<>("TYPOGRAPHIC_SMARTS", Boolean.TRUE);
        EXTRACT_AUTO_LINKS = new DataKey<>("EXTRACT_AUTO_LINKS", Boolean.TRUE);
        OUTPUT_ATTRIBUTES_ID = new DataKey<>("OUTPUT_ATTRIBUTES_ID", Boolean.TRUE);
        OUTPUT_ATTRIBUTES_NAMES_REGEX = new DataKey<>("OUTPUT_ATTRIBUTES_NAMES_REGEX", "");
        WRAP_AUTO_LINKS = new DataKey<>("WRAP_AUTO_LINKS", Boolean.TRUE);
        RENDER_COMMENTS = new DataKey<>("RENDER_COMMENTS", Boolean.FALSE);
        DOT_ONLY_NUMERIC_LISTS = new DataKey<>("DOT_ONLY_NUMERIC_LISTS", Boolean.TRUE);
        COMMENT_ORIGINAL_NON_NUMERIC_LIST_ITEM = new DataKey<>("COMMENT_ORIGINAL_NON_NUMERIC_LIST_ITEM", Boolean.FALSE);
        PRE_CODE_PRESERVE_EMPHASIS = new DataKey<>("PRE_CODE_PRESERVE_EMPHASIS", Boolean.FALSE);
        ORDERED_LIST_DELIMITER = new DataKey<>("ORDERED_LIST_DELIMITER", '.');
        UNORDERED_LIST_DELIMITER = new DataKey<>("UNORDERED_LIST_DELIMITER", '*');
        DEFINITION_MARKER_SPACES = new DataKey<>("DEFINITION_MARKER_SPACES", 3);
        MIN_SETEXT_HEADING_MARKER_LENGTH = new DataKey<>("MIN_SETEXT_HEADING_MARKER_LENGTH", 3);
        CODE_INDENT = new DataKey<>("CODE_INDENT", "    ");
        NBSP_TEXT = new DataKey<>("NBSP_TEXT", SequenceUtils.SPACE);
        EOL_IN_TITLE_ATTRIBUTE = new DataKey<>("EOL_IN_TITLE_ATTRIBUTE", SequenceUtils.SPACE);
        THEMATIC_BREAK = new DataKey<>("THEMATIC_BREAK", "*** ** * ** ***");
        UNWRAPPED_TAGS = new DataKey<>("UNWRAPPED_TAGS", new String[]{"article", "address", "frameset", "section", "small", "iframe"});
        WRAPPED_TAGS = new DataKey<>("WRAPPED_TAGS", new String[]{"kbd", "var"});
        OUTPUT_ID_ATTRIBUTE_REGEX = new DataKey<>("OUTPUT_ID_ATTRIBUTE_REGEX", "^user-content-(.*)$");
        TABLE_MIN_SEPARATOR_COLUMN_WIDTH = TableFormatOptions.FORMAT_TABLE_MIN_SEPARATOR_COLUMN_WIDTH;
        TABLE_MIN_SEPARATOR_DASHES = TableFormatOptions.FORMAT_TABLE_MIN_SEPARATOR_DASHES;
        TABLE_LEAD_TRAIL_PIPES = TableFormatOptions.FORMAT_TABLE_LEAD_TRAIL_PIPES;
        TABLE_SPACE_AROUND_PIPES = TableFormatOptions.FORMAT_TABLE_SPACE_AROUND_PIPES;
        TABLE_CAPTION = TableFormatOptions.FORMAT_TABLE_CAPTION;
        LISTS_END_ON_DOUBLE_BLANK = new DataKey<>("LISTS_END_ON_DOUBLE_BLANK", Boolean.FALSE);
        DIV_AS_PARAGRAPH = new DataKey<>("DIV_AS_PARAGRAPH", Boolean.FALSE);
        BR_AS_PARA_BREAKS = new DataKey<>("BR_AS_PARA_BREAKS", Boolean.TRUE);
        BR_AS_EXTRA_BLANK_LINES = new DataKey<>("BR_AS_EXTRA_BLANK_LINES", Boolean.TRUE);
        DIV_TABLE_PROCESSING = new DataKey<>("DIV_TABLE_PROCESSING", Boolean.FALSE);
        DIV_TABLE_HDR_CLASSES = new DataKey<>("DIV_TABLE_HDR_CLASSES", new String[]{"wt-data-grid__row_header"});
        DIV_TABLE_ROW_CLASSES = new DataKey<>("DIV_TABLE_ROW_CLASSES", new String[]{"wt-data-grid__row"});
        DIV_TABLE_CELL_CLASSES = new DataKey<>("DIV_TABLE_CELL_CLASSES", new String[]{"wt-data-grid__cell"});
        ADD_TRAILING_EOL = new DataKey<>("ADD_TRAILING_EOL", Boolean.TRUE);
        SKIP_HEADING_1 = new DataKey<>("SKIP_HEADING_1", Boolean.FALSE);
        SKIP_HEADING_2 = new DataKey<>("SKIP_HEADING_2", Boolean.FALSE);
        SKIP_HEADING_3 = new DataKey<>("SKIP_HEADING_3", Boolean.FALSE);
        SKIP_HEADING_4 = new DataKey<>("SKIP_HEADING_4", Boolean.FALSE);
        SKIP_HEADING_5 = new DataKey<>("SKIP_HEADING_5", Boolean.FALSE);
        SKIP_HEADING_6 = new DataKey<>("SKIP_HEADING_6", Boolean.FALSE);
        SKIP_ATTRIBUTES = new DataKey<>("SKIP_ATTRIBUTES", Boolean.FALSE);
        SKIP_FENCED_CODE = new DataKey<>("SKIP_FENCED_CODE", Boolean.FALSE);
        SKIP_CHAR_ESCAPE = new DataKey<>("SKIP_CHAR_ESCAPE", Boolean.FALSE);
        EXT_INLINE_STRONG = new DataKey<>("EXT_INLINE_STRONG", ExtensionConversion.MARKDOWN);
        EXT_INLINE_EMPHASIS = new DataKey<>("EXT_INLINE_EMPHASIS", ExtensionConversion.MARKDOWN);
        EXT_INLINE_CODE = new DataKey<>("EXT_INLINE_CODE", ExtensionConversion.MARKDOWN);
        EXT_INLINE_DEL = new DataKey<>("EXT_INLINE_DEL", ExtensionConversion.MARKDOWN);
        EXT_INLINE_INS = new DataKey<>("EXT_INLINE_INS", ExtensionConversion.MARKDOWN);
        EXT_INLINE_SUB = new DataKey<>("EXT_INLINE_SUB", ExtensionConversion.MARKDOWN);
        EXT_INLINE_SUP = new DataKey<>("EXT_INLINE_SUP", ExtensionConversion.MARKDOWN);
        EXT_MATH = new DataKey<>("EXT_MATH", ExtensionConversion.HTML);
        EXT_TABLES = new DataKey<>("EXT_TABLES", ExtensionConversion.MARKDOWN);
        EXT_INLINE_LINK = new DataKey<>("EXT_INLINE_LINK", LinkConversion.MARKDOWN_EXPLICIT);
        EXT_INLINE_IMAGE = new DataKey<>("EXT_INLINE_IMAGE", LinkConversion.MARKDOWN_EXPLICIT);
        FOR_DOCUMENT = new DataKey<>("FOR_DOCUMENT", new Ref(null));
        TYPOGRAPHIC_REPLACEMENT_MAP = new DataKey<>("TYPOGRAPHIC_REPLACEMENT_MAP", new HashMap());
        DUMP_HTML_TREE = new DataKey<>("DUMP_HTML_TREE", Boolean.FALSE);
        IGNORE_TABLE_HEADING_AFTER_ROWS = new DataKey<>("IGNORE_TABLE_HEADING_AFTER_ROWS", Boolean.TRUE);
        HEADING_NODES = new String[]{H1_NODE, H2_NODE, H3_NODE, H4_NODE, H5_NODE, H6_NODE};
        EXPLICIT_LINK_TEXT_TAGS = new String[]{IMG_NODE};
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        TABLE_CELL_ALIGNMENTS = linkedHashMap;
        linkedHashMap.put(Pattern.compile("\\bleft\\b"), CellAlignment.LEFT);
        TABLE_CELL_ALIGNMENTS.put(Pattern.compile("\\bcenter\\b"), CellAlignment.CENTER);
        TABLE_CELL_ALIGNMENTS.put(Pattern.compile("\\bright\\b"), CellAlignment.RIGHT);
        TABLE_CELL_ALIGNMENTS.put("text-left", CellAlignment.LEFT);
        TABLE_CELL_ALIGNMENTS.put("text-center", CellAlignment.CENTER);
        TABLE_CELL_ALIGNMENTS.put("text-right", CellAlignment.RIGHT);
        HashMap hashMap = new HashMap();
        SPECIAL_CHARS_MAP = hashMap;
        hashMap.put("“", "\"");
        SPECIAL_CHARS_MAP.put("”", "\"");
        SPECIAL_CHARS_MAP.put("&ldquo;", "\"");
        SPECIAL_CHARS_MAP.put("&rdquo;", "\"");
        SPECIAL_CHARS_MAP.put("‘", "'");
        SPECIAL_CHARS_MAP.put("’", "'");
        SPECIAL_CHARS_MAP.put("&lsquo;", "'");
        SPECIAL_CHARS_MAP.put("&rsquo;", "'");
        SPECIAL_CHARS_MAP.put("&apos;", "'");
        SPECIAL_CHARS_MAP.put("«", "<<");
        SPECIAL_CHARS_MAP.put("&laquo;", "<<");
        SPECIAL_CHARS_MAP.put("»", ">>");
        SPECIAL_CHARS_MAP.put("&raquo;", ">>");
        SPECIAL_CHARS_MAP.put("…", "...");
        SPECIAL_CHARS_MAP.put("&hellip;", "...");
        SPECIAL_CHARS_MAP.put("–", "--");
        SPECIAL_CHARS_MAP.put("&endash;", "--");
        SPECIAL_CHARS_MAP.put("—", NestedJarHandler.TEMP_FILENAME_LEAF_SEPARATOR);
        SPECIAL_CHARS_MAP.put("&emdash;", NestedJarHandler.TEMP_FILENAME_LEAF_SEPARATOR);
        TABLE_CELL_ALIGNMENT_MAP = new DataKey<>("TABLE_CELL_ALIGNMENT_MAP", TABLE_CELL_ALIGNMENTS);
        NULL_ITERATOR = new Iterator<Node>() { // from class: com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter.1
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
        NULL_ITERABLE = () -> {
            return NULL_ITERATOR;
        };
    }

    FlexmarkHtmlConverter(Builder builder) {
        this.options = builder.toImmutable();
        this.htmlConverterOptions = new HtmlConverterOptions(this.options);
        ArrayList arrayList = new ArrayList(builder.nodeRendererFactories.size() + 1);
        arrayList.addAll(builder.nodeRendererFactories);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList2.add(new DelegatingNodeRendererFactoryWrapper(arrayList2, (HtmlNodeRendererFactory) arrayList.get(size)));
        }
        arrayList2.add(new DelegatingNodeRendererFactoryWrapper(arrayList2, new HtmlConverterCoreNodeRendererFactory()));
        this.nodeRendererFactories = DependencyResolver.resolveFlatDependencies(arrayList2, null, delegatingNodeRendererFactoryWrapper -> {
            return delegatingNodeRendererFactoryWrapper.getFactory().getClass();
        });
        this.linkResolverFactories = DependencyResolver.resolveFlatDependencies(builder.linkResolverFactories, null, null);
    }

    public DataHolder getOptions() {
        return this.options;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(DataHolder dataHolder) {
        return new Builder(dataHolder);
    }

    public void convert(String str, Appendable appendable) {
        org.jsoup.nodes.Document parse = Jsoup.parse(str);
        if (DUMP_HTML_TREE.get(getOptions()).booleanValue()) {
            LineAppendableImpl lineAppendableImpl = new LineAppendableImpl(LineAppendable.F_TRIM_LEADING_EOL);
            lineAppendableImpl.setIndentPrefix("  ");
            dumpHtmlTree(lineAppendableImpl, parse.body());
            System.out.println(lineAppendableImpl.toString(0, 0));
        }
        MainHtmlConverter mainHtmlConverter = new MainHtmlConverter(this.options, new HtmlMarkdownWriter(this.htmlConverterOptions.formatFlags), parse, null);
        mainHtmlConverter.render(parse);
        mainHtmlConverter.flushTo(appendable, this.htmlConverterOptions.maxBlankLines, this.htmlConverterOptions.maxTrailingBlankLines);
    }

    public String convert(String str) {
        return convert(str, 1);
    }

    public String convert(String str, int i) {
        org.jsoup.nodes.Document parse = Jsoup.parse(str);
        if (DUMP_HTML_TREE.get(getOptions()).booleanValue()) {
            LineAppendableImpl lineAppendableImpl = new LineAppendableImpl(LineAppendable.F_TRIM_LEADING_EOL);
            lineAppendableImpl.setIndentPrefix("  ");
            dumpHtmlTree(lineAppendableImpl, parse.body());
            System.out.println(lineAppendableImpl.toString(0, 0));
        }
        MainHtmlConverter mainHtmlConverter = new MainHtmlConverter(this.options, new HtmlMarkdownWriter(this.htmlConverterOptions.formatFlags), parse, null);
        mainHtmlConverter.render(parse);
        return mainHtmlConverter.getMarkdown().toString(this.htmlConverterOptions.maxBlankLines, i);
    }

    public static void dumpHtmlTree(LineAppendable lineAppendable, Node node) {
        lineAppendable.line().append((CharSequence) node.nodeName());
        for (Attribute attribute : node.attributes().asList()) {
            lineAppendable.append(' ').append((CharSequence) attribute.getKey()).append("=\"").append((CharSequence) attribute.getValue()).append("\"");
        }
        lineAppendable.line().indent();
        Iterator<Node> it = node.childNodes().iterator();
        while (it.hasNext()) {
            dumpHtmlTree(lineAppendable, it.next());
        }
        lineAppendable.unIndent();
    }

    public void convert(Node node, Appendable appendable, int i) {
        MainHtmlConverter mainHtmlConverter = new MainHtmlConverter(this.options, new HtmlMarkdownWriter(this.htmlConverterOptions.formatFlags), node.ownerDocument(), null);
        mainHtmlConverter.render(node);
        mainHtmlConverter.flushTo(appendable, this.htmlConverterOptions.maxBlankLines, i);
    }

    public String convert(Node node) {
        StringBuilder sb = new StringBuilder();
        convert(node, sb, 0);
        return sb.toString();
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/FlexmarkHtmlConverter$Builder.class */
    public static class Builder extends BuilderBase<Builder> {
        List<HtmlNodeRendererFactory> nodeRendererFactories;
        List<HtmlLinkResolverFactory> linkResolverFactories;
        HeaderIdGeneratorFactory htmlIdGeneratorFactory;

        public Builder() {
            this.nodeRendererFactories = new ArrayList();
            this.linkResolverFactories = new ArrayList();
            this.htmlIdGeneratorFactory = null;
        }

        public Builder(DataHolder dataHolder) {
            super(dataHolder);
            this.nodeRendererFactories = new ArrayList();
            this.linkResolverFactories = new ArrayList();
            this.htmlIdGeneratorFactory = null;
            loadExtensions();
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        public FlexmarkHtmlConverter build() {
            return new FlexmarkHtmlConverter(this);
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        protected void removeApiPoint(Object obj) {
            if (obj instanceof HtmlNodeRendererFactory) {
                this.nodeRendererFactories.remove(obj);
            } else if (obj instanceof HtmlLinkResolverFactory) {
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
            if (extension instanceof HtmlConverterExtension) {
                ((HtmlConverterExtension) extension).rendererOptions(this);
            }
        }

        @Override // com.vladsch.flexmark.util.builder.BuilderBase
        protected boolean loadExtension(Extension extension) {
            if (extension instanceof HtmlConverterExtension) {
                ((HtmlConverterExtension) extension).extend(this);
                return true;
            }
            return false;
        }

        public Builder htmlNodeRendererFactory(HtmlNodeRendererFactory htmlNodeRendererFactory) {
            this.nodeRendererFactories.add(htmlNodeRendererFactory);
            return this;
        }

        public Builder linkResolverFactory(HtmlLinkResolverFactory htmlLinkResolverFactory) {
            this.linkResolverFactories.add(htmlLinkResolverFactory);
            addExtensionApiPoint(htmlLinkResolverFactory);
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/FlexmarkHtmlConverter$MainHtmlConverter.class */
    public class MainHtmlConverter extends HtmlNodeConverterSubContext {
        private final org.jsoup.nodes.Document document;
        private final Document myForDocument;
        private final Map<String, HtmlNodeRendererHandler<?>> renderers;
        private final List<PhasedHtmlNodeRenderer> phasedFormatters;
        private final Set<HtmlConverterPhase> renderingPhases;
        private final DataHolder myOptions;
        private HtmlConverterPhase phase;
        private final HtmlConverterOptions myHtmlConverterOptions;
        private final Pattern specialCharsPattern;
        private final Stack<HtmlConverterState> myStateStack;
        private final Map<String, String> mySpecialCharsMap;
        private HtmlConverterState myState;
        private boolean myTrace;
        private boolean myInlineCode;
        private Parser myParser;
        private final HtmlLinkResolver[] myHtmlLinkResolvers;
        private final HashMap<String, Reference> myReferenceUrlToReferenceMap;
        private final HashSet<Reference> myExternalReferences;
        static final /* synthetic */ boolean $assertionsDisabled;

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
        public /* bridge */ /* synthetic */ HtmlNodeConverterContext getSubContext(DataHolder dataHolder, ISequenceBuilder iSequenceBuilder) {
            return getSubContext(dataHolder, (ISequenceBuilder<?, ?>) iSequenceBuilder);
        }

        static {
            $assertionsDisabled = !FlexmarkHtmlConverter.class.desiredAssertionStatus();
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public HtmlConverterState getState() {
            return this.myState;
        }

        MainHtmlConverter(DataHolder dataHolder, HtmlMarkdownWriter htmlMarkdownWriter, org.jsoup.nodes.Document document, DataHolder dataHolder2) {
            super(htmlMarkdownWriter);
            this.myParser = null;
            this.myOptions = new ScopedDataSet(dataHolder2, dataHolder);
            this.renderers = new HashMap(32);
            this.renderingPhases = new HashSet(HtmlConverterPhase.values().length);
            this.phasedFormatters = new ArrayList(FlexmarkHtmlConverter.this.nodeRendererFactories.size());
            this.myHtmlLinkResolvers = new HtmlLinkResolver[FlexmarkHtmlConverter.this.linkResolverFactories.size()];
            htmlMarkdownWriter.setContext(this);
            this.myHtmlConverterOptions = new HtmlConverterOptions(this.myOptions);
            if (this.myHtmlConverterOptions.typographicQuotes && this.myHtmlConverterOptions.typographicSmarts) {
                this.specialCharsPattern = Pattern.compile("“|”|‘|’|«|»|&ldquo;|&rdquo;|&lsquo;|&rsquo;|&apos;|&laquo;|&raquo;|…|–|—|&hellip;|&endash;|&emdash;");
            } else if (this.myHtmlConverterOptions.typographicQuotes) {
                this.specialCharsPattern = Pattern.compile(FlexmarkHtmlConverter.TYPOGRAPHIC_QUOTES_PIPED);
            } else if (this.myHtmlConverterOptions.typographicSmarts) {
                this.specialCharsPattern = Pattern.compile(FlexmarkHtmlConverter.TYPOGRAPHIC_SMARTS_PIPED);
            } else {
                this.specialCharsPattern = null;
            }
            this.myStateStack = new Stack<>();
            this.myReferenceUrlToReferenceMap = new HashMap<>();
            this.myExternalReferences = new HashSet<>();
            this.myState = null;
            Map<String, String> map = FlexmarkHtmlConverter.TYPOGRAPHIC_REPLACEMENT_MAP.get(this.myOptions);
            if (!map.isEmpty()) {
                this.mySpecialCharsMap = map;
            } else {
                this.mySpecialCharsMap = FlexmarkHtmlConverter.SPECIAL_CHARS_MAP;
            }
            for (int size = FlexmarkHtmlConverter.this.nodeRendererFactories.size() - 1; size >= 0; size--) {
                HtmlNodeRenderer apply = FlexmarkHtmlConverter.this.nodeRendererFactories.get(size).apply(this.myOptions);
                Set<HtmlNodeRendererHandler<?>> htmlNodeRendererHandlers = apply.getHtmlNodeRendererHandlers();
                if (htmlNodeRendererHandlers != null) {
                    for (HtmlNodeRendererHandler<?> htmlNodeRendererHandler : htmlNodeRendererHandlers) {
                        this.renderers.put(htmlNodeRendererHandler.getTagName(), htmlNodeRendererHandler);
                    }
                    if (apply instanceof PhasedHtmlNodeRenderer) {
                        Set<HtmlConverterPhase> htmlConverterPhases = ((PhasedHtmlNodeRenderer) apply).getHtmlConverterPhases();
                        if (htmlConverterPhases != null) {
                            if (htmlConverterPhases.isEmpty()) {
                                throw new IllegalStateException("PhasedNodeFormatter with empty Phases");
                            }
                            this.renderingPhases.addAll(htmlConverterPhases);
                            this.phasedFormatters.add((PhasedHtmlNodeRenderer) apply);
                        } else {
                            throw new IllegalStateException("PhasedNodeFormatter with null Phases");
                        }
                    } else {
                        continue;
                    }
                }
            }
            for (int i = 0; i < FlexmarkHtmlConverter.this.linkResolverFactories.size(); i++) {
                this.myHtmlLinkResolvers[i] = FlexmarkHtmlConverter.this.linkResolverFactories.get(i).apply((HtmlNodeConverterContext) this);
            }
            this.document = document;
            this.myForDocument = FlexmarkHtmlConverter.FOR_DOCUMENT.get(dataHolder).value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/FlexmarkHtmlConverter$MainHtmlConverter$SubHtmlNodeConverter.class */
        public class SubHtmlNodeConverter extends HtmlNodeConverterSubContext implements HtmlNodeConverterContext {
            private final MainHtmlConverter myMainNodeRenderer;
            private final DataHolder myOptions;

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
            public /* bridge */ /* synthetic */ HtmlNodeConverterContext getSubContext(DataHolder dataHolder, ISequenceBuilder iSequenceBuilder) {
                return getSubContext(dataHolder, (ISequenceBuilder<?, ?>) iSequenceBuilder);
            }

            SubHtmlNodeConverter(MainHtmlConverter mainHtmlConverter, HtmlMarkdownWriter htmlMarkdownWriter, DataHolder dataHolder) {
                super(htmlMarkdownWriter);
                this.myMainNodeRenderer = mainHtmlConverter;
                this.myOptions = (dataHolder == null || dataHolder == this.myMainNodeRenderer.getOptions()) ? this.myMainNodeRenderer.getOptions() : new ScopedDataSet(this.myMainNodeRenderer.getOptions(), dataHolder);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
            public DataHolder getOptions() {
                return this.myOptions;
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public HtmlConverterOptions getHtmlConverterOptions() {
                return this.myMainNodeRenderer.getHtmlConverterOptions();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public org.jsoup.nodes.Document getDocument() {
                return this.myMainNodeRenderer.getDocument();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public HtmlConverterPhase getFormattingPhase() {
                return this.myMainNodeRenderer.getFormattingPhase();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void render(Node node) {
                this.myMainNodeRenderer.renderNode(node, this);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
            public Node getCurrentNode() {
                return this.myRenderingNode;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
            public HtmlNodeConverterContext getSubContext() {
                return getSubContext(this.myOptions, (ISequenceBuilder<?, ?>) StringSequenceBuilder.emptyBuilder());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
            public HtmlNodeConverterContext getSubContext(DataHolder dataHolder) {
                return getSubContext(dataHolder, (ISequenceBuilder<?, ?>) StringSequenceBuilder.emptyBuilder());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
            public HtmlNodeConverterContext getSubContext(DataHolder dataHolder, ISequenceBuilder<?, ?> iSequenceBuilder) {
                HtmlMarkdownWriter htmlMarkdownWriter = new HtmlMarkdownWriter(iSequenceBuilder, this.markdown.getOptions());
                htmlMarkdownWriter.setContext(this);
                return new SubHtmlNodeConverter(this.myMainNodeRenderer, htmlMarkdownWriter, (dataHolder == null || dataHolder == this.myOptions) ? this.myOptions : new ScopedDataSet(this.myOptions, dataHolder));
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void renderChildren(Node node, boolean z, Runnable runnable) {
                FlexmarkHtmlConverter.processHtmlTree(this, node, z, runnable);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public Document getForDocument() {
                return this.myMainNodeRenderer.getForDocument();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Boolean bool) {
                return this.myMainNodeRenderer.resolveLink(linkType, charSequence, bool);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Attributes attributes, Boolean bool) {
                return this.myMainNodeRenderer.resolveLink(linkType, charSequence, attributes, bool);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void pushState(Node node) {
                this.myMainNodeRenderer.pushState(node);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void popState(LineAppendable lineAppendable) {
                this.myMainNodeRenderer.popState(lineAppendable);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void processAttributes(Node node) {
                this.myMainNodeRenderer.processAttributes(node);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public int outputAttributes(LineAppendable lineAppendable, String str) {
                return this.myMainNodeRenderer.outputAttributes(lineAppendable, str);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void transferIdToParent() {
                this.myMainNodeRenderer.transferIdToParent();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void transferToParentExcept(String... strArr) {
                this.myMainNodeRenderer.transferToParentExcept(strArr);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void transferToParentOnly(String... strArr) {
                this.myMainNodeRenderer.transferToParentOnly(strArr);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public Node peek() {
                return this.myMainNodeRenderer.peek();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public Node peek(int i) {
                return this.myMainNodeRenderer.peek(i);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public Node next() {
                return this.myMainNodeRenderer.next();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void skip() {
                this.myMainNodeRenderer.skip();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public Node next(int i) {
                return this.myMainNodeRenderer.next(i);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void skip(int i) {
                this.myMainNodeRenderer.skip(i);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void delegateRender() {
                this.myMainNodeRenderer.renderByPreviousHandler(this);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public HashMap<String, Reference> getReferenceUrlToReferenceMap() {
                return this.myMainNodeRenderer.getReferenceUrlToReferenceMap();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public HashSet<Reference> getExternalReferences() {
                return this.myMainNodeRenderer.getExternalReferences();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public Reference getOrCreateReference(String str, String str2, String str3) {
                return this.myMainNodeRenderer.getOrCreateReference(str, str2, str3);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public com.vladsch.flexmark.util.ast.Node parseMarkdown(String str) {
                return this.myMainNodeRenderer.parseMarkdown(str);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void processUnwrapped(Node node) {
                this.myMainNodeRenderer.processUnwrapped(this, node);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void processWrapped(Node node, Boolean bool, boolean z) {
                FlexmarkHtmlConverter.processWrapped(this, node, bool, z);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void appendOuterHtml(Node node) {
                FlexmarkHtmlConverter.appendOuterHtml(this, node);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public boolean isInlineCode() {
                return this.myMainNodeRenderer.isInlineCode();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void setInlineCode(boolean z) {
                this.myMainNodeRenderer.setInlineCode(z);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void inlineCode(Runnable runnable) {
                this.myMainNodeRenderer.inlineCode(runnable);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public String escapeSpecialChars(String str) {
                return this.myMainNodeRenderer.escapeSpecialChars(str);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public String prepareText(String str) {
                return this.myMainNodeRenderer.prepareText(str);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public String prepareText(String str, boolean z) {
                return this.myMainNodeRenderer.prepareText(str, z);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public String processTextNodes(Node node) {
                return this.myMainNodeRenderer.processTextNodes(node);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void excludeAttributes(String... strArr) {
                this.myMainNodeRenderer.excludeAttributes(strArr);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void processTextNodes(Node node, boolean z) {
                processTextNodes(node, z, null, null);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void processTextNodes(Node node, boolean z, CharSequence charSequence) {
                processTextNodes(node, z, charSequence, charSequence);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void processTextNodes(Node node, boolean z, CharSequence charSequence, CharSequence charSequence2) {
                FlexmarkHtmlConverter.processTextNodes(this, node, z, charSequence, charSequence2);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void wrapTextNodes(Node node, CharSequence charSequence, boolean z) {
                FlexmarkHtmlConverter.wrapTextNodes(this, node, charSequence, z);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void processConditional(ExtensionConversion extensionConversion, Node node, Runnable runnable) {
                FlexmarkHtmlConverter.processConditional(this, extensionConversion, node, runnable);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void renderDefault(Node node) {
                FlexmarkHtmlConverter.processDefault(this, node, getHtmlConverterOptions().outputUnknownTags);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public HtmlConverterState getState() {
                return this.myMainNodeRenderer.getState();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public boolean isTrace() {
                return this.myMainNodeRenderer.isTrace();
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public void setTrace(boolean z) {
                this.myMainNodeRenderer.setTrace(z);
            }

            @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
            public Stack<HtmlConverterState> getStateStack() {
                return this.myMainNodeRenderer.getStateStack();
            }
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public HashMap<String, Reference> getReferenceUrlToReferenceMap() {
            return this.myReferenceUrlToReferenceMap;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public HashSet<Reference> getExternalReferences() {
            return this.myExternalReferences;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public boolean isTrace() {
            return this.myTrace;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public Stack<HtmlConverterState> getStateStack() {
            return this.myStateStack;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void setTrace(boolean z) {
            this.myTrace = z;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public com.vladsch.flexmark.util.ast.Node parseMarkdown(String str) {
            if (this.myParser == null) {
                this.myParser = Parser.builder(this.myOptions).build();
            }
            return this.myParser.parse(str);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public Reference getOrCreateReference(String str, String str2, String str3) {
            Reference reference = this.myReferenceUrlToReferenceMap.get(str);
            if (reference != null) {
                if (str3 != null && !str3.trim().isEmpty()) {
                    if (reference.getTitle().isBlank()) {
                        reference.setTitle(BasedSequence.of(str3).subSequence(0, str3.length()));
                        return reference;
                    }
                    if (reference.getTitle().equals(str3.trim())) {
                        return reference;
                    }
                }
                return reference;
            }
            String str4 = str2;
            if (this.myReferenceUrlToReferenceMap.containsKey(str4)) {
                int i = 1;
                while (true) {
                    str4 = str2 + JavaConstant.Dynamic.DEFAULT_NAME + i;
                    if (!this.myReferenceUrlToReferenceMap.containsKey(str4)) {
                        break;
                    }
                    i++;
                }
            }
            StringBuilder append = new StringBuilder("[").append(str4).append("]: ").append(str);
            if (str3 != null && !str3.trim().isEmpty()) {
                append.append(" '").append(str3.replace("'", "\\'")).append("'");
            }
            com.vladsch.flexmark.util.ast.Node firstChild = parseMarkdown(append.toString()).getFirstChild();
            if (firstChild instanceof Reference) {
                Reference reference2 = (Reference) firstChild;
                this.myReferenceUrlToReferenceMap.put(str, reference2);
                return reference2;
            }
            return null;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Boolean bool) {
            return resolveLink(linkType, charSequence, null, bool);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Attributes attributes, Boolean bool) {
            String valueOf = String.valueOf(charSequence);
            ResolvedLink resolvedLink = new ResolvedLink(linkType, valueOf, attributes);
            if (!valueOf.isEmpty()) {
                Node currentNode = getCurrentNode();
                for (HtmlLinkResolver htmlLinkResolver : this.myHtmlLinkResolvers) {
                    ResolvedLink resolveLink = htmlLinkResolver.resolveLink(currentNode, this, resolvedLink);
                    resolvedLink = resolveLink;
                    if (resolveLink.getStatus() != LinkStatus.UNKNOWN) {
                        break;
                    }
                }
            }
            return resolvedLink;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
        public Node getCurrentNode() {
            return this.myRenderingNode;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
        public DataHolder getOptions() {
            return this.myOptions;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public HtmlConverterOptions getHtmlConverterOptions() {
            return FlexmarkHtmlConverter.this.htmlConverterOptions;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public org.jsoup.nodes.Document getDocument() {
            return this.document;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public Document getForDocument() {
            return this.myForDocument;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public HtmlConverterPhase getFormattingPhase() {
            return this.phase;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void render(Node node) {
            renderNode(node, this);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void delegateRender() {
            renderByPreviousHandler(this);
        }

        void renderByPreviousHandler(HtmlNodeConverterSubContext htmlNodeConverterSubContext) {
            if (htmlNodeConverterSubContext.getRenderingNode() != null) {
                NodeRenderingHandlerWrapper<?> nodeRenderingHandlerWrapper = htmlNodeConverterSubContext.renderingHandlerWrapper.myPreviousRenderingHandler;
                if (nodeRenderingHandlerWrapper != null) {
                    Node renderingNode = htmlNodeConverterSubContext.getRenderingNode();
                    NodeRenderingHandlerWrapper<?> nodeRenderingHandlerWrapper2 = htmlNodeConverterSubContext.renderingHandlerWrapper;
                    try {
                        htmlNodeConverterSubContext.renderingHandlerWrapper = nodeRenderingHandlerWrapper;
                        nodeRenderingHandlerWrapper.myRenderingHandler.render(renderingNode, htmlNodeConverterSubContext, htmlNodeConverterSubContext.getMarkdown());
                        return;
                    } finally {
                        htmlNodeConverterSubContext.setRenderingNode(renderingNode);
                        htmlNodeConverterSubContext.renderingHandlerWrapper = nodeRenderingHandlerWrapper2;
                    }
                }
                return;
            }
            throw new IllegalStateException("renderingByPreviousHandler called outside node rendering code");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
        public HtmlNodeConverterContext getSubContext() {
            return getSubContext((DataHolder) null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
        public HtmlNodeConverterContext getSubContext(DataHolder dataHolder) {
            return getSubContext(dataHolder, this.markdown.getBuilder());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.util.format.NodeContext
        public HtmlNodeConverterContext getSubContext(DataHolder dataHolder, ISequenceBuilder<?, ?> iSequenceBuilder) {
            HtmlMarkdownWriter htmlMarkdownWriter = new HtmlMarkdownWriter(iSequenceBuilder, this.markdown.getOptions());
            htmlMarkdownWriter.setContext(this);
            return new SubHtmlNodeConverter(this, htmlMarkdownWriter, (dataHolder == null || dataHolder == this.myOptions) ? this.myOptions : new ScopedDataSet(this.myOptions, dataHolder));
        }

        void renderNode(Node node, HtmlNodeConverterSubContext htmlNodeConverterSubContext) {
            if (node instanceof org.jsoup.nodes.Document) {
                for (HtmlConverterPhase htmlConverterPhase : HtmlConverterPhase.values()) {
                    if (htmlConverterPhase == HtmlConverterPhase.DOCUMENT || this.renderingPhases.contains(htmlConverterPhase)) {
                        this.phase = htmlConverterPhase;
                        if (this.phase == HtmlConverterPhase.DOCUMENT) {
                            FlexmarkHtmlConverter.processHtmlTree(htmlNodeConverterSubContext, this.document.body(), false, null);
                        } else {
                            for (PhasedHtmlNodeRenderer phasedHtmlNodeRenderer : this.phasedFormatters) {
                                if (phasedHtmlNodeRenderer.getHtmlConverterPhases().contains(htmlConverterPhase)) {
                                    htmlNodeConverterSubContext.myRenderingNode = node;
                                    phasedHtmlNodeRenderer.renderDocument(htmlNodeConverterSubContext, htmlNodeConverterSubContext.markdown, (org.jsoup.nodes.Document) node, htmlConverterPhase);
                                    htmlNodeConverterSubContext.myRenderingNode = null;
                                }
                            }
                        }
                    }
                }
                return;
            }
            HtmlNodeRendererHandler<?> htmlNodeRendererHandler = this.renderers.get(node.nodeName().toLowerCase());
            HtmlNodeRendererHandler<?> htmlNodeRendererHandler2 = htmlNodeRendererHandler;
            if (htmlNodeRendererHandler == null) {
                htmlNodeRendererHandler2 = this.renderers.get("");
            }
            if (htmlNodeRendererHandler2 != null) {
                Node node2 = this.myRenderingNode;
                htmlNodeConverterSubContext.myRenderingNode = node;
                htmlNodeRendererHandler2.render(node, htmlNodeConverterSubContext, htmlNodeConverterSubContext.markdown);
                htmlNodeConverterSubContext.myRenderingNode = node2;
                return;
            }
            throw new IllegalStateException("Core Node Formatter should implement generic empty tag renderer");
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void renderChildren(Node node, boolean z, Runnable runnable) {
            FlexmarkHtmlConverter.processHtmlTree(this, node, z, runnable);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void pushState(Node node) {
            this.myStateStack.push(this.myState);
            this.myState = new HtmlConverterState(node);
            processAttributes(node);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void excludeAttributes(String... strArr) {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            for (String str : strArr) {
                this.myState.myAttributes.remove(str);
            }
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void processAttributes(Node node) {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            MutableAttributes mutableAttributes = this.myState.myAttributes;
            if (this.myHtmlConverterOptions.outputAttributesIdAttr || !this.myHtmlConverterOptions.outputAttributesNamesRegex.isEmpty()) {
                org.jsoup.nodes.Attributes attributes = node.attributes();
                boolean z = false;
                if (this.myHtmlConverterOptions.outputAttributesIdAttr) {
                    String str = attributes.get(com.vladsch.flexmark.util.html.Attribute.ID_ATTR);
                    String str2 = str;
                    if (str == null || str2.isEmpty()) {
                        str2 = attributes.get(com.vladsch.flexmark.util.html.Attribute.NAME_ATTR);
                    }
                    if (str2 != null && !str2.isEmpty()) {
                        mutableAttributes.replaceValue(com.vladsch.flexmark.util.html.Attribute.ID_ATTR, str2);
                        z = true;
                    }
                }
                if (!this.myHtmlConverterOptions.outputAttributesNamesRegex.isEmpty()) {
                    Iterator<Attribute> it = attributes.iterator();
                    while (it.hasNext()) {
                        Attribute next = it.next();
                        if (!z || (!next.getKey().equals(com.vladsch.flexmark.util.html.Attribute.ID_ATTR) && !next.getKey().equals(com.vladsch.flexmark.util.html.Attribute.NAME_ATTR))) {
                            if (next.getKey().matches(this.myHtmlConverterOptions.outputAttributesNamesRegex)) {
                                mutableAttributes.replaceValue(next.getKey(), next.getValue());
                            }
                        }
                    }
                }
            }
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public int outputAttributes(LineAppendable lineAppendable, String str) {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            MutableAttributes mutableAttributes = this.myState.myAttributes;
            int offsetWithPending = lineAppendable.offsetWithPending();
            if (!mutableAttributes.isEmpty() && !this.myHtmlConverterOptions.skipAttributes) {
                CharSequence charSequence = "";
                lineAppendable.append((CharSequence) str);
                lineAppendable.append("{");
                for (String str2 : mutableAttributes.keySet()) {
                    String value = mutableAttributes.getValue(str2);
                    lineAppendable.append(charSequence);
                    if (str2.equals(com.vladsch.flexmark.util.html.Attribute.ID_ATTR) || str2.equals(com.vladsch.flexmark.util.html.Attribute.NAME_ATTR)) {
                        boolean z = false;
                        if (!this.myHtmlConverterOptions.outputIdAttributeRegex.isEmpty()) {
                            Matcher matcher = this.myHtmlConverterOptions.outputIdAttributeRegexPattern.matcher(value);
                            if (matcher.matches()) {
                                StringBuilder sb = new StringBuilder();
                                int groupCount = matcher.groupCount();
                                for (int i = 0; i < groupCount; i++) {
                                    String group = matcher.group(i + 1);
                                    if (group != null && !group.isEmpty()) {
                                        sb.append(group);
                                    }
                                }
                                String trim = sb.toString().trim();
                                value = trim;
                                z = trim.isEmpty();
                            }
                        }
                        if (!z) {
                            lineAppendable.append("#").append((CharSequence) value);
                        }
                    } else if (str2.equals(com.vladsch.flexmark.util.html.Attribute.CLASS_ATTR)) {
                        lineAppendable.append(".").append((CharSequence) value);
                    } else {
                        lineAppendable.append((CharSequence) str2).append("=");
                        if (!value.contains("\"")) {
                            lineAppendable.append('\"').append((CharSequence) value).append('\"');
                        } else if (!value.contains("'")) {
                            lineAppendable.append('\'').append((CharSequence) value).append('\'');
                        } else {
                            lineAppendable.append('\"').append(value.replace("\"", "\\\"")).append('\"');
                        }
                    }
                    charSequence = SequenceUtils.SPACE;
                }
                lineAppendable.append("}");
                this.myState.myAttributes.clear();
            }
            return lineAppendable.offsetWithPending() - offsetWithPending;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void transferIdToParent() {
            HtmlConverterState peek;
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            if (this.myStateStack.isEmpty()) {
                throw new IllegalStateException("transferIdToParent with an empty stack");
            }
            com.vladsch.flexmark.util.html.Attribute attribute = this.myState.myAttributes.get(com.vladsch.flexmark.util.html.Attribute.ID_ATTR);
            this.myState.myAttributes.remove(com.vladsch.flexmark.util.html.Attribute.ID_ATTR);
            if (attribute != null && !attribute.getValue().isEmpty() && (peek = this.myStateStack.peek()) != null) {
                peek.myAttributes.addValue(com.vladsch.flexmark.util.html.Attribute.ID_ATTR, attribute.getValue());
            }
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void transferToParentExcept(String... strArr) {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            if (this.myStateStack.isEmpty()) {
                throw new IllegalStateException("transferIdToParent with an empty stack");
            }
            MutableAttributes mutableAttributes = new MutableAttributes(this.myState.myAttributes);
            this.myState.myAttributes.clear();
            for (String str : strArr) {
                this.myState.myAttributes.addValue(mutableAttributes.get(str));
                mutableAttributes.remove(str);
            }
            if (!mutableAttributes.isEmpty()) {
                HtmlConverterState peek = this.myStateStack.peek();
                Iterator<String> it = mutableAttributes.keySet().iterator();
                while (it.hasNext()) {
                    peek.myAttributes.addValue(mutableAttributes.get(it.next()));
                }
            }
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void transferToParentOnly(String... strArr) {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            if (this.myStateStack.isEmpty()) {
                throw new IllegalStateException("transferIdToParent with an empty stack");
            }
            MutableAttributes mutableAttributes = new MutableAttributes();
            for (String str : strArr) {
                com.vladsch.flexmark.util.html.Attribute attribute = this.myState.myAttributes.get(str);
                if (attribute != null) {
                    this.myState.myAttributes.remove(str);
                    mutableAttributes.addValue(attribute);
                }
            }
            if (!mutableAttributes.isEmpty()) {
                HtmlConverterState peek = this.myStateStack.peek();
                Iterator<String> it = mutableAttributes.keySet().iterator();
                while (it.hasNext()) {
                    peek.myAttributes.addValue(mutableAttributes.get(it.next()));
                }
            }
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void popState(LineAppendable lineAppendable) {
            if (this.myStateStack.isEmpty()) {
                throw new IllegalStateException("popState with an empty stack");
            }
            if (lineAppendable != null) {
                outputAttributes(lineAppendable, "");
            }
            this.myState = this.myStateStack.pop();
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public Node peek() {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            if (this.myState.myIndex < this.myState.myElements.size()) {
                return this.myState.myElements.get(this.myState.myIndex);
            }
            return null;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public Node peek(int i) {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            if (this.myState.myIndex + i >= 0 && this.myState.myIndex + i < this.myState.myElements.size()) {
                return this.myState.myElements.get(this.myState.myIndex + i);
            }
            return null;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public Node next() {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            Node peek = peek();
            if (peek != null) {
                this.myState.myIndex++;
            }
            return peek;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void skip() {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            if (peek() != null) {
                this.myState.myIndex++;
            }
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public Node next(int i) {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            if (i > 0) {
                Node peek = peek(i - 1);
                if (peek != null) {
                    this.myState.myIndex += i;
                }
                return peek;
            }
            return peek();
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void skip(int i) {
            if (!$assertionsDisabled && this.myState == null) {
                throw new AssertionError();
            }
            if (i <= 0 || peek(i - 1) == null) {
                return;
            }
            this.myState.myIndex += i;
        }

        private String dumpState() {
            if (!this.myStateStack.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                while (!this.myStateStack.isEmpty()) {
                    HtmlConverterState pop = this.myStateStack.pop();
                    sb.append(SequenceUtils.EOL).append(pop == null ? "null" : pop.toString());
                }
                return sb.toString();
            }
            return "";
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void processUnwrapped(Node node) {
            processUnwrapped(this, node);
        }

        void processUnwrapped(HtmlNodeConverterSubContext htmlNodeConverterSubContext, Node node) {
            FlexmarkHtmlConverter.processHtmlTree(htmlNodeConverterSubContext, node, false, null);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void processWrapped(Node node, Boolean bool, boolean z) {
            FlexmarkHtmlConverter.processWrapped(this, node, bool, z);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void processTextNodes(Node node, boolean z) {
            processTextNodes(node, z, null, null);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void processTextNodes(Node node, boolean z, CharSequence charSequence) {
            processTextNodes(node, z, charSequence, charSequence);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void processTextNodes(Node node, boolean z, CharSequence charSequence, CharSequence charSequence2) {
            FlexmarkHtmlConverter.processTextNodes(this, node, z, charSequence, charSequence2);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void wrapTextNodes(Node node, CharSequence charSequence, boolean z) {
            FlexmarkHtmlConverter.wrapTextNodes(this, node, charSequence, z);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public String processTextNodes(Node node) {
            pushState(node);
            HtmlNodeConverterContext subContext = getSubContext();
            while (true) {
                Node next = next();
                if (next != null) {
                    if (next instanceof TextNode) {
                        subContext.getMarkdown().append((CharSequence) prepareText(((TextNode) next).getWholeText()));
                    } else if (next instanceof Element) {
                        subContext.render(next);
                    }
                } else {
                    transferIdToParent();
                    popState(null);
                    return subContext.getMarkdown().toString(-1, -1);
                }
            }
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void appendOuterHtml(Node node) {
            FlexmarkHtmlConverter.appendOuterHtml(this, node);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public boolean isInlineCode() {
            return this.myInlineCode;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void setInlineCode(boolean z) {
            this.myInlineCode = z;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void inlineCode(Runnable runnable) {
            boolean z = this.myInlineCode;
            this.myInlineCode = true;
            try {
                runnable.run();
            } finally {
                this.myInlineCode = z;
            }
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public String prepareText(String str) {
            return prepareText(str, this.myInlineCode);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public String prepareText(String str, boolean z) {
            String replace;
            int i;
            if (this.specialCharsPattern != null) {
                Matcher matcher = this.specialCharsPattern.matcher(str);
                int length = str.length();
                StringBuilder sb = new StringBuilder(length << 1);
                int i2 = 0;
                while (true) {
                    i = i2;
                    if (!matcher.find()) {
                        break;
                    }
                    if (i < matcher.start()) {
                        sb.append((CharSequence) str, i, matcher.start());
                    }
                    String group = matcher.group();
                    String str2 = this.mySpecialCharsMap.get(group);
                    if (str2 != null) {
                        sb.append(str2);
                    } else {
                        sb.append(group);
                    }
                    i2 = matcher.end();
                }
                if (i < length) {
                    sb.append((CharSequence) str, i, length);
                }
                str = sb.toString();
            }
            if (!z) {
                replace = escapeSpecialChars(str);
            } else {
                replace = str.replace(" ", SequenceUtils.SPACE);
            }
            return replace;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public String escapeSpecialChars(String str) {
            if (!this.myHtmlConverterOptions.skipCharEscape) {
                str = str.replace("\\", "\\\\").replace("*", "\\*").replace("~", "\\~").replace("^", "\\^").replace("&", "\\&").replace("<", "\\<").replace(">", "\\>").replace("[", "\\[").replace("]", "\\]").replace("|", "\\|").replace("`", "\\`").replace(" ", this.myHtmlConverterOptions.nbspText);
            }
            return str;
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void processConditional(ExtensionConversion extensionConversion, Node node, Runnable runnable) {
            FlexmarkHtmlConverter.processConditional(this, extensionConversion, node, runnable);
        }

        @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext
        public void renderDefault(Node node) {
            FlexmarkHtmlConverter.processDefault(this, node, getHtmlConverterOptions().outputUnknownTags);
        }
    }

    static void processTextNodes(HtmlNodeConverterContext htmlNodeConverterContext, Node node, boolean z, CharSequence charSequence, CharSequence charSequence2) {
        htmlNodeConverterContext.pushState(node);
        HtmlMarkdownWriter markdown = htmlNodeConverterContext.getMarkdown();
        while (true) {
            Node next = htmlNodeConverterContext.next();
            if (next == null) {
                break;
            }
            if (next instanceof TextNode) {
                if (charSequence != null && charSequence.length() > 0) {
                    markdown.append(charSequence);
                }
                markdown.append((CharSequence) htmlNodeConverterContext.prepareText(((TextNode) next).getWholeText()));
                if (charSequence2 != null && charSequence2.length() > 0) {
                    markdown.append(charSequence2);
                }
            } else if (next instanceof Element) {
                htmlNodeConverterContext.render(next);
            }
        }
        if (z) {
            htmlNodeConverterContext.excludeAttributes(com.vladsch.flexmark.util.html.Attribute.ID_ATTR);
        }
        if (node.parent().childNode(node.parent().childNodeSize() - 1) == node) {
            htmlNodeConverterContext.transferIdToParent();
        }
        htmlNodeConverterContext.popState(markdown);
    }

    static void wrapTextNodes(HtmlNodeConverterContext htmlNodeConverterContext, Node node, CharSequence charSequence, boolean z) {
        String processTextNodes = htmlNodeConverterContext.processTextNodes(node);
        String str = null;
        String str2 = null;
        boolean z2 = false;
        boolean z3 = false;
        HtmlMarkdownWriter markdown = htmlNodeConverterContext.getMarkdown();
        if (!processTextNodes.isEmpty() && z) {
            if ("  \t\n".indexOf(processTextNodes.charAt(0)) != -1) {
                str = htmlNodeConverterContext.prepareText(processTextNodes.substring(0, 1));
                processTextNodes = processTextNodes.substring(1);
            } else if (processTextNodes.startsWith("&nbsp;")) {
                str = "&nbsp;";
                processTextNodes = processTextNodes.substring(str.length());
            } else {
                z2 = (markdown.getPendingEOL() == 0 || markdown.isPendingSpace() || markdown.offsetWithPending() == 0 || markdown.getPendingEOL() > 0) ? false : true;
            }
            if (!processTextNodes.isEmpty()) {
                String str3 = processTextNodes;
                if ("  \t\n".indexOf(str3.charAt(str3.length() - 1)) != -1) {
                    String str4 = processTextNodes;
                    str2 = htmlNodeConverterContext.prepareText(str4.substring(str4.length() - 1));
                    processTextNodes = processTextNodes.substring(0, processTextNodes.length() - 1);
                }
            }
            if (processTextNodes.endsWith("&nbsp;")) {
                str2 = "&nbsp;";
                processTextNodes = processTextNodes.substring(0, processTextNodes.length() - str2.length());
            } else {
                Node peek = htmlNodeConverterContext.peek();
                z3 = true;
                if (peek instanceof TextNode) {
                    String wholeText = ((TextNode) peek).getWholeText();
                    if (!wholeText.isEmpty() && Character.isWhitespace(wholeText.charAt(0))) {
                        z3 = false;
                    }
                }
            }
        }
        if (!processTextNodes.isEmpty()) {
            int length = processTextNodes.length() - 1;
            while (length >= 0 && Character.isWhitespace(processTextNodes.charAt(length))) {
                length--;
            }
            int i = length + 1;
            if (i > 0) {
                if (str != null) {
                    markdown.append((CharSequence) str);
                }
                if (z2) {
                    markdown.append(' ');
                }
                String substring = processTextNodes.substring(0, i);
                markdown.append(charSequence);
                markdown.append((CharSequence) substring);
                markdown.append(charSequence);
                if (str2 != null) {
                    markdown.append((CharSequence) str2);
                }
                if (z3) {
                    markdown.append(' ');
                }
            }
        }
    }

    static void processConditional(HtmlNodeConverterContext htmlNodeConverterContext, ExtensionConversion extensionConversion, Node node, Runnable runnable) {
        if (extensionConversion.isParsed()) {
            if (!extensionConversion.isSuppressed()) {
                runnable.run();
                return;
            }
            return;
        }
        htmlNodeConverterContext.processWrapped(node, null, true);
    }

    static void appendOuterHtml(HtmlNodeConverterSubContext htmlNodeConverterSubContext, Node node) {
        String outerHtml = node.outerHtml();
        int indexOf = outerHtml.indexOf(">");
        int lastIndexOf = outerHtml.lastIndexOf("</");
        if (indexOf != -1 && lastIndexOf != -1) {
            htmlNodeConverterSubContext.markdown.append((CharSequence) outerHtml.substring(0, indexOf + 1));
            int childNodeSize = node.childNodeSize();
            if (childNodeSize <= 0) {
                htmlNodeConverterSubContext.markdown.append((CharSequence) htmlNodeConverterSubContext.escapeSpecialChars(outerHtml.substring(indexOf + 1, lastIndexOf)));
            } else {
                for (int i = 0; i < childNodeSize; i++) {
                    appendOuterHtml(htmlNodeConverterSubContext, node.childNode(i));
                }
            }
            htmlNodeConverterSubContext.markdown.append((CharSequence) outerHtml.substring(lastIndexOf));
            return;
        }
        if (indexOf == -1) {
            htmlNodeConverterSubContext.markdown.append((CharSequence) htmlNodeConverterSubContext.escapeSpecialChars(outerHtml));
        } else {
            htmlNodeConverterSubContext.markdown.append((CharSequence) outerHtml);
        }
    }

    public static void processWrapped(HtmlNodeConverterSubContext htmlNodeConverterSubContext, Node node, Boolean bool, boolean z) {
        if ((node instanceof Element) && ((bool == null && ((Element) node).isBlock()) || (bool != null && bool.booleanValue()))) {
            String node2 = node.toString();
            htmlNodeConverterSubContext.markdown.lineIf(bool != null).append((CharSequence) node2.substring(0, node2.indexOf(">") + 1)).lineIf(bool != null);
            processHtmlTree(htmlNodeConverterSubContext, node, false, null);
            htmlNodeConverterSubContext.markdown.lineIf(bool != null).append((CharSequence) node2.substring(node2.lastIndexOf("<"))).lineIf(bool != null);
            return;
        }
        if (z) {
            appendOuterHtml(htmlNodeConverterSubContext, node);
        } else {
            htmlNodeConverterSubContext.markdown.append((CharSequence) node.toString());
        }
    }

    static void processHtmlTree(HtmlNodeConverterSubContext htmlNodeConverterSubContext, Node node, boolean z, Runnable runnable) {
        htmlNodeConverterSubContext.pushState(node);
        HtmlConverterState state = htmlNodeConverterSubContext.getState();
        if (!$assertionsDisabled && state == null) {
            throw new AssertionError();
        }
        if (runnable != null) {
            state.addPrePopAction(runnable);
        }
        while (true) {
            Node next = htmlNodeConverterSubContext.next();
            if (next == null) {
                break;
            } else {
                htmlNodeConverterSubContext.render(next);
            }
        }
        if (state != htmlNodeConverterSubContext.getState()) {
            throw new IllegalStateException("State not equal after process " + dumpState(htmlNodeConverterSubContext));
        }
        state.runPrePopActions();
        htmlNodeConverterSubContext.popState(z ? htmlNodeConverterSubContext.markdown : null);
    }

    static String dumpState(HtmlNodeConverterContext htmlNodeConverterContext) {
        Stack<HtmlConverterState> stateStack = htmlNodeConverterContext.getStateStack();
        if (!stateStack.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            while (!stateStack.isEmpty()) {
                HtmlConverterState pop = stateStack.pop();
                sb.append(SequenceUtils.EOL).append(pop == null ? "null" : pop.toString());
            }
            return sb.toString();
        }
        return "";
    }

    static void processDefault(HtmlNodeConverterSubContext htmlNodeConverterSubContext, Node node, boolean z) {
        if (z) {
            htmlNodeConverterSubContext.processWrapped(node, null, false);
        } else {
            htmlNodeConverterSubContext.processUnwrapped(node);
        }
    }
}
