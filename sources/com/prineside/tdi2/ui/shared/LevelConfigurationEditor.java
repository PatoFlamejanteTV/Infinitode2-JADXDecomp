package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonWriter;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.BasicLevelStage;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.EnemyGroup;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Requirement;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.WaveTemplates;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.RequirementType;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.CheckBox;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextArea;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/LevelConfigurationEditor.class */
public final class LevelConfigurationEditor extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3608a = TLog.forClass(LevelConfigurationEditor.class);
    private BasicLevel c;
    private Table d;
    private Table e;
    private SelectBox.SelectBoxStyle g;
    private TextField.TextFieldStyle h;
    private RectButton i;

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3609b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 141, "LevelConfigurationEditor main");
    private CheckBox.CheckBoxStyle f = new CheckBox.CheckBoxStyle(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.RED.P500), Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.GREEN.P500), Game.i.assetManager.getFont(24), Color.WHITE);

    public static LevelConfigurationEditor i() {
        return (LevelConfigurationEditor) Game.i.uiManager.getComponent(LevelConfigurationEditor.class);
    }

    public LevelConfigurationEditor() {
        this.f.checkboxOff.setLeftWidth(24.0f);
        this.f.checkboxOff.setBottomHeight(24.0f);
        this.g = Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getFont(24), true);
        this.h = Game.i.assetManager.getTextFieldStyle(24);
        Group group = new Group();
        group.setTransform(false);
        this.f3609b.getTable().add((Table) group).size(1200.0f, 1000.0f);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(1200.0f, 1000.0f);
        image.setColor(new Color(858993663));
        group.addActor(image);
        this.d = new Table();
        ScrollPane scrollPane = new ScrollPane(this.d, Game.i.assetManager.getScrollPaneStyle(8.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(240.0f, 1000.0f);
        scrollPane.setScrollingDisabled(true, false);
        group.addActor(scrollPane);
        b();
        this.e = new Table();
        this.e.setSize(900.0f, 906.0f);
        ScrollPane scrollPane2 = new ScrollPane(this.e, Game.i.assetManager.getScrollPaneStyle(16.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane2);
        scrollPane2.setSize(940.0f, 906.0f);
        scrollPane2.setPosition(260.0f, 84.0f);
        group.addActor(scrollPane2);
        Group group2 = new Group();
        group2.setTransform(false);
        group2.setPosition(250.0f, 10.0f);
        group.addActor(group2);
        RectButton rectButton = new RectButton("Save", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            if (this.c != null) {
                this.c.saveToDisk();
                Label label = new Label("Done!", Game.i.assetManager.getLabelStyle(24));
                label.setColor(MaterialColor.GREEN.P500);
                label.setPosition(740.0f, 0.0f);
                label.setSize(200.0f, 64.0f);
                label.setAlignment(1);
                label.setTouchable(Touchable.disabled);
                group2.addActor(label);
                label.addAction(Actions.sequence(Actions.moveBy(0.0f, 64.0f, 0.3f, Interpolation.exp5Out), Actions.fadeOut(0.2f), Actions.removeActor()));
                c();
            }
        });
        rectButton.setSize(200.0f, 64.0f);
        rectButton.setPosition(740.0f, 0.0f);
        group2.addActor(rectButton);
        c();
        hide();
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public final boolean isPersistent() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.d.clear();
        Array.ArrayIterator<BasicLevelStage> it = Game.i.basicLevelManager.stagesOrdered.iterator();
        while (it.hasNext()) {
            BasicLevelStage next = it.next();
            next.sortLevels();
            Label label = new Label(next.name + " - " + next.getTitle(), Game.i.assetManager.getLabelStyle(21));
            label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            label.setAlignment(1);
            this.d.add((Table) label).size(240.0f, 24.0f).padBottom(4.0f).padTop(16.0f).row();
            Array.ArrayIterator<BasicLevel> it2 = next.levels.iterator();
            while (it2.hasNext()) {
                BasicLevel next2 = it2.next();
                RectButton rectButton = new RectButton(next2.name, Game.i.assetManager.getLabelStyle(24), () -> {
                    this.c = next2;
                    c();
                });
                if (this.c == next2) {
                    Color color = Color.WHITE;
                    rectButton.setBackgroundColors(color, color, Color.WHITE, MaterialColor.AMBER.P900);
                    rectButton.setEnabled(false);
                }
                Color color2 = Color.WHITE;
                Color color3 = Color.WHITE;
                rectButton.setIconLabelColors(color2, color2, color3, color3);
                this.d.add((Table) rectButton).size(240.0f, 48.0f).padBottom(2.0f).row();
            }
        }
        RectButton rectButton2 = new RectButton("Add level", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.uiManager.getTextInput(new Input.TextInputListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.1
                @Override // com.badlogic.gdx.Input.TextInputListener
                public void input(String str) {
                    Threads.i().runOnMainThread(() -> {
                        if (str.length() < 2 || str.contains("/") || str.contains("\\")) {
                            Dialog.i().showAlert("0-9, a-Z and dot, minimum 2 characters");
                            return;
                        }
                        if (Game.i.basicLevelManager.getLevel(str) != null) {
                            Dialog.i().showAlert("Level with this name already exists");
                            return;
                        }
                        BasicLevel createNew = BasicLevel.createNew(str);
                        Game.i.basicLevelManager.registerLevel(createNew);
                        createNew.saveToDisk();
                        LevelConfigurationEditor.this.c = createNew;
                        LevelConfigurationEditor.this.c();
                    });
                }

                @Override // com.badlogic.gdx.Input.TextInputListener
                public void canceled() {
                }
            }, "Level name", "", "0-9, a-Z and dot, minimum 2 characters. Name can't be changed, choose wisely");
        });
        Color color4 = Color.WHITE;
        Color color5 = Color.WHITE;
        rectButton2.setIconLabelColors(color4, color4, color5, color5);
        rectButton2.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.WHITE);
        this.d.add((Table) rectButton2).size(240.0f, 48.0f).padBottom(2.0f).padTop(8.0f).row();
        RectButton rectButton3 = new RectButton("Add from Json", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.uiManager.getTextInput(new Input.TextInputListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.2
                @Override // com.badlogic.gdx.Input.TextInputListener
                public void input(String str) {
                    Threads.i().runOnMainThread(() -> {
                        LevelConfigurationEditor.this.a(str);
                    });
                }

                @Override // com.badlogic.gdx.Input.TextInputListener
                public void canceled() {
                }
            }, "Level JSON", "", "Copied from the existing level");
        });
        Color color6 = Color.WHITE;
        Color color7 = Color.WHITE;
        rectButton3.setIconLabelColors(color6, color6, color7, color7);
        rectButton3.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.WHITE);
        this.d.add((Table) rectButton3).size(240.0f, 48.0f).padBottom(2.0f).padTop(8.0f).row();
        RectButton rectButton4 = new RectButton("Add from URL", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.uiManager.getTextInput(new Input.TextInputListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.3
                @Override // com.badlogic.gdx.Input.TextInputListener
                public void input(String str) {
                    Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.GET);
                    httpRequest.setUrl(str);
                    Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.3.1
                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void handleHttpResponse(Net.HttpResponse httpResponse) {
                            String resultAsString = httpResponse.getResultAsString();
                            Threads.i().runOnMainThread(() -> {
                                LevelConfigurationEditor.this.a(resultAsString);
                            });
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void failed(Throwable th) {
                            LevelConfigurationEditor.f3608a.e("request failed", th);
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void cancelled() {
                        }
                    });
                }

                @Override // com.badlogic.gdx.Input.TextInputListener
                public void canceled() {
                }
            }, "Level Json URL", "", "Shared online by someone");
        });
        Color color8 = Color.WHITE;
        Color color9 = Color.WHITE;
        rectButton4.setIconLabelColors(color8, color8, color9, color9);
        rectButton4.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.WHITE);
        this.d.add((Table) rectButton4).size(240.0f, 48.0f).padBottom(2.0f).padTop(8.0f).row();
        this.d.add().width(1.0f).height(96.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            BasicLevel createNewFromFullJson = BasicLevel.createNewFromFullJson(str);
            Runnable runnable = () -> {
                if (Game.i.basicLevelManager.getStage(createNewFromFullJson.stageName) == null) {
                    String str2 = createNewFromFullJson.stageName;
                    createNewFromFullJson.stageName = "-1";
                    Notifications.i().add("Imported level is a part of stage '" + str2 + "' which is not configured. It will be added to stage '-1'", null, null, StaticSoundType.WARNING);
                }
                Game.i.basicLevelManager.registerLevel(createNewFromFullJson);
                createNewFromFullJson.saveToDisk();
                Game.i.basicLevelManager.setMap(createNewFromFullJson, createNewFromFullJson.getMap());
                this.c = createNewFromFullJson;
                c();
                Notifications.i().add("Level " + createNewFromFullJson.name + " imported from Json", null, null, StaticSoundType.SUCCESS);
            };
            if (Game.i.basicLevelManager.getLevel(createNewFromFullJson.name) == null) {
                runnable.run();
            } else {
                Dialog.i().showConfirm("Level " + createNewFromFullJson.name + " already exists and will be overwritten, continue?", runnable);
            }
        } catch (Exception e) {
            f3608a.e("failed to create level from json", e);
            Notifications.i().add("Failed to read level from json: " + e.getMessage(), null, MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
        }
    }

    public final void show() {
        if (!Config.isModdingMode() && !Game.i.progressManager.isDeveloperModeEnabled()) {
            Dialog.i().showAlert("Developer mode research required");
        } else {
            DarkOverlay.i().addCallerOverlayLayer("LevelConfigurationEditor", this.f3609b.zIndex - 1, () -> {
                hide();
                return true;
            });
            this.f3609b.getTable().setVisible(true);
        }
    }

    public final void showForLevel(BasicLevel basicLevel) {
        this.c = basicLevel;
        c();
        show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.c == null) {
            this.c = Game.i.basicLevelManager.levelsOrdered.first();
        }
        b();
        this.e.clearChildren();
        Group group = new Group();
        group.setTransform(false);
        this.e.add((Table) group).expandX().fillX().padTop(20.0f).height(40.0f).row();
        Label label = new Label(this.c.name, Game.i.assetManager.getLabelStyle(30));
        label.setColor(MaterialColor.AMBER.P500);
        label.setSize(200.0f, 40.0f);
        group.addActor(label);
        Table table = new Table();
        table.setSize(700.0f, 40.0f);
        table.setPosition(200.0f, 0.0f);
        group.addActor(table);
        Table table2 = new Table();
        table2.add().height(1.0f).growX();
        table.add(table2).growX().row();
        boolean exists = Gdx.files.internal("levels/" + this.c.name + ".json").exists();
        if (Gdx.app.getType() == Application.ApplicationType.Desktop || Gdx.app.getType() == Application.ApplicationType.HeadlessDesktop) {
            exists = false;
        }
        boolean exists2 = Gdx.files.local("levels/" + this.c.name + ".json").exists();
        boolean z = Gdx.app.getType() != Application.ApplicationType.Desktop && Game.i.basicLevelManager.defaultLevelNames.contains(this.c.name, false);
        boolean z2 = z;
        if (z) {
            table2.add((Table) new Label("Default", Game.i.assetManager.getLabelStyle(21))).padRight(10.0f);
        }
        table2.add((Table) new RectButton("Clone", Game.i.assetManager.getLabelStyle(21), () -> {
            Game.i.uiManager.getTextInput(new Input.TextInputListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.4
                @Override // com.badlogic.gdx.Input.TextInputListener
                public void input(String str) {
                    if (str.length() < 2 || str.contains("/") || str.contains("\\")) {
                        Dialog.i().showAlert("0-9, a-Z and dot, minimum 2 characters");
                        return;
                    }
                    if (Game.i.basicLevelManager.getLevel(str) == null) {
                        BasicLevel clone = LevelConfigurationEditor.this.c.clone(str);
                        Game.i.basicLevelManager.registerLevel(clone);
                        LevelConfigurationEditor.this.c = clone;
                        LevelConfigurationEditor.this.c();
                        return;
                    }
                    Dialog.i().showAlert("Level with this name already exists");
                }

                @Override // com.badlogic.gdx.Input.TextInputListener
                public void canceled() {
                }
            }, "Map name", this.c.name, "");
        })).size(150.0f, 32.0f).padLeft(10.0f);
        if (exists2) {
            if (exists) {
                table2.add((Table) new RectButton("Reset configuration", Game.i.assetManager.getLabelStyle(21), () -> {
                    Dialog.i().showConfirm("Reset level configuration? Original configuration will be used", () -> {
                        try {
                            String str = this.c.name;
                            Game.i.basicLevelManager.unloadLevel(this.c.name);
                            Gdx.files.local("levels/" + this.c.name + ".json").delete();
                            Game.i.basicLevelManager.loadAndRegisterLevelFromJson(new JsonReader().parse(Gdx.files.internal("levels/" + str + ".json").readString("UTF-8")));
                            c();
                        } catch (Exception e) {
                            f3608a.e("failed to restore level " + this.c.name, e);
                        }
                    });
                })).size(200.0f, 32.0f).padLeft(10.0f);
            } else if (!z2) {
                table2.add((Table) new RectButton("Delete", Game.i.assetManager.getLabelStyle(21), () -> {
                    Dialog.i().showConfirm("Delete this level? Map will be kept as a local file (create a level with the same name to access it)", () -> {
                        try {
                            Game.i.basicLevelManager.unloadLevel(this.c.name);
                            Gdx.files.local("levels/" + this.c.name + ".json").delete();
                            this.c = Game.i.basicLevelManager.levelsOrdered.first();
                            c();
                        } catch (Exception e) {
                            f3608a.e("failed to delete level " + this.c.name, e);
                        }
                    });
                })).size(150.0f, 32.0f).padLeft(10.0f);
            }
        }
        boolean exists3 = Gdx.files.internal("levels/maps/" + this.c.name + ".json").exists();
        if (Gdx.app.getType() == Application.ApplicationType.Desktop || Gdx.app.getType() == Application.ApplicationType.HeadlessDesktop) {
            exists3 = false;
        }
        boolean exists4 = Gdx.files.local("levels/maps/" + this.c.name + ".json").exists();
        if (z2 && exists3 && exists4) {
            table2.add((Table) new RectButton("Reset map", Game.i.assetManager.getLabelStyle(21), () -> {
                Dialog.i().showConfirm("Reset map? Map will be restored to the original state", () -> {
                    try {
                        Gdx.files.local("levels/maps/" + this.c.name + ".json").delete();
                        this.c.reloadMap();
                        c();
                    } catch (Exception e) {
                        f3608a.e("failed to delete level " + this.c.name, e);
                    }
                });
            })).size(200.0f, 32.0f).padLeft(10.0f);
        }
        if (!z2 && exists4) {
            table2.add((Table) new RectButton("Delete (+map)", Game.i.assetManager.getLabelStyle(21), () -> {
                Dialog.i().showConfirm("Delete this level and its map?", () -> {
                    try {
                        Gdx.files.local("levels/maps/" + this.c.name + ".json").delete();
                        Game.i.basicLevelManager.unloadLevel(this.c.name);
                        Gdx.files.local("levels/" + this.c.name + ".json").delete();
                        this.c = Game.i.basicLevelManager.levelsOrdered.first();
                        c();
                    } catch (Exception e) {
                        f3608a.e("failed to delete level " + this.c.name, e);
                    }
                });
            })).size(200.0f, 32.0f).padLeft(10.0f);
        }
        Table table3 = new Table();
        table3.add().height(1.0f).growX();
        table.row();
        table.add(table3).padTop(10.0f).growX();
        table3.add((Table) new RectButton("Copy JSON", Game.i.assetManager.getLabelStyle(21), () -> {
            String jsonStringEverything = this.c.toJsonStringEverything();
            f3608a.i(jsonStringEverything, new Object[0]);
            Gdx.app.getClipboard().setContents(jsonStringEverything);
            Notifications.i().add("Map and its configuration has been copied to the clipboard", null, null, StaticSoundType.SUCCESS);
        })).size(150.0f, 32.0f).padLeft(10.0f);
        this.i = new RectButton("Share online", Game.i.assetManager.getLabelStyle(21), () -> {
            this.i.setEnabled(false);
            Game.i.authManager.createPasteBin("Level " + this.c.name, this.c.toJsonStringEverything(), pasteBinResponse -> {
                if (pasteBinResponse.success) {
                    Gdx.app.getClipboard().setContents(pasteBinResponse.link);
                    f3608a.i("link to the copied map:", new Object[0]);
                    f3608a.i(pasteBinResponse.link, new Object[0]);
                    Notifications.i().add("Map and its configuration has been uploaded to the server, direct link to the file has been copied to your clipboard", null, null, StaticSoundType.SUCCESS);
                } else {
                    f3608a.e("failed to paste map to the server: " + pasteBinResponse.errorDescription, new Object[0]);
                    Notifications.i().add("Failed to upload contents to the server: " + pasteBinResponse.errorDescription, null, MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                }
                this.i.setEnabled(true);
            });
        });
        table3.add((Table) this.i).size(150.0f, 32.0f).padLeft(10.0f);
        d();
        b("Stage");
        final SelectBox selectBox = new SelectBox(this.g);
        Array array = new Array(String.class);
        Array.ArrayIterator<BasicLevelStage> it = Game.i.basicLevelManager.stagesOrdered.iterator();
        while (it.hasNext()) {
            BasicLevelStage next = it.next();
            array.add(next.name + " - " + next.getTitle());
        }
        selectBox.setItems(array);
        selectBox.setSelected(this.c.stageName + " - " + Game.i.basicLevelManager.getStage(this.c.stageName).getTitle());
        selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.5
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                String[] split = ((String) selectBox.getSelected()).split(" - ");
                Game.i.basicLevelManager.unloadLevel(LevelConfigurationEditor.this.c.name);
                LevelConfigurationEditor.this.c.stageName = split[0];
                Game.i.basicLevelManager.registerLevel(LevelConfigurationEditor.this.c);
                LevelConfigurationEditor.this.b();
            }
        });
        a(selectBox);
        d();
        b("Position in stage");
        hint("Levels will be sorted by this value, higher = further in the list");
        textField(String.valueOf(this.c.stagePosition), str -> {
            try {
                this.c.stagePosition = Integer.parseInt(str);
                b();
            } catch (Exception e) {
                f3608a.e("bad value: " + str, e);
            }
        });
        d();
        b("Seed");
        hint("Random seed - enemy waves depend on it");
        textField(String.valueOf(this.c.seed), str2 -> {
            try {
                this.c.seed = Integer.parseInt(str2);
            } catch (Exception e) {
                f3608a.e("bad value: " + str2, e);
            }
        });
        d();
        a("Has leaderboards", this.c.hasLeaderboards, bool -> {
            this.c.hasLeaderboards = bool.booleanValue();
        });
        hint("Should the game send replays for this level to the server and show leaderboards?");
        d();
        a("No achievements", this.c.achievementsDisabled, bool2 -> {
            this.c.achievementsDisabled = bool2.booleanValue();
        });
        a("Can not be restarted", this.c.canNotBeRestarted, bool3 -> {
            this.c.canNotBeRestarted = bool3.booleanValue();
        });
        a("Not affects statistics", this.c.notAffectsStatistics, bool4 -> {
            this.c.notAffectsStatistics = bool4.booleanValue();
        });
        hint("Playing on this level won't count towards the global statistics");
        d();
        b("Forced difficulty");
        final SelectBox selectBox2 = new SelectBox(this.g);
        Array array2 = new Array(String.class);
        array2.add("-");
        for (DifficultyMode difficultyMode : DifficultyMode.values()) {
            array2.add(difficultyMode.name());
        }
        selectBox2.setItems(array2);
        if (this.c.forcedDifficulty == null) {
            selectBox2.setSelected("-");
        } else {
            selectBox2.setSelected(this.c.forcedDifficulty.name());
        }
        selectBox2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.6
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                String str3 = (String) selectBox2.getSelected();
                if (str3.equals("-")) {
                    LevelConfigurationEditor.this.c.forcedDifficulty = null;
                } else {
                    LevelConfigurationEditor.this.c.forcedDifficulty = DifficultyMode.valueOf(str3);
                }
            }
        });
        a(selectBox2);
        d();
        b("Fast forward frame");
        hint("Game will start the first wave and auto-run to this frame at start");
        textField(String.valueOf(this.c.fastForwardFrame), str3 -> {
            try {
                this.c.fastForwardFrame = Integer.parseInt(str3);
                if (this.c.fastForwardFrame < 0) {
                    this.c.fastForwardFrame = 0;
                }
            } catch (Exception e) {
                f3608a.e("bad value: " + str3, e);
            }
        });
        d();
        a("Fixed quests", this.c.fixedQuests, bool5 -> {
            this.c.fixedQuests = bool5.booleanValue();
        });
        hint("If enabled, quests prizes / difficulty will not be affected by research (for example, Prestige mode hard quests research)");
        d();
        a("Bonus", this.c.isBonus, bool6 -> {
            this.c.isBonus = bool6.booleanValue();
        });
        hint("Will mark this level as bonus one");
        d();
        a("Custom waves", this.c.customWaves, bool7 -> {
            this.c.customWaves = bool7.booleanValue();
        });
        hint("Level has custom (scripted) waves, wave timeline won't be available in level's menu");
        d();
        a("Daily quest", this.c.dailyQuest, bool8 -> {
            this.c.dailyQuest = bool8.booleanValue();
        });
        hint("Daily quest level names should start with 'DQ'");
        d();
        b("Price in Green Papers");
        textField(String.valueOf(this.c.priceInMoney), str4 -> {
            try {
                this.c.priceInMoney = Integer.parseInt(str4);
                if (this.c.priceInMoney < 0) {
                    this.c.priceInMoney = 0;
                }
            } catch (Exception e) {
                f3608a.e("bad value: " + str4, e);
            }
        });
        d();
        b("Price in Resources");
        Table table4 = new Table();
        this.e.add(table4).top().left().pad(10.0f, 0.0f, 10.0f, 0.0f).top().left().row();
        table4.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.14f)));
        for (final ResourceType resourceType : ResourceType.values) {
            Label label2 = new Label(Game.i.resourceManager.getName(resourceType), Game.i.assetManager.getLabelStyle(24));
            label2.setColor(Game.i.resourceManager.getColor(resourceType));
            table4.add((Table) label2).padLeft(15.0f).padRight(15.0f).height(48.0f);
            final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(String.valueOf(this.c.priceInResources[resourceType.ordinal()]), this.h);
            textFieldXPlatform.setSize(300.0f, 40.0f);
            table4.add((Table) textFieldXPlatform).size(300.0f, 40.0f).padRight(4.0f).row();
            textFieldXPlatform.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.7
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        LevelConfigurationEditor.this.c.priceInResources[resourceType.ordinal()] = Integer.parseInt(textFieldXPlatform.getText());
                    } catch (Exception unused) {
                    }
                }
            });
        }
        d();
        b("Difficulty expected playtime");
        hint("Difficulty growth multiplier. Default is 1, smaller number will make difficulty grow earlier - better to keep in range 0..2\nDifficulty of portals define the overall difficulty of the map, this field defines how fast the difficulty will grow. Almost not affects the first ~20 waves");
        Table table5 = new Table();
        this.e.add(table5).top().left().pad(10.0f, 0.0f, 10.0f, 0.0f).top().left().row();
        final TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform(String.valueOf(this.c.difficultyExpectedPlaytime), this.h);
        textFieldXPlatform2.setSize(300.0f, 40.0f);
        table5.add((Table) textFieldXPlatform2).size(300.0f, 40.0f).padBottom(8.0f).row();
        textFieldXPlatform2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.8
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                try {
                    LevelConfigurationEditor.this.c.difficultyExpectedPlaytime = Float.parseFloat(textFieldXPlatform2.getText());
                } catch (Exception unused) {
                }
            }
        });
        d();
        b("Bonus stages config");
        final TextArea textArea = new TextArea("", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), true));
        if (this.c.bonusStagesConfig != null) {
            Json json = new Json(JsonWriter.OutputType.json);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeObjectStart();
            this.c.bonusStagesConfig.toJson(json);
            json.writeObjectEnd();
            textArea.setText(new JsonReader().parse(stringWriter.toString()).prettyPrint(JsonWriter.OutputType.json, 2));
        }
        textArea.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.9
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                try {
                    String text = textArea.getText();
                    if (text.length() == 0) {
                        LevelConfigurationEditor.this.c.bonusStagesConfig = null;
                    } else {
                        LevelConfigurationEditor.this.c.bonusStagesConfig = BonusStagesConfig.fromJsonString(text);
                    }
                } catch (Exception unused) {
                    LevelConfigurationEditor.f3608a.i("invalid bonus stages config", new Object[0]);
                }
            }
        });
        textArea.setMessageText("(No config)");
        this.e.add((Table) textArea).top().left().padRight(10.0f).growX().minHeight(300.0f).top().left().row();
        d();
        b("Enemy waves");
        hint("Use fixed enemy waves configuration");
        Table table6 = new Table();
        this.e.add(table6).top().left().pad(10.0f, 0.0f, 10.0f, 0.0f).top().left().row();
        if (this.c.enemyWaves != null) {
            int i = 0;
            for (WaveTemplates.PredefinedWaveTemplate predefinedWaveTemplate : this.c.enemyWaves) {
                Table table7 = new Table();
                table7.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.14f)));
                Table table8 = new Table();
                table7.add(table8).width(40.0f).align(10).padLeft(15.0f).padRight(15.0f).padTop(10.0f).top().left();
                table8.add((Table) new Label(String.valueOf(i + 1), Game.i.assetManager.getLabelStyle(30))).size(40.0f, 32.0f).align(1).row();
                int i2 = i;
                ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                    Dialog.i().showConfirm("Delete wave?", () -> {
                        if (this.c.enemyWaves.length == 1) {
                            this.c.enemyWaves = null;
                        } else {
                            WaveTemplates.PredefinedWaveTemplate[] predefinedWaveTemplateArr = new WaveTemplates.PredefinedWaveTemplate[this.c.enemyWaves.length - 1];
                            int i3 = 0;
                            for (int i4 = 0; i4 < this.c.enemyWaves.length; i4++) {
                                if (i4 != i2) {
                                    predefinedWaveTemplateArr[i3] = this.c.enemyWaves[i4];
                                    i3++;
                                }
                            }
                            this.c.enemyWaves = predefinedWaveTemplateArr;
                        }
                        c();
                    });
                });
                complexButton.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 40.0f, 40.0f);
                complexButton.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, Color.GRAY);
                complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 4.0f, 4.0f, 32.0f, 32.0f);
                table8.add((Table) complexButton).padTop(10.0f).size(40.0f, 40.0f);
                Table table9 = new Table();
                table9.add().height(4.0f).row();
                Label.LabelStyle labelStyle = new Label.LabelStyle(Game.i.assetManager.getLabelStyle(21));
                labelStyle.fontColor = MaterialColor.GREY.P600;
                table9.add((Table) new Label("Type", labelStyle));
                table9.add((Table) new Label("Count", labelStyle));
                table9.add((Table) new Label("HP", labelStyle));
                table9.add((Table) new Label("Speed", labelStyle));
                table9.add((Table) new Label("Delay", labelStyle));
                table9.add((Table) new Label("Interval", labelStyle));
                table9.add((Table) new Label("Coins", labelStyle));
                table9.add((Table) new Label("Score", labelStyle));
                table9.add((Table) new Label("XP", labelStyle));
                table9.add();
                table9.row();
                table9.add().height(4.0f).row();
                int i3 = 0;
                for (final EnemyGroup enemyGroup : predefinedWaveTemplate.enemyGroups) {
                    final SelectBox selectBox3 = new SelectBox(this.g);
                    selectBox3.setItems(new Array(EnemyType.values));
                    selectBox3.setSelected(enemyGroup.getEnemyType());
                    selectBox3.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.10
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            enemyGroup.setEnemyType((EnemyType) selectBox3.getSelected());
                        }
                    });
                    table9.add((Table) selectBox3).size(150.0f, 40.0f).padRight(4.0f);
                    final TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform(String.valueOf(enemyGroup.count), this.h);
                    textFieldXPlatform3.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.11
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                enemyGroup.count = Integer.parseInt(textFieldXPlatform3.getText());
                                if (enemyGroup.count <= 0) {
                                    enemyGroup.count = 1;
                                }
                            } catch (Exception unused) {
                            }
                        }
                    });
                    table9.add((Table) textFieldXPlatform3).top().left().size(60.0f, 40.0f).padRight(4.0f);
                    final TextFieldXPlatform textFieldXPlatform4 = new TextFieldXPlatform(String.valueOf(enemyGroup.health), this.h);
                    textFieldXPlatform4.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.12
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                enemyGroup.health = Float.parseFloat(textFieldXPlatform4.getText());
                                if (enemyGroup.health < 0.0f) {
                                    enemyGroup.health = 0.0f;
                                }
                            } catch (Exception unused) {
                            }
                        }
                    });
                    table9.add((Table) textFieldXPlatform4).top().left().size(100.0f, 40.0f).padRight(4.0f);
                    final TextFieldXPlatform textFieldXPlatform5 = new TextFieldXPlatform(String.valueOf(enemyGroup.speed), this.h);
                    textFieldXPlatform5.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.13
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                enemyGroup.speed = Float.parseFloat(textFieldXPlatform5.getText());
                                if (enemyGroup.speed < 0.0f) {
                                    enemyGroup.speed = 0.0f;
                                }
                            } catch (Exception unused) {
                            }
                        }
                    });
                    table9.add((Table) textFieldXPlatform5).top().left().size(75.0f, 40.0f).padRight(4.0f);
                    final TextFieldXPlatform textFieldXPlatform6 = new TextFieldXPlatform(String.valueOf(enemyGroup.delay), this.h);
                    textFieldXPlatform6.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.14
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                enemyGroup.delay = Float.parseFloat(textFieldXPlatform6.getText());
                                if (enemyGroup.delay < 0.0f) {
                                    enemyGroup.delay = 0.0f;
                                }
                            } catch (Exception unused) {
                            }
                        }
                    });
                    table9.add((Table) textFieldXPlatform6).top().left().size(75.0f, 40.0f).padRight(4.0f);
                    final TextFieldXPlatform textFieldXPlatform7 = new TextFieldXPlatform(String.valueOf(enemyGroup.interval), this.h);
                    textFieldXPlatform7.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.15
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                enemyGroup.interval = Float.parseFloat(textFieldXPlatform7.getText());
                                if (enemyGroup.interval < 0.0f) {
                                    enemyGroup.interval = 0.0f;
                                }
                            } catch (Exception unused) {
                            }
                        }
                    });
                    table9.add((Table) textFieldXPlatform7).top().left().size(75.0f, 40.0f).padRight(4.0f);
                    final TextFieldXPlatform textFieldXPlatform8 = new TextFieldXPlatform(String.valueOf(enemyGroup.bounty), this.h);
                    textFieldXPlatform8.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.16
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                enemyGroup.bounty = Float.parseFloat(textFieldXPlatform8.getText());
                                if (enemyGroup.bounty < 0.0f) {
                                    enemyGroup.bounty = 0.0f;
                                }
                            } catch (Exception unused) {
                            }
                        }
                    });
                    table9.add((Table) textFieldXPlatform8).top().left().size(75.0f, 40.0f).padRight(4.0f);
                    final TextFieldXPlatform textFieldXPlatform9 = new TextFieldXPlatform(String.valueOf(enemyGroup.killScore), this.h);
                    textFieldXPlatform9.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.17
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                enemyGroup.killScore = Integer.parseInt(textFieldXPlatform9.getText());
                                if (enemyGroup.killScore < 0) {
                                    enemyGroup.killScore = 0;
                                }
                            } catch (Exception unused) {
                            }
                        }
                    });
                    table9.add((Table) textFieldXPlatform9).top().left().size(75.0f, 40.0f).padRight(4.0f);
                    final TextFieldXPlatform textFieldXPlatform10 = new TextFieldXPlatform(String.valueOf(enemyGroup.killExp), this.h);
                    textFieldXPlatform10.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.18
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                enemyGroup.killExp = Float.parseFloat(textFieldXPlatform10.getText());
                                if (enemyGroup.killExp < 0.0f) {
                                    enemyGroup.killExp = 0.0f;
                                }
                            } catch (Exception unused) {
                            }
                        }
                    });
                    table9.add((Table) textFieldXPlatform10).top().left().size(75.0f, 40.0f).padRight(4.0f);
                    int i4 = i3;
                    ComplexButton complexButton2 = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                        Dialog.i().showConfirm("Delete group?", () -> {
                            if (predefinedWaveTemplate.enemyGroups.length == 1) {
                                Dialog.i().showAlert("Last enemy group can't be deleted");
                                return;
                            }
                            EnemyGroup[] enemyGroupArr = new EnemyGroup[predefinedWaveTemplate.enemyGroups.length - 1];
                            int i5 = 0;
                            for (int i6 = 0; i6 < predefinedWaveTemplate.enemyGroups.length; i6++) {
                                if (i6 != i4) {
                                    enemyGroupArr[i5] = predefinedWaveTemplate.enemyGroups[i6];
                                    i5++;
                                }
                            }
                            predefinedWaveTemplate.enemyGroups = enemyGroupArr;
                            c();
                        });
                    });
                    complexButton2.setIconColors(MaterialColor.ORANGE.P500, MaterialColor.ORANGE.P600, MaterialColor.ORANGE.P400, Color.GRAY);
                    complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 4.0f, 4.0f, 32.0f, 32.0f);
                    table9.add((Table) complexButton2).size(44.0f, 40.0f);
                    table9.row();
                    table9.add().width(1.0f).height(4.0f).row();
                    i3++;
                }
                table9.add((Table) new RectButton("Add group", Game.i.assetManager.getLabelStyle(24), () -> {
                    EnemyGroup[] enemyGroupArr = new EnemyGroup[predefinedWaveTemplate.enemyGroups.length + 1];
                    System.arraycopy(predefinedWaveTemplate.enemyGroups, 0, enemyGroupArr, 0, predefinedWaveTemplate.enemyGroups.length);
                    predefinedWaveTemplate.enemyGroups = enemyGroupArr;
                    enemyGroupArr[enemyGroupArr.length - 1] = new EnemyGroup(EnemyType.REGULAR, 1.0f, 20.0f, 10, 0.0f, 0.5f, 2.0f, 1.0f, 10);
                    c();
                })).size(150.0f, 40.0f).padBottom(4.0f).top().left().row();
                table7.add(table9).row();
                table6.add(table7).padBottom(4.0f).row();
                i++;
            }
        }
        this.e.add((Table) new RectButton("Add wave", Game.i.assetManager.getLabelStyle(24), () -> {
            if (this.c.enemyWaves == null) {
                this.c.enemyWaves = new WaveTemplates.PredefinedWaveTemplate[1];
            } else {
                WaveTemplates.PredefinedWaveTemplate[] predefinedWaveTemplateArr = new WaveTemplates.PredefinedWaveTemplate[this.c.enemyWaves.length + 1];
                System.arraycopy(this.c.enemyWaves, 0, predefinedWaveTemplateArr, 0, this.c.enemyWaves.length);
                this.c.enemyWaves = predefinedWaveTemplateArr;
            }
            this.c.enemyWaves[this.c.enemyWaves.length - 1] = new WaveTemplates.PredefinedWaveTemplate(new EnemyGroup(EnemyType.REGULAR, 1.0f, 20.0f, 10, 0.0f, 0.5f, 2.0f, 1.0f, 10));
            c();
        })).size(200.0f, 48.0f).top().left().row();
        d();
        b("Visibility requirements");
        requirements(this.c.showRequirements, this.e, this.g, this.h, this::c);
        b("Unlock requirements");
        requirements(this.c.openRequirements, this.e, this.g, this.h, this::c);
        b("Wave quests");
        Table table10 = new Table();
        this.e.add(table10).top().left().pad(10.0f, 0.0f, 10.0f, 0.0f).fillX().expandX().top().left().row();
        Array.ArrayIterator<BasicLevel.WaveQuest> it2 = this.c.waveQuests.iterator();
        while (it2.hasNext()) {
            final BasicLevel.WaveQuest next2 = it2.next();
            Table table11 = new Table();
            table11.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.14f)));
            Table table12 = new Table();
            table11.add(table12).width(220.0f).align(10).padLeft(15.0f).padRight(15.0f).padTop(10.0f).top().left();
            final TextFieldXPlatform textFieldXPlatform11 = new TextFieldXPlatform(next2.id, this.h);
            textFieldXPlatform11.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.19
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    next2.id = textFieldXPlatform11.getText();
                }
            });
            table12.add((Table) textFieldXPlatform11).size(160.0f, 40.0f).align(1);
            final TextFieldXPlatform textFieldXPlatform12 = new TextFieldXPlatform(String.valueOf(next2.waves), this.h);
            textFieldXPlatform12.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.20
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        next2.waves = Integer.parseInt(textFieldXPlatform12.getText());
                    } catch (Exception unused) {
                    }
                }
            });
            table12.add((Table) textFieldXPlatform12).size(56.0f, 40.0f).padLeft(4.0f).align(1).row();
            ComplexButton complexButton3 = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                Dialog.i().showConfirm("Delete wave quest?", () -> {
                    this.c.waveQuests.removeValue(next2, true);
                    c();
                });
            });
            complexButton3.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 40.0f, 40.0f);
            complexButton3.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, Color.GRAY);
            complexButton3.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 4.0f, 4.0f, 32.0f, 32.0f);
            table12.add((Table) complexButton3).padTop(10.0f).size(40.0f, 40.0f).left();
            Table table13 = new Table();
            a(table13, next2.prizes);
            table11.add(table13).fillX().expandX().padTop(8.0f).padBottom(8.0f).row();
            table10.add(table11).padBottom(4.0f).fillX().expandX().row();
        }
        this.e.add((Table) new RectButton("Add wave quest", Game.i.assetManager.getLabelStyle(24), () -> {
            String str5 = "W:" + this.c.name + ":";
            int i5 = 0;
            for (int i6 = 0; i6 < this.c.waveQuests.size; i6++) {
                String[] split = this.c.waveQuests.items[i6].id.split(":");
                int i7 = 0;
                try {
                    i7 = Integer.parseInt(split[split.length - 1]);
                } catch (Exception unused) {
                }
                if (i7 > i5) {
                    i5 = i7;
                }
            }
            this.c.waveQuests.add(new BasicLevel.WaveQuest(this.c, str5 + (i5 + 1), 1));
            c();
        })).size(200.0f, 48.0f).top().left().row();
        d();
        b("Quests");
        Table table14 = new Table();
        this.e.add(table14).top().left().pad(10.0f, 0.0f, 10.0f, 0.0f).fillX().expandX().top().left().row();
        Array.ArrayIterator<BasicLevelQuestConfig> it3 = this.c.quests.iterator();
        while (it3.hasNext()) {
            final BasicLevelQuestConfig next3 = it3.next();
            Table table15 = new Table();
            table15.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.14f)));
            Table table16 = new Table();
            table15.add(table16).width(220.0f).align(10).padLeft(15.0f).padRight(15.0f).padTop(10.0f).top().left();
            final TextFieldXPlatform textFieldXPlatform13 = new TextFieldXPlatform(next3.id, this.h);
            textFieldXPlatform13.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.21
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    next3.id = textFieldXPlatform13.getText();
                }
            });
            table16.add((Table) textFieldXPlatform13).size(220.0f, 40.0f).align(1);
            table16.row();
            ComplexButton complexButton4 = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                Dialog.i().showConfirm("Delete quest?", () -> {
                    this.c.quests.removeValue(next3, true);
                    c();
                });
            });
            complexButton4.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 40.0f, 40.0f);
            complexButton4.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, Color.GRAY);
            complexButton4.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 4.0f, 4.0f, 32.0f, 32.0f);
            table16.add((Table) complexButton4).padTop(10.0f).size(40.0f, 40.0f).left();
            Table table17 = new Table();
            table15.add(table17).expandX().fillX().padLeft(15.0f).padRight(15.0f).padTop(10.0f).top().left();
            Table table18 = new Table();
            table18.add(a(false, "Scripted", next3.scripted, bool9 -> {
                next3.scripted = bool9.booleanValue();
            }));
            table18.add(a(false, "One game", next3.duringGame, bool10 -> {
                next3.duringGame = bool10.booleanValue();
            })).padLeft(15.0f);
            final TextFieldXPlatform textFieldXPlatform14 = new TextFieldXPlatform(next3.scriptedTitle == null ? "" : next3.scriptedTitle, this.h);
            textFieldXPlatform14.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.22
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    if (textFieldXPlatform14.getText().length() == 0) {
                        next3.scriptedTitle = null;
                    } else {
                        next3.scriptedTitle = textFieldXPlatform14.getText();
                    }
                }
            });
            textFieldXPlatform14.setMessageText("Scripted alias");
            table18.add((Table) textFieldXPlatform14).padLeft(15.0f).size(260.0f, 40.0f);
            table18.add().height(1.0f).expandX().fillX();
            table17.add(table18).expandX().fillX().row();
            Table table19 = new Table();
            final SelectBox selectBox4 = new SelectBox(this.g);
            selectBox4.setItems(new Array(StatisticsType.values));
            selectBox4.setSelected(next3.statisticsType);
            selectBox4.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.23
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    next3.statisticsType = (StatisticsType) selectBox4.getSelected();
                }
            });
            table19.add((Table) selectBox4).size(340.0f, 40.0f);
            final TextFieldXPlatform textFieldXPlatform15 = new TextFieldXPlatform(String.valueOf(next3.requiredValue), this.h);
            textFieldXPlatform15.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.24
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        next3.requiredValue = Long.parseLong(textFieldXPlatform15.getText());
                    } catch (Exception unused) {
                    }
                }
            });
            table19.add((Table) textFieldXPlatform15).size(160.0f, 40.0f).padLeft(15.0f);
            table19.add().height(1.0f).expandX().fillX();
            table17.add(table19).expandX().fillX().padTop(10.0f).row();
            Table table20 = new Table();
            a(table20, next3.prizes);
            table17.add(table20).fillX().expandX().padTop(8.0f).padBottom(8.0f);
            table14.add(table15).padBottom(4.0f).fillX().expandX().row();
        }
        this.e.add((Table) new RectButton("Add quest", Game.i.assetManager.getLabelStyle(24), () -> {
            String str5 = "Q:" + this.c.name + ":";
            int i5 = 0;
            for (int i6 = 0; i6 < this.c.quests.size; i6++) {
                String[] split = this.c.quests.items[i6].id.split(":");
                int i7 = 0;
                try {
                    i7 = Integer.parseInt(split[split.length - 1]);
                } catch (Exception unused) {
                }
                if (i7 > i5) {
                    i5 = i7;
                }
            }
            this.c.quests.add(new BasicLevelQuestConfig(str5 + (i5 + 1), false, StatisticsType.PT, 360L, true, this.c));
            c();
        })).size(200.0f, 48.0f).top().left().row();
        d();
        this.e.add().row();
        this.e.add().width(1.0f).height(80.0f).row();
        this.e.add().expand().fill().row();
    }

    private void a(Table table, Array<ItemStack> array) {
        for (int i = 0; i < array.size; i++) {
            final ItemStack itemStack = array.items[i];
            Group group = new Group();
            group.setTransform(false);
            table.add((Table) group).size(96.0f, 144.0f);
            ItemCell itemCell = new ItemCell();
            itemCell.setCompact();
            itemCell.setColRow(i, 0);
            itemCell.setItem(itemStack.getItem(), 0);
            itemCell.setPosition(0.0f, 32.0f);
            itemCell.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.25
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    ItemCreationOverlay i2 = ItemCreationOverlay.i();
                    Item item = itemStack.getItem();
                    ItemStack itemStack2 = itemStack;
                    i2.showForItemListenable(item, item2 -> {
                        itemStack2.setItem(item2);
                        LevelConfigurationEditor.this.c();
                    }, true);
                }
            });
            group.addActor(itemCell);
            final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(String.valueOf(itemStack.getCount()), this.h);
            textFieldXPlatform.setSize(92.0f, 30.0f);
            textFieldXPlatform.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.26
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        itemStack.setCount(Integer.parseInt(textFieldXPlatform.getText()));
                    } catch (Exception unused) {
                    }
                }
            });
            group.addActor(textFieldXPlatform);
            RectButton rectButton = new RectButton("", Game.i.assetManager.getLabelStyle(24), () -> {
                array.removeValue(itemStack, true);
                c();
            });
            rectButton.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 32.0f, 32.0f);
            rectButton.setBackgroundColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700, Color.GRAY);
            rectButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-times"), 3.0f, 3.0f, 24.0f, 24.0f);
            rectButton.setSize(30.0f, 30.0f);
            rectButton.setPosition(66.0f, 112.0f);
            group.addActor(rectButton);
        }
        ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            ItemStack itemStack2 = new ItemStack(Item.D.GREEN_PAPER, 100);
            array.add(itemStack2);
            c();
            ItemCreationOverlay.i().showForItemListenable(itemStack2.getItem(), item -> {
                itemStack2.setItem(item);
                c();
            }, true);
        });
        complexButton.setBackground(Game.i.assetManager.getDrawable(array.size % 2 == 0 ? "item-cell-a-shape" : "item-cell-b-shape"), 0.0f, 0.0f, 96.0f, 105.0f);
        complexButton.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.28f), new Color(0.0f, 0.0f, 0.0f, 0.56f), new Color(1.0f, 1.0f, 1.0f, 0.14f), Color.GRAY);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-plus"), 32.0f, 36.5f, 32.0f, 32.0f);
        table.add((Table) complexButton).size(96.0f, 105.0f).padBottom(28.0f);
        table.add().height(1.0f).expandX().fillX();
    }

    public static void requirements(Array<Requirement> array, Table table, SelectBox.SelectBoxStyle selectBoxStyle, TextField.TextFieldStyle textFieldStyle, final Runnable runnable) {
        for (int i = 0; i < array.size; i++) {
            final Requirement requirement = array.get(i);
            Table table2 = new Table();
            Table table3 = new Table();
            table2.add(table3).expandX().fillX().height(48.0f).padBottom(4.0f);
            final SelectBox selectBox = new SelectBox(selectBoxStyle);
            selectBox.setItems(RequirementType.values);
            selectBox.setSelected(requirement.type);
            selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.27
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    Requirement.this.setType((RequirementType) selectBox.getSelected());
                    runnable.run();
                }
            });
            table3.add((Table) selectBox).size(250.0f, 48.0f).top().left();
            int i2 = i;
            PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-times"), () -> {
                array.removeIndex(i2);
                runnable.run();
            }, MaterialColor.ORANGE.P500, MaterialColor.ORANGE.P400, MaterialColor.ORANGE.P700);
            paddedImageButton.setIconSize(32.0f, 32.0f);
            paddedImageButton.setIconPosition(8.0f, 8.0f);
            table2.add((Table) paddedImageButton).size(48.0f).row();
            switch (requirement.type) {
                case RESEARCH:
                    final SelectBox selectBox2 = new SelectBox(selectBoxStyle);
                    selectBox2.setItems(ResearchType.values);
                    selectBox2.setSelected(requirement.researchType);
                    selectBox2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.28
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            Requirement.this.researchType = (ResearchType) selectBox2.getSelected();
                        }
                    });
                    table3.add((Table) selectBox2).size(400.0f, 48.0f).top().left().padLeft(4.0f);
                    final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(new StringBuilder().append(requirement.researchLevel).toString(), textFieldStyle);
                    textFieldXPlatform.setSize(100.0f, 64.0f);
                    textFieldXPlatform.setMessageText("Level");
                    table3.add((Table) textFieldXPlatform).top().left().size(200.0f, 48.0f).padLeft(4.0f);
                    textFieldXPlatform.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.29
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                Requirement.this.researchLevel = Integer.parseInt(textFieldXPlatform.getText());
                            } catch (Exception unused) {
                            }
                        }
                    });
                    break;
                case STARS:
                    final SelectBox selectBox3 = new SelectBox(selectBoxStyle);
                    Array array2 = new Array(String.class);
                    for (int i3 = 0; i3 < Game.i.basicLevelManager.levelsOrdered.size; i3++) {
                        array2.add(Game.i.basicLevelManager.levelsOrdered.items[i3].name);
                    }
                    selectBox3.setItems(array2);
                    selectBox3.setSelected(requirement.levelName);
                    selectBox3.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.30
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            Requirement.this.levelName = (String) selectBox3.getSelected();
                        }
                    });
                    table3.add((Table) selectBox3).size(400.0f, 48.0f).top().left().padLeft(4.0f);
                    final TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform(new StringBuilder().append(requirement.levelStars).toString(), textFieldStyle);
                    textFieldXPlatform2.setSize(100.0f, 64.0f);
                    textFieldXPlatform2.setMessageText("Stars");
                    table3.add((Table) textFieldXPlatform2).top().left().size(200.0f, 48.0f).padLeft(4.0f);
                    textFieldXPlatform2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.31
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                Requirement.this.levelStars = Integer.parseInt(textFieldXPlatform2.getText());
                            } catch (Exception unused) {
                            }
                        }
                    });
                    break;
                case STAGE_STARS:
                    final SelectBox selectBox4 = new SelectBox(selectBoxStyle);
                    Array array3 = new Array(String.class);
                    for (int i4 = 0; i4 < Game.i.basicLevelManager.stagesOrdered.size; i4++) {
                        array3.add(Game.i.basicLevelManager.stagesOrdered.items[i4].name);
                    }
                    selectBox4.setItems(array3);
                    selectBox4.setSelected(requirement.stageName);
                    selectBox4.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.32
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            Requirement.this.stageName = (String) selectBox4.getSelected();
                        }
                    });
                    table3.add((Table) selectBox4).size(400.0f, 48.0f).top().left().padLeft(4.0f);
                    final TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform(new StringBuilder().append(requirement.stageStars).toString(), textFieldStyle);
                    textFieldXPlatform3.setSize(100.0f, 64.0f);
                    textFieldXPlatform3.setMessageText("Stars");
                    table3.add((Table) textFieldXPlatform3).top().left().size(200.0f, 48.0f).padLeft(4.0f);
                    textFieldXPlatform3.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.33
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                Requirement.this.stageStars = Integer.parseInt(textFieldXPlatform3.getText());
                            } catch (Exception unused) {
                            }
                        }
                    });
                    break;
                case OPENED_LEVEL:
                    final SelectBox selectBox5 = new SelectBox(selectBoxStyle);
                    Array array4 = new Array(String.class);
                    for (int i5 = 0; i5 < Game.i.basicLevelManager.levelsOrdered.size; i5++) {
                        array4.add(Game.i.basicLevelManager.levelsOrdered.items[i5].name);
                    }
                    selectBox5.setItems(array4);
                    selectBox5.setSelected(requirement.openedLevelName);
                    selectBox5.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.34
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            Requirement.this.openedLevelName = (String) selectBox5.getSelected();
                        }
                    });
                    table3.add((Table) selectBox5).size(400.0f, 48.0f).top().left().padLeft(4.0f);
                    break;
                case ALL_TIME_STATISTIC:
                    final SelectBox selectBox6 = new SelectBox(selectBoxStyle);
                    selectBox6.setItems(StatisticsType.values);
                    selectBox6.setSelected(requirement.statisticsType);
                    selectBox6.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.35
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            Requirement.this.statisticsType = (StatisticsType) selectBox6.getSelected();
                        }
                    });
                    table3.add((Table) selectBox6).size(400.0f, 48.0f).top().left().padLeft(4.0f);
                    final TextFieldXPlatform textFieldXPlatform4 = new TextFieldXPlatform(new StringBuilder().append(requirement.statisticsValue).toString(), textFieldStyle);
                    textFieldXPlatform4.setSize(100.0f, 64.0f);
                    textFieldXPlatform4.setMessageText("Value");
                    table3.add((Table) textFieldXPlatform4).top().left().size(200.0f, 48.0f).padLeft(4.0f);
                    textFieldXPlatform4.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.36
                        @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                        public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                            try {
                                Requirement.this.statisticsValue = Double.parseDouble(textFieldXPlatform4.getText());
                            } catch (Exception unused) {
                            }
                        }
                    });
                    break;
            }
            table3.add().height(1.0f).expandX().fillX();
            table.add(table2).fillX().expandX().padRight(8.0f).row();
        }
        table.add((Table) new RectButton("Add requirement", Game.i.assetManager.getLabelStyle(24), () -> {
            Requirement requirement2 = new Requirement();
            requirement2.type = RequirementType.ALL_TIME_STATISTIC;
            requirement2.statisticsType = StatisticsType.WIP;
            requirement2.statisticsValue = 1.0d;
            array.add(requirement2);
            runnable.run();
        })).size(200.0f, 48.0f).top().left().row();
    }

    public final Label hint(String str) {
        Label label = new Label(str, Game.i.assetManager.getLabelStyle(21));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label.setWrap(true);
        label.setAlignment(8);
        this.e.add((Table) label).top().left().pad(-6.0f, 0.0f, 10.0f, 0.0f).top().left().width(900.0f).row();
        return label;
    }

    private LabelToggleButton a(String str, boolean z, ObjectConsumer<Boolean> objectConsumer) {
        return a(true, str, z, objectConsumer);
    }

    private LabelToggleButton a(boolean z, String str, boolean z2, ObjectConsumer<Boolean> objectConsumer) {
        LabelToggleButton labelToggleButton = new LabelToggleButton(str, z2, 24, 32.0f, objectConsumer);
        if (z) {
            this.e.add(labelToggleButton).height(48.0f).top().left().row();
        }
        return labelToggleButton;
    }

    private Label b(String str) {
        Label label = new Label(str, Game.i.assetManager.getLabelStyle(24));
        this.e.add((Table) label).top().left().pad(10.0f, 0.0f, 10.0f, 0.0f).top().left().row();
        return label;
    }

    private void a(SelectBox selectBox) {
        this.e.add((Table) selectBox).size(400.0f, 48.0f).top().left().row();
    }

    private void d() {
        this.e.add().width(1.0f).height(10.0f).row();
    }

    public final TextFieldXPlatform textField(String str, final ObjectConsumer<String> objectConsumer) {
        final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(str, this.h);
        textFieldXPlatform.setSize(400.0f, 64.0f);
        this.e.add((Table) textFieldXPlatform).top().left().size(300.0f, 48.0f).row();
        textFieldXPlatform.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.LevelConfigurationEditor.37
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                objectConsumer.accept(textFieldXPlatform.getText());
            }
        });
        return textFieldXPlatform;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        DarkOverlay.i().removeCaller("LevelConfigurationEditor");
        this.f3609b.getTable().setVisible(false);
        Game.i.uiManager.stage.unfocusAll();
    }
}
