package com.vladsch.flexmark.util.data;

import java.util.function.Supplier;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/NullableDataKey.class */
public class NullableDataKey<T> extends DataKeyBase<T> {
    public NullableDataKey(String str, T t, DataValueFactory<T> dataValueFactory) {
        super(str, t, dataValueFactory);
    }

    public NullableDataKey(String str, DataValueNullableFactory<T> dataValueNullableFactory) {
        super(str, dataValueNullableFactory.apply((DataHolder) null), dataValueNullableFactory);
    }

    public NullableDataKey(String str, Supplier<T> supplier) {
        super(str, supplier.get(), dataHolder -> {
            return supplier.get();
        });
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public NullableDataKey(java.lang.String r7, com.vladsch.flexmark.util.data.DataKeyBase<T> r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            java.lang.Object r2 = r2.getDefaultValue()
            r3 = r8
            r4 = r3
            java.lang.Class r4 = r4.getClass()
            void r3 = r3::get
            r0.<init>(r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.util.data.NullableDataKey.<init>(java.lang.String, com.vladsch.flexmark.util.data.DataKeyBase):void");
    }

    public NullableDataKey(String str, T t) {
        this(str, t, dataHolder -> {
            return t;
        });
    }

    public NullableDataKey(String str) {
        this(str, null, dataHolder -> {
            return null;
        });
    }

    @Override // com.vladsch.flexmark.util.data.DataKeyBase
    public T getDefaultValue() {
        return (T) super.getDefaultValue();
    }

    @Override // com.vladsch.flexmark.util.data.DataKeyBase
    public T getDefaultValue(DataHolder dataHolder) {
        return (T) super.getDefaultValue(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.data.DataKeyBase
    public T get(DataHolder dataHolder) {
        return (T) super.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataValueSetter
    public MutableDataHolder set(MutableDataHolder mutableDataHolder, T t) {
        return mutableDataHolder.set((NullableDataKey<NullableDataKey<T>>) this, (NullableDataKey<T>) t);
    }

    @Override // com.vladsch.flexmark.util.data.DataKeyBase
    public String toString() {
        T defaultValue = getDefaultValue();
        if (defaultValue != null) {
            return "DataKey<" + defaultValue.getClass().getSimpleName() + "> " + getName();
        }
        return "DataKey<null> " + getName();
    }
}
