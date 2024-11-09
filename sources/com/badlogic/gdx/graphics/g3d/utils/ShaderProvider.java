package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/ShaderProvider.class */
public interface ShaderProvider extends Disposable {
    Shader getShader(Renderable renderable);
}
