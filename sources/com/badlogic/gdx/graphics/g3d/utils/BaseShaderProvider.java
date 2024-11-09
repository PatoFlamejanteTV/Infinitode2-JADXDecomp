package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/BaseShaderProvider.class */
public abstract class BaseShaderProvider implements ShaderProvider {
    protected Array<Shader> shaders = new Array<>();

    protected abstract Shader createShader(Renderable renderable);

    @Override // com.badlogic.gdx.graphics.g3d.utils.ShaderProvider
    public Shader getShader(Renderable renderable) {
        Shader shader = renderable.shader;
        if (shader != null && shader.canRender(renderable)) {
            return shader;
        }
        Array.ArrayIterator<Shader> it = this.shaders.iterator();
        while (it.hasNext()) {
            Shader next = it.next();
            if (next.canRender(renderable)) {
                return next;
            }
        }
        Shader createShader = createShader(renderable);
        if (!createShader.canRender(renderable)) {
            throw new GdxRuntimeException("unable to provide a shader for this renderable");
        }
        createShader.init();
        this.shaders.add(createShader);
        return createShader;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Array.ArrayIterator<Shader> it = this.shaders.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        this.shaders.clear();
    }
}
