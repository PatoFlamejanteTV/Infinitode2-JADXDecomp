package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.ast.AutoLink;
import com.vladsch.flexmark.ast.BlockQuote;
import com.vladsch.flexmark.ast.BulletList;
import com.vladsch.flexmark.ast.BulletListItem;
import com.vladsch.flexmark.ast.Code;
import com.vladsch.flexmark.ast.CodeBlock;
import com.vladsch.flexmark.ast.Emphasis;
import com.vladsch.flexmark.ast.FencedCodeBlock;
import com.vladsch.flexmark.ast.HardLineBreak;
import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.HtmlBlock;
import com.vladsch.flexmark.ast.HtmlBlockBase;
import com.vladsch.flexmark.ast.HtmlCommentBlock;
import com.vladsch.flexmark.ast.HtmlEntity;
import com.vladsch.flexmark.ast.HtmlInline;
import com.vladsch.flexmark.ast.HtmlInlineBase;
import com.vladsch.flexmark.ast.HtmlInlineComment;
import com.vladsch.flexmark.ast.HtmlInnerBlock;
import com.vladsch.flexmark.ast.HtmlInnerBlockComment;
import com.vladsch.flexmark.ast.Image;
import com.vladsch.flexmark.ast.ImageRef;
import com.vladsch.flexmark.ast.IndentedCodeBlock;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ast.LinkRef;
import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.MailLink;
import com.vladsch.flexmark.ast.OrderedList;
import com.vladsch.flexmark.ast.OrderedListItem;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.ParagraphItemContainer;
import com.vladsch.flexmark.ast.Reference;
import com.vladsch.flexmark.ast.SoftLineBreak;
import com.vladsch.flexmark.ast.StrongEmphasis;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.TextBase;
import com.vladsch.flexmark.ast.ThematicBreak;
import com.vladsch.flexmark.ast.util.LineCollectingVisitor;
import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.HtmlRendererOptions;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NonRenderingInline;
import com.vladsch.flexmark.util.ast.TextCollectingVisitor;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.Range;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/CoreNodeRenderer.class */
public class CoreNodeRenderer implements NodeRenderer {
    public static final AttributablePart LOOSE_LIST_ITEM;
    public static final AttributablePart TIGHT_LIST_ITEM;
    public static final AttributablePart PARAGRAPH_LINE;
    public static final AttributablePart CODE_CONTENT;
    private final ListOptions listOptions;
    private final boolean obfuscateEmail;
    private final boolean obfuscateEmailRandom;
    private final ReferenceRepository referenceRepository;
    private final boolean recheckUndefinedReferences;
    private final boolean codeContentBlock;
    private final boolean codeSoftLineBreaks;
    private List<Range> myLines = null;
    private List<Integer> myEOLs = null;
    private int myNextLine = 0;
    private int nextLineStartOffset = 0;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !CoreNodeRenderer.class.desiredAssertionStatus();
        LOOSE_LIST_ITEM = new AttributablePart("LOOSE_LIST_ITEM");
        TIGHT_LIST_ITEM = new AttributablePart("TIGHT_LIST_ITEM");
        PARAGRAPH_LINE = new AttributablePart("PARAGRAPH_LINE");
        CODE_CONTENT = new AttributablePart("FENCED_CODE_CONTENT");
    }

    public CoreNodeRenderer(DataHolder dataHolder) {
        this.referenceRepository = Parser.REFERENCES.get(dataHolder);
        this.recheckUndefinedReferences = HtmlRenderer.RECHECK_UNDEFINED_REFERENCES.get(dataHolder).booleanValue();
        this.listOptions = ListOptions.get(dataHolder);
        this.obfuscateEmail = HtmlRenderer.OBFUSCATE_EMAIL.get(dataHolder).booleanValue();
        this.obfuscateEmailRandom = HtmlRenderer.OBFUSCATE_EMAIL_RANDOM.get(dataHolder).booleanValue();
        this.codeContentBlock = Parser.FENCED_CODE_CONTENT_BLOCK.get(dataHolder).booleanValue();
        this.codeSoftLineBreaks = Parser.CODE_SOFT_LINE_BREAKS.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet(Arrays.asList(new NodeRenderingHandler(AutoLink.class, this::render), new NodeRenderingHandler(BlockQuote.class, this::render), new NodeRenderingHandler(BulletList.class, this::render), new NodeRenderingHandler(Code.class, this::render), new NodeRenderingHandler(CodeBlock.class, this::render), new NodeRenderingHandler(Document.class, this::render), new NodeRenderingHandler(Emphasis.class, this::render), new NodeRenderingHandler(FencedCodeBlock.class, this::render), new NodeRenderingHandler(HardLineBreak.class, this::render), new NodeRenderingHandler(Heading.class, this::render), new NodeRenderingHandler(HtmlBlock.class, this::render), new NodeRenderingHandler(HtmlCommentBlock.class, this::render), new NodeRenderingHandler(HtmlInnerBlock.class, this::render), new NodeRenderingHandler(HtmlInnerBlockComment.class, this::render), new NodeRenderingHandler(HtmlEntity.class, this::render), new NodeRenderingHandler(HtmlInline.class, this::render), new NodeRenderingHandler(HtmlInlineComment.class, this::render), new NodeRenderingHandler(Image.class, this::render), new NodeRenderingHandler(ImageRef.class, this::render), new NodeRenderingHandler(IndentedCodeBlock.class, this::render), new NodeRenderingHandler(Link.class, this::render), new NodeRenderingHandler(LinkRef.class, this::render), new NodeRenderingHandler(BulletListItem.class, this::render), new NodeRenderingHandler(OrderedListItem.class, this::render), new NodeRenderingHandler(MailLink.class, this::render), new NodeRenderingHandler(OrderedList.class, this::render), new NodeRenderingHandler(Paragraph.class, this::render), new NodeRenderingHandler(Reference.class, this::render), new NodeRenderingHandler(SoftLineBreak.class, this::render), new NodeRenderingHandler(StrongEmphasis.class, this::render), new NodeRenderingHandler(Text.class, this::render), new NodeRenderingHandler(TextBase.class, this::render), new NodeRenderingHandler(ThematicBreak.class, this::render)));
    }

    void render(Document document, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(document);
    }

    void render(Heading heading, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String nodeId;
        if (nodeRendererContext.getHtmlOptions().renderHeaderId && (nodeId = nodeRendererContext.getNodeId(heading)) != null) {
            htmlWriter.attr(Attribute.ID_ATTR, (CharSequence) nodeId);
        }
        if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines) {
            htmlWriter.srcPos(heading.getChars()).withAttr().tagLine((CharSequence) ("h" + heading.getLevel()), () -> {
                htmlWriter.srcPos(heading.getText()).withAttr().tag(FlexmarkHtmlConverter.SPAN_NODE);
                nodeRendererContext.renderChildren(heading);
                htmlWriter.tag("/span");
            });
        } else {
            htmlWriter.srcPos(heading.getText()).withAttr().tagLine((CharSequence) ("h" + heading.getLevel()), () -> {
                nodeRendererContext.renderChildren(heading);
            });
        }
    }

    void render(BlockQuote blockQuote, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.withAttr().tagLineIndent(FlexmarkHtmlConverter.BLOCKQUOTE_NODE, () -> {
            nodeRendererContext.renderChildren(blockQuote);
        });
    }

    void render(FencedCodeBlock fencedCodeBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.line();
        htmlWriter.srcPosWithTrailingEOL(fencedCodeBlock.getChars()).withAttr().tag(FlexmarkHtmlConverter.PRE_NODE).openPre();
        BasedSequence info = fencedCodeBlock.getInfo();
        HtmlRendererOptions htmlOptions = nodeRendererContext.getHtmlOptions();
        if (info.isNotNull() && !info.isBlank()) {
            String unescape = fencedCodeBlock.getInfoDelimitedByAny(htmlOptions.languageDelimiterSet).unescape();
            htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) htmlOptions.languageClassMap.getOrDefault(unescape, htmlOptions.languageClassPrefix + unescape));
        } else {
            String trim = htmlOptions.noLanguageClass.trim();
            if (!trim.isEmpty()) {
                htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) trim);
            }
        }
        htmlWriter.srcPosWithEOL(fencedCodeBlock.getContentChars()).withAttr(CODE_CONTENT).tag(FlexmarkHtmlConverter.CODE_NODE);
        if (this.codeContentBlock) {
            nodeRendererContext.renderChildren(fencedCodeBlock);
        } else {
            htmlWriter.text(fencedCodeBlock.getContentChars().normalizeEOL());
        }
        htmlWriter.tag("/code");
        ((HtmlWriter) htmlWriter.tag("/pre")).closePre();
        htmlWriter.lineIf(htmlOptions.htmlBlockCloseTagEol);
    }

    void render(ThematicBreak thematicBreak, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.srcPos(thematicBreak.getChars()).withAttr().tagVoidLine(FlexmarkHtmlConverter.HR_NODE);
    }

    void render(IndentedCodeBlock indentedCodeBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.line();
        htmlWriter.srcPosWithEOL(indentedCodeBlock.getChars()).withAttr().tag(FlexmarkHtmlConverter.PRE_NODE).openPre();
        String trim = nodeRendererContext.getHtmlOptions().noLanguageClass.trim();
        if (!trim.isEmpty()) {
            htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) trim);
        }
        htmlWriter.srcPosWithEOL(indentedCodeBlock.getContentChars()).withAttr(CODE_CONTENT).tag(FlexmarkHtmlConverter.CODE_NODE);
        if (this.codeContentBlock) {
            nodeRendererContext.renderChildren(indentedCodeBlock);
        } else {
            htmlWriter.text(indentedCodeBlock.getContentChars().trimTailBlankLines().normalizeEndWithEOL());
        }
        htmlWriter.tag("/code");
        ((HtmlWriter) htmlWriter.tag("/pre")).closePre();
        htmlWriter.lineIf(nodeRendererContext.getHtmlOptions().htmlBlockCloseTagEol);
    }

    void render(CodeBlock codeBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (codeBlock.getParent() instanceof IndentedCodeBlock) {
            htmlWriter.text((CharSequence) codeBlock.getContentChars().trimTailBlankLines().normalizeEndWithEOL());
        } else {
            htmlWriter.text((CharSequence) codeBlock.getContentChars().normalizeEOL());
        }
    }

    void render(BulletList bulletList, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.withAttr().tagIndent(FlexmarkHtmlConverter.UL_NODE, () -> {
            nodeRendererContext.renderChildren(bulletList);
        });
    }

    void render(OrderedList orderedList, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        int startNumber = orderedList.getStartNumber();
        if (this.listOptions.isOrderedListManualStart() && startNumber != 1) {
            htmlWriter.attr("start", (CharSequence) String.valueOf(startNumber));
        }
        htmlWriter.withAttr().tagIndent(FlexmarkHtmlConverter.OL_NODE, () -> {
            nodeRendererContext.renderChildren(orderedList);
        });
    }

    void render(BulletListItem bulletListItem, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderListItem(bulletListItem, nodeRendererContext, htmlWriter);
    }

    void render(OrderedListItem orderedListItem, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderListItem(orderedListItem, nodeRendererContext, htmlWriter);
    }

    private void renderListItem(ListItem listItem, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.listOptions.isTightListItem(listItem)) {
            htmlWriter.srcPosWithEOL(listItem.getChars()).withAttr(TIGHT_LIST_ITEM).withCondIndent().tagLine((CharSequence) FlexmarkHtmlConverter.LI_NODE, () -> {
                htmlWriter.text((CharSequence) listItem.getMarkerSuffix().unescape());
                nodeRendererContext.renderChildren(listItem);
            });
        } else {
            htmlWriter.srcPosWithEOL(listItem.getChars()).withAttr(LOOSE_LIST_ITEM).withCondIndent().tagLine((CharSequence) FlexmarkHtmlConverter.LI_NODE, () -> {
                htmlWriter.text((CharSequence) listItem.getMarkerSuffix().unescape());
                nodeRendererContext.renderChildren(listItem);
            });
        }
    }

    public void renderTextBlockParagraphLines(Paragraph paragraph, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter, boolean z) {
        if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines && paragraph.hasChildren()) {
            LineCollectingVisitor lineCollectingVisitor = new LineCollectingVisitor();
            this.myLines = lineCollectingVisitor.collectAndGetRanges(paragraph);
            this.myEOLs = lineCollectingVisitor.getEOLs();
            this.myNextLine = 0;
            if (paragraph.getFirstChild() != null) {
                outputSourceLineSpan(paragraph, paragraph.getFirstChild(), paragraph, htmlWriter);
            }
            nodeRendererContext.renderChildren(paragraph);
            htmlWriter.tag("/span");
            return;
        }
        if (z) {
            htmlWriter.withAttr().tag(FlexmarkHtmlConverter.SPAN_NODE, false, false, () -> {
                nodeRendererContext.renderChildren(paragraph);
            });
        } else {
            nodeRendererContext.renderChildren(paragraph);
        }
    }

    private void outputSourceLineSpan(Node node, Node node2, Node node3, HtmlWriter htmlWriter) {
        int startOffset = node2.getStartOffset();
        Range range = this.myLines.get(this.myNextLine);
        int intValue = this.myEOLs.get(this.myNextLine).intValue();
        int endOffset = node3.getEndOffset();
        if (range.getEnd() <= endOffset) {
            int end = range.getEnd() - intValue;
            endOffset = end - node.baseSubSequence(startOffset, end).countTrailing(CharPredicate.SPACE_TAB);
            this.myNextLine++;
            this.nextLineStartOffset = range.getEnd();
            this.nextLineStartOffset += node.baseSubSequence(this.nextLineStartOffset, node.getEndOffset()).countLeading(CharPredicate.SPACE_TAB);
        }
        if (range.getStart() > startOffset) {
            startOffset = range.getStart();
        }
        htmlWriter.srcPos(startOffset, endOffset).withAttr(PARAGRAPH_LINE).tag(FlexmarkHtmlConverter.SPAN_NODE);
    }

    private void outputNextLineBreakSpan(Node node, HtmlWriter htmlWriter, boolean z) {
        Range range = this.myLines.get(this.myNextLine);
        int intValue = this.myEOLs.get(this.myNextLine).intValue();
        this.myNextLine++;
        int countTrailing = node.baseSubSequence(this.nextLineStartOffset, range.getEnd() - intValue).countTrailing(CharPredicate.SPACE_TAB);
        if (!z && countTrailing > 0) {
            countTrailing--;
        }
        htmlWriter.srcPos(this.nextLineStartOffset, range.getEnd() - (intValue + countTrailing)).withAttr(PARAGRAPH_LINE).tag(FlexmarkHtmlConverter.SPAN_NODE);
        this.nextLineStartOffset = range.getEnd();
        this.nextLineStartOffset += node.baseSubSequence(this.nextLineStartOffset, node.getChars().getBaseSequence().length()).countLeading(CharPredicate.SPACE_TAB);
    }

    private void renderLooseParagraph(Paragraph paragraph, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.getHtmlOptions().noPTagsUseBr) {
            renderTextBlockParagraphLines(paragraph, nodeRendererContext, htmlWriter, false);
            htmlWriter.tagVoid(FlexmarkHtmlConverter.BR_NODE).tagVoid((CharSequence) FlexmarkHtmlConverter.BR_NODE).line();
        } else {
            htmlWriter.srcPosWithEOL(paragraph.getChars()).withAttr().tagLine(FlexmarkHtmlConverter.P_NODE, () -> {
                renderTextBlockParagraphLines(paragraph, nodeRendererContext, htmlWriter, false);
            });
        }
    }

    void render(Paragraph paragraph, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (paragraph.getFirstChildAnyNot(NonRenderingInline.class) != null) {
            if (!(paragraph.getParent() instanceof ParagraphItemContainer) || !((ParagraphItemContainer) paragraph.getParent()).isParagraphWrappingDisabled(paragraph, this.listOptions, nodeRendererContext.getOptions())) {
                renderLooseParagraph(paragraph, nodeRendererContext, htmlWriter);
            } else {
                renderTextBlockParagraphLines(paragraph, nodeRendererContext, htmlWriter, false);
            }
        }
    }

    private boolean renderLineBreak(String str, String str2, Node node, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (this.myLines != null && this.myNextLine < this.myLines.size()) {
            List<String> openTagsAfterLast = htmlWriter.getOpenTagsAfterLast(FlexmarkHtmlConverter.SPAN_NODE);
            int size = openTagsAfterLast.size();
            boolean z = size == 0 || str2 == null || !str2.equalsIgnoreCase(openTagsAfterLast.get(size - 1));
            boolean z2 = z;
            if (!z && !htmlWriter.isPendingSpace()) {
                htmlWriter.raw(SequenceUtils.SPACE);
            }
            int i = size;
            while (true) {
                int i2 = i;
                i--;
                if (i2 <= 0) {
                    break;
                }
                htmlWriter.closeTag((CharSequence) openTagsAfterLast.get(i));
            }
            htmlWriter.tag("/span");
            if (z2) {
                htmlWriter.raw((CharSequence) str);
            }
            outputNextLineBreakSpan(node, htmlWriter, z2);
            for (String str3 : openTagsAfterLast) {
                if (!z2 && nodeRendererContext.getHtmlOptions().inlineCodeSpliceClass != null && !nodeRendererContext.getHtmlOptions().inlineCodeSpliceClass.isEmpty()) {
                    htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) nodeRendererContext.getHtmlOptions().inlineCodeSpliceClass).withAttr().tag((CharSequence) str3);
                } else {
                    htmlWriter.tag((CharSequence) str3);
                }
            }
            return true;
        }
        return false;
    }

    void render(SoftLineBreak softLineBreak, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String str = nodeRendererContext.getHtmlOptions().softBreak;
        if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines) {
            if (renderLineBreak(str, (str.equals(SequenceUtils.EOL) || str.equals("\r\n") || str.equals("\r")) ? FlexmarkHtmlConverter.CODE_NODE : null, softLineBreak, nodeRendererContext, htmlWriter)) {
                return;
            }
        }
        htmlWriter.raw((CharSequence) str);
    }

    void render(HardLineBreak hardLineBreak, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines && renderLineBreak(nodeRendererContext.getHtmlOptions().hardBreak, null, hardLineBreak, nodeRendererContext, htmlWriter)) {
            return;
        }
        htmlWriter.raw((CharSequence) nodeRendererContext.getHtmlOptions().hardBreak);
    }

    void render(Emphasis emphasis, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        HtmlRendererOptions htmlOptions = nodeRendererContext.getHtmlOptions();
        if (htmlOptions.emphasisStyleHtmlOpen == null || htmlOptions.emphasisStyleHtmlClose == null) {
            if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines) {
                htmlWriter.withAttr().tag(FlexmarkHtmlConverter.EM_NODE);
            } else {
                htmlWriter.srcPos(emphasis.getText()).withAttr().tag(FlexmarkHtmlConverter.EM_NODE);
            }
            nodeRendererContext.renderChildren(emphasis);
            htmlWriter.tag("/em");
            return;
        }
        htmlWriter.raw((CharSequence) htmlOptions.emphasisStyleHtmlOpen);
        nodeRendererContext.renderChildren(emphasis);
        htmlWriter.raw((CharSequence) htmlOptions.emphasisStyleHtmlClose);
    }

    void render(StrongEmphasis strongEmphasis, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        HtmlRendererOptions htmlOptions = nodeRendererContext.getHtmlOptions();
        if (htmlOptions.strongEmphasisStyleHtmlOpen == null || htmlOptions.strongEmphasisStyleHtmlClose == null) {
            if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines) {
                htmlWriter.withAttr().tag(FlexmarkHtmlConverter.STRONG_NODE);
            } else {
                htmlWriter.srcPos(strongEmphasis.getText()).withAttr().tag(FlexmarkHtmlConverter.STRONG_NODE);
            }
            nodeRendererContext.renderChildren(strongEmphasis);
            htmlWriter.tag("/strong");
            return;
        }
        htmlWriter.raw((CharSequence) htmlOptions.strongEmphasisStyleHtmlOpen);
        nodeRendererContext.renderChildren(strongEmphasis);
        htmlWriter.raw((CharSequence) htmlOptions.strongEmphasisStyleHtmlClose);
    }

    void render(Text text, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.text((CharSequence) Escaping.normalizeEOL(text.getChars().unescape()));
    }

    void render(TextBase textBase, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(textBase);
    }

    void render(Code code, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        HtmlRendererOptions htmlOptions = nodeRendererContext.getHtmlOptions();
        if (htmlOptions.codeStyleHtmlOpen == null || htmlOptions.codeStyleHtmlClose == null) {
            if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines) {
                htmlWriter.withAttr().tag(FlexmarkHtmlConverter.CODE_NODE);
            } else {
                htmlWriter.srcPos(code.getText()).withAttr().tag(FlexmarkHtmlConverter.CODE_NODE);
            }
            if (this.codeSoftLineBreaks && !htmlOptions.isSoftBreakAllSpaces) {
                ReversiblePeekingIterator<Node> it = code.getChildren().iterator();
                while (it.hasNext()) {
                    Node next = it.next();
                    if (next instanceof Text) {
                        htmlWriter.text((CharSequence) Escaping.collapseWhitespace((CharSequence) next.getChars(), true));
                    } else {
                        nodeRendererContext.render(next);
                    }
                }
            } else {
                htmlWriter.text((CharSequence) Escaping.collapseWhitespace((CharSequence) code.getText(), true));
            }
            htmlWriter.tag("/code");
            return;
        }
        htmlWriter.raw((CharSequence) htmlOptions.codeStyleHtmlOpen);
        if (this.codeSoftLineBreaks && !htmlOptions.isSoftBreakAllSpaces) {
            ReversiblePeekingIterator<Node> it2 = code.getChildren().iterator();
            while (it2.hasNext()) {
                Node next2 = it2.next();
                if (next2 instanceof Text) {
                    htmlWriter.text((CharSequence) Escaping.collapseWhitespace((CharSequence) next2.getChars(), true));
                } else {
                    nodeRendererContext.render(next2);
                }
            }
        } else {
            htmlWriter.text((CharSequence) Escaping.collapseWhitespace((CharSequence) code.getText(), true));
        }
        htmlWriter.raw((CharSequence) htmlOptions.codeStyleHtmlClose);
    }

    void render(HtmlBlock htmlBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.line();
        HtmlRendererOptions htmlOptions = nodeRendererContext.getHtmlOptions();
        if (htmlOptions.sourceWrapHtmlBlocks) {
            htmlWriter.srcPos(htmlBlock.getChars()).withAttr(AttributablePart.NODE_POSITION).tag(FlexmarkHtmlConverter.DIV_NODE).indent().line();
        }
        if (htmlBlock.hasChildren()) {
            nodeRendererContext.renderChildren(htmlBlock);
        } else {
            renderHtmlBlock(htmlBlock, nodeRendererContext, htmlWriter, htmlOptions.suppressHtmlBlocks, htmlOptions.escapeHtmlBlocks, false);
        }
        if (htmlOptions.sourceWrapHtmlBlocks) {
            htmlWriter.unIndent().tag((CharSequence) "/div");
        }
        htmlWriter.lineIf(htmlOptions.htmlBlockCloseTagEol);
    }

    void render(HtmlCommentBlock htmlCommentBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderHtmlBlock(htmlCommentBlock, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressHtmlCommentBlocks, nodeRendererContext.getHtmlOptions().escapeHtmlCommentBlocks, false);
    }

    void render(HtmlInnerBlock htmlInnerBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderHtmlBlock(htmlInnerBlock, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressHtmlBlocks, nodeRendererContext.getHtmlOptions().escapeHtmlBlocks, false);
    }

    void render(HtmlInnerBlockComment htmlInnerBlockComment, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderHtmlBlock(htmlInnerBlockComment, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressHtmlCommentBlocks, nodeRendererContext.getHtmlOptions().escapeHtmlCommentBlocks, false);
    }

    public static void renderHtmlBlock(HtmlBlockBase htmlBlockBase, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter, boolean z, boolean z2, boolean z3) {
        if (z) {
            return;
        }
        if (htmlBlockBase instanceof HtmlBlock) {
            htmlWriter.line();
        }
        String normalizeEOL = htmlBlockBase instanceof HtmlBlock ? htmlBlockBase.getContentChars().normalizeEOL() : htmlBlockBase.getChars().normalizeEOL();
        if (z3) {
            normalizeEOL = normalizeEOL.trim();
        }
        if (z2) {
            if (htmlBlockBase instanceof HtmlBlock) {
                if (normalizeEOL.length() > 0) {
                    String str = normalizeEOL;
                    if (str.charAt(str.length() - 1) == '\n') {
                        normalizeEOL = normalizeEOL.substring(0, normalizeEOL.length() - 1);
                    }
                }
                htmlWriter.raw("<p>").text((CharSequence) normalizeEOL).raw((CharSequence) "</p>");
            } else {
                htmlWriter.text((CharSequence) normalizeEOL);
            }
        } else {
            htmlWriter.rawPre((CharSequence) normalizeEOL);
        }
        if (htmlBlockBase instanceof HtmlBlock) {
            htmlWriter.lineIf(nodeRendererContext.getHtmlOptions().htmlBlockCloseTagEol);
        }
    }

    void render(HtmlInline htmlInline, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderInlineHtml(htmlInline, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressInlineHtml, nodeRendererContext.getHtmlOptions().escapeInlineHtml);
    }

    void render(HtmlInlineComment htmlInlineComment, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderInlineHtml(htmlInlineComment, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressInlineHtmlComments, nodeRendererContext.getHtmlOptions().escapeInlineHtmlComments);
    }

    public static void renderInlineHtml(HtmlInlineBase htmlInlineBase, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter, boolean z, boolean z2) {
        if (z) {
            return;
        }
        if (z2) {
            htmlWriter.text((CharSequence) htmlInlineBase.getChars().normalizeEOL());
        } else {
            htmlWriter.rawPre((CharSequence) htmlInlineBase.getChars().normalizeEOL());
        }
    }

    void render(Reference reference, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    void render(HtmlEntity htmlEntity, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.getHtmlOptions().unescapeHtmlEntities) {
            htmlWriter.text((CharSequence) htmlEntity.getChars().unescape());
        } else {
            htmlWriter.raw((CharSequence) htmlEntity.getChars().unescapeNoEntities());
        }
    }

    public static boolean isSuppressedLinkPrefix(CharSequence charSequence, NodeRendererContext nodeRendererContext) {
        Pattern pattern = nodeRendererContext.getHtmlOptions().suppressedLinks;
        if (pattern != null) {
            return pattern.matcher(charSequence).matches();
        }
        return false;
    }

    void render(AutoLink autoLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String obj = autoLink.getText().toString();
        if (nodeRendererContext.isDoNotRenderLinks() || isSuppressedLinkPrefix(autoLink.getUrl(), nodeRendererContext)) {
            htmlWriter.text((CharSequence) obj);
        } else {
            ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.LINK, obj, null);
            htmlWriter.srcPos(autoLink.getText()).attr("href", (CharSequence) (resolveLink.getUrl().startsWith("www.") ? nodeRendererContext.getHtmlOptions().autolinkWwwPrefix + resolveLink.getUrl() : resolveLink.getUrl())).withAttr(resolveLink).tag((CharSequence) FlexmarkHtmlConverter.A_NODE, false, false, () -> {
                htmlWriter.text((CharSequence) obj);
            });
        }
    }

    void render(MailLink mailLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String unescape = mailLink.getText().unescape();
        if (nodeRendererContext.isDoNotRenderLinks() || isSuppressedLinkPrefix(mailLink.getUrl(), nodeRendererContext)) {
            htmlWriter.text((CharSequence) unescape);
            return;
        }
        ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.LINK, unescape, null);
        if (this.obfuscateEmail) {
            htmlWriter.srcPos(mailLink.getText()).attr("href", (CharSequence) Escaping.obfuscate("mailto:" + resolveLink.getUrl(), this.obfuscateEmailRandom)).withAttr(resolveLink).tag((CharSequence) FlexmarkHtmlConverter.A_NODE).raw((CharSequence) Escaping.obfuscate(unescape, true)).tag((CharSequence) "/a");
        } else {
            htmlWriter.srcPos(mailLink.getText()).attr("href", (CharSequence) ("mailto:" + resolveLink.getUrl())).withAttr(resolveLink).tag((CharSequence) FlexmarkHtmlConverter.A_NODE).text((CharSequence) unescape).tag((CharSequence) "/a");
        }
    }

    void render(Image image, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (!nodeRendererContext.isDoNotRenderLinks() && !isSuppressedLinkPrefix(image.getUrl(), nodeRendererContext)) {
            String collectAndGetText = new TextCollectingVisitor().collectAndGetText(image);
            ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.IMAGE, image.getUrl().unescape(), null, null);
            ResolvedLink resolvedLink = resolveLink;
            String url = resolveLink.getUrl();
            if (!image.getUrlContent().isEmpty()) {
                url = url + Escaping.percentEncodeUrl(image.getUrlContent()).replace("+", "%2B").replace("%3D", "=").replace("%26", "&amp;");
            }
            htmlWriter.attr("src", (CharSequence) url);
            htmlWriter.attr("alt", (CharSequence) collectAndGetText);
            if (image.getTitle().isNotNull()) {
                resolvedLink = resolvedLink.withTitle(image.getTitle().unescape());
            }
            htmlWriter.attr(resolvedLink.getNonNullAttributes());
            htmlWriter.srcPos(image.getChars()).withAttr(resolvedLink).tagVoid(FlexmarkHtmlConverter.IMG_NODE);
        }
    }

    void render(Link link, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks() || isSuppressedLinkPrefix(link.getUrl(), nodeRendererContext)) {
            nodeRendererContext.renderChildren(link);
            return;
        }
        ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.LINK, link.getUrl().unescape(), null, null);
        htmlWriter.attr("href", (CharSequence) resolveLink.getUrl());
        if (link.getTitle().isNotNull()) {
            resolveLink = resolveLink.withTitle(link.getTitle().unescape());
        }
        htmlWriter.attr(resolveLink.getNonNullAttributes());
        htmlWriter.srcPos(link.getChars()).withAttr(resolveLink).tag(FlexmarkHtmlConverter.A_NODE);
        renderChildrenSourceLineWrapped(link, link.getText(), nodeRendererContext, htmlWriter);
        htmlWriter.tag("/a");
    }

    private void renderChildrenSourceLineWrapped(Node node, BasedSequence basedSequence, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines && basedSequence.indexOfAny(CharPredicate.ANY_EOL) >= 0) {
            if (this.myNextLine > 0) {
                this.myNextLine--;
            }
            outputSourceLineSpan(node, node, node, htmlWriter);
            nodeRendererContext.renderChildren(node);
            htmlWriter.tag("/span");
            return;
        }
        nodeRendererContext.renderChildren(node);
    }

    void render(ImageRef imageRef, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        ResolvedLink resolvedLink;
        boolean z = false;
        if (!imageRef.isDefined() && this.recheckUndefinedReferences && imageRef.getReferenceNode(this.referenceRepository) != null) {
            imageRef.setDefined(true);
        }
        Reference reference = null;
        if (imageRef.isDefined()) {
            Reference referenceNode = imageRef.getReferenceNode(this.referenceRepository);
            reference = referenceNode;
            String unescape = referenceNode.getUrl().unescape();
            z = isSuppressedLinkPrefix(unescape, nodeRendererContext);
            resolvedLink = nodeRendererContext.resolveLink(LinkType.IMAGE, unescape, null, null);
            if (reference.getTitle().isNotNull()) {
                resolvedLink = resolvedLink.withTitle(reference.getTitle().unescape());
            }
        } else {
            ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.IMAGE_REF, this.referenceRepository.normalizeKey(imageRef.getReference()), null, null);
            resolvedLink = resolveLink;
            if (resolveLink.getStatus() == LinkStatus.UNKNOWN || resolvedLink.getUrl().isEmpty()) {
                resolvedLink = null;
            }
        }
        if (resolvedLink == null) {
            htmlWriter.text((CharSequence) imageRef.getChars().unescape());
            return;
        }
        if (!nodeRendererContext.isDoNotRenderLinks() && !z) {
            String collectAndGetText = new TextCollectingVisitor().collectAndGetText(imageRef);
            Attributes nonNullAttributes = resolvedLink.getNonNullAttributes();
            htmlWriter.attr("src", (CharSequence) resolvedLink.getUrl());
            htmlWriter.attr("alt", (CharSequence) collectAndGetText);
            if (reference != null) {
                nonNullAttributes = nodeRendererContext.extendRenderingNodeAttributes(reference, AttributablePart.NODE, nonNullAttributes);
            }
            htmlWriter.attr(nonNullAttributes);
            htmlWriter.srcPos(imageRef.getChars()).withAttr(resolvedLink).tagVoid(FlexmarkHtmlConverter.IMG_NODE);
        }
    }

    void render(LinkRef linkRef, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        ResolvedLink resolvedLink;
        boolean z = false;
        if (!linkRef.isDefined() && this.recheckUndefinedReferences && linkRef.getReferenceNode(this.referenceRepository) != null) {
            linkRef.setDefined(true);
        }
        Reference reference = null;
        if (linkRef.isDefined()) {
            Reference referenceNode = linkRef.getReferenceNode(this.referenceRepository);
            reference = referenceNode;
            String unescape = referenceNode.getUrl().unescape();
            z = isSuppressedLinkPrefix(unescape, nodeRendererContext);
            resolvedLink = nodeRendererContext.resolveLink(LinkType.LINK, unescape, null, null);
            if (reference.getTitle().isNotNull()) {
                resolvedLink = resolvedLink.withTitle(reference.getTitle().unescape());
            }
        } else {
            ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.LINK_REF, linkRef.getReference().unescape(), null, null);
            resolvedLink = resolveLink;
            if (resolveLink.getStatus() == LinkStatus.UNKNOWN || resolvedLink.getUrl().isEmpty()) {
                resolvedLink = null;
            }
        }
        if (resolvedLink == null) {
            if (!$assertionsDisabled && linkRef.isDefined()) {
                throw new AssertionError();
            }
            if (!linkRef.hasChildren()) {
                htmlWriter.text((CharSequence) linkRef.getChars().unescape());
                return;
            }
            htmlWriter.text((CharSequence) linkRef.getChars().prefixOf(linkRef.getChildChars()).unescape());
            renderChildrenSourceLineWrapped(linkRef, linkRef.getText(), nodeRendererContext, htmlWriter);
            htmlWriter.text((CharSequence) linkRef.getChars().suffixOf(linkRef.getChildChars()).unescape());
            return;
        }
        if (nodeRendererContext.isDoNotRenderLinks() || z) {
            nodeRendererContext.renderChildren(linkRef);
            return;
        }
        Attributes nonNullAttributes = resolvedLink.getNonNullAttributes();
        htmlWriter.attr("href", (CharSequence) resolvedLink.getUrl());
        if (reference != null) {
            nonNullAttributes = nodeRendererContext.extendRenderingNodeAttributes(reference, AttributablePart.NODE, nonNullAttributes);
        }
        htmlWriter.attr(nonNullAttributes);
        htmlWriter.srcPos(linkRef.getChars()).withAttr(resolvedLink).tag(FlexmarkHtmlConverter.A_NODE);
        renderChildrenSourceLineWrapped(linkRef, linkRef.getText(), nodeRendererContext, htmlWriter);
        htmlWriter.tag("/a");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/CoreNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new CoreNodeRenderer(dataHolder);
        }
    }
}
