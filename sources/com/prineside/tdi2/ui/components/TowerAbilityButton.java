package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.ButtonHoldHint;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/TowerAbilityButton.class */
public final class TowerAbilityButton extends Group {
    private static final Color k = new Color(0.0f, 0.0f, 0.0f, 0.14f);
    private static final Color l = MaterialColor.LIGHT_BLUE.P800;
    private static final Color m = MaterialColor.LIGHT_BLUE.P700;
    private static final Color n = MaterialColor.LIGHT_BLUE.P900;
    private static final Color o = MaterialColor.GREEN.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.21f);
    private final int p;
    private final Image q;
    private final Image r;
    private final Image s;
    private final Image t;
    private final Image u;
    private final Image v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean A;
    private boolean B;
    private boolean C;
    private float D;
    private ButtonHoldHint E;
    private Image F;
    private final DelayedRemovalArray<AbilityButtonListener> G = new DelayedRemovalArray<>(false, 1);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/TowerAbilityButton$AbilityButtonListener.class */
    public interface AbilityButtonListener {
        void abilityConfirmed();

        void globalAbilityConfirmed();
    }

    static /* synthetic */ boolean a(TowerAbilityButton towerAbilityButton) {
        return f();
    }

    static /* synthetic */ void e(TowerAbilityButton towerAbilityButton) {
        towerAbilityButton.e();
    }

    static /* synthetic */ ButtonHoldHint f(TowerAbilityButton towerAbilityButton) {
        return towerAbilityButton.E;
    }

    static /* synthetic */ ButtonHoldHint a(TowerAbilityButton towerAbilityButton, ButtonHoldHint buttonHoldHint) {
        towerAbilityButton.E = buttonHoldHint;
        return buttonHoldHint;
    }

    static /* synthetic */ boolean c(TowerAbilityButton towerAbilityButton, boolean z) {
        towerAbilityButton.B = z;
        return z;
    }

    static /* synthetic */ boolean d(TowerAbilityButton towerAbilityButton, boolean z) {
        towerAbilityButton.C = false;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TowerAbilityButton(final int i) {
        this.p = i;
        setSize(80.0f, 80.0f);
        setTransform(false);
        setTouchable(Touchable.enabled);
        this.q = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.q.setSize(80.0f, 80.0f);
        this.q.setTouchable(Touchable.disabled);
        addActor(this.q);
        this.t = new Image();
        this.t.setSize(86.0f, 86.0f);
        this.t.setPosition(-3.0f, -3.0f);
        this.t.setTouchable(Touchable.disabled);
        addActor(this.t);
        this.r = new Image(Game.i.assetManager.getDrawable("ui-tower-ability-outline"));
        this.r.setSize(86.0f, 86.0f);
        this.r.setPosition(-3.0f, -3.0f);
        this.r.setVisible(false);
        this.r.setTouchable(Touchable.disabled);
        addActor(this.r);
        this.s = new Image(Game.i.assetManager.getDrawable("icon-check"));
        this.s.setSize(21.0f, 21.0f);
        this.s.setPosition(64.0f, -4.0f);
        this.s.setColor(MaterialColor.GREEN.P500);
        this.s.setVisible(false);
        this.s.setTouchable(Touchable.disabled);
        addActor(this.s);
        this.u = new Image();
        this.u.setSize(86.0f, 86.0f);
        this.u.setPosition(-3.0f, -3.0f);
        this.u.setTouchable(Touchable.disabled);
        addActor(this.u);
        this.v = new Image();
        this.v.setTouchable(Touchable.disabled);
        this.v.setVisible(false);
        this.v.setSize(86.0f, 86.0f);
        this.v.setPosition(-3.0f, -3.0f);
        addActor(this.v);
        addListener(new InputListener() { // from class: com.prineside.tdi2.ui.components.TowerAbilityButton.1
            /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.prineside.tdi2.ui.components.TowerAbilityButton.a(com.prineside.tdi2.ui.components.TowerAbilityButton):boolean
                	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
                	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
                Caused by: java.lang.IndexOutOfBoundsException: Index: 0
                	at java.base/java.util.Collections$EmptyList.get(Unknown Source)
                	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:103)
                	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:117)
                	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
                	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
                	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
                	... 1 more
                */
            @Override // com.prineside.tdi2.scene2d.InputListener
            public void enter(com.prineside.tdi2.scene2d.InputEvent r8, float r9, float r10, int r11, com.prineside.tdi2.scene2d.Actor r12) {
                /*
                    r7 = this;
                    r0 = r7
                    r1 = r8
                    r2 = r9
                    r3 = r10
                    r4 = r11
                    r5 = r12
                    super.enter(r1, r2, r3, r4, r5)
                    r0 = r11
                    r1 = -1
                    if (r0 != r1) goto L33
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r1 = 1
                    boolean r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.a(r0, r1)
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    boolean r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.a(r0)
                    if (r0 == 0) goto L2c
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r1 = 1
                    r0.setSelected(r1)
                L2c:
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r0.d()
                L33:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.TowerAbilityButton.AnonymousClass1.enter(com.prineside.tdi2.scene2d.InputEvent, float, float, int, com.prineside.tdi2.scene2d.Actor):void");
            }

            /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.prineside.tdi2.ui.components.TowerAbilityButton.a(com.prineside.tdi2.ui.components.TowerAbilityButton):boolean
                	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
                	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
                Caused by: java.lang.IndexOutOfBoundsException: Index: 0
                	at java.base/java.util.Collections$EmptyList.get(Unknown Source)
                	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:103)
                	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:117)
                	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
                	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
                	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
                	... 1 more
                */
            @Override // com.prineside.tdi2.scene2d.InputListener
            public void exit(com.prineside.tdi2.scene2d.InputEvent r8, float r9, float r10, int r11, com.prineside.tdi2.scene2d.Actor r12) {
                /*
                    r7 = this;
                    r0 = r7
                    r1 = r8
                    r2 = r9
                    r3 = r10
                    r4 = r11
                    r5 = r12
                    super.exit(r1, r2, r3, r4, r5)
                    r0 = r11
                    r1 = -1
                    if (r0 != r1) goto L33
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r1 = 0
                    boolean r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.a(r0, r1)
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    boolean r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.a(r0)
                    if (r0 == 0) goto L2c
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r1 = 0
                    r0.setSelected(r1)
                L2c:
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r0.d()
                L33:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.TowerAbilityButton.AnonymousClass1.exit(com.prineside.tdi2.scene2d.InputEvent, float, float, int, com.prineside.tdi2.scene2d.Actor):void");
            }

            /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.prineside.tdi2.ui.components.TowerAbilityButton.a(com.prineside.tdi2.ui.components.TowerAbilityButton):boolean
                	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
                	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
                Caused by: java.lang.IndexOutOfBoundsException: Index: 0
                	at java.base/java.util.Collections$EmptyList.get(Unknown Source)
                	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:103)
                	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:117)
                	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
                	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
                	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
                	... 1 more
                */
            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(com.prineside.tdi2.scene2d.InputEvent r8, float r9, float r10, int r11, int r12) {
                /*
                    r7 = this;
                    r0 = r7
                    r1 = r8
                    r2 = r9
                    r3 = r10
                    r4 = r11
                    r5 = r12
                    super.touchUp(r1, r2, r3, r4, r5)
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r1 = 0
                    boolean r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.b(r0, r1)
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    boolean r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.b(r0)
                    if (r0 != 0) goto L62
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    boolean r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.c(r0)
                    if (r0 == 0) goto L62
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    boolean r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.d(r0)
                    if (r0 != 0) goto L62
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    boolean r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.a(r0)
                    if (r0 == 0) goto L46
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    com.prineside.tdi2.ui.components.TowerAbilityButton.e(r0)
                    goto L62
                L46:
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    boolean r0 = r0.isSelected()
                    if (r0 == 0) goto L5a
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    com.prineside.tdi2.ui.components.TowerAbilityButton.e(r0)
                    goto L62
                L5a:
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r1 = 1
                    r0.setSelected(r1)
                L62:
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    com.prineside.tdi2.ui.actors.ButtonHoldHint r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.f(r0)
                    if (r0 == 0) goto L8e
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    com.prineside.tdi2.ui.actors.ButtonHoldHint r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.f(r0)
                    r8 = r0
                    com.prineside.tdi2.Threads r0 = com.prineside.tdi2.Threads.i()
                    r1 = r8
                    r2 = r1
                    java.lang.Object r2 = java.util.Objects.requireNonNull(r2)
                    void r1 = r1::remove
                    r0.postRunnable(r1)
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r1 = 0
                    com.prineside.tdi2.ui.actors.ButtonHoldHint r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.a(r0, r1)
                L8e:
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r1 = 0
                    boolean r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.c(r0, r1)
                    r0 = r7
                    com.prineside.tdi2.ui.components.TowerAbilityButton r0 = com.prineside.tdi2.ui.components.TowerAbilityButton.this
                    r0.d()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.TowerAbilityButton.AnonymousClass1.touchUp(com.prineside.tdi2.scene2d.InputEvent, float, float, int, int):void");
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i2, int i3) {
                if (i3 == 0) {
                    TowerAbilityButton.this.y = true;
                    TowerAbilityButton.this.d();
                    TowerAbilityButton.this.B = true;
                    TowerAbilityButton.d(TowerAbilityButton.this, false);
                    TowerAbilityButton.this.D = 0.0f;
                    if (i != 3) {
                        TowerAbilityButton.this.E = new ButtonHoldHint(f, f2, 0.75f);
                        TowerAbilityButton.this.addActor(TowerAbilityButton.this.E);
                        return true;
                    }
                    return true;
                }
                if (i3 == 1 && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.INSTANT_HOLD_BUTTON_ON_RMB) == 1.0d) {
                    TowerAbilityButton.this.B = true;
                    TowerAbilityButton.this.D = 1.0f;
                    return true;
                }
                return true;
            }
        });
        this.F = new Image(Game.i.assetManager.getDrawable("button-hold-mark"));
        this.F.setSize(20.0f, 20.0f);
        this.F.setPosition(4.0f, 4.0f);
        addActor(this.F);
        a(null, null, null);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.G.begin();
        int i = this.G.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.G.get(i2).abilityConfirmed();
        }
        this.G.end();
    }

    public final void addListener(AbilityButtonListener abilityButtonListener) {
        if (abilityButtonListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.G.contains(abilityButtonListener, true)) {
            this.G.add(abilityButtonListener);
        }
    }

    public final void removeListener(AbilityButtonListener abilityButtonListener) {
        if (abilityButtonListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.G.removeValue(abilityButtonListener, true);
    }

    public final boolean isSelected() {
        return this.x && this.A;
    }

    public final void setSelected(boolean z) {
        this.A = z;
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(boolean z) {
        this.x = z;
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(boolean z) {
        this.w = z;
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean f() {
        if (Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        if (drawable != null) {
            this.t.setDrawable(drawable);
            this.t.setColor(0.0f, 0.0f, 0.0f, 0.21f);
            this.t.setVisible(true);
        } else {
            this.t.setVisible(false);
        }
        if (drawable2 != null) {
            this.u.setDrawable(drawable2);
            this.u.setColor(0.78f, 0.78f, 0.78f, 1.0f);
            this.u.setVisible(true);
        } else {
            this.u.setVisible(false);
        }
        if (drawable3 != null) {
            this.v.setDrawable(drawable3);
            this.v.setVisible(true);
        } else {
            this.v.setVisible(false);
        }
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public final void act(float f) {
        if (this.B) {
            this.D += f;
            if (this.D > 0.75f) {
                if (this.p != 3) {
                    this.C = true;
                    if (this.E != null) {
                        this.E.disappearing = true;
                        this.E = null;
                    }
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("select_ability_for_all_towers_confirm"), () -> {
                        this.G.begin();
                        int i = this.G.size;
                        for (int i2 = 0; i2 < i; i2++) {
                            this.G.get(i2).globalAbilityConfirmed();
                        }
                        this.G.end();
                    });
                }
                this.B = false;
            }
        }
        super.act(f);
    }

    final void d() {
        this.r.setVisible(this.A);
        this.F.setVisible(false);
        if (this.w) {
            this.s.setVisible(true);
            this.q.setColor(o);
            this.q.clearActions();
            return;
        }
        this.s.setVisible(false);
        if (this.x) {
            if (this.A) {
                this.q.setColor(n);
                this.q.clearActions();
            } else if (this.y) {
                this.q.setColor(n);
                this.q.clearActions();
            } else if (this.z) {
                this.q.setColor(m);
                this.q.clearActions();
            } else if (!this.q.hasActions()) {
                this.q.setColor(l);
                this.q.addAction(Actions.forever(Actions.sequence(Actions.color(l, 0.5f), Actions.color(n, 0.7f))));
            }
            this.F.setVisible(true);
            return;
        }
        this.q.setColor(k);
        this.q.clearActions();
    }
}
