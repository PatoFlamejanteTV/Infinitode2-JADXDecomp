package com.vladsch.flexmark.ext.tables.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ast.WhiteSpace;
import com.vladsch.flexmark.ext.tables.TableBlock;
import com.vladsch.flexmark.ext.tables.TableBody;
import com.vladsch.flexmark.ext.tables.TableCaption;
import com.vladsch.flexmark.ext.tables.TableCell;
import com.vladsch.flexmark.ext.tables.TableHead;
import com.vladsch.flexmark.ext.tables.TableRow;
import com.vladsch.flexmark.ext.tables.TableSeparator;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.block.CharacterNodeFactory;
import com.vladsch.flexmark.parser.block.ParagraphPreProcessor;
import com.vladsch.flexmark.parser.block.ParagraphPreProcessorFactory;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.core.ReferencePreProcessorFactory;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.TableFormatOptions;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/internal/TableParagraphPreProcessor.class */
public class TableParagraphPreProcessor implements ParagraphPreProcessor {
    private static BitSet pipeCharacters = new BitSet();
    private static BitSet separatorCharacters = new BitSet();
    private static HashMap<Character, CharacterNodeFactory> pipeNodeMap;
    private static HashMap<Character, CharacterNodeFactory> pipeIntelliJNodeMap;
    private final TableParserOptions options;
    Pattern TABLE_HEADER_SEPARATOR;

