package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.TableFormatOptions;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.HashMap;
import java.util.function.Function;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/Parsing.class */
public class Parsing {
    public static final char INTELLIJ_DUMMY_IDENTIFIER_CHAR = 31;
    public final DataHolder options;
    private static final String ST_EOL = "(?:\r\n|\r|\n)";
    public final Pattern LINK_DESTINATION_ANGLES;
    public final Pattern LINK_DESTINATION;
    public final Pattern LINK_DESTINATION_MATCHED_PARENS;
    public final Pattern LINK_DESTINATION_MATCHED_PARENS_NOSP;
    private static final String ST_EXCLUDED_0_TO_SPACE_IDI = "��-\u001e ";
    private static final String ST_EXCLUDED_0_TO_SPACE_NO_IDI = "��- ";
    private static final String ST_ADDITIONAL_CHARS_IDI = "\u001f";
    private static final String ST_ADDITIONAL_CHARS_NO_IDI = "";
    private static final String ST_ADDITIONAL_CHARS_SET_IDI = "[\u001f]";
    private static final String ST_ADDITIONAL_CHARS_SET_NO_IDI = "";
    public static final String ST_HTMLCOMMENT = "<!---->|<!--(?:-?[^>-])(?:-?[^-])*-->";
    public static final String ST_PROCESSINGINSTRUCTION = "[<][?].*?[?][>]";
    public static final String ST_CDATA = "<!\\[CDATA\\[[\\s\\S]*?\\]\\]>";
    public static final String ST_SINGLEQUOTEDVALUE = "'[^']*'";
    public static final String ST_DOUBLEQUOTEDVALUE = "\"[^\"]*\"";
    private static final String ST_ASCII_PUNCTUATION = "'!\"#\\$%&\\*\\+,\\-\\./:;=\\?@\\\\\\^_`\\|~";
    private static final String ST_ASCII_OPEN_PUNCTUATION = "\\(<\\[\\{";
    private static final String ST_ASCII_CLOSE_PUNCTUATION = "\\)>\\]\\}";
    public final Pattern EMAIL_AUTOLINK;
    public final Pattern AUTOLINK;
    public final Pattern WWW_AUTOLINK;
    private static final String ST_TAGNAME_IDI = "[A-Za-z\u001f][A-Za-z0-9\u001f-]*";
    private static final String ST_TAGNAME_NO_IDI = "[A-Za-z][A-Za-z0-9-]*";
    private static final String ST_UNQUOTEDVALUE_IDI = "[^\"'=<>{}`��-\u001e ]+";
    private static final String ST_UNQUOTEDVALUE_NO_IDI = "[^\"'=<>{}`��- ]+";
    private static final String ST_ATTRIBUTENAME_IDI = "[a-zA-Z\u001f_:][a-zA-Z0-9\u001f:._-]*";
    private static final String ST_ATTRIBUTENAME_NO_IDI = "[a-zA-Z_:][a-zA-Z0-9:._-]*";
    private static final String ST_ATTRIBUTEVALUE_IDI = "(?:[^\"'=<>{}`��-\u001e ]+|'[^']*'|\"[^\"]*\")";
    private static final String ST_ATTRIBUTEVALUE_NO_IDI = "(?:[^\"'=<>{}`��- ]+|'[^']*'|\"[^\"]*\")";
    private static final String ST_ATTRIBUTEVALUESPEC_IDI = "(?:\\s*=\\s*(?:[^\"'=<>{}`��-\u001e ]+|'[^']*'|\"[^\"]*\"))";
    private static final String ST_ATTRIBUTEVALUESPEC_NO_IDI = "(?:\\s*=\\s*(?:[^\"'=<>{}`��- ]+|'[^']*'|\"[^\"]*\"))";
    private static final String ST_CLOSETAG_IDI = "</[A-Za-z\u001f][A-Za-z0-9\u001f-]*\\s*[>]";
    private static final String ST_CLOSETAG_NO_IDI = "</[A-Za-z][A-Za-z0-9-]*\\s*[>]";
    private static final String ST_ATTRIBUTE_IDI = "(?:\\s+[a-zA-Z\u001f_:][a-zA-Z0-9\u001f:._-]*(?:\\s*=\\s*(?:[^\"'=<>{}`��-\u001e ]+|'[^']*'|\"[^\"]*\"))?)";
    private static final String ST_ATTRIBUTE_NO_IDI = "(?:\\s+[a-zA-Z_:][a-zA-Z0-9:._-]*(?:\\s*=\\s*(?:[^\"'=<>{}`��- ]+|'[^']*'|\"[^\"]*\"))?)";
    private static final String ST_DECLARATION_IDI = "<![A-Z\u001f]+\\s+[^>]*>";
    private static final String ST_DECLARATION_NO_IDI = "<![A-Z]+\\s+[^>]*>";
    private static final String ST_ENTITY_IDI = "&(?:#x[a-f0-9\u001f]{1,8}|#[0-9]{1,8}|[a-z\u001f][a-z0-9\u001f]{1,31});";
    private static final String ST_ENTITY_NO_IDI = "&(?:#x[a-f0-9]{1,8}|#[0-9]{1,8}|[a-z][a-z0-9]{1,31});";
    private static final String ST_IN_BRACES_W_SP_IDI = "\\{\\{(?:[^{}\\\\��-\u001e ]| |\t)*\\}\\}";
    private static final String ST_IN_BRACES_W_SP_NO_IDI = "\\{\\{(?:[^{}\\\\��- ]| |\t)*\\}\\}";
    private static final String ST_REG_CHAR_IDI = "[^\\\\()��-\u001e ]";
    private static final String ST_REG_CHAR_NO_IDI = "[^\\\\()��- ]";
    private static final String ST_REG_CHAR_SP_IDI = "[^\\\\()��-\u001e ]| (?![\"'])";
    private static final String ST_REG_CHAR_SP_NO_IDI = "[^\\\\()��- ]| (?![\"'])";
    private static final String ST_OPENTAG_IDI = "<[A-Za-z\u001f][A-Za-z0-9\u001f-]*(?:\\s+[a-zA-Z\u001f_:][a-zA-Z0-9\u001f:._-]*(?:\\s*=\\s*(?:[^\"'=<>{}`��-\u001e ]+|'[^']*'|\"[^\"]*\"))?)*\\s*/?>";
    private static final String ST_OPENTAG_NO_IDI = "<[A-Za-z][A-Za-z0-9-]*(?:\\s+[a-zA-Z_:][a-zA-Z0-9:._-]*(?:\\s*=\\s*(?:[^\"'=<>{}`��- ]+|'[^']*'|\"[^\"]*\"))?)*\\s*/?>";
    private static final String ST_REG_CHAR_PARENS_IDI = "[^\\\\��-\u001e ]";
    private static final String ST_REG_CHAR_PARENS_NO_IDI = "[^\\\\��- ]";
    private static final String ST_REG_CHAR_SP_PARENS_IDI = "[^\\\\��-\u001e ]| (?![\"'])";
    private static final String ST_REG_CHAR_SP_PARENS_NO_IDI = "[^\\\\��- ]| (?![\"'])";
    public final String ADDITIONAL_CHARS;
    public final String EXCLUDED_0_TO_SPACE;
    public final String REG_CHAR;
    public final String REG_CHAR_PARENS;
    public final String REG_CHAR_SP;
    public final String REG_CHAR_SP_PARENS;
    public final String IN_PARENS_NOSP;
    public final String IN_PARENS_W_SP;
    public final String IN_MATCHED_PARENS_NOSP;
    public final String IN_MATCHED_PARENS_W_SP;
    public final String IN_BRACES_W_SP;
    public final String DECLARATION;
    public final String ENTITY;
    public final String TAGNAME;
    public final String ATTRIBUTENAME;
    public final String UNQUOTEDVALUE;
    public final String ATTRIBUTEVALUE;
    public final String ATTRIBUTEVALUESPEC;
    public final String ATTRIBUTE;
    public final String OPENTAG;
    public final String CLOSETAG;
    public final String HTMLTAG;
    public final Pattern ENTITY_HERE;
    public final Pattern HTML_TAG;
    public final Pattern LIST_ITEM_MARKER;
    public final int CODE_BLOCK_INDENT;
    public final boolean intellijDummyIdentifier;
    public final boolean htmlForTranslator;
    public final String translationHtmlInlineTagPattern;
    public final String translationAutolinkTagPattern;
    public final boolean spaceInLinkUrl;
    public final boolean parseJekyllMacroInLinkUrl;
    public final String itemPrefixChars;
    public final boolean listsItemMarkerSpace;
    public final boolean listsOrderedItemDotOnly;
    public static final String INTELLIJ_DUMMY_IDENTIFIER = TableFormatOptions.INTELLIJ_DUMMY_IDENTIFIER;
    private static final String ST_ESCAPED_CHAR = "\\\\" + Escaping.ESCAPABLE;
    private static final Pattern ST_LINK_LABEL = Pattern.compile("^\\[(?:[^\\\\\\[\\]]|" + ST_ESCAPED_CHAR + "|\\\\){0,999}\\]");
    private static final String ST_LINK_TITLE_STRING = "(?:\"(" + ST_ESCAPED_CHAR + "|[^\"\\x00])*\"|'(" + ST_ESCAPED_CHAR + "|[^'\\x00])*'|\\((" + ST_ESCAPED_CHAR + "|[^)\\x00])*\\))";
    private static final Pattern ST_LINK_TITLE = Pattern.compile("^" + ST_LINK_TITLE_STRING);
    private static final Pattern ST_PUNCTUATION = Pattern.compile("^['!\"#\\$%&\\*\\+,\\-\\./:;=\\?@\\\\\\^_`\\|~\\(<\\[\\{\\)>\\]\\}\\p{Pc}\\p{Pd}\\p{Pe}\\p{Pf}\\p{Pi}\\p{Po}\\p{Ps}]");
    private static final Pattern ST_PUNCTUATION_OPEN = Pattern.compile("^['!\"#\\$%&\\*\\+,\\-\\./:;=\\?@\\\\\\^_`\\|~\\(<\\[\\{]|[\\p{Pc}\\p{Pd}\\p{Pe}\\p{Pf}\\p{Pi}\\p{Po}\\p{Ps}]&&[^\\)>\\]\\}]");
    private static final Pattern ST_PUNCTUATION_CLOSE = Pattern.compile("^['!\"#\\$%&\\*\\+,\\-\\./:;=\\?@\\\\\\^_`\\|~\\)>\\]\\}]|[\\p{Pc}\\p{Pd}\\p{Pe}\\p{Pf}\\p{Pi}\\p{Po}\\p{Ps}]&&[^\\(<\\[\\{]");
    private static final Pattern ST_PUNCTUATION_ONLY = Pattern.compile("^['!\"#\\$%&\\*\\+,\\-\\./:;=\\?@\\\\\\^_`\\|~\\p{Pc}\\p{Pd}\\p{Pe}\\p{Pf}\\p{Pi}\\p{Po}\\p{Ps}]&&[^\\(<\\[\\{\\)>\\]\\}]");
    private static final Pattern ST_PUNCTUATION_OPEN_ONLY = Pattern.compile("^[\\(<\\[\\{]");
    private static final Pattern ST_PUNCTUATION_CLOSE_ONLY = Pattern.compile("^[\\)>\\]\\}]");
    private static final Pattern ST_ESCAPABLE = Pattern.compile("^" + Escaping.ESCAPABLE);
    private static final Pattern ST_TICKS = Pattern.compile("`+");
    private static final Pattern ST_TICKS_HERE = Pattern.compile("^`+");
    private static final Pattern ST_SPNL = Pattern.compile("^(?:[ \t])*(?:(?:\r\n|\r|\n)(?:[ \t])*)?");
    private static final Pattern ST_SPNL_URL = Pattern.compile("^(?:[ \t])*(?:\r\n|\r|\n)");
    private static final Pattern ST_SPNI = Pattern.compile("^ {0,3}");
    private static final Pattern ST_SP = Pattern.compile("^(?:[ \t])*");
    private static final Pattern ST_REST_OF_LINE = Pattern.compile("^.*(?:\r\n|\r|\n)");
    private static final Pattern ST_UNICODE_WHITESPACE_CHAR = Pattern.compile("^[\\p{Zs}\t\r\n\f]");
    private static final Pattern ST_WHITESPACE = Pattern.compile("\\s+");
    private static final Pattern ST_FINAL_SPACE = Pattern.compile(" *$");
    private static final Pattern ST_LINE_END = Pattern.compile("^[ \t]*(?:(?:\r\n|\r|\n)|$)");
    private static final Pattern ST_LINK_DESTINATION_ANGLES_SPC = Pattern.compile("^(?:[<](?:[^<> \\t\\n\\\\\\x00]|" + ST_ESCAPED_CHAR + "|\\\\| (?![\"']))*[>])");
    private static final Pattern ST_LINK_DESTINATION_ANGLES_NO_SPC = Pattern.compile("^(?:[<](?:[^<> \\t\\n\\\\\\x00]|" + ST_ESCAPED_CHAR + "|\\\\)*[>])");
    private static final String ST_IN_MATCHED_PARENS_NOSP_IDI = "\\(([^\\\\()��-\u001e ]|" + ST_ESCAPED_CHAR + ")*\\)";
    private static final String ST_IN_MATCHED_PARENS_NOSP_NO_IDI = "\\(([^\\\\()��- ]|" + ST_ESCAPED_CHAR + ")*\\)";
    private static final String ST_IN_MATCHED_PARENS_W_SP_IDI = "\\(([^\\\\()��-\u001e ]| (?![\"'])|" + ST_ESCAPED_CHAR + ")*\\)";
    private static final String ST_IN_MATCHED_PARENS_W_SP_NO_IDI = "\\(([^\\\\()��- ]| (?![\"'])|" + ST_ESCAPED_CHAR + ")*\\)";
    private static final String ST_IN_PARENS_NOSP_IDI = "\\(([^\\\\()��-\u001e ]|" + ST_ESCAPED_CHAR + ")*\\)";
    private static final String ST_IN_PARENS_NOSP_NO_IDI = "\\(([^\\\\()��- ]|" + ST_ESCAPED_CHAR + ")*\\)";
    private static final String ST_IN_PARENS_W_SP_IDI = "\\(([^\\\\()��-\u001e ]| (?![\"'])|" + ST_ESCAPED_CHAR + ")*\\)";
    private static final String ST_IN_PARENS_W_SP_NO_IDI = "\\(([^\\\\()��- ]| (?![\"'])|" + ST_ESCAPED_CHAR + ")*\\)";
    private static final Pattern ST_ENTITY_HERE_IDI = Pattern.compile("^&(?:#x[a-f0-9\u001f]{1,8}|#[0-9]{1,8}|[a-z\u001f][a-z0-9\u001f]{1,31});", 2);
    private static final Pattern ST_ENTITY_HERE_NO_IDI = Pattern.compile("^&(?:#x[a-f0-9]{1,8}|#[0-9]{1,8}|[a-z][a-z0-9]{1,31});", 2);
    static final HashMap<String, HashMap<PatternTypeFlags, Pattern>> cachedPatterns = new HashMap<>();
    public final String EOL = ST_EOL;
    public final String ESCAPED_CHAR = ST_ESCAPED_CHAR;
    public final Pattern LINK_LABEL = ST_LINK_LABEL;
    public final String LINK_TITLE_STRING = ST_LINK_TITLE_STRING;
    public final Pattern LINK_TITLE = ST_LINK_TITLE;
    public final String HTMLCOMMENT = ST_HTMLCOMMENT;
    public final String PROCESSINGINSTRUCTION = ST_PROCESSINGINSTRUCTION;
    public final String CDATA = ST_CDATA;
    public final String SINGLEQUOTEDVALUE = ST_SINGLEQUOTEDVALUE;
    public final String DOUBLEQUOTEDVALUE = ST_DOUBLEQUOTEDVALUE;
    public final String ASCII_PUNCTUATION = ST_ASCII_PUNCTUATION;
    public final String ASCII_OPEN_PUNCTUATION = ST_ASCII_OPEN_PUNCTUATION;
    public final String ASCII_CLOSE_PUNCTUATION = ST_ASCII_CLOSE_PUNCTUATION;
    public final Pattern PUNCTUATION = ST_PUNCTUATION;
    public final Pattern PUNCTUATION_OPEN = ST_PUNCTUATION_OPEN;
    public final Pattern PUNCTUATION_CLOSE = ST_PUNCTUATION_CLOSE;
    public final Pattern PUNCTUATION_ONLY = ST_PUNCTUATION_ONLY;
    public final Pattern PUNCTUATION_OPEN_ONLY = ST_PUNCTUATION_OPEN_ONLY;
    public final Pattern PUNCTUATION_CLOSE_ONLY = ST_PUNCTUATION_CLOSE_ONLY;
    public final Pattern ESCAPABLE = ST_ESCAPABLE;
    public final Pattern TICKS = ST_TICKS;
    public final Pattern TICKS_HERE = ST_TICKS_HERE;
    public final Pattern SPNL = ST_SPNL;
    public final Pattern SPNL_URL = ST_SPNL_URL;
    public final Pattern SPNI = ST_SPNI;
    public final Pattern SP = ST_SP;
    public final Pattern REST_OF_LINE = ST_REST_OF_LINE;
    public final Pattern UNICODE_WHITESPACE_CHAR = ST_UNICODE_WHITESPACE_CHAR;
    public final Pattern WHITESPACE = ST_WHITESPACE;
    public final Pattern FINAL_SPACE = ST_FINAL_SPACE;
    public final Pattern LINE_END = ST_LINE_END;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/Parsing$PatternTypeFlags.class */
    public static class PatternTypeFlags {
        final Boolean intellijDummyIdentifier;
        final Boolean htmlForTranslator;
        final String translationHtmlInlineTagPattern;
        final String translationAutolinkTagPattern;
        final Boolean spaceInLinkUrl;
        final Boolean parseJekyllMacroInLinkUrl;
        final String itemPrefixChars;
        final Boolean listsItemMarkerSpace;
        final Boolean listsOrderedItemDotOnly;

