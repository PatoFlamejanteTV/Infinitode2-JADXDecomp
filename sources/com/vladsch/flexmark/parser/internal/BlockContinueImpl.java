package com.vladsch.flexmark.parser.internal;

import com.vladsch.flexmark.parser.block.BlockContinue;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/internal/BlockContinueImpl.class */
public class BlockContinueImpl extends BlockContinue {
    private final int newIndex;
    private final int newColumn;
    private final boolean finalize;

    public BlockContinueImpl(int i, int i2, boolean z) {
        this.newIndex = i;
        this.newColumn = i2;
        this.finalize = z;
    }

    public int getNewIndex() {
        return this.newIndex;
    }

    public int getNewColumn() {
        return this.newColumn;
    }

    public boolean isFinalize() {
        return this.finalize;
    }
}
