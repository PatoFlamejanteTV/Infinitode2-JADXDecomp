package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.ButtonHoldHint;
import com.prineside.tdi2.ui.actors.HotKeyHintLabel;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/UpgradeSubmenu.class */
public final class UpgradeSubmenu extends Group {
    private static final Color k;
    private static final Color l;
    private static final Color m;
    private static final Color n;
    private static final Color o;
    private static final Color p;
    private final Label r;
    private final Image s;
    private final Label t;
    private final Label u;
    private final Image v;
    private final Label w;
    private final Label x;
    public final Group upgradeButton;
    private int z;
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private float F;
    private ButtonHoldHint G;
    private Image H;
    private static final StringBuilder J;
    private final Image[] q = new Image[10];
    private int y = 10;
    private final DelayedRemovalArray<UpgradeSubmenuListener> I = new DelayedRemovalArray<>(false, 1);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/UpgradeSubmenu$UpgradeSubmenuListener.class */
    public interface UpgradeSubmenuListener {
        void upgradeButtonStateChanged(boolean z);

        void upgradeButtonConfirmed();

        void globalUpgradeButtonConfirmed();
    }

    static /* synthetic */ boolean b(UpgradeSubmenu upgradeSubmenu, boolean z) {
        upgradeSubmenu.E = false;
        return false;
    }

    static /* synthetic */ ButtonHoldHint a(UpgradeSubmenu upgradeSubmenu, ButtonHoldHint buttonHoldHint) {
        upgradeSubmenu.G = buttonHoldHint;
        return buttonHoldHint;
    }

    static /* synthetic */ ButtonHoldHint a(UpgradeSubmenu upgradeSubmenu) {
        return upgradeSubmenu.G;
    }

    static /* synthetic */ boolean c(UpgradeSubmenu upgradeSubmenu) {
        return e();
    }

    static /* synthetic */ void d(UpgradeSubmenu upgradeSubmenu) {
        upgradeSubmenu.f();
    }

    static {
        Color color = Color.WHITE;
        new Color(1.0f, 1.0f, 1.0f, 0.14f);
        k = MaterialColor.GREEN.P800;
        l = MaterialColor.GREEN.P900;
        m = MaterialColor.LIGHT_BLUE.P800;
        n = MaterialColor.LIGHT_BLUE.P700;
        o = MaterialColor.LIGHT_BLUE.P900;
        p = new Color(1.0f, 1.0f, 1.0f, 0.14f);
        J = new StringBuilder();
    }

