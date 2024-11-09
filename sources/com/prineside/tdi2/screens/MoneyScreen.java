package com.prineside.tdi2.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.pay.Information;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Research;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.items.CaseItem;
import com.prineside.tdi2.items.CaseKeyItem;
import com.prineside.tdi2.items.DoubleGainShardItem;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.PurchaseManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Progress;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.AttentionRaysUnderlay;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.HighlightActor;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.ParticlesCanvas;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.TableButton;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.InventoryOverlay;
import com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay;
import com.prineside.tdi2.ui.shared.ItemDescriptionDialog;
import com.prineside.tdi2.ui.shared.LuckyWheelOverlay;
import com.prineside.tdi2.ui.shared.ResourcesAndMoney;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.InputVoid;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/MoneyScreen.class */
public class MoneyScreen extends Screen {

    /* renamed from: b, reason: collision with root package name */
    private com.badlogic.gdx.Screen f2793b;
    private ComplexButton e;
    private Label f;
    private Label g;
    private float h;
    private Table k;
    private Label n;
    private long o;
    private long p;
    private ScrollPane u;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2792a = TLog.forClass(MoneyScreen.class);
    private static float[] q = {0.0f, 5.0f, 0.0f, 161.0f, 590.0f, 166.0f, 590.0f, 0.0f};
    private static float[] r = {0.0f, 0.0f, 0.0f, 166.0f, 590.0f, 161.0f, 590.0f, 5.0f};
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "MoneyScreen main");
    private final UiManager.UiLayer d = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.OVERLAY, 101, "MoneyScreen loading overlay", true);
    private final Array<TableButton> i = new Array<>(true, 1, TableButton.class);
    private int j = -1;
    private final _PurchaseManagerListener l = new _PurchaseManagerListener(this, 0);
    private final _ProgressManagerListener m = new _ProgressManagerListener(this, 0);
    private Array<PaperPackConfig> s = new Array<>(PaperPackConfig.class);
    private Array<AcceleratorPackConfig> t = new Array<>(AcceleratorPackConfig.class);

    static {
        new StringBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/MoneyScreen$PaperPackConfig.class */
    public class PaperPackConfig {

        /* renamed from: a, reason: collision with root package name */
        String f2809a;

        /* renamed from: b, reason: collision with root package name */
        String f2810b;
        int c;
        int d;
        int e;
        int f;

        @Null
        ItemStack g;

        public PaperPackConfig(MoneyScreen moneyScreen, String str, String str2, int i, int i2, int i3, @Null int i4, ItemStack itemStack) {
            this.f2810b = str;
            this.f2809a = str2;
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.g = itemStack;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/MoneyScreen$AcceleratorPackConfig.class */
    public class AcceleratorPackConfig {

        /* renamed from: a, reason: collision with root package name */
        String f2807a;

        /* renamed from: b, reason: collision with root package name */
        String f2808b;
        int c;
        int d;
        int e;
        int f;

        @Null
        ItemStack g;

        public AcceleratorPackConfig(MoneyScreen moneyScreen, String str, String str2, int i, int i2, int i3, @Null int i4, ItemStack itemStack) {
            this.f2808b = str;
            this.f2807a = str2;
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.g = itemStack;
        }
    }

    public MoneyScreen(com.badlogic.gdx.Screen screen) {
        f2792a.i("AR rewardAdsAvailable: " + Game.i.actionResolver.rewardAdsAvailable(), new Object[0]);
        f2792a.i("AR getSecondsTillCanShowRewardAd: " + Game.i.actionResolver.getSecondsTillCanShowRewardAd(), new Object[0]);
        f2792a.i("AR canShowRewardAd: " + Game.i.actionResolver.canShowRewardAd(), new Object[0]);
        f2792a.i("PM rewardingAdsAvailable: " + Game.i.purchaseManager.rewardingAdsAvailable(), new Object[0]);
        f2792a.i("PM canShowRewardingAd (REGULAR): " + Game.i.purchaseManager.canShowRewardingAd(PurchaseManager.RewardingAdsType.REGULAR), new Object[0]);
        f2792a.i("PM getSecondsTillAdIsReady (REGULAR): " + Game.i.purchaseManager.getSecondsTillAdIsReady(PurchaseManager.RewardingAdsType.REGULAR), new Object[0]);
        PurchaseManager.IapOffersConfig iapOfferConfig = Game.i.purchaseManager.getIapOfferConfig();
        this.s.add(new PaperPackConfig(this, "money-pack-tiny", Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.PACK_TINY), iapOfferConfig.getPurchaseBaseAmount(Config.ProductId.PACK_TINY), iapOfferConfig.getBonusPurchaseAmount(Config.ProductId.PACK_TINY), iapOfferConfig.getPurchaseBonus(Config.ProductId.PACK_TINY), 21, iapOfferConfig.getAdditionalItem(Config.ProductId.PACK_TINY)));
        this.s.add(new PaperPackConfig(this, "money-pack-small", Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.PACK_SMALL), iapOfferConfig.getPurchaseBaseAmount(Config.ProductId.PACK_SMALL), iapOfferConfig.getBonusPurchaseAmount(Config.ProductId.PACK_SMALL), iapOfferConfig.getPurchaseBonus(Config.ProductId.PACK_SMALL), 21, iapOfferConfig.getAdditionalItem(Config.ProductId.PACK_SMALL)));
        this.s.add(new PaperPackConfig(this, "money-pack-medium", Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.PACK_MEDIUM), iapOfferConfig.getPurchaseBaseAmount(Config.ProductId.PACK_MEDIUM), iapOfferConfig.getBonusPurchaseAmount(Config.ProductId.PACK_MEDIUM), iapOfferConfig.getPurchaseBonus(Config.ProductId.PACK_MEDIUM), 24, iapOfferConfig.getAdditionalItem(Config.ProductId.PACK_MEDIUM)));
        this.s.add(new PaperPackConfig(this, "money-pack-large", Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.PACK_LARGE), iapOfferConfig.getPurchaseBaseAmount(Config.ProductId.PACK_LARGE), iapOfferConfig.getBonusPurchaseAmount(Config.ProductId.PACK_LARGE), iapOfferConfig.getPurchaseBonus(Config.ProductId.PACK_LARGE), 30, iapOfferConfig.getAdditionalItem(Config.ProductId.PACK_LARGE)));
        boolean z = false;
        Array<Transaction> transactions = ProgressPrefs.i().purchase.getTransactions();
        z = transactions.size >= 3 ? true : z;
        for (int i = 0; i < transactions.size; i++) {
            if (transactions.get(i).getIdentifier().equals(Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.PACK_MEDIUM)) || transactions.get(i).getIdentifier().equals(Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.PACK_LARGE)) || transactions.get(i).getIdentifier().equals(Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_LARGE))) {
                z = true;
            }
        }
        if (z) {
            this.s.add(new PaperPackConfig(this, "money-pack-huge", Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.PACK_HUGE), iapOfferConfig.getPurchaseBaseAmount(Config.ProductId.PACK_HUGE), iapOfferConfig.getBonusPurchaseAmount(Config.ProductId.PACK_HUGE), iapOfferConfig.getPurchaseBonus(Config.ProductId.PACK_HUGE), 36, iapOfferConfig.getAdditionalItem(Config.ProductId.PACK_HUGE)));
        }
        this.t.add(new AcceleratorPackConfig(this, "accelerator-pack-tiny", Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_TINY), iapOfferConfig.getPurchaseBaseAmount(Config.ProductId.ACCELERATOR_PACK_TINY), iapOfferConfig.getBonusPurchaseAmount(Config.ProductId.ACCELERATOR_PACK_TINY), iapOfferConfig.getPurchaseBonus(Config.ProductId.ACCELERATOR_PACK_TINY), 21, iapOfferConfig.getAdditionalItem(Config.ProductId.ACCELERATOR_PACK_TINY)));
        this.t.add(new AcceleratorPackConfig(this, "accelerator-pack-small", Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_SMALL), iapOfferConfig.getPurchaseBaseAmount(Config.ProductId.ACCELERATOR_PACK_SMALL), iapOfferConfig.getBonusPurchaseAmount(Config.ProductId.ACCELERATOR_PACK_SMALL), iapOfferConfig.getPurchaseBonus(Config.ProductId.ACCELERATOR_PACK_SMALL), 24, iapOfferConfig.getAdditionalItem(Config.ProductId.ACCELERATOR_PACK_SMALL)));
        this.t.add(new AcceleratorPackConfig(this, "accelerator-pack-medium", Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_MEDIUM), iapOfferConfig.getPurchaseBaseAmount(Config.ProductId.ACCELERATOR_PACK_MEDIUM), iapOfferConfig.getBonusPurchaseAmount(Config.ProductId.ACCELERATOR_PACK_MEDIUM), iapOfferConfig.getPurchaseBonus(Config.ProductId.ACCELERATOR_PACK_MEDIUM), 30, iapOfferConfig.getAdditionalItem(Config.ProductId.ACCELERATOR_PACK_MEDIUM)));
        this.t.add(new AcceleratorPackConfig(this, "accelerator-pack-large", Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_LARGE), iapOfferConfig.getPurchaseBaseAmount(Config.ProductId.ACCELERATOR_PACK_LARGE), iapOfferConfig.getBonusPurchaseAmount(Config.ProductId.ACCELERATOR_PACK_LARGE), iapOfferConfig.getPurchaseBonus(Config.ProductId.ACCELERATOR_PACK_LARGE), 36, iapOfferConfig.getAdditionalItem(Config.ProductId.ACCELERATOR_PACK_LARGE)));
        if (z) {
            this.t.add(new AcceleratorPackConfig(this, "accelerator-pack-huge", Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_HUGE), iapOfferConfig.getPurchaseBaseAmount(Config.ProductId.ACCELERATOR_PACK_HUGE), iapOfferConfig.getBonusPurchaseAmount(Config.ProductId.ACCELERATOR_PACK_HUGE), iapOfferConfig.getPurchaseBonus(Config.ProductId.ACCELERATOR_PACK_HUGE), 36, iapOfferConfig.getAdditionalItem(Config.ProductId.ACCELERATOR_PACK_HUGE)));
        }
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        this.f2793b = screen;
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        ResourcesAndMoney.i().setVisible(true);
        InventoryOverlay.i().hideWithToggleButton(true);
        ScreenTitle.i().setText(Game.i.localeManager.i18n.get("money_screen_title")).setIcon(Game.i.assetManager.getDrawable("icon-money")).setVisible(true);
        BackButton.i().setVisible(true).setText(null).setClickHandler(() -> {
            c();
        });
        Game.i.purchaseManager.addListener(this.l);
        Game.i.progressManager.addListener(this.m);
    }

    public void scrollToActor(String str) {
        Actor findActor = Game.i.uiManager.findActor(str);
        if (findActor == null) {
            f2792a.i("scrollToActor failed: actor with name " + str + " not found", new Object[0]);
            return;
        }
        if (this.u == null) {
            f2792a.i("scrollToActor failed: mainScroll is null", new Object[0]);
            return;
        }
        if (UiUtils.hasParent(findActor, this.u)) {
            try {
                this.u.scrollTo(0.0f, ((this.u.getChild(0).getHeight() + findActor.localToAscendantCoordinates(this.u, new Vector2(0.0f, 0.0f)).y) - this.u.getHeight()) - ((this.u.getHeight() * 0.5f) + (findActor.getHeight() * 0.5f)), 1.0f, 1.0f);
                final HighlightActor addHighlight = Game.i.uiManager.addHighlight(findActor);
                findActor.addListener(new InputListener(this) { // from class: com.prineside.tdi2.screens.MoneyScreen.1
                    @Override // com.prineside.tdi2.scene2d.InputListener
                    public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                        Game.i.uiManager.removeHighlight(addHighlight);
                        return false;
                    }
                });
                return;
            } catch (Exception e) {
                f2792a.i("scrollToActor failed", e);
                return;
            }
        }
        f2792a.i("scrollToActor failed: actor " + str + " is not contained in mainScroll", new Object[0]);
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void resize(int i, int i2) {
        super.resize(i, i2);
        if (i > 0 && i2 > 0) {
            a();
        }
    }

    private Group a(boolean z, Drawable drawable) {
        return a(z, drawable, 0.0f);
    }

    private static Group a(boolean z, Drawable drawable, float f) {
        Group group = new Group();
        group.setTransform(false);
        float[] fArr = new float[8];
        if (z) {
            System.arraycopy(q, 0, fArr, 0, q.length);
        } else {
            System.arraycopy(r, 0, fArr, 0, r.length);
        }
        fArr[3] = fArr[3] + f;
        fArr[5] = fArr[5] + f;
        group.addActor(new QuadActor(new Color(690563583), fArr));
        if (z) {
            QuadActor quadActor = new QuadActor(new Color(943208703), new float[]{0.0f, 0.0f, 0.0f, 6.0f, 590.0f, 10.0f, 589.0f, 8.0f});
            quadActor.setPosition(0.0f, f + 156.0f);
            group.addActor(quadActor);
            QuadActor quadActor2 = new QuadActor(new Color(943208703), new float[]{0.0f, 0.0f, 0.0f, f + 156.0f, 6.0f, f + 156.0f, 1.0f, 0.0f});
            quadActor2.setPosition(0.0f, 6.0f);
            group.addActor(quadActor2);
        } else {
            QuadActor quadActor3 = new QuadActor(new Color(943208703), new float[]{0.0f, 1.0f, 0.0f, 6.0f, 590.0f, 1.0f, 590.0f, 0.0f});
            quadActor3.setPosition(0.0f, f + 160.0f);
            group.addActor(quadActor3);
            QuadActor quadActor4 = new QuadActor(new Color(943208703), new float[]{0.0f, 0.0f, 0.0f, f + 165.0f, 5.0f, f + 164.0f, 1.0f, 0.0f});
            quadActor4.setPosition(0.0f, 0.0f);
            group.addActor(quadActor4);
        }
        Image image = new Image(drawable);
        image.setSize(128.0f, 128.0f);
        image.setPosition(16.0f, 19.0f + (f * 0.5f));
        group.addActor(image);
        return group;
    }

    private static ComplexButton a(CharSequence charSequence, Runnable runnable) {
        ComplexButton complexButton = new ComplexButton(charSequence, Game.i.assetManager.getLabelStyle(30), runnable);
        complexButton.setIconLabelShadowEnabled(true);
        complexButton.setSize(162.0f, 88.0f);
        complexButton.setBackground(Game.i.assetManager.getDrawable("ui-money-screen-button"), 0.0f, 0.0f, 162.0f, 88.0f);
        complexButton.setBackgroundColors(MaterialColor.LIGHT_GREEN.P600, MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P500, MaterialColor.GREY.P700);
        Image image = new Image(Game.i.assetManager.getDrawable("ui-money-screen-button-edge"));
        image.setSize(162.0f, 88.0f);
        complexButton.addActor(image);
        complexButton.setLabel(5.0f, 39.0f, 157.0f, 21.0f, 1);
        complexButton.setPosition(445.0f, 11.0f);
        return complexButton;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        int i;
        String charSequence;
        Label label;
        Label label2;
        Image image;
        String str;
        String str2;
        String str3;
        Label label3;
        Game.i.uiManager.removeAllHighlights();
        float f = 0.0f;
        if (this.u != null) {
            f = this.u.getScrollY();
        }
        float regularLayerWidth = Game.i.uiManager.getRegularLayerWidth();
        Table table = this.c.getTable();
        table.clear();
        Table table2 = new Table();
        ScrollPane scrollPane = new ScrollPane(table2);
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(regularLayerWidth, Game.i.settingsManager.getScaledViewportHeight());
        scrollPane.setScrollingDisabled(true, false);
        table.add((Table) scrollPane).width(regularLayerWidth).height(Game.i.settingsManager.getScaledViewportHeight());
        this.u = scrollPane;
        Game.i.uiManager.stage.setScrollFocus(this.u);
        table2.add().height(128.0f).width(1.0f).row();
        Group group = new Group();
        group.setTransform(false);
        QuadActor quadActor = new QuadActor(Color.WHITE, q);
        if (Game.i.progressManager.getLootBoostTimeLeft() > 0.0f) {
            quadActor.setVertexColors(new Color(641146367), new Color(472258559), new Color(506009855), new Color(674963199));
        } else {
            quadActor.setVertexColorsSingle(new Color(51));
        }
        group.addActor(quadActor);
        Image image2 = new Image(Game.i.assetManager.getDrawable("loot-token"));
        image2.setSize(128.0f, 128.0f);
        image2.setPosition(16.0f, 19.0f);
        group.addActor(image2);
        Label label4 = new Label(Item.D.LOOT_BOOST.getTitle(), Game.i.assetManager.getLabelStyle(30));
        label4.setSize(100.0f, 23.0f);
        label4.setPosition(158.0f, 112.0f);
        label4.setColor(MaterialColor.LIGHT_GREEN.P500);
        group.addActor(label4);
        Label label5 = new Label(Item.D.LOOT_BOOST.getDescription(), Game.i.assetManager.getLabelStyle(21));
        label5.setPosition(158.0f, 19.0f);
        label5.setSize(290.0f, 80.0f);
        label5.setWrap(true);
        label5.setAlignment(10);
        group.addActor(label5);
        this.n = new Label("", Game.i.assetManager.getLabelStyle(36));
        this.n.setAlignment(16);
        this.n.setPosition(425.0f, 25.0f);
        this.n.setSize(145.0f, 28.0f);
        group.addActor(this.n);
        table2.add((Table) group).size(590.0f, 166.0f).padRight(20.0f);
        int itemsCount = Game.i.progressManager.getItemsCount(Item.D.RARITY_BOOST);
        Group group2 = new Group();
        group2.setTransform(false);
        QuadActor quadActor2 = new QuadActor(Color.WHITE, r);
        if (itemsCount > 0) {
            quadActor2.setVertexColors(new Color(1311839743), new Color(756879103), new Color(857804799), new Color(1429542655));
        } else {
            quadActor2.setVertexColorsSingle(new Color(51));
        }
        group2.addActor(quadActor2);
        Image image3 = new Image(Game.i.assetManager.getDrawable("rarity-token"));
        image3.setSize(128.0f, 128.0f);
        image3.setPosition(16.0f, 19.0f);
        group2.addActor(image3);
        Label label6 = new Label(Item.D.RARITY_BOOST.getTitle(), Game.i.assetManager.getLabelStyle(30));
        label6.setSize(100.0f, 23.0f);
        label6.setPosition(158.0f, 112.0f);
        label6.setColor(MaterialColor.AMBER.P400);
        group2.addActor(label6);
        Label label7 = new Label(Item.D.RARITY_BOOST.getDescription(), Game.i.assetManager.getLabelStyle(21));
        label7.setPosition(158.0f, 19.0f);
        label7.setSize(290.0f, 80.0f);
        label7.setWrap(true);
        label7.setAlignment(10);
        group2.addActor(label7);
        Label label8 = new Label("x" + itemsCount, Game.i.assetManager.getLabelStyle(36));
        label8.setAlignment(16);
        label8.setPosition(425.0f, 25.0f);
        label8.setSize(145.0f, 28.0f);
        if (itemsCount > 0) {
            label8.setColor(MaterialColor.AMBER.P400);
        } else {
            label8.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        }
        group2.addActor(label8);
        table2.add((Table) group2).size(590.0f, 166.0f).row();
        Label label9 = new Label(Game.i.localeManager.i18n.get("shop_tokens_info"), Game.i.assetManager.getLabelStyle(21));
        label9.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table2.add((Table) label9).padTop(20.0f).padBottom(20.0f).colspan(2).row();
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            if (Game.i.authManager.isSignedIn() && Game.i.authManager.getSessionId() != null) {
                if (Game.i.authManager.getSteamAccountId() == null) {
                    Table table3 = new Table();
                    table3.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f)));
                    Label label10 = new Label(Game.i.localeManager.i18n.get("shop_steam_not_linked_no_iaps"), Game.i.assetManager.getLabelStyle(24));
                    label10.setColor(MaterialColor.LIGHT_BLUE.P300);
                    table3.add((Table) label10).center().pad(15.0f).row();
                    FancyButton withLabel = new FancyButton("regularButton.a", null).withLabel(24, Game.i.localeManager.i18n.get("link_steam_button"));
                    withLabel.setClickHandler(() -> {
                        Dialog.i().showConfirm(Game.i.localeManager.i18n.get("link_steam_button_confirm"), () -> {
                            withLabel.setEnabled(false);
                            Game.i.authManager.linkSteamAccount(bool -> {
                                Game.i.screenManager.goToAccountSettingsScreen();
                            });
                        });
                    });
                    table3.add(withLabel).height(48.0f).width(320.0f).padBottom(15.0f).row();
                    table2.add(table3).fillX().padTop(20.0f).padBottom(20.0f).colspan(2).row();
                }
            } else {
                Table table4 = new Table();
                table4.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f)));
                Label label11 = new Label(Game.i.localeManager.i18n.get("shop_steam_not_logined_no_iaps"), Game.i.assetManager.getLabelStyle(24));
                label11.setColor(MaterialColor.LIGHT_BLUE.P300);
                table4.add((Table) label11).center().pad(15.0f).row();
                table2.add(table4).fillX().padTop(20.0f).padBottom(20.0f).colspan(2).row();
            }
        }
        Group a2 = a(false, (Drawable) Game.i.assetManager.getDrawable("money-pack-double-gain"), 32.0f);
        String str4 = Game.i.localeManager.i18n.get("double_gain_title");
        if (Game.i.progressManager.hasTemporaryDoubleGain()) {
            str4 = str4 + ((Object) Game.i.assetManager.replaceRegionAliasesWithChars(" +<@time-accelerator>" + DoubleGainShardItem.getAcceleratorsForDuration(Game.i.progressManager.getTempDoubleGainDurationLeft())));
        }
        Label label12 = new Label(str4, Game.i.assetManager.getLabelStyle(30));
        label12.setSize(100.0f, 23.0f);
        label12.setPosition(158.0f, 144.0f);
        label12.setColor(MaterialColor.AMBER.P400);
        a2.addActor(label12);
        Label label13 = new Label(Game.i.localeManager.i18n.get("double_gain_description"), Game.i.assetManager.getLabelStyle(21));
        label13.setPosition(158.0f, 19.0f);
        label13.setSize(275.0f, 112.0f);
        label13.setWrap(true);
        label13.setAlignment(10);
        a2.addActor(label13);
        Label label14 = new Label(Game.i.localeManager.i18n.get("double_gain_permanent_hint").toUpperCase(), Game.i.assetManager.getLabelStyle(24));
        label14.setPosition(469.0f, 149.0f);
        label14.setAlignment(16);
        label14.setSize(100.0f, 18.0f);
        label14.setColor(MaterialColor.LIGHT_GREEN.P500);
        a2.addActor(label14);
        if (Game.i.progressManager.hasPermanentDoubleGain()) {
            Label label15 = new Label(Game.i.localeManager.i18n.get("enabled"), Game.i.assetManager.getLabelStyle(30));
            label15.setAlignment(16);
            label15.setPosition(425.0f, 30.0f);
            label15.setSize(145.0f, 28.0f);
            label15.setColor(MaterialColor.LIGHT_GREEN.P500);
            a2.addActor(label15);
        } else {
            ComplexButton complexButton = null;
            if (Game.i.purchaseManager.isPurchasesEnabled()) {
                try {
                    Information information = Game.i.purchaseManager.purchaseManager.getInformation(Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.DOUBLE_GAIN));
                    if (information != Information.UNAVAILABLE && "true".equals(Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.IAP_DOUBLE_GAIN_ENABLED))) {
                        complexButton = a(information.getLocalPricing(), () -> {
                            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("double_gain_purchase_confirm"), () -> {
                                a(Game.i.purchaseManager.getPurchaseIdentifier(Config.ProductId.DOUBLE_GAIN));
                            });
                        });
                        a2.addActor(complexButton);
                    }
                } catch (Throwable th) {
                    f2792a.e("failed to get purchase info", th);
                }
            }
            if (complexButton == null) {
                ComplexButton a3 = a("$4.99", () -> {
                });
                a3.setEnabled(false);
                a2.addActor(a3);
            }
        }
        table2.add((Table) a2).size(590.0f, 198.0f).padRight(20.0f);
        if (Game.i.purchaseManager.rewardingAdsAvailable()) {
            boolean isPremiumStatusActive = Game.i.progressManager.isPremiumStatusActive();
            Group a4 = a(true, (Drawable) Game.i.assetManager.getDrawable(isPremiumStatusActive ? "icon-cubes-stacked-tall" : "rewarding-ad"), 32.0f);
            if (isPremiumStatusActive) {
                label3 = new Label(Game.i.localeManager.i18n.get("free_rewards_rewarding_ads_title"), Game.i.assetManager.getLabelStyle(30));
            } else {
                label3 = new Label(Game.i.localeManager.i18n.get("rewarding_ads_title"), Game.i.assetManager.getLabelStyle(30));
            }
            label3.setSize(100.0f, 23.0f);
            label3.setPosition(158.0f, 144.0f);
            label3.setColor(MaterialColor.AMBER.P400);
            a4.addActor(label3);
            LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(Game.i.localeManager.i18n.get("rewarding_ads_description"), 21, 18, 390.0f);
            limitedWidthLabel.setPosition(158.0f, 19.0f);
            limitedWidthLabel.setSize(340.0f, 112.0f);
            limitedWidthLabel.setWrap(false);
            limitedWidthLabel.setAlignment(10);
            a4.addActor(limitedWidthLabel);
            IssuedItems regularRewardingAdItems = Game.i.progressManager.getRegularRewardingAdItems(((int) Game.i.statisticsManager.getAllTime(StatisticsType.RVW)) + 1);
            for (int i2 = 0; i2 < regularRewardingAdItems.items.size && i2 != 5; i2++) {
                Actor generateIcon = regularRewardingAdItems.items.items[i2].getItem().generateIcon(48.0f, true);
                generateIcon.setPosition(158.0f + (i2 * 56.0f), 42.0f);
                a4.addActor(generateIcon);
                Label label16 = new Label(StringFormatter.compactNumber(regularRewardingAdItems.items.items[i2].getCount(), false), Game.i.assetManager.getLabelStyle(18));
                label16.setPosition(158.0f + (i2 * 56.0f), 22.0f);
                label16.setSize(48.0f, 16.0f);
                label16.setAlignment(1);
                a4.addActor(label16);
            }
            this.f = new Label("", Game.i.assetManager.getLabelStyle(30));
            this.f.setAlignment(16);
            this.f.setPosition(425.0f, 30.0f);
            this.f.setSize(145.0f, 28.0f);
            this.f.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            a4.addActor(this.f);
            this.e = a(Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-triangle-right>"), () -> {
                if (Game.getTimestampMillis() - this.o < 1000) {
                    return;
                }
                if (Game.i.purchaseManager.canShowRewardingAd(PurchaseManager.RewardingAdsType.REGULAR)) {
                    this.o = Game.getTimestampMillis();
                    Game.i.purchaseManager.showRewardingAd(PurchaseManager.RewardingAdsType.REGULAR, bool -> {
                        if (Game.getTimestampMillis() - this.p < 30000) {
                            bool = Boolean.FALSE;
                        }
                        if (bool.booleanValue()) {
                            IssuedItems regularRewardingAdItems2 = Game.i.progressManager.getRegularRewardingAdItems((int) Game.i.statisticsManager.getAllTime(StatisticsType.RVW));
                            this.p = Game.getTimestampMillis();
                            Game.i.progressManager.addIssuedPrizes(regularRewardingAdItems2, true);
                            for (int i3 = 0; i3 < regularRewardingAdItems2.items.size; i3++) {
                                Game.i.progressManager.addItemStack(regularRewardingAdItems2.items.get(i3), "regular_ad");
                            }
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            Game.i.statisticsManager.registerDelta(StatisticsType.RVV, 1.0d);
                        } else {
                            Dialog.i().showAlert("Something went wrong, please try again later");
                        }
                        a();
                    });
                    b();
                    return;
                }
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("ad_is_not_loaded_yet"));
            });
            this.e.setVisible(false);
            this.e.setBackgroundColors(MaterialColor.LIGHT_BLUE.P600, MaterialColor.LIGHT_BLUE.P700, MaterialColor.LIGHT_BLUE.P500, MaterialColor.GREY.P700);
            a4.addActor(this.e);
            table2.add((Table) a4).size(590.0f, 198.0f).row();
        }
        if (Game.i.progressManager.hasTemporaryDoubleGain()) {
            Table table5 = new Table();
            Label label17 = new Label(Game.i.localeManager.i18n.get("temp_double_gain_active"), Game.i.assetManager.getLabelStyle(24));
            label17.setColor(MaterialColor.LIGHT_GREEN.P500);
            table5.add((Table) label17).row();
            this.g = new Label(StringFormatter.timePassed(Game.i.progressManager.getTempDoubleGainDurationLeft(), true, true), Game.i.assetManager.getLabelStyle(24));
            table5.add((Table) this.g);
            table5.setBackground(new QuadDrawable(new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.28f), new float[]{0.0f, 4.0f, 10.0f, 92.0f, 500.0f, 96.0f, 510.0f, 0.0f})));
            table2.add(table5).width(510.0f).height(96.0f).padLeft(40.0f).padRight(40.0f).padTop(-4.0f).padBottom(16.0f);
            table2.add().row();
        }
        table2.add().row();
        Group a5 = a(true, (Drawable) Game.i.assetManager.getDrawable("lucky-shot"));
        Actor label18 = new Label(Game.i.localeManager.i18n.get("lucky_shot"), Game.i.assetManager.getLabelStyle(30));
        label18.setSize(100.0f, 23.0f);
        label18.setPosition(158.0f, 112.0f);
        label18.setColor(MaterialColor.AMBER.P400);
        a5.addActor(label18);
        Actor image4 = new Image(Game.i.assetManager.getDrawable("lucky-shot-token"));
        image4.setSize(48.0f, 48.0f);
        image4.setPosition(158.0f, 44.0f);
        a5.addActor(image4);
        Actor label19 = new Label("x" + Game.i.progressManager.getItemsCount(Item.D.LUCKY_SHOT_TOKEN), Game.i.assetManager.getLabelStyle(30));
        label19.setSize(48.0f, 48.0f);
        label19.setPosition(212.0f, 44.0f);
        a5.addActor(label19);
        ComplexButton a6 = a("", () -> {
            LuckyWheelOverlay.i().setVisible(true);
        });
        String str5 = Game.i.localeManager.i18n.get("to_open");
        if (str5.length() <= 6) {
            a6.setText(Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-lucky-wheel> " + str5));
        } else {
            a6.setText(str5);
        }
        a6.setBackgroundColors(MaterialColor.LIGHT_BLUE.P600, MaterialColor.LIGHT_BLUE.P700, MaterialColor.LIGHT_BLUE.P500, MaterialColor.GREY.P700);
        a5.addActor(a6);
        table2.add((Table) a5).size(590.0f, 166.0f).padRight(20.0f).padTop(20.0f);
        table2.row();
        final boolean isProfileStatusActive = Game.i.authManager.isProfileStatusActive(Config.PROFILE_STATUS_PREMIUM);
        if (Game.i.purchaseManager.rewardingAdsAvailable()) {
            table2.row();
            Group group3 = new Group();
            group3.setTransform(false);
            if (isProfileStatusActive) {
                label = new Label(Game.i.localeManager.i18n.get("shop_ad_bars_premium_title"), Game.i.assetManager.getLabelStyle(36));
            } else {
                label = new Label(Game.i.localeManager.i18n.get("shop_ad_bars_title"), Game.i.assetManager.getLabelStyle(36));
            }
            label.setColor(MaterialColor.GREEN.P500);
            label.setPosition(0.0f, 278.0f);
            label.setSize(140.0f, 27.0f);
            group3.addActor(label);
            if (isProfileStatusActive) {
                label2 = new Label(Game.i.localeManager.i18n.get("shop_ad_bars_premium_description"), Game.i.assetManager.getLabelStyle(24));
            } else {
                label2 = new Label(Game.i.localeManager.i18n.get("shop_ad_bars_description"), Game.i.assetManager.getLabelStyle(24));
            }
            label2.setPosition(0.0f, 201.0f);
            label2.setSize(540.0f, 51.0f);
            label2.setWrap(true);
            group3.addActor(label2);
            boolean hasPermanentDoubleGain = Game.i.progressManager.hasPermanentDoubleGain();
            if (hasPermanentDoubleGain) {
                Label label20 = new Label(Game.i.localeManager.i18n.get("shop_ad_bars_hint_without_double_gain"), Game.i.assetManager.getLabelStyle(24));
                label20.setSize(255.0f, 47.0f);
                label20.setAlignment(1);
                label20.setWrap(true);
                label20.setPosition(615.0f, 191.0f);
                label20.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                group3.addActor(label20);
            } else {
                Label label21 = new Label(Game.i.localeManager.i18n.get("shop_ad_bars_hint_with_double_gain"), Game.i.assetManager.getLabelStyle(24));
                label21.setSize(255.0f, 47.0f);
                label21.setAlignment(1);
                label21.setWrap(true);
                label21.setPosition(905.0f, 191.0f);
                label21.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                group3.addActor(label21);
            }
            final PP_Progress pP_Progress = ProgressPrefs.i().progress;
            QuadActor quadActor3 = new QuadActor(Color.WHITE, new float[]{5.0f, 0.0f, 0.0f, 40.0f, 584.0f, 40.0f, 589.0f, 0.0f});
            quadActor3.setPosition(0.0f, 111.0f);
            quadActor3.setSize(589.0f, 40.0f);
            quadActor3.setVertexColorsSingle(new Color(606348543));
            group3.addActor(quadActor3);
            float videosWatchedForDoubleGain = pP_Progress.getVideosWatchedForDoubleGain() / 500.0f;
            float f2 = videosWatchedForDoubleGain;
            if (videosWatchedForDoubleGain > 1.0f) {
                f2 = 1.0f;
            }
            QuadActor quadActor4 = new QuadActor(Color.WHITE, new float[]{5.0f, 0.0f, 0.0f, 40.0f, 584.0f * f2, 40.0f, (584.0f * f2) + 5.0f, 0.0f});
            quadActor4.setPosition(0.0f, 111.0f);
            quadActor4.setSize((584.0f * f2) + 5.0f, 40.0f);
            quadActor4.setVertexColorsSingle(new Color(-797506305));
            group3.addActor(quadActor4);
            Image image5 = new Image(Game.i.assetManager.getDrawable("shop-ad-bar-reflection"));
            image5.setPosition(0.0f, 111.0f);
            image5.setSize(589.0f, 40.0f);
            group3.addActor(image5);
            Label label22 = new Label(pP_Progress.getVideosWatchedForDoubleGain() + " / 500", Game.i.assetManager.getLabelStyle(24));
            label22.setSize(100.0f, 40.0f);
            label22.setPosition(476.0f, 109.0f);
            label22.setAlignment(16);
            label22.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            group3.addActor(label22);
            Label label23 = new Label(pP_Progress.getVideosWatchedForDoubleGain() + " / 500", Game.i.assetManager.getLabelStyle(24));
            label23.setSize(100.0f, 40.0f);
            label23.setPosition(474.0f, 111.0f);
            label23.setAlignment(16);
            group3.addActor(label23);
            QuadActor quadActor5 = new QuadActor(Color.WHITE, new float[]{5.0f, 0.0f, 0.0f, 40.0f, 584.0f, 40.0f, 589.0f, 0.0f});
            quadActor5.setPosition(0.0f, 31.0f);
            quadActor5.setSize(589.0f, 40.0f);
            quadActor5.setVertexColorsSingle(new Color(606348543));
            group3.addActor(quadActor5);
            float videosWatchedForLuckyShot = pP_Progress.getVideosWatchedForLuckyShot() / 20.0f;
            float f3 = videosWatchedForLuckyShot;
            if (videosWatchedForLuckyShot > 1.0f) {
                f3 = 1.0f;
            }
            QuadActor quadActor6 = new QuadActor(Color.WHITE, new float[]{5.0f, 0.0f, 0.0f, 40.0f, 584.0f * f3, 40.0f, (584.0f * f3) + 5.0f, 0.0f});
            quadActor6.setPosition(0.0f, 31.0f);
            quadActor6.setSize((584.0f * f3) + 5.0f, 40.0f);
            quadActor6.setVertexColorsSingle(new Color(-1869573889));
            group3.addActor(quadActor6);
            Image image6 = new Image(Game.i.assetManager.getDrawable("shop-ad-bar-reflection"));
            image6.setPosition(0.0f, 31.0f);
            image6.setSize(589.0f, 40.0f);
            group3.addActor(image6);
            Label label24 = new Label(pP_Progress.getVideosWatchedForLuckyShot() + " / 20", Game.i.assetManager.getLabelStyle(24));
            label24.setSize(100.0f, 40.0f);
            label24.setPosition(476.0f, 29.0f);
            label24.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            label24.setAlignment(16);
            group3.addActor(label24);
            Label label25 = new Label(pP_Progress.getVideosWatchedForLuckyShot() + " / 20", Game.i.assetManager.getLabelStyle(24));
            label25.setSize(100.0f, 40.0f);
            label25.setPosition(474.0f, 31.0f);
            label25.setAlignment(16);
            group3.addActor(label25);
            QuadActor quadActor7 = new QuadActor(Color.WHITE, new float[]{0.0f, 1.0f, 25.0f, 190.0f, 31.0f, 189.0f, 6.0f, 0.0f});
            quadActor7.setVertexColorsSingle(new Color(1.0f, 1.0f, 1.0f, 0.14f));
            quadActor7.setSize(31.0f, 190.0f);
            quadActor7.setPosition(864.0f, 0.0f);
            group3.addActor(quadActor7);
            if (isProfileStatusActive) {
                image = new Image(Game.i.assetManager.getDrawable("time-accelerator"));
            } else {
                image = new Image(Game.i.assetManager.getDrawable("double-gain-shard"));
            }
            image.setPosition(622.0f, 99.0f);
            image.setSize(64.0f, 64.0f);
            image.setTouchable(Touchable.disabled);
            if (hasPermanentDoubleGain) {
                image.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            }
            final IssuedItems.IssueReason issueReason = isProfileStatusActive ? IssuedItems.IssueReason.PREMIUM_REWARD_VIDEO : IssuedItems.IssueReason.REWARD_VIDEO;
            if (!hasPermanentDoubleGain && pP_Progress.getVideosWatchedForDoubleGain() >= 500) {
                QuadActor quadActor8 = new QuadActor(Color.WHITE, new float[]{8.0f, 0.0f, 0.0f, 76.0f, 253.0f, 74.0f, 244.0f, 2.0f});
                quadActor8.setVertexColorsSingle(MaterialColor.GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f));
                quadActor8.setPosition(609.0f, 92.0f);
                quadActor8.setSize(253.0f, 76.0f);
                quadActor8.addAction(Actions.forever(Actions.sequence(Actions.alpha(1.0f, 0.3f), Actions.alpha(0.78f, 0.3f))));
                quadActor8.setTouchable(Touchable.enabled);
                group3.addActor(quadActor8);
                AttentionRaysUnderlay attentionRaysUnderlay = new AttentionRaysUnderlay(96.0f, MaterialColor.AMBER.P300);
                attentionRaysUnderlay.setPosition(606.0f, 83.0f);
                attentionRaysUnderlay.setSize(96.0f, 96.0f);
                attentionRaysUnderlay.setTouchable(Touchable.disabled);
                group3.addActor(attentionRaysUnderlay);
                quadActor8.addListener(new ClickListener() { // from class: com.prineside.tdi2.screens.MoneyScreen.2
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f4, float f5) {
                        Item item;
                        int i3;
                        if (pP_Progress.getVideosWatchedForDoubleGain() >= 500) {
                            pP_Progress.setVideosWatchedForDoubleGain(pP_Progress.getVideosWatchedForDoubleGain() - 500);
                            ProgressPrefs.i().requireSave();
                            if (isProfileStatusActive) {
                                item = Item.D.ACCELERATOR;
                                i3 = 200;
                            } else {
                                Item create = ((DoubleGainShardItem.DoubleGainShardItemFactory) Game.i.itemManager.getFactory(ItemType.DOUBLE_GAIN_SHARD)).create();
                                item = create;
                                ((DoubleGainShardItem) create).duration = DoubleGainShardItem.DEFAULT_DURATION;
                                i3 = 1;
                            }
                            Game.i.progressManager.addItems(item, i3, "regular_ad_lots");
                            IssuedItems issuedItems = new IssuedItems(issueReason, Game.getTimestampSeconds());
                            issuedItems.items.add(new ItemStack(item, i3));
                            Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                        }
                        MoneyScreen.this.a();
                    }
                });
            }
            group3.addActor(image);
            Label label26 = new Label(isProfileStatusActive ? Game.i.localeManager.i18n.get("item_title_ACCELERATOR") + "\nx200" : Game.i.localeManager.i18n.get("double_gain_title") + SequenceUtils.EOL + Game.i.localeManager.i18n.format("n_weeks", 2), Game.i.assetManager.getLabelStyle(24));
            label26.setSize(160.0f, 64.0f);
            label26.setPosition(702.0f, 99.0f);
            label26.setColor(MaterialColor.AMBER.P500);
            label26.setTouchable(Touchable.disabled);
            if (hasPermanentDoubleGain) {
                label26.setColor(label26.getColor().mul(1.0f, 1.0f, 1.0f, 0.28f));
            }
            group3.addActor(label26);
            Image image7 = new Image(Game.i.assetManager.getDrawable("lucky-shot-token"));
            image7.setPosition(622.0f, 19.0f);
            image7.setSize(64.0f, 64.0f);
            image7.setTouchable(Touchable.disabled);
            if (hasPermanentDoubleGain) {
                image7.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            }
            if (!hasPermanentDoubleGain && pP_Progress.getVideosWatchedForLuckyShot() >= 20) {
                QuadActor quadActor9 = new QuadActor(Color.WHITE, new float[]{8.0f, 0.0f, 0.0f, 76.0f, 253.0f, 74.0f, 244.0f, 2.0f});
                quadActor9.setVertexColorsSingle(MaterialColor.GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f));
                quadActor9.setPosition(609.0f, 12.0f);
                quadActor9.setSize(253.0f, 76.0f);
                quadActor9.addAction(Actions.forever(Actions.sequence(Actions.alpha(1.0f, 0.3f), Actions.alpha(0.78f, 0.3f))));
                quadActor9.setTouchable(Touchable.enabled);
                group3.addActor(quadActor9);
                AttentionRaysUnderlay attentionRaysUnderlay2 = new AttentionRaysUnderlay(96.0f, MaterialColor.GREY.P300);
                attentionRaysUnderlay2.setPosition(606.0f, 3.0f);
                attentionRaysUnderlay2.setSize(96.0f, 96.0f);
                attentionRaysUnderlay2.setTouchable(Touchable.disabled);
                group3.addActor(attentionRaysUnderlay2);
                quadActor9.addListener(new ClickListener() { // from class: com.prineside.tdi2.screens.MoneyScreen.3
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f4, float f5) {
                        if (pP_Progress.getVideosWatchedForLuckyShot() >= 20) {
                            pP_Progress.setVideosWatchedForLuckyShot(pP_Progress.getVideosWatchedForLuckyShot() - 20);
                            ProgressPrefs.i().requireSave();
                            int i3 = 2;
                            if (isProfileStatusActive) {
                                i3 = 5;
                            }
                            Game.i.progressManager.addItems(Item.D.LUCKY_SHOT_TOKEN, i3, "regular_ad_many");
                            IssuedItems issuedItems = new IssuedItems(issueReason, Game.getTimestampSeconds());
                            issuedItems.items.add(new ItemStack(Item.D.LUCKY_SHOT_TOKEN, i3));
                            Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                        }
                        MoneyScreen.this.a();
                    }
                });
            }
            group3.addActor(image7);
            String str6 = Game.i.localeManager.i18n.get("lucky_shot");
            if (isProfileStatusActive) {
                str = str6 + "\nx5";
            } else {
                str = str6 + "\nx2";
            }
            Label label27 = new Label(str, Game.i.assetManager.getLabelStyle(24));
            label27.setSize(160.0f, 64.0f);
            label27.setPosition(702.0f, 19.0f);
            label27.setColor(new Color(-656877313));
            label27.setTouchable(Touchable.disabled);
            if (hasPermanentDoubleGain) {
                label27.setColor(label27.getColor().mul(1.0f, 1.0f, 1.0f, 0.28f));
            }
            group3.addActor(label27);
            Image image8 = new Image(Game.i.assetManager.getDrawable("time-accelerator"));
            image8.setPosition(913.0f, 99.0f);
            image8.setSize(64.0f, 64.0f);
            image8.setTouchable(Touchable.disabled);
            if (!hasPermanentDoubleGain) {
                image8.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            }
            if (hasPermanentDoubleGain && pP_Progress.getVideosWatchedForDoubleGain() >= 500) {
                QuadActor quadActor10 = new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 8.0f, 76.0f, 243.0f, 74.0f, 253.0f, 2.0f});
                quadActor10.setVertexColorsSingle(MaterialColor.GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f));
                quadActor10.setPosition(901.0f, 92.0f);
                quadActor10.setSize(253.0f, 76.0f);
                quadActor10.addAction(Actions.forever(Actions.sequence(Actions.alpha(1.0f, 0.3f), Actions.alpha(0.78f, 0.3f))));
                quadActor10.setTouchable(Touchable.enabled);
                group3.addActor(quadActor10);
                AttentionRaysUnderlay attentionRaysUnderlay3 = new AttentionRaysUnderlay(96.0f, MaterialColor.AMBER.P300);
                attentionRaysUnderlay3.setPosition(897.0f, 83.0f);
                attentionRaysUnderlay3.setSize(96.0f, 96.0f);
                attentionRaysUnderlay3.setTouchable(Touchable.disabled);
                group3.addActor(attentionRaysUnderlay3);
                quadActor10.addListener(new ClickListener() { // from class: com.prineside.tdi2.screens.MoneyScreen.4
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f4, float f5) {
                        if (pP_Progress.getVideosWatchedForDoubleGain() >= 500) {
                            pP_Progress.setVideosWatchedForDoubleGain(pP_Progress.getVideosWatchedForDoubleGain() - 500);
                            ProgressPrefs.i().requireSave();
                            int i3 = 200;
                            if (isProfileStatusActive) {
                                i3 = 300;
                            }
                            Game.i.progressManager.addItems(Item.D.ACCELERATOR, i3, "regular_ad_lots");
                            IssuedItems issuedItems = new IssuedItems(issueReason, Game.getTimestampSeconds());
                            issuedItems.items.add(new ItemStack(Item.D.ACCELERATOR, i3));
                            Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                        }
                        MoneyScreen.this.a();
                    }
                });
            }
            group3.addActor(image8);
            String str7 = Game.i.localeManager.i18n.get("item_title_ACCELERATOR");
            if (isProfileStatusActive) {
                str2 = str7 + "\nx300";
            } else {
                str2 = str7 + "\nx200";
            }
            Label label28 = new Label(str2, Game.i.assetManager.getLabelStyle(24));
            label28.setSize(160.0f, 64.0f);
            label28.setPosition(993.0f, 99.0f);
            label28.setColor(MaterialColor.AMBER.P500);
            label28.setTouchable(Touchable.disabled);
            if (!hasPermanentDoubleGain) {
                label28.setColor(label28.getColor().mul(1.0f, 1.0f, 1.0f, 0.28f));
            }
            group3.addActor(label28);
            Image image9 = new Image(Game.i.assetManager.getDrawable("lucky-shot-token"));
            image9.setPosition(913.0f, 19.0f);
            image9.setSize(64.0f, 64.0f);
            image9.setTouchable(Touchable.disabled);
            if (!hasPermanentDoubleGain) {
                image9.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            }
            if (hasPermanentDoubleGain && pP_Progress.getVideosWatchedForLuckyShot() >= 20) {
                QuadActor quadActor11 = new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 8.0f, 76.0f, 243.0f, 74.0f, 253.0f, 2.0f});
                quadActor11.setVertexColorsSingle(MaterialColor.GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f));
                quadActor11.setPosition(901.0f, 12.0f);
                quadActor11.setSize(253.0f, 76.0f);
                quadActor11.addAction(Actions.forever(Actions.sequence(Actions.alpha(1.0f, 0.3f), Actions.alpha(0.78f, 0.3f))));
                quadActor11.setTouchable(Touchable.enabled);
                group3.addActor(quadActor11);
                AttentionRaysUnderlay attentionRaysUnderlay4 = new AttentionRaysUnderlay(96.0f, MaterialColor.GREY.P300);
                attentionRaysUnderlay4.setPosition(897.0f, 3.0f);
                attentionRaysUnderlay4.setSize(96.0f, 96.0f);
                attentionRaysUnderlay4.setTouchable(Touchable.disabled);
                group3.addActor(attentionRaysUnderlay4);
                quadActor11.addListener(new ClickListener() { // from class: com.prineside.tdi2.screens.MoneyScreen.5
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f4, float f5) {
                        if (pP_Progress.getVideosWatchedForLuckyShot() >= 20) {
                            pP_Progress.setVideosWatchedForLuckyShot(pP_Progress.getVideosWatchedForLuckyShot() - 20);
                            ProgressPrefs.i().requireSave();
                            int i3 = 5;
                            if (isProfileStatusActive) {
                                i3 = 7;
                            }
                            Game.i.progressManager.addItems(Item.D.LUCKY_SHOT_TOKEN, i3, "regular_ad_many");
                            IssuedItems issuedItems = new IssuedItems(issueReason, Game.getTimestampSeconds());
                            issuedItems.items.add(new ItemStack(Item.D.LUCKY_SHOT_TOKEN, i3));
                            Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                        }
                        MoneyScreen.this.a();
                    }
                });
            }
            group3.addActor(image9);
            String str8 = Game.i.localeManager.i18n.get("lucky_shot");
            if (isProfileStatusActive) {
                str3 = str8 + "\nx7";
            } else {
                str3 = str8 + "\nx5";
            }
            Label label29 = new Label(str3, Game.i.assetManager.getLabelStyle(24));
            label29.setSize(160.0f, 64.0f);
            label29.setPosition(993.0f, 19.0f);
            label29.setColor(new Color(-656877313));
            label29.setTouchable(Touchable.disabled);
            if (!hasPermanentDoubleGain) {
                label29.setColor(label29.getColor().mul(1.0f, 1.0f, 1.0f, 0.28f));
            }
            group3.addActor(label29);
            table2.add((Table) group3).size(1200.0f, 309.0f).padTop(45.0f).colspan(2).row();
        } else {
            table2.row();
            table2.add().size(1.0f, 20.0f).row();
        }
        table2.add().width(1.0f).height(32.0f).row();
        int i3 = 0;
        CaseType[] caseTypeArr = {CaseType.BLUE, CaseType.PURPLE, CaseType.ORANGE, CaseType.CYAN};
        for (int i4 = 0; i4 < 4; i4++) {
            CaseType caseType = caseTypeArr[i4];
            CaseItem create = Item.D.F_CASE.create(caseType, false);
            Group a7 = a(i3 == 0 || i3 == 3, create.getIconDrawable());
            Actor label30 = new Label(create.getTitle(), Game.i.assetManager.getLabelStyle(30));
            label30.setSize(100.0f, 23.0f);
            label30.setPosition(158.0f, 112.0f);
            label30.setColor(Game.i.progressManager.getRarityBrightColor(create.getRarity()));
            a7.addActor(label30);
            Group group4 = new Group();
            group4.setTransform(false);
            group4.setPosition(158.0f, 46.0f);
            group4.setSize(1.0f, 1.0f);
            a7.addActor(group4);
            int[] itemRarityChances = create.getItemRarityChances();
            int i5 = 0;
            for (RarityType rarityType : RarityType.values) {
                if (itemRarityChances[rarityType.ordinal()] > 0) {
                    float f4 = i5 * 46.0f;
                    Label label31 = new Label(new StringBuilder().append(itemRarityChances[rarityType.ordinal()]).toString(), Game.i.assetManager.getLabelStyle(21));
                    label31.setPosition(f4, 39.0f);
                    label31.setSize(32.0f, 16.0f);
                    label31.setColor(Game.i.progressManager.getRarityBrightColor(rarityType));
                    label31.setAlignment(1);
                    group4.addActor(label31);
                    Image image10 = new Image(Game.i.uiManager.getItemCellRarityCoat(rarityType, i5 % 2));
                    image10.setPosition(f4, 0.0f);
                    image10.setSize(32.0f, 36.0f);
                    group4.addActor(image10);
                    i5++;
                }
            }
            float f5 = i5 * 46.0f;
            Label label32 = new Label("%", Game.i.assetManager.getLabelStyle(21));
            label32.setPosition(f5, 39.0f);
            label32.setSize(32.0f, 16.0f);
            group4.addActor(label32);
            Label label33 = new Label("x" + create.getItemCount(), Game.i.assetManager.getLabelStyle(21));
            label33.setPosition(f5, 0.0f);
            label33.setSize(32.0f, 36.0f);
            group4.addActor(label33);
            if (create.getGuaranteedItemType() != null) {
                Actor label34 = new Label(Game.i.localeManager.i18n.get("guaranteed") + ": [#" + Game.i.progressManager.getRarityBrightColor(create.getGuaranteedItemType()).toString() + "]" + Game.i.progressManager.getRarityName(create.getGuaranteedItemType()) + "[] x1", Game.i.assetManager.getLabelStyle(21));
                label34.setPosition(158.0f, 22.0f);
                label34.setSize(100.0f, 16.0f);
                a7.addActor(label34);
            }
            int casePriceInKeys = create.getCasePriceInKeys();
            if (casePriceInKeys > 0) {
                CaseKeyItem create2 = Item.D.F_CASE_KEY.create(caseType);
                int itemsCount2 = Game.i.progressManager.getItemsCount(create2);
                Runnable runnable = null;
                if (itemsCount2 / casePriceInKeys > 0) {
                    runnable = () -> {
                        int min = Math.min(200, itemsCount2 / casePriceInKeys);
                        final Label label35 = new Label("", Game.i.assetManager.getLabelStyle(30));
                        ItemCountSelectionOverlay.ItemCountSelectionListener itemCountSelectionListener = new ItemCountSelectionOverlay.ItemCountSelectionListener() { // from class: com.prineside.tdi2.screens.MoneyScreen.6
                            @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
                            public void countChanged(int i6) {
                                label35.setText(((Object) StringFormatter.commaSeparatedNumber(casePriceInKeys * ItemCountSelectionOverlay.i().getSelectedCount())) + "[#AAAAAA] / " + ((Object) StringFormatter.commaSeparatedNumber(itemsCount2)) + "[]");
                            }

                            @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
                            public void selectionConfirmed(int i6) {
                                if (Game.i.progressManager.removeItems(create2, create.getCasePriceInKeys() * i6)) {
                                    Game.i.progressManager.addItems(create, i6, "case_purchase_keys");
                                    IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                                    issuedItems.items.add(new ItemStack(create, i6));
                                    Game.i.progressManager.addIssuedPrizes(issuedItems, false);
                                    Game.i.progressManager.openPack(create, i6, true, false);
                                    Game.i.analyticsManager.logCurrencySpent(caseType.name().toLowerCase(Locale.ENGLISH) + "_case", "case_key_" + caseType.name(), create.getCasePriceInKeys());
                                    MoneyScreen.this.a();
                                    return;
                                }
                                Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_items"));
                            }

                            @Override // com.prineside.tdi2.ui.shared.ItemCountSelectionOverlay.ItemCountSelectionListener
                            public void selectionCanceled() {
                            }
                        };
                        ItemCountSelectionOverlay.i().show(Game.i.localeManager.i18n.get("shop_buy_chests"), 1, min, create, itemCountSelectionListener);
                        Table infoContainer = ItemCountSelectionOverlay.i().getInfoContainer();
                        infoContainer.clear();
                        infoContainer.add((Table) create2.generateIcon(48.0f, false)).size(48.0f).padRight(16.0f);
                        infoContainer.add((Table) label35).size(400.0f).left().height(48.0f);
                        infoContainer.add().expandX().fillX();
                        ItemCountSelectionOverlay.i().setSelectedCount(min);
                        itemCountSelectionListener.countChanged(min);
                    };
                }
                ComplexButton complexButton2 = new ComplexButton(Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-key> " + itemsCount2 + " / " + casePriceInKeys), Game.i.assetManager.getLabelStyle(30), () -> {
                    if (Game.i.progressManager.removeItems(create2, create.getCasePriceInKeys())) {
                        Game.i.progressManager.addItems(create, 1, "case_purchase_keys");
                        IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                        issuedItems.items.add(new ItemStack(create, 1));
                        Game.i.progressManager.addIssuedPrizes(issuedItems, false);
                        Game.i.progressManager.openPack(create, 1, true, false);
                        Game.i.analyticsManager.logCurrencySpent(caseType.name().toLowerCase(Locale.ENGLISH) + "_case", "case_key_" + caseType.name(), create.getCasePriceInKeys());
                        a();
                        return;
                    }
                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_items"));
                }, runnable);
                complexButton2.setName("spend_keys_" + create2.caseType.name());
                complexButton2.setIconLabelShadowEnabled(true);
                complexButton2.setSize(162.0f, 78.0f);
                complexButton2.setBackground(Game.i.assetManager.getDrawable("ui-money-screen-button-small-top"), 0.0f, 0.0f, 162.0f, 78.0f);
                complexButton2.setBackgroundColors(MaterialColor.LIGHT_BLUE.P600, MaterialColor.LIGHT_BLUE.P700, MaterialColor.LIGHT_BLUE.P500, MaterialColor.GREY.P800);
                if (itemsCount2 < casePriceInKeys) {
                    complexButton2.setEnabled(false);
                }
                Image image11 = new Image(Game.i.assetManager.getDrawable("ui-money-screen-button-small-top-edge"));
                image11.setSize(162.0f, 78.0f);
                complexButton2.addActor(image11);
                complexButton2.setLabel(5.0f, 34.0f, 157.0f, 21.0f, 1);
                complexButton2.setPosition(445.0f, 7.0f);
                a7.addActor(complexButton2);
            }
            Cell padBottom = table2.add((Table) a7).size(590.0f, 166.0f).padBottom(20.0f);
            if (i3 % 2 == 0) {
                padBottom.padRight(20.0f);
            } else {
                padBottom.row();
            }
            i3++;
        }
        table2.row();
        table2.add().height(46.0f).width(1.0f).row();
        Array<ProgressManager.ShopOffer> shopOffers = Game.i.progressManager.getShopOffers();
        if (shopOffers != null && shopOffers.size != 0) {
            table2.row();
            Table table6 = new Table();
            table2.add(table6).colspan(2).fillX().row();
            Table table7 = new Table();
            table6.add(table7).growX().row();
            Label label35 = new Label(Game.i.localeManager.i18n.get("shop_title_item_market"), Game.i.assetManager.getLabelStyle(36));
            label35.setColor(MaterialColor.GREEN.P500);
            table7.add((Table) label35).growX();
            int playTimeUntilShopOffersUpdate = ProgressPrefs.i().progress.getPlayTimeUntilShopOffersUpdate();
            if (playTimeUntilShopOffersUpdate < 600) {
                i = 0;
            } else if (playTimeUntilShopOffersUpdate < 1200) {
                i = 3;
            } else if (playTimeUntilShopOffersUpdate < 1800) {
                i = 6;
            } else if (playTimeUntilShopOffersUpdate < 2400) {
                i = 9;
            } else if (playTimeUntilShopOffersUpdate < 3000) {
                i = 12;
            } else {
                i = 15;
            }
            if (ProgressPrefs.i().progress.isCurrentShopOffersAreAfterSkip()) {
                i = (int) (i * 1.7f);
            }
            if (i == 0) {
                charSequence = Game.i.assetManager.replaceRegionAliasesWithChars(Game.i.localeManager.i18n.get("update_shop_offers_now_for_free")).toString();
            } else {
                charSequence = Game.i.assetManager.replaceRegionAliasesWithChars(Game.i.localeManager.i18n.format("update_shop_offers_now_for_accelerators", Integer.valueOf(i))).toString();
            }
            int i6 = i;
            LabelButton labelButton = new LabelButton(charSequence, Game.i.assetManager.getLabelStyle(21), () -> {
                if (i6 == 0) {
                    Game.i.progressManager.generateNewShopOffers();
                    a();
                } else {
                    Dialog.i().showConfirm(Game.i.assetManager.replaceRegionAliasesWithChars(Game.i.localeManager.i18n.format("shop_offers_update_confirm", Integer.valueOf(i6))), () -> {
                        if (Game.i.progressManager.removeAccelerators(i6)) {
                            Game.i.actionResolver.logShopOffersSkipped(i6);
                            Game.i.progressManager.generateNewShopOffers();
                            ProgressPrefs.i().progress.setCurrentShopOffersAreAfterSkip(true);
                            ProgressPrefs.i().requireSave();
                            a();
                            return;
                        }
                        Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_accelerators"));
                    });
                }
            });
            labelButton.setColor(1.0f, 1.0f, 1.0f, 0.78f);
            labelButton.setColors(new Color(1.0f, 1.0f, 1.0f, 0.56f), Color.WHITE);
            table7.add((Table) labelButton).height(48.0f).padLeft(20.0f);
            Table table8 = new Table();
            table6.add(table8).growX().row();
            Label label36 = new Label(Game.i.localeManager.i18n.get("shop_offers_subtitle"), Game.i.assetManager.getLabelStyle(24));
            label36.setWrap(true);
            table8.add((Table) label36).growX();
            Label label37 = new Label(Game.i.localeManager.i18n.get("shop_offers_next_update_in"), Game.i.assetManager.getLabelStyle(24));
            label37.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table8.add((Table) label37).padRight(8.0f);
            Label label38 = new Label(StringFormatter.digestTimeWithZeroHours(ProgressPrefs.i().progress.getPlayTimeUntilShopOffersUpdate(), true), Game.i.assetManager.getLabelStyle(30));
            label38.setColor(MaterialColor.GREEN.P500);
            table8.add((Table) label38);
            this.k = new Table();
            this.k.setName("shop-offers-table");
            table6.add(this.k).growX().padTop(20.0f).row();
            ObjectMap objectMap = new ObjectMap();
            Array<Research> instances = Game.i.researchManager.getInstances();
            for (int i7 = 0; i7 < instances.size; i7++) {
                Research research = instances.get(i7);
                if (research.priceInStars == 0) {
                    Array<ItemStack> cumulativePrice = research.getCumulativePrice(research.getInstalledLevel(), research.maxEndlessLevel, false);
                    for (int i8 = 0; i8 < cumulativePrice.size; i8++) {
                        ItemStack itemStack = cumulativePrice.get(i8);
                        objectMap.put(itemStack.getItem(), Long.valueOf(((Long) objectMap.get(itemStack.getItem(), 0L)).longValue() + itemStack.getCount()));
                    }
                }
            }
            this.i.clear();
            for (int i9 = 0; i9 < shopOffers.size; i9++) {
                ProgressManager.ShopOffer shopOffer = shopOffers.get(i9);
                String str9 = "shop-offer-cell-" + i9;
                int i10 = i9;
                TableButton tableButton = new TableButton(() -> {
                    if (this.j != i10) {
                        a(i10);
                        return;
                    }
                    if (!shopOffer.purchased) {
                        if (Game.i.progressManager.removeItems(shopOffer.price.getItem(), shopOffer.price.getCount())) {
                            Game.i.progressManager.addItemStack(shopOffer.item, "shop_offer");
                            IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems.items.add(new ItemStack(shopOffer.item));
                            Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                            Game.i.statisticsManager.registerDelta(StatisticsType.SOP, 1.0d);
                            shopOffer.purchased = true;
                            ProgressPrefs.i().requireSave();
                            Game.i.actionResolver.logShopOfferPurchased(shopOffer.price.getItem().getAnalyticName(), shopOffer.price.getCount(), shopOffer.item.getItem().getAnalyticName(), shopOffer.item.getCount());
                            a();
                            Threads.i().postRunnable(() -> {
                                TableButton tableButton2 = (TableButton) Game.i.uiManager.findActor(str9);
                                Table table9 = (Table) Game.i.uiManager.findActor("shop-offers-table");
                                if (tableButton2 != null && table9 != null) {
                                    ParticlesCanvas particlesCanvas = new ParticlesCanvas();
                                    particlesCanvas.setSize(table9.getWidth(), table9.getHeight());
                                    particlesCanvas.setTouchable(Touchable.disabled);
                                    table9.addActor(particlesCanvas);
                                    ParticleEffectPool.PooledEffect obtain = Game.i.assetManager.getParticleEffectPool("shop-offer-purchase.p").obtain();
                                    float[] colors = obtain.getEmitters().first().getTint().getColors();
                                    Color rarityColor = Game.i.progressManager.getRarityColor(shopOffer.item.getItem().getRarity());
                                    colors[0] = rarityColor.r;
                                    colors[1] = rarityColor.g;
                                    colors[2] = rarityColor.f888b;
                                    obtain.getEmitters().first().getTint().setColors(colors);
                                    particlesCanvas.addParticle(obtain, tableButton2.getX() + (tableButton2.getWidth() * 0.5f), tableButton2.getY() + (tableButton2.getHeight() * 0.5f));
                                    Game.i.soundManager.playRarity(shopOffer.item.getItem().getRarity());
                                }
                            });
                            a(-1);
                            return;
                        }
                        Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_items"));
                    }
                });
                tableButton.setName(str9);
                this.i.add(tableButton);
                tableButton.setBackgroundDrawables(Game.i.assetManager.getQuad(i9 % 2 == 0 ? "ui.shop.timedOffer.cellOdd" : "ui.shop.timedOffer.cellEven"), Game.i.assetManager.getQuad(i9 % 2 == 0 ? "ui.shop.timedOffer.cellOddActive" : "ui.shop.timedOffer.cellEvenActive"), Game.i.assetManager.getQuad(i9 % 2 == 0 ? "ui.shop.timedOffer.cellOddHover" : "ui.shop.timedOffer.cellEvenHover"), Game.i.assetManager.getQuad(i9 % 2 == 0 ? "ui.shop.timedOffer.cellOddDisabled" : "ui.shop.timedOffer.cellEvenDisabled"));
                tableButton.clickableWhenDisabled = true;
                Image image12 = new Image(Game.i.assetManager.getQuad(i9 % 2 == 0 ? "ui.shop.timedOffer.selectionOdd" : "ui.shop.timedOffer.selectionEven"));
                image12.setSize(185.0f, 224.0f);
                image12.setTouchable(Touchable.disabled);
                image12.setName("shop-offer-cell-selection-" + i9);
                image12.setVisible(false);
                tableButton.addActor(image12);
                boolean z = Game.i.progressManager.getItemsCount(shopOffer.price.getItem()) < shopOffer.price.getCount();
                boolean z2 = z;
                if (z) {
                    Image image13 = new Image(Game.i.assetManager.getQuad(i9 % 2 == 0 ? "ui.shop.timedOffer.notEnoughItemsOverlayOdd" : "ui.shop.timedOffer.notEnoughItemsOverlayEven"));
                    image13.setSize(185.0f, 224.0f);
                    tableButton.addActor(image13);
                }
                Actor generateIcon2 = shopOffer.item.getItem().generateIcon(96.0f, true);
                generateIcon2.setPosition(44.5f, 112.0f);
                tableButton.addActor(generateIcon2);
                Label label39 = new Label("x" + ((Object) StringFormatter.commaSeparatedNumber(shopOffer.item.getCount())), Game.i.assetManager.getLabelStyle(30));
                label39.setAlignment(1);
                label39.setPosition(0.0f, 80.0f);
                label39.setSize(185.0f, 24.0f);
                tableButton.addActor(label39);
                Table table9 = new Table();
                table9.setWidth(185.0f);
                table9.setHeight(64.0f);
                tableButton.addActor(table9);
                Actor generateIcon3 = shopOffer.price.getItem().generateIcon(32.0f, false);
                table9.add((Table) generateIcon3).size(32.0f).padRight(8.0f);
                Label label40 = new Label(StringFormatter.compactNumber(shopOffer.price.getCount(), false).toString(), Game.i.assetManager.getLabelStyle(24));
                table9.add((Table) label40);
                if (z2) {
                    label40.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                }
                if (shopOffer.purchased) {
                    tableButton.setEnabled(false);
                    Image image14 = new Image(Game.i.assetManager.getQuad("ui.shop.timedOffer.soldOutOverlay"));
                    image14.setSize(185.0f, 224.0f);
                    tableButton.addActor(image14);
                    Label label41 = new Label("Sold out", Game.i.assetManager.getLabelStyle(21));
                    label41.setPosition(0.0f, 136.0f);
                    label41.setSize(185.0f, 14.0f);
                    label41.setAlignment(1);
                    tableButton.addActor(label41);
                    label39.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    generateIcon2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    generateIcon3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    label40.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                }
                if (shopOffer.discountPercent > 0) {
                    Label label42 = new Label("-" + shopOffer.discountPercent + "%", Game.i.assetManager.getLabelStyle(18));
                    label42.setPosition(0.0f, 35.0f);
                    label42.setWidth(177.0f);
                    label42.setHeight(24.0f);
                    label42.setAlignment(16);
                    label42.setColor(MaterialColor.LIGHT_GREEN.P500);
                    tableButton.addActor(label42);
                }
                Cell padBottom2 = this.k.add(tableButton).size(185.0f, 224.0f).padBottom(20.0f);
                if (i9 % 6 == 5) {
                    padBottom2.row();
                } else {
                    padBottom2.padRight(18.0f);
                }
            }
            table2.row();
            table2.add().height(46.0f).width(1.0f).row();
        }
        boolean z3 = false;
        Array.ArrayIterator<PaperPackConfig> it = this.s.iterator();
        while (true) {
            if (it.hasNext()) {
                if (Game.i.purchaseManager.purchaseManager.getInformation(it.next().f2809a) != Information.UNAVAILABLE) {
                    z3 = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (z3 && Game.i.purchaseManager.isPurchasesEnabled() && ("true".equals(Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.IAP_GREEN_PAPER_ENABLED)) || "true".equals(Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.IAP_ACCELERATOR_ENABLED)))) {
            Label label43 = new Label(Game.i.localeManager.i18n.get("shop_iap_category_title"), Game.i.assetManager.getLabelStyle(36));
            label43.setColor(MaterialColor.GREEN.P500);
            table2.add((Table) label43).fillX().colspan(2).row();
            Label label44 = new Label(Game.i.localeManager.i18n.get("shop_iap_category_subtitle"), Game.i.assetManager.getLabelStyle(24));
            label44.setWrap(true);
            label44.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table2.add((Table) label44).fillX().padBottom(20.0f).colspan(2).row();
        }
        if (z3 && Game.i.purchaseManager.isPurchasesEnabled() && "true".equals(Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.IAP_GREEN_PAPER_ENABLED))) {
            int i11 = 0;
            Array.ArrayIterator<PaperPackConfig> it2 = this.s.iterator();
            while (it2.hasNext()) {
                final PaperPackConfig next = it2.next();
                if (Game.i.progressManager.getGreenPapers() + next.c + next.d <= 999999999) {
                    Information information2 = null;
                    try {
                        information2 = Game.i.purchaseManager.purchaseManager.getInformation(next.f2809a);
                    } catch (Exception e) {
                        f2792a.w("failed to get IAP info %s", next.f2809a, e);
                    }
                    if (information2 != null && information2 != Information.UNAVAILABLE) {
                        Group a8 = a(i11 == 0 || i11 == 3 || i11 == 4, Game.i.assetManager.getDrawable(next.f2810b));
                        Actor image15 = new Image(Game.i.assetManager.getDrawable("item-cell-a-shape"));
                        image15.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                        image15.setSize(96.0f, 106.0f);
                        image15.setPosition(156.0f, 29.0f);
                        a8.addActor(image15);
                        Actor image16 = new Image(Game.i.assetManager.getDrawable("icon-money"));
                        image16.setColor(MaterialColor.GREEN.P500);
                        image16.setSize(64.0f, 64.0f);
                        image16.setPosition(172.0f, 63.0f);
                        a8.addActor(image16);
                        Label label45 = new Label(StringFormatter.compactNumber(next.c, false), Game.i.assetManager.getLabelStyle(21));
                        label45.setAlignment(1);
                        label45.setPosition(156.0f, 44.0f);
                        label45.setSize(96.0f, 16.0f);
                        a8.addActor(label45);
                        if (next.e > 0) {
                            Actor image17 = new Image(Game.i.assetManager.getDrawable("item-cell-b-shape"));
                            image17.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                            image17.setSize(96.0f, 106.0f);
                            image17.setPosition(252.0f, 29.0f);
                            a8.addActor(image17);
                            Actor image18 = new Image(Game.i.assetManager.getDrawable("icon-money"));
                            image18.setColor(MaterialColor.GREEN.P500);
                            image18.setSize(64.0f, 64.0f);
                            image18.setPosition(268.0f, 63.0f);
                            a8.addActor(image18);
                            Label label46 = new Label(StringFormatter.compactNumber(next.d, false), Game.i.assetManager.getLabelStyle(21));
                            label46.setAlignment(1);
                            label46.setColor(MaterialColor.AMBER.P400);
                            label46.setPosition(252.0f, 44.0f);
                            label46.setSize(96.0f, 16.0f);
                            a8.addActor(label46);
                            Actor image19 = new Image(Game.i.assetManager.getDrawable("icon-plus"));
                            image19.setColor(MaterialColor.AMBER.P400);
                            image19.setSize(24.0f, 24.0f);
                            image19.setPosition(241.0f, 72.0f);
                            a8.addActor(image19);
                            if (next.g != null) {
                                Group group5 = new Group();
                                group5.setTransform(false);
                                group5.setSize(96.0f, 106.0f);
                                group5.setPosition(348.0f, 29.0f);
                                a8.addActor(group5);
                                group5.setTouchable(Touchable.enabled);
                                group5.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.MoneyScreen.8
                                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                                    public void clicked(InputEvent inputEvent, float f6, float f7) {
                                        ItemDescriptionDialog.i().show(next.g.getItem());
                                    }
                                });
                                Image image20 = new Image(Game.i.assetManager.getDrawable("item-cell-a-shape"));
                                image20.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                                image20.setSize(96.0f, 106.0f);
                                group5.addActor(image20);
                                Actor generateIcon4 = next.g.getItem().generateIcon(64.0f, false);
                                generateIcon4.setSize(64.0f, 64.0f);
                                generateIcon4.setPosition(16.0f, 34.0f);
                                group5.addActor(generateIcon4);
                                Label label47 = new Label(StringFormatter.commaSeparatedNumber(next.g.getCount()), Game.i.assetManager.getLabelStyle(21));
                                label47.setAlignment(1);
                                label47.setColor(MaterialColor.AMBER.P400);
                                label47.setPosition(0.0f, 15.0f);
                                label47.setSize(96.0f, 16.0f);
                                group5.addActor(label47);
                                Actor image21 = new Image(Game.i.assetManager.getDrawable("icon-plus"));
                                image21.setTouchable(Touchable.disabled);
                                image21.setColor(MaterialColor.AMBER.P400);
                                image21.setSize(24.0f, 24.0f);
                                image21.setPosition(337.0f, 72.0f);
                                a8.addActor(image21);
                            }
                            Label label48 = new Label("+" + next.e + "%", Game.i.assetManager.getLabelStyle(next.f));
                            label48.setColor(MaterialColor.AMBER.P400);
                            label48.setSize(82.0f, 24.0f);
                            label48.setAlignment(16);
                            label48.setPosition(490.0f, 117.0f);
                            a8.addActor(label48);
                        }
                        a8.addActor(a(information2.getLocalPricing(), () -> {
                            a(next.f2809a);
                        }));
                        Cell padBottom3 = table2.add((Table) a8).size(590.0f, 166.0f).padBottom(20.0f);
                        if (i11 % 2 == 0) {
                            padBottom3.padRight(20.0f);
                        } else {
                            padBottom3.row();
                        }
                        i11++;
                    }
                }
            }
            table2.row();
            table2.add().height(46.0f).width(1.0f).row();
        } else {
            table2.row();
            table2.add().height(192.0f).width(1.0f).row();
        }
        if (Game.i.purchaseManager.isPurchasesEnabled() && Game.i.progressManager.getAccelerators() < 100000000 && "true".equals(Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.IAP_ACCELERATOR_ENABLED))) {
            int i12 = 0;
            Array.ArrayIterator<AcceleratorPackConfig> it3 = this.t.iterator();
            while (it3.hasNext()) {
                final AcceleratorPackConfig next2 = it3.next();
                Information information3 = null;
                try {
                    information3 = Game.i.purchaseManager.purchaseManager.getInformation(next2.f2807a);
                } catch (Exception e2) {
                    f2792a.w("failed to get IAP info %s", next2.f2807a, e2);
                }
                if (information3 != null && information3 != Information.UNAVAILABLE) {
                    Group a9 = a(i12 == 0 || i12 == 3 || i12 == 4, Game.i.assetManager.getDrawable(next2.f2808b));
                    Actor image22 = new Image(Game.i.assetManager.getDrawable("item-cell-a-shape"));
                    image22.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                    image22.setSize(96.0f, 106.0f);
                    image22.setPosition(156.0f, 29.0f);
                    a9.addActor(image22);
                    Actor image23 = new Image(Game.i.assetManager.getDrawable("time-accelerator"));
                    image23.setSize(64.0f, 64.0f);
                    image23.setPosition(172.0f, 63.0f);
                    a9.addActor(image23);
                    Label label49 = new Label(StringFormatter.commaSeparatedNumber(next2.c), Game.i.assetManager.getLabelStyle(21));
                    label49.setAlignment(1);
                    label49.setPosition(156.0f, 44.0f);
                    label49.setSize(96.0f, 16.0f);
                    a9.addActor(label49);
                    if (next2.e > 0) {
                        Actor image24 = new Image(Game.i.assetManager.getDrawable("item-cell-b-shape"));
                        image24.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                        image24.setSize(96.0f, 106.0f);
                        image24.setPosition(252.0f, 29.0f);
                        a9.addActor(image24);
                        Actor image25 = new Image(Game.i.assetManager.getDrawable("time-accelerator"));
                        image25.setSize(64.0f, 64.0f);
                        image25.setPosition(268.0f, 63.0f);
                        a9.addActor(image25);
                        Label label50 = new Label(StringFormatter.commaSeparatedNumber(next2.d), Game.i.assetManager.getLabelStyle(21));
                        label50.setAlignment(1);
                        label50.setColor(MaterialColor.AMBER.P400);
                        label50.setPosition(252.0f, 44.0f);
                        label50.setSize(96.0f, 16.0f);
                        a9.addActor(label50);
                        Actor image26 = new Image(Game.i.assetManager.getDrawable("icon-plus"));
                        image26.setColor(MaterialColor.AMBER.P400);
                        image26.setSize(24.0f, 24.0f);
                        image26.setPosition(241.0f, 72.0f);
                        a9.addActor(image26);
                        if (next2.g != null) {
                            Group group6 = new Group();
                            group6.setTransform(false);
                            group6.setSize(96.0f, 106.0f);
                            group6.setPosition(348.0f, 29.0f);
                            a9.addActor(group6);
                            group6.setTouchable(Touchable.enabled);
                            group6.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.MoneyScreen.9
                                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                                public void clicked(InputEvent inputEvent, float f6, float f7) {
                                    ItemDescriptionDialog.i().show(next2.g.getItem());
                                }
                            });
                            Image image27 = new Image(Game.i.assetManager.getDrawable("item-cell-a-shape"));
                            image27.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                            image27.setSize(96.0f, 106.0f);
                            group6.addActor(image27);
                            Actor generateIcon5 = next2.g.getItem().generateIcon(64.0f, false);
                            generateIcon5.setSize(64.0f, 64.0f);
                            generateIcon5.setPosition(16.0f, 34.0f);
                            group6.addActor(generateIcon5);
                            Label label51 = new Label(StringFormatter.commaSeparatedNumber(next2.g.getCount()), Game.i.assetManager.getLabelStyle(21));
                            label51.setAlignment(1);
                            label51.setColor(MaterialColor.AMBER.P400);
                            label51.setPosition(0.0f, 15.0f);
                            label51.setSize(96.0f, 16.0f);
                            group6.addActor(label51);
                            Actor image28 = new Image(Game.i.assetManager.getDrawable("icon-plus"));
                            image28.setTouchable(Touchable.disabled);
                            image28.setColor(MaterialColor.AMBER.P400);
                            image28.setSize(24.0f, 24.0f);
                            image28.setPosition(337.0f, 72.0f);
                            a9.addActor(image28);
                        }
                        Label label52 = new Label("+" + next2.e + "%", Game.i.assetManager.getLabelStyle(next2.f));
                        label52.setColor(MaterialColor.AMBER.P400);
                        label52.setSize(82.0f, 24.0f);
                        label52.setAlignment(16);
                        label52.setPosition(490.0f, 117.0f);
                        a9.addActor(label52);
                    }
                    a9.addActor(a(information3.getLocalPricing(), () -> {
                        a(next2.f2807a);
                    }));
                    Cell padBottom4 = table2.add((Table) a9).size(590.0f, 166.0f).padBottom(20.0f);
                    if (i12 % 2 == 0) {
                        padBottom4.padRight(20.0f);
                    } else {
                        padBottom4.row();
                    }
                    i12++;
                }
            }
        }
        table2.row();
        table2.add().height(160.0f).width(1.0f).row();
        b();
        scrollPane.layout();
        scrollPane.setScrollY(f);
        scrollPane.updateVisualScroll();
        this.d.getTable().clear();
        this.d.getTable().addListener(new InputVoid());
        this.d.getTable().background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(Config.BACKGROUND_COLOR.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f)));
        Image image29 = new Image(Game.i.assetManager.getDrawable("loading-icon"));
        image29.setColor(MaterialColor.CYAN.P500);
        image29.addAction(Actions.forever(Actions.sequence(Actions.color(MaterialColor.CYAN.P500, 1.0f), Actions.color(MaterialColor.LIGHT_GREEN.P500, 1.0f), Actions.color(MaterialColor.ORANGE.P500, 1.0f), Actions.color(MaterialColor.PINK.P500, 1.0f), Actions.color(MaterialColor.PURPLE.P500, 1.0f))));
        image29.addAction(Actions.forever(Actions.rotateBy(90.0f, 0.5f)));
        image29.setOrigin(64.0f, 64.0f);
        this.d.getTable().add((Table) image29).size(128.0f);
        this.d.getTable().setVisible(false);
    }

    private void a(int i) {
        Array<ItemStack> cumulativePrice;
        Array<ItemStack> price;
        this.j = i;
        for (int i2 = 0; i2 < this.i.size; i2++) {
            Image image = (Image) this.k.findActor("shop-offer-cell-selection-" + i2);
            if (image != null) {
                if (i2 == i) {
                    image.setVisible(true);
                } else {
                    image.setVisible(false);
                }
            }
        }
        if (i == -1) {
            TooltipsOverlay.i().hideEntry("shop-offer-summary");
            return;
        }
        Table table = new Table();
        Array<ProgressManager.ShopOffer> shopOffers = Game.i.progressManager.getShopOffers();
        if (shopOffers != null && shopOffers.size > i) {
            ProgressManager.ShopOffer shopOffer = shopOffers.get(i);
            Label label = new Label(shopOffer.item.getItem().getTitle(), Game.i.assetManager.getLabelStyle(24));
            label.setWrap(true);
            label.setAlignment(1);
            table.add((Table) label).growX().row();
            Label label2 = new Label(shopOffer.item.getItem().getDescription(), Game.i.assetManager.getLabelStyle(18));
            label2.setWrap(true);
            label2.setAlignment(1);
            label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table.add((Table) label2).growX().maxWidth(500.0f).row();
            Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image2.setColor(0.0f, 0.0f, 0.0f, 0.36f);
            table.add((Table) image2).growX().height(1.0f).padTop(10.0f).row();
            Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image3.setColor(1.0f, 1.0f, 1.0f, 0.21f);
            table.add((Table) image3).growX().height(1.0f).padBottom(10.0f).row();
            if (shopOffer.purchased) {
                Label label3 = new Label(Game.i.localeManager.i18n.get("already_purchased"), Game.i.assetManager.getLabelStyle(21));
                label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                table.add((Table) label3);
            } else if (Game.i.progressManager.getItemsCount(shopOffer.price.getItem()) < shopOffer.price.getCount()) {
                Label label4 = new Label(Game.i.localeManager.i18n.get("not_enough_items"), Game.i.assetManager.getLabelStyle(21));
                label4.setColor(MaterialColor.ORANGE.P500);
                table.add((Table) label4);
            } else {
                Label label5 = new Label(Game.i.localeManager.i18n.get("click_offer_again_to_buy"), Game.i.assetManager.getLabelStyle(21));
                label5.setColor(MaterialColor.LIGHT_GREEN.P500);
                table.add((Table) label5);
            }
            table.row();
            Image image4 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image4.setColor(0.0f, 0.0f, 0.0f, 0.36f);
            table.add((Table) image4).growX().height(1.0f).padTop(10.0f).row();
            Image image5 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image5.setColor(1.0f, 1.0f, 1.0f, 0.21f);
            table.add((Table) image5).growX().height(1.0f).padBottom(10.0f).row();
            int i3 = 0;
            long j = 0;
            Array<Research> instances = Game.i.researchManager.getInstances();
            for (int i4 = 0; i4 < instances.size; i4++) {
                Research research = instances.get(i4);
                if (!Game.i.researchManager.canStartResearching(research, false)) {
                    boolean z = false;
                    if (Game.i.progressManager.getDifficultyMode() == DifficultyMode.EASY || Game.i.progressManager.getDifficultyMode() == DifficultyMode.NORMAL) {
                        if (!research.isMaxNormalLevel()) {
                            z = true;
                        }
                    } else if (!research.isMaxEndlessLevel()) {
                        z = true;
                    }
                    if (z) {
                        boolean z2 = true;
                        if (research.priceInStars > 0) {
                            boolean z3 = false;
                            int i5 = 0;
                            while (true) {
                                if (i5 >= research.linksToParents.size) {
                                    break;
                                }
                                if (research.linksToParents.get(i5).parent.getInstalledLevel() <= 0) {
                                    i5++;
                                } else {
                                    z3 = true;
                                    break;
                                }
                            }
                            int i6 = 0;
                            while (true) {
                                if (i6 >= research.linksToChildren.size) {
                                    break;
                                }
                                if (research.linksToChildren.get(i6).child.getInstalledLevel() <= 0) {
                                    i6++;
                                } else {
                                    z3 = true;
                                    break;
                                }
                            }
                            if (!z3) {
                                z2 = false;
                            }
                            if (Game.i.researchManager.getUnusedStarsCount() < research.priceInStars) {
                                z2 = false;
                            }
                        } else {
                            int i7 = 0;
                            while (true) {
                                if (i7 >= research.linksToParents.size) {
                                    break;
                                }
                                Research.ResearchLink researchLink = research.linksToParents.get(i7);
                                if (researchLink.requiredLevels <= researchLink.parent.getInstalledLevel()) {
                                    i7++;
                                } else {
                                    z2 = false;
                                    break;
                                }
                            }
                        }
                        if (z2) {
                            if (research.levels.length > research.getInstalledLevel()) {
                                price = research.levels[research.getInstalledLevel()].price;
                            } else {
                                price = research.endlessLevel.getPrice(research.getInstalledLevel() + 1);
                            }
                            for (int i8 = 0; i8 < price.size; i8++) {
                                ItemStack itemStack = price.items[i8];
                                if (itemStack.getItem().sameAs(shopOffer.item.getItem()) && itemStack.getCount() > Game.i.progressManager.getItemsCount(itemStack.getItem())) {
                                    i3++;
                                }
                            }
                        }
                    }
                }
                if (research.priceInStars == 0) {
                    if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                        cumulativePrice = research.getCumulativePrice(research.getInstalledLevel(), research.maxEndlessLevel, false);
                    } else {
                        cumulativePrice = research.getCumulativePrice(research.getInstalledLevel(), research.levels.length, false);
                    }
                    for (int i9 = 0; i9 < cumulativePrice.size; i9++) {
                        if (cumulativePrice.get(i9).getItem().sameAs(shopOffer.item.getItem())) {
                            j += r0.getCount();
                        }
                    }
                }
            }
            f2792a.i("fullTreeRequiredCount " + j, new Object[0]);
            if (i3 != 0) {
                Label label6 = new Label(Game.i.localeManager.i18n.format("item_required_by_research_count_tooltip", Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-research>" + i3).toString()), Game.i.assetManager.getLabelStyle(18));
                label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                table.add((Table) label6).row();
            }
            if (j != 0) {
                Label label7 = new Label(Game.i.localeManager.i18n.format("item_required_for_full_research_tooltip", StringFormatter.compactNumber(j, false).toString()), Game.i.assetManager.getLabelStyle(18));
                label7.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                table.add((Table) label7).row();
            }
            Label label8 = new Label(Game.i.localeManager.i18n.format("you_have_n_such_items", StringFormatter.compactNumber(Game.i.progressManager.getItemsCount(shopOffer.item.getItem()), false).toString()), Game.i.assetManager.getLabelStyle(18));
            label8.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table.add((Table) label8).row();
            TooltipsOverlay.i().showActor("shop-offer-summary", this.i.get(i), table, this.c.mainUiLayer, this.c.zIndex + 1, 4).onDispose = () -> {
                if (i == this.j) {
                    a(-1);
                }
            };
        }
    }

    private void a(String str) {
        this.d.getTable().setVisible(true);
        Game.i.purchaseManager.purchaseManager.purchase(str);
    }

    private void b() {
        this.n.setText(StringFormatter.digestTimeWithZeroHours((int) Game.i.progressManager.getLootBoostTimeLeft(), true));
        if (Game.i.progressManager.getLootBoostTimeLeft() > 0.0f) {
            this.n.setColor(MaterialColor.LIGHT_GREEN.P500);
        } else {
            this.n.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        }
        if (!Game.i.purchaseManager.rewardingAdsAvailable() || this.e == null) {
            return;
        }
        if (Game.i.purchaseManager.canShowRewardingAd(PurchaseManager.RewardingAdsType.REGULAR)) {
            this.e.setVisible(true);
            this.f.setVisible(false);
        } else {
            this.e.setVisible(false);
            int secondsTillAdIsReady = Game.i.purchaseManager.getSecondsTillAdIsReady(PurchaseManager.RewardingAdsType.REGULAR);
            if (secondsTillAdIsReady > 0) {
                this.f.setText(StringFormatter.digestTime(secondsTillAdIsReady));
                this.f.setVisible(true);
            }
        }
        if (Game.i.progressManager.hasTemporaryDoubleGain() && this.g != null) {
            this.g.setText(StringFormatter.timePassed(Game.i.progressManager.getTempDoubleGainDurationLeft(), true, true));
        }
    }

    private void c() {
        dispose();
        if (this.f2793b instanceof AboutScreen) {
            Game.i.screenManager.goToAboutScreen();
            return;
        }
        if (this.f2793b instanceof LevelSelectScreen) {
            Game.i.screenManager.goToLevelSelectScreen();
            return;
        }
        if (this.f2793b instanceof CustomMapSelectScreen) {
            Game.i.screenManager.goToCustomMapSelectScreen();
            return;
        }
        if (this.f2793b instanceof ResearchesScreen) {
            Game.i.screenManager.goToResearchesScreen();
            return;
        }
        if (this.f2793b instanceof SettingsScreen) {
            Game.i.screenManager.goToSettingsScreen();
        } else if (this.f2793b instanceof StatisticsScreen) {
            Game.i.screenManager.goToStatisticsScreen();
        } else {
            Game.i.screenManager.goToMainMenu();
        }
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (Game.i.settingsManager.isEscButtonJustPressed()) {
            c();
            return;
        }
        this.h += f;
        if (this.h > 1.0f) {
            b();
            this.h = 0.0f;
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.c);
        Game.i.uiManager.removeLayer(this.d);
        Game.i.purchaseManager.removeListener(this.l);
        Game.i.progressManager.removeListener(this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/MoneyScreen$_ProgressManagerListener.class */
    public class _ProgressManagerListener extends ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter {
        private _ProgressManagerListener() {
        }

        /* synthetic */ _ProgressManagerListener(MoneyScreen moneyScreen, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter, com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
        public void itemsChanged(Item item, int i, int i2) {
            if (item == Item.D.LUCKY_SHOT_TOKEN || item == Item.D.GREEN_PAPER || item == Item.D.ACCELERATOR || item == Item.D.LOOT_BOOST || item == Item.D.RARITY_BOOST || item == Item.D.CASE_KEY_BLUE || item == Item.D.CASE_KEY_ORANGE || item == Item.D.CASE_KEY_PURPLE || item == Item.D.CASE_KEY_CYAN) {
                MoneyScreen.this.a();
            }
        }

        @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener.ProgressManagerListenerAdapter, com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
        public void doubleGainEnabled() {
            MoneyScreen.this.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/MoneyScreen$_PurchaseManagerListener.class */
    public class _PurchaseManagerListener extends PurchaseManager.PurchaseManagerListener.PurchaseManagerListenerAdapter {
        private _PurchaseManagerListener() {
        }

        /* synthetic */ _PurchaseManagerListener(MoneyScreen moneyScreen, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.PurchaseManager.PurchaseManagerListener.PurchaseManagerListenerAdapter, com.prineside.tdi2.managers.PurchaseManager.PurchaseManagerListener
        public void purchased(Transaction transaction) {
            MoneyScreen.this.a();
        }

        @Override // com.prineside.tdi2.managers.PurchaseManager.PurchaseManagerListener.PurchaseManagerListenerAdapter, com.prineside.tdi2.managers.PurchaseManager.PurchaseManagerListener
        public void gotResponse(String str, Object obj) {
            MoneyScreen.this.d.getTable().setVisible(false);
        }
    }
}
