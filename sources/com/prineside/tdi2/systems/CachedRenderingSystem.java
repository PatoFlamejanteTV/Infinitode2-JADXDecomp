package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.SpriteCacheExtended;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/CachedRenderingSystem.class */
public final class CachedRenderingSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private final ObjectMap<String, SpriteCacheExtended.CacheArray> f2933a = new ObjectMap<>();

    public final void removeLayer(String str) {
        SpriteCacheExtended.CacheArray layer = getLayer(str);
        if (layer != null) {
            layer.dispose();
            this.f2933a.remove(str);
        }
    }

    public final void setLayerDirty(String str) {
        SpriteCacheExtended.CacheArray cacheArray = this.f2933a.get(str);
        if (cacheArray != null) {
            cacheArray.dirty = true;
        }
    }

    public final boolean isDirty(String str) {
        SpriteCacheExtended.CacheArray cacheArray = this.f2933a.get(str);
        if (cacheArray != null) {
            return cacheArray.dirty;
        }
        return true;
    }

    @Null
    public final SpriteCacheExtended.CacheArray getLayer(String str) {
        return this.f2933a.get(str);
    }

    public final SpriteCacheExtended.CacheArray getOrAddLayer(String str, int i, ShaderProgram shaderProgram, boolean z) {
        SpriteCacheExtended.CacheArray cacheArray = this.f2933a.get(str);
        if (cacheArray == null) {
            return addLayer(str, i, shaderProgram, z);
        }
        if (cacheArray.getSizePerCache() < i || shaderProgram != cacheArray.getShaderProgram() || z != cacheArray.isUseIndices()) {
            removeLayer(str);
            return addLayer(str, i, shaderProgram, z);
        }
        return cacheArray;
    }

    public final SpriteCacheExtended.CacheArray addLayer(String str, int i, ShaderProgram shaderProgram, boolean z) {
        if (this.f2933a.containsKey(str)) {
            throw new IllegalStateException("Layer " + str + " already exists");
        }
        SpriteCacheExtended.CacheArray cacheArray = new SpriteCacheExtended.CacheArray(str, i, shaderProgram, z);
        this.f2933a.put(str, cacheArray);
        return cacheArray;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "CachedRendering";
    }
}
