package com.vladsch.flexmark.util.html;

import com.vladsch.flexmark.util.sequence.LineAppendable;
import java.util.List;
import java.util.Stack;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/HtmlAppendable.class */
public interface HtmlAppendable extends LineAppendable {
    Attributes getAttributes();

    HtmlAppendable setAttributes(Attributes attributes);

    boolean inPre();

    HtmlAppendable openPre();

    HtmlAppendable closePre();

    HtmlAppendable raw(CharSequence charSequence);

    HtmlAppendable raw(CharSequence charSequence, int i);

    HtmlAppendable rawPre(CharSequence charSequence);

    HtmlAppendable rawIndentedPre(CharSequence charSequence);

    HtmlAppendable text(CharSequence charSequence);

    HtmlAppendable attr(CharSequence charSequence, CharSequence charSequence2);

    HtmlAppendable attr(Attribute... attributeArr);

    HtmlAppendable attr(Attributes attributes);

    HtmlAppendable withAttr();

    Stack<String> getOpenTags();

    List<String> getOpenTagsAfterLast(CharSequence charSequence);

    HtmlAppendable withCondLineOnChildText();

    HtmlAppendable withCondIndent();

    HtmlAppendable tagVoid(CharSequence charSequence);

    HtmlAppendable tag(CharSequence charSequence);

    HtmlAppendable tag(CharSequence charSequence, Runnable runnable);

    HtmlAppendable tag(CharSequence charSequence, boolean z);

    HtmlAppendable tag(CharSequence charSequence, boolean z, boolean z2, Runnable runnable);

    HtmlAppendable tagVoidLine(CharSequence charSequence);

    HtmlAppendable tagLine(CharSequence charSequence);

    HtmlAppendable tagLine(CharSequence charSequence, boolean z);

    HtmlAppendable tagLine(CharSequence charSequence, Runnable runnable);

    HtmlAppendable tagIndent(CharSequence charSequence, Runnable runnable);

    HtmlAppendable tagLineIndent(CharSequence charSequence, Runnable runnable);

    HtmlAppendable closeTag(CharSequence charSequence);
}
