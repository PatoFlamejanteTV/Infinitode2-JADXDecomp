package com.prineside.tdi2;

import com.badlogic.gdx.graphics.g2d.Batch;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ProjectileTrail.class */
public interface ProjectileTrail {
    void update(float f);

    void draw(Batch batch);

    boolean isFinished();

    void free();
}
