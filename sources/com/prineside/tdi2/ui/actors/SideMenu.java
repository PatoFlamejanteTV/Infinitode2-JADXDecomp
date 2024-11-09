package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.utils.InputListenerExtended;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/SideMenu.class */
public class SideMenu implements Disposable {
    public static final float DEFAULT_WIDTH = 600.0f;
    public static final float TOGGLE_BUTTON_WIDTH = 140.0f;

    /* renamed from: a, reason: collision with root package name */
    private final float f3236a;

    /* renamed from: b, reason: collision with root package name */
    private final Group f3237b;
    private final Group c;
    private final Group d;
    private final Image e;
    private final PaddedImageButton f;
    private final Group g;
    private float h;
    private final Label i;
    private final Image j;
    public Image sideShadow;
    private boolean l;
    private boolean m;
    private final Drawable n;
    private final Drawable o;
    private final Runnable p;
    private final Runnable q;
    public final UiManager.UiLayer uiLayer = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 105, "SideMenu");
    private final Array<SideMenuContainer> k = new Array<>();
    private final DelayedRemovalArray<SideMenuListener> r = new DelayedRemovalArray<>();

    public SideMenu(float f) {
        this.f3236a = f;
        Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.childrenOnly);
        this.uiLayer.getTable().add((Table) group).width(f + 140.0f).height(Game.i.settingsManager.getScaledViewportHeight()).expand().bottom().right();
        this.f3237b = new Group();
        this.f3237b.setSize(f + 140.0f, Game.i.settingsManager.getScaledViewportHeight());
        this.f3237b.setTransform(true);
        this.f3237b.setTouchable(Touchable.childrenOnly);
        group.addActor(this.f3237b);
        this.sideShadow = new Image(Game.i.assetManager.getQuad("ui.actors.sideMenu.sideShadow"));
        this.sideShadow.setSize(7.0f, Game.i.settingsManager.getScaledViewportHeight());
        this.sideShadow.setPosition(133.0f, 0.0f);
        this.f3237b.addActor(this.sideShadow);
        this.e = new Image(Game.i.assetManager.getDrawable("ui-tile-menu-toggle-button"));
        this.e.setSize(180.0f, 196.0f);
        this.f3237b.addActor(this.e);
        this.c = new Group();
        this.c.setTransform(false);
        this.c.setSize(f, Game.i.settingsManager.getScaledViewportHeight());
        this.c.setPosition(140.0f, 0.0f);
        this.f3237b.addActor(this.c);
        this.g = new Group();
        this.g.setSize(288.0f, 173.0f);
        this.g.setPosition(-148.0f, 0.0f);
        this.f3237b.addActor(this.g);
        this.g.setTouchable(Touchable.enabled);
        this.g.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.actors.SideMenu.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f2, float f3) {
                SideMenu.this.hideSideTooltip();
                SideMenu.this.r.begin();
                for (int i = 0; i < SideMenu.this.r.size; i++) {
                    ((SideMenuListener) SideMenu.this.r.get(i)).sideTooltipHidden();
                }
                SideMenu.this.r.end();
            }
        });
        this.j = new Image(Game.i.assetManager.getQuad("ui.actors.sideMenu.sideTooltip"));
        this.g.addActor(this.j);
        this.i = new Label("Side tooltip", Game.i.assetManager.getLabelStyle(24));
        this.i.setSize(238.0f, 173.0f);
        this.i.setPosition(20.0f, 0.0f);
        this.i.setWrap(true);
        this.i.setAlignment(1);
        this.g.addActor(this.i);
        hideSideTooltip();
        this.n = Game.i.assetManager.getDrawable("icon-triangle-right-hollow");
        this.o = Game.i.assetManager.getDrawable("icon-triangle-left-hollow");
        this.f = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-right-hollow"), () -> {
            setOffscreen(!this.m);
        }, Color.WHITE, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P600);
        this.f.setName("side_menu_toggle_button");
        this.f.setSize(140.0f, 196.0f);
        this.f.setTouchable(Touchable.enabled);
        this.f.setIconSize(64.0f, 64.0f);
        this.f.setIconPosition(32.0f, 48.0f);
        this.f3237b.addActor(this.f);
        if (HotKeyHintLabel.isEnabled()) {
            this.f.addActor(new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.TOGGLE_TILE_MENU), 64.0f, 12.0f));
        }
        this.d = new Group();
        this.d.setSize(f, Game.i.settingsManager.getScaledViewportHeight());
        this.d.setPosition(140.0f, 0.0f);
        this.d.setTransform(false);
        this.d.setTouchable(Touchable.enabled);
        this.d.addListener(new InputListenerExtended(this) { // from class: com.prineside.tdi2.ui.actors.SideMenu.2
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                return true;
            }
        }.setMode(InputEvent.Type.touchDragged, 2));
        this.f3237b.addActor(this.d);
        this.p = () -> {
            this.d.setVisible(false);
            this.c.setVisible(false);
            this.r.begin();
            int i = this.r.size;
            for (int i2 = 0; i2 < i; i2++) {
                this.r.get(i2).offscreenChanged();
            }
            this.r.end();
        };
        this.q = () -> {
            this.d.setVisible(true);
            this.c.setVisible(true);
        };
        setVisible(true);
        this.m = true;
        setOffscreen(false);
    }

    public Group getWrapper() {
        return this.f3237b;
    }

    public void addOffscreenBackground() {
        if (Game.i.settingsManager.getScaledViewportHeight() > 0) {
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setColor(new Color(387389371));
            image.setSize(Game.i.uiManager.getScreenSafeMargin(), Game.i.settingsManager.getScaledViewportHeight());
            image.setPosition(this.f3236a, 0.0f);
            getBackgroundContainer().addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-left"));
            image2.setSize(10.0f, Game.i.settingsManager.getScaledViewportHeight());
            image2.setPosition(this.f3236a, 0.0f);
            image2.setColor(0.0f, 0.0f, 0.0f, 0.14f);
            getBackgroundContainer().addActor(image2);
        }
    }

    public void finalFadeOut() {
        setOffscreen(true);
        this.uiLayer.getTable().setTouchable(Touchable.disabled);
        this.uiLayer.getTable().clearActions();
        this.uiLayer.getTable().addAction(Actions.alpha(0.0f, 1.0f));
    }

    public Group getBackgroundContainer() {
        return this.c;
    }

    public void showSideTooltip(CharSequence charSequence, float f) {
        if (this.g.isVisible() && this.i.textEquals(charSequence) && this.h == f) {
            return;
        }
        this.i.setText(charSequence);
        this.i.layout();
        this.i.validate();
        float f2 = 173.0f;
        float prefHeight = this.i.getPrefHeight() + 24.0f;
        if (prefHeight > 173.0f) {
            f2 = prefHeight;
        }
        this.g.setSize(this.g.getWidth(), f2);
        this.g.setVisible(true);
        this.g.setY(f - (f2 / 2.0f));
        this.j.setSize(288.0f, f2);
        this.i.setSize(238.0f, f2);
        this.i.setPosition(20.0f, 0.0f);
        this.h = f;
    }

    public void hideSideTooltip() {
        this.g.setVisible(false);
    }

    public void addListener(SideMenuListener sideMenuListener) {
        if (sideMenuListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.r.contains(sideMenuListener, true)) {
            this.r.add(sideMenuListener);
        }
    }

    public void removeListener(SideMenuListener sideMenuListener) {
        if (sideMenuListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.r.removeValue(sideMenuListener, true);
    }

    public void setVisible(boolean z) {
        if (this.l != z) {
            this.l = z;
            this.r.begin();
            for (int i = 0; i < this.r.size; i++) {
                this.r.get(i).visibilityChanged();
            }
            this.r.end();
        }
        this.uiLayer.getTable().setVisible(z);
    }

    public void setOffscreen(boolean z) {
        if (this.m != z) {
            this.m = z;
            if (z) {
                this.f3237b.clearActions();
                this.r.begin();
                int i = this.r.size;
                for (int i2 = 0; i2 < i; i2++) {
                    this.r.get(i2).offscreenStartingToChange();
                }
                this.r.end();
                this.f.clearActions();
                this.e.clearActions();
                int screenSafeMargin = Game.i.uiManager.getScreenSafeMargin();
                if (Game.i.settingsManager.isUiAnimationsEnabled()) {
                    this.f3237b.addAction(Actions.sequence(Actions.moveTo(this.f3236a + screenSafeMargin, 0.0f, 0.2f, Interpolation.exp5Out), Actions.run(this.p)));
                    this.f.addAction(Actions.moveTo(-screenSafeMargin, 0.0f, 0.2f));
                    this.e.addAction(Actions.moveTo(-screenSafeMargin, 0.0f, 0.2f));
                } else {
                    this.f3237b.setPosition(this.f3236a + screenSafeMargin, 0.0f);
                    this.f.setPosition(-screenSafeMargin, 0.0f);
                    this.e.setPosition(-screenSafeMargin, 0.0f);
                    this.p.run();
                }
                this.f.setIcon(this.o);
                return;
            }
            this.f3237b.clearActions();
            this.q.run();
            this.f.clearActions();
            this.e.clearActions();
            if (Game.i.settingsManager.isUiAnimationsEnabled()) {
                this.f3237b.addAction(Actions.moveTo(0.0f, 0.0f, 0.2f, Interpolation.exp5Out));
                this.f.addAction(Actions.moveTo(0.0f, 0.0f, 0.2f));
                this.e.addAction(Actions.moveTo(0.0f, 0.0f, 0.2f));
            } else {
                this.f3237b.setPosition(0.0f, 0.0f);
                this.f.setPosition(0.0f, 0.0f);
                this.e.setPosition(0.0f, 0.0f);
            }
            this.f.setIcon(this.n);
            this.r.begin();
            int i3 = this.r.size;
            for (int i4 = 0; i4 < i3; i4++) {
                this.r.get(i4).offscreenStartingToChange();
            }
            this.r.end();
            this.r.begin();
            int i5 = this.r.size;
            for (int i6 = 0; i6 < i5; i6++) {
                this.r.get(i6).offscreenChanged();
            }
            this.r.end();
        }
    }

    public boolean isVisible() {
        return this.l;
    }

    public boolean isOffscreen() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Array.ArrayIterator<SideMenuContainer> it = this.k.iterator();
        while (it.hasNext()) {
            if (it.next().k) {
                setVisible(true);
                return;
            }
        }
        setVisible(false);
    }

    public SideMenuContainer createContainer(String str) {
        SideMenuContainer sideMenuContainer = new SideMenuContainer(this, str);
        sideMenuContainer.setSize(this.d.getWidth(), this.d.getHeight());
        this.d.addActor(sideMenuContainer);
        this.k.add(sideMenuContainer);
        sideMenuContainer.setVisible(false);
        return sideMenuContainer;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.uiLayer);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/SideMenu$SideMenuContainer.class */
    public static class SideMenuContainer extends Group {
        boolean k;
        private final SideMenu l;
        public Label hintLabel;
        public final String name;
        public static StringBuilder sb = new StringBuilder();

        public SideMenuContainer(SideMenu sideMenu, String str) {
            setTransform(false);
            this.name = str;
            this.l = sideMenu;
            setName("SMC-" + str);
        }

        public void setLabelOverTitleTilePos(Tile tile) {
            if (tile == null) {
                return;
            }
            sb.setLength(0);
            sb.append(tile.getX()).append(":").append(tile.getY());
            setLabelOverTitle(sb);
        }

        public void setLabelOverTitle(CharSequence charSequence) {
            if (this.hintLabel != null) {
                this.hintLabel.remove();
            }
            this.hintLabel = new Label(charSequence, Game.i.assetManager.getLabelStyle(24));
            this.hintLabel.setSize(100.0f, 26.0f);
            this.hintLabel.setPosition(460.0f, Game.i.settingsManager.getScaledViewportHeight() - 54.0f);
            this.hintLabel.setAlignment(16);
            this.hintLabel.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            addActor(this.hintLabel);
        }

        public void show() {
            super.setVisible(true);
            this.k = true;
            this.l.a();
        }

        public void hide() {
            super.setVisible(false);
            this.k = false;
            this.l.a();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/SideMenu$SideMenuListener.class */
    public interface SideMenuListener {
        void offscreenChanged();

        void offscreenStartingToChange();

        void visibilityChanged();

        void sideTooltipHidden();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/SideMenu$SideMenuListener$SideMenuListenerAdapter.class */
        public static class SideMenuListenerAdapter implements SideMenuListener {
            @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
            public void offscreenChanged() {
            }

            @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
            public void offscreenStartingToChange() {
            }

            @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
            public void visibilityChanged() {
            }

            @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
            public void sideTooltipHidden() {
            }
        }
    }
}
