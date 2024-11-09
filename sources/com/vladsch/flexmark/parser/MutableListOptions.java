package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/MutableListOptions.class */
public class MutableListOptions extends ListOptions {
    public MutableListOptions() {
        this.itemInterrupt = new ListOptions.MutableItemInterrupt(super.getItemInterrupt());
    }

    public MutableListOptions(DataHolder dataHolder) {
        this(ListOptions.get(dataHolder));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MutableListOptions(ListOptions listOptions) {
        super(listOptions);
        this.itemInterrupt = new ListOptions.MutableItemInterrupt(super.getItemInterrupt());
    }

    @Override // com.vladsch.flexmark.parser.ListOptions
    public MutableListOptions getMutable() {
        return new MutableListOptions(this);
    }

    public MutableListOptions setParserEmulationFamily(ParserEmulationProfile parserEmulationProfile) {
        this.myParserEmulationProfile = parserEmulationProfile;
        return this;
    }

    public MutableListOptions setItemInterrupt(ListOptions.MutableItemInterrupt mutableItemInterrupt) {
        this.itemInterrupt = mutableItemInterrupt;
        return this;
    }

    public MutableListOptions setAutoLoose(boolean z) {
        this.autoLoose = z;
        return this;
    }

    public MutableListOptions setAutoLooseOneLevelLists(boolean z) {
        this.autoLooseOneLevelLists = z;
        return this;
    }

    public MutableListOptions setDelimiterMismatchToNewList(boolean z) {
        this.delimiterMismatchToNewList = z;
        return this;
    }

    public MutableListOptions setEndOnDoubleBlank(boolean z) {
        this.endOnDoubleBlank = z;
        return this;
    }

    public MutableListOptions setItemMarkerSpace(boolean z) {
        this.itemMarkerSpace = z;
        return this;
    }

    public MutableListOptions setItemTypeMismatchToNewList(boolean z) {
        this.itemTypeMismatchToNewList = z;
        return this;
    }

    public MutableListOptions setItemTypeMismatchToSubList(boolean z) {
        this.itemTypeMismatchToSubList = z;
        return this;
    }

    public MutableListOptions setLooseWhenPrevHasTrailingBlankLine(boolean z) {
        this.looseWhenPrevHasTrailingBlankLine = z;
        return this;
    }

    public MutableListOptions setLooseWhenLastItemPrevHasTrailingBlankLine(boolean z) {
        this.looseWhenLastItemPrevHasTrailingBlankLine = z;
        return this;
    }

    public MutableListOptions setLooseWhenHasNonListChildren(boolean z) {
        this.looseWhenHasNonListChildren = z;
        return this;
    }

    public MutableListOptions setLooseWhenBlankLineFollowsItemParagraph(boolean z) {
        this.looseWhenBlankLineFollowsItemParagraph = z;
        return this;
    }

    public MutableListOptions setLooseWhenHasLooseSubItem(boolean z) {
        this.looseWhenHasLooseSubItem = z;
        return this;
    }

    public MutableListOptions setLooseWhenHasTrailingBlankLine(boolean z) {
        this.looseWhenHasTrailingBlankLine = z;
        return this;
    }

    public MutableListOptions setLooseWhenContainsBlankLine(boolean z) {
        this.looseWhenContainsBlankLine = z;
        return this;
    }

    public MutableListOptions setNumberedItemMarkerSuffixed(boolean z) {
        this.numberedItemMarkerSuffixed = z;
        return this;
    }

    public MutableListOptions setOrderedItemDotOnly(boolean z) {
        this.orderedItemDotOnly = z;
        return this;
    }

    public MutableListOptions setOrderedListManualStart(boolean z) {
        this.orderedListManualStart = z;
        return this;
    }

    public MutableListOptions setCodeIndent(int i) {
        this.codeIndent = i;
        return this;
    }

    public MutableListOptions setItemIndent(int i) {
        this.itemIndent = i;
        return this;
    }

    public MutableListOptions setNewItemCodeIndent(int i) {
        this.newItemCodeIndent = i;
        return this;
    }

    public MutableListOptions setItemMarkerSuffixes(String[] strArr) {
        this.itemMarkerSuffixes = strArr;
        return this;
    }
}
