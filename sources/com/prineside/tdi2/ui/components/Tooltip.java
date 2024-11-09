package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/Tooltip.class */
public class Tooltip implements Disposable {

    /* renamed from: b, reason: collision with root package name */
    private final Label f3426b;

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3425a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 106, "Tooltip");
    private final Group c = new Group();

    public Tooltip() {
        this.c.setOrigin(240.0f, 48.0f);
        this.f3425a.getTable().add((Table) this.c).size(480.0f, 96.0f).expand().top().padTop(175.0f);
        this.c.addActor(new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.56f), new float[]{16.0f, 0.0f, 0.0f, 96.0f, 480.0f, 96.0f, 464.0f, 0.0f}));
        this.f3426b = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.f3426b.setSize(440.0f, 96.0f);
        this.f3426b.setAlignment(1);
        this.f3426b.setWrap(true);
        this.c.addActor(this.f3426b);
        this.c.addAction(Actions.alpha(0.0f));
        this.c.setVisible(false);
    }

    public void show(CharSequence charSequence) {
        if (charSequence.length() > 30) {
            this.f3426b.setStyle(Game.i.assetManager.getLabelStyle(24));
        } else {
            this.f3426b.setStyle(Game.i.assetManager.getLabelStyle(30));
        }
        this.f3426b.setText(charSequence);
        this.c.clearActions();
        this.c.addAction(Actions.sequence(Actions.show(), Actions.parallel(Actions.sequence(Actions.scaleTo(1.1f, 1.1f, 0.1f), Actions.scaleTo(1.0f, 1.0f, 0.1f)), Actions.alpha(1.0f, 0.3f)), Actions.delay(3.5f), Actions.alpha(0.0f, 0.3f), Actions.hide()));
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3425a);
    }
}
