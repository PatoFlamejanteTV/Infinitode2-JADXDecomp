package com.vladsch.flexmark.util.data;

import com.vladsch.flexmark.util.misc.Extension;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/SharedDataKeys.class */
public class SharedDataKeys {
    public static final DataKey<Collection<Extension>> EXTENSIONS = new DataKey<>("EXTENSIONS", Extension.EMPTY_LIST);
    public static final DataKey<Boolean> HEADING_NO_ATX_SPACE = new DataKey<>("HEADING_NO_ATX_SPACE", Boolean.FALSE);
    public static final DataKey<Boolean> ESCAPE_HEADING_NO_ATX_SPACE;
    public static final DataKey<Boolean> HTML_FOR_TRANSLATOR;
    public static final DataKey<Boolean> INTELLIJ_DUMMY_IDENTIFIER;
    public static final DataKey<Boolean> PARSE_INNER_HTML_COMMENTS;
    public static final DataKey<Boolean> BLANK_LINES_IN_AST;
    public static final DataKey<String> TRANSLATION_HTML_BLOCK_TAG_PATTERN;
    public static final DataKey<String> TRANSLATION_HTML_INLINE_TAG_PATTERN;
    public static final DataKey<String> TRANSLATION_AUTOLINK_TAG_PATTERN;
    public static final DataKey<Integer> RENDERER_MAX_TRAILING_BLANK_LINES;
    public static final DataKey<Integer> RENDERER_MAX_BLANK_LINES;
    public static final DataKey<Integer> INDENT_SIZE;
    public static final DataKey<Boolean> PERCENT_ENCODE_URLS;
    public static final DataKey<Boolean> HEADER_ID_GENERATOR_RESOLVE_DUPES;
    public static final DataKey<String> HEADER_ID_GENERATOR_TO_DASH_CHARS;
    public static final DataKey<String> HEADER_ID_GENERATOR_NON_DASH_CHARS;
    public static final DataKey<Boolean> HEADER_ID_GENERATOR_NO_DUPED_DASHES;
    public static final DataKey<Boolean> HEADER_ID_GENERATOR_NON_ASCII_TO_LOWERCASE;
    public static final DataKey<Boolean> HEADER_ID_REF_TEXT_TRIM_LEADING_SPACES;
    public static final DataKey<Boolean> HEADER_ID_REF_TEXT_TRIM_TRAILING_SPACES;
    public static final DataKey<Boolean> HEADER_ID_ADD_EMOJI_SHORTCUT;
    public static final DataKey<Boolean> RENDER_HEADER_ID;
    public static final DataKey<Boolean> GENERATE_HEADER_ID;
    public static final DataKey<Boolean> DO_NOT_RENDER_LINKS;
    public static final DataKey<Integer> FORMATTER_MAX_BLANK_LINES;
    public static final DataKey<Integer> FORMATTER_MAX_TRAILING_BLANK_LINES;
    public static final DataKey<Boolean> BLOCK_QUOTE_BLANK_LINES;
    public static final DataKey<Boolean> APPLY_SPECIAL_LEAD_IN_HANDLERS;
    public static final DataKey<Boolean> ESCAPE_SPECIAL_CHARS;
    public static final DataKey<Boolean> ESCAPE_NUMBERED_LEAD_IN;
    public static final DataKey<Boolean> UNESCAPE_SPECIAL_CHARS;
    public static final DataKey<Boolean> RUNNING_TESTS;

