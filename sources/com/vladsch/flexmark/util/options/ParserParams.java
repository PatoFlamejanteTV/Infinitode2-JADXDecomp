package com.vladsch.flexmark.util.options;

import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/options/ParserParams.class */
public class ParserParams {
    public List<ParserMessage> messages = null;
    public boolean skip = false;
    public ParsedOptionStatus status = ParsedOptionStatus.VALID;

    public ParserParams add(ParserMessage parserMessage) {
        if (this.messages == null) {
            this.messages = new ArrayList();
        }
        this.messages.add(parserMessage);
        escalate(parserMessage.getStatus());
        return this;
    }

    public ParserParams escalate(ParsedOptionStatus parsedOptionStatus) {
        this.status = this.status.escalate(parsedOptionStatus);
        return this;
    }
}
