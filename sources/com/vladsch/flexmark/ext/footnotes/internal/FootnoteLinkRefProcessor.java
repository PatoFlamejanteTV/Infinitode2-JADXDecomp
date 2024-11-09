package com.vladsch.flexmark.ext.footnotes.internal;

import com.vladsch.flexmark.ext.footnotes.Footnote;
import com.vladsch.flexmark.ext.footnotes.FootnoteBlock;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.parser.LinkRefProcessor;
import com.vladsch.flexmark.parser.LinkRefProcessorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteLinkRefProcessor.class */
public class FootnoteLinkRefProcessor implements LinkRefProcessor {
    static final boolean WANT_EXCLAMATION_PREFIX = false;
    static final int BRACKET_NESTING_LEVEL = 0;
    private final FootnoteRepository footnoteRepository;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !FootnoteLinkRefProcessor.class.desiredAssertionStatus();
    }

    public FootnoteLinkRefProcessor(Document document) {
        this.footnoteRepository = FootnoteExtension.FOOTNOTES.get(document);
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public boolean getWantExclamationPrefix() {
        return false;
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public int getBracketNestingLevel() {
        return 0;
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public boolean isMatch(BasedSequence basedSequence) {
        return basedSequence.length() >= 3 && basedSequence.charAt(0) == '[' && basedSequence.charAt(1) == '^' && basedSequence.endCharAt(1) == ']';
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public Node createNode(BasedSequence basedSequence) {
        BasedSequence trim = basedSequence.midSequence(2, -1).trim();
        FootnoteBlock footnoteBlock = trim.length() > 0 ? this.footnoteRepository.get(trim.toString()) : null;
        Footnote footnote = new Footnote(basedSequence.subSequence(0, 2), trim, basedSequence.endSequence(1));
        footnote.setFootnoteBlock(footnoteBlock);
        if (footnoteBlock != null) {
            this.footnoteRepository.addFootnoteReference(footnoteBlock, footnote);
        }
        return footnote;
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public BasedSequence adjustInlineText(Document document, Node node) {
        if ($assertionsDisabled || (node instanceof Footnote)) {
            return ((Footnote) node).getText();
        }
        throw new AssertionError();
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public boolean allowDelimiters(BasedSequence basedSequence, Document document, Node node) {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public void updateNodeElements(Document document, Node node) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteLinkRefProcessor$Factory.class */
    public static class Factory implements LinkRefProcessorFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.parser.LinkRefProcessorFactory, java.util.function.Function
        public LinkRefProcessor apply(Document document) {
            return new FootnoteLinkRefProcessor(document);
        }

        @Override // com.vladsch.flexmark.parser.LinkRefProcessorFactory
        public boolean getWantExclamationPrefix(DataHolder dataHolder) {
            return false;
        }

        @Override // com.vladsch.flexmark.parser.LinkRefProcessorFactory
        public int getBracketNestingLevel(DataHolder dataHolder) {
            return 0;
        }
    }
}
