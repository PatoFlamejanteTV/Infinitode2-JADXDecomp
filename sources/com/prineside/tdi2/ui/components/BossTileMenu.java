package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.tiles.BossTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/BossTileMenu.class */
public class BossTileMenu {
    private final SideMenu.SideMenuContainer c;
    private boolean d;
    private Table e;
    private Label f;
    private GameSystemProvider g;
    private final _SideMenuListener h = new _SideMenuListener(this, 0);

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3259a = TLog.forClass(BossTileMenu.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3260b = new Color(623191551);
    private static final StringBuilder i = new StringBuilder();

    public BossTileMenu(SideMenu sideMenu, GameSystemProvider gameSystemProvider) {
        this.g = gameSystemProvider;
        this.c = sideMenu.createContainer("TargetMenu");
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        Label label = new Label(Game.i.localeManager.i18n.get("tile_name_BOSS").toUpperCase(), Game.i.assetManager.getLabelStyle(36));
        label.setSize(460.0f, 26.0f);
        label.setPosition(40.0f, 994.0f + scaledViewportHeight);
        this.c.addActor(label);
        Label label2 = new Label(Game.i.localeManager.i18n.get("tile_description_BOSS"), Game.i.assetManager.getLabelStyle(24));
        label2.setSize(420.0f, 100.0f);
        label2.setPosition(40.0f, 884.0f + scaledViewportHeight);
        label2.setAlignment(10);
        label2.setWrap(true);
        this.c.addActor(label2);
        this.f = new Label("", Game.i.assetManager.getLabelStyle(36));
        this.f.setSize(600.0f, 26.0f);
        this.f.setPosition(0.0f, 898.0f + scaledViewportHeight);
        this.f.setAlignment(1);
        this.f.setColor(MaterialColor.GREEN.P500);
        this.c.addActor(this.f);
        Label label3 = new Label(Game.i.localeManager.i18n.get("effect").toUpperCase(), Game.i.assetManager.getLabelStyle(21));
        label3.setSize(100.0f, 16.0f);
        label3.setPosition(40.0f, 846.0f + scaledViewportHeight);
        label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.c.addActor(label3);
        Label label4 = new Label(Game.i.localeManager.i18n.get("value_units").toUpperCase(), Game.i.assetManager.getLabelStyle(21));
        label4.setSize(100.0f, 16.0f);
        label4.setPosition(460.0f, 846.0f + scaledViewportHeight);
        label4.setAlignment(16);
        label4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.c.addActor(label4);
        this.e = new Table();
        ScrollPane scrollPane = new ScrollPane(this.e);
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(600.0f, 830.0f + scaledViewportHeight);
        this.c.addActor(scrollPane);
        sideMenu.addListener(this.h);
        gameSystemProvider.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            Tile selectedTile = gameSystemProvider._gameMapSelection.getSelectedTile();
            if (selectedTile != null && selectedTile.type == TileType.BOSS) {
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
            f3259a.i(z ? "shown" : "hidden", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.e.clearChildren();
        Tile selectedTile = this.g._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.BOSS) {
            this.c.setLabelOverTitleTilePos(selectedTile);
            BossTile bossTile = (BossTile) selectedTile;
            this.f.setText(bossTile.getBossTileTypeName());
            Array<GameValueConfig> gameValues = bossTile.getGameValues();
            if (gameValues.size != 0) {
                for (int i2 = 0; i2 < gameValues.size; i2++) {
                    GameValueConfig gameValueConfig = gameValues.get(i2);
                    Group group = new Group();
                    group.setTransform(false);
                    Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image.setSize(600.0f, 52.0f);
                    image.setColor(f3260b);
                    group.addActor(image);
                    Image image2 = new Image(Game.i.gameValueManager.getStockValueConfig(gameValueConfig.getType()).createIconForBackground(new Color(623191551)));
                    image2.setPosition(40.0f, 6.0f);
                    image2.setSize(40.0f, 40.0f);
                    group.addActor(image2);
                    i.setLength(0);
                    i.append(Game.i.gameValueManager.getTitle(gameValueConfig.getType()));
                    LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(i, 24, 21, 420.0f);
                    limitedWidthLabel.setSize(100.0f, 52.0f);
                    limitedWidthLabel.setPosition(96.0f, 0.0f);
                    group.addActor(limitedWidthLabel);
                    i.setLength(0);
                    GameValueManager.ValueUnits valueUnits = Game.i.gameValueManager.getStockValueConfig(gameValueConfig.getType()).units;
                    if (valueUnits != GameValueManager.ValueUnits.BOOLEAN) {
                        i.append(Game.i.gameValueManager.formatEffectValue(gameValueConfig.getValue(), valueUnits));
                        if (gameValueConfig.isOverwrite()) {
                            i.setCharAt(0, '=');
                        }
                    } else if (gameValueConfig.getValue() == 0.0d) {
                        i.append(Game.i.localeManager.i18n.get("disabled").toLowerCase(Locale.ENGLISH));
                    }
                    Label label = new Label(i, Game.i.assetManager.getLabelStyle(24));
                    label.setPosition(460.0f, 0.0f);
                    label.setSize(100.0f, 52.0f);
                    label.setAlignment(16);
                    group.addActor(label);
                    this.e.add((Table) group).size(600.0f, 52.0f).padBottom(4.0f).row();
                }
            } else {
                Label label2 = new Label(Game.i.localeManager.i18n.get("tile_has_no_effects"), Game.i.assetManager.getLabelStyle(24));
                label2.setAlignment(1);
                this.e.add((Table) label2).size(600.0f, 52.0f).padBottom(4.0f).row();
            }
            BossTile.BossWavesConfig bossWavesConfig = bossTile.getBossWavesConfig();
            if (bossWavesConfig.bossWavePairs.size > 0) {
                Group group2 = new Group();
                group2.setTransform(false);
                this.e.add((Table) group2).size(600.0f, 16.0f).padTop(16.0f).padBottom(16.0f).row();
                Label label3 = new Label(Game.i.localeManager.i18n.get("enemy_name_BOSS").toUpperCase(), Game.i.assetManager.getLabelStyle(21));
                label3.setSize(100.0f, 16.0f);
                label3.setPosition(40.0f, 0.0f);
                label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                group2.addActor(label3);
                Label label4 = new Label(Game.i.localeManager.i18n.get("main_ui_wave_title").toUpperCase(), Game.i.assetManager.getLabelStyle(21));
                label4.setSize(100.0f, 16.0f);
                label4.setPosition(460.0f, 0.0f);
                label4.setAlignment(16);
                label4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                group2.addActor(label4);
                for (int i3 = 0; i3 < bossWavesConfig.bossWavePairs.size; i3++) {
                    BossTile.BossTypeWavePair bossTypeWavePair = bossWavesConfig.bossWavePairs.items[i3];
                    Group group3 = new Group();
                    group3.setTransform(false);
                    Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image3.setSize(600.0f, 52.0f);
                    image3.setColor(f3260b);
                    group3.addActor(image3);
                    EnemyType bossEnemyType = Game.i.enemyManager.getBossEnemyType(bossTypeWavePair.bossType);
                    Image image4 = new Image(this.g.enemy.getTexture(bossEnemyType));
                    image4.setSize(64.0f, 64.0f);
                    image4.setPosition(30.0f, -6.0f);
                    group3.addActor(image4);
                    Label label5 = new Label(Game.i.enemyManager.getFactory(bossEnemyType).getTitle(), Game.i.assetManager.getLabelStyle(21));
                    label5.setSize(64.0f, 52.0f);
                    label5.setPosition(112.0f, 0.0f);
                    group3.addActor(label5);
                    int i4 = bossTypeWavePair.wave + bossWavesConfig.startDelay;
                    String valueOf = String.valueOf(i4);
                    if (bossWavesConfig.repeatCount <= 0) {
                        valueOf = valueOf + ", " + (i4 + bossWavesConfig.cycleLength) + "...";
                    }
                    Label label6 = new Label(valueOf, Game.i.assetManager.getLabelStyle(24));
                    label6.setSize(100.0f, 52.0f);
                    label6.setPosition(460.0f, 0.0f);
                    label6.setAlignment(16);
                    group3.addActor(label6);
                    this.e.add((Table) group3).size(600.0f, 52.0f).padBottom(4.0f).row();
                }
            }
            this.e.add().expandX().fillX().height(40.0f).row();
            this.e.add().expand().fill();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/BossTileMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(BossTileMenu bossTileMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            BossTileMenu.this.a();
        }
    }
}
