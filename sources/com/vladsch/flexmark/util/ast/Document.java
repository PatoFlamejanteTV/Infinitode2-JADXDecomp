package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.DataKeyBase;
import com.vladsch.flexmark.util.data.DataSet;
import com.vladsch.flexmark.util.data.DataValueFactory;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.data.MutableDataSetter;
import com.vladsch.flexmark.util.data.NullableDataKey;
import com.vladsch.flexmark.util.misc.Utils;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Collection;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/Document.class */
public class Document extends Block implements MutableDataHolder {
    public static final Document NULL;
    private final MutableDataSet dataSet;
    static final /* synthetic */ boolean $assertionsDisabled;

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public /* bridge */ /* synthetic */ MutableDataHolder remove(DataKeyBase dataKeyBase) {
        return remove((DataKeyBase<?>) dataKeyBase);
    }

    static {
        $assertionsDisabled = !Document.class.desiredAssertionStatus();
        NULL = new Document(null, BasedSequence.NULL);
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public Document(DataHolder dataHolder, BasedSequence basedSequence) {
        super(basedSequence);
        this.dataSet = new MutableDataSet(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public MutableDataHolder clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public <T> MutableDataHolder set(DataKey<T> dataKey, T t) {
        return this.dataSet.set((DataKey<DataKey<T>>) dataKey, (DataKey<T>) t);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public <T> MutableDataHolder set(NullableDataKey<T> nullableDataKey, T t) {
        return this.dataSet.set((NullableDataKey<NullableDataKey<T>>) nullableDataKey, (NullableDataKey<T>) t);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public MutableDataSet setFrom(MutableDataSetter mutableDataSetter) {
        return this.dataSet.setFrom(mutableDataSetter);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public MutableDataSet setAll(DataHolder dataHolder) {
        return this.dataSet.setAll(dataHolder);
    }

    public static MutableDataSet merge(DataHolder... dataHolderArr) {
        return MutableDataSet.merge(dataHolderArr);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder, com.vladsch.flexmark.util.data.DataHolder, com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        return this.dataSet.setIn(mutableDataHolder);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder
    public MutableDataSet remove(DataKeyBase<?> dataKeyBase) {
        return this.dataSet.remove(dataKeyBase);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataHolder, com.vladsch.flexmark.util.data.DataHolder
    public Object getOrCompute(DataKeyBase<?> dataKeyBase, DataValueFactory<?> dataValueFactory) {
        return this.dataSet.getOrCompute(dataKeyBase, dataValueFactory);
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public MutableDataSet toMutable() {
        return this.dataSet.toMutable();
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public DataSet toImmutable() {
        return this.dataSet.toImmutable();
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public MutableDataSet toDataSet() {
        return this.dataSet.toDataSet();
    }

    public static DataHolder aggregateActions(DataHolder dataHolder, DataHolder dataHolder2) {
        return DataSet.aggregateActions(dataHolder, dataHolder2);
    }

    public DataHolder aggregate() {
        return this.dataSet.aggregate();
    }

    public static DataHolder aggregate(DataHolder dataHolder, DataHolder dataHolder2) {
        return DataSet.aggregate(dataHolder, dataHolder2);
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public Map<? extends DataKeyBase<?>, Object> getAll() {
        return this.dataSet.getAll();
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public Collection<? extends DataKeyBase<?>> getKeys() {
        return this.dataSet.getKeys();
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder
    public boolean contains(DataKeyBase<?> dataKeyBase) {
        return this.dataSet.contains(dataKeyBase);
    }

    @Override // com.vladsch.flexmark.util.ast.ContentNode, com.vladsch.flexmark.util.ast.Content
    public int getLineCount() {
        if (this.lineSegments == BasedSequence.EMPTY_LIST) {
            char lastChar = getChars().lastChar();
            return ((lastChar == '\n' || lastChar == '\r') ? 0 : 1) + getLineNumber(getChars().length());
        }
        return this.lineSegments.size();
    }

    public int getLineNumber(int i) {
        if (this.lineSegments == BasedSequence.EMPTY_LIST) {
            BasedSequence baseSubSequence = getChars().baseSubSequence(0, Utils.maxLimit(i + 1, getChars().length()));
            if (baseSubSequence.isEmpty()) {
                return 0;
            }
            int i2 = 0;
            int endOfLineAnyEOL = baseSubSequence.endOfLineAnyEOL(0);
            int length = baseSubSequence.length();
            while (endOfLineAnyEOL < length) {
                int eolStartLength = endOfLineAnyEOL + baseSubSequence.eolStartLength(endOfLineAnyEOL);
                if (i >= eolStartLength) {
                    i2++;
                }
                int i3 = endOfLineAnyEOL;
                endOfLineAnyEOL = baseSubSequence.endOfLineAnyEOL(eolStartLength);
                if (!$assertionsDisabled && endOfLineAnyEOL <= i3) {
                    throw new AssertionError();
                }
            }
            return i2;
        }
        int size = this.lineSegments.size();
        for (int i4 = 0; i4 < size; i4++) {
            if (i < this.lineSegments.get(i4).getEndOffset()) {
                return i4;
            }
        }
        return size;
    }
}
