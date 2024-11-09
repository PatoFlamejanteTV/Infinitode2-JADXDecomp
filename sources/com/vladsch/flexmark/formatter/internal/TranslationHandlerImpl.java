package com.vladsch.flexmark.formatter.internal;

import com.vladsch.flexmark.ast.AnchorRefTarget;
import com.vladsch.flexmark.formatter.FormatterOptions;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.MergeContext;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.RenderPurpose;
import com.vladsch.flexmark.formatter.TranslatingSpanRender;
import com.vladsch.flexmark.formatter.TranslationHandler;
import com.vladsch.flexmark.formatter.TranslationPlaceholderGenerator;
import com.vladsch.flexmark.html.renderer.HtmlIdGenerator;
import com.vladsch.flexmark.html.renderer.HtmlIdGeneratorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/internal/TranslationHandlerImpl.class */
public class TranslationHandlerImpl implements TranslationHandler {
    final FormatterOptions myFormatterOptions;
    final HtmlIdGeneratorFactory myIdGeneratorFactory;
    final Pattern myPlaceHolderMarkerPattern;
    private RenderPurpose myRenderPurpose;
    private MarkdownWriter myWriter;
    private HtmlIdGenerator myIdGenerator;
    private TranslationPlaceholderGenerator myPlaceholderGenerator;
    private int myPlaceholderId = 0;
    private int myAnchorId = 0;
    private int myTranslatingSpanId = 0;
    private int myNonTranslatingSpanId = 0;
    private Function<String, CharSequence> myNonTranslatingPostProcessor = null;
    private MergeContext myMergeContext = null;
    final HashMap<String, String> myNonTranslatingTexts = new HashMap<>();
    final HashMap<String, String> myAnchorTexts = new HashMap<>();
    final HashMap<String, String> myTranslatingTexts = new HashMap<>();
    final HashMap<String, String> myTranslatedTexts = new HashMap<>();
    final HashMap<String, String> myOriginalAnchors = new HashMap<>();
    final HashMap<String, String> myTranslatedAnchors = new HashMap<>();
    final HashMap<Integer, String> myTranslatedRefTargets = new HashMap<>();
    final HashMap<String, Integer> myOriginalRefTargets = new HashMap<>();
    final ArrayList<String> myTranslatingSpans = new ArrayList<>();
    final ArrayList<String> myTranslatedSpans = new ArrayList<>();
    final ArrayList<String> myTranslatingPlaceholders = new ArrayList<>();
    final ArrayList<String> myNonTranslatingSpans = new ArrayList<>();
    final MutableDataSet myTranslationStore = new MutableDataSet();

