package com.vladsch.flexmark.parser;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/PegdownExtensions.class */
public interface PegdownExtensions {
    public static final int NONE = 0;
    public static final int SMARTS = 1;
    public static final int QUOTES = 2;
    public static final int SMARTYPANTS = 3;
    public static final int ABBREVIATIONS = 4;
    public static final int HARDWRAPS = 8;
    public static final int AUTOLINKS = 16;
    public static final int TABLES = 32;
    public static final int DEFINITIONS = 64;
    public static final int FENCED_CODE_BLOCKS = 128;
    public static final int WIKILINKS = 256;
    public static final int STRIKETHROUGH = 512;
    public static final int ANCHORLINKS = 1024;
    public static final int UNUSED_ALL = 63488;
    public static final int ALL = 65535;
    public static final int SUPPRESS_HTML_BLOCKS = 65536;
    public static final int SUPPRESS_INLINE_HTML = 131072;
    public static final int SUPPRESS_ALL_HTML = 196608;
    public static final int ATXHEADERSPACE = 262144;
    public static final int SUBSCRIPT = 524288;
    public static final int RELAXEDHRULES = 1048576;
    public static final int TASKLISTITEMS = 2097152;
    public static final int EXTANCHORLINKS = 4194304;
    public static final int EXTANCHORLINKS_WRAP = 8388608;
    public static final int FOOTNOTES = 16777216;
    public static final int TOC = 33554432;
    public static final int MULTI_LINE_IMAGE_URLS = 67108864;
    public static final int SUPERSCRIPT = 134217728;
    public static final int FORCELISTITEMPARA = 268435456;
    public static final int NOT_USED = 536870912;
    public static final int INSERTED = 1073741824;
    public static final int UNUSABLE = Integer.MIN_VALUE;
    public static final int ALL_OPTIONALS = 1333526528;
    public static final int ALL_WITH_OPTIONALS = 1333592063;
    public static final int GITHUB_DOCUMENT_COMPATIBLE = 3409584;
    public static final int GITHUB_WIKI_COMPATIBLE = 3409840;
    public static final int GITHUB_COMMENT_COMPATIBLE = 3409592;
}
