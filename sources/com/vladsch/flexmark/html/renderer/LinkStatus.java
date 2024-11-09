package com.vladsch.flexmark.html.renderer;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/LinkStatus.class */
public class LinkStatus {
    public static final LinkStatus UNKNOWN = new LinkStatus("UNKNOWN");
    public static final LinkStatus VALID = new LinkStatus("VALID");
    public static final LinkStatus INVALID = new LinkStatus("INVALID");
    public static final LinkStatus UNCHECKED = new LinkStatus("UNCHECKED");
    public static final LinkStatus NOT_FOUND = new LinkStatus("NOT_FOUND");
    private final String myName;

    public LinkStatus(String str) {
        this.myName = str;
    }

    public String getName() {
        return this.myName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LinkStatus) {
            return this.myName.equals(((LinkStatus) obj).myName);
        }
        return false;
    }

    public int hashCode() {
        return this.myName.hashCode();
    }

    public boolean isStatus(CharSequence charSequence) {
        return this.myName.equals(charSequence instanceof String ? (String) charSequence : String.valueOf(charSequence));
    }
}
