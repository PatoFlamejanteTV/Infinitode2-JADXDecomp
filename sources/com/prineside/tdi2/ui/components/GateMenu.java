package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GateType;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.gates.BarrierTypeGate;
import com.prineside.tdi2.gates.TeleportGate;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/GateMenu.class */
public class GateMenu implements Disposable {
    private final SideMenu.SideMenuContainer c;
    private boolean d;
    private Table e;
    private Label f;
    private Label g;
    private Group h;
    private Group i;
    private Group j;
    private final GameSystemProvider k;
    private final _SideMenuListener l = new _SideMenuListener(this, 0);

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3312a = TLog.forClass(GateMenu.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3313b = new Color(623191551);
    private static final StringBuilder m = new StringBuilder();

    public GateMenu(SideMenu sideMenu, GameSystemProvider gameSystemProvider) {
        this.k = gameSystemProvider;
        this.c = sideMenu.createContainer("GateMenu");
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        this.f = new Label("", Game.i.assetManager.getLabelStyle(36));
        this.f.setSize(460.0f, 26.0f);
        this.f.setPosition(40.0f, 994.0f + scaledViewportHeight);
        this.c.addActor(this.f);
        this.g = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.g.setSize(520.0f, 100.0f);
        this.g.setPosition(40.0f, 884.0f + scaledViewportHeight);
        this.g.setAlignment(10);
        this.g.setWrap(true);
        this.c.addActor(this.g);
        this.h = new Group();
        this.h.setTransform(false);
        this.h.setSize(600.0f, 940.0f + scaledViewportHeight);
        this.c.addActor(this.h);
        this.i = new Group();
        this.i.setTransform(false);
        this.i.setSize(600.0f, 940.0f + scaledViewportHeight);
        this.c.addActor(this.i);
        this.j = new Group();
        this.j.setTransform(false);
        this.j.setSize(600.0f, 940.0f + scaledViewportHeight);
        this.c.addActor(this.j);
        Label label = new Label(Game.i.localeManager.i18n.get("blocked_enemies").toUpperCase(), Game.i.assetManager.getLabelStyle(21));
        label.setSize(100.0f, 16.0f);
        label.setPosition(40.0f, 906.0f + scaledViewportHeight);
        label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.h.addActor(label);
        this.e = new Table();
        ScrollPane scrollPane = new ScrollPane(this.e);
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(600.0f, 890.0f + scaledViewportHeight);
        this.h.addActor(scrollPane);
        sideMenu.addListener(this.l);
        gameSystemProvider.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            if (gameSystemProvider._gameMapSelection.getSelectedGate() != null) {
                a();
                a(true);
            } else {
                a(false);
            }
        });
        this.c.hide();
    }

    private void a(boolean z) {
        if (this.d != z) {
            this.d = z;
            if (z) {
                this.c.show();
            } else {
                this.c.hide();
            }
            f3312a.i(z ? "shown" : "hidden", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.e.clearChildren();
        this.i.setVisible(false);
        this.h.setVisible(false);
        this.j.setVisible(false);
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        Gate selectedGate = this.k._gameMapSelection.getSelectedGate();
        if (selectedGate != null) {
            this.f.setText(Game.i.gateManager.getFactory(selectedGate.getType()).getTitle(selectedGate));
            this.g.setText(Game.i.gateManager.getFactory(selectedGate.getType()).getDescription(selectedGate));
            if (selectedGate.getType() != GateType.BARRIER_TYPE) {
                if (selectedGate.getType() == GateType.TELEPORT) {
                    this.j.setVisible(true);
                    this.j.clearChildren();
                    TeleportGate teleportGate = (TeleportGate) selectedGate;
                    m.setLength(0);
                    m.append(TeleportGate.INDEX_NAMES[teleportGate.index]).append(" (").append(teleportGate.index).append(")");
                    Label label = new Label(m, Game.i.assetManager.getLabelStyle(24));
                    label.setPosition(40.0f, 880.0f + scaledViewportHeight);
                    label.setSize(520.0f, 17.0f);
                    label.setWrap(true);
                    this.j.addActor(label);
                }
            } else {
                this.h.setVisible(true);
                BarrierTypeGate barrierTypeGate = (BarrierTypeGate) selectedGate;
                for (EnemyType enemyType : EnemyType.values) {
                    if (barrierTypeGate.isEnemyBlocked(enemyType)) {
                        Group group = new Group();
                        group.setTransform(false);
                        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image.setSize(600.0f, 52.0f);
                        image.setColor(f3313b);
                        group.addActor(image);
                        Image image2 = new Image(this.k.enemy.getTexture(enemyType));
                        image2.setPosition(48.0f, 6.0f);
                        image2.setSize(40.0f, 40.0f);
                        group.addActor(image2);
                        Label label2 = new Label(Game.i.enemyManager.getFactory(enemyType).getTitle(), Game.i.assetManager.getLabelStyle(24));
                        label2.setSize(100.0f, 52.0f);
                        label2.setPosition(100.0f, 0.0f);
                        group.addActor(label2);
                        this.e.add((Table) group).size(600.0f, 52.0f).padBottom(4.0f).row();
                    }
                }
            }
        }
        this.e.add().expandX().fillX().height(40.0f).row();
        this.e.add().expand().fill();
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/GateMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(GateMenu gateMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            GateMenu.this.a();
        }
    }
}
