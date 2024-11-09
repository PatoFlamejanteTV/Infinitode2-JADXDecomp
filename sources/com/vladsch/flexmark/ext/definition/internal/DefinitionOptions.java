package com.vladsch.flexmark.ext.definition.internal;

import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.data.DataHolder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/internal/DefinitionOptions.class */
public class DefinitionOptions {
    public final int markerSpaces;
    public final boolean tildeMarker;
    public final boolean colonMarker;
    public final ParserEmulationProfile myParserEmulationProfile;
    public final boolean autoLoose;
    public final boolean autoLooseOneLevelLists;
    public final boolean looseOnPrevLooseItem;
    public final boolean looseWhenHasLooseSubItem;
    public final boolean looseWhenHasTrailingBlankLine;
    public final boolean looseWhenBlankFollowsItemParagraph;
    public final boolean doubleBlankLineBreaksList;
    public final int codeIndent;
    public final int itemIndent;
    public final int newItemCodeIndent;

    public DefinitionOptions(DataHolder dataHolder) {
        this.markerSpaces = DefinitionExtension.MARKER_SPACES.get(dataHolder).intValue();
        this.tildeMarker = DefinitionExtension.TILDE_MARKER.get(dataHolder).booleanValue();
        this.colonMarker = DefinitionExtension.COLON_MARKER.get(dataHolder).booleanValue();
        this.myParserEmulationProfile = Parser.PARSER_EMULATION_PROFILE.get(dataHolder);
        this.autoLoose = Parser.LISTS_AUTO_LOOSE.get(dataHolder).booleanValue();
        this.autoLooseOneLevelLists = Parser.LISTS_AUTO_LOOSE_ONE_LEVEL_LISTS.get(dataHolder).booleanValue();
        this.looseOnPrevLooseItem = Parser.LISTS_LOOSE_WHEN_PREV_HAS_TRAILING_BLANK_LINE.get(dataHolder).booleanValue();
        this.looseWhenBlankFollowsItemParagraph = Parser.LISTS_LOOSE_WHEN_BLANK_LINE_FOLLOWS_ITEM_PARAGRAPH.get(dataHolder).booleanValue();
        this.looseWhenHasLooseSubItem = Parser.LISTS_LOOSE_WHEN_HAS_LOOSE_SUB_ITEM.get(dataHolder).booleanValue();
        this.looseWhenHasTrailingBlankLine = Parser.LISTS_LOOSE_WHEN_HAS_TRAILING_BLANK_LINE.get(dataHolder).booleanValue();
        this.codeIndent = Parser.LISTS_CODE_INDENT.get(dataHolder).intValue();
        this.itemIndent = Parser.LISTS_ITEM_INDENT.get(dataHolder).intValue();
        this.newItemCodeIndent = Parser.LISTS_NEW_ITEM_CODE_INDENT.get(dataHolder).intValue();
        this.doubleBlankLineBreaksList = DefinitionExtension.DOUBLE_BLANK_LINE_BREAKS_LIST.get(dataHolder).booleanValue();
    }
}
