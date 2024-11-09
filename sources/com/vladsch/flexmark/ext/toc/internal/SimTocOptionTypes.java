package com.vladsch.flexmark.ext.toc.internal;

import com.vladsch.flexmark.ext.toc.internal.TocOptions;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.options.BooleanOptionParser;
import com.vladsch.flexmark.util.options.MessageProvider;
import com.vladsch.flexmark.util.options.OptionParser;
import com.vladsch.flexmark.util.options.ParsedOption;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocOptionTypes.class */
public enum SimTocOptionTypes implements OptionParser<TocOptions> {
    LEVELS(new TocLevelsOptionParser("levels")),
    HTML(new BooleanOptionParser<TocOptions>("html") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return tocOptions.isHtml;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withIsHtml(true);
        }
    }),
    MARKDOWN(new BooleanOptionParser<TocOptions>("markdown") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return !tocOptions.isHtml;
        }

        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withIsHtml(false);
        }
    }),
    BULLETS(new BooleanOptionParser<TocOptions>("bullet") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.3
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return !tocOptions.isNumbered;
        }

        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withIsNumbered(false);
        }
    }),
    NUMERIC(new BooleanOptionParser<TocOptions>("numbered") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return tocOptions.isNumbered;
        }

        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withIsNumbered(true);
        }
    }),
    TEXT(new BooleanOptionParser<TocOptions>("text") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.5
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return tocOptions.isTextOnly;
        }

        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withIsTextOnly(true);
        }
    }),
    FORMATTED(new BooleanOptionParser<TocOptions>("formatted") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.6
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return !tocOptions.isTextOnly;
        }

        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withIsTextOnly(false);
        }
    }),
    HIERARCHY(new BooleanOptionParser<TocOptions>("hierarchy") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.7
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return tocOptions.listType == TocOptions.ListType.HIERARCHY;
        }

        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withListType(TocOptions.ListType.HIERARCHY);
        }
    }),
    FLAT(new BooleanOptionParser<TocOptions>("flat") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.8
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return tocOptions.listType == TocOptions.ListType.FLAT;
        }

        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withListType(TocOptions.ListType.FLAT);
        }
    }),
    FLAT_REVERSED(new BooleanOptionParser<TocOptions>("reversed") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.9
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return tocOptions.listType == TocOptions.ListType.FLAT_REVERSED;
        }

        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withListType(TocOptions.ListType.FLAT_REVERSED);
        }
    }),
    SORTED(new BooleanOptionParser<TocOptions>("increasing") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.10
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return tocOptions.listType == TocOptions.ListType.SORTED;
        }

        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withListType(TocOptions.ListType.SORTED);
        }
    }),
    SORTED_REVERSED(new BooleanOptionParser<TocOptions>("decreasing") { // from class: com.vladsch.flexmark.ext.toc.internal.SimTocOptionTypes.11
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final boolean isOptionSet(TocOptions tocOptions) {
            return tocOptions.listType == TocOptions.ListType.SORTED_REVERSED;
        }

        @Override // com.vladsch.flexmark.util.options.BooleanOptionParser
        public final TocOptions setOptions(TocOptions tocOptions) {
            return tocOptions.withListType(TocOptions.ListType.SORTED_REVERSED);
        }
    });

    public final OptionParser<TocOptions> parser;
    public static final OptionParser<TocOptions>[] OPTIONS = values();

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public final String getOptionName() {
        return this.parser.getOptionName();
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public final Pair<TocOptions, List<ParsedOption<TocOptions>>> parseOption(BasedSequence basedSequence, TocOptions tocOptions, MessageProvider messageProvider) {
        return this.parser.parseOption(basedSequence, tocOptions, messageProvider);
    }

    @Override // com.vladsch.flexmark.util.options.OptionParser
    public final String getOptionText(TocOptions tocOptions, TocOptions tocOptions2) {
        return this.parser.getOptionText(tocOptions, tocOptions2);
    }

    SimTocOptionTypes(OptionParser optionParser) {
        this.parser = optionParser;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/internal/SimTocOptionTypes$Constants.class */
    private static class Constants {
        static final String OPTION_BULLET = "bullet";
        static final String OPTION_NUMBERED = "numbered";
        static final String OPTION_MARKDOWN = "markdown";
        static final String OPTION_HTML = "html";
        static final String OPTION_TEXT = "text";
        static final String OPTION_FORMATTED = "formatted";
        static final String OPTION_HIERARCHY = "hierarchy";
        static final String OPTION_FLAT = "flat";
        static final String OPTION_FLAT_REVERSED = "reversed";
        static final String OPTION_SORTED = "increasing";
        static final String OPTION_SORTED_REVERSED = "decreasing";
        static final String OPTION_LEVELS = "levels";

        private Constants() {
        }
    }
}
