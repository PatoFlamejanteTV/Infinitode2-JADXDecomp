package com.vladsch.flexmark.formatter.internal;

import com.vladsch.flexmark.formatter.MergeContext;
import com.vladsch.flexmark.formatter.MergeContextConsumer;
import com.vladsch.flexmark.formatter.TranslationContext;
import com.vladsch.flexmark.formatter.TranslationHandler;
import com.vladsch.flexmark.util.ast.Document;
import java.util.HashMap;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/internal/MergeContextImpl.class */
public class MergeContextImpl implements MergeContext {
    private Document[] myDocuments;
    private TranslationHandler[] myTranslationHandlers;
    private final HashMap<TranslationContext, Document> myTranslationHandlerDocumentMap;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !MergeContextImpl.class.desiredAssertionStatus();
    }

    public MergeContextImpl(Document[] documentArr, TranslationHandler[] translationHandlerArr) {
        if (!$assertionsDisabled && documentArr.length != translationHandlerArr.length) {
            throw new AssertionError();
        }
        this.myDocuments = documentArr;
        this.myTranslationHandlers = translationHandlerArr;
        this.myTranslationHandlerDocumentMap = new HashMap<>();
        updateDocumentMap();
        for (TranslationHandler translationHandler : this.myTranslationHandlers) {
            translationHandler.setMergeContext(this);
        }
    }

    private void updateDocumentMap() {
        int length = this.myDocuments.length;
        for (int i = 0; i < length; i++) {
            this.myTranslationHandlerDocumentMap.put(this.myTranslationHandlers[i], this.myDocuments[i]);
        }
    }

    public Document[] getDocuments() {
        return this.myDocuments;
    }

    public void setDocuments(Document[] documentArr) {
        if (!$assertionsDisabled && documentArr.length != this.myTranslationHandlers.length) {
            throw new AssertionError();
        }
        this.myDocuments = documentArr;
        updateDocumentMap();
    }

    public TranslationHandler[] getTranslationHandlers() {
        return this.myTranslationHandlers;
    }

    @Override // com.vladsch.flexmark.formatter.MergeContext
    public Document getDocument(TranslationContext translationContext) {
        return this.myTranslationHandlerDocumentMap.get(translationContext);
    }

    @Override // com.vladsch.flexmark.formatter.MergeContext
    public void forEachPrecedingDocument(Document document, MergeContextConsumer mergeContextConsumer) {
        int length = this.myDocuments.length;
        for (int i = 0; i < length && this.myDocuments[i] != document; i++) {
            mergeContextConsumer.accept(this.myTranslationHandlers[i], this.myDocuments[i], i);
        }
    }
}
