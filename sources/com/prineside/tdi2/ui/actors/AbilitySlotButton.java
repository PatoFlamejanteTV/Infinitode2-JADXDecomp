package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/AbilitySlotButton.class */
public class AbilitySlotButton extends Group {
    private Image k;
    private Image l;
    private Image m;
    private RadialSprite n;
    private Image o;
    private Label p;
    private AbilityType r;
    private int s;
    private int t;
    private boolean v;
    private boolean w;
    private GameValueProvider x;
    private Image[] q = new Image[5];
    private float u = 10.0f;

    public AbilitySlotButton(boolean z, GameValueProvider gameValueProvider) {
        this.v = z;
        this.x = gameValueProvider;
        setTransform(false);
        setSize(106.0f, 115.0f);
        addListener(new InputListener() { // from class: com.prineside.tdi2.ui.actors.AbilitySlotButton.1
            @Override // com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    AbilitySlotButton.this.w = true;
                    AbilitySlotButton.this.update();
                }
                super.enter(inputEvent, f, f2, i, actor);
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    AbilitySlotButton.this.w = false;
                    AbilitySlotButton.this.update();
                }
                super.exit(inputEvent, f, f2, i, actor);
            }
        });
        this.k = new Image();
        addActor(this.k);
        this.n = new RadialSprite(Game.i.assetManager.getTextureRegion("ui-ability-button-edges"));
        this.o = new Image(this.n);
        this.o.setSize(96.0f, 104.0f);
        this.o.setPosition(0.0f, 11.0f);
        this.o.setVisible(false);
        addActor(this.o);
        this.l = new Image();
        this.l.setSize(64.0f, 64.0f);
        this.l.setPosition(16.0f, 30.0f);
        addActor(this.l);
        this.p = new Label("0", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.p.setPosition(82.0f, 12.0f);
        this.p.setSize(8.0f, 15.0f);
        this.p.setAlignment(1);
        addActor(this.p);
        this.m = new Image(Game.i.assetManager.getDrawable("ui-ability-button-selection"));
        this.m.setSize(108.0f, 118.0f);
        this.m.setPosition(-6.0f, 4.0f);
        this.m.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.5f, 0.5f), Actions.alpha(1.0f, 0.5f))));
        addActor(this.m);
        for (int i = 0; i < this.q.length; i++) {
            this.q[i] = new Image(Game.i.assetManager.getDrawable("ui-ability-button-energy-mark"));
            this.q[i].setSize(15.0f, 16.0f);
            this.q[i].setPosition(8.0f + (15.0f * i), 8.0f - i);
            addActor(this.q[i]);
        }
        setAbility(null);
        setSelected(false);
    }

    public void setGameEnergy(float f) {
        this.u = f;
        update();
    }

    public float getGameEnergy() {
        return this.u;
    }

    public void setAbility(AbilityType abilityType) {
        this.r = abilityType;
        update();
    }

    public AbilityType getAbility() {
        return this.r;
    }

    public void setCount(int i) {
        this.s = i;
        update();
    }

    public int getCount() {
        return this.s;
    }

    public int getEnergyCost() {
        return this.t;
    }

    public void update() {
        for (Image image : this.q) {
            image.setVisible(false);
        }
        if (this.r == null) {
            if (this.v) {
                this.k.setDrawable(Game.i.assetManager.getDrawable("ui-ability-button-empty-plus"));
                this.k.setColor(1.0f, 1.0f, 1.0f, 0.14f);
            } else {
                this.k.setDrawable(Game.i.assetManager.getDrawable("ui-ability-button-empty"));
                this.k.setColor(0.0f, 0.0f, 0.0f, 0.14f);
            }
            this.k.setSize(96.0f, 104.0f);
            this.k.setPosition(0.0f, 11.0f);
            this.l.setVisible(false);
            this.p.setVisible(false);
            this.o.setVisible(false);
            return;
        }
        this.o.setColor(Game.i.abilityManager.getFactory(this.r).getDarkerColor());
        this.k.setDrawable(Game.i.assetManager.getDrawable("ui-ability-button"));
        this.t = this.x.getIntValue(Game.i.abilityManager.getEnergyCostGameValueType(this.r));
        if (this.s <= 0) {
            this.l.setColor(MaterialColor.GREY.P700);
            this.k.setColor(MaterialColor.GREY.P900);
            this.o.setVisible(false);
        } else if (this.t <= this.u) {
            this.l.setColor(Color.WHITE);
            if (this.w) {
                this.k.setColor(Game.i.abilityManager.getFactory(this.r).getColor());
            } else {
                this.k.setColor(Game.i.abilityManager.getFactory(this.r).getDarkerColor());
            }
            for (int i = 0; i < this.t; i++) {
                if (i < this.q.length) {
                    this.q[i].setVisible(true);
                    this.q[i].setColor(Color.WHITE);
                }
            }
            this.o.setVisible(false);
        } else {
            this.l.setColor(0.0f, 0.0f, 0.0f, 0.78f);
            this.k.setColor(Game.i.abilityManager.getFactory(this.r).getDarkerColor());
            this.k.getColor().f889a = 0.4f;
            for (int i2 = 0; i2 < this.t; i2++) {
                if (i2 < this.q.length) {
                    this.q[i2].setVisible(true);
                    if (i2 + 1 <= this.u) {
                        this.q[i2].setColor(Color.WHITE);
                    } else {
                        this.q[i2].setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    }
                }
            }
            this.n.setAngle((1.0f - (this.u / this.t)) * 359.99f);
            this.o.setVisible(true);
        }
        this.k.setSize(106.0f, 115.0f);
        this.k.setPosition(0.0f, 0.0f);
        this.l.setDrawable(Game.i.abilityManager.getFactory(this.r).getIconDrawable());
        this.l.setVisible(true);
        this.p.setTextFromInt(this.s);
        this.p.setVisible(true);
    }

    public void setSelected(boolean z) {
        this.m.setVisible(z);
    }

    public boolean isSelected() {
        return this.m.isVisible();
    }
}
