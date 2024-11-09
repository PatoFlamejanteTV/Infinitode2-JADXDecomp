package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Widget;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/HighlightActor.class */
public class HighlightActor extends Widget {
    private float t;
    private Actor u;
    private static final Vector2 v = new Vector2();
    private TextureRegion j = Game.i.assetManager.getTextureRegion(AssetManager.BLANK_REGION_NAME);
    private TextureRegion k = Game.i.assetManager.getTextureRegion("gradient-corner-bottom-left");
    private TextureRegion l = Game.i.assetManager.getTextureRegion("gradient-corner-bottom-right");
    private TextureRegion m = Game.i.assetManager.getTextureRegion("gradient-corner-top-right");
    private TextureRegion n = Game.i.assetManager.getTextureRegion("gradient-corner-top-left");
    private TextureRegion q = Game.i.assetManager.getTextureRegion("gradient-top");
    private TextureRegion o = Game.i.assetManager.getTextureRegion("gradient-left");
    private TextureRegion p = Game.i.assetManager.getTextureRegion("gradient-right");
    private TextureRegion r = Game.i.assetManager.getTextureRegion("gradient-bottom");
    private long s = Game.getRealTickCount();

    public HighlightActor(Actor actor) {
        this.u = actor;
        setColor(new Color(1338242986));
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        boolean z = true;
        Actor actor = this.u;
        while (true) {
            Actor actor2 = actor;
            if (actor2 == null) {
                break;
            }
            if (!actor2.isVisible()) {
                z = false;
                break;
            } else if (actor2.hasParent()) {
                actor = actor2.getParent();
            } else {
                actor = null;
            }
        }
        if (z) {
            v.setZero();
            this.u.localToStageCoordinates(v);
            float f2 = v.x;
            float f3 = v.y;
            float width = this.u.getWidth();
            float height = this.u.getHeight();
            this.t += ((float) (Game.getRealTickCount() - this.s)) / 1000000.0f;
            this.s = Game.getRealTickCount();
            float apply = Interpolation.sineOut.apply(((this.t * 3.0f) % 3.0f) / 3.0f);
            float f4 = 1.0f - apply;
            Color color = getColor();
            batch.setColor(0.0f, 0.0f, 0.0f, 0.28f * color.f889a * f);
            batch.draw(this.p, (f2 - 256.0f) - 4.0f, f3 - 4.0f, 256.0f, height + 8.0f);
            batch.draw(this.o, f2 + width + 4.0f, f3 - 4.0f, 256.0f, height + 8.0f);
            batch.draw(this.q, f2 - 4.0f, (f3 - 256.0f) - 4.0f, width + 8.0f, 256.0f);
            batch.draw(this.r, f2 - 4.0f, f3 + height + 4.0f, width + 8.0f, 256.0f);
            batch.draw(this.n, (f2 - 256.0f) - 4.0f, f3 + height + 4.0f, 256.0f, 256.0f);
            batch.draw(this.m, f2 + width + 4.0f, f3 + height + 4.0f, 256.0f, 256.0f);
            batch.draw(this.k, (f2 - 256.0f) - 4.0f, (f3 - 256.0f) - 4.0f, 256.0f, 256.0f);
            batch.draw(this.l, f2 + width + 4.0f, (f3 - 256.0f) - 4.0f, 256.0f, 256.0f);
            batch.setColor(color.r, color.g, color.f888b, color.f889a * f * 0.5f * f4);
            float f5 = 18.0f * apply;
            batch.draw(this.j, (f2 - f5) - 4.0f, f3 - 4.0f, f5, height + 8.0f);
            batch.draw(this.j, f2 + width + 4.0f, f3 - 4.0f, f5, height + 8.0f);
            batch.draw(this.j, (f2 - f5) - 4.0f, (f3 - f5) - 4.0f, width + (f5 * 2.0f) + 8.0f, f5);
            batch.draw(this.j, (f2 - f5) - 4.0f, f3 + height + 4.0f, width + (f5 * 2.0f) + 8.0f, f5);
            float f6 = 40.0f * apply;
            batch.draw(this.j, (f2 - f6) - 4.0f, f3 - 4.0f, f6, height + 8.0f);
            batch.draw(this.j, f2 + width + 4.0f, f3 - 4.0f, f6, height + 8.0f);
            batch.draw(this.j, (f2 - f6) - 4.0f, (f3 - f6) - 4.0f, width + (f6 * 2.0f) + 8.0f, f6);
            batch.draw(this.j, (f2 - f6) - 4.0f, f3 + height + 4.0f, width + (f6 * 2.0f) + 8.0f, f6);
            batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
            batch.draw(this.j, (f2 - 4.0f) - 4.0f, f3 - 4.0f, 4.0f, height + 8.0f);
            batch.draw(this.j, f2 + width + 4.0f, f3 - 4.0f, 4.0f, height + 8.0f);
            batch.draw(this.j, (f2 - 4.0f) - 4.0f, (f3 - 4.0f) - 4.0f, width + 8.0f + 8.0f, 4.0f);
            batch.draw(this.j, (f2 - 4.0f) - 4.0f, f3 + height + 4.0f, width + 8.0f + 8.0f, 4.0f);
        }
    }
}
