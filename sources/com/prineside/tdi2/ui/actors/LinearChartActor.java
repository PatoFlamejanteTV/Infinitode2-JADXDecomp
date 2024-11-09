package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.InterpolationType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.shapes.MultiLine;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/LinearChartActor.class */
public class LinearChartActor extends Actor {
    private MultiLine j;
    private float k = Color.WHITE.toFloatBits();
    private Color l = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    private FloatArray m = new FloatArray();
    private float n = Float.NaN;
    private float o;
    private float p;
    private float q;
    private static Vector2 r = new Vector2();
    private TextureRegion s;

    @Override // com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        if (this.s == null) {
            return;
        }
        batch.setColor(this.l);
        batch.draw(this.s, getX(), getY(), getWidth(), getHeight());
        batch.setColor(Color.WHITE);
        if (this.j != null) {
            this.j.draw(batch);
        }
        batch.setColor(Color.WHITE);
    }

    public void setColor(Color color, Color color2) {
        this.n = Float.NaN;
        this.k = color.toFloatBits();
        this.l.set(color2);
    }

    public void setChart(FloatArray floatArray) {
        this.m.clear();
        this.m.addAll(floatArray);
        this.n = Float.NaN;
    }

    public void setChartFromInterpolation(InterpolationType interpolationType) {
        this.m.clear();
        Interpolation object = InterpolationType.getObject(interpolationType);
        double d = 0.0d;
        while (true) {
            double d2 = d;
            if (d2 <= 1.0d) {
                this.m.add(object.apply((float) d2));
                d = d2 + 0.05d;
            } else {
                this.n = Float.NaN;
                return;
            }
        }
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void act(float f) {
        super.act(f);
        if (!isVisible() || this.m.size < 2) {
            return;
        }
        if (this.s == null) {
            this.s = Game.i.assetManager.getTextureRegion(AssetManager.BLANK_REGION_NAME);
        }
        float width = getWidth();
        float height = getHeight();
        r.setZero();
        localToStageCoordinates(r);
        if (r.x != this.n || r.y != this.o || width != this.p || height != this.q || this.j == null) {
            if (this.j == null) {
                this.j = (MultiLine) Game.i.shapeManager.getFactory(ShapeType.MULTI_LINE).obtain();
            }
            this.j.reset();
            this.j.setTextureRegion(this.s, false, false);
            float f2 = width / (this.m.size - 1);
            for (int i = 0; i < this.m.size; i++) {
                this.j.appendNode(r.x + (i * f2), r.y + (this.m.items[i] * height), 2.0f, this.k, false);
            }
            this.j.updateAllNodes();
            this.n = r.x;
            this.o = r.y;
            this.p = width;
            this.q = height;
        }
    }
}
