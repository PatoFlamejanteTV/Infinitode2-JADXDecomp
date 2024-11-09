package com.vladsch.flexmark.util.options;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/options/ParsedOptionStatus.class */
public enum ParsedOptionStatus {
    VALID(0),
    IGNORED(1),
    WEAK_WARNING(2),
    WARNING(3),
    ERROR(4);

    private final int level;

    ParsedOptionStatus(int i) {
        this.level = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ParsedOptionStatus escalate(ParsedOptionStatus parsedOptionStatus) {
        return this.level < parsedOptionStatus.level ? parsedOptionStatus : this;
    }
}
