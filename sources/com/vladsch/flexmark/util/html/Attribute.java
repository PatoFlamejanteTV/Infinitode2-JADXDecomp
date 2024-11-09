package com.vladsch.flexmark.util.html;

import com.vladsch.flexmark.util.misc.Immutable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/Attribute.class */
public interface Attribute extends Immutable<Attribute, MutableAttribute> {
    public static final String LINK_STATUS_ATTR = "Link Status";
    public static final String TITLE_ATTR = "title";
    public static final String TARGET_ATTR = "target";

    @Deprecated
    public static final char NUL = 0;
    public static final Attribute NO_FOLLOW = AttributeImpl.of("rel", "nofollow");
    public static final String CLASS_ATTR = "class";
    public static final String ID_ATTR = "id";
    public static final String NAME_ATTR = "name";
    public static final String STYLE_ATTR = "style";
    public static final Set<String> NON_RENDERING_WHEN_EMPTY = new HashSet(Arrays.asList(CLASS_ATTR, ID_ATTR, NAME_ATTR, STYLE_ATTR));

    String getName();

    String getValue();

    char getValueListDelimiter();

    char getValueNameDelimiter();

    boolean isNonRendering();

    boolean containsValue(CharSequence charSequence);

    Attribute replaceValue(CharSequence charSequence);

    Attribute setValue(CharSequence charSequence);

    Attribute removeValue(CharSequence charSequence);
}
