package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.data.MutableDataSetter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/ParserEmulationProfile.class */
public enum ParserEmulationProfile implements MutableDataSetter {
    COMMONMARK(null),
    COMMONMARK_0_26(COMMONMARK),
    COMMONMARK_0_27(COMMONMARK),
    COMMONMARK_0_28(COMMONMARK),
    COMMONMARK_0_29(COMMONMARK),
    FIXED_INDENT(null),
    KRAMDOWN(null),
    MARKDOWN(null),
    GITHUB_DOC(MARKDOWN),
    GITHUB(COMMONMARK),
    MULTI_MARKDOWN(FIXED_INDENT),
    PEGDOWN(FIXED_INDENT),
    PEGDOWN_STRICT(FIXED_INDENT);

    public final ParserEmulationProfile family;
    public static final DataKey<Integer> PEGDOWN_EXTENSIONS = new DataKey<>("PEGDOWN_EXTENSIONS", 65535);

    ParserEmulationProfile(ParserEmulationProfile parserEmulationProfile) {
        this.family = parserEmulationProfile == null ? this : parserEmulationProfile;
    }

    public final MutableDataHolder getProfileOptions() {
        MutableDataSet mutableDataSet = new MutableDataSet();
        setIn(mutableDataSet);
        return mutableDataSet;
    }

    public final MutableListOptions getOptions() {
        return getOptions(null);
    }

