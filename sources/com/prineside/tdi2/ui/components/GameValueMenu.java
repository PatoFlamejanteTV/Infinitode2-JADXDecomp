package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.tiles.GameValueTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/GameValueMenu.class */
public class GameValueMenu {

    /* renamed from: a, reason: collision with root package name */
    private final SideMenu f3302a;

    /* renamed from: b, reason: collision with root package name */
    private final SideMenu.SideMenuContainer f3303b;
    private LimitedWidthLabel c;
    private Label d;
    private Label e;
    private Label f;
    private GameSystemProvider g;
    private final _SideMenuListener h = new _SideMenuListener(this, 0);

    static {
        new StringBuilder();
    }

    public GameValueMenu(SideMenu sideMenu, GameSystemProvider gameSystemProvider) {
        this.g = gameSystemProvider;
        this.f3302a = sideMenu;
        this.f3303b = sideMenu.createContainer("GameValueMenu");
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        Label label = new Label(Game.i.localeManager.i18n.get("tile_name_GAME_VALUE").toUpperCase(), Game.i.assetManager.getLabelStyle(36));
        label.setSize(460.0f, 26.0f);
        label.setPosition(40.0f, 994.0f + scaledViewportHeight);
        this.f3303b.addActor(label);
        Label label2 = new Label(Game.i.localeManager.i18n.get("tile_description_GAME_VALUE"), Game.i.assetManager.getLabelStyle(24));
        label2.setSize(420.0f, 100.0f);
        label2.setPosition(40.0f, 884.0f + scaledViewportHeight);
        label2.setAlignment(10);
        label2.setWrap(true);
        this.f3303b.addActor(label2);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(600.0f, 52.0f);
        image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image.setPosition(0.0f, 872.0f + scaledViewportHeight);
        this.f3303b.addActor(image);
        this.c = new LimitedWidthLabel("", 24, 21, 440.0f);
        this.c.setPosition(40.0f, 872.0f + scaledViewportHeight);
        this.c.setSize(100.0f, 52.0f);
        this.f3303b.addActor(this.c);
        this.d = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.d.setPosition(0.0f, 872.0f + scaledViewportHeight);
        this.d.setAlignment(16);
        this.d.setSize(560.0f, 52.0f);
        this.f3303b.addActor(this.d);
        this.e = new Label(Game.i.localeManager.i18n.get("overwrites_other_effects"), Game.i.assetManager.getLabelStyle(21));
        this.e.setPosition(40.0f, 816.0f + scaledViewportHeight);
        this.e.setAlignment(8);
        this.e.setSize(560.0f, 52.0f);
        this.e.setColor(MaterialColor.ORANGE.P500);
        this.f3303b.addActor(this.e);
        this.f = new Label(Game.i.localeManager.i18n.get("gv_tile_final_multiplier"), Game.i.assetManager.getLabelStyle(21));
        this.f.setPosition(40.0f, 816.0f + scaledViewportHeight);
        this.f.setAlignment(8);
        this.f.setSize(560.0f, 52.0f);
        this.f.setColor(MaterialColor.ORANGE.P500);
        this.f3303b.addActor(this.f);
        sideMenu.addListener(this.h);
        gameSystemProvider.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            Tile selectedTile = gameSystemProvider._gameMapSelection.getSelectedTile();
            if (selectedTile != null && selectedTile.type == TileType.GAME_VALUE && ((GameValueTile) selectedTile).getGameValueType() != GameValueType.DUMMY) {
                a();
                a(true);
            } else {
                a(false);
            }
        });
        this.f3303b.hide();
    }

    private void a(boolean z) {
        if (z) {
            this.f3303b.show();
            a();
        } else {
            this.f3303b.hide();
            this.f3302a.hideSideTooltip();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        StringBuilder formatEffectValue;
        Tile selectedTile = this.g._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.GAME_VALUE) {
            this.f3303b.setLabelOverTitleTilePos(selectedTile);
            GameValueTile gameValueTile = (GameValueTile) selectedTile;
            this.c.setText(Game.i.gameValueManager.getTitle(gameValueTile.getGameValueType()));
            this.d.clearListeners();
            if (gameValueTile.isFinalMultiplier()) {
                this.f.setVisible(true);
                this.e.setVisible(false);
                if (gameValueTile.getDelta() == 0.0d) {
                    this.d.setText(Game.i.localeManager.i18n.get("gv_bonus_disabled"));
                    this.d.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.GameValueMenu.1
                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                        public void clicked(InputEvent inputEvent, float f, float f2) {
                            Dialog.i().showAlert(Game.i.localeManager.i18n.get("gv_bonus_disabled_explanation"));
                        }
                    });
                    return;
                } else {
                    StringBuilder formatEffectValue2 = Game.i.gameValueManager.formatEffectValue(gameValueTile.getDelta(), GameValueManager.ValueUnits.UNITS);
                    formatEffectValue = formatEffectValue2;
                    formatEffectValue2.setCharAt(0, 'x');
                }
            } else {
                this.f.setVisible(false);
                this.e.setVisible(gameValueTile.isOverwrite());
                formatEffectValue = Game.i.gameValueManager.formatEffectValue(gameValueTile.getDelta(), Game.i.gameValueManager.getUnits(gameValueTile.getGameValueType()));
                if (gameValueTile.isOverwrite()) {
                    formatEffectValue.setCharAt(0, '=');
                }
            }
            this.d.setText(formatEffectValue);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/GameValueMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(GameValueMenu gameValueMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            GameValueMenu.this.a();
        }
    }
}
