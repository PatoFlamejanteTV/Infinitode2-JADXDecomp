package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ExpLine.class */
public class ExpLine extends Group {
    private final Image k;
    private final Image l;

    public ExpLine() {
        setTransform(false);
        setTouchable(Touchable.disabled);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(356.0f, 24.0f);
        image.setColor(new Color(0.0f, 0.0f, 0.0f, 0.28f));
        addActor(image);
        Image image2 = new Image(Game.i.assetManager.getDrawable("ui-exp-line-end"));
        image2.setSize(8.0f, 24.0f);
        image2.setColor(new Color(0.0f, 0.0f, 0.0f, 0.28f));
        image2.setPosition(356.0f, 0.0f);
        addActor(image2);
        this.k = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.k.setSize(356.0f, 24.0f);
        this.k.setColor(MaterialColor.AMBER.P700);
        addActor(this.k);
        this.l = new Image(Game.i.assetManager.getDrawable("ui-exp-line-end"));
        this.l.setSize(8.0f, 24.0f);
        this.l.setColor(MaterialColor.AMBER.P700);
        this.l.setPosition(356.0f, 0.0f);
        addActor(this.l);
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void setColor(Color color) {
        this.k.setColor(color);
        this.l.setColor(color);
    }

    public void setCoeff(float f) {
        float clamp = 356.0f * MathUtils.clamp(f, 0.0f, 1.0f);
        this.k.setWidth(clamp);
        this.l.setX(clamp);
    }
}
