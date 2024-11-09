package com.vladsch.flexmark.formatter.internal;

import com.vladsch.flexmark.ast.AutoLink;
import com.vladsch.flexmark.ast.BlockQuote;
import com.vladsch.flexmark.ast.BulletList;
import com.vladsch.flexmark.ast.BulletListItem;
import com.vladsch.flexmark.ast.Code;
import com.vladsch.flexmark.ast.DelimitedLinkNode;
import com.vladsch.flexmark.ast.Emphasis;
import com.vladsch.flexmark.ast.FencedCodeBlock;
import com.vladsch.flexmark.ast.HardLineBreak;
import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.HtmlBlock;
import com.vladsch.flexmark.ast.HtmlBlockBase;
import com.vladsch.flexmark.ast.HtmlCommentBlock;
import com.vladsch.flexmark.ast.HtmlEntity;
import com.vladsch.flexmark.ast.HtmlInline;
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
import com.vladsch.flexmark.ast.ParagraphContainer;
import com.vladsch.flexmark.ast.ParagraphItemContainer;
import com.vladsch.flexmark.ast.RefNode;
import com.vladsch.flexmark.ast.Reference;
import com.vladsch.flexmark.ast.SoftLineBreak;
import com.vladsch.flexmark.ast.StrongEmphasis;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.TextBase;
import com.vladsch.flexmark.ast.ThematicBreak;
import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.formatter.FormatterOptions;
import com.vladsch.flexmark.formatter.FormatterUtils;
import com.vladsch.flexmark.formatter.FormattingPhase;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormatterFactory;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.formatter.NodeRepositoryFormatter;
import com.vladsch.flexmark.formatter.RenderPurpose;
import com.vladsch.flexmark.formatter.TranslationPlaceholderGenerator;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.BlankLine;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeRepository;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;
import com.vladsch.flexmark.util.format.options.ListSpacing;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.RepeatedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.mappers.SpaceMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/internal/CoreNodeFormatter.class */
public class CoreNodeFormatter extends NodeRepositoryFormatter<ReferenceRepository, Reference, RefNode> {

    @Deprecated
    public static final DataKey<Map<String, String>> UNIQUIFICATION_MAP;

    @Deprecated
    public static final DataKey<Map<String, String>> ATTRIBUTE_UNIQUIFICATION_ID_MAP;
    final FormatterOptions formatterOptions;
    private final ListOptions listOptions;
    private final String myHtmlBlockPrefix;
    private final String myHtmlInlinePrefix;
    private final String myTranslationAutolinkPrefix;
    private int blankLines;
    MutableDataHolder myTranslationStore;
    private Map<String, String> attributeUniquificationIdMap;
    static final TranslationPlaceholderGenerator htmlEntityPlaceholderGenerator;
    public static final DataKey<Boolean> UNWRAPPED_AUTO_LINKS;
    public static final DataKey<HashSet<String>> UNWRAPPED_AUTO_LINKS_MAP;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !CoreNodeFormatter.class.desiredAssertionStatus();
        UNIQUIFICATION_MAP = Formatter.UNIQUIFICATION_MAP;
        ATTRIBUTE_UNIQUIFICATION_ID_MAP = Formatter.ATTRIBUTE_UNIQUIFICATION_ID_MAP;
        htmlEntityPlaceholderGenerator = i -> {
            return String.format(Locale.US, "&#%d;", Integer.valueOf(i));
        };
        UNWRAPPED_AUTO_LINKS = new DataKey<>("UNWRAPPED_AUTO_LINKS", Boolean.FALSE);
        UNWRAPPED_AUTO_LINKS_MAP = new DataKey<>("UNWRAPPED_AUTO_LINKS_MAP", HashSet::new);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/internal/CoreNodeFormatter$Factory.class */
    public static class Factory implements NodeFormatterFactory {
        @Override // com.vladsch.flexmark.formatter.NodeFormatterFactory
        public NodeFormatter create(DataHolder dataHolder) {
            return new CoreNodeFormatter(dataHolder);
        }
    }