    public final MutableListOptions getOptions(DataHolder dataHolder) {
        if (this.family == FIXED_INDENT) {
            if (this == MULTI_MARKDOWN) {
                return new MutableListOptions().setParserEmulationFamily(this).setAutoLoose(true).setAutoLooseOneLevelLists(true).setDelimiterMismatchToNewList(false).setCodeIndent(8).setEndOnDoubleBlank(false).setItemIndent(4).setItemInterrupt(new ListOptions.MutableItemInterrupt().setBulletItemInterruptsParagraph(false).setOrderedItemInterruptsParagraph(false).setOrderedNonOneItemInterruptsParagraph(false).setEmptyBulletItemInterruptsParagraph(false).setEmptyOrderedItemInterruptsParagraph(false).setEmptyOrderedNonOneItemInterruptsParagraph(false).setBulletItemInterruptsItemParagraph(true).setOrderedItemInterruptsItemParagraph(true).setOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletItemInterruptsItemParagraph(true).setEmptyOrderedItemInterruptsItemParagraph(true).setEmptyOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletSubItemInterruptsItemParagraph(true).setEmptyOrderedSubItemInterruptsItemParagraph(true).setEmptyOrderedNonOneSubItemInterruptsItemParagraph(true)).setItemMarkerSpace(false).setItemTypeMismatchToNewList(false).setItemTypeMismatchToSubList(false).setLooseWhenBlankLineFollowsItemParagraph(true).setLooseWhenHasTrailingBlankLine(false).setLooseWhenHasNonListChildren(true).setNewItemCodeIndent(Integer.MAX_VALUE).setOrderedItemDotOnly(true).setOrderedListManualStart(false);
            }
            if (this == PEGDOWN || this == PEGDOWN_STRICT) {
                return new MutableListOptions().setParserEmulationFamily(this).setAutoLoose(false).setAutoLooseOneLevelLists(false).setLooseWhenBlankLineFollowsItemParagraph(false).setLooseWhenHasLooseSubItem(false).setLooseWhenHasTrailingBlankLine(false).setLooseWhenPrevHasTrailingBlankLine(true).setOrderedListManualStart(false).setDelimiterMismatchToNewList(false).setItemTypeMismatchToNewList(true).setItemTypeMismatchToSubList(false).setEndOnDoubleBlank(false).setOrderedItemDotOnly(true).setItemMarkerSpace(true).setItemIndent(4).setCodeIndent(8).setNewItemCodeIndent(Integer.MAX_VALUE).setItemInterrupt(new ListOptions.MutableItemInterrupt().setBulletItemInterruptsParagraph(false).setOrderedItemInterruptsParagraph(false).setOrderedNonOneItemInterruptsParagraph(false).setEmptyBulletItemInterruptsParagraph(false).setEmptyOrderedItemInterruptsParagraph(false).setEmptyOrderedNonOneItemInterruptsParagraph(false).setBulletItemInterruptsItemParagraph(true).setOrderedItemInterruptsItemParagraph(true).setOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletItemInterruptsItemParagraph(true).setEmptyOrderedItemInterruptsItemParagraph(true).setEmptyOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletSubItemInterruptsItemParagraph(true).setEmptyOrderedSubItemInterruptsItemParagraph(true).setEmptyOrderedNonOneSubItemInterruptsItemParagraph(true));
            }
            return new MutableListOptions().setParserEmulationFamily(this).setAutoLoose(false).setAutoLooseOneLevelLists(false).setLooseWhenBlankLineFollowsItemParagraph(false).setLooseWhenHasLooseSubItem(false).setLooseWhenHasTrailingBlankLine(true).setLooseWhenPrevHasTrailingBlankLine(false).setLooseWhenLastItemPrevHasTrailingBlankLine(true).setOrderedListManualStart(false).setDelimiterMismatchToNewList(false).setItemTypeMismatchToNewList(false).setItemTypeMismatchToSubList(false).setEndOnDoubleBlank(false).setOrderedItemDotOnly(true).setItemMarkerSpace(true).setItemIndent(4).setCodeIndent(8).setNewItemCodeIndent(Integer.MAX_VALUE).setItemInterrupt(new ListOptions.MutableItemInterrupt().setBulletItemInterruptsParagraph(false).setOrderedItemInterruptsParagraph(false).setOrderedNonOneItemInterruptsParagraph(false).setEmptyBulletItemInterruptsParagraph(false).setEmptyOrderedItemInterruptsParagraph(false).setEmptyOrderedNonOneItemInterruptsParagraph(false).setBulletItemInterruptsItemParagraph(true).setOrderedItemInterruptsItemParagraph(true).setOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletItemInterruptsItemParagraph(true).setEmptyOrderedItemInterruptsItemParagraph(true).setEmptyOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletSubItemInterruptsItemParagraph(true).setEmptyOrderedSubItemInterruptsItemParagraph(true).setEmptyOrderedNonOneSubItemInterruptsItemParagraph(true));
        }
        if (this.family == KRAMDOWN) {
            return new MutableListOptions().setParserEmulationFamily(this).setAutoLoose(false).setLooseWhenBlankLineFollowsItemParagraph(true).setLooseWhenHasLooseSubItem(false).setLooseWhenHasTrailingBlankLine(false).setLooseWhenPrevHasTrailingBlankLine(false).setOrderedListManualStart(false).setDelimiterMismatchToNewList(false).setItemTypeMismatchToNewList(true).setItemTypeMismatchToSubList(true).setOrderedItemDotOnly(true).setItemMarkerSpace(true).setEndOnDoubleBlank(false).setItemIndent(4).setCodeIndent(8).setNewItemCodeIndent(Integer.MAX_VALUE).setItemInterrupt(new ListOptions.MutableItemInterrupt().setBulletItemInterruptsParagraph(false).setOrderedItemInterruptsParagraph(false).setOrderedNonOneItemInterruptsParagraph(false).setEmptyBulletItemInterruptsParagraph(false).setEmptyOrderedItemInterruptsParagraph(false).setEmptyOrderedNonOneItemInterruptsParagraph(false).setBulletItemInterruptsItemParagraph(true).setOrderedItemInterruptsItemParagraph(true).setOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletItemInterruptsItemParagraph(true).setEmptyOrderedItemInterruptsItemParagraph(true).setEmptyOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletSubItemInterruptsItemParagraph(false).setEmptyOrderedSubItemInterruptsItemParagraph(false).setEmptyOrderedNonOneSubItemInterruptsItemParagraph(false));
        }
        if (this.family == MARKDOWN) {
            if (this == GITHUB_DOC) {
                return new MutableListOptions().setParserEmulationFamily(this).setAutoLoose(false).setLooseWhenBlankLineFollowsItemParagraph(true).setLooseWhenHasLooseSubItem(true).setLooseWhenHasTrailingBlankLine(true).setLooseWhenPrevHasTrailingBlankLine(true).setLooseWhenContainsBlankLine(false).setLooseWhenHasNonListChildren(true).setOrderedListManualStart(false).setDelimiterMismatchToNewList(false).setItemTypeMismatchToNewList(false).setItemTypeMismatchToSubList(false).setEndOnDoubleBlank(false).setOrderedItemDotOnly(true).setItemMarkerSpace(true).setItemIndent(4).setCodeIndent(8).setNewItemCodeIndent(Integer.MAX_VALUE).setItemInterrupt(new ListOptions.MutableItemInterrupt().setBulletItemInterruptsParagraph(true).setOrderedItemInterruptsParagraph(false).setOrderedNonOneItemInterruptsParagraph(false).setEmptyBulletItemInterruptsParagraph(true).setEmptyOrderedItemInterruptsParagraph(false).setEmptyOrderedNonOneItemInterruptsParagraph(false).setBulletItemInterruptsItemParagraph(true).setOrderedItemInterruptsItemParagraph(true).setOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletItemInterruptsItemParagraph(true).setEmptyOrderedItemInterruptsItemParagraph(true).setEmptyOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletSubItemInterruptsItemParagraph(true).setEmptyOrderedSubItemInterruptsItemParagraph(true).setEmptyOrderedNonOneSubItemInterruptsItemParagraph(true));
            }
            return new MutableListOptions().setParserEmulationFamily(this).setAutoLoose(false).setLooseWhenBlankLineFollowsItemParagraph(true).setLooseWhenHasLooseSubItem(true).setLooseWhenHasTrailingBlankLine(true).setLooseWhenPrevHasTrailingBlankLine(true).setLooseWhenContainsBlankLine(true).setOrderedListManualStart(false).setDelimiterMismatchToNewList(false).setItemTypeMismatchToNewList(false).setItemTypeMismatchToSubList(false).setEndOnDoubleBlank(false).setOrderedItemDotOnly(true).setItemMarkerSpace(true).setItemIndent(4).setCodeIndent(8).setNewItemCodeIndent(Integer.MAX_VALUE).setItemInterrupt(new ListOptions.MutableItemInterrupt().setBulletItemInterruptsParagraph(false).setOrderedItemInterruptsParagraph(false).setOrderedNonOneItemInterruptsParagraph(false).setEmptyBulletItemInterruptsParagraph(false).setEmptyOrderedItemInterruptsParagraph(false).setEmptyOrderedNonOneItemInterruptsParagraph(false).setBulletItemInterruptsItemParagraph(true).setOrderedItemInterruptsItemParagraph(true).setOrderedNonOneItemInterruptsItemParagraph(true).setEmptyBulletItemInterruptsItemParagraph(false).setEmptyOrderedItemInterruptsItemParagraph(false).setEmptyOrderedNonOneItemInterruptsItemParagraph(false).setEmptyBulletSubItemInterruptsItemParagraph(true).setEmptyOrderedSubItemInterruptsItemParagraph(true).setEmptyOrderedNonOneSubItemInterruptsItemParagraph(true));
        }
        if (this.family == COMMONMARK && this == COMMONMARK_0_26) {
            return new MutableListOptions((DataHolder) null).setEndOnDoubleBlank(true);
        }
        return new MutableListOptions((DataHolder) null);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public final MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        if (this == FIXED_INDENT) {
            getOptions(mutableDataHolder).setIn(mutableDataHolder).set((DataKey<DataKey<Boolean>>) Parser.STRONG_WRAPS_EMPHASIS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.LINKS_ALLOW_MATCHED_PARENTHESES, (DataKey<Boolean>) Boolean.FALSE);
        } else if (this == KRAMDOWN) {
            getOptions(mutableDataHolder).setIn(mutableDataHolder);
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.HEADING_NO_LEAD_SPACE, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.BLOCK_QUOTE_INTERRUPTS_PARAGRAPH, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) HtmlRenderer.RENDER_HEADER_ID, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<String>>) HtmlRenderer.SOFT_BREAK, (DataKey<String>) SequenceUtils.SPACE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSER, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_NON_BLOCK, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_MARKDOWN_INTERRUPTS_CLOSED, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_INDENTED_CODE_INTERRUPTS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.STRONG_WRAPS_EMPHASIS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.LINKS_ALLOW_MATCHED_PARENTHESES, (DataKey<Boolean>) Boolean.FALSE);
        } else if (this == MARKDOWN) {
            getOptions(mutableDataHolder).setIn(mutableDataHolder);
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.HEADING_NO_LEAD_SPACE, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.BLOCK_QUOTE_IGNORE_BLANK_LINE, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<String>>) HtmlRenderer.SOFT_BREAK, (DataKey<String>) SequenceUtils.SPACE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSER, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_NON_BLOCK, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_MARKDOWN_INTERRUPTS_CLOSED, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_INDENTED_CODE_INTERRUPTS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.STRONG_WRAPS_EMPHASIS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.LINKS_ALLOW_MATCHED_PARENTHESES, (DataKey<Boolean>) Boolean.FALSE);
        } else if (this == GITHUB_DOC) {
            getOptions(mutableDataHolder).setIn(mutableDataHolder);
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.BLOCK_QUOTE_IGNORE_BLANK_LINE, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.BLOCK_QUOTE_INTERRUPTS_PARAGRAPH, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.BLOCK_QUOTE_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HEADING_NO_LEAD_SPACE, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSER, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_NON_BLOCK, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_MARKDOWN_INTERRUPTS_CLOSED, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_INDENTED_CODE_INTERRUPTS, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.STRONG_WRAPS_EMPHASIS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.LINKS_ALLOW_MATCHED_PARENTHESES, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<String>>) HtmlRenderer.HEADER_ID_GENERATOR_TO_DASH_CHARS, (DataKey<String>) " -").set((DataKey<DataKey<String>>) HtmlRenderer.HEADER_ID_GENERATOR_NON_DASH_CHARS, (DataKey<String>) JavaConstant.Dynamic.DEFAULT_NAME).set((DataKey<DataKey<Boolean>>) HtmlRenderer.HEADER_ID_GENERATOR_NON_ASCII_TO_LOWERCASE, (DataKey<Boolean>) Boolean.FALSE);
        } else if (this == GITHUB) {
            getOptions(mutableDataHolder).setIn(mutableDataHolder);
            mutableDataHolder.set((DataKey<DataKey<String>>) HtmlRenderer.HEADER_ID_GENERATOR_TO_DASH_CHARS, (DataKey<String>) " -").set((DataKey<DataKey<String>>) HtmlRenderer.HEADER_ID_GENERATOR_NON_DASH_CHARS, (DataKey<String>) JavaConstant.Dynamic.DEFAULT_NAME).set((DataKey<DataKey<Boolean>>) HtmlRenderer.HEADER_ID_GENERATOR_NON_ASCII_TO_LOWERCASE, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) HtmlRenderer.HEADER_ID_REF_TEXT_TRIM_TRAILING_SPACES, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) HtmlRenderer.HEADER_ID_ADD_EMOJI_SHORTCUT, (DataKey<Boolean>) Boolean.TRUE);
        } else if (this == MULTI_MARKDOWN) {
            getOptions(mutableDataHolder).setIn(mutableDataHolder);
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.BLOCK_QUOTE_IGNORE_BLANK_LINE, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.BLOCK_QUOTE_WITH_LEAD_SPACES_INTERRUPTS_ITEM_PARAGRAPH, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) HtmlRenderer.RENDER_HEADER_ID, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) HtmlRenderer.HEADER_ID_GENERATOR_RESOLVE_DUPES, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<String>>) HtmlRenderer.HEADER_ID_GENERATOR_TO_DASH_CHARS, (DataKey<String>) "").set((DataKey<DataKey<Boolean>>) HtmlRenderer.HEADER_ID_GENERATOR_NO_DUPED_DASHES, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<String>>) HtmlRenderer.SOFT_BREAK, (DataKey<String>) SequenceUtils.SPACE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSER, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_NON_BLOCK, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_MARKDOWN_INTERRUPTS_CLOSED, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_INDENTED_CODE_INTERRUPTS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.STRONG_WRAPS_EMPHASIS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.LINKS_ALLOW_MATCHED_PARENTHESES, (DataKey<Boolean>) Boolean.FALSE);
        } else if (this == PEGDOWN || this == PEGDOWN_STRICT) {
            int intValue = PEGDOWN_EXTENSIONS.get(mutableDataHolder).intValue();
            getOptions(mutableDataHolder).setIn(mutableDataHolder);
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.BLOCK_QUOTE_EXTEND_TO_BLANK_LINE, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.BLOCK_QUOTE_IGNORE_BLANK_LINE, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.BLOCK_QUOTE_ALLOW_LEADING_SPACE, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.INDENTED_CODE_NO_TRAILING_BLANK_LINES, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Integer>>) Parser.HEADING_SETEXT_MARKER_LENGTH, (DataKey<Integer>) 3).set((DataKey<DataKey<Boolean>>) Parser.HEADING_NO_LEAD_SPACE, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<KeepType>>) Parser.REFERENCES_KEEP, (DataKey<KeepType>) KeepType.LAST).set((DataKey<DataKey<Boolean>>) Parser.PARSE_INNER_HTML_COMMENTS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.SPACE_IN_LINK_ELEMENTS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) HtmlRenderer.OBFUSCATE_EMAIL, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) HtmlRenderer.GENERATE_HEADER_ID, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) HtmlRenderer.HEADER_ID_GENERATOR_NO_DUPED_DASHES, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) HtmlRenderer.HEADER_ID_GENERATOR_RESOLVE_DUPES, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<String>>) HtmlRenderer.SOFT_BREAK, (DataKey<String>) SequenceUtils.SPACE).set((DataKey<DataKey<Boolean>>) Parser.STRONG_WRAPS_EMPHASIS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.LINKS_ALLOW_MATCHED_PARENTHESES, (DataKey<Boolean>) Boolean.FALSE);
            if (haveAny(intValue, 1024)) {
                mutableDataHolder.set((DataKey<DataKey<Boolean>>) HtmlRenderer.RENDER_HEADER_ID, (DataKey<Boolean>) Boolean.FALSE);
            }
            if (this == PEGDOWN_STRICT) {
                mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSER, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_NON_BLOCK, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_MARKDOWN_INTERRUPTS_CLOSED, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_INDENTED_CODE_INTERRUPTS, (DataKey<Boolean>) Boolean.FALSE);
            } else {
                mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSER, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_NON_BLOCK, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_FIRST_OPEN_TAG_ON_ONE_LINE, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_MARKDOWN_INTERRUPTS_CLOSED, (DataKey<Boolean>) Boolean.TRUE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_BLANK_LINE_INTERRUPTS_PARTIAL_TAG, (DataKey<Boolean>) Boolean.FALSE).set((DataKey<DataKey<Boolean>>) Parser.HTML_BLOCK_DEEP_PARSE_INDENTED_CODE_INTERRUPTS, (DataKey<Boolean>) Boolean.FALSE);
            }
        } else if (this == COMMONMARK_0_26 || this == COMMONMARK_0_27) {
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.STRONG_WRAPS_EMPHASIS, (DataKey<Boolean>) Boolean.TRUE);
            mutableDataHolder.set((DataKey<DataKey<Boolean>>) Parser.LINKS_ALLOW_MATCHED_PARENTHESES, (DataKey<Boolean>) Boolean.FALSE);
        }
        return mutableDataHolder;
    }

    public static boolean haveAny(int i, int i2) {
        return (i & i2) != 0;
    }

    public static boolean haveAll(int i, int i2) {
        return (i & i2) == i2;
    }
}
