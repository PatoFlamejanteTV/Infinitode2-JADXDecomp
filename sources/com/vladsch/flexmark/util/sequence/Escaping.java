package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Utils;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/Escaping.class */
public class Escaping {
    public static final String ESCAPABLE_CHARS = "\"#$%&'()*+,./:;<=>?@[]\\^_`{|}~-";
    public static final String ESCAPABLE = "[!" + ESCAPABLE_CHARS.replace("\\", "\\\\").replace("[", "\\[").replace("]", "\\]") + "]";
    private static final Pattern BACKSLASH_ONLY = Pattern.compile("[\\\\]");
    private static final Pattern ESCAPED_CHAR = Pattern.compile("\\\\" + ESCAPABLE, 2);
    private static final Pattern BACKSLASH_OR_AMP = Pattern.compile("[\\\\&]");
    private static final Pattern AMP_ONLY = Pattern.compile("[\\&]");
    private static final String ENTITY = "&(?:#x[a-f0-9]{1,8}|#[0-9]{1,8}|[a-z][a-z0-9]{1,31});";
    private static final Pattern ENTITY_OR_ESCAPED_CHAR = Pattern.compile("\\\\" + ESCAPABLE + '|' + ENTITY, 2);
    private static final Pattern ENTITY_ONLY = Pattern.compile(ENTITY, 2);
    private static final String XML_SPECIAL = "[&<>\"]";
    private static final Pattern XML_SPECIAL_RE = Pattern.compile(XML_SPECIAL);
    private static final Pattern XML_SPECIAL_OR_ENTITY = Pattern.compile("&(?:#x[a-f0-9]{1,8}|#[0-9]{1,8}|[a-z][a-z0-9]{1,31});|[&<>\"]", 2);
    private static final Pattern ESCAPE_IN_URI = Pattern.compile("(%[a-fA-F0-9]{0,2}|[^:/?#@!$&'()*+,;=a-zA-Z0-9\\-._~])");
    private static final Pattern ESCAPE_URI_DECODE = Pattern.compile("(%[a-fA-F0-9]{2})");
    static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final Pattern WHITESPACE = Pattern.compile("[ \t\r\n]+");
    private static final Pattern COLLAPSE_WHITESPACE = Pattern.compile("[ \t]{2,}");
    private static final Replacer UNSAFE_CHAR_REPLACER = new Replacer() { // from class: com.vladsch.flexmark.util.sequence.Escaping.1
        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(String str, StringBuilder sb) {
            if (str.equals("&")) {
                sb.append("&amp;");
                return;
            }
            if (str.equals("<")) {
                sb.append("&lt;");
                return;
            }
            if (str.equals(">")) {
                sb.append("&gt;");
            } else if (str.equals("\"")) {
                sb.append("&quot;");
            } else {
                sb.append(str);
            }
        }

        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(BasedSequence basedSequence, int i, int i2, ReplacedTextMapper replacedTextMapper) {
            String obj = basedSequence.subSequence(i, i2).toString();
            if (obj.equals("&")) {
                replacedTextMapper.addReplacedText(i, i2, PrefixedSubSequence.prefixOf("&amp;", BasedSequence.NULL));
                return;
            }
            if (obj.equals("<")) {
                replacedTextMapper.addReplacedText(i, i2, PrefixedSubSequence.prefixOf("&lt;", BasedSequence.NULL));
                return;
            }
            if (obj.equals(">")) {
                replacedTextMapper.addReplacedText(i, i2, PrefixedSubSequence.prefixOf("&gt;", BasedSequence.NULL));
            } else if (obj.equals("\"")) {
                replacedTextMapper.addReplacedText(i, i2, PrefixedSubSequence.prefixOf("&quot;", BasedSequence.NULL));
            } else {
                replacedTextMapper.addOriginalText(i, i2);
            }
        }
    };
    private static final Replacer COLLAPSE_WHITESPACE_REPLACER = new Replacer() { // from class: com.vladsch.flexmark.util.sequence.Escaping.2
        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(String str, StringBuilder sb) {
            sb.append(SequenceUtils.SPACE);
        }

        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(BasedSequence basedSequence, int i, int i2, ReplacedTextMapper replacedTextMapper) {
            replacedTextMapper.addReplacedText(i, i2, basedSequence.subSequence(i, i + 1));
        }
    };
    private static final Replacer UNESCAPE_REPLACER = new Replacer() { // from class: com.vladsch.flexmark.util.sequence.Escaping.3
        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(String str, StringBuilder sb) {
            if (str.charAt(0) == '\\') {
                sb.append((CharSequence) str, 1, str.length());
            } else {
                sb.append(Html5Entities.entityToString(str));
            }
        }

        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(BasedSequence basedSequence, int i, int i2, ReplacedTextMapper replacedTextMapper) {
            if (basedSequence.charAt(i) == '\\') {
                replacedTextMapper.addReplacedText(i, i2, basedSequence.subSequence(i + 1, i2));
            } else {
                replacedTextMapper.addReplacedText(i, i2, Html5Entities.entityToSequence(basedSequence.subSequence(i, i2)));
            }
        }
    };
    private static final Replacer REMOVE_REPLACER = new Replacer() { // from class: com.vladsch.flexmark.util.sequence.Escaping.4
        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(String str, StringBuilder sb) {
        }

        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(BasedSequence basedSequence, int i, int i2, ReplacedTextMapper replacedTextMapper) {
            replacedTextMapper.addReplacedText(i, i2, basedSequence.subSequence(i2, i2));
        }
    };
    private static final Replacer ENTITY_REPLACER = new Replacer() { // from class: com.vladsch.flexmark.util.sequence.Escaping.5
        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(String str, StringBuilder sb) {
            sb.append(Html5Entities.entityToString(str));
        }

        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(BasedSequence basedSequence, int i, int i2, ReplacedTextMapper replacedTextMapper) {
            replacedTextMapper.addReplacedText(i, i2, Html5Entities.entityToSequence(basedSequence.subSequence(i, i2)));
        }
    };
    private static final Replacer URL_ENCODE_REPLACER = new Replacer() { // from class: com.vladsch.flexmark.util.sequence.Escaping.6
        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(String str, StringBuilder sb) {
            if (str.startsWith("%")) {
                if (str.length() == 3) {
                    sb.append(str);
                    return;
                } else {
                    sb.append("%25");
                    sb.append((CharSequence) str, 1, str.length());
                    return;
                }
            }
            for (byte b2 : str.getBytes(StandardCharsets.UTF_8)) {
                sb.append('%');
                sb.append(Escaping.HEX_DIGITS[(b2 >> 4) & 15]);
                sb.append(Escaping.HEX_DIGITS[b2 & 15]);
            }
        }

        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(BasedSequence basedSequence, int i, int i2, ReplacedTextMapper replacedTextMapper) {
            BasedSequence subSequence = basedSequence.subSequence(i, i2);
            if (subSequence.startsWith("%")) {
                if (subSequence.length() == 3) {
                    replacedTextMapper.addOriginalText(i, i2);
                    return;
                } else {
                    replacedTextMapper.addReplacedText(i, i + 1, PrefixedSubSequence.prefixOf("%25", BasedSequence.NULL));
                    replacedTextMapper.addOriginalText(i + 1, i2);
                    return;
                }
            }
            byte[] bytes = subSequence.toString().getBytes(StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bytes) {
                sb.append('%');
                sb.append(Escaping.HEX_DIGITS[(b2 >> 4) & 15]);
                sb.append(Escaping.HEX_DIGITS[b2 & 15]);
            }
            replacedTextMapper.addReplacedText(i, i2, PrefixedSubSequence.prefixOf(sb.toString(), BasedSequence.NULL));
        }
    };
    private static final Replacer URL_DECODE_REPLACER = new Replacer() { // from class: com.vladsch.flexmark.util.sequence.Escaping.7
        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(String str, StringBuilder sb) {
            sb.append(Utils.urlDecode(str, null));
        }

        @Override // com.vladsch.flexmark.util.sequence.Escaping.Replacer
        public final void replace(BasedSequence basedSequence, int i, int i2, ReplacedTextMapper replacedTextMapper) {
            replacedTextMapper.addReplacedText(i, i2, PrefixedSubSequence.prefixOf(Utils.urlDecode(basedSequence.subSequence(i, i2).toString(), null), BasedSequence.NULL));
        }
    };
    public static final CharPredicate AMP_BACKSLASH_SET = CharPredicate.anyOf('\\', '&');
    private static Random random = new Random(9766);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/Escaping$Replacer.class */
    public interface Replacer {
        void replace(String str, StringBuilder sb);

