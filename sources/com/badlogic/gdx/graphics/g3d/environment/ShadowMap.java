package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.math.Matrix4;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/environment/ShadowMap.class */
public interface ShadowMap {
    Matrix4 getProjViewTrans();

    TextureDescriptor getDepthMap();
}
