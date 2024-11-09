package com.vladsch.flexmark.util.sequence.mappers;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.function.Consumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/SpecialLeadInStartsWithCharsHandler.class */
public class SpecialLeadInStartsWithCharsHandler implements SpecialLeadInHandler {
    final CharPredicate predicate;

    protected SpecialLeadInStartsWithCharsHandler(CharPredicate charPredicate) {
        this.predicate = charPredicate;
    }

    @Override // com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler
    public boolean escape(BasedSequence basedSequence, DataHolder dataHolder, Consumer<CharSequence> consumer) {
        if (basedSequence.length() > 0 && this.predicate.test(basedSequence.charAt(0))) {
            consumer.accept("\\");
            consumer.accept(basedSequence);
            return true;
        }
        return false;
    }

    @Override // com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler
    public boolean unEscape(BasedSequence basedSequence, DataHolder dataHolder, Consumer<CharSequence> consumer) {
        if (basedSequence.length() >= 2 && basedSequence.charAt(0) == '\\' && this.predicate.test(basedSequence.charAt(1))) {
            consumer.accept(basedSequence.subSequence(1));
            return true;
        }
        return false;
    }

    public static SpecialLeadInStartsWithCharsHandler create(char c) {
        return new SpecialLeadInStartsWithCharsHandler(CharPredicate.anyOf(c));
    }

    public static SpecialLeadInStartsWithCharsHandler create(CharSequence charSequence) {
        return new SpecialLeadInStartsWithCharsHandler(CharPredicate.anyOf(charSequence));
    }
}
