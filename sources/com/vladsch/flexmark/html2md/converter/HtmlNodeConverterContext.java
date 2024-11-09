package com.vladsch.flexmark.html2md.converter;

import com.vladsch.flexmark.ast.Reference;
import com.vladsch.flexmark.html.renderer.LinkType;
import com.vladsch.flexmark.html.renderer.ResolvedLink;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.NodeContext;
import com.vladsch.flexmark.util.html.Attributes;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/HtmlNodeConverterContext.class */
public interface HtmlNodeConverterContext extends NodeContext<Node, HtmlNodeConverterContext> {
    HtmlMarkdownWriter getMarkdown();

    void delegateRender();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.format.NodeContext
    HtmlNodeConverterContext getSubContext();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.format.NodeContext
    HtmlNodeConverterContext getSubContext(DataHolder dataHolder);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.format.NodeContext
    HtmlNodeConverterContext getSubContext(DataHolder dataHolder, ISequenceBuilder<?, ?> iSequenceBuilder);

    void render(Node node);

    void renderChildren(Node node, boolean z, Runnable runnable);

    HtmlConverterPhase getFormattingPhase();

    @Override // com.vladsch.flexmark.util.format.NodeContext
    DataHolder getOptions();

    HtmlConverterOptions getHtmlConverterOptions();

    Document getDocument();

    com.vladsch.flexmark.util.ast.Document getForDocument();

    HtmlConverterState getState();

    HashMap<String, Reference> getReferenceUrlToReferenceMap();

    HashSet<Reference> getExternalReferences();

    boolean isTrace();

    Stack<HtmlConverterState> getStateStack();

    void setTrace(boolean z);

    com.vladsch.flexmark.util.ast.Node parseMarkdown(String str);

    Reference getOrCreateReference(String str, String str2, String str3);

    ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Boolean bool);

    ResolvedLink resolveLink(LinkType linkType, CharSequence charSequence, Attributes attributes, Boolean bool);

    @Override // com.vladsch.flexmark.util.format.NodeContext
    Node getCurrentNode();

    void pushState(Node node);

    void popState(LineAppendable lineAppendable);

    void excludeAttributes(String... strArr);

    void processAttributes(Node node);

    int outputAttributes(LineAppendable lineAppendable, String str);

    void transferIdToParent();

    void transferToParentExcept(String... strArr);

    void transferToParentOnly(String... strArr);

    Node peek();

    Node peek(int i);

    Node next();

    void skip();

    Node next(int i);

    void skip(int i);

    void processUnwrapped(Node node);

    void processWrapped(Node node, Boolean bool, boolean z);

    void processTextNodes(Node node, boolean z);

    void processTextNodes(Node node, boolean z, CharSequence charSequence);

    void processTextNodes(Node node, boolean z, CharSequence charSequence, CharSequence charSequence2);

    void wrapTextNodes(Node node, CharSequence charSequence, boolean z);

    String processTextNodes(Node node);

    void appendOuterHtml(Node node);

    boolean isInlineCode();

    void setInlineCode(boolean z);

    String escapeSpecialChars(String str);

    String prepareText(String str);

    String prepareText(String str, boolean z);

    void inlineCode(Runnable runnable);

    void processConditional(ExtensionConversion extensionConversion, Node node, Runnable runnable);

    void renderDefault(Node node);

    @Override // com.vladsch.flexmark.util.format.NodeContext
    /* bridge */ /* synthetic */ default HtmlNodeConverterContext getSubContext(DataHolder dataHolder, ISequenceBuilder iSequenceBuilder) {
        return getSubContext(dataHolder, (ISequenceBuilder<?, ?>) iSequenceBuilder);
    }
}
