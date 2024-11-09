package com.vladsch.flexmark.parser.core;

import com.vladsch.flexmark.ast.BulletList;
import com.vladsch.flexmark.ast.ListBlock;
import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.OrderedList;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.parser.block.AbstractBlockParser;
import com.vladsch.flexmark.parser.block.AbstractBlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockContinue;
import com.vladsch.flexmark.parser.block.BlockParser;
import com.vladsch.flexmark.parser.block.BlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockStart;
import com.vladsch.flexmark.parser.block.CustomBlockParserFactory;
import com.vladsch.flexmark.parser.block.MatchedBlockParser;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.core.BlockQuoteParser;
import com.vladsch.flexmark.parser.core.FencedCodeBlockParser;
import com.vladsch.flexmark.parser.core.HeadingParser;
import com.vladsch.flexmark.parser.core.HtmlBlockParser;
import com.vladsch.flexmark.parser.core.IndentedCodeBlockParser;
import com.vladsch.flexmark.parser.core.ThematicBreakParser;
import com.vladsch.flexmark.util.ast.BlankLine;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.SharedDataKeys;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInCharsHandler;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/ListBlockParser.class */
public class ListBlockParser extends AbstractBlockParser {
    private final ListBlock myBlock;
    private final ListOptions myOptions;
    private final ListData myListData;
    ListItemParser myLastChild;
    BasedSequence myItemHandledLine = null;
    boolean myItemHandledNewListLine;
    boolean myItemHandledNewItemLine;
    boolean myItemHandledSkipActiveLine;

    public ListBlockParser(ListOptions listOptions, ListData listData, ListItemParser listItemParser) {
        this.myLastChild = null;
        this.myOptions = listOptions;
        this.myListData = listData;
        this.myBlock = listData.listBlock;
        this.myBlock.setTight(true);
        this.myLastChild = listItemParser;
        this.myItemHandledNewListLine = false;
        this.myItemHandledNewItemLine = false;
        this.myItemHandledSkipActiveLine = false;
    }

