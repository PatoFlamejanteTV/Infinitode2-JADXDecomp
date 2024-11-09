package com.vladsch.flexmark.ext.gitlab.internal;

import com.vladsch.flexmark.ext.gitlab.GitLabExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;
import com.vladsch.flexmark.util.misc.CharPredicate;
import java.util.HashSet;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/internal/GitLabOptions.class */
public class GitLabOptions implements MutableDataSetter {
    public final boolean insParser;
    public final boolean delParser;
    public final boolean inlineMathParser;
    public final boolean blockQuoteParser;
    public final boolean nestedBlockQuotes;
    public final boolean renderBlockMath;
    public final boolean renderBlockMermaid;
    public final boolean renderVideoImages;
    public final boolean renderVideoLink;
    public final String inlineMathClass;
    public final String blockMathClass;
    public final String blockMermaidClass;
    public final String[] mathLanguages;
    public final String[] mermaidLanguages;
    public final String videoImageClass;
    public final String videoImageLinkTextFormat;
    public final String videoImageExtensions;
    public final HashSet<String> videoImageExtensionSet = new HashSet<>();

    @Deprecated
    public final String blockInfoDelimiters;

    @Deprecated
    public final CharPredicate blockInfoDelimiterSet;

    public GitLabOptions(DataHolder dataHolder) {
        this.insParser = GitLabExtension.INS_PARSER.get(dataHolder).booleanValue();
        this.delParser = GitLabExtension.DEL_PARSER.get(dataHolder).booleanValue();
        this.inlineMathParser = GitLabExtension.INLINE_MATH_PARSER.get(dataHolder).booleanValue();
        this.blockQuoteParser = GitLabExtension.BLOCK_QUOTE_PARSER.get(dataHolder).booleanValue();
        this.nestedBlockQuotes = GitLabExtension.NESTED_BLOCK_QUOTES.get(dataHolder).booleanValue();
        this.inlineMathClass = GitLabExtension.INLINE_MATH_CLASS.get(dataHolder);
        this.renderBlockMath = GitLabExtension.RENDER_BLOCK_MATH.get(dataHolder).booleanValue();
        this.renderBlockMermaid = GitLabExtension.RENDER_BLOCK_MERMAID.get(dataHolder).booleanValue();
        this.renderVideoImages = GitLabExtension.RENDER_VIDEO_IMAGES.get(dataHolder).booleanValue();
        this.renderVideoLink = GitLabExtension.RENDER_VIDEO_LINK.get(dataHolder).booleanValue();
        this.blockMathClass = GitLabExtension.BLOCK_MATH_CLASS.get(dataHolder);
        this.blockMermaidClass = GitLabExtension.BLOCK_MERMAID_CLASS.get(dataHolder);
        this.blockInfoDelimiters = HtmlRenderer.FENCED_CODE_LANGUAGE_DELIMITERS.get(dataHolder);
        this.blockInfoDelimiterSet = CharPredicate.anyOf(this.blockInfoDelimiters);
        this.mathLanguages = GitLabExtension.MATH_LANGUAGES.get(dataHolder);
        this.mermaidLanguages = GitLabExtension.MERMAID_LANGUAGES.get(dataHolder);
        this.videoImageClass = GitLabExtension.VIDEO_IMAGE_CLASS.get(dataHolder);
        this.videoImageLinkTextFormat = GitLabExtension.VIDEO_IMAGE_LINK_TEXT_FORMAT.get(dataHolder);
        this.videoImageExtensions = GitLabExtension.VIDEO_IMAGE_EXTENSIONS.get(dataHolder);
        for (String str : this.videoImageExtensions.split(",")) {
            String trim = str.trim();
            if (!trim.isEmpty()) {
                this.videoImageExtensionSet.add(trim);
            }
        }
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) GitLabExtension.INS_PARSER, (DataKey<Boolean>) Boolean.valueOf(this.insParser));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) GitLabExtension.DEL_PARSER, (DataKey<Boolean>) Boolean.valueOf(this.delParser));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) GitLabExtension.INLINE_MATH_PARSER, (DataKey<Boolean>) Boolean.valueOf(this.inlineMathParser));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) GitLabExtension.BLOCK_QUOTE_PARSER, (DataKey<Boolean>) Boolean.valueOf(this.blockQuoteParser));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) GitLabExtension.NESTED_BLOCK_QUOTES, (DataKey<Boolean>) Boolean.valueOf(this.nestedBlockQuotes));
        mutableDataHolder.set((DataKey<DataKey<String>>) GitLabExtension.INLINE_MATH_CLASS, (DataKey<String>) this.inlineMathClass);
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) GitLabExtension.RENDER_BLOCK_MATH, (DataKey<Boolean>) Boolean.valueOf(this.renderBlockMath));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) GitLabExtension.RENDER_BLOCK_MERMAID, (DataKey<Boolean>) Boolean.valueOf(this.renderBlockMermaid));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) GitLabExtension.RENDER_VIDEO_IMAGES, (DataKey<Boolean>) Boolean.valueOf(this.renderVideoImages));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) GitLabExtension.RENDER_VIDEO_LINK, (DataKey<Boolean>) Boolean.valueOf(this.renderVideoLink));
        mutableDataHolder.set((DataKey<DataKey<String>>) GitLabExtension.BLOCK_MATH_CLASS, (DataKey<String>) this.blockMathClass);
        mutableDataHolder.set((DataKey<DataKey<String>>) GitLabExtension.BLOCK_MERMAID_CLASS, (DataKey<String>) this.blockMermaidClass);
        mutableDataHolder.set((DataKey<DataKey<String>>) HtmlRenderer.FENCED_CODE_LANGUAGE_DELIMITERS, (DataKey<String>) this.blockInfoDelimiters);
        mutableDataHolder.set((DataKey<DataKey<String>>) GitLabExtension.VIDEO_IMAGE_CLASS, (DataKey<String>) this.videoImageClass);
        mutableDataHolder.set((DataKey<DataKey<String>>) GitLabExtension.VIDEO_IMAGE_LINK_TEXT_FORMAT, (DataKey<String>) this.videoImageLinkTextFormat);
        mutableDataHolder.set((DataKey<DataKey<String>>) GitLabExtension.VIDEO_IMAGE_EXTENSIONS, (DataKey<String>) this.videoImageExtensions);
        return mutableDataHolder;
    }
}
