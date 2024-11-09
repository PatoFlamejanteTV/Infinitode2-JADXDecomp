package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.InputVoid;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/UiElementsEmphasizer.class */
public class UiElementsEmphasizer implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3442a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 120, "UiElementsEmphasizer background and labels");

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3443b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 121, "UiElementsEmphasizer element");
    private Group c;
    private Label d;
    private Label e;
    private static final Vector2 f = new Vector2();
    private boolean g;
    private Actor h;
    private Rectangle i;
    private float j;
    private float k;
    private boolean l;
    private Touchable m;
    private Group n;
    private Runnable o;
    private float p;
    private final GameSystemProvider q;

    public UiElementsEmphasizer(GameSystemProvider gameSystemProvider) {
        this.q = gameSystemProvider;
        this.f3442a.getTable().setBackground(Game.i.assetManager.getOverlayBackground());
        this.d = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.d.setAlignment(1);
        this.f3442a.getTable().add((Table) this.d).size(100.0f, 40.0f).padTop(700.0f).row();
        this.e = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        this.e.setAlignment(1);
        this.e.setWrap(true);
        this.f3442a.getTable().add((Table) this.e).width(1200.0f).row();
        Label label = new Label(Game.i.localeManager.i18n.get("tap_screen_to_continue"), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        label.setAlignment(1);
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.f3442a.getTable().add((Table) label).size(100.0f, 40.0f).padTop(160.0f).row();
        this.f3443b.getTable().setTouchable(Touchable.enabled);
        this.f3443b.getTable().addListener(new InputVoid());
        this.f3443b.getTable().addListener(new InputListener() { // from class: com.prineside.tdi2.ui.components.UiElementsEmphasizer.1
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                UiElementsEmphasizer.this.hide(false);
                return true;
            }
        });
        this.c = new Group();
        this.f3443b.getTable().add((Table) this.c).size(1.0f);
        this.f3442a.getTable().addAction(Actions.alpha(0.0f));
        this.f3442a.getTable().setVisible(false);
        this.f3443b.getTable().setVisible(false);
    }

    public void show(Actor actor, Rectangle rectangle, String str, String str2, Runnable runnable) {
        if (this.g) {
            hide(true);
        }
        this.p = this.q.gameState.getNonAnimatedGameSpeed();
        this.q.gameState.setGameSpeed(0.0f);
        this.f3442a.getTable().setVisible(true);
        this.f3442a.getTable().clearActions();
        this.f3442a.getTable().addAction(Actions.alpha(1.0f, 0.3f));
        this.f3443b.getTable().setVisible(true);
        this.d.setText(str);
        this.e.setText(str2);
        this.g = true;
        this.h = actor;
        this.i = rectangle;
        this.o = runnable;
        float width = Game.i.uiManager.stage.getWidth() / 2.0f;
        float height = Game.i.uiManager.stage.getHeight() / 2.0f;
        if (actor.getParent() != null) {
            f.set(rectangle.x, rectangle.y);
            actor.getParent().localToStageCoordinates(f);
            this.j = f.x - width;
            this.k = f.y - height;
            this.n = actor.getParent();
            this.n.removeActor(actor);
        } else {
            this.j = 0.0f;
            this.k = 0.0f;
            this.n = null;
        }
        this.c.addActor(actor);
        actor.setVisible(true);
        actor.setScale(0.0f);
        actor.setPosition((-rectangle.width) * 0.5f, (-rectangle.height) * 0.5f);
        this.l = false;
        if (actor instanceof Group) {
            this.l = ((Group) actor).isTransform();
            ((Group) actor).setTransform(true);
        }
        this.m = actor.getTouchable();
        actor.setTouchable(Touchable.disabled);
        actor.addAction(Actions.scaleTo(1.5f, 1.5f, 0.75f, Interpolation.swingOut));
    }

    public void hide(boolean z) {
        if (this.g) {
            this.q.gameState.setGameSpeed(this.p);
            this.g = false;
            this.f3442a.getTable().clearActions();
            this.f3442a.getTable().addAction(Actions.sequence(Actions.alpha(0.0f, 0.5f), Actions.hide()));
            this.h.clearActions();
            if (z) {
                this.c.removeActor(this.h);
                if (this.n != null) {
                    this.n.addActor(this.h);
                }
                this.h.setScale(1.0f);
                this.h.setPosition(this.i.x, this.i.y);
                if (this.h instanceof Group) {
                    ((Group) this.h).setTransform(this.l);
                }
                this.h.setTouchable(this.m);
                this.h = null;
                this.n = null;
                this.f3443b.getTable().setVisible(false);
                if (this.o != null) {
                    this.o.run();
                    return;
                }
                return;
            }
            this.h.addAction(Actions.sequence(Actions.parallel(Actions.scaleTo(1.0f, 1.0f, 0.5f), Actions.moveTo(this.j, this.k, 0.5f, Interpolation.pow2)), Actions.run(() -> {
                this.c.removeActor(this.h);
                if (this.n != null) {
                    this.n.addActor(this.h);
                }
                this.h.setPosition(this.i.x, this.i.y);
                if (this.h instanceof Group) {
                    ((Group) this.h).setTransform(this.l);
                }
                this.h.setTouchable(this.m);
                this.h = null;
                this.n = null;
                this.f3443b.getTable().setVisible(false);
                if (this.o != null) {
                    this.o.run();
                }
            })));
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3443b);
        Game.i.uiManager.removeLayer(this.f3442a);
    }
}