    static {
        Boolean bool = Boolean.FALSE;
        DataKey<Boolean> dataKey = HEADING_NO_ATX_SPACE;
        dataKey.getClass();
        ESCAPE_HEADING_NO_ATX_SPACE = new DataKey<>("ESCAPE_HEADING_NO_ATX_SPACE", bool, dataKey::get);
        HTML_FOR_TRANSLATOR = new DataKey<>("HTML_FOR_TRANSLATOR", Boolean.FALSE);
        INTELLIJ_DUMMY_IDENTIFIER = new DataKey<>("INTELLIJ_DUMMY_IDENTIFIER", Boolean.FALSE);
        PARSE_INNER_HTML_COMMENTS = new DataKey<>("PARSE_INNER_HTML_COMMENTS", Boolean.FALSE);
        BLANK_LINES_IN_AST = new DataKey<>("BLANK_LINES_IN_AST", Boolean.FALSE);
        TRANSLATION_HTML_BLOCK_TAG_PATTERN = new DataKey<>("TRANSLATION_HTML_BLOCK_TAG_PATTERN", "___(?:\\d+)_");
        TRANSLATION_HTML_INLINE_TAG_PATTERN = new DataKey<>("TRANSLATION_HTML_INLINE_TAG_PATTERN", "__(?:\\d+)_");
        TRANSLATION_AUTOLINK_TAG_PATTERN = new DataKey<>("TRANSLATION_AUTOLINK_TAG_PATTERN", "____(?:\\d+)_");
        RENDERER_MAX_TRAILING_BLANK_LINES = new DataKey<>("RENDERER_MAX_TRAILING_BLANK_LINES", 1);
        RENDERER_MAX_BLANK_LINES = new DataKey<>("RENDERER_MAX_BLANK_LINES", 1);
        INDENT_SIZE = new DataKey<>("INDENT_SIZE", 0);
        PERCENT_ENCODE_URLS = new DataKey<>("PERCENT_ENCODE_URLS", Boolean.FALSE);
        HEADER_ID_GENERATOR_RESOLVE_DUPES = new DataKey<>("HEADER_ID_GENERATOR_RESOLVE_DUPES", Boolean.TRUE);
        HEADER_ID_GENERATOR_TO_DASH_CHARS = new DataKey<>("HEADER_ID_GENERATOR_TO_DASH_CHARS", " -_");
        HEADER_ID_GENERATOR_NON_DASH_CHARS = new DataKey<>("HEADER_ID_GENERATOR_NON_DASH_CHARS", "");
        HEADER_ID_GENERATOR_NO_DUPED_DASHES = new DataKey<>("HEADER_ID_GENERATOR_NO_DUPED_DASHES", Boolean.FALSE);
        HEADER_ID_GENERATOR_NON_ASCII_TO_LOWERCASE = new DataKey<>("HEADER_ID_GENERATOR_NON_ASCII_TO_LOWERCASE", Boolean.TRUE);
        HEADER_ID_REF_TEXT_TRIM_LEADING_SPACES = new DataKey<>("HEADER_ID_REF_TEXT_TRIM_LEADING_SPACES", Boolean.TRUE);
        HEADER_ID_REF_TEXT_TRIM_TRAILING_SPACES = new DataKey<>("HEADER_ID_REF_TEXT_TRIM_TRAILING_SPACES", Boolean.TRUE);
        HEADER_ID_ADD_EMOJI_SHORTCUT = new DataKey<>("HEADER_ID_ADD_EMOJI_SHORTCUT", Boolean.FALSE);
        RENDER_HEADER_ID = new DataKey<>("RENDER_HEADER_ID", Boolean.FALSE);
        GENERATE_HEADER_ID = new DataKey<>("GENERATE_HEADER_ID", Boolean.TRUE);
        DO_NOT_RENDER_LINKS = new DataKey<>("DO_NOT_RENDER_LINKS", Boolean.FALSE);
        FORMATTER_MAX_BLANK_LINES = new DataKey<>("FORMATTER_MAX_BLANK_LINES", 2);
        FORMATTER_MAX_TRAILING_BLANK_LINES = new DataKey<>("FORMATTER_MAX_TRAILING_BLANK_LINES", 1);
        BLOCK_QUOTE_BLANK_LINES = new DataKey<>("BLOCK_QUOTE_BLANK_LINES", Boolean.TRUE);
        APPLY_SPECIAL_LEAD_IN_HANDLERS = new DataKey<>("APPLY_SPECIAL_LEAD_IN_HANDLERS", Boolean.TRUE);
        Boolean bool2 = Boolean.TRUE;
        DataKey<Boolean> dataKey2 = APPLY_SPECIAL_LEAD_IN_HANDLERS;
        dataKey2.getClass();
        ESCAPE_SPECIAL_CHARS = new DataKey<>("ESCAPE_SPECIAL_CHARS", bool2, dataKey2::get);
        Boolean bool3 = Boolean.TRUE;
        DataKey<Boolean> dataKey3 = APPLY_SPECIAL_LEAD_IN_HANDLERS;
        dataKey3.getClass();
        ESCAPE_NUMBERED_LEAD_IN = new DataKey<>("ESCAPE_NUMBERED_LEAD_IN", bool3, dataKey3::get);
        Boolean bool4 = Boolean.TRUE;
        DataKey<Boolean> dataKey4 = APPLY_SPECIAL_LEAD_IN_HANDLERS;
        dataKey4.getClass();
        UNESCAPE_SPECIAL_CHARS = new DataKey<>("UNESCAPE_SPECIAL_CHARS", bool4, dataKey4::get);
        RUNNING_TESTS = new DataKey<>("RUNNING_TESTS", Boolean.FALSE);
    }
}