    static {
        pipeCharacters.set(124);
        separatorCharacters.set(124);
        separatorCharacters.set(58);
        separatorCharacters.set(45);
        HashMap<Character, CharacterNodeFactory> hashMap = new HashMap<>();
        pipeNodeMap = hashMap;
        hashMap.put('|', new CharacterNodeFactory() { // from class: com.vladsch.flexmark.ext.tables.internal.TableParagraphPreProcessor.1
            @Override // com.vladsch.flexmark.parser.block.CharacterNodeFactory
            public final boolean skipNext(char c) {
                return c == ' ' || c == '\t';
            }

            @Override // com.vladsch.flexmark.parser.block.CharacterNodeFactory
            public final boolean skipPrev(char c) {
                return c == ' ' || c == '\t';
            }

            @Override // com.vladsch.flexmark.parser.block.CharacterNodeFactory
            public final boolean wantSkippedWhitespace() {
                return true;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.function.Supplier
            public final Node get() {
                return new TableColumnSeparator();
            }
        });
        HashMap<Character, CharacterNodeFactory> hashMap2 = new HashMap<>();
        pipeIntelliJNodeMap = hashMap2;
        hashMap2.put('|', new CharacterNodeFactory() { // from class: com.vladsch.flexmark.ext.tables.internal.TableParagraphPreProcessor.2
            @Override // com.vladsch.flexmark.parser.block.CharacterNodeFactory
            public final boolean skipNext(char c) {
                return c == ' ' || c == '\t';
            }

            @Override // com.vladsch.flexmark.parser.block.CharacterNodeFactory
            public final boolean skipPrev(char c) {
                return c == ' ' || c == '\t';
            }

            @Override // com.vladsch.flexmark.parser.block.CharacterNodeFactory
            public final boolean wantSkippedWhitespace() {
                return true;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.function.Supplier
            public final Node get() {
                return new TableColumnSeparator();
            }
        });
    }

    public static ParagraphPreProcessorFactory Factory() {
        return new ParagraphPreProcessorFactory() { // from class: com.vladsch.flexmark.ext.tables.internal.TableParagraphPreProcessor.3
            @Override // com.vladsch.flexmark.util.dependency.Dependent
            public final boolean affectsGlobalScope() {
                return false;
            }

            @Override // com.vladsch.flexmark.util.dependency.Dependent
            public final Set<Class<?>> getAfterDependents() {
                HashSet hashSet = new HashSet();
                hashSet.add(ReferencePreProcessorFactory.class);
                return hashSet;
            }

            @Override // com.vladsch.flexmark.util.dependency.Dependent
            public final Set<Class<?>> getBeforeDependents() {
                return null;
            }

            @Override // com.vladsch.flexmark.parser.block.ParagraphPreProcessorFactory, java.util.function.Function
            public final ParagraphPreProcessor apply(ParserState parserState) {
                return new TableParagraphPreProcessor(parserState.getProperties());
            }
        };
    }

    public static Pattern getTableHeaderSeparator(int i, String str) {
        int i2 = i > 0 ? i : 1;
        int i3 = i >= 2 ? i - 1 : 1;
        String format = String.format(Locale.US, "(?:\\s*-{%d,}\\s*|\\s*:-{%d,}\\s*|\\s*-{%d,}:\\s*|\\s*:-{%d,}:\\s*)", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i3), Integer.valueOf(i >= 3 ? i - 2 : 1));
        boolean isEmpty = str.isEmpty();
        String str2 = isEmpty ? "" : TableFormatOptions.INTELLIJ_DUMMY_IDENTIFIER;
        return Pattern.compile(("\\|" + format + "\\|?\\s*|" + format + "\\|\\s*|\\|?(?:" + format + "\\|)+" + format + "\\|?\\s*").replace("\\s", isEmpty ? "\\s" : "(?:\\s" + str2 + "?)").replace("\\|", isEmpty ? "\\|" : "(?:" + str2 + "?\\|" + str2 + "?)").replace("-", isEmpty ? "-" : "(?:-" + str2 + "?)"));
    }

    private TableParagraphPreProcessor(DataHolder dataHolder) {
        this.options = new TableParserOptions(dataHolder);
        this.TABLE_HEADER_SEPARATOR = getTableHeaderSeparator(this.options.minSeparatorDashes, "");
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/internal/TableParagraphPreProcessor$TableSeparatorRow.class */
    private static class TableSeparatorRow extends TableRow implements DoNotDecorate {
        public TableSeparatorRow() {
        }

        public TableSeparatorRow(BasedSequence basedSequence) {
            super(basedSequence);
        }
    }

    @Override // com.vladsch.flexmark.parser.block.ParagraphPreProcessor
    public int preProcessBlock(Paragraph paragraph, ParserState parserState) {
        List<Node> parseCustom;
        int i;
        InlineParser inlineParser = parserState.getInlineParser();
        ArrayList arrayList = new ArrayList();
        int i2 = -1;
        BasedSequence basedSequence = null;
        int lineIndent = paragraph.getLineIndent(0);
        BasedSequence basedSequence2 = null;
        BitSet bitSet = separatorCharacters;
        Map<Character, CharacterNodeFactory> map = pipeNodeMap;
        Iterator<BasedSequence> it = paragraph.getContentLines().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BasedSequence next = it.next();
            int size = arrayList.size();
            if (i2 == -1 && size > this.options.maxHeaderRows) {
                return 0;
            }
            if (next.indexOf('|') < 0) {
                if (i2 == -1) {
                    return 0;
                }
                if (this.options.withCaption) {
                    BasedSequence trim = next.trim();
                    if (trim.startsWith("[") && trim.endsWith("]")) {
                        basedSequence2 = trim;
                    }
                }
            } else {
                BasedSequence subSequence = next.subSequence(paragraph.getLineIndent(size));
                if (i2 == -1 && size >= this.options.minHeaderRows && this.TABLE_HEADER_SEPARATOR.matcher(subSequence).matches()) {
                    if ((next.charAt(0) != ' ' && next.charAt(0) != '\t') || next.charAt(0) != '|') {
                        i2 = size;
                        basedSequence = subSequence;
                    } else if (next.charAt(0) == ' ' || next.charAt(0) == '\t') {
                        paragraph.setHasTableSeparator(true);
                    }
                }
                arrayList.add(subSequence);
            }
        }
        if (i2 == -1) {
            return 0;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            BasedSequence basedSequence3 = (BasedSequence) it2.next();
            int size2 = arrayList2.size();
            BasedSequence trimEOL = paragraph.getLineIndent(size2) <= lineIndent ? basedSequence3.trimEOL() : basedSequence3.baseSubSequence(basedSequence3.getStartOffset() - (paragraph.getLineIndent(size2) - lineIndent), basedSequence3.getEndOffset() - basedSequence3.eolEndLength());
            boolean z = size2 == i2;
            TableRow tableRow = new TableRow(trimEOL);
            if (z) {
                Node tableSeparatorRow = new TableSeparatorRow(trimEOL);
                parseCustom = inlineParser.parseCustom(trimEOL, tableSeparatorRow, bitSet, map);
                tableRow.takeChildren(tableSeparatorRow);
                i = 0;
            } else {
                parseCustom = inlineParser.parseCustom(trimEOL, tableRow, pipeCharacters, pipeNodeMap);
                i = size2 < i2 ? size2 + 1 : size2 - i2;
                if (parseCustom != null) {
                    parseCustom = cleanUpInlinedSeparators(inlineParser, tableRow, parseCustom);
                }
            }
            if (parseCustom == null) {
                if (size2 <= i2) {
                    return 0;
                }
            } else {
                tableRow.setRowNumber(i);
                arrayList2.add(tableRow);
            }
        }
        Block tableBlock = new TableBlock((List<BasedSequence>) arrayList.subList(0, arrayList2.size()));
        Node tableHead = new TableHead(((BasedSequence) arrayList.get(0)).subSequence(0, 0));
        tableBlock.appendChild(tableHead);
        List<TableCell.Alignment> parseAlignment = parseAlignment(basedSequence);
        int i3 = 0;
        int size3 = parseAlignment.size();
        Iterator it3 = arrayList2.iterator();
        while (it3.hasNext()) {
            TableRow tableRow2 = (TableRow) it3.next();
            if (i3 == i2) {
                tableHead.setCharsFromContent();
                tableHead = new TableSeparator();
                tableBlock.appendChild(tableHead);
            } else if (i3 == i2 + 1) {
                tableHead.setCharsFromContent();
                tableHead = new TableBody();
                tableBlock.appendChild(tableHead);
            }
            boolean z2 = true;
            int i4 = 0;
            NodeIterator nodeIterator = new NodeIterator(tableRow2.getFirstChild());
            TableRow tableRow3 = new TableRow(tableRow2.getChars());
            tableRow3.setRowNumber(tableRow2.getRowNumber());
            int i5 = 0;
            while (true) {
                if (!nodeIterator.hasNext()) {
                    break;
                }
                if (i4 >= size3 && this.options.discardExtraColumns) {
                    if (this.options.headerSeparatorColumnMatch && i3 < i2) {
                        return 0;
                    }
                } else {
                    TableCell tableCell = new TableCell();
                    if (z2 && (nodeIterator.peek() instanceof TableColumnSeparator)) {
                        Node next2 = nodeIterator.next();
                        tableCell.setOpeningMarker(next2.getChars());
                        next2.unlink();
                        z2 = false;
                    }
                    TableCell.Alignment alignment = i4 + i5 < size3 ? parseAlignment.get(i4 + i5) : null;
                    tableCell.setHeader(i3 < i2);
                    tableCell.setAlignment(alignment);
                    while (nodeIterator.hasNext() && !(nodeIterator.peek() instanceof TableColumnSeparator)) {
                        tableCell.appendChild(nodeIterator.next());
                    }
                    BasedSequence basedSequence4 = null;
                    int i6 = 1;
                    while (nodeIterator.hasNext() && (nodeIterator.peek() instanceof TableColumnSeparator)) {
                        if (basedSequence4 == null) {
                            basedSequence4 = nodeIterator.next().getChars();
                            if (!this.options.columnSpans) {
                                break;
                            }
                        } else {
                            BasedSequence chars = nodeIterator.peek().getChars();
                            if (!basedSequence4.isContinuedBy(chars)) {
                                break;
                            }
                            basedSequence4 = basedSequence4.spliceAtEnd(chars);
                            nodeIterator.next().unlink();
                            i6++;
                        }
                    }
                    i5 += i6 - 1;
                    if (basedSequence4 != null) {
                        tableCell.setClosingMarker(basedSequence4);
                    }
                    tableCell.setChars(tableCell.getChildChars());
                    if (this.options.trimCellWhitespace) {
                        tableCell.trimWhiteSpace();
                    } else {
                        tableCell.mergeWhiteSpace();
                    }
                    tableCell.setText(tableCell.getChildChars());
                    tableCell.setCharsFromContent();
                    tableCell.setSpan(i6);
                    tableRow3.appendChild(tableCell);
                    i4++;
                }
            }
            if (this.options.headerSeparatorColumnMatch && i3 < i2 && i4 < size3) {
                return 0;
            }
            while (this.options.appendMissingColumns && i4 < size3) {
                TableCell tableCell2 = new TableCell();
                tableCell2.setHeader(i3 < i2);
                tableCell2.setAlignment(parseAlignment.get(i4));
                tableRow3.appendChild(tableCell2);
                i4++;
            }
            tableRow3.setCharsFromContent();
            tableHead.appendChild(tableRow3);
            i3++;
        }
        tableHead.setCharsFromContent();
        if (tableHead instanceof TableSeparator) {
            tableBlock.appendChild(new TableBody(tableHead.getChars().subSequence(tableHead.getChars().length())));
        }
        if (basedSequence2 != null) {
            BasedSequence basedSequence5 = basedSequence2;
            TableCaption tableCaption = new TableCaption(basedSequence2.subSequence(0, 1), basedSequence2.subSequence(1, basedSequence2.length() - 1), basedSequence5.subSequence(basedSequence5.length() - 1));
            inlineParser.parse(tableCaption.getText(), tableCaption);
            tableCaption.setCharsFromContent();
            tableBlock.appendChild(tableCaption);
        }
        tableBlock.setCharsFromContent();
        paragraph.insertBefore(tableBlock);
        parserState.blockAdded(tableBlock);
        return tableBlock.getChars().length();
    }

    List<Node> cleanUpInlinedSeparators(InlineParser inlineParser, TableRow tableRow, List<Node> list) {
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        for (Node node : list) {
            if (node.getParent() != null && node.getParent() != tableRow) {
                Node previous = node.getPrevious() instanceof WhiteSpace ? node.getPrevious() : node;
                Node next = node.getNext() instanceof WhiteSpace ? node.getNext() : node;
                Text text = new Text(node.baseSubSequence(previous.getStartOffset(), next.getEndOffset()));
                node.insertBefore(text);
                node.unlink();
                previous.unlink();
                next.unlink();
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    arrayList2 = new ArrayList();
                }
                arrayList.add(node);
                arrayList2.add(text.getParent());
            }
        }
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                Node node2 = (Node) it.next();
                inlineParser.mergeTextNodes(node2.getFirstChild(), node2.getLastChild());
            }
            if (arrayList.size() == list.size()) {
                return null;
            }
            ArrayList arrayList3 = new ArrayList(list);
            arrayList3.removeAll(arrayList);
            return arrayList3;
        }
        return list;
    }

    private List<TableCell.Alignment> parseAlignment(BasedSequence basedSequence) {
        List<BasedSequence> split = split(basedSequence, false, false);
        ArrayList arrayList = new ArrayList();
        Iterator<BasedSequence> it = split.iterator();
        while (it.hasNext()) {
            BasedSequence trim = it.next().trim();
            arrayList.add(getAlignment(trim.startsWith(":"), trim.endsWith(":")));
        }
        return arrayList;
    }

    private static List<BasedSequence> split(BasedSequence basedSequence, boolean z, boolean z2) {
        BasedSequence trim = basedSequence.trim();
        BasedSequence basedSequence2 = trim;
        int length = trim.length();
        ArrayList arrayList = new ArrayList();
        if (basedSequence2.startsWith("|")) {
            if (z2) {
                arrayList.add(basedSequence2.subSequence(0, 1));
            }
            basedSequence2 = basedSequence2.subSequence(1, length);
            length--;
        }
        boolean z3 = false;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = basedSequence2.charAt(i3);
            if (z3) {
                z3 = false;
            } else {
                switch (charAt) {
                    case '\\':
                        z3 = true;
                        break;
                    case '|':
                        if (!z || i < i3) {
                            arrayList.add(basedSequence2.subSequence(i, i3));
                        }
                        if (z2) {
                            int i4 = i3;
                            arrayList.add(basedSequence2.subSequence(i4, i4 + 1));
                        }
                        i = i3 + 1;
                        i2 = 0;
                        continue;
                }
            }
            i2++;
        }
        if (i2 > 0) {
            arrayList.add(basedSequence2.subSequence(i, length));
        }
        return arrayList;
    }

    private static TableCell.Alignment getAlignment(boolean z, boolean z2) {
        if (z && z2) {
            return TableCell.Alignment.CENTER;
        }
        if (z) {
            return TableCell.Alignment.LEFT;
        }
        if (z2) {
            return TableCell.Alignment.RIGHT;
        }
        return null;
    }
}
