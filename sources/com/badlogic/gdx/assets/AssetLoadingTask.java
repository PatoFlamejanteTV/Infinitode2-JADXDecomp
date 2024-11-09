package com.badlogic.gdx.assets;

import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncResult;
import com.badlogic.gdx.utils.async.AsyncTask;
import java.lang.reflect.GenericDeclaration;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/AssetLoadingTask.class */
public class AssetLoadingTask implements AsyncTask<Void> {
    AssetManager manager;
    final AssetDescriptor assetDesc;
    final AssetLoader loader;
    final AsyncExecutor executor;
    final long startTime;
    volatile boolean asyncDone;
    volatile boolean dependenciesLoaded;
    volatile Array<AssetDescriptor> dependencies;
    volatile AsyncResult<Void> depsFuture;
    volatile AsyncResult<Void> loadFuture;
    volatile Object asset;
    volatile boolean cancel;

    public AssetLoadingTask(AssetManager assetManager, AssetDescriptor assetDescriptor, AssetLoader assetLoader, AsyncExecutor asyncExecutor) {
        this.manager = assetManager;
        this.assetDesc = assetDescriptor;
        this.loader = assetLoader;
        this.executor = asyncExecutor;
        this.startTime = assetManager.log.getLevel() == 3 ? TimeUtils.nanoTime() : 0L;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.badlogic.gdx.utils.async.AsyncTask
    public Void call() {
        if (this.cancel) {
            return null;
        }
        AsynchronousAssetLoader asynchronousAssetLoader = (AsynchronousAssetLoader) this.loader;
        if (!this.dependenciesLoaded) {
            this.dependencies = asynchronousAssetLoader.getDependencies(this.assetDesc.fileName, resolve(this.loader, this.assetDesc), this.assetDesc.params);
            if (this.dependencies != null) {
                removeDuplicates(this.dependencies);
                this.manager.injectDependencies(this.assetDesc.fileName, this.dependencies);
                return null;
            }
            asynchronousAssetLoader.loadAsync(this.manager, this.assetDesc.fileName, resolve(this.loader, this.assetDesc), this.assetDesc.params);
            this.asyncDone = true;
            return null;
        }
        asynchronousAssetLoader.loadAsync(this.manager, this.assetDesc.fileName, resolve(this.loader, this.assetDesc), this.assetDesc.params);
        this.asyncDone = true;
        return null;
    }

    public boolean update() {
        if (this.loader instanceof SynchronousAssetLoader) {
            handleSyncLoader();
        } else {
            handleAsyncLoader();
        }
        return this.asset != null;
    }

    private void handleSyncLoader() {
        SynchronousAssetLoader synchronousAssetLoader = (SynchronousAssetLoader) this.loader;
        if (!this.dependenciesLoaded) {
            this.dependenciesLoaded = true;
            this.dependencies = synchronousAssetLoader.getDependencies(this.assetDesc.fileName, resolve(this.loader, this.assetDesc), this.assetDesc.params);
            if (this.dependencies == null) {
                this.asset = synchronousAssetLoader.load(this.manager, this.assetDesc.fileName, resolve(this.loader, this.assetDesc), this.assetDesc.params);
                return;
            } else {
                removeDuplicates(this.dependencies);
                this.manager.injectDependencies(this.assetDesc.fileName, this.dependencies);
                return;
            }
        }
        this.asset = synchronousAssetLoader.load(this.manager, this.assetDesc.fileName, resolve(this.loader, this.assetDesc), this.assetDesc.params);
    }

    private void handleAsyncLoader() {
        AsynchronousAssetLoader asynchronousAssetLoader = (AsynchronousAssetLoader) this.loader;
        if (!this.dependenciesLoaded) {
            if (this.depsFuture == null) {
                this.depsFuture = this.executor.submit(this);
                return;
            }
            if (this.depsFuture.isDone()) {
                try {
                    this.depsFuture.get();
                    this.dependenciesLoaded = true;
                    if (this.asyncDone) {
                        this.asset = asynchronousAssetLoader.loadSync(this.manager, this.assetDesc.fileName, resolve(this.loader, this.assetDesc), this.assetDesc.params);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    throw new GdxRuntimeException("Couldn't load dependencies of asset: " + this.assetDesc.fileName, e);
                }
            }
            return;
        }
        if (this.loadFuture == null && !this.asyncDone) {
            this.loadFuture = this.executor.submit(this);
            return;
        }
        if (this.asyncDone) {
            this.asset = asynchronousAssetLoader.loadSync(this.manager, this.assetDesc.fileName, resolve(this.loader, this.assetDesc), this.assetDesc.params);
        } else if (this.loadFuture.isDone()) {
            try {
                this.loadFuture.get();
                this.asset = asynchronousAssetLoader.loadSync(this.manager, this.assetDesc.fileName, resolve(this.loader, this.assetDesc), this.assetDesc.params);
            } catch (Exception e2) {
                throw new GdxRuntimeException("Couldn't load asset: " + this.assetDesc.fileName, e2);
            }
        }
    }

    public void unload() {
        if (this.loader instanceof AsynchronousAssetLoader) {
            ((AsynchronousAssetLoader) this.loader).unloadAsync(this.manager, this.assetDesc.fileName, resolve(this.loader, this.assetDesc), this.assetDesc.params);
        }
    }

    private FileHandle resolve(AssetLoader assetLoader, AssetDescriptor assetDescriptor) {
        if (assetDescriptor.file == null) {
            assetDescriptor.file = assetLoader.resolve(assetDescriptor.fileName);
        }
        return assetDescriptor.file;
    }

    private void removeDuplicates(Array<AssetDescriptor> array) {
        boolean z = array.ordered;
        array.ordered = true;
        for (int i = 0; i < array.size; i++) {
            String str = array.get(i).fileName;
            GenericDeclaration genericDeclaration = array.get(i).type;
            for (int i2 = array.size - 1; i2 > i; i2--) {
                if (genericDeclaration == array.get(i2).type && str.equals(array.get(i2).fileName)) {
                    array.removeIndex(i2);
                }
            }
        }
        array.ordered = z;
    }
}
