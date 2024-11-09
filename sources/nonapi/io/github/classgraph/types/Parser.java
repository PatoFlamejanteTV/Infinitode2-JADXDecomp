package nonapi.io.github.classgraph.types;

import nonapi.io.github.classgraph.json.JSONUtils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/types/Parser.class */
public class Parser {
    private final String string;
    private int position;
    private final StringBuilder token = new StringBuilder();
    private Object state;
    private static final int SHOW_BEFORE = 80;
    private static final int SHOW_AFTER = 80;

    public Parser(String str) {
        if (str == null) {
            throw new ParseException(null, "Cannot parse null string");
        }
        this.string = str;
    }

    public String getPositionInfo() {
        return "before: \"" + JSONUtils.escapeJSONString(this.string.substring(Math.max(0, this.position - 80), this.position)) + "\"; after: \"" + JSONUtils.escapeJSONString(this.string.substring(this.position, Math.min(this.string.length(), this.position + 80))) + "\"; position: " + this.position + "; token: \"" + ((Object) this.token) + "\"";
    }

    public Object setState(Object obj) {
        Object obj2 = this.state;
        this.state = obj;
        return obj2;
    }

    public Object getState() {
        return this.state;
    }

    public char getc() {
        if (this.position >= this.string.length()) {
            throw new ParseException(this, "Ran out of input while parsing");
        }
        String str = this.string;
        int i = this.position;
        this.position = i + 1;
        return str.charAt(i);
    }

    public void expect(char c) {
        char cVar = getc();
        if (cVar != c) {
            throw new ParseException(this, "Expected '" + c + "'; got '" + cVar + "'");
        }
    }

    public char peek() {
        if (this.position == this.string.length()) {
            return (char) 0;
        }
        return this.string.charAt(this.position);
    }

    public void peekExpect(char c) {
        if (this.position == this.string.length()) {
            throw new ParseException(this, "Expected '" + c + "'; reached end of string");
        }
        char charAt = this.string.charAt(this.position);
        if (charAt != c) {
            throw new ParseException(this, "Expected '" + c + "'; got '" + charAt + "'");
        }
    }

    public boolean peekMatches(String str) {
        return this.string.regionMatches(this.position, str, 0, str.length());
    }

    public void next() {
        this.position++;
    }

    public void advance(int i) {
        if (this.position + i >= this.string.length()) {
            throw new IllegalArgumentException("Invalid skip distance");
        }
        this.position += i;
    }

    public boolean hasMore() {
        return this.position < this.string.length();
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        if (i < 0 || i >= this.string.length()) {
            throw new IllegalArgumentException("Invalid position");
        }
        this.position = i;
    }

    public CharSequence getSubsequence(int i, int i2) {
        return this.string.subSequence(i, i2);
    }

    public String getSubstring(int i, int i2) {
        return this.string.substring(i, i2);
    }

    public void appendToToken(String str) {
        this.token.append(str);
    }

    public void appendToToken(char c) {
        this.token.append(c);
    }

    public void skipWhitespace() {
        while (this.position < this.string.length()) {
            char charAt = this.string.charAt(this.position);
            if (charAt == ' ' || charAt == '\n' || charAt == '\r' || charAt == '\t') {
                this.position++;
            } else {
                return;
            }
        }
    }

    public String currToken() {
        String sb = this.token.toString();
        this.token.setLength(0);
        return sb;
    }

    public String toString() {
        return getPositionInfo();
    }
}
