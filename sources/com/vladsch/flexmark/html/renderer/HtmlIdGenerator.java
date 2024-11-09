package com.vladsch.flexmark.html.renderer;

import com.vladsch.flexmark.ast.util.AnchorRefTargetBlockPreVisitor;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html/renderer/HtmlIdGenerator.class */
public interface HtmlIdGenerator {
    public static final HtmlIdGenerator NULL = new HtmlIdGenerator() { // from class: com.vladsch.flexmark.html.renderer.HtmlIdGenerator.1
        @Override // com.vladsch.flexmark.html.renderer.HtmlIdGenerator
        public final void generateIds(Document document) {
        }

        @Override // com.vladsch.flexmark.html.renderer.HtmlIdGenerator
        public final void generateIds(Document document, AnchorRefTargetBlockPreVisitor anchorRefTargetBlockPreVisitor) {
        }

        @Override // com.vladsch.flexmark.html.renderer.HtmlIdGenerator
        public final String getId(Node node) {
            return null;
        }

        @Override // com.vladsch.flexmark.html.renderer.HtmlIdGenerator
        public final String getId(CharSequence charSequence) {
            return null;
        }
    };

    void generateIds(Document document);

    String getId(Node node);

    String getId(CharSequence charSequence);

    default void generateIds(Document document, AnchorRefTargetBlockPreVisitor anchorRefTargetBlockPreVisitor) {
        generateIds(document);
    }
}
