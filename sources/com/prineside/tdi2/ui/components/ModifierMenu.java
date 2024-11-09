package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.game.BuildingRemove;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.events.game.ModifierCustomButtonPress;
import com.prineside.tdi2.events.game.ModifierPlace;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/ModifierMenu.class */
public class ModifierMenu implements Disposable {

    /* renamed from: b, reason: collision with root package name */
    private final SideMenu.SideMenuContainer f3367b;
    private boolean c;
    private final Label d;
    private final Label e;
    private final Group f;
    private final SellButton h;
    private ComplexButton i;
    private InputAdapter j;
    private Label k;
    private boolean m;
    private final GameSystemProvider n;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3366a = TLog.forClass(ModifierMenu.class);
    private static final Vector2 p = new Vector2();
    private ObjectMap<String, Object> g = new ObjectMap<>();
    private int l = -1;
    private final _SideMenuListener o = new _SideMenuListener(this, 0);
    private final Runnable q = () -> {
        c();
        a(true);
    };
    private final Runnable r = () -> {
        updateCustomButton();
    };

    public ModifierMenu(SideMenu sideMenu, final GameSystemProvider gameSystemProvider) {
        this.n = gameSystemProvider;
        this.f3367b = sideMenu.createContainer("ModifierMenu");
        this.f3367b.setName("modifier_menu_container");
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        this.d = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.d.setSize(520.0f, 26.0f);
        this.d.setPosition(40.0f, 994.0f + scaledViewportHeight);
        this.f3367b.addActor(this.d);
        this.e = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.e.setSize(520.0f, 100.0f);
        this.e.setPosition(40.0f, 884.0f + scaledViewportHeight);
        this.e.setAlignment(10);
        this.e.setWrap(true);
        this.f3367b.addActor(this.e);
        this.f = new Group();
        this.f.setTransform(false);
        this.f.setSize(600.0f, 400.0f);
        this.f.setPosition(0.0f, 160.0f);
        this.f3367b.addActor(this.f);
        this.i = new ComplexButton("", Game.i.assetManager.getLabelStyle(30), () -> {
            if (this.m) {
                cancelUsingCustomButton();
                return;
            }
            Modifier b2 = b();
            if (b2 != null && b2.hasCustomButton()) {
                if (!b2.isCustomButtonNeedMapPoint()) {
                    gameSystemProvider.modifier.customModifierButtonAction(b2, 0, 0);
                    updateCustomButton();
                } else {
                    startUsingCustomButton();
                }
            }
        });
        this.i.setLabel(80.0f, 20.0f, 200.0f, 40.0f, 8);
        this.i.label.setWrap(true);
        this.i.icon.setSize(40.0f, 40.0f);
        this.i.icon.setPosition(20.0f, 20.0f);
        this.i.setPosition(40.0f, 40.0f);
        this.i.setSize(309.0f, 80.0f);
        this.i.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 80.0f, 309.0f, 80.0f, 283.0f, 0.0f})), 0.0f, 0.0f, 309.0f, 80.0f);
        this.f3367b.addActor(this.i);
        this.k = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.k.setSize(192.0f, 16.0f);
        this.k.setPosition(368.0f, 132.0f);
        this.k.setAlignment(16);
        this.k.setColor(MaterialColor.RED.P500);
        this.k.setVisible(false);
        this.f3367b.addActor(this.k);
        this.h = new SellButton(() -> {
            Modifier b2 = b();
            if (b2 != null) {
                gameSystemProvider.modifier.sellModifierAction(b2);
            }
        });
        this.h.setPosition(368.0f, 40.0f);
        this.f3367b.addActor(this.h);
        this.j = new InputAdapter() { // from class: com.prineside.tdi2.ui.components.ModifierMenu.1
            @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
            public boolean touchUp(int i, int i2, int i3, int i4) {
                if (gameSystemProvider._input == null) {
                    return false;
                }
                if (ModifierMenu.this.b() != null && ModifierMenu.this.m) {
                    ModifierMenu.p.set(i, i2);
                    gameSystemProvider._input.getCameraController().screenToMap(ModifierMenu.p);
                    if (ModifierMenu.this.m) {
                        gameSystemProvider.modifier.customModifierButtonAction(ModifierMenu.this.b(), (int) ModifierMenu.p.x, (int) ModifierMenu.p.y);
                        ModifierMenu.this.updateCustomButton();
                    }
                }
                ModifierMenu.this.cancelUsingCustomButton();
                return false;
            }
        };
        sideMenu.addListener(this.o);
        gameSystemProvider.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            Tile selectedTile = gameSystemProvider._gameMapSelection.getSelectedTile();
            if (selectedTile != null && selectedTile.type == TileType.PLATFORM && ((PlatformTile) selectedTile).building != null && ((PlatformTile) selectedTile).building.buildingType == BuildingType.MODIFIER) {
                c();
                a(true);
            } else {
                a(false);
            }
        }).setDescription("ModifierMenu - shows or hides itself if tile is selected");
        gameSystemProvider.events.getListeners(ModifierPlace.class).add(modifierPlace -> {
            if (modifierPlace.getModifier().getTile() == gameSystemProvider._gameMapSelection.getSelectedTile()) {
                Game.i.uiManager.runOnStageActOnce(this.q);
            }
        });
        gameSystemProvider.events.getListeners(BuildingRemove.class).add(buildingRemove -> {
            if (buildingRemove.getBuilding().buildingType == BuildingType.MODIFIER && buildingRemove.getOldTile() == gameSystemProvider._gameMapSelection.getSelectedTile()) {
                a(false);
            }
        });
        gameSystemProvider.events.getListeners(ModifierCustomButtonPress.class).add(modifierCustomButtonPress -> {
            Game.i.uiManager.runOnStageActOnce(this.r);
        });
    }

    public void startUsingCustomButton() {
        if (this.m) {
            f3366a.e("been using custom button, canceling", new Object[0]);
            cancelUsingCustomButton();
        }
        this.m = true;
        this.n._input.setupInputMultiplexer(true, true, false).addProcessor(this.j);
        updateCustomButton();
    }

    public void updateCustomButton() {
        Modifier b2 = b();
        this.i.setVisible(false);
        if (b2 != null && b2.hasCustomButton()) {
            this.i.setVisible(true);
            b2.updateCustomButton(this.i, this.m);
        }
    }

    public void cancelUsingCustomButton() {
        this.n._input.enableAllInput();
        this.m = false;
        updateCustomButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Modifier b() {
        Tile selectedTile = this.n._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.PLATFORM) {
            PlatformTile platformTile = (PlatformTile) selectedTile;
            if (platformTile.building != null && platformTile.building.buildingType == BuildingType.MODIFIER) {
                return (Modifier) platformTile.building;
            }
            return null;
        }
        return null;
    }

    public boolean isVisible() {
        return this.c;
    }

    public void draw(float f) {
        Modifier b2;
        if (this.c && (b2 = b()) != null) {
            b2.fillModifierMenu(this.f, this.g);
            int ceil = MathUtils.ceil(b2.getTimeTillSellAvailable());
            int i = ((31 + (b2.isSellAvailable() ? 1 : 0)) * 31) + ceil;
            if (i != this.l) {
                this.l = i;
                int sellPrice = b2.getSellPrice();
                if (b2.isSellAvailable()) {
                    this.h.setPrice(sellPrice);
                    this.h.setColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700);
                    this.k.setVisible(false);
                } else {
                    this.h.setPrice((int) (sellPrice * 0.75f));
                    this.h.setColors(MaterialColor.GREY.P800, MaterialColor.GREY.P900, MaterialColor.GREY.P700);
                    this.k.setText(StringFormatter.digestTime(ceil));
                    this.k.setVisible(true);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.l = -1;
        this.g.clear();
        this.f.clearChildren();
        Tile selectedTile = this.n._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.PLATFORM && ((PlatformTile) selectedTile).building != null && ((PlatformTile) selectedTile).building.buildingType == BuildingType.MODIFIER) {
            this.f3367b.setLabelOverTitleTilePos(selectedTile);
            Modifier modifier = (Modifier) ((PlatformTile) selectedTile).building;
            this.d.setText(Game.i.modifierManager.getFactory(modifier.type).getTitle());
            CharSequence description = Game.i.modifierManager.getFactory(modifier.type).getDescription(this.n.gameValue);
            if (new GlyphLayout(Game.i.assetManager.getFont(24), description, Color.WHITE, this.e.getWidth(), 8, true).height > 150.0f) {
                this.e.setStyle(new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
            } else {
                this.e.setStyle(new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
            }
            this.e.setText(description);
        }
        updateCustomButton();
    }

    private void a(boolean z) {
        if (this.c != z) {
            this.c = z;
            if (z) {
                this.f3367b.show();
                c();
            } else {
                this.g.clear();
                this.f3367b.hide();
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/ModifierMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(ModifierMenu modifierMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            ModifierMenu.this.c();
        }
    }
}
