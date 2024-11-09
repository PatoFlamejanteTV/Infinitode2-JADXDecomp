package com.prineside.tdi2.ui.shared.stateDebugger;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.shared.StateDebugger;
import com.prineside.tdi2.ui.shared.stateDebugger.enemies.EnemyViewer;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/stateDebugger/EnemiesView.class */
public class EnemiesView implements StateDebugger.View {

    /* renamed from: a, reason: collision with root package name */
    private Table f3780a;

    @Null
    public GameSystemProvider S;

    /* renamed from: b, reason: collision with root package name */
    private Integer f3781b;

    public void setSystemProvider(@Null GameSystemProvider gameSystemProvider) {
        this.S = gameSystemProvider;
        rebuildWindow();
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public String getId() {
        return "ENEMIES";
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public String getName() {
        return "Enemies";
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void rebuildWindow() {
        this.f3780a = new Table();
        Table table = StateDebugger.i().contentTable;
        table.clear();
        table.add(this.f3780a).grow();
        onRender();
        StateDebugger.i().window.fitToContent(12, true, false, true);
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void init() {
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void postInit() {
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onShow() {
        if (Game.i.screenManager.getCurrentScreen() instanceof GameScreen) {
            setSystemProvider(((GameScreen) Game.i.screenManager.getCurrentScreen()).S);
        } else {
            setSystemProvider(null);
        }
        StateDebugger.i().renderWindow();
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onHide() {
        setSystemProvider(null);
    }

    @Override // com.prineside.tdi2.ui.shared.StateDebugger.View
    public void onRender() {
        if (this.S != null && this.S.map == null) {
            setSystemProvider(null);
        }
        int a2 = a();
        if (this.f3781b == null || a2 != this.f3781b.intValue()) {
            this.f3781b = Integer.valueOf(a2);
            this.f3780a.clear();
            Label.LabelStyle labelStyle = Game.i.assetManager.getLabelStyle(18);
            if (this.S == null) {
                this.f3780a.add((Table) new Label("GameSystemProvider not found", labelStyle));
            } else {
                Label label = new Label("", labelStyle);
                label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.f3780a.add((Table) label).padLeft(6.0f).padRight(6.0f);
                Label label2 = new Label("ID", labelStyle);
                label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                this.f3780a.add((Table) label2).growX().padRight(6.0f);
                this.f3780a.row();
                StateDebugger.tableRowSep(this.f3780a, 4);
                Array array = new Array(this.S.map.spawnedEnemies);
                array.sort((enemyReference, enemyReference2) -> {
                    return Integer.compare(enemyReference.enemy == null ? -1 : enemyReference.enemy.id, enemyReference2.enemy == null ? -1 : enemyReference2.enemy.id);
                });
                for (int i = 0; i < array.size; i++) {
                    Enemy enemy = ((Enemy.EnemyReference) array.get(i)).enemy;
                    if (enemy != null) {
                        Image image = new Image(enemy.getTexture());
                        LabelButton labelButton = new LabelButton(new StringBuilder().append(enemy.id).toString(), labelStyle, null);
                        labelButton.setClickHandler(() -> {
                            EnemyViewer enemyViewer = new EnemyViewer(this.S, enemy);
                            Game.i.uiManager.addWindow(enemyViewer);
                            enemyViewer.fitToContentSimple();
                            Vector2 localToStageCoordinates = labelButton.localToStageCoordinates(new Vector2());
                            enemyViewer.showAtPointAligned(localToStageCoordinates.x, localToStageCoordinates.y, 1);
                        });
                        labelButton.setAlignment(8);
                        this.f3780a.add((Table) image).size(24.0f).padRight(6.0f);
                        this.f3780a.add((Table) labelButton).growX();
                        this.f3780a.row();
                        StateDebugger.tableRowSep(this.f3780a, 2);
                    }
                }
            }
            this.f3780a.row();
            this.f3780a.add().width(1.0f).growY();
        }
    }

    private int a() {
        int i;
        int i2;
        int i3;
        if (this.S == null) {
            i = -1;
        } else {
            DelayedRemovalArray<Enemy.EnemyReference> delayedRemovalArray = this.S.map.spawnedEnemies;
            i = 31 + delayedRemovalArray.size;
            for (int i4 = 0; i4 < delayedRemovalArray.size; i4++) {
                Enemy enemy = delayedRemovalArray.get(i4).enemy;
                if (enemy != null) {
                    i2 = i * 31;
                    i3 = enemy.hashCode();
                } else {
                    i2 = i * 31;
                    i3 = 1;
                }
                i = i2 + i3;
            }
        }
        return i;
    }
}
