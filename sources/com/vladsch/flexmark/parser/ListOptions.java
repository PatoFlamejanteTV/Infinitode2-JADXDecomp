package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.ast.BulletList;
import com.vladsch.flexmark.ast.ListBlock;
import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.OrderedList;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;
import java.util.Arrays;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/ListOptions.class */
public class ListOptions implements MutableDataSetter {
    protected ParserEmulationProfile myParserEmulationProfile;
    protected ItemInterrupt itemInterrupt;
    protected boolean autoLoose;
    protected boolean autoLooseOneLevelLists;
    protected boolean delimiterMismatchToNewList;
    protected boolean endOnDoubleBlank;
    protected boolean itemMarkerSpace;
    protected boolean itemTypeMismatchToNewList;
    protected boolean itemTypeMismatchToSubList;
    protected boolean looseWhenPrevHasTrailingBlankLine;
    protected boolean looseWhenLastItemPrevHasTrailingBlankLine;
    protected boolean looseWhenHasNonListChildren;
    protected boolean looseWhenBlankLineFollowsItemParagraph;
    protected boolean looseWhenHasLooseSubItem;
    protected boolean looseWhenHasTrailingBlankLine;
    protected boolean looseWhenContainsBlankLine;
    protected boolean numberedItemMarkerSuffixed;
    protected boolean orderedItemDotOnly;
    protected boolean orderedListManualStart;
    protected boolean itemContentAfterSuffix;
    protected String itemPrefixChars;
    protected int codeIndent;
    protected int itemIndent;
    protected int newItemCodeIndent;
    protected String[] itemMarkerSuffixes;

    public ListOptions() {
        this((DataHolder) null);
    }

