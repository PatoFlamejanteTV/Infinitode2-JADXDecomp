package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.prineside.tdi2.scene2d.Group;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/GroupClipping.class */
public final class GroupClipping extends Group {
    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public final void draw(Batch batch, float f) {
        batch.flush();
        if (clipBegin(getX(), getY(), getWidth(), getHeight())) {
            a(batch, f);
            batch.flush();
            clipEnd();
        }
    }
}
