package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/RenderableSorter.class */
public interface RenderableSorter {
    void sort(Camera camera, Array<Renderable> array);
}
