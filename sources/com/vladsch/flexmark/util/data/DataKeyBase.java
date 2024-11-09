package com.vladsch.flexmark.util.data;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/data/DataKeyBase.class */
public abstract class DataKeyBase<T> implements MutableDataValueSetter<T> {
    private final String name;
    private final DataValueFactory<T> factory;
    private final T defaultValue;

    public DataKeyBase(String str, T t, DataValueFactory<T> dataValueFactory) {
        this.name = str;
        this.defaultValue = t;
        this.factory = dataValueFactory;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public DataKeyBase(java.lang.String r7, com.vladsch.flexmark.util.data.DataKeyBase<T> r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            T r2 = r2.defaultValue
            r3 = r8
            r4 = r3
            java.lang.Class r4 = r4.getClass()
            void r3 = r3::get
            r0.<init>(r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.util.data.DataKeyBase.<init>(java.lang.String, com.vladsch.flexmark.util.data.DataKeyBase):void");
    }

    public DataKeyBase(String str, T t) {
        this(str, t, dataHolder -> {
            return t;
        });
    }

    public String getName() {
        return this.name;
    }

    public DataValueFactory<T> getFactory() {
        return this.factory;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public T getDefaultValue(DataHolder dataHolder) {
        return this.factory.apply(dataHolder);
    }

    public T get(DataHolder dataHolder) {
        return dataHolder == null ? this.defaultValue : (T) dataHolder.getOrCompute(this, this::getDefaultValue);
    }

    @Deprecated
    public final T getFrom(DataHolder dataHolder) {
        return get(dataHolder);
    }

    public String toString() {
        if (this.defaultValue != null) {
            return "NullableDataKey<" + this.defaultValue.getClass().getSimpleName() + "> " + this.name;
        }
        return "NullableDataKey<unknown> " + this.name;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return super.hashCode();
    }
}
