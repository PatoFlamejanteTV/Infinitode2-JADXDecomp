package com.vladsch.flexmark.ext.yaml.front.matter.internal;

import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterBlock;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterNode;
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
import com.vladsch.flexmark.parser.core.DocumentBlockParser;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.PrefixedSubSequence;
import com.vladsch.flexmark.util.sequence.SegmentedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/internal/YamlFrontMatterBlockParser.class */
public class YamlFrontMatterBlockParser extends AbstractBlockParser {
    private static final Pattern REGEX_METADATA = Pattern.compile("^[ ]{0,3}([A-Za-z0-9_-]+):\\s*(.*)");
    private static final Pattern REGEX_METADATA_LIST = Pattern.compile("^[ ]+-\\s*(.*)");
    private static final Pattern REGEX_METADATA_LITERAL = Pattern.compile("^\\s*(.*)");
    private static final Pattern REGEX_BEGIN = Pattern.compile("^-{3}(\\s.*)?");
    private static final Pattern REGEX_END = Pattern.compile("^(-{3}|\\.{3})(\\s.*)?");
    private boolean inYAMLBlock = true;
    private boolean inLiteral = false;
    private BasedSequence currentKey = null;
    private List<BasedSequence> currentValues = new ArrayList();
    private YamlFrontMatterBlock block = new YamlFrontMatterBlock();
    private BlockContent content = new BlockContent();

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public boolean isContainer() {
        return false;
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void addLine(ParserState parserState, BasedSequence basedSequence) {
        this.content.add(basedSequence, parserState.getIndent());
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public void closeBlock(ParserState parserState) {
        this.block.setContent(this.content.getLines().subList(0, this.content.getLineCount()));
        this.block.setCharsFromContent();
        this.content = null;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState parserState) {
        BasedSequence line = parserState.getLine();
        if (this.inYAMLBlock) {
            if (REGEX_END.matcher(line).matches()) {
                if (this.currentKey != null) {
                    YamlFrontMatterNode yamlFrontMatterNode = new YamlFrontMatterNode(this.currentKey, this.currentValues);
                    yamlFrontMatterNode.setCharsFromContent();
                    this.block.appendChild(yamlFrontMatterNode);
                }
                addLine(parserState, line);
                return BlockContinue.finished();
            }
            Matcher matcher = REGEX_METADATA.matcher(line);
            if (matcher.matches()) {
                if (this.currentKey != null) {
                    YamlFrontMatterNode yamlFrontMatterNode2 = new YamlFrontMatterNode(this.currentKey, this.currentValues);
                    yamlFrontMatterNode2.setCharsFromContent();
                    this.block.appendChild(yamlFrontMatterNode2);
                }
                this.inLiteral = false;
                this.currentKey = line.subSequence(matcher.start(1), matcher.end(1));
                this.currentValues = new ArrayList();
                if ("|".equals(matcher.group(2))) {
                    this.inLiteral = true;
                } else if (!"".equals(matcher.group(2))) {
                    this.currentValues.add(line.subSequence(matcher.start(2), matcher.end(2)));
                }
                return BlockContinue.atIndex(parserState.getIndex());
            }
            if (this.inLiteral) {
                Matcher matcher2 = REGEX_METADATA_LITERAL.matcher(line);
                if (matcher2.matches()) {
                    if (this.currentValues.size() != 1) {
                        this.currentValues.add(line.subSequence(matcher2.start(1), matcher2.end(1)).trim());
                    } else {
                        this.currentValues.set(0, SegmentedSequence.create(this.currentValues.get(0), PrefixedSubSequence.prefixOf(SequenceUtils.EOL, line.subSequence(matcher2.start(1), matcher2.end(1)).trim())));
                    }
                }
            } else {
                Matcher matcher3 = REGEX_METADATA_LIST.matcher(line);
                if (matcher3.matches()) {
                    this.currentValues.add(line.subSequence(matcher3.start(1), matcher3.end(1)));
                }
            }
            return BlockContinue.atIndex(parserState.getIndex());
        }
        if (REGEX_BEGIN.matcher(line).matches()) {
            this.inYAMLBlock = true;
            return BlockContinue.atIndex(parserState.getIndex());
        }
        return BlockContinue.none();
    }

    @Override // com.vladsch.flexmark.parser.block.AbstractBlockParser, com.vladsch.flexmark.parser.block.BlockParser
    public void parseInlines(InlineParser inlineParser) {
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/internal/YamlFrontMatterBlockParser$Factory.class */
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
    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/internal/YamlFrontMatterBlockParser$BlockFactory.class */
    public static class BlockFactory extends AbstractBlockParserFactory {
        private BlockFactory(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.vladsch.flexmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState parserState, MatchedBlockParser matchedBlockParser) {
            BasedSequence line = parserState.getLine();
            BlockParser blockParser = matchedBlockParser.getBlockParser();
            return ((blockParser instanceof DocumentBlockParser) && blockParser.getBlock().getFirstChild() == null && YamlFrontMatterBlockParser.REGEX_BEGIN.matcher(line).matches()) ? BlockStart.of(new YamlFrontMatterBlockParser()).atIndex(parserState.getNextNonSpaceIndex()) : BlockStart.none();
        }
    }
}
