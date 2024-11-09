package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ScreenTitle.class */
public final class ScreenTitle extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3744a;

    /* renamed from: b, reason: collision with root package name */
    private final Image f3745b;
    private final Label c;

    public static ScreenTitle i() {
        return (ScreenTitle) Game.i.uiManager.getComponent(ScreenTitle.class);
    }

    public ScreenTitle() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE);
        this.f3744a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 100, "ScreenTitle");
        Table table = this.f3744a.getTable();
        Group group = new Group();
        group.setTouchable(Touchable.disabled);
        table.add((Table) group).expand().top().left().size(300.0f, 64.0f).padTop(48.0f).padLeft(40.0f);
        this.f3745b = new Image();
        this.f3745b.setSize(64.0f, 64.0f);
        group.addActor(this.f3745b);
        this.c = new Label("", labelStyle);
        this.c.setSize(204.0f, 64.0f);
        this.c.setPosition(96.0f, 0.0f);
        group.addActor(this.c);
        setVisible(false);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        setVisible(false);
    }

    public final ScreenTitle setVisible(boolean z) {
        this.f3744a.getTable().setVisible(z);
        return this;
    }

    public final ScreenTitle setText(CharSequence charSequence) {
        this.c.setText(charSequence);
        return this;
    }

    public final ScreenTitle setIcon(Drawable drawable) {
        this.f3745b.setDrawable(drawable);
        return this;
    }
}
