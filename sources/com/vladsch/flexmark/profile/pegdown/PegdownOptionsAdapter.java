package com.vladsch.flexmark.profile.pegdown;

import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.escaped.character.EscapedCharacterExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughSubscriptExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.SubscriptExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.SimTocExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.toc.internal.TocOptions;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.KeepType;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.data.MutableDataSetter;
import com.vladsch.flexmark.util.misc.Extension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/profile/pegdown/PegdownOptionsAdapter.class */
public class PegdownOptionsAdapter {
    public static final DataKey<Integer> PEGDOWN_EXTENSIONS = ParserEmulationProfile.PEGDOWN_EXTENSIONS;
    private final MutableDataSet myOptions;
    private int myPegdownExtensions;
    private boolean myIsUpdateNeeded;

    public PegdownOptionsAdapter() {
        this.myPegdownExtensions = 0;
        this.myIsUpdateNeeded = false;
        this.myOptions = new MutableDataSet();
    }

    public PegdownOptionsAdapter(DataHolder dataHolder) {
        this.myPegdownExtensions = 0;
        this.myIsUpdateNeeded = false;
        this.myOptions = new MutableDataSet(dataHolder);
    }

    public PegdownOptionsAdapter(int i) {
        this.myPegdownExtensions = 0;
        this.myIsUpdateNeeded = false;
        this.myOptions = new MutableDataSet();
        this.myPegdownExtensions = i;
        this.myIsUpdateNeeded = true;
    }

    public static DataHolder flexmarkOptions(int i, Extension... extensionArr) {
        return flexmarkOptions(false, i, extensionArr);
    }

    public static DataHolder flexmarkOptions(boolean z, int i, Extension... extensionArr) {
        return new PegdownOptionsAdapter(i).getFlexmarkOptions(z, extensionArr);
    }

    public boolean haveAnyExtensions(int i) {
        return ParserEmulationProfile.haveAny(this.myPegdownExtensions, i);
    }

    public boolean haveAllExtensions(int i) {
        return ParserEmulationProfile.haveAll(this.myPegdownExtensions, i);
    }

    public DataHolder getFlexmarkOptions(Extension... extensionArr) {
        return getFlexmarkOptions(false, extensionArr);
    }

