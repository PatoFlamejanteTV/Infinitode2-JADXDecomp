package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ext.toc.internal.SimTocOptionsParser;
import com.vladsch.flexmark.ext.toc.internal.TocOptions;
import com.vladsch.flexmark.ext.toc.internal.TocOptionsParser;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.TextCollectingVisitor;
import com.vladsch.flexmark.util.collection.iteration.ReversiblePeekingIterator;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.misc.DelimitedBuilder;
import com.vladsch.flexmark.util.misc.Extension;
import com.vladsch.flexmark.util.misc.Pair;
import com.vladsch.flexmark.util.misc.Paired;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.RepeatedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/TocUtils.class */
public class TocUtils {
    public static final AttributablePart TOC_CONTENT;
    public static final AttributablePart TOC_LIST;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !TocUtils.class.desiredAssertionStatus();
        TOC_CONTENT = new AttributablePart("TOC_CONTENT");
        TOC_LIST = new AttributablePart("TOC_LIST");
    }

    public static String getTocPrefix(TocOptions tocOptions, TocOptions tocOptions2) {
        DelimitedBuilder delimitedBuilder = new DelimitedBuilder(SequenceUtils.SPACE);
        delimitedBuilder.append("[TOC").mark();
        delimitedBuilder.append(new TocOptionsParser().getOptionText(tocOptions, tocOptions2));
        delimitedBuilder.unmark().append("]");
        delimitedBuilder.append(SequenceUtils.EOL).unmark();
        return delimitedBuilder.toString();
    }

    public static String getSimTocPrefix(TocOptions tocOptions, TocOptions tocOptions2) {
        DelimitedBuilder delimitedBuilder = new DelimitedBuilder(SequenceUtils.SPACE);
        delimitedBuilder.append("[TOC").mark();
        delimitedBuilder.append(new SimTocOptionsParser().getOptionText(tocOptions, tocOptions2));
        delimitedBuilder.unmark().append("]:").mark().append('#').mark();
        String titleHeading = tocOptions.getTitleHeading();
        String str = tocOptions.title;
        if (tocOptions2 == null || !titleHeading.equals(tocOptions2.getTitleHeading())) {
            if (!str.isEmpty()) {
                delimitedBuilder.append('\"');
                if (tocOptions2 == null || tocOptions.titleLevel != tocOptions2.titleLevel) {
                    delimitedBuilder.append(titleHeading.trim().replace("\\", "\\\\").replace("\"", "\\\""));
                } else {
                    delimitedBuilder.append(str.trim().replace("\\", "\\\\").replace("\"", "\\\""));
                }
                delimitedBuilder.append('\"').mark();
            } else {
                delimitedBuilder.append("\"\"").mark();
            }
        }
        delimitedBuilder.unmark().append(SequenceUtils.EOL).unmark();
        return delimitedBuilder.toString();
    }

    public static void renderTocContent(MarkdownWriter markdownWriter, TocOptions tocOptions, TocOptions tocOptions2, List<Heading> list, List<String> list2) {
        if (list.isEmpty()) {
            return;
        }
        Document document = list.get(0).getDocument();
        if (tocOptions.isHtml) {
            MarkdownWriter markdownWriter2 = new MarkdownWriter(markdownWriter.getOptions());
            Iterator<Heading> it = list.iterator();
            while (it.hasNext()) {
                markdownWriter2.append((CharSequence) it.next().getChars()).line();
            }
            markdownWriter2.append((CharSequence) getTocPrefix(tocOptions, tocOptions2));
            MutableDataSet mutableDataSet = new MutableDataSet(document);
            tocOptions2.setIn(mutableDataSet);
            tocOptions.setIn(mutableDataSet);
            if (!mutableDataSet.contains(HtmlRenderer.INDENT_SIZE)) {
                mutableDataSet.set((DataKey<DataKey<Integer>>) HtmlRenderer.INDENT_SIZE, (DataKey<Integer>) 2);
            }
            mutableDataSet.set((DataKey<DataKey<Boolean>>) HtmlRenderer.RENDER_HEADER_ID, (DataKey<Boolean>) Boolean.FALSE);
            mutableDataSet.set((DataKey<DataKey<Boolean>>) HtmlRenderer.GENERATE_HEADER_ID, (DataKey<Boolean>) Boolean.FALSE);
            ArrayList arrayList = new ArrayList(Parser.EXTENSIONS.get(mutableDataSet));
            arrayList.removeIf(extension -> {
                return extension instanceof SimTocExtension;
            });
            arrayList.add(TocExtension.create());
            mutableDataSet.set((DataKey<DataKey<Collection<Extension>>>) Parser.EXTENSIONS, (DataKey<Collection<Extension>>) arrayList);
            Parser build = Parser.builder(mutableDataSet).build();
            HtmlRenderer build2 = HtmlRenderer.builder(mutableDataSet).build();
            Document parse = build.parse(markdownWriter2.toString());
            int i = 0;
            ReversiblePeekingIterator<Node> it2 = parse.getChildren().iterator();
            while (it2.hasNext()) {
                Node next = it2.next();
                if (next instanceof Heading) {
                    ((Heading) next).setAnchorRefId(list.get(i).getAnchorRefId());
                    i++;
                }
            }
            Node firstChildAny = parse.getFirstChildAny(TocBlock.class);
            if (!$assertionsDisabled && firstChildAny == null) {
                throw new AssertionError();
            }
            markdownWriter.openPreFormatted(false);
            build2.render(firstChildAny, markdownWriter);
            markdownWriter.closePreFormatted();
            return;
        }
        String titleHeading = tocOptions.getTitleHeading();
        if (!titleHeading.isEmpty()) {
            markdownWriter.append((CharSequence) titleHeading);
        }
        if (Parser.PARSER_EMULATION_PROFILE.get(document).family == ParserEmulationProfile.FIXED_INDENT) {
            markdownWriter.setIndentPrefix(RepeatedSequence.ofSpaces(4));
        } else {
            markdownWriter.setIndentPrefix(RepeatedSequence.ofSpaces(tocOptions.isNumbered ? 3 : 2));
        }
        renderMarkdownToc(markdownWriter, (List) list.stream().map((v0) -> {
            return v0.getLevel();
        }).collect(Collectors.toList()), list2, tocOptions);
    }

    public static void renderHtmlToc(HtmlWriter htmlWriter, BasedSequence basedSequence, List<Integer> list, List<String> list2, List<String> list3, TocOptions tocOptions) {
        if (list.size() > 0 && (basedSequence.isNotNull() || !tocOptions.title.trim().isEmpty())) {
            if (basedSequence.isNotNull()) {
                htmlWriter.srcPos(basedSequence);
            }
            htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) tocOptions.divClass).withAttr(TOC_CONTENT).tag((CharSequence) FlexmarkHtmlConverter.DIV_NODE).line().indent();
            if (!tocOptions.title.trim().isEmpty()) {
                htmlWriter.tag((CharSequence) ("h" + tocOptions.titleLevel)).text((CharSequence) tocOptions.title).tag((CharSequence) ("/h" + tocOptions.titleLevel)).line();
            }
        }
        int i = -1;
        int i2 = -1;
        String str = tocOptions.isNumbered ? FlexmarkHtmlConverter.OL_NODE : FlexmarkHtmlConverter.UL_NODE;
        String str2 = "/" + str;
        boolean[] zArr = new boolean[7];
        boolean[] zArr2 = new boolean[7];
        int[] iArr = new int[7];
        for (int i3 = 0; i3 < list.size(); i3++) {
            String str3 = list2.get(i3);
            int intValue = tocOptions.listType != TocOptions.ListType.HIERARCHY ? 1 : list.get(i3).intValue();
            if (i == -1) {
                i = intValue;
                i2 = intValue;
                htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) tocOptions.listClass).withAttr(TOC_LIST).line().tag((CharSequence) str).indent().line();
                zArr2[0] = true;
            }
            if (i2 < intValue) {
                for (int i4 = i2; i4 < intValue; i4++) {
                    zArr[i4 + 1] = false;
                    zArr2[i4 + 1] = false;
                }
                if (!zArr2[i2]) {
                    htmlWriter.withAttr().indent().line().tag((CharSequence) str).indent();
                    zArr2[i2] = true;
                }
            } else if (i2 == intValue) {
                if (zArr[i2]) {
                    if (zArr2[i2]) {
                        htmlWriter.unIndent().tag((CharSequence) str2).line();
                    }
                    htmlWriter.lineIf(iArr[i2] != htmlWriter.offsetWithPending()).tag((CharSequence) "/li").line();
                }
                zArr[i2] = false;
                zArr2[i2] = false;
            } else {
                for (int i5 = i2; i5 >= intValue; i5--) {
                    if (zArr[i5]) {
                        if (zArr2[i5]) {
                            htmlWriter.unIndent().tag((CharSequence) str2).unIndent().line();
                        }
                        htmlWriter.lineIf(iArr[i2] != htmlWriter.offsetWithPending()).tag((CharSequence) "/li").line();
                    }
                    zArr[i5] = false;
                    zArr2[i5] = false;
                }
            }
            htmlWriter.line().tag((CharSequence) FlexmarkHtmlConverter.LI_NODE);
            zArr[intValue] = true;
            String str4 = list3.get(i3);
            if (str4 == null || str4.isEmpty()) {
                htmlWriter.raw((CharSequence) str3);
            } else {
                htmlWriter.attr("href", (CharSequence) ("#" + str4)).withAttr().tag((CharSequence) FlexmarkHtmlConverter.A_NODE);
                htmlWriter.raw((CharSequence) str3);
                htmlWriter.tag("/a");
            }
            i2 = intValue;
            iArr[intValue] = htmlWriter.offsetWithPending();
        }
        for (int i6 = i2; i6 > 0; i6--) {
            if (zArr[i6]) {
                if (zArr2[i6]) {
                    htmlWriter.unIndent().tag((CharSequence) str2).unIndent().line();
                }
                htmlWriter.lineIf(iArr[i2] != htmlWriter.offsetWithPending()).tag((CharSequence) "/li").line();
            }
        }
        if (zArr2[0]) {
            htmlWriter.unIndent().tag((CharSequence) str2).line();
        }
        if (list.size() > 0 && (basedSequence.isNotNull() || !tocOptions.title.trim().isEmpty())) {
            htmlWriter.line().unIndent().tag((CharSequence) "/div");
        }
        htmlWriter.line();
    }

    public static List<Heading> filteredHeadings(List<Heading> list, TocOptions tocOptions) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Heading heading : list) {
            if (tocOptions.isLevelIncluded(heading.getLevel()) && !(heading.getParent() instanceof SimTocContent)) {
                arrayList.add(heading);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.List, java.util.List<com.vladsch.flexmark.ast.Heading>] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v2 */
    public static Paired<List<Heading>, List<String>> htmlHeadingTexts(NodeRendererContext nodeRendererContext, List<Heading> list, TocOptions tocOptions) {
        String headingContent;
        ArrayList arrayList = new ArrayList(list.size());
        boolean z = tocOptions.listType == TocOptions.ListType.SORTED_REVERSED || tocOptions.listType == TocOptions.ListType.FLAT_REVERSED;
        boolean z2 = tocOptions.listType == TocOptions.ListType.SORTED || tocOptions.listType == TocOptions.ListType.SORTED_REVERSED;
        boolean z3 = z || z2;
        boolean z4 = z3;
        HashMap hashMap = !z3 ? null : new HashMap(list.size());
        HashMap hashMap2 = (!z4 || tocOptions.isTextOnly) ? null : new HashMap(list.size());
        for (Heading heading : list) {
            if (tocOptions.isTextOnly) {
                headingContent = getHeadingText(heading);
            } else {
                headingContent = getHeadingContent(nodeRendererContext, heading);
                if (z4) {
                    hashMap2.put(headingContent, getHeadingText(heading));
                }
            }
            if (z4) {
                hashMap.put(headingContent, heading);
            }
            arrayList.add(headingContent);
        }
        if (z2 || z) {
            if (tocOptions.isTextOnly) {
                if (z2) {
                    arrayList.sort((str, str2) -> {
                        return z ? str2.compareTo(str) : str.compareTo(str2);
                    });
                } else {
                    Collections.reverse(arrayList);
                }
            } else if (z2) {
                arrayList.sort((str3, str4) -> {
                    String str3 = (String) hashMap2.get(str3);
                    String str4 = (String) hashMap2.get(str4);
                    return z ? str4.compareTo(str3) : str3.compareTo(str4);
                });
            } else {
                Collections.reverse(arrayList);
            }
            list = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                list.add(hashMap.get((String) it.next()));
            }
        }
        return Pair.of(list, arrayList);
    }

    private static String getHeadingText(Heading heading) {
        return Escaping.escapeHtml(new TextCollectingVisitor().collectAndGetText(heading), false);
    }

    private static String getHeadingContent(NodeRendererContext nodeRendererContext, Heading heading) {
        NodeRendererContext subContext = nodeRendererContext.getSubContext(false);
        subContext.doNotRenderLinks();
        subContext.renderChildren(heading);
        return subContext.getHtmlWriter().toString(-1, -1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.List, java.util.List<com.vladsch.flexmark.ast.Heading>] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v2 */
    public static Pair<List<Heading>, List<String>> markdownHeaderTexts(List<Heading> list, TocOptions tocOptions) {
        String obj;
        String str;
        ArrayList arrayList = new ArrayList(list.size());
        boolean z = tocOptions.listType == TocOptions.ListType.SORTED_REVERSED || tocOptions.listType == TocOptions.ListType.FLAT_REVERSED;
        boolean z2 = tocOptions.listType == TocOptions.ListType.SORTED || tocOptions.listType == TocOptions.ListType.SORTED_REVERSED;
        boolean z3 = z || z2;
        boolean z4 = z3;
        HashMap hashMap = !z3 ? null : new HashMap(list.size());
        HashMap hashMap2 = (!z4 || tocOptions.isTextOnly) ? null : new HashMap(list.size());
        for (Heading heading : list) {
            String collectAndGetText = (tocOptions.isTextOnly || z4) ? new TextCollectingVisitor().collectAndGetText(heading) : "";
            if (tocOptions.isTextOnly) {
                obj = collectAndGetText;
            } else {
                obj = heading.getText().toString();
            }
            String anchorRefId = heading.getAnchorRefId();
            if (anchorRefId != null && !obj.isEmpty()) {
                str = "[" + obj + "](#" + anchorRefId + ")";
            } else {
                str = obj;
            }
            if (z4) {
                if (!tocOptions.isTextOnly) {
                    hashMap2.put(str, collectAndGetText);
                }
                hashMap.put(str, heading);
            }
            arrayList.add(str);
        }
        if (z2 || z) {
            if (tocOptions.isTextOnly) {
                if (z2) {
                    arrayList.sort((str2, str3) -> {
                        return z ? str3.compareTo(str2) : str2.compareTo(str3);
                    });
                } else {
                    Collections.reverse(arrayList);
                }
            } else if (z2) {
                arrayList.sort((str4, str5) -> {
                    String str4 = (String) hashMap2.get(str4);
                    String str5 = (String) hashMap2.get(str5);
                    return z ? str5.compareTo(str4) : str4.compareTo(str5);
                });
            } else {
                Collections.reverse(arrayList);
            }
            list = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                list.add(hashMap.get((String) it.next()));
            }
        }
        return Pair.of(list, arrayList);
    }

    public static void renderMarkdownToc(MarkdownWriter markdownWriter, List<Integer> list, List<String> list2, TocOptions tocOptions) {
        int i = -1;
        int i2 = -1;
        boolean[] zArr = new boolean[7];
        boolean[] zArr2 = new boolean[7];
        int[] iArr = new int[7];
        for (int i3 = 0; i3 < list.size(); i3++) {
            String str = list2.get(i3);
            int intValue = tocOptions.listType != TocOptions.ListType.HIERARCHY ? 1 : list.get(i3).intValue();
            if (i == -1) {
                i = intValue;
                i2 = intValue;
                markdownWriter.line();
                zArr2[0] = true;
            }
            if (i2 < intValue) {
                for (int i4 = i2; i4 < intValue; i4++) {
                    zArr[i4 + 1] = false;
                    zArr2[i4 + 1] = false;
                }
                if (!zArr2[i2]) {
                    markdownWriter.indent();
                    zArr2[i2] = true;
                }
            } else if (i2 == intValue) {
                if (zArr[i2]) {
                    if (zArr2[i2]) {
                        markdownWriter.unIndent();
                    }
                    markdownWriter.lineIf(iArr[i2] != markdownWriter.offsetWithPending()).line();
                }
                zArr[i2] = false;
                zArr2[i2] = false;
            } else {
                for (int i5 = i2; i5 >= intValue; i5--) {
                    if (zArr[i5]) {
                        if (zArr2[i5]) {
                            markdownWriter.unIndent();
                        }
                        markdownWriter.lineIf(iArr[i2] != markdownWriter.offsetWithPending()).line();
                    }
                    zArr[i5] = false;
                    zArr2[i5] = false;
                }
            }
            markdownWriter.line().append((CharSequence) (tocOptions.isNumbered ? "1. " : "- "));
            zArr[intValue] = true;
            markdownWriter.append((CharSequence) str);
            i2 = intValue;
            iArr[intValue] = markdownWriter.offsetWithPending();
        }
        for (int i6 = i2; i6 > 0; i6--) {
            if (zArr[i6]) {
                if (zArr2[i6]) {
                    markdownWriter.unIndent();
                }
                markdownWriter.lineIf(iArr[i2] != markdownWriter.offsetWithPending()).line();
            }
        }
        markdownWriter.line();
    }
}
