package com.prineside.tdi2.systems;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.lib.jse.CoerceJavaToLua;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.actions.ScriptAction;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.script.ScriptEnvironment;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.tiles.ScriptTile;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/ScriptSystem.class */
public final class ScriptSystem extends GameSystem {
    public static DeepClassComparator<ScriptSystem> CLASS_COMPARATOR;

    @NAGS
    @Null
    public ScriptEnvironment scriptEnvironment;

    static {
        TLog.forClass(ScriptSystem.class);
        CLASS_COMPARATOR = new DeepClassComparator<ScriptSystem>() { // from class: com.prineside.tdi2.systems.ScriptSystem.1
            @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
            public Class<ScriptSystem> forClass() {
                return ScriptSystem.class;
            }

            @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
            public void compare(ScriptSystem scriptSystem, ScriptSystem scriptSystem2, DeepClassComparisonConfig deepClassComparisonConfig) {
                deepClassComparisonConfig.addPrefix(".scriptEnvironment");
                SyncChecker.compareObjects(scriptSystem.scriptEnvironment, scriptSystem2.scriptEnvironment, deepClassComparisonConfig);
                deepClassComparisonConfig.popPrefix(1);
            }
        };
        SyncChecker.CLASS_COMPARATORS.add(CLASS_COMPARATOR);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.scriptEnvironment, ScriptEnvironment.class);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.scriptEnvironment = (ScriptEnvironment) kryo.readObjectOrNull(input, ScriptEnvironment.class);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.scriptEnvironment = new ScriptEnvironment();
        this.scriptEnvironment.initialize();
        this.scriptEnvironment.getGlobals().set("S", CoerceJavaToLua.coerce(this.S));
        if (this.S.CFG.setup == GameSystemProvider.SystemsConfig.Setup.MAP_EDITOR) {
            this.scriptEnvironment.loadScriptsInDir("scripts/map-editor");
            return;
        }
        this.scriptEnvironment.loadScriptsInDir("scripts/game");
        DelayedRemovalArray<Tile> allTiles = this.S.map.getMap().getAllTiles();
        for (int i = 0; i < allTiles.size; i++) {
            Tile tile = this.S.map.getMap().getAllTiles().items[i];
            if (tile.type == TileType.SCRIPT) {
                ScriptTile scriptTile = (ScriptTile) tile;
                this.S.script.scriptEnvironment.executeLua(scriptTile.getScript(), "ScriptTile[" + scriptTile.getX() + ":" + scriptTile.getY() + "]");
            }
        }
    }

    public final void runScriptAction(String str) {
        if (this.S.gameState == null) {
            throw new IllegalStateException("GameStateSystem is not registered");
        }
        if (this.S.gameState.replayMode) {
            this.scriptEnvironment.executeLua(str, "scriptAction");
        } else {
            this.S.gameState.pushActionNextUpdate(new ScriptAction(str));
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        StateSystem.ActionsArray currentUpdateActions;
        if (this.scriptEnvironment != null && this.S.gameState != null && !Config.isHeadless() && (currentUpdateActions = this.S.gameState.getCurrentUpdateActions()) != null) {
            for (int i = 0; i < currentUpdateActions.size; i++) {
                Action action = currentUpdateActions.actions[i];
                if (action.getType() == ActionType.S) {
                    if (!Config.isHeadless()) {
                        this.scriptEnvironment.executeLua(((ScriptAction) action).script, "scriptAction");
                    } else {
                        throw new IllegalStateException("Run script actions are not allowed in headless mode");
                    }
                }
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Script";
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        if (this.scriptEnvironment == null) {
            return;
        }
        this.scriptEnvironment.dispose();
        this.scriptEnvironment = null;
        super.dispose();
    }
}