    public UpgradeSubmenu() {
        setSize(600.0f, 116.0f);
        setTransform(false);
        setTouchable(Touchable.childrenOnly);
        this.q[0] = new Image(Game.i.assetManager.getDrawable("ui-upgrade-level-line-start"));
        this.q[0].setSize(42.0f, 24.0f);
        this.q[0].setPosition(40.0f, 92.0f);
        addActor(this.q[0]);
        TextureRegionDrawable drawable = Game.i.assetManager.getDrawable("ui-upgrade-level-line");
        for (int i = 1; i < 10; i++) {
            this.q[i] = new Image(drawable);
            this.q[i].setSize(46.0f, 24.0f);
            this.q[i].setPosition(80.0f + (44.0f * (i - 1)), 92.0f);
            addActor(this.q[i]);
        }
        this.r = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.r.setSize(80.0f, 24.0f);
        this.r.setPosition(480.0f, 92.0f);
        this.r.setAlignment(16);
        addActor(this.r);
        this.upgradeButton = new Group();
        this.upgradeButton.setSize(338.0f, 80.0f);
        this.upgradeButton.setPosition(40.0f, 0.0f);
        this.upgradeButton.setTransform(false);
        this.upgradeButton.setTouchable(Touchable.enabled);
        this.upgradeButton.addListener(new InputListener() { // from class: com.prineside.tdi2.ui.components.UpgradeSubmenu.1
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i2, int i3) {
                if (i3 == 0) {
                    UpgradeSubmenu.this.B = true;
                    UpgradeSubmenu.this.d();
                    UpgradeSubmenu.b(UpgradeSubmenu.this, false);
                    UpgradeSubmenu.this.F = 0.0f;
                    UpgradeSubmenu.this.G = new ButtonHoldHint(f, f2, 0.75f);
                    UpgradeSubmenu.this.upgradeButton.addActor(UpgradeSubmenu.this.G);
                    return true;
                }
                if (i3 == 1 && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.INSTANT_HOLD_BUTTON_ON_RMB) == 1.0d) {
                    UpgradeSubmenu.this.B = true;
                    UpgradeSubmenu.this.F = 1.0f;
                    return true;
                }
                return true;
            }

            /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.prineside.tdi2.ui.components.UpgradeSubmenu.c(com.prineside.tdi2.ui.components.UpgradeSubmenu):boolean
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
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    r1 = 0
                    boolean r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.a(r0, r1)
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    boolean r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.b(r0)
                    if (r0 != 0) goto L70
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    boolean r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.c(r0)
                    if (r0 != 0) goto L3d
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    boolean r0 = r0.isButtonSelected()
                    if (r0 != 0) goto L3d
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    r1 = 1
                    r0.setButtonSelected(r1)
                    goto L44
                L3d:
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    com.prineside.tdi2.ui.components.UpgradeSubmenu.d(r0)
                L44:
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    com.prineside.tdi2.ui.actors.ButtonHoldHint r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.a(r0)
                    if (r0 == 0) goto L70
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    com.prineside.tdi2.ui.actors.ButtonHoldHint r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.a(r0)
                    r8 = r0
                    com.prineside.tdi2.Threads r0 = com.prineside.tdi2.Threads.i()
                    r1 = r8
                    r2 = r1
                    java.lang.Object r2 = java.util.Objects.requireNonNull(r2)
                    void r1 = r1::remove
                    r0.postRunnable(r1)
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    r1 = 0
                    com.prineside.tdi2.ui.actors.ButtonHoldHint r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.a(r0, r1)
                L70:
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    r0.d()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.UpgradeSubmenu.AnonymousClass1.touchUp(com.prineside.tdi2.scene2d.InputEvent, float, float, int, int):void");
            }

