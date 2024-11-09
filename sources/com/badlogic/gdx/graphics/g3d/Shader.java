package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/Shader.class */
public interface Shader extends Disposable {
    void init();

    int compareTo(Shader shader);

    boolean canRender(Renderable renderable);

    void begin(Camera camera, RenderContext renderContext);

    void render(Renderable renderable);

    void end();
}
