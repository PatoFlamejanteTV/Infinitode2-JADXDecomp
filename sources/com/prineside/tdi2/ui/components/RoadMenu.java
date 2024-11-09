package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.SideMenu;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/RoadMenu.class */
public class RoadMenu implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private final SideMenu.SideMenuContainer f3395a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3396b;
    private Group c;
    private GameSystemProvider d;
    private final _SideMenuListener e = new _SideMenuListener(this, 0);

    static {
        new StringBuilder();
    }

    public RoadMenu(SideMenu sideMenu, GameSystemProvider gameSystemProvider) {
        this.d = gameSystemProvider;
        this.f3395a = sideMenu.createContainer("RoadMenu");
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        Label label = new Label(Game.i.localeManager.i18n.get("tile_name_ROAD").toUpperCase(), Game.i.assetManager.getLabelStyle(36));
        label.setSize(460.0f, 26.0f);
        label.setPosition(40.0f, 994.0f + scaledViewportHeight);
        this.f3395a.addActor(label);
        Label label2 = new Label(Game.i.localeManager.i18n.get("tile_description_ROAD"), Game.i.assetManager.getLabelStyle(24));
        label2.setSize(420.0f, 100.0f);
        label2.setPosition(40.0f, 884.0f + scaledViewportHeight);
        label2.setAlignment(10);
        label2.setWrap(true);
        this.f3395a.addActor(label2);
        this.c = new Group();
        this.c.setTransform(false);
        this.c.setSize(600.0f, 1080.0f);
        this.c.setTouchable(Touchable.disabled);
        this.f3395a.addActor(this.c);
        sideMenu.addListener(this.e);
        gameSystemProvider.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            Tile selectedTile = gameSystemProvider._gameMapSelection.getSelectedTile();
            if (selectedTile != null && selectedTile.type == TileType.ROAD) {
                a();
                a(true);
            } else {
                a(false);
            }
        }).setDescription("RoadMenu - shows or hides itself if road tile is selected");
        this.f3395a.hide();
    }

    private void a(boolean z) {
        if (this.f3396b != z) {
            this.f3396b = z;
            if (z) {
                this.f3395a.show();
            } else {
                this.f3395a.hide();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Tile selectedTile = this.d._gameMapSelection.getSelectedTile();
        this.c.clear();
        if (selectedTile != null && selectedTile.type == TileType.ROAD) {
            this.f3395a.setLabelOverTitleTilePos(selectedTile);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/RoadMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(RoadMenu roadMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            RoadMenu.this.a();
        }
    }
}
