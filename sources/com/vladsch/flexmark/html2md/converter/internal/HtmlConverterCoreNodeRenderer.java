package com.vladsch.flexmark.html2md.converter.internal;

import com.vladsch.flexmark.ast.Reference;
import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.ext.emoji.internal.EmojiReference;
import com.vladsch.flexmark.ext.emoji.internal.EmojiShortcuts;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.html2md.converter.HtmlConverterOptions;
import com.vladsch.flexmark.html2md.converter.HtmlConverterPhase;
import com.vladsch.flexmark.html2md.converter.HtmlMarkdownWriter;
import com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext;
import com.vladsch.flexmark.html2md.converter.HtmlNodeRendererHandler;
import com.vladsch.flexmark.html2md.converter.LinkConversion;
import com.vladsch.flexmark.html2md.converter.ListState;
import com.vladsch.flexmark.html2md.converter.PhasedHtmlNodeRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.MarkdownTable;
import com.vladsch.flexmark.util.format.RomanNumeral;
import com.vladsch.flexmark.util.format.TableCell;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.html.CellAlignment;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.RepeatedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/internal/HtmlConverterCoreNodeRenderer.class */
public class HtmlConverterCoreNodeRenderer implements PhasedHtmlNodeRenderer {
    public static final String EMOJI_ALT_PREFIX = "emoji ";
    public static final Pattern NUMERIC_DOT_LIST_PAT = Pattern.compile("^(\\d+)\\.\\s*$");
    public static final Pattern NUMERIC_PAREN_LIST_PAT = Pattern.compile("^(\\d+)\\)\\s*$");
    public static final Pattern NON_NUMERIC_DOT_LIST_PAT = Pattern.compile("^((?:(?:" + RomanNumeral.ROMAN_NUMERAL.pattern() + ")|(?:" + RomanNumeral.LOWERCASE_ROMAN_NUMERAL.pattern() + ")|[a-z]+|[A-Z]+))\\.\\s*$");
    public static final Pattern NON_NUMERIC_PAREN_LIST_PAT = Pattern.compile("^((?:[a-z]+|[A-Z]+))\\)\\s*$");
    public static final Pattern BULLET_LIST_PAT = Pattern.compile("^([·])\\s*$");
    public static final Pattern ALPHA_NUMERAL_PAT = Pattern.compile("^[a-z]+|[A-Z]+$");
    public static HashSet<String> explicitLinkTextTags = new HashSet<>(Arrays.asList(FlexmarkHtmlConverter.EXPLICIT_LINK_TEXT_TAGS));
    private final HtmlConverterOptions myHtmlConverterOptions;
    private MarkdownTable myTable;
    private boolean myTableSuppressColumns = false;
    private final HashMap<String, String> myAbbreviations = new HashMap<>();
    private final HashMap<String, String> myMacrosMap = new HashMap<>();

