package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.ibxm.Module;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.tiles.XmMusicTrackTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/XmMusicTrackMenu.class */
public class XmMusicTrackMenu implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private final SideMenu.SideMenuContainer f3446a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3447b;
    private Table c;
    private GameSystemProvider d;
    private final _SideMenuListener e = new _SideMenuListener(this, 0);

    static {
        new StringBuilder();
    }

    public XmMusicTrackMenu(SideMenu sideMenu, GameSystemProvider gameSystemProvider) {
        this.d = gameSystemProvider;
        this.f3446a = sideMenu.createContainer("XmMusicTrackMenu");
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        Label label = new Label(Game.i.localeManager.i18n.get("tile_name_XM_MUSIC_TRACK").toUpperCase(), Game.i.assetManager.getLabelStyle(36));
        label.setSize(460.0f, 26.0f);
        label.setPosition(40.0f, 994.0f + scaledViewportHeight);
        this.f3446a.addActor(label);
        Label label2 = new Label(Game.i.localeManager.i18n.get("tile_description_XM_MUSIC_TRACK"), Game.i.assetManager.getLabelStyle(24));
        label2.setSize(420.0f, 100.0f);
        label2.setPosition(40.0f, 884.0f + scaledViewportHeight);
        label2.setAlignment(10);
        label2.setWrap(true);
        this.f3446a.addActor(label2);
        this.c = new Table();
        this.c.setSize(600.0f, scaledViewportHeight + 950);
        this.c.setTouchable(Touchable.childrenOnly);
        this.f3446a.addActor(this.c);
        sideMenu.addListener(this.e);
        gameSystemProvider.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            Tile selectedTile = gameSystemProvider._gameMapSelection.getSelectedTile();
            if (selectedTile != null && selectedTile.type == TileType.XM_MUSIC_TRACK) {
                a();
                a(true);
            } else {
                a(false);
            }
        });
        this.f3446a.hide();
    }

    private void a(boolean z) {
        if (this.f3447b != z) {
            this.f3447b = z;
            if (z) {
                this.f3446a.show();
            } else {
                this.f3446a.hide();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Tile selectedTile = this.d._gameMapSelection.getSelectedTile();
        this.c.clear();
        if (selectedTile != null && selectedTile.type == TileType.XM_MUSIC_TRACK) {
            this.f3446a.setLabelOverTitleTilePos(selectedTile);
            XmMusicTrackTile xmMusicTrackTile = (XmMusicTrackTile) selectedTile;
            Table table = new Table();
            this.c.add(table).width(600.0f).height(32.0f).row();
            Color[] idColors = xmMusicTrackTile.getIdColors();
            float length = 600.0f / idColors.length;
            for (Color color : idColors) {
                Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image.setColor(color);
                table.add((Table) image).size(length, 8.0f);
            }
            table.row();
            Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image2.setColor(0.0f, 0.0f, 0.0f, 0.14f);
            table.add((Table) image2).colspan(idColors.length).width(600.0f).height(4.0f);
            Table table2 = new Table();
            this.c.add(table2).width(520.0f).row();
            String titleCached = xmMusicTrackTile.getTitleCached();
            if (titleCached != null) {
                Label label = new Label(titleCached, Game.i.assetManager.getLabelStyle(30));
                label.setColor(MaterialColor.AMBER.P500);
                table2.add((Table) label).growX().padTop(15.0f).row();
                Array.ArrayIterator<Module.TrackInfoEntry> it = xmMusicTrackTile.getDescriptionCached().iterator();
                while (it.hasNext()) {
                    final Module.TrackInfoEntry next = it.next();
                    final LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(next.value, 24, 18, 540.0f);
                    switch (next.type) {
                        case AUTHOR:
                            Label label2 = new Label(Game.i.localeManager.i18n.get("music_track_author") + ":", Game.i.assetManager.getLabelStyle(18));
                            label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                            table2.add((Table) label2).growX().padTop(15.0f).row();
                            break;
                        case GROUP:
                            Label label3 = new Label(Game.i.localeManager.i18n.get("music_track_group") + ":", Game.i.assetManager.getLabelStyle(18));
                            label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                            table2.add((Table) label3).growX().padTop(15.0f).row();
                            break;
                        case LINK:
                            limitedWidthLabel.setTouchable(Touchable.enabled);
                            limitedWidthLabel.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.XmMusicTrackMenu.1
                                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                                public void clicked(InputEvent inputEvent, float f, float f2) {
                                    Gdx.f881net.openURI(next.getCompleteLink());
                                }
                            });
                            limitedWidthLabel.addListener(new InputListener(this) { // from class: com.prineside.tdi2.ui.components.XmMusicTrackMenu.2
                                @Override // com.prineside.tdi2.scene2d.InputListener
                                public void enter(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
                                    limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P300);
                                }

                                @Override // com.prineside.tdi2.scene2d.InputListener
                                public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
                                    limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P500);
                                }
                            });
                            limitedWidthLabel.setText(((Object) limitedWidthLabel.getText()) + Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-link-out>").toString());
                            limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P500);
                            break;
                    }
                    limitedWidthLabel.setAlignment(8);
                    table2.add((Table) limitedWidthLabel).growX().row();
                }
            }
        }
        this.c.row();
        this.c.add().width(1.0f).growY().row();
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/XmMusicTrackMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(XmMusicTrackMenu xmMusicTrackMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            XmMusicTrackMenu.this.a();
        }
    }
}
