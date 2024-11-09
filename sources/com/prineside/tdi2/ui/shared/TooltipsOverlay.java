package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.Widget;
import com.prineside.tdi2.scene2d.ui.WidgetGroup;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.UiUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/TooltipsOverlay.class */
public final class TooltipsOverlay extends UiManager.UiComponent.Adapter {
    public static final String TAG_GENERIC_TOOLTIP = "_generic_";
    public static final float PREF_WIDTH = 400.0f;
    public static final float MAX_WIDTH = 500.0f;

    /* renamed from: a, reason: collision with root package name */
    private final DelayedRemovalArray<Entry> f3751a = new DelayedRemovalArray<>(false, 1, Entry.class);

    public static TooltipsOverlay i() {
        return (TooltipsOverlay) Game.i.uiManager.getComponent(TooltipsOverlay.class);
    }

    public final Entry showActor(String str, @Null Actor actor, Actor actor2, UiManager.MainUiLayer mainUiLayer, int i, int i2) {
        Preconditions.checkNotNull(actor2, "contents can not be null");
        if (i2 != 2 && i2 != 4 && i2 != 8 && i2 != 16) {
            throw new IllegalArgumentException("align must be one of top, bottom, left or right");
        }
        hideEntry(str);
        Entry entry = new Entry(this, str, actor, actor2, mainUiLayer, i, i2, (byte) 0);
        this.f3751a.add(entry);
        return entry;
    }

