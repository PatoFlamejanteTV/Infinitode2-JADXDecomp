package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;
import com.vladsch.flexmark.util.data.NullableDataKey;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.misc.Immutable;
import com.vladsch.flexmark.util.misc.Mutable;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocOptions.class */
public class TocOptions implements MutableDataSetter, Immutable<TocOptions, AsMutable> {
    public static final int DEFAULT_LEVELS = 12;
    public static final String DEFAULT_TITLE = "Table of Contents";
    public static final int DEFAULT_TITLE_LEVEL = 1;
    public static final int VALID_LEVELS = 126;
    public final int levels;
    public final boolean isTextOnly;
    public final boolean isNumbered;
    public final ListType listType;
    public final boolean isHtml;
    public final int titleLevel;
    public final String title;
    public final boolean isAstAddOptions;
    public final boolean isBlankLineSpacer;
    public final String divClass;
    public final String listClass;
    public final boolean isCaseSensitiveTocTag;
    public static final TocOptions DEFAULT = new TocOptions();
    public static final ListType LIST_TYPE = ListType.HIERARCHY;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocOptions$ListType.class */
    public enum ListType {
        HIERARCHY,
        FLAT,
        FLAT_REVERSED,
        SORTED,
        SORTED_REVERSED
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.misc.Immutable
    public AsMutable toMutable() {
        return new AsMutable(this);
    }

    public TocOptions() {
        this(12, false, false, false, 1, DEFAULT_TITLE, ListType.HIERARCHY, false, true, "", "", true);
    }

    public TocOptions(int i, boolean z, boolean z2, boolean z3, ListType listType) {
        this(i, z, z2, z3, 1, DEFAULT_TITLE, listType, false, true, "", "", true);
    }

    public TocOptions(int i, boolean z, boolean z2, boolean z3, int i2, String str, ListType listType) {
        this(i, z, z2, z3, i2, str, listType, false, true, "", "", true);
    }

    public TocOptions(AsMutable asMutable) {
        this(asMutable.levels, asMutable.isHtml, asMutable.isTextOnly, asMutable.isNumbered, asMutable.titleLevel, asMutable.title, asMutable.listType, asMutable.isAstAddOptions, asMutable.isBlankLineSpacer, asMutable.divClass, asMutable.listClass, asMutable.isCaseSensitiveTocTag);
    }

    public TocOptions(TocOptions tocOptions) {
        this(tocOptions.levels, tocOptions.isHtml, tocOptions.isTextOnly, tocOptions.isNumbered, tocOptions.titleLevel, tocOptions.title, tocOptions.listType, tocOptions.isAstAddOptions, tocOptions.isBlankLineSpacer, tocOptions.divClass, tocOptions.listClass, tocOptions.isCaseSensitiveTocTag);
    }

