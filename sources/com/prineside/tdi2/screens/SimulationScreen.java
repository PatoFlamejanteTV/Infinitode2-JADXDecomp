package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.VerticalGroup;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.simulation.SimConfig;
import com.prineside.tdi2.utils.simulation.SimLogListener;
import com.prineside.tdi2.utils.simulation.SimTypeProvider;
import com.prineside.tdi2.utils.simulation.Simulation;
import com.prineside.tdi2.utils.simulation.providers.PerformanceBenchmarkSimProvider;
import com.prineside.tdi2.utils.simulation.providers.SyncCheckSimProvider;
import com.prineside.tdi2.utils.simulation.providers.TowerBenchmarkSimProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/SimulationScreen.class */
public class SimulationScreen extends Screen {
    public static final HashMap<String, SimTypeProvider> SIM_TYPE_PROVIDERS = new HashMap<>();
    public SimConfig simConfig;
    public SelectBox<String> simTypeSelect;
    public Table formTable;
    public VerticalGroup simCells;
    public LabelToggleButton autoNextToggle;
    public final DelayedRemovalArray<SimEntry> runningSimulations = new DelayedRemovalArray<>(true, 1, SimEntry.class);

    /* renamed from: a, reason: collision with root package name */
    private final UiManager.UiLayer f2832a = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "SimulationScreen main");

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/SimulationScreen$SimEntry.class */
    public static final class SimEntry {
        public RunningSimCell uiCell;
        public Simulation simulation;
    }

    static {
        SimTypeProvider[] simTypeProviderArr = {new SyncCheckSimProvider(), new TowerBenchmarkSimProvider(), new PerformanceBenchmarkSimProvider()};
        for (int i = 0; i < 3; i++) {
            SimTypeProvider simTypeProvider = simTypeProviderArr[i];
            SIM_TYPE_PROVIDERS.put(simTypeProvider.getName(), simTypeProvider);
        }
    }

    public SimulationScreen() {
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        Game.i.musicManager.stopMusic();
        ScreenTitle.i().setIcon(Game.i.assetManager.getDrawable("icon-lstm-network")).setText("Simulation").setVisible(true);
        BackButton.i().setVisible(true).setText(null).setClickHandler(() -> {
            Game.i.screenManager.goToMainMenu();
        });
        Table table = this.f2832a.getTable();
        table.add().height(1.0f).growX();
        Table table2 = new Table();
        table2.add().width(1.0f).height(40.0f).row();
        ScrollPane scrollPane = new ScrollPane(table2, Game.i.assetManager.getScrollPaneStyle(10.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setScrollingDisabled(true, false);
        table.add((Table) scrollPane).width(350.0f).growY();
        this.simConfig = new SimConfig();
        this.simConfig.difficultyMode = DifficultyMode.NORMAL;
        this.simConfig.basicLevelName = "sim";
        this.simConfig.startTimestamp = Game.getTimestampMillis();
        Label label = new Label("Difficulty mode", Game.i.assetManager.getLabelStyle(18));
        label.setColor(MaterialColor.GREY.P500);
        table2.add((Table) label).growX().row();
        final SelectBox selectBox = new SelectBox(Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getDebugFont(false), true));
        selectBox.setItems(DifficultyMode.values);
        selectBox.setSelected(DifficultyMode.NORMAL);
        selectBox.addListener(new ChangeListener() { // from class: com.prineside.tdi2.screens.SimulationScreen.1
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                SimulationScreen.this.simConfig.difficultyMode = (DifficultyMode) selectBox.getSelected();
            }
        });
        table2.add((Table) selectBox).growX().height(40.0f).padBottom(10.0f).row();
        Label label2 = new Label("Map name", Game.i.assetManager.getLabelStyle(18));
        label2.setColor(MaterialColor.GREY.P500);
        table2.add((Table) label2).growX().row();
        final SelectBox selectBox2 = new SelectBox(Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getDebugFont(true), false));
        Array array = new Array(true, 1, String.class);
        Array.ArrayIterator<BasicLevel> it = Game.i.basicLevelManager.levelsOrdered.iterator();
        while (it.hasNext()) {
            array.add(it.next().name);
        }
        selectBox2.setItems(array);
        selectBox2.setSelected("sim");
        selectBox2.addListener(new ChangeListener() { // from class: com.prineside.tdi2.screens.SimulationScreen.2
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                SimulationScreen.this.simConfig.basicLevelName = (String) selectBox2.getSelected();
                if (Game.i.basicLevelManager.getLevel(SimulationScreen.this.simConfig.basicLevelName) == null) {
                    selectBox2.setColor(Color.RED);
                } else {
                    selectBox2.setColor(Color.WHITE);
                }
            }
        });
        table2.add((Table) selectBox2).height(40.0f).growX().padBottom(10.0f).row();
        Label label3 = new Label("Simulation type", Game.i.assetManager.getLabelStyle(18));
        label3.setColor(MaterialColor.GREY.P500);
        table2.add((Table) label3).growX().row();
        this.simTypeSelect = new SelectBox<>(Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getDebugFont(true), true));
        Array<String> array2 = new Array<>();
        Iterator<Map.Entry<String, SimTypeProvider>> it2 = SIM_TYPE_PROVIDERS.entrySet().iterator();
        while (it2.hasNext()) {
            array2.add(it2.next().getKey());
        }
        this.simTypeSelect.setItems(array2);
        this.simTypeSelect.addListener(new ChangeListener() { // from class: com.prineside.tdi2.screens.SimulationScreen.3
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                SimulationScreen.this.updateForm();
            }
        });
        table2.add((Table) this.simTypeSelect).growX().padBottom(10.0f).height(40.0f).row();
        this.autoNextToggle = new LabelToggleButton("Run next sim on finish", true, 18, 24.0f, false, null);
        table2.add(this.autoNextToggle).growX().padTop(8.0f).padBottom(4.0f).row();
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(MaterialColor.GREY.P700);
        table2.add((Table) image).growX().height(1.0f).padBottom(10.0f).row();
        this.formTable = new Table();
        table2.add(this.formTable).growX().row();
        table2.add().width(1.0f).height(140.0f).row();
        table2.add().width(1.0f).growY();
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setColor(MaterialColor.GREY.P700);
        table.add((Table) image2).fillY().width(1.0f).padLeft(10.0f).padRight(10.0f);
        this.simCells = new VerticalGroup();
        ScrollPane scrollPane2 = new ScrollPane(this.simCells, Game.i.assetManager.getScrollPaneStyle(10.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane2);
        scrollPane2.setScrollingDisabled(true, false);
        table.add((Table) scrollPane2).growY().width(1000.0f).padTop(40.0f).padBottom(40.0f);
        table.add().height(1.0f).growX();
        updateForm();
    }

    public void updateForm() {
        this.formTable.clear();
        SIM_TYPE_PROVIDERS.get(this.simTypeSelect.getSelected()).prepareSimForm(this);
    }

    public void addSimulation(Simulation simulation) {
        SimEntry simEntry = new SimEntry();
        simEntry.simulation = simulation;
        simEntry.uiCell = new RunningSimCell(simulation);
        this.runningSimulations.add(simEntry);
        this.simCells.addActor(simEntry.uiCell);
        simulation.setSimFinishListener(() -> {
            startNextSim(simulation);
        });
    }

    public void startNextSim(Simulation simulation) {
        if (this.autoNextToggle.isEnabled()) {
            for (int i = 0; i < this.runningSimulations.size; i++) {
                if (this.runningSimulations.get(i).simulation == simulation) {
                    for (int i2 = i + 1; i2 < this.runningSimulations.size; i2++) {
                        SimEntry simEntry = this.runningSimulations.get(i2);
                        if (!simEntry.simulation.isRunning()) {
                            simEntry.uiCell.sumRunTime = 0.0d;
                            simEntry.simulation.start();
                            return;
                        }
                    }
                    return;
                }
            }
        }
    }

    public void stopAllSimulations() {
        for (int i = 0; i < this.runningSimulations.size; i++) {
            SimEntry simEntry = this.runningSimulations.get(i);
            if (simEntry.simulation.isRunning()) {
                simEntry.simulation.stop();
            }
        }
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (Game.i.settingsManager.isEscButtonJustPressed()) {
            Game.i.screenManager.goToMainMenu();
        }
        for (int i = 0; i < this.runningSimulations.size; i++) {
            this.runningSimulations.get(i).uiCell.update();
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        stopAllSimulations();
        super.dispose();
        Game.i.uiManager.removeLayer(this.f2832a);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/SimulationScreen$RunningSimCell.class */
    public static final class RunningSimCell extends Table implements SimLogListener {
        public Simulation simulation;
        public Label titleLabel;
        public Label statusLabel;
        public Label progressLabel;
        public Label lastLogMessage;
        public Image progressBar;
        public FancyButton startStopButton;
        public double sumRunTime;

        public RunningSimCell(Simulation simulation) {
            Preconditions.checkNotNull(simulation);
            this.simulation = simulation;
            Table table = new Table();
            table.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.BLUE_GREY.P900));
            add((RunningSimCell) table).grow().width(1000.0f).padBottom(5.0f);
            this.titleLabel = new Label(simulation.getName(), Game.i.assetManager.getLabelStyle(21));
            this.titleLabel.setColor(MaterialColor.AMBER.P300);
            this.titleLabel.setWrap(true);
            table.add((Table) this.titleLabel).padLeft(10.0f).padRight(10.0f).padTop(10.0f).growX().row();
            Table table2 = new Table();
            table.add(table2).pad(10.0f).growX().row();
            Table table3 = new Table();
            table2.add(table3).growX();
            this.statusLabel = new Label("", Game.i.assetManager.getLabelStyle(18));
            table3.add((Table) this.statusLabel).growX().row();
            Group group = new Group();
            group.setTransform(false);
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.56f)));
            image.setSize(700.0f, 24.0f);
            group.addActor(image);
            this.progressBar = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.BLUE_GREY.P700));
            this.progressBar.setHeight(24.0f);
            group.addActor(this.progressBar);
            this.progressLabel = new Label("", Game.i.assetManager.getLabelStyle(18));
            this.progressLabel.setHeight(24.0f);
            this.progressLabel.setPosition(10.0f, 0.0f);
            group.addActor(this.progressLabel);
            table3.add((Table) group).width(700.0f).height(24.0f).left().row();
            this.lastLogMessage = new Label("", Game.i.assetManager.getLabelStyle(18));
            this.lastLogMessage.setWrap(true);
            table3.add((Table) this.lastLogMessage).fillX().padTop(5.0f).row();
            this.startStopButton = new FancyButton("regularButton.a", () -> {
                if (simulation.isRunning()) {
                    simulation.stop();
                } else {
                    this.sumRunTime = 0.0d;
                    simulation.start();
                }
            }).withLabel(21, "");
            table2.add(this.startStopButton).size(192.0f, 48.0f).row();
            simulation.setSimLogListener(this);
            update();
        }

        public final void update() {
            String str = "";
            if (this.sumRunTime > 0.0d) {
                float progress = this.simulation.getProgress();
                str = StringFormatter.digestTime((int) this.sumRunTime).toString();
                if (this.simulation.isRunning() && progress > 0.0f) {
                    str = str + ", ETA: " + ((Object) StringFormatter.digestTime((int) ((1.0f - progress) * (this.sumRunTime / progress))));
                }
            }
            if (this.simulation.isRunning()) {
                this.sumRunTime += Gdx.graphics.getDeltaTime();
                this.statusLabel.setColor(MaterialColor.LIGHT_GREEN.P500);
                this.statusLabel.setText("Running " + str);
                this.startStopButton.label.setText("Stop");
                this.startStopButton.setFlavor("regularRedButton.a");
            } else {
                if (this.simulation.isSuccessful()) {
                    this.statusLabel.setColor(MaterialColor.GREY.P500);
                    this.statusLabel.setText("Stopped " + str);
                } else {
                    this.statusLabel.setColor(MaterialColor.ORANGE.P500);
                    this.statusLabel.setText("Stopped (error) " + str);
                }
                if (this.simulation.isReadyToStart()) {
                    this.startStopButton.label.setText("Start");
                    this.startStopButton.setEnabled(true);
                    this.startStopButton.setFlavor("regularGreenButton.a");
                } else {
                    this.startStopButton.label.setText("Wait...");
                    this.startStopButton.setEnabled(false);
                }
            }
            float progress2 = this.simulation.getProgress();
            float f = progress2;
            if (progress2 < 1.0E-5f) {
                f = 0.0f;
            }
            this.progressLabel.setText(((Object) StringFormatter.compactNumberWithPrecision(f * 100.0f, 2)) + "%");
            this.progressBar.setWidth(f * 700.0f);
        }

        @Override // com.prineside.tdi2.utils.simulation.SimLogListener
        public final void onLog(byte b2, String str) {
            Threads.i().runOnMainThread(() -> {
                switch (b2) {
                    case 1:
                        this.lastLogMessage.setColor(MaterialColor.GREY.P500);
                        break;
                    case 2:
                        this.lastLogMessage.setColor(MaterialColor.AMBER.P500);
                        break;
                    case 3:
                        this.lastLogMessage.setColor(MaterialColor.RED.P500);
                        break;
                }
                this.lastLogMessage.setText(str);
            });
        }
    }
}
