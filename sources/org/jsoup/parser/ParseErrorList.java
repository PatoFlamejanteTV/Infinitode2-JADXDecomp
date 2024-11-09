package org.jsoup.parser;

import java.util.ArrayList;

/* loaded from: infinitode-2.jar:org/jsoup/parser/ParseErrorList.class */
public class ParseErrorList extends ArrayList<ParseError> {
    private static final int INITIAL_CAPACITY = 16;
    private final int initialCapacity;
    private final int maxSize;

    ParseErrorList(int i, int i2) {
        super(i);
        this.initialCapacity = i;
        this.maxSize = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseErrorList(ParseErrorList parseErrorList) {
        this(parseErrorList.initialCapacity, parseErrorList.maxSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean canAddError() {
        return size() < this.maxSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMaxSize() {
        return this.maxSize;
    }

    public static ParseErrorList noTracking() {
        return new ParseErrorList(0, 0);
    }

    public static ParseErrorList tracking(int i) {
        return new ParseErrorList(16, i);
    }

    @Override // java.util.ArrayList
    public Object clone() {
        return super.clone();
    }
}
