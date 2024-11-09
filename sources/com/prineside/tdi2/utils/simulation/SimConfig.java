package com.prineside.tdi2.utils.simulation;

import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.systems.GameStateSystem;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/SimConfig.class */
public final class SimConfig {
    public DifficultyMode difficultyMode = DifficultyMode.NORMAL;
    public int difficultyModeMultiplier = 100;
    public GameStateSystem.GameMode gameMode = GameStateSystem.GameMode.BASIC_LEVELS;
    public String basicLevelName = "1.1";
    public String userMapName = "test";
    public boolean lootBoost;
    public boolean rarityBoost;
    public long startTimestamp;

    public final String getShortDescription() {
        return this.gameMode + " / " + this.difficultyMode + " / " + (this.gameMode == GameStateSystem.GameMode.BASIC_LEVELS ? this.basicLevelName : this.userMapName);
    }

    public final SimConfig cpy() {
        SimConfig simConfig = new SimConfig();
        simConfig.difficultyMode = this.difficultyMode;
        simConfig.difficultyModeMultiplier = this.difficultyModeMultiplier;
        simConfig.gameMode = this.gameMode;
        simConfig.basicLevelName = this.basicLevelName;
        simConfig.userMapName = this.userMapName;
        simConfig.lootBoost = this.lootBoost;
        simConfig.rarityBoost = this.rarityBoost;
        simConfig.startTimestamp = this.startTimestamp;
        return simConfig;
    }

    public static GameSystemProvider createProgressSnapshotAndInitGSP(SimConfig simConfig) {
        return initGSP(simConfig, Game.i.progressManager.createProgressSnapshotForState());
    }

    public static GameSystemProvider initGSP(SimConfig simConfig, ProgressManager.ProgressSnapshotForState progressSnapshotForState) {
        GameSystemProvider gameSystemProvider = new GameSystemProvider(new GameSystemProvider.SystemsConfig(GameSystemProvider.SystemsConfig.Setup.GAME, true));
        gameSystemProvider.createSystems();
        BasicLevel basicLevel = null;
        UserMap userMap = null;
        if (simConfig.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
            BasicLevel level = Game.i.basicLevelManager.getLevel(simConfig.basicLevelName);
            basicLevel = level;
            if (level == null) {
                throw new IllegalArgumentException("Basic level '" + simConfig.basicLevelName + "' not exists");
            }
        } else {
            userMap = Game.i.userMapManager.getUserMap(simConfig.userMapName);
        }
        GameScreen.configureSystemsBeforeSetup(gameSystemProvider, null, true, simConfig.lootBoost, simConfig.rarityBoost, simConfig.startTimestamp, basicLevel, userMap, simConfig.difficultyMode, simConfig.difficultyModeMultiplier, simConfig.gameMode, userMap == null ? null : Game.i.userMapManager.getDefaultBosses(), progressSnapshotForState, Game.i.progressManager.getInventoryStatistics(), null);
        gameSystemProvider.setupSystems();
        gameSystemProvider.postSetupSystems();
        return gameSystemProvider;
    }
}
