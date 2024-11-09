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

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/InlineVisitor.class */
public interface InlineVisitor {
    void visit(AutoLink autoLink);

    void visit(Code code);

    void visit(Emphasis emphasis);

    void visit(HardLineBreak hardLineBreak);

    void visit(HtmlEntity htmlEntity);

    void visit(HtmlInline htmlInline);

    void visit(HtmlInlineComment htmlInlineComment);

    void visit(Image image);

    void visit(ImageRef imageRef);

    void visit(Link link);

    void visit(LinkRef linkRef);

    void visit(MailLink mailLink);

    void visit(SoftLineBreak softLineBreak);

    void visit(StrongEmphasis strongEmphasis);

    void visit(Text text);
}
