package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.InputVoid;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/PanZoomTooltip.class */
public class PanZoomTooltip implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3227a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 150, "PanZoomTooltip");

    /* renamed from: b, reason: collision with root package name */
    private final FingerActor f3228b;
    private final FingerActor c;
    private final FingerActor d;
    private Label e;
    private Label f;
    private Group g;

    public PanZoomTooltip() {
        Group group = new Group();
        group.setTransform(false);
        this.f3227a.getTable().add((Table) group).size(800.0f, 400.0f);
        this.f3227a.getTable().setBackground(Game.i.assetManager.getOverlayBackground());
        this.f3227a.getTable().setTouchable(Touchable.enabled);
        this.f3227a.getTable().addListener(new InputVoid());
        this.f3227a.getTable().addListener(new InputListener() { // from class: com.prineside.tdi2.ui.actors.PanZoomTooltip.1
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                PanZoomTooltip.this.hide();
                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                return true;
            }
        });
        this.f3228b = new FingerActor(this, (byte) 0);
        group.addActor(this.f3228b);
        this.e = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.e.setPosition(0.0f, 0.0f);
        this.e.setSize(400.0f, 50.0f);
        this.e.setAlignment(1);
        group.addActor(this.e);
        this.c = new FingerActor(this, (byte) 0);
        group.addActor(this.c);
        this.d = new FingerActor(this, (byte) 0);
        group.addActor(this.d);
        this.f = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.f.setPosition(400.0f, 0.0f);
        this.f.setSize(400.0f, 50.0f);
        this.f.setAlignment(1);
        group.addActor(this.f);
        this.g = new Group();
        this.g.setTransform(false);
        this.g.setVisible(false);
        this.g.setSize(400.0f, 300.0f);
        this.g.setPosition(400.0f, 100.0f);
        group.addActor(this.g);
        Image image = new Image(Game.i.assetManager.getDrawable("icon-mouse-wheel"));
        image.setSize(96.0f, 96.0f);
        image.setPosition(152.0f, 82.0f);
        image.setColor(MaterialColor.LIGHT_BLUE.P300);
        this.g.addActor(image);
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-triangle-top-hollow"));
        image2.setSize(64.0f, 64.0f);
        image2.setPosition(168.0f, 178.0f);
        image2.addAction(Actions.forever(Actions.sequence(Actions.moveTo(168.0f, 226.0f, 1.25f, Interpolation.pow2), Actions.fadeOut(0.3f), Actions.moveTo(168.0f, 178.0f), Actions.fadeIn(0.3f))));
        image2.setColor(MaterialColor.LIGHT_BLUE.P500);
        this.g.addActor(image2);
        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-triangle-bottom-hollow"));
        image3.setSize(64.0f, 64.0f);
        image3.setPosition(168.0f, 18.0f);
        image3.setColor(MaterialColor.LIGHT_BLUE.P500);
        image3.addAction(Actions.forever(Actions.sequence(Actions.moveTo(168.0f, -30.0f, 1.25f, Interpolation.pow2), Actions.fadeOut(0.3f), Actions.moveTo(168.0f, 18.0f), Actions.fadeIn(0.3f))));
        this.g.addActor(image3);
        this.f3227a.getTable().setVisible(false);
        hide();
    }

    public void show() {
        this.e.setText(Game.i.localeManager.i18n.get("screen_pan"));
        this.f.setText(Game.i.localeManager.i18n.get("screen_zoom"));
        this.f3228b.clearActions();
        this.f3228b.addAction(Actions.forever(Actions.sequence(Actions.run(() -> {
            this.f3228b.k.stop();
            this.f3228b.setPosition(50.0f, 100.0f);
        }), Actions.alpha(1.0f, 0.1f), Actions.moveTo(350.0f, 350.0f, 1.25f, Interpolation.pow2), Actions.alpha(0.0f, 0.5f), Actions.delay(0.3f))));
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            this.c.setVisible(false);
            this.d.setVisible(false);
            this.g.setVisible(true);
        } else {
            this.c.clearActions();
            this.c.setPosition(630.0f, 250.0f);
            this.c.addAction(Actions.forever(Actions.sequence(Actions.run(() -> {
            }), Actions.moveTo(750.0f, 350.0f, 1.25f, Interpolation.pow2), Actions.alpha(0.0f, 0.5f), Actions.delay(0.3f), Actions.alpha(1.0f, 0.1f), Actions.run(() -> {
            }), Actions.moveTo(630.0f, 250.0f, 1.25f, Interpolation.pow2), Actions.alpha(0.0f, 0.5f), Actions.delay(0.3f), Actions.alpha(1.0f, 0.1f))));
            this.d.clearActions();
            this.d.setPosition(570.0f, 200.0f);
            this.d.addAction(Actions.forever(Actions.sequence(Actions.run(() -> {
            }), Actions.moveTo(450.0f, 100.0f, 1.25f, Interpolation.pow2), Actions.alpha(0.0f, 0.5f), Actions.delay(0.3f), Actions.alpha(1.0f, 0.1f), Actions.run(() -> {
            }), Actions.moveTo(570.0f, 200.0f, 1.25f, Interpolation.pow2), Actions.alpha(0.0f, 0.5f), Actions.delay(0.3f), Actions.alpha(1.0f, 0.1f))));
        }
        this.f3227a.getTable().setVisible(true);
        this.f3227a.getTable().clearActions();
        this.f3227a.getTable().addAction(Actions.alpha(1.0f, 0.3f));
    }

    public void hide() {
        this.f3228b.clearActions();
        this.c.clearActions();
        this.d.clearActions();
        this.f3227a.getTable().clearActions();
        this.f3227a.getTable().addAction(Actions.sequence(Actions.alpha(0.0f, 0.3f), Actions.run(() -> {
            this.f3227a.getTable().setVisible(false);
        })));
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3227a);
        this.f3228b.dispose();
        this.c.dispose();
        this.d.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/PanZoomTooltip$FingerActor.class */
    public class FingerActor extends Group implements Disposable {
        private TrailMultilineActor k;

        /* synthetic */ FingerActor(PanZoomTooltip panZoomTooltip, byte b2) {
            this(panZoomTooltip);
        }

        private FingerActor(PanZoomTooltip panZoomTooltip) {
            setTransform(false);
            setSize(1.0f, 1.0f);
            this.k = new TrailMultilineActor();
            this.k.setup(MaterialColor.LIGHT_BLUE.P500, 32.0f, 0.5f, 0.0f);
            addActor(this.k);
            Image image = new Image(Game.i.assetManager.getDrawable("circle"));
            image.setPosition(-16.0f, -16.0f);
            image.setSize(32.0f, 32.0f);
            image.setColor(MaterialColor.LIGHT_BLUE.P500);
            addActor(image);
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
            this.k.dispose();
        }
    }
}