        void replace(BasedSequence basedSequence, int i, int i2, ReplacedTextMapper replacedTextMapper);
    }

    public static String escapeHtml(CharSequence charSequence, boolean z) {
        return replaceAll(z ? XML_SPECIAL_OR_ENTITY : XML_SPECIAL_RE, charSequence, UNSAFE_CHAR_REPLACER);
    }

    public static BasedSequence escapeHtml(BasedSequence basedSequence, boolean z, ReplacedTextMapper replacedTextMapper) {
        return replaceAll(z ? XML_SPECIAL_OR_ENTITY : XML_SPECIAL_RE, basedSequence, UNSAFE_CHAR_REPLACER, replacedTextMapper);
    }

    public static String unescapeString(CharSequence charSequence) {
        if (BACKSLASH_OR_AMP.matcher(charSequence).find()) {
            return replaceAll(ENTITY_OR_ESCAPED_CHAR, charSequence, UNESCAPE_REPLACER);
        }
        return String.valueOf(charSequence);
    }

    public static String unescapeString(CharSequence charSequence, boolean z) {
        if (z) {
            if (BACKSLASH_OR_AMP.matcher(charSequence).find()) {
                return replaceAll(ESCAPED_CHAR, charSequence, UNESCAPE_REPLACER);
            }
            return String.valueOf(charSequence);
        }
        if (BACKSLASH_ONLY.matcher(charSequence).find()) {
            return replaceAll(ENTITY_OR_ESCAPED_CHAR, charSequence, UNESCAPE_REPLACER);
        }
        return String.valueOf(charSequence);
    }

