package com.vladsch.flexmark.parser.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.util.ClassifyingBlockTracker;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.InlineParserFactory;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.block.BlockContinue;
import com.vladsch.flexmark.parser.block.BlockParser;
import com.vladsch.flexmark.parser.block.BlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockPreProcessor;
import com.vladsch.flexmark.parser.block.BlockPreProcessorFactory;
import com.vladsch.flexmark.parser.block.BlockStart;
import com.vladsch.flexmark.parser.block.CustomBlockParserFactory;
import com.vladsch.flexmark.parser.block.ParagraphPreProcessor;
import com.vladsch.flexmark.parser.block.ParagraphPreProcessorFactory;
import com.vladsch.flexmark.parser.block.ParserPhase;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.parser.core.BlockQuoteParser;
import com.vladsch.flexmark.parser.core.DocumentBlockParser;
import com.vladsch.flexmark.parser.core.FencedCodeBlockParser;
import com.vladsch.flexmark.parser.core.HeadingParser;
import com.vladsch.flexmark.parser.core.HtmlBlockParser;
import com.vladsch.flexmark.parser.core.IndentedCodeBlockParser;
import com.vladsch.flexmark.parser.core.ListBlockParser;
import com.vladsch.flexmark.parser.core.ParagraphParser;
import com.vladsch.flexmark.parser.core.ReferencePreProcessorFactory;
import com.vladsch.flexmark.parser.core.ThematicBreakParser;
import com.vladsch.flexmark.util.ast.BlankLine;
import com.vladsch.flexmark.util.ast.BlankLineContainer;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.KeepTrailingBlankLineContainer;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.collection.ItemFactoryMap;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIndexedIterator;
import com.vladsch.flexmark.util.collection.iteration.ReversibleIterator;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.dependency.DependencyResolver;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.PrefixedSubSequence;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/DocumentParser.class */
public class DocumentParser implements ParserState {
    public static final InlineParserFactory INLINE_PARSER_FACTORY = CommonmarkInlineParser::new;
    private static final HashMap<CustomBlockParserFactory, DataKey<Boolean>> CORE_FACTORIES_DATA_KEYS;
    private static final HashMap<DataKey<Boolean>, ParagraphPreProcessorFactory> CORE_PARAGRAPH_PRE_PROCESSORS;
    private BasedSequence line;
    private BasedSequence lineWithEOL;
    private boolean columnIsInTab;
    private boolean blank;
    private boolean isBlankLine;
    private final List<BlockParserFactory> blockParserFactories;
    private final List<List<ParagraphPreProcessorFactory>> paragraphPreProcessorDependencies;
    private final List<List<BlockPreProcessorFactory>> blockPreProcessorDependencies;
    private final InlineParser inlineParser;
    private final DocumentBlockParser documentBlockParser;
    private final boolean blankLinesInAst;
    private final boolean trackDocumentLines;
    private final DataHolder options;
    private ParserPhase currentPhase;
    private final Parsing myParsing;
    private int lineNumber = 0;
    private int lineStart = 0;
    private int lineEOLIndex = 0;
    private int lineEndIndex = 0;
    private int index = 0;
    private int column = 0;
    private int nextNonSpace = 0;
    private int nextNonSpaceColumn = 0;
    private int indent = 0;
    private final List<BasedSequence> lineSegments = new ArrayList();
    private final List<BlockParser> activeBlockParsers = new ArrayList();
    private final ClassifyingBlockTracker blockTracker = new ClassifyingBlockTracker();
    private final Map<Node, Boolean> lastLineBlank = new HashMap();

