package com.vladsch.flexmark.util.options;

import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/options/ParserMessage.class */
public class ParserMessage {
    protected final BasedSequence source;
    protected final ParsedOptionStatus status;
    protected final String message;

    public ParserMessage(BasedSequence basedSequence, ParsedOptionStatus parsedOptionStatus, String str) {
        this.source = basedSequence;
        this.status = parsedOptionStatus;
        this.message = str;
    }

    public BasedSequence getSource() {
        return this.source;
    }

    public ParsedOptionStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