        PatternTypeFlags(DataHolder dataHolder) {
            this.intellijDummyIdentifier = Parser.INTELLIJ_DUMMY_IDENTIFIER.get(dataHolder);
            this.htmlForTranslator = Parser.HTML_FOR_TRANSLATOR.get(dataHolder);
            this.translationHtmlInlineTagPattern = Parser.TRANSLATION_HTML_INLINE_TAG_PATTERN.get(dataHolder);
            this.translationAutolinkTagPattern = Parser.TRANSLATION_AUTOLINK_TAG_PATTERN.get(dataHolder);
            this.spaceInLinkUrl = Parser.SPACE_IN_LINK_URLS.get(dataHolder);
            this.parseJekyllMacroInLinkUrl = Parser.PARSE_JEKYLL_MACROS_IN_URLS.get(dataHolder);
            this.itemPrefixChars = Parser.LISTS_ITEM_PREFIX_CHARS.get(dataHolder);
            this.listsItemMarkerSpace = Parser.LISTS_ITEM_MARKER_SPACE.get(dataHolder);
            this.listsOrderedItemDotOnly = Parser.LISTS_ORDERED_ITEM_DOT_ONLY.get(dataHolder);
        }

        public PatternTypeFlags(Boolean bool, Boolean bool2, String str, String str2, Boolean bool3, Boolean bool4, String str3, Boolean bool5, Boolean bool6) {
            this.intellijDummyIdentifier = bool;
            this.htmlForTranslator = bool2;
            this.translationHtmlInlineTagPattern = str;
            this.translationAutolinkTagPattern = str2;
            this.spaceInLinkUrl = bool3;
            this.parseJekyllMacroInLinkUrl = bool4;
            this.itemPrefixChars = str3;
            this.listsItemMarkerSpace = bool5;
            this.listsOrderedItemDotOnly = bool6;
        }

