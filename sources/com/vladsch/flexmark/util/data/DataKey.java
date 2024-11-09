package com.vladsch.flexmark.util.data;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/DataKey.class */
public class DataKey<T> extends DataKeyBase<T> {
    public DataKey(String str, T t, DataNotNullValueFactory<T> dataNotNullValueFactory) {
        super(str, t, dataNotNullValueFactory);
    }

    public DataKey(String str, NotNullValueSupplier<T> notNullValueSupplier) {
        super(str, notNullValueSupplier.get(), dataHolder -> {
            return notNullValueSupplier.get();
        });
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public DataKey(java.lang.String r7, com.vladsch.flexmark.util.data.DataKey<T> r8) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.util.data.DataKey.<init>(java.lang.String, com.vladsch.flexmark.util.data.DataKey):void");
    }

    public DataKey(String str, T t) {
        this(str, t, dataHolder -> {
            return t;
        });
    }

    @Override // com.vladsch.flexmark.util.data.DataKeyBase
    public DataNotNullValueFactory<T> getFactory() {
        return (DataNotNullValueFactory) super.getFactory();
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
        return mutableDataHolder.set((DataKey<DataKey<T>>) this, (DataKey<T>) t);
    }

    @Override // com.vladsch.flexmark.util.data.DataKeyBase
    public String toString() {
        return "DataKey<" + getDefaultValue().getClass().getSimpleName() + "> " + getName();
    }
}