            /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.prineside.tdi2.ui.components.UpgradeSubmenu.c(com.prineside.tdi2.ui.components.UpgradeSubmenu):boolean
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
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    r1 = 1
                    boolean r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.c(r0, r1)
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    boolean r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.c(r0)
                    if (r0 == 0) goto L2c
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    r1 = 1
                    r0.setButtonSelected(r1)
                L2c:
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    r0.d()
                L33:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.UpgradeSubmenu.AnonymousClass1.enter(com.prineside.tdi2.scene2d.InputEvent, float, float, int, com.prineside.tdi2.scene2d.Actor):void");
            }

            /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.prineside.tdi2.ui.components.UpgradeSubmenu.c(com.prineside.tdi2.ui.components.UpgradeSubmenu):boolean
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
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    r1 = 0
                    boolean r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.c(r0, r1)
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    boolean r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.c(r0)
                    if (r0 == 0) goto L2c
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    r1 = 0
                    r0.setButtonSelected(r1)
                L2c:
                    r0 = r7
                    com.prineside.tdi2.ui.components.UpgradeSubmenu r0 = com.prineside.tdi2.ui.components.UpgradeSubmenu.this
                    r0.d()
                L33:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.UpgradeSubmenu.AnonymousClass1.exit(com.prineside.tdi2.scene2d.InputEvent, float, float, int, com.prineside.tdi2.scene2d.Actor):void");
            }
        });
        addActor(this.upgradeButton);
        this.s = new Image(Game.i.assetManager.getDrawable("ui-upgrade-button"));
        this.s.setSize(338.0f, 80.0f);
        this.upgradeButton.addActor(this.s);
        Image image = new Image(Game.i.assetManager.getDrawable("icon-double-arrow-up"));
        image.setSize(40.0f, 40.0f);
        image.setPosition(20.0f, 20.0f);
        this.upgradeButton.addActor(image);
        this.w = new Label(Game.i.localeManager.i18n.get("do_upgrade"), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        this.w.setSize(100.0f, 40.0f);
        this.w.setPosition(80.0f, 20.0f);
        this.upgradeButton.addActor(this.w);
        this.x = new Label(Game.i.localeManager.i18n.get("click_to_confirm"), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.x.setSize(100.0f, 30.0f);
        this.x.setPosition(80.0f, 10.0f);
        this.x.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.upgradeButton.addActor(this.x);
        if (HotKeyHintLabel.isEnabled()) {
            HotKeyHintLabel hotKeyHintLabel = new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.UPGRADE_BUILDING), 12.0f, 59.0f, 8);
            hotKeyHintLabel.addVariant(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.UPGRADE_ALL_BUILDINGS));
            this.upgradeButton.addActor(hotKeyHintLabel);
        }
        this.H = new Image(Game.i.assetManager.getDrawable("button-hold-mark"));
        this.H.setSize(20.0f, 20.0f);
        this.H.setPosition(4.0f, 4.0f);
        this.upgradeButton.addActor(this.H);
        this.t = new Label(Game.i.localeManager.i18n.get("for_price_glue_word"), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.t.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.t.setSize(50.0f, 80.0f);
        this.t.setPosition(378.0f, 0.0f);
        this.t.setAlignment(1);
        addActor(this.t);
        this.v = new Image(Game.i.assetManager.getDrawable("game-ui-coin-icon"));
        this.v.setSize(40.0f, 40.0f);
        this.v.setPosition(436.0f, 20.0f);
        addActor(this.v);
        this.u = new Label("32123", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        this.u.setSize(132.0f, 80.0f);
        this.u.setPosition(428.0f, 0.0f);
        this.u.setAlignment(16);
        addActor(this.u);
        a(1, 1);
        setButtonSelected(false);
        g();
        d();
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public final void act(float f) {
        if (this.B) {
            this.F += f;
            if (this.F > 0.75f) {
                this.E = true;
                this.I.begin();
                int i = this.I.size;
                for (int i2 = 0; i2 < i; i2++) {
                    this.I.get(i2).globalUpgradeButtonConfirmed();
                }
                this.I.end();
                if (this.G != null) {
                    this.G.disappearing = true;
                    this.G = null;
                }
                this.B = false;
            }
        }
        super.act(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean e() {
        if (Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.I.begin();
        int i = this.I.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.I.get(i2).upgradeButtonConfirmed();
        }
        this.I.end();
    }

    public final void addListener(UpgradeSubmenuListener upgradeSubmenuListener) {
        if (upgradeSubmenuListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.I.contains(upgradeSubmenuListener, true)) {
            this.I.add(upgradeSubmenuListener);
        }
    }

    public final void removeListener(UpgradeSubmenuListener upgradeSubmenuListener) {
        if (upgradeSubmenuListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.I.removeValue(upgradeSubmenuListener, true);
    }

    public final void setButtonSelected(boolean z) {
        this.D = z;
        this.I.begin();
        int i = this.I.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.I.get(i2).upgradeButtonStateChanged(z);
        }
        this.I.end();
        d();
    }

    public final boolean isButtonSelected() {
        return this.A && this.D;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, int i2) {
        this.z = i;
        this.y = i2;
        J.setLength(0);
        J.append(i).append(" LVL");
        this.r.setText(J);
        g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(boolean z) {
        this.A = z;
        if (!z) {
            this.D = false;
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        if (i < 0) {
            this.t.setVisible(false);
            this.u.setText("MAX");
            this.v.setVisible(false);
        } else {
            this.t.setVisible(true);
            J.setLength(0);
            J.append(i);
            this.u.setText(J);
            this.v.setVisible(true);
        }
    }

    final void d() {
        this.H.setVisible(false);
        if (this.A) {
            this.H.setVisible(true);
            if (this.D) {
                if (this.B) {
                    this.s.setColor(l);
                } else {
                    this.s.setColor(k);
                }
            } else if (this.B) {
                this.s.setColor(o);
            } else if (this.C) {
                this.s.setColor(n);
            } else {
                this.s.setColor(m);
            }
        } else {
            this.s.setColor(p);
        }
        if (this.A && this.D) {
            this.x.setVisible(true);
            this.w.setY(30.0f);
        } else {
            this.x.setVisible(false);
            this.w.setY(20.0f);
        }
    }

    private void g() {
        for (int i = 0; i < this.q.length; i++) {
            if (i < this.z) {
                this.q[i].setVisible(true);
                this.q[i].setColor(Color.WHITE);
            } else if (i < this.y) {
                this.q[i].setVisible(true);
                this.q[i].setColor(1.0f, 1.0f, 1.0f, 0.14f);
            } else {
                this.q[i].setVisible(false);
            }
        }
    }
}
