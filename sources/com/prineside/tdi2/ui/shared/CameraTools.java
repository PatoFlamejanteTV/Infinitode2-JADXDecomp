package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.InterpolationType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.screens.MapEditorScreen;
import com.prineside.tdi2.screens.ResearchesScreen;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.LinearChartActor;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/CameraTools.class */
public final class CameraTools extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3464a = TLog.forClass(CameraTools.class);
    private final Table d;
    private final Label j;
    private final Label k;
    private final Label l;
    private final TextField.TextFieldStyle m;
    private final SelectBox.SelectBoxStyle n;
    private final Table o;
    private Table p;
    private Table q;
    private Group r;
    private Image s;
    private Image t;
    private int u;
    private boolean w;

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3465b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 9002, "DeveloperConsole camera tools");
    private final Array<UiManager.UiLayer> c = new Array<>(UiManager.UiLayer.class);
    private final Vector2 e = new Vector2();
    private float f = 300.0f;
    private float g = 100.0f;
    private float h = 1.0f;
    private float i = 50.0f;
    private final Scenario[] v = new Scenario[9];
    private boolean x = true;
    private int y = 0;

    static /* synthetic */ void a(CameraTools cameraTools) {
    }

    public static CameraTools i() {
        return (CameraTools) Game.i.uiManager.getComponent(CameraTools.class);
    }

    public CameraTools() {
        Gdx.app.addLifecycleListener(new LifecycleListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.1
            @Override // com.badlogic.gdx.LifecycleListener
            public void pause() {
                CameraTools.a(CameraTools.this);
            }

            @Override // com.badlogic.gdx.LifecycleListener
            public void resume() {
            }

            @Override // com.badlogic.gdx.LifecycleListener
            public void dispose() {
                CameraTools.a(CameraTools.this);
            }
        });
        this.m = Game.i.assetManager.getTextFieldStyle(21);
        this.n = Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getFont(21), true);
        Table table = new Table();
        table.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(640825565)));
        this.f3465b.getTable().add(table).width(800.0f);
        Table table2 = new Table();
        table.add(table2).pad(10.0f).expandX().fillX();
        Table table3 = new Table();
        table2.add(table3).top().left().expandX().fillX();
        Table table4 = new Table();
        table3.add(table4).top().left().row();
        table4.add((Table) new Label("UI layers", Game.i.assetManager.getLabelStyle(24)));
        LabelButton labelButton = new LabelButton("Toggle all", Game.i.assetManager.getLabelStyle(21), () -> {
            if (this.c.size == 0) {
                for (int i = 0; i < Game.i.uiManager.layers.length; i++) {
                    Array<UiManager.UiLayer> array = Game.i.uiManager.layers[i];
                    for (int i2 = 0; i2 < array.size; i2++) {
                        UiManager.UiLayer uiLayer = array.items[i2];
                        if (uiLayer != this.f3465b && uiLayer.getTable().isVisible()) {
                            uiLayer.getTable().setVisible(false);
                            this.c.add(uiLayer);
                        }
                    }
                }
            } else {
                for (int i3 = 0; i3 < this.c.size; i3++) {
                    this.c.items[i3].getTable().setVisible(true);
                }
                this.c.clear();
            }
            update();
        });
        labelButton.setAlignment(16);
        table4.add((Table) labelButton).padLeft(10.0f);
        Table table5 = new Table();
        table5.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f)));
        table3.add(table5).fillX().expandX().height(250.0f).padTop(5.0f);
        this.d = new Table();
        ScrollPane scrollPane = new ScrollPane(this.d, Game.i.assetManager.getScrollPaneStyle(10.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setScrollingDisabled(true, false);
        table5.add((Table) scrollPane).expand().fill().pad(5.0f).row();
        TextField.TextFieldStyle textFieldStyle = Game.i.assetManager.getTextFieldStyle(21);
        Table table6 = new Table();
        table2.add(table6).padLeft(10.0f).top().left().row();
        Table table7 = new Table();
        table6.add(table7).top().left().row();
        table7.add((Table) new Label("Cam controller", Game.i.assetManager.getLabelStyle(24)));
        Table table8 = new Table();
        table8.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f)));
        table6.add(table8).fillX().top().left().padTop(5.0f);
        Table table9 = new Table();
        table8.add(table9).expand().fill().pad(5.0f).row();
        Label label = new Label("Max velocity", Game.i.assetManager.getLabelStyle(21));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table9.add((Table) label).top().left().padRight(10.0f);
        final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(String.valueOf(MathUtils.round(this.f * 10.0f) / 10.0f), textFieldStyle);
        textFieldXPlatform.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.2
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                try {
                    CameraTools.this.f = Float.parseFloat(textFieldXPlatform.getText());
                    if (CameraTools.this.f < 0.0f) {
                        CameraTools.this.f = 0.0f;
                    }
                } catch (Exception unused) {
                }
            }
        });
        table9.add((Table) textFieldXPlatform).left().size(120.0f, 24.0f).row();
        Label label2 = new Label("Acceleration", Game.i.assetManager.getLabelStyle(21));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table9.add((Table) label2).top().left().padRight(10.0f);
        final TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform(String.valueOf(MathUtils.round(this.g * 10.0f) / 10.0f), textFieldStyle);
        textFieldXPlatform2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.3
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                try {
                    CameraTools.this.g = Float.parseFloat(textFieldXPlatform2.getText());
                    if (CameraTools.this.g < 0.0f) {
                        CameraTools.this.g = 0.0f;
                    }
                } catch (Exception unused) {
                }
            }
        });
        table9.add((Table) textFieldXPlatform2).left().size(120.0f, 24.0f).row();
        Label label3 = new Label("Decay", Game.i.assetManager.getLabelStyle(21));
        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table9.add((Table) label3).top().left().padRight(10.0f);
        final TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform(String.valueOf(MathUtils.round(this.g * 10.0f) / 10.0f), textFieldStyle);
        textFieldXPlatform3.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.4
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                try {
                    CameraTools.this.i = Float.parseFloat(textFieldXPlatform3.getText());
                    if (CameraTools.this.i < 0.0f) {
                        CameraTools.this.i = 0.0f;
                    }
                } catch (Exception unused) {
                }
            }
        });
        table9.add((Table) textFieldXPlatform3).left().size(120.0f, 24.0f).row();
        Label label4 = new Label("Zoom speed", Game.i.assetManager.getLabelStyle(21));
        label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table9.add((Table) label4).top().left().padRight(10.0f);
        final TextFieldXPlatform textFieldXPlatform4 = new TextFieldXPlatform(String.valueOf(MathUtils.round(this.h * 100.0f) / 100.0f), textFieldStyle);
        textFieldXPlatform4.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.5
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                try {
                    CameraTools.this.h = Float.parseFloat(textFieldXPlatform4.getText());
                    if (CameraTools.this.h < 0.0f) {
                        CameraTools.this.h = 0.0f;
                    }
                } catch (Exception unused) {
                }
            }
        });
        table9.add((Table) textFieldXPlatform4).left().size(120.0f, 24.0f).row();
        Label label5 = new Label("pos", Game.i.assetManager.getLabelStyle(21));
        label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table9.add((Table) label5).top().left().padTop(10.0f).padRight(10.0f);
        this.j = new Label("", Game.i.assetManager.getLabelStyle(21));
        table9.add((Table) this.j).top().left().padTop(10.0f).row();
        Label label6 = new Label("zoom", Game.i.assetManager.getLabelStyle(21));
        label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table9.add((Table) label6).top().left().padTop(10.0f).padRight(10.0f);
        this.k = new Label("", Game.i.assetManager.getLabelStyle(21));
        table9.add((Table) this.k).top().left().padTop(10.0f).row();
        Label label7 = new Label("velocity", Game.i.assetManager.getLabelStyle(21));
        label7.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table9.add((Table) label7).top().left().padRight(10.0f);
        this.l = new Label("", Game.i.assetManager.getLabelStyle(21));
        table9.add((Table) this.l).top().left().row();
        table9.add((Table) new LabelButton("Stop", Game.i.assetManager.getLabelStyle(21), () -> {
            this.e.x = 0.0f;
            this.e.y = 0.0f;
        })).colspan(2).top().left().row();
        this.o = new Table();
        table2.add(this.o).colspan(2).fillX().expandX();
        this.f3465b.getTable().setVisible(false);
        update();
    }

    public final Scenario getSelectedScenario() {
        return this.v[this.u];
    }

    public final void setSelectedScenarioIdx(int i, boolean z) {
        if (i < 0 || i > 8) {
            throw new IllegalArgumentException("idx is " + i);
        }
        this.u = i;
        if (z) {
            CameraController e = e();
            Scenario selectedScenario = getSelectedScenario();
            if (e != null && selectedScenario != null) {
                e.playScenario(selectedScenario, selectedScenario.startFrame / selectedScenario.fps, this.w);
            } else {
                f3464a.e("controller " + e + " scenario " + selectedScenario, new Object[0]);
            }
        }
        updateScenarioMenu();
    }

    public final void update() {
        this.d.clear();
        for (int i = 0; i < Game.i.uiManager.layers.length; i++) {
            Array<UiManager.UiLayer> array = Game.i.uiManager.layers[i];
            for (int i2 = 0; i2 < array.size; i2++) {
                UiManager.UiLayer uiLayer = array.items[i2];
                this.d.add(new LabelToggleButton(uiLayer.name, uiLayer.getTable().isVisible(), 21, 21.0f, bool -> {
                    uiLayer.getTable().setVisible(bool.booleanValue());
                })).fillX().row();
            }
        }
        updateScenarioMenu();
    }

    public final void updateScenarioMenu() {
        this.o.clear();
        Table table = new Table();
        this.o.add(table).expandX().fillX().row();
        table.add((Table) new Label("Scenarios", Game.i.assetManager.getLabelStyle(24))).padRight(16.0f).left();
        for (int i = 1; i <= 5; i++) {
            int i2 = i;
            RectButton rectButton = new RectButton(String.valueOf(i), Game.i.assetManager.getLabelStyle(21), () -> {
                setSelectedScenarioIdx(i2 - 1, false);
            });
            if (this.u == i - 1) {
                Color color = Color.WHITE;
                Color color2 = Color.WHITE;
                rectButton.setBackgroundColors(color, color, color2, color2);
                Color color3 = Color.BLACK;
                Color color4 = Color.BLACK;
                rectButton.setLabelColors(color3, color3, color4, color4);
            } else {
                rectButton.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(0.0f, 0.0f, 0.0f, 0.0f));
                if (this.v[i - 1] == null) {
                    Color color5 = MaterialColor.LIGHT_BLUE.P500;
                    rectButton.setLabelColors(color5, color5, MaterialColor.LIGHT_BLUE.P500, Color.WHITE);
                } else {
                    Color color6 = MaterialColor.LIGHT_GREEN.P500;
                    rectButton.setLabelColors(color6, color6, MaterialColor.LIGHT_GREEN.P500, Color.WHITE);
                }
            }
            table.add((Table) rectButton).size(24.0f, 24.0f).bottom();
        }
        table.add().height(1.0f).expandX().fillX();
        table.add(new LabelToggleButton("Loop", this.w, 21, 16.0f, false, bool -> {
            this.w = bool.booleanValue();
            updateScenarioMenu();
        })).height(24.0f).padLeft(64.0f);
        table.add(new LabelToggleButton("Draw", this.x, 21, 16.0f, false, bool2 -> {
            this.x = bool2.booleanValue();
            updateScenarioMenu();
        })).height(24.0f).padLeft(16.0f);
        Table table2 = new Table();
        this.o.add(table2).expandX().fillX().row();
        final Scenario selectedScenario = getSelectedScenario();
        if (selectedScenario != null) {
            Table table3 = new Table();
            this.o.add(table3).fillX().expandX().row();
            final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(selectedScenario.name, this.m);
            textFieldXPlatform.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.ui.shared.CameraTools.6
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    selectedScenario.name = textFieldXPlatform.getText();
                }
            });
            table3.add((Table) textFieldXPlatform).size(160.0f, 24.0f);
            PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-times"), () -> {
                Dialog.i().showConfirm("Remove scenario?", () -> {
                    this.v[this.u] = null;
                    updateScenarioMenu();
                });
            }, MaterialColor.RED.P500, MaterialColor.RED.P300, MaterialColor.RED.P800);
            paddedImageButton.setIconPosition(4.0f, 4.0f);
            paddedImageButton.setIconSize(16.0f, 16.0f);
            table3.add((Table) paddedImageButton).size(24.0f).padLeft(16.0f);
            Label label = new Label("FPS:", Game.i.assetManager.getLabelStyle(21));
            label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table3.add((Table) label).height(24.0f).padLeft(16.0f);
            final TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform(String.valueOf(selectedScenario.fps), this.m);
            textFieldXPlatform2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.7
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        selectedScenario.fps = Integer.parseInt(textFieldXPlatform2.getText());
                        if (selectedScenario.fps <= 0) {
                            selectedScenario.fps = 1;
                        }
                        CameraTools.this.b();
                    } catch (Exception unused) {
                    }
                }
            });
            table3.add((Table) textFieldXPlatform2).size(48.0f, 24.0f);
            Label label2 = new Label("Start:", Game.i.assetManager.getLabelStyle(21));
            label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table3.add((Table) label2).height(24.0f).padLeft(16.0f);
            final TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform(String.valueOf(selectedScenario.startFrame), this.m);
            textFieldXPlatform3.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.8
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        selectedScenario.startFrame = Integer.parseInt(textFieldXPlatform3.getText());
                        if (selectedScenario.startFrame < 0) {
                            selectedScenario.startFrame = 0;
                        }
                        CameraTools.this.b();
                    } catch (Exception unused) {
                    }
                }
            });
            table3.add((Table) textFieldXPlatform3).size(60.0f, 24.0f);
            Label label3 = new Label("Length:", Game.i.assetManager.getLabelStyle(21));
            label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            table3.add((Table) label3).height(24.0f).padLeft(16.0f);
            final TextFieldXPlatform textFieldXPlatform4 = new TextFieldXPlatform(String.valueOf(selectedScenario.length), this.m);
            textFieldXPlatform4.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.9
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        selectedScenario.length = Integer.parseInt(textFieldXPlatform4.getText());
                        if (selectedScenario.length <= 0) {
                            selectedScenario.length = 1;
                        }
                        CameraTools.this.b();
                    } catch (Exception unused) {
                    }
                }
            });
            table3.add((Table) textFieldXPlatform4).size(48.0f, 24.0f);
            PaddedImageButton paddedImageButton2 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-right"), () -> {
                CameraController e = e();
                Scenario selectedScenario2 = getSelectedScenario();
                if (e != null && selectedScenario2 != null) {
                    float f = selectedScenario2.startFrame / selectedScenario2.fps;
                    if (this.y >= selectedScenario2.startFrame && this.y <= selectedScenario2.length) {
                        f /= selectedScenario2.fps;
                    }
                    e.playScenario(selectedScenario2, f, this.w);
                }
            }, MaterialColor.GREEN.P500, MaterialColor.GREEN.P300, MaterialColor.GREEN.P800);
            paddedImageButton2.setIconPosition(4.0f, 4.0f);
            paddedImageButton2.setIconSize(16.0f, 16.0f);
            table3.add((Table) paddedImageButton2).size(24.0f).padLeft(16.0f);
            table3.add((Table) new LabelButton("From JSON", Game.i.assetManager.getLabelStyle(21), () -> {
                Game.i.uiManager.getTextInput(new Input.TextInputListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.10
                    @Override // com.badlogic.gdx.Input.TextInputListener
                    public void input(String str) {
                        Threads.i().runOnMainThread(() -> {
                            try {
                                CameraTools.this.v[CameraTools.this.u] = Scenario.fromJson(new JsonReader().parse(str));
                                CameraTools.this.updateScenarioMenu();
                            } catch (Exception e) {
                                CameraTools.f3464a.e("failed to load scenario from json: " + str, e);
                                Notifications.i().add("Failed to load scenario from JSON", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                            }
                        });
                    }

                    @Override // com.badlogic.gdx.Input.TextInputListener
                    public void canceled() {
                    }
                }, "Load from JSON", "", "Paste scenario JSON here");
            })).left().padLeft(20.0f).height(24.0f);
            table3.add((Table) new LabelButton("To JSON", Game.i.assetManager.getLabelStyle(21), () -> {
                String str = "";
                Scenario selectedScenario2 = getSelectedScenario();
                if (selectedScenario2 != null) {
                    Json json = new Json(JsonWriter.OutputType.json);
                    StringWriter stringWriter = new StringWriter();
                    json.setWriter(stringWriter);
                    json.writeObjectStart();
                    selectedScenario2.toJson(json);
                    json.writeObjectEnd();
                    str = stringWriter.toString();
                }
                Gdx.app.getClipboard().setContents(str);
                Notifications.i().add("Scenario copied to the clipboard", null, null, null);
            })).left().padLeft(20.0f).height(24.0f);
            table3.add().height(1.0f).expandX().fillX();
            this.q = new Table();
            Drawable tint = new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f));
            Drawable tint2 = new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(new Color(1044266751));
            tint.setMinWidth(12.0f);
            tint2.setMinWidth(12.0f);
            ScrollPane scrollPane = new ScrollPane(this.q, new ScrollPane.ScrollPaneStyle(null, tint, tint2, null, null));
            UiUtils.enableMouseMoveScrollFocus(scrollPane);
            this.o.add((Table) scrollPane).padTop(8.0f).height(120.0f).expandX().fillX().row();
            b();
            this.p = new Table();
            this.o.add(this.p).expandX().fillX().padTop(16.0f).row();
            c();
            return;
        }
        table2.add((Table) new RectButton("New scenario", Game.i.assetManager.getLabelStyle(21), () -> {
            Scenario scenario = new Scenario();
            CameraController e = e();
            if (e == null) {
                scenario.setKeyframe(0, 0.0f, InterpolationType.linear, 0.0f, InterpolationType.linear, 1.0f, InterpolationType.linear);
            } else {
                scenario.setKeyframe(0, e.camera.position.x, InterpolationType.linear, e.camera.position.y, InterpolationType.linear, (float) e.zoom, InterpolationType.linear);
            }
            this.v[this.u] = scenario;
            updateScenarioMenu();
        })).size(192.0f, 32.0f);
        this.q = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Scenario selectedScenario = getSelectedScenario();
        if (selectedScenario != null && this.q != null) {
            this.q.clearChildren();
            this.r = new Group();
            this.r.setTransform(false);
            int i = selectedScenario.length << 4;
            int max = Math.max(i, 770);
            this.q.add((Table) this.r).size(max, 120.0f).padLeft(5.0f).padRight(5.0f).row();
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 > selectedScenario.length) {
                    break;
                }
                Label label = new Label(String.valueOf(i3), Game.i.assetManager.getLabelStyle(21));
                label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                label.setPosition((i3 << 4) - 1.0f, 96.0f);
                label.setSize(2.0f, 24.0f);
                label.setAlignment(1);
                this.r.addActor(label);
                i2 = i3 + selectedScenario.fps;
            }
            for (int i4 = 0; i4 < 3; i4++) {
                Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                if (i4 % 2 == 0) {
                    image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                } else {
                    image.setColor(0.0f, 0.0f, 0.0f, 0.14f);
                }
                image.setSize(max, 24.0f);
                image.setPosition(0.0f, ((2 - i4) * 24) + 24);
                this.r.addActor(image);
            }
            if (selectedScenario.startFrame != 0) {
                Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image2.setSize(selectedScenario.startFrame << 4, 72.0f);
                image2.setPosition(0.0f, 24.0f);
                image2.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                this.r.addActor(image2);
            }
            if (max > i) {
                Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image3.setSize(max - i, 72.0f);
                image3.setPosition(i, 24.0f);
                image3.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                this.r.addActor(image3);
            }
            for (int i5 = 0; i5 <= selectedScenario.length; i5++) {
                Image image4 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image4.setSize(1.0f, 72.0f);
                image4.setPosition(i5 << 4, 24.0f);
                image4.setColor(1.0f, 1.0f, 1.0f, 0.07f);
                this.r.addActor(image4);
            }
            int i6 = 0;
            while (true) {
                int i7 = i6;
                if (i7 > selectedScenario.length) {
                    break;
                }
                Image image5 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image5.setSize(2.0f, 72.0f);
                image5.setPosition(i7 << 4, 24.0f);
                image5.setColor(1.0f, 1.0f, 1.0f, 0.14f);
                this.r.addActor(image5);
                Label label2 = new Label(":" + (i7 / selectedScenario.fps), Game.i.assetManager.getLabelStyle(21));
                label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                label2.setPosition((i7 << 4) - 1.0f, 0.0f);
                label2.setSize(2.0f, 24.0f);
                label2.setAlignment(1);
                this.r.addActor(label2);
                i6 = i7 + selectedScenario.fps;
            }
            for (int i8 = 0; i8 < selectedScenario.keyframes.size; i8++) {
                Scenario.Keyframe keyframe = selectedScenario.keyframes.items[i8];
                if (!Float.isNaN(keyframe.x)) {
                    Image image6 = new Image(Game.i.assetManager.getDrawable("small-circle"));
                    image6.setColor(MaterialColor.RED.P300);
                    image6.setSize(12.0f, 12.0f);
                    image6.setPosition((keyframe.frame << 4) - 6.0f, 76.0f);
                    this.r.addActor(image6);
                }
                if (!Float.isNaN(keyframe.y)) {
                    Image image7 = new Image(Game.i.assetManager.getDrawable("small-circle"));
                    image7.setColor(MaterialColor.GREEN.P300);
                    image7.setSize(12.0f, 12.0f);
                    image7.setPosition((keyframe.frame << 4) - 6.0f, 52.0f);
                    this.r.addActor(image7);
                }
                if (!Float.isNaN(keyframe.z)) {
                    Image image8 = new Image(Game.i.assetManager.getDrawable("small-circle"));
                    image8.setColor(MaterialColor.BLUE.P300);
                    image8.setSize(12.0f, 12.0f);
                    image8.setPosition((keyframe.frame << 4) - 6.0f, 28.0f);
                    this.r.addActor(image8);
                }
            }
            this.r.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.11
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    CameraTools.this.y = MathUtils.round(f / 16.0f);
                    CameraTools.this.c();
                }
            });
            if (this.y >= 0) {
                if (this.y > selectedScenario.length) {
                    this.y = selectedScenario.length;
                }
            } else {
                this.y = 0;
            }
            this.s = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            this.s.setSize(2.0f, 96.0f);
            this.s.setPosition(0.0f, 0.0f);
            this.s.setColor(MaterialColor.ORANGE.P300);
            this.r.addActor(this.s);
            this.t = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            this.t.setSize(4.0f, 72.0f);
            this.t.setColor(0.0f, 1.0f, 1.0f, 0.78f);
            this.r.addActor(this.t);
            d();
            return;
        }
        this.s = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        final Scenario selectedScenario = getSelectedScenario();
        if (selectedScenario != null && this.p != null) {
            this.p.clearChildren();
            Table table = new Table();
            this.p.add(table).expandX().fillX().row();
            table.add((Table) new Label("Frame: " + this.y, Game.i.assetManager.getLabelStyle(21))).height(24.0f);
            table.add().height(1.0f).expandX().fillX();
            table.add((Table) new RectButton("Clear", Game.i.assetManager.getLabelStyle(21), () -> {
                selectedScenario.removeKeyframe(this.y);
                b();
            })).size(96.0f, 24.0f).padLeft(16.0f);
            table.add((Table) new RectButton("Cam -> Frame", Game.i.assetManager.getLabelStyle(21), () -> {
                CameraController e = e();
                if (e != null) {
                    Scenario.Keyframe keyframe = selectedScenario.getKeyframe(this.y);
                    if (keyframe != null) {
                        selectedScenario.setKeyframe(this.y, e.camera.position.x, keyframe.iX, e.camera.position.y, keyframe.iY, (float) e.zoom, keyframe.iZ);
                    } else {
                        selectedScenario.setKeyframe(this.y, e.camera.position.x, InterpolationType.linear, e.camera.position.y, InterpolationType.linear, (float) e.zoom, InterpolationType.linear);
                    }
                    b();
                    c();
                }
            })).size(160.0f, 24.0f).padLeft(16.0f);
            table.add((Table) new RectButton("Frame -> Cam (calculate)", Game.i.assetManager.getLabelStyle(21), () -> {
                CameraController e = e();
                Scenario selectedScenario2 = getSelectedScenario();
                if (e != null && selectedScenario2 != null) {
                    Vector3 calculate = selectedScenario2.calculate(this.y / selectedScenario2.fps);
                    e.lookAt(Float.isNaN(calculate.x) ? e.camera.position.x : calculate.x, Float.isNaN(calculate.y) ? e.camera.position.y : calculate.y);
                    e.setZoom(Float.isNaN(calculate.z) ? e.zoom : calculate.z);
                }
            })).size(256.0f, 24.0f).padLeft(16.0f);
            table.add().height(1.0f).expandX().fillX();
            final Scenario.Keyframe keyframe = selectedScenario.getKeyframe(this.y);
            Table table2 = new Table();
            this.p.add(table2).fillX().expandX().padTop(8.0f);
            Label label = new Label("x", Game.i.assetManager.getLabelStyle(21));
            label.setColor(MaterialColor.RED.P300);
            table2.add((Table) label);
            final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform(keyframe == null || Float.isNaN(keyframe.x) ? "" : String.valueOf(keyframe.x), this.m);
            textFieldXPlatform.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.12
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        selectedScenario.setKeyframeX(CameraTools.this.y, Float.parseFloat(textFieldXPlatform.getText()), null);
                        CameraTools.this.b();
                    } catch (Exception unused) {
                    }
                }
            });
            table2.add((Table) textFieldXPlatform).size(96.0f, 24.0f).padLeft(8.0f);
            PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-times"), () -> {
                selectedScenario.setKeyframeX(this.y, Float.NaN, null);
                updateScenarioMenu();
            }, MaterialColor.RED.P500, MaterialColor.RED.P300, MaterialColor.RED.P800);
            paddedImageButton.setIconPosition(4.0f, 4.0f);
            paddedImageButton.setIconSize(16.0f, 16.0f);
            table2.add((Table) paddedImageButton).size(24.0f).padLeft(8.0f);
            InterpolationType interpolationType = (keyframe == null || keyframe.iX == null) ? InterpolationType.linear : keyframe.iX;
            final SelectBox selectBox = new SelectBox(this.n);
            selectBox.setItems(InterpolationType.values);
            selectBox.setSelected(interpolationType);
            selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.13
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    selectedScenario.setKeyframeX(CameraTools.this.y, keyframe == null ? Float.NaN : keyframe.x, (InterpolationType) selectBox.getSelected());
                    CameraTools.this.c();
                }
            });
            table2.add((Table) selectBox).size(128.0f, 24.0f).padLeft(8.0f);
            Group group = new Group();
            group.setTransform(false);
            table2.add((Table) group).size(256.0f, 16.0f).padBottom(4.0f).padTop(4.0f).padLeft(8.0f);
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setColor(1.0f, 1.0f, 1.0f, 0.14f);
            image.setSize(256.0f, 16.0f);
            group.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("small-circle"));
            image2.setSize(16.0f, 16.0f);
            image2.addAction(Actions.forever(Actions.sequence(Actions.moveTo(0.0f, 0.0f), Actions.moveTo(240.0f, 0.0f, 1.5f, InterpolationType.getObject(interpolationType)))));
            group.addActor(image2);
            LinearChartActor linearChartActor = new LinearChartActor();
            linearChartActor.setChartFromInterpolation(interpolationType);
            linearChartActor.setColor(MaterialColor.RED.P300, new Color(0.0f, 0.0f, 0.0f, 0.28f));
            table2.add((Table) linearChartActor).size(128.0f, 24.0f).padLeft(8.0f);
            table2.add().height(1.0f).expandX().fillX().row();
            Label label2 = new Label("y", Game.i.assetManager.getLabelStyle(21));
            label2.setColor(MaterialColor.GREEN.P300);
            table2.add((Table) label2);
            final TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform(keyframe == null || Float.isNaN(keyframe.y) ? "" : String.valueOf(keyframe.y), this.m);
            textFieldXPlatform2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.14
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        selectedScenario.setKeyframeY(CameraTools.this.y, Float.parseFloat(textFieldXPlatform2.getText()), null);
                        CameraTools.this.b();
                    } catch (Exception unused) {
                    }
                }
            });
            table2.add((Table) textFieldXPlatform2).size(96.0f, 24.0f).padLeft(8.0f);
            PaddedImageButton paddedImageButton2 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-times"), () -> {
                selectedScenario.setKeyframeY(this.y, Float.NaN, null);
                updateScenarioMenu();
            }, MaterialColor.RED.P500, MaterialColor.RED.P300, MaterialColor.RED.P800);
            paddedImageButton2.setIconPosition(4.0f, 4.0f);
            paddedImageButton2.setIconSize(16.0f, 16.0f);
            table2.add((Table) paddedImageButton2).size(24.0f).padLeft(8.0f);
            InterpolationType interpolationType2 = (keyframe == null || keyframe.iY == null) ? InterpolationType.linear : keyframe.iY;
            final SelectBox selectBox2 = new SelectBox(this.n);
            selectBox2.setItems(InterpolationType.values);
            selectBox2.setSelected(interpolationType2);
            selectBox2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.15
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    selectedScenario.setKeyframeY(CameraTools.this.y, keyframe == null ? Float.NaN : keyframe.y, (InterpolationType) selectBox2.getSelected());
                    CameraTools.this.c();
                }
            });
            table2.add((Table) selectBox2).size(128.0f, 24.0f).padLeft(8.0f);
            Group group2 = new Group();
            group2.setTransform(false);
            table2.add((Table) group2).size(256.0f, 16.0f).padBottom(4.0f).padTop(4.0f).padLeft(8.0f);
            Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image3.setColor(1.0f, 1.0f, 1.0f, 0.14f);
            image3.setSize(256.0f, 16.0f);
            group2.addActor(image3);
            Image image4 = new Image(Game.i.assetManager.getDrawable("small-circle"));
            image4.setSize(16.0f, 16.0f);
            image4.addAction(Actions.forever(Actions.sequence(Actions.moveTo(0.0f, 0.0f), Actions.moveTo(240.0f, 0.0f, 1.5f, InterpolationType.getObject(interpolationType2)))));
            group2.addActor(image4);
            LinearChartActor linearChartActor2 = new LinearChartActor();
            linearChartActor2.setChartFromInterpolation(interpolationType2);
            linearChartActor2.setColor(MaterialColor.GREEN.P300, new Color(0.0f, 0.0f, 0.0f, 0.28f));
            table2.add((Table) linearChartActor2).size(128.0f, 24.0f).padLeft(8.0f);
            table2.add().height(1.0f).expandX().fillX().row();
            Label label3 = new Label("z", Game.i.assetManager.getLabelStyle(21));
            label3.setColor(MaterialColor.BLUE.P300);
            table2.add((Table) label3);
            final TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform(keyframe == null || Float.isNaN(keyframe.z) ? "" : String.valueOf(keyframe.z), this.m);
            textFieldXPlatform3.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.16
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    try {
                        selectedScenario.setKeyframeZ(CameraTools.this.y, Float.parseFloat(textFieldXPlatform3.getText()), null);
                        CameraTools.this.b();
                    } catch (Exception unused) {
                    }
                }
            });
            table2.add((Table) textFieldXPlatform3).size(96.0f, 24.0f).padLeft(8.0f);
            PaddedImageButton paddedImageButton3 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-times"), () -> {
                selectedScenario.setKeyframeZ(this.y, Float.NaN, null);
                updateScenarioMenu();
            }, MaterialColor.RED.P500, MaterialColor.RED.P300, MaterialColor.RED.P800);
            paddedImageButton3.setIconPosition(4.0f, 4.0f);
            paddedImageButton3.setIconSize(16.0f, 16.0f);
            table2.add((Table) paddedImageButton3).size(24.0f).padLeft(8.0f);
            InterpolationType interpolationType3 = (keyframe == null || keyframe.iZ == null) ? InterpolationType.linear : keyframe.iZ;
            final SelectBox selectBox3 = new SelectBox(this.n);
            selectBox3.setItems(InterpolationType.values);
            selectBox3.setSelected(interpolationType3);
            selectBox3.addListener(new ChangeListener() { // from class: com.prineside.tdi2.ui.shared.CameraTools.17
                @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
                public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                    selectedScenario.setKeyframeZ(CameraTools.this.y, keyframe == null ? Float.NaN : keyframe.z, (InterpolationType) selectBox3.getSelected());
                    CameraTools.this.c();
                }
            });
            table2.add((Table) selectBox3).size(128.0f, 24.0f).padLeft(8.0f);
            Group group3 = new Group();
            group3.setTransform(false);
            table2.add((Table) group3).size(256.0f, 16.0f).padBottom(4.0f).padTop(4.0f).padLeft(8.0f);
            Image image5 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image5.setColor(1.0f, 1.0f, 1.0f, 0.14f);
            image5.setSize(256.0f, 16.0f);
            group3.addActor(image5);
            Image image6 = new Image(Game.i.assetManager.getDrawable("small-circle"));
            image6.setSize(16.0f, 16.0f);
            image6.addAction(Actions.forever(Actions.sequence(Actions.moveTo(0.0f, 0.0f), Actions.moveTo(240.0f, 0.0f, 1.5f, InterpolationType.getObject(interpolationType3)))));
            group3.addActor(image6);
            LinearChartActor linearChartActor3 = new LinearChartActor();
            linearChartActor3.setChartFromInterpolation(interpolationType3);
            linearChartActor3.setColor(MaterialColor.BLUE.P300, new Color(0.0f, 0.0f, 0.0f, 0.28f));
            table2.add((Table) linearChartActor3).size(128.0f, 24.0f).padLeft(8.0f);
            table2.add().height(1.0f).expandX().fillX().row();
        }
    }

    private void d() {
        CameraController e = e();
        if (e != null && e.currentScenario != null && this.s != null) {
            this.s.setX(((e.scenarioTime * e.currentScenario.fps) * 16.0f) - 1.0f);
        }
        if (this.t != null) {
            this.t.setPosition((this.y << 4) - 2.0f, 24.0f);
        }
    }

    private static CameraController e() {
        if (Game.i.screenManager.getCurrentScreen() instanceof GameScreen) {
            return ((GameScreen) Game.i.screenManager.getCurrentScreen()).S._input.cameraController;
        }
        if (Game.i.screenManager.getCurrentScreen() instanceof ResearchesScreen) {
            return ((ResearchesScreen) Game.i.screenManager.getCurrentScreen()).cameraController;
        }
        if (Game.i.screenManager.getCurrentScreen() instanceof MapEditorScreen) {
            return ((MapEditorScreen) Game.i.screenManager.getCurrentScreen()).S._input.getCameraController();
        }
        return null;
    }

    private static boolean f() {
        return Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.CAMERA_TOOLS_ENABLED) != 0.0d;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        setVisible(false);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public final void postRender(float f) {
        if (f()) {
            if (Game.i.uiManager.stage.getKeyboardFocus() == null && Gdx.input.isKeyJustPressed(139)) {
                setVisible(!isVisible());
            }
            if (isVisible() && Gdx.input.isKeyJustPressed(66)) {
                Game.i.uiManager.stage.unfocusAll();
            }
            CameraController e = e();
            boolean z = false;
            boolean z2 = false;
            float f2 = 0.0f;
            if (Gdx.input.isKeyPressed(129)) {
                if (e != null) {
                    if (Gdx.input.isKeyPressed(21)) {
                        this.e.x -= this.g * f;
                        z = true;
                    }
                    if (Gdx.input.isKeyPressed(22)) {
                        this.e.x += this.g * f;
                        z = true;
                    }
                    if (Gdx.input.isKeyPressed(19)) {
                        this.e.y += this.g * f;
                        z2 = true;
                    }
                    if (Gdx.input.isKeyPressed(20)) {
                        this.e.y -= this.g * f;
                        z2 = true;
                    }
                    if (Gdx.input.isKeyPressed(76)) {
                        f2 = this.h * f;
                    }
                    if (Gdx.input.isKeyPressed(75)) {
                        f2 = (-this.h) * f;
                    }
                }
                if (Gdx.input.isKeyJustPressed(8)) {
                    setSelectedScenarioIdx(0, true);
                } else if (Gdx.input.isKeyJustPressed(9)) {
                    setSelectedScenarioIdx(1, true);
                } else if (Gdx.input.isKeyJustPressed(10)) {
                    setSelectedScenarioIdx(2, true);
                } else if (Gdx.input.isKeyJustPressed(11)) {
                    setSelectedScenarioIdx(3, true);
                } else if (Gdx.input.isKeyJustPressed(12)) {
                    setSelectedScenarioIdx(4, true);
                }
            }
            if (Gdx.input.isKeyJustPressed(69)) {
                Scenario selectedScenario = getSelectedScenario();
                if (selectedScenario != null && e != null) {
                    e.playScenario(selectedScenario, 0.0f, this.w);
                } else if (selectedScenario == null) {
                    Notifications.i().add("Camera scenario is not loaded", null, null, null);
                }
            }
            if (this.e.x < 0.0f) {
                if (this.e.x < (-this.f)) {
                    this.e.x = -this.f;
                }
            } else if (this.e.x > this.f) {
                this.e.x = this.f;
            }
            if (this.e.y < 0.0f) {
                if (this.e.y < (-this.f)) {
                    this.e.y = -this.f;
                }
            } else if (this.e.y > this.f) {
                this.e.y = this.f;
            }
            if (e != null) {
                Vector3 lookPos = e.getLookPos();
                if (!this.e.isZero()) {
                    e.lookAt(lookPos.x + (this.e.x * f), lookPos.y + (this.e.y * f));
                    if (!z) {
                        if (this.e.x > 0.0f) {
                            this.e.x -= this.i * f;
                            if (this.e.x < 0.0f) {
                                this.e.x = 0.0f;
                            }
                        } else {
                            this.e.x += this.i * f;
                            if (this.e.x > 0.0f) {
                                this.e.x = 0.0f;
                            }
                        }
                    }
                    if (!z2) {
                        if (this.e.y > 0.0f) {
                            this.e.y -= this.i * f;
                            if (this.e.y < 0.0f) {
                                this.e.y = 0.0f;
                            }
                        } else {
                            this.e.y += this.i * f;
                            if (this.e.y > 0.0f) {
                                this.e.y = 0.0f;
                            }
                        }
                    }
                }
                if (f2 != 0.0f) {
                    e.setZoom(e.zoom + f2);
                }
            }
            if (e != null) {
                Vector3 lookPos2 = e.getLookPos();
                this.j.setText(((int) lookPos2.x) + " : " + ((int) lookPos2.y));
                this.j.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
                this.k.setText(new StringBuilder().append(MathUtils.round(((float) e.zoom) * 100.0f) / 100.0f).toString());
                this.l.setText((MathUtils.round(this.e.x * 10.0f) / 10.0f) + " : " + (MathUtils.round(this.e.y * 10.0f) / 10.0f));
            } else {
                this.j.setText("No camera");
                this.j.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.k.setText("-");
                this.l.setText("-");
            }
            Scenario selectedScenario2 = getSelectedScenario();
            if (e != null && this.x && selectedScenario2 != null) {
                SpriteBatch spriteBatch = Game.i.renderingManager.batch;
                ResourcePack.AtlasTextureRegion blankWhiteTextureRegion = Game.i.assetManager.getBlankWhiteTextureRegion();
                Color color = new Color();
                Color color2 = new Color();
                Color color3 = MaterialColor.GREEN.P500;
                Color color4 = MaterialColor.RED.P500;
                BitmapFont debugFont = Game.i.assetManager.getDebugFont(false);
                spriteBatch.setProjectionMatrix(e.camera.combined);
                spriteBatch.begin();
                float f3 = Float.NaN;
                float f4 = Float.NaN;
                float f5 = Float.NaN;
                double d = 0.0d;
                float f6 = selectedScenario2.length / selectedScenario2.fps;
                double d2 = 0.0d;
                while (true) {
                    double d3 = d2;
                    if (d3 > f6) {
                        break;
                    }
                    Vector3 calculate = selectedScenario2.calculate((float) d3);
                    if (!Float.isNaN(f3) && !Float.isNaN(f4) && !Float.isNaN(calculate.x) && !Float.isNaN(calculate.y)) {
                        color.set(color3).lerp(color4, (float) (d / f6));
                        color2.set(color3).lerp(color4, (float) (d3 / f6));
                        DrawUtils.texturedLineD(spriteBatch, blankWhiteTextureRegion, f3, f4, calculate.x, calculate.y, f5 * 2.0f, calculate.z * 2.0f, color, color2);
                    }
                    f3 = calculate.x;
                    f4 = calculate.y;
                    f5 = calculate.z;
                    d = d3;
                    d2 = d3 + 0.05d;
                }
                ResourcePack.AtlasTextureRegion textureRegion = Game.i.assetManager.getTextureRegion("circle");
                int i = -1;
                for (int i2 = 0; i2 < selectedScenario2.length; i2++) {
                    Vector3 calculate2 = selectedScenario2.calculate(i2 / selectedScenario2.fps);
                    if (i2 != i) {
                        for (int i3 = 0; i3 < selectedScenario2.keyframes.size; i3++) {
                            Scenario.Keyframe keyframe = selectedScenario2.keyframes.items[i3];
                            if (keyframe.frame == i2) {
                                spriteBatch.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                                spriteBatch.draw(textureRegion, calculate2.x - 8.0f, calculate2.y - 8.0f, 16.0f, 16.0f);
                                spriteBatch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
                                debugFont.draw(spriteBatch, String.valueOf(i2), calculate2.x - 1.0f, calculate2.y + 3.0f, 2.0f, 1, false);
                            }
                            if (keyframe.frame >= i2) {
                                break;
                            }
                        }
                        i = i2;
                    }
                }
                spriteBatch.end();
                spriteBatch.setProjectionMatrix(Game.i.uiManager.stage.getCamera().combined);
                float f7 = Float.NaN;
                float f8 = Float.NaN;
                int i4 = 0;
                spriteBatch.begin();
                float floatBits = new Color(1.0f, 1.0f, 1.0f, 0.14f).toFloatBits();
                double d4 = 0.0d;
                while (true) {
                    double d5 = d4;
                    if (d5 > f6) {
                        break;
                    }
                    Vector3 calculate3 = selectedScenario2.calculate((float) d5);
                    if (!Float.isNaN(f7) && !Float.isNaN(f8) && !Float.isNaN(calculate3.x) && !Float.isNaN(calculate3.y)) {
                        DrawUtils.texturedLineC(spriteBatch, blankWhiteTextureRegion, i4, 0.0f, i4, ((float) (PMath.getDistanceBetweenPoints(calculate3.x, calculate3.y, f7, f8) / 0.01d)) * 0.5f, 2.0f, floatBits, floatBits);
                    }
                    f7 = calculate3.x;
                    f8 = calculate3.y;
                    i4++;
                    d4 = d5 + 0.01d;
                }
                float floatBits2 = MaterialColor.GREEN.P500.toFloatBits();
                if (e.currentScenario != null) {
                    Vector3 calculate4 = selectedScenario2.calculate((float) (e.scenarioTime - 0.005d));
                    float f9 = calculate4.x;
                    float f10 = calculate4.y;
                    Vector3 calculate5 = selectedScenario2.calculate((float) (e.scenarioTime + 0.005d));
                    if (!Float.isNaN(calculate5.x) && !Float.isNaN(calculate5.y) && !Float.isNaN(f9) && !Float.isNaN(f10)) {
                        float distanceBetweenPoints = (float) (PMath.getDistanceBetweenPoints(calculate5.x, calculate5.y, f9, f10) / 0.01d);
                        float f11 = (float) (e.scenarioTime / 0.01d);
                        DrawUtils.texturedLineC(spriteBatch, blankWhiteTextureRegion, f11, 0.0f, f11, distanceBetweenPoints * 0.5f, 4.0f, floatBits2, floatBits2);
                    }
                }
                spriteBatch.end();
            }
            if (isVisible()) {
                d();
            }
        }
    }

    public final boolean isVisible() {
        return this.f3465b.getTable().isVisible();
    }

    public final void setVisible(boolean z) {
        this.f3465b.getTable().setVisible(z);
        if (z) {
            update();
        } else {
            Game.i.uiManager.stage.unfocusAll();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/CameraTools$Scenario.class */
    public static class Scenario {

        /* renamed from: a, reason: collision with root package name */
        private static final Vector3 f3497a = new Vector3();

        /* renamed from: b, reason: collision with root package name */
        private static final Comparator<Keyframe> f3498b = (keyframe, keyframe2) -> {
            return Integer.compare(keyframe.frame, keyframe2.frame);
        };
        public String name = "CS";
        public int fps = 4;
        public int length = 40;
        public int startFrame = 0;
        public Array<Keyframe> keyframes = new Array<>(true, 1, Keyframe.class);

        public double getDuration() {
            return this.length / this.fps;
        }

        public Vector3 calculate(float f) {
            float f2;
            float f3;
            float f4;
            if (f > this.length / this.fps) {
                f = this.length / this.fps;
            }
            int i = (int) (f * this.fps);
            f3497a.x = Float.NaN;
            Keyframe keyframe = null;
            Keyframe keyframe2 = null;
            int i2 = 0;
            int i3 = this.keyframes.size - 1;
            while (true) {
                if (i3 < 0) {
                    break;
                }
                Keyframe keyframe3 = this.keyframes.items[i3];
                if (keyframe3.frame > i || Float.isNaN(keyframe3.x)) {
                    i3--;
                } else {
                    keyframe = keyframe3;
                    i2 = i3;
                    break;
                }
            }
            if (keyframe != null) {
                int i4 = i2 + 1;
                while (true) {
                    if (i4 >= this.keyframes.size) {
                        break;
                    }
                    Keyframe keyframe4 = this.keyframes.items[i4];
                    if (keyframe4.frame <= i || Float.isNaN(keyframe4.x)) {
                        i4++;
                    } else {
                        keyframe2 = keyframe4;
                        break;
                    }
                }
                if (keyframe2 == null) {
                    f3497a.x = keyframe.x;
                } else {
                    if (keyframe.frame == keyframe2.frame) {
                        f4 = 1.0f;
                    } else {
                        float f5 = keyframe.frame / this.fps;
                        f4 = (f - f5) / ((keyframe2.frame / this.fps) - f5);
                    }
                    f3497a.x = InterpolationType.getObject(keyframe2.iX).apply(keyframe.x, keyframe2.x, f4);
                }
            }
            f3497a.y = Float.NaN;
            Keyframe keyframe5 = null;
            Keyframe keyframe6 = null;
            int i5 = 0;
            int i6 = this.keyframes.size - 1;
            while (true) {
                if (i6 < 0) {
                    break;
                }
                Keyframe keyframe7 = this.keyframes.items[i6];
                if (keyframe7.frame > i || Float.isNaN(keyframe7.y)) {
                    i6--;
                } else {
                    keyframe5 = keyframe7;
                    i5 = i6;
                    break;
                }
            }
            if (keyframe5 != null) {
                int i7 = i5 + 1;
                while (true) {
                    if (i7 >= this.keyframes.size) {
                        break;
                    }
                    Keyframe keyframe8 = this.keyframes.items[i7];
                    if (keyframe8.frame <= i || Float.isNaN(keyframe8.y)) {
                        i7++;
                    } else {
                        keyframe6 = keyframe8;
                        break;
                    }
                }
                if (keyframe6 == null) {
                    f3497a.y = keyframe5.y;
                } else {
                    if (keyframe5.frame == keyframe6.frame) {
                        f3 = 1.0f;
                    } else {
                        float f6 = keyframe5.frame / this.fps;
                        f3 = (f - f6) / ((keyframe6.frame / this.fps) - f6);
                    }
                    f3497a.y = InterpolationType.getObject(keyframe6.iY).apply(keyframe5.y, keyframe6.y, f3);
                }
            }
            f3497a.z = Float.NaN;
            Keyframe keyframe9 = null;
            Keyframe keyframe10 = null;
            int i8 = 0;
            int i9 = this.keyframes.size - 1;
            while (true) {
                if (i9 < 0) {
                    break;
                }
                Keyframe keyframe11 = this.keyframes.items[i9];
                if (keyframe11.frame > i || Float.isNaN(keyframe11.z)) {
                    i9--;
                } else {
                    keyframe9 = keyframe11;
                    i8 = i9;
                    break;
                }
            }
            if (keyframe9 != null) {
                int i10 = i8 + 1;
                while (true) {
                    if (i10 >= this.keyframes.size) {
                        break;
                    }
                    Keyframe keyframe12 = this.keyframes.items[i10];
                    if (keyframe12.frame <= i || Float.isNaN(keyframe12.z)) {
                        i10++;
                    } else {
                        keyframe10 = keyframe12;
                        break;
                    }
                }
                if (keyframe10 == null) {
                    f3497a.z = keyframe9.z;
                } else {
                    if (keyframe9.frame == keyframe10.frame) {
                        f2 = 1.0f;
                    } else {
                        float f7 = keyframe9.frame / this.fps;
                        f2 = (f - f7) / ((keyframe10.frame / this.fps) - f7);
                    }
                    f3497a.z = InterpolationType.getObject(keyframe10.iZ).apply(keyframe9.z, keyframe10.z, f2);
                }
            }
            return f3497a;
        }

        public Keyframe getKeyframe(int i) {
            for (int i2 = 0; i2 < this.keyframes.size; i2++) {
                if (this.keyframes.items[i2].frame == i) {
                    return this.keyframes.items[i2];
                }
            }
            return null;
        }

        public void removeKeyframe(int i) {
            Keyframe keyframe = getKeyframe(i);
            if (keyframe != null) {
                this.keyframes.removeValue(keyframe, true);
            }
        }

        public Keyframe setKeyframe(int i, float f, InterpolationType interpolationType, float f2, InterpolationType interpolationType2, float f3, InterpolationType interpolationType3) {
            if (Float.isNaN(f) && Float.isNaN(f2) && Float.isNaN(f3)) {
                removeKeyframe(i);
                return null;
            }
            Keyframe keyframe = getKeyframe(i);
            Keyframe keyframe2 = keyframe;
            if (keyframe == null) {
                Keyframe keyframe3 = new Keyframe();
                keyframe2 = keyframe3;
                keyframe3.frame = i;
                this.keyframes.add(keyframe2);
                this.keyframes.sort(f3498b);
            }
            keyframe2.x = f;
            keyframe2.iX = interpolationType;
            keyframe2.y = f2;
            keyframe2.iY = interpolationType2;
            keyframe2.z = f3;
            keyframe2.iZ = interpolationType3;
            return keyframe2;
        }

        public void setKeyframeX(int i, float f, InterpolationType interpolationType) {
            Keyframe keyframe = getKeyframe(i);
            if (interpolationType == null) {
                if (keyframe == null) {
                    interpolationType = InterpolationType.linear;
                } else {
                    interpolationType = keyframe.iX;
                }
            }
            if (keyframe == null) {
                setKeyframe(i, f, interpolationType, Float.NaN, null, Float.NaN, null);
            } else {
                setKeyframe(i, f, interpolationType, keyframe.y, keyframe.iY, keyframe.z, keyframe.iZ);
            }
        }

        public void setKeyframeY(int i, float f, InterpolationType interpolationType) {
            Keyframe keyframe = getKeyframe(i);
            if (interpolationType == null) {
                if (keyframe == null) {
                    interpolationType = InterpolationType.linear;
                } else {
                    interpolationType = keyframe.iY;
                }
            }
            if (keyframe == null) {
                setKeyframe(i, Float.NaN, null, f, interpolationType, Float.NaN, null);
            } else {
                setKeyframe(i, keyframe.x, keyframe.iX, f, interpolationType, keyframe.z, keyframe.iZ);
            }
        }

        public void setKeyframeZ(int i, float f, InterpolationType interpolationType) {
            Keyframe keyframe = getKeyframe(i);
            if (interpolationType == null) {
                if (keyframe == null) {
                    interpolationType = InterpolationType.linear;
                } else {
                    interpolationType = keyframe.iZ;
                }
            }
            if (keyframe == null) {
                setKeyframe(i, Float.NaN, null, Float.NaN, null, f, interpolationType);
            } else {
                setKeyframe(i, keyframe.x, keyframe.iX, keyframe.y, keyframe.iY, f, interpolationType);
            }
        }

        public static Scenario fromJson(JsonValue jsonValue) {
            Scenario scenario = new Scenario();
            scenario.name = jsonValue.getString(Attribute.NAME_ATTR);
            scenario.fps = jsonValue.getInt("fps");
            scenario.length = jsonValue.getInt("length");
            scenario.startFrame = jsonValue.getInt("startFrame", 0);
            try {
                Iterator<JsonValue> iterator2 = jsonValue.get("keyframes").iterator2();
                while (iterator2.hasNext()) {
                    scenario.keyframes.add(Keyframe.fromJson(iterator2.next()));
                }
                scenario.keyframes.sort(f3498b);
            } catch (Exception unused) {
            }
            return scenario;
        }

        public void toJson(Json json) {
            json.writeValue(Attribute.NAME_ATTR, this.name);
            json.writeValue("fps", Integer.valueOf(this.fps));
            json.writeValue("length", Integer.valueOf(this.length));
            json.writeValue("startFrame", Integer.valueOf(this.startFrame));
            json.writeArrayStart("keyframes");
            for (int i = 0; i < this.keyframes.size; i++) {
                json.writeObjectStart();
                this.keyframes.items[i].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/CameraTools$Scenario$Keyframe.class */
        public static class Keyframe {
            public int frame;
            public float x;
            public float y;
            public float z;
            public InterpolationType iX = InterpolationType.linear;
            public InterpolationType iY = InterpolationType.linear;
            public InterpolationType iZ = InterpolationType.linear;

            public static Keyframe fromJson(JsonValue jsonValue) {
                Keyframe keyframe = new Keyframe();
                keyframe.frame = jsonValue.getInt("f");
                keyframe.x = jsonValue.getFloat("x", Float.NaN);
                if (!Float.isNaN(keyframe.x)) {
                    try {
                        keyframe.iX = InterpolationType.valueOf(jsonValue.getString("iX"));
                    } catch (Exception unused) {
                    }
                }
                keyframe.y = jsonValue.getFloat("y", Float.NaN);
                if (!Float.isNaN(keyframe.y)) {
                    try {
                        keyframe.iY = InterpolationType.valueOf(jsonValue.getString("iY"));
                    } catch (Exception unused2) {
                    }
                }
                keyframe.z = jsonValue.getFloat("z", Float.NaN);
                if (!Float.isNaN(keyframe.z)) {
                    try {
                        keyframe.iZ = InterpolationType.valueOf(jsonValue.getString("iZ"));
                    } catch (Exception unused3) {
                    }
                }
                return keyframe;
            }

            public void toJson(Json json) {
                json.writeValue("f", Integer.valueOf(this.frame));
                if (!Float.isNaN(this.x)) {
                    json.writeValue("x", Float.valueOf(this.x));
                    json.writeValue("iX", this.iX.name());
                }
                if (!Float.isNaN(this.y)) {
                    json.writeValue("y", Float.valueOf(this.y));
                    json.writeValue("iY", this.iY.name());
                }
                if (!Float.isNaN(this.z)) {
                    json.writeValue("z", Float.valueOf(this.z));
                    json.writeValue("iZ", this.iZ.name());
                }
            }
        }
    }
}
