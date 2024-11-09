package com.badlogic.gdx.assets;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.CubemapLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.PixmapLoader;
import com.badlogic.gdx.assets.loaders.ShaderProgramLoader;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonRegionLoader;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.UBJsonReader;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.ThreadUtils;
import com.badlogic.gdx.utils.reflect.ClassReflection;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/AssetManager.class */
public class AssetManager implements Disposable {
    final ObjectMap<Class, ObjectMap<String, RefCountedContainer>> assets;
    final ObjectMap<String, Class> assetTypes;
    final ObjectMap<String, Array<String>> assetDependencies;
    final ObjectSet<String> injected;
    final ObjectMap<Class, ObjectMap<String, AssetLoader>> loaders;
    final Array<AssetDescriptor> loadQueue;
    final AsyncExecutor executor;
    final Array<AssetLoadingTask> tasks;
    AssetErrorListener listener;
    int loaded;
    int toLoad;
    int peakTasks;
    final FileHandleResolver resolver;
    Logger log;

    public AssetManager() {
        this(new InternalFileHandleResolver());
    }

    public AssetManager(FileHandleResolver fileHandleResolver) {
        this(fileHandleResolver, true);
    }

    public AssetManager(FileHandleResolver fileHandleResolver, boolean z) {
        this.assets = new ObjectMap<>();
        this.assetTypes = new ObjectMap<>();
        this.assetDependencies = new ObjectMap<>();
        this.injected = new ObjectSet<>();
        this.loaders = new ObjectMap<>();
        this.loadQueue = new Array<>();
        this.tasks = new Array<>();
        this.log = new Logger("AssetManager", 0);
        this.resolver = fileHandleResolver;
        if (z) {
            setLoader(BitmapFont.class, new BitmapFontLoader(fileHandleResolver));
            setLoader(Music.class, new MusicLoader(fileHandleResolver));
            setLoader(Pixmap.class, new PixmapLoader(fileHandleResolver));
            setLoader(Sound.class, new SoundLoader(fileHandleResolver));
            setLoader(TextureAtlas.class, new TextureAtlasLoader(fileHandleResolver));
            setLoader(Texture.class, new TextureLoader(fileHandleResolver));
            setLoader(Skin.class, new SkinLoader(fileHandleResolver));
            setLoader(ParticleEffect.class, new ParticleEffectLoader(fileHandleResolver));
            setLoader(com.badlogic.gdx.graphics.g3d.particles.ParticleEffect.class, new com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader(fileHandleResolver));
            setLoader(PolygonRegion.class, new PolygonRegionLoader(fileHandleResolver));
            setLoader(I18NBundle.class, new I18NBundleLoader(fileHandleResolver));
            setLoader(Model.class, ".g3dj", new G3dModelLoader(new JsonReader(), fileHandleResolver));
            setLoader(Model.class, ".g3db", new G3dModelLoader(new UBJsonReader(), fileHandleResolver));
            setLoader(Model.class, ".obj", new ObjLoader(fileHandleResolver));
            setLoader(ShaderProgram.class, new ShaderProgramLoader(fileHandleResolver));
            setLoader(Cubemap.class, new CubemapLoader(fileHandleResolver));
        }
        this.executor = new AsyncExecutor(1, "AssetManager");
    }

    public FileHandleResolver getFileHandleResolver() {
        return this.resolver;
    }

    public synchronized <T> T get(String str) {
        return (T) get(str, true);
    }

    public synchronized <T> T get(String str, Class<T> cls) {
        return (T) get(str, cls, true);
    }

    @Null
    public synchronized <T> T get(String str, boolean z) {
        ObjectMap<String, RefCountedContainer> objectMap;
        RefCountedContainer refCountedContainer;
        Class cls = this.assetTypes.get(str);
        if (cls != null && (objectMap = this.assets.get(cls)) != null && (refCountedContainer = objectMap.get(str)) != null) {
            return (T) refCountedContainer.object;
        }
        if (z) {
            throw new GdxRuntimeException("Asset not loaded: " + str);
        }
        return null;
    }

