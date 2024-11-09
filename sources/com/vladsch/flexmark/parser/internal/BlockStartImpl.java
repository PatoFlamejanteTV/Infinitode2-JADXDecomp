package com.vladsch.flexmark.parser.internal;

import com.vladsch.flexmark.parser.block.BlockParser;
import com.vladsch.flexmark.parser.block.BlockStart;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/BlockStartImpl.class */
public class BlockStartImpl extends BlockStart {
    private final BlockParser[] blockParsers;
    private int newIndex = -1;
    private int newColumn = -1;
    private boolean replaceActiveBlockParser = false;

    public BlockStartImpl(BlockParser... blockParserArr) {
        this.blockParsers = blockParserArr;
    }

    public BlockParser[] getBlockParsers() {
        return this.blockParsers;
    }

    public int getNewIndex() {
        return this.newIndex;
    }

    public int getNewColumn() {
        return this.newColumn;
    }

    public boolean isReplaceActiveBlockParser() {
        return this.replaceActiveBlockParser;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockStart
    public BlockStart atIndex(int i) {
        this.newIndex = i;
        return this;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockStart
    public BlockStart atColumn(int i) {
        this.newColumn = i;
        return this;
    }

    @Override // com.vladsch.flexmark.parser.block.BlockStart
    public BlockStart replaceActiveBlockParser() {
        this.replaceActiveBlockParser = true;
        return this;
    }
}
