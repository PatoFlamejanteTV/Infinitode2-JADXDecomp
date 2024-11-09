package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.RightSideMenuButton;
import com.prineside.tdi2.utils.InputVoid;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/StorylineMessages.class */
public class StorylineMessages implements Disposable {
    private boolean e;
    private float f;
    private Group g;
    private Runnable j;
    private GameSystemProvider k;

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f3415a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 193, "StorylineMessages main");

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3416b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 194, "StorylineMessages overlay");
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 195, "StorylineMessages button");
    private final UiManager.UiLayer[] d = {this.f3415a, this.f3416b, this.c};
    private Array<String> h = new Array<>();
    private Array<Label> i = new Array<>();

    public StorylineMessages(GameSystemProvider gameSystemProvider) {
        this.k = gameSystemProvider;
        this.f3415a.getTable().setBackground(Game.i.assetManager.getOverlayBackground());
        this.g = new Group();
        this.g.setWidth(1060.0f);
        this.g.setHeight(1.0f);
        this.f3415a.getTable().add((Table) this.g).expand().bottom().left().padLeft(40.0f).padRight(40.0f).padBottom(160.0f);
        this.f3416b.getTable().setTouchable(Touchable.enabled);
        this.f3416b.getTable().addListener(new InputVoid());
        Image image = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image.setColor(Config.BACKGROUND_COLOR);
        this.f3416b.getTable().add((Table) image).expand().fillX().height(760.0f).top().row();
        Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image2.setColor(Config.BACKGROUND_COLOR);
        this.f3416b.getTable().add((Table) image2).expand().fillX().height(160.0f).bottom();
        Table table = new Table();
        this.c.getTable().add(table).expand().bottom().right().padBottom(40.0f);
        table.add((Table) new RightSideMenuButton(Game.i.localeManager.i18n.get("continue"), "icon-triangle-right", () -> {
            a();
        }));
        for (UiManager.UiLayer uiLayer : this.d) {
            uiLayer.getTable().setVisible(false);
        }
    }

    private void a() {
        if (this.h.size > 0) {
            a(this.h.removeIndex(0));
            return;
        }
        hide();
        if (this.j != null) {
            Runnable runnable = this.j;
            this.j = null;
            runnable.run();
        }
    }

    public void runOnContinue(Runnable runnable) {
        this.j = runnable;
    }

    public void flushQueue() {
        while (this.h.size > 0) {
            a(this.h.removeIndex(0));
        }
    }

    private void a(String str) {
        Label label = new Label(str, new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        label.setWrap(true);
        label.setWidth(1060.0f);
        label.layout();
        label.pack();
        label.setWidth(1060.0f);
        this.i.add(label);
        label.addAction(Actions.alpha(0.0f));
        this.g.clearChildren();
        float f = 0.0f;
        for (int i = this.i.size - 1; i >= 0; i--) {
            Label label2 = this.i.get(i);
            label2.setPosition(0.0f, f);
            this.g.addActor(label2);
            if (i == this.i.size - 1) {
                label2.addAction(Actions.alpha(1.0f, 0.3f));
                f += 16.0f;
            } else {
                label2.addAction(Actions.alpha(0.56f));
            }
            f += label2.getHeight();
        }
    }

    public void queue(String[] strArr) {
        if (strArr.length == 0) {
            return;
        }
        this.h.addAll(strArr);
        if (!this.e) {
            a(this.h.removeIndex(0));
            show();
        }
    }

    public void add(String str) {
        flushQueue();
        a(str);
        show();
    }

    public void show() {
        if (!this.e) {
            this.f = this.k.gameState.getNonAnimatedGameSpeed();
            this.k.gameState.setGameSpeed(0.0f);
            for (UiManager.UiLayer uiLayer : this.d) {
                uiLayer.getTable().setVisible(true);
                uiLayer.getTable().clearActions();
                uiLayer.getTable().addAction(Actions.sequence(Actions.alpha(1.0f, 0.3f)));
            }
            this.e = true;
        }
    }

    public void hide() {
        if (this.e) {
            this.k.gameState.setGameSpeed(this.f);
            for (UiManager.UiLayer uiLayer : this.d) {
                uiLayer.getTable().clearActions();
                uiLayer.getTable().addAction(Actions.sequence(Actions.alpha(0.0f, 0.3f), Actions.run(() -> {
                    uiLayer.getTable().setVisible(false);
                })));
            }
            this.e = false;
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3415a);
        Game.i.uiManager.removeLayer(this.f3416b);
        Game.i.uiManager.removeLayer(this.c);
    }
}
