package com.vladsch.flexmark.html.renderer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/AttributablePart.class */
public class AttributablePart {
    public static final AttributablePart NODE = new AttributablePart("NODE");
    public static final AttributablePart NODE_POSITION = new AttributablePart("NODE_POSITION");
    public static final AttributablePart LINK = new AttributablePart("LINK");
    public static final AttributablePart ID = new AttributablePart("ID");
    private final String myName;

    public AttributablePart(String str) {
        this.myName = str;
    }

    public String getName() {
        return this.myName;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