    public DataHolder getFlexmarkOptions(boolean z, Extension... extensionArr) {
        if (this.myIsUpdateNeeded) {
            this.myIsUpdateNeeded = false;
            MutableDataSet mutableDataSet = this.myOptions;
            ArrayList arrayList = new ArrayList(Arrays.asList(extensionArr));
            mutableDataSet.clear();
            mutableDataSet.set((DataKey<DataKey<Integer>>) ParserEmulationProfile.PEGDOWN_EXTENSIONS, (DataKey<Integer>) Integer.valueOf(this.myPegdownExtensions));
            mutableDataSet.setFrom((MutableDataSetter) (z ? ParserEmulationProfile.PEGDOWN_STRICT : ParserEmulationProfile.PEGDOWN));
            mutableDataSet.set((DataKey<DataKey<Boolean>>) HtmlRenderer.SUPPRESS_HTML_BLOCKS, (DataKey<Boolean>) Boolean.valueOf(haveAnyExtensions(65536)));
            mutableDataSet.set((DataKey<DataKey<Boolean>>) HtmlRenderer.SUPPRESS_INLINE_HTML, (DataKey<Boolean>) Boolean.valueOf(haveAnyExtensions(131072)));
            arrayList.add(EscapedCharacterExtension.create());
            if (haveAnyExtensions(4)) {
                arrayList.add(AbbreviationExtension.create());
                mutableDataSet.set((DataKey<DataKey<KeepType>>) AbbreviationExtension.ABBREVIATIONS_KEEP, (DataKey<KeepType>) KeepType.LAST);
            }
            if (haveAnyExtensions(4195328)) {
                mutableDataSet.set((DataKey<DataKey<Boolean>>) HtmlRenderer.RENDER_HEADER_ID, (DataKey<Boolean>) Boolean.FALSE);
                arrayList.add(AnchorLinkExtension.create());
                if (haveAnyExtensions(4194304)) {
                    mutableDataSet.set((DataKey<DataKey<Boolean>>) AnchorLinkExtension.ANCHORLINKS_WRAP_TEXT, (DataKey<Boolean>) Boolean.FALSE);
                } else if (haveAnyExtensions(1024)) {
                    mutableDataSet.set((DataKey<DataKey<Boolean>>) AnchorLinkExtension.ANCHORLINKS_WRAP_TEXT, (DataKey<Boolean>) Boolean.TRUE);
                }
            }
            if (haveAnyExtensions(16)) {
                arrayList.add(AutolinkExtension.create());
            }
            if (haveAnyExtensions(64)) {
                arrayList.add(DefinitionExtension.create());
            }
            if (!haveAnyExtensions(128)) {
                mutableDataSet.set((DataKey<DataKey<Boolean>>) Parser.FENCED_CODE_BLOCK_PARSER, (DataKey<Boolean>) Boolean.FALSE);
            } else {
                mutableDataSet.set((DataKey<DataKey<Boolean>>) Parser.MATCH_CLOSING_FENCE_CHARACTERS, (DataKey<Boolean>) Boolean.FALSE);
            }
            if (haveAnyExtensions(268435456)) {
                mutableDataSet.set((DataKey<DataKey<Boolean>>) Parser.LISTS_LOOSE_WHEN_HAS_NON_LIST_CHILDREN, (DataKey<Boolean>) Boolean.TRUE);
            }
            if (haveAnyExtensions(8)) {
                mutableDataSet.set((DataKey<DataKey<String>>) HtmlRenderer.SOFT_BREAK, (DataKey<String>) "<br />\n");
                mutableDataSet.set((DataKey<DataKey<String>>) HtmlRenderer.HARD_BREAK, (DataKey<String>) "<br />\n");
            }
            if (!haveAnyExtensions(262144)) {
                mutableDataSet.set((DataKey<DataKey<Boolean>>) Parser.HEADING_NO_ATX_SPACE, (DataKey<Boolean>) Boolean.TRUE);
            }
            if (haveAnyExtensions(3)) {
                arrayList.add(TypographicExtension.create());
                mutableDataSet.set((DataKey<DataKey<Boolean>>) TypographicExtension.ENABLE_SMARTS, (DataKey<Boolean>) Boolean.valueOf(haveAnyExtensions(1)));
                mutableDataSet.set((DataKey<DataKey<Boolean>>) TypographicExtension.ENABLE_QUOTES, (DataKey<Boolean>) Boolean.valueOf(haveAnyExtensions(2)));
            }
            if (!haveAnyExtensions(1048576)) {
                mutableDataSet.set((DataKey<DataKey<Boolean>>) Parser.THEMATIC_BREAK_RELAXED_START, (DataKey<Boolean>) Boolean.FALSE);
            }
            if (haveAnyExtensions(32)) {
                arrayList.add(TablesExtension.create());
                mutableDataSet.set((DataKey<DataKey<Boolean>>) TablesExtension.TRIM_CELL_WHITESPACE, (DataKey<Boolean>) Boolean.FALSE);
                mutableDataSet.set((DataKey<DataKey<Boolean>>) TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH, (DataKey<Boolean>) Boolean.FALSE);
            }
            if (haveAnyExtensions(2097152)) {
                arrayList.add(TaskListExtension.create());
            }
            if (haveAnyExtensions(256)) {
                arrayList.add(WikiLinkExtension.create());
                mutableDataSet.set((DataKey<DataKey<Boolean>>) WikiLinkExtension.LINK_FIRST_SYNTAX, (DataKey<Boolean>) Boolean.FALSE);
                mutableDataSet.set((DataKey<DataKey<Boolean>>) WikiLinkExtension.ALLOW_ANCHORS, (DataKey<Boolean>) Boolean.TRUE);
            }
            if (haveAnyExtensions(524288) && haveAnyExtensions(512)) {
                arrayList.add(StrikethroughSubscriptExtension.create());
            } else if (haveAnyExtensions(512)) {
                arrayList.add(StrikethroughExtension.create());
            } else if (haveAnyExtensions(524288)) {
                arrayList.add(SubscriptExtension.create());
            }
            if (haveAnyExtensions(134217728)) {
                arrayList.add(SuperscriptExtension.create());
            }
            if (haveAnyExtensions(1073741824)) {
                arrayList.add(InsExtension.create());
            }
            if (haveAnyExtensions(33554432)) {
                arrayList.add(SimTocExtension.create());
                mutableDataSet.set((DataKey<DataKey<Boolean>>) TocExtension.BLANK_LINE_SPACER, (DataKey<Boolean>) Boolean.TRUE);
                arrayList.add(TocExtension.create());
                mutableDataSet.set((DataKey<DataKey<Integer>>) TocExtension.LEVELS, (DataKey<Integer>) Integer.valueOf(TocOptions.getLevels(2, 3)));
            }
            if (haveAnyExtensions(67108864)) {
                mutableDataSet.set((DataKey<DataKey<Boolean>>) Parser.PARSE_MULTI_LINE_IMAGE_URLS, (DataKey<Boolean>) Boolean.TRUE);
            }
            if (haveAnyExtensions(16777216)) {
                arrayList.add(FootnoteExtension.create());
                mutableDataSet.set((DataKey<DataKey<KeepType>>) FootnoteExtension.FOOTNOTES_KEEP, (DataKey<KeepType>) KeepType.LAST);
            }
            this.myOptions.set((DataKey<DataKey<Collection<Extension>>>) Parser.EXTENSIONS, (DataKey<Collection<Extension>>) arrayList);
        }
        return this.myOptions.toImmutable();
    }

    public PegdownOptionsAdapter setPegdownExtensions(int i) {
        this.myPegdownExtensions = i;
        this.myIsUpdateNeeded = true;
        return this;
    }

    public PegdownOptionsAdapter addPegdownExtensions(int i) {
        this.myPegdownExtensions |= i;
        this.myIsUpdateNeeded = true;
        return this;
    }

    public PegdownOptionsAdapter removePegdownExtensions(int i) {
        this.myPegdownExtensions &= i ^ (-1);
        this.myIsUpdateNeeded = true;
        return this;
    }
}