    @Null
    public synchronized <T> T get(String str, Class<T> cls, boolean z) {
        RefCountedContainer refCountedContainer;
        ObjectMap<String, RefCountedContainer> objectMap = this.assets.get(cls);
        if (objectMap != null && (refCountedContainer = objectMap.get(str)) != null) {
            return (T) refCountedContainer.object;
        }
        if (z) {
            throw new GdxRuntimeException("Asset not loaded: " + str);
        }
        return null;
    }

    public synchronized <T> T get(AssetDescriptor<T> assetDescriptor) {
        return (T) get(assetDescriptor.fileName, assetDescriptor.type, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized <T> Array<T> getAll(Class<T> cls, Array<T> array) {
        ObjectMap<String, RefCountedContainer> objectMap = this.assets.get(cls);
        if (objectMap != null) {
            ObjectMap.Values<RefCountedContainer> it = objectMap.values().iterator();
            while (it.hasNext()) {
                array.add(it.next().object);
            }
        }
        return array;
    }

    public synchronized boolean contains(String str) {
        if (this.tasks.size > 0 && this.tasks.first().assetDesc.fileName.equals(str)) {
            return true;
        }
        for (int i = 0; i < this.loadQueue.size; i++) {
            if (this.loadQueue.get(i).fileName.equals(str)) {
                return true;
            }
        }
        return isLoaded(str);
    }

    public synchronized boolean contains(String str, Class cls) {
        if (this.tasks.size > 0) {
            AssetDescriptor assetDescriptor = this.tasks.first().assetDesc;
            if (assetDescriptor.type == cls && assetDescriptor.fileName.equals(str)) {
                return true;
            }
        }
        for (int i = 0; i < this.loadQueue.size; i++) {
            AssetDescriptor assetDescriptor2 = this.loadQueue.get(i);
            if (assetDescriptor2.type == cls && assetDescriptor2.fileName.equals(str)) {
                return true;
            }
        }
        return isLoaded(str, cls);
    }

    public synchronized void unload(String str) {
        if (this.tasks.size > 0) {
            AssetLoadingTask first = this.tasks.first();
            if (first.assetDesc.fileName.equals(str)) {
                this.log.info("Unload (from tasks): " + str);
                first.cancel = true;
                first.unload();
                return;
            }
        }
        Class cls = this.assetTypes.get(str);
        int i = -1;
        int i2 = 0;
        while (true) {
            if (i2 >= this.loadQueue.size) {
                break;
            }
            if (!this.loadQueue.get(i2).fileName.equals(str)) {
                i2++;
            } else {
                i = i2;
                break;
            }
        }
        if (i != -1) {
            this.toLoad--;
            AssetDescriptor removeIndex = this.loadQueue.removeIndex(i);
            this.log.info("Unload (from queue): " + str);
            if (cls != null && removeIndex.params != null && removeIndex.params.loadedCallback != null) {
                removeIndex.params.loadedCallback.finishedLoading(this, removeIndex.fileName, removeIndex.type);
                return;
            }
            return;
        }
        if (cls == null) {
            throw new GdxRuntimeException("Asset not loaded: " + str);
        }
        RefCountedContainer refCountedContainer = this.assets.get(cls).get(str);
        refCountedContainer.refCount--;
        if (refCountedContainer.refCount <= 0) {
            this.log.info("Unload (dispose): " + str);
            if (refCountedContainer.object instanceof Disposable) {
                ((Disposable) refCountedContainer.object).dispose();
            }
            this.assetTypes.remove(str);
            this.assets.get(cls).remove(str);
        } else {
            this.log.info("Unload (decrement): " + str);
        }
        Array<String> array = this.assetDependencies.get(str);
        if (array != null) {
            Array.ArrayIterator<String> it = array.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (isLoaded(next)) {
                    unload(next);
                }
            }
        }
        if (refCountedContainer.refCount <= 0) {
            this.assetDependencies.remove(str);
        }
    }

    public synchronized <T> boolean containsAsset(T t) {
        ObjectMap<String, RefCountedContainer> objectMap = this.assets.get(t.getClass());
        if (objectMap == null) {
            return false;
        }
        ObjectMap.Values<RefCountedContainer> it = objectMap.values().iterator();
        while (it.hasNext()) {
            RefCountedContainer next = it.next();
            if (next.object == t || t.equals(next.object)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized <T> String getAssetFileName(T t) {
        ObjectMap.Keys<Class> it = this.assets.keys().iterator();
        while (it.hasNext()) {
            ObjectMap.Entries<String, RefCountedContainer> it2 = this.assets.get(it.next()).iterator();
            while (it2.hasNext()) {
                ObjectMap.Entry next = it2.next();
                Object obj = ((RefCountedContainer) next.value).object;
                if (obj == t || t.equals(obj)) {
                    return (String) next.key;
                }
            }
        }
        return null;
    }

    public synchronized boolean isLoaded(AssetDescriptor assetDescriptor) {
        return isLoaded(assetDescriptor.fileName);
    }

    public synchronized boolean isLoaded(String str) {
        if (str == null) {
            return false;
        }
        return this.assetTypes.containsKey(str);
    }

    public synchronized boolean isLoaded(String str, Class cls) {
        ObjectMap<String, RefCountedContainer> objectMap = this.assets.get(cls);
        return (objectMap == null || objectMap.get(str) == null) ? false : true;
    }

    public <T> AssetLoader getLoader(Class<T> cls) {
        return getLoader(cls, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> AssetLoader getLoader(Class<T> cls, String str) {
        ObjectMap<String, AssetLoader> objectMap = this.loaders.get(cls);
        if (objectMap == null || objectMap.size <= 0) {
            return null;
        }
        if (str == null) {
            return objectMap.get("");
        }
        AssetLoader assetLoader = null;
        int i = -1;
        ObjectMap.Entries<String, AssetLoader> it = objectMap.entries().iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            if (((String) next.key).length() > i && str.endsWith((String) next.key)) {
                assetLoader = (AssetLoader) next.value;
                i = ((String) next.key).length();
            }
        }
        return assetLoader;
    }

    public synchronized <T> void load(String str, Class<T> cls) {
        load(str, cls, null);
    }

    public synchronized <T> void load(String str, Class<T> cls, AssetLoaderParameters<T> assetLoaderParameters) {
        if (getLoader(cls, str) == null) {
            throw new GdxRuntimeException("No loader for type: " + ClassReflection.getSimpleName(cls));
        }
        if (this.loadQueue.size == 0) {
            this.loaded = 0;
            this.toLoad = 0;
            this.peakTasks = 0;
        }
        for (int i = 0; i < this.loadQueue.size; i++) {
            AssetDescriptor assetDescriptor = this.loadQueue.get(i);
            if (assetDescriptor.fileName.equals(str) && !assetDescriptor.type.equals(cls)) {
                throw new GdxRuntimeException("Asset with name '" + str + "' already in preload queue, but has different type (expected: " + ClassReflection.getSimpleName(cls) + ", found: " + ClassReflection.getSimpleName(assetDescriptor.type) + ")");
            }
        }
        for (int i2 = 0; i2 < this.tasks.size; i2++) {
            AssetDescriptor assetDescriptor2 = this.tasks.get(i2).assetDesc;
            if (assetDescriptor2.fileName.equals(str) && !assetDescriptor2.type.equals(cls)) {
                throw new GdxRuntimeException("Asset with name '" + str + "' already in task list, but has different type (expected: " + ClassReflection.getSimpleName(cls) + ", found: " + ClassReflection.getSimpleName(assetDescriptor2.type) + ")");
            }
        }
        Class cls2 = this.assetTypes.get(str);
        if (cls2 != null && !cls2.equals(cls)) {
            throw new GdxRuntimeException("Asset with name '" + str + "' already loaded, but has different type (expected: " + ClassReflection.getSimpleName(cls) + ", found: " + ClassReflection.getSimpleName(cls2) + ")");
        }
        this.toLoad++;
        AssetDescriptor assetDescriptor3 = new AssetDescriptor(str, cls, assetLoaderParameters);
        this.loadQueue.add(assetDescriptor3);
        this.log.debug("Queued: " + assetDescriptor3);
    }

    public synchronized void load(AssetDescriptor assetDescriptor) {
        load(assetDescriptor.fileName, assetDescriptor.type, assetDescriptor.params);
    }

    public synchronized boolean update() {
        try {
            if (this.tasks.size == 0) {
                while (this.loadQueue.size != 0 && this.tasks.size == 0) {
                    nextTask();
                }
                if (this.tasks.size == 0) {
                    return true;
                }
            }
            if (updateTask() && this.loadQueue.size == 0) {
                return this.tasks.size == 0;
            }
            return false;
        } catch (Throwable th) {
            handleTaskError(th);
            return this.loadQueue.size == 0;
        }
    }

    public boolean update(int i) {
        boolean update;
        if (Gdx.app.getType() == Application.ApplicationType.WebGL) {
            return update();
        }
        long millis = TimeUtils.millis() + i;
        while (true) {
            update = update();
            if (update || TimeUtils.millis() > millis) {
                break;
            }
            ThreadUtils.yield();
        }
        return update;
    }

    public synchronized boolean isFinished() {
        return this.loadQueue.size == 0 && this.tasks.size == 0;
    }

    public void finishLoading() {
        this.log.debug("Waiting for loading to complete...");
        while (!update()) {
            ThreadUtils.yield();
        }
        this.log.debug("Loading complete.");
    }

    public <T> T finishLoadingAsset(AssetDescriptor assetDescriptor) {
        return (T) finishLoadingAsset(assetDescriptor.fileName);
    }

    public <T> T finishLoadingAsset(String str) {
        ObjectMap<String, RefCountedContainer> objectMap;
        RefCountedContainer refCountedContainer;
        this.log.debug("Waiting for asset to be loaded: " + str);
        while (true) {
            synchronized (this) {
                Class cls = this.assetTypes.get(str);
                if (cls != null && (objectMap = this.assets.get(cls)) != null && (refCountedContainer = objectMap.get(str)) != null) {
                    this.log.debug("Asset loaded: " + str);
                    return (T) refCountedContainer.object;
                }
                update();
            }
            ThreadUtils.yield();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void injectDependencies(String str, Array<AssetDescriptor> array) {
        ObjectSet<String> objectSet = this.injected;
        Array.ArrayIterator<AssetDescriptor> it = array.iterator();
        while (it.hasNext()) {
            AssetDescriptor next = it.next();
            if (!objectSet.contains(next.fileName)) {
                objectSet.add(next.fileName);
                injectDependency(str, next);
            }
        }
        objectSet.clear(32);
    }

    private synchronized void injectDependency(String str, AssetDescriptor assetDescriptor) {
        Array<String> array = this.assetDependencies.get(str);
        Array<String> array2 = array;
        if (array == null) {
            array2 = new Array<>();
            this.assetDependencies.put(str, array2);
        }
        array2.add(assetDescriptor.fileName);
        if (isLoaded(assetDescriptor.fileName)) {
            this.log.debug("Dependency already loaded: " + assetDescriptor);
            this.assets.get(this.assetTypes.get(assetDescriptor.fileName)).get(assetDescriptor.fileName).refCount++;
            incrementRefCountedDependencies(assetDescriptor.fileName);
            return;
        }
        this.log.info("Loading dependency: " + assetDescriptor);
        addTask(assetDescriptor);
    }

    private void nextTask() {
        AssetDescriptor removeIndex = this.loadQueue.removeIndex(0);
        if (isLoaded(removeIndex.fileName)) {
            this.log.debug("Already loaded: " + removeIndex);
            this.assets.get(this.assetTypes.get(removeIndex.fileName)).get(removeIndex.fileName).refCount++;
            incrementRefCountedDependencies(removeIndex.fileName);
            if (removeIndex.params != null && removeIndex.params.loadedCallback != null) {
                removeIndex.params.loadedCallback.finishedLoading(this, removeIndex.fileName, removeIndex.type);
            }
            this.loaded++;
            return;
        }
        this.log.info("Loading: " + removeIndex);
        addTask(removeIndex);
    }

    private void addTask(AssetDescriptor assetDescriptor) {
        AssetLoader loader = getLoader(assetDescriptor.type, assetDescriptor.fileName);
        if (loader == null) {
            throw new GdxRuntimeException("No loader for type: " + ClassReflection.getSimpleName(assetDescriptor.type));
        }
        this.tasks.add(new AssetLoadingTask(this, assetDescriptor, loader, this.executor));
        this.peakTasks++;
    }

    protected <T> void addAsset(String str, Class<T> cls, T t) {
        this.assetTypes.put(str, cls);
        ObjectMap<String, RefCountedContainer> objectMap = this.assets.get(cls);
        ObjectMap<String, RefCountedContainer> objectMap2 = objectMap;
        if (objectMap == null) {
            objectMap2 = new ObjectMap<>();
            this.assets.put(cls, objectMap2);
        }
        RefCountedContainer refCountedContainer = new RefCountedContainer();
        refCountedContainer.object = t;
        objectMap2.put(str, refCountedContainer);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00df A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean updateTask() {
        /*
            Method dump skipped, instructions count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.assets.AssetManager.updateTask():boolean");
    }

    protected void taskFailed(AssetDescriptor assetDescriptor, RuntimeException runtimeException) {
        throw runtimeException;
    }

    private void incrementRefCountedDependencies(String str) {
        Array<String> array = this.assetDependencies.get(str);
        if (array == null) {
            return;
        }
        Array.ArrayIterator<String> it = array.iterator();
        while (it.hasNext()) {
            String next = it.next();
            this.assets.get(this.assetTypes.get(next)).get(next).refCount++;
            incrementRefCountedDependencies(next);
        }
    }

    private void handleTaskError(Throwable th) {
        this.log.error("Error loading asset.", th);
        if (this.tasks.isEmpty()) {
            throw new GdxRuntimeException(th);
        }
        AssetLoadingTask pop = this.tasks.pop();
        AssetDescriptor assetDescriptor = pop.assetDesc;
        if (pop.dependenciesLoaded && pop.dependencies != null) {
            Array.ArrayIterator<AssetDescriptor> it = pop.dependencies.iterator();
            while (it.hasNext()) {
                unload(it.next().fileName);
            }
        }
        this.tasks.clear();
        if (this.listener != null) {
            this.listener.error(assetDescriptor, th);
            return;
        }
        throw new GdxRuntimeException(th);
    }

    public synchronized <T, P extends AssetLoaderParameters<T>> void setLoader(Class<T> cls, AssetLoader<T, P> assetLoader) {
        setLoader(cls, null, assetLoader);
    }

    public synchronized <T, P extends AssetLoaderParameters<T>> void setLoader(Class<T> cls, String str, AssetLoader<T, P> assetLoader) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if (assetLoader == null) {
            throw new IllegalArgumentException("loader cannot be null.");
        }
        this.log.debug("Loader set: " + ClassReflection.getSimpleName(cls) + " -> " + ClassReflection.getSimpleName(assetLoader.getClass()));
        ObjectMap<String, AssetLoader> objectMap = this.loaders.get(cls);
        ObjectMap<String, AssetLoader> objectMap2 = objectMap;
        if (objectMap == null) {
            ObjectMap<Class, ObjectMap<String, AssetLoader>> objectMap3 = this.loaders;
            ObjectMap<String, AssetLoader> objectMap4 = new ObjectMap<>();
            objectMap2 = objectMap4;
            objectMap3.put(cls, objectMap4);
        }
        objectMap2.put(str == null ? "" : str, assetLoader);
    }

    public synchronized int getLoadedAssets() {
        return this.assetTypes.size;
    }

    public synchronized int getQueuedAssets() {
        return this.loadQueue.size + this.tasks.size;
    }

    public synchronized float getProgress() {
        if (this.toLoad == 0) {
            return 1.0f;
        }
        float f = this.loaded;
        if (this.peakTasks > 0) {
            f += (this.peakTasks - this.tasks.size) / this.peakTasks;
        }
        return Math.min(1.0f, f / this.toLoad);
    }

    public synchronized void setErrorListener(AssetErrorListener assetErrorListener) {
        this.listener = assetErrorListener;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.log.debug("Disposing.");
        clear();
        this.executor.dispose();
    }

    public void clear() {
        synchronized (this) {
            this.loadQueue.clear();
        }
        finishLoading();
        synchronized (this) {
            ObjectIntMap objectIntMap = new ObjectIntMap();
            while (this.assetTypes.size > 0) {
                objectIntMap.clear(51);
                Array<String> array = this.assetTypes.keys().toArray();
                Array.ArrayIterator<String> it = array.iterator();
                while (it.hasNext()) {
                    Array<String> array2 = this.assetDependencies.get(it.next());
                    if (array2 != null) {
                        Array.ArrayIterator<String> it2 = array2.iterator();
                        while (it2.hasNext()) {
                            objectIntMap.getAndIncrement(it2.next(), 0, 1);
                        }
                    }
                }
                Array.ArrayIterator<String> it3 = array.iterator();
                while (it3.hasNext()) {
                    String next = it3.next();
                    if (objectIntMap.get(next, 0) == 0) {
                        unload(next);
                    }
                }
            }
            this.assets.clear(51);
            this.assetTypes.clear(51);
            this.assetDependencies.clear(51);
            this.loaded = 0;
            this.toLoad = 0;
            this.peakTasks = 0;
            this.loadQueue.clear();
            this.tasks.clear();
        }
    }

    public Logger getLogger() {
        return this.log;
    }

    public void setLogger(Logger logger) {
        this.log = logger;
    }

    public synchronized int getReferenceCount(String str) {
        Class cls = this.assetTypes.get(str);
        if (cls == null) {
            throw new GdxRuntimeException("Asset not loaded: " + str);
        }
        return this.assets.get(cls).get(str).refCount;
    }

    public synchronized void setReferenceCount(String str, int i) {
        Class cls = this.assetTypes.get(str);
        if (cls == null) {
            throw new GdxRuntimeException("Asset not loaded: " + str);
        }
        this.assets.get(cls).get(str).refCount = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized String getDiagnostics() {
        StringBuilder sb = new StringBuilder(256);
        ObjectMap.Entries<String, Class> it = this.assetTypes.iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            String str = (String) next.key;
            Class cls = (Class) next.value;
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append(str);
            sb.append(", ");
            sb.append(ClassReflection.getSimpleName(cls));
            sb.append(", refs: ");
            sb.append(this.assets.get(cls).get(str).refCount);
            Array<String> array = this.assetDependencies.get(str);
            if (array != null) {
                sb.append(", deps: [");
                Array.ArrayIterator<String> it2 = array.iterator();
                while (it2.hasNext()) {
                    sb.append(it2.next());
                    sb.append(',');
                }
                sb.append(']');
            }
        }
        return sb.toString();
    }

    public synchronized Array<String> getAssetNames() {
        return this.assetTypes.keys().toArray();
    }

    public synchronized Array<String> getDependencies(String str) {
        return this.assetDependencies.get(str);
    }

    public synchronized Class getAssetType(String str) {
        return this.assetTypes.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/assets/AssetManager$RefCountedContainer.class */
    public static class RefCountedContainer {
        Object object;
        int refCount = 1;

        RefCountedContainer() {
        }
    }
}