    public HtmlConverterCoreNodeRenderer(DataHolder dataHolder) {
        this.myHtmlConverterOptions = new HtmlConverterOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.html2md.converter.PhasedHtmlNodeRenderer
    public Set<HtmlConverterPhase> getHtmlConverterPhases() {
        return new HashSet(Arrays.asList(HtmlConverterPhase.COLLECT, HtmlConverterPhase.DOCUMENT_BOTTOM));
    }

    @Override // com.vladsch.flexmark.html2md.converter.HtmlNodeRenderer
    public Set<HtmlNodeRendererHandler<?>> getHtmlNodeRendererHandlers() {
        HashSet hashSet = new HashSet(Arrays.asList(new HtmlNodeRendererHandler(FlexmarkHtmlConverter.COMMENT_NODE, Comment.class, this::processComment), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.A_NODE, Element.class, this::processA), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.ABBR_NODE, Element.class, this::processAbbr), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.ASIDE_NODE, Element.class, this::processAside), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.B_NODE, Element.class, this::processStrong), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.BLOCKQUOTE_NODE, Element.class, this::processBlockQuote), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.BR_NODE, Element.class, this::processBr), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.CODE_NODE, Element.class, this::processCode), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.DEL_NODE, Element.class, this::processDel), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.DIV_NODE, Element.class, this::processDiv), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.DL_NODE, Element.class, this::processDl), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.EM_NODE, Element.class, this::processEmphasis), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.EMOJI_NODE, Element.class, this::processEmoji), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.H1_NODE, Element.class, this::processHeading), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.H2_NODE, Element.class, this::processHeading), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.H3_NODE, Element.class, this::processHeading), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.H4_NODE, Element.class, this::processHeading), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.H5_NODE, Element.class, this::processHeading), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.H6_NODE, Element.class, this::processHeading), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.HR_NODE, Element.class, this::processHr), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.I_NODE, Element.class, this::processEmphasis), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.IMG_NODE, Element.class, this::processImg), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.INPUT_NODE, Element.class, this::processInput), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.INS_NODE, Element.class, this::processIns), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.LI_NODE, Element.class, this::processLi), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.MATH_NODE, Element.class, this::processMath), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.OL_NODE, Element.class, this::processOl), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.P_NODE, Element.class, this::processP), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.PRE_NODE, Element.class, this::processPre), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.SPAN_NODE, Element.class, this::processSpan), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.STRIKE_NODE, Element.class, this::processDel), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.STRONG_NODE, Element.class, this::processStrong), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.SUB_NODE, Element.class, this::processSub), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.SUP_NODE, Element.class, this::processSup), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.SVG_NODE, Element.class, this::processSvg), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.TABLE_NODE, Element.class, this::processTable), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.U_NODE, Element.class, this::processIns), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.UL_NODE, Element.class, this::processUl), new HtmlNodeRendererHandler(FlexmarkHtmlConverter.TEXT_NODE, TextNode.class, this::processText), new HtmlNodeRendererHandler("", Node.class, this::processDefault)));
        if (this.myHtmlConverterOptions.unwrappedTags.length > 0) {
            for (String str : this.myHtmlConverterOptions.unwrappedTags) {
                hashSet.add(new HtmlNodeRendererHandler(str, Node.class, this::processUnwrapped));
            }
        }
        if (this.myHtmlConverterOptions.wrappedTags.length > 0) {
            for (String str2 : this.myHtmlConverterOptions.wrappedTags) {
                hashSet.add(new HtmlNodeRendererHandler(str2, Node.class, this::processWrapped));
            }
        }
        return hashSet;
    }

    @Override // com.vladsch.flexmark.html2md.converter.PhasedHtmlNodeRenderer
    public void renderDocument(HtmlNodeConverterContext htmlNodeConverterContext, LineAppendable lineAppendable, Document document, HtmlConverterPhase htmlConverterPhase) {
        ReferenceRepository referenceRepository;
        switch (htmlConverterPhase) {
            case COLLECT:
                com.vladsch.flexmark.util.ast.Document forDocument = htmlNodeConverterContext.getForDocument();
                if (forDocument != null && (referenceRepository = Parser.REFERENCES.get(forDocument)) != null) {
                    HashMap<String, Reference> referenceUrlToReferenceMap = htmlNodeConverterContext.getReferenceUrlToReferenceMap();
                    HashSet<Reference> externalReferences = htmlNodeConverterContext.getExternalReferences();
                    for (Reference reference : referenceRepository.getValues()) {
                        referenceUrlToReferenceMap.put(reference.getUrl().toString(), reference);
                        referenceUrlToReferenceMap.put(reference.getReference().toString(), reference);
                        externalReferences.add(reference);
                    }
                    return;
                }
                return;
            case DOCUMENT_BOTTOM:
                if (!this.myAbbreviations.isEmpty()) {
                    lineAppendable.blankLine();
                    for (Map.Entry<String, String> entry : this.myAbbreviations.entrySet()) {
                        lineAppendable.line().append("*[").append((CharSequence) entry.getKey()).append("]: ").append((CharSequence) entry.getValue()).line();
                    }
                    lineAppendable.blankLine();
                }
                HashMap<String, Reference> referenceUrlToReferenceMap2 = htmlNodeConverterContext.getReferenceUrlToReferenceMap();
                if (!referenceUrlToReferenceMap2.isEmpty()) {
                    boolean z = true;
                    HashSet<Reference> externalReferences2 = htmlNodeConverterContext.getExternalReferences();
                    for (Map.Entry<String, Reference> entry2 : referenceUrlToReferenceMap2.entrySet()) {
                        if (!externalReferences2.contains(entry2.getValue())) {
                            if (z) {
                                z = false;
                                lineAppendable.blankLine();
                            }
                            lineAppendable.line().append((CharSequence) entry2.getValue().getChars()).line();
                        }
                    }
                    if (!z) {
                        lineAppendable.blankLine();
                    }
                }
                if (!this.myMacrosMap.isEmpty()) {
                    for (Map.Entry<String, String> entry3 : this.myMacrosMap.entrySet()) {
                        lineAppendable.blankLine();
                        lineAppendable.append(">>>").append((CharSequence) entry3.getKey()).line();
                        lineAppendable.append((CharSequence) BasedSequence.of(entry3.getValue()).trimEnd()).append(SequenceUtils.EOL);
                        lineAppendable.append("<<<\n");
                        lineAppendable.blankLine();
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static int getMaxRepeatedChars(CharSequence charSequence, char c, int i) {
        int indexOf;
        BasedSequence of = BasedSequence.of(charSequence);
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= of.length() || (indexOf = of.indexOf(c, i3)) < 0) {
                break;
            }
            int countLeading = of.countLeading(i4 -> {
                return i4 == c;
            }, indexOf);
            if (i <= countLeading) {
                i = countLeading + 1;
            }
            i2 = indexOf + countLeading;
        }
        return i;
    }

    public static boolean hasChildrenOfType(Element element, Set<String> set) {
        Iterator<Element> it = element.children().iterator();
        while (it.hasNext()) {
            if (set.contains(it.next().nodeName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFirstChild(Element element) {
        Iterator<Node> it = element.parent().childNodes().iterator();
        while (it.hasNext()) {
            Node next = it.next();
            if (next instanceof Element) {
                return element == next;
            }
            if (next.nodeName().equals(FlexmarkHtmlConverter.TEXT_NODE) && !next.outerHtml().trim().isEmpty()) {
                return false;
            }
        }
        return false;
    }

    public static boolean isLastChild(Element element) {
        Element element2;
        Elements children = element.parent().children();
        int size = children.size();
        do {
            int i = size;
            size--;
            if (i > 0) {
                element2 = children.get(size);
            } else {
                return false;
            }
        } while (!(element2 instanceof Element));
        return element == element2;
    }

    private void processDefault(Node node, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.renderDefault(node);
    }

    private boolean isHeading(Element element) {
        if (element != null) {
            String lowerCase = element.tagName().toLowerCase();
            for (String str : FlexmarkHtmlConverter.HEADING_NODES) {
                if (lowerCase.equals(str)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private void processA(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        Reference orCreateReference;
        int indexOf;
        if (element.hasAttr("href")) {
            LinkConversion linkConversion = this.myHtmlConverterOptions.extInlineLink;
            if (linkConversion.isSuppressed()) {
                return;
            }
            String attr = element.attr("href");
            String url = htmlNodeConverterContext.resolveLink(LinkType.LINK, attr, Boolean.FALSE).getUrl();
            if (!htmlMarkdownWriter.isPreFormatted()) {
                if (!linkConversion.isParsed()) {
                    if (!linkConversion.isSuppressed()) {
                        htmlNodeConverterContext.processWrapped(element, null, true);
                        return;
                    }
                    return;
                }
                htmlNodeConverterContext.pushState(element);
                String trim = htmlNodeConverterContext.processTextNodes(element).trim();
                String attr2 = element.hasAttr(Attribute.TITLE_ATTR) ? element.attr(Attribute.TITLE_ATTR) : null;
                if (!trim.isEmpty() || !url.contains("#") || (!isHeading(element.parent()) && !url.equals("#") && (htmlNodeConverterContext.getState() == null || htmlNodeConverterContext.getState().getAttributes().get(Attribute.ID_ATTR) == null || htmlNodeConverterContext.getState().getAttributes().get(Attribute.ID_ATTR).getValue().isEmpty()))) {
                    if (this.myHtmlConverterOptions.extractAutoLinks && attr.equals(trim) && (attr2 == null || attr2.isEmpty())) {
                        if (this.myHtmlConverterOptions.wrapAutoLinks) {
                            htmlMarkdownWriter.append('<');
                        }
                        htmlMarkdownWriter.append((CharSequence) url);
                        if (this.myHtmlConverterOptions.wrapAutoLinks) {
                            htmlMarkdownWriter.append('>');
                        }
                        htmlNodeConverterContext.transferIdToParent();
                    } else if (!linkConversion.isTextOnly() && !url.startsWith("javascript:")) {
                        boolean z = false;
                        if (linkConversion.isReference() && !hasChildrenOfType(element, explicitLinkTextTags) && (orCreateReference = htmlNodeConverterContext.getOrCreateReference(url, trim, attr2)) != null) {
                            z = true;
                            if (!orCreateReference.getReference().equals(trim)) {
                                ((HtmlMarkdownWriter) htmlMarkdownWriter.append('[')).append((CharSequence) trim).append((CharSequence) "][").append((CharSequence) orCreateReference.getReference()).append(']');
                            } else {
                                ((HtmlMarkdownWriter) htmlMarkdownWriter.append('[')).append((CharSequence) trim).append((CharSequence) "][]");
                            }
                        }
                        if (!z) {
                            htmlMarkdownWriter.append('[');
                            htmlMarkdownWriter.append((CharSequence) trim);
                            htmlMarkdownWriter.append(']');
                            ((HtmlMarkdownWriter) htmlMarkdownWriter.append('(')).append((CharSequence) url);
                            if (attr2 != null) {
                                ((HtmlMarkdownWriter) htmlMarkdownWriter.append(" \"")).append((CharSequence) attr2.replace(SequenceUtils.EOL, this.myHtmlConverterOptions.eolInTitleAttribute).replace("\"", "\\\"")).append('\"');
                            }
                            htmlMarkdownWriter.append(")");
                        }
                    } else if (attr.equals(trim)) {
                        htmlMarkdownWriter.append((CharSequence) url);
                    } else {
                        htmlMarkdownWriter.append((CharSequence) trim);
                    }
                    htmlNodeConverterContext.excludeAttributes("href", Attribute.TITLE_ATTR);
                    htmlNodeConverterContext.popState(htmlMarkdownWriter);
                    return;
                }
                htmlNodeConverterContext.transferIdToParent();
                htmlNodeConverterContext.popState(null);
                return;
            }
            int lastIndexOf = url.lastIndexOf(47);
            if (lastIndexOf != -1 && (indexOf = url.indexOf(35, lastIndexOf)) != -1 && lastIndexOf + 1 == indexOf) {
                url = url.substring(0, lastIndexOf) + url.substring(indexOf);
            }
            htmlMarkdownWriter.append((CharSequence) url);
            return;
        }
        boolean z2 = false;
        if (element.childNodeSize() == 0 && element.parent().tagName().equals("body")) {
            z2 = true;
        }
        htmlNodeConverterContext.processTextNodes(element, z2);
    }

    private void processAbbr(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        if (element.hasAttr(Attribute.TITLE_ATTR)) {
            this.myAbbreviations.put(htmlNodeConverterContext.processTextNodes(element).trim(), element.attr(Attribute.TITLE_ATTR));
        }
    }

    private void processAside(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        if (isFirstChild(element)) {
            htmlMarkdownWriter.line();
        }
        htmlMarkdownWriter.pushPrefix();
        htmlMarkdownWriter.addPrefix("| ");
        htmlNodeConverterContext.renderChildren(element, true, null);
        htmlMarkdownWriter.line();
        htmlMarkdownWriter.popPrefix();
    }

    private void processBlockQuote(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        if (isFirstChild(element)) {
            htmlMarkdownWriter.line();
        }
        htmlMarkdownWriter.pushPrefix();
        htmlMarkdownWriter.addPrefix("> ");
        htmlNodeConverterContext.renderChildren(element, true, null);
        htmlMarkdownWriter.line();
        htmlMarkdownWriter.popPrefix();
    }

    private void processBr(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        if (htmlMarkdownWriter.isPreFormatted()) {
            htmlMarkdownWriter.append('\n');
            return;
        }
        int options = htmlMarkdownWriter.getOptions();
        htmlMarkdownWriter.setOptions(options & ((LineAppendable.F_TRIM_TRAILING_WHITESPACE | LineAppendable.F_COLLAPSE_WHITESPACE) ^ (-1)));
        if (htmlMarkdownWriter.getPendingEOL() == 0) {
            htmlMarkdownWriter.append(' ', 2).line();
        } else if (htmlMarkdownWriter.getPendingEOL() == 1) {
            if (!htmlMarkdownWriter.toString().endsWith("<br />")) {
                if (this.myHtmlConverterOptions.brAsParaBreaks) {
                    htmlMarkdownWriter.blankLine();
                }
            } else if (this.myHtmlConverterOptions.brAsExtraBlankLines) {
                htmlMarkdownWriter.append("<br />").blankLine();
            }
        } else if (this.myHtmlConverterOptions.brAsExtraBlankLines) {
            htmlMarkdownWriter.append("<br />").blankLine();
        }
        htmlMarkdownWriter.setOptions(options);
    }

    private void processCode(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.processConditional(this.myHtmlConverterOptions.extInlineCode, element, () -> {
            CharSequence repeatOf = RepeatedSequence.repeatOf("`", getMaxRepeatedChars(BasedSequence.of(element.ownText()), '`', 1));
            htmlNodeConverterContext.inlineCode(() -> {
                htmlNodeConverterContext.processTextNodes(element, false, this.myHtmlConverterOptions.extInlineCode.isTextOnly() ? "" : repeatOf);
            });
        });
    }

    private void processDel(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.processConditional(this.myHtmlConverterOptions.extInlineDel, element, () -> {
            HtmlNodeConverterContext htmlNodeConverterContext2;
            Element element2;
            String str;
            boolean z;
            if (!this.myHtmlConverterOptions.preCodePreserveEmphasis && htmlMarkdownWriter.isPreFormatted()) {
                htmlNodeConverterContext2 = htmlNodeConverterContext;
                element2 = element;
                str = "";
            } else {
                htmlNodeConverterContext2 = htmlNodeConverterContext;
                element2 = element;
                str = this.myHtmlConverterOptions.extInlineDel.isTextOnly() ? "" : "~~";
                if (element.nextElementSibling() != null) {
                    z = true;
                    htmlNodeConverterContext2.wrapTextNodes(element2, str, z);
                }
            }
            z = false;
            htmlNodeConverterContext2.wrapTextNodes(element2, str, z);
        });
    }

    private void handleDivTable(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        Node next;
        MarkdownTable markdownTable = this.myTable;
        this.myTable = new MarkdownTable("", this.myHtmlConverterOptions.tableOptions);
        this.myTableSuppressColumns = false;
        Node node = element;
        do {
            if (!node.nodeName().toLowerCase().equals(FlexmarkHtmlConverter.DIV_NODE)) {
                if (node instanceof Element) {
                    break;
                }
                next = htmlNodeConverterContext.next();
                node = next;
            } else {
                if (!((Element) node).classNames().contains("wt-data-grid__row")) {
                    break;
                }
                handleDivTableRow((Element) node, htmlNodeConverterContext, htmlMarkdownWriter);
                next = htmlNodeConverterContext.next();
                node = next;
            }
        } while (next != null);
        this.myTable.finalizeTable();
        if (this.myTable.getMaxColumns() > 0) {
            htmlMarkdownWriter.blankLine();
            this.myTable.appendTable(htmlMarkdownWriter);
            htmlMarkdownWriter.tailBlankLine();
        }
        this.myTable = markdownTable;
    }

    private void handleDivTableRow(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.pushState(element);
        this.myTable.setHeader(hasIntersection(element.classNames(), this.myHtmlConverterOptions.divTableHdrClasses));
        while (true) {
            Node next = htmlNodeConverterContext.next();
            if (next != null) {
                if (!next.nodeName().toLowerCase().equals(FlexmarkHtmlConverter.DIV_NODE)) {
                    if (next instanceof Element) {
                        break;
                    }
                } else if (!hasIntersection(((Element) next).classNames(), this.myHtmlConverterOptions.divTableCellClasses)) {
                    break;
                } else {
                    handleDivTableCell((Element) next, htmlNodeConverterContext, htmlMarkdownWriter);
                }
            } else {
                break;
            }
        }
        this.myTable.nextRow();
        htmlNodeConverterContext.popState(htmlMarkdownWriter);
    }

    private void handleDivTableCell(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        String replaceAll = htmlNodeConverterContext.processTextNodes(element).trim().replaceAll("\\s*\n\\s*", SequenceUtils.SPACE);
        CellAlignment cellAlignment = CellAlignment.NONE;
        if (!this.myTableSuppressColumns) {
            this.myTable.addCell(new TableCell(null, BasedSequence.NULL, replaceAll.replace(SequenceUtils.EOL, SequenceUtils.SPACE), BasedSequence.NULL, 1, 1, cellAlignment));
        }
    }

    private boolean hasIntersection(Set<String> set, String[] strArr) {
        for (String str : strArr) {
            if (set.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private void processDiv(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        int lineCountWithPending;
        if (this.myHtmlConverterOptions.divTableProcessing && hasIntersection(element.classNames(), this.myHtmlConverterOptions.divTableRowClasses)) {
            handleDivTable(element, htmlNodeConverterContext, htmlMarkdownWriter);
            return;
        }
        if (!isFirstChild(element)) {
            if (!this.myHtmlConverterOptions.divAsParagraph) {
                int pendingEOL = htmlMarkdownWriter.getPendingEOL();
                if (pendingEOL != 0) {
                    if (pendingEOL == 1 && (lineCountWithPending = htmlMarkdownWriter.getLineCountWithPending()) > 0) {
                        BasedSequence lineContent = htmlMarkdownWriter.getLineContent(lineCountWithPending - 1);
                        int countTrailing = BasedSequence.of(lineContent).countTrailing(CharPredicate.SPACE_TAB);
                        if (countTrailing < 2) {
                            htmlMarkdownWriter.removeLines(lineCountWithPending - 1, lineCountWithPending);
                            htmlMarkdownWriter.append((CharSequence) lineContent);
                            htmlMarkdownWriter.lineWithTrailingSpaces(2 - countTrailing);
                        }
                    }
                } else {
                    htmlMarkdownWriter.lineWithTrailingSpaces(Utils.minLimit(0, 2 - htmlMarkdownWriter.getPendingSpace()));
                }
            } else {
                htmlMarkdownWriter.blankLine();
            }
        }
        htmlNodeConverterContext.renderChildren(element, false, null);
        if (!isLastChild(element)) {
            htmlMarkdownWriter.line();
            if (this.myHtmlConverterOptions.divAsParagraph) {
                htmlMarkdownWriter.blankLine();
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0029. Please report as an issue. */
    private void processDl(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.pushState(element);
        boolean z = true;
        boolean z2 = true;
        while (true) {
            Node next = htmlNodeConverterContext.next();
            if (next != null) {
                String lowerCase = next.nodeName().toLowerCase();
                boolean z3 = -1;
                switch (lowerCase.hashCode()) {
                    case 3200:
                        if (lowerCase.equals(FlexmarkHtmlConverter.DD_NODE)) {
                            z3 = true;
                            break;
                        }
                        break;
                    case 3216:
                        if (lowerCase.equals(FlexmarkHtmlConverter.DT_NODE)) {
                            z3 = false;
                            break;
                        }
                        break;
                }
                switch (z3) {
                    case false:
                        htmlMarkdownWriter.blankLineIf(z).lineIf(!z2);
                        htmlNodeConverterContext.processTextNodes(next, false);
                        htmlMarkdownWriter.line();
                        z = false;
                        z2 = false;
                        break;
                    case true:
                        handleDefinition((Element) next, htmlNodeConverterContext, htmlMarkdownWriter);
                        z = true;
                        z2 = false;
                        break;
                }
            } else {
                htmlNodeConverterContext.popState(htmlMarkdownWriter);
                return;
            }
        }
    }

    private void handleDefinition(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.pushState(element);
        int options = htmlMarkdownWriter.getOptions();
        Elements children = element.children();
        boolean z = false;
        if (!children.isEmpty() && children.get(0).tagName().equalsIgnoreCase(FlexmarkHtmlConverter.P_NODE)) {
            htmlMarkdownWriter.blankLine();
            z = true;
        }
        CharSequence repeatOf = RepeatedSequence.repeatOf(SequenceUtils.SPACE, this.myHtmlConverterOptions.listContentIndent ? this.myHtmlConverterOptions.definitionMarkerSpaces + 1 : 4);
        htmlMarkdownWriter.line().setOptions(options & (LineAppendable.F_COLLAPSE_WHITESPACE ^ (-1)));
        htmlMarkdownWriter.append(':').append(' ', this.myHtmlConverterOptions.definitionMarkerSpaces);
        htmlMarkdownWriter.pushPrefix();
        htmlMarkdownWriter.addPrefix(repeatOf, true);
        htmlMarkdownWriter.setOptions(options);
        if (z) {
            htmlNodeConverterContext.renderChildren(element, true, null);
        } else {
            htmlNodeConverterContext.processTextNodes(element, false);
        }
        htmlMarkdownWriter.line();
        htmlMarkdownWriter.popPrefix();
        htmlNodeConverterContext.popState(htmlMarkdownWriter);
    }

    private void processEmoji(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        EmojiReference.Emoji emojiFromURI;
        if (element.hasAttr("alias")) {
            htmlMarkdownWriter.append(':').append((CharSequence) element.attr("alias")).append(':');
        } else if (element.hasAttr("fallback-src") && (emojiFromURI = EmojiShortcuts.getEmojiFromURI(element.attr("fallback-src"))) != null) {
            htmlMarkdownWriter.append(':').append((CharSequence) emojiFromURI.shortcut).append(':');
        } else {
            htmlNodeConverterContext.renderDefault(element);
        }
    }

    private void processEmphasis(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.processConditional(this.myHtmlConverterOptions.extInlineEmphasis, element, () -> {
            HtmlNodeConverterContext htmlNodeConverterContext2;
            Element element2;
            String str;
            boolean z;
            if (!this.myHtmlConverterOptions.preCodePreserveEmphasis && htmlMarkdownWriter.isPreFormatted()) {
                htmlNodeConverterContext2 = htmlNodeConverterContext;
                element2 = element;
                str = "";
            } else {
                htmlNodeConverterContext2 = htmlNodeConverterContext;
                element2 = element;
                str = this.myHtmlConverterOptions.extInlineEmphasis.isTextOnly() ? "" : "*";
                if (element.nextElementSibling() != null) {
                    z = true;
                    htmlNodeConverterContext2.wrapTextNodes(element2, str, z);
                }
            }
            z = false;
            htmlNodeConverterContext2.wrapTextNodes(element2, str, z);
        });
    }

    private void processHr(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlMarkdownWriter.blankLine().append((CharSequence) this.myHtmlConverterOptions.thematicBreak).blankLine();
    }

    private void processImg(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        int indexOf;
        int indexOf2;
        if (element.hasAttr("src")) {
            String attr = element.attr("src");
            EmojiReference.Emoji emojiFromURI = EmojiShortcuts.getEmojiFromURI(attr);
            EmojiReference.Emoji emoji = emojiFromURI;
            if (emojiFromURI == null && element.hasAttr("alt")) {
                String attr2 = element.attr("alt");
                if (attr2.startsWith(EMOJI_ALT_PREFIX) && (indexOf2 = attr2.indexOf(":", 6)) > 0) {
                    String substring = attr2.substring(6, indexOf2);
                    EmojiReference.Emoji emojiFromShortcut = EmojiShortcuts.getEmojiFromShortcut(attr2.substring(indexOf2 + 1));
                    if (emojiFromShortcut.category.equals(substring)) {
                        emoji = emojiFromShortcut;
                    }
                }
            }
            if (emoji != null) {
                htmlMarkdownWriter.append(':').append((CharSequence) emoji.shortcut).append(':');
                return;
            }
            LinkConversion linkConversion = this.myHtmlConverterOptions.extInlineImage;
            if (linkConversion.isSuppressed()) {
                return;
            }
            if (!linkConversion.isParsed()) {
                if (!linkConversion.isSuppressed()) {
                    htmlNodeConverterContext.processWrapped(element, null, false);
                    return;
                }
                return;
            }
            String replace = !element.hasAttr("alt") ? null : element.attr("alt").trim().replace("[", "\\[").replace("]", "\\]");
            String str = replace;
            if (replace != null && str.isEmpty()) {
                str = null;
            }
            String replace2 = !element.hasAttr(Attribute.TITLE_ATTR) ? null : element.attr(Attribute.TITLE_ATTR).replace(SequenceUtils.EOL, this.myHtmlConverterOptions.eolInTitleAttribute).replace("\"", "\\\"");
            String str2 = replace2;
            if (replace2 != null && str2.isEmpty()) {
                str2 = null;
            }
            if (!linkConversion.isTextOnly()) {
                String url = htmlNodeConverterContext.resolveLink(LinkType.IMAGE, attr, Boolean.FALSE).getUrl();
                int indexOf3 = url.indexOf(63);
                if (indexOf3 >= 0) {
                    indexOf = url.indexOf("%0A", indexOf3);
                } else {
                    indexOf = indexOf3;
                }
                boolean z = indexOf3 > 0 && indexOf > 0;
                boolean z2 = false;
                if (linkConversion.isReference() && !z) {
                    Reference orCreateReference = htmlNodeConverterContext.getOrCreateReference(url, str == null ? "image" : str, str2);
                    if (orCreateReference != null) {
                        z2 = true;
                        if (str == null || orCreateReference.getReference().equals(str)) {
                            htmlMarkdownWriter.append("![").append((CharSequence) orCreateReference.getReference()).append((CharSequence) "][]");
                        } else {
                            htmlMarkdownWriter.append("![").append((CharSequence) str).append((CharSequence) "][").append((CharSequence) orCreateReference.getReference()).append((CharSequence) "]");
                        }
                    }
                }
                if (!z2) {
                    htmlMarkdownWriter.append("![");
                    if (str != null) {
                        htmlMarkdownWriter.append((CharSequence) str);
                    }
                    htmlMarkdownWriter.append(']').append('(');
                    if (z) {
                        htmlMarkdownWriter.append((CharSequence) url, 0, indexOf3 + 1);
                        htmlMarkdownWriter.line().append((CharSequence) Utils.urlDecode(url.substring(indexOf3 + 1).replace("+", "%2B"), "UTF8"));
                    } else {
                        htmlMarkdownWriter.append((CharSequence) url);
                    }
                    if (str2 != null) {
                        htmlMarkdownWriter.append(" \"").append((CharSequence) str2).append('\"');
                    }
                    htmlMarkdownWriter.append(")");
                    return;
                }
                return;
            }
            if (str == null) {
                if (str2 != null) {
                    htmlMarkdownWriter.append((CharSequence) str2);
                    return;
                }
                return;
            }
            htmlMarkdownWriter.append((CharSequence) str);
        }
    }

    private void processInput(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        boolean z = false;
        Element firstElementSibling = element.firstElementSibling();
        if (firstElementSibling == null || element == firstElementSibling) {
            z = element.parent().tagName().equalsIgnoreCase(FlexmarkHtmlConverter.LI_NODE);
        }
        if (z && element.hasAttr("type") && "checkbox".equalsIgnoreCase(element.attr("type"))) {
            if (element.hasAttr("checked")) {
                htmlMarkdownWriter.append("[x] ");
                return;
            } else {
                htmlMarkdownWriter.append("[ ] ");
                return;
            }
        }
        htmlNodeConverterContext.renderDefault(element);
    }

    private void processIns(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.processConditional(this.myHtmlConverterOptions.extInlineIns, element, () -> {
            HtmlNodeConverterContext htmlNodeConverterContext2;
            Element element2;
            String str;
            boolean z;
            if (!this.myHtmlConverterOptions.preCodePreserveEmphasis && htmlMarkdownWriter.isPreFormatted()) {
                htmlNodeConverterContext2 = htmlNodeConverterContext;
                element2 = element;
                str = "";
            } else {
                htmlNodeConverterContext2 = htmlNodeConverterContext;
                element2 = element;
                str = this.myHtmlConverterOptions.extInlineIns.isTextOnly() ? "" : "++";
                if (element.nextElementSibling() != null) {
                    z = true;
                    htmlNodeConverterContext2.wrapTextNodes(element2, str, z);
                }
            }
            z = false;
            htmlNodeConverterContext2.wrapTextNodes(element2, str, z);
        });
    }

    private void processStrong(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.processConditional(this.myHtmlConverterOptions.extInlineStrong, element, () -> {
            HtmlNodeConverterContext htmlNodeConverterContext2;
            Element element2;
            String str;
            boolean z;
            if (!this.myHtmlConverterOptions.preCodePreserveEmphasis && htmlMarkdownWriter.isPreFormatted()) {
                htmlNodeConverterContext2 = htmlNodeConverterContext;
                element2 = element;
                str = "";
            } else {
                htmlNodeConverterContext2 = htmlNodeConverterContext;
                element2 = element;
                str = this.myHtmlConverterOptions.extInlineStrong.isTextOnly() ? "" : "**";
                if (element.nextElementSibling() != null) {
                    z = true;
                    htmlNodeConverterContext2.wrapTextNodes(element2, str, z);
                }
            }
            z = false;
            htmlNodeConverterContext2.wrapTextNodes(element2, str, z);
        });
    }

    private void processSub(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.processConditional(this.myHtmlConverterOptions.extInlineSub, element, () -> {
            if (this.myHtmlConverterOptions.extInlineSub.isTextOnly() || (!this.myHtmlConverterOptions.preCodePreserveEmphasis && htmlMarkdownWriter.isPreFormatted())) {
                htmlNodeConverterContext.wrapTextNodes(element, "", false);
            } else {
                htmlNodeConverterContext.wrapTextNodes(element, "~", false);
            }
        });
    }

    private void processSup(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.processConditional(this.myHtmlConverterOptions.extInlineSup, element, () -> {
            if (this.myHtmlConverterOptions.extInlineSup.isTextOnly() || (!this.myHtmlConverterOptions.preCodePreserveEmphasis && htmlMarkdownWriter.isPreFormatted())) {
                htmlNodeConverterContext.wrapTextNodes(element, "", false);
            } else {
                htmlNodeConverterContext.wrapTextNodes(element, "^", false);
            }
        });
    }

    private void processMath(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.processConditional(this.myHtmlConverterOptions.extMath, element, () -> {
            boolean isTextOnly = this.myHtmlConverterOptions.extMath.isTextOnly();
            htmlNodeConverterContext.processTextNodes(element, false, isTextOnly ? "" : "$`", isTextOnly ? "" : "`$");
        });
    }

    private void handleListItem(HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter, Element element, ListState listState) {
        htmlNodeConverterContext.pushState(element);
        listState.itemCount++;
        String itemPrefix = listState.getItemPrefix(this.myHtmlConverterOptions);
        CharSequence repeatOf = RepeatedSequence.repeatOf(SequenceUtils.SPACE, this.myHtmlConverterOptions.listContentIndent ? itemPrefix.length() : 4);
        htmlMarkdownWriter.line().append((CharSequence) itemPrefix);
        htmlMarkdownWriter.pushPrefix();
        htmlMarkdownWriter.addPrefix(repeatOf, true);
        int offsetWithPending = htmlMarkdownWriter.offsetWithPending();
        htmlNodeConverterContext.renderChildren(element, true, null);
        if (offsetWithPending == htmlMarkdownWriter.offsetWithPending()) {
            int options = htmlMarkdownWriter.getOptions();
            htmlMarkdownWriter.setOptions(options & ((LineAppendable.F_TRIM_TRAILING_WHITESPACE | LineAppendable.F_TRIM_LEADING_WHITESPACE) ^ (-1)));
            htmlMarkdownWriter.line();
            htmlMarkdownWriter.setOptions(options);
        } else {
            htmlMarkdownWriter.line();
        }
        htmlMarkdownWriter.popPrefix();
        htmlNodeConverterContext.popState(htmlMarkdownWriter);
    }

    private boolean hasListItemParent(Element element) {
        Element parent = element.parent();
        while (true) {
            Element element2 = parent;
            if (element2 != null) {
                if (element2.tagName().equalsIgnoreCase(FlexmarkHtmlConverter.LI_NODE)) {
                    return true;
                }
                parent = element2.parent();
            } else {
                return false;
            }
        }
    }

    private boolean haveListItemAncestor(Node node) {
        Node parent = node.parent();
        while (true) {
            Node node2 = parent;
            if (node2 != null) {
                if (node2.nodeName().toLowerCase().equals(FlexmarkHtmlConverter.LI_NODE)) {
                    return true;
                }
                parent = node2.parent();
            } else {
                return false;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleList(com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext r9, com.vladsch.flexmark.html2md.converter.HtmlMarkdownWriter r10, org.jsoup.nodes.Element r11, boolean r12, boolean r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 559
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.html2md.converter.internal.HtmlConverterCoreNodeRenderer.handleList(com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.html2md.converter.HtmlMarkdownWriter, org.jsoup.nodes.Element, boolean, boolean, boolean):void");
    }

    private void processLi(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        handleList(htmlNodeConverterContext, htmlMarkdownWriter, element, false, true, false);
    }

    private void processOl(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        handleList(htmlNodeConverterContext, htmlMarkdownWriter, element, true, false, false);
    }

    private void processUl(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        handleList(htmlNodeConverterContext, htmlMarkdownWriter, element, false, false, false);
    }

    private void processSvg(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        if (!element.hasClass("octicon")) {
            htmlNodeConverterContext.renderDefault(element);
        }
    }

    private void processP(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        boolean z = false;
        boolean z2 = false;
        Element firstElementSibling = element.firstElementSibling();
        if (firstElementSibling == null || element == firstElementSibling) {
            String tagName = element.parent().tagName();
            z = tagName.equalsIgnoreCase(FlexmarkHtmlConverter.LI_NODE);
            z2 = tagName.equalsIgnoreCase(FlexmarkHtmlConverter.DD_NODE);
        }
        htmlMarkdownWriter.blankLineIf((z || z2 || isFirstChild(element)) ? false : true);
        if (element.childNodeSize() == 0) {
            if (this.myHtmlConverterOptions.brAsExtraBlankLines) {
                htmlMarkdownWriter.append("<br />").blankLine();
            }
        } else {
            htmlNodeConverterContext.processTextNodes(element, false);
        }
        htmlMarkdownWriter.line();
        if (z || z2) {
            htmlMarkdownWriter.tailBlankLine();
        }
    }

    private void processHeading(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        int i;
        boolean z;
        String lowerCase = element.nodeName().toLowerCase();
        boolean z2 = -1;
        switch (lowerCase.hashCode()) {
            case 3273:
                if (lowerCase.equals(FlexmarkHtmlConverter.H1_NODE)) {
                    z2 = false;
                    break;
                }
                break;
            case 3274:
                if (lowerCase.equals(FlexmarkHtmlConverter.H2_NODE)) {
                    z2 = true;
                    break;
                }
                break;
            case 3275:
                if (lowerCase.equals(FlexmarkHtmlConverter.H3_NODE)) {
                    z2 = 2;
                    break;
                }
                break;
            case 3276:
                if (lowerCase.equals(FlexmarkHtmlConverter.H4_NODE)) {
                    z2 = 3;
                    break;
                }
                break;
            case 3277:
                if (lowerCase.equals(FlexmarkHtmlConverter.H5_NODE)) {
                    z2 = 4;
                    break;
                }
                break;
            case 3278:
                if (lowerCase.equals(FlexmarkHtmlConverter.H6_NODE)) {
                    z2 = 5;
                    break;
                }
                break;
        }
        switch (z2) {
            case false:
                i = 1;
                z = this.myHtmlConverterOptions.skipHeading1;
                break;
            case true:
                i = 2;
                z = this.myHtmlConverterOptions.skipHeading2;
                break;
            case true:
                i = 3;
                z = this.myHtmlConverterOptions.skipHeading3;
                break;
            case true:
                i = 4;
                z = this.myHtmlConverterOptions.skipHeading4;
                break;
            case true:
                i = 5;
                z = this.myHtmlConverterOptions.skipHeading5;
                break;
            default:
                i = 6;
                z = this.myHtmlConverterOptions.skipHeading6;
                break;
        }
        String trim = htmlNodeConverterContext.processTextNodes(element).trim();
        if (!trim.isEmpty()) {
            htmlMarkdownWriter.blankLine();
            if (z) {
                htmlMarkdownWriter.append((CharSequence) trim);
                return;
            }
            if (this.myHtmlConverterOptions.setextHeadings && i <= 2) {
                htmlMarkdownWriter.append((CharSequence) trim);
                htmlMarkdownWriter.line().append(i == 1 ? '=' : '-', Utils.minLimit(trim.length() + htmlNodeConverterContext.outputAttributes(htmlMarkdownWriter, SequenceUtils.SPACE), this.myHtmlConverterOptions.minSetextHeadingMarkerLength));
            } else {
                htmlMarkdownWriter.append('#', i).append(' ');
                htmlMarkdownWriter.append((CharSequence) trim);
                htmlNodeConverterContext.outputAttributes(htmlMarkdownWriter, SequenceUtils.SPACE);
            }
            htmlMarkdownWriter.blankLine();
        }
    }

    private void processPre(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.pushState(element);
        boolean z = false;
        String str = "";
        HtmlNodeConverterContext subContext = htmlNodeConverterContext.getSubContext();
        subContext.getMarkdown().setOptions(htmlMarkdownWriter.getOptions() & ((LineAppendable.F_COLLAPSE_WHITESPACE | LineAppendable.F_TRIM_TRAILING_WHITESPACE) ^ (-1)));
        subContext.getMarkdown().openPreFormatted(false);
        while (true) {
            Node next = htmlNodeConverterContext.next();
            if (next == null) {
                break;
            }
            if (next.nodeName().equalsIgnoreCase(FlexmarkHtmlConverter.CODE_NODE) || next.nodeName().equalsIgnoreCase("tt")) {
                z = true;
                Element element2 = (Element) next;
                subContext.renderChildren(element2, false, null);
                if (str.isEmpty()) {
                    str = Utils.removePrefix(element2.className(), "language-");
                }
            } else if (next.nodeName().equalsIgnoreCase(FlexmarkHtmlConverter.BR_NODE)) {
                subContext.getMarkdown().append((CharSequence) SequenceUtils.EOL);
            } else if (next.nodeName().equalsIgnoreCase(FlexmarkHtmlConverter.TEXT_NODE)) {
                subContext.getMarkdown().append((CharSequence) ((TextNode) next).getWholeText());
            } else {
                subContext.renderChildren(next, false, null);
            }
        }
        subContext.getMarkdown().closePreFormatted();
        String htmlMarkdownWriter2 = subContext.getMarkdown().toString(Integer.MAX_VALUE, 2);
        CharSequence repeatOf = RepeatedSequence.repeatOf("`", getMaxRepeatedChars(htmlMarkdownWriter2, '`', 3));
        if (!this.myHtmlConverterOptions.skipFencedCode && (!str.isEmpty() || htmlMarkdownWriter2.trim().isEmpty() || !z)) {
            htmlMarkdownWriter.blankLine().append(repeatOf);
            if (!str.isEmpty()) {
                htmlMarkdownWriter.append((CharSequence) str);
            }
            htmlMarkdownWriter.line();
            htmlMarkdownWriter.openPreFormatted(true);
            htmlMarkdownWriter.append((CharSequence) (htmlMarkdownWriter2.isEmpty() ? SequenceUtils.EOL : htmlMarkdownWriter2));
            htmlMarkdownWriter.closePreFormatted();
            htmlMarkdownWriter.line().append(repeatOf).line();
            htmlMarkdownWriter.tailBlankLine();
        } else {
            htmlMarkdownWriter.blankLine();
            htmlMarkdownWriter.pushPrefix();
            htmlMarkdownWriter.addPrefix((CharSequence) this.myHtmlConverterOptions.codeIndent);
            htmlMarkdownWriter.openPreFormatted(true);
            htmlMarkdownWriter.append((CharSequence) (htmlMarkdownWriter2.isEmpty() ? SequenceUtils.EOL : htmlMarkdownWriter2));
            htmlMarkdownWriter.closePreFormatted();
            htmlMarkdownWriter.line();
            htmlMarkdownWriter.tailBlankLine();
            htmlMarkdownWriter.popPrefix();
        }
        htmlNodeConverterContext.popState(htmlMarkdownWriter);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00cc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ee A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0103 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0026 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void processTable(org.jsoup.nodes.Element r7, com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext r8, com.vladsch.flexmark.html2md.converter.HtmlMarkdownWriter r9) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.html2md.converter.internal.HtmlConverterCoreNodeRenderer.processTable(org.jsoup.nodes.Element, com.vladsch.flexmark.html2md.converter.HtmlNodeConverterContext, com.vladsch.flexmark.html2md.converter.HtmlMarkdownWriter):void");
    }

    private void handleTableSection(HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter, Element element) {
        htmlNodeConverterContext.pushState(element);
        while (true) {
            Node next = htmlNodeConverterContext.next();
            if (next != null) {
                if (next.nodeName().equalsIgnoreCase(FlexmarkHtmlConverter.TR_NODE)) {
                    Element element2 = (Element) next;
                    Elements children = element2.children();
                    boolean header = this.myTable.getHeader();
                    if (!children.isEmpty() && children.get(0).tagName().equalsIgnoreCase(FlexmarkHtmlConverter.TH_NODE)) {
                        this.myTable.setHeader(true);
                    }
                    if (this.myTable.getHeader() && this.myTable.body.rows.size() > 0) {
                        if (this.myHtmlConverterOptions.ignoreTableHeadingAfterRows) {
                            this.myTableSuppressColumns = true;
                        } else {
                            this.myTable.setHeader(false);
                        }
                    }
                    handleTableRow(htmlNodeConverterContext, htmlMarkdownWriter, element2);
                    this.myTableSuppressColumns = false;
                    this.myTable.setHeader(header);
                }
            } else {
                htmlNodeConverterContext.popState(htmlMarkdownWriter);
                return;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0023. Please report as an issue. */
    private void handleTableRow(HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter, Element element) {
        htmlNodeConverterContext.pushState(element);
        while (true) {
            Node next = htmlNodeConverterContext.next();
            if (next != null) {
                String lowerCase = next.nodeName().toLowerCase();
                boolean z = -1;
                switch (lowerCase.hashCode()) {
                    case 3696:
                        if (lowerCase.equals(FlexmarkHtmlConverter.TD_NODE)) {
                            z = true;
                            break;
                        }
                        break;
                    case 3700:
                        if (lowerCase.equals(FlexmarkHtmlConverter.TH_NODE)) {
                            z = false;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                    case true:
                        handleTableCell((Element) next, htmlNodeConverterContext, htmlMarkdownWriter);
                        break;
                }
            } else {
                this.myTable.nextRow();
                htmlNodeConverterContext.popState(htmlMarkdownWriter);
                return;
            }
        }
    }

    private void handleTableCaption(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        this.myTable.setCaption(htmlNodeConverterContext.processTextNodes(element).trim());
    }

    private void handleTableCell(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        String replaceAll = htmlNodeConverterContext.processTextNodes(element).trim().replaceAll("\\s*\n\\s*", SequenceUtils.SPACE);
        int i = 1;
        int i2 = 1;
        CellAlignment cellAlignment = null;
        if (element.hasAttr("colSpan")) {
            try {
                i = Integer.parseInt(element.attr("colSpan"));
            } catch (NumberFormatException unused) {
            }
        }
        if (element.hasAttr("rowSpan")) {
            try {
                i2 = Integer.parseInt(element.attr("rowSpan"));
            } catch (NumberFormatException unused2) {
            }
        }
        if (element.hasAttr("align")) {
            cellAlignment = CellAlignment.getAlignment(element.attr("align"));
        } else {
            Set<String> classNames = element.classNames();
            if (!classNames.isEmpty()) {
                Iterator<String> it = classNames.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CellAlignment cellAlignment2 = this.myHtmlConverterOptions.tableCellAlignmentMap.get(it.next());
                    if (cellAlignment2 != null) {
                        cellAlignment = cellAlignment2;
                        break;
                    }
                }
                if (cellAlignment == null) {
                    for (Object obj : this.myHtmlConverterOptions.tableCellAlignmentMap.keySet()) {
                        if (obj instanceof Pattern) {
                            Pattern pattern = (Pattern) obj;
                            Iterator<String> it2 = classNames.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    if (pattern.matcher(it2.next()).find()) {
                                        cellAlignment = this.myHtmlConverterOptions.tableCellAlignmentMap.get(obj);
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                            if (cellAlignment != null) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (!this.myTableSuppressColumns) {
            this.myTable.addCell(new TableCell(null, BasedSequence.NULL, replaceAll.replace(SequenceUtils.EOL, SequenceUtils.SPACE), BasedSequence.NULL, i2, i, cellAlignment));
        }
    }

    private boolean matchingText(Pattern pattern, String str, String[] strArr) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            if (matcher.groupCount() > 0) {
                strArr[0] = matcher.group(1);
                return true;
            }
            strArr[0] = matcher.group();
            return true;
        }
        return false;
    }

    private String convertNumeric(String str) {
        String trim = str.trim();
        if (RomanNumeral.LIMITED_ROMAN_NUMERAL.matcher(trim).matches() || RomanNumeral.LIMITED_LOWERCASE_ROMAN_NUMERAL.matcher(trim).matches()) {
            return String.valueOf(new RomanNumeral(trim).toInt());
        }
        if (ALPHA_NUMERAL_PAT.matcher(trim).matches()) {
            int i = 0;
            String upperCase = trim.toUpperCase();
            int length = upperCase.length();
            for (int i2 = 0; i2 < length; i2++) {
                i = (i * 26) + (upperCase.charAt(i2) - 'A') + 1;
            }
            return String.valueOf(i);
        }
        return "1";
    }

    private void processUnwrapped(Node node, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.processUnwrapped(node);
    }

    private void processWrapped(Node node, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        htmlNodeConverterContext.processWrapped(node, Boolean.FALSE, false);
    }

    private void processSpan(Element element, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        if (element.hasAttr(Attribute.STYLE_ATTR) && element.attr(Attribute.STYLE_ATTR).equals("mso-list:Ignore")) {
            String[] strArr = {"1"};
            String processTextNodes = htmlNodeConverterContext.processTextNodes(element);
            if (matchingText(NUMERIC_DOT_LIST_PAT, processTextNodes, strArr)) {
                htmlMarkdownWriter.append((CharSequence) processTextNodes).append(' ');
            } else if (matchingText(NUMERIC_PAREN_LIST_PAT, processTextNodes, strArr)) {
                if (this.myHtmlConverterOptions.dotOnlyNumericLists) {
                    htmlMarkdownWriter.append((CharSequence) strArr[0]).append((CharSequence) ". ");
                } else {
                    htmlMarkdownWriter.append((CharSequence) strArr[0]).append((CharSequence) ") ");
                }
            } else if (matchingText(NON_NUMERIC_DOT_LIST_PAT, processTextNodes, strArr)) {
                htmlMarkdownWriter.append((CharSequence) convertNumeric(strArr[0])).append((CharSequence) ". ");
                if (this.myHtmlConverterOptions.commentOriginalNonNumericListItem) {
                    htmlMarkdownWriter.append(" <!-- ").append((CharSequence) strArr[0]).append((CharSequence) " -->");
                }
            } else if (matchingText(NON_NUMERIC_PAREN_LIST_PAT, processTextNodes, strArr)) {
                if (this.myHtmlConverterOptions.dotOnlyNumericLists) {
                    htmlMarkdownWriter.append((CharSequence) convertNumeric(strArr[0])).append((CharSequence) ". ");
                    if (this.myHtmlConverterOptions.commentOriginalNonNumericListItem) {
                        htmlMarkdownWriter.append(" <!-- ").append((CharSequence) strArr[0]).append((CharSequence) " -->");
                    }
                } else {
                    htmlMarkdownWriter.append((CharSequence) convertNumeric(strArr[0])).append((CharSequence) ") ");
                    if (this.myHtmlConverterOptions.commentOriginalNonNumericListItem) {
                        htmlMarkdownWriter.append(" <!-- ").append((CharSequence) strArr[0]).append((CharSequence) " -->");
                    }
                }
            } else if (BULLET_LIST_PAT.matcher(processTextNodes).matches()) {
                htmlMarkdownWriter.append("* ");
            } else {
                htmlMarkdownWriter.append("* ").append((CharSequence) processTextNodes);
            }
            htmlNodeConverterContext.transferIdToParent();
            return;
        }
        htmlNodeConverterContext.getClass();
        htmlNodeConverterContext.renderChildren(element, true, htmlNodeConverterContext::transferIdToParent);
    }

    private void processComment(Comment comment, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        if (this.myHtmlConverterOptions.renderComments) {
            htmlMarkdownWriter.append("<!--").append((CharSequence) comment.getData()).append((CharSequence) "-->");
        }
    }

    private void processText(TextNode textNode, HtmlNodeConverterContext htmlNodeConverterContext, HtmlMarkdownWriter htmlMarkdownWriter) {
        if (htmlMarkdownWriter.isPreFormatted()) {
            htmlMarkdownWriter.append((CharSequence) htmlNodeConverterContext.prepareText(textNode.getWholeText(), true));
            return;
        }
        String prepareText = htmlNodeConverterContext.prepareText(textNode.text());
        if (htmlMarkdownWriter.offsetWithPending() != 0 || !prepareText.trim().isEmpty()) {
            htmlMarkdownWriter.append((CharSequence) prepareText);
        }
    }
}
