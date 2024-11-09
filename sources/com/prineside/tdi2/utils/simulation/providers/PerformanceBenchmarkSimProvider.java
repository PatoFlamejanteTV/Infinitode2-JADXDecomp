package com.prineside.tdi2.utils.simulation.providers;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.screens.SimulationScreen;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.simulation.PerformanceBenchmarkSim;
import com.prineside.tdi2.utils.simulation.SimTypeProvider;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/providers/PerformanceBenchmarkSimProvider.class */
public final class PerformanceBenchmarkSimProvider implements SimTypeProvider {
    @Override // com.prineside.tdi2.utils.simulation.SimTypeProvider
    public final String getName() {
        return "PerformanceBenchmark";
    }

    @Override // com.prineside.tdi2.utils.simulation.SimTypeProvider
    public final void prepareSimForm(SimulationScreen simulationScreen) {
        Table table = simulationScreen.formTable;
        Label label = new Label("Thread count", Game.i.assetManager.getLabelStyle(18));
        label.setColor(MaterialColor.GREY.P500);
        table.add((Table) label).growX().row();
        TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("8", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), true));
        table.add((Table) textFieldXPlatform).growX().padBottom(10.0f).row();
        Label label2 = new Label("Repeat count", Game.i.assetManager.getLabelStyle(18));
        label2.setColor(MaterialColor.GREY.P500);
        table.add((Table) label2).growX().row();
        TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform("8", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), false));
        table.add((Table) textFieldXPlatform2).growX().padBottom(10.0f).row();
        Label label3 = new Label("Total count of jobs is thread count X repeat count. Running 8 threads with 8 repeats is exactly equal to running to 16 threads with 4 repeats - adjust according to your CPU cores", Game.i.assetManager.getLabelStyle(18));
        label3.setColor(MaterialColor.GREY.P800);
        label3.setWrap(true);
        table.add((Table) label3).growX().row();
        Label label4 = new Label("Frame count", Game.i.assetManager.getLabelStyle(18));
        label4.setColor(MaterialColor.GREY.P500);
        table.add((Table) label4).growX().row();
        TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform("400000", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), true));
        table.add((Table) textFieldXPlatform3).growX().padBottom(10.0f).row();
        table.add((Table) new RectButton("Add simulation", Game.i.assetManager.getLabelStyle(24), () -> {
            simulationScreen.addSimulation(new PerformanceBenchmarkSim(simulationScreen.simConfig, Integer.parseInt(textFieldXPlatform.getText()), Integer.parseInt(textFieldXPlatform2.getText()), Integer.parseInt(textFieldXPlatform3.getText())));
        })).height(40.0f).growX().row();
    }
}
