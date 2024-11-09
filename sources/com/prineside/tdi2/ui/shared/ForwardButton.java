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
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ForwardButton.class */
public class ForwardButton extends UiManager.UiComponent.Adapter {
    public static final Color DEFAULT_NORMAL_COLOR = MaterialColor.LIGHT_BLUE.P700;
    public static final Color DEFAULT_HOVER_COLOR = MaterialColor.LIGHT_BLUE.P600;
    public static final Color DEFAULT_ACTIVE_COLOR = MaterialColor.LIGHT_BLUE.P800;
    public static final Color DEFAULT_DISABLED_COLOR = Color.GRAY;

    /* renamed from: a, reason: collision with root package name */
    private Color f3534a;

    /* renamed from: b, reason: collision with root package name */
    private Color f3535b;
    private Color c;
    private Color d;
    private final UiManager.UiLayer e;
    private final Image f;
    private final Label g;
    private final Image h;
    private boolean i = true;
    private boolean j = false;
    private boolean k = false;
    private Runnable l;

    public static ForwardButton i() {
        return (ForwardButton) Game.i.uiManager.getComponent(ForwardButton.class);
    }

    public ForwardButton() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE);
        this.e = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 100, "Forward button");
        Group group = new Group();
        group.setTouchable(Touchable.enabled);
        this.e.getTable().add((Table) group).expand().bottom().right().size(442.0f, 128.0f);
        this.f3534a = DEFAULT_NORMAL_COLOR;
        this.f3535b = DEFAULT_HOVER_COLOR;
        this.c = DEFAULT_ACTIVE_COLOR;
        this.d = DEFAULT_DISABLED_COLOR;
        this.f = new Image(Game.i.assetManager.getDrawable("ui-forward-button"));
        this.f.setSize(442.0f, 128.0f);
        this.f.setColor(DEFAULT_NORMAL_COLOR);
        group.addActor(this.f);
        this.h = new Image();
        this.h.setSize(64.0f, 64.0f);
        this.h.setPosition(346.0f, 28.0f);
        group.addActor(this.h);
        this.g = new Label("", labelStyle);
        this.g.setSize(330.0f, 124.0f);
        this.g.setAlignment(16);
        group.addActor(this.g);
        group.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.ForwardButton.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (ForwardButton.this.i && ForwardButton.this.l != null) {
                    ForwardButton.this.l.run();
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                ForwardButton.this.j = true;
                ForwardButton.this.a();
                return super.touchDown(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                ForwardButton.this.j = false;
                ForwardButton.this.a();
                super.touchUp(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    ForwardButton.this.k = true;
                    ForwardButton.this.a();
                }
                super.enter(inputEvent, f, f2, i, actor);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    ForwardButton.this.k = false;
                    ForwardButton.this.a();
                }
                super.exit(inputEvent, f, f2, i, actor);
            }
        });
        reset();
    }

    public ForwardButton reset() {
        setIcon(Game.i.assetManager.getDrawable("icon-triangle-right"));
        setText(Game.i.localeManager.i18n.get("continue"));
        setVisible(false);
        setEnabled(true);
        setBackgroundColors(DEFAULT_NORMAL_COLOR, DEFAULT_HOVER_COLOR, DEFAULT_ACTIVE_COLOR, DEFAULT_DISABLED_COLOR);
        return this;
    }

    public ForwardButton setBackgroundColors(Color color, Color color2, Color color3, Color color4) {
        this.f3534a = color;
        this.f3535b = color2;
        this.c = color3;
        this.d = color4;
        a();
        return this;
    }

    public ForwardButton setIcon(Drawable drawable) {
        this.h.setDrawable(drawable);
        return this;
    }

    public ForwardButton setEnabled(boolean z) {
        this.i = z;
        a();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.i) {
            if (this.j) {
                this.f.setColor(this.c);
                return;
            } else if (this.k) {
                this.f.setColor(this.f3535b);
                return;
            } else {
                this.f.setColor(this.f3534a);
                return;
            }
        }
        this.f.setColor(this.d);
    }

    public ForwardButton setText(CharSequence charSequence) {
        this.g.setText(charSequence);
        return this;
    }

    public ForwardButton setVisible(boolean z) {
        this.e.getTable().setVisible(z);
        return this;
    }

    public ForwardButton setClickHandler(Runnable runnable) {
        this.l = runnable;
        return this;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        setVisible(false);
    }
}
