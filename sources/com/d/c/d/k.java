package com.d.c.d;

/* loaded from: infinitode-2.jar:com/d/c/d/k.class */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    public static final k f1062a = new k(1, "S", "whitespace");

    /* renamed from: b, reason: collision with root package name */
    public static final k f1063b = new k(2, "CDO", "<!--");
    public static final k c = new k(3, "CDC", "-->");
    public static final k d = new k(4, "INCLUDES", "an attribute word match");
    public static final k e = new k(5, "DASHMATCH", "an attribute hyphen match");
    public static final k f = new k(6, "PREFIXMATCH", "an attribute prefix match");
    public static final k g = new k(7, "SUFFIXMATCH", "an attribute suffix match");
    public static final k h = new k(8, "SUBSTRINGMATCH", "an attribute substring match");
    public static final k i = new k(9, "LBRACE", "a {");
    public static final k j = new k(10, "PLUS", "a +");
    public static final k k = new k(11, "GREATER", "a >");
    public static final k l = new k(12, "COMMA", "a comma");
    public static final k m = new k(13, "STRING", "a string");
    public static final k n = new k(14, "INVALID", "an unclosed string");
    public static final k o = new k(15, "IDENT", "an identifier");
    public static final k p = new k(16, "HASH", "a hex color");
    public static final k q = new k(17, "IMPORT_SYM", "@import");
    public static final k r = new k(18, "PAGE_SYM", "@page");
    public static final k s = new k(19, "MEDIA_SYM", "@media");
    public static final k t = new k(20, "CHARSET_SYM", "@charset");
    public static final k u = new k(21, "NAMESPACE_SYM", "@namespace,");
    public static final k v = new k(22, "FONT_FACE_SYM", "@font-face");
    public static final k w = new k(23, "AT_RULE", "at rule");
    public static final k x = new k(24, "IMPORTANT_SYM", "!important");
    public static final k y = new k(25, "EMS", "an em value");
    public static final k z = new k(26, "EXS", "an ex value");
    public static final k A = new k(27, "PX", "a pixel value");
    public static final k B = new k(28, "CM", "a centimeter value");
    public static final k C = new k(29, "MM", "a millimeter value");
    public static final k D = new k(30, "IN", "an inch value");
    public static final k E = new k(31, "PT", "a point value");
    public static final k F = new k(32, "PC", "a pica value");
    public static final k G = new k(33, "ANGLE", "an angle value");
    public static final k H = new k(34, "TIME", "a time value");
    public static final k I = new k(35, "FREQ", "a freq value");
    public static final k J = new k(36, "DIMENSION", "a dimension");
    public static final k K = new k(37, "PERCENTAGE", "a percentage");
    public static final k L = new k(38, "NUMBER", "a number");
    public static final k M = new k(39, "URI", "a URI");
    public static final k N = new k(40, "FUNCTION", "function");
    public static final k O;
    public static final k P;
    public static final k Q;
    public static final k R;
    public static final k S;
    public static final k T;
    public static final k U;
    public static final k V;
    public static final k W;
    public static final k X;
    public static final k Y;
    public static final k Z;
    public static final k aa;
    private final int ab;
    private final String ac;
    private final String ad;

    static {
        new k(41, "OTHER", "other");
        O = new k(42, "RBRACE", "}");
        P = new k(43, "SEMICOLON", ";");
        Q = new k(44, "VIRGULE", "/");
        R = new k(45, "COLON", ":");
        S = new k(46, "MINUS", "-");
        T = new k(47, "RPAREN", ")");
        U = new k(48, "LBRACKET", "[");
        V = new k(49, "RBRACKET", "]");
        W = new k(50, "PERIOD", ".");
        X = new k(51, "EQUALS", "=");
        Y = new k(52, "ASTERISK", "*");
        Z = new k(53, "VERTICAL_BAR", "|");
        aa = new k(54, "EOF", "end of file");
    }

    private k(int i2, String str, String str2) {
        this.ab = i2;
        this.ac = str;
        this.ad = str2;
    }

    public final int a() {
        return this.ab;
    }

    public final String b() {
        return this.ad;
    }

    public final String toString() {
        return this.ac;
    }

    public static k a(String str) {
        return new k(41, "OTHER", str + " (other)");
    }
}