    BasedSequence getItemHandledLine() {
        return this.myItemHandledLine;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setItemHandledLine(BasedSequence basedSequence) {
        this.myItemHandledLine = basedSequence;
        this.myItemHandledNewListLine = false;
        this.myItemHandledNewItemLine = false;
        this.myItemHandledSkipActiveLine = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setItemHandledNewListLine(BasedSequence basedSequence) {
        this.myItemHandledLine = basedSequence;
        this.myItemHandledNewListLine = true;
        this.myItemHandledNewItemLine = false;
        this.myItemHandledSkipActiveLine = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setItemHandledNewItemLine(BasedSequence basedSequence) {
        this.myItemHandledLine = basedSequence;
        this.myItemHandledNewListLine = false;
        this.myItemHandledNewItemLine = true;
        this.myItemHandledSkipActiveLine = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setItemHandledLineSkipActive(BasedSequence basedSequence) {
        this.myItemHandledLine = basedSequence;
        this.myItemHandledNewListLine = false;
        this.myItemHandledNewItemLine = false;
        this.myItemHandledSkipActiveLine = true;
    }

    public ListItemParser getLastChild() {
        return this.myLastChild;
    }

    public void setLastChild(ListItemParser listItemParser) {
        this.myLastChild = listItemParser;
    }

    public ListOptions getOptions() {
        return this.myOptions;
    }

    public ListData getListData() {
        return this.myListData;
    }

    int getContentIndent() {
        return this.myListData.markerIndent + this.myListData.listMarker.length() + this.myListData.contentOffset;
    }

    int getLastContentIndent() {
        return this.myLastChild.getContentIndent();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canContain(ParserState parserState, BlockParser blockParser, Block block) {
        return block instanceof ListItem;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public ListBlock getBlock() {
        return this.myBlock;
    }

    private void setTight(boolean z) {
        this.myBlock.setTight(z);
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        finalizeListTight(parserState);
        if (Parser.BLANK_LINES_IN_AST.get(parserState.getProperties()).booleanValue()) {
            Node firstChildAnyNot = getBlock().getFirstChildAnyNot(BlankLine.class);
            while (true) {
                Node node = firstChildAnyNot;
                if (!(node instanceof ListItem)) {
                    break;
                }
                node.moveTrailingBlankLines();
                firstChildAnyNot = node.getNextAnyNot(BlankLine.class);
            }
        }
        this.myBlock.setCharsFromContentOnly();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean breakOutOnDoubleBlankLine() {
        return this.myOptions.isEndOnDoubleBlank();
    }

    private static boolean hasNonItemChildren(ListItem listItem) {
        if (listItem.hasChildren()) {
            int i = 0;
            ReversiblePeekingIterator<Node> it = listItem.getChildren().iterator();
            while (it.hasNext()) {
                if (!(it.next() instanceof ListBlock)) {
                    i++;
                    if (i >= 2) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private void finalizeListTight(ParserState parserState) {
        boolean z = true;
        boolean z2 = false;
        boolean z3 = false;
        for (Node firstChild = getBlock().getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
            boolean z4 = false;
            boolean z5 = false;
            boolean z6 = false;
            boolean z7 = false;
            boolean z8 = false;
            if (firstChild instanceof ListItem) {
                if (((ListItem) firstChild).isHadBlankAfterItemParagraph() && (firstChild.getNext() != null || (firstChild.getFirstChild() != null && firstChild.getFirstChild().getNext() != null))) {
                    z4 = true;
                }
                if (((ListItem) firstChild).isContainsBlankLine()) {
                    z5 = true;
                }
                if (parserState.endsWithBlankLine(firstChild) && firstChild.getNext() != null) {
                    z6 = true;
                }
                if (hasNonItemChildren((ListItem) firstChild)) {
                    z7 = true;
                }
                boolean z9 = (z6 && this.myOptions.isLooseWhenHasTrailingBlankLine()) || (z4 && this.myOptions.isLooseWhenBlankLineFollowsItemParagraph()) || ((z5 && this.myOptions.isLooseWhenContainsBlankLine()) || ((z7 && this.myOptions.isLooseWhenHasNonListChildren()) || (((z6 && firstChild.getPrevious() == null) || z2) && (this.myOptions.isLooseWhenPrevHasTrailingBlankLine() || (this.myOptions.isLooseWhenLastItemPrevHasTrailingBlankLine() && firstChild.getNext() == null)))));
                z8 = z9;
                if (z9) {
                    ((ListItem) firstChild).setLoose(true);
                    z = false;
                }
            }
            Node firstChild2 = firstChild.getFirstChild();
            while (true) {
                Node node = firstChild2;
                if (node == null) {
                    break;
                }
                if (parserState.endsWithBlankLine(node) && (firstChild.getNext() != null || node.getNext() != null)) {
                    if (node == firstChild.getLastChild()) {
                        z6 = true;
                    }
                    if (!z8) {
                        if (this.myOptions.isLooseWhenHasTrailingBlankLine()) {
                            z = false;
                        }
                        if (z6 && firstChild.getPrevious() == null && this.myOptions.isLooseWhenPrevHasTrailingBlankLine()) {
                            z = false;
                            z8 = true;
                            ((ListItem) firstChild).setLoose(true);
                        }
                    }
                }
                if (node instanceof ListBlock) {
                    z3 = true;
                    if (!z8 && this.myOptions.isLooseWhenHasLooseSubItem()) {
                        ReversiblePeekingIterator<Node> childIterator = node.getChildIterator();
                        while (true) {
                            if (!childIterator.hasNext()) {
                                break;
                            }
                            if (!((ListItem) childIterator.next()).isTight()) {
                                z8 = true;
                                z = false;
                                ((ListItem) firstChild).setLoose(true);
                                break;
                            }
                        }
                    }
                }
                if (!this.myOptions.isLooseWhenHasLooseSubItem() ? !(z || (!z3 && this.myOptions.isAutoLooseOneLevelLists())) : !(!z8 || (!z3 && this.myOptions.isAutoLooseOneLevelLists()))) {
                    break;
                } else {
                    firstChild2 = node.getNext();
                }
            }
            if (firstChild instanceof ListItem) {
                z2 = z6;
            }
        }
        if (this.myOptions.isAutoLoose() && this.myOptions.isAutoLooseOneLevelLists()) {
            if (!z3 && getBlock().getAncestorOfType(ListBlock.class) == null && !z) {
                setTight(false);
                return;
            }
            return;
        }
        if (this.myOptions.isAutoLoose() && !z) {
            setTight(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ListData parseListMarker(ListOptions listOptions, int i, ParserState parserState) {
        char midCharAt;
        Parsing parsing = parserState.getParsing();
        BasedSequence line = parserState.getLine();
        int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
        int column = parserState.getColumn() + parserState.getIndent();
        int indent = parserState.getIndent();
        BasedSequence subSequence = line.subSequence(nextNonSpaceIndex, line.length());
        Matcher matcher = parsing.LIST_ITEM_MARKER.matcher(subSequence);
        if (!matcher.find()) {
            return null;
        }
        ListBlock createListBlock = createListBlock(matcher);
        int end = matcher.end() - matcher.start();
        boolean z = !"+-*".contains(matcher.group());
        int i2 = nextNonSpaceIndex + end;
        int i3 = column + end;
        int i4 = 0;
        boolean z2 = false;
        int i5 = i2;
        int i6 = i2;
        while (true) {
            if (i6 >= line.length()) {
                break;
            }
            char charAt = line.charAt(i6);
            if (charAt == '\t') {
                i4 += Parsing.columnsToNextTabStop(i3 + i4);
            } else if (charAt == ' ') {
                i4++;
            } else {
                z2 = true;
                break;
            }
            i5++;
            i6++;
        }
        BasedSequence basedSequence = BasedSequence.NULL;
        int i7 = i4;
        if (!z2 || i4 > i) {
            i4 = 1;
            i7 = 1;
        } else if (!z || listOptions.isNumberedItemMarkerSuffixed()) {
            for (String str : listOptions.getItemMarkerSuffixes()) {
                int length = str.length();
                if (length > 0 && line.matchChars(str, i5) && (!listOptions.isItemMarkerSpace() || (midCharAt = line.midCharAt(i5 + length)) == ' ' || midCharAt == '\t')) {
                    int i8 = i5;
                    basedSequence = line.subSequence(i8, i8 + length);
                    i4 += length;
                    int i9 = i5 + length;
                    int i10 = i3 + length;
                    z2 = false;
                    int i11 = i9;
                    while (true) {
                        if (i11 >= line.length()) {
                            break;
                        }
                        char charAt2 = line.charAt(i11);
                        if (charAt2 == '\t') {
                            i4 += Parsing.columnsToNextTabStop(i10 + i4);
                        } else if (charAt2 == ' ') {
                            i4++;
                        } else {
                            z2 = true;
                            break;
                        }
                        i11++;
                    }
                    if (!z2 || i4 - i4 > i) {
                        i4++;
                    }
                }
            }
        }
        return new ListData(createListBlock, !z2, nextNonSpaceIndex, column, indent, i4, subSequence.subSequence(matcher.start(), matcher.end()), z, basedSequence, i7);
    }

    private static ListBlock createListBlock(Matcher matcher) {
        String group = matcher.group(1);
        if (group != null) {
            BulletList bulletList = new BulletList();
            bulletList.setOpeningMarker(group.charAt(0));
            return bulletList;
        }
        String group2 = matcher.group(2);
        String group3 = matcher.group(3);
        OrderedList orderedList = new OrderedList();
        orderedList.setStartNumber(Integer.parseInt(group2));
        orderedList.setDelimiter(group3.charAt(0));
        return orderedList;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        return BlockContinue.atIndex(parserState.getIndex());
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/ListBlockParser$Factory.class */
    public static class Factory implements CustomBlockParserFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return new HashSet(Arrays.asList(BlockQuoteParser.Factory.class, HeadingParser.Factory.class, FencedCodeBlockParser.Factory.class, HtmlBlockParser.Factory.class, ThematicBreakParser.Factory.class));
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            HashSet hashSet = new HashSet();
            hashSet.add(IndentedCodeBlockParser.Factory.class);
            return hashSet;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }

        @Override // com.vladsch.flexmark.parser.block.CustomBlockParserFactory, java.util.function.Function
        public BlockParserFactory apply(DataHolder dataHolder) {
            return new BlockFactory(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.CustomBlockParserFactory
        public SpecialLeadInHandler getLeadInHandler(DataHolder dataHolder) {
            return ListItemLeadInHandler.create(Parser.LISTS_ITEM_PREFIX_CHARS.get(dataHolder), Parser.LISTS_ORDERED_ITEM_DOT_ONLY.get(dataHolder).booleanValue());
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/ListBlockParser$ListItemLeadInHandler.class */
    static class ListItemLeadInHandler extends SpecialLeadInCharsHandler {
        static final CharPredicate ORDERED_DELIM_DOT = CharPredicate.anyOf('.');
        static final CharPredicate ORDERED_DELIM_DOT_PARENS = CharPredicate.anyOf(".)");
        static final SpecialLeadInHandler ORDERED_DELIM_DOT_HANDLER = new ListItemLeadInHandler(Parser.LISTS_ITEM_PREFIX_CHARS.getDefaultValue(), true);
        static final SpecialLeadInHandler ORDERED_DELIM_DOT_PARENS_HANDLER = new ListItemLeadInHandler(Parser.LISTS_ITEM_PREFIX_CHARS.getDefaultValue(), false);
        final CharPredicate orderedDelims;

        static SpecialLeadInHandler create(CharSequence charSequence, boolean z) {
            return SequenceUtils.equals(Parser.LISTS_ITEM_PREFIX_CHARS.getDefaultValue(), charSequence) ? z ? ORDERED_DELIM_DOT_HANDLER : ORDERED_DELIM_DOT_PARENS_HANDLER : new ListItemLeadInHandler(charSequence, z);
        }

        public ListItemLeadInHandler(CharSequence charSequence, boolean z) {
            super(CharPredicate.anyOf(charSequence));
            this.orderedDelims = z ? ORDERED_DELIM_DOT : ORDERED_DELIM_DOT_PARENS;
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInCharsHandler, com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler
        public boolean escape(BasedSequence basedSequence, DataHolder dataHolder, Consumer<CharSequence> consumer) {
            int indexOfAnyNot;
            if (super.escape(basedSequence, dataHolder, consumer)) {
                return true;
            }
            if (SharedDataKeys.ESCAPE_NUMBERED_LEAD_IN.get(dataHolder).booleanValue() && (indexOfAnyNot = basedSequence.indexOfAnyNot(CharPredicate.DECIMAL_DIGITS)) > 0 && indexOfAnyNot + 1 == basedSequence.length() && this.orderedDelims.test(basedSequence.charAt(indexOfAnyNot))) {
                consumer.accept(basedSequence.subSequence(0, indexOfAnyNot));
                consumer.accept("\\");
                consumer.accept(basedSequence.subSequence(indexOfAnyNot));
                return true;
            }
            return false;
        }

        @Override // com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInCharsHandler, com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler
        public boolean unEscape(BasedSequence basedSequence, DataHolder dataHolder, Consumer<CharSequence> consumer) {
            if (super.unEscape(basedSequence, dataHolder, consumer)) {
                return true;
            }
            int indexOfAnyNot = basedSequence.indexOfAnyNot(CharPredicate.DECIMAL_DIGITS);
            if (indexOfAnyNot > 0 && indexOfAnyNot + 2 == basedSequence.length() && basedSequence.charAt(indexOfAnyNot) == '\\' && this.orderedDelims.test(basedSequence.charAt(indexOfAnyNot + 1))) {
                consumer.accept(basedSequence.subSequence(0, indexOfAnyNot));
                consumer.accept(basedSequence.subSequence(indexOfAnyNot + 1));
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/ListBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private final ListOptions myOptions;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !ListBlockParser.class.desiredAssertionStatus();
        }

        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.myOptions = ListOptions.get(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            BlockParser blockParser = matchedBlockParser.getBlockParser();
            ParserEmulationProfile parserEmulationProfile = this.myOptions.getParserEmulationProfile().family;
            int newItemCodeIndent = this.myOptions.getNewItemCodeIndent();
            if (blockParser instanceof ListBlockParser) {
                ListBlockParser listBlockParser = (ListBlockParser) blockParser;
                if (parserState.getLine() == listBlockParser.myItemHandledLine) {
                    if (listBlockParser.myItemHandledNewListLine) {
                        ListData parseListMarker = ListBlockParser.parseListMarker(this.myOptions, newItemCodeIndent, parserState);
                        ListItemParser listItemParser = new ListItemParser(this.myOptions, parserState.getParsing(), parseListMarker);
                        if ($assertionsDisabled || parseListMarker != null) {
                            return BlockStart.of(new ListBlockParser(this.myOptions, parseListMarker, listItemParser), listItemParser).atColumn(parseListMarker.markerColumn + parseListMarker.listMarker.length() + parseListMarker.contentOffset);
                        }
                        throw new AssertionError();
                    }
                    if (listBlockParser.myItemHandledNewItemLine) {
                        ListData parseListMarker2 = ListBlockParser.parseListMarker(this.myOptions, newItemCodeIndent, parserState);
                        ListItemParser listItemParser2 = new ListItemParser(this.myOptions, parserState.getParsing(), parseListMarker2);
                        if (!$assertionsDisabled && parseListMarker2 == null) {
                            throw new AssertionError();
                        }
                        int length = parseListMarker2.markerColumn + parseListMarker2.listMarker.length() + parseListMarker2.contentOffset;
                        listBlockParser.myLastChild = listItemParser2;
                        return BlockStart.of(listItemParser2).atColumn(length);
                    }
                    listBlockParser.myItemHandledLine = null;
                    return BlockStart.none();
                }
                return BlockStart.none();
            }
            ListBlock listBlock = (ListBlock) blockParser.getBlock().getAncestorOfType(ListBlock.class);
            if (listBlock != null) {
                ListBlockParser listBlockParser2 = (ListBlockParser) parserState.getActiveBlockParser(listBlock);
                if (listBlockParser2.myItemHandledLine == parserState.getLine() && listBlockParser2.myItemHandledSkipActiveLine) {
                    listBlockParser2.myItemHandledLine = null;
                    return BlockStart.none();
                }
            }
            if (parserEmulationProfile == ParserEmulationProfile.COMMONMARK) {
                if (parserState.getIndent() >= this.myOptions.getCodeIndent()) {
                    return BlockStart.none();
                }
            } else if (parserEmulationProfile == ParserEmulationProfile.FIXED_INDENT) {
                if (parserState.getIndent() >= this.myOptions.getItemIndent()) {
                    return BlockStart.none();
                }
            } else if (parserEmulationProfile == ParserEmulationProfile.KRAMDOWN) {
                if (parserState.getIndent() >= this.myOptions.getItemIndent()) {
                    return BlockStart.none();
                }
            } else if (parserEmulationProfile == ParserEmulationProfile.MARKDOWN && parserState.getIndent() >= this.myOptions.getItemIndent()) {
                return BlockStart.none();
            }
            ListData parseListMarker3 = ListBlockParser.parseListMarker(this.myOptions, newItemCodeIndent, parserState);
            if (parseListMarker3 != null) {
                int length2 = parseListMarker3.markerColumn + parseListMarker3.listMarker.length() + parseListMarker3.contentOffset;
                boolean isParagraphParser = blockParser.isParagraphParser();
                boolean z = isParagraphParser && (blockParser.getBlock().getParent() instanceof ListItem) && blockParser.getBlock() == blockParser.getBlock().getParent().getFirstChild();
                if (!isParagraphParser || this.myOptions.canInterrupt(parseListMarker3.listBlock, parseListMarker3.isEmpty, z)) {
                    ListItemParser listItemParser3 = new ListItemParser(this.myOptions, parserState.getParsing(), parseListMarker3);
                    return BlockStart.of(new ListBlockParser(this.myOptions, parseListMarker3, listItemParser3), listItemParser3).atColumn(length2);
                }
                return BlockStart.none();
            }
            return BlockStart.none();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/core/ListBlockParser$ListData.class */
    public static class ListData {
        final ListBlock listBlock;
        final boolean isEmpty;
        final int markerIndex;
        final int markerColumn;
        final int markerIndent;
        final int contentOffset;
        final BasedSequence listMarker;
        final boolean isNumberedList;
        final BasedSequence markerSuffix;
        final int markerSuffixOffset;

        ListData(ListBlock listBlock, boolean z, int i, int i2, int i3, int i4, BasedSequence basedSequence, boolean z2, BasedSequence basedSequence2, int i5) {
            this.listBlock = listBlock;
            this.isEmpty = z;
            this.markerIndex = i;
            this.markerColumn = i2;
            this.markerIndent = i3;
            this.contentOffset = i4;
            this.listMarker = basedSequence;
            this.isNumberedList = z2;
            this.markerSuffix = basedSequence2;
            this.markerSuffixOffset = i5;
        }
    }
}
