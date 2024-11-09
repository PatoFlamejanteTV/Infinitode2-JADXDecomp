package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.AutoLink;
import com.vladsch.flexmark.ast.Code;
import com.vladsch.flexmark.ast.Emphasis;
import com.vladsch.flexmark.ast.HardLineBreak;
import com.vladsch.flexmark.ast.HtmlEntity;
import com.vladsch.flexmark.ast.HtmlInline;
import com.vladsch.flexmark.ast.HtmlInlineComment;
import com.vladsch.flexmark.ast.Image;
import com.vladsch.flexmark.ast.ImageRef;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ast.LinkRef;
import com.vladsch.flexmark.ast.MailLink;
import com.vladsch.flexmark.ast.SoftLineBreak;
import com.vladsch.flexmark.ast.StrongEmphasis;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/InlineVisitorExt.class */
public class InlineVisitorExt {
    public static <V extends InlineVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(AutoLink.class, v::visit), new VisitHandler<>(Code.class, v::visit), new VisitHandler<>(Emphasis.class, v::visit), new VisitHandler<>(HardLineBreak.class, v::visit), new VisitHandler<>(HtmlEntity.class, v::visit), new VisitHandler<>(HtmlInline.class, v::visit), new VisitHandler<>(HtmlInlineComment.class, v::visit), new VisitHandler<>(Image.class, v::visit), new VisitHandler<>(ImageRef.class, v::visit), new VisitHandler<>(Link.class, v::visit), new VisitHandler<>(LinkRef.class, v::visit), new VisitHandler<>(MailLink.class, v::visit), new VisitHandler<>(SoftLineBreak.class, v::visit), new VisitHandler<>(StrongEmphasis.class, v::visit), new VisitHandler<>(Text.class, v::visit)};
    }
}
