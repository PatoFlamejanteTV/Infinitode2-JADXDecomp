package com.prineside.tdi2.ui.actors;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/HorizontalSlider.class */
public class HorizontalSlider extends Group {
    public static final float HEIGHT = 48.0f;
    public static final float HANDLE_WIDTH = 56.0f;
    private ObjectConsumer<Double> k;
    private Group l;
    private Image m;
    private double n;
    private double o;
    private double p;
    private boolean q;

    public HorizontalSlider(final float f, double d, final double d2, final double d3, ObjectConsumer<Double> objectConsumer) {
        setTransform(false);
        setSize(f, 48.0f);
        this.k = objectConsumer;
        this.n = d;
        this.o = d2;
        this.p = d3;
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(f - 16.0f, 10.0f);
        image.setColor(1.0f, 1.0f, 1.0f, 0.14f);
        image.setPosition(8.0f, 19.0f);
        addActor(image);
        this.l = new Group();
        this.l.setTransform(false);
        this.l.setSize(56.0f, 64.0f);
        this.l.addListener(new InputListener() { // from class: com.prineside.tdi2.ui.actors.HorizontalSlider.1

            /* renamed from: a, reason: collision with root package name */
            private float f3217a;

            /* renamed from: b, reason: collision with root package name */
            private double f3218b;
            private double c;
            private float d;

            {
                this.f3217a = f - 56.0f;
                this.f3218b = (d3 - d2) / this.f3217a;
            }

            private double a(float f2) {
                double d4 = this.c + ((f2 - this.d) * this.f3218b);
                double d5 = d4;
                if (d4 < d2) {
                    d5 = d2;
                } else if (d5 > d3) {
                    d5 = d3;
                }
                return d5;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                inputEvent.cancel();
                this.d = inputEvent.getStageX();
                this.c = HorizontalSlider.this.n;
                HorizontalSlider.this.m.setColor(MaterialColor.LIGHT_BLUE.P700);
                return true;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                HorizontalSlider.this.setValue(a(inputEvent.getStageX()), true);
                HorizontalSlider.this.m.setColor(MaterialColor.LIGHT_BLUE.P600);
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f2, float f3, int i) {
                HorizontalSlider.this.setValue(a(inputEvent.getStageX()), HorizontalSlider.this.q);
            }
        });
        addActor(this.l);
        this.m = new Image(Game.i.assetManager.getDrawable("ui-horizontal-slider-handle"));
        this.m.setSize(32.0f, 48.0f);
        this.m.setPosition(12.0f, 8.0f);
        this.m.setColor(MaterialColor.LIGHT_BLUE.P600);
        this.l.addActor(this.m);
        addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.actors.HorizontalSlider.2
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f2, float f3) {
                double width = f2 / HorizontalSlider.this.getWidth();
                double d4 = width;
                if (width < 0.0d) {
                    d4 = 0.0d;
                } else if (d4 > 1.0d) {
                    d4 = 1.0d;
                }
                HorizontalSlider.this.setValue(d2 + ((d3 - d2) * d4), true);
            }
        });
        setValue(d, false);
    }

    public void setNotifyOnDrag(boolean z) {
        this.q = z;
    }

    public void setValue(double d, boolean z) {
        if (d < this.o) {
            d = this.o;
        }
        if (d > this.p) {
            d = this.p;
        }
        this.n = d;
        this.l.setPosition(((float) ((d - this.o) / (this.p - this.o))) * (getWidth() - 56.0f), -8.0f);
        if (this.k != null && z) {
            this.k.accept(Double.valueOf(d));
        }
    }

    public double getValue() {
        return this.n;
    }
}
