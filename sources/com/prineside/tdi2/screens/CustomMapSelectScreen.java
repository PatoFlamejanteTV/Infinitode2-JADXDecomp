package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.UiUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/CustomMapSelectScreen.class */
public class CustomMapSelectScreen extends Screen {

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f2747a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "CustomMapSelectScreen main");

    /* renamed from: b, reason: collision with root package name */
    private Table f2748b;

    public CustomMapSelectScreen() {
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        ScreenTitle.i().setIcon(Game.i.assetManager.getDrawable("icon-edit")).setText(Game.i.localeManager.i18n.get("map_editor_map_select_title")).setVisible(true);
        BackButton.i().setVisible(true).setText(null).setClickHandler(() -> {
            if (AbilitySelectionOverlay.i().isVisible()) {
                AbilitySelectionOverlay.i().hide();
            } else {
                Game.i.screenManager.goToMainMenu();
            }
        });
        Table table = this.f2747a.getTable();
        Table table2 = new Table();
        ScrollPane scrollPane = new ScrollPane(table2);
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        table.add((Table) scrollPane).padRight(40.0f).fill().expand();
        table2.add().top().left().height(160.0f).fillX().expandX().row();
        this.f2748b = new Table();
        table2.add(this.f2748b).expandX().fillX().padBottom(160.0f).row();
        table2.add().fill().expand();
        boolean z = false;
        Array<UserMap> userMaps = Game.i.userMapManager.getUserMaps();
        for (int i = 0; i < userMaps.size; i++) {
            if (userMaps.items[i].removeUnexistentTilesFromMap()) {
                z = true;
            }
        }
        if (z) {
            ProgressPrefs.i().requireSave();
        }
        a();
        Game.i.progressManager.showNewlyIssuedPrizesPopup();
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void show() {
        Game.i.uiManager.setAsInputHandler();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.f2748b.clear();
        Table table = new Table();
        this.f2748b.add(table).width(800.0f).padRight(40.0f).expandY().fillY();
        Array<UserMap> userMaps = Game.i.userMapManager.getUserMaps();
        for (int i = 0; i < userMaps.size; i++) {
            UserMap userMap = userMaps.get(i);
            Group group = new Group();
            group.setTransform(false);
            table.add((Table) group).size(800.0f, 250.0f).padBottom(4.0f).row();
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setSize(800.0f, 250.0f);
            image.setColor(new Color(0.0f, 0.0f, 0.0f, 0.28f));
            group.addActor(image);
            userMap.map.regeneratePreview();
            Image image2 = new Image(userMap.map.getPreview().getTextureRegion());
            image2.setSize(310.0f, 230.0f);
            image2.setPosition(10.0f, 10.0f);
            group.addActor(image2);
            Label label = new Label(userMap.name, Game.i.assetManager.getLabelStyle(30));
            label.setPosition(340.0f, 192.0f);
            label.setSize(100.0f, 30.0f);
            group.addActor(label);
            PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-edit"), () -> {
                Game.i.uiManager.getTextInput(new Input.TextInputListener() { // from class: com.prineside.tdi2.screens.CustomMapSelectScreen.1
                    @Override // com.badlogic.gdx.Input.TextInputListener
                    public void input(String str) {
                        Threads i2 = Threads.i();
                        UserMap userMap2 = userMap;
                        i2.runOnMainThread(() -> {
                            Game.i.userMapManager.rename(userMap2, str);
                            CustomMapSelectScreen.this.a();
                        });
                    }

                    @Override // com.badlogic.gdx.Input.TextInputListener
                    public void canceled() {
                    }
                }, "", userMap.name, "");
            }, MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P700, MaterialColor.LIGHT_BLUE.P900);
            paddedImageButton.setIconSize(40.0f, 40.0f);
            paddedImageButton.setIconPosition(12.0f, 12.0f);
            paddedImageButton.setPosition(712.0f, 176.0f);
            group.addActor(paddedImageButton);
            ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(30), () -> {
                Runnable runnable = () -> {
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("delete_map_confirm"), () -> {
                        Game.i.userMapManager.removeUserMap(userMap.id);
                        a();
                    });
                    Dialog.i().makeConfirmButtonDisabled(2);
                };
                if (GameStateSystem.savedGameExists()) {
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("saved_game_will_be_lost_confirm"), () -> {
                        GameStateSystem.deleteSavedGame();
                        runnable.run();
                    });
                } else {
                    runnable.run();
                }
            });
            complexButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 64.0f, 104.0f, 64.0f, 96.0f, 0.0f})), 0.0f, 0.0f, 104.0f, 64.0f);
            complexButton.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, Color.GRAY);
            complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 22.0f, 8.0f, 48.0f, 48.0f);
            complexButton.setPosition(338.0f, 18.0f);
            group.addActor(complexButton);
            ComplexButton complexButton2 = new ComplexButton("", Game.i.assetManager.getLabelStyle(30), () -> {
                Game.i.screenManager.goToMapEditorScreenUserMap(userMap);
            });
            complexButton2.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 8.0f, 64.0f, 170.0f, 64.0f, 162.0f, 0.0f})), 0.0f, 0.0f, 170.0f, 64.0f);
            complexButton2.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, Color.GRAY);
            complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-edit"), 61.0f, 8.0f, 48.0f, 48.0f);
            complexButton2.setPosition(442.0f, 18.0f);
            group.addActor(complexButton2);
            ComplexButton complexButton3 = new ComplexButton("", Game.i.assetManager.getLabelStyle(30), () -> {
                try {
                    userMap.map.validate();
                    Runnable runnable = () -> {
                        if (userMap.map.getTargetTileOrThrow().isDisableAbilities() || !Game.i.abilityManager.isAnyAbilityOpened()) {
                            Game.i.screenManager.startNewUserLevel(userMap, null);
                        } else {
                            AbilitySelectionOverlay.i().show(() -> {
                            }, selectedAbilitiesConfiguration -> {
                                Game.i.screenManager.startNewUserLevel(userMap, selectedAbilitiesConfiguration);
                            });
                        }
                    };
                    Runnable runnable2 = () -> {
                        if (GameStateSystem.savedGameExists()) {
                            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("saved_game_will_be_lost_confirm"), () -> {
                                GameStateSystem.deleteSavedGame();
                                runnable.run();
                            });
                        } else {
                            runnable.run();
                        }
                    };
                    runnable2.run();
                } catch (Map.InvalidMapException e) {
                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("map_cant_be_played") + ": " + e.getFixHintMessage());
                }
            });
            complexButton3.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 8.0f, 64.0f, 170.0f, 64.0f, 170.0f, 0.0f})), 0.0f, 0.0f, 170.0f, 64.0f);
            complexButton3.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.GRAY);
            complexButton3.setIconPositioned(Game.i.assetManager.getDrawable("icon-triangle-right"), 65.0f, 8.0f, 48.0f, 48.0f);
            complexButton3.setPosition(612.0f, 18.0f);
            group.addActor(complexButton3);
        }
        if (userMaps.size < 8) {
            Group group2 = new Group();
            group2.setTransform(false);
            table.add((Table) group2).size(800.0f, 100.0f).padBottom(4.0f).row();
            Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image3.setSize(800.0f, 100.0f);
            image3.setColor(new Color(0.0f, 0.0f, 0.0f, 0.28f));
            group2.addActor(image3);
            Label label2 = new Label(Game.i.localeManager.i18n.get("new_map") + ":", Game.i.assetManager.getLabelStyle(24));
            label2.setSize(222.0f, 64.0f);
            label2.setPosition(18.0f, 18.0f);
            label2.setAlignment(16);
            group2.addActor(label2);
            TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(24));
            textFieldXPlatform.setSize(306.0f, 64.0f);
            textFieldXPlatform.setPosition(258.0f, 18.0f);
            group2.addActor(textFieldXPlatform);
            FancyButton withLabel = new FancyButton("regularButton.b", () -> {
                if (textFieldXPlatform.getText().length() == 0) {
                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("enter_map_name"));
                } else {
                    Game.i.userMapManager.addUserMap(textFieldXPlatform.getText());
                    a();
                }
            }).withLabel(30, Game.i.localeManager.i18n.get("create"));
            withLabel.setSize(200.0f, 64.0f);
            withLabel.setPosition(582.0f, 18.0f);
            group2.addActor(withLabel);
        }
        table.add().expand().fill();
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (Game.i.settingsManager.isEscButtonJustPressed()) {
            Game.i.screenManager.goToMainMenu();
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        super.dispose();
        Game.i.uiManager.removeLayer(this.f2747a);
    }
}
