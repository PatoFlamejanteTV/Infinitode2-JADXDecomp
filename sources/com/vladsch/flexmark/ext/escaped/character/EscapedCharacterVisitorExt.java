package com.vladsch.flexmark.ext.escaped.character;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/escaped/character/EscapedCharacterVisitorExt.class */
public class EscapedCharacterVisitorExt {
    public static <V extends EscapedCharacterVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(EscapedCharacter.class, v::visit)};
    }
}
