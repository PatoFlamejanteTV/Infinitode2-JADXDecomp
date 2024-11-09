package com.vladsch.flexmark.util.builder;

import com.vladsch.flexmark.util.builder.BuilderBase;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.data.NullableDataKey;
import com.vladsch.flexmark.util.data.SharedDataKeys;
import com.vladsch.flexmark.util.misc.Extension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/builder/BuilderBase.class */
public abstract class BuilderBase<T extends BuilderBase<T>> extends MutableDataSet {
    private final HashSet<Class<?>> loadedExtensions;
    private final HashMap<Class<?>, HashSet<Object>> extensionApiPoints;
    private Extension currentExtension;

    protected abstract void removeApiPoint(Object obj);

    protected abstract void preloadExtension(Extension extension);

    protected abstract boolean loadExtension(Extension extension);

    public abstract Object build();

    @Override // com.vladsch.flexmark.util.data.MutableDataSet, com.vladsch.flexmark.util.data.MutableDataHolder
    public /* bridge */ /* synthetic */ MutableDataHolder set(NullableDataKey nullableDataKey, Object obj) {
        return set((NullableDataKey<NullableDataKey>) nullableDataKey, (NullableDataKey) obj);
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSet, com.vladsch.flexmark.util.data.MutableDataHolder
    public /* bridge */ /* synthetic */ MutableDataHolder set(DataKey dataKey, Object obj) {
        return set((DataKey<DataKey>) dataKey, (DataKey) obj);
    }

    public final T extensions(Collection<? extends Extension> collection) {
        ArrayList arrayList = new ArrayList(SharedDataKeys.EXTENSIONS.get(this).size() + collection.size());
        for (Extension extension : collection) {
            this.currentExtension = extension;
            if (!this.loadedExtensions.contains(extension.getClass())) {
                preloadExtension(extension);
                arrayList.add(extension);
            }
            this.currentExtension = null;
        }
        for (Extension extension2 : collection) {
            this.currentExtension = extension2;
            Class<?> cls = extension2.getClass();
            if (!this.loadedExtensions.contains(cls) && loadExtension(extension2)) {
                this.loadedExtensions.add(cls);
                arrayList.add(extension2);
            }
            this.currentExtension = null;
        }
        if (!arrayList.isEmpty()) {
            arrayList.addAll(0, SharedDataKeys.EXTENSIONS.get(this));
            set((DataKey<DataKey>) SharedDataKeys.EXTENSIONS, (DataKey) arrayList);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addExtensionApiPoint(Object obj) {
        Extension extension = this.currentExtension;
        if (extension != null) {
            this.extensionApiPoints.computeIfAbsent(extension.getClass(), cls -> {
                return new HashSet();
            }).add(obj);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vladsch.flexmark.util.data.MutableDataSet, com.vladsch.flexmark.util.data.MutableDataHolder
    public <V> MutableDataSet set(DataKey<V> dataKey, V v) {
        addExtensionApiPoint(dataKey);
        return super.set((DataKey<DataKey<V>>) dataKey, (DataKey<V>) v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vladsch.flexmark.util.data.MutableDataSet, com.vladsch.flexmark.util.data.MutableDataHolder
    public <V> MutableDataSet set(NullableDataKey<V> nullableDataKey, V v) {
        addExtensionApiPoint(nullableDataKey);
        return super.set((NullableDataKey<NullableDataKey<V>>) nullableDataKey, (NullableDataKey<V>) v);
    }

    @Override // com.vladsch.flexmark.util.data.DataHolder, com.vladsch.flexmark.util.data.MutableDataHolder
    @Deprecated
    public <V> V get(DataKey<V> dataKey) {
        return dataKey.get(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BuilderBase(DataHolder dataHolder) {
        super(dataHolder);
        this.loadedExtensions = new HashSet<>();
        this.extensionApiPoints = new HashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void loadExtensions() {
        if (contains(SharedDataKeys.EXTENSIONS)) {
            extensions(SharedDataKeys.EXTENSIONS.get(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BuilderBase() {
        this.loadedExtensions = new HashSet<>();
        this.extensionApiPoints = new HashMap<>();
    }

    public static DataHolder removeExtensions(DataHolder dataHolder, Collection<Class<? extends Extension>> collection) {
        if (dataHolder.contains(SharedDataKeys.EXTENSIONS)) {
            ArrayList arrayList = new ArrayList(SharedDataKeys.EXTENSIONS.get(dataHolder));
            if (arrayList.removeIf(extension -> {
                return collection.contains(extension.getClass());
            })) {
                return dataHolder instanceof MutableDataHolder ? ((MutableDataHolder) dataHolder).set((DataKey<DataKey<Collection<Extension>>>) SharedDataKeys.EXTENSIONS, (DataKey<Collection<Extension>>) arrayList) : dataHolder.toMutable().set((DataKey<DataKey<Collection<Extension>>>) SharedDataKeys.EXTENSIONS, (DataKey<Collection<Extension>>) arrayList).toImmutable();
            }
        }
        return dataHolder;
    }
}
