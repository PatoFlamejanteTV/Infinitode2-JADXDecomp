package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.scene2d.ui.Widget;
import com.prineside.tdi2.shapes.TrailMultiLine;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/TrailMultilineActor.class */
public class TrailMultilineActor extends Widget implements Disposable {
    public final TrailMultiLine trail = (TrailMultiLine) Game.i.shapeManager.getFactory(ShapeType.TRAIL_MULTI_LINE).obtain();
    private long j;
    private boolean k;
    private boolean l;

    public TrailMultilineActor() {
        setSize(1.0f, 1.0f);
    }

    public void setup(Color color, float f, float f2, float f3) {
        this.trail.setup(color, f, f2, f3);
        this.k = true;
        this.l = false;
    }

    public void stop() {
        this.l = false;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        super.draw(batch, f);
        if (this.k) {
            float realTickCount = ((float) (Game.getRealTickCount() - this.j)) / 1000000.0f;
            float f2 = realTickCount;
            if (realTickCount > 0.5f) {
                f2 = 0.5f;
            }
            this.j = Game.getRealTickCount();
            if (this.l) {
                this.trail.setHeadPosition(getX(), getY());
                this.trail.update(f2);
                this.trail.draw(batch);
            } else if (getX() != 0.0f && getY() != 0.0f) {
                this.trail.setStartPoint(getX(), getY());
                this.l = true;
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        ((TrailMultiLine.TrailMultiLineFactory) Game.i.shapeManager.getFactory(ShapeType.TRAIL_MULTI_LINE)).free(this.trail);
    }
}
