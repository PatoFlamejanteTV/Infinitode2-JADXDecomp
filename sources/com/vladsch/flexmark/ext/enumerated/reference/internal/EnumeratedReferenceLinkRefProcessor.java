package com.vladsch.flexmark.ext.enumerated.reference.internal;

import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceBase;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceBlock;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceLink;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceRepository;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceText;
import com.vladsch.flexmark.parser.LinkRefProcessor;
import com.vladsch.flexmark.parser.LinkRefProcessorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceLinkRefProcessor.class */
public class EnumeratedReferenceLinkRefProcessor implements LinkRefProcessor {
    static final boolean WANT_EXCLAMATION_PREFIX = false;
    static final int BRACKET_NESTING_LEVEL = 0;
    private final EnumeratedReferenceRepository enumeratedReferenceRepository;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !EnumeratedReferenceLinkRefProcessor.class.desiredAssertionStatus();
    }

    public EnumeratedReferenceLinkRefProcessor(Document document) {
        this.enumeratedReferenceRepository = EnumeratedReferenceExtension.ENUMERATED_REFERENCES.get(document);
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
        if (basedSequence.length() < 3 || basedSequence.charAt(0) != '[') {
            return false;
        }
        if ((basedSequence.charAt(1) == '@' || basedSequence.charAt(1) == '#') && basedSequence.endCharAt(1) == ']') {
            return basedSequence.length() == 3 || !Character.isDigit(basedSequence.charAt(2));
        }
        return false;
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public Node createNode(BasedSequence basedSequence) {
        BasedSequence trim = basedSequence.midSequence(2, -1).trim();
        EnumeratedReferenceBlock enumeratedReferenceBlock = trim.length() > 0 ? this.enumeratedReferenceRepository.get(trim.toString()) : null;
        if (basedSequence.charAt(1) == '@') {
            EnumeratedReferenceLink enumeratedReferenceLink = new EnumeratedReferenceLink(basedSequence.subSequence(0, 2), trim, basedSequence.endSequence(1));
            enumeratedReferenceLink.setEnumeratedReferenceBlock(enumeratedReferenceBlock);
            return enumeratedReferenceLink;
        }
        EnumeratedReferenceText enumeratedReferenceText = new EnumeratedReferenceText(basedSequence.subSequence(0, 2), trim, basedSequence.endSequence(1));
        enumeratedReferenceText.setEnumeratedReferenceBlock(enumeratedReferenceBlock);
        return enumeratedReferenceText;
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public BasedSequence adjustInlineText(Document document, Node node) {
        if ($assertionsDisabled || (node instanceof EnumeratedReferenceBase)) {
            return ((EnumeratedReferenceBase) node).getText();
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

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceLinkRefProcessor$Factory.class */
    public static class Factory implements LinkRefProcessorFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.parser.LinkRefProcessorFactory, java.util.function.Function
        public LinkRefProcessor apply(Document document) {
            return new EnumeratedReferenceLinkRefProcessor(document);
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