        PatternTypeFlags withJekyllMacroInLinkUrl() {
            return new PatternTypeFlags(this.intellijDummyIdentifier, null, null, null, null, this.parseJekyllMacroInLinkUrl, null, null, null);
        }

        PatternTypeFlags withJekyllMacroSpaceInLinkUrl() {
            return new PatternTypeFlags(this.intellijDummyIdentifier, null, null, null, this.spaceInLinkUrl, this.parseJekyllMacroInLinkUrl, null, null, null);
        }

        PatternTypeFlags withHtmlTranslator() {
            return new PatternTypeFlags(this.intellijDummyIdentifier, this.htmlForTranslator, this.translationHtmlInlineTagPattern, this.translationAutolinkTagPattern, null, null, null, null, null);
        }

        PatternTypeFlags withItemPrefixChars() {
            return new PatternTypeFlags(null, null, null, null, null, null, this.itemPrefixChars, this.listsItemMarkerSpace, this.listsOrderedItemDotOnly);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            PatternTypeFlags patternTypeFlags = (PatternTypeFlags) obj;
            if (this.intellijDummyIdentifier != null && !this.intellijDummyIdentifier.equals(patternTypeFlags.intellijDummyIdentifier)) {
                return false;
            }
            if (this.htmlForTranslator != null && !this.htmlForTranslator.equals(patternTypeFlags.htmlForTranslator)) {
                return false;
            }
            if (this.translationHtmlInlineTagPattern != null && !this.translationHtmlInlineTagPattern.equals(patternTypeFlags.translationHtmlInlineTagPattern)) {
                return false;
            }
            if (this.translationAutolinkTagPattern != null && !this.translationAutolinkTagPattern.equals(patternTypeFlags.translationAutolinkTagPattern)) {
                return false;
            }
            if (this.spaceInLinkUrl != null && !this.spaceInLinkUrl.equals(patternTypeFlags.spaceInLinkUrl)) {
                return false;
            }
            if (this.parseJekyllMacroInLinkUrl != null && !this.parseJekyllMacroInLinkUrl.equals(patternTypeFlags.parseJekyllMacroInLinkUrl)) {
                return false;
            }
            if (this.itemPrefixChars != null && !this.itemPrefixChars.equals(patternTypeFlags.itemPrefixChars)) {
                return false;
            }
            if (this.listsItemMarkerSpace == null || this.listsItemMarkerSpace.equals(patternTypeFlags.listsItemMarkerSpace)) {
                return this.listsOrderedItemDotOnly == null || this.listsOrderedItemDotOnly.equals(patternTypeFlags.listsOrderedItemDotOnly);
            }
            return false;
        }

