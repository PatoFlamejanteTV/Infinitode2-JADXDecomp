package com.prineside.tdi2.ui.shared.stateDebugger.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.global.Render;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.Window;
import com.prineside.tdi2.ui.shared.StateDebugger;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.MaterialColor;
import com.vladsch.flexmark.util.html.Attribute;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/stateDebugger/enemies/EnemyViewer.class */
public class EnemyViewer extends Window {
    private final Listener<Render> n;
    private final GameSystemProvider o;
    private final Enemy p;
    private Table q;

    private static Window.WindowStyle d() {
        Window.WindowStyle createDefaultWindowStyle = Game.i.assetManager.createDefaultWindowStyle();
        createDefaultWindowStyle.resizeable = true;
        createDefaultWindowStyle.inheritWidgetMinSize = true;
        return createDefaultWindowStyle;
    }

    public EnemyViewer(GameSystemProvider gameSystemProvider, Enemy enemy) {
        super(d());
        this.n = this::a;
        this.o = gameSystemProvider;
        this.p = enemy;
        this.minWidth = 400.0f;
        this.minHeight = 200.0f;
        setTitle("Enemy #" + enemy.id);
        this.q = new Table();
        a(new Render(0.0f));
    }

    @Override // com.prineside.tdi2.ui.actors.Window
    public void showAtPointAligned(float f, float f2, int i) {
        super.showAtPointAligned(f, f2, i);
        Game.EVENTS.getListeners(Render.class).add(this.n).setName("EnemyViewer_" + this.p.id).setDescription("Updates enemy properties view");
        fitToContentSimple();
    }

    private void a(Render render) {
        if (this.o.isDisposed()) {
            close();
            return;
        }
        SpriteBatch spriteBatch = Game.i.renderingManager.batch;
        CameraController cameraController = this.o._input.cameraController;
        Vector2 vector2 = new Vector2(getWidth() * 0.5f, getHeight() * 0.5f);
        localToStageCoordinates(vector2);
        cameraController.stageToMap(vector2);
        this.o._render.prepareBatch(spriteBatch, false);
        DrawUtils.texturedLineC(Game.i.renderingManager.batch, Game.i.assetManager.getBlankWhiteTextureRegion(), vector2.x, vector2.y, this.p.drawPosition.x, this.p.drawPosition.y, 2.0f, MaterialColor.CYAN.P500.toFloatBits(), MaterialColor.CYAN.P500.toFloatBits());
        spriteBatch.end();
        this.main.clear();
        this.q.clear();
        this.main.add(this.q).grow().pad(8.0f, 12.0f, 12.0f, 12.0f);
        Label.LabelStyle labelStyle = Game.i.assetManager.getLabelStyle(18);
        boolean isRegistered = this.p.isRegistered();
        if (!isRegistered) {
            this.q.add((Table) new Label("No longer registered", labelStyle)).colspan(2).growX();
            this.q.row();
        }
        this.q.add((Table) new Label(Attribute.ID_ATTR, labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.id).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("angle", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.angle).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("type", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.type).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("bounty", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.bounty).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("otherEnemiesNearby", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.otherEnemiesNearby).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("healthBarScale", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.healthBarScale).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("graphPath", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.graphPath).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("pathSearches", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.pathSearches).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("velocity", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.velocity).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("ignorePathfinding", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.ignorePathfinding).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("sideShiftIndex", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.sideShiftIndex).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("passedTiles", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.passedTiles).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("sumPassedTiles", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.sumPassedTiles).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("existsTime", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.existsTime).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("spawnTile", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.spawnTile).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("doesNotDisableTowers", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.doesNotDisableTowers).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("ignoredOnGameOverNoEnemies", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.ignoredOnGameOverNoEnemies).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("canNotBeDisoriented", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.canNotBeDisoriented).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("wave", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.wave).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("ignoredByAutoWaveCall", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.ignoredByAutoWaveCall).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("buffFreezingPercent", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.buffFreezingPercent).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("health", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.getHealth()).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("maxHealth", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.maxHealth).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("drawAngle", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.drawAngle).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("drawScale", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.drawScale).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("healthBarInvisible", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.healthBarInvisible).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("drawPosition", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.drawPosition).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("invisible", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.invisible).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("disabled", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.disabled).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("chasedByCrusher", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.chasedByCrusher).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("gaveMiningSpeedForGauss", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.gaveMiningSpeedForGauss).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("loot", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.loot).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("thrownBackBy", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.thrownBackBy).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("ignitionProgress", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.ignitionProgress).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("ignitionIncreasedLastFrame", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.ignitionIncreasedLastFrame).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("notAffectsWaveKillCounter", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.notAffectsWaveKillCounter).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("lowAimPriority", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.lowAimPriority).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("caughtByCrushersSet", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.caughtByCrushersSet).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("totalCatchesByCrushers", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.totalCatchesByCrushers).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("stunDebuffStats", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.stunDebuffStats).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("attachedParticles", labelStyle));
        this.q.add((Table) new Label(this.p.getAttachedParticles() == null ? "null" : new StringBuilder().append(this.p.getAttachedParticles().size).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("getKillExp()", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.getKillExp()).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("killScore", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.killScore).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("bounty", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.bounty).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("buffSnowballHits", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.buffSnowballHits).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("multishotTowerHits", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.multishotTowerHits).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("wasAimedAtWithChainReactionBuff", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.wasAimedAtWithChainReactionBuff).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("wasStunnedByGauss", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.wasStunnedByGauss).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("getAllUserData()", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.getAllUserData()).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        this.q.add((Table) new Label("getSize()", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.getSize()).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        if (isRegistered) {
            this.q.add((Table) new Label("getColor()", labelStyle));
            this.q.add((Table) new Label(this.p.getColor().toString(), labelStyle)).growX();
            this.q.row();
            StateDebugger.tableRowSep(this.q, 2);
        }
        if (isRegistered) {
            this.q.add((Table) new Label("getPosition()", labelStyle));
            this.q.add((Table) new Label(this.p.getPosition().toString(), labelStyle)).growX();
            this.q.row();
            StateDebugger.tableRowSep(this.q, 2);
        }
        if (isRegistered) {
            this.q.add((Table) new Label("getSpeed()", labelStyle));
            this.q.add((Table) new Label(new StringBuilder().append(this.p.getSpeed()).toString(), labelStyle)).growX();
            this.q.row();
            StateDebugger.tableRowSep(this.q, 2);
        }
        if (isRegistered) {
            this.q.add((Table) new Label("getBuffedSpeed()", labelStyle));
            this.q.add((Table) new Label(new StringBuilder().append(this.p.getBuffedSpeed()).toString(), labelStyle)).growX();
            this.q.row();
            StateDebugger.tableRowSep(this.q, 2);
        }
        this.q.add((Table) new Label("buffFreezingLightningLengthBonus", labelStyle));
        this.q.add((Table) new Label(new StringBuilder().append(this.p.buffFreezingLightningLengthBonus).toString(), labelStyle)).growX();
        this.q.row();
        StateDebugger.tableRowSep(this.q, 2);
        StateDebugger.tableRowSep(this.q, 2);
        this.q.row();
        this.q.add().width(1.0f).growY();
        clampWindowPosition();
    }

    @Override // com.prineside.tdi2.ui.actors.Window
    public void close() {
        super.close();
        Game.EVENTS.getListeners(Render.class).remove(this.n);
    }
}
