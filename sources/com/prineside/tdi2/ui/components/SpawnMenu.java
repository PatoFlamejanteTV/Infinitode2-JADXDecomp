package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.EnemyGroup;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.events.game.WaveStatusChange;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.ui.shared.WavesTimelineOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/SpawnMenu.class */
public class SpawnMenu implements Disposable, Listener<EnemySpawn> {
    public final SideMenu sideMenu;
    public final SideMenu.SideMenuContainer container;
    private boolean c;
    private Label d;
    private Group e;
    private Group f;
    private ComplexButton g;
    private ObjectMap<EnemyGroup, Label> h = new ObjectMap<>();
    private final GameSystemProvider i;

    /* renamed from: a, reason: collision with root package name */
    private static final Color f3400a = new Color(808464639);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3401b = new Color(623191551);
    private static final StringBuilder j = new StringBuilder();

    public SpawnMenu(SideMenu sideMenu, GameSystemProvider gameSystemProvider) {
        this.i = gameSystemProvider;
        this.sideMenu = sideMenu;
        this.container = sideMenu.createContainer("SpawnMenu");
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        Label label = new Label(Game.i.localeManager.i18n.get("tile_name_SPAWN").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        label.setSize(460.0f, 26.0f);
        label.setPosition(40.0f, 994.0f + scaledViewportHeight);
        this.container.addActor(label);
        this.d = new Label("250%", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.d.setSize(100.0f, 26.0f);
        this.d.setAlignment(16);
        this.d.setPosition(460.0f, 994.0f + scaledViewportHeight);
        this.container.addActor(this.d);
        Label label2 = new Label(Game.i.localeManager.i18n.get("tile_description_SPAWN"), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        label2.setSize(420.0f, 100.0f);
        label2.setPosition(40.0f, 884.0f + scaledViewportHeight);
        label2.setAlignment(10);
        label2.setWrap(true);
        this.container.addActor(label2);
        Label label3 = new Label(Game.i.localeManager.i18n.get("difficulty").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        label3.setSize(100.0f, 100.0f);
        label3.setPosition(460.0f, 884.0f + scaledViewportHeight);
        label3.setAlignment(18);
        label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.container.addActor(label3);
        Group group = new Group();
        group.setTransform(false);
        group.setName("spawn_menu_details");
        this.container.addActor(group);
        Label label4 = new Label(Game.i.localeManager.i18n.get("enemies_that_can_be_spawned"), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        label4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label4.setPosition(40.0f, 906.0f + scaledViewportHeight);
        label4.setSize(100.0f, 17.0f);
        group.addActor(label4);
        this.e = new Group();
        this.e.setTransform(false);
        this.e.setPosition(0.0f, 840.0f + scaledViewportHeight);
        this.e.setSize(600.0f, 64.0f);
        group.addActor(this.e);
        Label label5 = new Label(Game.i.localeManager.i18n.get("enemies_by_wave").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        label5.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label5.setPosition(40.0f, 820.0f + scaledViewportHeight);
        label5.setSize(100.0f, 17.0f);
        group.addActor(label5);
        Label label6 = new Label(Game.i.localeManager.i18n.get("density"), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label6.setPosition(270.0f, 774.0f + scaledViewportHeight);
        label6.setSize(110.0f, 32.0f);
        label6.setAlignment(1);
        group.addActor(label6);
        Image image = new Image(Game.i.assetManager.getDrawable("icon-heart"));
        image.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        image.setSize(32.0f, 32.0f);
        image.setPosition(419.0f, 774.0f + scaledViewportHeight);
        group.addActor(image);
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-count"));
        image2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        image2.setSize(32.0f, 32.0f);
        image2.setPosition(528.0f, 774.0f + scaledViewportHeight);
        group.addActor(image2);
        this.f = new Group();
        this.f.setTransform(false);
        this.f.setPosition(0.0f, 0.0f);
        this.f.setSize(600.0f, 766.0f + scaledViewportHeight);
        group.addActor(this.f);
        this.g = new ComplexButton(Game.i.localeManager.i18n.get("waves"), Game.i.assetManager.getLabelStyle(30), () -> {
            if (gameSystemProvider.gameState.basicLevelName != null) {
                WavesTimelineOverlay.i().setConfiguration(gameSystemProvider.wave.generateWavesTimelineConfigurationBasicLevel(Game.i.basicLevelManager.getLevel(gameSystemProvider.gameState.basicLevelName), gameSystemProvider.map.getMap(), gameSystemProvider.wave.wave == null ? 1 : gameSystemProvider.wave.wave.waveNumber));
            } else if (gameSystemProvider.gameState.userMapId != null) {
                WavesTimelineOverlay.i().setConfiguration(gameSystemProvider.wave.generateWavesTimelineConfigurationUserMap(Game.i.userMapManager.getUserMap(gameSystemProvider.gameState.userMapId), gameSystemProvider.map.getMap(), gameSystemProvider.wave.wave == null ? 1 : gameSystemProvider.wave.wave.waveNumber, gameSystemProvider.wave.bossWaves));
            } else {
                return;
            }
            gameSystemProvider.gameState.getNonAnimatedGameSpeed();
            WavesTimelineOverlay.i().setVisible(true);
        });
        this.g.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME), 0.0f, 0.0f, 296.0f, 80.0f);
        this.g.setSize(296.0f, 80.0f);
        this.g.setIconPositioned(Game.i.assetManager.getDrawable("icon-wave"), 20.0f, 16.0f, 40.0f, 40.0f);
        this.g.setLabel(76.0f, 20.0f, 100.0f, 40.0f, 8);
        this.g.setPosition(304.0f, 40.0f);
        group.addActor(this.g);
        sideMenu.addListener(new _SideMenuListener(this, (byte) 0));
        gameSystemProvider.events.getListeners(EnemySpawn.class).add(this).setDescription("Updates menu if some enemy has spawned from the selected spawn");
        gameSystemProvider.events.getListeners(WaveStatusChange.class).add(waveStatusChange -> {
            if (this.c) {
                update();
            }
        });
        gameSystemProvider.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            Tile selectedTile = gameSystemProvider._gameMapSelection.getSelectedTile();
            if (selectedTile != null && selectedTile.type == TileType.SPAWN) {
                update();
                a(true);
            } else {
                a(false);
            }
        });
        this.container.hide();
    }

    private void a(boolean z) {
        if (this.c != z) {
            this.c = z;
            if (z) {
                this.container.show();
                update();
            } else {
                this.container.hide();
            }
        }
    }

    public void update() {
        float f;
        float f2;
        String str;
        Tile selectedTile = this.i._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.SPAWN) {
            this.container.setLabelOverTitleTilePos(selectedTile);
            SpawnTile spawnTile = (SpawnTile) selectedTile;
            int round = StrictMath.round(spawnTile.difficulty);
            j.setLength(0);
            j.append(round).append('%');
            this.d.setText(j);
            if (round < 100) {
                this.d.setColor(MaterialColor.GREEN.P500);
            } else if (round > 100) {
                this.d.setColor(MaterialColor.RED.P500);
            } else {
                this.d.setColor(MaterialColor.AMBER.P500);
            }
            this.e.clearChildren();
            int i = spawnTile.getAllowedEnemies().size;
            if (i * 64.0f <= 520.0f) {
                f = ((520.0f - (i * 64.0f)) / 2.0f) + 40.0f;
                f2 = 64.0f;
            } else {
                f = 40.0f;
                f2 = 456.0f / (i - 1);
            }
            Array.ArrayIterator<SpawnTile.AllowedEnemyConfig> it = spawnTile.getAllowedEnemies().iterator();
            while (it.hasNext()) {
                final SpawnTile.AllowedEnemyConfig next = it.next();
                Image image = new Image(this.i.enemy.getTexture(next.enemyType));
                image.setSize(32.0f, 32.0f);
                image.setPosition(f + 16.0f, 16.0f);
                image.setTouchable(Touchable.enabled);
                image.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.SpawnMenu.1
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f3, float f4) {
                        SpawnMenu.this.i._gameUi.newEnemyOverlay.show(next.enemyType);
                    }
                });
                this.e.addActor(image);
                f += f2;
            }
            this.f.clearChildren();
            this.h.clear();
            int i2 = this.i.wave.wave == null ? 0 : this.i.wave.wave.waveNumber;
            float height = this.f.getHeight();
            for (int i3 = 0; i3 <= 10 && height >= 100.0f; i3++) {
                int i4 = i3 + i2;
                if (i4 > 0) {
                    boolean z = i4 == i2;
                    Image image2 = new Image(Game.i.assetManager.getDrawable("icon-wave"));
                    image2.setPosition(40.0f, height - 42.0f);
                    image2.setSize(32.0f, 32.0f);
                    this.f.addActor(image2);
                    j.setLength(0);
                    j.append(i4);
                    Label label = new Label(j, new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
                    label.setSize(100.0f, 32.0f);
                    label.setPosition(80.0f, height - 42.0f);
                    this.f.addActor(label);
                    Label label2 = null;
                    if (i3 == 0) {
                        label2 = new Label(Game.i.localeManager.i18n.get("current"), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                    } else if (i3 == 1) {
                        label2 = new Label(Game.i.localeManager.i18n.get("next"), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                    }
                    if (label2 != null) {
                        label2.setSize(80.0f, 32.0f);
                        label2.setPosition(40.0f, height - 76.0f);
                        this.f.addActor(label2);
                    }
                    Array<EnemyGroup.SpawnEnemyGroup> array = spawnTile.enemySpawnQueues.get(i3);
                    if (array.size == 0) {
                        Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image3.setSize(440.0f, 52.0f);
                        image3.setPosition(160.0f, height - 52.0f);
                        image3.setColor(z ? f3400a : f3401b);
                        this.f.addActor(image3);
                        Label label3 = new Label(Game.i.localeManager.i18n.get("no_enemies"), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                        label3.setSize(100.0f, 52.0f);
                        label3.setPosition(176.0f, height - 52.0f);
                        label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        this.f.addActor(label3);
                        height -= 108.0f;
                    } else {
                        for (int i5 = 0; i5 < array.size; i5++) {
                            EnemyGroup.SpawnEnemyGroup spawnEnemyGroup = array.get(i5);
                            Image image4 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                            image4.setSize(440.0f, 52.0f);
                            image4.setPosition(160.0f, height - 52.0f);
                            image4.setColor(z ? f3400a : f3401b);
                            this.f.addActor(image4);
                            Image image5 = new Image(this.i.enemy.getTexture(spawnEnemyGroup.getEnemyType()));
                            image5.setSize(40.0f, 40.0f);
                            image5.setPosition(183.0f, height - 46.0f);
                            this.f.addActor(image5);
                            if (spawnEnemyGroup.interval <= 0.25f) {
                                str = "icon-density-high";
                            } else if (spawnEnemyGroup.interval >= 1.0f) {
                                str = "icon-density-low";
                            } else {
                                str = "icon-density-medium";
                            }
                            Image image6 = new Image(Game.i.assetManager.getDrawable(str));
                            image6.setSize(32.0f, 32.0f);
                            image6.setPosition(309.0f, height - 42.0f);
                            this.f.addActor(image6);
                            Label label4 = new Label(StringFormatter.compactNumber(spawnEnemyGroup.health, false), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
                            label4.setSize(110.0f, 52.0f);
                            label4.setAlignment(1);
                            label4.setPosition(380.0f, height - 52.0f);
                            this.f.addActor(label4);
                            Label label5 = new Label(StringFormatter.compactNumber(spawnEnemyGroup.count - spawnEnemyGroup.getSpawnedCount(), false), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
                            label5.setSize(100.0f, 52.0f);
                            label5.setAlignment(16);
                            label5.setPosition(460.0f, height - 52.0f);
                            this.f.addActor(label5);
                            if (z) {
                                this.h.put(spawnEnemyGroup, label5);
                            }
                            height -= 56.0f;
                        }
                        height -= 56.0f;
                    }
                }
            }
            this.g.setVisible(this.i.wave.getWaveGenerator() == null);
        }
    }

    private void a() {
        Tile selectedTile;
        if (this.i.wave.wave != null && (selectedTile = this.i._gameMapSelection.getSelectedTile()) != null && selectedTile.type == TileType.SPAWN) {
            Array<EnemyGroup.SpawnEnemyGroup> array = ((SpawnTile) selectedTile).enemySpawnQueues.get(0);
            for (int i = 0; i < array.size; i++) {
                Label label = this.h.get(array.get(i));
                if (label != null) {
                    label.setText(StringFormatter.compactNumber(r0.count - r0.getSpawnedCount(), false));
                }
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    @Override // com.prineside.tdi2.events.Listener
    public void handleEvent(EnemySpawn enemySpawn) {
        if (this.c && enemySpawn.getEnemy().spawnTile == this.i._gameMapSelection.getSelectedTile()) {
            a();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/SpawnMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(SpawnMenu spawnMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            SpawnMenu.this.update();
        }
    }
}
