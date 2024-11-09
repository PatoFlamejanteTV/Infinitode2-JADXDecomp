package com.vladsch.flexmark.parser.internal;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/HtmlDeepParser.class */
public class HtmlDeepParser {
    public static final Set<String> BLOCK_TAGS = new HashSet();
    public static final Set<String> VOID_TAGS = new HashSet();
    public static final Map<String, Set<String>> OPTIONAL_TAGS;
    public static final Pattern START_PATTERN;
    private static final HtmlMatch[] PATTERN_MAP;
    private final ArrayList<String> myOpenTags;
    private Pattern myClosingPattern;
    private HtmlMatch myHtmlMatch;
    private int myHtmlCount;
    private final HashSet<String> myBlockTags;
    private boolean myFirstBlockTag;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/HtmlDeepParser$HtmlMatch.class */
    public enum HtmlMatch {
        NONE(null, null, false),
        SCRIPT("<(script)(?:\\s|>|$)", "</script>", true),
        STYLE("<(style)(?:\\s|>|$)", "</style>", true),
        OPEN_TAG("<([A-Za-z][A-Za-z0-9-]*)", "<|/>|\\s/>|>", true),
        CLOSE_TAG("</([A-Za-z][A-Za-z0-9-]*)>", null, true),
        NON_TAG("<(![A-Z])", ">", false),
        TEMPLATE("<([?])", "\\?>", false),
        COMMENT("<(!--)", "-->", false),
        CDATA("<!\\[(CDATA)\\[", "\\]\\]>", false);

        public final Pattern open;
        public final Pattern close;
        public final boolean caseInsentive;

        HtmlMatch(String str, String str2, boolean z) {
            Pattern compile;
            Pattern compile2;
            if (str == null) {
                compile = null;
            } else {
                compile = Pattern.compile(str, z ? 2 : 0);
            }
            this.open = compile;
            if (str2 == null) {
                compile2 = null;
            } else {
                compile2 = Pattern.compile(str2, z ? 2 : 0);
            }
            this.close = compile2;
            this.caseInsentive = z;
        }
    }

    static {
        BLOCK_TAGS.addAll(Arrays.asList("address|article|aside|base|basefont|blockquote|body|caption|center|col|colgroup|dd|details|dialog|dir|div|dl|dt|fieldset|figcaption|figure|footer|form|frame|frameset|h1|h2|h3|h4|h5|h6|head|header|hr|html|iframe|legend|li|link|main|menu|menuitem|meta|nav|noframes|ol|optgroup|option|p|param|pre|section|source|summary|table|tbody|td|tfoot|th|thead|title|tr|track|ul".split("\\|")));
        VOID_TAGS.addAll(Arrays.asList("area|base|br|col|embed|hr|img|input|keygen|link|menuitem|meta|param|source|track|wbr".split("\\|")));
        HashMap hashMap = new HashMap();
        OPTIONAL_TAGS = hashMap;
        hashMap.put(FlexmarkHtmlConverter.LI_NODE, new HashSet(Arrays.asList(FlexmarkHtmlConverter.LI_NODE)));
        OPTIONAL_TAGS.put(FlexmarkHtmlConverter.DT_NODE, new HashSet(Arrays.asList(FlexmarkHtmlConverter.DT_NODE, FlexmarkHtmlConverter.DD_NODE)));
        OPTIONAL_TAGS.put(FlexmarkHtmlConverter.DD_NODE, new HashSet(Arrays.asList(FlexmarkHtmlConverter.DD_NODE, FlexmarkHtmlConverter.DT_NODE)));
        OPTIONAL_TAGS.put(FlexmarkHtmlConverter.P_NODE, new HashSet(Arrays.asList("address", "article", FlexmarkHtmlConverter.ASIDE_NODE, FlexmarkHtmlConverter.BLOCKQUOTE_NODE, "details", FlexmarkHtmlConverter.DIV_NODE, FlexmarkHtmlConverter.DL_NODE, "fieldset", "figcaption", "figure", "footer", "form", FlexmarkHtmlConverter.H1_NODE, FlexmarkHtmlConverter.H2_NODE, FlexmarkHtmlConverter.H3_NODE, FlexmarkHtmlConverter.H4_NODE, FlexmarkHtmlConverter.H5_NODE, FlexmarkHtmlConverter.H6_NODE, "header", FlexmarkHtmlConverter.HR_NODE, "main", "menu", "nav", FlexmarkHtmlConverter.OL_NODE, FlexmarkHtmlConverter.P_NODE, FlexmarkHtmlConverter.PRE_NODE, "section", FlexmarkHtmlConverter.TABLE_NODE, FlexmarkHtmlConverter.UL_NODE)));
        OPTIONAL_TAGS.put("rt", new HashSet(Arrays.asList("rt", "rp")));
        OPTIONAL_TAGS.put("rp", new HashSet(Arrays.asList("rt", "rp")));
        OPTIONAL_TAGS.put("optgroup", new HashSet(Arrays.asList("optgroup")));
        OPTIONAL_TAGS.put("option", new HashSet(Arrays.asList("option", "optgroup")));
        OPTIONAL_TAGS.put("colgroup", new HashSet(Arrays.asList("colgroup")));
        OPTIONAL_TAGS.put(FlexmarkHtmlConverter.THEAD_NODE, new HashSet(Arrays.asList(FlexmarkHtmlConverter.TBODY_NODE, "tfoot")));
        OPTIONAL_TAGS.put(FlexmarkHtmlConverter.TBODY_NODE, new HashSet(Arrays.asList(FlexmarkHtmlConverter.TBODY_NODE, "tfoot")));
        OPTIONAL_TAGS.put("tfoot", new HashSet(Arrays.asList(FlexmarkHtmlConverter.TBODY_NODE)));
        OPTIONAL_TAGS.put(FlexmarkHtmlConverter.TR_NODE, new HashSet(Arrays.asList(FlexmarkHtmlConverter.TR_NODE)));
        OPTIONAL_TAGS.put(FlexmarkHtmlConverter.TD_NODE, new HashSet(Arrays.asList(FlexmarkHtmlConverter.TD_NODE, FlexmarkHtmlConverter.TH_NODE)));
        OPTIONAL_TAGS.put(FlexmarkHtmlConverter.TH_NODE, new HashSet(Arrays.asList(FlexmarkHtmlConverter.TD_NODE, FlexmarkHtmlConverter.TH_NODE)));
        PATTERN_MAP = new HtmlMatch[HtmlMatch.values().length];
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (HtmlMatch htmlMatch : HtmlMatch.values()) {
            if (htmlMatch != HtmlMatch.NONE) {
                if (sb.length() != 0) {
                    sb.append("|");
                }
                if (htmlMatch.caseInsentive) {
                    sb.append("(?i:");
                    sb.append(htmlMatch.open.pattern());
                    sb.append(")");
                } else {
                    sb.append(htmlMatch.open.pattern());
                }
                PATTERN_MAP[i] = htmlMatch;
            }
            i++;
        }
        START_PATTERN = Pattern.compile(sb.toString());
    }

