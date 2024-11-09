package com.vladsch.flexmark.ext.emoji;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/emoji/EmojiVisitorExt.class */
public class EmojiVisitorExt {
    public static <V extends EmojiVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(Emoji.class, v::visit)};
    }
}
