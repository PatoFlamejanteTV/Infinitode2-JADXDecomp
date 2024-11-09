package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/AttentionRaysUnderlay.class */
public class AttentionRaysUnderlay extends Group {
    private Image k;
    private Image[] l;
    private Color m;
    public float size;

    public AttentionRaysUnderlay(float f, Color color) {
        this.size = f;
        this.m = color;
        setTransform(false);
        setTouchable(Touchable.disabled);
        this.k = new Image(Game.i.assetManager.getTextureRegion("attention-rays-0"));
        this.l = new Image[]{this.k, new Image(Game.i.assetManager.getTextureRegion("attention-rays-1")), new Image(Game.i.assetManager.getTextureRegion("attention-rays-2")), new Image(Game.i.assetManager.getTextureRegion("attention-rays-3"))};
        restart();
    }

    public void restart() {
        Color cpy = this.m.cpy();
        cpy.f889a = 0.0f;
        setSize(this.size, this.size);
        int i = 0;
        for (Image image : this.l) {
            image.clearActions();
            image.setSize(this.size, this.size);
            image.setOrigin(this.size * 0.5f, this.size * 0.5f);
            image.setColor(cpy);
            if (image != this.k) {
                image.addAction(Actions.sequence(Actions.delay(i), Actions.forever(Actions.parallel(Actions.rotateBy(42.0f + (i * 3.0f), 3.0f), Actions.sequence(Actions.color(this.m, 1.5f), Actions.color(cpy, 1.5f))))));
                i++;
            }
            addActor(image);
        }
        this.k.setColor(cpy);
        this.k.addAction(Actions.parallel(Actions.color(this.m, 1.5f), Actions.forever(Actions.rotateBy(45.0f, 3.0f))));
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void setColor(Color color) {
        this.m = color;
        restart();
    }
}
