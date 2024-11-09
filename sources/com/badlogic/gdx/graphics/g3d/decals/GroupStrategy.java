package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/decals/GroupStrategy.class */
public interface GroupStrategy {
    ShaderProgram getGroupShader(int i);

    int decideGroup(Decal decal);

    void beforeGroup(int i, Array<Decal> array);

    void afterGroup(int i);

    void beforeGroups();

    void afterGroups();
}