        public int hashCode() {
            return ((((((((((((((((this.intellijDummyIdentifier != null ? this.intellijDummyIdentifier.hashCode() : 0) * 31) + (this.htmlForTranslator != null ? this.htmlForTranslator.hashCode() : 0)) * 31) + (this.translationHtmlInlineTagPattern != null ? this.translationHtmlInlineTagPattern.hashCode() : 0)) * 31) + (this.translationAutolinkTagPattern != null ? this.translationAutolinkTagPattern.hashCode() : 0)) * 31) + (this.spaceInLinkUrl != null ? this.spaceInLinkUrl.hashCode() : 0)) * 31) + (this.parseJekyllMacroInLinkUrl != null ? this.parseJekyllMacroInLinkUrl.hashCode() : 0)) * 31) + (this.itemPrefixChars != null ? this.itemPrefixChars.hashCode() : 0)) * 31) + (this.listsItemMarkerSpace != null ? this.listsItemMarkerSpace.hashCode() : 0)) * 31) + (this.listsOrderedItemDotOnly != null ? this.listsOrderedItemDotOnly.hashCode() : 0);
        }
    }

    static Pattern getCachedPattern(String str, PatternTypeFlags patternTypeFlags, Function<PatternTypeFlags, Pattern> function) {
        return cachedPatterns.computeIfAbsent(str, str2 -> {
            return new HashMap();
        }).computeIfAbsent(patternTypeFlags, function);
    }

