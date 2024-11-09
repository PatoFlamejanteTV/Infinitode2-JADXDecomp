package com.vladsch.flexmark.youtrack.converter.internal;

import com.vladsch.flexmark.ast.AutoLink;
import com.vladsch.flexmark.ast.BlockQuote;
import com.vladsch.flexmark.ast.BulletList;
import com.vladsch.flexmark.ast.BulletListItem;
import com.vladsch.flexmark.ast.Code;
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
import com.vladsch.flexmark.ast.ListBlock;
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
import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/youtrack/converter/internal/YouTrackConverterNodeRenderer.class */
public class YouTrackConverterNodeRenderer implements NodeRenderer {
    private final ReferenceRepository referenceRepository;
    private final ListOptions listOptions;
    private int inBlockQuote = 0;
    private final boolean recheckUndefinedReferences;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !YouTrackConverterNodeRenderer.class.desiredAssertionStatus();
    }

    public YouTrackConverterNodeRenderer(DataHolder dataHolder) {
        this.referenceRepository = Parser.REFERENCES.get(dataHolder);
        this.recheckUndefinedReferences = HtmlRenderer.RECHECK_UNDEFINED_REFERENCES.get(dataHolder).booleanValue();
        this.listOptions = ListOptions.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        return new HashSet(Arrays.asList(new NodeRenderingHandler(AutoLink.class, this::render), new NodeRenderingHandler(BlockQuote.class, this::render), new NodeRenderingHandler(BulletList.class, this::render), new NodeRenderingHandler(BulletListItem.class, this::render), new NodeRenderingHandler(Code.class, this::render), new NodeRenderingHandler(Document.class, this::render), new NodeRenderingHandler(Emphasis.class, this::render), new NodeRenderingHandler(FencedCodeBlock.class, this::render), new NodeRenderingHandler(HardLineBreak.class, this::render), new NodeRenderingHandler(Heading.class, this::render), new NodeRenderingHandler(HtmlBlock.class, this::render), new NodeRenderingHandler(HtmlCommentBlock.class, this::render), new NodeRenderingHandler(HtmlEntity.class, this::render), new NodeRenderingHandler(HtmlInline.class, this::render), new NodeRenderingHandler(HtmlInlineComment.class, this::render), new NodeRenderingHandler(HtmlInnerBlock.class, this::render), new NodeRenderingHandler(HtmlInnerBlockComment.class, this::render), new NodeRenderingHandler(Image.class, this::render), new NodeRenderingHandler(ImageRef.class, this::render), new NodeRenderingHandler(IndentedCodeBlock.class, this::render), new NodeRenderingHandler(Link.class, this::render), new NodeRenderingHandler(LinkRef.class, this::render), new NodeRenderingHandler(MailLink.class, this::render), new NodeRenderingHandler(OrderedList.class, this::render), new NodeRenderingHandler(OrderedListItem.class, this::render), new NodeRenderingHandler(Paragraph.class, this::render), new NodeRenderingHandler(Reference.class, this::render), new NodeRenderingHandler(SoftLineBreak.class, this::render), new NodeRenderingHandler(StrongEmphasis.class, this::render), new NodeRenderingHandler(Text.class, this::render), new NodeRenderingHandler(TextBase.class, this::render), new NodeRenderingHandler(ThematicBreak.class, this::render)));
    }

    private void render(Document document, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(document);
    }

    private String repeat(String str, int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(str);
        }
        return sb.toString();
    }

    private void render(Heading heading, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String repeat = repeat("=", heading.getLevel());
        htmlWriter.line().raw((CharSequence) repeat);
        nodeRendererContext.renderChildren(heading);
        htmlWriter.raw((CharSequence) repeat);
        tailBlankLine(heading, htmlWriter);
    }

    private HtmlWriter tailBlankLine(Node node, HtmlWriter htmlWriter) {
        return tailBlankLine(node, 1, htmlWriter);
    }

    public boolean isLastBlockQuoteChild(Node node) {
        Node parent = node.getParent();
        return (parent instanceof BlockQuote) && parent.getLastChild() == node;
    }

    public HtmlWriter tailBlankLine(Node node, int i, HtmlWriter htmlWriter) {
        if (isLastBlockQuoteChild(node)) {
            BasedSequence prefix = htmlWriter.getPrefix();
            htmlWriter.popPrefix();
            htmlWriter.blankLine(i);
            htmlWriter.pushPrefix();
            htmlWriter.setPrefix((CharSequence) prefix, false);
        } else {
            htmlWriter.blankLine(i);
        }
        return htmlWriter;
    }

    private void render(BlockQuote blockQuote, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        this.inBlockQuote++;
        String str = repeat(">", this.inBlockQuote) + SequenceUtils.SPACE;
        htmlWriter.pushPrefix();
        htmlWriter.line().setPrefix((CharSequence) "").raw((CharSequence) str);
        htmlWriter.setPrefix((CharSequence) str);
        nodeRendererContext.renderChildren(blockQuote);
        this.inBlockQuote--;
        htmlWriter.popPrefix();
        tailBlankLine(blockQuote, htmlWriter);
    }

    private void render(FencedCodeBlock fencedCodeBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        BasedSequence info = fencedCodeBlock.getInfo();
        if (info.isNotNull() && !info.isBlank()) {
            htmlWriter.line().raw((CharSequence) ("{code:lang=" + info.unescape() + "}")).line();
        } else {
            htmlWriter.line().raw((CharSequence) "{code}").line();
        }
        htmlWriter.raw((CharSequence) fencedCodeBlock.getContentChars().normalizeEOL());
        htmlWriter.line().raw((CharSequence) "{code}");
        tailBlankLine(fencedCodeBlock, htmlWriter);
    }

    private void render(ThematicBreak thematicBreak, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.line().raw((CharSequence) "-----");
        tailBlankLine(thematicBreak, htmlWriter);
    }

    private void render(IndentedCodeBlock indentedCodeBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.line().raw((CharSequence) "{noformat}").line();
        htmlWriter.raw((CharSequence) indentedCodeBlock.getContentChars().trimTailBlankLines().normalizeEndWithEOL());
        htmlWriter.line().raw((CharSequence) "{noformat}").line();
    }

    private void renderListItemPrefix(ListItem listItem, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        ListItem listItem2 = listItem;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!(listItem2 instanceof ListBlock) && !(listItem2 instanceof ListItem)) {
                break;
            }
            if (listItem2 instanceof BulletList) {
                sb.append('*');
            } else if (listItem2 instanceof OrderedList) {
                sb.append('#');
            }
            listItem2 = listItem2.getParent();
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        htmlWriter.line().raw((CharSequence) sb.toString());
    }

    private void renderListItem(ListItem listItem, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderListItemPrefix(listItem, nodeRendererContext, htmlWriter);
        if (this.listOptions.isTightListItem(listItem)) {
            nodeRendererContext.renderChildren(listItem);
            return;
        }
        nodeRendererContext.renderChildren(listItem);
        if (listItem.getFirstChild().getNext() != null) {
            tailBlankLine(listItem, htmlWriter);
        }
    }

    private void renderList(ListBlock listBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(listBlock);
        if (listBlock.getParent() instanceof Document) {
            if (listBlock.getLastChild() == null || this.listOptions.isTightListItem((ListItem) listBlock.getLastChild())) {
                tailBlankLine(listBlock, htmlWriter);
            }
        }
    }

    private void render(BulletList bulletList, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderList(bulletList, nodeRendererContext, htmlWriter);
    }

    private void render(OrderedList orderedList, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderList(orderedList, nodeRendererContext, htmlWriter);
    }

    private void render(BulletListItem bulletListItem, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderListItem(bulletListItem, nodeRendererContext, htmlWriter);
    }

    private void render(OrderedListItem orderedListItem, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderListItem(orderedListItem, nodeRendererContext, htmlWriter);
    }

    private static void renderTextBlockParagraphLines(Node node, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(node);
        htmlWriter.line();
    }

    private void renderLooseParagraph(Paragraph paragraph, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderTextBlockParagraphLines(paragraph, nodeRendererContext, htmlWriter);
        if (this.inBlockQuote > 0 && paragraph.getNext() == null) {
            htmlWriter.line();
        } else {
            tailBlankLine(paragraph, htmlWriter);
        }
    }

    private void render(Paragraph paragraph, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (!(paragraph.getParent() instanceof ParagraphItemContainer) || !((ParagraphItemContainer) paragraph.getParent()).isParagraphWrappingDisabled(paragraph, this.listOptions, nodeRendererContext.getOptions())) {
            renderLooseParagraph(paragraph, nodeRendererContext, htmlWriter);
        } else {
            renderTextBlockParagraphLines(paragraph, nodeRendererContext, htmlWriter);
        }
    }

    public static BasedSequence getSoftLineBreakSpan(Node node) {
        if (node == null) {
            return BasedSequence.NULL;
        }
        Node node2 = node;
        Node next = node.getNext();
        while (true) {
            Node node3 = next;
            if (node3 == null || (node3 instanceof SoftLineBreak)) {
                break;
            }
            node2 = node3;
            next = node3.getNext();
        }
        return Node.spanningChars(node.getChars(), node2.getChars());
    }

    private void render(SoftLineBreak softLineBreak, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.raw(SequenceUtils.SPACE);
    }

    private void render(HardLineBreak hardLineBreak, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.line();
    }

    private void render(Emphasis emphasis, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.raw("''");
        nodeRendererContext.renderChildren(emphasis);
        htmlWriter.raw("''");
    }

    private void render(StrongEmphasis strongEmphasis, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.raw("*");
        nodeRendererContext.renderChildren(strongEmphasis);
        htmlWriter.raw("*");
    }

    private void render(Text text, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.raw((CharSequence) Escaping.normalizeEOL(text.getChars().unescape()));
    }

    private void render(TextBase textBase, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        nodeRendererContext.renderChildren(textBase);
    }

    private void render(Code code, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.raw("`");
        htmlWriter.raw((CharSequence) Escaping.collapseWhitespace((CharSequence) code.getText(), true));
        htmlWriter.raw("`");
    }

    private void render(HtmlBlock htmlBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (htmlBlock.hasChildren()) {
            nodeRendererContext.renderChildren(htmlBlock);
        } else {
            renderHtmlBlock(htmlBlock, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressHtmlBlocks, nodeRendererContext.getHtmlOptions().escapeHtmlBlocks);
        }
    }

    private void render(HtmlCommentBlock htmlCommentBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderHtmlBlock(htmlCommentBlock, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressHtmlCommentBlocks, nodeRendererContext.getHtmlOptions().escapeHtmlCommentBlocks);
    }

    private void render(HtmlInnerBlock htmlInnerBlock, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderHtmlBlock(htmlInnerBlock, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressHtmlBlocks, nodeRendererContext.getHtmlOptions().escapeHtmlBlocks);
    }

    private void render(HtmlInnerBlockComment htmlInnerBlockComment, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderHtmlBlock(htmlInnerBlockComment, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressHtmlCommentBlocks, nodeRendererContext.getHtmlOptions().escapeHtmlCommentBlocks);
    }

    public void renderHtmlBlock(HtmlBlockBase htmlBlockBase, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter, boolean z, boolean z2) {
        if (z) {
            return;
        }
        htmlWriter.line().raw((CharSequence) "{code:html}").line();
        htmlWriter.raw((CharSequence) htmlBlockBase.getContentChars().normalizeEOL());
        htmlWriter.line().raw((CharSequence) "{code:html}").line();
    }

    private void render(HtmlInline htmlInline, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderInlineHtml(htmlInline, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressInlineHtml, nodeRendererContext.getHtmlOptions().escapeInlineHtml);
    }

    private void render(HtmlInlineComment htmlInlineComment, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        renderInlineHtml(htmlInlineComment, nodeRendererContext, htmlWriter, nodeRendererContext.getHtmlOptions().suppressInlineHtmlComments, nodeRendererContext.getHtmlOptions().escapeInlineHtmlComments);
    }

    public void renderInlineHtml(HtmlInlineBase htmlInlineBase, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter, boolean z, boolean z2) {
        if (z) {
            return;
        }
        htmlWriter.raw("`").raw((CharSequence) htmlInlineBase.getChars().normalizeEOL()).raw((CharSequence) "`");
    }

    private void render(Reference reference, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
    }

    private void render(HtmlEntity htmlEntity, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        htmlWriter.raw((CharSequence) htmlEntity.getChars().unescape());
    }

    private void render(AutoLink autoLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String obj = autoLink.getText().toString();
        if (nodeRendererContext.isDoNotRenderLinks()) {
            htmlWriter.text((CharSequence) obj);
        } else {
            htmlWriter.raw("[").raw((CharSequence) obj).raw((CharSequence) "|").raw((CharSequence) nodeRendererContext.resolveLink(LinkType.LINK, obj, null).getUrl());
        }
    }

    private void render(MailLink mailLink, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        String unescape = mailLink.getText().unescape();
        if (nodeRendererContext.isDoNotRenderLinks()) {
            htmlWriter.text((CharSequence) unescape);
        } else {
            htmlWriter.raw("[").raw((CharSequence) unescape).raw((CharSequence) "|mailto:").raw((CharSequence) nodeRendererContext.resolveLink(LinkType.LINK, unescape, null).getUrl()).raw((CharSequence) "]");
        }
    }

    private void render(Image image, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (!nodeRendererContext.isDoNotRenderLinks()) {
            htmlWriter.raw("!").raw((CharSequence) nodeRendererContext.resolveLink(LinkType.IMAGE, image.getUrl().unescape(), null).getUrl()).raw((CharSequence) "!");
        }
    }

    private void render(Link link, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (nodeRendererContext.isDoNotRenderLinks()) {
            nodeRendererContext.renderChildren(link);
            return;
        }
        ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.LINK, link.getUrl().unescape(), null);
        htmlWriter.raw("[");
        nodeRendererContext.renderChildren(link);
        htmlWriter.raw("|").raw((CharSequence) resolveLink.getUrl()).raw((CharSequence) "]");
    }

    private void render(ImageRef imageRef, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (!imageRef.isDefined() && this.recheckUndefinedReferences && imageRef.getReferenceNode(this.referenceRepository) != null) {
            imageRef.setDefined(true);
        }
        if (!imageRef.isDefined()) {
            if (!$assertionsDisabled && imageRef.isDefined()) {
                throw new AssertionError();
            }
            htmlWriter.text((CharSequence) imageRef.getChars().unescape());
            return;
        }
        if (!nodeRendererContext.isDoNotRenderLinks()) {
            Reference referenceNode = imageRef.getReferenceNode(this.referenceRepository);
            if (!$assertionsDisabled && referenceNode == null) {
                throw new AssertionError();
            }
            htmlWriter.raw("!").raw((CharSequence) nodeRendererContext.resolveLink(LinkType.IMAGE, referenceNode.getUrl().unescape(), null).getUrl()).raw((CharSequence) "!");
        }
    }

    private void render(LinkRef linkRef, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        if (!linkRef.isDefined() && this.recheckUndefinedReferences && linkRef.getReferenceNode(this.referenceRepository) != null) {
            linkRef.setDefined(true);
        }
        if (!linkRef.isDefined()) {
            if (!$assertionsDisabled && linkRef.isDefined()) {
                throw new AssertionError();
            }
            htmlWriter.raw("[");
            nodeRendererContext.renderChildren(linkRef);
            htmlWriter.raw("]");
            if (!linkRef.isReferenceTextCombined()) {
                htmlWriter.raw("[");
                htmlWriter.raw((CharSequence) linkRef.getReference().unescape());
                htmlWriter.raw("]");
                return;
            }
            return;
        }
        if (nodeRendererContext.isDoNotRenderLinks()) {
            nodeRendererContext.renderChildren(linkRef);
            return;
        }
        Reference referenceNode = linkRef.getReferenceNode(this.referenceRepository);
        if (!$assertionsDisabled && referenceNode == null) {
            throw new AssertionError();
        }
        ResolvedLink resolveLink = nodeRendererContext.resolveLink(LinkType.LINK, referenceNode.getUrl().unescape(), null);
        htmlWriter.raw("[");
        nodeRendererContext.renderChildren(linkRef);
        htmlWriter.raw("|");
        htmlWriter.raw((CharSequence) resolveLink.getUrl());
        htmlWriter.raw("]");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/youtrack/converter/internal/YouTrackConverterNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new YouTrackConverterNodeRenderer(dataHolder);
        }
    }
}