    public TranslationHandlerImpl(DataHolder dataHolder, HtmlIdGeneratorFactory htmlIdGeneratorFactory) {
        this.myFormatterOptions = new FormatterOptions(dataHolder);
        this.myIdGeneratorFactory = htmlIdGeneratorFactory;
        this.myPlaceHolderMarkerPattern = Pattern.compile(this.myFormatterOptions.translationExcludePattern);
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public MergeContext getMergeContext() {
        return this.myMergeContext;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationHandler
    public void setMergeContext(MergeContext mergeContext) {
        this.myMergeContext = mergeContext;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public MutableDataSet getTranslationStore() {
        return this.myTranslationStore;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public HtmlIdGenerator getIdGenerator() {
        return this.myIdGenerator;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationHandler
    public void beginRendering(Document document, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        this.myWriter = markdownWriter;
        this.myIdGenerator = this.myIdGeneratorFactory.create();
        this.myIdGenerator.generateIds(document);
    }

    static boolean isNotBlank(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationHandler
    public List<String> getTranslatingTexts() {
        this.myTranslatingPlaceholders.clear();
        this.myTranslatingPlaceholders.ensureCapacity(this.myTranslatedSpans.size() + this.myTranslatedTexts.size());
        ArrayList arrayList = new ArrayList(this.myTranslatedSpans.size() + this.myTranslatedTexts.size());
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : this.myTranslatingTexts.entrySet()) {
            if (isNotBlank(entry.getValue()) && !this.myPlaceHolderMarkerPattern.matcher(entry.getValue()).matches() && !hashMap.containsKey(entry.getValue())) {
                hashMap.put(entry.getValue(), Integer.valueOf(arrayList.size()));
                arrayList.add(entry.getValue());
                this.myTranslatingPlaceholders.add(entry.getKey());
            }
        }
        Iterator<String> it = this.myTranslatingSpans.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (isNotBlank(next) && !this.myPlaceHolderMarkerPattern.matcher(next).matches()) {
                arrayList.add(next.toString());
            }
        }
        return arrayList;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationHandler
    public void setTranslatedTexts(List<? extends CharSequence> list) {
        this.myTranslatedTexts.clear();
        this.myTranslatedTexts.putAll(this.myTranslatingTexts);
        this.myTranslatedSpans.clear();
        this.myTranslatedSpans.ensureCapacity(this.myTranslatingSpans.size());
        int i = 0;
        list.size();
        int size = this.myTranslatingPlaceholders.size();
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : this.myTranslatingTexts.entrySet()) {
            if (isNotBlank(entry.getValue()) && !this.myPlaceHolderMarkerPattern.matcher(entry.getValue()).matches()) {
                Integer num = (Integer) hashMap.get(entry.getValue());
                if (num != null) {
                    this.myTranslatedTexts.put(entry.getKey(), list.get(num.intValue()).toString());
                } else {
                    if (i >= size) {
                        break;
                    }
                    hashMap.put(entry.getValue(), Integer.valueOf(i));
                    this.myTranslatedTexts.put(entry.getKey(), list.get(i).toString());
                    i++;
                }
            }
        }
        Iterator<String> it = this.myTranslatingSpans.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!isNotBlank(next) || this.myPlaceHolderMarkerPattern.matcher(next).matches()) {
                this.myTranslatedSpans.add(next.toString());
            } else {
                this.myTranslatedSpans.add(list.get(i).toString());
                i++;
            }
        }
    }

    @Override // com.vladsch.flexmark.formatter.TranslationHandler
    public void setRenderPurpose(RenderPurpose renderPurpose) {
        this.myAnchorId = 0;
        this.myTranslatingSpanId = 0;
        this.myPlaceholderId = 0;
        this.myRenderPurpose = renderPurpose;
        this.myNonTranslatingSpanId = 0;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public RenderPurpose getRenderPurpose() {
        return this.myRenderPurpose;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public boolean isTransformingText() {
        return this.myRenderPurpose != RenderPurpose.FORMAT;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public CharSequence transformAnchorRef(CharSequence charSequence, CharSequence charSequence2) {
        String str;
        switch (this.myRenderPurpose) {
            case TRANSLATION_SPANS:
                String str2 = this.myFormatterOptions.translationIdFormat;
                int i = this.myAnchorId + 1;
                this.myAnchorId = i;
                String format = String.format(str2, Integer.valueOf(i));
                this.myAnchorTexts.put(format, charSequence2.toString());
                return format;
            case TRANSLATED_SPANS:
                String str3 = this.myFormatterOptions.translationIdFormat;
                int i2 = this.myAnchorId + 1;
                this.myAnchorId = i2;
                return String.format(str3, Integer.valueOf(i2));
            case TRANSLATED:
                String str4 = this.myFormatterOptions.translationIdFormat;
                int i3 = this.myAnchorId + 1;
                this.myAnchorId = i3;
                String format2 = String.format(str4, Integer.valueOf(i3));
                String str5 = this.myNonTranslatingTexts.get(charSequence.toString());
                if (str5 != null && str5.length() == 0) {
                    String str6 = this.myAnchorTexts.get(format2);
                    if (str6 != null) {
                        Integer num = this.myOriginalRefTargets.get(str6);
                        if (num != null && (str = this.myTranslatedRefTargets.get(num)) != null) {
                            return str;
                        }
                        return str6;
                    }
                } else {
                    String str7 = this.myAnchorTexts.get(format2);
                    if (str7 != null) {
                        return str7;
                    }
                }
                break;
        }
        return charSequence2;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public void customPlaceholderFormat(TranslationPlaceholderGenerator translationPlaceholderGenerator, TranslatingSpanRender translatingSpanRender) {
        if (this.myRenderPurpose != RenderPurpose.TRANSLATED_SPANS) {
            TranslationPlaceholderGenerator translationPlaceholderGenerator2 = this.myPlaceholderGenerator;
            this.myPlaceholderGenerator = translationPlaceholderGenerator;
            translatingSpanRender.render(this.myWriter.getContext(), this.myWriter);
            this.myPlaceholderGenerator = translationPlaceholderGenerator2;
        }
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public void translatingSpan(TranslatingSpanRender translatingSpanRender) {
        translatingRefTargetSpan(null, translatingSpanRender);
    }

    private String renderInSubContext(TranslatingSpanRender translatingSpanRender, boolean z) {
        new StringBuilder();
        MarkdownWriter markdownWriter = this.myWriter;
        NodeFormatterContext subContext = this.myWriter.getContext().getSubContext();
        MarkdownWriter markdown = subContext.getMarkdown();
        this.myWriter = markdown;
        translatingSpanRender.render(subContext, markdown);
        String markdownWriter2 = markdown.toString(2, -1);
        this.myWriter = markdownWriter;
        if (z) {
            this.myWriter.append((CharSequence) markdownWriter2);
        }
        return markdownWriter2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public void translatingRefTargetSpan(Node node, TranslatingSpanRender translatingSpanRender) {
        switch (this.myRenderPurpose) {
            case TRANSLATION_SPANS:
                String renderInSubContext = renderInSubContext(translatingSpanRender, true);
                if (node != 0 && (!(node instanceof AnchorRefTarget) || !((AnchorRefTarget) node).isExplicitAnchorRefId())) {
                    this.myOriginalRefTargets.put(this.myIdGenerator.getId(node), Integer.valueOf(this.myTranslatingSpans.size()));
                }
                this.myTranslatingSpans.add(renderInSubContext);
                return;
            case TRANSLATED_SPANS:
                renderInSubContext(translatingSpanRender, false);
                String str = this.myTranslatedSpans.get(this.myTranslatingSpanId);
                if (node != 0 && (!(node instanceof AnchorRefTarget) || !((AnchorRefTarget) node).isExplicitAnchorRefId())) {
                    this.myTranslatedRefTargets.put(Integer.valueOf(this.myTranslatingSpanId), this.myIdGenerator.getId(str));
                }
                this.myTranslatingSpanId++;
                this.myWriter.append((CharSequence) str);
                return;
            case TRANSLATED:
                if (node != 0 && (!(node instanceof AnchorRefTarget) || !((AnchorRefTarget) node).isExplicitAnchorRefId())) {
                    this.myTranslatedRefTargets.put(Integer.valueOf(this.myTranslatingSpanId), this.myIdGenerator.getId(node));
                }
                this.myTranslatingSpanId++;
                renderInSubContext(translatingSpanRender, true);
                return;
            default:
                translatingSpanRender.render(this.myWriter.getContext(), this.myWriter);
                return;
        }
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public void nonTranslatingSpan(TranslatingSpanRender translatingSpanRender) {
        switch (this.myRenderPurpose) {
            case TRANSLATION_SPANS:
                this.myNonTranslatingSpans.add(renderInSubContext(translatingSpanRender, false));
                this.myNonTranslatingSpanId++;
                this.myWriter.append((CharSequence) getPlaceholderId(this.myFormatterOptions.translationIdFormat, this.myNonTranslatingSpanId, null, null, null));
                return;
            case TRANSLATED_SPANS:
                renderInSubContext(translatingSpanRender, false);
                String str = this.myNonTranslatingSpans.get(this.myNonTranslatingSpanId);
                this.myNonTranslatingSpanId++;
                this.myWriter.append((CharSequence) str);
                return;
            case TRANSLATED:
                renderInSubContext(translatingSpanRender, true);
                this.myNonTranslatingSpanId++;
                return;
            default:
                translatingSpanRender.render(this.myWriter.getContext(), this.myWriter);
                return;
        }
    }

    public String getPlaceholderId(String str, int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        String placeholder = this.myPlaceholderGenerator != null ? this.myPlaceholderGenerator.getPlaceholder(i) : String.format(str, Integer.valueOf(i));
        return (charSequence == null && charSequence2 == null && charSequence3 == null) ? placeholder : addPrefixSuffix(placeholder, charSequence, charSequence2, charSequence3);
    }

    public static String addPrefixSuffix(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
        if (charSequence2 == null && charSequence3 == null && charSequence4 == null) {
            return charSequence.toString();
        }
        StringBuilder sb = new StringBuilder();
        if (charSequence2 != null) {
            sb.append(charSequence2);
        }
        sb.append(charSequence);
        if (charSequence3 != null) {
            sb.append(charSequence3);
        }
        if (charSequence4 != null) {
            sb.append(charSequence4);
        }
        return sb.toString();
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public void postProcessNonTranslating(Function<String, CharSequence> function, Runnable runnable) {
        Function<String, CharSequence> function2 = this.myNonTranslatingPostProcessor;
        try {
            this.myNonTranslatingPostProcessor = function;
            runnable.run();
        } finally {
            this.myNonTranslatingPostProcessor = function2;
        }
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public <T> T postProcessNonTranslating(Function<String, CharSequence> function, Supplier<T> supplier) {
        Function<String, CharSequence> function2 = this.myNonTranslatingPostProcessor;
        try {
            this.myNonTranslatingPostProcessor = function;
            return supplier.get();
        } finally {
            this.myNonTranslatingPostProcessor = function2;
        }
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public boolean isPostProcessingNonTranslating() {
        return this.myNonTranslatingPostProcessor != null;
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public CharSequence transformNonTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
        CharSequence trimmedEOL;
        if (charSequence4 != null) {
            trimmedEOL = charSequence4;
        } else {
            trimmedEOL = BasedSequence.of(charSequence2).trimmedEOL();
        }
        switch (this.myRenderPurpose) {
            case TRANSLATION_SPANS:
                String str = this.myFormatterOptions.translationIdFormat;
                int i = this.myPlaceholderId + 1;
                this.myPlaceholderId = i;
                String placeholderId = getPlaceholderId(str, i, charSequence, charSequence3, trimmedEOL);
                String str2 = placeholderId;
                if (this.myNonTranslatingPostProcessor != null) {
                    str2 = this.myNonTranslatingPostProcessor.apply(placeholderId).toString();
                }
                this.myNonTranslatingTexts.put(str2, charSequence2.toString());
                return str2;
            case TRANSLATED_SPANS:
                String str3 = this.myFormatterOptions.translationIdFormat;
                int i2 = this.myPlaceholderId + 1;
                this.myPlaceholderId = i2;
                String placeholderId2 = getPlaceholderId(str3, i2, charSequence, charSequence3, trimmedEOL);
                if (this.myNonTranslatingPostProcessor != null) {
                    return this.myNonTranslatingPostProcessor.apply(placeholderId2);
                }
                return placeholderId2;
            case TRANSLATED:
                if (charSequence2.length() > 0) {
                    String str4 = this.myNonTranslatingTexts.get(charSequence2.toString());
                    String str5 = str4;
                    if (str4 == null) {
                        str5 = "";
                    }
                    if (this.myNonTranslatingPostProcessor != null) {
                        return this.myNonTranslatingPostProcessor.apply(str5);
                    }
                    return str5;
                }
                return charSequence2;
            default:
                return charSequence2;
        }
    }

    @Override // com.vladsch.flexmark.formatter.TranslationContext
    public CharSequence transformTranslating(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
        switch (this.myRenderPurpose) {
            case TRANSLATION_SPANS:
                String str = this.myFormatterOptions.translationIdFormat;
                int i = this.myPlaceholderId + 1;
                this.myPlaceholderId = i;
                String placeholderId = getPlaceholderId(str, i, charSequence, charSequence3, charSequence4);
                this.myTranslatingTexts.put(placeholderId, charSequence2.toString());
                return placeholderId;
            case TRANSLATED_SPANS:
                String str2 = this.myFormatterOptions.translationIdFormat;
                int i2 = this.myPlaceholderId + 1;
                this.myPlaceholderId = i2;
                return getPlaceholderId(str2, i2, charSequence, charSequence3, charSequence4);
            case TRANSLATED:
                String str3 = this.myTranslatedTexts.get(((charSequence == null && charSequence3 == null && charSequence4 == null) ? charSequence2 : addPrefixSuffix(charSequence2, charSequence, charSequence3, charSequence4)).toString());
                if (str3 != null && (charSequence != null || charSequence3 != null || charSequence4 != null)) {
                    return addPrefixSuffix(str3, charSequence, charSequence3, charSequence4);
                }
                if (str3 != null) {
                    return str3;
                }
                break;
        }
        return charSequence2;
    }
}
