package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.BlockQuote;
import com.vladsch.flexmark.ast.BulletList;
import com.vladsch.flexmark.ast.BulletListItem;
import com.vladsch.flexmark.ast.FencedCodeBlock;
import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ast.HtmlBlock;
import com.vladsch.flexmark.ast.HtmlCommentBlock;
import com.vladsch.flexmark.ast.IndentedCodeBlock;
import com.vladsch.flexmark.ast.OrderedList;
import com.vladsch.flexmark.ast.OrderedListItem;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.Reference;
import com.vladsch.flexmark.ast.ThematicBreak;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/BlockVisitorExt.class */
public class BlockVisitorExt {
    public static <V extends BlockVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
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
        return new VisitHandler[]{new VisitHandler<>(BlockQuote.class, v::visit), new VisitHandler<>(BulletList.class, v::visit), new VisitHandler<>(Document.class, v::visit), new VisitHandler<>(FencedCodeBlock.class, v::visit), new VisitHandler<>(Heading.class, v::visit), new VisitHandler<>(HtmlBlock.class, v::visit), new VisitHandler<>(HtmlCommentBlock.class, v::visit), new VisitHandler<>(IndentedCodeBlock.class, v::visit), new VisitHandler<>(BulletListItem.class, v::visit), new VisitHandler<>(OrderedListItem.class, v::visit), new VisitHandler<>(OrderedList.class, v::visit), new VisitHandler<>(Paragraph.class, v::visit), new VisitHandler<>(Reference.class, v::visit), new VisitHandler<>(ThematicBreak.class, v::visit)};
    }
}
