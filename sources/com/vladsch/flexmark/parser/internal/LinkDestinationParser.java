package com.vladsch.flexmark.parser.internal;

import com.vladsch.flexmark.util.sequence.Escaping;
import java.util.BitSet;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/LinkDestinationParser.class */
public class LinkDestinationParser {
    public final BitSet EXCLUDED_0_TO_SPACE_CHARS;
    public final BitSet JEKYLL_EXCLUDED_CHARS;
    public final BitSet PAREN_EXCLUDED_CHARS;
    public final BitSet PAREN_ESCAPABLE_CHARS;
    public final BitSet PAREN_QUOTE_CHARS;
    public final boolean allowMatchedParentheses;
    public final boolean spaceInUrls;
    public final boolean parseJekyllMacrosInUrls;
    public final boolean intellijDummyIdentifier;

    public LinkDestinationParser(boolean z, boolean z2, boolean z3, boolean z4) {
        this.allowMatchedParentheses = z || z3;
        this.spaceInUrls = z2;
        this.parseJekyllMacrosInUrls = z3;
        this.intellijDummyIdentifier = z4;
        this.EXCLUDED_0_TO_SPACE_CHARS = getCharSet((char) 0, ' ');
        if (z4) {
            this.EXCLUDED_0_TO_SPACE_CHARS.clear(31);
        }
        this.JEKYLL_EXCLUDED_CHARS = getCharSet("{}\\");
        this.JEKYLL_EXCLUDED_CHARS.or(this.EXCLUDED_0_TO_SPACE_CHARS);
        this.JEKYLL_EXCLUDED_CHARS.clear(32);
        this.JEKYLL_EXCLUDED_CHARS.clear(9);
        this.PAREN_EXCLUDED_CHARS = getCharSet("()\\");
        this.PAREN_EXCLUDED_CHARS.or(this.EXCLUDED_0_TO_SPACE_CHARS);
        this.PAREN_ESCAPABLE_CHARS = getCharSet(Escaping.ESCAPABLE_CHARS);
        this.PAREN_QUOTE_CHARS = getCharSet("\"'");
    }

    /* JADX WARN: Code restructure failed: missing block: B:95:0x01fa, code lost:            if (r4.PAREN_QUOTE_CHARS.get(r5.safeCharAt(r0)) == false) goto L103;     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v84 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.vladsch.flexmark.util.sequence.BasedSequence parseLinkDestination(com.vladsch.flexmark.util.sequence.BasedSequence r5, int r6) {
        /*
            Method dump skipped, instructions count: 584
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.parser.internal.LinkDestinationParser.parseLinkDestination(com.vladsch.flexmark.util.sequence.BasedSequence, int):com.vladsch.flexmark.util.sequence.BasedSequence");
    }

    public static BitSet getCharSet(CharSequence charSequence) {
        BitSet bitSet = new BitSet(charSequence.length());
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            bitSet.set(charSequence.charAt(i));
        }
        return bitSet;
    }

    public static BitSet getCharSet(char c, char c2) {
        BitSet bitSet = new BitSet();
        for (int i = c; i <= c2; i++) {
            bitSet.set(i);
        }
        return bitSet;
    }
}
