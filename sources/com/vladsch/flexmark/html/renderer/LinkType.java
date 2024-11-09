package com.vladsch.flexmark.html.renderer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/LinkType.class */
public class LinkType {
    public static final LinkType LINK = new LinkType("LINK");
    public static final LinkType IMAGE = new LinkType("IMAGE");
    public static final LinkType LINK_REF = new LinkType("LINK_REF");
    public static final LinkType IMAGE_REF = new LinkType("IMAGE_REF");
    private final String myName;

    public LinkType(String str) {
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
