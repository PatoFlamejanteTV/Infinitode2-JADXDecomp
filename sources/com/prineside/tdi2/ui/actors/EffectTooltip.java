package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/EffectTooltip.class */
public class EffectTooltip extends Table {
    private final Image k = new Image();
    private final Label l;
    private final Label n;

    public EffectTooltip(Drawable drawable, CharSequence charSequence) {
        add((EffectTooltip) this.k).size(64.0f, 64.0f);
        Table table = new Table();
        add((EffectTooltip) table).padLeft(8.0f);
        this.l = new Label("Title", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.l.setAlignment(8);
        table.add((Table) this.l).fillX().row();
        this.n = new Label("Hint", new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.n.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.n.setVisible(false);
        this.n.setAlignment(8);
        table.add((Table) this.n).fillX();
        setIcon(drawable);
        setTitle(charSequence);
    }

    public EffectTooltip setIcon(Drawable drawable) {
        this.k.setDrawable(drawable);
        return this;
    }

    public EffectTooltip setTitle(CharSequence charSequence) {
        this.l.setText(charSequence);
        return this;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void setColor(Color color) {
        super.setColor(color);
        this.k.setColor(color);
        this.l.setColor(color);
        this.n.setColor(color.r, color.g, color.f888b, color.f889a * 0.56f);
    }

    public EffectTooltip setHint(CharSequence charSequence) {
        if (charSequence == null) {
            this.n.setVisible(false);
        } else {
            this.n.setText(charSequence);
            this.n.setVisible(true);
        }
        return this;
    }
}