    public HtmlDeepParser() {
        this(Collections.emptyList());
    }

    public HtmlDeepParser(List<String> list) {
        this.myOpenTags = new ArrayList<>();
        this.myClosingPattern = null;
        this.myHtmlMatch = null;
        this.myHtmlCount = 0;
        this.myFirstBlockTag = false;
        this.myBlockTags = new HashSet<>(BLOCK_TAGS);
        this.myBlockTags.addAll(list);
    }

    public ArrayList<String> getOpenTags() {
        return this.myOpenTags;
    }

    public Pattern getClosingPattern() {
        return this.myClosingPattern;
    }

    public HtmlMatch getHtmlMatch() {
        return this.myHtmlMatch;
    }

    public int getHtmlCount() {
        return this.myHtmlCount;
    }

    public boolean isFirstBlockTag() {
        return this.myFirstBlockTag;
    }

    public boolean isHtmlClosed() {
        return this.myClosingPattern == null && this.myOpenTags.isEmpty();
    }

    public boolean isBlankLineInterruptible() {
        if (this.myOpenTags.isEmpty() && this.myClosingPattern == null) {
            return true;
        }
        return this.myHtmlMatch == HtmlMatch.OPEN_TAG && this.myClosingPattern != null && this.myOpenTags.size() == 1;
    }

    public boolean haveOpenRawTag() {
        return (this.myClosingPattern == null || this.myHtmlMatch == HtmlMatch.OPEN_TAG) ? false : true;
    }

    public boolean haveOpenBlockTag() {
        if (!this.myOpenTags.isEmpty()) {
            Iterator<String> it = this.myOpenTags.iterator();
            while (it.hasNext()) {
                if (this.myBlockTags.contains(it.next())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean hadHtml() {
        return this.myHtmlCount > 0 || !isHtmlClosed();
    }

    private void openTag(String str) {
        if (!this.myOpenTags.isEmpty()) {
            String str2 = this.myOpenTags.get(this.myOpenTags.size() - 1);
            if (OPTIONAL_TAGS.containsKey(str2) && OPTIONAL_TAGS.get(str2).contains(str)) {
                this.myOpenTags.set(this.myOpenTags.size() - 1, str);
                return;
            }
        }
        this.myOpenTags.add(str);
        this.myFirstBlockTag = this.myBlockTags.contains(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x00aa, code lost:            if (r4.myHtmlCount == 0) goto L37;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void parseHtmlChunk(java.lang.CharSequence r5, boolean r6, boolean r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 717
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.parser.internal.HtmlDeepParser.parseHtmlChunk(java.lang.CharSequence, boolean, boolean, boolean):void");
    }
}
