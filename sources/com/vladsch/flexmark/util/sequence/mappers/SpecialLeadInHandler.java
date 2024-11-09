package com.vladsch.flexmark.util.sequence.mappers;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.function.Consumer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/mappers/SpecialLeadInHandler.class */
public interface SpecialLeadInHandler {
    boolean escape(BasedSequence basedSequence, DataHolder dataHolder, Consumer<CharSequence> consumer);

    boolean unEscape(BasedSequence basedSequence, DataHolder dataHolder, Consumer<CharSequence> consumer);
}
