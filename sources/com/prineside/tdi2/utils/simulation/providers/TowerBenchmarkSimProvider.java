package com.prineside.tdi2.utils.simulation.providers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.screens.SimulationScreen;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.simulation.SimTypeProvider;
import com.prineside.tdi2.utils.simulation.TowerBenchmarkSim;
import com.prineside.tdi2.utils.simulation.TowersBenchmarkScenario;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/providers/TowerBenchmarkSimProvider.class */
public final class TowerBenchmarkSimProvider implements SimTypeProvider {
    @Override // com.prineside.tdi2.utils.simulation.SimTypeProvider
    public final String getName() {
        return "TowerBenchmark";
    }

    @Override // com.prineside.tdi2.utils.simulation.SimTypeProvider
    public final void prepareSimForm(SimulationScreen simulationScreen) {
        Table table = simulationScreen.formTable;
        Array array = new Array(new TowerBenchmarkSim.TowerBenchmarkXpConfig[]{new TowerBenchmarkSim.TowerBenchmarkXpConfig("1|NONE", 1, new int[0]), new TowerBenchmarkSim.TowerBenchmarkXpConfig("4|NONE", 4, new int[0]), new TowerBenchmarkSim.TowerBenchmarkXpConfig("4|1", 4, new int[]{0}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("4|2", 4, new int[]{1}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("4|3", 4, new int[]{2}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("7|NONE", 7, new int[0]), new TowerBenchmarkSim.TowerBenchmarkXpConfig("7|1/2", 7, new int[]{0, 1}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("7|1/3", 7, new int[]{0, 2}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("7|2/3", 7, new int[]{1, 2}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("10|NONE", 10, new int[0]), new TowerBenchmarkSim.TowerBenchmarkXpConfig("10|1", 10, new int[]{0}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("10|2", 10, new int[]{1}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("10|3", 10, new int[]{2}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("10|1/2", 10, new int[]{0, 1}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("10|1/3", 10, new int[]{0, 2}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("10|2/3", 10, new int[]{1, 2}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|NONE", 20, new int[0]), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|1", 20, new int[]{0}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|2", 20, new int[]{1}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|3", 20, new int[]{2}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|1/2", 20, new int[]{0, 1}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|1/3", 20, new int[]{0, 2}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|2/3", 20, new int[]{1, 2}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|ULT", 20, new int[]{4}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|1/ULT", 20, new int[]{0, 4}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|2/ULT", 20, new int[]{1, 4}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|3/ULT", 20, new int[]{2, 4}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|1/2/ULT", 20, new int[]{0, 1, 4}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|1/3/ULT", 20, new int[]{0, 2, 4}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|2/3/ULT", 20, new int[]{1, 2, 4}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|PWR", 20, new int[]{5}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|1/PWR", 20, new int[]{0, 5}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|2/PWR", 20, new int[]{1, 5}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|3/PWR", 20, new int[]{2, 5}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|1/2/PWR", 20, new int[]{0, 1, 5}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|1/3/PWR", 20, new int[]{0, 2, 5}), new TowerBenchmarkSim.TowerBenchmarkXpConfig("20|2/3/PWR", 20, new int[]{1, 2, 5})});
        final TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("no-research-starting", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), false));
        Label label = new Label("Research tree mode", Game.i.assetManager.getLabelStyle(18));
        label.setColor(MaterialColor.GREY.P500);
        table.add((Table) label).growX().row();
        final SelectBox selectBox = new SelectBox(Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getDebugFont(false), true));
        selectBox.setItems(TowerBenchmarkSim.ResearchTreeMode.values());
        selectBox.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.utils.simulation.providers.TowerBenchmarkSimProvider.1
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                switch (AnonymousClass2.f3974a[((TowerBenchmarkSim.ResearchTreeMode) selectBox.getSelected()).ordinal()]) {
                    case 1:
                        textFieldXPlatform.setText("no-research-");
                        return;
                    case 2:
                        textFieldXPlatform.setText("normal-");
                        return;
                    case 3:
                        textFieldXPlatform.setText("endless-");
                        return;
                    default:
                        return;
                }
            }
        });
        table.add((Table) selectBox).growX().padBottom(10.0f).row();
        Label label2 = new Label("Benchmark name", Game.i.assetManager.getLabelStyle(18));
        label2.setColor(MaterialColor.GREY.P500);
        table.add((Table) label2).growX().row();
        table.add((Table) textFieldXPlatform).growX().padBottom(10.0f).row();
        Label label3 = new Label("Thread count", Game.i.assetManager.getLabelStyle(18));
        label3.setColor(MaterialColor.GREY.P500);
        table.add((Table) label3).growX().row();
        TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform("8", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), true));
        table.add((Table) textFieldXPlatform2).growX().padBottom(10.0f).row();
        Label label4 = new Label("Runs per combo", Game.i.assetManager.getLabelStyle(18));
        label4.setColor(MaterialColor.GREY.P500);
        table.add((Table) label4).growX().row();
        TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform("2", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), false));
        table.add((Table) textFieldXPlatform3).growX().padBottom(10.0f).row();
        Label label5 = new Label("If set to 2+ runs, different start timestamp will be used and results will be averaged", Game.i.assetManager.getLabelStyle(18));
        label5.setColor(MaterialColor.GREY.P800);
        label5.setWrap(true);
        table.add((Table) label5).growX().row();
        Label label6 = new Label("Tower types", Game.i.assetManager.getLabelStyle(18));
        label6.setColor(MaterialColor.GREY.P500);
        table.add((Table) label6).growX().row();
        LabelToggleButton[] labelToggleButtonArr = new LabelToggleButton[TowerType.values.length];
        for (int i = 0; i < TowerType.values.length; i++) {
            TowerType towerType = TowerType.values[i];
            LabelToggleButton labelToggleButton = new LabelToggleButton(towerType.name(), towerType != TowerType.FREEZING, 21, 24.0f, false, null);
            labelToggleButtonArr[i] = labelToggleButton;
            table.add(labelToggleButton).growX().padBottom(3.0f).row();
        }
        Label label7 = new Label("Wave count", Game.i.assetManager.getLabelStyle(18));
        label7.setColor(MaterialColor.GREY.P500);
        table.add((Table) label7).growX().row();
        TextFieldXPlatform textFieldXPlatform4 = new TextFieldXPlatform("300", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), true));
        table.add((Table) textFieldXPlatform4).growX().padBottom(10.0f).row();
        Label label8 = new Label("Upgrade levels", Game.i.assetManager.getLabelStyle(18));
        label8.setColor(MaterialColor.GREY.P500);
        table.add((Table) label8).growX().row();
        Table table2 = new Table();
        table.add(table2).growX().padBottom(10.0f).row();
        Label label9 = new Label("Extra towers", Game.i.assetManager.getLabelStyle(18));
        label9.setColor(MaterialColor.GREY.P500);
        table.add((Table) label9).growX().row();
        LabelToggleButton[] labelToggleButtonArr2 = new LabelToggleButton[TowersBenchmarkScenario.ExtraTowers.values().length];
        for (int i2 = 0; i2 < TowersBenchmarkScenario.ExtraTowers.values().length; i2++) {
            LabelToggleButton labelToggleButton2 = new LabelToggleButton(TowersBenchmarkScenario.ExtraTowers.values()[i2].name(), true, 21, 24.0f, false, null);
            labelToggleButtonArr2[i2] = labelToggleButton2;
            table.add(labelToggleButton2).growX().padBottom(3.0f).row();
        }
        Label label10 = new Label("XP level / ability configs", Game.i.assetManager.getLabelStyle(18));
        label10.setColor(MaterialColor.GREY.P500);
        table.add((Table) label10).growX().row();
        LabelToggleButton[] labelToggleButtonArr3 = new LabelToggleButton[array.size];
        for (int i3 = 0; i3 < array.size; i3++) {
            LabelToggleButton labelToggleButton3 = new LabelToggleButton(((TowerBenchmarkSim.TowerBenchmarkXpConfig) array.get(i3)).name, true, 21, 24.0f, false, null);
            labelToggleButtonArr3[i3] = labelToggleButton3;
            table.add(labelToggleButton3).growX().padBottom(3.0f).row();
        }
        int[] iArr = {0, 1, 3, 5, 7, 10};
        LabelToggleButton[] labelToggleButtonArr4 = new LabelToggleButton[6];
        for (int i4 = 0; i4 < 6; i4++) {
            int i5 = iArr[i4];
            LabelToggleButton labelToggleButton4 = new LabelToggleButton(new StringBuilder().append(i5).toString(), i5 == 0 || i5 == 5 || i5 == 10, 21, 24.0f, false, null);
            labelToggleButtonArr4[i4] = labelToggleButton4;
            Cell padBottom = table2.add(labelToggleButton4).growX().padBottom(3.0f);
            if (i4 % 2 == 1) {
                padBottom.padLeft(20.0f);
                padBottom.row();
            } else {
                padBottom.padRight(20.0f);
            }
        }
        RectButton rectButton = new RectButton("Set defaults", Game.i.assetManager.getLabelStyle(24), () -> {
            selectBox.setSelected(TowerBenchmarkSim.ResearchTreeMode.FULL_NORMAL);
            textFieldXPlatform2.setText("32");
            textFieldXPlatform3.setText("2");
            textFieldXPlatform4.setText("300");
            labelToggleButtonArr4[0].setEnabled(true);
            labelToggleButtonArr4[1].setEnabled(false);
            labelToggleButtonArr4[2].setEnabled(false);
            labelToggleButtonArr4[3].setEnabled(true);
            labelToggleButtonArr4[4].setEnabled(false);
            labelToggleButtonArr4[5].setEnabled(true);
            labelToggleButtonArr2[TowersBenchmarkScenario.ExtraTowers.FREEZING.ordinal()].setEnabled(true);
            labelToggleButtonArr2[TowersBenchmarkScenario.ExtraTowers.NONE.ordinal()].setEnabled(true);
            labelToggleButtonArr2[TowersBenchmarkScenario.ExtraTowers.BLAST.ordinal()].setEnabled(false);
            labelToggleButtonArr2[TowersBenchmarkScenario.ExtraTowers.FREEZING_BLAST.ordinal()].setEnabled(true);
        });
        rectButton.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.GRAY);
        table.add((Table) rectButton).height(40.0f).growX().row();
        table.add((Table) new RectButton("Add simulation", Game.i.assetManager.getLabelStyle(24), () -> {
            Array array2 = new Array(true, 1, TowerType.class);
            for (int i6 = 0; i6 < TowerType.values.length; i6++) {
                if (labelToggleButtonArr[i6].isEnabled()) {
                    array2.add(TowerType.values[i6]);
                }
            }
            IntArray intArray = new IntArray();
            intArray.add(Integer.parseInt(textFieldXPlatform4.getText()));
            IntArray intArray2 = new IntArray();
            for (int i7 = 0; i7 < iArr.length; i7++) {
                int i8 = iArr[i7];
                if (labelToggleButtonArr4[i7].isEnabled()) {
                    intArray2.add(i8);
                }
            }
            Array array3 = new Array(true, 1, TowersBenchmarkScenario.ExtraTowers.class);
            for (int i9 = 0; i9 < TowersBenchmarkScenario.ExtraTowers.values().length; i9++) {
                if (labelToggleButtonArr2[i9].isEnabled()) {
                    array3.add(TowersBenchmarkScenario.ExtraTowers.values()[i9]);
                }
            }
            Array array4 = new Array(true, 1, TowerBenchmarkSim.TowerBenchmarkXpConfig.class);
            for (int i10 = 0; i10 < array.size; i10++) {
                if (labelToggleButtonArr3[i10].isEnabled()) {
                    array4.add((TowerBenchmarkSim.TowerBenchmarkXpConfig) array.get(i10));
                }
            }
            simulationScreen.addSimulation(new TowerBenchmarkSim(simulationScreen.simConfig, textFieldXPlatform.getText(), Integer.parseInt(textFieldXPlatform2.getText()), Integer.parseInt(textFieldXPlatform3.getText()), (TowerBenchmarkSim.ResearchTreeMode) selectBox.getSelected(), array4, intArray, intArray2, array3, array2));
        })).height(40.0f).growX().row();
    }

    /* renamed from: com.prineside.tdi2.utils.simulation.providers.TowerBenchmarkSimProvider$2, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/providers/TowerBenchmarkSimProvider$2.class */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3974a = new int[TowerBenchmarkSim.ResearchTreeMode.values().length];

        static {
            try {
                f3974a[TowerBenchmarkSim.ResearchTreeMode.NO_RESEARCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3974a[TowerBenchmarkSim.ResearchTreeMode.FULL_NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3974a[TowerBenchmarkSim.ResearchTreeMode.FULL_ENDLESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
