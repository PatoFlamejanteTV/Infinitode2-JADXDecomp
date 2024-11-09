package com.prineside.tdi2.utils.simulation.providers;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.screens.SimulationScreen;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.simulation.BuildTowerScenario;
import com.prineside.tdi2.utils.simulation.JustUpdateScenario;
import com.prineside.tdi2.utils.simulation.SimTypeProvider;
import com.prineside.tdi2.utils.simulation.SyncCheckSim;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/providers/SyncCheckSimProvider.class */
public final class SyncCheckSimProvider implements SimTypeProvider {
    @Override // com.prineside.tdi2.utils.simulation.SimTypeProvider
    public final String getName() {
        return "SyncCheck";
    }

    @Override // com.prineside.tdi2.utils.simulation.SimTypeProvider
    public final void prepareSimForm(SimulationScreen simulationScreen) {
        Table table = simulationScreen.formTable;
        Label label = new Label("Syn check scenario", Game.i.assetManager.getLabelStyle(18));
        label.setColor(MaterialColor.GREY.P500);
        table.add((Table) label).growX().row();
        SelectBox selectBox = new SelectBox(Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getDebugFont(true), true));
        selectBox.setItems("TowersAndAbilities", "JustUpdate");
        table.add((Table) selectBox).growX().padBottom(10.0f).row();
        Label label2 = new Label("Sync thread count", Game.i.assetManager.getLabelStyle(18));
        label2.setColor(MaterialColor.GREY.P500);
        table.add((Table) label2).growX().row();
        TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("2", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), false));
        table.add((Table) textFieldXPlatform).growX().padBottom(10.0f).row();
        Label label3 = new Label("Extra load thread count", Game.i.assetManager.getLabelStyle(18));
        label3.setColor(MaterialColor.GREY.P500);
        table.add((Table) label3).growX().row();
        TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform("2", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), true));
        table.add((Table) textFieldXPlatform2).growX().padBottom(10.0f).row();
        Label label4 = new Label("Frame count", Game.i.assetManager.getLabelStyle(18));
        label4.setColor(MaterialColor.GREY.P500);
        table.add((Table) label4).growX().row();
        TextFieldXPlatform textFieldXPlatform3 = new TextFieldXPlatform("500000", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), false));
        table.add((Table) textFieldXPlatform3).growX().padBottom(10.0f).row();
        Label label5 = new Label("Sync check frame interval", Game.i.assetManager.getLabelStyle(18));
        label5.setColor(MaterialColor.GREY.P500);
        table.add((Table) label5).growX().row();
        Label label6 = new Label("Set to 1 for precise sync check", Game.i.assetManager.getLabelStyle(18));
        label6.setColor(MaterialColor.GREY.P800);
        table.add((Table) label6).growX().row();
        TextFieldXPlatform textFieldXPlatform4 = new TextFieldXPlatform("500", Game.i.assetManager.getTextFieldStyleWithFontAndVariant(Game.i.assetManager.getDebugFont(false), true));
        table.add((Table) textFieldXPlatform4).growX().padBottom(10.0f).row();
        table.add((Table) new RectButton("Add simulation", Game.i.assetManager.getLabelStyle(24), () -> {
            int parseInt = Integer.parseInt(textFieldXPlatform.getText());
            int parseInt2 = Integer.parseInt(textFieldXPlatform2.getText());
            int parseInt3 = Integer.parseInt(textFieldXPlatform4.getText());
            int parseInt4 = Integer.parseInt(textFieldXPlatform3.getText());
            int nextInt = FastRandom.random.nextInt();
            Array array = new Array();
            if (((String) selectBox.getSelected()).equals("TowersAndAbilities")) {
                for (TowerType towerType : TowerType.values) {
                    array.add(new ObjectPair(towerType.name(), new BuildTowerScenario(parseInt4, towerType, new int[0], Tower.AimStrategy.RANDOM, FastRandom.getFairFloat() * 360.0f, 5, nextInt)));
                }
                for (TowerType towerType2 : TowerType.values) {
                    array.add(new ObjectPair(towerType2.name() + "(1st ability)", new BuildTowerScenario(parseInt4, towerType2, new int[]{0}, Tower.AimStrategy.RANDOM, FastRandom.getFairFloat() * 360.0f, 5, nextInt)));
                }
                for (TowerType towerType3 : TowerType.values) {
                    array.add(new ObjectPair(towerType3.name() + "(2nd ability)", new BuildTowerScenario(parseInt4, towerType3, new int[]{1}, Tower.AimStrategy.RANDOM, FastRandom.getFairFloat() * 360.0f, 5, nextInt)));
                }
                for (TowerType towerType4 : TowerType.values) {
                    array.add(new ObjectPair(towerType4.name() + "(3rd ability)", new BuildTowerScenario(parseInt4, towerType4, new int[]{2}, Tower.AimStrategy.RANDOM, FastRandom.getFairFloat() * 360.0f, 5, nextInt)));
                }
                array.shuffle();
            } else {
                array.add(new ObjectPair("JustUpdate", new JustUpdateScenario(parseInt4)));
            }
            simulationScreen.addSimulation(new SyncCheckSim(simulationScreen.simConfig, array, new JustUpdateScenario(500000000), parseInt, parseInt2, parseInt3));
        })).height(40.0f).growX().row();
    }
}
