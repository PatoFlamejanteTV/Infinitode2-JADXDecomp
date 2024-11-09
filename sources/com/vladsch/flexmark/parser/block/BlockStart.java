package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.parser.internal.BlockStartImpl;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/block/BlockStart.class */
public abstract class BlockStart {
    public abstract BlockStart atIndex(int i);

    public abstract BlockStart atColumn(int i);

    public abstract BlockStart replaceActiveBlockParser();

    public static BlockStart none() {
        return null;
    }

    public static BlockStart of(BlockParser... blockParserArr) {
        return new BlockStartImpl(blockParserArr);
    }
}