    static {
        HashMap<CustomBlockParserFactory, DataKey<Boolean>> hashMap = new HashMap<>();
        CORE_FACTORIES_DATA_KEYS = hashMap;
        hashMap.put(new BlockQuoteParser.Factory(), Parser.BLOCK_QUOTE_PARSER);
        CORE_FACTORIES_DATA_KEYS.put(new HeadingParser.Factory(), Parser.HEADING_PARSER);
        CORE_FACTORIES_DATA_KEYS.put(new FencedCodeBlockParser.Factory(), Parser.FENCED_CODE_BLOCK_PARSER);
        CORE_FACTORIES_DATA_KEYS.put(new HtmlBlockParser.Factory(), Parser.HTML_BLOCK_PARSER);
        CORE_FACTORIES_DATA_KEYS.put(new ThematicBreakParser.Factory(), Parser.THEMATIC_BREAK_PARSER);
        CORE_FACTORIES_DATA_KEYS.put(new ListBlockParser.Factory(), Parser.LIST_BLOCK_PARSER);
        CORE_FACTORIES_DATA_KEYS.put(new IndentedCodeBlockParser.Factory(), Parser.INDENTED_CODE_BLOCK_PARSER);
        HashMap<DataKey<Boolean>, ParagraphPreProcessorFactory> hashMap2 = new HashMap<>();
        CORE_PARAGRAPH_PRE_PROCESSORS = hashMap2;
        hashMap2.put(Parser.REFERENCE_PARAGRAPH_PRE_PROCESSOR, new ReferencePreProcessorFactory());
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public List<BasedSequence> getLineSegments() {
        return this.lineSegments;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParserTracker
    public void blockParserAdded(BlockParser blockParser) {
        this.blockTracker.blockParserAdded(blockParser);
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParserTracker
    public void blockParserRemoved(BlockParser blockParser) {
        this.blockTracker.blockParserRemoved(blockParser);
    }

    @Override // com.vladsch.flexmark.util.ast.BlockTracker
    public void blockAdded(Block block) {
        this.blockTracker.blockAdded(block);
    }

    @Override // com.vladsch.flexmark.util.ast.BlockTracker
    public void blockAddedWithChildren(Block block) {
        this.blockTracker.blockAddedWithChildren(block);
    }

    @Override // com.vladsch.flexmark.util.ast.BlockTracker
    public void blockAddedWithDescendants(Block block) {
        this.blockTracker.blockAddedWithDescendants(block);
    }

    @Override // com.vladsch.flexmark.util.ast.BlockTracker
    public void blockRemoved(Block block) {
        this.blockTracker.blockRemoved(block);
    }

    @Override // com.vladsch.flexmark.util.ast.BlockTracker
    public void blockRemovedWithChildren(Block block) {
        this.blockTracker.blockRemovedWithChildren(block);
    }

    @Override // com.vladsch.flexmark.util.ast.BlockTracker
    public void blockRemovedWithDescendants(Block block) {
        this.blockTracker.blockRemovedWithDescendants(block);
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public ParserPhase getParserPhase() {
        return this.currentPhase;
    }

    public DocumentParser(DataHolder dataHolder, List<CustomBlockParserFactory> list, List<List<ParagraphPreProcessorFactory>> list2, List<List<BlockPreProcessorFactory>> list3, InlineParser inlineParser) {
        this.options = dataHolder;
        this.myParsing = inlineParser.getParsing();
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<CustomBlockParserFactory> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().apply(dataHolder));
        }
        this.blockParserFactories = arrayList;
        this.paragraphPreProcessorDependencies = list2;
        this.blockPreProcessorDependencies = list3;
        this.inlineParser = inlineParser;
        this.documentBlockParser = new DocumentBlockParser();
        activateBlockParser(this.documentBlockParser);
        this.currentPhase = ParserPhase.STARTING;
        this.blankLinesInAst = Parser.BLANK_LINES_IN_AST.get(dataHolder).booleanValue();
        this.trackDocumentLines = Parser.TRACK_DOCUMENT_LINES.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public Parsing getParsing() {
        return this.myParsing;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public MutableDataHolder getProperties() {
        return this.documentBlockParser.getBlock();
    }

    public static List<CustomBlockParserFactory> calculateBlockParserFactories(DataHolder dataHolder, List<CustomBlockParserFactory> list) {
        ArrayList arrayList = new ArrayList(list);
        for (Map.Entry<CustomBlockParserFactory, DataKey<Boolean>> entry : CORE_FACTORIES_DATA_KEYS.entrySet()) {
            if (entry.getValue().get(dataHolder).booleanValue()) {
                arrayList.add(entry.getKey());
            }
        }
        return DependencyResolver.resolveFlatDependencies(arrayList, null, null);
    }

    public static List<List<ParagraphPreProcessorFactory>> calculateParagraphPreProcessors(DataHolder dataHolder, List<ParagraphPreProcessorFactory> list, InlineParserFactory inlineParserFactory) {
        ArrayList arrayList = new ArrayList(list);
        if (inlineParserFactory == INLINE_PARSER_FACTORY) {
            for (DataKey<Boolean> dataKey : CORE_PARAGRAPH_PRE_PROCESSORS.keySet()) {
                if (dataKey.get(dataHolder).booleanValue()) {
                    arrayList.add(CORE_PARAGRAPH_PRE_PROCESSORS.get(dataKey));
                }
            }
        }
        return DependencyResolver.resolveDependencies(arrayList, null, null);
    }

    public static List<List<BlockPreProcessorFactory>> calculateBlockPreProcessors(DataHolder dataHolder, List<BlockPreProcessorFactory> list) {
        return DependencyResolver.resolveDependencies(list, null, null);
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public InlineParser getInlineParser() {
        return this.inlineParser;
    }

    public Document parse(CharSequence charSequence) {
        int i;
        int i2;
        BasedSequence of = BasedSequence.of(charSequence);
        int i3 = 0;
        this.lineNumber = 0;
        this.documentBlockParser.initializeDocument(this.options, of);
        this.inlineParser.initializeDocument(this.documentBlockParser.getBlock());
        this.currentPhase = ParserPhase.PARSE_BLOCKS;
        while (true) {
            int findLineBreak = Parsing.findLineBreak(of, i3);
            if (findLineBreak == -1) {
                break;
            }
            BasedSequence subSequence = of.subSequence(i3, findLineBreak);
            if (findLineBreak + 1 < of.length() && of.charAt(findLineBreak) == '\r' && of.charAt(findLineBreak + 1) == '\n') {
                i = findLineBreak;
                i2 = 2;
            } else {
                i = findLineBreak;
                i2 = 1;
            }
            int i4 = i + i2;
            this.lineWithEOL = of.subSequence(i3, i4);
            this.lineStart = i3;
            this.lineEOLIndex = findLineBreak;
            this.lineEndIndex = i4;
            incorporateLine(subSequence);
            this.lineNumber++;
            i3 = i4;
        }
        if (of.length() > 0 && (i3 == 0 || i3 < of.length())) {
            this.lineWithEOL = of.subSequence(i3, of.length());
            this.lineStart = i3;
            this.lineEOLIndex = of.length();
            this.lineEndIndex = this.lineEOLIndex;
            incorporateLine(this.lineWithEOL);
            this.lineNumber++;
        }
        return finalizeAndProcess();
    }

    public Document parse(Reader reader) {
        BufferedReader bufferedReader;
        if (reader instanceof BufferedReader) {
            bufferedReader = (BufferedReader) reader;
        } else {
            bufferedReader = new BufferedReader(reader);
        }
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[16384];
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read >= 0) {
                sb.append(cArr, 0, read);
            } else {
                return parse(BasedSequence.of(sb.toString()));
            }
        }
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public int getLineStart() {
        return this.lineStart;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public int getLineEndIndex() {
        return this.lineEndIndex;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public BasedSequence getLine() {
        return this.line;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public BasedSequence getLineWithEOL() {
        return this.lineWithEOL;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public int getLineEolLength() {
        return this.lineEndIndex - this.lineEOLIndex;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public int getIndex() {
        return this.index;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public int getNextNonSpaceIndex() {
        return this.nextNonSpace;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public int getColumn() {
        return this.column;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public int getIndent() {
        return this.indent;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public boolean isBlank() {
        return this.blank;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public boolean isBlankLine() {
        return this.isBlankLine;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public BlockParser getActiveBlockParser() {
        return this.activeBlockParsers.get(this.activeBlockParsers.size() - 1);
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public BlockParser getActiveBlockParser(Block block) {
        BlockParser key = this.blockTracker.getKey(block);
        if (key == null || key.isClosed()) {
            return null;
        }
        return key;
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public List<BlockParser> getActiveBlockParsers() {
        return this.activeBlockParsers;
    }

    private void incorporateLine(BasedSequence basedSequence) {
        this.line = basedSequence;
        this.index = 0;
        this.column = 0;
        this.columnIsInTab = false;
        if (this.trackDocumentLines) {
            this.lineSegments.add(this.lineWithEOL);
        }
        int i = 1;
        BlankLine blankLine = null;
        findNextNonSpace();
        if (this.blank && this.blankLinesInAst) {
            blankLine = new BlankLine(this.lineWithEOL);
            this.documentBlockParser.getBlock().appendChild(blankLine);
        }
        for (BlockParser blockParser : this.activeBlockParsers.subList(1, this.activeBlockParsers.size())) {
            boolean z = this.blank;
            findNextNonSpace();
            if (this.blank && this.blankLinesInAst) {
                if (blankLine == null) {
                    blankLine = new BlankLine(this.lineWithEOL);
                    this.documentBlockParser.getBlock().appendChild(blankLine);
                }
                if (!z && (blockParser.getBlock() instanceof BlankLineContainer)) {
                    blankLine.setClaimedBlankLine(blockParser.getBlock());
                }
            }
            this.isBlankLine = z;
            BlockContinue tryContinue = blockParser.tryContinue(this);
            if (!(tryContinue instanceof BlockContinueImpl)) {
                break;
            }
            BlockContinueImpl blockContinueImpl = (BlockContinueImpl) tryContinue;
            if (blockContinueImpl.isFinalize()) {
                finalize(blockParser);
                return;
            }
            if (blockContinueImpl.getNewIndex() != -1) {
                setNewIndex(blockContinueImpl.getNewIndex());
                if (!this.blank && (blockParser.getBlock() instanceof BlankLineContainer)) {
                    findNextNonSpace();
                    if (this.blank) {
                        blankLine = new BlankLine(this.lineWithEOL, blockParser.getBlock());
                        blockParser.getBlock().appendChild(blankLine);
                    }
                }
            } else if (blockContinueImpl.getNewColumn() != -1) {
                setNewColumn(blockContinueImpl.getNewColumn());
                if (!this.blank && (blockParser.getBlock() instanceof BlankLineContainer)) {
                    findNextNonSpace();
                    if (this.blank) {
                        blankLine = new BlankLine(this.lineWithEOL, blockParser.getBlock());
                        blockParser.getBlock().appendChild(blankLine);
                    }
                }
            }
            i++;
            if (blankLine != null && (this.blankLinesInAst || blankLine.getClaimedBlankLine() == blockParser.getBlock())) {
                if (blockParser.getBlock() instanceof BlankLineContainer) {
                    blockParser.getBlock().appendChild(blankLine);
                }
            }
        }
        List<BlockParser> list = this.activeBlockParsers;
        ArrayList arrayList = new ArrayList(list.subList(i, list.size()));
        BlockParser blockParser2 = this.activeBlockParsers.get(i - 1);
        BlockParser blockParser3 = blockParser2;
        boolean isEmpty = arrayList.isEmpty();
        if (this.blank && isLastLineBlank(blockParser3.getBlock())) {
            breakOutOfLists(new ArrayList(this.activeBlockParsers.subList(0, i)));
        }
        boolean z2 = blockParser3.isInterruptible() || blockParser3.isContainer();
        BlockParser blockParser4 = null;
        while (true) {
            if (!z2) {
                break;
            }
            boolean z3 = this.blank;
            findNextNonSpace();
            if (this.blank && !z3) {
                blockParser4 = blockParser3;
            }
            if (this.blank || (this.indent < this.myParsing.CODE_BLOCK_INDENT && Parsing.isLetter(this.line, this.nextNonSpace))) {
                break;
            }
            BlockStartImpl findBlockStart = findBlockStart(blockParser3);
            if (findBlockStart == null) {
                if (!blockParser3.isRawText() || !blockParser3.isInterruptible()) {
                    setNewIndex(this.nextNonSpace);
                }
            } else {
                if (!isEmpty) {
                    finalizeBlocks(arrayList);
                    isEmpty = true;
                }
                if (findBlockStart.getNewIndex() != -1) {
                    setNewIndex(findBlockStart.getNewIndex());
                } else if (findBlockStart.getNewColumn() != -1) {
                    setNewColumn(findBlockStart.getNewColumn());
                }
                if (findBlockStart.isReplaceActiveBlockParser()) {
                    removeActiveBlockParser();
                }
                for (BlockParser blockParser5 : findBlockStart.getBlockParsers()) {
                    blockParser3 = addChild(blockParser5);
                    z2 = blockParser5.isContainer();
                }
            }
        }
        setNewIndex(this.nextNonSpace);
        if (!isEmpty && !this.blank && getActiveBlockParser().isParagraphParser()) {
            addLine();
            return;
        }
        if (!isEmpty) {
            finalizeBlocks(arrayList);
        }
        propagateLastLineBlank(blockParser3, blockParser2);
        if (this.blank && (blockParser3.getBlock() instanceof KeepTrailingBlankLineContainer)) {
            if (blankLine != null) {
                blockParser3.getBlock().appendChild(blankLine);
            } else if (blockParser3.isContainer() && blockParser4 == blockParser3) {
                blockParser3.getBlock().appendChild(new BlankLine(this.lineWithEOL, blockParser3.getBlock()));
            }
        }
        if (!blockParser3.isContainer()) {
            addLine();
        } else if (!this.blank) {
            addChild(new ParagraphParser());
            addLine();
        }
    }

    private void findNextNonSpace() {
        int i = this.index;
        int i2 = this.column;
        this.blank = true;
        while (true) {
            if (i < this.line.length()) {
                switch (this.line.charAt(i)) {
                    case '\t':
                        i++;
                        i2 += 4 - (i2 % 4);
                        break;
                    case ' ':
                        i++;
                        i2++;
                        break;
                    default:
                        this.blank = false;
                        break;
                }
            }
        }
        this.nextNonSpace = i;
        this.nextNonSpaceColumn = i2;
        this.indent = this.nextNonSpaceColumn - this.column;
    }

    private void setNewIndex(int i) {
        if (i >= this.nextNonSpace) {
            this.index = this.nextNonSpace;
            this.column = this.nextNonSpaceColumn;
        }
        while (this.index < i && this.index != this.line.length()) {
            advance();
        }
        this.columnIsInTab = false;
    }

    private void setNewColumn(int i) {
        if (i >= this.nextNonSpaceColumn) {
            this.index = this.nextNonSpace;
            this.column = this.nextNonSpaceColumn;
        }
        while (this.column < i && this.index != this.line.length()) {
            advance();
        }
        if (this.column > i) {
            this.index--;
            this.column = i;
            this.columnIsInTab = true;
            return;
        }
        this.columnIsInTab = false;
    }

    private void advance() {
        if (this.line.charAt(this.index) == '\t') {
            this.index++;
            this.column += Parsing.columnsToNextTabStop(this.column);
        } else {
            this.index++;
            this.column++;
        }
    }

    private void addLine() {
        BasedSequence subSequence = this.lineWithEOL.subSequence(this.index);
        if (this.columnIsInTab) {
            BasedSequence subSequence2 = subSequence.subSequence(1);
            int columnsToNextTabStop = Parsing.columnsToNextTabStop(this.column);
            StringBuilder sb = new StringBuilder(columnsToNextTabStop + subSequence2.length());
            for (int i = 0; i < columnsToNextTabStop; i++) {
                sb.append(' ');
            }
            subSequence = PrefixedSubSequence.prefixOf(sb.toString(), subSequence2);
        }
        getActiveBlockParser().addLine(this, subSequence);
    }

    private BlockStartImpl findBlockStart(BlockParser blockParser) {
        MatchedBlockParserImpl matchedBlockParserImpl = new MatchedBlockParserImpl(blockParser);
        for (BlockParserFactory blockParserFactory : this.blockParserFactories) {
            if (blockParser.canInterruptBy(blockParserFactory)) {
                BlockStart tryStart = blockParserFactory.tryStart(this, matchedBlockParserImpl);
                if (tryStart instanceof BlockStartImpl) {
                    return (BlockStartImpl) tryStart;
                }
            }
        }
        return null;
    }

    private void finalize(BlockParser blockParser) {
        if (getActiveBlockParser() == blockParser) {
            deactivateBlockParser();
        }
        Block block = blockParser.getBlock();
        if (block.getParent() != null) {
            Node lastChild = block.getLastChild();
            if ((lastChild instanceof BlankLine) && ((BlankLine) lastChild).getClaimedBlankLine() != block) {
                block.insertChainAfter(lastChild.getFirstInChain());
                block.setCharsFromContentOnly();
            }
        }
        blockParser.closeBlock(this);
        blockParser.finalizeClosedBlock();
        while (true) {
            Node next = block.getNext();
            if ((next instanceof BlankLine) && next.getEndOffset() <= block.getEndOffset()) {
                next.unlink();
            } else {
                return;
            }
        }
    }

    private void processInlines() {
        ReversibleIndexedIterator<BlockParser> it = this.blockTracker.allBlockParsers().iterator();
        while (it.hasNext()) {
            it.next().parseInlines(this.inlineParser);
        }
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public boolean endsWithBlankLine(Node node) {
        while (node != null) {
            if (isLastLineBlank(node)) {
                return true;
            }
            node = node.getLastBlankLineChild();
        }
        return false;
    }

    private void breakOutOfLists(List<BlockParser> list) {
        int i = -1;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).breakOutOnDoubleBlankLine()) {
                i = size;
            }
        }
        if (i != -1) {
            finalizeBlocks(list.subList(i, list.size()));
        }
    }

    private <T extends BlockParser> T addChild(T t) {
        while (!getActiveBlockParser().canContain(this, t, t.getBlock())) {
            finalize(getActiveBlockParser());
        }
        getActiveBlockParser().getBlock().appendChild(t.getBlock());
        activateBlockParser(t);
        return t;
    }

    private void activateBlockParser(BlockParser blockParser) {
        this.activeBlockParsers.add(blockParser);
        if (!this.blockTracker.containsKey(blockParser)) {
            blockParserAdded(blockParser);
        }
    }

    private void deactivateBlockParser() {
        this.activeBlockParsers.remove(this.activeBlockParsers.size() - 1);
    }

    private void removeActiveBlockParser() {
        BlockParser activeBlockParser = getActiveBlockParser();
        deactivateBlockParser();
        blockParserRemoved(activeBlockParser);
        activeBlockParser.getBlock().unlink();
    }

    private void propagateLastLineBlank(BlockParser blockParser, BlockParser blockParser2) {
        Node lastChild;
        if (this.blank && (lastChild = blockParser.getBlock().getLastChild()) != null) {
            setLastLineBlank(lastChild, true);
        }
        boolean z = this.blank && blockParser.isPropagatingLastBlankLine(blockParser2);
        Node block = blockParser.getBlock();
        while (true) {
            Node node = block;
            if (node != null) {
                setLastLineBlank(node, z);
                block = node.getParent();
            } else {
                return;
            }
        }
    }

    private void setLastLineBlank(Node node, boolean z) {
        this.lastLineBlank.put(node, Boolean.valueOf(z));
    }

    @Override // com.vladsch.flexmark.parser.block.ParserState
    public boolean isLastLineBlank(Node node) {
        Boolean bool = this.lastLineBlank.get(node);
        return bool != null && bool.booleanValue();
    }

    private boolean finalizeBlocks(List<BlockParser> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            finalize(list.get(size));
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/DocumentParser$ParagraphPreProcessorCache.class */
    public static class ParagraphPreProcessorCache extends ItemFactoryMap<ParagraphPreProcessor, ParserState> {
        ParagraphPreProcessorCache(ParserState parserState) {
            super(parserState);
        }
    }

    private void preProcessParagraph(Paragraph paragraph, List<ParagraphPreProcessorFactory> list, ParagraphPreProcessorCache paragraphPreProcessorCache) {
        do {
            boolean z = false;
            Iterator<ParagraphPreProcessorFactory> it = list.iterator();
            while (it.hasNext()) {
                int preProcessBlock = paragraphPreProcessorCache.getItem(it.next()).preProcessBlock(paragraph, this);
                if (preProcessBlock > 0) {
                    z = true;
                    BasedSequence chars = paragraph.getChars();
                    BasedSequence subSequence = chars.subSequence(preProcessBlock + chars.countLeading(CharPredicate.WHITESPACE, preProcessBlock, chars.length()));
                    if (subSequence.isBlank()) {
                        paragraph.unlink();
                        blockRemoved(paragraph);
                        return;
                    }
                    int lineCount = paragraph.getLineCount();
                    int i = 0;
                    while (i < lineCount && paragraph.getLineChars(i).getEndOffset() <= subSequence.getStartOffset()) {
                        i++;
                    }
                    if (i < lineCount) {
                        if (paragraph.getLineChars(i).getEndOffset() == subSequence.getStartOffset()) {
                            paragraph.setContent(paragraph, i, lineCount);
                        } else {
                            ArrayList arrayList = new ArrayList(lineCount - i);
                            arrayList.addAll(paragraph.getContentLines().subList(i, lineCount));
                            int startOffset = subSequence.getStartOffset() - ((BasedSequence) arrayList.get(0)).getStartOffset();
                            if (startOffset > 0 && startOffset < ((BasedSequence) arrayList.get(0)).length()) {
                                arrayList.set(0, ((BasedSequence) arrayList.get(0)).subSequence(startOffset));
                            }
                            int[] iArr = new int[lineCount - i];
                            System.arraycopy(paragraph.getLineIndents(), i, iArr, 0, iArr.length);
                            paragraph.setContentLines(arrayList);
                            paragraph.setLineIndents(iArr);
                            paragraph.setChars(subSequence);
                        }
                    } else {
                        paragraph.unlink();
                        blockRemoved(paragraph);
                        return;
                    }
                }
            }
            if (!z) {
                return;
            }
        } while (list.size() >= 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void preProcessParagraphs() {
        if (this.blockTracker.getNodeClassifier().containsCategory(Paragraph.class)) {
            ParagraphPreProcessorCache paragraphPreProcessorCache = new ParagraphPreProcessorCache(this);
            for (List<ParagraphPreProcessorFactory> list : this.paragraphPreProcessorDependencies) {
                ReversibleIterator it = this.blockTracker.getNodeClassifier().getCategoryItems(Paragraph.class, Paragraph.class).iterator();
                while (it.hasNext()) {
                    preProcessParagraph((Paragraph) it.next(), list, paragraphPreProcessorCache);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void preProcessBlocks() {
        HashSet hashSet = new HashSet();
        Iterator<List<BlockPreProcessorFactory>> it = this.blockPreProcessorDependencies.iterator();
        while (it.hasNext()) {
            Iterator<BlockPreProcessorFactory> it2 = it.next().iterator();
            while (it2.hasNext()) {
                hashSet.addAll(it2.next().getBlockTypes());
            }
        }
        if (!this.blockTracker.getNodeClassifier().categoriesBitSet(hashSet).isEmpty()) {
            Iterator<List<BlockPreProcessorFactory>> it3 = this.blockPreProcessorDependencies.iterator();
            while (it3.hasNext()) {
                for (BlockPreProcessorFactory blockPreProcessorFactory : it3.next()) {
                    Iterable categoryItems = this.blockTracker.getNodeClassifier().getCategoryItems(Block.class, blockPreProcessorFactory.getBlockTypes());
                    BlockPreProcessor apply = blockPreProcessorFactory.apply((ParserState) this);
                    ReversibleIterator it4 = categoryItems.iterator();
                    while (it4.hasNext()) {
                        apply.preProcess(this, (Block) it4.next());
                    }
                }
            }
        }
    }

    private Document finalizeAndProcess() {
        finalizeBlocks(this.activeBlockParsers);
        this.currentPhase = ParserPhase.PRE_PROCESS_PARAGRAPHS;
        preProcessParagraphs();
        this.currentPhase = ParserPhase.PRE_PROCESS_BLOCKS;
        preProcessBlocks();
        this.currentPhase = ParserPhase.PARSE_INLINES;
        processInlines();
        this.currentPhase = ParserPhase.DONE;
        Document block = this.documentBlockParser.getBlock();
        this.inlineParser.finalizeDocument(block);
        return block;
    }
}
