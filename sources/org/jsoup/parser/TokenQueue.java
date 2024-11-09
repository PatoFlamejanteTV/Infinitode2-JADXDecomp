package org.jsoup.parser;

import net.bytebuddy.utility.JavaConstant;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;

/* loaded from: infinitode-2.jar:org/jsoup/parser/TokenQueue.class */
public class TokenQueue {
    private String queue;
    private int pos = 0;
    private static final char ESC = '\\';
    private static final String[] ElementSelectorChars = {"*|", "|", JavaConstant.Dynamic.DEFAULT_NAME, "-"};
    private static final String[] CssIdentifierChars = {"-", JavaConstant.Dynamic.DEFAULT_NAME};

    public TokenQueue(String str) {
        Validate.notNull(str);
        this.queue = str;
    }

    public boolean isEmpty() {
        return remainingLength() == 0;
    }

    private int remainingLength() {
        return this.queue.length() - this.pos;
    }

    public void addFirst(String str) {
        this.queue = str + this.queue.substring(this.pos);
        this.pos = 0;
    }

    public boolean matches(String str) {
        return this.queue.regionMatches(true, this.pos, str, 0, str.length());
    }

    public boolean matchesAny(String... strArr) {
        for (String str : strArr) {
            if (matches(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean matchesAny(char... cArr) {
        if (isEmpty()) {
            return false;
        }
        for (char c : cArr) {
            if (this.queue.charAt(this.pos) == c) {
                return true;
            }
        }
        return false;
    }

    public boolean matchChomp(String str) {
        if (matches(str)) {
            this.pos += str.length();
            return true;
        }
        return false;
    }

    public boolean matchesWhitespace() {
        return !isEmpty() && StringUtil.isWhitespace(this.queue.charAt(this.pos));
    }

    public boolean matchesWord() {
        return !isEmpty() && Character.isLetterOrDigit(this.queue.charAt(this.pos));
    }

    public void advance() {
        if (!isEmpty()) {
            this.pos++;
        }
    }

    public char consume() {
        String str = this.queue;
        int i = this.pos;
        this.pos = i + 1;
        return str.charAt(i);
    }

    public void consume(String str) {
        if (!matches(str)) {
            throw new IllegalStateException("Queue did not match expected sequence");
        }
        int length = str.length();
        if (length > remainingLength()) {
            throw new IllegalStateException("Queue not long enough to consume sequence");
        }
        this.pos += length;
    }

    public String consumeTo(String str) {
        int indexOf = this.queue.indexOf(str, this.pos);
        if (indexOf != -1) {
            String substring = this.queue.substring(this.pos, indexOf);
            this.pos += substring.length();
            return substring;
        }
        return remainder();
    }

    public String consumeToIgnoreCase(String str) {
        int i = this.pos;
        String substring = str.substring(0, 1);
        boolean equals = substring.toLowerCase().equals(substring.toUpperCase());
        while (!isEmpty() && !matches(str)) {
            if (equals) {
                int indexOf = this.queue.indexOf(substring, this.pos) - this.pos;
                if (indexOf == 0) {
                    this.pos++;
                } else if (indexOf < 0) {
                    this.pos = this.queue.length();
                } else {
                    this.pos += indexOf;
                }
            } else {
                this.pos++;
            }
        }
        return this.queue.substring(i, this.pos);
    }

    public String consumeToAny(String... strArr) {
        int i = this.pos;
        while (!isEmpty() && !matchesAny(strArr)) {
            this.pos++;
        }
        return this.queue.substring(i, this.pos);
    }

    public String chompTo(String str) {
        String consumeTo = consumeTo(str);
        matchChomp(str);
        return consumeTo;
    }

    public String chompToIgnoreCase(String str) {
        String consumeToIgnoreCase = consumeToIgnoreCase(str);
        matchChomp(str);
        return consumeToIgnoreCase;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00cf A[EDGE_INSN: B:35:0x00cf->B:36:0x00cf BREAK  A[LOOP:0: B:2:0x0014->B:46:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[LOOP:0: B:2:0x0014->B:46:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String chompBalanced(char r5, char r6) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.TokenQueue.chompBalanced(char, char):java.lang.String");
    }

    public static String unescape(String str) {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        char c = 0;
        for (char c2 : str.toCharArray()) {
            char c3 = c2;
            if (c2 == '\\') {
                if (c == '\\') {
                    borrowBuilder.append(c3);
                    c3 = 0;
                }
            } else {
                borrowBuilder.append(c3);
            }
            c = c3;
        }
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    public static String escapeCssIdentifier(String str) {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        TokenQueue tokenQueue = new TokenQueue(str);
        while (!tokenQueue.isEmpty()) {
            if (tokenQueue.matchesCssIdentifier(ElementSelectorChars)) {
                borrowBuilder.append(tokenQueue.consume());
            } else {
                borrowBuilder.append('\\').append(tokenQueue.consume());
            }
        }
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    public boolean consumeWhitespace() {
        boolean z = false;
        while (true) {
            boolean z2 = z;
            if (matchesWhitespace()) {
                this.pos++;
                z = true;
            } else {
                return z2;
            }
        }
    }

    public String consumeWord() {
        int i = this.pos;
        while (matchesWord()) {
            this.pos++;
        }
        return this.queue.substring(i, this.pos);
    }

    public String consumeElementSelector() {
        return consumeEscapedCssIdentifier(ElementSelectorChars);
    }

    public String consumeCssIdentifier() {
        return consumeEscapedCssIdentifier(CssIdentifierChars);
    }

    private String consumeEscapedCssIdentifier(String... strArr) {
        int i = this.pos;
        boolean z = false;
        while (!isEmpty()) {
            if (this.queue.charAt(this.pos) == '\\' && remainingLength() > 1) {
                z = true;
                this.pos += 2;
            } else {
                if (!matchesCssIdentifier(strArr)) {
                    break;
                }
                this.pos++;
            }
        }
        String substring = this.queue.substring(i, this.pos);
        return z ? unescape(substring) : substring;
    }

    private boolean matchesCssIdentifier(String... strArr) {
        return matchesWord() || matchesAny(strArr);
    }

    public String remainder() {
        String substring = this.queue.substring(this.pos);
        this.pos = this.queue.length();
        return substring;
    }

    public String toString() {
        return this.queue.substring(this.pos);
    }
}
