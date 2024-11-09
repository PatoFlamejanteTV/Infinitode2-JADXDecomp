package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/BackButton.class */
public final class BackButton extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final Color f3460a = MaterialColor.LIGHT_BLUE.P800;

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3461b = MaterialColor.LIGHT_BLUE.P700;
    private static final Color c = MaterialColor.LIGHT_BLUE.P900;
    private final UiManager.UiLayer d = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 100, "BackButton");
    private final Image e;
    private final Label f;
    private Group g;
    private Runnable h;

    public static BackButton i() {
        return (BackButton) Game.i.uiManager.getComponent(BackButton.class);
    }

    public BackButton() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE);
        this.g = new Group();
        this.g.setTouchable(Touchable.enabled);
        this.g.setName("shared_back_button");
        this.d.getTable().add((Table) this.g).expand().bottom().left().size(442.0f, 128.0f);
        this.e = new Image(Game.i.assetManager.getDrawable("ui-back-button"));
        this.e.setSize(462.0f, 128.0f);
        this.e.setColor(f3460a);
        this.e.setPosition(-20.0f, 0.0f);
        this.g.addActor(this.e);
        Image image = new Image(Game.i.assetManager.getDrawable("icon-triangle-left"));
        image.setSize(64.0f, 64.0f);
        image.setPosition(32.0f, 28.0f);
        this.g.addActor(image);
        this.f = new Label(Game.i.localeManager.i18n.get("back"), labelStyle);
        this.f.setSize(314.0f, 124.0f);
        this.f.setPosition(112.0f, 0.0f);
        this.g.addActor(this.f);
        this.g.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.BackButton.1

            /* renamed from: a, reason: collision with root package name */
            private boolean f3462a = false;

            /* renamed from: b, reason: collision with root package name */
            private boolean f3463b = false;

            private void a() {
                if (this.f3462a) {
                    BackButton.this.e.setColor(BackButton.c);
                } else if (this.f3463b) {
                    BackButton.this.e.setColor(BackButton.f3461b);
                } else {
                    BackButton.this.e.setColor(BackButton.f3460a);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (BackButton.this.h != null) {
                    BackButton.this.h.run();
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                this.f3462a = true;
                a();
                return super.touchDown(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                this.f3462a = false;
                a();
                super.touchUp(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    this.f3463b = true;
                    a();
                }
                super.enter(inputEvent, f, f2, i, actor);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    this.f3463b = false;
                    a();
                }
                super.exit(inputEvent, f, f2, i, actor);
            }
        });
        setVisible(false, true);
    }

    public final BackButton setText(CharSequence charSequence) {
        if (charSequence == null) {
            this.f.setText(Game.i.localeManager.i18n.get("back"));
        } else {
            this.f.setText(charSequence);
        }
        return this;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final BackButton setVisible(boolean z) {
        setVisible(z, false);
        return this;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final BackButton setVisible(boolean z, boolean z2) {
        this.d.getTable().setVisible(z);
        if (!z) {
            this.h = null;
        }
        return this;
    }

    public final BackButton setClickHandler(Runnable runnable) {
        this.h = runnable;
        return this;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        setVisible(false, true);
    }
}
