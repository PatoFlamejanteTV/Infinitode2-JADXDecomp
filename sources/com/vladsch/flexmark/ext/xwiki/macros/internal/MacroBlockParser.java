package com.vladsch.flexmark.ext.xwiki.macros.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.util.Parsing;
import com.vladsch.flexmark.ext.xwiki.macros.Macro;
import com.vladsch.flexmark.ext.xwiki.macros.MacroAttribute;
import com.vladsch.flexmark.ext.xwiki.macros.MacroBlock;
import com.vladsch.flexmark.ext.xwiki.macros.MacroClose;
import com.vladsch.flexmark.parser.InlineParser;
import com.vladsch.flexmark.parser.block.AbstractBlockParser;
import com.vladsch.flexmark.parser.block.AbstractBlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockContinue;
import com.vladsch.flexmark.parser.block.BlockParser;
import com.vladsch.flexmark.parser.block.BlockParserFactory;
import com.vladsch.flexmark.parser.block.BlockStart;
import com.vladsch.flexmark.parser.block.CustomBlockParserFactory;
import com.vladsch.flexmark.parser.block.MatchedBlockParser;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/internal/MacroBlockParser.class */
public class MacroBlockParser extends AbstractBlockParser {
    private final MacroBlock block = new MacroBlock();
    private BlockContent content = new BlockContent();
    private final MacroParsing parsing;
    private final BasedSequence macroName;
    private final boolean oneLine;
    private boolean hadClose;