    public Parsing(DataHolder dataHolder) {
        this.options = dataHolder;
        this.CODE_BLOCK_INDENT = Parser.CODE_BLOCK_INDENT.get(dataHolder).intValue();
        PatternTypeFlags patternTypeFlags = new PatternTypeFlags(dataHolder);
        this.intellijDummyIdentifier = patternTypeFlags.intellijDummyIdentifier.booleanValue();
        this.htmlForTranslator = patternTypeFlags.htmlForTranslator.booleanValue();
        this.translationHtmlInlineTagPattern = patternTypeFlags.translationHtmlInlineTagPattern;
        this.translationAutolinkTagPattern = patternTypeFlags.translationAutolinkTagPattern;
        this.spaceInLinkUrl = patternTypeFlags.spaceInLinkUrl.booleanValue();
        this.parseJekyllMacroInLinkUrl = patternTypeFlags.parseJekyllMacroInLinkUrl.booleanValue();
        this.itemPrefixChars = patternTypeFlags.itemPrefixChars;
        this.listsItemMarkerSpace = patternTypeFlags.listsItemMarkerSpace.booleanValue();
        this.listsOrderedItemDotOnly = patternTypeFlags.listsOrderedItemDotOnly.booleanValue();
        if (this.intellijDummyIdentifier) {
            this.ADDITIONAL_CHARS = ST_ADDITIONAL_CHARS_IDI;
            this.EXCLUDED_0_TO_SPACE = ST_EXCLUDED_0_TO_SPACE_IDI;
            this.REG_CHAR = ST_REG_CHAR_IDI;
            this.REG_CHAR_PARENS = ST_REG_CHAR_PARENS_IDI;
            this.REG_CHAR_SP = ST_REG_CHAR_SP_IDI;
            this.REG_CHAR_SP_PARENS = ST_REG_CHAR_SP_PARENS_IDI;
            this.IN_PARENS_NOSP = ST_IN_PARENS_NOSP_IDI;
            this.IN_PARENS_W_SP = ST_IN_PARENS_W_SP_IDI;
            this.IN_MATCHED_PARENS_NOSP = ST_IN_MATCHED_PARENS_NOSP_IDI;
            this.IN_MATCHED_PARENS_W_SP = ST_IN_MATCHED_PARENS_W_SP_IDI;
            this.IN_BRACES_W_SP = ST_IN_BRACES_W_SP_IDI;
            this.DECLARATION = ST_DECLARATION_IDI;
            this.ENTITY = ST_ENTITY_IDI;
            this.TAGNAME = ST_TAGNAME_IDI;
            this.ATTRIBUTENAME = ST_ATTRIBUTENAME_IDI;
            this.UNQUOTEDVALUE = ST_UNQUOTEDVALUE_IDI;
            this.ATTRIBUTEVALUE = ST_ATTRIBUTEVALUE_IDI;
            this.ATTRIBUTEVALUESPEC = ST_ATTRIBUTEVALUESPEC_IDI;
            this.ATTRIBUTE = ST_ATTRIBUTE_IDI;
            this.OPENTAG = ST_OPENTAG_IDI;
            this.CLOSETAG = ST_CLOSETAG_IDI;
        } else {
            this.ADDITIONAL_CHARS = "";
            this.EXCLUDED_0_TO_SPACE = ST_EXCLUDED_0_TO_SPACE_NO_IDI;
            this.REG_CHAR = ST_REG_CHAR_NO_IDI;
            this.REG_CHAR_PARENS = ST_REG_CHAR_PARENS_NO_IDI;
            this.REG_CHAR_SP = ST_REG_CHAR_SP_NO_IDI;
            this.REG_CHAR_SP_PARENS = ST_REG_CHAR_SP_PARENS_NO_IDI;
            this.IN_PARENS_NOSP = ST_IN_PARENS_NOSP_NO_IDI;
            this.IN_PARENS_W_SP = ST_IN_PARENS_W_SP_NO_IDI;
            this.IN_MATCHED_PARENS_NOSP = ST_IN_MATCHED_PARENS_NOSP_NO_IDI;
            this.IN_MATCHED_PARENS_W_SP = ST_IN_MATCHED_PARENS_W_SP_NO_IDI;
            this.IN_BRACES_W_SP = ST_IN_BRACES_W_SP_NO_IDI;
            this.DECLARATION = ST_DECLARATION_NO_IDI;
            this.ENTITY = ST_ENTITY_NO_IDI;
            this.TAGNAME = ST_TAGNAME_NO_IDI;
            this.ATTRIBUTENAME = ST_ATTRIBUTENAME_NO_IDI;
            this.UNQUOTEDVALUE = ST_UNQUOTEDVALUE_NO_IDI;
            this.ATTRIBUTEVALUE = ST_ATTRIBUTEVALUE_NO_IDI;
            this.ATTRIBUTEVALUESPEC = ST_ATTRIBUTEVALUESPEC_NO_IDI;
            this.ATTRIBUTE = ST_ATTRIBUTE_NO_IDI;
            this.OPENTAG = ST_OPENTAG_NO_IDI;
            this.CLOSETAG = ST_CLOSETAG_NO_IDI;
        }
        this.LINK_DESTINATION_ANGLES = this.spaceInLinkUrl ? ST_LINK_DESTINATION_ANGLES_SPC : ST_LINK_DESTINATION_ANGLES_NO_SPC;
        this.ENTITY_HERE = this.intellijDummyIdentifier ? ST_ENTITY_HERE_IDI : ST_ENTITY_HERE_NO_IDI;
        synchronized (cachedPatterns) {
            this.LINK_DESTINATION_MATCHED_PARENS_NOSP = getCachedPattern("LINK_DESTINATION_MATCHED_PARENS_NOSP", patternTypeFlags.withJekyllMacroInLinkUrl(), patternTypeFlags2 -> {
                return Pattern.compile("^(?:" + (this.parseJekyllMacroInLinkUrl ? this.IN_BRACES_W_SP + "|" : "") + this.REG_CHAR + "|" + this.ESCAPED_CHAR + "|\\\\|\\(|\\))*");
            });
            this.LINK_DESTINATION = getCachedPattern("LINK_DESTINATION", patternTypeFlags.withJekyllMacroSpaceInLinkUrl(), patternTypeFlags3 -> {
                return Pattern.compile("^(?:" + (this.parseJekyllMacroInLinkUrl ? this.IN_BRACES_W_SP + "|" : "") + (this.spaceInLinkUrl ? "(?:" + this.REG_CHAR_SP + ")|" : this.REG_CHAR + "|") + this.ESCAPED_CHAR + "|\\\\|" + (this.spaceInLinkUrl ? this.IN_PARENS_W_SP : this.IN_PARENS_NOSP) + ")*");
            });
            this.LINK_DESTINATION_MATCHED_PARENS = getCachedPattern("LINK_DESTINATION_MATCHED_PARENS", patternTypeFlags.withJekyllMacroSpaceInLinkUrl(), patternTypeFlags4 -> {
                return Pattern.compile("^(?:" + (this.parseJekyllMacroInLinkUrl ? this.IN_BRACES_W_SP + "|" : "") + (this.spaceInLinkUrl ? "(?:" + this.REG_CHAR_SP + ")|" : this.REG_CHAR + "|") + this.ESCAPED_CHAR + "|\\\\|\\(|\\))*");
            });
            this.EMAIL_AUTOLINK = getCachedPattern("EMAIL_AUTOLINK", patternTypeFlags.withHtmlTranslator(), patternTypeFlags5 -> {
                return Pattern.compile("^<((?:[a-zA-Z0-9" + this.ADDITIONAL_CHARS + ".!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9" + this.ADDITIONAL_CHARS + "](?:[a-zA-Z0-9" + this.ADDITIONAL_CHARS + "-]{0,61}[a-zA-Z0-9" + this.ADDITIONAL_CHARS + "])?(?:\\.[a-zA-Z0-9" + this.ADDITIONAL_CHARS + "](?:[a-zA-Z0-9" + this.ADDITIONAL_CHARS + "-]{0,61}[a-zA-Z0-9" + this.ADDITIONAL_CHARS + "])?)*)" + (this.htmlForTranslator ? "|(?:" + this.translationAutolinkTagPattern + ")" : "") + ")>");
            });
            this.AUTOLINK = getCachedPattern("AUTOLINK", patternTypeFlags.withHtmlTranslator(), patternTypeFlags6 -> {
                return Pattern.compile("^<((?:[a-zA-Z][a-zA-Z0-9" + this.ADDITIONAL_CHARS + ".+-]{1,31}:[^<>" + this.EXCLUDED_0_TO_SPACE + "]*)" + (this.htmlForTranslator ? "|(?:" + this.translationAutolinkTagPattern + ")" : "") + ")>");
            });
            this.WWW_AUTOLINK = getCachedPattern("WWW_AUTOLINK", patternTypeFlags.withHtmlTranslator(), patternTypeFlags7 -> {
                return Pattern.compile("^<((?:w" + this.ADDITIONAL_CHARS + "?){3,3}\\.[^<>" + this.EXCLUDED_0_TO_SPACE + "]*" + (this.htmlForTranslator ? "|(?:" + this.translationAutolinkTagPattern + ")" : "") + ")>");
            });
            this.HTML_TAG = getCachedPattern("HTML_TAG", patternTypeFlags.withHtmlTranslator(), patternTypeFlags8 -> {
                return Pattern.compile("^(?:" + this.OPENTAG + "|" + this.CLOSETAG + "|<!---->|<!--(?:-?[^>-])(?:-?[^-])*-->|[<][?].*?[?][>]|" + this.DECLARATION + "|<!\\[CDATA\\[[\\s\\S]*?\\]\\]>" + (this.htmlForTranslator ? "|<(?:" + this.translationHtmlInlineTagPattern + ")>|</(?:" + this.translationHtmlInlineTagPattern + ")>" : "") + ")", 2);
            });
            this.LIST_ITEM_MARKER = getCachedPattern("LIST_ITEM_MARKER", patternTypeFlags.withItemPrefixChars(), patternTypeFlags9 -> {
                if (this.listsItemMarkerSpace) {
                    if (this.listsOrderedItemDotOnly) {
                        return Pattern.compile("^([\\Q" + this.itemPrefixChars + "\\E])(?=[ \t])|^(\\d{1,9})([.])(?=[ \t])");
                    }
                    return Pattern.compile("^([\\Q" + this.itemPrefixChars + "\\E])(?=[ \t])|^(\\d{1,9})([.)])(?=[ \t])");
                }
                if (this.listsOrderedItemDotOnly) {
                    return Pattern.compile("^([\\Q" + this.itemPrefixChars + "\\E])(?= |\t|$)|^(\\d{1,9})([.])(?= |\t|$)");
                }
                return Pattern.compile("^([\\Q" + this.itemPrefixChars + "\\E])(?= |\t|$)|^(\\d{1,9})([.)])(?= |\t|$)");
            });
        }
        this.HTMLTAG = this.HTML_TAG.pattern();
    }

