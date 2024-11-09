package com.vladsch.flexmark.ext.attributes;

import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/AttributeValueQuotes.class */
public enum AttributeValueQuotes {
    AS_IS,
    NO_QUOTES_SINGLE_PREFERRED,
    NO_QUOTES_DOUBLE_PREFERRED,
    SINGLE_PREFERRED,
    DOUBLE_PREFERRED,
    SINGLE_QUOTES,
    DOUBLE_QUOTES;

    static final CharPredicate P_SPACES_OR_QUOTES = CharPredicate.anyOf(" \t\n'\"");
    static final CharPredicate P_SINGLE_QUOTES = CharPredicate.anyOf("'");
    static final CharPredicate P_DOUBLE_QUOTES = CharPredicate.anyOf("\"");

    public final String quotesFor(CharSequence charSequence, CharSequence charSequence2) {
        switch (this) {
            case NO_QUOTES_SINGLE_PREFERRED:
                if (!SequenceUtils.containsAny(charSequence, P_SPACES_OR_QUOTES)) {
                    return "";
                }
                if (!SequenceUtils.containsAny(charSequence, P_SINGLE_QUOTES) || SequenceUtils.containsAny(charSequence, P_DOUBLE_QUOTES)) {
                    return "'";
                }
                return "\"";
            case NO_QUOTES_DOUBLE_PREFERRED:
                if (!SequenceUtils.containsAny(charSequence, P_SPACES_OR_QUOTES)) {
                    return "";
                }
                if (!SequenceUtils.containsAny(charSequence, P_DOUBLE_QUOTES) || SequenceUtils.containsAny(charSequence, P_SINGLE_QUOTES)) {
                    return "\"";
                }
                return "'";
            case SINGLE_PREFERRED:
                if (!SequenceUtils.containsAny(charSequence, P_SINGLE_QUOTES) || SequenceUtils.containsAny(charSequence, P_DOUBLE_QUOTES)) {
                    return "'";
                }
                return "\"";
            case DOUBLE_PREFERRED:
                if (!SequenceUtils.containsAny(charSequence, P_DOUBLE_QUOTES) || SequenceUtils.containsAny(charSequence, P_SINGLE_QUOTES)) {
                    return "\"";
                }
                return "'";
            case SINGLE_QUOTES:
                return "'";
            case DOUBLE_QUOTES:
                return "\"";
            default:
                return charSequence2.toString();
        }
    }
}