    private ListOptions(DataHolder dataHolder) {
        this.myParserEmulationProfile = Parser.PARSER_EMULATION_PROFILE.get(dataHolder);
        this.itemInterrupt = new ItemInterrupt(dataHolder);
        this.autoLoose = Parser.LISTS_AUTO_LOOSE.get(dataHolder).booleanValue();
        this.autoLooseOneLevelLists = Parser.LISTS_AUTO_LOOSE_ONE_LEVEL_LISTS.get(dataHolder).booleanValue();
        this.delimiterMismatchToNewList = Parser.LISTS_DELIMITER_MISMATCH_TO_NEW_LIST.get(dataHolder).booleanValue();
        this.endOnDoubleBlank = Parser.LISTS_END_ON_DOUBLE_BLANK.get(dataHolder).booleanValue();
        this.itemMarkerSpace = Parser.LISTS_ITEM_MARKER_SPACE.get(dataHolder).booleanValue();
        this.itemTypeMismatchToNewList = Parser.LISTS_ITEM_TYPE_MISMATCH_TO_NEW_LIST.get(dataHolder).booleanValue();
        this.itemTypeMismatchToSubList = Parser.LISTS_ITEM_TYPE_MISMATCH_TO_SUB_LIST.get(dataHolder).booleanValue();
        this.looseWhenPrevHasTrailingBlankLine = Parser.LISTS_LOOSE_WHEN_PREV_HAS_TRAILING_BLANK_LINE.get(dataHolder).booleanValue();
        this.looseWhenLastItemPrevHasTrailingBlankLine = Parser.LISTS_LOOSE_WHEN_LAST_ITEM_PREV_HAS_TRAILING_BLANK_LINE.get(dataHolder).booleanValue();
        this.looseWhenHasNonListChildren = Parser.LISTS_LOOSE_WHEN_HAS_NON_LIST_CHILDREN.get(dataHolder).booleanValue();
        this.looseWhenBlankLineFollowsItemParagraph = Parser.LISTS_LOOSE_WHEN_BLANK_LINE_FOLLOWS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
        this.looseWhenHasLooseSubItem = Parser.LISTS_LOOSE_WHEN_HAS_LOOSE_SUB_ITEM.get(dataHolder).booleanValue();
        this.looseWhenHasTrailingBlankLine = Parser.LISTS_LOOSE_WHEN_HAS_TRAILING_BLANK_LINE.get(dataHolder).booleanValue();
        this.looseWhenContainsBlankLine = Parser.LISTS_LOOSE_WHEN_CONTAINS_BLANK_LINE.get(dataHolder).booleanValue();
        this.numberedItemMarkerSuffixed = Parser.LISTS_NUMBERED_ITEM_MARKER_SUFFIXED.get(dataHolder).booleanValue();
        this.orderedItemDotOnly = Parser.LISTS_ORDERED_ITEM_DOT_ONLY.get(dataHolder).booleanValue();
        this.orderedListManualStart = Parser.LISTS_ORDERED_LIST_MANUAL_START.get(dataHolder).booleanValue();
        this.itemContentAfterSuffix = Parser.LISTS_ITEM_CONTENT_AFTER_SUFFIX.get(dataHolder).booleanValue();
        this.itemPrefixChars = Parser.LISTS_ITEM_PREFIX_CHARS.get(dataHolder);
        this.codeIndent = Parser.LISTS_CODE_INDENT.get(dataHolder).intValue();
        this.itemIndent = Parser.LISTS_ITEM_INDENT.get(dataHolder).intValue();
        this.newItemCodeIndent = Parser.LISTS_NEW_ITEM_CODE_INDENT.get(dataHolder).intValue();
        this.itemMarkerSuffixes = Parser.LISTS_ITEM_MARKER_SUFFIXES.get(dataHolder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListOptions(ListOptions listOptions) {
        this.myParserEmulationProfile = listOptions.getParserEmulationProfile();
        this.itemInterrupt = new ItemInterrupt(listOptions.getItemInterrupt());
        this.autoLoose = listOptions.isAutoLoose();
        this.autoLooseOneLevelLists = listOptions.isAutoLooseOneLevelLists();
        this.delimiterMismatchToNewList = listOptions.isDelimiterMismatchToNewList();
        this.endOnDoubleBlank = listOptions.isEndOnDoubleBlank();
        this.itemMarkerSpace = listOptions.isItemMarkerSpace();
        this.itemTypeMismatchToNewList = listOptions.isItemTypeMismatchToNewList();
        this.itemTypeMismatchToSubList = listOptions.isItemTypeMismatchToSubList();
        this.looseWhenPrevHasTrailingBlankLine = listOptions.isLooseWhenPrevHasTrailingBlankLine();
        this.looseWhenLastItemPrevHasTrailingBlankLine = listOptions.isLooseWhenLastItemPrevHasTrailingBlankLine();
        this.looseWhenHasNonListChildren = listOptions.isLooseWhenHasNonListChildren();
        this.looseWhenBlankLineFollowsItemParagraph = listOptions.isLooseWhenBlankLineFollowsItemParagraph();
        this.looseWhenHasLooseSubItem = listOptions.isLooseWhenHasLooseSubItem();
        this.looseWhenHasTrailingBlankLine = listOptions.isLooseWhenHasTrailingBlankLine();
        this.looseWhenContainsBlankLine = listOptions.isLooseWhenContainsBlankLine();
        this.numberedItemMarkerSuffixed = listOptions.isNumberedItemMarkerSuffixed();
        this.orderedItemDotOnly = listOptions.isOrderedItemDotOnly();
        this.orderedListManualStart = listOptions.isOrderedListManualStart();
        this.itemContentAfterSuffix = listOptions.isItemContentAfterSuffix();
        this.itemPrefixChars = listOptions.getItemPrefixChars();
        this.codeIndent = listOptions.getCodeIndent();
        this.itemIndent = listOptions.getItemIndent();
        this.newItemCodeIndent = listOptions.getNewItemCodeIndent();
        this.itemMarkerSuffixes = listOptions.getItemMarkerSuffixes();
    }

    @Deprecated
    public static ListOptions getFrom(DataHolder dataHolder) {
        return get(dataHolder);
    }

    public static ListOptions get(DataHolder dataHolder) {
        return new ListOptions(dataHolder);
    }

    public boolean isTightListItem(ListItem listItem) {
        if (listItem.isLoose()) {
            return false;
        }
        boolean isAutoLoose = isAutoLoose();
        if (isAutoLoose && isAutoLooseOneLevelLists()) {
            boolean z = listItem.getAncestorOfType(ListItem.class) == null && listItem.getChildOfType(ListBlock.class) == null;
            if (listItem.getFirstChild() == null) {
                return true;
            }
            if (z || !listItem.isTight()) {
                return z && listItem.isInTightList();
            }
            return true;
        }
        if (listItem.getFirstChild() == null) {
            return true;
        }
        if (isAutoLoose || !listItem.isTight()) {
            return isAutoLoose && listItem.isInTightList();
        }
        return true;
    }

    public boolean isInTightListItem(Paragraph paragraph) {
        Block parent = paragraph.getParent();
        if (!(parent instanceof ListItem)) {
            return false;
        }
        ListItem listItem = (ListItem) parent;
        if (!listItem.isItemParagraph(paragraph)) {
            return false;
        }
        boolean isAutoLoose = isAutoLoose();
        if (isAutoLoose && isAutoLooseOneLevelLists()) {
            return isTightListItem(listItem);
        }
        if (isAutoLoose || !listItem.isParagraphInTightListItem(paragraph)) {
            return isAutoLoose && listItem.isInTightList();
        }
        return true;
    }

    public boolean canInterrupt(ListBlock listBlock, boolean z, boolean z2) {
        boolean z3 = listBlock instanceof OrderedList;
        return getItemInterrupt().canInterrupt(z3, z3 && (!isOrderedListManualStart() || ((OrderedList) listBlock).getStartNumber() == 1), z, z2);
    }

    public boolean canStartSubList(ListBlock listBlock, boolean z) {
        boolean z2 = listBlock instanceof OrderedList;
        return getItemInterrupt().canStartSubList(z2, z2 && (!isOrderedListManualStart() || ((OrderedList) listBlock).getStartNumber() == 1), z);
    }

    public boolean startNewList(ListBlock listBlock, ListBlock listBlock2) {
        boolean z = listBlock instanceof OrderedList;
        if (z == (listBlock2 instanceof OrderedList)) {
            return z ? isDelimiterMismatchToNewList() && ((OrderedList) listBlock).getDelimiter() != ((OrderedList) listBlock2).getDelimiter() : isDelimiterMismatchToNewList() && ((BulletList) listBlock).getOpeningMarker() != ((BulletList) listBlock2).getOpeningMarker();
        }
        return isItemTypeMismatchToNewList();
    }

    public boolean startSubList(ListBlock listBlock, ListBlock listBlock2) {
        return (listBlock instanceof OrderedList) != (listBlock2 instanceof OrderedList) && isItemTypeMismatchToSubList();
    }

    public MutableListOptions getMutable() {
        return new MutableListOptions(this);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<ParserEmulationProfile>>) Parser.PARSER_EMULATION_PROFILE, (DataKey<ParserEmulationProfile>) getParserEmulationProfile());
        getItemInterrupt().setIn(mutableDataHolder);
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_AUTO_LOOSE, (DataKey<Boolean>) Boolean.valueOf(this.autoLoose));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_AUTO_LOOSE_ONE_LEVEL_LISTS, (DataKey<Boolean>) Boolean.valueOf(this.autoLooseOneLevelLists));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_DELIMITER_MISMATCH_TO_NEW_LIST, (DataKey<Boolean>) Boolean.valueOf(this.delimiterMismatchToNewList));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_END_ON_DOUBLE_BLANK, (DataKey<Boolean>) Boolean.valueOf(this.endOnDoubleBlank));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_ITEM_MARKER_SPACE, (DataKey<Boolean>) Boolean.valueOf(this.itemMarkerSpace));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_ITEM_TYPE_MISMATCH_TO_NEW_LIST, (DataKey<Boolean>) Boolean.valueOf(this.itemTypeMismatchToNewList));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_ITEM_TYPE_MISMATCH_TO_SUB_LIST, (DataKey<Boolean>) Boolean.valueOf(this.itemTypeMismatchToSubList));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_LOOSE_WHEN_PREV_HAS_TRAILING_BLANK_LINE, (DataKey<Boolean>) Boolean.valueOf(this.looseWhenPrevHasTrailingBlankLine));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_LOOSE_WHEN_LAST_ITEM_PREV_HAS_TRAILING_BLANK_LINE, (DataKey<Boolean>) Boolean.valueOf(this.looseWhenLastItemPrevHasTrailingBlankLine));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_LOOSE_WHEN_HAS_NON_LIST_CHILDREN, (DataKey<Boolean>) Boolean.valueOf(this.looseWhenHasNonListChildren));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_LOOSE_WHEN_BLANK_LINE_FOLLOWS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.looseWhenBlankLineFollowsItemParagraph));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_LOOSE_WHEN_HAS_LOOSE_SUB_ITEM, (DataKey<Boolean>) Boolean.valueOf(this.looseWhenHasLooseSubItem));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_LOOSE_WHEN_HAS_TRAILING_BLANK_LINE, (DataKey<Boolean>) Boolean.valueOf(this.looseWhenHasTrailingBlankLine));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_LOOSE_WHEN_CONTAINS_BLANK_LINE, (DataKey<Boolean>) Boolean.valueOf(this.looseWhenContainsBlankLine));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_NUMBERED_ITEM_MARKER_SUFFIXED, (DataKey<Boolean>) Boolean.valueOf(this.numberedItemMarkerSuffixed));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_ORDERED_ITEM_DOT_ONLY, (DataKey<Boolean>) Boolean.valueOf(this.orderedItemDotOnly));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_ORDERED_LIST_MANUAL_START, (DataKey<Boolean>) Boolean.valueOf(this.orderedListManualStart));
        mutableDataHolder.set((DataKey<DataKey<Integer>>) Parser.LISTS_CODE_INDENT, (DataKey<Integer>) Integer.valueOf(this.codeIndent));
        mutableDataHolder.set((DataKey<DataKey<Integer>>) Parser.LISTS_ITEM_INDENT, (DataKey<Integer>) Integer.valueOf(this.itemIndent));
        mutableDataHolder.set((DataKey<DataKey<Integer>>) Parser.LISTS_NEW_ITEM_CODE_INDENT, (DataKey<Integer>) Integer.valueOf(this.newItemCodeIndent));
        mutableDataHolder.set((DataKey<DataKey<String[]>>) Parser.LISTS_ITEM_MARKER_SUFFIXES, (DataKey<String[]>) this.itemMarkerSuffixes);
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_ITEM_CONTENT_AFTER_SUFFIX, (DataKey<Boolean>) Boolean.valueOf(this.itemContentAfterSuffix));
        mutableDataHolder.set((DataKey<DataKey<String>>) Parser.LISTS_ITEM_PREFIX_CHARS, (DataKey<String>) this.itemPrefixChars);
        return mutableDataHolder;
    }

    public static void addItemMarkerSuffixes(MutableDataHolder mutableDataHolder, String... strArr) {
        String[] strArr2 = Parser.LISTS_ITEM_MARKER_SUFFIXES.get(mutableDataHolder);
        int length = strArr.length;
        int length2 = strArr.length;
        for (String str : strArr2) {
            int i = 0;
            while (true) {
                if (i >= length2) {
                    break;
                }
                String str2 = strArr[i];
                if (str2 == null || !str2.equals(str)) {
                    i++;
                } else {
                    length--;
                    strArr[i] = null;
                    break;
                }
            }
            if (length == 0) {
                break;
            }
        }
        if (length > 0) {
            String[] strArr3 = new String[strArr2.length + length];
            System.arraycopy(strArr2, 0, strArr3, 0, strArr2.length);
            int length3 = strArr2.length;
            for (String str3 : strArr) {
                if (str3 != null) {
                    int i2 = length3;
                    length3++;
                    strArr3[i2] = str3;
                }
            }
            mutableDataHolder.set((DataKey<DataKey<String[]>>) Parser.LISTS_ITEM_MARKER_SUFFIXES, (DataKey<String[]>) strArr3);
        }
    }

    public ParserEmulationProfile getParserEmulationProfile() {
        return this.myParserEmulationProfile;
    }

    public ItemInterrupt getItemInterrupt() {
        return this.itemInterrupt;
    }

    public boolean isAutoLoose() {
        return this.autoLoose;
    }

    public boolean isAutoLooseOneLevelLists() {
        return this.autoLooseOneLevelLists;
    }

    public boolean isDelimiterMismatchToNewList() {
        return this.delimiterMismatchToNewList;
    }

    public boolean isEndOnDoubleBlank() {
        return this.endOnDoubleBlank;
    }

    public boolean isItemMarkerSpace() {
        return this.itemMarkerSpace;
    }

    public boolean isItemTypeMismatchToNewList() {
        return this.itemTypeMismatchToNewList;
    }

    public boolean isItemContentAfterSuffix() {
        return this.itemContentAfterSuffix;
    }

    public String getItemPrefixChars() {
        return this.itemPrefixChars;
    }

    public boolean isItemTypeMismatchToSubList() {
        return this.itemTypeMismatchToSubList;
    }

    public boolean isLooseWhenPrevHasTrailingBlankLine() {
        return this.looseWhenPrevHasTrailingBlankLine;
    }

    public boolean isLooseWhenLastItemPrevHasTrailingBlankLine() {
        return this.looseWhenLastItemPrevHasTrailingBlankLine;
    }

    public boolean isLooseWhenHasNonListChildren() {
        return this.looseWhenHasNonListChildren;
    }

    public boolean isLooseWhenHasLooseSubItem() {
        return this.looseWhenHasLooseSubItem;
    }

    public boolean isLooseWhenHasTrailingBlankLine() {
        return this.looseWhenHasTrailingBlankLine;
    }

    public boolean isLooseWhenContainsBlankLine() {
        return this.looseWhenContainsBlankLine;
    }

    public boolean isLooseWhenBlankLineFollowsItemParagraph() {
        return this.looseWhenBlankLineFollowsItemParagraph;
    }

    public boolean isOrderedItemDotOnly() {
        return this.orderedItemDotOnly;
    }

    public boolean isOrderedListManualStart() {
        return this.orderedListManualStart;
    }

    public boolean isNumberedItemMarkerSuffixed() {
        return this.numberedItemMarkerSuffixed;
    }

    public int getCodeIndent() {
        return this.codeIndent;
    }

    public int getItemIndent() {
        return this.itemIndent;
    }

    public int getNewItemCodeIndent() {
        return this.newItemCodeIndent;
    }

    public String[] getItemMarkerSuffixes() {
        return this.itemMarkerSuffixes;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/ListOptions$ItemInterrupt.class */
    public static class ItemInterrupt {
        protected boolean bulletItemInterruptsParagraph;
        protected boolean orderedItemInterruptsParagraph;
        protected boolean orderedNonOneItemInterruptsParagraph;
        protected boolean emptyBulletItemInterruptsParagraph;
        protected boolean emptyOrderedItemInterruptsParagraph;
        protected boolean emptyOrderedNonOneItemInterruptsParagraph;
        protected boolean bulletItemInterruptsItemParagraph;
        protected boolean orderedItemInterruptsItemParagraph;
        protected boolean orderedNonOneItemInterruptsItemParagraph;
        protected boolean emptyBulletItemInterruptsItemParagraph;
        protected boolean emptyOrderedItemInterruptsItemParagraph;
        protected boolean emptyOrderedNonOneItemInterruptsItemParagraph;
        protected boolean emptyBulletSubItemInterruptsItemParagraph;
        protected boolean emptyOrderedSubItemInterruptsItemParagraph;
        protected boolean emptyOrderedNonOneSubItemInterruptsItemParagraph;

        public ItemInterrupt() {
            this.bulletItemInterruptsParagraph = false;
            this.orderedItemInterruptsParagraph = false;
            this.orderedNonOneItemInterruptsParagraph = false;
            this.emptyBulletItemInterruptsParagraph = false;
            this.emptyOrderedItemInterruptsParagraph = false;
            this.emptyOrderedNonOneItemInterruptsParagraph = false;
            this.bulletItemInterruptsItemParagraph = false;
            this.orderedItemInterruptsItemParagraph = false;
            this.orderedNonOneItemInterruptsItemParagraph = false;
            this.emptyBulletItemInterruptsItemParagraph = false;
            this.emptyOrderedItemInterruptsItemParagraph = false;
            this.emptyOrderedNonOneItemInterruptsItemParagraph = false;
            this.emptyBulletSubItemInterruptsItemParagraph = false;
            this.emptyOrderedSubItemInterruptsItemParagraph = false;
            this.emptyOrderedNonOneSubItemInterruptsItemParagraph = false;
        }

        public ItemInterrupt(DataHolder dataHolder) {
            this.bulletItemInterruptsParagraph = Parser.LISTS_BULLET_ITEM_INTERRUPTS_PARAGRAPH.get(dataHolder).booleanValue();
            this.orderedItemInterruptsParagraph = Parser.LISTS_ORDERED_ITEM_INTERRUPTS_PARAGRAPH.get(dataHolder).booleanValue();
            this.orderedNonOneItemInterruptsParagraph = Parser.LISTS_ORDERED_NON_ONE_ITEM_INTERRUPTS_PARAGRAPH.get(dataHolder).booleanValue();
            this.emptyBulletItemInterruptsParagraph = Parser.LISTS_EMPTY_BULLET_ITEM_INTERRUPTS_PARAGRAPH.get(dataHolder).booleanValue();
            this.emptyOrderedItemInterruptsParagraph = Parser.LISTS_EMPTY_ORDERED_ITEM_INTERRUPTS_PARAGRAPH.get(dataHolder).booleanValue();
            this.emptyOrderedNonOneItemInterruptsParagraph = Parser.LISTS_EMPTY_ORDERED_NON_ONE_ITEM_INTERRUPTS_PARAGRAPH.get(dataHolder).booleanValue();
            this.bulletItemInterruptsItemParagraph = Parser.LISTS_BULLET_ITEM_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
            this.orderedItemInterruptsItemParagraph = Parser.LISTS_ORDERED_ITEM_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
            this.orderedNonOneItemInterruptsItemParagraph = Parser.LISTS_ORDERED_NON_ONE_ITEM_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
            this.emptyBulletItemInterruptsItemParagraph = Parser.LISTS_EMPTY_BULLET_ITEM_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
            this.emptyOrderedItemInterruptsItemParagraph = Parser.LISTS_EMPTY_ORDERED_ITEM_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
            this.emptyOrderedNonOneItemInterruptsItemParagraph = Parser.LISTS_EMPTY_ORDERED_NON_ONE_ITEM_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
            this.emptyBulletSubItemInterruptsItemParagraph = Parser.LISTS_EMPTY_BULLET_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
            this.emptyOrderedSubItemInterruptsItemParagraph = Parser.LISTS_EMPTY_ORDERED_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
            this.emptyOrderedNonOneSubItemInterruptsItemParagraph = Parser.LISTS_EMPTY_ORDERED_NON_ONE_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
        }

        public void setIn(MutableDataHolder mutableDataHolder) {
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_BULLET_ITEM_INTERRUPTS_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.bulletItemInterruptsParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_ORDERED_ITEM_INTERRUPTS_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.orderedItemInterruptsParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_ORDERED_NON_ONE_ITEM_INTERRUPTS_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.orderedNonOneItemInterruptsParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_EMPTY_BULLET_ITEM_INTERRUPTS_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.emptyBulletItemInterruptsParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_EMPTY_ORDERED_ITEM_INTERRUPTS_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.emptyOrderedItemInterruptsParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_EMPTY_ORDERED_NON_ONE_ITEM_INTERRUPTS_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.emptyOrderedNonOneItemInterruptsParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_BULLET_ITEM_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.bulletItemInterruptsItemParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_ORDERED_ITEM_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.orderedItemInterruptsItemParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_ORDERED_NON_ONE_ITEM_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.orderedNonOneItemInterruptsItemParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_EMPTY_BULLET_ITEM_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.emptyBulletItemInterruptsItemParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_EMPTY_ORDERED_ITEM_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.emptyOrderedItemInterruptsItemParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_EMPTY_ORDERED_NON_ONE_ITEM_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.emptyOrderedNonOneItemInterruptsItemParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_EMPTY_BULLET_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.emptyBulletSubItemInterruptsItemParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_EMPTY_ORDERED_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.emptyOrderedSubItemInterruptsItemParagraph));
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LISTS_EMPTY_ORDERED_NON_ONE_SUB_ITEM_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.valueOf(this.emptyOrderedNonOneSubItemInterruptsItemParagraph));
        }

        public ItemInterrupt(ItemInterrupt itemInterrupt) {
            this.bulletItemInterruptsParagraph = itemInterrupt.bulletItemInterruptsParagraph;
            this.orderedItemInterruptsParagraph = itemInterrupt.orderedItemInterruptsParagraph;
            this.orderedNonOneItemInterruptsParagraph = itemInterrupt.orderedNonOneItemInterruptsParagraph;
            this.emptyBulletItemInterruptsParagraph = itemInterrupt.emptyBulletItemInterruptsParagraph;
            this.emptyOrderedItemInterruptsParagraph = itemInterrupt.emptyOrderedItemInterruptsParagraph;
            this.emptyOrderedNonOneItemInterruptsParagraph = itemInterrupt.emptyOrderedNonOneItemInterruptsParagraph;
            this.bulletItemInterruptsItemParagraph = itemInterrupt.bulletItemInterruptsItemParagraph;
            this.orderedItemInterruptsItemParagraph = itemInterrupt.orderedItemInterruptsItemParagraph;
            this.orderedNonOneItemInterruptsItemParagraph = itemInterrupt.orderedNonOneItemInterruptsItemParagraph;
            this.emptyBulletItemInterruptsItemParagraph = itemInterrupt.emptyBulletItemInterruptsItemParagraph;
            this.emptyOrderedItemInterruptsItemParagraph = itemInterrupt.emptyOrderedItemInterruptsItemParagraph;
            this.emptyOrderedNonOneItemInterruptsItemParagraph = itemInterrupt.emptyOrderedNonOneItemInterruptsItemParagraph;
            this.emptyBulletSubItemInterruptsItemParagraph = itemInterrupt.emptyBulletSubItemInterruptsItemParagraph;
            this.emptyOrderedSubItemInterruptsItemParagraph = itemInterrupt.emptyOrderedSubItemInterruptsItemParagraph;
            this.emptyOrderedNonOneSubItemInterruptsItemParagraph = itemInterrupt.emptyOrderedNonOneSubItemInterruptsItemParagraph;
        }

        public boolean canInterrupt(boolean z, boolean z2, boolean z3, boolean z4) {
            if (z) {
                if (z2) {
                    if (z4) {
                        if (this.orderedItemInterruptsItemParagraph) {
                            return !z3 || this.emptyOrderedItemInterruptsItemParagraph;
                        }
                        return false;
                    }
                    if (this.orderedItemInterruptsParagraph) {
                        return !z3 || this.emptyOrderedItemInterruptsParagraph;
                    }
                    return false;
                }
                if (z4) {
                    if (this.orderedNonOneItemInterruptsItemParagraph) {
                        return !z3 || this.emptyOrderedNonOneItemInterruptsItemParagraph;
                    }
                    return false;
                }
                if (this.orderedNonOneItemInterruptsParagraph) {
                    return !z3 || this.emptyOrderedNonOneItemInterruptsParagraph;
                }
                return false;
            }
            if (z4) {
                if (this.bulletItemInterruptsItemParagraph) {
                    return !z3 || this.emptyBulletItemInterruptsItemParagraph;
                }
                return false;
            }
            if (this.bulletItemInterruptsParagraph) {
                return !z3 || this.emptyBulletItemInterruptsParagraph;
            }
            return false;
        }

        public boolean canStartSubList(boolean z, boolean z2, boolean z3) {
            if (z) {
                if (!this.orderedItemInterruptsItemParagraph) {
                    return false;
                }
                if (z3 && (!this.emptyOrderedSubItemInterruptsItemParagraph || !this.emptyOrderedItemInterruptsItemParagraph)) {
                    return false;
                }
                if (z2) {
                    return true;
                }
                if (!this.orderedNonOneItemInterruptsItemParagraph) {
                    return false;
                }
                if (z3) {
                    return this.emptyOrderedNonOneSubItemInterruptsItemParagraph && this.emptyOrderedNonOneItemInterruptsItemParagraph;
                }
                return true;
            }
            if (!this.bulletItemInterruptsItemParagraph) {
                return false;
            }
            if (z3) {
                return this.emptyBulletSubItemInterruptsItemParagraph && this.emptyBulletItemInterruptsItemParagraph;
            }
            return true;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ItemInterrupt)) {
                return false;
            }
            ItemInterrupt itemInterrupt = (ItemInterrupt) obj;
            return this.bulletItemInterruptsParagraph == itemInterrupt.bulletItemInterruptsParagraph && this.orderedItemInterruptsParagraph == itemInterrupt.orderedItemInterruptsParagraph && this.orderedNonOneItemInterruptsParagraph == itemInterrupt.orderedNonOneItemInterruptsParagraph && this.emptyBulletItemInterruptsParagraph == itemInterrupt.emptyBulletItemInterruptsParagraph && this.emptyOrderedItemInterruptsParagraph == itemInterrupt.emptyOrderedItemInterruptsParagraph && this.emptyOrderedNonOneItemInterruptsParagraph == itemInterrupt.emptyOrderedNonOneItemInterruptsParagraph && this.bulletItemInterruptsItemParagraph == itemInterrupt.bulletItemInterruptsItemParagraph && this.orderedItemInterruptsItemParagraph == itemInterrupt.orderedItemInterruptsItemParagraph && this.orderedNonOneItemInterruptsItemParagraph == itemInterrupt.orderedNonOneItemInterruptsItemParagraph && this.emptyBulletItemInterruptsItemParagraph == itemInterrupt.emptyBulletItemInterruptsItemParagraph && this.emptyOrderedItemInterruptsItemParagraph == itemInterrupt.emptyOrderedItemInterruptsItemParagraph && this.emptyOrderedNonOneItemInterruptsItemParagraph == itemInterrupt.emptyOrderedNonOneItemInterruptsItemParagraph && this.emptyBulletSubItemInterruptsItemParagraph == itemInterrupt.emptyBulletSubItemInterruptsItemParagraph && this.emptyOrderedSubItemInterruptsItemParagraph == itemInterrupt.emptyOrderedSubItemInterruptsItemParagraph && this.emptyOrderedNonOneSubItemInterruptsItemParagraph == itemInterrupt.emptyOrderedNonOneSubItemInterruptsItemParagraph;
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((this.bulletItemInterruptsParagraph ? 1 : 0) * 31) + (this.orderedItemInterruptsParagraph ? 1 : 0)) * 31) + (this.orderedNonOneItemInterruptsParagraph ? 1 : 0)) * 31) + (this.emptyBulletItemInterruptsParagraph ? 1 : 0)) * 31) + (this.emptyOrderedItemInterruptsParagraph ? 1 : 0)) * 31) + (this.emptyOrderedNonOneItemInterruptsParagraph ? 1 : 0)) * 31) + (this.bulletItemInterruptsItemParagraph ? 1 : 0)) * 31) + (this.orderedItemInterruptsItemParagraph ? 1 : 0)) * 31) + (this.orderedNonOneItemInterruptsItemParagraph ? 1 : 0)) * 31) + (this.emptyBulletItemInterruptsItemParagraph ? 1 : 0)) * 31) + (this.emptyOrderedItemInterruptsItemParagraph ? 1 : 0)) * 31) + (this.emptyOrderedNonOneItemInterruptsItemParagraph ? 1 : 0)) * 31) + (this.emptyBulletSubItemInterruptsItemParagraph ? 1 : 0)) * 31) + (this.emptyOrderedSubItemInterruptsItemParagraph ? 1 : 0)) * 31) + (this.emptyOrderedNonOneSubItemInterruptsItemParagraph ? 1 : 0);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/ListOptions$MutableItemInterrupt.class */
    public static class MutableItemInterrupt extends ItemInterrupt {
        public MutableItemInterrupt() {
        }

        public MutableItemInterrupt(DataHolder dataHolder) {
            super(dataHolder);
        }

        public MutableItemInterrupt(ItemInterrupt itemInterrupt) {
            super(itemInterrupt);
        }

        public boolean isBulletItemInterruptsParagraph() {
            return this.bulletItemInterruptsParagraph;
        }

        public boolean isOrderedItemInterruptsParagraph() {
            return this.orderedItemInterruptsParagraph;
        }

        public boolean isOrderedNonOneItemInterruptsParagraph() {
            return this.orderedNonOneItemInterruptsParagraph;
        }

        public boolean isEmptyBulletItemInterruptsParagraph() {
            return this.emptyBulletItemInterruptsParagraph;
        }

        public boolean isEmptyOrderedItemInterruptsParagraph() {
            return this.emptyOrderedItemInterruptsParagraph;
        }

        public boolean isEmptyOrderedNonOneItemInterruptsParagraph() {
            return this.emptyOrderedNonOneItemInterruptsParagraph;
        }

        public boolean isBulletItemInterruptsItemParagraph() {
            return this.bulletItemInterruptsItemParagraph;
        }

        public boolean isOrderedItemInterruptsItemParagraph() {
            return this.orderedItemInterruptsItemParagraph;
        }

        public boolean isOrderedNonOneItemInterruptsItemParagraph() {
            return this.orderedNonOneItemInterruptsItemParagraph;
        }

        public boolean isEmptyBulletItemInterruptsItemParagraph() {
            return this.emptyBulletItemInterruptsItemParagraph;
        }

        public boolean isEmptyOrderedItemInterruptsItemParagraph() {
            return this.emptyOrderedItemInterruptsItemParagraph;
        }

        public boolean isEmptyOrderedNonOneItemInterruptsItemParagraph() {
            return this.emptyOrderedNonOneItemInterruptsItemParagraph;
        }

        public boolean isEmptyBulletSubItemInterruptsItemParagraph() {
            return this.emptyBulletSubItemInterruptsItemParagraph;
        }

        public boolean isEmptyOrderedSubItemInterruptsItemParagraph() {
            return this.emptyOrderedSubItemInterruptsItemParagraph;
        }

        public boolean isEmptyOrderedNonOneSubItemInterruptsItemParagraph() {
            return this.emptyOrderedNonOneSubItemInterruptsItemParagraph;
        }

        public MutableItemInterrupt setBulletItemInterruptsParagraph(boolean z) {
            this.bulletItemInterruptsParagraph = z;
            return this;
        }

        public MutableItemInterrupt setOrderedItemInterruptsParagraph(boolean z) {
            this.orderedItemInterruptsParagraph = z;
            return this;
        }

        public MutableItemInterrupt setOrderedNonOneItemInterruptsParagraph(boolean z) {
            this.orderedNonOneItemInterruptsParagraph = z;
            return this;
        }

        public MutableItemInterrupt setEmptyBulletItemInterruptsParagraph(boolean z) {
            this.emptyBulletItemInterruptsParagraph = z;
            return this;
        }

        public MutableItemInterrupt setEmptyOrderedItemInterruptsParagraph(boolean z) {
            this.emptyOrderedItemInterruptsParagraph = z;
            return this;
        }

        public MutableItemInterrupt setEmptyOrderedNonOneItemInterruptsParagraph(boolean z) {
            this.emptyOrderedNonOneItemInterruptsParagraph = z;
            return this;
        }

        public MutableItemInterrupt setBulletItemInterruptsItemParagraph(boolean z) {
            this.bulletItemInterruptsItemParagraph = z;
            return this;
        }

        public MutableItemInterrupt setOrderedItemInterruptsItemParagraph(boolean z) {
            this.orderedItemInterruptsItemParagraph = z;
            return this;
        }

        public MutableItemInterrupt setOrderedNonOneItemInterruptsItemParagraph(boolean z) {
            this.orderedNonOneItemInterruptsItemParagraph = z;
            return this;
        }

        public MutableItemInterrupt setEmptyBulletItemInterruptsItemParagraph(boolean z) {
            this.emptyBulletItemInterruptsItemParagraph = z;
            return this;
        }

        public MutableItemInterrupt setEmptyOrderedItemInterruptsItemParagraph(boolean z) {
            this.emptyOrderedItemInterruptsItemParagraph = z;
            return this;
        }

        public MutableItemInterrupt setEmptyOrderedNonOneItemInterruptsItemParagraph(boolean z) {
            this.emptyOrderedNonOneItemInterruptsItemParagraph = z;
            return this;
        }

        public MutableItemInterrupt setEmptyBulletSubItemInterruptsItemParagraph(boolean z) {
            this.emptyBulletSubItemInterruptsItemParagraph = z;
            return this;
        }

        public MutableItemInterrupt setEmptyOrderedSubItemInterruptsItemParagraph(boolean z) {
            this.emptyOrderedSubItemInterruptsItemParagraph = z;
            return this;
        }

        public MutableItemInterrupt setEmptyOrderedNonOneSubItemInterruptsItemParagraph(boolean z) {
            this.emptyOrderedNonOneSubItemInterruptsItemParagraph = z;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListOptions)) {
            return false;
        }
        ListOptions listOptions = (ListOptions) obj;
        if (this.myParserEmulationProfile == listOptions.myParserEmulationProfile && this.autoLoose == listOptions.autoLoose && this.autoLooseOneLevelLists == listOptions.autoLooseOneLevelLists && this.delimiterMismatchToNewList == listOptions.delimiterMismatchToNewList && this.endOnDoubleBlank == listOptions.endOnDoubleBlank && this.itemMarkerSpace == listOptions.itemMarkerSpace && this.itemTypeMismatchToNewList == listOptions.itemTypeMismatchToNewList && this.itemTypeMismatchToSubList == listOptions.itemTypeMismatchToSubList && this.looseWhenPrevHasTrailingBlankLine == listOptions.looseWhenPrevHasTrailingBlankLine && this.looseWhenLastItemPrevHasTrailingBlankLine == listOptions.looseWhenLastItemPrevHasTrailingBlankLine && this.looseWhenHasNonListChildren == listOptions.looseWhenHasNonListChildren && this.looseWhenBlankLineFollowsItemParagraph == listOptions.looseWhenBlankLineFollowsItemParagraph && this.looseWhenHasLooseSubItem == listOptions.looseWhenHasLooseSubItem && this.looseWhenHasTrailingBlankLine == listOptions.looseWhenHasTrailingBlankLine && this.looseWhenContainsBlankLine == listOptions.looseWhenContainsBlankLine && this.numberedItemMarkerSuffixed == listOptions.numberedItemMarkerSuffixed && this.orderedItemDotOnly == listOptions.orderedItemDotOnly && this.orderedListManualStart == listOptions.orderedListManualStart && this.codeIndent == listOptions.codeIndent && this.itemIndent == listOptions.itemIndent && this.newItemCodeIndent == listOptions.newItemCodeIndent && this.itemMarkerSuffixes == listOptions.itemMarkerSuffixes && this.itemContentAfterSuffix == listOptions.itemContentAfterSuffix && this.itemPrefixChars.equals(listOptions.itemPrefixChars)) {
            return this.itemInterrupt.equals(listOptions.itemInterrupt);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((this.myParserEmulationProfile.hashCode() * 31) + this.itemInterrupt.hashCode()) * 31) + (this.autoLoose ? 1 : 0)) * 31) + (this.autoLooseOneLevelLists ? 1 : 0)) * 31) + (this.delimiterMismatchToNewList ? 1 : 0)) * 31) + (this.endOnDoubleBlank ? 1 : 0)) * 31) + (this.itemMarkerSpace ? 1 : 0)) * 31) + (this.itemTypeMismatchToNewList ? 1 : 0)) * 31) + (this.itemTypeMismatchToSubList ? 1 : 0)) * 31) + (this.looseWhenPrevHasTrailingBlankLine ? 1 : 0)) * 31) + (this.looseWhenLastItemPrevHasTrailingBlankLine ? 1 : 0)) * 31) + (this.looseWhenHasNonListChildren ? 1 : 0)) * 31) + (this.looseWhenBlankLineFollowsItemParagraph ? 1 : 0)) * 31) + (this.looseWhenHasLooseSubItem ? 1 : 0)) * 31) + (this.looseWhenHasTrailingBlankLine ? 1 : 0)) * 31) + (this.looseWhenContainsBlankLine ? 1 : 0)) * 31) + (this.numberedItemMarkerSuffixed ? 1 : 0)) * 31) + (this.orderedItemDotOnly ? 1 : 0)) * 31) + (this.orderedListManualStart ? 1 : 0)) * 31) + (this.itemContentAfterSuffix ? 1 : 0)) * 31) + this.itemPrefixChars.hashCode()) * 31) + this.codeIndent) * 31) + this.itemIndent) * 31) + this.newItemCodeIndent) * 31) + Arrays.hashCode(this.itemMarkerSuffixes);
    }
}