    @Deprecated
    public String EXCLUDED_0_TO_SPACE() {
        return this.intellijDummyIdentifier ? ST_EXCLUDED_0_TO_SPACE_IDI : ST_EXCLUDED_0_TO_SPACE_NO_IDI;
    }

    @Deprecated
    public String ADDITIONAL_CHARS() {
        return this.intellijDummyIdentifier ? ST_ADDITIONAL_CHARS_IDI : "";
    }

    @Deprecated
    public String ADDITIONAL_CHARS_SET(String str) {
        return this.intellijDummyIdentifier ? ST_ADDITIONAL_CHARS_SET_IDI + str : "";
    }

    public static int columnsToNextTabStop(int i) {
        return 4 - (i % 4);
    }

    public static int findLineBreak(CharSequence charSequence, int i) {
        return SequenceUtils.indexOfAny(charSequence, CharPredicate.ANY_EOL, i);
    }

    public static boolean isBlank(CharSequence charSequence) {
        return SequenceUtils.indexOfAnyNot(charSequence, CharPredicate.BLANKSPACE) == -1;
    }

    public static boolean isLetter(CharSequence charSequence, int i) {
        return Character.isLetter(Character.codePointAt(charSequence, i));
    }

    public static boolean isSpaceOrTab(CharSequence charSequence, int i) {
        return CharPredicate.SPACE_TAB.test(SequenceUtils.safeCharAt(charSequence, i));
    }
}
