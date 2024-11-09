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

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/BlockVisitor.class */
public interface BlockVisitor {
    void visit(BlockQuote blockQuote);

    void visit(BulletList bulletList);

    void visit(Document document);

    void visit(FencedCodeBlock fencedCodeBlock);

    void visit(Heading heading);

    void visit(HtmlBlock htmlBlock);

    void visit(HtmlCommentBlock htmlCommentBlock);

    void visit(IndentedCodeBlock indentedCodeBlock);

    void visit(BulletListItem bulletListItem);

    void visit(OrderedListItem orderedListItem);

    void visit(OrderedList orderedList);

    void visit(Paragraph paragraph);

    void visit(Reference reference);

    void visit(ThematicBreak thematicBreak);
}
