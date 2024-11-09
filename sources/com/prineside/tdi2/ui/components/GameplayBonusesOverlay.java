package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.events.game.BonusesReRoll;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.systems.BonusSystem;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.shared.DarkOverlay;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/GameplayBonusesOverlay.class */
public class GameplayBonusesOverlay implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3305a = TLog.forClass(GameplayBonusesOverlay.class);
    private GameSystemProvider c;
    private long e;
    private long f;

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3306b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, HttpStatus.SC_MULTI_STATUS, "AbilitySelectionOverlay main");
    private float d = -1.0f;

    public GameplayBonusesOverlay(GameSystemProvider gameSystemProvider) {
        this.c = gameSystemProvider;
        gameSystemProvider.events.getListeners(BonusesReRoll.class).add(bonusesReRoll -> {
            if (!gameSystemProvider.gameState.replayMode) {
                show();
            }
        }).setDescription("GameplayBonusesOverlay - shows the overlay");
        this.f3306b.getTable().setVisible(false);
    }

    public boolean isVisible() {
        return this.f3306b.getTable().isVisible();
    }

    public void show() {
        Array<GameplayMod> bonusesToChooseFrom;
        String str;
        String str2;
        if (!this.c.bonus.isEnabled()) {
            return;
        }
        this.e = Game.getTimestampMillis();
        if (this.d == -1.0f) {
            this.d = this.c.gameState.getNonAnimatedGameSpeed();
        }
        this.c.gameState.setGameSpeed(0.0f);
        DarkOverlay.i().addCaller("GameplayBonusesOverlay", UiManager.MainUiLayer.SCREEN, HttpStatus.SC_PARTIAL_CONTENT, () -> {
            if (Game.getTimestampMillis() - this.e < 500) {
                return false;
            }
            if (!this.c.bonus.getStagesConfig().forceImmediateSelection || this.c.bonus.getStageToChooseBonusFor() == null) {
                hide();
                return true;
            }
            if (Game.getTimestampMillis() - this.f > 1000) {
                Notifications.i().add(Game.i.localeManager.i18n.get("bonus_selection_notification_selection_required"), null, null, StaticSoundType.FAIL);
                this.f = Game.getTimestampMillis();
                return false;
            }
            return false;
        });
        this.f3306b.getTable().setVisible(true);
        this.f3306b.getTable().clear();
        Label label = new Label(Game.i.localeManager.i18n.get("bonuses_menu_active_bonuses_title"), Game.i.assetManager.getLabelStyle(24));
        label.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.f3306b.getTable().add((Table) label).padBottom(16.0f).row();
        Table table = new Table();
        this.f3306b.getTable().add((Table) new ScrollPane(table, Game.i.assetManager.getScrollPaneStyle(0.0f))).height(308.0f).width(1280.0f).row();
        table.add().width(1.0f).height(4.0f).row();
        Array array = new Array(true, 1, GameplayModSystem.ActiveMod.class);
        for (int i = 0; i < this.c.gameplayMod.getActiveMods().size; i++) {
            GameplayModSystem.ActiveMod activeMod = this.c.gameplayMod.getActiveMods().get(i);
            if (activeMod.getSource().equals(BonusSystem.GAMEPLAY_MOD_SOURCE_NAME)) {
                array.add(activeMod);
            }
        }
        array.sort((activeMod2, activeMod3) -> {
            int compare = Boolean.compare(activeMod2.getMod().isImmediateAndNotListed(), activeMod3.getMod().isImmediateAndNotListed());
            if (compare != 0) {
                return compare;
            }
            return activeMod2.getMod().getId().compareTo(activeMod3.getMod().getId());
        });
        int i2 = this.c.bonus.getStagesConfig().activeBonusesSlotLimit;
        if (i2 > 0) {
            int i3 = array.size;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                if (i5 >= array.size) {
                    break;
                }
                if (((GameplayModSystem.ActiveMod) array.get(i5)).getMod().isImmediateAndNotListed()) {
                    i3 = i5;
                    break;
                } else {
                    i4++;
                    i5++;
                }
            }
            for (int i6 = 0; i6 < i2 - i4; i6++) {
                array.insert(i3, null);
            }
        }
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < array.size; i10++) {
            final GameplayModSystem.ActiveMod activeMod4 = (GameplayModSystem.ActiveMod) array.get(i10);
            final Table table2 = new Table();
            table.add(table2).width(96.0f).height(128.0f).padRight(8.0f).padLeft(8.0f).padBottom(16.0f);
            boolean z = (i8 + i7) % 2 == 0;
            if (activeMod4 == null) {
                table2.background(Game.i.assetManager.getQuad(z ? "ui.gameplayBonuses.activeBonusCell.emptyEven" : "ui.gameplayBonuses.activeBonusCell.emptyOdd"));
            } else {
                table2.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.GameplayBonusesOverlay.1
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f, float f2) {
                        String valueOf = String.valueOf(Game.i.assetManager.replaceRegionAliasesWithChars(activeMod4.getMod().getDescription()));
                        if (activeMod4.getMod().isImmediateAndNotListed()) {
                            valueOf = valueOf + "\n[#607D8B]" + Game.i.localeManager.i18n.get("bonuses_menu_active_bonus_immediate_hint") + "[]";
                        }
                        TooltipsOverlay.i().showText("GameplayBonusesOverlay.activeBonusDescription", table2, valueOf, UiManager.MainUiLayer.SCREEN, 208, 4);
                    }
                });
                AssetManager assetManager = Game.i.assetManager;
                if (activeMod4.getMod().isImmediateAndNotListed()) {
                    str2 = z ? "ui.gameplayBonuses.activeBonusCell.immediateEven" : "ui.gameplayBonuses.activeBonusCell.immediateOdd";
                } else {
                    str2 = z ? "ui.gameplayBonuses.activeBonusCell.even" : "ui.gameplayBonuses.activeBonusCell.odd";
                }
                table2.background(assetManager.getQuad(str2));
                table2.add((Table) new Image(activeMod4.getMod().getIcon())).size(80.0f).row();
                if (activeMod4.getMod().getMaxPower() != 2048) {
                    int maxPower = activeMod4.getMod().getMaxPower();
                    int i11 = maxPower;
                    if (maxPower > 10) {
                        i11 = 10;
                    }
                    int power = activeMod4.getMod().getPower();
                    Table table3 = new Table();
                    table3.add().width(1.0f).growY().row();
                    table2.add(table3).size(80.0f, 24.0f).padTop(4.0f);
                    for (int i12 = 0; i12 < i11; i12++) {
                        Image image = new Image(AssetManager.TextureRegions.i().smallCircle);
                        table3.add((Table) image).size(8.0f).pad(2.0f);
                        if (i12 >= power) {
                            image.setColor(0.0f, 0.0f, 0.0f, 1.0f);
                        } else if (activeMod4.getMod().isPowerLevelUpgradedByOtherMod(i12 + 1)) {
                            image.setColor(MaterialColor.LIGHT_GREEN.P500);
                        }
                        if ((i12 + 1) % 5 == 0) {
                            table3.row();
                        }
                    }
                } else {
                    table2.add().size(80.0f, 24.0f).padTop(4.0f);
                }
            }
            i7++;
            if (i7 == 10) {
                table.row();
                i7 = 0;
                i8++;
            }
            i9++;
        }
        if (i9 == 0) {
            Label label2 = new Label(Game.i.localeManager.i18n.get("bonuses_menu_no_active_bonuses_hint"), Game.i.assetManager.getLabelStyle(21));
            label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table.add((Table) label2).row();
        }
        table.row();
        table.add().width(1.0f).height(4.0f).row();
        table.add().width(1.0f).growY();
        BonusSystem.BonusStage stageToChooseBonusFor = this.c.bonus.getStageToChooseBonusFor();
        if (stageToChooseBonusFor != null && (bonusesToChooseFrom = stageToChooseBonusFor.getBonusesToChooseFrom()) != null && bonusesToChooseFrom.size > 0) {
            Label label3 = new Label(Game.i.localeManager.i18n.get("bonuses_menu_select_bonus_title"), Game.i.assetManager.getLabelStyle(24));
            label3.setColor(MaterialColor.LIGHT_GREEN.P500);
            this.f3306b.getTable().add((Table) label3).padBottom(26.0f).padTop(16.0f).row();
            Table table4 = new Table();
            this.f3306b.getTable().add(table4).padTop(58.0f).row();
            for (int i13 = 0; i13 < bonusesToChooseFrom.size; i13++) {
                final GameplayMod gameplayMod = bonusesToChooseFrom.get(i13);
                Table table5 = new Table();
                table5.setTouchable(Touchable.enabled);
                final Image image2 = new Image(Game.i.assetManager.getQuad("ui.gameplayBonuses.card"));
                image2.setFillParent(true);
                table5.addActor(image2);
                String str3 = "ui.gameplayBonuses.cardOverlay." + gameplayMod.getCategory().name().toLowerCase(Locale.US);
                if (gameplayMod.getAdditionalCategory() != null) {
                    str3 = str3 + "-" + gameplayMod.getAdditionalCategory().name().toLowerCase(Locale.US);
                }
                Quad quad = Game.i.assetManager.getQuad(str3);
                if (quad != Quad.getNoQuad()) {
                    Image image3 = new Image(quad);
                    image3.setFillParent(true);
                    table5.addActor(image3);
                } else {
                    f3305a.i("no overlay quad for " + str3, new Object[0]);
                }
                if (!gameplayMod.isImmediateAndNotListed() && gameplayMod.getPower() > 1) {
                    Image image4 = new Image(Game.i.assetManager.getQuad("ui.gameplayBonuses.cardOverlay.upgrade"));
                    image4.setFillParent(true);
                    table5.addActor(image4);
                }
                table4.add(table5).width(350.0f).padLeft(20.0f).padRight(20.0f).height(250.0f);
                table5.setTransform(true);
                float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.5f : 0.0f;
                table5.setOrigin(175.0f, 0.0f);
                table5.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.moveBy(0.0f, 0.0f), Actions.delay(f * 0.1f * (i13 + 1)), Actions.parallel(Actions.moveBy(0.0f, 0.0f, 0.2f * f), Actions.sequence(Actions.scaleTo(1.0f, 1.0f, 0.2f * f, Interpolation.swingOut))), Actions.run(() -> {
                    table5.setTransform(false);
                })));
                Image image5 = new Image(gameplayMod.getIcon());
                image5.setSize(80.0f, 80.0f);
                image5.setPosition(135.0f, 220.0f);
                table5.addActor(image5);
                Label label4 = new Label(Game.i.assetManager.replaceRegionAliasesWithChars(gameplayMod.getDescription()), Game.i.assetManager.getLabelStyle(24));
                label4.setWrap(true);
                label4.setAlignment(2);
                table5.add((Table) label4).growX().padLeft(25.0f).padRight(25.0f).padTop(58.0f).row();
                table5.add().growY().width(1.0f).row();
                if (gameplayMod.getMaxPower() < 30) {
                    Label label5 = new Label(Game.i.localeManager.i18n.format("bonus_gmod_level", gameplayMod.getPower() + " / " + gameplayMod.getMaxPower()), Game.i.assetManager.getLabelStyle(18));
                    label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    label5.setAlignment(4);
                    table5.add((Table) label5).growX().padLeft(20.0f).padRight(20.0f).padBottom(5.0f).row();
                    Table table6 = new Table();
                    table5.add(table6).growX().padLeft(20.0f).padRight(20.0f).padBottom(12.0f).row();
                    for (int i14 = 1; i14 <= gameplayMod.getMaxPower(); i14++) {
                        Image image6 = new Image(AssetManager.TextureRegions.i().smallCircle);
                        table6.add((Table) image6).padLeft(2.0f).padRight(2.0f).size(8.0f);
                        if (gameplayMod.getPower() >= i14) {
                            image6.setColor(Color.WHITE);
                        } else {
                            image6.setColor(0.0f, 0.0f, 0.0f, 0.35f);
                        }
                    }
                } else {
                    table5.add().height(22.0f).growX().padLeft(20.0f).padRight(20.0f).padBottom(5.0f).row();
                }
                if (gameplayMod.getNotSatisfiedPreconditions(this.c) != null) {
                    Image image7 = new Image(Game.i.assetManager.getQuad("ui.gameplayBonuses.cardOverlay.notAvailable"));
                    image7.setFillParent(true);
                    table5.addActor(image7);
                    for (int i15 = 0; i15 < 4; i15++) {
                        Label label6 = new Label(Game.i.localeManager.i18n.get("bonus_selection_requirements_not_met_label"), Game.i.assetManager.getLabelStyle(21));
                        switch (i15) {
                            case 0:
                                label6.setPosition(0.0f, 18.0f);
                                break;
                            case 1:
                                label6.setPosition(0.0f, 22.0f);
                                break;
                            case 2:
                                label6.setPosition(2.0f, 20.0f);
                                break;
                            case 3:
                                label6.setPosition(-2.0f, 20.0f);
                                break;
                        }
                        label6.setSize(350.0f, 20.0f);
                        label6.setAlignment(1);
                        label6.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                        table5.addActor(label6);
                    }
                    Label label7 = new Label(Game.i.localeManager.i18n.get("bonus_selection_requirements_not_met_label"), Game.i.assetManager.getLabelStyle(21));
                    label7.setPosition(0.0f, 20.0f);
                    label7.setSize(350.0f, 20.0f);
                    label7.setAlignment(1);
                    label7.setColor(MaterialColor.DEEP_ORANGE.P500);
                    table5.addActor(label7);
                }
                Actor actor = new Actor();
                actor.setSize(350.0f, 250.0f);
                table5.addActor(actor);
                final int i16 = i13;
                actor.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.GameplayBonusesOverlay.2
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f2, float f3) {
                        if (Game.getTimestampMillis() - GameplayBonusesOverlay.this.e >= 500) {
                            ObjectSupplier<CharSequence> notSatisfiedPreconditions = gameplayMod.getNotSatisfiedPreconditions(GameplayBonusesOverlay.this.c);
                            if (notSatisfiedPreconditions == null) {
                                GameplayBonusesOverlay.this.c.bonus.selectBonusAction(i16);
                                GameplayBonusesOverlay.this.hide();
                            } else {
                                Notifications.i().add(Game.i.localeManager.i18n.get("bonus_selection_preconditions_not_satisfied") + ":\n" + ((Object) notSatisfiedPreconditions.get()), null, null, StaticSoundType.FAIL);
                            }
                        }
                    }
                });
                actor.addListener(new InputListener(this) { // from class: com.prineside.tdi2.ui.components.GameplayBonusesOverlay.3
                    @Override // com.prineside.tdi2.scene2d.InputListener
                    public void enter(InputEvent inputEvent, float f2, float f3, int i17, @Null Actor actor2) {
                        image2.setDrawable(Game.i.assetManager.getQuad("ui.gameplayBonuses.cardHover"));
                    }

                    @Override // com.prineside.tdi2.scene2d.InputListener
                    public void exit(InputEvent inputEvent, float f2, float f3, int i17, @Null Actor actor2) {
                        image2.setDrawable(Game.i.assetManager.getQuad("ui.gameplayBonuses.card"));
                    }
                });
                GameplayMod replacesUnsatisfiedMod = gameplayMod.getReplacesUnsatisfiedMod();
                if (replacesUnsatisfiedMod != null) {
                    PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-clockwise"), null, MaterialColor.PINK.P500, MaterialColor.PINK.P300, MaterialColor.PINK.P700);
                    paddedImageButton.disableClickThrough = true;
                    paddedImageButton.setClickHandler(() -> {
                        f3305a.i("show tooltip", new Object[0]);
                        Table table7 = new Table();
                        Label label8 = new Label(Game.i.localeManager.i18n.get("bonus_selection_bonus_is_replacement_tooltip") + ":", Game.i.assetManager.getLabelStyle(18));
                        label8.setWrap(true);
                        table7.add((Table) label8).width(350.0f).growX().row();
                        table7.add((Table) new Image(replacesUnsatisfiedMod.getIcon())).size(64.0f).padTop(8.0f).padBottom(8.0f).row();
                        Label label9 = new Label(replacesUnsatisfiedMod.getDescription(), Game.i.assetManager.getLabelStyle(21));
                        label9.setWrap(true);
                        label9.setColor(MaterialColor.GREY.P500);
                        table7.add((Table) label9).width(350.0f).growX().row();
                        Label label10 = new Label(Game.i.localeManager.i18n.get("bonus_selection_requirements_not_met_label") + ":", Game.i.assetManager.getLabelStyle(18));
                        label10.setWrap(true);
                        label10.setColor(MaterialColor.PINK.P500);
                        table7.add((Table) label10).width(350.0f).growX().padTop(8.0f).row();
                        ObjectSupplier<CharSequence> notSatisfiedPreconditions = replacesUnsatisfiedMod.getNotSatisfiedPreconditions(this.c);
                        Label label11 = new Label(notSatisfiedPreconditions == null ? "" : notSatisfiedPreconditions.get(), Game.i.assetManager.getLabelStyle(21));
                        label11.setWrap(true);
                        table7.add((Table) label11).width(350.0f).growX().row();
                        Label label12 = new Label(Game.i.localeManager.i18n.get("bonus_selection_requirements_not_met_next_run_hint"), Game.i.assetManager.getLabelStyle(18));
                        label12.setWrap(true);
                        label12.setColor(MaterialColor.LIGHT_GREEN.P500);
                        table7.add((Table) label12).width(350.0f).growX().padTop(8.0f).row();
                        TooltipsOverlay.i().showActor("GameplayBonusesOverlay_replacedBonusHint", paddedImageButton, table7, this.f3306b.mainUiLayer, this.f3306b.zIndex + 1, 4);
                    });
                    paddedImageButton.setSize(64.0f, 64.0f);
                    paddedImageButton.setPosition(235.0f, 248.0f);
                    paddedImageButton.setIconSize(48.0f, 48.0f);
                    paddedImageButton.setIconPosition(8.0f, 8.0f);
                    table5.addActor(paddedImageButton);
                    Image image8 = new Image(Game.i.assetManager.getDrawable("icon-info"));
                    image8.setTouchable(Touchable.disabled);
                    image8.setSize(16.0f, 16.0f);
                    image8.setPosition(259.0f, 272.0f);
                    table5.addActor(image8);
                }
            }
            Table table7 = new Table();
            this.f3306b.getTable().add(table7).size(1024.0f, 64.0f).padTop(32.0f).row();
            if (bonusesToChooseFrom.size == 1 && bonusesToChooseFrom.first().getNotSatisfiedPreconditions(this.c) == null) {
                table7.add(new FancyButton("regularGreenButton.a", () -> {
                    this.c.bonus.setAutoSelectionOnSingleBonus(true);
                    this.c.bonus.selectBonusAction(0);
                    hide();
                }).withLabel(24, Game.i.localeManager.i18n.get("bonus_selection_auto_toggle"))).height(64.0f).width(256.0f).padRight(16.0f);
            }
            if (this.c.bonus.canReRollBonuses(stageToChooseBonusFor.getNumber())) {
                int reRollPrice = this.c.bonus.getStagesConfig().getReRollPrice(stageToChooseBonusFor.getNumber(), this.c);
                if (reRollPrice > 0) {
                    str = Game.i.localeManager.i18n.format("bonus_selection_reroll_button", Integer.valueOf(reRollPrice));
                } else {
                    str = Game.i.localeManager.i18n.get("bonus_selection_reroll_button_free");
                }
                FancyButton withLabel = new FancyButton("regularButton.b", () -> {
                    this.c.bonus.reRollBonusesAction();
                }).withLabel(24, str);
                table7.add(withLabel).size(256.0f, 64.0f);
                if (this.c.bonus.getStagesConfig().maxReRollsAllTime > 0) {
                    Label label8 = new Label(Game.i.localeManager.i18n.format("bonus_selection_reroll_count_left", Integer.valueOf(this.c.bonus.getStagesConfig().maxReRollsAllTime - this.c.bonus.getAllTimeReRollCount())), Game.i.assetManager.getLabelStyle(21));
                    label8.setSize(256.0f, 16.0f);
                    label8.setAlignment(1);
                    label8.setPosition(0.0f, -28.0f);
                    withLabel.addActor(label8);
                }
            }
            Label label9 = new Label("", Game.i.assetManager.getLabelStyle(21));
            if (this.c.bonus.getStagesConfig().forceImmediateSelection) {
                label9.setColor(MaterialColor.ORANGE.P300);
                label9.setText(Game.i.localeManager.i18n.get("bonus_selection_overlay_hint_forced_selection"));
            } else {
                label9.setColor(MaterialColor.LIGHT_GREEN.P300);
                label9.setText(Game.i.localeManager.i18n.get("bonus_selection_overlay_hint_not_forced_selection"));
            }
            this.f3306b.getTable().add((Table) label9).padTop(40.0f).row();
        }
    }

    public void hide() {
        if (!this.c.gameState.isPaused()) {
            this.c.gameState.setGameSpeed(this.d);
        }
        this.d = -1.0f;
        DarkOverlay.i().removeCaller("GameplayBonusesOverlay");
        this.f3306b.getTable().setVisible(false);
        Actor scrollFocus = Game.i.uiManager.stage.getScrollFocus();
        if (scrollFocus != null && UiUtils.hasParent(scrollFocus, this.f3306b.getTable())) {
            f3305a.d("resetting scroll focus", new Object[0]);
            Game.i.uiManager.stage.setScrollFocus(null);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        DarkOverlay.i().removeCaller("GameplayBonusesOverlay");
        Game.i.uiManager.removeLayer(this.f3306b);
    }
}
