package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.math.Interpolation;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.Resource;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.PreferencesManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.PurchaseManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.screens.TicTacToeScreen;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/ResourcesAndMoney.class */
public final class ResourcesAndMoney implements UiManager.UiComponent {

    /* renamed from: a, reason: collision with root package name */
    private static final Color f3737a = new Color(168430267);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3738b = MaterialColor.GREEN.P800;
    private static final Color c = MaterialColor.GREEN.P700;
    private static final Color d = MaterialColor.GREEN.P900;
    private final Group f;
    private final Label g;
    private final Table h;
    private final Image i;
    private final Image j;
    private final Image k;
    private final Label.LabelStyle l;
    private boolean o;
    private final UiManager.UiLayer e = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SHARED_COMPONENTS, 100, "ResourcesAndMoney");
    private float m = 0.0f;
    private int n = -1;

    static /* synthetic */ boolean a(ResourcesAndMoney resourcesAndMoney, boolean z) {
        resourcesAndMoney.o = true;
        return true;
    }

    public static ResourcesAndMoney i() {
        return (ResourcesAndMoney) Game.i.uiManager.getComponent(ResourcesAndMoney.class);
    }

    public ResourcesAndMoney() {
        this.o = false;
        Group group = new Group();
        group.setTransform(false);
        this.e.getTable().add((Table) group).expand().top().right().size(1200.0f, 80.0f);
        boolean z = false;
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("exit_game_confirm"), Game::exit);
            });
            complexButton.setBackground(Game.i.assetManager.getDrawable("ui-top-bar-exit"), 0.0f, 0.0f, 139.0f, 88.0f);
            complexButton.setBackgroundColors(new Color(673720575), new Color(-2112218369), new Color(-1255922433), Color.GRAY);
            complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 46.0f, 26.0f, 40.0f, 40.0f);
            Color color = Color.WHITE;
            Color color2 = Color.WHITE;
            complexButton.setIconColors(color, color, color2, color2);
            this.e.getTable().add((Table) complexButton).top().right().size(128.0f, 88.0f).padLeft(-5.0f);
            z = true;
        }
        this.l = new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE);
        this.f = new Group();
        this.f.setTransform(false);
        this.f.setSize(1200.0f, 80.0f);
        group.addActor(this.f);
        Group group2 = new Group() { // from class: com.prineside.tdi2.ui.shared.ResourcesAndMoney.1
            @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
            public void act(float f) {
                super.act(f);
                if (ResourcesAndMoney.this.i != null) {
                    ResourcesAndMoney.this.i.setVisible(Game.i.purchaseManager.canShowRewardingAd(PurchaseManager.RewardingAdsType.REGULAR));
                }
            }
        };
        group2.setTransform(false);
        group2.setSize(273.0f, 88.0f);
        group2.setPosition(927.0f, -8.0f);
        group.addActor(group2);
        final Image image = new Image(Game.i.assetManager.getTextureRegion("ui-top-bar-money"));
        image.setColor(f3738b);
        image.setSize(283.0f, 88.0f);
        group2.addActor(image);
        this.j = new Image(Game.i.assetManager.getTextureRegion("icon-money"));
        this.j.setSize(48.0f, 48.0f);
        this.j.setOrigin(24.0f, 4.0f);
        this.j.setPosition(209.0f, 24.0f);
        this.j.setColor(Color.WHITE);
        group2.addActor(this.j);
        this.k = new Image(Game.i.assetManager.getDrawable("count-bubble"));
        this.k.setSize(21.5f, 24.5f);
        this.k.setPosition(249.0f, 56.0f);
        this.k.setVisible(false);
        group2.addActor(this.k);
        Game.i.preferencesManager.addListener(new PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter() { // from class: com.prineside.tdi2.ui.shared.ResourcesAndMoney.2
            @Override // com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter, com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener
            public void reloaded() {
                ResourcesAndMoney.a(ResourcesAndMoney.this, true);
            }
        });
        Game.i.screenManager.addListener(() -> {
            this.o = true;
        });
        this.g = new Label("0", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        this.g.setAlignment(16);
        this.g.setSize(193.0f, 80.0f);
        this.g.setPosition(0.0f, 8.0f);
        group2.addActor(this.g);
        this.h = new Table();
        this.h.setSize(273.0f, 24.0f);
        if (z) {
            this.h.setPosition(1050.0f, -40.0f);
        } else {
            this.h.setPosition(927.0f, -40.0f);
        }
        group.addActor(this.h);
        this.i = new Image(Game.i.assetManager.getDrawable("ui-top-bar-ad-available"));
        this.i.setSize(64.0f, 59.0f);
        this.i.setPosition(-1.0f, 14.0f);
        this.i.setVisible(false);
        this.i.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(0.85f, 0.85f, 0.85f, 1.0f), 0.5f, Interpolation.exp5In), Actions.color(Color.WHITE, 0.5f, Interpolation.exp5Out))));
        group2.addActor(this.i);
        group2.setTouchable(Touchable.enabled);
        group2.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.ResourcesAndMoney.3

            /* renamed from: a, reason: collision with root package name */
            private boolean f3740a = false;

            /* renamed from: b, reason: collision with root package name */
            private boolean f3741b = false;

            private void a() {
                if (this.f3740a) {
                    image.setColor(ResourcesAndMoney.d);
                } else if (this.f3741b) {
                    image.setColor(ResourcesAndMoney.c);
                } else {
                    image.setColor(ResourcesAndMoney.f3738b);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Game.i.screenManager.goToMoneyScreen();
                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                this.f3740a = true;
                a();
                return super.touchDown(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                this.f3740a = false;
                a();
                super.touchUp(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    this.f3741b = true;
                    a();
                }
                super.enter(inputEvent, f, f2, i, actor);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    this.f3741b = false;
                    a();
                }
                super.exit(inputEvent, f, f2, i, actor);
            }
        });
        Game.i.cursorGraphics.setActorCustomMouseCursor(group2, Cursor.SystemCursor.Hand);
        this.o = true;
        setVisible(false);
        Game.i.progressManager.addListener(new ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter() { // from class: com.prineside.tdi2.ui.shared.ResourcesAndMoney.4
            @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter, com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
            public void itemsChanged(Item item, int i, int i2) {
                if (item.getType() == ItemType.RESOURCE || item.getType() == ItemType.GREEN_PAPER || item.getType() == ItemType.ACCELERATOR) {
                    ResourcesAndMoney.a(ResourcesAndMoney.this, true);
                }
            }
        });
    }

    public final void updateBoosts() {
        int itemsCount = Game.i.progressManager.getItemsCount(Item.D.RARITY_BOOST);
        int lootBoostTimeLeft = (int) Game.i.progressManager.getLootBoostTimeLeft();
        int i = (itemsCount * 31) + lootBoostTimeLeft;
        if (this.n != i) {
            this.h.clear();
            this.h.add().height(1.0f).expandX().fillX();
            if (itemsCount > 0) {
                this.h.add((Table) new Image(Game.i.assetManager.getDrawable("rarity-token"))).size(24.0f, 24.0f).padRight(5.0f);
                Label label = new Label("x" + itemsCount, Game.i.assetManager.getLabelStyle(21));
                label.setColor(MaterialColor.AMBER.P500);
                this.h.add((Table) label).height(24.0f).padRight(16.0f);
            }
            if (lootBoostTimeLeft > 0) {
                this.h.add((Table) new Image(Game.i.assetManager.getDrawable("loot-token"))).size(24.0f, 24.0f).padRight(5.0f);
                Label label2 = new Label(StringFormatter.digestTime(lootBoostTimeLeft), Game.i.assetManager.getLabelStyle(21));
                label2.setColor(MaterialColor.LIGHT_GREEN.P400);
                this.h.add((Table) label2).height(24.0f).padRight(16.0f);
            }
            this.h.add().height(1.0f).width(8.0f);
            this.n = i;
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void preRender(float f) {
        if (this.o) {
            d();
            this.o = false;
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        setVisible(false);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final boolean isPersistent() {
        return false;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void postRender(float f) {
        if (this.e.getTable().isVisible()) {
            this.m += f;
            if (this.m >= 1.0f) {
                this.m = 0.0f;
                updateBoosts();
            }
        }
    }

    private void d() {
        this.g.setText(String.format(Locale.ENGLISH, "%,d", Integer.valueOf(Game.i.progressManager.getGreenPapers())));
        if (Game.i.progressManager.getGreenPapers() < 10000000) {
            this.g.setStyle(Game.i.assetManager.getLabelStyle(30));
        } else {
            this.g.setStyle(Game.i.assetManager.getLabelStyle(24));
        }
        if (ProgressPrefs.i().progress.getVideosWatchedForDoubleGain() >= 500 || ProgressPrefs.i().progress.getVideosWatchedForLuckyShot() >= 20) {
            this.k.setVisible(true);
            this.j.setDrawable(Game.i.assetManager.getDrawable("icon-gift"));
            this.j.clearActions();
            this.j.setPosition(209.0f, 24.0f);
            this.j.addAction(Actions.sequence(Actions.sequence(Actions.scaleTo(1.0f, 1.0f)), Actions.forever(Actions.sequence(Actions.scaleTo(1.2f, 0.8f, 0.2f, Interpolation.fastSlow), Actions.parallel(Actions.sequence(Actions.scaleTo(0.8f, 1.2f, 0.2f, Interpolation.sine), Actions.scaleTo(1.1f, 0.9f, 0.2f, Interpolation.sine), Actions.scaleTo(1.0f, 1.0f, 0.1f, Interpolation.sine)), Actions.sequence(Actions.moveBy(0.0f, 12.0f, 0.2f, Interpolation.pow2Out), Actions.moveBy(0.0f, -12.0f, 0.2f, Interpolation.pow2In))), Actions.delay(1.0f)))));
        } else {
            this.j.clearActions();
            this.j.setPosition(209.0f, 24.0f);
            this.j.setDrawable(Game.i.assetManager.getDrawable("icon-money"));
            this.k.setVisible(false);
        }
        this.f.clearChildren();
        Image image = new Image(Game.i.assetManager.getBlankWhiteTextureRegion());
        image.setColor(f3737a);
        image.setSize(280.0f, 80.0f);
        float f = 920.0f;
        image.setPosition(920.0f, 0.0f);
        this.f.addActor(image);
        for (int length = ResourceType.values.length - 1; length >= 0; length--) {
            ResourceType resourceType = ResourceType.values[length];
            if (Game.i.progressManager.isResourceOpened(resourceType)) {
                Image image2 = new Image(Game.i.assetManager.getBlankWhiteTextureRegion());
                image2.setColor(f3737a);
                image2.setSize(136.0f, 80.0f);
                f -= 136.0f;
                image2.setPosition(f, 0.0f);
                this.f.addActor(image2);
                Group group = new Group();
                group.setTransform(false);
                group.setSize(150.0f, 80.0f);
                group.setPosition(f, 0.0f);
                this.f.addActor(group);
                Image image3 = new Image(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[resourceType.ordinal()]));
                image3.setSize(48.0f, 48.0f);
                image3.setColor(Game.i.resourceManager.getColor(resourceType));
                image3.setPosition(16.0f, 16.0f);
                group.addActor(image3);
                Label label = new Label(StringFormatter.compactNumber(Game.i.progressManager.getResources(resourceType), false), this.l);
                label.setColor(Game.i.resourceManager.getColor(resourceType));
                label.setSize(62.0f, 80.0f);
                label.setPosition(72.0f, 0.0f);
                group.addActor(label);
            }
        }
        QuadActor quadActor = new QuadActor(Color.WHITE, new float[]{28.0f, 0.0f, 0.0f, 80.0f, 38.0f, 80.0f, 38.0f, 0.0f});
        quadActor.setColor(f3737a);
        quadActor.setSize(38.0f, 80.0f);
        float f2 = f - 38.0f;
        quadActor.setPosition(f2, 0.0f);
        this.f.addActor(quadActor);
        Image image4 = new Image(Game.i.assetManager.getDrawable("time-accelerator"));
        image4.setColor(MaterialColor.YELLOW.P500);
        image4.setSize(48.0f, 48.0f);
        image4.setPosition(f2 - 136.0f, 16.0f);
        image4.setTouchable(Touchable.enabled);
        final int[] iArr = {0};
        image4.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.ResourcesAndMoney.5
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f3, float f4) {
                int[] iArr2 = iArr;
                iArr2[0] = iArr2[0] + 1;
                if (iArr[0] == 10) {
                    Game.i.screenManager.setScreen(new TicTacToeScreen());
                }
            }
        });
        this.f.addActor(image4);
        Label label2 = new Label(StringFormatter.compactNumber(Game.i.progressManager.getAccelerators(), false), this.l);
        label2.setColor(MaterialColor.YELLOW.P500);
        label2.setSize(64.0f, 80.0f);
        label2.setAlignment(1);
        label2.setPosition((f2 - 136.0f) + 64.0f, 0.0f);
        this.f.addActor(label2);
        updateBoosts();
    }

    public final void setVisible(boolean z) {
        this.e.getTable().setVisible(z);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public final void dispose() {
    }
}
