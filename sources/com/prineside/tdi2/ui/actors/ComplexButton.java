package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ComplexButton.class */
public class ComplexButton extends Group {
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    public final Image background;
    public final Image backgroundShadow;
    public final Image iconShadow;
    public final Image icon;
    public final Label label;
    public final Label labelShadow;
    public Image holdHintIcon;
    protected boolean k;
    private final Color p;
    private final Color q;
    private final Color r;
    private final Color s;
    private final Color t;
    private final Color u;
    private final Color v;
    private final Color w;
    private final Color x;
    private final Color y;
    private final Color z;
    private final Color A;
    private Runnable B;
    private Runnable C;

    public ComplexButton(CharSequence charSequence, Label.LabelStyle labelStyle, Runnable runnable, Runnable runnable2) {
        this.l = false;
        this.m = false;
        this.n = true;
        this.o = false;
        this.p = MaterialColor.LIGHT_BLUE.P800.cpy();
        this.q = MaterialColor.LIGHT_BLUE.P700.cpy();
        this.r = MaterialColor.LIGHT_BLUE.P900.cpy();
        this.s = MaterialColor.GREY.P800.cpy();
        this.t = Color.WHITE.cpy();
        this.u = Color.WHITE.cpy();
        this.v = Color.WHITE.cpy();
        this.w = MaterialColor.GREY.P500.cpy();
        this.x = Color.WHITE.cpy();
        this.y = Color.WHITE.cpy();
        this.z = Color.WHITE.cpy();
        this.A = MaterialColor.GREY.P500.cpy();
        this.B = runnable;
        this.C = runnable2;
        setTransform(false);
        this.backgroundShadow = new Image();
        this.backgroundShadow.setVisible(false);
        addActor(this.backgroundShadow);
        this.background = new Image();
        addActor(this.background);
        this.iconShadow = new Image();
        this.iconShadow.setVisible(false);
        this.iconShadow.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        addActor(this.iconShadow);
        this.icon = new Image();
        addActor(this.icon);
        this.labelShadow = new Label(charSequence, labelStyle);
        this.labelShadow.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        this.labelShadow.setVisible(false);
        addActor(this.labelShadow);
        this.label = new Label(charSequence, labelStyle);
        addActor(this.label);
        setTouchable(Touchable.enabled);
        addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.actors.ComplexButton.1

            /* renamed from: b, reason: collision with root package name */
            private final Timer.Task f3213b = new Timer.Task() { // from class: com.prineside.tdi2.ui.actors.ComplexButton.1.1
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    if (AnonymousClass1.this.c != null) {
                        AnonymousClass1.this.c.disappearing = true;
                        AnonymousClass1.this.c = null;
                    }
                    if (ComplexButton.this.C != null) {
                        ComplexButton.this.C.run();
                    }
                }
            };
            private ButtonHoldHint c;

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (ComplexButton.this.n && ComplexButton.this.B != null) {
                    ComplexButton.this.B.run();
                    if (!ComplexButton.this.o) {
                        Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                    }
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (i2 == 0) {
                    ComplexButton.this.l = true;
                    ComplexButton.this.d();
                    if (this.f3213b.isScheduled()) {
                        this.f3213b.cancel();
                    }
                    if (ComplexButton.this.C != null) {
                        Timer.schedule(this.f3213b, 0.5f);
                        this.c = new ButtonHoldHint(f, f2, 0.5f);
                        ComplexButton.this.addActor(this.c);
                    }
                } else if (i2 == 1 && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.INSTANT_HOLD_BUTTON_ON_RMB) == 1.0d) {
                    if (this.f3213b.isScheduled()) {
                        this.f3213b.cancel();
                    }
                    this.f3213b.run();
                }
                return super.touchDown(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                ComplexButton.this.l = false;
                ComplexButton.this.d();
                if (ComplexButton.this.C != null && !this.f3213b.isScheduled()) {
                    cancel();
                }
                this.f3213b.cancel();
                if (this.c != null) {
                    ButtonHoldHint buttonHoldHint = this.c;
                    Threads i3 = Threads.i();
                    Objects.requireNonNull(buttonHoldHint);
                    i3.postRunnable(buttonHoldHint::remove);
                    this.c = null;
                }
                super.touchUp(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    ComplexButton.this.m = true;
                    ComplexButton.this.d();
                }
                super.enter(inputEvent, f, f2, i, actor);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    ComplexButton.this.m = false;
                    ComplexButton.this.d();
                }
                super.exit(inputEvent, f, f2, i, actor);
            }
        });
        if (runnable2 != null) {
            this.holdHintIcon = new Image(Game.i.assetManager.getDrawable("button-hold-mark"));
            this.holdHintIcon.setSize(20.0f, 20.0f);
            this.holdHintIcon.setPosition(12.0f, 12.0f);
            addActor(this.holdHintIcon);
        }
        d();
    }

    public ComplexButton(CharSequence charSequence, Label.LabelStyle labelStyle, Runnable runnable) {
        this(charSequence, labelStyle, runnable, null);
    }

    public void setHoldHintIconBright(boolean z) {
        if (this.holdHintIcon != null) {
            if (z) {
                this.holdHintIcon.setDrawable(Game.i.assetManager.getDrawable("button-hold-mark-white"));
            } else {
                this.holdHintIcon.setDrawable(Game.i.assetManager.getDrawable("button-hold-mark"));
            }
        }
    }

    public void setMuted(boolean z) {
        this.o = z;
    }

    public void setClickHandler(Runnable runnable) {
        this.B = runnable;
    }

    public ComplexButton setBackground(Drawable drawable, float f, float f2, float f3, float f4) {
        this.background.setDrawable(drawable);
        this.background.setPosition(f, f2);
        this.background.setSize(f3, f4);
        return this;
    }

    public ComplexButton setIconPositioned(Drawable drawable, float f, float f2, float f3, float f4) {
        this.icon.setDrawable(drawable);
        this.icon.setPosition(f, f2);
        this.icon.setSize(f3, f4);
        this.iconShadow.setDrawable(drawable);
        this.iconShadow.setPosition(f, f2 - 3.0f);
        this.iconShadow.setSize(f3, f4);
        return this;
    }

    public ComplexButton setIcon(Drawable drawable) {
        this.icon.setDrawable(drawable);
        this.iconShadow.setDrawable(drawable);
        return this;
    }

    public ComplexButton setText(CharSequence charSequence) {
        this.label.setText(charSequence);
        return this;
    }

    public ComplexButton setTextFromInt(int i) {
        this.label.setTextFromInt(i);
        this.labelShadow.setTextFromInt(i);
        return this;
    }

    public ComplexButton setLabel(float f, float f2, float f3, float f4, int i) {
        this.label.setPosition(f, f2);
        this.label.setSize(f3, f4);
        this.label.setAlignment(i);
        this.labelShadow.setPosition(f, f2 - 2.0f);
        this.labelShadow.setSize(f3, f4);
        this.labelShadow.setAlignment(i);
        this.k = true;
        return this;
    }

    public ComplexButton setBackgroundColors(Color color, Color color2, Color color3, Color color4) {
        if (color != null) {
            this.p.set(color);
        }
        if (color3 != null) {
            this.q.set(color3);
        }
        if (color2 != null) {
            this.r.set(color2);
        }
        if (color4 != null) {
            this.s.set(color4);
        }
        d();
        return this;
    }

    public ComplexButton setIconLabelColors(Color color, Color color2, Color color3, Color color4) {
        setIconColors(color, color2, color3, color4);
        setLabelColors(color, color2, color3, color4);
        return this;
    }

    public ComplexButton setIconColors(Color color, Color color2, Color color3, Color color4) {
        if (color != null) {
            this.x.set(color);
        }
        if (color3 != null) {
            this.z.set(color3);
        }
        if (color2 != null) {
            this.y.set(color2);
        }
        if (color4 != null) {
            this.A.set(color4);
        }
        d();
        return this;
    }

    public void setIconLabelShadowEnabled(boolean z) {
        this.iconShadow.setVisible(z);
        this.labelShadow.setVisible(z);
    }

    public ComplexButton setLabelColors(Color color, Color color2, Color color3, Color color4) {
        if (color != null) {
            this.t.set(color);
        }
        if (color3 != null) {
            this.v.set(color3);
        }
        if (color2 != null) {
            this.u.set(color2);
        }
        if (color4 != null) {
            this.w.set(color4);
        }
        d();
        return this;
    }

    public ComplexButton setEnabled(boolean z) {
        this.n = z;
        d();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.n) {
            if (this.l) {
                this.label.setColor(this.v);
                this.icon.setColor(this.z);
                this.background.setColor(this.r);
                return;
            } else if (this.m) {
                this.label.setColor(this.u);
                this.icon.setColor(this.y);
                this.background.setColor(this.q);
                return;
            } else {
                this.label.setColor(this.t);
                this.icon.setColor(this.x);
                this.background.setColor(this.p);
                return;
            }
        }
        this.background.setColor(this.s);
        this.label.setColor(this.w);
        this.icon.setColor(this.A);
    }
}