    public static BasedSequence unescape(BasedSequence basedSequence, ReplacedTextMapper replacedTextMapper) {
        if (basedSequence.indexOfAny(AMP_BACKSLASH_SET) != -1) {
            return replaceAll(ENTITY_OR_ESCAPED_CHAR, basedSequence, UNESCAPE_REPLACER, replacedTextMapper);
        }
        return basedSequence;
    }

    public static BasedSequence removeAll(BasedSequence basedSequence, CharSequence charSequence, ReplacedTextMapper replacedTextMapper) {
        if (basedSequence.indexOf(charSequence) != -1) {
            return replaceAll(Pattern.compile("\\Q" + ((Object) charSequence) + "\\E"), basedSequence, REMOVE_REPLACER, replacedTextMapper);
        }
        return basedSequence;
    }

    public static String unescapeHtml(CharSequence charSequence) {
        if (AMP_ONLY.matcher(charSequence).find()) {
            return replaceAll(ENTITY_ONLY, charSequence, ENTITY_REPLACER);
        }
        return String.valueOf(charSequence);
    }

    public static BasedSequence unescapeHtml(BasedSequence basedSequence, ReplacedTextMapper replacedTextMapper) {
        if (basedSequence.indexOf('&') != -1) {
            return replaceAll(ENTITY_ONLY, basedSequence, ENTITY_REPLACER, replacedTextMapper);
        }
        return basedSequence;
    }

    public static BasedSequence unescapeHtml(BasedSequence basedSequence, List<Range> list, ReplacedTextMapper replacedTextMapper) {
        if (basedSequence.indexOf('&') != -1) {
            return replaceAll(ENTITY_ONLY, basedSequence, list, ENTITY_REPLACER, replacedTextMapper);
        }
        return basedSequence;
    }

    public static String normalizeEndWithEOL(CharSequence charSequence) {
        return normalizeEOL(charSequence, true);
    }

    public static String normalizeEOL(CharSequence charSequence) {
        return normalizeEOL(charSequence, false);
    }

