package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.ast.HtmlCommentBlock;
import com.vladsch.flexmark.ast.HtmlInlineComment;
import com.vladsch.flexmark.ast.HtmlInnerBlockComment;
import com.vladsch.flexmark.ast.ListBlock;
import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.OrderedList;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.ParagraphContainer;
import com.vladsch.flexmark.ast.SoftLineBreak;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.BlankLine;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockQuoteLike;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.NullableDataKey;
import com.vladsch.flexmark.util.format.MarkdownParagraph;
import com.vladsch.flexmark.util.format.TrackedOffset;
import com.vladsch.flexmark.util.format.TrackedOffsetList;
import com.vladsch.flexmark.util.format.options.BlockQuoteMarker;
import com.vladsch.flexmark.util.format.options.ListSpacing;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.LineInfo;
import com.vladsch.flexmark.util.sequence.RepeatedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.builder.SequenceBuilder;
import com.vladsch.flexmark.util.sequence.mappers.SpaceMapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/FormatterUtils.class */
public class FormatterUtils {
    public static final DataKey<Integer> LIST_ITEM_NUMBER = new DataKey<>("LIST_ITEM_NUMBER", 0);
    public static final DataKey<Boolean> FIRST_LIST_ITEM_CHILD = new DataKey<>("FIRST_LIST_ITEM_CHILD", Boolean.FALSE);
    public static final Function<CharSequence, Pair<Integer, Integer>> NULL_PADDING = charSequence -> {
        return Pair.of(0, 0);
    };
    public static final DataKey<Function<CharSequence, Pair<Integer, Integer>>> LIST_ALIGN_NUMERIC = new DataKey<>("LIST_ITEM_NUMBER", NULL_PADDING);
    public static final NullableDataKey<ListSpacing> LIST_ITEM_SPACING = new NullableDataKey<>("LIST_ITEM_SPACING");

