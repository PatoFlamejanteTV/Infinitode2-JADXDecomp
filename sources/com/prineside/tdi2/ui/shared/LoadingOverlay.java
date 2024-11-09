package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/LoadingOverlay.class */
public class LoadingOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3687a = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.OVERLAY, 150, "LoadingOverlay overlay", true);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3688b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 151, "LoadingOverlay main");
    private static final StringBuffer c = new StringBuffer();
    private final Label d;
    private final Label e;
    private final Image f;
    private final Image g;
    private final Image h;
    private boolean i;

    public static LoadingOverlay i() {
        return (LoadingOverlay) Game.i.uiManager.getComponent(LoadingOverlay.class);
    }

    public LoadingOverlay() {
        Image image = new Image(Game.i.assetManager.getBlankWhiteTextureRegion());
        image.setColor(Config.BACKGROUND_COLOR);
        image.setTouchable(Touchable.enabled);
        this.f3687a.getTable().add((Table) image).expand().fill();
        Group group = new Group();
        this.f3688b.getTable().add((Table) group).expand().bottom().left().size(320.0f, 64.0f).padLeft(40.0f).padBottom(40.0f);
        this.f = new Image(Game.i.assetManager.getDrawable("loading-icon"));
        this.f.setOrigin(1);
        group.addActor(this.f);
        this.g = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.g.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        this.g.setPosition(0.0f, -16.0f);
        this.g.setSize(320.0f, 5.0f);
        group.addActor(this.g);
        this.h = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.h.setColor(MaterialColor.CYAN.P500);
        this.h.setPosition(0.0f, -16.0f);
        group.addActor(this.h);
        this.d = new Label("Loading...", Game.i.assetManager.getLabelStyle(30));
        this.d.setPosition(80.0f, 8.0f);
        this.d.setSize(200.0f, 64.0f);
        group.addActor(this.d);
        this.e = new Label("Tip", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.e.setPosition(80.0f, -24.0f);
        this.e.setSize(200.0f, 64.0f);
        this.e.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        group.addActor(this.e);
        this.i = true;
        hide();
    }

    public void show() {
        if (!this.i) {
            c.setLength(0);
            c.append(Game.i.localeManager.i18n.get("loading")).append("...");
            this.d.setText(c);
            this.f.clearActions();
            this.f.addAction(Actions.forever(Actions.rotateBy(90.0f, 1.0f)));
            this.f3687a.getTable().setVisible(true);
            this.f3688b.getTable().setVisible(true);
            this.i = true;
        }
    }

    public void showWithBar(float f, CharSequence charSequence) {
        show();
        this.g.setVisible(true);
        this.h.setVisible(true);
        this.h.setSize(320.0f * f, 5.0f);
        this.e.setVisible(true);
        this.e.setText(charSequence);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        if (this.i) {
            this.f3687a.getTable().setVisible(false);
            this.f3688b.getTable().setVisible(false);
            this.g.setVisible(false);
            this.h.setVisible(false);
            this.e.setVisible(false);
            this.i = false;
        }
    }
}