    public TocOptions(DataHolder dataHolder, boolean z) {
        this(TocExtension.LEVELS.get(dataHolder).intValue(), TocExtension.IS_HTML.get(dataHolder).booleanValue(), TocExtension.IS_TEXT_ONLY.get(dataHolder).booleanValue(), TocExtension.IS_NUMBERED.get(dataHolder).booleanValue(), TocExtension.TITLE_LEVEL.get(dataHolder).intValue(), TocExtension.TITLE.get(dataHolder) == null ? z ? DEFAULT_TITLE : "" : TocExtension.TITLE.get(dataHolder), TocExtension.LIST_TYPE.get(dataHolder), TocExtension.AST_INCLUDE_OPTIONS.get(dataHolder).booleanValue(), TocExtension.BLANK_LINE_SPACER.get(dataHolder).booleanValue(), TocExtension.DIV_CLASS.get(dataHolder), TocExtension.LIST_CLASS.get(dataHolder), TocExtension.CASE_SENSITIVE_TOC_TAG.get(dataHolder).booleanValue());
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<Integer>>) TocExtension.LEVELS, (DataKey<Integer>) Integer.valueOf(this.levels));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) TocExtension.IS_TEXT_ONLY, (DataKey<Boolean>) Boolean.valueOf(this.isTextOnly));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) TocExtension.IS_NUMBERED, (DataKey<Boolean>) Boolean.valueOf(this.isNumbered));
        mutableDataHolder.set((DataKey<DataKey<ListType>>) TocExtension.LIST_TYPE, (DataKey<ListType>) this.listType);
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) TocExtension.IS_HTML, (DataKey<Boolean>) Boolean.valueOf(this.isHtml));
        mutableDataHolder.set((DataKey<DataKey<Integer>>) TocExtension.TITLE_LEVEL, (DataKey<Integer>) Integer.valueOf(this.titleLevel));
        mutableDataHolder.set((NullableDataKey<NullableDataKey<String>>) TocExtension.TITLE, (NullableDataKey<String>) this.title);
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) TocExtension.AST_INCLUDE_OPTIONS, (DataKey<Boolean>) Boolean.valueOf(this.isAstAddOptions));
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) TocExtension.BLANK_LINE_SPACER, (DataKey<Boolean>) Boolean.valueOf(this.isBlankLineSpacer));
        mutableDataHolder.set((DataKey<DataKey<String>>) TocExtension.DIV_CLASS, (DataKey<String>) this.divClass);
        mutableDataHolder.set((DataKey<DataKey<String>>) TocExtension.LIST_CLASS, (DataKey<String>) this.listClass);
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) TocExtension.CASE_SENSITIVE_TOC_TAG, (DataKey<Boolean>) Boolean.valueOf(this.isCaseSensitiveTocTag));
        return mutableDataHolder;
    }

    public TocOptions(int i, boolean z, boolean z2, boolean z3, int i2, CharSequence charSequence, ListType listType, boolean z4, boolean z5, CharSequence charSequence2, CharSequence charSequence3, boolean z6) {
        this.levels = 126 & i;
        this.isTextOnly = z2;
        this.isNumbered = z3;
        this.listType = listType;
        this.isHtml = z;
        if (charSequence != null) {
            int countLeading = BasedSequence.of(SequenceUtils.trim(charSequence)).countLeading(CharPredicate.HASH);
            int i3 = countLeading;
            if (countLeading > 0) {
                int min = Math.min(i3, 6);
                i2 = min;
                i3 = min;
            }
            String charSequence4 = SequenceUtils.trim(charSequence.subSequence(i3, charSequence.length())).toString();
            this.title = charSequence4.isEmpty() ? SequenceUtils.SPACE : charSequence4;
        } else {
            this.title = "";
        }
        this.titleLevel = Math.max(1, Math.min(i2, 6));
        this.isAstAddOptions = z4;
        this.isBlankLineSpacer = z5;
        this.divClass = charSequence2 instanceof String ? (String) charSequence2 : String.valueOf(charSequence2);
        this.listClass = charSequence3 instanceof String ? (String) charSequence3 : String.valueOf(charSequence3);
        this.isCaseSensitiveTocTag = z6;
    }

    public boolean isLevelIncluded(int i) {
        return i > 0 && i <= 6 && (this.levels & (1 << i)) != 0;
    }

    public TocOptions withLevels(int i) {
        return new TocOptions(i, this.isHtml, this.isTextOnly, this.isNumbered, this.titleLevel, this.title, this.listType, this.isAstAddOptions, this.isBlankLineSpacer, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withIsHtml(boolean z) {
        return new TocOptions(this.levels, z, this.isTextOnly, this.isNumbered, this.titleLevel, this.title, this.listType, this.isAstAddOptions, this.isBlankLineSpacer, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withIsTextOnly(boolean z) {
        return new TocOptions(this.levels, this.isHtml, z, this.isNumbered, this.titleLevel, this.title, this.listType, this.isAstAddOptions, this.isBlankLineSpacer, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withIsNumbered(boolean z) {
        return new TocOptions(this.levels, this.isHtml, this.isTextOnly, z, this.titleLevel, this.title, this.listType, this.isAstAddOptions, this.isBlankLineSpacer, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withTitleLevel(int i) {
        return new TocOptions(this.levels, this.isHtml, this.isTextOnly, this.isNumbered, i, this.title, this.listType, this.isAstAddOptions, this.isBlankLineSpacer, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withTitle(CharSequence charSequence) {
        return new TocOptions(this.levels, this.isHtml, this.isTextOnly, this.isNumbered, this.titleLevel, charSequence, this.listType, this.isAstAddOptions, this.isBlankLineSpacer, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withListType(ListType listType) {
        return new TocOptions(this.levels, this.isHtml, this.isTextOnly, this.isNumbered, this.titleLevel, this.title, listType, this.isAstAddOptions, this.isBlankLineSpacer, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withIsAstAddOptions(boolean z) {
        return new TocOptions(this.levels, this.isHtml, this.isTextOnly, this.isNumbered, this.titleLevel, this.title, this.listType, z, this.isBlankLineSpacer, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withIsBlankLineSpacer(boolean z) {
        return new TocOptions(this.levels, this.isHtml, this.isTextOnly, this.isNumbered, this.titleLevel, this.title, this.listType, this.isAstAddOptions, z, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withRawTitleLevel(int i) {
        return new TocOptions(this.levels, this.isHtml, this.isTextOnly, this.isNumbered, i, this.title, this.listType, this.isAstAddOptions, this.isBlankLineSpacer, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withRawTitle(CharSequence charSequence) {
        return new TocOptions(this.levels, this.isHtml, this.isTextOnly, this.isNumbered, this.titleLevel, charSequence, this.listType, this.isAstAddOptions, this.isBlankLineSpacer, this.divClass, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withDivClass(CharSequence charSequence) {
        return new TocOptions(this.levels, this.isHtml, this.isTextOnly, this.isNumbered, this.titleLevel, this.title, this.listType, this.isAstAddOptions, this.isBlankLineSpacer, charSequence, this.listClass, this.isCaseSensitiveTocTag);
    }

    public TocOptions withListClass(CharSequence charSequence) {
        return new TocOptions(this.levels, this.isHtml, this.isTextOnly, this.isNumbered, this.titleLevel, this.title, this.listType, this.isAstAddOptions, this.isBlankLineSpacer, this.divClass, charSequence, this.isCaseSensitiveTocTag);
    }

    public TocOptions withLevelList(int... iArr) {
        return withLevels(getLevels(iArr));
    }

    public static int getLevels(int... iArr) {
        int i = 0;
        for (int i2 : iArr) {
            if (i2 <= 0 || i2 > 6) {
                throw new IllegalArgumentException("TocOption level out of range [1, 6]");
            }
            i |= 1 << i2;
        }
        return i;
    }

    public String getTitleHeading() {
        String str = this.title;
        if (!str.trim().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int i = this.titleLevel;
            while (true) {
                int i2 = i;
                i--;
                if (i2 <= 0) {
                    sb.append(' ');
                    sb.append(str);
                    return sb.toString();
                }
                sb.append('#');
            }
        } else {
            return "";
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TocOptions) && !(obj instanceof AsMutable)) {
            return false;
        }
        TocOptions immutable = obj instanceof TocOptions ? (TocOptions) obj : ((AsMutable) obj).toImmutable();
        return this.levels == immutable.levels && this.isTextOnly == immutable.isTextOnly && this.isNumbered == immutable.isNumbered && this.listType == immutable.listType && this.isHtml == immutable.isHtml && this.titleLevel == immutable.titleLevel && this.title.equals(immutable.title) && this.divClass.equals(immutable.divClass) && this.listClass.equals(immutable.listClass) && this.isAstAddOptions == immutable.isAstAddOptions && this.isBlankLineSpacer == immutable.isBlankLineSpacer && this.isCaseSensitiveTocTag == immutable.isCaseSensitiveTocTag;
    }

    public int hashCode() {
        return (((((((((((((((((((((this.levels * 31) + (this.isTextOnly ? 1 : 0)) * 31) + (this.isNumbered ? 1 : 0)) * 31) + this.listType.hashCode()) * 31) + (this.isHtml ? 1 : 0)) * 31) + this.titleLevel) * 31) + this.title.hashCode()) * 31) + this.divClass.hashCode()) * 31) + this.listClass.hashCode()) * 31) + (this.isAstAddOptions ? 1 : 0)) * 31) + (this.isBlankLineSpacer ? 1 : 0)) * 31) + (this.isCaseSensitiveTocTag ? 1 : 0);
    }

    public String toString() {
        return "TocOptions { levels=" + this.levels + ", isHtml=" + this.isHtml + ", isTextOnly=" + this.isTextOnly + ", isNumbered=" + this.isNumbered + ", titleLevel=" + this.titleLevel + ", title='" + this.title + "', listType=" + this.listType + ", divClass='" + this.divClass + "', listClass='" + this.listClass + "' }";
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/TocOptions$AsMutable.class */
    public static class AsMutable implements MutableDataSetter, Mutable<AsMutable, TocOptions> {
        public int levels;
        public boolean isTextOnly;
        public boolean isNumbered;
        public ListType listType;
        public boolean isHtml;
        public int titleLevel;
        public String title;
        public boolean isAstAddOptions;
        public boolean isBlankLineSpacer;
        public String divClass;
        public String listClass;
        public boolean isCaseSensitiveTocTag;

        protected AsMutable(TocOptions tocOptions) {
            this.levels = tocOptions.levels;
            this.isTextOnly = tocOptions.isTextOnly;
            this.isNumbered = tocOptions.isNumbered;
            this.listType = tocOptions.listType;
            this.isHtml = tocOptions.isHtml;
            this.titleLevel = tocOptions.titleLevel;
            this.title = tocOptions.title;
            this.isAstAddOptions = tocOptions.isAstAddOptions;
            this.isBlankLineSpacer = tocOptions.isBlankLineSpacer;
            this.divClass = tocOptions.divClass;
            this.listClass = tocOptions.listClass;
            this.isCaseSensitiveTocTag = tocOptions.isCaseSensitiveTocTag;
        }

        protected AsMutable(AsMutable asMutable) {
            this(asMutable.toImmutable());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.util.misc.Mutable
        public TocOptions toImmutable() {
            return new TocOptions(this);
        }

        public AsMutable normalizeTitle() {
            TocOptions immutable = toImmutable();
            this.title = immutable.title;
            this.titleLevel = immutable.titleLevel;
            return this;
        }

        @Override // com.vladsch.flexmark.util.data.MutableDataSetter
        public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
            return toImmutable().setIn(mutableDataHolder);
        }

        public AsMutable setLevelList(int... iArr) {
            int i = 0;
            for (int i2 : iArr) {
                if (i2 <= 0 || i2 > 6) {
                    throw new IllegalArgumentException("TocOption level out of range [1, 6]");
                }
                i |= 1 << i2;
            }
            this.levels = i;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TocOptions) && !(obj instanceof AsMutable)) {
                return false;
            }
            AsMutable mutable = obj instanceof AsMutable ? (AsMutable) obj : ((TocOptions) obj).toMutable();
            return this.levels == mutable.levels && this.isTextOnly == mutable.isTextOnly && this.isNumbered == mutable.isNumbered && this.listType == mutable.listType && this.isHtml == mutable.isHtml && this.titleLevel == mutable.titleLevel && this.title.equals(mutable.title) && this.divClass.equals(mutable.divClass) && this.listClass.equals(mutable.listClass) && this.isAstAddOptions == mutable.isAstAddOptions && this.isBlankLineSpacer == mutable.isBlankLineSpacer && this.isCaseSensitiveTocTag == mutable.isCaseSensitiveTocTag;
        }

        public int hashCode() {
            return (((((((((((((((((((((this.levels * 31) + (this.isTextOnly ? 1 : 0)) * 31) + (this.isNumbered ? 1 : 0)) * 31) + this.listType.hashCode()) * 31) + (this.isHtml ? 1 : 0)) * 31) + this.titleLevel) * 31) + this.title.hashCode()) * 31) + this.divClass.hashCode()) * 31) + this.listClass.hashCode()) * 31) + (this.isAstAddOptions ? 1 : 0)) * 31) + (this.isBlankLineSpacer ? 1 : 0)) * 31) + (this.isCaseSensitiveTocTag ? 1 : 0);
        }

        public String toString() {
            return "TocOptions { levels=" + this.levels + ", isHtml=" + this.isHtml + ", isTextOnly=" + this.isTextOnly + ", isNumbered=" + this.isNumbered + ", titleLevel=" + this.titleLevel + ", title='" + this.title + "', listType=" + this.listType + ", divClass='" + this.divClass + "', listClass='" + this.listClass + "' }";
        }
    }
}