    public static String normalizeEOL(CharSequence charSequence, boolean z) {
        StringBuilder sb = new StringBuilder(charSequence.length());
        int length = charSequence.length();
        boolean z2 = false;
        boolean z3 = false;
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (charAt == '\r') {
                z2 = true;
            } else if (charAt == '\n') {
                sb.append(SequenceUtils.EOL);
                z2 = false;
                z3 = true;
            } else {
                if (z2) {
                    sb.append('\n');
                }
                sb.append(charAt);
                z2 = false;
                z3 = false;
            }
        }
        if (z && !z3) {
            sb.append('\n');
        }
        return sb.toString();
    }

    public static BasedSequence normalizeEndWithEOL(BasedSequence basedSequence, ReplacedTextMapper replacedTextMapper) {
        return normalizeEOL(basedSequence, replacedTextMapper, true);
    }

    public static BasedSequence normalizeEOL(BasedSequence basedSequence, ReplacedTextMapper replacedTextMapper) {
        return normalizeEOL(basedSequence, replacedTextMapper, false);
    }

    public static BasedSequence normalizeEOL(BasedSequence basedSequence, ReplacedTextMapper replacedTextMapper, boolean z) {
        int length = basedSequence.length();
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        if (replacedTextMapper.isModified()) {
            replacedTextMapper.startNestedReplacement(basedSequence);
        }
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = basedSequence.charAt(i2);
            if (charAt == '\r') {
                z2 = true;
            } else if (charAt == '\n') {
                if (z2) {
                    if (i < i2 - 1) {
                        replacedTextMapper.addOriginalText(i, i2 - 1);
                    }
                    i = i2;
                    z2 = false;
                    z3 = true;
                }
            } else if (z2) {
                if (i < i2 - 1) {
                    replacedTextMapper.addOriginalText(i, i2 + 1);
                }
                replacedTextMapper.addReplacedText(i2 - 1, i2, BasedSequence.EOL);
                i = i2;
                z2 = false;
                z3 = false;
            }
        }
        if (!z2) {
            if (i < length) {
                replacedTextMapper.addOriginalText(i, length);
            }
            if (!z3 && z) {
                replacedTextMapper.addReplacedText(length - 1, length, BasedSequence.EOL);
            }
        }
        return replacedTextMapper.getReplacedSequence();
    }

    public static String percentEncodeUrl(CharSequence charSequence) {
        return replaceAll(ESCAPE_IN_URI, charSequence, URL_ENCODE_REPLACER);
    }

    public static BasedSequence percentEncodeUrl(BasedSequence basedSequence, ReplacedTextMapper replacedTextMapper) {
        return replaceAll(ESCAPE_IN_URI, basedSequence, URL_ENCODE_REPLACER, replacedTextMapper);
    }

    public static String percentDecodeUrl(CharSequence charSequence) {
        return replaceAll(ESCAPE_URI_DECODE, charSequence, URL_DECODE_REPLACER);
    }

    public static BasedSequence percentDecodeUrl(BasedSequence basedSequence, ReplacedTextMapper replacedTextMapper) {
        return replaceAll(ESCAPE_URI_DECODE, basedSequence, URL_DECODE_REPLACER, replacedTextMapper);
    }

    public static String normalizeReference(CharSequence charSequence, boolean z) {
        return z ? collapseWhitespace((CharSequence) charSequence.toString(), true).toLowerCase() : collapseWhitespace((CharSequence) charSequence.toString(), true);
    }

    private static String encode(char c) {
        switch (c) {
            case '\"':
                return "&quot;";
            case '&':
                return "&amp;";
            case '\'':
                return "&#39;";
            case '<':
                return "&lt;";
            case '>':
                return "&gt;";
            default:
                return null;
        }
    }

    public static String obfuscate(String str, boolean z) {
        if (!z) {
            random = new Random(0L);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            switch (random.nextInt(5)) {
                case 0:
                case 1:
                    sb.append("&#").append((int) charAt).append(';');
                    break;
                case 2:
                case 3:
                    sb.append("&#x").append(Integer.toHexString(charAt)).append(';');
                    break;
                case 4:
                    String encode = encode(charAt);
                    if (encode != null) {
                        sb.append(encode);
                        break;
                    } else {
                        sb.append(charAt);
                        break;
                    }
            }
        }
        return sb.toString();
    }

    public static String normalizeReferenceChars(CharSequence charSequence, boolean z) {
        if (charSequence.length() > 1) {
            return normalizeReference(charSequence.subSequence(charSequence.charAt(0) == '!' ? 2 : 1, charSequence.length() - (charSequence.charAt(charSequence.length() - 1) == ':' ? 2 : 1)), z);
        }
        return String.valueOf(charSequence);
    }

    public static String collapseWhitespace(CharSequence charSequence, boolean z) {
        boolean z2;
        StringBuilder sb = new StringBuilder(charSequence.length());
        int length = charSequence.length();
        boolean z3 = false;
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (charAt == ' ' || charAt == '\t' || charAt == '\n' || charAt == '\r') {
                z2 = true;
            } else {
                if (z3 && (!z || sb.length() > 0)) {
                    sb.append(' ');
                }
                sb.append(charAt);
                z2 = false;
            }
            z3 = z2;
        }
        if (z3 && !z) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public static BasedSequence collapseWhitespace(BasedSequence basedSequence, ReplacedTextMapper replacedTextMapper) {
        return replaceAll(COLLAPSE_WHITESPACE, basedSequence, COLLAPSE_WHITESPACE_REPLACER, replacedTextMapper);
    }

    private static String replaceAll(Pattern pattern, CharSequence charSequence, Replacer replacer) {
        Matcher matcher = pattern.matcher(charSequence);
        if (!matcher.find()) {
            return String.valueOf(charSequence);
        }
        StringBuilder sb = new StringBuilder(charSequence.length() + 16);
        int i = 0;
        do {
            sb.append(charSequence, i, matcher.start());
            replacer.replace(matcher.group(), sb);
            i = matcher.end();
        } while (matcher.find());
        if (i != charSequence.length()) {
            sb.append(charSequence, i, charSequence.length());
        }
        return sb.toString();
    }

    private static BasedSequence replaceAll(Pattern pattern, BasedSequence basedSequence, Replacer replacer, ReplacedTextMapper replacedTextMapper) {
        return replaceAll(pattern, basedSequence, 0, basedSequence.length(), replacer, replacedTextMapper);
    }

    private static BasedSequence replaceAll(Pattern pattern, BasedSequence basedSequence, int i, int i2, Replacer replacer, ReplacedTextMapper replacedTextMapper) {
        Matcher matcher = pattern.matcher(basedSequence);
        matcher.region(i, i2);
        matcher.useTransparentBounds(false);
        if (replacedTextMapper.isModified()) {
            replacedTextMapper.startNestedReplacement(basedSequence);
        }
        if (!matcher.find()) {
            replacedTextMapper.addOriginalText(0, basedSequence.length());
            return basedSequence;
        }
        int i3 = 0;
        do {
            replacedTextMapper.addOriginalText(i3, matcher.start());
            replacer.replace(basedSequence, matcher.start(), matcher.end(), replacedTextMapper);
            i3 = matcher.end();
        } while (matcher.find());
        if (i3 < basedSequence.length()) {
            replacedTextMapper.addOriginalText(i3, basedSequence.length());
        }
        return replacedTextMapper.getReplacedSequence();
    }

    private static BasedSequence replaceAll(Pattern pattern, BasedSequence basedSequence, List<Range> list, Replacer replacer, ReplacedTextMapper replacedTextMapper) {
        Matcher matcher = pattern.matcher(basedSequence);
        matcher.useTransparentBounds(false);
        if (replacedTextMapper.isModified()) {
            replacedTextMapper.startNestedReplacement(basedSequence);
        }
        int i = 0;
        for (Range range : list) {
            int rangeLimit = Utils.rangeLimit(range.getStart(), i, basedSequence.length());
            matcher.region(rangeLimit, Utils.rangeLimit(range.getEnd(), rangeLimit, basedSequence.length()));
            while (matcher.find()) {
                replacedTextMapper.addOriginalText(i, matcher.start());
                replacer.replace(basedSequence, matcher.start(), matcher.end(), replacedTextMapper);
                i = matcher.end();
            }
        }
        if (i < basedSequence.length()) {
            replacedTextMapper.addOriginalText(i, basedSequence.length());
        }
        return replacedTextMapper.getReplacedSequence();
    }
}