    public static String getBlockLikePrefix(BlockQuoteLike blockQuoteLike, NodeFormatterContext nodeFormatterContext, BlockQuoteMarker blockQuoteMarker, BasedSequence basedSequence) {
        String str;
        String str2;
        String obj = blockQuoteLike.getOpeningMarker().toString();
        boolean z = false;
        switch (blockQuoteMarker) {
            case AS_IS:
                if (blockQuoteLike.getFirstChild() != null) {
                    str = blockQuoteLike.getChars().baseSubSequence(blockQuoteLike.getOpeningMarker().getStartOffset(), blockQuoteLike.getFirstChild().getStartOffset()).toString();
                    break;
                } else {
                    str = obj;
                    break;
                }
            case ADD_COMPACT:
                str = obj.trim();
                break;
            case ADD_COMPACT_WITH_SPACE:
                z = true;
                str = obj.trim() + SequenceUtils.SPACE;
                break;
            case ADD_SPACED:
                str = obj.trim() + SequenceUtils.SPACE;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + blockQuoteMarker);
        }
        CharPredicate blockQuoteLikePrefixPredicate = nodeFormatterContext.getBlockQuoteLikePrefixPredicate();
        String obj2 = basedSequence.toString();
        if (z && obj2.endsWith(SequenceUtils.SPACE) && obj2.length() >= 2 && blockQuoteLikePrefixPredicate.test(obj2.charAt(obj2.length() - 2))) {
            str2 = obj2.substring(0, obj2.length() - 1) + str;
        } else {
            str2 = obj2 + str;
        }
        return str2;
    }

    public static CharSequence stripSoftLineBreak(CharSequence charSequence, CharSequence charSequence2) {
        StringBuffer stringBuffer = null;
        Matcher matcher = Pattern.compile("\\s*(?:\r\n|\r|\n)\\s*").matcher(charSequence);
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            matcher.appendReplacement(stringBuffer, charSequence2.toString());
        }
        if (stringBuffer != null) {
            matcher.appendTail(stringBuffer);
            return stringBuffer;
        }
        return charSequence;
    }

    public static String getActualAdditionalPrefix(BasedSequence basedSequence, MarkdownWriter markdownWriter) {
        return RepeatedSequence.repeatOf(SequenceUtils.SPACE, Utils.minLimit(0, basedSequence.baseColumnAtStart() - markdownWriter.getPrefix().length())).toString();
    }

    public static String getAdditionalPrefix(BasedSequence basedSequence, BasedSequence basedSequence2) {
        return RepeatedSequence.repeatOf(SequenceUtils.SPACE, Utils.minLimit(0, basedSequence2.getStartOffset() - basedSequence.getStartOffset())).toString();
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

    public static void appendWhiteSpaceBetween(MarkdownWriter markdownWriter, Node node, Node node2, boolean z, boolean z2, boolean z3) {
        if (node2 == null || node == null) {
            return;
        }
        if (z || z2) {
            appendWhiteSpaceBetween(markdownWriter, node.getChars(), node2.getChars(), z, z2, z3);
        }
    }

    public static void appendWhiteSpaceBetween(MarkdownWriter markdownWriter, BasedSequence basedSequence, BasedSequence basedSequence2, boolean z, boolean z2, boolean z3) {
        if (basedSequence2 != null && basedSequence != null) {
            if ((z || z2) && basedSequence.getEndOffset() <= basedSequence2.getStartOffset()) {
                BasedSequence baseSubSequence = basedSequence.baseSubSequence(basedSequence.getEndOffset(), basedSequence2.getStartOffset());
                if (!baseSubSequence.isEmpty() && baseSubSequence.isBlank()) {
                    if (!z) {
                        if (z3 && baseSubSequence.indexOfAny(CharPredicate.ANY_EOL) != -1) {
                            markdownWriter.append('\n');
                            return;
                        } else {
                            markdownWriter.append(' ');
                            return;
                        }
                    }
                    int options = markdownWriter.getOptions();
                    markdownWriter.setOptions(options & (LineAppendable.F_TRIM_LEADING_WHITESPACE ^ (-1)));
                    markdownWriter.append((CharSequence) baseSubSequence);
                    markdownWriter.setOptions(options);
                }
            }
        }
    }

    public static void renderList(ListBlock listBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (nodeFormatterContext.isTransformingText()) {
            nodeFormatterContext.renderChildren(listBlock);
            return;
        }
        ArrayList arrayList = new ArrayList();
        Node firstChild = listBlock.getFirstChild();
        while (true) {
            Node node = firstChild;
            if (node != null) {
                arrayList.add(node);
                firstChild = node.getNext();
            } else {
                renderList(listBlock, nodeFormatterContext, markdownWriter, arrayList);
                return;
            }
        }
    }

    public static void renderList(ListBlock listBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter, List<Node> list) {
        ListSpacing listSpacing;
        FormatterOptions formatterOptions = nodeFormatterContext.getFormatterOptions();
        if (formatterOptions.listAddBlankLineBefore && !listBlock.isOrDescendantOfType(ListItem.class)) {
            markdownWriter.blankLine();
        }
        Document document = nodeFormatterContext.getDocument();
        ListSpacing listSpacing2 = LIST_ITEM_SPACING.get(document);
        int intValue = LIST_ITEM_NUMBER.get(document).intValue();
        int startNumber = (!(listBlock instanceof OrderedList) || (formatterOptions.listRenumberItems && formatterOptions.listResetFirstItemNumber)) ? 1 : ((OrderedList) listBlock).getStartNumber();
        Function<CharSequence, Pair<Integer, Integer>> function = LIST_ALIGN_NUMERIC.get(document);
        document.set((DataKey<DataKey<Integer>>) LIST_ITEM_NUMBER, (DataKey<Integer>) Integer.valueOf(startNumber));
        ListSpacing listSpacing3 = null;
        switch (formatterOptions.listSpacing) {
            case LOOSE:
                listSpacing = ListSpacing.LOOSE;
                listSpacing3 = listSpacing;
                break;
            case TIGHT:
                listSpacing = ListSpacing.TIGHT;
                listSpacing3 = listSpacing;
                break;
            case LOOSEN:
                listSpacing3 = hasLooseItems(list) ? ListSpacing.LOOSE : ListSpacing.TIGHT;
                break;
            case TIGHTEN:
                listSpacing = hasLooseItems(list) ? ListSpacing.AS_IS : ListSpacing.TIGHT;
                listSpacing3 = listSpacing;
                break;
        }
        document.remove((DataKeyBase<?>) LIST_ALIGN_NUMERIC);
        if (!formatterOptions.listAlignNumeric.isNoChange() && (listBlock instanceof OrderedList)) {
            int i = Integer.MIN_VALUE;
            int i2 = Integer.MAX_VALUE;
            int i3 = startNumber;
            for (Node node : list) {
                if (!formatterOptions.listRemoveEmptyItems || (node.hasChildren() && node.getFirstChildAnyNot(BlankLine.class) != null)) {
                    int length = formatterOptions.listRenumberItems ? Integer.toString(i3).length() + 1 : ((ListItem) node).getOpeningMarker().length();
                    i = Math.max(i, length);
                    i2 = Math.min(i2, length);
                    i3++;
                }
            }
            if (i != i2) {
                int i4 = i;
                document.set((DataKey<DataKey<Function<CharSequence, Pair<Integer, Integer>>>>) LIST_ALIGN_NUMERIC, (DataKey<Function<CharSequence, Pair<Integer, Integer>>>) (formatterOptions.listAlignNumeric.isLeft() ? charSequence -> {
                    return Pair.of(0, Integer.valueOf(Math.min(4, Math.max(0, i4 - charSequence.length()))));
                } : charSequence2 -> {
                    return Pair.of(Integer.valueOf(Math.min(4, Math.max(0, i4 - charSequence2.length()))), 0);
                }));
            }
        }
        document.set((NullableDataKey<NullableDataKey<ListSpacing>>) LIST_ITEM_SPACING, (NullableDataKey<ListSpacing>) ((listSpacing3 == ListSpacing.LOOSE && (listSpacing2 == null || listSpacing2 == ListSpacing.LOOSE)) ? ListSpacing.LOOSE : listSpacing3));
        for (Node node2 : list) {
            if (listSpacing3 == ListSpacing.LOOSE && (listSpacing2 == null || listSpacing2 == ListSpacing.LOOSE)) {
                markdownWriter.blankLine();
            }
            nodeFormatterContext.render(node2);
        }
        document.set((NullableDataKey<NullableDataKey<ListSpacing>>) LIST_ITEM_SPACING, (NullableDataKey<ListSpacing>) listSpacing2);
        document.set((DataKey<DataKey<Integer>>) LIST_ITEM_NUMBER, (DataKey<Integer>) Integer.valueOf(intValue));
        document.set((DataKey<DataKey<Function<CharSequence, Pair<Integer, Integer>>>>) LIST_ALIGN_NUMERIC, (DataKey<Function<CharSequence, Pair<Integer, Integer>>>) function);
        if (!listBlock.isOrDescendantOfType(ListItem.class)) {
            markdownWriter.tailBlankLine();
        }
    }

    public static void renderLooseParagraph(Paragraph paragraph, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        markdownWriter.blankLine();
        renderLooseItemParagraph(paragraph, nodeFormatterContext, markdownWriter);
    }

    public static boolean isFollowedByBlankLine(Node node) {
        while (node != null) {
            if (node.getNextAnyNot(HtmlCommentBlock.class, HtmlInnerBlockComment.class, HtmlInlineComment.class) instanceof BlankLine) {
                return true;
            }
            if (node.getNextAnyNot(BlankLine.class, HtmlCommentBlock.class, HtmlInnerBlockComment.class, HtmlInlineComment.class) != null) {
                return false;
            }
            node = node.getParent();
        }
        return false;
    }

    public static boolean isNotLastItem(Node node) {
        while (node != null && !(node instanceof Document)) {
            if (node.getNextAnyNot(BlankLine.class, HtmlCommentBlock.class, HtmlInnerBlockComment.class, HtmlInlineComment.class) != null) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

    public static boolean isLastOfItem(Node node) {
        return node != null && node.getNextAnyNot(BlankLine.class, HtmlCommentBlock.class, HtmlInnerBlockComment.class, HtmlInlineComment.class) == null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void renderLooseItemParagraph(Paragraph paragraph, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        boolean z;
        renderTextBlockParagraphLines(paragraph, nodeFormatterContext, markdownWriter);
        Block parent = paragraph.getParent();
        if (parent instanceof ListItem) {
            if (nodeFormatterContext.getFormatterOptions().blankLinesInAst) {
                boolean z2 = !((ParagraphContainer) parent).isParagraphEndWrappingDisabled(paragraph);
                ListItem listItem = (ListItem) parent;
                switch (nodeFormatterContext.getFormatterOptions().listSpacing) {
                    case AS_IS:
                        z = isFollowedByBlankLine(paragraph) && isNotLastItem(parent);
                        break;
                    case LOOSE:
                        z = true;
                        break;
                    case TIGHT:
                    default:
                        z = false;
                        break;
                    case LOOSEN:
                        z = (parent.getParent() instanceof ListBlock) && ((ListBlock) parent.getParent()).isLoose() && hasLooseItems(parent.getParent().getChildren()) && !((!isFollowedByBlankLine(paragraph) || !isNotLastItem(parent)) && listItem.isOwnTight() && (!listItem.isItemParagraph(paragraph) || parent.getFirstChild() == null || parent.getFirstChild().getNext() == null));
                        break;
                    case TIGHTEN:
                        z = z2 && (!listItem.isItemParagraph(paragraph) ? !isNotLastItem(paragraph) : !(isFollowedByBlankLine(paragraph) && isNotLastItem(paragraph)));
                        break;
                }
                if (z) {
                    markdownWriter.tailBlankLine();
                    return;
                }
                return;
            }
            if (nodeFormatterContext.getFormatterOptions().listSpacing != ListSpacing.TIGHTEN || parent.getNext() != null) {
                markdownWriter.tailBlankLine();
                return;
            }
            return;
        }
        markdownWriter.tailBlankLine();
    }

    static boolean hasLooseItems(Iterable<Node> iterable) {
        for (Node node : iterable) {
            if ((node instanceof ListItem) && !((ListItem) node).isOwnTight() && node.getNext() != null) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:47:0x01d1. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x02e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void renderListItem(com.vladsch.flexmark.ast.ListItem r8, com.vladsch.flexmark.formatter.NodeFormatterContext r9, com.vladsch.flexmark.formatter.MarkdownWriter r10, com.vladsch.flexmark.parser.ListOptions r11, com.vladsch.flexmark.util.sequence.BasedSequence r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 1310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.formatter.FormatterUtils.renderListItem(com.vladsch.flexmark.ast.ListItem, com.vladsch.flexmark.formatter.NodeFormatterContext, com.vladsch.flexmark.formatter.MarkdownWriter, com.vladsch.flexmark.parser.ListOptions, com.vladsch.flexmark.util.sequence.BasedSequence, boolean):void");
    }

    public static void renderTextBlockParagraphLines(Node node, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        int startOffset;
        int endOffset;
        if (nodeFormatterContext.isTransformingText()) {
            nodeFormatterContext.translatingSpan((nodeFormatterContext2, markdownWriter2) -> {
                nodeFormatterContext2.renderChildren(node);
            });
            markdownWriter.line();
            return;
        }
        FormatterOptions formatterOptions = nodeFormatterContext.getFormatterOptions();
        if (formatterOptions.rightMargin > 0) {
            MutableDataHolder mutableDataHolder = nodeFormatterContext.getOptions().toMutable().set((DataKey<DataKey<Boolean>>) Formatter.KEEP_SOFT_LINE_BREAKS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Formatter.KEEP_HARD_LINE_BREAKS, (DataKey<Boolean>) Boolean.TRUE);
            SequenceBuilder builder = nodeFormatterContext.getDocument().getChars().getBuilder();
            NodeFormatterContext subContext = nodeFormatterContext.getSubContext(mutableDataHolder, builder.getBuilder());
            MarkdownWriter markdown = subContext.getMarkdown();
            markdown.removeOptions(LineAppendable.F_TRIM_TRAILING_WHITESPACE);
            subContext.renderChildren(node);
            BasedSequence trimEOL = node.getChars().trimEOL();
            BasedSequence trimmedEnd = node.getChars().trimmedEnd();
            if (trimmedEnd.isNotEmpty() && !markdown.endsWithEOL()) {
                markdown.append((CharSequence) trimmedEnd);
            }
            markdown.line();
            markdown.appendToSilently(builder, 0, -1);
            BasedSequence sequence = builder.toSequence();
            BasedSequence sequence2 = builder.toSequence(nodeFormatterContext.getTrackedSequence());
            boolean z = sequence != sequence2;
            TrackedOffsetList trackedOffsets = nodeFormatterContext.getTrackedOffsets();
            if (z) {
                BasedSequence trimEnd = sequence2.trimEnd();
                startOffset = trimEnd.getStartOffset();
                endOffset = trimEnd.getEndOffset() + (trimEOL.countTrailingWhitespace() - trimEnd.countTrailingWhitespace()) + 1;
            } else {
                startOffset = trimEOL.getStartOffset();
                endOffset = trimEOL.getEndOffset();
            }
            TrackedOffsetList trackedOffsets2 = trackedOffsets.getTrackedOffsets(startOffset, endOffset);
            MarkdownParagraph markdownParagraph = new MarkdownParagraph(sequence, sequence2, formatterOptions.charWidthProvider);
            markdownParagraph.setOptions(nodeFormatterContext.getOptions());
            markdownParagraph.setWidth(formatterOptions.rightMargin - markdownWriter.getPrefix().length());
            markdownParagraph.setKeepSoftBreaks(false);
            markdownParagraph.setKeepHardBreaks(formatterOptions.keepHardLineBreaks);
            markdownParagraph.setRestoreTrackedSpaces(nodeFormatterContext.isRestoreTrackedSpaces());
            markdownParagraph.setFirstIndent(BasedSequence.NULL);
            markdownParagraph.setIndent(BasedSequence.NULL);
            markdownParagraph.setFirstWidthOffset((-markdownWriter.column()) + markdownWriter.getAfterEolPrefixDelta());
            if (formatterOptions.applySpecialLeadInHandlers) {
                markdownParagraph.setLeadInHandlers(Parser.SPECIAL_LEAD_IN_HANDLERS.get(nodeFormatterContext.getDocument()));
            }
            Iterator<TrackedOffset> it = trackedOffsets2.iterator();
            while (it.hasNext()) {
                markdownParagraph.addTrackedOffset(it.next());
            }
            BasedSequence mapped = markdownParagraph.wrapText().toMapped(SpaceMapper.fromNonBreakSpace);
            int lineCount = markdownWriter.getLineCount();
            int column = markdownWriter.column();
            markdownWriter.pushOptions().preserveSpaces().append((CharSequence) mapped).line().popOptions();
            if (!trackedOffsets2.isEmpty()) {
                LineInfo lineInfo = markdownWriter.getLineInfo(lineCount);
                Iterator<TrackedOffset> it2 = trackedOffsets2.iterator();
                while (it2.hasNext()) {
                    TrackedOffset next = it2.next();
                    if (next.isResolved()) {
                        int index = next.getIndex();
                        LineInfo lineInfo2 = markdownWriter.getLineInfo(lineCount + mapped.lineColumnAtIndex(index).getFirst().intValue());
                        next.setIndex(index + column + (lineInfo.sumLength - lineInfo.length) + (lineInfo2.sumPrefixLength - lineInfo.sumPrefixLength) + lineInfo.prefixLength);
                    }
                }
                return;
            }
            return;
        }
        nodeFormatterContext.renderChildren(node);
        markdownWriter.line();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void renderBlockQuoteLike(BlockQuoteLike blockQuoteLike, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        FormatterOptions formatterOptions = nodeFormatterContext.getFormatterOptions();
        String blockLikePrefix = getBlockLikePrefix(blockQuoteLike, nodeFormatterContext, formatterOptions.blockQuoteMarkers, markdownWriter.getPrefix());
        markdownWriter.pushPrefix();
        if (!FIRST_LIST_ITEM_CHILD.get(nodeFormatterContext.getDocument()).booleanValue()) {
            if (formatterOptions.blockQuoteBlankLines) {
                markdownWriter.blankLine();
            }
            markdownWriter.setPrefix((CharSequence) blockLikePrefix, false);
        } else {
            markdownWriter.pushOptions().removeOptions(LineAppendable.F_WHITESPACE_REMOVAL).append((CharSequence) getBlockLikePrefix(blockQuoteLike, nodeFormatterContext, formatterOptions.blockQuoteMarkers, BasedSequence.NULL)).popOptions();
            markdownWriter.setPrefix((CharSequence) blockLikePrefix, true);
        }
        int lineCountWithPending = markdownWriter.getLineCountWithPending();
        nodeFormatterContext.renderChildren((Node) blockQuoteLike);
        markdownWriter.popPrefix();
        if (formatterOptions.blockQuoteBlankLines && lineCountWithPending < markdownWriter.getLineCountWithPending() && !FIRST_LIST_ITEM_CHILD.get(nodeFormatterContext.getDocument()).booleanValue()) {
            markdownWriter.tailBlankLine();
        }
    }
}