    MacroBlockParser(DataHolder dataHolder, MacroParsing macroParsing, BasedSequence basedSequence, boolean z) {
        this.parsing = macroParsing;
        this.macroName = basedSequence;
        this.oneLine = z;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        BlockParser blockParser;
        if (this.hadClose) {
            return BlockContinue.none();
        }
        BasedSequence line = parserState.getLine();
        Matcher matcher = this.parsing.MACRO_CLOSE.matcher(line);
        if (matcher.find() && this.macroName.equals(matcher.group(1))) {
            List<BlockParser> activeBlockParsers = parserState.getActiveBlockParsers();
            boolean z = false;
            int size = activeBlockParsers.size();
            while (true) {
                int i = size;
                size--;
                if (i <= 0 || (blockParser = activeBlockParsers.get(size)) == this) {
                    break;
                }
                if ((blockParser instanceof MacroBlockParser) && !((MacroBlockParser) blockParser).hadClose && ((MacroBlockParser) blockParser).macroName.equals(this.macroName)) {
                    z = true;
                }
            }
            if (!z) {
                this.hadClose = true;
                MacroClose macroClose = new MacroClose(line.subSequence(matcher.start(), matcher.start() + 3), line.subSequence(matcher.start(1), matcher.end(1)), line.subSequence(matcher.end() - 2, matcher.end()));
                macroClose.setCharsFromContent();
                this.block.appendChild(macroClose);
                return BlockContinue.atIndex(parserState.getLineEndIndex());
            }
        }
        return BlockContinue.atIndex(parserState.getIndex());
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
        this.content.add(basedSequence, parserState.getIndent());
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canContain(ParserState parserState, BlockParser blockParser, Block block) {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        BasedSequence baseSubSequence;
        if (this.oneLine) {
            ArrayList arrayList = new ArrayList();
            Macro macro = (Macro) this.block.getFirstChild();
            Node lastChild = this.block.getLastChild();
            if (lastChild instanceof MacroClose) {
                baseSubSequence = macro.baseSubSequence(macro.getEndOffset(), lastChild.getStartOffset());
            } else {
                baseSubSequence = macro.baseSubSequence(macro.getEndOffset(), macro.getEndOffset());
            }
            arrayList.add(baseSubSequence);
            this.block.setContent(arrayList);
        } else if (this.hadClose) {
            this.block.setContent(this.content.getLines());
        } else {
            List<BasedSequence> lines = this.content.getLines();
            this.block.setContent(lines.subList(0, lines.size()));
        }
        this.block.setCharsFromContent();
        this.content = null;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
        Node lastChild = this.block.getLastChild();
        if (lastChild instanceof MacroClose) {
            lastChild.unlink();
        }
        inlineParser.parse(this.block.getContentChars(), this.block);
        if (lastChild instanceof MacroClose) {
            this.block.appendChild(lastChild);
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/internal/MacroBlockParser$Factory.class */
    public static class Factory implements CustomBlockParserFactory {
        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return false;
        }

        @Override // com.vladsch.flexmark.parser.block.CustomBlockParserFactory, java.util.function.Function
        public BlockParserFactory apply(DataHolder dataHolder) {
            return new BlockFactory(dataHolder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/internal/MacroBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private final MacroParsing parsing;

        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.parsing = new MacroParsing(new Parsing(dataHolder));
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            BasedSequence line = parserState.getLine();
            if (parserState.getIndent() == 0 && !(matchedBlockParser.getBlockParser().getBlock() instanceof Paragraph)) {
                BasedSequence subSequence = line.subSequence(parserState.getIndex());
                Matcher matcher = this.parsing.MACRO_OPEN.matcher(subSequence);
                if (matcher.find()) {
                    BasedSequence subSequence2 = line.subSequence(matcher.start(1), matcher.end(1));
                    BasedSequence subSequence3 = subSequence.subSequence(0, matcher.end());
                    BasedSequence subSequence4 = subSequence.subSequence(matcher.end());
                    boolean z = false;
                    boolean z2 = false;
                    MacroClose macroClose = null;
                    if (subSequence3.endsWith("/}}")) {
                        if (subSequence4.isBlank()) {
                            z = true;
                            z2 = true;
                        } else {
                            return BlockStart.none();
                        }
                    } else if (!subSequence4.isBlank()) {
                        Matcher matcher2 = this.parsing.MACRO_CLOSE_END.matcher(subSequence4);
                        if (matcher2.find() && subSequence2.equals(matcher2.group(1)) && (matcher2.groupCount() < 2 || matcher2.start(2) == -1 || (matcher2.group(2).length() & 1) == 1)) {
                            z = true;
                            MacroClose macroClose2 = new MacroClose(subSequence4.subSequence(matcher2.start(), matcher2.start() + 3), subSequence4.subSequence(matcher2.start(1), matcher2.end(1)), subSequence4.subSequence(matcher2.end() - 2, matcher2.end()));
                            macroClose = macroClose2;
                            macroClose2.setCharsFromContent();
                        }
                        if (!z) {
                            return BlockStart.none();
                        }
                    }
                    Macro macro = new Macro(subSequence3.subSequence(0, 2), subSequence2, subSequence3.endSequence(z2 ? 3 : 2));
                    macro.setCharsFromContent();
                    BasedSequence trim = subSequence3.baseSubSequence(subSequence2.getEndOffset(), macro.getClosingMarker().getStartOffset()).trim();
                    if (!trim.isEmpty()) {
                        macro.setAttributeText(trim);
                        Matcher matcher3 = this.parsing.MACRO_ATTRIBUTE.matcher(trim);
                        while (matcher3.find()) {
                            BasedSequence subSequence5 = trim.subSequence(matcher3.start(1), matcher3.end(1));
                            BasedSequence trim2 = (matcher3.groupCount() == 1 || matcher3.start(2) == -1) ? BasedSequence.NULL : trim.subSequence(matcher3.end(1), matcher3.start(2)).trim();
                            BasedSequence subSequence6 = (matcher3.groupCount() == 1 || matcher3.start(2) == -1) ? BasedSequence.NULL : trim.subSequence(matcher3.start(2), matcher3.end(2));
                            BasedSequence basedSequence = subSequence6;
                            boolean z3 = subSequence6.length() >= 2 && ((basedSequence.charAt(0) == '\"' && basedSequence.endCharAt(1) == '\"') || (basedSequence.charAt(0) == '\'' && basedSequence.endCharAt(1) == '\''));
                            boolean z4 = z3;
                            BasedSequence subSequence7 = !z3 ? BasedSequence.NULL : basedSequence.subSequence(0, 1);
                            BasedSequence endSequence = !z4 ? BasedSequence.NULL : basedSequence.endSequence(1, 0);
                            if (z4) {
                                basedSequence = basedSequence.midSequence(1, -1);
                            }
                            macro.appendChild(new MacroAttribute(subSequence5, trim2, subSequence7, basedSequence, endSequence));
                        }
                    }
                    MacroBlockParser macroBlockParser = new MacroBlockParser(parserState.getProperties(), this.parsing, subSequence2, z);
                    if (z) {
                        macroBlockParser.hadClose = true;
                    }
                    macroBlockParser.block.appendChild(macro);
                    if (macroClose != null) {
                        macroBlockParser.block.appendChild(macroClose);
                    }
                    return BlockStart.of(macroBlockParser).atIndex(parserState.getLineEndIndex());
                }
            }
            return BlockStart.none();
        }
    }
}
