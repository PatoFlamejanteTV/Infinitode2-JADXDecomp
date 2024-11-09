package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Sort;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/decals/SimpleOrthoGroupStrategy.class */
public class SimpleOrthoGroupStrategy implements GroupStrategy {
    private Comparator comparator = new Comparator();
    private static final int GROUP_OPAQUE = 0;
    private static final int GROUP_BLEND = 1;

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public int decideGroup(Decal decal) {
        return decal.getMaterial().isOpaque() ? 0 : 1;
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroup(int i, Array<Decal> array) {
        if (i == 1) {
            Sort.instance().sort(array, this.comparator);
            Gdx.gl.glEnable(3042);
            Gdx.gl.glDepthMask(false);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroup(int i) {
        if (i == 1) {
            Gdx.gl.glDepthMask(true);
            Gdx.gl.glDisable(3042);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroups() {
        Gdx.gl.glEnable(3553);
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroups() {
        Gdx.gl.glDisable(3553);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/decals/SimpleOrthoGroupStrategy$Comparator.class */
    class Comparator implements java.util.Comparator<Decal> {
        Comparator() {
        }

        @Override // java.util.Comparator
        public int compare(Decal decal, Decal decal2) {
            if (decal.getZ() == decal2.getZ()) {
                return 0;
            }
            return decal.getZ() - decal2.getZ() < 0.0f ? -1 : 1;
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public ShaderProgram getGroupShader(int i) {
        return null;
    }
}