    private void a() {
        this.f3751a.begin();
        for (int i = 0; i < this.f3751a.size; i++) {
            this.f3751a.get(i).a();
        }
        this.f3751a.end();
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public final void preRender(float f) {
        a();
    }

    public final void markTagShown(String str) {
        Preconditions.checkNotNull(str);
        if (SettingsPrefs.i().settings.shownTooltipTags.add(str)) {
            SettingsPrefs.i().requireSave();
        }
    }

    public final boolean isTagShown(String str) {
        return SettingsPrefs.i().settings.shownTooltipTags.contains(str);
    }

    public final Entry showText(String str, @Null Actor actor, CharSequence charSequence, UiManager.MainUiLayer mainUiLayer, int i, int i2) {
        Label label = new Label(charSequence, Game.i.assetManager.getLabelStyle(21));
        label.setWrap(true);
        label.setAlignment(1);
        return showActor(str, actor, label, mainUiLayer, i, i2);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        hideAll(false);
    }

    public final boolean isVisible(String str) {
        for (int i = 0; i < this.f3751a.size; i++) {
            if (str.equals(this.f3751a.get(i).f3752b)) {
                return true;
            }
        }
        return false;
    }

    public final void hideAll(boolean z) {
        this.f3751a.begin();
        for (int i = 0; i < this.f3751a.size; i++) {
            if (!z) {
                this.f3751a.get(i).onDispose = null;
            }
            this.f3751a.get(i).b();
        }
        this.f3751a.end();
        this.f3751a.clear();
    }

    public final void hideEntry(String str) {
        Preconditions.checkNotNull(str);
        this.f3751a.begin();
        int i = 0;
        while (true) {
            if (i >= this.f3751a.size) {
                break;
            }
            if (!str.equals(this.f3751a.get(i).f3752b)) {
                i++;
            } else {
                this.f3751a.get(i).b();
                this.f3751a.removeIndex(i);
                break;
            }
        }
        this.f3751a.end();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/TooltipsOverlay$Entry.class */
    public final class Entry {

        /* renamed from: b, reason: collision with root package name */
        private String f3752b;

        @Null
        private Actor c;
        private Table d;
        private final Image e;
        private final Image f;
        private final Table g;
        private boolean h;
        private int i;
        private UiManager.UiLayer j;
        private final Vector2 k;
        public Runnable onDispose;

        /* synthetic */ Entry(TooltipsOverlay tooltipsOverlay, String str, Actor actor, Actor actor2, UiManager.MainUiLayer mainUiLayer, int i, int i2, byte b2) {
            this(str, actor, actor2, mainUiLayer, i, i2);
        }

        private Entry(@Null String str, @Null Actor actor, Actor actor2, UiManager.MainUiLayer mainUiLayer, int i, int i2) {
            this.k = new Vector2();
            str = str == null ? TooltipsOverlay.TAG_GENERIC_TOOLTIP : str;
            this.f3752b = str;
            this.i = i2;
            this.c = actor;
            this.j = Game.i.uiManager.addLayer(mainUiLayer, i, "tooltips_overlay_" + str);
            this.j.getTable().setTouchable(Touchable.childrenOnly);
            Quad quad = Game.i.assetManager.getQuad("ui.tooltips.background");
            Quad quad2 = Game.i.assetManager.getQuad("ui.tooltips.overlay");
            Quad quad3 = Game.i.assetManager.getQuad("ui.tooltips.glow");
            long timestampMillis = Game.getTimestampMillis();
            this.d = new Table();
            this.d.background(quad);
            this.d.setTouchable(Touchable.enabled);
            this.d.addListener(new ClickListener(TooltipsOverlay.this, timestampMillis) { // from class: com.prineside.tdi2.ui.shared.TooltipsOverlay.Entry.1

                /* renamed from: a, reason: collision with root package name */
                private /* synthetic */ long f3754a;

                {
                    this.f3754a = timestampMillis;
                }

                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    if (Game.getTimestampMillis() - this.f3754a > 500) {
                        TooltipsOverlay.this.markTagShown(Entry.this.f3752b);
                        TooltipsOverlay.this.hideEntry(Entry.this.f3752b);
                    }
                }
            });
            Cell padTop = this.d.add((Table) actor2).maxWidth(500.0f).padLeft(quad.getLeftWidth()).padRight(quad.getRightWidth()).padTop(quad.getTopHeight());
            if (actor2.getWidth() < 5.0f || (((actor2 instanceof Widget) && ((Widget) actor2).getPrefWidth() < 5.0f) || ((actor2 instanceof WidgetGroup) && ((WidgetGroup) actor2).getPrefWidth() < 5.0f))) {
                if (actor2 instanceof Label) {
                    Label label = (Label) actor2;
                    if (label.getWrap()) {
                        label.setWrap(false);
                        label.layout();
                        float prefWidth = label.getPrefWidth();
                        label.setWrap(true);
                        label.layout();
                        padTop.prefWidth((int) (prefWidth + 2.0f));
                    }
                } else {
                    padTop.prefWidth(400.0f);
                }
            }
            padTop.row();
            this.g = new Table();
            this.d.add(this.g).center().padTop(8.0f).padBottom(quad.getBottomHeight()).fillX();
            Label label2 = new Label(Game.i.localeManager.i18n.get("click_to_close"), Game.i.assetManager.getLabelStyle(18));
            label2.setColor(1.0f, 1.0f, 1.0f, 0.0f);
            label2.addAction(Actions.sequence(Actions.delay(0.4f), Actions.alpha(0.3f, 0.6f), Actions.forever(Actions.sequence(Actions.alpha(0.6f, 0.3f), Actions.alpha(0.3f, 0.7f)))));
            this.g.add((Table) label2);
            Image image = new Image(quad2);
            image.setFillParent(true);
            this.d.addActor(image);
            Image image2 = new Image(quad3);
            image2.setFillParent(true);
            image2.setColor(1.0f, 1.0f, 1.0f, 0.0f);
            image2.addAction(Actions.forever(Actions.sequence(Actions.alpha(1.0f, 0.3f), Actions.alpha(0.0f, 0.7f))));
            this.d.addActor(image2);
            this.e = new Image();
            this.d.addActor(this.e);
            this.f = new Image();
            this.f.setColor(1.0f, 1.0f, 1.0f, 0.0f);
            this.f.addAction(Actions.forever(Actions.sequence(Actions.alpha(1.0f, 0.3f), Actions.alpha(0.0f, 0.7f))));
            this.d.addActor(this.f);
            this.d.setTransform(true);
            this.d.setScale(0.0f);
            this.d.setVisible(false);
            this.h = true;
            this.j.getTable().addActor(this.d);
        }

        public final void setTargetPoint(float f, float f2) {
            this.k.set(f, f2);
        }

        public final void setTargetActor(@Null Actor actor) {
            this.c = actor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            Quad quad;
            Quad quad2;
            float f;
            float minHeight;
            float minWidth;
            float f2;
            if (this.d != null) {
                if (this.c != null) {
                    if (this.c.getStage() == null) {
                        TooltipsOverlay.this.hideEntry(this.f3752b);
                        return;
                    }
                    if (!UiUtils.isVisibleRecursive(this.c)) {
                        if (this.d.isVisible()) {
                            this.d.setVisible(false);
                            return;
                        }
                        return;
                    }
                    this.k.setZero();
                    this.c.localToStageCoordinates(this.k);
                    if (this.i == 8 || this.i == 16) {
                        this.k.y += this.c.getHeight() * 0.5f;
                        if (this.i == 16) {
                            this.k.x += this.c.getWidth();
                        }
                    } else {
                        this.k.x += this.c.getWidth() * 0.5f;
                        if (this.i == 2) {
                            this.k.y += this.c.getHeight();
                        }
                    }
                }
                this.d.layout();
                this.d.pack();
                float prefWidth = this.d.getPrefWidth();
                float prefHeight = this.d.getPrefHeight();
                float x = this.k.x - this.j.getTable().getX();
                float f3 = this.k.y;
                switch (this.i) {
                    case 2:
                        quad = Game.i.assetManager.getQuad("ui.tooltips.arrowDown");
                        quad2 = Game.i.assetManager.getQuad("ui.tooltips.arrowDownGlow");
                        f = x - (prefWidth * 0.5f);
                        minHeight = f3 + quad.getMinHeight();
                        minWidth = (prefWidth * 0.5f) - (quad.getMinWidth() * 0.5f);
                        f2 = -quad.getMinHeight();
                        break;
                    case 8:
                        quad = Game.i.assetManager.getQuad("ui.tooltips.arrowRight");
                        quad2 = Game.i.assetManager.getQuad("ui.tooltips.arrowRightGlow");
                        f = x - (prefWidth + quad.getMinWidth());
                        minHeight = f3 - (prefHeight * 0.5f);
                        minWidth = prefWidth;
                        f2 = (prefHeight * 0.5f) - (quad.getMinHeight() * 0.5f);
                        break;
                    case 16:
                        quad = Game.i.assetManager.getQuad("ui.tooltips.arrowLeft");
                        quad2 = Game.i.assetManager.getQuad("ui.tooltips.arrowLeftGlow");
                        f = x + quad.getMinWidth();
                        minHeight = f3 - (prefHeight * 0.5f);
                        minWidth = -quad.getMinWidth();
                        f2 = (prefHeight * 0.5f) - (quad.getMinHeight() * 0.5f);
                        break;
                    default:
                        quad = Game.i.assetManager.getQuad("ui.tooltips.arrowUp");
                        quad2 = Game.i.assetManager.getQuad("ui.tooltips.arrowUpGlow");
                        f = x - (prefWidth * 0.5f);
                        minHeight = f3 - (prefHeight + quad.getMinHeight());
                        minWidth = (prefWidth * 0.5f) - (quad.getMinWidth() * 0.5f);
                        f2 = prefHeight;
                        break;
                }
                float width = this.j.getTable().getWidth();
                float height = this.j.getTable().getHeight();
                if (this.i == 8 || this.i == 16) {
                    float f4 = 0.0f;
                    if (minHeight < 40.0f) {
                        f4 = 40.0f - minHeight;
                    } else if (minHeight + prefHeight > height - 40.0f) {
                        f4 = (height - 40.0f) - (minHeight + prefHeight);
                    }
                    minHeight += f4;
                    f2 = MathUtils.clamp(f2 - f4, 0.0f, prefHeight - quad.getMinHeight());
                } else {
                    float f5 = 0.0f;
                    if (f < 40.0f) {
                        f5 = 40.0f - f;
                    } else if (f + prefWidth > width - 40.0f) {
                        f5 = (width - 40.0f) - (f + prefWidth);
                    }
                    f += f5;
                    minWidth = MathUtils.clamp(minWidth - f5, 0.0f, prefWidth - quad.getMinWidth());
                }
                this.e.setDrawable(quad);
                this.e.setSize(quad.getMinWidth(), quad.getMinHeight());
                this.e.setPosition(minWidth, f2);
                this.f.setDrawable(quad2);
                this.f.setSize(quad.getMinWidth(), quad.getMinHeight());
                this.f.setPosition(minWidth, f2);
                this.d.setPosition(f, minHeight);
                if (!this.d.isVisible()) {
                    this.d.setVisible(true);
                }
                if (this.h) {
                    this.d.setOrigin(1);
                    this.d.addAction(Actions.sequence(Actions.scaleTo(0.0f, 1.1f), Actions.delay(0.05f), Actions.parallel(Actions.sequence(Actions.scaleBy(1.1f, 0.0f, 0.2f, Interpolation.pow2Out), Actions.scaleBy(-0.1f, 0.0f, 0.1f, Interpolation.pow2)), Actions.sequence(Actions.scaleBy(0.0f, -0.2f, 0.2f, Interpolation.pow2Out), Actions.scaleBy(0.0f, 0.1f, 0.1f, Interpolation.pow2))), Actions.run(() -> {
                        this.d.setTransform(false);
                    })));
                    Quad quad3 = Game.i.assetManager.getQuad("ui.tooltips.background");
                    Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image.setColor(MaterialColor.BLUE_GREY.P300);
                    image.setSize(14.0f, 14.0f);
                    image.setPosition(((prefWidth / 2.0f) - quad3.getLeftWidth()) - 7.0f, -20.0f);
                    image.addAction(Actions.sequence(Actions.delay(0.1f), Actions.parallel(Actions.moveBy(0.0f, 22.0f, 0.4f, Interpolation.swingOut)), Actions.delay(0.05f), Actions.parallel(Actions.alpha(0.0f, 0.8f, Interpolation.pow2Out), Actions.moveTo(-quad3.getLeftWidth(), 9.0f, 0.8f, Interpolation.pow2Out), Actions.sizeTo(prefWidth, 0.0f, 0.8f, Interpolation.pow2Out), Actions.scaleTo(1.0f, 0.0f, 0.8f))));
                    this.g.addActor(image);
                    this.h = false;
                }
            }
        }

        public final boolean exists() {
            return this.d != null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            if (this.d != null) {
                if (this.onDispose != null) {
                    Threads.i().postRunnable(this.onDispose);
                }
                this.onDispose = null;
                this.d.remove();
                this.d = null;
                this.c = null;
                this.f3752b = null;
                Game.i.uiManager.removeLayer(this.j);
                this.j = null;
            }
        }
    }
}
