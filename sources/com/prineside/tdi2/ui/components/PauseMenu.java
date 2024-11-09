package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.events.game.GamePaused;
import com.prineside.tdi2.events.game.GameResumed;
import com.prineside.tdi2.events.game.RewardingAdRegistered;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.PurchaseManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_BasicLevel;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.systems.LootSystem;
import com.prineside.tdi2.ui.actors.AttentionRaysUnderlay;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.HorizontalSlider;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.RightSideMenuButton;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.ItemDescriptionDialog;
import com.prineside.tdi2.ui.shared.MusicListOverlay;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/PauseMenu.class */
public class PauseMenu implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3374a = TLog.forClass(PauseMenu.class);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3375b = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SCREEN, 200, "PauseMenu overlay", true);
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 201, "PauseMenu main");
    private final UiManager.UiLayer d = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, HttpStatus.SC_ACCEPTED, "PauseMenu ad loot bonus");
    private Group e;
    private Group f;
    private Group g;
    private Group h;
    private Table i;
    private Table j;
    private Label k;
    private Label l;
    private Label m;
    private GameSystemProvider n;

    public PauseMenu(GameSystemProvider gameSystemProvider) {
        PP_BasicLevel.LevelLootBonus levelLootBonus;
        this.n = gameSystemProvider;
        this.d.getTable().setVisible(false);
        this.h = new Group();
        this.h.setTransform(false);
        this.d.getTable().add().width(1.0f).expandY().fillY().row();
        this.d.getTable().add((Table) this.h).size(540.0f, 105.0f).bottom().padBottom(50.0f);
        Image image = new Image(Game.i.assetManager.getBlankWhiteTextureRegion());
        image.setColor(new Color(218959311));
        this.f3375b.getTable().add((Table) image).expand().fill();
        Table table = this.c.getTable();
        table.setName("pause_menu");
        Table table2 = new Table();
        table.add(table2).expand().fill();
        this.i = new Table();
        ScrollPane scrollPane = new ScrollPane(this.i, Game.i.assetManager.getScrollPaneStyle(0.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setOverscroll(false, false);
        scrollPane.setSmoothScrolling(false);
        scrollPane.setFadeScrollBars(false);
        table2.add((Table) scrollPane).expand().fill().top().left().padTop(40.0f).row();
        Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.childrenOnly);
        table2.add((Table) group).size(1.0f, 256.0f).bottom().left().row();
        this.e = new Group();
        this.e.setTransform(false);
        this.e.setPosition(100.0f, 173.0f);
        group.addActor(this.e);
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-arrow-pointer-bottom-left"));
        image2.setSize(64.0f, 64.0f);
        this.e.addActor(image2);
        Label label = new Label(Game.i.localeManager.i18n.get("pause_menu_hint_hold_for_auto_wave"), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        label.setPosition(74.0f, 48.0f);
        this.e.addActor(label);
        this.f = new Group();
        this.f.setTransform(false);
        this.f.setPosition(268.0f, 136.0f);
        group.addActor(this.f);
        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-arrow-pointer-bottom-left"));
        image3.setSize(64.0f, 64.0f);
        this.f.addActor(image3);
        Label label2 = new Label(Game.i.localeManager.i18n.get("pause_menu_hint_hold_for_pause"), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        label2.setPosition(74.0f, 48.0f);
        this.f.addActor(label2);
        this.g = new Group();
        this.g.setTransform(false);
        this.g.setPosition(290.0f, 380.0f);
        group.addActor(this.g);
        ComplexButton complexButton = new ComplexButton(Game.i.localeManager.i18n.get("mainMenu_musicPlayerButton"), Game.i.assetManager.getLabelStyle(24), () -> {
            MusicListOverlay.i().show();
            gameSystemProvider._sound.updateMusicPlayback();
        });
        complexButton.setSize(300.0f, 96.0f);
        complexButton.setPosition(460.0f, 16.0f);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-music-player"), 16.0f, 16.0f, 64.0f, 64.0f);
        complexButton.setLabel(96.0f, 0.0f, 236.0f, 96.0f, 8);
        complexButton.setIconLabelColors(MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P700, Color.GRAY);
        group.addActor(complexButton);
        Table table3 = new Table();
        table.add(table3).right().expandY().fillY().padBottom(40.0f);
        Table table4 = new Table();
        table3.add(table4).top().right().row();
        this.k = new Label("", Game.i.assetManager.getLabelStyle(36));
        table4.add((Table) this.k).padRight(40.0f).padTop(40.0f).top().right().row();
        this.l = new Label("", Game.i.assetManager.getLabelStyle(24));
        table4.add((Table) this.l).padRight(40.0f).padTop(8.0f).top().right().row();
        this.m = new Label("", Game.i.assetManager.getLabelStyle(24));
        table4.add((Table) this.m).padRight(40.0f).padTop(8.0f).top().right().row();
        table4.add().width(1.0f).height(32.0f).row();
        if (gameSystemProvider.gameState.rarityBoostEnabled) {
            Table table5 = new Table();
            table4.add(table5).padTop(8.0f).padRight(40.0f).expandX().fillX().row();
            table5.add().height(1.0f).expandX().fillX();
            Label label3 = new Label(Item.D.RARITY_BOOST.getTitle(), Game.i.assetManager.getLabelStyle(21));
            label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table5.add((Table) label3);
            table5.add((Table) Item.D.RARITY_BOOST.generateIcon(32.0f, false)).size(32.0f).padLeft(12.0f);
        }
        if (gameSystemProvider.gameState.lootBoostEnabled) {
            Table table6 = new Table();
            table4.add(table6).padTop(8.0f).padRight(40.0f).expandX().fillX().row();
            table6.add().height(1.0f).expandX().fillX();
            Label label4 = new Label(Item.D.LOOT_BOOST.getTitle(), Game.i.assetManager.getLabelStyle(21));
            label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table6.add((Table) label4);
            table6.add((Table) Item.D.LOOT_BOOST.generateIcon(32.0f, false)).size(32.0f).padLeft(12.0f);
        }
        if (gameSystemProvider.gameState.basicLevelName != null && (levelLootBonus = ProgressPrefs.i().basicLevel.getLevelLootBonus(gameSystemProvider.gameState.basicLevelName)) != null) {
            Table table7 = new Table();
            table4.add(table7).padTop(8.0f).padRight(40.0f).expandX().fillX().row();
            table7.add().height(1.0f).expandX().fillX();
            table7.add((Table) new Image(levelLootBonus.getIconDrawable())).size(32.0f).padRight(12.0f);
            Label label5 = new Label("x" + ((Object) StringFormatter.compactNumberWithPrecisionTrimZeros(levelLootBonus.multiplier, 1, true)), Game.i.assetManager.getLabelStyle(21));
            label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table7.add((Table) label5);
        }
        table3.add().expandY().fillY().width(100.0f).minHeight(30.0f).row();
        this.j = new Table();
        table3.add(this.j).padRight(40.0f).right().row();
        String str = Game.i.localeManager.i18n.get("settings_instant_auto_wave_calls");
        String str2 = str;
        table3.add(new LabelToggleButton(str.length() > 24 ? str2.substring(0, 22) + "..." : str2, Game.i.settingsManager.isInstantAutoWaveCallEnabled(), 24, 32.0f, bool -> {
            Game.i.settingsManager.setInstantAutoWaveCallEnabled(bool.booleanValue());
            gameSystemProvider.wave.instantWaveCallsEnabled = bool.booleanValue();
        })).height(56.0f).width(348.0f).padRight(40.0f).right().row();
        table3.add(new LabelToggleButton(Game.i.localeManager.i18n.get("settings_draw_particles"), Game.i.settingsManager.isParticlesDrawing(), 24, 32.0f, bool2 -> {
            Game.i.settingsManager.setParticlesDrawing(bool2.booleanValue());
        })).height(56.0f).width(348.0f).padRight(40.0f).right().row();
        Group group2 = new Group();
        group2.setTransform(false);
        Label label6 = new Label(Game.i.localeManager.i18n.get("settings_sound"), Game.i.assetManager.getLabelStyle(24));
        label6.setSize(1.0f, 56.0f);
        group2.addActor(label6);
        HorizontalSlider horizontalSlider = new HorizontalSlider(200.0f, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SOUND_VOLUME), 0.0d, 1.0d, d -> {
            Game.i.settingsManager.setSoundVoulme(d.doubleValue());
        });
        horizontalSlider.setNotifyOnDrag(true);
        horizontalSlider.setPosition(148.0f, (56.0f - horizontalSlider.getHeight()) * 0.5f);
        group2.addActor(horizontalSlider);
        table3.add((Table) group2).height(56.0f).width(348.0f).padRight(40.0f).right().row();
        Group group3 = new Group();
        group3.setTransform(false);
        Label label7 = new Label(Game.i.localeManager.i18n.get("settings_music"), Game.i.assetManager.getLabelStyle(24));
        label7.setSize(1.0f, 56.0f);
        group3.addActor(label7);
        HorizontalSlider horizontalSlider2 = new HorizontalSlider(200.0f, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_VOLUME), 0.0d, 1.0d, d2 -> {
            if (gameSystemProvider._sound == null) {
                return;
            }
            Game.i.settingsManager.setMusicVolume(d2.doubleValue());
            gameSystemProvider._sound.updateMusicPlayback();
        });
        horizontalSlider2.setNotifyOnDrag(true);
        horizontalSlider2.setPosition(148.0f, (56.0f - horizontalSlider.getHeight()) * 0.5f);
        group3.addActor(horizontalSlider2);
        table3.add((Table) group3).height(56.0f).width(348.0f).padRight(40.0f).right().row();
        table3.add().height(16.0f).row();
        RightSideMenuButton rightSideMenuButton = new RightSideMenuButton(Game.i.localeManager.i18n.get("continue"), "icon-triangle-right", () -> {
            try {
                gameSystemProvider.gameState.resumeGame();
            } catch (Throwable th) {
                f3374a.e("failed to resume game", th);
            }
        });
        rightSideMenuButton.setColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P700, MaterialColor.GREEN.P900, Color.WHITE);
        table3.add((Table) rightSideMenuButton).row();
        table3.add((Table) new RightSideMenuButton(Game.i.localeManager.i18n.get("restart"), "icon-restart", () -> {
            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("restart_confirm"), () -> {
                if (gameSystemProvider.gameState.replayMode) {
                    GameStateSystem.startReplay(gameSystemProvider.gameState.replayRecord);
                } else if (gameSystemProvider.gameState.basicLevelName != null && Game.i.basicLevelManager.getLevel(gameSystemProvider.gameState.basicLevelName).canNotBeRestarted) {
                    Notifications.i().addFailure(Game.i.localeManager.i18n.get("level_can_not_be_restarted"));
                } else {
                    gameSystemProvider.gameState.restartGame(true);
                }
            });
        })).row();
        table3.add((Table) new RightSideMenuButton(Game.i.localeManager.i18n.get("end_game_button_text"), "icon-times", () -> {
            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("end_game_button_confirm"), () -> {
                setVisible(false);
                gameSystemProvider.gameState.triggerGameOver(GameStateSystem.GameOverReason.MANUAL);
            });
        })).row();
        RightSideMenuButton rightSideMenuButton2 = new RightSideMenuButton(Game.i.localeManager.i18n.get("main_menu"), "icon-house", () -> {
            if (!gameSystemProvider.gameValue.getBooleanValue(GameValueType.GAME_SAVES)) {
                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("game_cant_be_continued_confirm"), () -> {
                    Game.i.screenManager.goToMainMenu();
                });
            } else {
                Game.i.screenManager.goToMainMenu();
            }
        });
        rightSideMenuButton2.setName("pause_menu_main_menu_button");
        table3.add((Table) rightSideMenuButton2);
        this.f3375b.getTable().setVisible(false);
        this.c.getTable().setVisible(false);
        this.d.getTable().setVisible(false);
        gameSystemProvider.events.getListeners(RewardingAdRegistered.class).add(rewardingAdRegistered -> {
            if (gameSystemProvider.gameState.isFastForwarding() || !this.c.getTable().isVisible()) {
                return;
            }
            setVisible(true);
        }).setDescription("PauseMenu - hides the menu on ad shown");
        gameSystemProvider.events.getListeners(GamePaused.class).add(gamePaused -> {
            setVisible(true);
        }).setDescription("PauseMenu - shows itself");
        gameSystemProvider.events.getListeners(GameResumed.class).add(gameResumed -> {
            setVisible(false);
        }).setDescription("PauseMenu - hides itself");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setVisible(boolean z) {
        Label label;
        this.f3375b.getTable().setVisible(z);
        this.c.getTable().setVisible(z);
        this.d.getTable().setVisible(false);
        if (!z) {
            Game.i.uiManager.stage.setScrollFocus(null);
        }
        if (z && !this.n.gameState.isGameOver()) {
            Game.i.preferencesManager.saveNowIfRequired();
            if (Game.i.purchaseManager.rewardingAdsAvailable()) {
                this.d.getTable().setVisible(true);
                this.h.clear();
                this.h.addActor(new QuadActor(new Color(943208703), new float[]{5.0f, 0.0f, 0.0f, 100.0f, 540.0f, 105.0f, 535.0f, 0.0f}));
                Table table = new Table();
                table.setPosition(19.0f, 60.0f);
                table.setSize(300.0f, 24.0f);
                this.h.addActor(table);
                table.add((Table) new Label(Game.i.localeManager.i18n.get("loot_bonus_menu_title"), Game.i.assetManager.getLabelStyle(21)));
                final Image image = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
                table.add((Table) image).padLeft(4.0f).size(24.0f);
                table.setTouchable(Touchable.enabled);
                table.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.PauseMenu.1
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f, float f2) {
                        TooltipsOverlay.i().showText(TooltipsOverlay.TAG_GENERIC_TOOLTIP, image, Game.i.localeManager.i18n.get("pause_menu_ad_loot_bonus_info"), PauseMenu.this.c.mainUiLayer, PauseMenu.this.c.zIndex + 1, 2);
                    }
                });
                table.add().height(1.0f).growX();
                int rewardingAdViews = this.n.loot.getRewardingAdViews();
                for (int i = 0; i < 5; i++) {
                    Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image2.setPosition(19.0f + (i * 62.0f), 13.0f);
                    image2.setSize(58.0f, 36.0f);
                    this.h.addActor(image2);
                    if (i == 4) {
                        label = new Label(LootSystem.REWARDING_AD_VIEW_BONUSES[i] + "%", Game.i.assetManager.getLabelStyle(24));
                    } else {
                        label = new Label(LootSystem.REWARDING_AD_VIEW_BONUSES[i] + "%", Game.i.assetManager.getLabelStyle(21));
                    }
                    label.setPosition(image2.getX(), image2.getY());
                    label.setAlignment(1);
                    label.setSize(58.0f, 36.0f);
                    this.h.addActor(label);
                    if (rewardingAdViews > i) {
                        label.setColor(Color.WHITE);
                        if (i == 4) {
                            image2.setColor(MaterialColor.CYAN.P800);
                        } else {
                            image2.setColor(MaterialColor.GREEN.P800);
                        }
                    } else {
                        image2.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                        if (i == 4) {
                            label.setColor(MaterialColor.CYAN.P700);
                        } else {
                            label.setColor(MaterialColor.GREY.P700);
                        }
                    }
                }
                AttentionRaysUnderlay attentionRaysUnderlay = new AttentionRaysUnderlay(192.0f, MaterialColor.LIGHT_BLUE.P300);
                attentionRaysUnderlay.setPosition(340.0f, -44.0f);
                this.h.addActor(attentionRaysUnderlay);
                boolean isPremiumStatusActive = Game.i.progressManager.isPremiumStatusActive();
                ComplexButton complexButton = new ComplexButton(Game.i.localeManager.i18n.get("do_upgrade"), Game.i.assetManager.getLabelStyle(24), () -> {
                    if (isPremiumStatusActive) {
                        Dialog.i().showAlert(Game.i.localeManager.i18n.get("auto_pause_menu_reward_ads"));
                    } else {
                        Game.i.purchaseManager.showRewardingAd(PurchaseManager.RewardingAdsType.LOOT_MULTIPLIER, bool -> {
                            if (bool.booleanValue()) {
                                this.n.loot.viewRewardingAdAction();
                            }
                        });
                    }
                });
                complexButton.setPosition(344.0f, 13.0f);
                complexButton.setSize(184.0f, 78.0f);
                complexButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{3.0f, 0.0f, 0.0f, 76.0f, 184.0f, 78.0f, 181.0f, 0.0f})), 0.0f, 0.0f, 184.0f, 78.0f);
                complexButton.setIconPositioned(Game.i.assetManager.getDrawable(isPremiumStatusActive ? "icon-crown" : "rewarding-ad"), 14.0f, 15.0f, 48.0f, 48.0f);
                complexButton.setLabel(73.0f, 15.0f, 100.0f, 48.0f, 8);
                this.h.addActor(complexButton);
                float timeToRewardingAds = this.n.loot.getTimeToRewardingAds(true);
                if (timeToRewardingAds == -1.0f) {
                    attentionRaysUnderlay.setVisible(false);
                    complexButton.setText("MAX");
                    if (!isPremiumStatusActive) {
                        complexButton.setEnabled(false);
                    }
                } else if (timeToRewardingAds == 0.0f) {
                    attentionRaysUnderlay.setVisible(true);
                    complexButton.setEnabled(true);
                } else {
                    attentionRaysUnderlay.setVisible(false);
                    complexButton.setText(StringFormatter.digestTime(MathUtils.ceil(timeToRewardingAds)));
                    if (!isPremiumStatusActive) {
                        complexButton.setEnabled(false);
                    }
                }
            }
            GameStateSystem gameStateSystem = this.n.gameState;
            this.m.setText(Game.i.progressManager.getDifficultyName(gameStateSystem.difficultyMode));
            this.m.setColor(Game.i.progressManager.getDifficultyModeColor(gameStateSystem.difficultyMode));
            if (gameStateSystem.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
                if (gameStateSystem.dailyQuestLevel != null) {
                    this.l.setText(Game.i.localeManager.i18n.get("daily_quest"));
                } else {
                    this.l.setText(Game.i.localeManager.i18n.get("basic_level"));
                }
                this.k.setText(gameStateSystem.basicLevelName);
            } else {
                this.l.setText(Game.i.localeManager.i18n.get("custom_map"));
                this.k.setText(Game.i.userMapManager.getUserMap(gameStateSystem.userMapId).name);
            }
            this.e.setVisible(this.n._gameUi.mainUi.nextWaveButtonVisible());
            this.f.setVisible(this.n._gameUi.mainUi.gameSpeedButtonVisible());
            DelayedRemovalArray delayedRemovalArray = new DelayedRemovalArray();
            Array.ArrayIterator it = new Array(gameStateSystem.getQuestsIssuedPrizes()).iterator();
            while (it.hasNext()) {
                delayedRemovalArray.addAll(((IssuedItems) it.next()).items);
            }
            delayedRemovalArray.addAll(gameStateSystem.getGameLootIssuedItems().items);
            int calculatePrizeGreenPapers = gameStateSystem.calculatePrizeGreenPapers();
            if (calculatePrizeGreenPapers > 0) {
                delayedRemovalArray.add(new ItemStack(Item.D.GREEN_PAPER, calculatePrizeGreenPapers));
            }
            for (ResourceType resourceType : ResourceType.values) {
                int resources = gameStateSystem.getResources(resourceType);
                if (resources != 0) {
                    delayedRemovalArray.add(new ItemStack(Item.D.F_RESOURCE.create(resourceType), resources));
                }
            }
            Array array = new Array(ItemStack.class);
            for (int i2 = 0; i2 < delayedRemovalArray.size; i2++) {
                ItemStack itemStack = (ItemStack) delayedRemovalArray.get(i2);
                boolean z2 = false;
                int i3 = 0;
                while (true) {
                    if (i3 >= array.size) {
                        break;
                    }
                    ItemStack itemStack2 = ((ItemStack[]) array.items)[i3];
                    if (itemStack.getFlags() != itemStack2.getFlags() || !itemStack2.getItem().sameAs(itemStack.getItem())) {
                        i3++;
                    } else {
                        z2 = true;
                        itemStack2.setCount(PMath.addWithoutOverflow(itemStack2.getCount(), itemStack.getCount()));
                        break;
                    }
                }
                if (!z2) {
                    array.add(new ItemStack(itemStack));
                }
            }
            array.sort(ProgressManager.ITEM_RARITY_COMPARATOR);
            this.i.clear();
            if (!gameStateSystem.isGameOver()) {
                int i4 = 0;
                int i5 = 0;
                int i6 = array.size <= 25 ? 5 : 10;
                for (int i7 = 0; i7 < array.size; i7++) {
                    final ItemStack itemStack3 = ((ItemStack[]) array.items)[i7];
                    ItemCell itemCell = new ItemCell();
                    itemCell.setCompact();
                    itemCell.setItemStack(itemStack3);
                    itemCell.setColRow(i4, i5);
                    if (itemStack3.isCovered()) {
                        itemCell.setCovered(true);
                        itemCell.shine(false, false);
                    } else {
                        itemCell.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.PauseMenu.2
                            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                            public void clicked(InputEvent inputEvent, float f, float f2) {
                                ItemDescriptionDialog.i().showWithCount(itemStack3.getItem(), itemStack3.getCount());
                            }
                        });
                        itemCell.shine(false, false);
                    }
                    Cell add = this.i.add((Table) itemCell);
                    i4++;
                    if (i4 == i6) {
                        i5++;
                        i4 = 0;
                        add.row();
                    }
                }
            }
            a();
        }
    }

    private void a() {
        this.j.clear();
        String str = Game.i.localeManager.i18n.get("enabled");
        String str2 = Game.i.localeManager.i18n.get("disabled");
        TextureRegionDrawable drawable = Game.i.assetManager.getDrawable("icon-overload");
        Runnable runnable = () -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.MULTITHREADING, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MULTITHREADING) == 0.0d ? 1.0d : 0.0d);
            a();
            String str3 = Game.i.localeManager.i18n.get("settings_multithreaded_rendering");
            if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MULTITHREADING) == 0.0d) {
                Notifications.i().add(str3 + " - " + str2, null, null, null);
            } else {
                Notifications.i().add(str3 + " - " + str, null, null, null);
            }
        };
        Color color = Color.WHITE;
        PaddedImageButton paddedImageButton = new PaddedImageButton(drawable, runnable, color, color, Color.WHITE);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MULTITHREADING) != 0.0d) {
            paddedImageButton.setColors(MaterialColor.AMBER.P800, MaterialColor.AMBER.P700, MaterialColor.AMBER.P900);
        } else {
            paddedImageButton.setColors(MaterialColor.GREY.P800, MaterialColor.GREY.P700, MaterialColor.GREY.P900);
        }
        paddedImageButton.setIconPosition(6.0f, 6.0f).setIconSize(40.0f, 40.0f);
        this.j.add((Table) paddedImageButton).size(52.0f);
        TextureRegionDrawable drawable2 = Game.i.assetManager.getDrawable("icon-easel");
        Runnable runnable2 = () -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.POSTPROCESSING, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.POSTPROCESSING) == 0.0d ? 1.0d : 0.0d);
            a();
            String str3 = Game.i.localeManager.i18n.get("settings_postprocessing");
            if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.POSTPROCESSING) == 0.0d) {
                Notifications.i().add(str3 + " - " + str2, null, null, null);
            } else {
                Notifications.i().add(str3 + " - " + str, null, null, null);
            }
        };
        Color color2 = Color.WHITE;
        PaddedImageButton paddedImageButton2 = new PaddedImageButton(drawable2, runnable2, color2, color2, Color.WHITE);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.POSTPROCESSING) != 0.0d) {
            paddedImageButton2.setColors(MaterialColor.AMBER.P800, MaterialColor.AMBER.P700, MaterialColor.AMBER.P900);
        } else {
            paddedImageButton2.setColors(MaterialColor.GREY.P800, MaterialColor.GREY.P700, MaterialColor.GREY.P900);
        }
        paddedImageButton2.setIconPosition(6.0f, 6.0f).setIconSize(40.0f, 40.0f);
        this.j.add((Table) paddedImageButton2).size(52.0f);
        TextureRegionDrawable drawable3 = Game.i.assetManager.getDrawable("icon-explosion-range");
        Runnable runnable3 = () -> {
            Game.i.settingsManager.setExplosionsDrawing(!Game.i.settingsManager.isExplosionsDrawing());
            a();
            String str3 = Game.i.localeManager.i18n.get("settings_draw_explosions");
            if (Game.i.settingsManager.isExplosionsDrawing()) {
                Notifications.i().add(str3 + " - " + str, null, null, null);
            } else {
                Notifications.i().add(str3 + " - " + str2, null, null, null);
            }
        };
        Color color3 = Color.WHITE;
        PaddedImageButton paddedImageButton3 = new PaddedImageButton(drawable3, runnable3, color3, color3, Color.WHITE);
        if (Game.i.settingsManager.isExplosionsDrawing()) {
            paddedImageButton3.setColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P900);
        } else {
            paddedImageButton3.setColors(MaterialColor.GREY.P800, MaterialColor.GREY.P700, MaterialColor.GREY.P900);
        }
        paddedImageButton3.setIconPosition(6.0f, 6.0f).setIconSize(40.0f, 40.0f);
        this.j.add((Table) paddedImageButton3).size(52.0f);
        TextureRegionDrawable drawable4 = Game.i.assetManager.getDrawable("icon-coins");
        Runnable runnable4 = () -> {
            Game.i.settingsManager.setFlyingCoinsEnabled(!Game.i.settingsManager.isFlyingCoinsEnabled());
            a();
            String str3 = Game.i.localeManager.i18n.get("settings_flying_coins");
            if (Game.i.settingsManager.isFlyingCoinsEnabled()) {
                Notifications.i().add(str3 + " - " + str, null, null, null);
            } else {
                Notifications.i().add(str3 + " - " + str2, null, null, null);
            }
        };
        Color color4 = Color.WHITE;
        PaddedImageButton paddedImageButton4 = new PaddedImageButton(drawable4, runnable4, color4, color4, Color.WHITE);
        if (Game.i.settingsManager.isFlyingCoinsEnabled()) {
            paddedImageButton4.setColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P900);
        } else {
            paddedImageButton4.setColors(MaterialColor.GREY.P800, MaterialColor.GREY.P700, MaterialColor.GREY.P900);
        }
        paddedImageButton4.setIconPosition(6.0f, 6.0f).setIconSize(40.0f, 40.0f);
        this.j.add((Table) paddedImageButton4).size(52.0f);
        TextureRegionDrawable drawable5 = Game.i.assetManager.getDrawable("icon-bullet");
        Runnable runnable5 = () -> {
            Game.i.settingsManager.setProjectilesDrawing(!Game.i.settingsManager.isProjectilesDrawing());
            a();
            String str3 = Game.i.localeManager.i18n.get("settings_draw_projectiles");
            if (Game.i.settingsManager.isProjectilesDrawing()) {
                Notifications.i().add(str3 + " - " + str, null, null, null);
            } else {
                Notifications.i().add(str3 + " - " + str2, null, null, null);
            }
        };
        Color color5 = Color.WHITE;
        PaddedImageButton paddedImageButton5 = new PaddedImageButton(drawable5, runnable5, color5, color5, Color.WHITE);
        if (Game.i.settingsManager.isProjectilesDrawing()) {
            paddedImageButton5.setColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P900);
        } else {
            paddedImageButton5.setColors(MaterialColor.GREY.P800, MaterialColor.GREY.P700, MaterialColor.GREY.P900);
        }
        paddedImageButton5.setIconPosition(6.0f, 6.0f).setIconSize(40.0f, 40.0f);
        this.j.add((Table) paddedImageButton5).size(52.0f);
        TextureRegionDrawable drawable6 = Game.i.assetManager.getDrawable("icon-projectile-speed");
        Runnable runnable6 = () -> {
            Game.i.settingsManager.setProjectileTrailsDrawing(!Game.i.settingsManager.isProjectileTrailsDrawing());
            a();
            String str3 = Game.i.localeManager.i18n.get("settings_draw_projectile_trails");
            if (Game.i.settingsManager.isProjectileTrailsDrawing()) {
                Notifications.i().add(str3 + " - " + str, null, null, null);
            } else {
                Notifications.i().add(str3 + " - " + str2, null, null, null);
            }
        };
        Color color6 = Color.WHITE;
        PaddedImageButton paddedImageButton6 = new PaddedImageButton(drawable6, runnable6, color6, color6, Color.WHITE);
        if (Game.i.settingsManager.isProjectileTrailsDrawing()) {
            paddedImageButton6.setColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P900);
        } else {
            paddedImageButton6.setColors(MaterialColor.GREY.P800, MaterialColor.GREY.P700, MaterialColor.GREY.P900);
        }
        paddedImageButton6.setIconPosition(6.0f, 6.0f).setIconSize(40.0f, 40.0f);
        this.j.add((Table) paddedImageButton6).size(52.0f);
        TextureRegionDrawable drawable7 = Game.i.assetManager.getDrawable("icon-dat-paper");
        Runnable runnable7 = () -> {
            Game.i.settingsManager.setStainsEnabled(!Game.i.settingsManager.isStainsEnabled());
            a();
            String str3 = Game.i.localeManager.i18n.get("settings_stains");
            if (Game.i.settingsManager.isStainsEnabled()) {
                Notifications.i().add(str3 + " - " + str, null, null, null);
            } else {
                Notifications.i().add(str3 + " - " + str2, null, null, null);
            }
        };
        Color color7 = Color.WHITE;
        PaddedImageButton paddedImageButton7 = new PaddedImageButton(drawable7, runnable7, color7, color7, Color.WHITE);
        if (Game.i.settingsManager.isStainsEnabled()) {
            paddedImageButton7.setColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P900);
        } else {
            paddedImageButton7.setColors(MaterialColor.GREY.P800, MaterialColor.GREY.P700, MaterialColor.GREY.P900);
        }
        paddedImageButton7.setIconPosition(6.0f, 6.0f).setIconSize(40.0f, 40.0f);
        this.j.add((Table) paddedImageButton7).size(52.0f);
        TextureRegionDrawable drawable8 = Game.i.assetManager.getDrawable("icon-chest");
        Runnable runnable8 = () -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.LOOT_ICONS_ENABLED, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.LOOT_ICONS_ENABLED) == 0.0d ? 1.0d : 0.0d);
            a();
            String str3 = Game.i.localeManager.i18n.get("settings_loot_icons");
            if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.LOOT_ICONS_ENABLED) != 0.0d) {
                Notifications.i().add(str3 + " - " + str, null, null, null);
            } else {
                Notifications.i().add(str3 + " - " + str2, null, null, null);
            }
        };
        Color color8 = Color.WHITE;
        PaddedImageButton paddedImageButton8 = new PaddedImageButton(drawable8, runnable8, color8, color8, Color.WHITE);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.LOOT_ICONS_ENABLED) != 0.0d) {
            paddedImageButton8.setColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P900);
        } else {
            paddedImageButton8.setColors(MaterialColor.GREY.P800, MaterialColor.GREY.P700, MaterialColor.GREY.P900);
        }
        paddedImageButton8.setIconPosition(6.0f, 6.0f).setIconSize(40.0f, 40.0f);
        this.j.add((Table) paddedImageButton8).size(52.0f);
        TextureRegionDrawable drawable9 = Game.i.assetManager.getDrawable("icon-crosshair");
        Runnable runnable9 = () -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.DRAW_TOWER_TARGET, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DRAW_TOWER_TARGET) == 0.0d ? 1.0d : 0.0d);
            a();
            String str3 = Game.i.localeManager.i18n.get("settings_draw_tower_target");
            if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DRAW_TOWER_TARGET) != 0.0d) {
                Notifications.i().add(str3 + " - " + str, null, null, null);
            } else {
                Notifications.i().add(str3 + " - " + str2, null, null, null);
            }
        };
        Color color9 = Color.WHITE;
        PaddedImageButton paddedImageButton9 = new PaddedImageButton(drawable9, runnable9, color9, color9, Color.WHITE);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DRAW_TOWER_TARGET) != 0.0d) {
            paddedImageButton9.setColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P900);
        } else {
            paddedImageButton9.setColors(MaterialColor.GREY.P800, MaterialColor.GREY.P700, MaterialColor.GREY.P900);
        }
        paddedImageButton9.setIconPosition(6.0f, 6.0f).setIconSize(40.0f, 40.0f);
        this.j.add((Table) paddedImageButton9).size(52.0f).padRight(-6.0f);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3375b);
        Game.i.uiManager.removeLayer(this.c);
        Game.i.uiManager.removeLayer(this.d);
    }
}