    public CoreNodeFormatter(DataHolder dataHolder) {
        super(dataHolder, null, Formatter.UNIQUIFICATION_MAP);
        this.formatterOptions = new FormatterOptions(dataHolder);
        this.listOptions = ListOptions.get(dataHolder);
        this.blankLines = 0;
        this.myHtmlBlockPrefix = "<" + this.formatterOptions.translationHtmlBlockPrefix;
        this.myHtmlInlinePrefix = this.formatterOptions.translationHtmlInlinePrefix;
        this.myTranslationAutolinkPrefix = this.formatterOptions.translationAutolinkPrefix;
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public char getBlockQuoteLikePrefixChar() {
        return '>';
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(Node.class, this::render), new NodeFormattingHandler(AutoLink.class, this::render), new NodeFormattingHandler(BlankLine.class, this::render), new NodeFormattingHandler(BlockQuote.class, this::render), new NodeFormattingHandler(Code.class, this::render), new NodeFormattingHandler(Document.class, this::render), new NodeFormattingHandler(Emphasis.class, this::render), new NodeFormattingHandler(FencedCodeBlock.class, this::render), new NodeFormattingHandler(HardLineBreak.class, this::render), new NodeFormattingHandler(Heading.class, this::render), new NodeFormattingHandler(HtmlBlock.class, this::render), new NodeFormattingHandler(HtmlCommentBlock.class, this::render), new NodeFormattingHandler(HtmlInnerBlock.class, (v1, v2, v3) -> {
            render(v1, v2, v3);
        }), new NodeFormattingHandler(HtmlInnerBlockComment.class, this::render), new NodeFormattingHandler(HtmlEntity.class, this::render), new NodeFormattingHandler(HtmlInline.class, this::render), new NodeFormattingHandler(HtmlInlineComment.class, this::render), new NodeFormattingHandler(Image.class, this::render), new NodeFormattingHandler(ImageRef.class, this::render), new NodeFormattingHandler(IndentedCodeBlock.class, this::render), new NodeFormattingHandler(Link.class, this::render), new NodeFormattingHandler(LinkRef.class, this::render), new NodeFormattingHandler(BulletList.class, this::render), new NodeFormattingHandler(OrderedList.class, this::render), new NodeFormattingHandler(BulletListItem.class, this::render), new NodeFormattingHandler(OrderedListItem.class, this::render), new NodeFormattingHandler(MailLink.class, this::render), new NodeFormattingHandler(Paragraph.class, this::render), new NodeFormattingHandler(Reference.class, this::render), new NodeFormattingHandler(SoftLineBreak.class, this::render), new NodeFormattingHandler(StrongEmphasis.class, this::render), new NodeFormattingHandler(Text.class, this::render), new NodeFormattingHandler(TextBase.class, this::render), new NodeFormattingHandler(ThematicBreak.class, this::render)));
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        if (this.formatterOptions.referencePlacement.isNoChange() || !this.formatterOptions.referenceSort.isUnused()) {
            return null;
        }
        return new HashSet(Arrays.asList(RefNode.class));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ReferenceRepository getRepository(DataHolder dataHolder) {
        return Parser.REFERENCES.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ElementPlacement getReferencePlacement() {
        return this.formatterOptions.referencePlacement;
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public ElementPlacementSort getReferenceSort() {
        return this.formatterOptions.referenceSort;
    }

    void appendReference(CharSequence charSequence, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (nodeFormatterContext.isTransformingText() && nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATED && nodeFormatterContext.getMergeContext() != null) {
            String valueOf = String.valueOf(nodeFormatterContext.transformTranslating(null, charSequence, null, null));
            markdownWriter.append((CharSequence) this.referenceUniqificationMap.getOrDefault(valueOf, valueOf));
        } else {
            markdownWriter.appendTranslating(charSequence);
        }
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter
    public void renderReferenceBlock(Reference reference, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (nodeFormatterContext.isTransformingText()) {
            markdownWriter.append((CharSequence) reference.getOpeningMarker());
            appendReference(reference.getReference(), nodeFormatterContext, markdownWriter);
            markdownWriter.append((CharSequence) reference.getClosingMarker());
            markdownWriter.append(' ');
            markdownWriter.append((CharSequence) reference.getUrlOpeningMarker());
            if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATION_SPANS) {
                ResolvedLink resolveLink = nodeFormatterContext.resolveLink(LinkType.LINK, reference.getUrl(), Boolean.FALSE);
                markdownWriter.appendNonTranslating(resolveLink.getPageRef());
                if (resolveLink.getAnchorRef() != null) {
                    markdownWriter.append("#");
                    CharSequence transformAnchorRef = nodeFormatterContext.transformAnchorRef(resolveLink.getPageRef(), resolveLink.getAnchorRef());
                    if (this.attributeUniquificationIdMap != null && resolveLink.getPageRef().isEmpty() && nodeFormatterContext.isTransformingText() && nodeFormatterContext.getMergeContext() != null) {
                        String valueOf = String.valueOf(transformAnchorRef);
                        markdownWriter.append((CharSequence) this.attributeUniquificationIdMap.getOrDefault(valueOf, valueOf));
                    } else {
                        markdownWriter.append(transformAnchorRef);
                    }
                    markdownWriter.append(transformAnchorRef);
                }
            } else {
                markdownWriter.appendNonTranslating(reference.getPageRef());
                markdownWriter.append((CharSequence) reference.getAnchorMarker());
                if (reference.getAnchorRef().isNotNull()) {
                    markdownWriter.append(nodeFormatterContext.transformAnchorRef(reference.getPageRef(), reference.getAnchorRef()));
                }
            }
            if (reference.getTitleOpeningMarker().isNotNull()) {
                markdownWriter.append(' ');
                markdownWriter.append((CharSequence) reference.getTitleOpeningMarker());
                if (reference.getTitle().isNotNull()) {
                    markdownWriter.appendTranslating(reference.getTitle());
                }
                markdownWriter.append((CharSequence) reference.getTitleClosingMarker());
            }
            markdownWriter.append((CharSequence) reference.getUrlClosingMarker()).line();
            return;
        }
        markdownWriter.append((CharSequence) reference.getChars()).line();
        Node next = reference.getNext();
        if ((next instanceof HtmlCommentBlock) || (next instanceof HtmlInnerBlockComment)) {
            BasedSequence midSequence = next.getChars().trim().midSequence(4, -3);
            if (this.formatterOptions.linkMarkerCommentPattern != null && this.formatterOptions.linkMarkerCommentPattern.matcher(midSequence).matches()) {
                markdownWriter.append("<!--").append((CharSequence) String.valueOf(midSequence)).append((CharSequence) "-->");
            }
        }
        markdownWriter.line();
    }

    @Override // com.vladsch.flexmark.formatter.NodeRepositoryFormatter, com.vladsch.flexmark.formatter.PhasedNodeFormatter
    public void renderDocument(NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, Document document, FormattingPhase formattingPhase) {
        super.renderDocument(nodeFormatterContext, markdownWriter, document, formattingPhase);
        this.attributeUniquificationIdMap = Formatter.ATTRIBUTE_UNIQUIFICATION_ID_MAP.get(nodeFormatterContext.getTranslationStore());
        if (formattingPhase == FormattingPhase.DOCUMENT_BOTTOM && this.formatterOptions.appendTransferredReferences) {
            ArrayList arrayList = new ArrayList();
            for (DataKeyBase<?> dataKeyBase : document.getAll().keySet()) {
                if (dataKeyBase.get(document) instanceof NodeRepository) {
                    arrayList.add(dataKeyBase);
                }
            }
            arrayList.sort(Comparator.comparing((v0) -> {
                return v0.getName();
            }));
            boolean z = true;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                DataKeyBase dataKeyBase2 = (DataKeyBase) it.next();
                if (dataKeyBase2.get(document) instanceof NodeRepository) {
                    for (Object obj : ((NodeRepository) dataKeyBase2.get(document)).getReferencedElements(document)) {
                        if (obj instanceof Node) {
                            Node node = (Node) obj;
                            if (node.getDocument() != document) {
                                if (z) {
                                    z = false;
                                    markdownWriter.blankLine();
                                }
                                nodeFormatterContext.render(node);
                            }
                        }
                    }
                }
            }
        }
    }

    private void render(Node node, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        BasedSequence chars = node.getChars();
        if (node instanceof Block) {
            BasedSequence contentChars = ((Block) node).getContentChars();
            if (chars.isNotNull()) {
                BasedSequence prefixOf = chars.prefixOf(contentChars);
                if (!prefixOf.isEmpty()) {
                    markdownWriter.append((CharSequence) prefixOf);
                }
            }
            nodeFormatterContext.renderChildren(node);
            if (chars.isNotNull()) {
                BasedSequence suffixOf = chars.suffixOf(contentChars);
                if (!suffixOf.isEmpty()) {
                    markdownWriter.append((CharSequence) suffixOf);
                    return;
                }
                return;
            }
            return;
        }
        if (this.formatterOptions.keepSoftLineBreaks) {
            markdownWriter.append((CharSequence) chars);
        } else {
            markdownWriter.append(FormatterUtils.stripSoftLineBreak(chars, SequenceUtils.SPACE));
        }
    }

    private void render(BlankLine blankLine, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (FormatterUtils.LIST_ITEM_SPACING.get(nodeFormatterContext.getDocument()) == null && markdownWriter.offsetWithPending() > 0) {
            if (blankLine.getPrevious() != null && !(blankLine.getPrevious() instanceof BlankLine)) {
                this.blankLines = 0;
            }
            this.blankLines++;
            if (this.blankLines <= this.formatterOptions.maxBlankLines) {
                markdownWriter.blankLine(this.blankLines);
            }
        }
    }

    private void render(Document document, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        this.myTranslationStore = nodeFormatterContext.getTranslationStore();
        nodeFormatterContext.renderChildren(document);
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00b5, code lost:            if (r9.getClosingMarker().isNull() == false) goto L23;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void render(com.vladsch.flexmark.ast.Heading r9, com.vladsch.flexmark.formatter.NodeFormatterContext r10, com.vladsch.flexmark.formatter.MarkdownWriter r11) {
        /*
            Method dump skipped, instructions count: 673
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.formatter.internal.CoreNodeFormatter.render(com.vladsch.flexmark.ast.Heading, com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.formatter.MarkdownWriter):void");
    }

    private void render(BlockQuote blockQuote, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        FormatterUtils.renderBlockQuoteLike(blockQuote, nodeFormatterContext, markdownWriter);
    }

    private void render(ThematicBreak thematicBreak, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.blankLine();
        if (this.formatterOptions.thematicBreak != null) {
            markdownWriter.append((CharSequence) this.formatterOptions.thematicBreak);
        } else {
            markdownWriter.append((CharSequence) thematicBreak.getChars());
        }
        markdownWriter.tailBlankLine();
    }

    private void render(FencedCodeBlock fencedCodeBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.blankLine();
        BasedSequence openingMarker = fencedCodeBlock.getOpeningMarker();
        BasedSequence closingMarker = fencedCodeBlock.getClosingMarker();
        char charAt = openingMarker.charAt(0);
        char charAt2 = closingMarker.length() > 0 ? closingMarker.charAt(0) : (char) 0;
        int length = openingMarker.length();
        int length2 = closingMarker.length();
        switch (this.formatterOptions.fencedCodeMarkerType) {
            case BACK_TICK:
                charAt = '`';
                charAt2 = '`';
                break;
            case TILDE:
                charAt = '~';
                charAt2 = '~';
                break;
        }
        if (length < this.formatterOptions.fencedCodeMarkerLength) {
            length = this.formatterOptions.fencedCodeMarkerLength;
        }
        if (length2 < this.formatterOptions.fencedCodeMarkerLength) {
            length2 = this.formatterOptions.fencedCodeMarkerLength;
        }
        CharSequence repeatOf = RepeatedSequence.repeatOf(String.valueOf(charAt), length);
        CharSequence repeatOf2 = (this.formatterOptions.fencedCodeMatchClosingMarker || charAt2 == 0) ? repeatOf : RepeatedSequence.repeatOf(String.valueOf(charAt2), length2);
        markdownWriter.append(repeatOf);
        if (this.formatterOptions.fencedCodeSpaceBeforeInfo) {
            markdownWriter.append(' ');
        }
        markdownWriter.appendNonTranslating(fencedCodeBlock.getInfo());
        markdownWriter.line();
        markdownWriter.openPreFormatted(true);
        if (nodeFormatterContext.isTransformingText()) {
            markdownWriter.appendNonTranslating(fencedCodeBlock.getContentChars());
        } else if (this.formatterOptions.fencedCodeMinimizeIndent) {
            List<BasedSequence> contentLines = fencedCodeBlock.getContentLines();
            int[] iArr = new int[contentLines.size()];
            int i = Integer.MAX_VALUE;
            int i2 = 0;
            Iterator<BasedSequence> it = contentLines.iterator();
            while (it.hasNext()) {
                iArr[i2] = it.next().countLeadingColumns(0, CharPredicate.SPACE_TAB);
                i = Utils.min(i, iArr[i2]);
                i2++;
            }
            if (i > 0) {
                int i3 = 0;
                for (BasedSequence basedSequence : contentLines) {
                    if (iArr[i3] > i) {
                        markdownWriter.append(' ', iArr[i3] - i);
                    }
                    markdownWriter.append((CharSequence) basedSequence.trimStart());
                    i3++;
                }
            } else {
                markdownWriter.append((CharSequence) fencedCodeBlock.getContentChars());
            }
        } else {
            markdownWriter.append((CharSequence) fencedCodeBlock.getContentChars());
        }
        markdownWriter.closePreFormatted();
        markdownWriter.line().append(repeatOf2).line();
        if (!(fencedCodeBlock.getParent() instanceof ListItem) || !FormatterUtils.isLastOfItem(fencedCodeBlock) || FormatterUtils.LIST_ITEM_SPACING.get(nodeFormatterContext.getDocument()) == ListSpacing.LOOSE) {
            markdownWriter.tailBlankLine();
        }
    }

    private void render(IndentedCodeBlock indentedCodeBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.blankLine();
        if (nodeFormatterContext.isTransformingText()) {
            BasedSequence contentChars = indentedCodeBlock.getContentChars();
            BasedSequence basedSequence = contentChars;
            String actualAdditionalPrefix = FormatterUtils.getActualAdditionalPrefix(contentChars, markdownWriter);
            if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATED) {
                basedSequence = basedSequence.trimStart();
            }
            markdownWriter.pushPrefix().addPrefix((CharSequence) actualAdditionalPrefix);
            markdownWriter.openPreFormatted(true);
            markdownWriter.appendNonTranslating(Utils.suffixWith(basedSequence.toString(), '\n'));
        } else {
            String charSequence = RepeatedSequence.repeatOf(SequenceUtils.SPACE, this.listOptions.getCodeIndent()).toString();
            if (this.formatterOptions.emulationProfile == ParserEmulationProfile.GITHUB_DOC && (indentedCodeBlock.getParent() instanceof ListItem)) {
                charSequence = RepeatedSequence.repeatOf(SequenceUtils.SPACE, Utils.minLimit((8 - ((ListItem) indentedCodeBlock.getParent()).getOpeningMarker().length()) - 1, 4)).toString();
            }
            markdownWriter.pushPrefix().addPrefix((CharSequence) charSequence);
            markdownWriter.openPreFormatted(true);
            if (this.formatterOptions.indentedCodeMinimizeIndent) {
                List<BasedSequence> contentLines = indentedCodeBlock.getContentLines();
                int[] iArr = new int[contentLines.size()];
                int i = Integer.MAX_VALUE;
                int i2 = 0;
                Iterator<BasedSequence> it = contentLines.iterator();
                while (it.hasNext()) {
                    iArr[i2] = it.next().countLeadingColumns(0, CharPredicate.SPACE_TAB);
                    i = Utils.min(i, iArr[i2]);
                    i2++;
                }
                if (i > 0) {
                    int i3 = 0;
                    for (BasedSequence basedSequence2 : contentLines) {
                        if (iArr[i3] > i) {
                            markdownWriter.append(' ', iArr[i3] - i);
                        }
                        markdownWriter.append((CharSequence) basedSequence2.trimStart());
                        i3++;
                    }
                } else {
                    markdownWriter.append((CharSequence) indentedCodeBlock.getContentChars());
                }
            } else {
                markdownWriter.append((CharSequence) indentedCodeBlock.getContentChars());
            }
        }
        markdownWriter.closePreFormatted();
        markdownWriter.popPrefix(true);
        markdownWriter.tailBlankLine();
    }

    private void render(BulletList bulletList, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        FormatterUtils.renderList(bulletList, nodeFormatterContext, markdownWriter);
    }

    private void render(OrderedList orderedList, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        FormatterUtils.renderList(orderedList, nodeFormatterContext, markdownWriter);
    }

    private void render(BulletListItem bulletListItem, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        FormatterUtils.renderListItem(bulletListItem, nodeFormatterContext, markdownWriter, this.listOptions, bulletListItem.getMarkerSuffix(), false);
    }

    private void render(OrderedListItem orderedListItem, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        FormatterUtils.renderListItem(orderedListItem, nodeFormatterContext, markdownWriter, this.listOptions, orderedListItem.getMarkerSuffix(), false);
    }

    private void render(Emphasis emphasis, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append((CharSequence) emphasis.getOpeningMarker());
        nodeFormatterContext.renderChildren(emphasis);
        markdownWriter.append((CharSequence) emphasis.getOpeningMarker());
    }

    private void render(StrongEmphasis strongEmphasis, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append((CharSequence) strongEmphasis.getOpeningMarker());
        nodeFormatterContext.renderChildren(strongEmphasis);
        markdownWriter.append((CharSequence) strongEmphasis.getOpeningMarker());
    }

    private void render(Paragraph paragraph, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (nodeFormatterContext.isTransformingText()) {
            FormatterUtils.renderTextBlockParagraphLines(paragraph, nodeFormatterContext, markdownWriter);
            if (paragraph.isTrailingBlankLine()) {
                markdownWriter.tailBlankLine();
                return;
            }
            return;
        }
        if (!(paragraph.getParent() instanceof ParagraphItemContainer)) {
            if (paragraph.getParent() instanceof ParagraphContainer) {
                boolean isParagraphStartWrappingDisabled = ((ParagraphContainer) paragraph.getParent()).isParagraphStartWrappingDisabled(paragraph);
                boolean isParagraphEndWrappingDisabled = ((ParagraphContainer) paragraph.getParent()).isParagraphEndWrappingDisabled(paragraph);
                if (isParagraphStartWrappingDisabled || isParagraphEndWrappingDisabled) {
                    if (!isParagraphStartWrappingDisabled) {
                        markdownWriter.blankLine();
                    }
                    FormatterUtils.renderTextBlockParagraphLines(paragraph, nodeFormatterContext, markdownWriter);
                    if (isParagraphEndWrappingDisabled) {
                        return;
                    }
                    markdownWriter.tailBlankLine();
                    return;
                }
                FormatterUtils.renderLooseParagraph(paragraph, nodeFormatterContext, markdownWriter);
                return;
            }
            if (!paragraph.isTrailingBlankLine() && (paragraph.getNext() == null || (paragraph.getNext() instanceof ListBlock))) {
                FormatterUtils.renderTextBlockParagraphLines(paragraph, nodeFormatterContext, markdownWriter);
                return;
            } else {
                FormatterUtils.renderLooseParagraph(paragraph, nodeFormatterContext, markdownWriter);
                return;
            }
        }
        if (((ParagraphItemContainer) paragraph.getParent()).isItemParagraph(paragraph)) {
            if (this.formatterOptions.blankLinesInAst) {
                FormatterUtils.renderLooseItemParagraph(paragraph, nodeFormatterContext, markdownWriter);
                return;
            }
            ListSpacing listSpacing = FormatterUtils.LIST_ITEM_SPACING.get(nodeFormatterContext.getDocument());
            if (listSpacing != ListSpacing.TIGHT) {
                if (listSpacing == ListSpacing.LOOSE) {
                    if (paragraph.getParent().getNextAnyNot(BlankLine.class) != null) {
                        FormatterUtils.renderLooseItemParagraph(paragraph, nodeFormatterContext, markdownWriter);
                        return;
                    }
                } else if (!((ParagraphItemContainer) paragraph.getParent()).isParagraphWrappingDisabled(paragraph, this.listOptions, nodeFormatterContext.getOptions())) {
                    FormatterUtils.renderLooseItemParagraph(paragraph, nodeFormatterContext, markdownWriter);
                    return;
                }
            }
            FormatterUtils.renderTextBlockParagraphLines(paragraph, nodeFormatterContext, markdownWriter);
            return;
        }
        FormatterUtils.renderLooseParagraph(paragraph, nodeFormatterContext, markdownWriter);
    }

    private void render(SoftLineBreak softLineBreak, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (this.formatterOptions.keepSoftLineBreaks || this.formatterOptions.rightMargin > 0) {
            markdownWriter.append((CharSequence) softLineBreak.getChars());
        } else if (!markdownWriter.isPendingSpace()) {
            markdownWriter.append(' ');
        }
    }

    private void render(HardLineBreak hardLineBreak, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (this.formatterOptions.keepHardLineBreaks) {
            if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.FORMAT) {
                markdownWriter.append((CharSequence) hardLineBreak.getChars());
                return;
            } else {
                markdownWriter.append((CharSequence) hardLineBreak.getChars());
                return;
            }
        }
        if (!markdownWriter.isPendingSpace()) {
            markdownWriter.append(' ');
        }
    }

    private void render(HtmlEntity htmlEntity, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.FORMAT) {
            markdownWriter.append((CharSequence) htmlEntity.getChars());
        } else {
            nodeFormatterContext.customPlaceholderFormat(htmlEntityPlaceholderGenerator, (nodeFormatterContext2, markdownWriter2) -> {
                markdownWriter2.appendNonTranslating(htmlEntity.getChars());
            });
        }
    }

    private void render(Text text, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (this.formatterOptions.keepSoftLineBreaks) {
            markdownWriter.append((CharSequence) text.getChars());
        } else {
            markdownWriter.append(FormatterUtils.stripSoftLineBreak(text.getChars(), SequenceUtils.SPACE));
        }
    }

    private void render(TextBase textBase, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        nodeFormatterContext.renderChildren(textBase);
    }

    private void render(Code code, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.append((CharSequence) code.getOpeningMarker());
        if (nodeFormatterContext.isTransformingText() || this.formatterOptions.rightMargin == 0) {
            if (this.formatterOptions.keepSoftLineBreaks) {
                markdownWriter.appendNonTranslating(code.getText());
            } else {
                markdownWriter.appendNonTranslating(FormatterUtils.stripSoftLineBreak(code.getText(), SequenceUtils.SPACE));
            }
        } else if (this.formatterOptions.keepSoftLineBreaks) {
            markdownWriter.append((CharSequence) code.getText());
        } else {
            markdownWriter.append(FormatterUtils.stripSoftLineBreak(code.getText(), SequenceUtils.SPACE));
        }
        markdownWriter.append((CharSequence) code.getClosingMarker());
    }

    private void render(HtmlBlock htmlBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (htmlBlock.hasChildren()) {
            nodeFormatterContext.renderChildren(htmlBlock);
            return;
        }
        markdownWriter.blankLine();
        render((HtmlBlockBase) htmlBlock, nodeFormatterContext, markdownWriter);
        markdownWriter.tailBlankLine();
    }

    private void render(HtmlCommentBlock htmlCommentBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        BasedSequence midSequence = htmlCommentBlock.getChars().trim().midSequence(4, -3);
        CharSequence charSequence = BasedSequence.EOL;
        if (!nodeFormatterContext.isTransformingText() && this.formatterOptions.linkMarkerCommentPattern != null && this.formatterOptions.linkMarkerCommentPattern.matcher(midSequence).matches()) {
            if (!(htmlCommentBlock.getPrevious() instanceof Reference)) {
                ((MarkdownWriter) markdownWriter.append("<!--")).append((CharSequence) String.valueOf(midSequence.toMapped(SpaceMapper.toNonBreakSpace))).append((CharSequence) "-->");
                return;
            }
            return;
        }
        markdownWriter.appendTranslating("<!--", midSequence, "-->", charSequence);
    }

    private void render(HtmlBlockBase htmlBlockBase, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        switch (nodeFormatterContext.getRenderPurpose()) {
            case TRANSLATION_SPANS:
            case TRANSLATED_SPANS:
                markdownWriter.appendNonTranslating(this.myHtmlBlockPrefix, htmlBlockBase.getChars().trimEOL(), ">", htmlBlockBase.getChars().trimmedEOL());
                return;
            case TRANSLATED:
                markdownWriter.openPreFormatted(true);
                markdownWriter.appendNonTranslating(htmlBlockBase.getChars());
                markdownWriter.closePreFormatted();
                return;
            default:
                markdownWriter.openPreFormatted(true);
                if (htmlBlockBase.getSpanningChars().equals(htmlBlockBase.getChars())) {
                    Iterator<BasedSequence> it = htmlBlockBase.getContentLines().iterator();
                    while (it.hasNext()) {
                        markdownWriter.append((CharSequence) it.next());
                    }
                } else {
                    markdownWriter.append((CharSequence) htmlBlockBase.getChars());
                }
                markdownWriter.line().closePreFormatted();
                return;
        }
    }

    private void render(HtmlInnerBlockComment htmlInnerBlockComment, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        BasedSequence midSequence = htmlInnerBlockComment.getChars().trim().midSequence(4, -3);
        if (!nodeFormatterContext.isTransformingText() && this.formatterOptions.linkMarkerCommentPattern != null && this.formatterOptions.linkMarkerCommentPattern.matcher(midSequence).matches()) {
            if (!(htmlInnerBlockComment.getPrevious() instanceof Reference)) {
                ((MarkdownWriter) markdownWriter.append("<!--")).append((CharSequence) String.valueOf(midSequence.toMapped(SpaceMapper.toNonBreakSpace))).append((CharSequence) "-->");
                return;
            }
            return;
        }
        markdownWriter.appendTranslating("<!--", midSequence, "-->");
    }

    private void render(HtmlInline htmlInline, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        switch (nodeFormatterContext.getRenderPurpose()) {
            case TRANSLATION_SPANS:
            case TRANSLATED_SPANS:
                markdownWriter.appendNonTranslating((htmlInline.getChars().startsWith("</") ? "</" : "<") + this.myHtmlInlinePrefix, htmlInline.getChars(), ">");
                return;
            case TRANSLATED:
                markdownWriter.appendNonTranslating(htmlInline.getChars());
                return;
            default:
                markdownWriter.append((CharSequence) htmlInline.getChars());
                return;
        }
    }

    private void render(HtmlInlineComment htmlInlineComment, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        BasedSequence midSequence = htmlInlineComment.getChars().trim().midSequence(4, -3);
        if (!nodeFormatterContext.isTransformingText() && this.formatterOptions.linkMarkerCommentPattern != null && this.formatterOptions.linkMarkerCommentPattern.matcher(midSequence).matches()) {
            ((MarkdownWriter) markdownWriter.append("<!--")).append((CharSequence) String.valueOf(midSequence.toMapped(SpaceMapper.toNonBreakSpace))).append((CharSequence) "-->");
        } else {
            markdownWriter.appendTranslating("<!--", midSequence, "-->");
        }
    }

    private void render(Reference reference, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        renderReference(reference, nodeFormatterContext, markdownWriter);
    }

    private void render(AutoLink autoLink, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        renderAutoLink(autoLink, nodeFormatterContext, markdownWriter, this.myTranslationAutolinkPrefix, null);
    }

    private void render(MailLink mailLink, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        renderAutoLink(mailLink, nodeFormatterContext, markdownWriter, this.myTranslationAutolinkPrefix, null);
    }

    private void renderAutoLink(DelimitedLinkNode delimitedLinkNode, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, String str, String str2) {
        if (nodeFormatterContext.isTransformingText()) {
            switch (nodeFormatterContext.getRenderPurpose()) {
                case TRANSLATION_SPANS:
                    if (delimitedLinkNode.getOpeningMarker().isNull()) {
                        this.myTranslationStore.set((DataKey<DataKey<Boolean>>) UNWRAPPED_AUTO_LINKS, (DataKey<Boolean>) Boolean.TRUE);
                        nodeFormatterContext.postProcessNonTranslating(str3 -> {
                            UNWRAPPED_AUTO_LINKS_MAP.get(this.myTranslationStore).add(str3);
                            return str3;
                        }, () -> {
                            markdownWriter.append("<");
                            markdownWriter.appendNonTranslating(str, delimitedLinkNode.getText(), str2);
                            markdownWriter.append(">");
                        });
                        return;
                    } else {
                        markdownWriter.append("<");
                        markdownWriter.appendNonTranslating(str, delimitedLinkNode.getText(), str2);
                        markdownWriter.append(">");
                        return;
                    }
                case TRANSLATED_SPANS:
                    markdownWriter.append("<");
                    markdownWriter.appendNonTranslating(str, delimitedLinkNode.getText(), str2);
                    markdownWriter.append(">");
                    return;
                case TRANSLATED:
                    if (UNWRAPPED_AUTO_LINKS.get(this.myTranslationStore).booleanValue() && UNWRAPPED_AUTO_LINKS_MAP.get(this.myTranslationStore).contains(delimitedLinkNode.getText().toString())) {
                        markdownWriter.appendNonTranslating(str, delimitedLinkNode.getText(), str2);
                        return;
                    }
                    markdownWriter.append("<");
                    markdownWriter.appendNonTranslating(str, delimitedLinkNode.getText(), str2);
                    markdownWriter.append(">");
                    return;
                default:
                    markdownWriter.append((CharSequence) delimitedLinkNode.getChars());
                    return;
            }
        }
        markdownWriter.append((CharSequence) delimitedLinkNode.getChars());
    }

    private void render(Image image, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (!nodeFormatterContext.isTransformingText() && this.formatterOptions.rightMargin > 0 && this.formatterOptions.keepImageLinksAtStart) {
            markdownWriter.append((char) 8232);
        } else {
            markdownWriter.lineIf(this.formatterOptions.keepImageLinksAtStart);
        }
        if (!this.formatterOptions.optimizedInlineRendering || nodeFormatterContext.isTransformingText()) {
            markdownWriter.append((CharSequence) image.getTextOpeningMarker());
            if (!nodeFormatterContext.isTransformingText() || image.getFirstChildAny(HtmlInline.class) != null) {
                if (this.formatterOptions.rightMargin > 0) {
                    markdownWriter.append((CharSequence) image.getText().toMapped(SpaceMapper.toNonBreakSpace));
                } else {
                    nodeFormatterContext.renderChildren(image);
                }
            } else {
                markdownWriter.appendTranslating(image.getText());
            }
            markdownWriter.append((CharSequence) image.getTextClosingMarker());
            markdownWriter.append((CharSequence) image.getLinkOpeningMarker());
            markdownWriter.append((CharSequence) image.getUrlOpeningMarker());
            if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATION_SPANS) {
                markdownWriter.appendNonTranslating(nodeFormatterContext.resolveLink(LinkType.LINK, image.getUrl(), Boolean.FALSE).getPageRef());
            } else {
                markdownWriter.append((CharSequence) image.getUrlOpeningMarker());
                markdownWriter.appendNonTranslating(image.getPageRef());
            }
            markdownWriter.append((CharSequence) image.getUrlClosingMarker());
            if (!image.getUrlContent().isEmpty()) {
                markdownWriter.openPreFormatted(true);
                markdownWriter.pushOptions().preserveSpaces();
                if (!nodeFormatterContext.isTransformingText() && this.formatterOptions.rightMargin > 0) {
                    BasedSequence urlContent = image.getUrlContent();
                    int length = urlContent.length();
                    boolean z = true;
                    markdownWriter.append('\n');
                    for (int i = 0; i < length; i++) {
                        switch (urlContent.charAt(i)) {
                            case '\n':
                            case '\r':
                                z = true;
                                int i2 = i;
                                markdownWriter.append(urlContent.subSequence(i2, i2 + 1));
                                break;
                            case ' ':
                                if (z) {
                                    markdownWriter.append((char) 8232);
                                    z = false;
                                }
                                markdownWriter.append((char) 160);
                                break;
                            default:
                                if (z) {
                                    markdownWriter.append((char) 8232);
                                    z = false;
                                }
                                int i3 = i;
                                markdownWriter.append(urlContent.subSequence(i3, i3 + 1));
                                break;
                        }
                    }
                } else {
                    markdownWriter.append((CharSequence) image.getUrlContent());
                }
                markdownWriter.popOptions();
                markdownWriter.closePreFormatted();
                markdownWriter.append((char) 8232);
            }
            if (image.getTitleOpeningMarker().isNotNull()) {
                markdownWriter.append(' ');
                markdownWriter.append((CharSequence) image.getTitleOpeningMarker());
                if (image.getTitle().isNotNull()) {
                    markdownWriter.appendTranslating(image.getTitle());
                }
                markdownWriter.append((CharSequence) image.getTitleClosingMarker());
            }
            markdownWriter.append((CharSequence) image.getLinkClosingMarker());
            return;
        }
        markdownWriter.append((CharSequence) image.getChars());
    }

    private void render(Link link, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (!nodeFormatterContext.isTransformingText() && this.formatterOptions.rightMargin > 0 && this.formatterOptions.keepExplicitLinksAtStart) {
            markdownWriter.append((char) 8232);
        } else {
            markdownWriter.lineIf(this.formatterOptions.keepExplicitLinksAtStart);
        }
        if (!this.formatterOptions.optimizedInlineRendering || nodeFormatterContext.isTransformingText()) {
            markdownWriter.append((CharSequence) link.getTextOpeningMarker());
            if (!nodeFormatterContext.isTransformingText() || link.getFirstChildAny(HtmlInline.class) != null) {
                if (this.formatterOptions.rightMargin > 0) {
                    markdownWriter.append((CharSequence) link.getText().toMapped(SpaceMapper.toNonBreakSpace));
                } else {
                    nodeFormatterContext.renderChildren(link);
                }
            } else {
                markdownWriter.appendTranslating(link.getText());
            }
            markdownWriter.append((CharSequence) link.getTextClosingMarker());
            markdownWriter.append((CharSequence) link.getLinkOpeningMarker());
            markdownWriter.append((CharSequence) link.getUrlOpeningMarker());
            if (nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATION_SPANS) {
                ResolvedLink resolveLink = nodeFormatterContext.resolveLink(LinkType.LINK, link.getUrl(), Boolean.FALSE);
                markdownWriter.appendNonTranslating(resolveLink.getPageRef());
                if (resolveLink.getAnchorRef() != null) {
                    markdownWriter.append("#");
                    markdownWriter.append(nodeFormatterContext.transformAnchorRef(resolveLink.getPageRef(), resolveLink.getAnchorRef()));
                }
            } else {
                CharSequence transformNonTranslating = nodeFormatterContext.transformNonTranslating(null, link.getPageRef(), null, null);
                markdownWriter.append(transformNonTranslating);
                markdownWriter.append((CharSequence) link.getAnchorMarker());
                if (link.getAnchorRef().isNotNull()) {
                    CharSequence transformAnchorRef = nodeFormatterContext.transformAnchorRef(link.getPageRef(), link.getAnchorRef());
                    if (this.attributeUniquificationIdMap != null && nodeFormatterContext.getRenderPurpose() == RenderPurpose.TRANSLATED && nodeFormatterContext.getMergeContext() != null) {
                        String valueOf = String.valueOf(transformAnchorRef);
                        if (transformNonTranslating.length() == 0) {
                            valueOf = this.attributeUniquificationIdMap.getOrDefault(valueOf, valueOf);
                        }
                        markdownWriter.append((CharSequence) valueOf);
                    } else {
                        markdownWriter.append(transformAnchorRef);
                    }
                }
            }
            markdownWriter.append((CharSequence) link.getUrlClosingMarker());
            if (link.getTitleOpeningMarker().isNotNull()) {
                markdownWriter.append(' ');
                markdownWriter.append((CharSequence) link.getTitleOpeningMarker());
                if (link.getTitle().isNotNull()) {
                    markdownWriter.appendTranslating(link.getTitle());
                }
                markdownWriter.append((CharSequence) link.getTitleClosingMarker());
            }
            markdownWriter.append((CharSequence) link.getLinkClosingMarker());
            return;
        }
        markdownWriter.append((CharSequence) link.getChars());
    }

    private void render(ImageRef imageRef, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (!this.formatterOptions.optimizedInlineRendering || nodeFormatterContext.isTransformingText()) {
            if (nodeFormatterContext.isTransformingText() || this.formatterOptions.rightMargin == 0) {
                if (imageRef.isReferenceTextCombined()) {
                    markdownWriter.append((CharSequence) imageRef.getReferenceOpeningMarker());
                    markdownWriter.appendTranslating(imageRef.getReference());
                    markdownWriter.append((CharSequence) imageRef.getReferenceClosingMarker());
                    markdownWriter.append((CharSequence) imageRef.getTextOpeningMarker());
                    markdownWriter.append((CharSequence) imageRef.getTextClosingMarker());
                    return;
                }
                markdownWriter.append((CharSequence) imageRef.getTextOpeningMarker());
                appendReference(imageRef.getText(), nodeFormatterContext, markdownWriter);
                markdownWriter.append((CharSequence) imageRef.getTextClosingMarker());
                markdownWriter.append((CharSequence) imageRef.getReferenceOpeningMarker());
                markdownWriter.appendTranslating(imageRef.getReference());
                markdownWriter.append((CharSequence) imageRef.getReferenceClosingMarker());
                return;
            }
            if (imageRef.isReferenceTextCombined()) {
                markdownWriter.append((CharSequence) imageRef.getReferenceOpeningMarker());
                if (imageRef.isOrDescendantOfType(Paragraph.class)) {
                    markdownWriter.append((CharSequence) imageRef.getReference().toMapped(SpaceMapper.toNonBreakSpace));
                } else {
                    markdownWriter.append((CharSequence) imageRef.getReference());
                }
                markdownWriter.append((CharSequence) imageRef.getReferenceClosingMarker());
                markdownWriter.append((CharSequence) imageRef.getTextOpeningMarker());
                markdownWriter.append((CharSequence) imageRef.getTextClosingMarker());
                return;
            }
            markdownWriter.append((CharSequence) imageRef.getTextOpeningMarker());
            nodeFormatterContext.renderChildren(imageRef);
            markdownWriter.append((CharSequence) imageRef.getTextClosingMarker());
            markdownWriter.append((CharSequence) imageRef.getReferenceOpeningMarker());
            markdownWriter.append((CharSequence) imageRef.getReference());
            markdownWriter.append((CharSequence) imageRef.getReferenceClosingMarker());
            return;
        }
        markdownWriter.append((CharSequence) imageRef.getChars());
    }

    private void render(LinkRef linkRef, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (!this.formatterOptions.optimizedInlineRendering || nodeFormatterContext.isTransformingText()) {
            if (nodeFormatterContext.isTransformingText() || this.formatterOptions.rightMargin == 0) {
                if (linkRef.isReferenceTextCombined()) {
                    markdownWriter.append((CharSequence) linkRef.getReferenceOpeningMarker());
                    FormatterUtils.appendWhiteSpaceBetween(markdownWriter, linkRef.getReferenceOpeningMarker(), linkRef.getReference(), true, false, false);
                    appendReference(linkRef.getReference(), nodeFormatterContext, markdownWriter);
                    markdownWriter.append((CharSequence) linkRef.getReferenceClosingMarker());
                    markdownWriter.append((CharSequence) linkRef.getTextOpeningMarker());
                    markdownWriter.append((CharSequence) linkRef.getTextClosingMarker());
                    return;
                }
                markdownWriter.append((CharSequence) linkRef.getTextOpeningMarker());
                if (!nodeFormatterContext.isTransformingText() || linkRef.getFirstChildAny(HtmlInline.class) != null) {
                    nodeFormatterContext.renderChildren(linkRef);
                } else {
                    appendReference(linkRef.getText(), nodeFormatterContext, markdownWriter);
                }
                markdownWriter.append((CharSequence) linkRef.getTextClosingMarker());
                markdownWriter.append((CharSequence) linkRef.getReferenceOpeningMarker());
                FormatterUtils.appendWhiteSpaceBetween(markdownWriter, linkRef.getReferenceOpeningMarker(), linkRef.getReference(), true, false, false);
                markdownWriter.appendTranslating(linkRef.getReference());
                markdownWriter.append((CharSequence) linkRef.getReferenceClosingMarker());
                return;
            }
            if (linkRef.isReferenceTextCombined()) {
                markdownWriter.append((CharSequence) linkRef.getReferenceOpeningMarker());
                if (linkRef.isOrDescendantOfType(Paragraph.class)) {
                    markdownWriter.append((CharSequence) linkRef.getReference().toMapped(SpaceMapper.toNonBreakSpace));
                } else {
                    markdownWriter.append((CharSequence) linkRef.getReference());
                }
                markdownWriter.append((CharSequence) linkRef.getReferenceClosingMarker());
                markdownWriter.append((CharSequence) linkRef.getTextOpeningMarker());
                markdownWriter.append((CharSequence) linkRef.getTextClosingMarker());
                return;
            }
            markdownWriter.append((CharSequence) linkRef.getTextOpeningMarker());
            nodeFormatterContext.renderChildren(linkRef);
            markdownWriter.append((CharSequence) linkRef.getTextClosingMarker());
            markdownWriter.append((CharSequence) linkRef.getReferenceOpeningMarker());
            markdownWriter.append((CharSequence) linkRef.getReference());
            markdownWriter.append((CharSequence) linkRef.getReferenceClosingMarker());
            return;
        }
        markdownWriter.append((CharSequence) linkRef.getChars());
    }
}
