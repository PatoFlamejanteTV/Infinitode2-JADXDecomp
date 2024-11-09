package com.vladsch.flexmark.parser.block;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.dependency.Dependent;
import com.vladsch.flexmark.util.sequence.mappers.SpecialLeadInHandler;
import java.util.function.Function;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/parser/block/CustomBlockParserFactory.class */
public interface CustomBlockParserFactory extends Dependent, Function<DataHolder, BlockParserFactory> {
    @Override // java.util.function.Function
    BlockParserFactory apply(DataHolder dataHolder);

    default SpecialLeadInHandler getLeadInHandler(DataHolder dataHolder) {
        return null;
    }
}
