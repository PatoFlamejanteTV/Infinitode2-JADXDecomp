package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.events.mapEditor.MapEditorSelectionChange;
import com.prineside.tdi2.items.GateItem;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.InputVoid;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorItemInfoMenu.class */
public class MapEditorItemInfoMenu implements Disposable {
    public static final int MENU_WIDTH = 400;
    public static final int MENU_CONTENT_PADDING = 24;
    public static final int MENU_CONTENT_PADDING_BOTTOM = 12;
    public static final int MENU_CONTENT_MAX_HEIGHT = 900;
    public static final int MENU_CONTENT_WIDTH = 352;
    public static final float ROW_DEFAULT_HEIGHT = 32.0f;
    private final GameSystemProvider c;
    private boolean d;
    private final PaddedImageButton e;
    private final LimitedWidthLabel f;
    public final Label itemPosition;
    private Image g;
    private Label h;
    private final Group i;
    private final Table j;
    private final Table k;
    private final ScrollPane l;
    public final Table itemDetailsContainer;
    private final PaddedImageButton m;
    public SelectBox.SelectBoxStyle selectBoxStyle;
    public TextField.TextFieldStyle textFieldStyle;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3341a = TLog.forClass(MapEditorItemInfoMenu.class);
    public static final Color ROW_BACKGROUND = new Color(72);

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3342b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 102, "MapEditorItemInfoMenu");
    public ObjectMap<String, Object> customData = new ObjectMap<>();
    private boolean n = false;
    public boolean expandCodeEditor = false;
    private final DelayedRemovalArray<MapEditorTileInfoMenuListener> o = new DelayedRemovalArray<>();

    public MapEditorItemInfoMenu(GameSystemProvider gameSystemProvider) {
        this.c = gameSystemProvider;
        gameSystemProvider.events.getListeners(MapEditorSelectionChange.class).add(mapEditorSelectionChange -> {
            update();
        }).setName("MapEditorItemInfoMenu");
        this.selectBoxStyle = Game.i.assetManager.getSelectBoxStyle(Game.i.assetManager.getFont(24), true);
        this.textFieldStyle = Game.i.assetManager.getTextFieldStyle(24);
        Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.childrenOnly);
        this.f3342b.getTable().add((Table) group).size(400.0f, 96.0f).expand().bottom().left().padLeft(160.0f);
        this.j = new Table();
        this.j.setBackground(Game.i.assetManager.getQuad("ui.components.mapEditorItemInfo.panelBackground"));
        this.j.setWidth(400.0f);
        group.addActor(this.j);
        this.k = new Table();
        this.l = new ScrollPane(this.k, Game.i.assetManager.getScrollPaneStyle(0.0f));
        this.l.setScrollingDisabled(true, false);
        this.l.addListener(new ScrollPaneFocusListener(this, (byte) 0));
        this.j.add((Table) this.l).width(400.0f).fillY();
        this.itemDetailsContainer = new Table();
        this.k.add(this.itemDetailsContainer).width(352.0f).padLeft(24.0f).padRight(24.0f).padTop(24.0f).padBottom(12.0f);
        Group group2 = new Group();
        group2.setTransform(false);
        group2.setTouchable(Touchable.enabled);
        group2.addListener(new InputVoid());
        group2.setSize(400.0f, 96.0f);
        group.addActor(group2);
        Image image = new Image(Game.i.assetManager.getQuad("ui.components.mapEditorItemInfo.panel"));
        image.setSize(400.0f, 96.0f);
        group2.addActor(image);
        this.i = new Group();
        this.i.setTransform(false);
        this.i.setSize(64.0f, 64.0f);
        this.i.setPosition(16.0f, 16.0f);
        group2.addActor(this.i);
        this.e = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-bottom"), () -> {
            setMinimized(!this.d);
        }, Color.WHITE, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P500);
        this.e.setSize(400.0f, 96.0f);
        this.e.setIconSize(32.0f, 32.0f);
        this.e.setIconPosition(336.0f, 42.0f);
        group2.addActor(this.e);
        this.m = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-edit"), () -> {
            if (gameSystemProvider._mapEditor.selection.count() == 1) {
                Item currentItem = gameSystemProvider._mapEditor.selection.getCurrentItem();
                boolean z = false;
                if (gameSystemProvider._mapEditor.selection.count() == 1) {
                    if (currentItem instanceof TileItem) {
                        Tile tile = ((TileItem) currentItem).tile;
                        if (tile.sameAs(gameSystemProvider.map.getMap().getTile(tile.getX(), tile.getY()))) {
                            z = true;
                        }
                    } else if (currentItem instanceof GateItem) {
                        Gate gate = ((GateItem) currentItem).gate;
                        if (gate.sameAs(gameSystemProvider.map.getMap().getGate(gate.getX(), gate.getY(), gate.isLeftSide()))) {
                            z = true;
                        }
                    }
                }
                if (z) {
                    f3341a.i("editing map item", new Object[0]);
                    ItemCreationOverlay.i().showForItemListenable(currentItem, item -> {
                        f3341a.i("item changed: " + item, new Object[0]);
                        notifySelectedElementChanged();
                        update();
                    }, false);
                } else {
                    f3341a.i("editing inventory item", new Object[0]);
                    ItemCreationOverlay.i().showForItem(currentItem);
                }
            }
        }, Color.WHITE, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P500);
        this.m.setShadow(Game.i.assetManager.getDrawable("circle"), 52.0f, 6.0f, 38.0f, 38.0f, new Color(724249531));
        this.m.setIconSize(24.0f, 24.0f);
        this.m.setIconPosition(56.0f, 16.0f);
        this.m.setSize(96.0f, 96.0f);
        this.m.setPosition(0.0f, 0.0f);
        this.m.setTouchable(Touchable.enabled);
        this.m.setVisible(false);
        group2.addActor(this.m);
        this.f = new LimitedWidthLabel("", 30, 21, 230.0f);
        this.f.setPosition(96.0f, 48.0f);
        this.f.setSize(230.0f, 22.0f);
        this.f.setTouchable(Touchable.disabled);
        group2.addActor(this.f);
        Table table = new Table();
        table.setPosition(96.0f, 18.0f);
        table.setSize(286.0f, 24.0f);
        table.setTouchable(Touchable.disabled);
        group2.addActor(table);
        if (Game.i.gameValueManager.getSnapshot().getBooleanValue(GameValueType.PRESTIGE_MODE) || Game.i.progressManager.isDeveloperModeEnabled()) {
            this.g = new Image(Game.i.assetManager.getDrawable("icon-crown"));
            this.g.setColor(MaterialColor.LIGHT_BLUE.P400);
            table.add((Table) this.g).size(24.0f).padRight(8.0f);
            this.h = new Label("350 mP", Game.i.assetManager.getLabelStyle(21));
            this.h.setColor(MaterialColor.LIGHT_BLUE.P400);
            table.add((Table) this.h);
        }
        table.add().height(1.0f).growX();
        this.itemPosition = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.itemPosition.setColor(MaterialColor.AMBER.P500);
        this.itemPosition.setAlignment(1);
        table.add((Table) this.itemPosition).width(60.0f);
        setVisible(false);
        b();
    }

    public void addListener(MapEditorTileInfoMenuListener mapEditorTileInfoMenuListener) {
        Preconditions.checkNotNull(mapEditorTileInfoMenuListener);
        if (!this.o.contains(mapEditorTileInfoMenuListener, true)) {
            this.o.add(mapEditorTileInfoMenuListener);
        }
    }

    public void removeListener(MapEditorTileInfoMenuListener mapEditorTileInfoMenuListener) {
        Preconditions.checkNotNull(mapEditorTileInfoMenuListener);
        this.o.removeValue(mapEditorTileInfoMenuListener, true);
    }

    public void listRowBg(int i, Table table) {
        if (i % 2 == 0) {
            table.background(Game.i.assetManager.getQuad("ui.components.mapEditorItemInfo.listRowBackgroundOdd"));
        }
    }

    public void notifySelectedElementChanged() {
        this.o.begin();
        for (int i = 0; i < this.o.size; i++) {
            this.o.get(i).selectedElementModified();
        }
        this.o.end();
        this.c._mapEditor.mapChanged = true;
        update();
    }

    public boolean isSelectionFromInventory() {
        return this.c._mapEditor.selection.isFromInventory();
    }

    public void update() {
        if (this.c._mapEditor.selection.count() == 0) {
            setVisible(false);
            return;
        }
        setVisible(true);
        this.i.clearChildren();
        Item currentItem = this.c._mapEditor.selection.count() == 1 ? this.c._mapEditor.selection.getCurrentItem() : null;
        double d = 0.0d;
        if (currentItem != null) {
            this.i.addActor(currentItem.generateIcon(64.0f, false));
            if (ProgressPrefs.i().inventory.isStarred(currentItem)) {
                Image image = new Image(Game.i.assetManager.getDrawable("icon-star"));
                image.setColor(MaterialColor.YELLOW.P500);
                image.setSize(16.0f, 16.0f);
                image.setPosition(56.0f, 56.0f);
                this.i.addActor(image);
            }
            this.f.setText(currentItem.getTitle());
            this.itemPosition.setVisible(true);
            if (currentItem instanceof TileItem) {
                Tile tile = ((TileItem) currentItem).tile;
                d = tile.getPrestigeScore() * 1000.0d;
                this.itemPosition.setText(tile.getX() + ":" + tile.getY());
            } else {
                Gate gate = ((GateItem) currentItem).gate;
                d = gate.getPrestigeScore() * 1000.0d;
                this.itemPosition.setText(gate.getX() + ":" + gate.getY() + SequenceUtils.SPACE + (gate.isLeftSide() ? "L" : "B"));
            }
            if (this.c._mapEditor.selection.isFromInventory()) {
                this.itemPosition.setText(Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-backpack>"));
            }
            this.itemDetailsContainer.clearChildren();
            this.itemDetailsContainer.setWidth(352.0f);
            if (currentItem instanceof TileItem) {
                ((TileItem) currentItem).tile.fillMapEditorMenu(this.itemDetailsContainer, this);
            } else {
                ((GateItem) currentItem).gate.fillMapEditorMenu(this.itemDetailsContainer, this);
            }
            this.m.setVisible(Game.i.progressManager.isDeveloperModeEnabled());
        } else {
            this.itemPosition.setVisible(false);
            this.m.setVisible(false);
            int count = this.c._mapEditor.selection.count();
            Image image2 = new Image(Game.i.assetManager.getQuad("mapeditor.tools.select-rectangle"));
            image2.setSize(56.0f, 56.0f);
            image2.setPosition(4.0f, 4.0f);
            this.i.addActor(image2);
            Label label = new Label(new StringBuilder().append(this.c._mapEditor.selection.count()).toString(), Game.i.assetManager.getLabelStyle(24));
            label.setAlignment(1);
            label.setSize(64.0f, 64.0f);
            label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            this.i.addActor(label);
            this.f.setText(Game.i.localeManager.i18n.format("map_editor_selected_n_elements", Integer.valueOf(count)));
            this.itemDetailsContainer.clearChildren();
            IntRectangle dimensions = this.c._mapEditor.selection.dimensions();
            float f = (dimensions.maxX - dimensions.minX) + 1;
            float f2 = (dimensions.maxY - dimensions.minY) + 1;
            float min = Math.min(352.0f / Math.max(f, f2), 128.0f);
            final float f3 = min / 128.0f;
            this.itemDetailsContainer.add((Table) new Actor() { // from class: com.prineside.tdi2.ui.components.MapEditorItemInfoMenu.1
                @Override // com.prineside.tdi2.scene2d.Actor
                public void draw(Batch batch, float f4) {
                    try {
                        Color color = getColor();
                        batch.setColor(color.r, color.g, color.f888b, color.f889a * f4);
                        MapEditorItemInfoMenu.this.c._mapEditor.selection.draw(batch, getX(), getY(), f3, f3, MapEditorItemInfoMenu.this.c.map.getMap());
                    } catch (Exception unused) {
                    }
                }
            }).size(f * min, f2 * min).row();
        }
        this.o.begin();
        for (int i = 0; i < this.o.size; i++) {
            this.o.get(i).menuUpdated();
        }
        this.o.end();
        this.itemDetailsContainer.invalidate();
        this.k.pack();
        this.n = false;
        if (this.itemDetailsContainer.getHeight() == 0.0f) {
            this.j.setVisible(false);
        } else {
            this.j.setVisible(true);
            float min2 = Math.min(this.itemDetailsContainer.getHeight() + 24.0f + 12.0f, 900.0f);
            if (min2 > 900.0f) {
                this.n = true;
            }
            this.j.setHeight(min2);
            this.l.setHeight(min2);
        }
        if (this.h != null) {
            if (d == 0.0d) {
                this.h.setVisible(false);
                this.g.setVisible(false);
            } else {
                this.h.setVisible(true);
                this.g.setVisible(true);
                this.h.setText(((Object) StringFormatter.commaSeparatedNumber(StrictMath.round(d))) + " mP");
            }
        }
        b();
    }

    private void b() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        if (this.itemDetailsContainer.hasChildren()) {
            this.e.setVisible(true);
            if (this.d) {
                this.e.setIcon(Game.i.assetManager.getDrawable("icon-triangle-top-hollow"));
                this.j.clearActions();
                this.j.addAction(Actions.sequence(Actions.moveTo(0.0f, (-this.j.getHeight()) - 96.0f, 0.15f * f), Actions.hide()));
                return;
            } else {
                this.e.setIcon(Game.i.assetManager.getDrawable("icon-triangle-bottom-hollow"));
                this.j.clearActions();
                this.j.addAction(Actions.sequence(Actions.show(), Actions.moveTo(0.0f, 96.0f, 0.15f * f)));
                return;
            }
        }
        this.e.setVisible(false);
        this.j.clearActions();
        this.j.addAction(Actions.sequence(Actions.moveTo(0.0f, (-this.j.getHeight()) - 96.0f, 0.15f * f), Actions.hide()));
    }

    public void setMinimized(boolean z) {
        this.d = z;
        if (z) {
            Game.i.uiManager.stage.setKeyboardFocus(null);
        }
        b();
    }

    public void setVisible(boolean z) {
        this.f3342b.getTable().setVisible(z);
        if (!z) {
            Game.i.uiManager.stage.setKeyboardFocus(null);
        } else {
            b();
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3342b);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorItemInfoMenu$MapEditorTileInfoMenuListener.class */
    public interface MapEditorTileInfoMenuListener {
        void selectedElementModified();

        void menuUpdated();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorItemInfoMenu$MapEditorTileInfoMenuListener$MapEditorTileInfoMenuListenerAdapter.class */
        public static class MapEditorTileInfoMenuListenerAdapter implements MapEditorTileInfoMenuListener {
            @Override // com.prineside.tdi2.ui.components.MapEditorItemInfoMenu.MapEditorTileInfoMenuListener
            public void selectedElementModified() {
            }

            @Override // com.prineside.tdi2.ui.components.MapEditorItemInfoMenu.MapEditorTileInfoMenuListener
            public void menuUpdated() {
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapEditorItemInfoMenu$ScrollPaneFocusListener.class */
    private class ScrollPaneFocusListener extends InputListener {
        private ScrollPaneFocusListener() {
        }

        /* synthetic */ ScrollPaneFocusListener(MapEditorItemInfoMenu mapEditorItemInfoMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public void enter(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
            if (MapEditorItemInfoMenu.this.n) {
                MapEditorItemInfoMenu.f3341a.i("itemDetailsScroll enter", new Object[0]);
                Game.i.uiManager.stage.setScrollFocus(MapEditorItemInfoMenu.this.l);
            }
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
            if (Game.i.uiManager.stage.getScrollFocus() == MapEditorItemInfoMenu.this.l) {
                MapEditorItemInfoMenu.f3341a.i("itemDetailsScroll exit", new Object[0]);
                Game.i.uiManager.stage.setScrollFocus(null);
            }
        }
    }
}
