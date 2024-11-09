package com.vladsch.flexmark.parser;

import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/InlineParserOptions.class */
public class InlineParserOptions {
    public final boolean matchLookaheadFirst;
    public final boolean parseMultiLineImageUrls;
    public final boolean hardLineBreakLimit;
    public final boolean spaceInLinkUrls;
    public final boolean spaceInLinkElements;
    public final boolean codeSoftLineBreaks;
    public final boolean inlineDelimiterDirectionalPunctuations;
    public final boolean linksAllowMatchedParentheses;
    public final boolean wwwAutoLinkElement;
    public final boolean intellijDummyIdentifier;
    public final boolean parseJekyllMacrosInUrls;
    public final boolean useHardcodedLinkAddressParser;
    public final boolean linkTextPriorityOverLinkRef;

    public InlineParserOptions(DataHolder dataHolder) {
        this.matchLookaheadFirst = Parser.MATCH_NESTED_LINK_REFS_FIRST.get(dataHolder).booleanValue();
        this.parseMultiLineImageUrls = Parser.PARSE_MULTI_LINE_IMAGE_URLS.get(dataHolder).booleanValue();
        this.hardLineBreakLimit = Parser.HARD_LINE_BREAK_LIMIT.get(dataHolder).booleanValue();
        this.spaceInLinkUrls = Parser.SPACE_IN_LINK_URLS.get(dataHolder).booleanValue();
        this.spaceInLinkElements = Parser.SPACE_IN_LINK_ELEMENTS.get(dataHolder).booleanValue();
        this.wwwAutoLinkElement = Parser.WWW_AUTO_LINK_ELEMENT.get(dataHolder).booleanValue();
        this.intellijDummyIdentifier = Parser.INTELLIJ_DUMMY_IDENTIFIER.get(dataHolder).booleanValue();
        this.parseJekyllMacrosInUrls = Parser.PARSE_JEKYLL_MACROS_IN_URLS.get(dataHolder).booleanValue();
        this.useHardcodedLinkAddressParser = Parser.USE_HARDCODED_LINK_ADDRESS_PARSER.get(dataHolder).booleanValue();
        this.codeSoftLineBreaks = Parser.CODE_SOFT_LINE_BREAKS.get(dataHolder).booleanValue();
        this.inlineDelimiterDirectionalPunctuations = Parser.INLINE_DELIMITER_DIRECTIONAL_PUNCTUATIONS.get(dataHolder).booleanValue();
        this.linksAllowMatchedParentheses = Parser.LINKS_ALLOW_MATCHED_PARENTHESES.get(dataHolder).booleanValue();
        this.linkTextPriorityOverLinkRef = Parser.LINK_TEXT_PRIORITY_OVER_LINK_REF.get(dataHolder).booleanValue();
    }
}
