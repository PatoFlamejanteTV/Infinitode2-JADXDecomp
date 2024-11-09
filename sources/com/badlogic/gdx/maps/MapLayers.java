package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/maps/MapLayers.class */
public class MapLayers implements Iterable<MapLayer> {
    private Array<MapLayer> layers = new Array<>();

    public MapLayer get(int i) {
        return this.layers.get(i);
    }

    public MapLayer get(String str) {
        int i = this.layers.size;
        for (int i2 = 0; i2 < i; i2++) {
            MapLayer mapLayer = this.layers.get(i2);
            if (str.equals(mapLayer.getName())) {
                return mapLayer;
            }
        }
        return null;
    }

    public int getIndex(String str) {
        return getIndex(get(str));
    }

    public int getIndex(MapLayer mapLayer) {
        return this.layers.indexOf(mapLayer, true);
    }

    public int getCount() {
        return this.layers.size;
    }

    public void add(MapLayer mapLayer) {
        this.layers.add(mapLayer);
    }

    public void remove(int i) {
        this.layers.removeIndex(i);
    }

    public void remove(MapLayer mapLayer) {
        this.layers.removeValue(mapLayer, true);
    }

    public int size() {
        return this.layers.size;
    }

    public <T extends MapLayer> Array<T> getByType(Class<T> cls) {
        return getByType(cls, new Array<>());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T extends MapLayer> Array<T> getByType(Class<T> cls, Array<T> array) {
        array.clear();
        int i = this.layers.size;
        for (int i2 = 0; i2 < i; i2++) {
            MapLayer mapLayer = this.layers.get(i2);
            if (ClassReflection.isInstance(cls, mapLayer)) {
                array.add(mapLayer);
            }
        }
        return array;
    }

    @Override // java.lang.Iterable
    public Iterator<MapLayer> iterator() {
        return this.layers.iterator();
    }
}
