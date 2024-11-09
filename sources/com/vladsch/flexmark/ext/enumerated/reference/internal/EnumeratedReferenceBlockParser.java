package com.vladsch.flexmark.ext.enumerated.reference.internal;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceBlock;
import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension;
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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceBlockParser.class */
public class EnumeratedReferenceBlockParser extends AbstractBlockParser {
    static String ENUM_REF_ID = "(?:[^0-9].*)?";
    static Pattern ENUM_REF_ID_PATTERN = Pattern.compile("\\[[\\@|#]\\s*(" + ENUM_REF_ID + ")\\s*\\]");
    static Pattern ENUM_REF_DEF_PATTERN = Pattern.compile("^(\\[[\\@]\\s*(" + ENUM_REF_ID + ")\\s*\\]:)\\s+");
    final EnumeratedReferenceBlock block = new EnumeratedReferenceBlock();
    private BlockContent content = new BlockContent();

    public EnumeratedReferenceBlockParser(EnumeratedReferenceOptions enumeratedReferenceOptions, int i) {
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public BlockContent getBlockContent() {
        return this.content;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        return BlockContinue.none();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
        throw new IllegalStateException("Abbreviation Blocks hold a single line");
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.block.setCharsFromContent();
        this.block.setEnumeratedReference(this.block.getChars().subSequence(this.block.getClosingMarker().getEndOffset() - this.block.getStartOffset()).trimStart());
        this.content = null;
        EnumeratedReferenceExtension.ENUMERATED_REFERENCES.get(parserState.getProperties()).put2(this.block.getText().toString(), (String) this.block);
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
        Node firstChild = this.block.getFirstChild();
        if (firstChild != null) {
            inlineParser.parse(firstChild.getChars(), firstChild);
        }
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean canContain(ParserState parserState, BlockParser blockParser, Block block) {
        return blockParser.isParagraphParser();
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceBlockParser$Factory.class */
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
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private final EnumeratedReferenceOptions options;

        BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
            this.options = new EnumeratedReferenceOptions(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            if (parserState.getIndent() >= 4) {
                return BlockStart.none();
            }
            BasedSequence lineWithEOL = parserState.getLineWithEOL();
            int nextNonSpaceIndex = parserState.getNextNonSpaceIndex();
            BasedSequence subSequence = lineWithEOL.subSequence(nextNonSpaceIndex, lineWithEOL.length());
            Matcher matcher = EnumeratedReferenceBlockParser.ENUM_REF_DEF_PATTERN.matcher(subSequence);
            if (matcher.find()) {
                int start = nextNonSpaceIndex + matcher.start(1);
                int end = nextNonSpaceIndex + matcher.end(1);
                BasedSequence subSequence2 = lineWithEOL.subSequence(start, start + 2);
                BasedSequence subSequence3 = lineWithEOL.subSequence(matcher.start(2), matcher.end(2));
                BasedSequence subSequence4 = lineWithEOL.subSequence(end - 2, end);
                EnumeratedReferenceBlockParser enumeratedReferenceBlockParser = new EnumeratedReferenceBlockParser(this.options, this.options.contentIndent);
                enumeratedReferenceBlockParser.block.setOpeningMarker(subSequence2);
                enumeratedReferenceBlockParser.block.setText(subSequence3);
                enumeratedReferenceBlockParser.block.setClosingMarker(subSequence4);
                BasedSequence subSequence5 = subSequence.subSequence(matcher.end());
                enumeratedReferenceBlockParser.block.setEnumeratedReference(subSequence5);
                enumeratedReferenceBlockParser.block.appendChild(new Paragraph(subSequence5));
                enumeratedReferenceBlockParser.block.setCharsFromContent();
                return BlockStart.of(enumeratedReferenceBlockParser).atIndex(lineWithEOL.length());
            }
            return BlockStart.none();
        }
    }
}
